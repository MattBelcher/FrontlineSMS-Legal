package frontlinesms.legal

class CaseController {

    def create = {}

    def save = {
        def newCase = new Case(params)

        if(newCase.save(flush: true)) {
            redirect(action: 'show', params: [id: newCase.caseId])
        }
        else {
            redirect( action : 'create')
            flash.error = "Case number is required"

        }

    }

    def show = {}


    def search = {
       Case.findByCaseId(params.caseId)

    }
}
