package com.jdev.microservice.contact.model

import org.modelmapper.ModelMapper

class MailDTO(
        val address:String
        )
        :ObjectDTO {

    override fun convertToDTO(any: Any): ObjectDTO {
        val modelMapper = ModelMapper()
        return modelMapper.map(any,MailDTO::class.java)
    }

    fun convertToMail():Mail{
        val modelMapper = ModelMapper()
        return modelMapper.map(this,Mail::class.java)
    }
}