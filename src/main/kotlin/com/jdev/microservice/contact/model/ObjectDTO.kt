package com.jdev.microservice.contact.model

interface ObjectDTO {
    fun convertToDTO(any: Any):ObjectDTO
}