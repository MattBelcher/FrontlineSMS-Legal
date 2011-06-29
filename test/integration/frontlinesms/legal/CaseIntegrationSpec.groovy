package frontlinesms.legal

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

    def 'should not be able to save with blank case id'(){
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


}
