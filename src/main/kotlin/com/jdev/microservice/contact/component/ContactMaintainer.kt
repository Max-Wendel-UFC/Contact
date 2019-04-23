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
        var contactExist = contactRepository.findByName(contactDTO.name)

        if (contactExist.isPresent){
            throw RuntimeException("is already exists")
        }else{
            contactRepository.save(contactDTO.convertToContact())
        }

        return contactDTO
    }

    fun findByNameContact(){

    }

    fun deleteContact(){

    }

    fun editContact(){

    }
}