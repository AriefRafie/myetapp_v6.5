<div id="fileupload_progress"></div>
<div id="progressBar" style="display: none;">
<div id="progressBarBoxContent"></div>
</div>

#if ($completed)
<script>
parent.document.getElementById("fileupload_progress").innerHTML="<div class=\"success\">Dokumen berjaya dimuat naik.</div>";
</script>
#end

<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #if ($flagPopup == 'openPopupDokumen')
  <tr>
    <td colspan="2"> #parse("app/php2/online/frmTKRImejDetail.jsp") </td>
  </tr>
  <tr>
    <td colspan="2">&nbsp;</td>
  </tr>
  #end
  <tr>
    <td colspan="2"><fieldset>
      <legend><strong>SENARAI DOKUMEN YANG DISERTAKAN</strong></legend>
      <table align="center" width="100%">
        #if ($idStatus == '')
        #if ($userRole == 'online_kjp' && $layerKJP  == $flagLayerKJP )
        <tr>
          <td colspan="4" scope="row"><input name="cmdDaftar" type="button" value="Tambah" onClick="javascript:daftarDokumen()"/></td>
        </tr>
        #end
        #end
        <tr class="table_header">
          <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
          <td width="20%"><strong>Nama Dokumen</strong></td>
          <td width="40%"><strong>Keterangan</strong></td>
          <td width="35%"><strong>Muat Turun</strong></td>
        </tr>
        #set ($senaraiImejan = "")
        #if ($SenaraiImejan.size() > 0)
        #foreach ($senaraiImejan in $SenaraiImejan)
        #if ($senaraiImejan.bil == '')
        #set( $row = "row1" )
        #elseif (($senaraiImejan.bil % 2) != 0)
        #set( $row = "row1" )
        #else 
        #set( $row = "row2" )
        #end
        <tr>
          <td class="$row" align="center">$senaraiImejan.bil</td>
          <td class="$row"><a href="javascript:paparDokumen($senaraiImejan.idDokumen)" class="style2">$senaraiImejan.namaDokumen</a></td>
          <td class="$row">$senaraiImejan.catatan</td>
          <td class="$row"><a href="javascript:javascript:cetakImej($senaraiImejan.idDokumen)" class="style2">$senaraiImejan.namaFail</a></td>
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
    <td width="30%">&nbsp;</td>
    <td width="70%">
    #if ($idStatus == '')
    #if ($mode == 'view')
    <input type="button" name="cmdBackList" id="cmdBackList" value="Kembali" onClick="doBacklist()"/>
     #if ($userRole == 'online_kjp' && $layerKJP  == $flagLayerKJP )
     <input type="button" name="cmdHapus" id="cmdHapus" value="Hapus" onClick="doHapus()"/>
     #end
     
     #if ($userRole == 'online_kjp' && $layerKJP  == $flagLayerKJP )
     #if ($layerKJP == '1')
     <input type="button" name="cmdHantar" id="cmdHantar" value="Hantar Untuk Semakan" onClick="doHantarSemakan()"/>
     #elseif ($layerKJP == '2')
    <input type="button" name="cmdHantar" id="cmdHantar" value="Sahkan Semakan" onClick="doHantarKelulusan()"/>
     #elseif ($layerKJP == '3')
     <input type="button" name="cmdHantar" id="cmdHantar" value="Luluskan Permohonan" onClick="doHantarEmel()"/>
     #end
     #end
     
    #end
    #else
    <input type="button" name="cmdBackList" id="cmdBackList" value="Kembali" onClick="doBacklist()"/>
     <input type="button" name="cdmCetak" id="cdmCetak" value="Cetak" onClick="javascript:setTable('tableReport')"/>
    #end
    </td>
  </tr>
</table>

<script>
function daftarDokumen() {
	document.${formName}.action = "?_portal_module=ekptg.view.php2.online.FrmTKROnlineSenaraiFailView";
	document.${formName}.method="POST";
	document.${formName}.actionOnline.value = "seterusnya";
	document.${formName}.mode.value = "view";	
	document.${formName}.flagPopup.value = "openPopupDokumen";
	document.${formName}.modePopup.value = "new";
	document.${formName}.submit();
}
function simpanDokumen(idPermohonan,idFail,idStatus) {

	if(document.${formName}.txtNamaImej.value == ""){
		alert('Sila masukkan Nama Imej.');
  		document.${formName}.txtNamaImej.focus(); 
		return; 
	}
	if(document.${formName}.fileupload.value == ""){
		alert('Sila pilih Imej yang Ingin Dimuatnaik.');
  		document.${formName}.fileupload.focus(); 
		return; 
	}

	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}

	var namaImej = document.${formName}.txtNamaImej.value;
 	var catatanImej = document.${formName}.txtCatatanImej.value ;
	var dp = document.${formName}.form_token.value ;
	var dopost = "&form_token="+dp;

	document.${formName}.action = "?_portal_module=ekptg.view.php2.online.FrmTKROnlineSenaraiFailView&hitButton=simpanDokumen&namaImej="+namaImej+"&catatanImej="+catatanImej+"&idPermohonan="+idPermohonan+"&selectedTabUpper=3"+dopost+"&actionOnline=seterusnya&mode=view&flagPopup=openPopupDokumen&modePopup=new&idFail="+idFail+"&idStatus="+idStatus;
	
	document.${formName}.method="post";
	document.${formName}.enctype="multipart/form-data";
    document.${formName}.encoding="multipart/form-data";
	document.${formName}.submit();
}
function batalDokumen(){
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	doAjaxCall${formName}("");
}
function kemaskiniDokumen(){
	document.${formName}.flagPopup.value = "openPopupDokumen";
	document.${formName}.modePopup.value = "update";
	doAjaxCall${formName}("");
}
function simpanKemaskiniDokumen() {

	if(document.${formName}.txtNamaImej.value == ""){
		alert('Sila masukkan Nama Imej.');
  		document.${formName}.txtNamaImej.focus(); 
		return; 
	}
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}

	document.${formName}.flagPopup.value = "openPopupDokumen";
	document.${formName}.modePopup.value = "view";
	document.${formName}.hitButton.value = "simpanKemaskiniDokumen";
	doAjaxCall${formName}("");
}
function paparDokumen(idDokumen){
	document.${formName}.action = "?_portal_module=ekptg.view.php2.online.FrmTKROnlineSenaraiFailView";
	document.${formName}.method="POST";
	document.${formName}.idDokumen.value = idDokumen;
	document.${formName}.flagPopup.value = "openPopupDokumen";
	document.${formName}.modePopup.value = "view";
	document.${formName}.submit();
}
function batalKemaskiniDokumen(){
	document.${formName}.flagPopup.value = "openPopupDokumen";
	document.${formName}.modePopup.value = "view";
	doAjaxCall${formName}("");
}
function hapusDokumen(){	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}	
	
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	document.${formName}.hitButton.value = "hapusDokumen";
	document.${formName}.submit();
}
function cetakImej(id){
	var url = "../servlet/ekptg.view.php2.FrmDisplayImage?id="+id;
    var hWnd=window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes,menubar=1');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener=document.window;
    if (hWnd.focus != null) hWnd.focus();
}
</script>
