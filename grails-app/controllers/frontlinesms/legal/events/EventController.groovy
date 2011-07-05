package frontlinesms.legal.events

import frontlinesms.legal.Event
import java.sql.Time

class EventController {

    def index = { }
    def create = { }
    def save = {
        params.startTimeField = formatTime(params.startTimeField)
        params.endTimeField = formatTime(params.endTimeField)

        def newEvent = new Event(title: params.title, dateFieldSelected: new Date(params.dateFieldSelected), startTimeField: Time.valueOf(params.startTimeField), endTimeField: Time.valueOf(params.endTimeField))
        // def newEvent = new Event(params)
        newEvent.save(flush: true)


    }

    def "formatTime"(time) {
        if (time.contains("AM")) {
            time = time.substring(0, 5)
            time = time.concat(":00")
        }
        else {
            def tempTime = time.substring(0, 2)
            int timeVal = Integer.parseInt(tempTime) - 12
            def finalTime = timeVal + time.substring(2, 5)
            time = finalTime.concat(":00")
        }

        time
    }
}
