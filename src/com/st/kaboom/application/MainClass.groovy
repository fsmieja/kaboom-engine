package com.st.kaboom.application

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.context.annotation.AnnotationConfigApplicationContext

public class MainClass implements CommandLineRunner  {

	/**
	 * start up the container and objects
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
 
		SpringApplication.run(MainClass.class, args)
	}
	
	@Override
	public void run(String... args) {
		if (args.length < 1) {
			println "Usage: java -jar urlpoke.jar <propsFileName>"
			return
		}
		/*
		def config = new ConfigSlurper().parse(new File(args[0]).toURI().toURL())
		def numEngines = 1
		if (config != null) {
			if (config.perf.maxThreads != null)
				numEngines = config.perf.maxThreads
		}
		*/	
		
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		//ctx.scan("com.st.wasup.db")
		ctx.scan("com.st.wasup.application")
		ctx.scan("com.st.wasup.bus")
		ctx.refresh();


		Thread.start {
			ctx.getBean(EngineProcess.class).run(args[0], ctx)
		}

		
		Thread.start {
			ctx.getBean(UrlSelector.class).run(args[0])
		}

		
		Thread.start {
			ctx.getBean(Notifier.class).run(args[0])
		}
		
/*
		AtomicInteger engId = new AtomicInteger()
		numEngines.times {
			Thread.start {
				def i = engId.getAndIncrement()
				ctx.getBean("engine${(i+1)}").run(args[0])
			}
		}
	*/	

		Thread.start {
			ctx.getBean(Persister.class).run(args[0])
		}

		Thread.start {
			ctx.getBean(SystemStats.class).run(args[0])
		}

		Thread.start {
			ctx.getBean(Housekeep.class).run(args[0])
		}

		Thread.start {
			ctx.getBean(SummaryGenerator.class).run(args[0])
		}

	
	}
}
