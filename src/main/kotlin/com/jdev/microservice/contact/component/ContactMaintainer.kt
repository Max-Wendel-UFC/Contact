package com.jdev.microservice.contact.component

import com.jdev.microservice.contact.model.Contact
import com.jdev.microservice.contact.model.ContactDTO
import com.jdev.microservice.contact.repository.ContactRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component

@Component
class ContactMaintainer {
    @Autowired
    lateinit var contactRepository: ContactRepository

    fun saveContact(contactDTO: ContactDTO): ContactDTO {
        val contactExist = contactRepository.findByName(contactDTO.name)

        if (contactExist.isPresent){
            throw RuntimeException("is already exists")
        }else{
            contactRepository.save(contactDTO.convertToContact())
        }

        return findByNameContact(contactDTO.name)
    }

    fun findByNameContact(name: String): ContactDTO {
        val contactExist = contactRepository.findByName(name)

        if (!contactExist.isPresent){
            throw RuntimeException("Not Found")
        }
        val contactDTO = ContactDTO()

        return contactDTO.convertToDTO(contactExist.get())
    }

    fun deleteContact(name: String):Boolean{
        val contactExist = contactRepository.findByName(name)

        if(contactExist.isPresent){
            contactRepository.deleteById(contactExist.get().id)
            return true
        }
        return false
    }

    fun updateContact(name: String,contactDTO: ContactDTO):Boolean{
        val contactExist = contactRepository.findByName(name)

        if (contactExist.isPresent){
            val c = contactDTO.convertToContact()
            contactRepository.save(Contact(contactExist.get().id, c.name,c.phones,c.mails))
            return true
        }

        return false
    }
}