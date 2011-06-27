includeTargets << grailsScript("Init")
includeTargets << new File("${basedir}/scripts/UpdateCore.groovy")
includeTargets << grailsScript("_GrailsTest")
includeTargets << new File("${basedir}/scripts/Dist.groovy")

target(main: "Builds application") {
    depends(updateCore, dist)
}

setDefaultTarget(main)
