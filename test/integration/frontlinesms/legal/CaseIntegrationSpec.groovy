package frontlinesms.legal

import grails.plugin.spock.IntegrationSpec

class CaseIntegrationSpec extends IntegrationSpec {

    def 'should be able to save a case to the database'() {
        given:
        def newCase = new Case(caseId: "1234", description: "Whatever")

        when:
        def success = newCase.save(flush: true)

        then:
        success != null
    }


}
