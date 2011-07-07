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

    def "should be able to create case with id, description"() {
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

    def 'should remain on Create case page when no is clicked on cancel confirm dialog'() {
        given:
        to NewCasePage

        when:
        caseId = "123"
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
        caseId = "123"
        cancel.click()

        and:
        cancelYes.click()

        then:
        assert at(HomePage)
    }


}
