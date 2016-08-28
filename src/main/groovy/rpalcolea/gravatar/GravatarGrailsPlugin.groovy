package rpalcolea.gravatar

import grails.plugins.*

class GravatarGrailsPlugin extends Plugin {

    def grailsVersion = "3.0.0 > *"
    def title = "Gravatar"
    def author = "Roberto Perez Alcolea"
    def authorEmail = "roberto@perezalcolea.info"
    def description = 'Provides the capability for displaying avatars from Gravatar.'
    def profiles = ['web']
    def documentation = "http://grails.org/plugin/grails-gravatar"
    def license = "APACHE"
    def issueManagement = [system: "Github", url: "https://github.com/rpalcolea/grails-gravatar/issues"]
    def scm = [url: "https://github.com/rpalcolea/grails-gravatar"]

}
