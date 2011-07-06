package frontlinesms.legal.cases

import frontlinesms.legal.Case

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
            redirect(action: 'create')
        }
        else {
            flash.error = "Case number already exists. Please enter a unique case number."
            redirect(action: 'create')
        }

    }


    def show = {
        [caseToDisplay: Case.findByCaseId(params.id)]
    }

    def search = {
        if (params.caseId && !params.caseId.isAllWhitespace()) {
            def foundCases = Case.findAllByCaseIdLike("${params.caseId}%")
            if (foundCases.size()== 0) {
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

    def linkContact = {
        redirect(controller: "legalContact", action: "linkContact",
                params: [id: params.currentId, newCaseId: params.newCaseId, newCaseDescription: params.newCaseDescription,
                        contactNames: params.contactNames, contactNumbers: params.contactPhone, contactTypes: params.contactType]
        )
    }
}
