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
}
