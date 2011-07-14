package frontlinesms.legal

class EventContact {
    static mapping = {
            table 'event_contact_links'
        }

        LegalContact legalContact
        Event event

        static EventContact link(event, legalContact ) {

            def contactEventLink = EventContact.findByEventAndLegalContact(event, legalContact)
            if(!contactEventLink){
                contactEventLink = new EventContact()
                legalContact?.addToLinkedEvents(contactEventLink)
                event?.addToLinkedContacts(contactEventLink)
                contactEventLink.save()
            }
            return contactEventLink

        }

}
