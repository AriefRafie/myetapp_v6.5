<style type="text/css">
<!--
.style1 {
	color: #FF0000
}
.style2 {
	color: #0000FF
}
-->
</style>
<p>
  <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
  <input name="actionTukarguna" type="hidden" id="actionTukarguna" value="$actionTukarguna"/>
  <input name="idFail" type="hidden" id="idFail" value="$idFail"/>
  <input name="idPermohonan" type="hidden" id="idPermohonan" value="$idPermohonan"/>
  <input name="idStatus" type="hidden" id="idStatus" value="$idStatus"/>
  <input name="idKeputusan" type="hidden" id="idKeputusan" value="$idKeputusan"/>
  <input name="flagGuna" type="hidden" id="idHakmilik" value="$flagGuna"/>
  <input name="statusRizab" type="hidden" id="statusRizab" value="$statusRizab"/>
  <input name="hitButton" type="hidden" id="hitButton"/>
  <input name="mode" type="hidden" id="mode" value="$mode"/>
  <input name="flagPampasan" type="hidden" id="flagPampasan" value="$flagPampasan"/>
  <input name="pampasan" type="hidden" id="pampasan" value="$pampasan"/>
  <input name="flagAktif" type="hidden" id="flagAktif" value="$flagAktif"/>
  <input name="noRayuan" type="hidden" id="noRayuan" value="$noRayuan"/>  
  <input name="step" type="hidden" id="step" value="$step"/>
</p>
<table width="100%">
  #if ($idFail != '')
  <tr>
    <td>#parse("app/php2/frmTKRHeader.jsp")</td>
  </tr>
  #else
  <tr>
    <td>&nbsp;
    <div class="warning">SILA PILIH FAIL DI SENARAI FAIL TERLEBIH DAHULU</div></td>
  </tr>
  #end
  #if ($idFail != '' && $idStatus != '1610198' && $idStatus != '1610199' && $idStatus != '1610200' && $idStatus != '1610201')
  <tr>
    <td><fieldset>
      <legend>KEPUTUSAN</legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        #foreach($beanMaklumatKeputusan in $BeanMaklumatKeputusan)
         <input name="socGanti" type="hidden" id="socGanti" value="$beanMaklumatKeputusan.jenis"/>
         <input name="txtPampasan" type="hidden" id="txtPampasan" value="$beanMaklumatKeputusan.pampasan"/>
         <input name="txtCatatan" type="hidden" id="txtCatatan" value="$beanMaklumatKeputusan.catatan"/>
         <!-- <input name="txtUlasan" type="hidden" id="txtUlasan" value="$beanMaklumatKeputusan.ulasan"/> -->
        #if ($idFail != '' && $idStatus != '1610198' && $idStatus != '1610199' && $idStatus != '1610200' && $idStatus != '1610201' && $idStatus != '1610202')
        <tr>
          <td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
          <td width="28%">Keputusan</td>
          <td width="1%">:</td>
          <td width="70%"><select name="socKeputusan" id="socKeputusan" style="width:140px;" $readonly class="$disabled" $disabled onchange="doChangeKeputusan()">
      	
        #if ($beanMaklumatKeputusan.keputusan == 'L')
              <option value=""> SILA PILIH</option>
              <option value="L" selected>LULUS</option>
              <option value="B">LULUS BERSYARAT</option>
              <option value="T">TOLAK</option>
 		#elseif ($beanMaklumatKeputusan.keputusan == 'B')
              <option value="">SILA PILIH</option>
              <option value="L">LULUS</option>
              <option value="B" selected>LULUS BERSYARAT</option>
              <option value="T">TOLAK</option>
        #elseif ($beanMaklumatKeputusan.keputusan == 'T')
              <option value="">SILA PILIH</option>
              <option value="L">LULUS</option>
              <option value="B">LULUS BERSYARAT</option>
              <option value="T" selected>TOLAK</option>  
        #else
              <option selected value="">SILA PILIH</option>
              <option value="L">LULUS</option>
              <option value="B">LULUS BERSYARAT</option>
              <option value="T">TOLAK</option>
         #end     
            </select>
          </td>
        </tr>
        #end
        #if ($idPampasan == '99999')
        	#set ($idPampasan = $beanMaklumatKeputusan.jenis)
        #end
		#if ($beanMaklumatKeputusan.keputusan == 'L')
        <tr>
          <td width="1%">&nbsp;</td>
          <td width="27%" align="top">Jenis Pampasan</td>
          <td width="1%">:</td>
		  <td width="71%">
          
			#if ($idPampasan == '1')
              TANAH GANTI
            #elseif($idPampasan == '2')
              WANG PAMPASAN
            #elseif($idPampasan == '3')
              TANAH GANTI DAN WANG PAMPASAN
            #elseif($idPampasan == '4')
              LAIN-LAIN
            #else
              TIADA PAMPASAN
            #end
		  </td>
        </tr>
        #if($idPampasan == '2' || $idPampasan == '3')
        <tr>
          <td width="1%">&nbsp;</td>
          <td align="top">Nilai Pampasan(RM)</td>
          <td>:</td>
          <td>$beanMaklumatKeputusan.pampasan</td>
       	</tr>
        #end
        #if($idPampasan == '4')
        <tr>
          <td width="1%">&nbsp;</td>
          <td align="top">Catatan</td>
          <td>:</td>
          <td>$beanMaklumatKeputusan.catatan</td>
       	</tr>
        #end
        #end
        <!-- medan baru by Ain -->
        #if ($beanMaklumatKeputusan.keputusan == 'B')
        <tr>
          <td width="1%">&nbsp;</td>
          <td>Ulasan</td>
          <td>:</td>
		  <td>
		  	<textarea name="txtUlasan" id="txtUlasan" rows="5" cols="50" $readonly class="$inputTextClass">$beanMaklumatKeputusan.ulasan</textarea>
          </td>
        </tr>
        #end
        
        <tr>
          <td>#if ($mode != 'view')<span class="style1">*</span>#end</td>
          <td>Tarikh Hantar Surat</td>
          <td>:</td>
          <td><input name="txtTarikhKeputusan" type="text" class="$inputTextClass" id="txtTarikhKeputusan" onBlur="check_date(this)" value="$beanMaklumatKeputusan.tarikhKeputusan" size="9" maxlength="10" $readonly>
            #if ($mode != 'view')<a href="javascript:displayDatePicker('txtTarikhKeputusan',false,'dmy');"><img border="0" src="../img/calendar.gif"/>#end</td>
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
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onClick="javascript:setTable('tableReport')"/>
            #if ($idStatus == '1610206')
            <input type="button" name="cmdHantar" id="cmdHantar" value="Selesai" onClick="doHantarProses()"/>
            <input type="button" name="cmdBatalPermohonan" id="cmdBatalPermohonan" value="Batal Permohonan" onClick="gotoBatalPermohonan()"/>
            #end
            #if ($idStatus == '1610208')
            #if ($flagAktif == 'Y' && ($noRayuan == '' || $noRayuan == '0'))
            <input type="button" name="cmdRayuan" id="cmdRayuan" value="Daftar Rayuan" onClick="daftarRayuan()"/>
            #end
            #if ($flagAktif == 'Y' && ($noRayuan != '' && $noRayuan != '0'))
            <input type="button" name="cmdEPU" id="cmdEPU" value="Mesyuarat EPU" onClick="daftarEPU()"/>
            #end
            #end
            #end
            #if ($mode == 'update')
            <input type="button" name="cmdSimpanKemaskini" id="cmdSimpanKemaskini" value="Simpan" onClick="simpanKeputusan('$idPampasan','$beanMaklumatKeputusan.keputusan')"/>
            <input type="button" name="cmdBatalKemaskini" id="cmdBatalKemaskini" value="Batal" onClick="batal()"/>
            #end </td>
        </tr>
        #end
      </table>
      </fieldset></td>
  </tr>
  #end
</table>
<fieldset id="tableReport" style="display:none;"-->
<legend><strong>SENARAI LAPORAN</strong></legend>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #if ($idKeputusan == 'L')
  <tr>
    <td ><a href="#" class="style2" onClick="javascript:cetakTKRSuratLulus('$idFail')"> Surat Lulus </a></td>
  </tr>
  #elseif ($idKeputusan == 'B')
  <tr>
    <td ><a href="#" class="style2" onClick="javascript:cetakTKRSuratLulusBersyarat('$idFail')"> Surat Lulus Bersyarat</a></td>
  </tr>
  #else  
  <tr>
    <td ><a href="#" class="style2" onClick="javascript:cetakTKRSuratTolak('$idFail')"> Surat Tolak </a></td>
  </tr>
  #end
</table>
</fieldset>
<script>
function doChangeKeputusan() {
	doAjaxCall${formName}("doChangeKeputusan");
}
function doChangePampasan() {
	doAjaxCall${formName}("doChangePampasan");
}
function validateCurrency(elmnt,content,content2) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = content2;
		return;
	}
	
	if(content != ""){
		var num = content * 1;
		elmnt.value = num.toFixed(2);
		return;
	} else {
		elmnt.value ="";
		return;
	}
}
function simpanKeputusan(idPampasan,idKeputusan) {
	if(document.${formName}.txtTarikhKeputusan.value == ""){
		alert('Sila masukkan Tarikh Hantar Surat');
  		document.${formName}.txtTarikhKeputusan.focus(); 
		return; 
	}
	
	
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

	if (document.${formName}.socKeputusan.value ==""){
		alert('Sila masukkan Keputusan.');
		return;
	}
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "view";
		return;
	}
	
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "doHantarProses";
	document.${formName}.submit();
}
function gotoBatalPermohonan(){	
	document.${formName}.step.value = "batalPermohonan";
	document.${formName}.submit();
}
function daftarRayuan() {
	document.${formName}.actionTukarguna.value = "daftarRayuan";
	document.${formName}.mode.value = "new";
	document.${formName}.submit();
}
function daftarEPU() {
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "view";
		return;
	}
	
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "daftarEPU";
	document.${formName}.submit();
}
function cetakTKRSuratTolak(idFail) {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmPLPPopupCetakLaporanView?idFail="+idFail+"&report=TKRSuratTolak";
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakTKRSuratLulus(idFail) {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmPLPPopupCetakLaporanView?idFail="+idFail+"&report=TKRSuratLulus";
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakTKRSuratLulusBersyarat(idFail) {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmPLPPopupCetakLaporanView?idFail="+idFail+"&report=TKRSuratLulusBersyarat";
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function setTable(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}
</script>
