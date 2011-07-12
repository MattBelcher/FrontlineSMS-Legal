package frontlinesms.legal.contact

import grails.plugin.spock.IntegrationSpec
import frontlinesms.legal.LegalContact
import frontlinesms2.Contact

class LegalContactIntegrationSpec extends IntegrationSpec {
    def "phone number should be unique"() {
        setup:
        def firstContact = new Contact(name: "fabio", address: "1234 a").save()
        def secondContact = new Contact(name: "dev", address: "5678 b").save()

        when:
        new LegalContact(contact: firstContact, primaryMobile: "1234" ).save()
        def secondLegalContact = new LegalContact(contact: secondContact, primaryMobile: "1234").save()

        then:
        secondLegalContact == null
    }
}
