package com.jdev.microservice.contact.model

import com.jdev.microservice.contact.model.enum.Operator
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.validation.constraints.NotBlank

@Entity
class Phone(@Id
            @GeneratedValue(strategy = GenerationType.AUTO)
            val id:Long,
            @NotBlank
            val number:String,
            val operatorId:Operator)