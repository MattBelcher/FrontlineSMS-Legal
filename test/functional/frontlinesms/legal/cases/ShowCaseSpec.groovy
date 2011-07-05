package frontlinesms.legal.cases

import frontlinesms.legal.Case
import frontlinesms.legal.functionaltests.FrontlinesmsLegalGebSpec
import frontlinesms.legal.functionaltests.pages.cases.ShowCasePage
import spock.lang.Ignore

class ShowCaseSpec extends FrontlinesmsLegalGebSpec {

    def "should display case details given case id"() {
        given: aCase(caseId: "testCaseid", description: "whatever")

        when: to ShowCasePage, "testCaseid"

        then: caseId == "testCaseid"
        and: description == "whatever"
    }

    @Ignore
    def "add a contact to existing case"() {
        given: aCase(caseId: "updateTest",description: "adding contact")

        when:
        to ShowCasePage, "updateTest"
        contact("tom","client")

        then:
        contactName == "tom"
        contactType == "client"


    }

    private Case aCase(options) {
        return new Case(options).save()
    }


}

