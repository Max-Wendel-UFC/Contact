package com.jdev.microservice.contact.controller

import com.jdev.microservice.contact.component.ContactMaintainer
import com.jdev.microservice.contact.model.ContactDTO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ContactController {

    @Autowired
    lateinit var contactMaintainer: ContactMaintainer

    @PostMapping("/save")
    fun saveRequest(@RequestBody contactDTO: ContactDTO): HttpStatus {
        try {
            contactMaintainer.saveContact(contactDTO)
        }catch (e:RuntimeException){
            return HttpStatus.NOT_ACCEPTABLE
        }
        return HttpStatus.CREATED
    }
}