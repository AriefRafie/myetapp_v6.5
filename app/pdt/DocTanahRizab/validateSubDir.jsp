#set($fieldvalidateSubDir = "fieldvalidateSubDir_"+$ID_DOCTRMUTAMA+"_"+$ID_REFER+"_"+$ID_DOCTRM)
#if($duplicateSubDir=="Y")
<span class="blink"><font color='red'>Nama Direktori Telah Wujud!</font></span>
#end	
<input type="hidden" id="$fieldvalidateSubDir" name="$fieldvalidateSubDir" value="$duplicateSubDir" >