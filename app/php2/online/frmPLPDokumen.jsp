<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #if ($flagPopup == 'openKJP')
  <tr>
    <td> #parse("app/php2/online/frmPLPMaklumatUlasanKJP.jsp") </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
  #end
  <tr>
    <td><fieldset>
      <legend><b>SENARAI DOKUMEN</b></legend>
      <table align="center" width="100%">
        <!-- <tr>
          <td colspan="9" scope="row"><input name="cmdTambah" type="button" value="Tambah" onclick="javascript:doDaftarMaklumatKJP()"/></td>
        </tr> -->
        <tr class="table_header">
          <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
          <td width="23%"><strong>Nama Dokumen</strong></td>
          <td width="32%"><strong>Agensi</strong></td>
          <td width="10%" align="center"><strong>Tarikh Hantar</strong></td>
          <td width="10%" align="center"><strong>Tarikh Dijangka Terima</strong></td>
          <td width="10%"><strong>Status</strong></td>
          <td width="10%"><strong>Upload</strong></td>
          <td width="5%"><strong></strong></td>
        </tr>
        #set ($list = "")
        #if ($SenaraiKJP.size() > 0)
        #foreach ($list in $SenaraiKJP)
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
          <td class="$row"><a href="javascript:paparMaklumatKJP('$list.idUlasanTeknikal')" class="style2">$list.namaDokumen</a>&nbsp;&nbsp;<font class="blink" ><span class="style3">$!list.statusHari</span></font></td>
          #else
          <td class="$row"><a href="javascript:paparMaklumatKJP('$list.idUlasanTeknikal')" class="style2">$list.namaDokumen</a>&nbsp;&nbsp;(ULANGAN $list.bilUlangan)&nbsp;&nbsp;<font class="blink" ><span class="style3">$!list.statusHari</span></font></td>
          #end
          <td class="$row">$list.agensi</td>
          <td class="$row" align="center">$list.tarikhHantar</td>
          <td class="$row" align="center">$list.tarikhJangkaTerima</td>
          <td class="$row">$list.status</td>
          <td class="$row"><a href="javascript:papar_Lampiran('$list.idUlasanTeknikal')"><font color="blue">$listD.nama_Fail</font></a></td>
          <!-- #if ($list.bilUlangan == '' || $list.bilUlangan == '0') -->
          <td class="$row" align="center"><a href="#" class="style2" onClick="javascript:cetakPLPSMUKJP('$idFail','$list.idUlasanTeknikal')"><img border="0" src="../../img/print.gif"/></a></td>
          <!-- #else
          <td class="$row" align="center"><a href="#" class="style2" onClick="javascript:cetakPLPSuratUlanganKJP('$idFail','$list.idUlasanTeknikal','$list.bilUlangan')"><img border="0" src="../../img/print.gif"/></a></td>
          #end </tr> -->
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
<!--   <tr>
    <td align="center"> #if ($idStatus == '1610199')
      <input type="button" name="cmdHantar" id="cmdHantar" value="Seterusnya" onClick="doSeterusnya()"/>
      <input type="button" name="cmdBatalPermohonan" id="cmdBatalPermohonan" value="Batal Permohonan" onClick="gotoBatalPermohonan()"/>
      #end</td>
  </tr> -->
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
function papar_Lampiran(idUlasanTeknikal) {
    var url = "../servlet/ekptg.view.php2.online.DisplayBlob?id="+idUlasanTeknikal;
    var hWnd = window.open(url,'displayfile','width=800,height=600, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
    hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function cetakPLPSMUKJP(idFail,idUlasanTeknikal) {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmPLPPopupCetakLaporanView?idFail="+idFail+"&report=suratMUKJP&idUlasanTeknikal="+idUlasanTeknikal;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function paparMaklumatKJP(idUlasanTeknikal){
	document.${formName}.flagPopup.value = "openKJP";
	document.${formName}.modePopup.value = "view";
	document.${formName}.idUlasanTeknikal.value = idUlasanTeknikal;
	doAjaxCall${formName}("");
	
	/* document.${formName}.action = "?_portal_module=ekptg.view.php2.online.FrmPLPOnlineMaklumat&hitButton=&doPost=tru&flagPopup=openKJP&modePopup=view&idUlasanTeknikal=idUlasanTeknikal";
	document.${formName}.submit(); */
}
function doDaftarMaklumatKJP(){
	/* document.${formName}.mode.value = "new"; */
	document.${formName}.flagPopup.value = "openKJP";
	document.${formName}.modePopup.value = "new";
	document.${formName}.flagStatus.value = "1";
	doAjaxCall${formName}("");
}
function doSimpanMaklumatKJP(){
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	/* document.${formName}.flagPopup.value = "openKJP";
	document.${formName}.modePopup.value = "view";
	document.${formName}.doPost.value = "true";
	document.${formName}.hitButton.value = "simpanMaklumatKJP";
	doAjaxCall${formName}(""); */
	
	document.${formName}.action = "?_portal_module=ekptg.view.php2.online.FrmPLPOnlineMaklumat&hitButton=simpanMaklumatKJP&doPost=tru&flagPopup=openKJP&modePopup=view";
	document.${formName}.submit();
}
function doSimpanMaklumatUlanganKJP(){	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.flagPopup.value = "openKJP";
	document.${formName}.modePopup.value = "view";
	document.${formName}.hitButton.value = "simpanMaklumatUlanganKJP";
	doAjaxCall${formName}("");
}
function doBatalMaklumatKJP(){
	/* document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	doAjaxCall${formName}(""); */
	
	document.${formName}.action = "?_portal_module=ekptg.view.php2.online.FrmPLPOnlineMaklumat&hitButton=&doPost=tru&flagPopup=&modePopup=";
	document.${formName}.submit();
}
function doKemaskiniMaklumatKJP(){
	/* document.${formName}.flagPopup.value = "openKJP";
	document.${formName}.modePopup.value = "update";
	doAjaxCall${formName}(""); */
	
	document.${formName}.action = "?_portal_module=ekptg.view.php2.online.FrmPLPOnlineMaklumat&hitButton=&doPost=tru&flagPopup=openKJP&modePopup=update";
	document.${formName}.submit();
}
function doBatalKemaskiniMaklumatKJP(){
	document.${formName}.flagPopup.value = "openKJP";
	document.${formName}.modePopup.value = "view";
	doAjaxCall${formName}("");
	
	document.${formName}.action = "?_portal_module=ekptg.view.php2.online.FrmPLPOnlineMaklumat&hitButton=&doPost=tru&flagPopup=openKJP&modePopup=view";
	document.${formName}.submit();
}
function doSimpanKemaskiniMaklumatKJP(){
	
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
			if(document.${formName}.socKeputusan.value !="L" && document.${formName}.socKeputusan.value !="G" && document.${formName}.socKeputusan.value !="T"){
			!window.confirm("Sila masukkan Keputusan.");
			return;
			}
			
	}
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	/* document.${formName}.flagPopup.value = "openKJP";
	document.${formName}.modePopup.value = "view";
	document.${formName}.hitButton.value = "simpanKemaskiniMaklumatKJP";
	doAjaxCall${formName}(""); */
	
	document.${formName}.action = "?_portal_module=ekptg.view.php2.online.FrmPLPOnlineMaklumat&hitButton=simpanKemaskiniMaklumatKJP&doPost=tru&flagPopup=openKJP&modePopup=view";
	document.${formName}.submit();
}
function doHapusMaklumatKJP(){
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	document.${formName}.hitButton.value = "hapusMaklumatKJPKJT";
	doAjaxCall${formName}("");
	
	/* document.${formName}.action = "?_portal_module=ekptg.view.php2.online.FrmPLPOnlineMaklumat&hitButton=hapusMaklumatKJPKJT&flagPopup=&modePopup=";
	document.${formName}.submit(); */
}
function doTerimaKJP(){
	/* document.${formName}.flagPopup.value = "openKJP";
	document.${formName}.modePopup.value = "update";
	document.${formName}.flagStatus.value = "2";
	doAjaxCall${formName}(""); */
	
	document.${formName}.action = "?_portal_module=ekptg.view.php2.online.FrmPLPOnlineMaklumat&hitButton=&doPost=tru&flagPopup=openKJP&modePopup=update&flagStatus=2";
	document.${formName}.submit();
}
function doUlanganKJP(){
	document.${formName}.flagPopup.value = "openKJP";
	document.${formName}.modePopup.value = "newUlangan";
	document.${formName}.flagStatus.value = "1";
	doAjaxCall${formName}("");
}
</script>
