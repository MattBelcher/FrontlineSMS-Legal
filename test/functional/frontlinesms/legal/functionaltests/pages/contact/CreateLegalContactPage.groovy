package frontlinesms.legal.functionaltests.pages.contact

import geb.Page


class CreateLegalContactPage extends Page {
    static at = { $("title").text() == "Create New Contact" }
    static url = "contact/create"
    static content = {}
}
