package com.jdev.microservice.contact.model.dto

import com.jdev.microservice.contact.model.Contact
import com.jdev.microservice.contact.model.Mail
import com.jdev.microservice.contact.model.Phone
import org.modelmapper.ModelMapper

class ContactDTO(
        var name: String,
        var phones:List<PhoneDTO>,
        var mails:List<MailDTO>
        ):ObjectDTO{

    override fun convertToDTO(any: Any): ObjectDTO {
        val modelMapper = ModelMapper()
        val contact = modelMapper.map(any,ContactDTO::class.java)

        this.name = contact.name
        this.phones = contact.phones
        this.mails = contact.mails

        return this
    }

    constructor()
            :this(
            name = "",
            phones = arrayListOf<PhoneDTO>(),
            mails = arrayListOf<MailDTO>()
            )

    fun convertToContact(): Contact {
        val modelMapper = ModelMapper()
        return modelMapper.map(this, Contact::class.java)
    }

}