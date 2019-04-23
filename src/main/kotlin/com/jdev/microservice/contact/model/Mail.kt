package com.jdev.microservice.contact.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.GenerationType
import javax.validation.constraints.NotBlank

@Entity
class Mail(@Id
           @GeneratedValue(strategy = GenerationType.AUTO)
           val id:Long,
           @NotBlank
           val address:String){
}