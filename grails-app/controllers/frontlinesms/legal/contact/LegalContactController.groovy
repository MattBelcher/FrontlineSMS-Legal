package frontlinesms.legal.contact

import frontlinesms.legal.LegalContact
import grails.converters.JSON


class LegalContactController {
    def search = {
        def contacts = LegalContact.findByContactName(params.keyword)
        render(contentType:"text/json", text: (contacts as JSON))
    }
}
