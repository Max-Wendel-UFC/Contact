package com.jdev.microservice.contact.model

import org.modelmapper.ModelMapper

class ContactDTO(
        val name: String,
        val phones:List<PhoneDTO>,
        val mails:List<MailDTO>
        )
        :ObjectDTO {

    override fun convertToDTO(any: Any): ObjectDTO {
        val modelMapper = ModelMapper()
        return modelMapper.map(any,ContactDTO::class.java)
    }

    fun convertToContact():Contact{
        val modelMapper = ModelMapper()

        val result = modelMapper.map(this,Contact::class.java)

        val phones = mutableListOf<Phone>()
        val mails = mutableListOf<Mail>()

        for (i in this.phones){
            phones.add(i.convertToPhone())
        }

        for (i in this.mails){
            mails.add(i.convertToMail())
        }

        result.name = this.name
        result.phones = phones
        result.mails = mails

        return result
    }
}