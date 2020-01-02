<fieldset>
<legend>Format Report</legend>
#foreach ($i in ["PDF","RTF","HTML","Excel"])
#set( $counter = $velocityCount )
#if ($rFormat == $i) 
#set ($selected = "checked")
#else
#set ($selected = "")
#end
<input onclick="javascript:doAjaxCall${formName}('setReport','format=$i')" 
id="rFormat$i" name="rFormat" value="$i" $selected type="radio">
<label for="rFormat$i">$counter. $i</label>
#end
</fieldset>
