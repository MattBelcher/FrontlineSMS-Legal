package frontlinesms.legal

class CaseController {

    def create = {}

    def save = {
        def newCase = new Case(params)

        if (newCase.save(flush: true)) {
            redirect(action: 'show', params: [id: newCase.caseId])
        }
        else if (params.caseId == "" || params.caseId == null) {
            flash.error = "Case number is required"
            redirect(action: 'create')
        }
        else {
            flash.error = "Case number already exists. Please enter a unique case number"
            redirect(action: 'create')
        }

    }


    def show = {
        [caseToDisplay: Case.findByCaseId(params.id)]
    }

    def search = {
        if (params.caseId) {
            [foundCase: Case.findByCaseId(params.caseId)]
            //[foundCase: Case.findAll("from legal_case as l where l.caseId='9999'")]
             //[foundCase:Case.findAll("from legal_case as b where b.caseId=:caseId", [caseId:'9999'])]


        }
        else
        {
            [foundCase: Case.getAll()]
        }
    }
    }
