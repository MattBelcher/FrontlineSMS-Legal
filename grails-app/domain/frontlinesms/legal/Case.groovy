package frontlinesms.legal

class Case {

    static mapping = {
        table 'legal_case'
    }

    static constraints = {
        caseId(unique: true, blank: false)
        description(nullable: true)
    }

    static hasMany = [contacts: LegalContact]
    String caseId
    String description

}
