package frontlinesms.legal.events

import frontlinesms.legal.Event
import grails.plugin.spock.ControllerSpec

class EventControllerSpec extends ControllerSpec {

        def "should save event"(){
            setup:
            mockDomain(Event)
            controller.params.title = "Meeting with someone"
            controller.params.dateFieldSelected = "July 12, 2011"
            controller.params.startTimeField = "08:45AM"
            controller.params.endTimeField = "06:00PM"

            when:
            controller.save()

            then:
            Event.count() == 1
        }


}

