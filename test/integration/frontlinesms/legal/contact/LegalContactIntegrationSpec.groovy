package frontlinesms.legal.contact

import frontlinesms.legal.LegalContact
import grails.plugin.spock.IntegrationSpec

class LegalContactIntegrationSpec extends IntegrationSpec {
    def "phone number should be unique"() {
        given:
        new LegalContact(name: "fabio", primaryMobile: "1234").save()

        when:
        def secondContact = new LegalContact(name: "dev", primaryMobile: "1234").save()

        then:
        secondContact == null
    }

    def "should be able to update contact details after creating a contact"() {

        given:
        def aLegalContact = new LegalContact(name: "Tester", primaryMobile: "12345678").save()

        when:
        aLegalContact.name = "ChangedName"
        aLegalContact.save()
        def foundContact = LegalContact.findById(aLegalContact.id)

        then:
        foundContact.name == aLegalContact.name
    }

    def "should have errors if primaryMobile is already taken"() {
        given:
        new LegalContact(name: "John Doe", primaryMobile: "5285", notes: "He is anonymous.").save(flush: true)
        new LegalContact(name: "", primaryMobile: "7777", notes: "").save(flush: true)

        when:
        def SteveJobs = LegalContact.findByPrimaryMobile("5285")
        SteveJobs.primaryMobile = "7777"
        SteveJobs.name = "Steve Jobs"
        SteveJobs.save(flush: true)

        then:
        SteveJobs.hasErrors() == true

    }

}
