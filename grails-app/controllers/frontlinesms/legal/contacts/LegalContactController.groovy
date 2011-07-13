package frontlinesms.legal.contacts

import frontlinesms.legal.LegalContact

class LegalContactController {

    def index = { }

    def create = {}

    def save =
    {

        def legalContact = new LegalContact(params)

        if (legalContact.save(flush: true)) {
            redirect(action: 'show', params: [primaryMobile: legalContact.primaryMobile])
        }
        else if (params.primaryMobile == null || params.primaryMobile == "" || params.primaryMobile.isAllWhitespace()) {
            flash.error = "primarymobile is required"
            redirect(action: 'create', params: [name: params.name])
        }


    }

    def show = {}
}
