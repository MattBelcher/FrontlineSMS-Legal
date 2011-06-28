package frontlinesms.legal

class CaseController {

    def index = { }

    def create = {
        def newCase = new LegalCase()
        newCase.caseId = params.caseId
        newCase.description = params.description

        redirect(action: 'show', params: [id: newCase.caseId])
    }

    def show = {}

}
