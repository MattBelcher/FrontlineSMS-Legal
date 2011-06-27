grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
grails.project.dependency.resolution = {
    inherits("global") {
    }
    log "warn"
    repositories {
        grailsPlugins()
        grailsHome()
        grailsCentral()
        mavenCentral()
    }
    dependencies {
        compile "org.codehaus.geb:geb-core:latest.release"
        compile "org.seleniumhq.selenium:selenium-firefox-driver:latest.release"
        compile "org.codehaus.geb:geb-spock:latest.release"
        test 'org.seleniumhq.selenium:selenium-chrome-driver:latest.release'
        test 'org.seleniumhq.selenium:selenium-ie-driver:latest.release'


    }
}

coverage {
    xml = true
    enabledByDefault = true
}
