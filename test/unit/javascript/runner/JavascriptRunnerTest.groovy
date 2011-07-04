package javascript.runner

import geb.Browser
import org.openqa.selenium.firefox.FirefoxDriver

class JavascriptRunnerTest extends GroovyTestCase {

    private Browser browser

    void setUp() {
        browser = new Browser()
        browser.driver = new FirefoxDriver();
    }

    void tearDown() {
        browser.close()
    }

    void testJavascript() {
        browser.go(new File("test/unit/javascript/runner/SpecRunner.html").toURI().toString())
        assertTrue(testsSuccessful(browser))
    }

    private def testsSuccessful(browser) {
        def success = true
        browser.$(".spec").each() { spec ->
            if (spec.getAttribute("class").endsWith("failed")) {
                def description = spec.children(".description").text()
                def message = spec.children(".messages").children(".resultMessage").text()
                println("* ${description}: ${message}")
                success = false
            }
        }
        success
    }
}
