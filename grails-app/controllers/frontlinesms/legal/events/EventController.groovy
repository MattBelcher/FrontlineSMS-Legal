package frontlinesms.legal.events

import frontlinesms.legal.Event
import frontlinesms.legal.EventContact
import frontlinesms.legal.LegalContact
import frontlinesms.legal.TimeFormatter
import frontlinesms2.Contact
import java.sql.Time
import java.text.SimpleDateFormat

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
                def YearFormat =new SimpleDateFormat("yyyy");
                Date selectedDate= new Date(params.dateFieldSelected);
                def newEvent = new Event(eventTitle: formattedParams.eventTitle, dateFieldSelected: new Date(params.dateFieldSelected), startTimeField: Time.valueOf(formattedParams.startTimeField), endTimeField: Time.valueOf(formattedParams.endTimeField))
                if (newEvent.save(flush: true)) {
                    linkContactsToEvent(newEvent)
                    flash.message = "Event created."
                    redirect(controller: "schedule", action: "index", params:[year:YearFormat.format(selectedDate),month:selectedDate.month])
                }
                else {
                    flash.error = "There was a problem saving your event."

                    redirect(action: "create", params: [eventTitle: params.eventTitle, dateFieldSelected: params.dateFieldSelected, startTimeField: params.startTimeField, endTimeField: params.endTimeField])
                }
            }
            else {
                flash.error = "End time cannot be before the start time."
                redirect(action: "create", params: [eventTitle: params.eventTitle, dateFieldSelected: params.dateFieldSelected, startTimeField: params.startTimeField, endTimeField: params.endTimeField])
            }


        }
    }

    private def linkContactsToEvent(event) {
        if (params.linkedContacts != null && params.linkedContacts != "") {
            def contactId = params.linkedContacts.split(",")
            contactId.each { it ->
                def contact = LegalContact.findById(it as Integer)
                EventContact.link(event, contact)
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
        if (start.equals(end))
            return true
        return start.before(end)
    }


    private def checkForNullDateTimes() {
        return (params.dateFieldSelected == null || params.startTimeField == null || params.endTimeField == null || params.dateFieldSelected == "" || params.startTimeField == "" || params.endTimeField == "")
    }
}
