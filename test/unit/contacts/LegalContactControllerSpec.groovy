package contacts

import spock.lang.*
import grails.plugin.spock.*
import frontlinesms.legal.LegalContact

class LegalContactControllerSpec extends ControllerSpec {
    def "should save contact"() {
        setup:
        def contacts = []
        mockDomain(LegalContact, contacts)
        controller.params.phoneNumber = '1234'
        controller.params.notes = 'hagsdhs'

        when:
        controller.save()

        then:
        LegalContact.count() == 1
    }
}

