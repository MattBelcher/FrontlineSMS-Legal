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


    private Case aCase(options) {
        return new Case(options).save()
    }

    def "should display all frontlinesms.legal.contacts in contact list table in pop-up dialog on load"() {
        setup:
        new Contact(name: "fabio", primaryMobile: "22222").save(flush: true)
        new Contact(name: "dev", primaryMobile: "55555").save(flush: true)
        new Case(caseId: "1112").save(flush: true)
        when:
        to ShowCasePage, "1112"
        and:
        clickLinkContact.click()

        then:
        contactsTable.collect { it -> it.name }.contains("fabio")

        and:
        contactsTable.collect { it -> it.primaryMobile}.contains("22222")

        and:
        contactsTable.collect { it -> it.name }.contains("dev")
        and:
        contactsTable.collect { it -> it.primaryMobile }.contains("55555")
    }

}

