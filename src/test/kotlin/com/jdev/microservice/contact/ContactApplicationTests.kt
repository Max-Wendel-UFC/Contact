package com.jdev.microservice.contact

import com.jdev.microservice.contact.model.Contact
import com.jdev.microservice.contact.model.Mail
import com.jdev.microservice.contact.model.Phone
import com.jdev.microservice.contact.model.dto.ContactDTO
import com.jdev.microservice.contact.model.dto.MailDTO
import com.jdev.microservice.contact.model.dto.PhoneDTO
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class ContactApplicationTests {

    @Test
    fun contextLoads() {

    }

    @Test
    fun testDTOConvertion() {
        val contact = Contact(
                id = 0,
                name = "teste",
                mails = arrayListOf<Mail>(),
                phones = arrayListOf<Phone>()
        )

        val c1
                = ContactDTO().convertToDTO(contact) as ContactDTO

        val c2 = ContactDTO(
                name = contact.name,
                phones = contact.phones.map {
                    PhoneDTO().convertToDTO(it)
                } as List<PhoneDTO>,
                mails = contact.mails.map {
                    MailDTO().convertToDTO(it)
                } as List<MailDTO>
        )

        Assert.assertEquals(c2.name,c1.name)
        Assert.assertEquals(c2.mails,c1.mails)
        Assert.assertEquals(c2.phones,c1.phones)
    }

    @Test
    fun testContactDTOConversion(){
        val contact = Contact(
                id = 0,
                name = "teste",
                mails = arrayListOf<Mail>(),
                phones = arrayListOf<Phone>()
        )

        val contactDTO
                = ContactDTO().convertToDTO(contact) as ContactDTO

        val convertedContact = contactDTO.convertToContact()

        Assert.assertEquals(contact.id,convertedContact.id)
        Assert.assertEquals(contact.name,convertedContact.name)
        Assert.assertEquals(contact.mails,convertedContact.mails)
        Assert.assertEquals(contact.phones,convertedContact.phones)

    }
}
