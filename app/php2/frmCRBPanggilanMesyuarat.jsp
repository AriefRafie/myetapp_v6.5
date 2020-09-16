<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #if ($flagPopup == 'openPanggilMesyuarat')
  <tr>
    <td> #parse("app/php2/frmCRBPanggilanMesyuaratDetails.jsp") </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
  #end
  <tr>
    <td>
     <fieldset>
      <legend><b>SENARAI NOTIFIKASI EMEL</b></legend>
      <table align="center" width="100%">
        <tr>
          <td colspan="9" scope="row"><input name="cmdTambah" type="button" value="Tambah" onClick="javascript:daftarNotifikasi()"/></td>
        </tr>
        <tr class="table_header">
          <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
          <td width="25%"><strong>Nama Pegawai</strong></td>
          <td width="35%"><strong>PTD / PTG / KJP / JKPTG</strong></td>
          <td width="15%" align="center"><strong>Emel</strong></td>
          <td width="10%"><strong>Status</strong></td>
        </tr>
        #set ($list = "")
        #if ($SenaraiNotifikasiEmel.size() > 0)
        #foreach ($list in $SenaraiNotifikasiEmel)
        #if ($list.bil == '')
        #set( $row = "row1" )
        #elseif (($list.bil % 2) != 0)
        #set( $row = "row1" )
        #else 
        #set( $row = "row2" )
        #end
        <tr>
          <td class="$row" align="center">$list.bil</td>
          <td class="$row"><a href="javascript:paparMaklumatNotifikasi('$list.idUlasanTeknikal')" class="style2">$list.namaPegawai</a></td>
          <td class="$row">$list.namaPejabatPTGPTD</td>
          <td class="$row" align="center">$list.emelPegawai</td>
          <td class="$row">$list.status</td>
        </tr>
        #end
        #else
        <tr>
          <td class="row1" align="center">&nbsp;</td>
          <td class="row1" >Tiada Rekod</td>
          <td class="row1">&nbsp;</td>
          <td class="row1">&nbsp;</td>
          <td class="row1">&nbsp;</td>
          <td class="row1">&nbsp;</td>
        </tr>
        #end
      </table>
     </fieldset>
    </td>
  </tr>
</table>
<script>
function doBatalPanggilanMesyuarat(){
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	doAjaxCall${formName}("");
}
function doSimpanRekodNotifikasiEmail(){
	
	if(document.${formName}.idSuratKe.value == ""){
		alert('Sila pilih Surat Ke terlebih dahulu.');
  		document.${formName}.idSuratKe.focus(); 
		return; 
	}
	if(document.${formName}.socNegeri.value == ""){
		alert('Sila pilih Negeri.');
		document.${formName}.socNegeri.focus(); 
		return; 
	}
	if(document.${formName}.socPejabat.value == ""){
		alert('Sila pilih Pejabat.');
		document.${formName}.socPejabat.focus(); 
		return; 
	}
	if(document.${formName}.txtNamaPegawai.value == ""){
		alert('Sila pilih Nama Pegawai.');
  		document.${formName}.txtNamaPegawai.focus(); 
		return; 
	}
	if(document.${formName}.txtEmel.value == ""){
		alert('Sila pilih Emel Pegawai.');
  		document.${formName}.txtEmel.focus(); 
		return; 
	}
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.flagPopup.value = "openPanggilMesyuarat";
	document.${formName}.modePopup.value = "view";
	document.${formName}.hitButton.value = "simpanRekodNotifikasiEmail";
	doAjaxCall${formName}("");
}
function paparMaklumatNotifikasi(idUlasanTeknikal){
	document.${formName}.flagPopup.value = "openPanggilMesyuarat";
	document.${formName}.modePopup.value = "view";
	document.${formName}.idUlasanTeknikal.value = idUlasanTeknikal;
	doAjaxCall${formName}("");
}
function doHapusRekodNotifikasiEmail(){
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	document.${formName}.hitButton.value = "hapusRekodNotifikasiEmail";
	doAjaxCall${formName}("");
}
function doKemaskiniRekodNotifikasiEmail(){
	document.${formName}.flagPopup.value = "openPanggilMesyuarat";
	document.${formName}.modePopup.value = "update";
	doAjaxCall${formName}("");
}
</script>