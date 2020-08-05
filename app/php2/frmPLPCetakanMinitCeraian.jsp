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
  
  #if ($idFail != '' && $idStatus != '1610198' && $idStatus != '1610199' && $idStatus != '1610200' && $idStatus != '1610201' &&  $idStatus != '1610202' &&  $idStatus != '1610203')
  <tr>
    <td><table width="100%" border="0" cellspacing="2" cellpadding="2">
        #foreach ($beanKertasCeraian in $BeanKertasCeraian)
        <tr>
          <td colspan="2"><fieldset>
            <legend>MAKLUMAT MINIT CERAIAN</legend>
            <table width="100%" border="0" cellspacing="2" cellpadding="2">
              <tr>
                <td><table width="100%" border="0" cellspacing="2" cellpadding="2">
                  <tr>
                    <td width="29%">Kepada</td>
                    <td width="1%">:</td>
                    <td width="70%"><input name="txtKepada" type="text" class="$inputTextClass" id="txtKepada" value="$beanKertasCeraian.txtKepada" $readonly size="80"/></td>
                  </tr>
                  <tr>
                    <td>Melalui</td>
                    <td>:</td>
                    <td><input name="txtMelalui" type="text" class="$inputTextClass" id="txtMelalui" value="$beanKertasCeraian.txtMelalui" $readonly size="80"/></td>
                  </tr>
                  <tr>
                    <td>Daripada</td>
                    <td>:</td>
                    <td><input name="txtDaripada" type="text" class="$inputTextClass" id="txtDaripada" value="$beanKertasCeraian.txtDaripada" $readonly size="60"/></td>
                  </tr>
                  <tr>
                    <td>Yang Berhormat</td>
                    <td>:</td>
                    <td><input name="txtYangBerhormat" type="text" class="$inputTextClass" id="txtYangBerhormat" value="$beanKertasCeraian.txtYangBerhormat" $readonly size="60"/></td>
                  </tr>
                  <tr>
                    <td>Tarikh Hantar</td>
                    <td>:</td>
                    <td><input name="txtTarikhHantar" type="text" $readonly class="$inputTextClass" id="txtTarikhHantar" onBlur="check_date(this)" value="$beanKertasCeraian.txtTarikhHantar" size="9" maxlength="10">
      #if ($mode != 'view') <a href="javascript:displayDatePicker('txtTarikhHantar',false,'dmy');"><img border="0" src="../img/calendar.gif"/>#end</td>
                  </tr>
                </table></td>
              </tr>
            </table>
            </fieldset></td>
        </tr>
        <tr>
          <td colspan="2"><fieldset>
            <legend>TAJUK KERTAS</legend>
            <table width="100%" border="0" cellspacing="2" cellpadding="2">
              <tr>
                <td width="20%">&nbsp;</td>
                <td width="80%"><textarea name="txtTajukKertas" id="txtTajukKertas" cols="80" rows="5" $readonly class="$inputTextClass" onBlur="this.value=this.value.toUpperCase();">$beanKertasCeraian.txtTajukKertas</textarea>
                  <input name="idKertasKerja" type="hidden" id="idKertasKerja" value="$beanKertasCeraian.idKertasKerja"/></td>
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
                <td width="80%"><textarea name="txtTujuan" id="txtTujuan" cols="80" rows="5" $readonly class="$inputTextClass">$beanKertasCeraian.txtTujuan</textarea></td>
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
                <td width="80%"><textarea name="txtPerihalKemajuan" id="txtPerihalKemajuan" cols="80" rows="5" $readonly class="$inputTextClass">$beanKertasCeraian.txtPerihalKemajuan</textarea></td>
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
                <td width="80%"><textarea name="txtLaporanNilaian" id="txtLaporanNilaian" cols="80" rows="5" $readonly class="$inputTextClass">$beanKertasCeraian.txtLaporanNilaian</textarea></td>
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
                <td width="80%"><textarea name="txtUlasanKJP" id="txtUlasanKJP" cols="80" rows="5" $readonly class="$inputTextClass">$beanKertasCeraian.txtUlasanKJP</textarea></td>
              </tr>
            </table>
            </fieldset></td>
        </tr>
        <tr>
          <td colspan="2"><fieldset>
            <legend>ULASAN KEMENTERIAN KEWANGAN</legend>
            <table width="100%" border="0" cellspacing="2" cellpadding="2">
              <tr>
                <td width="20%">&nbsp;</td>
                <td width="80%"><textarea name="txtUlasanKewangan" id="txtUlasanKewangan" cols="80" rows="5" $readonly class="$inputTextClass">$beanKertasCeraian.txtUlasanKewangan</textarea></td>
              </tr>
            </table>
            </fieldset></td>
        </tr>
        <tr>
          <td colspan="2"><fieldset>
            <legend>PERAKUAN DAN SYOR PESURUHJAYA TANAH PERSEKUTUAN</legend>
            <table width="100%" border="0" cellspacing="2" cellpadding="2">
              <tr>
                <td width="20%">&nbsp;</td>
                <td width="80%"><textarea name="txtPerakuanPTP" id="txtPerakuanPTP" cols="80" rows="5" $readonly class="$inputTextClass">$beanKertasCeraian.txtPerakuanPTP</textarea></td>
              </tr>
            </table>
            </fieldset></td>
        </tr>
        <tr>
          <td colspan="2"><fieldset>
            <legend>PERAKUAN KETUA SETIAUSAHA KEMENTERIAN SUMBER ASLI DAN ALAM SEKITAR</legend>
            <table width="100%" border="0" cellspacing="2" cellpadding="2">
              <tr>
                <td width="20%">&nbsp;</td>
                <td width="80%"><textarea name="txtPerakuanKSU" id="txtPerakuanKSU" cols="80" rows="5" $readonly class="$inputTextClass">$beanKertasCeraian.txtPerakuanKSU</textarea></td>
              </tr>
            </table>
            </fieldset></td>
        </tr>
        <tr>
          <td colspan="2"><fieldset>
            <legend>KEPUTUSAN MENTERI SUMBER ASLI DAN ALAM SEKITAR</legend>
            <table width="100%" border="0" cellspacing="2" cellpadding="2">
              <tr>
                <td width="20%">&nbsp;</td>
                <td width="80%"><textarea name="txtKeputusanMenteri" id="txtKeputusanMenteri" cols="80" rows="5" $readonly class="$inputTextClass">$beanKertasCeraian.txtKeputusanMenteri</textarea></td>
              </tr>
            </table>
            </fieldset></td>
        </tr>
        #end
        <tr>
          <td colspan="2">&nbsp;</td>
        </tr>
        <tr align="center">
          <td width="20%">&nbsp;</td>
          <td width="80%"> 
            #if ($mode == 'view')
            #if ($!{session.getAttribute("FLAG_FROM")} == 'failTugasan' || $!{session.getAttribute("FLAG_FROM")} == 'failHQ')
            <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onClick="kemaskiniCeraian()"/>
            #if ($idStatus == '1610204')
            <input type="button" name="cmdHantar" id="cmdHantar" value="Seterusnya" onClick="doSeterusnya()"/>
            <input type="button" name="cmdBatalPermohonan" id="cmdBatalPermohonan" value="Batal Permohonan" onClick="gotoBatalPermohonan()"/>
            #end
            #end
            <input type="button" name="cdmCetakCadangan" id="cdmCetakCadangan" value="Cetak" onClick="javascript:setTable('tableReportMinitKewangan')"/>            
            #end
            #if ($!{session.getAttribute("FLAG_FROM")} == 'failKeseluruhan')
        	<input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="gotoSenaraiFailKeseluruhan()"/>
        	#end
            #if ($mode == 'update')
            <input type="button" name="cmdSimpanKemaskini" id="cmdSimpanKemaskini" value="Simpan" onclick="simpanKemaskiniCeraian()"/>
            <input type="button" name="cmdBatalKemaskini" id="cmdBatalKemaskini" value="Batal" onClick="batalCeraian()"/>
            #end 
          </td>
        </tr>
    </table></td>
  </tr>
  #end
</table>
<fieldset id="tableReportMinitKewangan" style="display:none;"-->
<legend><strong>SENARAI LAPORAN</strong></legend>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td ><a href="#" class="style2" onClick="javascript:cetakMinitCeraian('$idFail')">Minit Ceraian</a></td>
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
function kemaskiniCeraian() {
	document.${formName}.mode.value = "update";
	doAjaxCall${formName}("");	
}
function simpanKemaskiniCeraian(){

	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "update";
		return;
	}
	
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "simpanKemaskiniCeraian";
	document.${formName}.submit();
}
function batalCeraian(){
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
function cetakMinitCeraian(idFail) {
	var url = "../servlet/ekptg.report.php2.PLPMinitCeraian?ID_FAIL="+idFail;
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
