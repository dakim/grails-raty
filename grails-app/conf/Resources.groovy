def isDev = grails.util.GrailsUtil.isDevelopmentEnv()

modules = {
    raty {
    	dependsOn 'jquery'
        resource url:[dir:'js',file: isDev ? 'jquery.raty.js' : 'jquery.raty.min.js'], disposition: 'head'
    }

}