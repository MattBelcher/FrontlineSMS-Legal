package frontlinesms.legal.events

import frontlinesms.legal.Event
import frontlinesms.legal.TimeFormatter
import java.sql.Time

class EventController {

    def index = { }
    def create = { }
    def save = {
        params.startTimeField = TimeFormatter.formatTime(params.startTimeField)
        params.endTimeField = TimeFormatter.formatTime(params.endTimeField)

        def newEvent = new Event(title: params.title, dateFieldSelected: new Date(params.dateFieldSelected), startTimeField: Time.valueOf(params.startTimeField), endTimeField: Time.valueOf(params.endTimeField))
        newEvent.save(flush: true)


    }


}
