
#if($checkDuplicateModule=="false")
<font color='red' class='blink' >Modul ID Telah Wujud!</font>
#end

<input type="hidden" id="CHECK_MODULE_ID_$MODULE_GROUP$MODULE_ID" 
				name="CHECK_MODULE_ID_$MODULE_GROUP$MODULE_ID" 
				value="$checkDuplicateModule" >