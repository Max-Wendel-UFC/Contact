package com.jdev.microservice.contact.component

import com.jdev.microservice.contact.model.ContactDTO
import com.jdev.microservice.contact.repository.ContactRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ContactMaintainer {
    @Autowired
    lateinit var contactRepository: ContactRepository

    fun saveContact(contactDTO: ContactDTO):ContactDTO{
        val contactExist = contactRepository.findByName(contactDTO.name)

        if (contactExist.isPresent){
            throw RuntimeException("is already exists")
        }else{
            contactRepository.save(contactDTO.convertToContact())
        }

        val contact = contactDTO.convertToContact()
        val contactDTO1 = ContactDTO()
        contactDTO1.convertToDTO(contact)
        return contactDTO1
    }

    fun findByNameContact(name: String): ContactDTO {
        val contactExist = contactRepository.findByName(name)

        if (!contactExist.isPresent){
            throw RuntimeException("Not Found")
        }

        val contact = contactExist.get()
        val contactDTO = ContactDTO("", arrayListOf(), arrayListOf())

        return contactDTO.convertToDTO(contact)
    }

    fun deleteContact(){

    }

    fun editContact(){

    }
}