package frontlinesms.legal

import frontlinesms2.Contact

class LegalContact extends Contact {
    static constraints = {
        primaryMobile(unique: true, blank: false,nullable: false)
    }
}
