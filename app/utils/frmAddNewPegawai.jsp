
<style type="text/css">
<!--
.bold {
	font-weight: bold;
}
.inputField {
 
 border: 1px solid #ece6b6;
 background-color:#ece6b6;
 font: 11px Arial, Helvetica, sans-serif; color: #000;
 text-align: left;
 width:200px; height:18px;
}
.inputField2 {
 
 border: 1px solid #ece6b6;
 background-color:#ece6b6;
 font: 11px Arial, Helvetica, sans-serif; color: #000;
 text-align: left;
 width:100px; height:18px;
}
.inputField3 {
 
 border: 1px solid #ece6b6;
 background-color:#ece6b6;
 font: 11px Arial, Helvetica, sans-serif; color: #000;
 text-align: left;
 width:400px; height:18px;
}
.inputField4 {
 
 border: 1px solid #ece6b6;
 background-color:#ece6b6;
 font: 11px Arial, Helvetica, sans-serif; color: #000;
 text-align: left;
 width:150px; height:18px;
}
.inputField5 {
 
 border: 1px solid #ece6b6;
 background-color:#ece6b6;
 font: 11px Arial, Helvetica, sans-serif; color: #000;
 text-align: left;
 width:50px; height:18px;
}
.inputField6 {
 
 border: 1px solid #ece6b6;
 background-color:#ece6b6;
 font: 11px Arial, Helvetica, sans-serif; color: #000;
 text-align: left;
 width:18px; height:18px;
}
.inputField7 {
 
 border: 1px solid #ece6b6;
 background-color:#ece6b6;
 font: 11px Arial, Helvetica, sans-serif; color: #000;
 text-align: left;
 width:42px; height:18px;
}
.inputField8 {
 
 border: 1px solid #ece6b6;
 background-color:#ece6b6;
 font: 11px Arial, Helvetica, sans-serif; color: #000;
 text-align: left;
 width:35px; height:18px;
}
.inputFieldb { 
 border: 1px solid #ebb33c;
 background-color:#ffffff;
 font: 11px Arial, Helvetica, sans-serif; color: #000;
 text-align: left;
 width:200px; height:18px;
}
.inputFieldb2 { 
border: 1px solid #ebb33c;
 background-color:#ffffff;
 font: 11px Arial, Helvetica, sans-serif; color: #000;
 text-align: left;
 width:100px; height:18px;
}
.inputFieldb3 { 
border: 1px solid #ebb33c;
 background-color:#ffffff;
 font: 11px Arial, Helvetica, sans-serif; color: #000;
 text-align: left;
 width:300px; height:18px;
}
.inputFieldb4 { 
border: 1px solid #ebb33c;
 background-color:#ffffff;
 font: 11px Arial, Helvetica, sans-serif; color: #000;
 text-align: left;
 width:150px; height:18px;
}
.inputFieldb6 { 
border: 1px solid #ebb33c;
 background-color:#ffffff;
 font: 11px Arial, Helvetica, sans-serif; color: #000;
 text-align: left;
 width:18px; height:18px;
}
.inputFieldb7 { 
border: 1px solid #ebb33c;
 background-color:#ffffff;
 font: 11px Arial, Helvetica, sans-serif; color: #000;
 text-align: left;
 width:42px; height:18px;
}
.inputFieldb8 { 
border: 1px solid #ebb33c;
 background-color:#ffffff;
 font: 11px Arial, Helvetica, sans-serif; color: #000;
 text-align: left;
 width:35px; height:18px;
}
.inputFieldb9 { 
 border: 1px solid #ebb33c;
 background-color:#ffffff;
 font: 11px Arial, Helvetica, sans-serif; color: #000;
 text-align: left;
 width:250px; height:18px;
}
.inputField10 {
 
 border: 1px solid #ece6b6;
 background-color:#ece6b6;
 font: 11px Arial, Helvetica, sans-serif; color: #000;
 text-align: left;
 width:10px; height:18px;
}
.inputFieldb10 { 
 border: 1px solid #ebb33c;
 background-color:#ffffff;
 font: 11px Arial, Helvetica, sans-serif; color: #000;
 text-align: left;
 width:25px; height:18px;
}
-->
</style>
<script src="../library/js/SpryValidationConfirm.js" type="text/javascript"></script>
<link href="../library/js/SpryValidationConfirm.css" rel="stylesheet" type="text/css" />
<script src="../library/js/SpryValidationTextField.js" type="text/javascript"></script>
<link href="../library/js/SpryValidationTextField.css" rel="stylesheet" type="text/css" />
<fieldset>
  <legend>PENDAFTARAN BARU PEGAWAI
  </legend><table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <td><table width="100%" border="0" cellspacing="2" cellpadding="0">
       
        <tr>
          <td width="21%">Nama Pegawai</td>
          <td width="1%" align="center" valign="top">:</td>
          <td width="78%"><span id="sprytextfield2">
            <input name="nama_pegawai" type="text" class="inputFieldb3" id="nama_pegawai">
            <span class="textfieldRequiredMsg">Sila masukkan nama pegawai.</span></span></td>
        </tr>
		<tr>
		<td>Kod Pegawai</td>
		<td align="center" valign="top">:</td>
		<td>
		<span id="sprytextfield4">
		<input maxlength=2 name="kod_pegawai" type="text" class="inputFieldb10" id="kod_pegawai">
		<span class="textfieldRequiredMsg">Sila masukkan kod pegawai.</span></span></td>
		</tr>
        <tr>
          <td>Jawatan</td>
          <td align="center" valign="top">:</td>
          <td>$selectJawatan</td>
        </tr>
        <tr>
          <td>Keterangan Unit</td>
          <td align="center" valign="top">:</td>
          <td><input name="keterangan_unit" type="text" class="inputFieldb" id="keterangan_unit"></td>
        </tr>
        <tr>
          <td>Pejabat</td>
          <td align="center" valign="top">:</td>
          <td><input name="nama_pejabat" type="text" class="inputField3" id="nama_pejabat" readonly value="$pejabatInfo.nama_pejabat"></td>
        </tr>
        <tr>
          <td>Alamat</td>
          <td align="center" valign="top">:</td>
          <td><input name="alamat1" type="text" class="inputField3" id="alamat1" readonly value="$pejabatInfo.alamat1"></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td align="center" valign="top">&nbsp;</td>
          <td><input name="alamat2" type="text" class="inputField3" id="alamat2" readonly value="$pejabatInfo.alamat2"></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td align="center" valign="top">&nbsp;</td>
          <td><input name="alamat3" type="text" class="inputField3" id="alamat3" readonly value="$pejabatInfo.alamat3"></td>
        </tr>
        <tr>
          <td>Poskod</td>
          <td align="center" valign="top">:</td>
          <td><input name="poskod" type="text" class="inputField2" id="poskod" readonly value="$pejabatInfo.poskod"></td>
        </tr>
        <tr>
          <td>Negeri</td>
          <td align="center" valign="top">:</td>
          <td><input name="nama_negeri" type="text" class="inputField" id="nama_negeri" value="$pejabatInfo.nama_negeri" readonly="readonly" /></td>
        </tr>
        <tr>
          <td>No. Tel </td>
          <td align="center" valign="top">:</td>
          <td><span id="sprytextfield1">
          <input name="no_tel" type="text" class="inputFieldb4" id="no_tel">
<span class="textfieldInvalidFormatMsg">Format No. Telefon tidak sah. Sila masukkan format no. telefon yang betul!</span></span></td>
        </tr>
        <tr>
          <td>Extension:</td>
          <td align="center" valign="top">:</td>
          <td><span id="sprytextfield3">
            <input name="no_tel_ext" type="text" class="inputFieldb8" id="no_tel_ext" size="3" maxlength="3" />
            <span class="textfieldInvalidFormatMsg">Format No. Telefon Sambungan tidak sah. Sila masukkan format no. telefon sambungan yang betul!</span></span></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td align="center" valign="top">&nbsp;</td>
          <td><input type="submit" name="button" id="button" value="Simpan" onclick="simpan_data('SimpanPegawai')">
            <input type="submit" name="button2" id="button2" value="Batal" onclick="javascript:doAjaxCall${formName}('batal','id=$pejabatInfo.id_pejabatjkptg')"></td>
        </tr>
      </table></td>
    </tr>
    <tr>
      <td><input name="id_bandar" type="hidden" id="id_bandar" value="$pejabatInfo.id_bandar">
      <input name="id_negeri" type="hidden" id="id_negeri" value="$pejabatInfo.id_negeri">
      <input name="id_pejabatjkptg" type="hidden" id="id_pejabatjkptg" value="$pejabatInfo.id_pejabatjkptg" /></td>
    </tr>
  </table>
</fieldset>
<script type="text/javascript">
function batal(s) {	
    document.${formName}.command.value = s;
    document.${formName}.action = "";
    document.${formName}.submit();
}
function simpan_data(s) {	
    document.${formName}.command.value = s;
    document.${formName}.action = "";
    document.${formName}.submit();
}
function showhide(layer_ref,displayType) {

	if (displayType =="show") displayType = "display:inline";
	else displayType = "display:none";
	
	if (document.all) { //IS IE 4 or 5 (or 6 beta)
	eval( "document.all." + layer_ref + ".style.display = "+displayType);
	}
	if (document.layers) { //IS NETSCAPE 4 or below
	document.layers[layer_ref].display = displayType ;
	}
	if (document.getElementById &&!document.all) {
	hza = document.getElementById(layer_ref);
	hza.style.display = displayType;
	}

} 
<!--
var sprytextfield1 = new Spry.Widget.ValidationTextField("sprytextfield1", "integer", {validateOn:["blur"], isRequired:false});
var sprytextfield2 = new Spry.Widget.ValidationTextField("sprytextfield2", "none", {validateOn:["blur"]});
var sprytextfield3 = new Spry.Widget.ValidationTextField("sprytextfield3", "integer", {validateOn:["blur"], isRequired:false});
var sprytextfield4 = new Spry.Widget.ValidationTextField("sprytextfield4", "integer", {validateOn:["blur"], isRequired:true});

//-->
</script>
