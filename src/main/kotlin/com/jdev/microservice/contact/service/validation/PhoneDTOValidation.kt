package com.jdev.microservice.contact.service.validation

import com.jdev.microservice.contact.model.dto.ObjectDTO
import com.jdev.microservice.contact.model.dto.PhoneDTO
import com.jdev.microservice.contact.model.enum.Operator
import com.jdev.microservice.contact.service.validation.exception.InvalidFormatValueException
import com.jdev.microservice.contact.service.validation.exception.NotSupportableException
import com.jdev.microservice.contact.service.validation.exception.NullOrEmptyValueException

class PhoneDTOValidation:ObjectDTOValidation {
    private val className = "Phone"
    private val operatorID = "$className OperatorID"
    private val number = "$className Number"

    override fun validate(objectDTO: ObjectDTO) {
        support(objectDTO::class.java)
        nullOrEmpty(objectDTO)
        invalidFormatValue(objectDTO)
    }

    override fun support(clazz: Class<*>) {
        if(clazz::class.java != PhoneDTO::class.java){
            throw NotSupportableException(className)
        }
    }

    override fun nullOrEmpty(objectDTO: ObjectDTO) {
        val phoneDTO = objectDTO as PhoneDTO

        if (phoneDTO.number.isBlank()){
            throw NullOrEmptyValueException(number)
        }
        if (phoneDTO.operatorId.toString().isBlank()){
            throw NullOrEmptyValueException(operatorID)
        }
    }

    override fun invalidFormatValue(objectDTO: ObjectDTO) {
        val phoneDTO = objectDTO as PhoneDTO

        val operatorsId = arrayListOf(
                Operator.UNDEFINED,
                Operator.CLARO,
                Operator.OI,
                Operator.TIM,
                Operator.VIVO)

        var isValidOperator = false

        operatorsId.forEach { i ->
            if (phoneDTO.operatorId == i){
                isValidOperator = true
            }
        }

        if (!isValidOperator){
            throw InvalidFormatValueException(operatorID)
        }

        val sizePattern = "\\w{3,16}".toRegex()
        val formatPattern = "[0-9]".toRegex()

        if (!sizePattern.matches(phoneDTO.number) || !formatPattern.matches(phoneDTO.number)){
            throw InvalidFormatValueException(number)
        }

    }
}