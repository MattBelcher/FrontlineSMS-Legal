package frontlinesms.legal

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
 static LegalContact[] findContactsByCase(frontlinesms.legal.Case legalCase) {
        def linkedContacts = CaseContacts.findAllByLegalCase(legalCase)
        linkedContacts.collect { it -> it.legalContact}

    }

    static List findContactsAndInvolvementByCase(Case legalCase){
        def linkedContacts = CaseContacts.findAllByLegalCase(legalCase)
        def nestedListofInvolvmentsAndContacts = []
        linkedContacts.each{it -> nestedListofInvolvmentsAndContacts.add([id: it.legalContact.id, contactName: it.legalContact.name, contactNumber: it.legalContact.primaryMobile, contactInvolvement: it.involvement])}
        println nestedListofInvolvmentsAndContacts
        nestedListofInvolvmentsAndContacts as List
    }


    static Case[] findCasesByLegalContact(LegalContact contact) {
        def linkedCases = EventContact.findAllByLegalContact(contact)
        linkedCases.collect { it -> it.legalCase}

    }

}
