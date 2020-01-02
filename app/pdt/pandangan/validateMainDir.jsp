#set($fieldvalidateMainDir = "fieldvalidateMainDir_"+$ID_PANDANGANUNDANGUTAMA)
#if($duplicateMainDir=="Y")
<span class="blink"><font color='red'>Nama Rujukan Telah Wujud!</font></span>
#end	
<input type="hidden" id="$fieldvalidateMainDir" name="$fieldvalidateMainDir" value="$duplicateMainDir" >