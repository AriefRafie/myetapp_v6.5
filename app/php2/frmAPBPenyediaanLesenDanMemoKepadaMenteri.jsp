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
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
<input name="idFail" type="hidden" id="idFail" value="$idFail"/>
<input name="idPermohonan" type="hidden" id="idPermohonan" value="$idPermohonan"/>
<input name="mode" type="hidden" id="mode" value="$mode"/>
<input name="hitButton" type="hidden" id="hitButton"/>
<style type="text/css">
<!--
.style1 {color: #FF0000}
-->
</style>
<!-- saiz text -->
<table width="100%" border="0" cellpadding="2" cellspacing="2">
  #if ($idFail != '')
  <tr>
    <td> #parse("app/php2/frmAPBHeader.jsp") </td>
  </tr>
  #else
  <tr>
    <td>&nbsp;
      <div class="warning">SILA PILIH FAIL DI SENARAI FAIL TERLEBIH DAHULU</div></td>
  </tr>
  #end
  
  #if ($idFail != '' && $flagOpenDetail)
  <tr>
    <td><fieldset>
      <legend><strong>MAKLUMAT PENYEDIAAN LESEN</strong></legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        #foreach ($beanMaklumatPenyediaanLesenDanMemo in $BeanMaklumatPenyediaanLesenDanMemo)
        <tr>
          <td valign="top">#if ($mode != 'view')<span class="style1">*</span>#end</td>
          <td width="28%" valign="top">Tempoh Yang Diluluskan</td>
          <td width="1%" valign="top">:</td>
          <!-- <td width="70%" valign="top">$beanMaklumatPenyediaanLesenDanMemo.tempohDipohon #if($beanMaklumatPenyediaanLesenDanMemo.idTempoh=='1') Tahun #else Bulan #end</td> -->
          <td><input name="txtTempohDiluluskan" type="text" size="1" maxlength="2" onkeyup="validateNumber(this,this.value);" value="$beanMaklumatPenyediaanLesenDanMemo.tempohDiluluskan" $readonly class="$inputTextClass"/>
            $selectTempoh</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Luas Kawasan</td>
          <td>: </td>
          <td>$beanMaklumatPenyediaanLesenDanMemo.luas $beanMaklumatPenyediaanLesenDanMemo.jenisLuas</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Fee Lesen</td>
          <td>:</td>
          <td>RM $beanMaklumatPenyediaanLesenDanMemo.kadarFeeLesen bagi setiap
            $beanMaklumatPenyediaanLesenDanMemo.kmPersegi
            km persegi</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Jumlah Fee Lesen</td>
          <td>:</td>
          <td>RM
            $beanMaklumatPenyediaanLesenDanMemo.jumlahFeeLesen </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Isipadu Dibenarkan
          <td>:</td>
          <td>RM $beanMaklumatPenyediaanLesenDanMemo.isipadu meter padu</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Kadar Royalti Pasir</td>
          <td>:</td>
          <td>RM
            $beanMaklumatPenyediaanLesenDanMemo.kadarRoyaltiPasir / meter padu</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Jumlah Royalti Keseluruhan</td>
          <td>:</td>
          <td>RM $beanMaklumatPenyediaanLesenDanMemo.jumlahRoyaltiSeluruh</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Jumlah Pendahuluan Royalti</td>
          <td>:</td>
          <td>RM          $beanMaklumatPenyediaanLesenDanMemo.jumDahuluRoyalti</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Luar Perairan Negeri</td>
          <td>: </td>
          <td>#if($beanMaklumatPenyediaanLesenDanMemo.flagLuar=='1') Ya #else Tidak #end</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Negeri</td>
          <td>: </td>
          <td>$beanMaklumatPenyediaanLesenDanMemo.namaNegeri</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Lokasi</td>
          <td>: </td>
          <td>$beanMaklumatPenyediaanLesenDanMemo.lokasi</td>
        </tr>
        #if ($mode != 'view')
        <tr>
          <td colspan="4" valign="bottom"><i><font color="#ff0000">Perhatian</font> : Pastikan label bertanda <font color="#ff0000">*</font> diisi.</i></td>
        </tr>
        #end
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>#if ($mode == 'view')
            <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onClick="doKemaskini()"/>
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onClick="javascript:setTable('tableReport')"/>
            #if($idStatus =='1610238')
            <input type="button" name="cmdSeterusnya" id="cmdHantar" value="Seterusnya" onClick="doSeterusnya()"/>
            <input type="button" name="cmdBatalPermohonan" id="cmdBatalPermohonan" value="Batal Permohonan" onClick="gotoBatalPermohonan()"/>
            #end       
            #end
            #if ($mode == 'update')
            <input type="button" name="cmdSimpanKemaskini" id="cmdSimpanKemaskini" value="Simpan" onClick="doSimpanKemaskiniPenyediaanLesenDanMemo()"/>
            <input type="button" name="cmdBatalKemaskini" id="cmdBatalKemaskini" value="Batal" onClick="doBatalKemaskini()"/>
            #end </td>
        </tr>
        #end
      </table>
      </fieldset></td>
  </tr>
  #elseif ($idFail != '')
  <tr>
    <td> 
      <div class="warning"><strong>STATUS FAIL MASIH DI PERINGKAT $!status</strong></div></td>
  </tr>
  #end
</table>
<fieldset id="tableReport" style="display:none;"-->
<legend><strong>SENARAI DOKUMEN</strong></legend>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td ><a href="#" class="style2" onClick="javascript:cetakMemo('$idFail')"> Cetak Memo </a></td>
  </tr>
  <tr>
    <td ><a href="#" class="style2" onClick="javascript:cetakMemo('$idFail')"> Cetak Lesen </a></td>
  </tr>
</table>
</fieldset>
<script>
function doKemaskini() {
	/* alert("1"); */
	document.${formName}.mode.value = "update";
	/* alert("2"); */
	doAjaxCall${formName}("doKemaskini");
	/* alert("3"); */
}
function doBatalKemaskini() {
	document.${formName}.mode.value = "view";
	doAjaxCall${formName}("");
}
function doSimpanKemaskiniPenyediaanLesenDanMemo() {
	if(document.${formName}.txtTempohDiluluskan.value == ""){
		alert('Sila masukkan Tempoh');
  		document.${formName}.txtTempohDiluluskan.focus(); 
		return; 
	}
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "update";
		return;
	}
	
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "doSimpanKemaskiniPenyediaanLesenDanMemo";
	document.${formName}.submit();
}
function doSeterusnya(){
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "view";
		return;
	}

	document.${formName}.hitButton.value = "doSeterusnya";
	document.${formName}.submit();
}
function setTable(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}
function cetakMemo(idFail) {
	var url = "..//servlet/ekptg.report.php2.APBMemoMenteri?ID_FAIL="+idFail;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
</script>
<input name="step" type="hidden" id="step"/>
<script>
function gotoSelesaiPermohonan(){	
	document.${formName}.step.value = "selesaiPermohonan";
	document.${formName}.submit();
}
function gotoBatalPermohonan(){	
	document.${formName}.step.value = "batalPermohonan";
	document.${formName}.submit();
}
</script>
