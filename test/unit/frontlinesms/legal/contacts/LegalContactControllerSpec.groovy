package frontlinesms.legal.contacts

import frontlinesms.legal.Case
import frontlinesms.legal.Event
import frontlinesms.legal.EventContact
import frontlinesms.legal.LegalContact
import grails.plugin.spock.ControllerSpec
import java.sql.Time
import frontlinesms.legal.CaseContacts

class LegalContactControllerSpec extends ControllerSpec {

    def "update action should update contact"() {
        given:
        def newLegalContact = new LegalContact(id: 666, name: "John Doe", primaryMobile: "9090", notes: "He is anonymous.")
        mockDomain(LegalContact, [newLegalContact])
        mockDomain(Case, [])
        mockDomain(CaseContacts, [])
        controller.params.name = "Steve Jobs"
        controller.params.primaryMobile = "9090"
        controller.params.notes = "Identified."
        controller.params.currentId = 666
        controller.params.linkedCases = "{}"

        when:
        controller.update()

        then:
        newLegalContact.name == "Steve Jobs"
    }


    def "should save contact"() {
        setup:
        def contacts = []
        mockDomain(LegalContact, contacts)
        controller.params.name = 'bla bla'
        controller.params.primaryMobile = '333333'

        when:
        controller.save()

        then:
        LegalContact.count() == 1
    }

    def "should not save contact when notes field has more than 1024 characters"() {
        setup:
        def contacts = []
        mockDomain(LegalContact, contacts)
        controller.params.name = 'bla bla'
        controller.params.primaryMobile = '333333'
        controller.params.notes = "12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890"
        when:
        controller.save()

        then:
        redirectArgs == [action: "create", params: [name: "bla bla", primaryMobile: '333333', notes: "12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890"]]
        controller.flash.error == "Please reduce the number of characters entered in notes field to save contact. Notes field cannot have more than 1024 characters"

    }

    def 'create action should redirect to Contact detail page'() {
        setup:
        mockDomain(LegalContact)

        controller.params.name = 'Steve Jobs'
        controller.params.primaryMobile = '666'

        when:
        controller.save()

        then:
        controller.flash.message == "Contact Saved"
        redirectArgs.action == 'show'
    }

    def 'should display error if primaryMobile is blank'() {

        given:

        mockDomain(LegalContact)
        controller.params.name = 'bla bla'

        when:
        controller.save()

        then:
        redirectArgs == [action: "create", params: [name: "bla bla", notes: null]]
        controller.flash.error == "Please enter a contact number. Contact cannot be saved without a contact number."
    }

    def 'should display error if primaryMobile is not unique'() {
        given:
        def cases = []
        cases.add(new LegalContact(primaryMobile: '999'))
        mockDomain(LegalContact, cases)
        controller.params.name = 'Steve'
        controller.params.primaryMobile = '999'

        when:
        controller.save()

        then:
        redirectArgs == [action: "create", params: [name: "Steve", notes: null, primaryMobile: '999']]
        controller.flash.error == "Contact number already exists. Please enter a unique contact number."
    }

    def 'should display all the cases when the popup appears'() {
        given:
        mockDomain(LegalContact, [new LegalContact(id: 666, name: "John Doe", primaryMobile: "5285", notes: "He is anonymous.")])
        controller.params.id = 666
        def newCase = [new Case(caseId: '23')]
        mockDomain(Case, newCase)
        mockDomain(Event, [])
        mockDomain(EventContact, [])

        when:
        def models = controller.show()

        then:
        models['allCases'] == newCase
    }

    def 'should return past and future event'() {
        def yearOffsetForDate = 1900 //To fix bug in java.sql.Date year attribute
        def contact1 = new LegalContact(primaryMobile: "123")

        def pastEvent = new Event(eventTitle: "Past", dateFieldSelected: new Date(1990 - yearOffsetForDate, 7, 3), startTimeField: new Time(13, 50, 0), endTimeField: new Time(15, 30, 0))
        def futureEvent = new Event(eventTitle: "Future", dateFieldSelected: new Date(2020 - yearOffsetForDate, 7, 3), startTimeField: new Time(13, 50, 0), endTimeField: new Time(15, 30, 0))

        def pastEventContact = new EventContact(event: pastEvent, legalContact: contact1)
        def futureEventContact = new EventContact(event: futureEvent, legalContact: contact1)
        def eventContactList = [pastEventContact, futureEventContact]

        contact1.linkedEvents = eventContactList
        mockDomain(Case, [])
        mockDomain(LegalContact, [contact1])
        mockDomain(EventContact, eventContactList)

        when:
        controller.params.id = contact1.id
        def models = controller.show()

        then:
        models["pastEvents"].contains(pastEvent)
        models["futureEvents"].contains(futureEvent)
    }

    def 'should return overlapping past events'() {
        given:
        def yearOffsetForDate = 1900
        def contact1 = new LegalContact(primaryMobile: "123")

        def pastEvent1 = new Event(eventTitle: "Past1", dateFieldSelected: new Date(1990 - yearOffsetForDate, 7, 3), startTimeField: new Time(13, 50, 0), endTimeField: new Time(15, 30, 0))
        def pastEvent2 = new Event(eventTitle: "Past2", dateFieldSelected: new Date(1990 - yearOffsetForDate, 7, 3), startTimeField: new Time(13, 50, 0), endTimeField: new Time(15, 30, 0))

        def eventContactList = [new EventContact(event: pastEvent1, legalContact: contact1), new EventContact(event: pastEvent2, legalContact: contact1)]

        contact1.linkedEvents = eventContactList

        mockDomain(Case, [])
        mockDomain(LegalContact, [contact1])
        mockDomain(EventContact, eventContactList)


        when:
        controller.params.id = contact1.id
        def models = controller.show()

        then:
        models["pastEvents"].contains(pastEvent1)
        models["pastEvents"].contains(pastEvent2)
    }

    def 'should return overlapping future events'() {
        def yearOffsetForDate = 1900
        def contact1 = new LegalContact(primaryMobile: "123")

        def futureEvent1 = new Event(eventTitle: "Future1", dateFieldSelected: new Date(2020 - yearOffsetForDate, 7, 3), startTimeField: new Time(13, 50, 0), endTimeField: new Time(15, 30, 0))
        def futureEvent2 = new Event(eventTitle: "Future2", dateFieldSelected: new Date(2020 - yearOffsetForDate, 7, 3), startTimeField: new Time(13, 50, 0), endTimeField: new Time(15, 30, 0))

        def eventContactList = [new EventContact(event: futureEvent1, legalContact: contact1), new EventContact(event: futureEvent2, legalContact: contact1)]

        contact1.linkedEvents = eventContactList

        mockDomain(Case, [])
        mockDomain(LegalContact, [contact1])
        mockDomain(EventContact, eventContactList)

        when:
        controller.params.id = contact1.id
        def models = controller.show()

        then:
        models["futureEvents"].contains(futureEvent1)
        models["futureEvents"].contains(futureEvent2)
    }

    def 'should link cases to contacts'() {
        given:
        def newCase1 = new Case(id: 1, caseId: "567")
        def newCase2 = new Case(id: 2, caseId: "568")
        def legalContact = new LegalContact(primaryMobile: "67890", id: 1)
        mockDomain(Case, [newCase1, newCase2])
        mockDomain(LegalContact, [legalContact])
        mockDomain(CaseContacts, [])

        when:
        controller.params.currentId = 1
        controller.params.primaryMobile = "67890"
        controller.params.notes = ""
        controller.params.name = ""
        controller.params.linkedCases = "{'1':'client','2':'witness'}"

        and:
        controller.update()

        then:
        CaseContacts.count() == 2


    }

    def 'should unlink cases from contacts'() {
        given:
        def newCase1 = new Case(id: 1, caseId: "567")
        def newCase2 = new Case(id: 2, caseId: "568")
        def legalContact = new LegalContact(primaryMobile: "67890", id: 1)
        def caseContact1 = new CaseContacts(legalContact: legalContact, legalCase: newCase1, involvement: "client")
        def caseContact2 = new CaseContacts(legalContact: legalContact, legalCase: newCase2, involvement: "witness")
        mockDomain(Case, [newCase1, newCase2])
        mockDomain(LegalContact, [legalContact])
        mockDomain(CaseContacts, [caseContact1, caseContact2])

        when:
        controller.params.currentId = 1
        controller.params.primaryMobile = "67890"
        controller.params.notes = ""
        controller.params.name = ""
        controller.params.linkedCases = "{'1':'client'}"

        and:
        controller.update()

        then:
        CaseContacts.count() == 1
    }

}
