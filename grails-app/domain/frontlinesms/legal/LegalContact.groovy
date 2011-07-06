package frontlinesms.legal

import frontlinesms2.Contact

class LegalContact {
    Contact contact
    String phoneNumber
    String type

    static constraints = {
        type(nullable: true)
        phoneNumber(unique: true, blank: false)
    }

}
