Time picker

<input id="test" type="text" value="" size=4>

<script>
$jquery(document).ready(function(){
$jquery('#test').timepickr({
format24: '{h:02.d}{m:02.d}',
convention: 24,
resetOnBlur:true 
});
});
</script>
<br>

##parse("vtl/editor/fck.vm")
##<input type=button value=XXX onClick="javascript:doAjaxCall${formname}('doCarian')">
##$dbUrl <br>
schema : $DBProperties.get("user")<br>
AD Server :$DBProperties.get("ADServer")<br>

IP : $ip_address <br>
tab ID: $EkptgUtil.getTabID("Roles Management",$portal_role) <br>
My Infox: $EkptgUtil.getTabID("'My Info'",$portal_role) <br>
Tab Seksyen 8: $EkptgUtil.getTabID("Seksyen 8","adminppk") <br>
Tab Seksyen 17: $EkptgUtil.getTabID("Seksyen 17",$portal_role) <br>
Tab Seksyen 17: $EkptgUtil.getTabID("Seksyen 17","adminppk") <br>

$label.get("Username") <br>
$label.get("Info1")<br>
$label.get("Info2")<br>

$EkptgUtil.getTabID("Seksyen 8",$portal_role)<br>
$EkptgUtil.getTabID("Seksyen 17",$portal_role)<br>
$EkptgUtil.getTabID("Roles Management",$portal_role)

<br>Poskod AutoComplete <p>

<label>Type here</label> 
<input size=5 type="text" id="search" name="autocomplete_parameter" />
<span id="ajaxindicator" style="display: none">
  <img src="../img/indicator.gif" alt="Working..." />
</span>
<div id="autocomplete" class="autocomplete"></div>

<p></p>
<label>Type here2</label> 
<input onKeyUp="doCompleter();" size=5 type="text" id="search2" name="search2" />
<span id="ajaxindicator2" style="display: none">
  <img src="../img/indicator.gif" alt="Working..." />
</span>
<div id="hint2" class="autocomplete"></div>
	
<script type="text/javascript">	
	
var autoCompleter = new Ajax.Autocompleter("search","autocomplete",
"../servlet/ekptg.test.AutoComplete",
{
	minChars: 1, 
	indicator: 'ajaxindicator'
	//,afterUpdateElement : getSelectionId
}
);

function getSelectionId(text, li) {
    alert (li.id);
}

function doCompleter() {

new Ajax.Autocompleter("search2","hint2","../servlet/ekptg.test.AutoComplete",
{
	minChars: 1, 
	indicator: 'ajaxindicator2',
	paramName: "search2"
	//,afterUpdateElement : getSelectionId
}
);
}

</script>