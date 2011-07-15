package frontlinesms.legal.events

import frontlinesms.legal.TimeFormatter
import grails.plugin.spock.UnitSpec

class TimeFormatterSpec extends UnitSpec {

    def "should format AM time to 24 hours time"() {
        when:
        def formattedTime = TimeFormatter.formatTime("08:45AM")

        then:
        assert formattedTime == "08:45:00"
    }

    def "should format PM time to 24 hours time"() {
        when:
        def formattedTime = TimeFormatter.formatTime("08:45PM")

        then:
        assert formattedTime == "20:45:00"
    }

def "should format 12:45 AM to 00:45:00"() {
        when:
        def formattedTime = TimeFormatter.formatTime("12:45AM")

        then:
        assert formattedTime == "00:45:00"
    }
    def "should format 12:45 PM to 12:45:00"() {
            when:
            def formattedTime = TimeFormatter.formatTime("12:45PM")

            then:
            assert formattedTime == "12:45:00"
        }
    def "should format 06:45 PM to 18:45:00"() {
               when:
               def formattedTime = TimeFormatter.formatTime("06:45PM")

               then:
               assert formattedTime == "18:45:00"
           }

     def "should format 06:45 AM to 06:45:00"() {
               when:
               def formattedTime = TimeFormatter.formatTime("06:45AM")

               then:
               assert formattedTime == "06:45:00"
           }



}
