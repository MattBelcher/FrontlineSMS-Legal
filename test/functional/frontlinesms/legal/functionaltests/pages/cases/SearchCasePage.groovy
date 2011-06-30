package frontlinesms.legal.functionaltests.pages.cases

import geb.Page

class SearchCasePage extends Page {


    static url = "case/search"

    static content = {
        id { $("input", id: "caseId") }
        search { $("input", id: "case-search") }
        SearchResults { $("div", id: "SearchResults").text()}
    }
}




