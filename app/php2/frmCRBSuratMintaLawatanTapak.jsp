<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #if ($flagPopup == 'openKJT')
  <tr>
    <td> #parse("app/php2/frmCRBSuratMintaLawatanTapakDetail.jsp") </td>
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
          <td colspan="9" scope="row"><input name="cmdTambah" type="button" value="Tambah" onclick="javascript:doDaftarMaklumatKJT()"/></td>
        </tr>
        <tr class="table_header">
          <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
          <td width="60%"><strong>Agensi</strong></td>
          <td width="10%" align="center"><strong>Tarikh Hantar</strong></td>
          <td width="10%" align="center"><strong>Tarikh Dijangka Terima</strong></td>
          <td width="10%"><strong>Status</strong></td>
          <td width="5%"><strong></strong></td>
        </tr>
        #set ($list = "")
        #if ($SenaraiKJT.size() > 0)
        #foreach ($list in $SenaraiKJT)
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
          <td class="$row"><a href="javascript:paparMaklumatKJT('$list.idUlasanTeknikal')" class="style2">$list.agensi</a></td>
          #else
          <td class="$row"><a href="javascript:paparMaklumatKJT('$list.idUlasanTeknikal')" class="style2">$list.agensi</a>&nbsp;&nbsp;(ULANGAN $list.bilUlangan)</td>
          #end
          <td class="$row" align="center">$list.tarikhHantar</td>
          <td class="$row" align="center">$list.tarikhJangkaTerima</td>
          <td class="$row">$list.status</td>
          #if ($list.bilUlangan == '' || $list.bilUlangan == '0')
          <td class="$row" align="center"><a href="#" class="style2" onClick="javascript:cetakCRBSuratMULT('$idFail','$list.idUlasanTeknikal')"><img border="0" src="../img/print.gif"/></a></td>
          #else
          <td class="$row" align="center"><a href="#" class="style2" onClick="javascript:cetakCRBSuratUlanganLT('$idFail','$list.idUlasanTeknikal')"><img border="0" src="../img/print.gif"/></a></td>
          #end </tr>
        #end
        #else
        <tr>
          <td class="row1" align="center">&nbsp;</td>
          <td class="row1" >Tiada Rekod</td>
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
function doChangeJenisDokumen() {
	doAjaxCall${formName}("doChangeJenisDokumen");
}
function doChangeKementerian() {
	doAjaxCall${formName}("doChangeKementerian");
}
function doChangeNegeri() {
	doAjaxCall${formName}("doChangeNegeri");
}
function doChangePejabat() {
	doAjaxCall${formName}("doChangePejabat");
}

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
function doDaftarMaklumatKJT(){
	document.${formName}.flagPopup.value = "openKJT";
	document.${formName}.modePopup.value = "new";
	document.${formName}.flagStatus.value = "1";
	doAjaxCall${formName}("");
}
function paparMaklumatKJT(idUlasanTeknikal){
	document.${formName}.flagPopup.value = "openKJT";
	document.${formName}.modePopup.value = "view";
	document.${formName}.idUlasanTeknikal.value = idUlasanTeknikal;
	doAjaxCall${formName}("");
}
function doSimpanMaklumatKJT(){
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
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.flagPopup.value = "openKJT";
	document.${formName}.modePopup.value = "view";
	document.${formName}.hitButton.value = "simpanMaklumatKJT";
	doAjaxCall${formName}("");
}
function doSimpanMaklumatUlanganKJT(){	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.flagPopup.value = "openKJT";
	document.${formName}.modePopup.value = "view";
	document.${formName}.hitButton.value = "simpanMaklumatUlanganKJT";
	doAjaxCall${formName}("");
}
function doBatalMaklumatKJT(){
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	doAjaxCall${formName}("");
}
function doKemaskiniMaklumatKJT(){
	document.${formName}.flagPopup.value = "openKJT";
	document.${formName}.modePopup.value = "update";
	doAjaxCall${formName}("");
}
function doBatalKemaskiniMaklumatKJT(){
	document.${formName}.flagPopup.value = "openKJT";
	document.${formName}.modePopup.value = "view";
	doAjaxCall${formName}("");
}
function doSimpanKemaskiniMaklumatKJT(){
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
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.flagPopup.value = "openKJT";
	document.${formName}.modePopup.value = "view";
	document.${formName}.hitButton.value = "simpanKemaskiniMaklumatKJT";
	doAjaxCall${formName}("");
}
function doHapusMaklumatKJT(){
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	document.${formName}.hitButton.value = "hapusMaklumatKJPKJT";
	doAjaxCall${formName}("");
}
function doTerimaKJT(){
	document.${formName}.flagPopup.value = "openKJT";
	document.${formName}.modePopup.value = "update";
	document.${formName}.flagStatus.value = "2";
	doAjaxCall${formName}("");
}
function doUlanganKJT(){
	document.${formName}.flagPopup.value = "openKJT";
	document.${formName}.modePopup.value = "newUlangan";
	document.${formName}.flagStatus.value = "1";
	doAjaxCall${formName}("");
}
function cetakCRBSuratMULT(idFail,idUlasanTeknikal) {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmCRBPopupCetakLaporanView?idFail="+idFail+"&report=suratMULT&idUlasanTeknikal="+idUlasanTeknikal;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakCRBSuratUlanganLT(idFail,idUlasanTeknikal) {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmCRBPopupCetakLaporanView?idFail="+idFail+"&report=suratUlanganLT&idUlasanTeknikal="+idUlasanTeknikal;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
</script>
