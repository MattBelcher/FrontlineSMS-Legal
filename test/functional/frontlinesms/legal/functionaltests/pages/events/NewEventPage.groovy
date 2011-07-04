package frontlinesms.legal.functionaltests.pages.events

import geb.Page

class NewEventPage extends Page {
    static at = { $("title").text() == "Create New Event" }
    static url = "event/create"
    static content = {
        dateFieldSelected {
            $("input", id: "event-date").click()
            waitFor(3) { datePicker.present }
        }
        datePicker { $("div" , id: "ui-datepicker-div")}
    }
}
