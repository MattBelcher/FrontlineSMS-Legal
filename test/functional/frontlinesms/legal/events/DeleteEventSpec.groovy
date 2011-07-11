package frontlinesms.legal.events

import frontlinesms.legal.functionaltests.FrontlinesmsLegalGebSpec
import frontlinesms.legal.functionaltests.pages.events.NewEventPage
import frontlinesms.legal.functionaltests.pages.schedule.SchedulePage

class DeleteEventSpec extends FrontlinesmsLegalGebSpec {

    def 'should delete event on clicking delete button on the pop-up dialog with event details'() {
        given:
        to NewEventPage
        eventTitle = "Test Event"
        dateFieldSelected = new Date().format("MMMM d, yyyy")
        startTimeField = "08:09PM"
        endTimeField = "08:56AM"
        save.click()

        when:
        to SchedulePage, "index"
        def eventList = events.collect {it}
        for (def event in eventList) {
            if (event.text().contains("Test Event")) {
                event.click()
                break;
            }
        }
        def oldEventListSize = events.size();
        events[0].click()
        deleteEventButton.click()
        def newEventListSize = events.size();

        then:
        oldEventListSize == events.size() + 1;

        and:

        when:
        to SchedulePage , "index"

        then:
        newEventListSize == events.size()
    }
}
