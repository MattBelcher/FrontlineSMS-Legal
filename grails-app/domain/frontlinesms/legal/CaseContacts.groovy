package frontlinesms.legal

class CaseContacts {

    static mapping = {
        table 'case_contact_links'
    }

    LegalContact legalContact
    Case legalCase
    String involvement

    static CaseContacts link(legalCase, legalContact, involvement ) {

        def contactCaseLink = CaseContacts.findByLegalCaseAndLegalContact(legalCase, legalContact)
        if(!contactCaseLink){
            contactCaseLink = new CaseContacts()
            legalContact?.addToLinkedCases(contactCaseLink)
            legalCase?.addToLinkedContacts(contactCaseLink)
            contactCaseLink.save()
        }
        return contactCaseLink

    }

}
