package frontlinesms.legal

import grails.plugin.spock.IntegrationSpec

class CaseViewIntegrationSpec extends IntegrationSpec {

    def "save should change the database state"() {
        given:
        def newCase = new Case(caseId: "134", description: "Whatever")
        when:
        newCase.save(flush: true)
        then:
        Case.count() == 1
    }

    def "retrieve case details from database"() {
        given:
        def newCase = new Case(caseId: "34", description: "Whatever")
        def caseController = new CaseViewController()
        when:
        newCase.save(flush: true)
        caseController.params.id = "34"
        then:
        def caseDetails = caseController.caseview().theCase
        caseDetails.caseId == "34"
        caseDetails.description == "Whatever"
    }
}
