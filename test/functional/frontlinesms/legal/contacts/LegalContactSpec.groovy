package frontlinesms.legal.contacts

import frontlinesms.legal.functionaltests.FrontlinesmsLegalGebSpec
import frontlinesms.legal.functionaltests.pages.contact.CreateLegalContactPage
import frontlinesms.legal.functionaltests.pages.HomePage

class LegalContactSpec extends FrontlinesmsLegalGebSpec{
    def "should be able to navigate to the create page from the index page"(){
        given: to HomePage
        when:
        createNewContact.click()
        then:
        assert at (CreateLegalContactPage)
    }



}