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
        compile "org.seleniumhq.selenium:selenium-firefox-driver:2.0rc3"
        compile "org.codehaus.geb:geb-spock:latest.release"
        compile "org.apache.ant:ant:1.7.1"
        runtime "org.apache.ant:ant:1.7.1"
        compile "org.apache.ant:ant-launcher:1.7.1"
        runtime "org.apache.ant:ant-launcher:1.7.1"
        test 'org.seleniumhq.selenium:selenium-chrome-driver:latest.release'
        test 'org.seleniumhq.selenium:selenium-ie-driver:latest.release'
        test 'org.spockframework:spock-core:0.5-groovy-1.7'
    }

}

coverage {
    xml = true
    enabledByDefault = true
}
