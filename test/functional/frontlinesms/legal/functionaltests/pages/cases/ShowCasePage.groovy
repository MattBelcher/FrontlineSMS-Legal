package frontlinesms.legal.functionaltests.pages.cases

import geb.Page
import geb.Module

class ShowCasePage extends Page {
    static at = { $("title").text() == "ShowCasePage"}
    static url = "case/show"
    static content = {
        caseId { $("input", id: "case-id").value() }
        description { $("textarea", id: "case-description").value()}
        saveCaseButton {$("input", id: "case-save")}
        status { $("div", id: "status").text() }
        clickLinkContact {$("#link-contact-button")}
        contactsTable {$("#link-contacts-inner-table-div #contactsTable tbody tr").collect {module ContactRow, it} }

    }

}
class ContactRow extends Module {
    static content = {
        cell { i -> $("td", i) }
        name { cell(0).text() }
        primaryMobile { cell(1).text() }
    }

}