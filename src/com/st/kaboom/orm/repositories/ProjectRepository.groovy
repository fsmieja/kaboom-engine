package com.st.kaboom.orm.repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

import com.st.kaboom.orm.jpa.Project

/**
 * Specifies methods used to obtain and modify person related information
 * which is stored in the database.
 * @author Frank Smieja
 */
public interface ProjectRepository extends JpaRepository<Project, Long> {
	@Query("select u.profile from User u where user_id=?1")
	def getProjectsForUser(id)
}