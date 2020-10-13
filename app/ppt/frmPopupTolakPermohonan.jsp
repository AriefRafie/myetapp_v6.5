
<br/>
<center>

<fieldset>
<legend><strong>Sebab Permohonan Dikembalikan</strong></legend>	
	#if($mode=="new")
	<table width="100%" border="0">
		<tr>
			<td><textarea name="txtCatatan" id="txtCatatan" cols="54%" rows="6" ></textarea></td>
		</tr>
	</table>
	
	#else
	
		#foreach($data in $dataTolakPermohonan)
			#set($txtCatatan = $data.catatan_status_online)
		#end
	
		#if($isEdit=="no")
			#set($disability = "disabled")
		#else
			#set($disability = "")
		#end	

	<table width="100%" border="0">
		<tr>
			<td><textarea $disability class="disabled" style="text-transform:uppercase;" onblur="this.value.toUpperCase()" name="txtCatatan" id="txtCatatan" cols="54%" rows="6" >$!txtCatatan</textarea></td>
		</tr>
	</table>
	#end
	
</fieldset>

	<table width="100%" border="0">
	<tr>
	<td>
	<font color="red">Perhatian : </font> Sila tekan butang 'Kembali' untuk mengembalikan permohonan ini.
	</td>
	</tr>
		<tr align="center">
			<td>
				
				#if($mode=="new")
					<input type="button" name="cmdSimpan" value ="Simpan" onClick="javascript:simpanCatatanTolak('$!id_permohonan','$!mode','$jenisTolak')">
				#end
				
				#if($mode=="view")
					#if($isEdit=="no")
					<input type="button" name="cmdKemaskini" value ="Kemaskini" onClick="javascript:kemaskiniCatatanTolak('$!id_permohonan','$jenisTolak')">
					#else
					<input type="button" name="cmdUpdate" value ="Simpan" onClick="javascript:simpanCatatanTolak('$!id_permohonan','$!mode','$jenisTolak')">
					<input type="button" name="cmdBatal" value ="Batal" onClick="javascript:batalKemaskini('$!id_permohonan','$jenisTolak')">
					#end
				#end
				
				#if($modul=="php")
					<input type="button" name="cmdKembali" value ="Sila Meninggal" onClick="javascript:kembaliDashboardPhp()">
				#else
					<input type="button" name="cmdKembali" value ="Kembali" onClick="javascript:kembaliScreenUtama('$!id_permohonan','$jenisTolak')">
				#end
				
			</td>
		</tr>
	</table>
				
</center>

<!-- START HIDDEN VALUE -->
	<!-- Main Id -->
<input type="hidden" name="id_permohonan" value="$!id_permohonan">
<input type="hidden" name="modul" value="$!modul">
<input type="hidden" name="formnew">
	<!-- Others -->
<input type="hidden" name="command2">
<!-- END HIDDEN VALUE -->

<!-- START MAIN JAVASCRIPT -->
<script>
function kembaliScreenUtama(id_permohonan,jenisTolak) {

	if(jenisTolak=="internal"){
		window.opener.kembaliScreenUtama(id_permohonan);
	}else if(jenisTolak=="pelulus"){
		window.opener.returnFromPopupTolak(id_permohonan,"4",jenisTolak);
	}else if(jenisTolak=="penyemak"){
		window.opener.returnFromPopupTolak(id_permohonan,"4",jenisTolak);
	}
	window.close();

}

function kembaliDashboardPhp(){
	if ( !window.confirm("Adakah Anda Pasti?") ){
			return;
	}
	window.close();
}

function batalKemaskini(id_permohonan,jenisTolak) {
	if ( !window.confirm("Adakah Anda Pasti?")) return;
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.formnew.value = "no";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPopupTolakPermohonan&jenisTolak="+jenisTolak;
	document.${formName}.submit();

}

function kemaskiniCatatanTolak(id_permohonan,jenisTolak) {
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "kemaskiniCatatanTolak";
	document.${formName}.formnew.value = "no";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPopupTolakPermohonan&jenisTolak="+jenisTolak;
	document.${formName}.submit();

}

function simpanCatatanTolak(id_permohonan,mode,jenisTolak) {
//function simpanCatatanTolak(id_permohonan,mode) {
	if(document.${formName}.txtCatatan.value == ""){
		alert("Sila masukkan \"Sebab Permohonan Ini Ditolak\" terlebih dahulu.");
  		document.${formName}.txtCatatan.focus(); 
		return;
		
	}else{		
		if ( !window.confirm("Adakah Anda Pasti?")) return;
		document.${formName}.id_permohonan.value = id_permohonan;
		if(mode=="view"){
			document.${formName}.command.value = "kemaskiniCatatanTolak";
			document.${formName}.command2.value = "updateCatatanTolak";
			document.${formName}.formnew.value = "no";
			
		}else{
			document.${formName}.command.value = "simpanCatatanTolak";
			document.${formName}.formnew.value = "yes";
		}
		document.${formName}.action = "?_portal_module=FrmPopupTolakPermohonan&jenisTolak="+jenisTolak+"&txtCatatan="+document.${formName}.txtCatatan.value;
		document.${formName}.submit();
		
	}
	
}

</script>
<!-- END MAIN JAVASCRIPT 		-->
<!-- START OTHERS JAVASCRIPT	-->
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
	}else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
	
}

</script>
<!-- END OTHERS JAVASCRIPT -->