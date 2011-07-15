package frontlinesms.legal

class EventContact {
    static mapping = {
        table 'event_contact_links'
    }

    static belongsTo = [event:Event]

    LegalContact legalContact
    Event event

    static EventContact link(event, legalContact) {

        def contactEventLink = EventContact.findByEventAndLegalContact(event, legalContact)
        if (!contactEventLink) {
            contactEventLink = new EventContact()
            legalContact?.addToLinkedEvents(contactEventLink)
            event?.addToLinkedContacts(contactEventLink)
            contactEventLink.save()
        }
        return contactEventLink

    }


    static LegalContact[] findContactsByEvent(Event event) {
        def linkedContacts = EventContact.findByEvent(event)
        linkedContacts.collect { it -> it.legalContact}

    }

}
