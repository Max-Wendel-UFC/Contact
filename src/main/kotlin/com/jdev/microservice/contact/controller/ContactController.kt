package com.jdev.microservice.contact.controller

import com.jdev.microservice.contact.controller.validator.ContactValidator
import com.jdev.microservice.contact.model.dto.ContactDTO
import com.jdev.microservice.contact.service.ContactService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpEntity
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/contact")
class ContactController {

    @Autowired
    lateinit var contactService: ContactService

    private val log = LoggerFactory.getLogger(this::class.java)
    private val validator = ContactValidator()

    @GetMapping("/")
    @ResponseBody
    fun getAllRequest(): HttpEntity<*> {
        log.trace("--- getAllRequest ---")
        val result = contactService.listAllContact()
        return if (result.isEmpty()) {
            log.trace("getAllRequest: NO CONTENT")
            HttpEntity(HttpStatus.NO_CONTENT)
        } else {
            log.trace("getAllRequest: OK")
            HttpEntity(result)
        }
    }

    @PostMapping("/save")
    @ResponseBody
    fun saveRequest(@RequestBody @Valid contactDTO: ContactDTO): HttpEntity<*> {
        log.trace("--- saveRequest ---")
        return if (validator.isValid(contactDTO)) {
            val response = contactService.saveContact(contactDTO) as ContactDTO

            if (response.name.isEmpty()) {
                log.trace("saveRequest: BAD REQUEST")
                HttpEntity(HttpStatus.NOT_ACCEPTABLE)
            } else {
                log.trace("saveRequest: OK")
                HttpEntity(response)
            }
        } else {
            HttpEntity(HttpStatus.BAD_REQUEST)
        }

    }

    @GetMapping("/find")
    @ResponseBody
    fun getContactRequest(@RequestParam(name = "name") name: String): HttpEntity<*> {
        log.trace("--- getContactRequest ---")
        val contactDTO = contactService.findByNameContact(name) as ContactDTO

        return if (contactDTO.name.isEmpty()) {
            log.trace("getContactRequest: NOT FOUND")
            HttpEntity(HttpStatus.NOT_FOUND)
        } else {
            log.trace("getContactRequest: OK")
            HttpEntity(contactDTO)
        }
    }

    @DeleteMapping("/delete")
    @ResponseBody
    fun deleteRequest(@RequestParam(name = "name") name: String): HttpEntity<*> {
        log.trace("--- deleteRequest ---")
        return if (!contactService.deleteContact(name)) {
            log.trace("deleteRequest: NOT FOUND")
            HttpEntity(HttpStatus.NOT_FOUND)
        } else {
            log.trace("deleteRequest: OK")
            HttpEntity(HttpStatus.OK)
        }
    }

    @PutMapping("/update")
    @ResponseBody
    fun updateRequest(@RequestParam(name = "name") name: String, @RequestBody @Valid contactDTO: ContactDTO): HttpEntity<*> {
        log.trace("--- updateRequest ---")
        return if (validator.isValid(contactDTO)) {

            val done = contactService.updateContact(name, contactDTO)

            if (!done) {
                log.trace("updateRequest: NOT FOUND")
                HttpEntity(HttpStatus.NOT_FOUND)
            } else {
                log.trace("updateRequest: OK")
                HttpEntity(HttpStatus.OK)
            }
        } else {
            HttpEntity(HttpStatus.BAD_REQUEST)
        }
    }
}