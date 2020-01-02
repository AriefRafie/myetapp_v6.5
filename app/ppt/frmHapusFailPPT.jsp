
<center>
<fieldset id="top">
<legend>Semakan Permohonan</legend>
	<table width="100%" border="0">
		<tr>
			<td width="23%">&nbsp;</td>
			<td width="8%">No. Fail</td>
			<td width="1%">:</td>
			<td width="68%"><input type="text" name="txtNoFail" id="txtNoFail" value="$!txtNoFail" style="text-transform:uppercase;" size="35" onblur="this.value=this.value.toUpperCase();" /></td>
		</tr>
	</table>
</fieldset>

	<table width="100%" border="0">
		<tr align="right">
			<td width="53%">
				<input type="button" name="cmdSemak" value="Semak" onClick="javascript:semakFail()">
				<input type="button" name="cmdKosongkan" value="Kosongkan" onClick="javascript:clearData();">
			</td>
			<td width="47%">&nbsp;</td>
		</tr>
	</table>


#if($showItem=="yes")

	<br/>
		
	#if($saiz_dataCollectionData!=0)
	
	#foreach ($data in $dataCollectionData)
		#set($lblTotalHM = $data.totalhm)
		#set($lblStatus = $data.status_semasa)
		#set($lblUrusan = $data.nama_suburusan)
	#end
		
		<table width="100%" border="0">
			<tr>
				<td width="3%">&nbsp;</td>
				<td width="13%"><b>Status Semasa</b></td>
				<td width="1%"><b>:</b></td>
				<td width="83%"><b>$!lblStatus</b></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td><b>Urusan</b></td>
				<td><b>:</b></td>
				<td><b>$!lblUrusan</b></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td><b>Jumlah Hakmilik</b></td>
				<td><b>:</b></td>
				<td><b>$!lblTotalHM</b></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td colspan="3"><a href="javascript:hapusFail('$!id_permohonan')"><font color="red"><b>&raquo;HAPUS FAIL</b></font></a></td>
			</tr>
		</table>
	
	#else
		
		<table width="100%" border="0">
			<tr>
				<td width="1%">&nbsp;</td>
				<td width="99%"><font color="red"><b>No. fail $!txtNoFail tiada di dalam rekod</b></font></td>
			</tr>
		</table>
	
	#end

#end


#if($showDelete=="yes")
<script>
	alert("No. fail telah dihapuskan");
</script>
#end

</center>








<input type="hidden" name="id_permohonan">
<input type="hidden" name="command2">
<input type="hidden" name="command3">

<!-- Anchor -->
<input type="hidden" name="ScreenLocation" value="$!ScreenLocation">
<input type="hidden" name="CursorPoint" value="$!CursorPoint">

<!-- do post -->
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>



<script>
window.onload = submitForm;
function submitForm(){

	if('$ScreenLocation' != ""){
		window.location.hash='$ScreenLocation';
		goTo('$CursorPoint');
	}
}
function clearData() {
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.command.value = "clear";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmHapusFailPPT";
	document.${formName}.submit();
}
function semakFail() {

	if(document.${formName}.txtNoFail.value == ""){
		alert("Sila masukkan \"No. Fail\" terlebih dahulu.");
  		document.${formName}.txtNoFail.focus(); 
		return;

	}else{
		
		document.${formName}.ScreenLocation.value = "top";
		document.${formName}.command.value = "semakFail";
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmHapusFailPPT";
		document.${formName}.submit();
	}
}
function hapusFail(id_permohonan) {

	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "semakFail";
	document.${formName}.command2.value = "hapusFail";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmHapusFailPPT";
	document.${formName}.submit();

}

</script>