import geb.spock.GebSpec

class ExampleTest extends GebSpec {

    def "test something"() {
        when:
        go "http://www.google.com/"
        then:
        title == "Google"
    }

}