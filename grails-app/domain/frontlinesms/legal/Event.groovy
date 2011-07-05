package frontlinesms.legal

import java.sql.Time

class Event {
    static mappings = {
        table "legal_event"
    }
    static constraints = {
        title(nullable: true)
    }
    String title
    Date dateFieldSelected
    Time startTimeField, endTimeField
}
