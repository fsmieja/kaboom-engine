package com.st.kaboom.orm.jpa

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Table
import javax.persistence.Temporal
import javax.persistence.TemporalType

import org.hibernate.annotations.GenericGenerator
import org.springframework.data.annotation.Id


@Entity
@Table( name = "projects" )
class Project {

	@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")
	Long id

	String name
	String summary
	String description

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at")
	Date created_at

}

