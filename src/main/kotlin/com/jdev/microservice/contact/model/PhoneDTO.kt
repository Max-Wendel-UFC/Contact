package com.jdev.microservice.contact.model

import com.jdev.microservice.contact.model.enum.Operator
import org.modelmapper.ModelMapper

class PhoneDTO(
        val number:String,
        val operatorId:Operator
        )
        :ObjectDTO {

    override fun convertToDTO(any: Any): ObjectDTO {
        val modelMapper = ModelMapper()
        return modelMapper.map(any,PhoneDTO::class.java)
    }

    fun convertToPhone():Phone{
        val modelMapper = ModelMapper()
        return modelMapper.map(this,Phone::class.java)
    }
}