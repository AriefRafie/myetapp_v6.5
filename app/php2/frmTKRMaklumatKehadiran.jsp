<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #if ($flagPopup == 'openPopupKehadiran')
  <tr>
    <td> #parse("app/php2/frmTKRMaklumatKehadiranDetail.jsp") </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
  #end
  <tr>
    <td><fieldset>
      <legend><strong>SENARAI KEHADIRAN</strong></legend>
      <table align="center" width="100%">
        <tr>
          <td colspan="4" scope="row"><input name="cmdDaftar" type="button" value="Tambah" onClick="javascript:daftarKehadiran()"/></td>
        </tr>
        <tr class="table_header">
          <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
          <td width="40%"><strong>Nama</strong></td>
          <td width="30%"><strong>Jawatan</strong></td>
          <td width="25%"><strong>Negeri</strong></td>
        </tr>
        #set ($senaraiKehadiran = "")
        #if ($SenaraiKehadiran.size() > 0)
        #foreach ($senaraiKehadiran in $SenaraiKehadiran)
        #if ($senaraiKehadiran.bil == '')
        #set( $row = "row1" )
        #elseif (($senaraiKehadiran.bil % 2) != 0)
        #set( $row = "row1" )
        #else 
        #set( $row = "row2" )
        #end
        <tr>
          <td class="$row" align="center">$senaraiKehadiran.bil</td>
          <td class="$row"><a href="javascript:paparKehadiran($senaraiKehadiran.idPegawaiLaporanTanah)" class="style2">$senaraiKehadiran.namaPegawai</a></td>
          <td class="$row">$senaraiKehadiran.jawatan</td>
          <td class="$row">$senaraiKehadiran.negeri</td>
        </tr>
        #end
        #else
        <tr>
          <td class="row1" align="center">&nbsp;</td>
          <td class="row1">Tiada Rekod</td>
          <td class="row1">&nbsp;</td>
          <td class="row1">&nbsp;</td>
        </tr>
        #end
      </table>
      </fieldset></td>
  </tr>
  <tr>
    <td align="center"><input type="button" name="cmKembali" id="cmKembali" value="Kembali" onclick="kembali()"/></td>
  </tr>
</table>

<script>
function paparKehadiran(id) {
	document.${formName}.idPegawaiLaporanTanah.value = id;
	document.${formName}.flagPopup.value = "openPopupKehadiran";
	document.${formName}.modePopup.value = "view";
	doAjaxCall${formName}("");
}
function daftarKehadiran() {
	document.${formName}.flagPopup.value = "openPopupKehadiran";
	document.${formName}.modePopup.value = "new";
	doAjaxCall${formName}("");
}
function simpanKehadiran(){
	if(document.${formName}.txtNama.value == ""){
		alert('Sila masukkan Nama Pegawai.');
  		document.${formName}.txtNama.focus(); 
		return; 
	}
	if(document.${formName}.socNegeri.value == ""){
		alert('Sila pilih Negeri.');
  		document.${formName}.socNegeri.focus(); 
		return; 
	}
	if(document.${formName}.socJawatan.value == ""){
		alert('Sila pilih Jawatan.');
  		document.${formName}.socJawatan.focus(); 
		return; 
	}
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.flagPopup.value = "openPopupKehadiran";
	document.${formName}.modePopup.value = "new";
	document.${formName}.hitButton.value = "simpanKehadiran";
	doAjaxCall${formName}("");
}
function batalKehadiran() {
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	doAjaxCall${formName}("");
}
function kemaskiniKehadiran() {
	document.${formName}.flagPopup.value = "openPopupKehadiran";
	document.${formName}.modePopup.value = "update";
	doAjaxCall${formName}("");
}
function batalKemaskiniKehadiran() {
	document.${formName}.flagPopup.value = "openPopupKehadiran";
	document.${formName}.modePopup.value = "view";
	doAjaxCall${formName}("");
}
function simpanKemaskiniKehadiran(){
	if(document.${formName}.txtNama.value == ""){
		alert('Sila masukkan Nama Pegawai.');
  		document.${formName}.txtNama.focus(); 
		return; 
	}
	if(document.${formName}.socNegeri.value == ""){
		alert('Sila pilih Negeri.');
  		document.${formName}.socNegeri.focus(); 
		return; 
	}
	if(document.${formName}.socJawatan.value == ""){
		alert('Sila pilih Jawatan.');
  		document.${formName}.socJawatan.focus(); 
		return; 
	}
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.flagPopup.value = "openPopupKehadiran";
	document.${formName}.modePopup.value = "view";
	document.${formName}.hitButton.value = "simpanKemaskiniKehadiran";
	doAjaxCall${formName}("");
}
function hapusKehadiran(){
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	document.${formName}.hitButton.value = "hapusKehadiran";
	doAjaxCall${formName}("");
}
</script>
