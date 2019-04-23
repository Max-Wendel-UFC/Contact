package com.jdev.microservice.contact.model

import javax.persistence.OneToMany
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.validation.constraints.NotBlank

@Entity
class Contact(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id:Long,
        @NotBlank
        var name:String,
        @OneToMany(mappedBy = "id", orphanRemoval = true)
        var phones:List<Phone>,
        @OneToMany(mappedBy = "id",orphanRemoval = true)
        var mails:List<Mail>)