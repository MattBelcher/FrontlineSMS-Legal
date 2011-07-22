package frontlinesms.legal.contacts

import frontlinesms.legal.LegalContact
import frontlinesms2.Contact
import frontlinesms.legal.Case
import frontlinesms.legal.CaseContacts
import frontlinesms.legal.LegalContact
import grails.converters.JSON

class LegalContactController {

    def index = { }

    def create = {
    }

    def search = {
        if (params.name) {
            def foundContacts = LegalContact.findAllByNameLike("${params.name}%")
            [foundContact: foundContacts as List]
        }
        else {
            [foundContact: LegalContact.list()]
        }
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
        else if (params.notes.size() > 1024) {
                flash.error = "Please reduce the number of characters entered in notes field to save contact. Notes field cannot have more than 1024 characters"
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
        caseListString = caseListString ? caseListString : "{}"
        def caseList = new HashMap<Long, String>(JSON.parse(caseListString))
        CaseContacts.findAllByLegalContact(legalContact)*.delete(flush: true)
        caseList.each { it ->
            CaseContacts.link(Case.findById(it.key as Long), legalContact, it.value)
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
        def nearestPastEventTime = null
        def nearestFutureEventTime = null
        def ongoingEventList = []

        linkedEvents.each {eventContact ->
            def eventIterator = eventContact.event
            def linkedEventStartTime = new Date(eventIterator.dateFieldSelected.year, eventIterator.dateFieldSelected.month, eventIterator.dateFieldSelected.date, eventIterator.startTimeField.hours, eventIterator.startTimeField.minutes)
            def linkedEventEndTime = new Date(eventIterator.dateFieldSelected.year, eventIterator.dateFieldSelected.month, eventIterator.dateFieldSelected.date, eventIterator.endTimeField.hours, eventIterator.endTimeField.minutes)
            if (linkedEventStartTime.after(currentDate)) {
                if (nearestFutureEventTime == null || linkedEventStartTime.compareTo(nearestFutureEventTime) < 0) {
                    nearestFutureEventTime = linkedEventStartTime
                    futureEventList = [eventIterator]
                }
                else if (linkedEventStartTime.compareTo(nearestFutureEventTime) == 0) {
                    nearestFutureEventTime = linkedEventStartTime
                    futureEventList.add(eventIterator)
                }
            }
            else {
                if (linkedEventEndTime.compareTo(currentDate) > 0) {
                    ongoingEventList.add(eventIterator)
                }
                else if (nearestPastEventTime == null || linkedEventEndTime.compareTo(nearestPastEventTime) > 0) {
                    nearestPastEventTime = linkedEventEndTime
                    pastEventList = [eventIterator]
                }
                else if (linkedEventEndTime == nearestPastEventTime) {
                    nearestPastEventTime = linkedEventEndTime
                    pastEventList.add(eventIterator)
                }

            }
        }
        if (!ongoingEventList.isEmpty()) {
            futureEventList = null
        }
        def contactLinkedCases = pairUpCaseIdAndRelationship(tempContact.linkedCases) as JSON
        [allCases: Case.list(), contactToDisplay: LegalContact.findById(params.id), pastEvents: pastEventList, ongoingEvents: ongoingEventList, futureEvents: futureEventList, contactLinkedCases: contactLinkedCases.toString()]
    }
}