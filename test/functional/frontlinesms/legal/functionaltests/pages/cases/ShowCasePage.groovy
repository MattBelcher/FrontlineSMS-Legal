package frontlinesms.legal.functionaltests.pages.cases

import geb.Page

class ShowCasePage extends Page {
    static url = "case/show"
    static content = {
        status { $("div", id: "status").text() }
    }

}
