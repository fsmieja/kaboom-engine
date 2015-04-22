package com.st.kaboom.application


import javax.persistence.EntityManagerFactory

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.jdbc.datasource.DriverManagerDataSource
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.transaction.PlatformTransactionManager

import com.st.libs.db.MyDataSource
import com.st.libs.db.MyEntityManagerFactoryBean


@Configuration
@EnableScheduling
@EnableAutoConfiguration
@EnableConfigurationProperties
@EnableJpaRepositories("com.st.kaboom.orm")
class AppConfig {

	@Bean
	//@ConfigurationProperties(prefix = "datasource", ignoreUnknownFields = false)
	 public MyDataSource myDataSource() {
		 return  new MyDataSource()
	 }
	 
 


	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory emf){
		JpaTransactionManager transactionManager = new JpaTransactionManager()
		transactionManager.setEntityManagerFactory(emf)

		return transactionManager
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
		return new PersistenceExceptionTranslationPostProcessor()
	}

	
	@Bean
	public MyEntityManagerFactoryBean entityManagerFactory(DriverManagerDataSource ds) {
		return new MyEntityManagerFactoryBean(ds)
	}

}
