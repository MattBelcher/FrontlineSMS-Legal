includeTargets << grailsScript("_GrailsSettings")
includeTargets << grailsScript("_GrailsWar")
includeTargets << grailsScript("_GrailsPackage")
def winstoneDir = "target/winstone"
def winstoneManifest = "${winstoneDir}/META-INF/MANIFEST.MF"
def warFileName = "target/${metadata["app.name"]}-${metadata["app.version"]}.war"
def unzippedWarDir = "target/unzippedWar"
target(dist: "Packages the final distribution file") {

    ant.exec(executable: "grails") {
        arg(value: "-Dgrails.env=prod")
        arg(value: "war")
        arg(value: warFileName)
    }
    ant.unzip(src: warFileName, dest:unzippedWarDir)
    ant.delete(file:"${unzippedWarDir}/WEB-INF/lib/ant-1.7.0.jar")
    ant.delete(file:"${unzippedWarDir}/WEB-INF/lib/ant-launcher-1.7.0.jar")
    ant.zip(basedir:unzippedWarDir, destFile: warFileName)
    ant.mkdir(dir: winstoneDir)
    ant.unzip(src: "lib/java/winstone-0.8.1.jar", dest: winstoneDir)
    ant.copy(file: warFileName, tofile: "${winstoneDir}/embedded.war")
    ant.jar(destfile: "target/frontlinesms-legal.jar", basedir: winstoneDir, manifest: winstoneManifest)
}

setDefaultTarget(dist)
