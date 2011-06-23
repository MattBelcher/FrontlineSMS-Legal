includeTargets << grailsScript("Init")
includeTargets << grailsScript("_GrailsWar")

def winstoneDir = "target/winstone"

target(main: "The description of the script goes here!") {
    depends(war)
    ant.mkdir(dir: winstoneDir)
    ant.unzip(src: "lib/winstone-0.9.10.jar", dest: winstoneDir)
    ant.copy(file: "target/frontlinesms-legal-0.1.war", tofile: "${winstoneDir}/embedded.war")
    ant.jar(destfile: "target/frontlinesms-legal.jar", basedir: winstoneDir, manifest: "${winstoneDir}/META-INF/MANIFEST.MF")
}

setDefaultTarget(main)
