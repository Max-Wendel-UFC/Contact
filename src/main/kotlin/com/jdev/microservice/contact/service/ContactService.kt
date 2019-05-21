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

    private val LOG = LoggerFactory.getLogger(this::class.java)

    fun listAllContact(): List<ContactDTO> {
        LOG.trace("--- List All Contact ---")
        val aux = ContactDTO()

        return contactRepository.findAll().map {
            LOG.trace(it.name)
            aux.convertToDTO(it) as ContactDTO
        }
    }

    fun saveContact(contactDTO: ContactDTO): ObjectDTO? {
        LOG.trace("--- Save Contact ---")
        val contactExist = contactRepository.findByName(contactDTO.name)

        return if (contactExist.isPresent) {
            LOG.trace("THIS CONTACT ALREADY EXIST")
            null
        } else {
            LOG.trace("IS NOT PRESENT")
            contactRepository.save(contactDTO.convertToContact())
            ContactDTO().convertToDTO(contactRepository.findByName(contactDTO.name).get())
        }
    }

    fun findByNameContact(name: String): ObjectDTO? {
        LOG.trace("--- Find By Name Contact ---")
        val contactExist = contactRepository.findByName(name)
        return if (!contactExist.isPresent) {
            LOG.trace("IS NOT PRESENT")
            null
        } else{
            LOG.trace("FOUND")
            ContactDTO().convertToDTO(contactExist.get())
        }
    }

    fun deleteContact(name: String): Boolean {
        LOG.trace("--- Delete Contact ---")
        val contactExist = contactRepository.findByName(name)
        return if (contactExist.isPresent) {
            contactRepository.deleteById(contactExist.get().id)
            LOG.trace("DELETED")
            true
        } else {
            LOG.trace("IS NOT PRESENT")
            false
        }
    }

    fun updateContact(name: String, contactDTO: ContactDTO): Boolean {
        LOG.trace("--- Update Contact ---")
        val contactExist = contactRepository.findByName(name)

        return if (contactExist.isPresent) {
            val c = contactDTO.convertToContact()
            contactRepository.save(Contact(contactExist.get().id, c.name, c.phones, c.mails))
            true
        } else

            false
    }
}