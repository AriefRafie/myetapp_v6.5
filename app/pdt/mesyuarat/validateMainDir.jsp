#set($fieldvalidateMainDir = "fieldvalidateMainDir_"+$ID_MESYUARATUTAMA)
#if($duplicateMainDir=="Y")
<br />
<span class="blink"><font color='red'>Maklumat Mesyuarat Telah Wujud!</font></span>
#end	
<input type="hidden" id="$fieldvalidateMainDir" name="$fieldvalidateMainDir" value="$duplicateMainDir" >