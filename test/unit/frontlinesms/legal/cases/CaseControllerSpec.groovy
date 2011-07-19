package frontlinesms.legal.cases

import grails.plugin.spock.ControllerSpec
import frontlinesms.legal.Case
import frontlinesms.legal.LegalContact
import frontlinesms2.Contact
import org.junit.Ignore
import frontlinesms.legal.CaseContacts

class CaseControllerSpec extends ControllerSpec {

    def 'should create action should redirect to Case detail page'() {
        setup:
        mockDomain(Case)

        controller.params.caseId = '1234'
        controller.params.description = 'hagsdhs'

        when:
        controller.save()

        then:
        redirectArgs == [action: "show", params: [id: "1234"]]
    }

    def "should save case with Id and description"() {
        setup:
        def cases = []
        mockDomain(Case, cases)
        controller.params.caseId = '1234'
        controller.params.description = 'hagsdhs'

        when:
        controller.save()

        then:
        Case.count() == 1
    }

    def "should save contact link when linked on create case"() {
        setup:
        mockDomain(Case)
        mockDomain(CaseContacts)
        mockDomain(LegalContact, [new LegalContact(id: 1, name: "John Doe", primaryMobile: "435352", notes: "hii")])

        when:
        controller.params.caseId = "2"
        controller.params.linkedContactIds = ",1"
        controller.params.involvementList = ",somthing"

        and:
        controller.save()

        then:
        CaseContacts.count() == 1
    }

    def 'should display error message and redirect to create when case id is blank'() {
        given:
        mockDomain(Case)
        controller.params.description = '1234'

        when:
        controller.save()

        then:
        redirectArgs == [action: "create", params: [description: "1234"]]
        controller.flash.error == "Case number is required"
    }

    def 'should display an error when creating a case with duplicate id'() {
        given:
        def cases = []
        cases.add(new Case(caseId: '1234'))
        mockDomain(Case, cases)

        controller.params.caseId = '1234'

        when:
        controller.save()

        then:
        controller.flash.error == 'Case number already exists. Please enter a unique case number.'
    }

    def 'should display list of cases matching search criteria'() {
        given:
        def casesList = []
        casesList.add(new Case(caseId: '456'))
        mockDomain(Case, casesList)


        controller.params.caseId = '456'
        when:
        def foundCases = controller.search()

        then:
        foundCases['foundCase'].get(0).caseId == '456'

    }

    def 'should display case details given case id'() {
        given:
        def newCase = new Case(caseId: "1234")
        def contactList = []
        mockDomain(Case, [newCase])
        mockDomain(LegalContact, contactList)
        controller.params.id = "1234"

        when:
        def displayedCase = controller.show()['caseToDisplay']

        then:
        displayedCase.caseId == newCase.caseId
    }

    def 'should display all the contacts when the popup appears'() {
        given:
        def newCase = new Case(caseId: "4567")
        mockDomain(Case, [newCase])
        controller.params.id = "4567"
        def newContact = [new Contact()]
        mockDomain(Contact, newContact)

        when:
        def models = controller.show()

        then:
        models['contactList'] == newContact
    }

    def 'should update case details'() {
        setup:
        def existingCase = new Case(caseId: '12344', description: 'hiiii')
        mockDomain(Case, [existingCase])
        controller.params.currentId = existingCase.id
        controller.params.caseId = '12344'
        controller.params.description = 'hagsdhs'
        controller.params.caseActive = false
        when:
        controller.update()

        then:
        existingCase.active == false
    }


}
