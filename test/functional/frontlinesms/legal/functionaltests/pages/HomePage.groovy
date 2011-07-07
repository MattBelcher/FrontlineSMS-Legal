package frontlinesms.legal.functionaltests.pages

import geb.Page

class HomePage extends Page {
    static at = { $("title").text() == "FrontlineSMS-Legal" }
    static url = ""
    static content = {
        createNewCase { $("a", name: "createNewCase") }
        createNewEvent { $("a", name: "createNewEvent") }
        errorMessage { $("div", id: "errorMessage")}
        statusMessage { $("div", id: "status")}
    }
}
