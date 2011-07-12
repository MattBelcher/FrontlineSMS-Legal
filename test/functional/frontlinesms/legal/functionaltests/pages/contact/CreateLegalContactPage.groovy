package frontlinesms.legal.functionaltests.pages.contact

import geb.Page


class CreateLegalContactPage extends Page {
    static at = { $("title").text() == "Create New Contact" }
    static url = "legalContact/create"
    static content = {
        contactName { $("input", id: "contact-name") }
        contactNumber { $("input", id: "contact-number") }
        contactNotes { $("input", id: "contact-notes") }
        save { $("input", id: "contact-save") }
    }
}
