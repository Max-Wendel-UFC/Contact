package com.jdev.microservice.contact.controller

import com.jdev.microservice.contact.service.ContactService
import com.jdev.microservice.contact.model.dto.ContactDTO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpEntity
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/contact")
class ContactController {

    @Autowired
    lateinit var contactService: ContactService

    @GetMapping("/")
    @ResponseBody
    fun getAllRequest():HttpEntity<*>{
        val result = contactService.listAllContact()
        if (result.isNotEmpty()){
            return HttpEntity(result)
        }
        return HttpEntity(HttpStatus.NO_CONTENT)
    }

    @PostMapping("/save")
    @ResponseBody
    fun saveRequest(@RequestBody contactDTO: ContactDTO): HttpEntity<*> {
        lateinit var response: ContactDTO
        try {
            response = contactService.saveContact(contactDTO) as ContactDTO
        }catch (e:RuntimeException){
            return HttpEntity(HttpStatus.NOT_ACCEPTABLE)
        }
        return HttpEntity(response)
    }

    @GetMapping("/find")
    @ResponseBody
    fun getContactRequest(@RequestParam(name = "name") name:String): HttpEntity<*> {
        lateinit var contactDTO: ContactDTO
        try {
            contactDTO = contactService.findByNameContact(name) as ContactDTO
        }catch (e:RuntimeException){
            return HttpEntity(HttpStatus.NOT_FOUND)
        }

        return HttpEntity(contactDTO)
    }

    @DeleteMapping("/delete")
    @ResponseBody
    fun deleteRequest(@RequestParam(name = "name") name:String):HttpEntity<*>{
        if (!contactService.deleteContact(name)){
            return HttpEntity(HttpStatus.NOT_FOUND)
        }
        return HttpEntity(HttpStatus.OK)
    }

    @PutMapping("/update")
    @ResponseBody
    fun updateRequest(@RequestParam(name = "name") name:String, @RequestBody contactDTO: ContactDTO): HttpEntity<*>{
        val done = contactService.updateContact(name,contactDTO)

        if (!done){
            return HttpEntity(HttpStatus.NOT_FOUND)
        }

        return HttpEntity(HttpStatus.OK)
    }
}