package frontlinesms.legal.functionaltests.pages.contact

import geb.Page

/**
 * Created by IntelliJ IDEA.
 * User: Thoughtworks
 * Date: 7/12/11
 * Time: 10:01 AM
 * To change this template use File | Settings | File Templates.
 */
class ShowContactPage extends Page {
    static at = { $("title").text() == "Show Contact Page"}
    static url = "legalContact/show"
    static content = {
        contactName { $("input", id: "contact-name").value() }
        contactNumber { $("input", id: "contact-number").value()}
        contactNotes {$("input", id: "contact-notes")}
    }
}

