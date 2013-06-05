# Grails-Raty Plugin

This plugin integrates [Raty.js](http://wbotelhos.com/raty/ "Offical Website") with the goal to provide a comfortable utilization of ajax calls and accessing the raty features without typing any javaScript code. 

## Taglib

Example

	<div id="demo"></div>
	<ry:stars divId="demo" name="myScore" number="5" score="2" />

###required

- `divId` - the referenced div for displaying the stars

###optional

- `name`  - the name for the hiddenfield to be submitted, default is 'score'
- `score` - the number of preselected stars
- `number` - the number of stars, default is 5
- `half` - allows selecting half stars, default is false
- `readOnly` - default is false
- `remoteAction` - the grails controller action
- `remoteController` - deviating controller name
- `remoteParams` - List of additional params

## Configuration

For using custom images configure an own folder in `Config.groovy`

    grailsApplication.config.raty.images = "images/customImgs"

