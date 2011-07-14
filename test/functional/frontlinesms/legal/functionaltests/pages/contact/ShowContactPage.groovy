package frontlinesms.legal.functionaltests.pages.contact

import geb.Page

class ShowContactPage extends Page {
    static at = { $("title").text() == "Show Contact Page"}
    static url = "legalContact/show"
    static content = {
        name{ $("input", id: "contact-name").value() }
        primaryMobile { $("input", id: "contact-primary-mobile").value()}
        notes {$("input", id: "contact-notes")}
        linkCaseButton{ $("button", id: "link-case-button")}

    }
}

