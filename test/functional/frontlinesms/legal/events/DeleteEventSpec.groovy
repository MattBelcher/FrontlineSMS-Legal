package frontlinesms.legal.events

import frontlinesms.legal.functionaltests.FrontlinesmsLegalGebSpec
import frontlinesms.legal.functionaltests.pages.events.NewEventPage
import frontlinesms.legal.functionaltests.pages.schedule.SchedulePage

class DeleteEventSpec extends FrontlinesmsLegalGebSpec {

    def 'should delete event on clicking delete button on the pop-up dialog with event details'() {
        given:
        createEvent("Test Event")
        to SchedulePage, "index"

        when:
        def eventListSizeBeforeDeletion = events().size();

        events()[0].click()
        deleteEvent()

        def eventListSizeAfterDeletion = eventListSize()

        then:
        eventListSizeBeforeDeletion == eventListSizeAfterDeletion + 1;
    }

    def 'should not delete event on clicking No in confirm dialog'() {
        given:
        createEvent("Test Event")
        to SchedulePage, "index"

        when:
        def oldEventListSize = events().size();

        events()[0].click()
        $('#delete-event').click()
        $('#cancel-confirm-no').click()

        def newEventListSize = eventListSize()

        then:
        oldEventListSize == newEventListSize;
    }

    def 'should redirect to the event date on successful creation of the event'() {

        when:
        createEvent("Test1")
        then:
        at SchedulePage
        atDate == new Date().format("MMMM yyyy")

    }

    def createEvent(title) {
        to NewEventPage
        eventTitle = title
        dateFieldSelected = new Date().format("MMMM d, yyyy")
        startTimeField = "08:09AM"
        endTimeField = "08:56PM"
        save.click()
    }


}
