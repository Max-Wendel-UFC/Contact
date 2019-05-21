package com.jdev.microservice.contact.repository

import com.jdev.microservice.contact.model.Contact
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.query.Param
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import java.util.*

@RepositoryRestResource
interface ContactRepository : JpaRepository<Contact, Long> {
    fun findByName(@Param("name") name: String): Optional<Contact>
}