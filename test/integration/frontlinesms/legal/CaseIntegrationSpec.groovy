package frontlinesms.legal

import frontlinesms2.Contact
import grails.plugin.spock.IntegrationSpec

class CaseIntegrationSpec extends IntegrationSpec {

    def 'should be able to save a case to the database'() {
        given:
        def newCase = new Case(caseId: "1234", description: "Whatever")

        when:
        def success = newCase.save()

        then:
        success != null
    }

    def 'should not be able to save a case with a duplicate id'() {
        given:
        def case1 = new Case(caseId: "1234", description: "case 1")
        def case2 = new Case(caseId: "1234", description: "case 2")
        case1.save()

        when:
        def returnValue = case2.save()

        then:
        returnValue == null
    }

    def 'should not be able to save a case with a null case id'() {
        given:
        def newCase = new Case(caseId: null, description: "some description")

        when:
        def returnValue = newCase.save()

        then:
        returnValue == null
    }

    def 'should not be able to save with blank case id'() {
        given:
        def newCase = new Case(caseId: "", description: "some description")

        when:
        def returnValue = newCase.save()

        then:
        returnValue == null
    }

    def 'should save case without a description'() {
        given:
        def newCase = new Case(caseId: "4567")

        when:
        def success = newCase.save()

        then:
        success != null
    }

    def 'should update case description'() {
        given:
        def newCase = new Case(caseId: "4567", description: "Old description")
        newCase.save();

        when:
        def caseToUpdate = Case.findByCaseId("4567")
        caseToUpdate.description = "New description"
        caseToUpdate.save()

        then:
        def updatedCase = Case.findByCaseId("4567")
        updatedCase.description == caseToUpdate.description
    }

    def 'should update case id'() {
        given:
        def newCase = new Case(caseId: "4567")
        newCase.save();

        when:
        def caseToUpdate = Case.findByCaseId("4567")
        caseToUpdate.caseId = "ABCD"
        caseToUpdate.save()

        then:
        def updatedCase = Case.findByCaseId("ABCD")
        updatedCase.caseId == caseToUpdate.caseId
        Case.findByCaseId("4567") == null
    }

    def 'case id should not be null on update'() {
        given:
        def newCase = new Case(caseId: "4567")
        newCase.save();

        when:
        def caseToUpdate = Case.findByCaseId("4567")
        caseToUpdate.caseId = ""

        then:
        caseToUpdate.save() == null
    }

    def "add contacts to case"() {
        given:
        def newCase = new Case(caseId: "4567", description: "adding contact")
        newCase.save()
        def contact = new LegalContact(name: "Dumbledore", primaryMobile: "987654321").save()
        contact.save()

        when:
        newCase.addToContacts(contact)
        newCase.save()

        then:
        def savedCase = Case.findByCaseId("4567")
        savedCase.contacts.contains(contact)
    }
    def "new cases should be active by default"(){
        given:
        def newCase = new Case(caseId: "4567")
        newCase.save();

        when:
        def storedCase = Case.findByCaseId("4567")

        then:
        storedCase.active
    }
    def "should be able to link contacts to case"() {
        given:
        def newCase = new Case(caseId: "4567", description: "adding contact")
        newCase.save()
        def contact = new LegalContact(name: "Dumbledore", primaryMobile: "987654321").save()
        contact.save()

        when:
        def caseContact = CaseContacts.link(newCase, contact, "witness")

        then:
        caseContact.legalCase.caseId == newCase.caseId
        and:
        caseContact.legalContact.primaryMobile == contact.primaryMobile

    }
}
