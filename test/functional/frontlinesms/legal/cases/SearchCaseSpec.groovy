package frontlinesms.legal.cases

import frontlinesms.legal.Case
import frontlinesms.legal.functionaltests.FrontlinesmsLegalGebSpec
import frontlinesms.legal.functionaltests.pages.cases.SearchCasePage
import org.codenarc.results.Results
import org.springframework.stereotype.Controller

class SearchCaseSpec extends FrontlinesmsLegalGebSpec {

    def "should be able to search case with caseId"() {
        setup:
        Case someCase = new Case(caseId: "9999", description: "blah")

        someCase.save(flush: true)

        and:
        to SearchCasePage

        when:
        caseId = "9999"

        and:
        search.click()

        then:
        at SearchCasePage
        Results == "9999"


    }

}
