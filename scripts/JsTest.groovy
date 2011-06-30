import geb.Browser

target(jsTestDriver: "The description of the script goes here!") {
    def reportsDir = "target/js-test-reports"
    def jsTestDriverJar = "lib/java/JsTestDriver-1.3.2.jar"

    ant.mkdir(dir: reportsDir)

    ant.java(jar: jsTestDriverJar, fork: "true", spawn: "true") {
        arg(line: "--port 9876")
    }

    Browser.drive() {
        go("http://localhost:9876/capture")
        ant.java(jar: jsTestDriverJar, fork: "true") {
            arg(line: "--basePath . --config src/javascript/jsTestDriver.conf --tests all --testOutput ${reportsDir}")
        }
    }
}

setDefaultTarget(jsTestDriver)
