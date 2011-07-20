package frontlinesms.legal.contacts

import frontlinesms.legal.LegalContact
import frontlinesms2.Contact
import frontlinesms.legal.Case

class LegalContactController {

    def index = { }

    def create = {
    }

    def save = {
        def legalContact = new LegalContact(params)
        saveLegalContact(legalContact)
    }

    def update = {
        def legalContact = LegalContact.get(params.id)
        updateLegalContact(legalContact)
        redirect(action: 'show', params: [id: legalContact.id])
    }

    private def saveLegalContact(legalContact) {
        if (legalContact.save(flush: true)) {
            flash.message = "Contact Saved"
            redirect(action: 'show', params: [id: legalContact.id])
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

    private def updateLegalContact(legalContact) {

        legalContact.primaryMobile = params.primaryMobile
        legalContact.name = params.name
        legalContact.notes = params.notes

        if (legalContact.validate() && legalContact.save(flush: true)) {
            flash.message = "Contact Saved"
        }
        else if (params.primaryMobile == null || params.primaryMobile == "" || params.primaryMobile.isAllWhitespace()) {
            flash.error = "Please enter a contact number. Contact cannot be saved without a contact number."
        }
        else if ((LegalContact.findByPrimaryMobile(legalContact.primaryMobile) != null)) {
            flash.error = "Contact number already exists. Please enter a unique contact number."
        }
    }

    def show = {
        [foundCase: Case.list(), contactToDisplay: LegalContact.findById(params.id)]
        }
}
