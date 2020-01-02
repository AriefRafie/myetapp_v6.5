
<br/>
<center>

<fieldset>
<legend><strong><font color="red">*</font>Catatan Siasatan Tidak Diteruskan</strong></legend>	

	#foreach($data in $dataStopSiasatan)
		#set($txtCatatan = $data.CATATAN_STOP_SIASATAN)
	#end

	<table width="100%" border="0">
		<tr>
			<td><textarea style="text-transform:uppercase;" onblur="this.value.toUpperCase()" name="txtCatatan" id="txtCatatan" cols="40%" rows="4" >$!txtCatatan</textarea></td>
		</tr>
	</table>

	#if($showAlert=="yes")
	<div align="left"><font color="red">Data telah berjaya disimpan</font></div>
	#end
	
</fieldset>

	<table width="100%" border="0">
		<tr align="center">
			<td>
				<input type="button" name="cmdSimpan" value ="Simpan" onClick="javascript:updateCatatan()">
				<input type="button" name="cmdKembali" value ="Kembali" onClick="javascript:kembaliScreenUtama('$!id_permohonan','$!id_hakmilik')">
			</td>
		</tr>
	</table>
				
</center>


<!-- START HIDDEN VALUE -->

<!-- Main Id -->
<input type="hidden" name="id_hakmilik" value="$!id_hakmilik">
<input type="hidden" name="id_permohonan" value="$!id_permohonan">
<input type="hidden" name="id_suburusanstatusfailppt" value="$!id_suburusanstatusfailppt">
<input type="hidden" name="id_fail" value="$!id_fail">

<!-- Others -->
<input type="hidden" name="command2">
<!-- END HIDDEN VALUE -->



<!-- START MAIN JAVASCRIPT -->
<script>
function kembaliScreenUtama(id_permohonan,id_hakmilik) {
	window.opener.kembaliScreenUtama(id_permohonan,id_hakmilik);
	window.close();
}
function updateCatatan() {

	if(document.${formName}.txtCatatan.value == ""){
		alert("Sila masukkan catatan.");
  		document.${formName}.txtCatatan.focus(); 
		return;
	}else{
		
		if ( !window.confirm("Adakah Anda Pasti?")) return;
		document.${formName}.command.value = "updateCatatan";
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPopupStopSiasatan";
		document.${formName}.submit();
	}
}

/*
function gotoFail(){

	
}
*/
</script>
<!-- END MAIN JAVASCRIPT -->


<!-- START OTHERS JAVASCRIPT -->
<script>
window.onload = submitForm;
function submitForm(){

	if('$ScreenLocation' != ""){
		window.location.hash='$ScreenLocation';
		goTo('$CursorPoint');
	}
}
function setTable(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}
</script>
<!-- END OTHERS JAVASCRIPT -->