package com.st.kaboom.services


import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Autowired

import com.st.kaboom.orm.repositories.ProjectRepository


class NoteGenerator {

	def pretend = false
	def logger
	def period=1
	def persistQueueName='persist'
	def HOUR = 60*60

	@Autowired
	RabbitTemplate rabbitTemplate

	@Autowired
	ProjectRepository projectRepository


	public init(propsFile) {

		def config = new ConfigSlurper().parse(new File(propsFile).toURI().toURL())
		if (config != null) {
			if (config.notegenerator.pretend != null)
				pretend = config.notegenerator.pretend
		}
		logger = LoggerFactory.getLogger("com.st.wasup.services.NoteGenerator")
		logger.info "creating note generator process"
		


	}

	// 3 am every morning
//	@Scheduled(cron="0 0 3 * * *")
	//@Scheduled(cron="0 */2 * * * *")
	void performNoteGeneration() {
		if (logger) {
		}
		else
			System.out "NO LOGGER FOR NOTE GENERATOR"
	}

	public run(p) {

		init(p)
		// this will get called by the spring scheduler - no need to put sleep in :)
		//while (true) {

		performNoteGeneration()
	}


	public static void main(String[] args) {

		if (args.length < 1) {
			println "Usage: notegnerator.jar <propsFileName>"
			return
		}


		new NoteGenerator().run(args[0])
	}


}
