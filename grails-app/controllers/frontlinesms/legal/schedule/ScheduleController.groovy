package frontlinesms.legal.schedule

import frontlinesms.legal.Event

class ScheduleController {

    def index = { }

    def fetchEvents = {
        def eventsList = Event.list()
        render(contentType: "text/json") {
            array {
                for (e in eventsList) {

                    event(id:e.id,title: e.eventTitle, start:  new Date(e.dateFieldSelected.year,e.dateFieldSelected.month,e.dateFieldSelected.date,e.startTimeField.hours,e.startTimeField.minutes),end:  new Date(e.dateFieldSelected.year,e.dateFieldSelected.month,e.dateFieldSelected.date,e.endTimeField.hours,e.endTimeField.minutes))
                }
            }
        }

    }

    def deleteEvent = {
            def event=Event.get(params.id);
            if(event!=null){
                event.delete();
                render "Event is successfully deleted"
            }
            else
                render "Event not found!!!"
    }

}
