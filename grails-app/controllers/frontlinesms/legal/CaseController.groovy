package frontlinesms.legal

class CaseController {

    def create = {}

    def save = {
        def newCase = new Case(params)

        newCase.save(flush: true)

        redirect(action: 'show', params: [id: newCase.caseId])
    }

    def show = {}

    def search = {
        if (params.caseId) {
            [foundCase: Case.findByCaseId(params.caseId)]
        }

    }
}
