#set($fieldvalidatetindakan = "fieldvalidateTindakan_"+$ID_MESYUARATCONTENT+"_"+$ID_MESYUARATTINDAKAN)
#if($duplicateName=="Y")
<br />
<span class="blink"><font color='red'>Tindakan Bahagian Telah Wujud!</font></span>
#end	
<input type="hidden" id="$fieldvalidatetindakan" name="$fieldvalidatetindakan" value="$duplicateName" >