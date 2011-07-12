package frontlinesms.legal.contact

import grails.plugin.spock.*
import frontlinesms.legal.LegalContact

class LegalContactControllerSpec extends ControllerSpec {
    def "Should Show contact saved message when contact is saved with name, number, and notes fields"() {
        setup: mockDomain(LegalContact)

        controller.params.contactName = 'Bill Gates'
        controller.params.contactNumber = '666'
        controller.params.contactNotes = 'The very embodiment of evil'

        when:
        controller.save()

        then:
        redirectArgs == [action: "create", params: [contactNumber:"666"]]
        controller.flash.message == "Contact Saved"
    }
}

