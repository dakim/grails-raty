class GrailsRatyGrailsPlugin {
    // the plugin version
    def version = "0.1"
    // the version or versions of Grails the plugin is designed for
    def grailsVersion = "2.2 > *"
    // resources that are excluded from plugin packaging
    def pluginExcludes = [
        "grails-app/views/error.gsp"
    ]

    // TODO Fill in these fields
    def title = "Grails Raty Plugin" // Headline display name of the plugin
    def author = "Your name"
    def authorEmail = ""
    def description = '''\
Brief summary/description of the plugin.
'''

    // URL to the plugin's documentation
    def documentation = "http://grails.org/plugin/grails-raty"

    // Extra (optional) plugin metadata

    // License: one of 'APACHE', 'GPL2', 'GPL3'
//    def license = "APACHE"

    // Details of company behind the plugin (if there is one)
//    def organization = [ name: "My Company", url: "http://www.my-company.com/" ]

    // Any additional developers beyond the author specified above.
//    def developers = [ [ name: "Joe Bloggs", email: "joe@bloggs.net" ]]

    // Location of the plugin's issue tracker.
//    def issueManagement = [ system: "JIRA", url: "http://jira.grails.org/browse/GPMYPLUGIN" ]

    // Online location of the plugin's browseable source code.
//    def scm = [ url: "http://svn.codehaus.org/grails-plugins/" ]

    def doWithWebDescriptor = { xml ->
        // TODO Implement additions to web.xml (optional), this event occurs before
    }

    def doWithSpring = {
        // TODO Implement runtime spring config (optional)
    }

    def doWithDynamicMethods = { ctx ->
        for (domainClass in application.domainClasses) {
            if (Ratyble.class.isAssignableFrom(domainClass.clazz)) {
                domainClass.clazz.metaClass {

                    rate = { rater, points ->

                        def rating = RatyMapper.createCriteria().get {
                            projections { property "rating" }
                            rating {
                                eq 'raterId', rater.id
                            }
                            eq "ratingRef", delegate.id
                            eq "type", GrailsNameUtils.getPropertyName(delegate.class)
                            cache true
                        }

                        if(rating){
                            rating.points = points
                        }else{
                            rating = new Rating(points: points, raterClass: rater.class.name, raterId: rater.id)
                            new RatyMapper(rating: rating, ratingRef:delegate.id, type:GrailsNameUtils.getPropertyName(delegate.class))
                        }
                        return delegate
                    }

                    getAverageRating = { ->

                        def result = RatyMapper.createCriteria().get {
                            rating {
                                projections { avg 'points' }
                            }
                            eq "ratingRef", delegate.id
                            eq "type", GrailsNameUtils.getPropertyName(delegate.class)                              
                            cache true
                        }
                        return result
                    }
                }

           }
       }
    }

    def doWithApplicationContext = { applicationContext ->
        // TODO Implement post initialization spring config (optional)
    }

    def onChange = { event ->
        // TODO Implement code that is executed when any artefact that this plugin is
        // watching is modified and reloaded. The event contains: event.source,
        // event.application, event.manager, event.ctx, and event.plugin.
    }

    def onConfigChange = { event ->
        // TODO Implement code that is executed when the project configuration changes.
        // The event is the same as for 'onChange'.
    }

    def onShutdown = { event ->
        // TODO Implement code that is executed when the application shuts down (optional)
    }
}
