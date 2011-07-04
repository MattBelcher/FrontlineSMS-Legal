package frontlinesms.legal.events

import frontlinesms.legal.functionaltests.FrontlinesmsLegalGebSpec
import frontlinesms.legal.functionaltests.pages.HomePage
import frontlinesms.legal.functionaltests.pages.events.NewEventPage

class CreateEventSpec extends FrontlinesmsLegalGebSpec {
     def "should be able to navigate to the create event page from the menu"() {
        given:
        to HomePage

        when:
        createNewEvent.click()

        then:
        assert at(NewEventPage)
    }

    def "should show date picker when date field is focused"(){
        given:
        to NewEventPage

        when:
        dateField.click()

        then:
        assert datePicker.displayed == true
    }
}
