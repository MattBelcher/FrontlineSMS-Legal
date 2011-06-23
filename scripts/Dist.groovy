import org.codehaus.groovy.grails.commons.ApplicationHolder

includeTargets << grailsScript("_GrailsSettings")
includeTargets << grailsScript("_GrailsWar")

def winstoneDir = "target/winstone"
def winstoneManifest = "${winstoneDir}/META-INF/MANIFEST.MF"
def warFileName = "target/${metadata["app.name"]}-${metadata["app.version"]}.war"

target(main: "The description of the script goes here!") {
    depends(war)
    ant.mkdir(dir: winstoneDir)
    ant.unzip(src: "lib/winstone-0.9.10.jar", dest: winstoneDir)
    ant.copy(file: warFileName, tofile: "${winstoneDir}/embedded.war")
    ant.jar(destfile: "target/frontlinesms-legal.jar", basedir: winstoneDir, manifest: winstoneManifest)
}

setDefaultTarget(main)
