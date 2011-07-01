target(jsTestDriver: "The description of the script goes here!") {
    def reportsDir = "target/test-reports"
    def jsTestDriverJar = "lib/java/JsTestDriver-1.3.2.jar"
    def browser = loadBrowserPath()

    ant.delete(dir: "web-app/js", includes: "*.js")
    ant.copy(todir: "web-app/js/"){
        fileset(dir: "src/javascript/", includes: "*.js")
        fileset(dir: "lib/javascript/", includes: "*.js")
    }

    ant.mkdir(dir: reportsDir)
    ant.java(jar: jsTestDriverJar, fork: "true") {
        command = "--basePath . --config src/javascript/jsTestDriver.conf --port 9876 "
        command += "--tests all --testOutput ${reportsDir} --browser ${browser}"
        arg(line: command)
    }
}

String loadBrowserPath() {
    def browser = System.getProperty("browser.path")
    if (browser == null) {
        ant.property(file: "build.properties")
        if (ant.project.properties."browser.path") {
            browser = ant.project.properties."browser.path"
        } else {
            ant.fail("### Could not load browser.path property. Make sure it is set either "
                + "as a system property with -Dbrowser.path=<path>, or you put it into "
                + "application.properties")
        }
    }
    browser
}

setDefaultTarget(jsTestDriver)
