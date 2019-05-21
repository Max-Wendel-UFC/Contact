package com.jdev.microservice.contact.model.dto

import org.modelmapper.ModelMapper

class MailDTO(
        var address: String
) : ObjectDTO {

    constructor()
            : this(
            address = ""
    )

    override fun convertToDTO(any: Any): ObjectDTO {
        val modelMapper = ModelMapper()
        var mailDTO = modelMapper.map(any, MailDTO::class.java)
        this.address = mailDTO.address
        return this
    }
}