target(sass: "Compiles SASS files into the project") {
    ant.java(jar: "lib/java/jruby-complete-1.6.2.jar", fork: "true") {
        def command = "-rlib/ruby/sass.jar "
        command += "-e \"require 'rubygems';load Gem.bin_path('sass', 'sass', '3.1.3')\" "
        command += "-- --update src/sass:web-app/css"
        arg(line: command)
    }
}

setDefaultTarget(sass)
