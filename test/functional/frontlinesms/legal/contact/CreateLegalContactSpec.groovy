package frontlinesms.legal.contact

import frontlinesms.legal.functionaltests.FrontlinesmsLegalGebSpec
import frontlinesms.legal.functionaltests.pages.HomePage
import frontlinesms.legal.functionaltests.pages.contact.CreateLegalContactPage


class CreateLegalContactSpec extends FrontlinesmsLegalGebSpec {

    def 'should be able to navigate to the create contact page from the main menu'(){

        given:
        to HomePage

        when:
        createNewContact.click()

        then:
        assert at (CreateLegalContactPage)



    }


}
