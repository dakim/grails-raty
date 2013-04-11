<html>
<head>
    <title>JQuery Grails Raty</title>
	<r:require module="raty"/>
	<r:layoutResources />
</head>

<body>
	<h2> Welcome !</h2>


	<div id="star"></div>

	<br />

	<div id="demo"></div>
	<ry:stars divId="demo" name="myScore" number="5" score="2" readOnly="false"  half="true" 
				click="${remoteFunction(action: 'checkRemote', params: '\'score=\' + score')}"/>

	<script type="text/javascript">
		jQuery.fn.raty.defaults.path = 'images';
		jQuery('#star').raty({ number: 10, score: 2 });
	</script>
	

	<r:layoutResources/>
</body>

</html>