package frontlinesms.legal

import frontlinesms2.Contact

class LegalContact {
    Contact contact
    String phoneNumber
    String type

    static constraints = {
        type(nullable: true)
        phoneNumber(unique: true,blank: false)
    }

    def static findByContactName(keyword) {
        if(keyword!=null && !keyword.toString().isEmpty())
        executeQuery("select val.contact.name,val.phoneNumber from LegalContact val where val.contact.name like '%" + keyword + "%'")
        else
        executeQuery("select val.contact.name,val.phoneNumber from LegalContact val")
    }
}
