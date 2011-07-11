package frontlinesms.legal.events

import frontlinesms.legal.functionaltests.FrontlinesmsLegalGebSpec
import frontlinesms.legal.functionaltests.pages.events.NewEventPage
import frontlinesms.legal.functionaltests.pages.schedule.SchedulePage
import spock.lang.Ignore

class DeleteEventSpec extends FrontlinesmsLegalGebSpec {

    def 'should delete event on clicking delete button on the pop-up dialog with event details'() {
        given:
            createEvent("Test Event")
       //     createEvent("Test Event1")
            to SchedulePage , "index"
        when:
        def oldEventListSize = events().size();

        events()[0].click()
        deleteEvent()

        def newEventListSize=isEventListSize()

        then:
        oldEventListSize == newEventListSize + 1;
    }

    def createEvent(title) {
        to NewEventPage
        eventTitle = title
        dateFieldSelected = new Date().format("MMMM d, yyyy")
        startTimeField = "08:09PM"
        endTimeField = "08:56AM"
        save.click()
    }


}
