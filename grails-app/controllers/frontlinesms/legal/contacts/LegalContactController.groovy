package frontlinesms.legal.contacts

import frontlinesms.legal.LegalContact

class LegalContactController {

    def index = { }

    def create = {}

    def save = {
        def legalContact = new LegalContact(params)
        if (legalContact.save(flush: true)) {
            flash.message = "Contact Saved"
            redirect(action: 'show', params: [id: legalContact.primaryMobile])
        }
        else if (params.primaryMobile == null || params.primaryMobile == "" || params.primaryMobile.isAllWhitespace()) {
            flash.error = "Please enter a contact number. Contact cannot be saved without a contact number."
            redirect(action: 'create', params: [name: params.name, notes:params.notes])
        }
        else if ((LegalContact.findByPrimaryMobile(legalContact.primaryMobile) != null)) {
            flash.error = "Contact number already exists. Please enter a unique contact number."
            redirect(action: 'create', params: [name: params.name, notes:params.notes, primaryMobile:params.primaryMobile])
        }


    }

    def show = {
        [contactToDisplay: LegalContact.findByPrimaryMobile(params.id)]
        }
}
