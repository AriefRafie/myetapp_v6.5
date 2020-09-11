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
  <input name="idFail" type="hidden" id="idFail" value="$idFail"/>
  <input name="idPermohonan" type="hidden" id="idPermohonan" value="$idPermohonan"/>
  <input name="idStatus" type="hidden" id="idStatus" value="$idStatus"/>
  <input name="mode" type="hidden" id="mode" value="$mode"/>
  <input name="hitButton" type="hidden" id="hitButton"/>
    
  <input name="step" type="hidden" id="step" value="$step"/>
</p>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #if ($idFail != '')
  <tr>
    <td> #parse("app/php2/frmPLPHeader.jsp") </td>
  </tr>
  #elseif ($idFail == '' )
  <tr>
    <td>&nbsp;
      <div class="warning">SILA PILIH FAIL DI SENARAI FAIL TERLEBIH DAHULU</div></td>
  </tr>
  #end
  
  #if ($idFail != '' && $idStatus != '1610198' && $idStatus != '1610199' && $idStatus != '1610200' && $idStatus != '1610201')
  <tr>
    <td><table width="100%" border="0" cellspacing="2" cellpadding="2">
        #foreach ($beanKertasKewangan in $BeanKertasKewangan)
        <tr>
          <td colspan="2"><fieldset>
            <legend>TAJUK KERTAS</legend>
            <table width="100%" border="0" cellspacing="2" cellpadding="2">
              <tr>
                <td width="20%">&nbsp;</td>
                <td width="80%"><textarea name="txtTajukKertas" id="txtTajukKertas" cols="80" rows="5" $readonly class="$inputTextClass" onBlur="this.value=this.value.toUpperCase();">$beanKertasKewangan.txtTajukKertas</textarea>
                  <input name="idKertasKerja" type="hidden" id="idKertasKerja" value="$beanKertasKewangan.idKertasKerja"/></td>
              </tr>
            </table>
            </fieldset></td>
        </tr>
        <tr>
          <td colspan="2"><fieldset>
            <legend>TUJUAN</legend>
            <table width="100%" border="0" cellspacing="2" cellpadding="2">
              <tr>
                <td width="20%">&nbsp;</td>
                <td width="80%"><textarea name="txtTujuan" id="txtTujuan" cols="80" rows="5" $readonly class="$inputTextClass">$beanKertasKewangan.txtTujuan</textarea></td>
              </tr>
            </table>
            </fieldset></td>
        </tr>
        <tr>
          <td colspan="2"><fieldset>
            <legend>PERIHAL KEMAJUAN ATAS TANAH</legend>
            <table width="100%" border="0" cellspacing="2" cellpadding="2">
              <tr>
                <td width="20%">&nbsp;</td>
                <td width="80%"><textarea name="txtPerihalKemajuan" id="txtPerihalKemajuan" cols="80" rows="5" $readonly class="$inputTextClass">$beanKertasKewangan.txtPerihalKemajuan</textarea></td>
              </tr>
            </table>
            </fieldset></td>
        </tr>
        <tr>
          <td colspan="2"><fieldset>
            <legend>PIHAK YANG MEMOHON PENYERAHAN BALIK</legend>
            <table width="100%" border="0" cellspacing="2" cellpadding="2">
              <tr>
                <td width="20%">&nbsp;</td>
                <td width="80%"><textarea name="txtPemohon" id="txtPemohon" cols="80" rows="5" $readonly class="$inputTextClass">$beanKertasKewangan.txtPemohon</textarea></td>
              </tr>
            </table>
            </fieldset></td>
        </tr>
        <tr>
          <td colspan="2"><fieldset>
            <legend>LAPORAN PENILAIAN TANAH</legend>
            <table width="100%" border="0" cellspacing="2" cellpadding="2">
              <tr>
                <td width="20%">&nbsp;</td>
                <td width="80%"><textarea name="txtLaporanNilaian" id="txtLaporanNilaian" cols="80" rows="5" $readonly class="$inputTextClass">$beanKertasKewangan.txtLaporanNilaian</textarea></td>
              </tr>
            </table>
            </fieldset></td>
        </tr>
        <tr>
          <td colspan="2"><fieldset>
            <legend>ULASAN KEMENTERIAN / JABATAN PENGGUNA</legend>
            <table width="100%" border="0" cellspacing="2" cellpadding="2">
              <tr>
                <td width="20%">&nbsp;</td>
                <td width="80%"><textarea name="txtUlasanKJP" id="txtUlasanKJP" cols="80" rows="5" $readonly class="$inputTextClass">$beanKertasKewangan.txtUlasanKJP</textarea></td>
              </tr>
            </table>
            </fieldset></td>
        </tr>
        <tr>
          <td colspan="2"><fieldset>
            <legend>PERAKUAN PESURUHJAYA TANAH PERSEKUTUAN</legend>
            <table width="100%" border="0" cellspacing="2" cellpadding="2">
              <tr>
                <td width="20%">&nbsp;</td>
                <td width="80%"><textarea name="txtPerakuanPTP" id="txtPerakuanPTP" cols="80" rows="5" $readonly class="$inputTextClass">$beanKertasKewangan.txtPerakuanPTP</textarea></td>
              </tr>
            </table>
            </fieldset></td>
        </tr>
        <tr>
          <td colspan="2"><fieldset>
            <legend>MAKLUMAT KERTAS KEWANGAN</legend>
            <table width="100%" border="0" cellspacing="2" cellpadding="2">
              <tr>
                <td width="20%">Tarikh Hantar</td>
                <td width="80%">: <input name="txtTarikhHantar" type="text" $readonly class="$inputTextClass" id="txtTarikhHantar" onBlur="check_date(this)" value="$beanKertasKewangan.txtTarikhHantar" size="9" maxlength="10">
      #if ($mode != 'view') <a href="javascript:displayDatePicker('txtTarikhHantar',false,'dmy');"><img border="0" src="../img/calendar.gif"/>#end</td>
              </tr>
            </table>
            </fieldset></td>
        </tr>
        #end
        <tr>
          <td colspan="2">&nbsp;</td>
          <td colspan="2">&nbsp;</td>
        </tr>
        <tr align="center">
          <td width="20%">&nbsp;</td>
          <td width="80%"> 
            #if ($mode == 'view')
            #if ($!{session.getAttribute("FLAG_FROM")} == 'failTugasan' || $!{session.getAttribute("FLAG_FROM")} == 'failHQ')
            <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onClick="kemaskiniKewangan()"/>
            #if ($idStatus == '1610202')
            <input type="button" name="cmdHantar" id="cmdHantar" value="Seterusnya" onClick="doSeterusnya()"/>
            <input type="button" name="cmdBatalPermohonan" id="cmdBatalPermohonan" value="Batal Permohonan" onClick="gotoBatalPermohonan()"/>
            #end
            #end
            #end
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onClick="javascript:setTable('tableReportKertasKewangan')"/>
            #if ($!{session.getAttribute("FLAG_FROM")} == 'failKeseluruhan')
        	<input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="gotoSenaraiFailKeseluruhan()"/>
        	#end
            #if ($mode == 'update')
            <input type="button" name="cmdSimpanKemaskini" id="cmdSimpanKemaskini" value="Simpan" onclick="simpanKemaskiniKewangan()"/>
            <input type="button" name="cmdBatalKemaskini" id="cmdBatalKemaskini" value="Batal" onClick="batalKewangan()"/>
            #end 
          </td>
        </tr>
    </table></td>
  </tr>
  #end
</table>
<fieldset id="tableReportKertasKewangan" style="display:none;"-->
<legend><strong>SENARAI LAPORAN</strong></legend>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
	<tr>
    <td ><a href="#" class="style2" onClick="javascript:cetakSuratIringanKewangan('$idFail')">Surat Iringan</a></td>
  </tr>
  <tr>
    <td ><a href="#" class="style2" onClick="javascript:cetakKertasKewangan('$idFail')">Kertas Cadangan</a></td>
  </tr>
</table>
</fieldset>

<script>
function setTable(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}
function kemaskiniKewangan() {
	document.${formName}.mode.value = "update";
	doAjaxCall${formName}("");	
}
function simpanKemaskiniKewangan(){

	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "update";
		return;
	}
	
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "simpanKemaskiniKewangan";
	document.${formName}.submit();
}
function batalKewangan(){
	document.${formName}.mode.value = "view";
	doAjaxCall${formName}("");
}
function doSeterusnya(){
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.hitButton.value = "doSeterusnya";
	document.${formName}.submit();
}
function gotoBatalPermohonan(){	
	document.${formName}.step.value = "batalPermohonan";
	document.${formName}.submit();
}
function cetakKertasKewangan(idFail) {
	var url = "../servlet/ekptg.report.php2.PLPKertasKewangan?ID_FAIL="+idFail;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakSuratIringanKewangan(idFail) {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmPLPPopupCetakLaporanView?idFail="+idFail+"&report=suratIringanKewangan";
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function gotoSenaraiFailKeseluruhan() {
	document.${formName}.action = "$EkptgUtil.getTabID("My Info",$portal_role)?_portal_module=ekptg.view.php2.FrmPLPSenaraiFailKeseluruhanView";
	document.${formName}.submit();
}
</script>
