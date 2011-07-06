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

    def "should return all contacts for empty keyword"() {
        setup:
        def firstContact = new Contact(name: "jkl", address: "1234 a").save()
        def secondContact = new Contact(name: "bnm", address: "5678 b").save()
        new LegalContact(contact: firstContact, phoneNumber: "1", type: "z").save()
        new LegalContact(contact: secondContact, phoneNumber: "2", type: "x").save()

        when:
        def result = LegalContact.findByContactName("")

        then:
        result.size() == LegalContact.count()
    }

    def "should return all contacts for null keyword"() {
        setup:
        def firstContact = new Contact(name: "jkl", address: "1234 a").save()
        def secondContact = new Contact(name: "bnm", address: "5678 b").save()
        new LegalContact(contact: firstContact, phoneNumber: "1", type: "z").save()
        new LegalContact(contact: secondContact, phoneNumber: "2", type: "x").save()

        when:
        def result = LegalContact.findByContactName(null)

        then:
        result.size() == LegalContact.count()
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
