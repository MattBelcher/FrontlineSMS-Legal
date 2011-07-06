package frontlinesms.legal.contact

import frontlinesms.legal.LegalContact
import grails.plugin.spock.ControllerSpec

class LegalContactControllerSpec extends ControllerSpec {
    def "should return Contact list for a given keyword"() {
        def contacts = [new LegalContact(phoneNumber: "123"), new LegalContact(phoneNumber: "456")]
        controller.params.keyword = "something"
        mockFor(LegalContact).demand.static.findByContactName("something") { key -> contacts }

        when:
        controller.search()

        then:
        renderArgs.text.toString().contains("123")
        renderArgs.text.toString().contains("456")
        renderArgs.contentType == "text/json"
    }

}
