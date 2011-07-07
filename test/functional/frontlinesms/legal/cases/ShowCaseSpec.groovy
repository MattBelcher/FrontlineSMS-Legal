package frontlinesms.legal.cases

import frontlinesms.legal.Case
import frontlinesms.legal.functionaltests.FrontlinesmsLegalGebSpec
import frontlinesms.legal.functionaltests.pages.cases.ShowCasePage
import spock.lang.Ignore
import frontlinesms.legal.LegalContact
import frontlinesms2.Contact

class ShowCaseSpec extends FrontlinesmsLegalGebSpec {

    def "should display case details given case id"() {
        given: aCase(caseId: "testCaseid", description: "whatever")

        when: to ShowCasePage, "testCaseid"

        then: caseId == "testCaseid"
        and: description == "whatever"
    }

    @Ignore
    def "add a contact to existing case"() {
        given: aCase(caseId: "updateTest", description: "adding contact")

        when:
        to ShowCasePage, "updateTest"
        contact("tom", "client")

        then:
        contactName == "tom"
        contactType == "client"


    }

    private Case aCase(options) {
        return new Case(options).save()
    }

    def "should display all contacts in contact list table in pop-up dialog on load"() {
        setup:
        new Contact(name: "fabio").save(flush:true)
        new Contact(name: "dev").save(flush:true)
        new Case(caseId: "1112").save(flush:true)
        when:
        to ShowCasePage, "1112"
        // contact("fabio","client")
        clickLinkContact.click()

        then:
        contactsTable.collect { it -> it.name }.contains("fabio")


    }


}

