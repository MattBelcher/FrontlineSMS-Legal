package frontlinesms.legal

import java.sql.Time

class Event {
    static mappings = {
        table "legal_event"
    }
    static constraints = {
        eventTitle(nullable: true)
    }
    String eventTitle
    Date dateFieldSelected
    Time startTimeField, endTimeField

    static hasMany = [linkedContacts: EventContact]

    def delete = {
        def eventContacts = EventContact.findByEvent(this)
        eventContacts.each{
            it.delete()
        }
        this.delete()
    }
}
