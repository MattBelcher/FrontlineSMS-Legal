package frontlinesms.legal.contacts

import frontlinesms.legal.Case
import frontlinesms.legal.CaseContacts
import frontlinesms.legal.LegalContact
import grails.converters.JSON

class LegalContactController {

    def index = { }

    def create = {
    }

    def search = {
        [foundContact: LegalContact.list()]

    }

    def save = {
        def legalContact = new LegalContact(params)
        saveLegalContact(legalContact)
    }

    def update = {
        def legalContact = LegalContact.get(params.currentId)
        updateLegalContact(legalContact)
        redirect(action: 'show', params: [id: params.currentId])
    }

    private def saveLegalContact(legalContact) {
        if (legalContact.save(flush: true)) {
            flash.message = "Contact Saved"
            redirect(action: 'show', params: [id: legalContact.id])
        }
        else if (params.primaryMobile == null || params.primaryMobile == "" || params.primaryMobile.isAllWhitespace()) {
            flash.error = "Please enter a contact number. Contact cannot be saved without a contact number."
            redirect(action: 'create', params: [name: params.name, notes: params.notes])
        }
        else if ((LegalContact.findByPrimaryMobile(legalContact.primaryMobile) != null)) {
            flash.error = "Contact number already exists. Please enter a unique contact number."
            redirect(action: 'create', params: [name: params.name, notes: params.notes, primaryMobile: params.primaryMobile])
        }
    }

    private def updateLegalContact(legalContact) {

        legalContact.primaryMobile = params.primaryMobile
        legalContact.name = params.name
        legalContact.notes = params.notes

        if (legalContact.validate() && legalContact.save(flush: true)) {
            linkCasesToContact(params.linkedCases, legalContact)
            flash.message = "Contact Updated"
        }
        else if (params.primaryMobile == null || params.primaryMobile == "" || params.primaryMobile.isAllWhitespace()) {
            flash.error = "Please enter a contact number. Contact cannot be saved without a contact number."
        }
        else if ((LegalContact.findByPrimaryMobile(legalContact.primaryMobile) != null)) {
            flash.error = "Contact number already exists. Please enter a unique contact number."
        }
    }

    private def linkCasesToContact(caseListString, legalContact) {
            def caseList = new HashMap<Long, String>(JSON.parse(caseListString))
            CaseContacts.findAllByLegalContact(legalContact)*.delete(flush: true)
            caseList.each { it ->
                CaseContacts.link(Case.findById(it.key as Long), legalContact, it.value )
            }
    }

    private def pairUpCaseIdAndRelationship(caseContacts) {
        def returnList = new HashMap<Long, String>()
        caseContacts.each { it ->
            returnList[it.legalCase.id] = it.involvement
        }
        return returnList
    }

    def show = {

        def tempContact = LegalContact.findById(params.id as Long)
        def linkedEvents = tempContact.linkedEvents
        def currentDate = new Date()
        def pastEventList = []
        def futureEventList = []
        def pastTime = new Date()
        def futureTime = null

        linkedEvents.each {eventContact ->
            def e = eventContact.event
            def linkedEventTime = new Date(e.dateFieldSelected.year, e.dateFieldSelected.month, e.dateFieldSelected.date, e.startTimeField.hours, e.startTimeField.minutes)

            if (linkedEventTime.after(currentDate)) {
                if (futureTime == null || !linkedEventTime.before(futureTime)) {
                    futureTime = linkedEventTime
                    futureEventList.add(e)
                }
            }
            else {
                if (!linkedEventTime.after(pastTime)) {
                    pastTime = linkedEventTime
                    pastEventList.add(e)
                }
            }
        }
        def contactLinkedCases = pairUpCaseIdAndRelationship(tempContact.linkedCases) as JSON
        [allCases: Case.list(), contactToDisplay: LegalContact.findById(params.id), pastEvents: pastEventList, futureEvents: futureEventList, contactLinkedCases: contactLinkedCases.toString()]
    }
}