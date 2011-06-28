package frontlinesms.legal.functionaltests

import geb.spock.GebSpec
import geb.Browser

class FrontlinesmsLegalGebSpec extends GebSpec {
    Browser createBrowser() {
        Browser browser = new Browser()
        browser.baseUrl = "http://localhost:8080/frontlinesms-legal/"
        browser
    }
}

