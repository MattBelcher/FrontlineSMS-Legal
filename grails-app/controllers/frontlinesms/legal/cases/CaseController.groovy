package frontlinesms.legal.cases

import frontlinesms.legal.Case
import frontlinesms.legal.LegalContact
import frontlinesms2.Contact

class CaseController {

    def create = {}

    def save = {
        def newCase = new Case(params)

        if (newCase.save(flush: true)) {
            flash.message = "Case created"
            redirect(action: 'show', params: [id: newCase.caseId])
        }
        else if (params.caseId == null || params.caseId == "" || params.caseId.isAllWhitespace()) {
            flash.error = "Case number is required"
            def enteredDescription = params.description
            redirect(action: 'create', params: [description: params.description])
        }
        else {
            flash.error = "Case number already exists. Please enter a unique case number."
            redirect(action: 'create')
        }

    }


    def show = {

        [caseToDisplay: Case.findByCaseId(params.id), legalContactList: LegalContact.list()]

    }

    def search = {
        if (params.caseId && !params.caseId.isAllWhitespace()) {
            def foundCases = Case.findAllByCaseIdLike("${params.caseId}%")
            if (foundCases.size() == 0) {
                flash.error = "There were no results returned for your search. Please try again"
                redirect(action: 'search')
            }
            else {
                [foundCase: foundCases]
            }

        }
        else {
            [foundCase: Case.getAll()]
        }
    }


}
