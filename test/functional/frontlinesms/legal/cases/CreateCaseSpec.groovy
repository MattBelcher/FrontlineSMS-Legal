package frontlinesms.legal.cases

import frontlinesms.legal.functionaltests.FrontlinesmsLegalGebSpec
import frontlinesms.legal.functionaltests.pages.HomePage
import frontlinesms.legal.functionaltests.pages.cases.NewCasePage
import frontlinesms.legal.functionaltests.pages.cases.ShowCasePage

class CreateCaseSpec extends FrontlinesmsLegalGebSpec {

    def "should be able to navigate to the create case page from the menu"() {
        given:
        to HomePage

        when:
        createNewCase.click()

        then:
        assert at(NewCasePage)
    }

    def "should be able to create case with id and description"() {
        given:
        to NewCasePage

        when:
        caseId = "123"
        description = "whatever"

        and:
        save.click()

        then:
        assert at(ShowCasePage)
    }

    def "should display error message when creating a case without an id"() {
        given:
        to NewCasePage

        when:
        caseId = ""
        description = "some description"

        and:
        save.click()

        then:
        errorMessage == "Case number is required"
    }

    def "should display error message when creating a case with an id of only whitespace"() {
        given:
        to NewCasePage

        when:
        caseId = "      "
        description = "some description"

        and:
        save.click()

        then:
        errorMessage == "Case number is required"
    }

    def 'should display error message when creating a case with duplicate case Id'() {
        given:
        to NewCasePage
        caseId = "1234"
        description = "some description"
        save.click()
        to NewCasePage

        when:
        caseId = "1234"
        description = "some description"

        and:
        save.click()

        then:
        errorMessage == "Case number already exists. Please enter a unique case number."
    }

    def 'should open confirmation dialog when cancel is clicked'(){
        given:
        to NewCasePage

        when:
        cancel.click()

        then:
        caseCancelDialog.displayed == true
    }

    def 'should hide cancel confirm dialog when no is clicked'(){
        given:
        to NewCasePage

        when:
        cancel.click()

        and:
        cancelNo.click()

        then:
        caseCancelDialog.displayed == false
    }

    def 'should remain on Create case page when no is clicked on cancel confirm dialog'(){
        given:
        to NewCasePage

        when:
        cancel.click()

        and:
        cancelNo.click()

        then:
        assert at(NewCasePage)
    }

    def 'should go to home page when yes is clicked on cancel confirm dialog'() {
        given:
        to NewCasePage

        when:
        cancel.click()

        and:
        cancelYes.click()

        then:
        assert at(HomePage)
    }


}
