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
        val modelMapper = ModelMapper()
        val contact = modelMapper.map(any, ContactDTO::class.java)

        this.name = contact.name
        this.phones = contact.phones
        this.mails = contact.mails

        return this
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