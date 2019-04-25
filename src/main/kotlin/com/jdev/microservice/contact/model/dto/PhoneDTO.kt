package com.jdev.microservice.contact.model

import com.jdev.microservice.contact.model.enum.Operator
import org.modelmapper.ModelMapper

class PhoneDTO(
        var number:String,
        var operatorId:Operator
        ) {

    constructor()
            :this(
            number= "",
            operatorId= Operator.UNDEFINED
    )
    constructor(phone: Phone)
            :this(
            number= phone.number,
            operatorId= phone.operatorId
    )

    fun convertToPhone():Phone{
        val phone = Phone()
        phone.number = number
        phone.operatorId = operatorId
        return phone
    }
}