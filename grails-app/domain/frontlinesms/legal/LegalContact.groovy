package frontlinesms.legal

import frontlinesms2.Contact

class LegalContact {
    Contact contact
    String phoneNumber
    String type

    static constraints = {
        type(nullable: true)
    }

    def static findByContactName(keyword) {
        executeQuery("select val.contact.name,val.phoneNumber from LegalContact val where val.contact.name like '%" + keyword + "%'")
    }
}
