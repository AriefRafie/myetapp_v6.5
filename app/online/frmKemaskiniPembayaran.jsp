<style type="text/css">
<!--
.style1 {color: #FF0000}
-->
</style>

&nbsp;
<fieldset>
<legend><strong>::Maklumat Pembayaran::</strong></legend>
<input name="action" type="hidden" value="$action" />
<input name="radioCheck1" type="hidden" value="$radioCheck1" />
<table width="100%" border="0" cellpadding="2">
  <tr>
    <td width="29%"><span class="style1">*</span>Mod Pembayaran</td>
    <td width="1%">:</td>
    <td width="70%"><label>
      <input type="radio" name="radio" id="sorFPX" value="1" onclick="fpx()" $radioChecked1>
    FPX Gateway
    <input type="radio" name="radio" id="sorInternetBanking" value="2" onclick="internetBank()" $radioChecked2>
    Internet Banking</label></td>
  </tr>
  <tr>
    <td><span class="style1">*</span>No Akaun Bil</td>
    <td>:</td>
    <td><label>
      <input name="txtNoAkaunBil" type="text" id="txtNoAkaunBil" size="40">
    </label></td>
  </tr>
  <tr>
    <td><span class="style1">*</span>Jenis Urusan Pembayaran</td>
    <td>:</td>
    <td>$selectJenisBayaranA</td>
  </tr>
  <tr>
    <td><span class="style1">*</span>Amaun Bayaran (RM)</td>
    <td>:</td>
    <td><label>
      <input type="text" name="txtAmaunBayaran" id="txtAmaunBayaran">
    </label></td>
  </tr>
  <tr align="center">
    <td colspan="3">&nbsp;</td>
  </tr>
  <tr align="center">
    <td colspan="3"><label></label>
      <label></label>
      <label>
      <input type="button" name="cmdSeterusnya" id="cmdSeterusnya" value="Seterusnya" onclick="seterusnya()">
      </label>
      <label>
      <input type="button" name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" onclick="kosongkan()">
      </label>
      <label>
      <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="kembali()">
      </label></td>
    </tr>
</table>
</fieldset>
<table width="100%" border="0" cellpadding="2">
  	<tr>
    <td align="right"><strong>CL-08-17</strong></td>
  	</tr>
</table>
<script>
function seterusnya(){
	
	if ( !window.confirm("Anda Pasti?") ) return;
	
		if (document.${formName}.sorFPX.value == ""){
			alert('Sila pilih " Mod Pembayaran " terlebih dahulu.');
			document.${formName}.sorFPX.focus();
			return;
		} 
		if (document.${formName}.sorInternetBanking.value == ""){
			alert('Sila pilih " Mod Pembayaran " terlebih dahulu.');
			document.${formName}.sorInternetBanking.focus();
			return;
		}
		if (document.${formName}.txtNoAkaunBil.value == ""){
			alert('Sila masukkan " No Akaun Bil " terlebih dahulu.');
			document.${formName}.txtNoAkaunBil.focus();
			return;
		} 
		if (document.${formName}.socJenisBayaranA.value == ""){
			alert('Sila pilih " Jenis Bayaran " terlebih dahulu.');
			document.${formName}.socJenisBayaranA.focus();
			return;
		} 
		if (document.${formName}.txtAmaunBayaran.value == ""){
			alert('Sila masukkan " Amaun Bayaran " terlebih dahulu.');
			document.${formName}.txtAmaunBayaran.focus();
			return;
		}
		
	document.${formName}.action.value = "seterusnya1";
	document.${formName}.submit();
}
function kembali(){
	document.${formName}.action.value = "kembali";
	document.${formName}.submit();
}
function fpx(){
	document.${formName}.radioCheck1.value = "fpx";
	document.${formName}.submit();
}
function internetBank(){
	document.${formName}.radioCheck1.value = "internetBank";
	document.${formName}.submit();
}
function kosongkan(){

	document.${formName}.reset();
	document.${formName}.txtNoAkaunBil.value = "";
	document.${formName}.txtAmaunBayaran.value = "";
	document.${formName}.socJenisBayaran.value = "";
	document.${formName}.sorFPX.checked = "false";
	document.${formName}.sorInterntBanking.checked = "false";
	
}
</script>

