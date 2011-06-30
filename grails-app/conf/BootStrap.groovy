import grails.util.Environment

class BootStrap {

    def init = { servletContext ->
        if (Environment.current == Environment.DEVELOPMENT) {
            def ant = new AntBuilder()
            ant.java(jar: "lib/java/jruby-complete-1.6.2.jar", fork: "true", spawn: "true") {
                def command = "-rlib/ruby/sass.jar "
                command += "-e \"require 'rubygems';load Gem.bin_path('sass', 'sass', '3.1.3')\" "
                command += "-- --watch src/sass:web-app/css"
                arg(line: command)
            }
        }
    }
    def destroy = {
    }
}
