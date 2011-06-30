import grails.util.Environment

class BootStrap {

    def runnable = {
        def ant = new AntBuilder()
        while (!Thread.currentThread().interrupted()) {
            ant.copy(todir: "web-app/js/", verbose: "true") {
                fileset(dir: "src/javascript/", includes: "*.js")
                fileset(dir: "lib/javascript/", includes: "*.js")
            }
            Thread.sleep(2000)
        }
    } as Runnable
    def jsWatcher = new Thread(runnable)

    def init = { servletContext ->
        def ant = new AntBuilder()
        if (Environment.current == Environment.DEVELOPMENT) {
            jsWatcher.start()

            ant.java(jar: "lib/java/jruby-complete-1.6.2.jar", fork: "true", spawn: "true") {
                def command = "-rlib/ruby/sass.jar "
                command += "-e \"require 'rubygems';load Gem.bin_path('sass', 'sass', '3.1.3')\" "
                command += "-- --watch src/sass:web-app/css"
                arg(line: command)
            }
        }
        else {
            ant.delete(dir: "web-app/js", includes: "*.js")
            ant.copy(todir: "web-app/js/"){
                fileset(dir: "src/javascript/", includes: "*.js")
                fileset(dir: "lib/javascript/", includes: "*.js")
            }
        }

    }

    def destroy = {
        jsWatcher.interrupt()
        jsWatcher.join()
    }
}
