package frontlinesms.legal

class Case {

    static mapping = {
        table 'legal_case'
    }

    static constraints = {
        caseId(unique: true, blank: false)
        description(nullable: true)
    }

    static hasMany = [contacts: LegalContact, linkedContacts: CaseContacts]

    String caseId
    String description
    boolean active = true



}
