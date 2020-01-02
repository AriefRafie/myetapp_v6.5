<style type="text/css">
<!--
.style1 {color: #FF0000}
-->
</style>
<input name="action" type="hidden" value="$action" />
<fieldset>
<legend><strong>::Aduan::</strong></legend>
<table width="100%" border="0" cellpadding="2">
  <!--#if($command != 'tambahAduan')
  <tr>
      <td width="29%">No Aduan</td>
    <td width="1%">:</td>
    <td width="70%">&nbsp;</td>
  </tr>
  #end
  <tr>-->
    <td width="29%"><span class="style1">*</span>Jenis Aduan</td>
    <td width="1%">:</td>
    <td width="70%">$selectJenisAduan</td>
  </tr>
  <tr>
    <td valign="top"><span class="style1">*</span>Aduan</td>
    <td valign="top">:</td>
    <td><label>
      <textarea name="txtAduan" cols="40" id="txtAduan"></textarea>
    </label></td>
  </tr>
  <tr>
    <td><span class="style1">*</span>Nama Pengadu</td>
    <td>:</td>
    <td><label>
      <input type="text" name="txtNamaPengadu" id="txtNamaPengadu" />
    </label></td>
  </tr>
  <tr>
    <td><span class="style1">*</span>No Tel (Rumah)</td>
    <td>:</td>
    <td><label>
      <input type="text" name="txtNoTelRumah" id="txtNoTelRumah" />
    </label></td>
  </tr>
  <tr>
    <td><span class="style1">*</span>No Tel (Bimbit)</td>
    <td>:</td>
    <td><label>
      <input type="text" name="txtNoTelBimbit" id="txtNoTelBimbit" />
    </label></td>
  </tr>
  <tr>
    <td><span class="style1">*</span>Emel</td>
    <td>:</td>
    <td><label>
      <input type="text" name="txtEmel" id="txtEmel" />
    </label></td>
  </tr>
  <!--<tr>
    <td>Bukti Aduan</td>
    <td>:</td>
    <td><label>
      <input type="file" name="txfBuktiAduan" id="txfBuktiAduan" />
    </label></td>
  </tr>-->
  <tr>
    <td>Tarikh Aduan</td>
    <td>:</td>
    <td>
    	$tarikhAduan    </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td><label></label>
        <label>
        <input type="button" name="cmdHantar" id="cmdHantar" value="Hantar Aduan" onClick = "hantar()"/>
        </label>
        <label>
        <input type="button" name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" onClick="kosongkan()"/> </label>
        <label><input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="kembali()" /></label>   </td>
  </tr>
</table>
</fieldset>
<script>
function hantar(){
	
	if ( !window.confirm("Anda Pasti?") ) return;
	
	if (document.${formName}.socJenisAduan.value == ""){
		alert('Sila pilih " Jenis Aduan " terlebih dahulu.');
		document.${formName}.socJenisAduan.focus();
		return;
	} 
	if (document.${formName}.txtAduan.value == ""){
		alert('Sila masukkan " Aduan " terlebih dahulu.');
		document.${formName}.txtAduan.focus();
		return;
	}
	if (document.${formName}.txtNamaPengadu.value == ""){
		alert('Sila masukkan " Nama Pengadu " terlebih dahulu.');
		document.${formName}.txtNamaPengadu.focus();
		return;
	} 
	if (document.${formName}.txtNoTelRumah.value == ""){
		alert('Sila masukkan " No Tel Rumah " terlebih dahulu.');
		document.${formName}.txtNoTelRumah.focus();
		return;
	} 
	if (document.${formName}.txtNoTelBimbit.value == ""){
		alert('Sila masukkan " No Tel Bimbit " terlebih dahulu.');
		document.${formName}.txtNoTelBimbit.focus();
		return;
	} 
	if (document.${formName}.txtEmel.value == ""){
		alert('Sila masukkan " Emel " terlebih dahulu.');
		document.${formName}.txtEmel.focus();
		return;
	}
	
	document.${formName}.action.value = "hantar";
	document.${formName}.submit();

}
function kosongkan(){

	document.${formName}.reset();
	document.${formName}.socJenisAduan.value = "";
	document.${formName}.txtAduan.value = "";
	document.${formName}.txtNamaPengadu.value = "";
	document.${formName}.txtNoTelRumah.value = "";
	document.${formName}.txtNoTelBimbit.value = "";
	document.${formName}.txtEmel.value = "";

}
function kembali(){
	
	document.${formName}.action.value = "kembali";
	document.${formName}.submit();
}
</script>

