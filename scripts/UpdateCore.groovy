includeTargets << grailsScript("_GrailsProxy")
includeTargets << grailsScript("_GrailsPlugins")

target(updateCore: "Update FrontlineSMS Core plugin") {
    if (connectionAvailable(new URL("http://221.134.198.6/job/FrontlineSMS2-Core/lastSuccessfulBuild/artifact/grails-frontlinesms2-0.1-SNAPSHOT.zip"))){
        def url = "http://221.134.198.6/job/FrontlineSMS2-Core/lastSuccessfulBuild/artifact/grails-frontlinesms2-0.1-SNAPSHOT.zip"
    args = url
        installPlugin()
    }
    else if (connectionAvailable(new URL("http://localhost/job/FrontlineSMS2-Core/lastSuccessfulBuild/artifact/grails-frontlinesms2-0.1-SNAPSHOT.zip"))){
        def url = "http://localhost/job/FrontlineSMS2-Core/lastSuccessfulBuild/artifact/grails-frontlinesms2-0.1-SNAPSHOT.zip"
           args = url
                installPlugin()
    }
    else {
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
