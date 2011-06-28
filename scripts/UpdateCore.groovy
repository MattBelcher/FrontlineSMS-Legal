includeTargets << grailsScript("_GrailsProxy")
includeTargets << grailsScript("_GrailsPlugins")

target(updateCore: "Update FrontlineSMS Core plugin") {
    def url = "http://10.10.5.48:8080/job/FrontlineSMS2-Core/lastSuccessfulBuild/artifact/grails-frontlinesms2-0.1-SNAPSHOT.zip"
    if (connectionAvailable(new URL(url))) {
        args = url
        installPlugin()
    } else {
        echo "WARNING: Could not load the latest version of FrontlineSMS Core"
    }
}

private def connectionAvailable(url) {
    try {
        url.openStream()
        true
    } catch (exception) {
        false
    }
}

setDefaultTarget(updateCore)
