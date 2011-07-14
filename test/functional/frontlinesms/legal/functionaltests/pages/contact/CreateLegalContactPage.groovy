package frontlinesms.legal.functionaltests.pages.contact

import geb.Page


class CreateLegalContactPage extends Page {
    static at = { $("title").text() == "Create New Contact" }
    static url = "legalContact/create"
    static content = {
        name { $("input", id: "contact-name")}
        primaryMobile { $("input", id: "contact-primary-mobile")}
        notes { $("input", id: "contact-notes")}
        save { $("button", id: "contact-save") }
        cancel { $("button", id: "contact-create-cancel") }
        saveWithoutNameDialog { $("div", id: "contact-save-no-name-dialog")}
        saveWithoutNameYes { $("button", id: "save-confirm-yes") }
  }
}
