package frontlinesms.legal

class CaseController {

    def create = {}

    def save = {
        def newCase = new Case()
        newCase.caseId = params.caseId
        newCase.description = params.description

        redirect(action: 'show', params: [id: newCase.caseId])
    }

    def show = {}
}
