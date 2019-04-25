package com.jdev.microservice.contact.service.validation.exception

class NullOrEmptyValueException(override val message: String?) : RuntimeException(message)  {
    fun getMsg():String = "The field $message is empty or null"
}