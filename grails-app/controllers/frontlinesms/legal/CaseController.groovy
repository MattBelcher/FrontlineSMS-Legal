package frontlinesms.legal

class CaseController {

    def create = {}

    def save = {
        def newCase = new Case(params)

        if(newCase.save(flush: true)) {
            redirect(action: 'show', params: [id: newCase.caseId])
        }
        else if(params.caseId == "" || params.caseId == null) {
            flash.error = "Case number is required"
            redirect( action : 'create')
        }
        else {
            flash.error = "Case number already exists. Please enter a unique case number"
            redirect( action : 'create')
        }

    }
   

    def show = {}

    def search = {
        if (params.caseId) {
            [foundCase: Case.findByCaseId(params.caseId)]
        }

    }
}
