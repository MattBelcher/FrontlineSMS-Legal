package frontlinesms.legal

import spock.lang.*
import grails.plugin.spock.*

class CaseViewControllerSpec extends ControllerSpec {

    def "retrieve case details given case ID "() {
        setup:
        def cases =[]
        cases.add(new Case(caseId: "1234", description: "whatever"))
        mockDomain(Case,cases)

        controller.params.id = "1234"

        when:
        def thisCase = controller.caseview()

        then:
        thisCase.caseId == cases[0].caseId
    }
}

