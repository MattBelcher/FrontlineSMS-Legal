package frontlinesms.legal.events

import frontlinesms.legal.Event
import frontlinesms.legal.TimeFormatter
import java.sql.Time

class EventController {

    def index = { }
    def create = {
        params.eventTitle = params.eventTitle ? params.eventTitle : ""
        params.startTimeField = params.startTimeField ? params.startTimeField : ""
        params.dateFieldSelected = params.dateFieldSelected ? params.dateFieldSelected : ""
        params.endTimeField = params.endTimeField ? params.endTimeField : ""
    }

    def save = {
        if (checkForNullDateTimes()) {

            flash.error = "Please complete date and time fields."

            redirect(action: "create", params: [eventTitle: params.eventTitle, dateFieldSelected: params.dateFieldSelected, startTimeField: params.startTimeField, endTimeField: params.endTimeField])

        }
        else {
            formatParameters()

            if (isStartTimeBeforeEndTime()) {
                def newEvent = new Event(eventTitle: params.eventTitle, dateFieldSelected: new Date(params.dateFieldSelected), startTimeField: Time.valueOf(params.startTimeField), endTimeField: Time.valueOf(params.endTimeField))
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
        params.startTimeField = TimeFormatter.formatTime(params.startTimeField)
        params.endTimeField = TimeFormatter.formatTime(params.endTimeField)
        params.eventTitle = (params.eventTitle == null || params.eventTitle.trim() == "") ? "Untitled Event" : params.eventTitle.trim()
    }

    private def isStartTimeBeforeEndTime() {
        Time start = Time.valueOf(params.startTimeField)
        Time end = Time.valueOf(params.endTimeField)
        return start.before(end)
    }

    private def checkForNullDateTimes() {
        return (params.dateFieldSelected == null || params.startTimeField == null || params.endTimeField == null || params.dateFieldSelected == "" || params.startTimeField == "" || params.endTimeField == "")
    }
}
