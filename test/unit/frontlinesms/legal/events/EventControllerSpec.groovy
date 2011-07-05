package frontlinesms.legal.events
import frontlinesms2.Contact
import grails.plugin.spock.IntegrationSpec
import grails.plugin.spock.ControllerSpec
import frontlinesms.legal.Event


class EventControllerSpec extends ControllerSpec {

        def "should save event"(){
            setup:
            //def events =[]
            mockDomain(Event)
            controller.params.title = "Meeting with someone"
            controller.params.dateFieldSelected = "July 12, 2011"
            controller.params.startTimeField = "08:45AM"
            controller.params.endTimeField = "17:00PM"

            when:
            controller.save()

            then:
            Event.count() == 1
        }


}

