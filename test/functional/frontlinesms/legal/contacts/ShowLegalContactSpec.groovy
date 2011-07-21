package frontlinesms.legal.contacts

import frontlinesms.legal.Event
import frontlinesms.legal.EventContact
import frontlinesms.legal.LegalContact
import frontlinesms.legal.functionaltests.FrontlinesmsLegalGebSpec
import frontlinesms.legal.functionaltests.pages.contact.ShowLegalContactPage
import java.sql.Time

class ShowLegalContactSpec extends FrontlinesmsLegalGebSpec {

    def "should show future and past event linked with legal contact"() {
        setup:
        def id = createFutureAndPastEventsAndLinkContacts()

        when:
        to ShowLegalContactPage, id

        then:
        pastEventsTable.first().title == "Past"
        and:
        futureEventsTable.first().title == "Future"
    }

    def createFutureAndPastEventsAndLinkContacts() {
        def yearOffsetForDate = 1900
        def legalContact = new LegalContact(name: 'fabio', primaryMobile: '1234567')
        legalContact.save(flush: true)
        def pastEvent = new Event(eventTitle: "Past", dateFieldSelected: new Date(1990 - yearOffsetForDate, 8, 12), startTimeField: new Time(12, 30, 0), endTimeField: new Time(13, 30, 0))
        pastEvent.save(flush: true)
        def futureEvent = new Event(eventTitle: "Future", dateFieldSelected: new Date(2020 - yearOffsetForDate, 8, 12), startTimeField: new Time(12, 30, 0), endTimeField: new Time(13, 30, 0))
        futureEvent.save(flush: true)
        EventContact.link(pastEvent, legalContact)
        EventContact.link(futureEvent, legalContact)
        legalContact.id

    }


    def "should show current and past event linked with legal contact"() {
        setup:
        def id = createCurrentAndPastEventsAndLinkContacts()

        when:
        to ShowLegalContactPage, id

        then:
        pastEventsTable.first().title == "Past"
        and:
        currentEventsTable.first().title == "Current"
    }

    def createCurrentAndPastEventsAndLinkContacts() {
        def yearOffsetForDate = 1900
        def legalContact = new LegalContact(name: 'temptation', primaryMobile: '987654')
        legalContact.save(flush: true)
        def pastEvent = new Event(eventTitle: "Past", dateFieldSelected: new Date(1990 - yearOffsetForDate, 8, 12), startTimeField: new Time(12, 30, 0), endTimeField: new Time(13, 30, 0))
        pastEvent.save(flush: true)
        def futureEvent = new Event(eventTitle: "Future", dateFieldSelected: new Date(2020 - yearOffsetForDate, 8, 12), startTimeField: new Time(12, 30, 0), endTimeField: new Time(13, 30, 0))
        futureEvent.save(flush: true)
        def startDate = new Date()
        def startTime = new Time(startDate.getTime() - 240000)
        def endTime = new Time(startDate.getTime() + 240000)
        def currentEvent = new Event(eventTitle: "Current", dateFieldSelected: startDate, startTimeField: startTime, endTimeField: endTime)
        currentEvent.save(flush: true)

        EventContact.link(pastEvent, legalContact)
        EventContact.link(futureEvent, legalContact)
        EventContact.link(currentEvent, legalContact)
        legalContact.id

    }


}
