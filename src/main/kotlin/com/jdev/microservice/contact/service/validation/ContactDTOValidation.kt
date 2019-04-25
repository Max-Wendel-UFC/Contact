package com.jdev.microservice.contact.service.validation

import com.jdev.microservice.contact.model.dto.ContactDTO
import com.jdev.microservice.contact.model.dto.ObjectDTO
import com.jdev.microservice.contact.service.validation.exception.InvalidFormatValueException
import com.jdev.microservice.contact.service.validation.exception.NotSupportableException
import com.jdev.microservice.contact.service.validation.exception.NullOrEmptyValueException

class ContactDTOValidation:ObjectDTOValidation {

    private val className = "Contact"
    private val name = "$className Name"
    private val phones = "$className Phones"
    private val mails = "$className E-Mails"

    override fun validate(objectDTO: ObjectDTO) {
        support(objectDTO::class.java)
        nullOrEmpty(objectDTO)
        invalidFormatValue(objectDTO)
        validatePhones(objectDTO)
        validateMail(objectDTO)
    }

    override fun support(clazz: Class<*>) {
        if(clazz::class.java != ContactDTO::class.java){
            throw NotSupportableException(className)
        }
    }

    override fun nullOrEmpty(objectDTO: ObjectDTO) {
        val contactDTO = objectDTO as ContactDTO

        if (contactDTO.name.isBlank()){
            throw NullOrEmptyValueException(name)
        }
    }

    override fun invalidFormatValue(objectDTO: ObjectDTO) {
        val contactDTO = objectDTO as ContactDTO
        val sizePattern = "\\w{1,32}".toRegex()

        if (sizePattern.matches(contactDTO.name)){
            throw NullOrEmptyValueException(name)
        }
    }

    fun validatePhones(objectDTO: ObjectDTO){
        val v = PhoneDTOValidation()
        val contactDTO = objectDTO as ContactDTO
        contactDTO.phones.forEach { i ->
            try {
                v.validate(i)
            }catch (e:NotSupportableException){
                throw NotSupportableException(e.getMsg()+i)
            }catch (e:NullOrEmptyValueException){
                throw NullOrEmptyValueException(e.getMsg()+i)
            }catch (e:InvalidFormatValueException){
                throw InvalidFormatValueException(e.getMsg()+i)
            }
        }
    }

    fun validateMail(objectDTO: ObjectDTO){
        val v = MailDTOValidation()
        val contactDTO = objectDTO as ContactDTO
        contactDTO.mails.forEach { i ->
            try {
                v.validate(i)
            }catch (e:NotSupportableException){
                throw NotSupportableException(e.getMsg()+i)
            }catch (e:NullOrEmptyValueException){
                throw NullOrEmptyValueException(e.getMsg()+i)
            }catch (e:InvalidFormatValueException){
                throw InvalidFormatValueException(e.getMsg()+i)
            }
        }
    }
}