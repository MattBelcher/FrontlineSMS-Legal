package frontlinesms.legal.contacts

import grails.plugin.spock.ControllerSpec
import frontlinesms.legal.LegalContact


class LegalContactControllerSpec extends ControllerSpec {
    def "should save contact"() {
        setup:
        def contacts = []
        mockDomain(LegalContact, contacts)
        controller.params.name = 'bla bla'
        controller.params.primaryMobile = '333333'

        when:
        controller.save()

        then:
        LegalContact.count() == 1
    }

    def 'create action should redirect to Contact detail page'() {
         setup:
         mockDomain(LegalContact)

         controller.params.name = 'Steve Jobs'
         controller.params.primaryMobile = '666'

         when:
         controller.save()

         then:
         controller.flash.message == "Contact Saved"
         redirectArgs == [action: "show", params: [id: "666"]]
     }

    def 'should display error if primaryMobile is blank'(){

        given:

        mockDomain(LegalContact)
        controller.params.name = 'bla bla'

        when:
        controller.save()

        then:
        redirectArgs == [action: "create", params: [name: "bla bla", notes:null]]
        controller.flash.error == "Please enter a contact number. Contact cannot be saved without a contact number."
    }




}
