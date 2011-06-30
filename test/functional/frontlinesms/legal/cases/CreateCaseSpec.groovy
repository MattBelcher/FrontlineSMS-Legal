package frontlinesms.legal.cases

import frontlinesms.legal.functionaltests.FrontlinesmsLegalGebSpec
import frontlinesms.legal.functionaltests.pages.cases.NewCasePage
import frontlinesms.legal.functionaltests.pages.cases.ShowCasePage

class CreateCaseSpec extends FrontlinesmsLegalGebSpec {


    def "should be able to create case with id and description"() {

        given:
        to NewCasePage

        when:
        caseId = "123"
        description = "whatever"

        and:
        save.click()

        then:
        at ShowCasePage
        status == "Case created"
    }

    def "should display error message when creating a case without an id"() {
        given:
        to NewCasePage

        when:
        caseId = ""
        description = "some description"

        and:
        save.click()

        then:
        at NewCasePage
        errorMessage == "Case number is required"
    }

    def 'should display error message when creating a case with duplicate case Id'() {
        given:
        to NewCasePage
        caseId = "1234"
        description = "some description"
        save.click()
        to NewCasePage

        when:
        caseId = "1234"
        description = "some description"

        and:
        save.click()

        then:
        at NewCasePage
        errorMessage == "Case number already exists. Please enter a unique case number"
    }
}
