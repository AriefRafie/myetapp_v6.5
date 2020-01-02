<input name="emel" type="hidden" id="emel" value="$emel"/>

#if($status == 'success')
Email telah berjaya dihantar kepada $emel
#else
Email gagal dihantar. Sila berhubung dengan Admin sistem
#end
<br/>
<input type="button" name="cmdTutup" id="cmdTutup" value="Tutup" onclick="javascript:window.close();">
