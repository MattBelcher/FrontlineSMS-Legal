includeTargets << grailsScript("_GrailsProxy")
includeTargets << grailsScript("_GrailsPlugins")

target(updateCore: "Update FrontlineSMS Core plugin") {
    def url = new URL("http://10.10.5.48:8080/job/FrontlineSMS2-Core/lastSuccessfulBuild/artifact/target/frontlinesms-core.zip")
    if (connectionAvailable(url)) {
        doInstallPluginFromURL(url)
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
