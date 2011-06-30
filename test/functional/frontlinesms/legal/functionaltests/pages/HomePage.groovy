package frontlinesms.legal.functionaltests.pages

import geb.Page

class HomePage extends Page {
    static url = ""
    static content = {
    createNewCase { $("a", name: "createNewCase") }
    }
}
