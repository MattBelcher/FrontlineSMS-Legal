package frontlinesms.legal.contact

class LegalContactController {


    def create = {}

    def show = {}

    def save={
         redirect(action: 'create', params: [contactNumber: params.contactNumber])
         flash.message="Contact Saved"

    }
}
