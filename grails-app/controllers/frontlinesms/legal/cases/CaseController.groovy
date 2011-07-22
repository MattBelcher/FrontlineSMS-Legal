package frontlinesms.legal.cases

import frontlinesms.legal.Case
import frontlinesms.legal.CaseContacts
import frontlinesms.legal.LegalContact
import grails.converters.JSON

class CaseController {

    def create = {
        [contactList: LegalContact.list()]

    }

    def save = {
        def newCase = new Case(params)

        if (newCase.save(flush: true)) {
            saveLinkedContacts(newCase)
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

    private def saveLinkedContacts(newCase) {
        def parsedJSON = params.caseLinkedContacts ? params.caseLinkedContacts : "{}"
        def contactList = new HashMap<Long, String>(JSON.parse(parsedJSON))

        CaseContacts.findAllByLegalCase(newCase)*.delete(flush: true)
        contactList.each { it ->
            CaseContacts.link(newCase, LegalContact.findById(it.key as Long), it.value)
        }
    }


    def show = {
        def caseToDisplay = (params.description) ? Case.get(params.uniqueId) : Case.findByCaseId(params.id)
        def caseLinkedContacts = pairUpContactIdAndRelationship(caseToDisplay.linkedContacts) as JSON
        if (params.description) {
            caseToDisplay.description = params.description
            caseToDisplay.active = params.caseStatus
        }
        [caseToDisplay: caseToDisplay, caseLinkedContacts: caseLinkedContacts, contactList: LegalContact.list(), linkedContactRowData: CaseContacts.findContactsAndInvolvementByCase(caseToDisplay)]
    }

    private def pairUpContactIdAndRelationship(caseContacts) {
        def returnList = new HashMap<Long, String>()
        caseContacts.each { it ->
            returnList[it.legalContact.id] = it.involvement
        }
        return returnList
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
            saveLinkedContacts(fetchedCase)
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
