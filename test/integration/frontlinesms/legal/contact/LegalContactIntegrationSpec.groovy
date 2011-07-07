package frontlinesms.legal.contact

import grails.plugin.spock.IntegrationSpec
import frontlinesms.legal.LegalContact
import frontlinesms2.Contact

class LegalContactIntegrationSpec extends IntegrationSpec {
    def "legal contact type should be optional"() {
        setup:
        def contact = new Contact(name: "Tom").save()

        when:
        def legalContact = new LegalContact(contact: contact, phoneNumber: "65427623").save()

        then:
        legalContact != null
    }

    def "phone number should be unique"() {
        setup:
        def firstContact = new Contact(name: "fabio", address: "1234 a").save()
        def secondContact = new Contact(name: "dev", address: "5678 b").save()

        when:
        new LegalContact(contact: firstContact, phoneNumber: "1234", type: "z").save()
        def secondLegalContact = new LegalContact(contact: secondContact, phoneNumber: "1234", type: "x").save()

        then:
        secondLegalContact == null
    }
}
