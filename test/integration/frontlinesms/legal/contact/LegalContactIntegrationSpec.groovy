package frontlinesms.legal.contact

import grails.plugin.spock.IntegrationSpec
import frontlinesms.legal.LegalContact
import frontlinesms2.Contact

class LegalContactIntegrationSpec extends IntegrationSpec {
    def "should find legal contacts based on a contact keyword"() {
        setup:
        def firstContact = new Contact(name: "abcd", address: "1234 a").save()
        def secondContact = new Contact(name: "def", address: "5678 b").save()
        new LegalContact(contact: firstContact, phoneNumber: "1", type: "z").save()
        new LegalContact(contact: secondContact, phoneNumber: "2", type: "x").save()

        when:
        def results = LegalContact.findByContactName("a")

        then: results.size() == 1
    }

    def "legal contact type should be optional"() {
        setup:
        def contact = new Contact(name: "Tom").save()

        when:
        def legalContact = new LegalContact(contact: contact, phoneNumber: "65427623").save()

        then:
        legalContact != null
    }
}
