package frontlinesms.legal.contacts

import frontlinesms.legal.functionaltests.FrontlinesmsLegalGebSpec
import frontlinesms.legal.functionaltests.pages.HomePage
import frontlinesms.legal.functionaltests.pages.contact.SearchLegalContactPage
import frontlinesms.legal.LegalContact

class SearchLegalContactSpec extends FrontlinesmsLegalGebSpec {

    def 'should navigate to search legal contact page and list of contacts is displayed when search contact link is clicked at the home page'() {
        given:
        def newContact = new LegalContact(name: 'Me', primaryMobile: '98765')
        newContact.save()

        and:
        to HomePage

        when:
        searchContactLink.click()

        then:
        at SearchLegalContactPage

        and:
        searchResults.collect {it->it.name}.contains('Me')
        searchResults.collect {it->it.primaryMobile}.contains('98765')

    }

}
