package frontlinesms.legal.functionaltests.pages.contact

import geb.Module
import geb.Page

class ShowLegalContactPage extends Page {
    static at = { $("title").text() == "Show Contact Page"}
    static url = "legalContact/show"
    static content = {
        name { $("input", id: "contact-name").value() }
        primaryMobile { $("input", id: "contact-primary-mobile").value()}
        notes {$("textarea", id: "contact-notes")}
        linkCaseButton { $("button", id: "link-case-button")}
        pastEventsTable {$("#past-events tbody tr").collect {module EventRow, it} }
        futureEventsTable {$("#future-events tbody tr").collect {module EventRow, it} }
//        linkedContactsTable {$("#future-events tbody tr").collect {module CaseRow, it} }
         casesToLink {
            try {
                $("tr[class='caseLink']")
            }
            catch (Exception e) {
                null
            }
        }
//=======
        currentEventsTable {$("#current-events tbody tr").collect {module EventRow, it} }
//>>>>>>> Anshul/Deva: Completed Legal-5: View upcoming and last event for a client
    }
}
class EventRow extends Module {
    static content = {
        cell { i -> $("td", i) }
        title { cell(0).text() }
    }
}
class CaseRow extends Module {
    static content = {
        cell { i -> $("td", i) }
        caseId { $("span").text() }
        remove { cell(2) }
    }
}
