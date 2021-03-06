package frontlinesms.legal.functionaltests.pages.cases

import geb.Page
import geb.Module

class ShowCasePage extends Page {
    static at = { $("title").text() == "ShowCasePage"}
    static url = "case/show"
    static content = {
        caseId { $("input", id: "case-id").value() }
        currentId {$("input", id: "current-Id").value()}
        description { $("textarea", id: "case-description").value()}
        updateCaseButton {$("input", id: "case-update")}
        caseActive { $("input", id: "case-status") }
        clickLinkContact {$("#link-contact-button")}
        contactsTable {$("#link-contacts-inner-table-div #contactsTable tbody tr").collect {module ContactRow, it} }
        statusMessage { $("div", id: "status").text() }
        errorMessage { $("div", id: "errorMessage").text() }
        linkedContactsRow {$(name:"contactRow", id: "contact-row")}
    }

}
class ContactRow extends Module {
    static content = {
        cell { i -> $("td", i) }
        name { cell(0).text() }
        primaryMobile { cell(1).text() }
    }
}
