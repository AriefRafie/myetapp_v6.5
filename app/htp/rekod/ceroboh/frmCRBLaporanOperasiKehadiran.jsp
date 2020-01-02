<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #if ($flagPopup == 'openKehadiran')
  #if ($modePopup == 'new')
  <tr>
    <td> #parse("app/php2/frmCRBKehadiranOperasiDaftar.jsp") </td>
  </tr>
  #end
  #if ($modePopup != 'new')
  <tr>
    <td> #parse("app/php2/frmCRBKehadiranOperasiPapar.jsp") </td>
  </tr>
  #end
  <tr>
    <td>&nbsp;</td>
  </tr>
  #end
  <tr>
    <td><fieldset>
      <legend><strong>SENARAI KEHADIRAN </strong></legend>
      <table align="center" width="100%">
        <!--
        <tr>
          <td colspan="5" scope="row"><input name="cmdDaftarKehadiran" type="button" value="Tambah" onclick="javascript:daftarKehadiran()"/></td>
        </tr> 
        -->
        <tr class="table_header">
          <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
          <td width="40%" align="left" ><strong>Nama Pegawai</strong></td>
          <td width="30%" align="left"><strong>KJP/Agensi/Syarikat</strong></td>
          <td width="20%"><strong>Jawatan</strong></td>
          <td width="5%" align="center">&nbsp;</td>
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
          <td class="$row"><a href="javascript:paparKehadiran('$senaraiKehadiran.idPegawaiLaporanTanah')" class="style2">$senaraiKehadiran.namaPegawai</a>&nbsp;&nbsp; #if ($senaraiKehadiran.flagPengerusi == 'Y')(KETUA OPERASI)#end</td>
          <td class="$row">$senaraiKehadiran.namaAgensi</td>
          <td class="$row">$senaraiKehadiran.namaJawatan</td>
          <td class="$row" align="center"><input name="cmdHapus" type="button" value="Hapus" onclick="hapusKehadiran('$senaraiKehadiran.idPegawaiLaporanTanah')"/></td>
        </tr>
        #end
        #else
        <tr>
          <td class="row1" align="center">&nbsp;</td>
          <td class="row1">Tiada Rekod</td>
          <td class="row1">&nbsp;</td>
          <td class="row1">&nbsp;</td>
          <td class="row1">&nbsp;</td>
        </tr>
        #end
      </table>
      </fieldset></td>
  </tr>
</table>

<script>
function doUpdateCheck(bil){
	var b = parseInt(bil)-1;
	if (document.${formName}.flagPengerusi.length > 1){
		for (i = 0; i < document.${formName}.flagPengerusi.length; i++){
			document.${formName}.flagPengerusi[i].checked = false;
		}
		document.${formName}.flagPengerusi[b].checked = true;
	}			         
}

function batalKehadiran(){
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	doAjaxCall${formName}("");
}
function simpanKehadiran(){	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	document.${formName}.hitButton.value = "simpanKehadiran";
	doAjaxCall${formName}("");
}
function kemaskiniKehadiran(){
	document.${formName}.modePopup.value = "update";
	doAjaxCall${formName}("");
}
function batalKemaskiniKehadiran(){
	document.${formName}.modePopup.value = "view";
	doAjaxCall${formName}("");
}
function simpanKemaskiniKehadiran(){	
	if(document.${formName}.txtNama.value == ""){
		alert('Sila masukkan Nama Pegawai.');
  		document.${formName}.txtNama.focus(); 
		return; 
	}
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.modePopup.value = "view";
	document.${formName}.hitButton.value = "simpanKemaskiniKehadiran";
	doAjaxCall${formName}("");
}
</script>
