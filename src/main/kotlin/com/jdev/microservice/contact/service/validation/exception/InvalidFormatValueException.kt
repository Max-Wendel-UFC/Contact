package com.jdev.microservice.contact.service.validation.exception

class InvalidFormatValueException(override val message: String?) : RuntimeException(message) {

    fun getMsg():String = "The field $message is incorrect format"
}