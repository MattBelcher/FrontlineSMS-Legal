package frontlinesms.legal.contacts

import frontlinesms.legal.functionaltests.FrontlinesmsLegalGebSpec
import frontlinesms.legal.functionaltests.pages.contact.ShowContactPage
import frontlinesms.legal.functionaltests.pages.contact.CreateLegalContactPage

class LinkCaseToContactSpec extends FrontlinesmsLegalGebSpec{
    def "the link button should appear on the show contacts page"(){
        given:
        to CreateLegalContactPage

        when:
        name = "Jack"
        primaryMobile = "767676"

        and:
        save.click()

        then:
        assert at(ShowContactPage)
        linkCaseButton.present
    }
}
