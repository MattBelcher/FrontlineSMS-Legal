package frontlinesms.legal.cases

import frontlinesms.legal.Case
import frontlinesms.legal.functionaltests.FrontlinesmsLegalGebSpec
import frontlinesms.legal.functionaltests.pages.cases.ShowCasePage

class ShowCaseSpec extends FrontlinesmsLegalGebSpec {

    def "should display case details given case id"() {
        given:
        String url=ShowCasePage.url;
        ShowCasePage.url+="/testCaseid"
        def newCase = new Case(caseId: "testCaseid", description: "whatever")
        newCase.save()

        when:
        to ShowCasePage
        ShowCasePage.url=url

        then:
        caseId == "testCaseid"
        description == "whatever"

    }
}

