#set($fieldvalidatelampiran = "fieldvalidatelampiran_"+$ID_MESYUARATDOKUMEN)
#if($duplicateName=="Y")
<br>
<span class="blink"><font color='red'>Nama Dokumen Telah Wujud!</font></span>
#end	
<input type="hidden" id="$fieldvalidatelampiran" name="$fieldvalidatelampiran" value="$duplicateName" >