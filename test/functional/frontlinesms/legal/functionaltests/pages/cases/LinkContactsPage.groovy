package frontlinesms.legal.functionaltests.pages.cases

import frontlinesms.legal.functionaltests.FrontlinesmsLegalGebSpec

/**
 * Created by IntelliJ IDEA.
 * User: devam
 * Date: 6/30/11
 * Time: 2:11 PM
 * To change this template use File | Settings | File Templates.
 */
class LinkContactsPage extends geb.Page{
    static url="caseView/linkContacts"

    static contents={
        title {$("title").text()}
        searchField {$("input", id:"link-contacts")}
        searchContacts {$("input",id:"submit-search")}
        nameCellofContactsTable {$("span",id:"name-header")}
        }

    }



