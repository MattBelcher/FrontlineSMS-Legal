package frontlinesms.legal.functionaltests.pages.cases

import geb.Page

class ShowCasePage extends Page {
    static url = "case/show"
    static content = {
        caseId { $("input", id: "case-id").value() }
        description { $("textarea", id: "case-description").value()}
        saveCaseButton{$("input",id:"case-save")}
        status { $("div", id: "status").text() }
    }

}
