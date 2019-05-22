package com.jdev.microservice.contact.model.dto

import com.jdev.microservice.contact.model.Contact
import org.modelmapper.ModelMapper
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

class ContactDTO(
        @NotEmpty
        var name: String,
        @NotNull
        var phones: List<PhoneDTO>,
        @NotNull
        var mails: List<MailDTO>
) : ObjectDTO {

    override fun convertToDTO(any: Any): ObjectDTO {
        return ModelMapper().map(any, ContactDTO::class.java)
    }

    constructor()
            : this(
            name = "",
            phones = arrayListOf<PhoneDTO>(),
            mails = arrayListOf<MailDTO>()
    )

    fun convertToContact(): Contact {
        val modelMapper = ModelMapper()
        return modelMapper.map(this, Contact::class.java)
    }

}