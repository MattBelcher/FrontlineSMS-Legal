package frontlinesms.legal.events

import frontlinesms.legal.Event
import grails.plugin.spock.ControllerSpec
import frontlinesms.legal.LegalContact
import frontlinesms.legal.EventContact

class EventControllerSpec extends ControllerSpec {

    def "should save event"() {
        setup:
        mockDomain(Event)
        controller.params.eventTitle = "Meeting with someone"
        controller.params.dateFieldSelected = "July 12, 2011"
        controller.params.startTimeField = "08:45AM"
        controller.params.endTimeField = "06:00PM"

        when:
        controller.save()

        then:
        Event.count() == 1
    }

    def "should show error message when date is not specified"() {
        setup:
        mockDomain(Event)
        controller.params.eventTitle = "Meeting with someone"
        controller.params.startTimeField = "08:45AM"
        controller.params.endTimeField = "06:00PM"

        when:
        controller.save()

        then:


        controller.flash.error == 'Please complete date and time fields.'

    }

    def "should show error message when start time is not specified"() {
        setup:
        mockDomain(Event)
        controller.params.eventTitle = "Meeting with someone"
        controller.params.dateFieldSelected = "July 12, 2011"
        controller.params.endTimeField = "06:00PM"

        when:
        controller.save()

        then:

        controller.flash.error == "Please complete date and time fields."
    }

    def "should show error message when end time is not specified"() {
        setup:
        mockDomain(Event)
        controller.params.eventTitle = "Meeting with someone"
        controller.params.dateFieldSelected = "July 12, 2011"
        controller.params.startTimeField = "08:45AM"

        when:
        controller.save()

        then:
        controller.flash.error == "Please complete date and time fields."
    }

    def "should not save event when end time is before start time"() {
        setup:
        mockDomain(Event)
        controller.params.eventTitle = "Event"
        controller.params.dateFieldSelected = "July 12, 2011"
        controller.params.startTimeField = "06:30PM"
        controller.params.endTimeField = "06:30AM"

        when:
        controller.save()

        then:
        Event.count() == 0
    }

    def "should show error message when end time is before start time"() {
        setup:
        mockDomain(Event)
        controller.params.eventTitle = "Event"
        controller.params.dateFieldSelected = "July 12, 2011"
        controller.params.startTimeField = "06:30PM"
        controller.params.endTimeField = "06:30AM"

        when:
        controller.save()

        then:
        controller.flash.error == "End time cannot be before the start time."
    }

    def "should return true for isStartTimeBeforeEndTime if startTime is before endTime"() {
        when:
        controller.params.startTimeField = "06:30AM"
        controller.params.endTimeField = "06:30PM"

        then:
        controller.isStartTimeBeforeEndTime() == true
    }
    def 'should return true if start time  and end time are equal'() {
        when:
        controller.params.startTimeField = "04:30PM"
        controller.params.endTimeField = "04:30PM"

        then:
        controller.isStartTimeBeforeEndTime() == true
    }


    def "should return false for isStartTimeBeforeEndTime if startTime is after endTime"() {
        when:
        controller.params.startTimeField = "06:30PM"
        controller.params.endTimeField = "06:30AM"

        then:
        controller.isStartTimeBeforeEndTime() == false
    }



    def "should save contact when linked on the event"() {
        setup:
        mockDomain(Event)
        mockDomain(EventContact)
        mockDomain(LegalContact,[new LegalContact(id:1,name: "John Doe",primaryMobile: "435352",notes: "hii")])

        when:
        controller.params.eventTitle = "Event"
        controller.params.dateFieldSelected = "July 12, 2011"
        controller.params.startTimeField = "06:30AM"
        controller.params.endTimeField = "07:30AM"
        controller.params.linkedContacts = "1"

        and:
        controller.save()

        then:
         EventContact.count()==1
    }

    def "should save multiple contacts when linked on the event"() {
        setup:
        mockDomain(Event)
        mockDomain(EventContact)
        mockDomain(LegalContact,[
                new LegalContact(id:1,name: "John Doe",primaryMobile: "435352",notes: "hii"),
                new LegalContact(id:34,name: "Jane Do",primaryMobile: "546354",notes: ":)"),
                new LegalContact(id:87,name: "Jason D",primaryMobile: "57766",notes: "???")
        ])

        when:
        controller.params.eventTitle = "Event"
        controller.params.dateFieldSelected = "July 12, 2011"
        controller.params.startTimeField = "06:30AM"
        controller.params.endTimeField = "07:30AM"
        controller.params.linkedContacts = "1,34,87"

        and:
        controller.save()

        then:
         EventContact.count()==3
    }

}

