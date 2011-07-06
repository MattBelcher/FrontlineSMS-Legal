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

    def "should show error message when date is not specified"(){
        setup:
        mockDomain(Event)
        controller.params.title = "Meeting with someone"
        controller.params.startTimeField = "08:45AM"
        controller.params.endTimeField = "06:00PM"

        when:
        controller.save()

        then:
        controller.flash.error == "Please complete date and time fields."
    }

    def "should show error message when start time is not specified"(){
        setup:
        mockDomain(Event)
        controller.params.title = "Meeting with someone"
        controller.params.dateFieldSelected = "July 12, 2011"
        controller.params.endTimeField = "06:00PM"

        when:
        controller.save()

        then:
        controller.flash.error == "Please complete date and time fields."
    }

    def "should show error message when end time is not specified"(){
        setup:
        mockDomain(Event)
        controller.params.title = "Meeting with someone"
        controller.params.dateFieldSelected = "July 12, 2011"
        controller.params.startTimeField = "08:45AM"

        when:
        controller.save()

        then:
        controller.flash.error == "Please complete date and time fields."
    }




}

