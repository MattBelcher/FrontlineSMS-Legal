package frontlinesms.legal

import frontlinesms2.Contact

class CaseContacts {

    static mapping = {
        table 'case_contact_links'
    }

    LegalContact legalContact
    Case legalCase
    String involvement


    static belongsTo = [legalCase :Case]

    static CaseContacts link(legalCase, legalContact, involvement ) {
        def contactCaseLink = CaseContacts.findByLegalCaseAndLegalContact(legalCase, legalContact)
        if(!contactCaseLink){
            contactCaseLink = new CaseContacts()
            contactCaseLink.involvement = involvement
            legalContact?.addToLinkedCases(contactCaseLink)
            legalCase?.addToLinkedContacts(contactCaseLink)
            contactCaseLink.save(flush:true)
        }
        return contactCaseLink

    }

    static Case[] findCasesByLegalContact(LegalContact contact) {
        def linkedCases = EventContact.findAllByLegalContact(contact)
        linkedCases.collect { it -> it.legalCase}

    }

}
