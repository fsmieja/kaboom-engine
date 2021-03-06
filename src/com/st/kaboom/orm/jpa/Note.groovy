package com.st.kaboom.orm.jpa

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.JoinColumn
import javax.persistence.Table
import javax.persistence.Temporal
import javax.persistence.TemporalType

import org.hibernate.annotations.GenericGenerator
import org.springframework.data.annotation.Id


@Entity
@Table( name = "notes" )
class Note {

	@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")
	Long id

	String title
	String content
	String location

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at")
	Date created_at

	// @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "project_id", nullable = false)
	Project project
	//Integer project_id
}

