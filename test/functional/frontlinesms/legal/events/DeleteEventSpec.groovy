package frontlinesms.legal.events

import frontlinesms.legal.functionaltests.FrontlinesmsLegalGebSpec
import frontlinesms.legal.functionaltests.pages.events.NewEventPage
import spock.lang.Ignore


class DeleteEventSpec extends FrontlinesmsLegalGebSpec{
    @Ignore
    def 'should display pop-up dialog with event details on selecting an event in schedule page'() {
        given:
        to NewEventPage

        when:
        eventTitle="Test event"
        dateFieldSelected= new Date().format("MMMM d, yyyy")
        startTimeField="08:09PM"
        endTimeField="08:56AM"

        and:
        save.click()

        then:

        assert events.collect{it -> it.text()}.contains("super SPECIAL event!")

    }
}
