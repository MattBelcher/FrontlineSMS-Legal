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
        def id= createEventAndLinkContacts()

        when:
        to ShowLegalContactPage, id

        then:
        pastEventsTable.first().title == "Past"
        and:
        futureEventsTable.first().title == "Future"
    }

    def createEventAndLinkContacts() {
        def yearOffsetForDate = 1900
        def legalContact = new LegalContact(name: 'fabio', primaryMobile: '1234567')
        legalContact.save()
        def pastEvent = new Event(eventTitle: "Past", dateFieldSelected: new Date(1990 - yearOffsetForDate, 8, 12), startTimeField: new Time(12, 30, 0), endTimeField: new Time(13, 30, 0))
        pastEvent.save()
        def futureEvent = new Event(eventTitle: "Future", dateFieldSelected: new Date(2020 - yearOffsetForDate, 8, 12), startTimeField: new Time(12, 30, 0), endTimeField: new Time(13, 30, 0))
        futureEvent.save()

        EventContact.link(pastEvent, legalContact)
        EventContact.link(futureEvent, legalContact)
        legalContact.id

    }

}
