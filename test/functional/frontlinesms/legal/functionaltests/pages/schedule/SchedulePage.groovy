package frontlinesms.legal.functionaltests.pages.schedule

import geb.Page

class SchedulePage extends Page {
    static at = { $("title").text() == "Schedule" }
    static url = "schedule"
    static content = {
        events { $("span[class='fc-event-title']") }
    }
}
