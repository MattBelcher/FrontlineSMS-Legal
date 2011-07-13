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
}
