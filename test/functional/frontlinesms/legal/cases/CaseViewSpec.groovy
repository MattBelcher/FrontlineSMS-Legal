package frontlinesms.legal.cases

import frontlinesms.legal.functionaltests.FrontlinesmsLegalGebSpec
import frontlinesms.legal.functionaltests.pages.cases.CaseViewPage
import frontlinesms.legal.Case
import frontlinesms.legal.functionaltests.pages.cases.LinkContactsPage
import frontlinesms2.Contact

/**
 * Created by IntelliJ IDEA.
 * User: Thoughtworks
 * Date: 6/29/11
 * Time: 2:29 PM
 * To change this template use File | Settings | File Templates.
 */
class CaseViewSpec  extends FrontlinesmsLegalGebSpec{

    def "create page on any case id"(){

        given:
        def newCase = new Case(caseId: "5678", description: "whatever")
            newCase.save()


        when:
                to CaseViewPage

        then:
        title == "Case View"
        id.value() == "5678"
        description.value()=="whatever"
    }


    def "pop up window on clicking link button"(){
        given:
        to CaseViewPage

        when:
        linkCase.click();
        then:
        at LinkContactsPage
        title == "Link Contacts"
        searchField.isEmpty()

    }

    def "link contacts page should contain table for contacts"(){
        given:
        to LinkContactsPage
        when:
        true
        then:
        nameCellofContactsTable == "Contacts"


    }

}

