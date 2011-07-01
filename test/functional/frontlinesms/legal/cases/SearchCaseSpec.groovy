package frontlinesms.legal.cases

import frontlinesms.legal.functionaltests.FrontlinesmsLegalGebSpec
import frontlinesms.legal.functionaltests.pages.cases.SearchCasePage
import frontlinesms.legal.functionaltests.pages.cases.NewCasePage

class SearchCaseSpec extends FrontlinesmsLegalGebSpec {

    def "should be able to search case with caseId"() {
        given:
        to NewCasePage

        and:
        caseId = "9999"
        description = "whatever"

        and:
        save.click()

        and:
        to SearchCasePage

        when:
        caseId = "9999"

        and:
        search.click()

        then:
        at SearchCasePage
        SearchResults == "9999"


    }

    def "should return all the cases stored in the database when an empty search is carried out"(){

        given:
        to NewCasePage

        and:
        caseId = "1234"
        description = "someCase"

        and:
        save.click()

        and:
        to NewCasePage

        and:

        caseId = "5678"
        description = "anotherCase"

        and:
        save.click()

        and:
        to SearchCasePage

        when:
        caseId = ""

        and:
        search.click()

        then:
        at SearchCasePage
        SearchResults.contains('1234')

        and:
        SearchResults.contains('5678')

    }


}
