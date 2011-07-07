package frontlinesms.legal.schedule

import frontlinesms.legal.Event

class ScheduleController {

    def index = { }

    def fetchEvents = {
        def eventsList = Event.list()
        render(contentType: "text/json"){
            array {
                for(e in eventsList){
                    event (title: e.eventTitle, start: e.dateFieldSelected)
                    }
            }
        }

    }

}
