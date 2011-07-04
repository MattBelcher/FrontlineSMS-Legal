package frontlinesms.legal.events

import frontlinesms.legal.functionaltests.FrontlinesmsLegalGebSpec
import frontlinesms.legal.functionaltests.pages.HomePage
import frontlinesms.legal.functionaltests.pages.events.NewEventPage
import frontlinesms.legal.functionaltests.pages.cases.NewCasePage

class CreateEventSpec extends FrontlinesmsLegalGebSpec {
     def "should be able to navigate to the create event page from the menu"() {
        given: to HomePage

        when: createNewEvent.click()

        then: at(NewEventPage)
    }

    def "should show date picker when date field is focused"(){
        given: to NewEventPage

        when: dateFieldSelected

        then: datePicker.present
    }
     def 'should open confirmation dialog when cancel is clicked'(){
        given:
        to NewEventPage

        when:
        cancel.click()

        then:
        eventCancelDialog.displayed == true
    }

    def 'should hide cancel confirm dialog when no is clicked'(){
        given:
        to NewEventPage

        when:
        cancel.click()

        and:
        cancelNo.click()

        then:
        eventCancelDialog.displayed == false
    }

    def 'should remain on Create event page when no is clicked on cancel confirm dialog'(){
        given:
        to NewEventPage

        when:
        cancel.click()

        and:
        cancelNo.click()

        then:
        assert at(NewEventPage)
    }

    def 'should go to home page when yes is clicked on cancel confirm dialog'() {
        given:
        to NewEventPage

        when:
        cancel.click()

        and:
        cancelYes.click()

        then:
        assert at(HomePage)
    }
    def 'should not display any garbage text on start time and end time field'() {

    }
}
