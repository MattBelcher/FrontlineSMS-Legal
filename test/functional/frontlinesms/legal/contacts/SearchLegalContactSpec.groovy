package frontlinesms.legal.contacts

import frontlinesms.legal.functionaltests.FrontlinesmsLegalGebSpec
import frontlinesms.legal.functionaltests.pages.HomePage
import frontlinesms.legal.functionaltests.pages.contact.SearchLegalContactPage

class SearchLegalContactSpec extends FrontlinesmsLegalGebSpec {

    def 'should navigate to search legal contact page when search contact link is clicked at the home page'() {
        given:
        to HomePage

        when:
        searchContactLink.click()

        then:
        at SearchLegalContactPage
    }

}
