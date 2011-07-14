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
        and: caseActive == true

    }



    private Case aCase(options) {
        return new Case(options).save()
    }

    def "should display all contacts in contact list table in pop-up dialog on load"() {
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

    def "should be able to edit description of case"() {
        setup:
        new Case(caseId: "1112", description: "ertyui").save(flush: true)
        when:
        to ShowCasePage, "1112"
        and:
        description = "jughjbgjhnbj"
        and:
        updateCaseButton.click()

        then:
        statusMessage == "Case details updated"

    }

    def "should be able to edit case ID of case"() {
        setup:
        new Case(caseId: "1112", description: "ertyui").save(flush: true)
        when:
        to ShowCasePage, "1112"
        and:
        caseId = "1123"
        and:
        updateCaseButton.click()

        then:
        statusMessage == "Case details updated"

    }

    def "should not update case id that already exists"() {
        setup:
        new Case(caseId: "1112", description: "ertyui").save(flush: true)
        new Case(caseId: "1123", description: "ertyui").save(flush: true)
        when:
        to ShowCasePage, "1112"
        and:
        caseId = "1123"
        and:
        updateCaseButton.click()

        then:
        errorMessage == "Case number already exists. Please enter a unique case number."

    }

    def "should show the same description on trying to update case id that already exists"() {
        setup:
        new Case(caseId: "1112", description: "ertyui").save(flush: true)
        new Case(caseId: "1123", description: "ertyui").save(flush: true)
        when:
        to ShowCasePage, "1112"
        and:
        caseId = "1123"
        description = "new description"

        and:
        updateCaseButton.click()

        then:
        at ShowCasePage
        description == "new description"

        and:
        caseId == "1112"

    }

    def "should not allow update case if case id is blank"() {
        setup:
        new Case(caseId: "1112", description: "ertyui").save(flush: true)
        when:
        to ShowCasePage, "1112"
        and:
        caseId = ""
        and:
        updateCaseButton.click()

        then:
        errorMessage == "Case number required"

    }


}

