package com.jdev.microservice.contact.controller

import com.jdev.microservice.contact.component.ContactMaintainer
import com.jdev.microservice.contact.model.ContactDTO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpEntity
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/contact")
class ContactController {

    @Autowired
    lateinit var contactMaintainer: ContactMaintainer

    @PostMapping("/save")
    @ResponseBody
    fun saveRequest(@RequestBody contactDTO: ContactDTO): HttpEntity<*> {
        lateinit var response:ContactDTO
        try {
            response = contactMaintainer.saveContact(contactDTO)
        }catch (e:RuntimeException){
            return HttpEntity(HttpStatus.NOT_ACCEPTABLE)
        }
        return HttpEntity(response)
    }

    @GetMapping("/find")
    @ResponseBody
    fun getContactRequest(@RequestParam(name = "name") name:String): HttpEntity<*> {
        lateinit var contactDTO:ContactDTO
        try {
            contactDTO = contactMaintainer.findByNameContact(name)
        }catch (e:RuntimeException){
            return HttpEntity(HttpStatus.NOT_FOUND)
        }

        return HttpEntity(contactDTO)
    }

    @DeleteMapping("/delete")
    @ResponseBody
    fun deleteRequest(@RequestParam(name = "name") name:String):HttpEntity<*>{
        if (!contactMaintainer.deleteContact(name)){
            return HttpEntity(HttpStatus.NOT_FOUND)
        }
        return HttpEntity(HttpStatus.OK)
    }

    @PutMapping("/update")
    @ResponseBody
    fun updateRequest(@RequestParam(name = "name") name:String, @RequestBody contactDTO: ContactDTO): HttpEntity<*>{
        val done = contactMaintainer.updateContact(name,contactDTO)

        if (!done){
            return HttpEntity(HttpStatus.NOT_FOUND)
        }

        return HttpEntity(HttpStatus.OK)
    }
}