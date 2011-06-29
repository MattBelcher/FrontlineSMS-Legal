package frontlinesms.legal.cases

import frontlinesms.legal.functionaltests.FrontlinesmsLegalGebSpec
import frontlinesms.legal.functionaltests.pages.cases.CaseViewPage

/**
 * Created by IntelliJ IDEA.
 * User: Thoughtworks
 * Date: 6/29/11
 * Time: 2:29 PM
 * To change this template use File | Settings | File Templates.
 */
class CaseViewSpec  extends FrontlinesmsLegalGebSpec{

    def "create view on any case id"(){

        given:
        to CaseViewPage
        when:
        true
        then:
        title == "Case View"
        id.value() == "1234"
    }



}
