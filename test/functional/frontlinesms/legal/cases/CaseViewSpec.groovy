package frontlinesms.legal.cases

import frontlinesms.legal.functionaltests.FrontlinesmsLegalGebSpec
import frontlinesms.legal.functionaltests.pages.cases.CaseViewPage
import frontlinesms.legal.Case

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




}
