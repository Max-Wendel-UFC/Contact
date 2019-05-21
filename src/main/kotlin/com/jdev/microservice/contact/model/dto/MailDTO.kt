package com.jdev.microservice.contact.model.dto

import org.modelmapper.ModelMapper
import javax.validation.constraints.Email

class MailDTO(
        @Email
        var address: String
) : ObjectDTO {

    constructor()
            : this(
            address = ""
    )

    override fun convertToDTO(any: Any): ObjectDTO {
        val modelMapper = ModelMapper()
        val mailDTO = modelMapper.map(any, MailDTO::class.java)
        this.address = mailDTO.address
        return this
    }
}