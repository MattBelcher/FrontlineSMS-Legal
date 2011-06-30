package frontlinesms.legal.functionaltests.pages.cases

import geb.Page

class NewCasePage extends Page {
    static url = "case/create"
    static content = {
        caseId { $("input", id: "case-id") }
        description { $("input", id: "case-description") }
        save { $("input", id: "case-save") }
        status { $("div", id: "status").text() }
    }
}