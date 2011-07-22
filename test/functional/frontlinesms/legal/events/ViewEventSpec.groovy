package frontlinesms.legal.events

import frontlinesms.legal.functionaltests.FrontlinesmsLegalGebSpec
import frontlinesms.legal.functionaltests.pages.contact.CreateLegalContactPage
import frontlinesms.legal.functionaltests.pages.events.NewEventPage
import frontlinesms.legal.functionaltests.pages.schedule.SchedulePage
import frontlinesms.legal.Event
import spock.lang.Ignore

class ViewEventSpec extends FrontlinesmsLegalGebSpec {
//    @Ignore
    def "should display linked contacts for event when event is clicked"() {
        given:
        createContact("76575658")
        createEventWithLink("test event")
        to SchedulePage, "index"

        when:
        at SchedulePage
        testEvent.click()
        sleep(500)

        then:
        assert eventContacts.size() == 1

    }

    def createContact(number) {
        to CreateLegalContactPage
        name = "Fred"
        primaryMobile = number
        notes = "Blah"
        save.click()
    }

    def createEventWithLink(title) {
        to NewEventPage
        eventTitle = title
        dateFieldSelected = new Date().format("MMMM d, yyyy")
        startTimeField = "08:09AM"
        endTimeField = "08:56PM"
        linkContactButton.click()
        contactsToLink.first().click()
        save.click()
    }

}
