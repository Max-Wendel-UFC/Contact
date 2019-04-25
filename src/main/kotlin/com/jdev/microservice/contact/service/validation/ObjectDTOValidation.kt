package com.jdev.microservice.contact.service.validation

import com.jdev.microservice.contact.model.dto.ObjectDTO

interface ObjectDTOValidation {

    fun validate(objectDTO: ObjectDTO)

    fun support(clazz: Class<*>)

    fun nullOrEmpty(objectDTO: ObjectDTO)

    fun invalidFormatValue(objectDTO: ObjectDTO)
}