#set($fieldvalidateSubDir = "fieldvalidateSubDir_"+$ID_MESYUARATUTAMA+"_"+$ID_REFER+"_"+$ID_MESYUARATCONTENT)
#if($duplicateSubDir=="Y")
<span class="blink"><font color='red'>Item Mesyuarat Telah Wujud!</font></span>
#end	
<input type="hidden" id="$fieldvalidateSubDir" name="$fieldvalidateSubDir" value="$duplicateSubDir" >