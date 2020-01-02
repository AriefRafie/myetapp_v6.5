<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #if ($flagPopup == 'openPertindihanKoordinat')
  <tr>
    <td> #parse("app/php2/frmAPBMaklumatPertindihan.jsp") </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
  #end
  <tr>
    <td><fieldset>
      <legend><strong>SENARAI PERTINDIHAN</strong></legend>
      <table align="center" width="100%">
        <tr>
          <td colspan="9" scope="row"><input name="cmdDaftar" type="button" value="Tambah" onClick="javascript:doDaftarMaklumatPertindihanKoordinat()"/></td>
        </tr>
        <tr class="table_header">
          <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
          <td width="20%"><strong>No. Fail</strong></td>
          <td width="35%"><strong>Nama Syarikat</strong></td>
          <td width="10%" align="center"><strong>Jenis Pertindihan</strong></td>
        </tr>
        #set ($list = "")
        #if ($SenaraiPertindihan.size() > 0)
        #foreach ($list in $SenaraiPertindihan)
        #if ($list.bil == '')
        #set( $row = "row1" )
        #elseif (($list.bil % 2) != 0)
        #set( $row = "row1" )
        #else 
        #set( $row = "row2" )
        #end
        <tr>
          <td class="$row" align="center">$list.bil</td>
          <td class="$row"><a href="javascript:paparMaklumatPertindihanKoordinat('$list.idPertindihan')" class="style2">$list.noFailTindih</a></td>
          <td class="$row">$list.namaSyarikatTindih</td>
          <td class="$row" align="center">#if($list.flagJenisTindih=='1') SELURUH #else SEBAHAGIAN #end</td>
        </tr>
        #end
        #else
        <tr>
          <td class="row1" align="center">&nbsp;</td>
          <td class="row1" >Tiada Rekod</td>
          <td class="row1">&nbsp;</td>
          <td class="row1">&nbsp;</td>
        </tr>
        #end
      </table>
      </fieldset></td>
  </tr>
</table>
<script>
function doDaftarMaklumatPertindihanKoordinat(){
	document.${formName}.flagPopup.value = "openPertindihanKoordinat";
	document.${formName}.modePopup.value = "new";
	document.${formName}.action = "?_portal_module=ekptg.view.php2.FrmAPBJabatanTeknikalView";
	document.${formName}.method="POST";
	document.${formName}.submit();
}
function doSimpanMaklumatPertindihanKoordinat(){
	
	if(document.${formName}.txtNoFailTindih.value == ""){
		alert('Sila masukkan No. Fail.');
  		document.${formName}.txtNoFailTindih.focus(); 
		return; 
	}
	if(document.${formName}.txtNamaSyarikat.value == ""){
		alert('Sila masukkan Nama Syarikat.');
  		document.${formName}.txtNamaSyarikat.focus(); 
		return; 
	}
	if(document.${formName}.socJenisTindih.value == ""){
		alert('Sila masukkan Jenis Pertindihan.');
  		document.${formName}.socJenisTindih.focus(); 
		return; 
	}
	if(document.${formName}.socStatusTindih.value == ""){
		alert('Sila masukkan Status Koordinat.');
  		document.${formName}.socStatusTindih.focus(); 
		return; 
	}
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.flagPopup.value = "openPertindihanKoordinat";
	document.${formName}.modePopup.value = "view";
	document.${formName}.hitButton.value = "simpanMaklumatPertindihanKoordinat";
	document.${formName}.action = "?_portal_module=ekptg.view.php2.FrmAPBJabatanTeknikalView";
	document.${formName}.method="POST";
	document.${formName}.submit();
}
function doSimpanKemaskiniMaklumatPertindihanKoordinat(){

	if(document.${formName}.txtNoFailTindih.value == ""){
		alert('Sila masukkan No. Fail.');
  		document.${formName}.txtNoFailTindih.focus(); 
		return; 
	}
	if(document.${formName}.txtNamaSyarikat.value == ""){
		alert('Sila masukkan Nama Syarikat.');
  		document.${formName}.txtNamaSyarikat.focus(); 
		return; 
	}
	if(document.${formName}.socJenisTindih.value == ""){
		alert('Sila masukkan Jenis Pertindihan.');
  		document.${formName}.socJenisTindih.focus(); 
		return; 
	}
	if(document.${formName}.socStatusTindih.value == ""){
		alert('Sila masukkan Status Koordinat.');
  		document.${formName}.socStatusTindih.focus(); 
		return; 
	}
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.flagPopup.value = "openPertindihanKoordinat";
	document.${formName}.modePopup.value = "view";
	document.${formName}.hitButton.value = "simpanKemaskiniMaklumatPertindihanKoordinat";
	document.${formName}.action = "?_portal_module=ekptg.view.php2.FrmAPBJabatanTeknikalView";
	document.${formName}.method="POST";
	document.${formName}.submit();
}

function paparMaklumatPertindihanKoordinat(idPertindihan){
	document.${formName}.flagPopup.value = "openPertindihanKoordinat";
	document.${formName}.modePopup.value = "view";
	document.${formName}.idPertindihan.value = idPertindihan;
	document.${formName}.action = "?_portal_module=ekptg.view.php2.FrmAPBJabatanTeknikalView";
	document.${formName}.method="POST";
	document.${formName}.submit();
}
function doBatalMaklumatPertindihanKoordinat(){
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	document.${formName}.action = "?_portal_module=ekptg.view.php2.FrmAPBJabatanTeknikalView";
	document.${formName}.method="POST";
	document.${formName}.submit();
}
function doKemaskiniMaklumatPertindihanKoordinat(){
	document.${formName}.flagPopup.value = "openPertindihanKoordinat";
	document.${formName}.modePopup.value = "update";
	document.${formName}.action = "?_portal_module=ekptg.view.php2.FrmAPBJabatanTeknikalView";
	document.${formName}.method="POST";
	document.${formName}.submit();
}
function doBatalKemaskiniMaklumatPertindihanKoordinat(){
	document.${formName}.flagPopup.value = "openPertindihanKoordinat";
	document.${formName}.modePopup.value = "view";
	document.${formName}.action = "?_portal_module=ekptg.view.php2.FrmAPBJabatanTeknikalView";
	document.${formName}.method="POST";
	document.${formName}.submit();
}
function doHapusMaklumatPertindihanKoordinat(){
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	document.${formName}.hitButton.value = "hapusMaklumatPertindihanKoordinat";
	document.${formName}.action = "?_portal_module=ekptg.view.php2.FrmAPBJabatanTeknikalView";
	document.${formName}.method="POST";
	document.${formName}.submit();
}
</script>
