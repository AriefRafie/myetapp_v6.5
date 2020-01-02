#set($fieldvalidatelampiran = "fieldvalidatelampiran_"+$ID_DOCTRM+"_"+$ID_DOCTRMLAMPIRAN)
#if($duplicateName=="Y")
<span class="blink"><font color='red'>Nama Dokumen Telah Wujud!</font></span>
#end	
<input type="hidden" id="$fieldvalidatelampiran" name="$fieldvalidatelampiran" value="$duplicateName" >