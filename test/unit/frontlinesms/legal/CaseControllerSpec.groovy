package frontlinesms.legal

import grails.plugin.spock.ControllerSpec

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

    def "should save case"() {
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

    def 'should display error message and redirect to create when case id is blank'() {
        given:
        mockDomain(Case)

        when:
        controller.save()

        then:
        redirectArgs == [action: "create"]
        controller.flash.error == "Case number is required"
    }

    def 'should display an error when creating a case with duplicate id'() {
        given:
        def cases=[]
        cases.add(new Case(caseId: '1234'))
        mockDomain(Case, cases)

        controller.params.caseId = '1234'

        when:
        controller.save()

        then:
        controller.flash.error == 'Case number already exists. Please enter a unique case number'
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
        foundCases['foundCase'].caseId == '456'

    }

    def 'should display case details given case id'() {
        given:
        def newCase = new Case(caseId: "1234")
        mockDomain(Case, [newCase])
        controller.params.id = "1234"

        when:
        def displayedCase = controller.show().caseToDisplay

        then:
        displayedCase.caseId == newCase.caseId
    }

    def "linkContact should redirect to contact controller with case details as params"() {
        given:
        def newCase = new Case(caseId: "1234", description: "some description")
        mockDomain(Case, [newCase])
        controller.params.currentId = newCase.id
        def newCaseId = controller.params.newCaseId = "4567"
        def newCaseDescription = controller.params.newCaseDescription = "Other description"
        def contactNames = controller.params.contactNames = ["John", "James"]
        def contactNumbers = controller.params.contactPhone = ["123456789", "987654321"]
        def contactTypes = controller.params.contactType = ["Client", "Witness"]

        when:
        controller.linkContact()

        then:
        redirectArgs == [controller: "legalContact", action: "linkContact",
                params: [id: newCase.id, newCaseId: newCaseId, newCaseDescription: newCaseDescription,
                        contactNames: contactNames, contactNumbers: contactNumbers, contactTypes: contactTypes]]
    }
}
