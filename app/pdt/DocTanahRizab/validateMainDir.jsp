#set($fieldvalidateMainDir = "fieldvalidateMainDir_"+$ID_DOCTRMUTAMA)
#if($duplicateMainDir=="Y")
<span class="blink"><font color='red'>Nama Direktori Telah Wujud!</font></span>
#end	
<input type="hidden" id="$fieldvalidateMainDir" name="$fieldvalidateMainDir" value="$duplicateMainDir" >