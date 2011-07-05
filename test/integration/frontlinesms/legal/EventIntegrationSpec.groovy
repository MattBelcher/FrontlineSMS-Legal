package frontlinesms.legal

import grails.plugin.spock.IntegrationSpec
import java.sql.Time


class EventIntegrationSpec extends IntegrationSpec {
    def "should save event when title,date, start time and end time are specified"() {
        given:
        def newEvent = new Event(title: "Meeting with someone", dateFieldSelected: new Date("July 12, 2011"), startTimeField: Time.valueOf("08:45:00"), endTimeField: Time.valueOf("09:00:00"))

        when:
        def success = newEvent.save(flush: true)

        then:
        success
    }

    def "should save event when title is not specified and date, start time , end time are specified"() {
        given:
        def newEvent = new Event(dateFieldSelected: new Date("July 12, 2011"), startTimeField: Time.valueOf("08:45:00"), endTimeField: Time.valueOf("09:00:00"))

        when:
        def success = newEvent.save(flush: true)

        then:
        success

    }

    def "should not save event when date is not specified"() {
        given:
        def newEvent = new Event(startTimeField: Time.valueOf("08:45:00"), endTimeField: Time.valueOf("09:00:00"))

        when:
        def success = newEvent.save(flush: true)

        then:
        !success

    }

    def "should not save event when start time is not specified"() {
        given:
        def newEvent = new Event(dateFieldSelected: new Date("July 12,2011"), endTimeField: Time.valueOf("09:00:00"))

        when:
        def success = newEvent.save(flush: true)

        then:
        !success

    }


    def "should not save event when end time is not specified"() {
        given:
        def newEvent = new Event(dateFieldSelected: new Date("July 12,2011"), startTimeField: Time.valueOf("09:00:00"))

        when:
        def success = newEvent.save(flush: true)

        then:
        !success

    }

}
