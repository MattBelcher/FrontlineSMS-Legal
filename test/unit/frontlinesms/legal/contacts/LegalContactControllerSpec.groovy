package frontlinesms.legal.contacts

import grails.plugin.spock.ControllerSpec
import frontlinesms.legal.LegalContact
import frontlinesms.legal.Case
import frontlinesms2.Contact


class LegalContactControllerSpec extends ControllerSpec {

    def "update action should update contact"() {
        given:
        def newLegalContact = new LegalContact(id: 666, name: "John Doe", primaryMobile: "5285", notes: "He is anonymous.")
        mockDomain(LegalContact, [newLegalContact])
        controller.params.name = "Steve Jobs"
        controller.params.primaryMobile = "5285"
        controller.params.notes = "Identified."
        controller.params.id = 666

        when:
        controller.update()

        then:
        newLegalContact.name == "Steve Jobs"
    }


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
         def contacts = []
         mockDomain(LegalContact, contacts)

         controller.params.name = 'Steve Jobs'
         controller.params.primaryMobile = '666'

         when:
         controller.save()

         then:
         controller.flash.message == "Contact Saved"
         redirectArgs.action == "show"
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

    def 'should display error if primaryMobile is not unique'(){
        given:
        def cases = []
        cases.add(new LegalContact(primaryMobile: '999'))
        mockDomain(LegalContact, cases)
        controller.params.name = 'Steve'
        controller.params.primaryMobile = '999'

        when:
        controller.save()

        then:
        println cases
        redirectArgs == [action: "create", params: [name: "Steve", notes:null, primaryMobile:'999']]
        controller.flash.error == "Contact number already exists. Please enter a unique contact number."
    }

    def 'should display all the cases when the popup appears'() {
        given:
        def newLegalContact = new LegalContact(primaryMobile: "4567")
        mockDomain(LegalContact, [newLegalContact])
        controller.params.primaryMobile = "4567"
        def newCase = [new Case(caseId:'23')]
        mockDomain(Case, newCase)

        when:
        def models = controller.show()

        then:
        models['foundCase'] == newCase
    }
}
