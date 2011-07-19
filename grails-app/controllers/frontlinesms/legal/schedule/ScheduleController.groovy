package frontlinesms.legal.schedule

import frontlinesms.legal.Event
import frontlinesms.legal.EventContact

class ScheduleController {

    def index = {
    [hh:"2011, 5"]}

    def fetchEvents = {
        def eventsList = Event.list()
        render(contentType: "text/json") {
            array {
                for (e in eventsList) {
                    def startTime = new Date(e.dateFieldSelected.year, e.dateFieldSelected.month, e.dateFieldSelected.date, e.startTimeField.hours, e.startTimeField.minutes)
                    def endTime = new Date(e.dateFieldSelected.year, e.dateFieldSelected.month, e.dateFieldSelected.date, e.endTimeField.hours, e.endTimeField.minutes)
                    event(id: e.id, title: e.eventTitle, start: startTime, end: endTime)
                }
            }
        }

    }

        def fetchEventContacts = {
        if (params.eventId != null && params.eventId != "") {
            def linkedContacts = EventContact.findContactsByEvent(Event.findById(params.eventId))
            render(contentType: "text/json") {
                array {
                    for (c in linkedContacts) {
                        contact(
                            id: c.id,
                            name: c.name,
                            primaryMobile: c.primaryMobile,
                            notes: c.notes
                        )
                    }
                }
            }
        } else {
            render(contentType: "text/json") {
                array {
                }
            }
        }
    }

    def deleteEvent = {
        def event = Event.get(params.id);
        if (event != null) {
            event.delete();
            render "Event is successfully deleted"
        }
        else
            render "Event not found!!!"
    }

}
