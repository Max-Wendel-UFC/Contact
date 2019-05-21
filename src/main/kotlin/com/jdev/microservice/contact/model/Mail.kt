package com.jdev.microservice.contact.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

@Entity
class Mail(@Id
           @GeneratedValue(strategy = GenerationType.AUTO)
           var id:Long,
           @NotBlank
           @Email
           var address:String){

    constructor()
            :this(
            id = 0,
            address = "none@none"
    )
}