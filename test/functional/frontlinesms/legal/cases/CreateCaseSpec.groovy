package frontlinesms.legal.cases

import frontlinesms.legal.functionaltests.FrontlinesmsLegalGebSpec
import frontlinesms.legal.functionaltests.pages.cases.NewCasePage

class CreateCaseSpec extends FrontlinesmsLegalGebSpec {

    def "should be able to create case with id and description"() {
        given:
        to NewCasePage

        when:
        id = "1234"
        description = "whatever"

        and:
        save.click()

        then:
        status == "success"
    }
}
