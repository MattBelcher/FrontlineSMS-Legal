eventCompileEnd = { compilationType ->
    ant.java(jar: "lib/java/jruby-complete-1.6.2.jar", fork: "true") {
        def command = "-rlib/ruby/sass.jar "
        command += "-e \"require 'rubygems';load Gem.bin_path('sass', 'sass', '3.1.3')\" "
        command += "-- --update src/sass:web-app/css"
        arg(line: command)
    }
    ant.copy(todir: "web-app/js/", verbose: "true") {
        fileset(dir: "src/javascript/", includes: "*.js")
        fileset(dir: "lib/javascript/", includes: "*.js")

    }
}
