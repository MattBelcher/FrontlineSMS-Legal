package frontlinesms.legal.contacts

import frontlinesms.legal.functionaltests.FrontlinesmsLegalGebSpec
import frontlinesms.legal.functionaltests.pages.contact.CreateLegalContactPage
import frontlinesms.legal.functionaltests.pages.HomePage
import frontlinesms.legal.functionaltests.pages.contact.ShowContactPage

class CreateLegalContactSpec extends FrontlinesmsLegalGebSpec {
    def "should be able to navigate to the create page from the index page"() {
        given: to HomePage
        when:
        createNewContact.click()
        then:
        assert at(CreateLegalContactPage)
    }

    def "a lawyer should see fields to enter contact name, number, and notes when arriving at the contact page"() {
        given:
        to CreateLegalContactPage

        when:
        true

        then:

        name.present
        primaryMobile.present
        notes.present
    }

    def "should navigate to contact details page when user enters name and number"() {
        given:
        to CreateLegalContactPage
        
        when:
        name = "Jack"
        primaryMobile = "123456789"
        
        and:
        save.click()

        then:
        assert at(ShowContactPage)
    }

    def "should save contact and redirect to show page when user chooses to save contact without name"() {
        given:
        to CreateLegalContactPage
        name = ""
        primaryMobile = "8675309"
        
        when:
        save.click()

        and:
        saveWithoutNameYes.click()

        then:
        assert at(ShowContactPage)
        primaryMobile == "8675309"
    }

    def 'should go to home page when user chooses to cancel creation of contact when all fields are blank'() {
        given:
        to CreateLegalContactPage
        name = ""
        primaryMobile = ""
        notes = ""

        when:
        cancel.click()

        then:
        assert at(HomePage)
    }

}
