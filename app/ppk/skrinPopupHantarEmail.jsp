<script type="text/javascript" src="../../library/js/CalendarPopup.js" ></script>
<link rel="stylesheet" type="text/css" href="../../library/js/jquery.timepickr.css" />
<link rel="stylesheet" type="text/css" href="../../css/eTapp_PPK.css" />

<style type="text/css">
<!-- 
input[readonly] {
	background-color:#AAD198;
	border:#546F0E 1px solid;
	color:#000;
}
-->
</style>

<input name="hitButt" type="hidden" id="hitButt">

#if ($checkAdaEmail == "false")
	<font color="red"><b>Pemohon bagi fail ini tidak mempunyai alamat email.</b></font>
	<p align="center"><input name="buttonTutup" type="button" value="Tutup" onclick="tutupWindow()" /></p>
#end

#if ($checkAdaEmail == "true")
	<fieldset><legend><font style="font-family:Verdana; font-size:8pt;	font-weight:bold;">BUTIRAN PEMOHON</font></legend>
			<table border="0" cellpadding="1" cellspacing="1" align="center">
				
				<tr>
					<td align="right">Nama Pemohon</td>
					<td>:</td>
					<td colspan="2">
						<input type="hidden" name="idFail" value="$idFail">
						<input type="text" name="namaPemohon" value="$!namaPemohon" readonly="readonly" size="40" style="text-transform:uppercase;" />
						<!-- #if($!semakPermohonan=='ada')
							<br /><font color="#FF0000" size="1"><i>Permohonan semakan telah dihantar.</font>
						#end -->
					</td>
				</tr>
				<tr>
					<td align="right">Email Pemohon</td>
					<td>:</td>
					<td colspan="2"><input type="text" name="emailPemohon" value="$!emailPemohon" size="40" style="text-transform:uppercase;" readonly="readonly" /></td>
				</tr>
				
				</table>
				</fieldset>
#end

<p align="center">

#if ($checkAdaEmail == "true")


<input type="button"  name="cmdHantarEmail" id="cmdHantarEmail" value="Hantar Email" onClick="javascript:hantarEmail()" /> 

#end
</p> 
<script>
function hantarEmail() {
	
	document.${formName}.action = "?_portal_module=ekptg.view.ppk.SkrinPopupHantarEmail";
	document.${formName}.hitButt.value = "hantarEmail";
	document.${formName}.submit();
}



function tutupWindow()
{
	
	window.close();
    
}


</script>