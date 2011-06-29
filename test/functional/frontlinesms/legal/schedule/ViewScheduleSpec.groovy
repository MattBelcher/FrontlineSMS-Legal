package frontlinesms.legal.schedule

import frontlinesms.legal.functionaltests.FrontlinesmsLegalGebSpec
import frontlinesms.legal.functionaltests.pages.schedule.SchedulePage

class ViewScheduleSpec extends FrontlinesmsLegalGebSpec {
    def 'should display events on my schedule'() {
        when:
        to SchedulePage

        then:
        events.first().text() == "Appointment"
    }
}
