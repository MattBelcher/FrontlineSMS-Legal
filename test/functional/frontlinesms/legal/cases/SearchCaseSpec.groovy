package frontlinesms.legal.cases

import frontlinesms.legal.functionaltests.FrontlinesmsLegalGebSpec
import frontlinesms.legal.functionaltests.pages.cases.SearchCasePage
import frontlinesms.legal.functionaltests.pages.cases.NewCasePage
import frontlinesms.legal.Case


class SearchCaseSpec extends FrontlinesmsLegalGebSpec {

    def "should be able to search case with caseId"() {
        setup:
        Case someCase = new Case(caseId: '1234', description: 'blah')
        someCase.save(flush: true)

        and:
        to SearchCasePage

        when:
        caseId = "1234"

        and:
        search.click()

        then:
        Results == "1234"


    }

}
