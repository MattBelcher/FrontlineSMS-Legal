package frontlinesms.legal.functionaltests.pages.contact

import geb.Page
import geb.Module

class ShowLegalContactPage extends Page {
    static at = { $("title").text() == "Show Contact Page"}
    static url = "legalContact/show"
    static content = {
        name{ $("input", id: "contact-name").value() }
        primaryMobile { $("input", id: "contact-primary-mobile").value()}
        notes {$("textarea", id: "contact-notes")}
        linkCaseButton{ $("button", id: "link-case-button")}
        pastEventsTable {$("#past-events tbody tr").collect {module EventRow, it} }
        futureEventsTable {$("#future-events tbody tr").collect {module EventRow, it} }
    }
}
class EventRow extends Module {
    static content = {
        cell { i -> $("td", i) }
        title { cell(0).text() }
    }
}

