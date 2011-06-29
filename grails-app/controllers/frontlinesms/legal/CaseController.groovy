package frontlinesms.legal

class CaseController {

    def create = {}

    def save = {
        def newCase = new Case(params)

        newCase.save()

        redirect(action: 'show', params: [id: newCase.caseId])
    }

    def show = {}
}
