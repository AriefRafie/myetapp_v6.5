#set($fieldvalidateSubDir = "fieldvalidateSubDir_"+$ID_PANDANGANUNDANGUTAMA+"_"+$ID_REFER+"_"+$ID_PANDANGANUNDANG)
#if($duplicateSubDir=="Y")
<span class="blink"><font color='red'>Nama Rujukan Telah Wujud!</font></span>
#end	
<input type="hidden" id="$fieldvalidateSubDir" name="$fieldvalidateSubDir" value="$duplicateSubDir" >