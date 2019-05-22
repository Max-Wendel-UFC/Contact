package com.jdev.microservice.contact.controller.validator

import com.jdev.microservice.contact.model.dto.ContactDTO
import org.slf4j.LoggerFactory

class ContactValidator {

    private lateinit var contactDTO: ContactDTO
    private val log = LoggerFactory.getLogger(this::class.java)

    fun isValid(contactDTO: ContactDTO): Boolean {
        log.trace("--- VALIDATION ---")
        log.trace("Receive: ${contactDTO.name}")

        this.contactDTO = contactDTO

        log.trace("IS SUPORTABLE: ${isSuportable()}")
        log.trace("HASN'T NULL VALUES: ${itHasNotNullValues()}")
        log.trace("VALIDATION RESULT: ${isSuportable() && itHasNotNullValues()}")

        return isSuportable() && itHasNotNullValues()
    }

    private fun isSuportable(): Boolean = ContactDTO::class.java.equals(contactDTO::class.java)

    private fun itHasNotNullValues(): Boolean = !contactDTO.name.isNullOrEmpty() && contactDTO.mails.isNotEmpty() && contactDTO.phones.isNotEmpty()

}