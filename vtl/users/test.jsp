
	<link type="text/css" href="$urlLinkImg/jquery_custom/themes/custom-theme/ui.all.css" rel="stylesheet" />
	<script type="text/javascript" src="$urlLinkImg/jquery_custom/jquery-1.3.2.js"></script>
	<script type="text/javascript" src="$urlLinkImg/jquery_custom/ui/ui.core.js"></script>
	<script type="text/javascript" src="$urlLinkImg/jquery_custom/ui/ui.draggable.js"></script>
	<script type="text/javascript" src="$urlLinkImg/jquery_custom/ui/ui.resizable.js"></script>
	<script type="text/javascript" src="$urlLinkImg/jquery_custom/ui/ui.dialog.js"></script>
	<script type="text/javascript" src="$urlLinkImg/jquery_custom/external/bgiframe/jquery.bgiframe.js"></script>
	<link type="text/css" href="$urlLinkImg/jquery_custom/demos/demos.css" rel="stylesheet" />
	<script type="text/javascript">
	$(function() {
		$("#dialog").dialog({
			bgiframe: true,
			modal: true,
			buttons: {
				Ok: function() {
					$(this).dialog('close');
				}
			}
		});
	});
	</script>

<body>


<div id="dialog" title="Download complete">
	<p>
		<span class="ui-icon ui-icon-circle-check" style="float:left; margin:0 7px 50px 0;"></span>
		Your files have downloaded successfully into the My Downloads folder.
	</p>
	<p>
		Currently using <b>36% of your storage space</b>.
	</p>
</div>



</body>
