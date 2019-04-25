package com.jdev.microservice.contact.model.dto

import com.jdev.microservice.contact.model.Phone
import com.jdev.microservice.contact.model.enum.Operator
import org.modelmapper.ModelMapper

class PhoneDTO(
        var number:String,
        var operatorId:Operator
        ):ObjectDTO {

    constructor()
            :this(
            number= "",
            operatorId= Operator.UNDEFINED
    )

    override fun convertToDTO(any: Any): ObjectDTO {
        val modelMapper = ModelMapper()
        var phoneDTO = modelMapper.map(any, PhoneDTO::class.java)

        this.number = phoneDTO.number
        this.operatorId = phoneDTO.operatorId

        return this
    }
}