package com.jdev.microservice.contact.model

import javax.persistence.*
import javax.validation.constraints.NotBlank

@Entity
class Contact(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long,

        @NotBlank
        var name: String,

        @ManyToMany(cascade = [CascadeType.ALL])
        @JoinTable(
                name = "contact_phones",
                joinColumns = [JoinColumn(name = "contact_id")],
                inverseJoinColumns = [JoinColumn(name = "phone_id")]
        )
        var phones: List<Phone>,

        @ManyToMany(cascade = [CascadeType.ALL])
        @JoinTable(
                name = "contact_mail",
                joinColumns = [JoinColumn(name = "contact_id")],
                inverseJoinColumns = [JoinColumn(name = "mail_id")]
        )
        var mails: List<Mail>) {

    constructor()
            : this(
            id = 0,
            name = "",
            phones = arrayListOf<Phone>(),
            mails = arrayListOf<Mail>()
    )
}