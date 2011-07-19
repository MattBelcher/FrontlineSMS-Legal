package frontlinesms.legal.cases

import frontlinesms.legal.Case
import frontlinesms.legal.LegalContact
import frontlinesms.legal.CaseContacts

class CaseController {

    def create = {
        [contactList: LegalContact.list()]

    }

    def save = {
        def newCase = new Case(params)

        if (newCase.save(flush: true)) {
            if(params.linkedContactIds !="" && params.linkedContactIds != null){
                def idList = params.linkedContactIds.split(',') as List
                def involvementList = params.involvementList.split(',') as List
                idList = idList.subList(1,idList.size())
                involvementList = involvementList.subList(1,involvementList.size())

                idList.eachWithIndex{ id, i ->
                    def linkedContact = LegalContact.get(id as Long)
                    CaseContacts.link(newCase,linkedContact, involvementList[i])
                }
            }

            flash.message = "Case created"
            redirect(action: 'show', params: [id: newCase.caseId])
        }
        else if (params.caseId == null || params.caseId == "" || params.caseId.isAllWhitespace()) {
            flash.error = "Case number is required"
            redirect(action: 'create', params: [description: params.description])
        }
        else {
            flash.error = "Case number already exists. Please enter a unique case number."
            def enteredDescription = params.description
            redirect(action: 'create', params: [description: params.description])
        }
    }


    def show = {
        if (params.description) {
            def caseToDisplay = Case.get(params.uniqueId)
            caseToDisplay.description = params.description
            caseToDisplay.active = params.caseStatus
            [caseToDisplay: caseToDisplay, contactList: LegalContact.list()]
        }
        else {
            [caseToDisplay: Case.findByCaseId(params.id), contactList: LegalContact.list()]
        }
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

    def update = {
        def fetchedCase = Case.get(params.currentId)
        def originalUniqueId = params.currentId
        def originalCaseId = fetchedCase.caseId
        fetchedCase.caseId = params.caseId
        fetchedCase.description = params.description
        if (params.caseStatus == null) {

            fetchedCase.active = ""
        }
        else {
            fetchedCase.active = "true"
        }
        if (fetchedCase.save(flush: true)) {
            flash.message = "Case details updated"
            redirect(action: 'show', params: [id: fetchedCase.caseId])
        }
        else if (params.caseId == null || params.caseId == "" || params.caseId.isAllWhitespace()) {
            flash.error = "Case number required"
            redirect(action: 'show', params: [id: originalCaseId, description: fetchedCase.description, uniqueId: originalUniqueId, caseStatus: params.caseStatus])
        }
        else {
            flash.error = "Case number already exists. Please enter a unique case number."
            redirect(action: 'show', params: [id: originalCaseId, description: fetchedCase.description, uniqueId: originalUniqueId, caseStatus: params.caseStatus])
        }
    }
}
