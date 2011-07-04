package frontlinesms.legal.functionaltests.pages.events

import geb.Page

class NewEventPage extends Page {
    static at = { $("title").text() == "Create New Event" }
    static url = "event/create"
    static content = {
        dateField { $("input", id: "event-date") }
        datePicker { $("div" , id: "ui-datepicker-div")}
    }
}
