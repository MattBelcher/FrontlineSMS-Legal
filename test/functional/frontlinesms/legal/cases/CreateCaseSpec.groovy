package frontlinesms.legal.cases

import frontlinesms.legal.functionaltests.FrontlinesmsLegalGebSpec
import frontlinesms.legal.functionaltests.pages.cases.NewCasePage
import frontlinesms.legal.functionaltests.pages.cases.ShowCasePage

class CreateCaseSpec extends FrontlinesmsLegalGebSpec {

    def "should be able to create case with id and description"() {
        given:
        to NewCasePage

        when:
        caseId = "1234"
        description = "whatever"

        and:
        save.click()

        then:
        at ShowCasePage
        status == "success"
    }
}
