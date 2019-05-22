package com.jdev.microservice.contact.service

import com.jdev.microservice.contact.model.Contact
import com.jdev.microservice.contact.model.dto.ContactDTO
import com.jdev.microservice.contact.model.dto.ObjectDTO
import com.jdev.microservice.contact.repository.ContactRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ContactService {
    @Autowired
    lateinit var contactRepository: ContactRepository

    private val log = LoggerFactory.getLogger(this::class.java)

    fun listAllContact(): List<ContactDTO> {
        log.trace("--- List All Contact ---")
        val aux = ContactDTO()

        return contactRepository.getAllContactOrderByName().map {
            log.trace("listAllContact: ${it.name}")
            aux.convertToDTO(it) as ContactDTO
        }
    }

    fun saveContact(contactDTO: ContactDTO): ObjectDTO {
        log.trace("--- Save Contact ---")
        val contactExist = contactRepository.findByName(contactDTO.name)

        return if (contactExist.isPresent) {
            log.trace("saveContact: THIS CONTACT ALREADY EXIST")
            ContactDTO()
        } else {
            log.trace("saveContact: IS NOT PRESENT")
            contactRepository.save(contactDTO.convertToContact())
            ContactDTO().convertToDTO(contactRepository.findByName(contactDTO.name).get())
        }
    }

    fun findByNameContact(name: String): ObjectDTO {
        log.trace("--- Find By Name Contact ---")
        val contactExist = contactRepository.findByName(name)
        return if (!contactExist.isPresent) {
            log.trace("findByNameContact: IS NOT PRESENT")
            ContactDTO()
        } else {
            log.trace("findByNameContact: FOUND")
            ContactDTO().convertToDTO(contactExist.get())
        }
    }

    fun deleteContact(name: String): Boolean {
        log.trace("--- Delete Contact ---")
        val contactExist = contactRepository.findByName(name)
        return if (contactExist.isPresent) {
            contactRepository.deleteById(contactExist.get().id)
            log.trace("deleteContact: DELETED")
            true
        } else {
            log.trace("deleteContact: IS NOT PRESENT")
            false
        }
    }

    fun updateContact(name: String, contactDTO: ContactDTO): Boolean {
        log.trace("--- Update Contact ---")
        val contactExist = contactRepository.findByName(name)

        return if (contactExist.isPresent) {
            val c = contactDTO.convertToContact()
            contactRepository.save(Contact(contactExist.get().id, c.name, c.phones, c.mails))
            log.trace("updateContact: UPGRADED")
            true
        } else {
            log.trace("updateContact: NOT UPGRADED")
            false
        }
    }
}