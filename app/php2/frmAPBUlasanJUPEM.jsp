<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #if ($flagPopup == 'openJUPEM')
  <tr>
    <td> #parse("app/php2/frmAPBMaklumatJUPEM.jsp") </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
  #end
  <tr>
    <td><fieldset>
      <legend><b>SENARAI DOKUMEN</b></legend>
      <table align="center" width="100%">
        <tr>
          <td colspan="9" scope="row"><input name="cmdTambah" type="button" value="Tambah" onclick="javascript:doDaftarMaklumatJUPEM()"/></td>
        </tr>
        <tr class="table_header">
          <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
          <td width="23%"><strong>Nama Dokumen</strong></td>
          <td width="32%"><strong>Agensi</strong></td>
          <td width="10%" align="center"><strong>Tarikh Hantar</strong></td>
          <td width="10%" align="center"><strong>Tarikh Dijangka Terima</strong></td>
          <td width="10%"><strong>Status</strong></td>
          <td width="5%"><strong></strong></td>
        </tr>
        #set ($list = "")
        #if ($SenaraiJUPEM.size() > 0)
        #foreach ($list in $SenaraiJUPEM)
        #if ($list.bil == '')
        #set( $row = "row1" )
        #elseif (($list.bil % 2) != 0)
        #set( $row = "row1" )
        #else 
        #set( $row = "row2" )
        #end
        <tr>
          <td class="$row" align="center">$list.bil</td>
          #if ($list.bilUlangan == '' || $list.bilUlangan == '0')
          <td class="$row"><a href="javascript:paparMaklumatJUPEM('$list.idUlasanTeknikal')" class="style2">$list.namaDokumen</a>&nbsp;&nbsp;<font class="blink" ><span class="style1">$!list.statusHari</span></font></td>
          #else
          <td class="$row"><a href="javascript:paparMaklumatJUPEM('$list.idUlasanTeknikal')" class="style2">$list.namaDokumen</a>&nbsp;&nbsp;(ULANGAN $list.bilUlangan)&nbsp;&nbsp;<font class="blink" ><span class="style1">$!list.statusHari</span></font></td>
          #end
          <td class="$row">$list.agensi</td>
          <td class="$row" align="center">$list.tarikhHantar</td>
          <td class="$row" align="center">$list.tarikhJangkaTerima</td>
          <td class="$row">$list.status</td>
          #if ($list.bilUlangan == '' || $list.bilUlangan == '0')
          <td class="$row" align="center"><a href="#" class="style2" onClick="javascript:cetakAPBSMUJUPEM('$idFail','$list.idUlasanTeknikal')"><img border="0" src="../img/print.gif"/></a></td>
          #else
          <td class="$row" align="center"><a href="#" class="style2" onClick="javascript:cetakAPBSMUJUPEMUlangan('$idFail','$list.idUlasanTeknikal','$list.bilUlangan')"><img border="0" src="../img/print.gif"/></a></td>
          #end </tr>
        #end
        #else
        <tr>
          <td class="row1" align="center">&nbsp;</td>
          <td class="row1" >Tiada Rekod</td>
          <td class="row1">&nbsp;</td>
          <td class="row1">&nbsp;</td>
          <td class="row1" align="center">&nbsp;</td>
          <td class="row1">&nbsp;</td>
          <td class="row1">&nbsp;</td>
        </tr>
        #end
      </table>
      </fieldset></td>
  </tr>
</table>
<script>
function calcDate(){
	if (document.${formName}.txtTarikhHantar.value != "" && document.${formName}.txtJangkaMasa.value != ""){
		
		var tarikhHantar  = document.${formName}.txtTarikhHantar.value;
		var days  = parseInt(document.${formName}.txtJangkaMasa.value);
		
		var dt1   = parseInt(tarikhHantar.substring(0,2),10) + days;
		var mon1  = parseInt(tarikhHantar.substring(3,5),10)-1;
		var yr1   = parseInt(tarikhHantar.substring(6,10),10);
	 
		var myDate = new Date(yr1, mon1, dt1);
		
		var day = myDate.getDate();
		var month = myDate.getMonth()+1;
		var year = myDate.getFullYear();
		
		var tarikhJangkaTerima = "";
		if(month>=10){
			if(day>=10){
				tarikhJangkaTerima = day + "/" + month + "/" + year;	
			} else {
				tarikhJangkaTerima = "0"+ day + "/" + month + "/" + year;	
			}				
		} else {
			if(day>=10){
				tarikhJangkaTerima = day + "/0" + month + "/" + year;	
			} else {
				tarikhJangkaTerima = "0"+ day + "/0" + month + "/" + year;	
			}
		}
		document.${formName}.txtTarikhJangkaTerima.value = tarikhJangkaTerima;
	
	} else {
		document.${formName}.txtTarikhJangkaTerima.value = "";
	}
}
function paparMaklumatJUPEM(idUlasanTeknikal){
	document.${formName}.flagPopup.value = "openJUPEM";
	document.${formName}.modePopup.value = "view";
	document.${formName}.idUlasanTeknikal.value = idUlasanTeknikal;
	document.${formName}.action = "?_portal_module=ekptg.view.php2.FrmAPBJabatanTeknikalView";
	document.${formName}.method="POST";
	document.${formName}.submit();
}
function doDaftarMaklumatJUPEM(){
	document.${formName}.flagPopup.value = "openJUPEM";
	document.${formName}.modePopup.value = "new";
	document.${formName}.flagStatus.value = "1";
	document.${formName}.action = "?_portal_module=ekptg.view.php2.FrmAPBJabatanTeknikalView";
	document.${formName}.method="POST";
	document.${formName}.submit();
}
function doSimpanMaklumatJUPEM(){
	if(document.${formName}.txtTarikhHantar.value == ""){
		alert('Sila masukkan Tarikh Hantar.');
  		document.${formName}.txtTarikhHantar.focus(); 
		return; 
	}
	if(document.${formName}.txtJangkaMasa.value == ""){
		alert('Sila masukkan Jangkamasa.');
  		document.${formName}.txtJangkaMasa.focus(); 
		return; 
	}			
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.flagPopup.value = "openJUPEM";
	document.${formName}.modePopup.value = "view";
	document.${formName}.hitButton.value = "simpanMaklumatJUPEM";
	document.${formName}.action = "?_portal_module=ekptg.view.php2.FrmAPBJabatanTeknikalView";
	document.${formName}.method="POST";
	document.${formName}.submit();
}
function doSimpanMaklumatUlanganJUPEM(){	
	if(document.${formName}.txtTarikhHantar.value == ""){
		alert('Sila masukkan Tarikh Hantar.');
  		document.${formName}.txtTarikhHantar.focus(); 
		return; 
	}
	if(document.${formName}.txtJangkaMasa.value == ""){
		alert('Sila masukkan Jangkamasa.');
  		document.${formName}.txtJangkaMasa.focus(); 
		return; 
	}
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.flagPopup.value = "openJUPEM";
	document.${formName}.modePopup.value = "view";
	document.${formName}.hitButton.value = "simpanMaklumatUlanganJUPEM";
	document.${formName}.action = "?_portal_module=ekptg.view.php2.FrmAPBJabatanTeknikalView";
	document.${formName}.method="POST";
	document.${formName}.submit();
}
function doBatalMaklumatJUPEM(){
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	document.${formName}.action = "?_portal_module=ekptg.view.php2.FrmAPBJabatanTeknikalView";
	document.${formName}.method="POST";
	document.${formName}.submit();
}
function doKemaskiniMaklumatJUPEM(){
	document.${formName}.flagPopup.value = "openJUPEM";
	document.${formName}.modePopup.value = "update";
	document.${formName}.action = "?_portal_module=ekptg.view.php2.FrmAPBJabatanTeknikalView";
	document.${formName}.method="POST";
	document.${formName}.submit();
}
function doBatalKemaskiniMaklumatJUPEM(){
	document.${formName}.flagPopup.value = "openJUPEM";
	document.${formName}.modePopup.value = "view";
	document.${formName}.action = "?_portal_module=ekptg.view.php2.FrmAPBJabatanTeknikalView";
	document.${formName}.method="POST";
	document.${formName}.submit();
}
function doSimpanKemaskiniMaklumatJUPEM(){
	
	if (document.${formName}.flagStatus.value == "2"){
	
			if (document.${formName}.txtTarikhTerima.value ==""){
			!window.confirm("Sila masukkan Tarikh Terima.");
			return;
			}
			if(document.${formName}.txtTarikhSurat.value ==""){
			!window.confirm("Sila masukkan Tarikh Surat.");
			return;
			}
			if (document.${formName}.txtNoRujukanSurat.value ==""){
			!window.confirm("Sila masukkan No Rujukan Surat.");
			return;
			}
			
	}
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.flagPopup.value = "openJUPEM";
	document.${formName}.modePopup.value = "view";
	document.${formName}.hitButton.value = "simpanKemaskiniMaklumatJUPEM";
	document.${formName}.action = "?_portal_module=ekptg.view.php2.FrmAPBJabatanTeknikalView";
	document.${formName}.method="POST";
	document.${formName}.submit();
}
function doHapusMaklumatUlasan(){
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	document.${formName}.hitButton.value = "hapusMaklumatUlasan";
	document.${formName}.action = "?_portal_module=ekptg.view.php2.FrmAPBJabatanTeknikalView";
	document.${formName}.method="POST";
	document.${formName}.submit();
}
function doTerimaJUPEM(){
	document.${formName}.flagPopup.value = "openJUPEM";
	document.${formName}.modePopup.value = "update";
	document.${formName}.flagStatus.value = "2";
	document.${formName}.action = "?_portal_module=ekptg.view.php2.FrmAPBJabatanTeknikalView";
	document.${formName}.method="POST";
	document.${formName}.submit();
}
function doUlanganJUPEM(){
	document.${formName}.flagPopup.value = "openJUPEM";
	document.${formName}.modePopup.value = "newUlangan";
	document.${formName}.flagStatus.value = "1";
	document.${formName}.action = "?_portal_module=ekptg.view.php2.FrmAPBJabatanTeknikalView";
	document.${formName}.method="POST";
	document.${formName}.submit();
}
function simpanDokumenMohonJUPEM(idUlasanTeknikal,idPermohonan) {
	
	if(document.${formName}.fileuploadJUPEM.value == ""){
		alert('Sila pilih Fail yang Ingin Dimuatnaik.');
  		document.${formName}.fileuploadJUPEM.focus(); 
		return; 
	}

	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	var dp = document.${formName}.form_token.value ;
	var dopost = "&form_token="+dp;
	
	document.${formName}.action = "?_portal_module=ekptg.view.php2.FrmAPBJabatanTeknikalView&hitButton=simpanDokumenMohonKJT&selectedTabUpper=0&idPermohonan="+idPermohonan+"&idUlasanTeknikal="+idUlasanTeknikal+"&flagPopup=openJUPEM&modePopup=view"+dopost;
	document.${formName}.method="post";
	document.${formName}.enctype="multipart/form-data";
    document.${formName}.encoding="multipart/form-data";
	document.${formName}.submit();
}
function simpanDokumenJUPEM(idUlasanTeknikal,idPermohonan) {
	
	if(document.${formName}.fileuploadJUPEM.value == ""){
		alert('Sila pilih Fail yang Ingin Dimuatnaik.');
  		document.${formName}.fileuploadJUPEM.focus(); 
		return; 
	}

	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	var dp = document.${formName}.form_token.value ;
	var dopost = "&form_token="+dp;
	
	document.${formName}.action = "?_portal_module=ekptg.view.php2.FrmAPBJabatanTeknikalView&hitButton=simpanDokumenKJT&selectedTabUpper=0&idPermohonan="+idPermohonan+"&idUlasanTeknikal="+idUlasanTeknikal+"&flagPopup=openJUPEM&modePopup=view"+dopost;
	document.${formName}.method="post";
	document.${formName}.enctype="multipart/form-data";
    document.${formName}.encoding="multipart/form-data";
	document.${formName}.submit();
}
function cetakDokumen(id){
	var url = "../servlet/ekptg.view.php2.FrmDisplayImage?id="+id;
    var hWnd=window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes,menubar=1');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener=document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function cetakAPBSMUJUPEM(idFail,idUlasanTeknikal) {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmAPBPopupCetakLaporanView?idFail="+idFail+"&report=pengesahanPelanKawasan&idUlasanTeknikal="+idUlasanTeknikal;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakAPBSMUJUPEMUlangan(idFail,idUlasanTeknikal) {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmAPBPopupCetakLaporanView?idFail="+idFail+"&report=ulanganPengesahanPelanKawasan&idUlasanTeknikal="+idUlasanTeknikal;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function doChangeSuratKe() {
	doAjaxCall${formName}("doChangeSuratKe");
}
function doChangeNegeri() {
	doAjaxCall${formName}("doChangeNegeri");
}
function doChangePejabat() {
	doAjaxCall${formName}("doChangePejabat");
}
</script>