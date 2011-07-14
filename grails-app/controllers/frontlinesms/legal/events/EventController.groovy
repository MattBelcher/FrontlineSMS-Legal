package frontlinesms.legal.events

import frontlinesms.legal.Event
import frontlinesms.legal.TimeFormatter
import java.sql.Time
import frontlinesms2.Contact

class EventController {

    def index = { }
    def create = {
        params.eventTitle = params.eventTitle ? params.eventTitle : ""
        params.startTimeField = params.startTimeField ? params.startTimeField : ""
        params.dateFieldSelected = params.dateFieldSelected ? params.dateFieldSelected : ""
        params.endTimeField = params.endTimeField ? params.endTimeField : ""
        [contactList: Contact.list()]
    }

    def save = {
        if (checkForNullDateTimes()) {

            flash.error = "Please complete date and time fields."

            redirect(action: "create", params: [eventTitle: params.eventTitle, dateFieldSelected: params.dateFieldSelected, startTimeField: params.startTimeField, endTimeField: params.endTimeField])

        }
        else {
            def formattedParams = formatParameters()

            if (isStartTimeBeforeEndTime()) {
                def newEvent = new Event(eventTitle: formattedParams.eventTitle, dateFieldSelected: new Date(params.dateFieldSelected), startTimeField: Time.valueOf(formattedParams.startTimeField), endTimeField: Time.valueOf(formattedParams.endTimeField))
                if (newEvent.save(flush: true)) {
                    flash.message = "Event created."
                    redirect(controller: "schedule", action: "index")
                }
                else {
                    flash.error = "There was a problem saving your event."

                    redirect(action: "create", params: [eventTitle: params.eventTitle, dateFieldSelected: params.dateFieldSelected, startTimeField: params.startTimeField, endTimeField: params.endTimeField])
                }
            }
            else
            {
                flash.error= "End time cannot be before the start time."
                redirect(action: "create", params: [eventTitle: params.eventTitle, dateFieldSelected: params.dateFieldSelected, startTimeField: params.startTimeField, endTimeField: params.endTimeField])
            }


        }
    }

    private def formatParameters() {
        [
            startTimeField: TimeFormatter.formatTime(params.startTimeField),
            endTimeField: TimeFormatter.formatTime(params.endTimeField),
            eventTitle: (params.eventTitle == null || params.eventTitle.trim() == "") ? "Untitled Event" : params.eventTitle.trim()
        ]
    }

    private def isStartTimeBeforeEndTime() {
        Time start = Time.valueOf(TimeFormatter.formatTime(params.startTimeField))
        Time end = Time.valueOf(TimeFormatter.formatTime(params.endTimeField))
        return start.before(end)
    }

    private def checkForNullDateTimes() {
        return (params.dateFieldSelected == null || params.startTimeField == null || params.endTimeField == null || params.dateFieldSelected == "" || params.startTimeField == "" || params.endTimeField == "")
    }
}
