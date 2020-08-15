<style type="text/css">
<!--
.style1 {
	color: #FF0000
}
.style2 {color: #0000FF}
-->
</style>
<p>
  <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
  <input name="idFail" type="hidden" id="idFail" value="$idFail"/>
  <input name="idPermohonan" type="hidden" id="idPermohonan" value="$idPermohonan"/>
  <input name="idStatus" type="hidden" id="idStatus" value="$idStatus"/>
  <input name="idKeputusan" type="hidden" id="idKeputusan" value="$idKeputusan"/>
  <input name="flagGuna" type="hidden" id="idHakmilik" value="$flagGuna"/>
  <input name="hitButton" type="hidden" id="hitButton"/>
  <input name="mode" type="hidden" id="mode" value="$mode"/>  
  <input name="step" type="hidden" id="step" value="$step"/>
</p>
<table width="100%">
  #if ($idFail != '')
  <tr>
    <td>#parse("app/php2/frmPLPHeader.jsp")</td>
  </tr>
  #else
  <tr>
    <td><div class="warning">SILA PILIH FAIL DI SENARAI FAIL TERLEBIH DAHULU</div></td>
  </tr>
  #end
  #if ($idFail != '' && $idStatus != '1610198' && $idStatus != '1610199' && $idStatus != '1610200' && $idStatus != '1610201' && $idStatus != '1610202' && $idStatus != '1610203' && $idStatus !='1610204' && $idStatus !='1610205' && $idStatus !='1610206' && $idStatus !='1610208')
  <tr>
	<td><fieldset id="tableReport">
	<legend><strong>SENARAI LAPORAN</strong></legend>
	<table width="100%" border="0" cellspacing="2" cellpadding="2">
	
	#if($idKeputusan == 'L' || ($idKeputusan == 'B' && $idKeputusanPTD == 'S' ))
		#if($flagGuna == '1')
  		<tr>
    		<td ><a href="#" class="style2" onClick="javascript:cetakSuratKelulusanSeluruh('$idFail')">Surat Kelulusan</a></td>
  		</tr>
  		<tr>
    		<td ><a href="#" class="style2" onClick="javascript:cetakSuratIringan12A('$idFail')">Surat Iringan 12A</a></td>
  		</tr>
  		<tr>
    		<td ><a href="#" class="style2" onClick="javascript:cetakBorang12A('$idFail')">Borang 12A</a></td>
  		</tr>
		#elseif($flagGuna == '2')
  		<tr>
    		<td ><a href="#" class="style2" onClick="javascript:cetakSuratKelulusanSebahagian('$idFail')">Surat Kelulusan</a></td>
  		</tr>
  		<tr>
    		<td ><a href="#" class="style2" onClick="javascript:cetakSuratIringan12B('$idFail')">Surat Iringan 12B</a></td>
  		</tr>
  		<tr>
    		<td ><a href="#" class="style2" onClick="javascript:cetakBorang12B('$idFail')">Borang 12B</a></td>
  		</tr>
		#end
	#end
	</table>
	</fieldset>
	</td>
  </tr>
  <tr>
    <td>#if($idKeputusan == 'L')
    <fieldset>
    #if($flagGuna == '1')
      <legend>MAKLUMAT BORANG 12A</legend>
    #else
     <legend>MAKLUMAT BORANG 12B</legend>
    #end
    <table width="100%" border="0" cellspacing="2" cellpadding="2">
    #foreach($beanMaklumatTarikh in $BeanMaklumatTarikh)
    	<tr>
          <td width="1%">&nbsp;</td>
          <td width="28%">Tarikh Serah ke PTD/PTG</td>
          <td width="1%">:</td>
          <td width="70%"><input name="txtTarikhHantar" type="text" class="$inputTextClass" id="txtTarikhHantar" onBlur="check_date(this)" value="$beanMaklumatTarikh.tarikhHantar" size="9" maxlength="10" $readonly>
            #if ($mode != 'view')<a href="javascript:displayDatePicker('txtTarikhHantar',false,'dmy');"><img border="0" src="../img/calendar.gif"/>#end</td>
        </tr>
        <tr>
          <td width="1%">&nbsp;</td>
          <td width="28%">Tarikh Kelulusan Pelepasan</td>
          <td width="1%">:</td>
          <td width="70%"><input name="txtTarikhLulus" type="text" class="$inputTextClass" id="txtTarikhLulus" onBlur="check_date(this)" value="$beanMaklumatTarikh.tarikhLulus" size="9" maxlength="10" $readonly>
            #if ($mode != 'view')<a href="javascript:displayDatePicker('txtTarikhLulus',false,'dmy');"><img border="0" src="../img/calendar.gif"/>#end</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
        #if ($mode != 'view')
        <tr>
          <td colspan="4" valign="bottom"><i><font color="#ff0000">Perhatian</font>: Pastikan label bertanda <font color="#ff0000">*</font> diisi.</i></td>
        </tr>
        #end
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>#if ($mode == 'view')
            <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="kemaskini()"/>
            #if ($idStatus == '1614197')
            <input type="button" name="cmdHantar" id="cmdHantar" value="Selesai" onClick="doHantarProses()"/>
            <input type="button" name="cmdBatalPermohonan" id="cmdBatalPermohonan" value="Batal Permohonan" onClick="gotoBatalPermohonan()"/>
            #end
            #end
            #if ($mode == 'update')
            <input type="button" name="cmdSimpanKemaskini" id="cmdSimpanKemaskini" value="Simpan" onClick="simpanTarikh('$idKeputusan')"/>
            <input type="button" name="cmdBatalKemaskini" id="cmdBatalKemaskini" value="Batal" onClick="batal()"/>
            #end </td>
        </tr>
        #end
      </table>
      </fieldset>#end</td>
  </tr>
  #end
</table>
<script>
function setTable(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}
function simpanTarikh(idKeputusan) {
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "update";
		return;
	}
	
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "doSimpan";
	doAjaxCall${formName}("");
}
function kemaskini() {
	document.${formName}.mode.value = "update";
	doAjaxCall${formName}("");
}
function batal() {
	document.${formName}.mode.value = "view";
	doAjaxCall${formName}("");
}
function doHantarProses(){
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "view";
		return;
	}
	
	document.${formName}.mode.value = "view";
	document.${formName}.idStatus.value = "1610207";
	document.${formName}.hitButton.value = "doHantarProses";
	document.${formName}.submit();
}
function gotoBatalPermohonan(){	
	document.${formName}.step.value = "batalPermohonan";
	document.${formName}.submit();
}
function cetakSuratKelulusanSeluruh(idFail) {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmPLPPopupCetakLaporanView?idFail="+idFail+"&report=suratLulusSeluruh";
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakSuratKelulusanSebahagian(idFail) {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmPLPPopupCetakLaporanView?idFail="+idFail+"&report=suratLulusSebahagian";
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakSuratIringan12A(idFail) {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmPLPPopupCetakLaporanView?idFail="+idFail+"&report=suratIringan12A";
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakSuratIringan12B(idFail) {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmPLPPopupCetakLaporanView?idFail="+idFail+"&report=suratIringan12B";
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakBorang12A(idFail) {
	var url = "../servlet/ekptg.report.php2.PLPBorang12A?ID_FAIL="+idFail;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakBorang12B(idFail) {
	var url = "../servlet/ekptg.report.php2.PLPBorang12B?ID_FAIL="+idFail;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
</script>
