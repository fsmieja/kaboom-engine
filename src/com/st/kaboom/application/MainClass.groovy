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
			println "Usage: java -jar kaboom.jar <propsFileName>"
			return
		}
		
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		//ctx.scan("com.st.wasup.db")
		ctx.scan("com.st.kaboom.application")
		ctx.scan("com.st.kaboom.bus")
		ctx.refresh();


		Thread.start {
			ctx.getBean(NoteGenerator.class).run(args[0], ctx)
		}


	
	}
}
