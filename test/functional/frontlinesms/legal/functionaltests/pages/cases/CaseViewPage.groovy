package frontlinesms.legal.functionaltests.pages.cases

/**
 * Created by IntelliJ IDEA.
 * User: Thoughtworks
 * Date: 6/29/11
 * Time: 2:33 PM
 * To change this template use File | Settings | File Templates.
 */
class CaseViewPage extends geb.Page {
    static url="caseView/caseview/5678"
    static content= {
        id { $("input", id: "case-id") }
        description { $("input", id: "case-description") }
        linkCase { $("input", id: "case-link") }

    }

}

