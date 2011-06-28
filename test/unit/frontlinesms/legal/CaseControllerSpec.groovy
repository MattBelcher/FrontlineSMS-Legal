package frontlinesms.legal

import grails.plugin.spock.ControllerSpec

class CaseControllerSpec extends ControllerSpec {
    def 'should create action should redirect to Case detail page'() {
        setup:
        def newCase = new Case()
        newCase.caseId = "1234"
        newCase.description = "blablabla"
        controller.params.caseId = newCase.caseId
        controller.params.description = newCase.description

        when:
        controller.create()

        then:
        redirectArgs == [action: "show", params: [id: newCase.caseId]]
    }
}
