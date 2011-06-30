package frontlinesms.legal

class CaseController {

    def create = {}

    def save = {
        def newCase = new Case(params)

        if(newCase.save(flush: true)) {
            redirect(action: 'show', params: [id: newCase.caseId])
        }
        else if(params.caseId == null)
        {
            redirect( action : 'create')
            flash.error = "Case number is required"

        }
        else
        {
            redirect( action : 'create')
            flash.error = "Case number already exists. Please enter a unique case number"
        }



    }

    def show = {}


    def search = {
       Case.findByCaseId(params.caseId)

    }
}
