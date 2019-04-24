package com.jdev.microservice.contact.model

import org.modelmapper.ModelMapper

class ContactDTO(
        var name: String,
        var phones:List<PhoneDTO>,
        var mails:List<MailDTO>
        ){

    constructor()
            :this(
            name = "",
            phones = arrayListOf<PhoneDTO>(),
            mails = arrayListOf<MailDTO>()
            )

    fun convertToDTO(contact: Contact):ContactDTO{
        this.name = contact.name
        this.phones = mapPhone(contact.phones)
        this.mails = mapMails(contact.mails)
        return this
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

    fun mapMails(mails: List<Mail>):List<MailDTO>{
        val result = mutableListOf<MailDTO>()
        for (i in mails){
            result.add(MailDTO(i))
        }
        return result
    }

    fun mapPhone(phones: List<Phone>):List<PhoneDTO>{
        val result = mutableListOf<PhoneDTO>()
        for (i in phones){
            result.add(PhoneDTO(i))
        }
        return result
    }
}