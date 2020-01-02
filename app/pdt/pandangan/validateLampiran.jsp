#set($fieldvalidatelampiran = "fieldvalidatelampiran_"+$ID_PANDANGANUNDANG+"_"+$ID_PANDANGANLAMPIRAN)
#if($duplicateName=="Y")
<span class="blink"><font color='red'>Nama Dokumen Telah Wujud!</font></span>
#end	
<input type="hidden" id="$fieldvalidatelampiran" name="$fieldvalidatelampiran" value="$duplicateName" >