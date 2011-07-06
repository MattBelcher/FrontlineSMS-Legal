package frontlinesms.legal.functionaltests

import frontlinesms.legal.functionaltests.pages.HomePage

class FlashMessageSpec extends FrontlinesmsLegalGebSpec {

    def 'should show error message when flash.error is set'(){
        given: to HomePage

        when: controller.flash.error = "Error Message"

        then:  errorMessage.text() == "Error Message"

    }

    def 'should show status message when flash.message is set'(){
        given: to HomePage

        when: controller.flash.message = "Flash Message"

        then:  status.text() == "Flash Message"

    }
}
