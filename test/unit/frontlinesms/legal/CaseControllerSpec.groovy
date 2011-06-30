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
}
