
<style type="text/css">
<!--
.style1 {color: #FF0000}
-->
</style>
</head>

<body>
<table width="100%" border="0" cellpadding="2">
  <tr>
    <td>
    
<fieldset>
<legend><strong>::Aduan::</strong></legend>
<table width="100%" border="0" cellpadding="2">
 <input name="action" type="hidden" value="$action" />

 #foreach($view in $ViewAduan)
 <input name="idAduan" type="hidden" value="$view.id_Aduan" />
  <tr>
      <td width="29%">No Aduan</td>
    <td width="1%">:</td>
    <td width="70%">$view.no_Aduan_Online</td>
  </tr>
 
  <tr>
    <td width="29%">Jenis Aduan</td>
    <td width="1%">:</td>
    <td width="70%">$view.jenis_Aduan</td>
  </tr>
  <tr>
    <td valign="top">Aduan</td>
    <td valign="top">:</td>
    <td><label>$view.aduan</label></td>
  </tr>
  <tr>
    <td>Nama Pengadu</td>
    <td>:</td>
    <td><label>$view.nama_Pengadu</label></td>
  </tr>
  <tr>
    <td>No Tel (Rumah)</td>
    <td>:</td>
    <td><label>$view.no_Tel_Rumah</label></td>
  </tr>
  <tr>
    <td>No Tel (Bimbit)</td>
    <td>:</td>
    <td><label>$view.no_Tel_Bimbit</label></td>
  </tr>
  <tr>
    <td>Emel</td>
    <td>:</td>
    <td><label>$view.emel</label></td>
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
    <td>$view.tarikh_Aduan</td>
  </tr>
  #end
</table>
</fieldset>
    </td>
  </tr>
  <tr>
    <td>
     <fieldset><legend><strong>Untuk Kegunaan Seksyen Pengurusan dan Perundangan Tanah</strong></legend>
        <table width="100%" border="0" cellpadding="2">
          <tr>
            <td width="29%"><span class="style1">*</span>Seksyen Yang Bertanggungjawab</td>
            <td width="1%">:</td>
            <td width="70%">$selectSeksyen</td>
          </tr>
          <tr>
            <td><span class="style1">*</span>Tarikh Pengagihan Aduan</td>
            <td>:</td>
            <td>$tarikhAgihanKeSeksyen
            </td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td><label>
              <input type="button" name="cmdAgihAduan" id="cmdAgihAduan" value="Agih Aduan" onClick="agihAduan()"/>
              <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="kembali()" />
            </label></td>
          </tr>
        </table>
      </fieldset>
    </td>
  </tr>

</table>
<script>
function agihAduan(){
	
	if ( !window.confirm("Anda Pasti?") ) return;
	if (document.${formName}.socSeksyen.value == ""){
			alert('Sila pilih " Seksyen " terlebih dahulu.');
			document.${formName}.socSeksyen.focus();
			return;
	} 
	
	document.${formName}.action.value = "agihAduan";
	document.${formName}.submit();
	
}
function kembali(){
	
	document.${formName}.action.value = "kembali";
	document.${formName}.submit();

}
</script>
