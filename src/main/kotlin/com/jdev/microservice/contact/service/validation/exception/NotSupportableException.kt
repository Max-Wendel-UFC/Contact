package com.jdev.microservice.contact.service.validation.exception

class NotSupportableException(override val message: String?) : RuntimeException(message) {

    fun getMsg():String = "The object $message isn't supportable"
}