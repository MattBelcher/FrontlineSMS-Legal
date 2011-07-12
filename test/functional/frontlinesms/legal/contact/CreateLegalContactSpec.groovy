package frontlinesms.legal.contact

import frontlinesms.legal.functionaltests.FrontlinesmsLegalGebSpec
import frontlinesms.legal.functionaltests.pages.HomePage
import frontlinesms.legal.functionaltests.pages.contact.CreateLegalContactPage
import frontlinesms.legal.functionaltests.pages.contact.ShowContactPage


class CreateLegalContactSpec extends FrontlinesmsLegalGebSpec {

    def 'should be able to navigate to the create contact page from the main menu'(){

        given:
        to HomePage

        when:
        createNewContact.click()

        then:
        assert at (CreateLegalContactPage)



    }

    def "should be able to create contact with name, number and notes"() {
        given:
        to CreateLegalContactPage

        when:
        contactName = "Bob Barker"
        contactNumber = "5555555"
        contactNotes = "This is a very good client."

        and:
        save.click()

        then:
        assert at (ShowContactPage)

    }


}
