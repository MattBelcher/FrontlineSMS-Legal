package frontlinesms.legal.functionaltests.pages.schedule

import geb.Page

class SchedulePage extends Page {
    static at = { $("title").text() == "Schedule" }
    static url = "schedule"
    static content = {
        events {
            try {
                $("span[class='fc-event-title']")
            }
            catch (Exception e) {
                null
            }
        }
        isEventListSize {
            try {
                $("span[class='fc-event-title']").size()

            }
            catch (Exception e) {
                0
            }
        }
        deleteEvent {
            $('#delete-event').click()
            $('#cancel-confirm-yes').click()
            true
        }
        popupDialogEventTitle {$('#event-title').text()}

    }
}
