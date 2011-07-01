package frontlinesms.legal.functionaltests.pages.cases

import geb.Page

class NewCasePage extends Page {
    static url = "case/create"
    static content = {
        caseId { $("input", id: "case-id") }
        description { $("input", id: "case-description") }
        save { $("input", id: "case-save") }
        cancel { $("button", id: "case-cancel") }
        errorMessage { $("div", id: "errorMessage").text() }
        caseCancelDialog { $("div" , id: "case-cancel-dialog")}
        cancelYes { $("button", id: "cancel-confirm-yes")}
        cancelNo { $("button", id: "cancel-confirm-no")}
    }
}