package frontlinesms.legal

import grails.plugin.spock.ControllerSpec

class CaseViewControllerSpec extends ControllerSpec {

    def "retrieve case details given case ID "() {
        setup:
        def cases = []
        cases.add(new Case(caseId: "1234", description: "whatever"))
        mockDomain(Case, cases)

        controller.params.id = "1234"

        when:
        true
        then:
        controller.caseview().theCase.caseId == cases[0].caseId
    }
}

