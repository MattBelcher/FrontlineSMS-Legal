package frontlinesms.legal.functionaltests

import geb.spock.GebSpec
import geb.Browser

class FrontlinesmsLegalGebSpec extends GebSpec {
    Browser createBrowser() {
        Browser browser = new Browser()
        String port = getPort()
        browser.baseUrl = "http://localhost:${port}/frontlinesms-legal/"
        browser
    }

    String getPort() {
        System.getProperty("server.port", "8080")
    }
}

