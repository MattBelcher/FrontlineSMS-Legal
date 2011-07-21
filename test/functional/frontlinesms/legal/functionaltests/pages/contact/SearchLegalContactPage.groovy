package frontlinesms.legal.functionaltests.pages.contact

import geb.Page
import geb.Module

class SearchLegalContactPage extends Page {
    static url = "legalContact/search"

    static content = {
      searchResults {
            $("tbody tr").collect {module LegalContactRow, it}
        }
    }


}



class LegalContactRow extends Module {
    static content = {
        cell { i -> $("td", i) }
        name { cell(0).text() }
        primaryMobile { cell(1).text() }
    }

}