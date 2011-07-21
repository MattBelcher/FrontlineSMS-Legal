package frontlinesms.legal.contacts

import frontlinesms.legal.functionaltests.FrontlinesmsLegalGebSpec
import frontlinesms.legal.functionaltests.pages.HomePage
import frontlinesms.legal.functionaltests.pages.contact.CreateLegalContactPage
import frontlinesms.legal.functionaltests.pages.contact.ShowLegalContactPage

class CreateLegalContactSpec extends FrontlinesmsLegalGebSpec {

    def "should save contact and redirect to show page when user chooses to save contact without name"() {
        given:
        to HomePage
        createNewContact.click()

        when:
        at CreateLegalContactPage
        name = ""
        primaryMobile = "8675309"

        and:
        save.click()

        and:
        saveWithoutNameYes.click()

        then:
        assert at(ShowLegalContactPage)
        primaryMobile == "8675309"
    }

    def 'should go to home page when yes is clicked on cancel creation of contact when some fields are filled in'() {
        given:
        to CreateLegalContactPage

        when:
        name = "Bob"
        cancel.click()

        and:
        cancelYes.click()

        then:
        assert at(HomePage)
    }


}
