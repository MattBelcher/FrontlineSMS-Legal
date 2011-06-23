import org.codehaus.groovy.grails.commons.ApplicationHolder

includeTargets << grailsScript("Init")
includeTargets << grailsScript("_GrailsWar")

target(main: "The description of the script goes here!") {
    depends(war)
    def winstoneDir = "target/winstone"
    def winstoneManifest = "${winstoneDir}/META-INF/MANIFEST.MF"
    def warFileName = "target/${appMetadata("app.name")}-${appMetadata("app.version")}.war"

    ant.mkdir(dir: winstoneDir)
    ant.unzip(src: "lib/winstone-0.9.10.jar", dest: winstoneDir)
    ant.copy(file: warFileName, tofile: "${winstoneDir}/embedded.war")
    ant.jar(destfile: "target/frontlinesms-legal.jar", basedir: winstoneDir, manifest: winstoneManifest)
}

String appMetadata(String property) {
    ApplicationHolder.application.metadata[property]
}

setDefaultTarget(main)