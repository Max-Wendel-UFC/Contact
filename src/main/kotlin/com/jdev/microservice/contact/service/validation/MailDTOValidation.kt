package com.jdev.microservice.contact.service.validation

import com.jdev.microservice.contact.model.dto.MailDTO
import com.jdev.microservice.contact.model.dto.ObjectDTO
import com.jdev.microservice.contact.service.validation.exception.InvalidFormatValueException
import com.jdev.microservice.contact.service.validation.exception.NotSupportableException
import com.jdev.microservice.contact.service.validation.exception.NullOrEmptyValueException

class MailDTOValidation : ObjectDTOValidation{

    private val className = "E-Mail"
    private val address = "$className Address"

    override fun validate(objectDTO: ObjectDTO) {
        support(objectDTO::class.java)
        nullOrEmpty(objectDTO)
        invalidFormatValue(objectDTO)
    }

    override fun support(clazz: Class<*>){
        if(clazz::class.java != MailDTO::class.java){
            throw NotSupportableException(className)
        }
    }

    override fun nullOrEmpty(objectDTO: ObjectDTO){
        val mailDTO = objectDTO as MailDTO
        if (mailDTO.address.isBlank()){
            throw NullOrEmptyValueException(address)
        }
    }

    override fun invalidFormatValue(objectDTO: ObjectDTO){
        val mailDTO = objectDTO as MailDTO
        val sizePattern = "\\w{5,32}".toRegex()
        val formatPattern = ("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9]))|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").toRegex()

        if (!sizePattern.matches(mailDTO.address) || !formatPattern.matches(mailDTO.address)){
            throw InvalidFormatValueException(address)
        }
    }
}