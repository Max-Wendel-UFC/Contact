package com.jdev.microservice.contact.model

import org.modelmapper.ModelMapper

class MailDTO(
        val address:String
        ) {

    constructor()
            :this(
            address = ""
            )

    constructor(mail: Mail)
            :this(
            address = mail.address
            )

    fun convertToMail():Mail{
        val modelMapper = ModelMapper()
        return modelMapper.map(this,Mail::class.java)
    }
}