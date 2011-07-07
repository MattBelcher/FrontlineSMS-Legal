package frontlinesms.legal.functionaltests.pages.cases

import geb.Page
import geb.Module

class ShowCasePage extends Page {
    static at = { $("div", id: "status").text() == "Case created" }
    static url = "case/show"
    static content = {
        caseId { $("input", id: "case-id").value() }
        description { $("textarea", id: "case-description").value()}
        saveCaseButton {$("input", id: "case-save")}
        status { $("div", id: "status").text() }
    /*    contact { name, type ->
            $("#link-contact-button").click()
            $("#contact-name-search").value(name)
            $("#search-button").click()
            $("#search-results td a").first().click()
            $("#contact-type").value(type)
            $("#add-contact").click()

        }*/
        clickLinkContact {$("#link-contact-button")}
        contactsTable {$("#link-contacts-inner-table-div #contactsTable tbody tr").collect {module ContactRow, it} }
        contactName { $("#contacts td.contact-name").first().text() }
        contactType { $("#contacts td.contact-type").first().text() }


}

}
      class ContactRow extends Module {
            static content = {
                cell { i -> $("td", i) }
                name { cell(0).text() }


            }

      }