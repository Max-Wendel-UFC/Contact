package com.jdev.microservice.contact.model.dto

interface ObjectDTO {
    fun convertToDTO(any: Any):ObjectDTO
}