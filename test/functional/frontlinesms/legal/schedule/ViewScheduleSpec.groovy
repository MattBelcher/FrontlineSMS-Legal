package frontlinesms.legal.schedule

import frontlinesms.legal.functionaltests.FrontlinesmsLegalGebSpec
import frontlinesms.legal.functionaltests.pages.schedule.SchedulePage
import frontlinesms.legal.functionaltests.pages.events.NewEventPage

class ViewScheduleSpec extends FrontlinesmsLegalGebSpec {

    def 'should display events from the database'(){
        given:
        to NewEventPage

        when:

        eventTitle="super SPECIAL event!"
        dateFieldSelected= new Date().format("MMMM d, yyyy")
        startTimeField="08:09AM"
        endTimeField="08:56AM"

        and:
        save.click()

        then:
        assert at(SchedulePage)
        at SchedulePage
        assert events.collect{it -> it.text()}.contains("super SPECIAL event!")
    }
}
