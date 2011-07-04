package frontlinesms.legal.functionaltests.pages.cases

import geb.Page
import geb.Module

class SearchCasePage extends Page {


    static url = "case/search"

    static content = {
        id { $("input", id: "caseId") }
        search { $("input", id: "case-search") }
        SearchResults {$("tbody tr").collect {module CaseRow, it}

        }
    }
}

        class CaseRow extends Module {
            static content = {
                cell { i -> $("td", i) }
                caseId { cell(0).text() }


            }
        }


