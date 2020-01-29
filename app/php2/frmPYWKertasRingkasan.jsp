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
  <input name="idSuburusan" type="hidden" id="idSuburusan" value="$idSuburusan"/>
  <input name="mode" type="hidden" id="mode" value="$mode"/>
  <input name="hitButton" type="hidden" id="hitButton"/>
  <input name="flagPermohonanDari" type="hidden" id="flagPermohonanDari" value="$flagPermohonanDari"/>
  <input name="step" type="hidden" id="step" value="$step"/>
  <input name="idLaporanTanah" type="hidden" id="idLaporanTanah" value="$idLaporanTanah"/>
</p>
<body onLoad = $onload >
<table width="100%" border="0" cellpadding="2" cellspacing="2">
  #if ($idFail != '' && $idStatus != '1610198' && $idStatus != '1610199' && $idStatus != '1610200')
  <tr>
    <td> #parse("app/php2/frmPYWHeader.jsp") </td>
  </tr>
  #elseif ($idFail == '' )
  <tr>
    <td>&nbsp;
      <div class="warning">SILA PILIH FAIL DI SENARAI TUGASAN TERLEBIH DAHULU</div></td>
  </tr>
  #else
  #foreach($beanHeader in $BeanHeader)
  <tr>
    <td>&nbsp;
      <div class="warning">FAIL INI MASIH DI STATUS <strong>$beanHeader.status</strong></div></td>
  </tr>
  #end
  #end

  #if ($idFail != '' && $idStatus != '1610198' && $idStatus != '1610199' && $idStatus != '1610200')
  <tr>
    <td><table width="100%" border="0" cellspacing="2" cellpadding="2">
        #foreach ($beanKertasRingkasan in $BeanKertasRingkasan)
        <tr>
          <td colspan="2"><fieldset>
            <legend><strong>ULASAN KEMENTERIAN / JABATAN PENGGUNA</strong></legend>
            <table width="100%" border="0" cellspacing="2" cellpadding="2">
              <tr>
                <td width="20%">&nbsp;</td>
                <td width="80%"><textarea name="txtUlasanKJP" id="txtUlasanKJP" cols="80" rows="5" $readonly class="$inputTextClass" >$beanKertasRingkasan.ulasanKJP</textarea>
                  <input name="idKertasKerja" type="hidden" id="idKertasKerja" value="$beanKertasRingkasan.idKertasKerja"/></td>
              </tr>
            </table>
            </fieldset></td>
        </tr>
        <tr>
          <td colspan="2"><fieldset>
            <legend><strong>LAPORAN PENILAIAN JPPH</strong></legend>
            <table width="100%" border="0" cellspacing="2" cellpadding="2">
              <tr>
                <td width="20%">&nbsp;</td>
                <td width="80%"><textarea name="txtUlasanJPPH" id="txtUlasanJPPH" cols="80" rows="5" $readonly class="$inputTextClass">$beanKertasRingkasan.ulasanJPPH</textarea></td>
              </tr>
            </table>
            </fieldset></td>
        </tr>
        <tr>
          <td colspan="2"><fieldset>
            <legend><strong>LAPORAN TANAH DARIPADA JKPTGN</strong></legend>
            <table width="100%" border="0" cellspacing="2" cellpadding="2">
              <tr>
                <td width="20%">&nbsp;</td>
                <td width="80%"><textarea name="txtUlasanJKPTGN" id="txtUlasanJKPTGN" cols="80" rows="5" $readonly class="$inputTextClass">$beanKertasRingkasan.ulasanJKPTGN</textarea></td>
              </tr>
            </table>
            </fieldset></td>
        </tr>
        #if ($flagPermohonanDari == '0')
        <tr>
          <td colspan="2"><fieldset>
            <legend><strong>ULASAN KEMENTERIAN KEWANGAN MALAYSIA</strong></legend>
            <table width="100%" border="0" cellspacing="2" cellpadding="2">
              <tr>
                <td width="20%">&nbsp;</td>
                <td width="80%"><textarea name="txtUlasanKemKewangan" id="txtUlasanKemKewangan" cols="80" rows="5" $readonly class="$inputTextClass">$beanKertasRingkasan.ulasanKemKewangan</textarea></td>
              </tr>
            </table>
            </fieldset></td>
        </tr>
        <tr>
          <td colspan="2"><fieldset>
            <legend><strong>ULASAN KEMENTERIAN WILAYAH PERSEKUTUAN</strong></legend>
            <table width="100%" border="0" cellspacing="2" cellpadding="2">
              <tr>
                <td width="20%">&nbsp;</td>
                <td width="80%"><textarea name="txtUlasanKemWP" id="txtUlasanKemWP" cols="80" rows="5" $readonly class="$inputTextClass">$beanKertasRingkasan.ulasanKemWP</textarea></td>
              </tr>
            </table>
            </fieldset></td>
        </tr>
        <tr>
          <td colspan="2"><fieldset>
            <legend><strong>ULASAN PEJABAT TANAH DAN GALIAN KUALA LUMPUR</strong></legend>
            <table width="100%" border="0" cellspacing="2" cellpadding="2">
              <tr>
                <td width="20%">&nbsp;</td>
                <td width="80%"><textarea name="txtUlasanPTG" id="txtUlasanPTG" cols="80" rows="5" $readonly class="$inputTextClass">$beanKertasRingkasan.ulasanPTG</textarea></td>
              </tr>
            </table>
            </fieldset></td>
        </tr>
        <tr>
          <td colspan="2"><fieldset>
            <legend><strong>ULASAN DEWAN BANDARAYA KUALA LUMPUR</strong></legend>
            <table width="100%" border="0" cellspacing="2" cellpadding="2">
              <tr>
                <td width="20%">&nbsp;</td>
                <td width="80%"><textarea name="txtUlasanDBKL" id="txtUlasanDBKL" cols="80" rows="5" $readonly class="$inputTextClass">$beanKertasRingkasan.ulasanDBKL</textarea></td>
              </tr>
            </table>
            </fieldset></td>
        </tr>
        <tr>
          <td colspan="2"><fieldset>
            <legend><strong>ULASAN BAHAGIAN PENGURUSAN HARTANAH,JPM</strong></legend>
            <table width="100%" border="0" cellspacing="2" cellpadding="2">
              <tr>
                <td width="20%">&nbsp;</td>
                <td width="80%"><textarea name="txtUlasanBPH" id="txtUlasanBPH" cols="80" rows="5" $readonly class="$inputTextClass">$beanKertasRingkasan.ulasanBPH</textarea></td>
              </tr>
            </table>
            </fieldset></td>
        </tr>
        #end
        <tr>
          <td colspan="2"><fieldset>
            <legend><strong>SEMAKAN DENGAN BAHAGIAN HARTA TANAH PERSEKUTUAN</strong></legend>
            <table width="100%" border="0" cellspacing="2" cellpadding="2">
              <tr>
                <td width="20%">Pajakan</td>
                <td width="80%">:
                  <select name="socPajakan" id="socPajakan" style="width:140px;" $readonly class="$disabled" $disabled>
                    
            	  #if ($beanKertasRingkasan.flagPajakan == 'Y')
            		
                    <option value="">SILA PILIH</option>
                    <option value="Y" selected="selected">YA</option>
                    <option value="T">TIADA</option>
                    
                  #elseif ($beanKertasRingkasan.flagPajakan == 'T')
                	
                    <option value="">SILA PILIH</option>
                    <option value="Y">YA</option>
                    <option value="T" selected="selected">TIADA</option>
                    
                  #else
                	
                    <option value="" selected="selected">SILA PILIH</option>
                    <option value="Y">YA</option>
                    <option value="T">TIADA</option>
                    
                  #end
            	
                  </select>                </td>
              </tr>
              <tr>
                <td width="20%">Penswastaan</td>
                <td width="80%">:
                  <select name="socPenswastaan" id="socPenswastaan" style="width:140px;" $readonly class="$disabled" $disabled>
                    
            	  #if ($beanKertasRingkasan.flagPenswastaan == 'Y')
            		
                    <option value="">SILA PILIH</option>
                    <option value="Y" selected="selected">YA</option>
                    <option value="T">TIADA</option>
                    
                  #elseif ($beanKertasRingkasan.flagPenswastaan == 'T')
                	
                    <option value="">SILA PILIH</option>
                    <option value="Y">YA</option>
                    <option value="T" selected="selected">TIADA</option>
                    
                  #else
                	
                    <option value="" selected="selected">SILA PILIH</option>
                    <option value="Y">YA</option>
                    <option value="T">TIADA</option>
                    
                  #end
            	
                  </select>                </td>
              </tr>
              <tr>
                <td width="20%">Nama Pegawai</td>
                <td width="80%">:
                  <input name="txtNamaPegawai" type="text" class="$inputTextClass" id="txtNamaPegawai" onBlur="this.value=this.value.toUpperCase();" value="$beanKertasRingkasan.namaPegawai" size="40" maxlength="80" $readonly/></td>
              </tr>
              <tr>
                <td width="20%">Tarikh Semakan</td>
                <td width="80%">:
                  <input name="txtTarikhRujukan" type="text" $readonly class="$inputTextClass" id="txtTarikhRujukan" onBlur="check_date(this)" value="$beanKertasRingkasan.tarikhRujukan" size="9" maxlength="10">
                  #if ($mode != 'view') <a href="javascript:displayDatePicker('txtTarikhRujukan',false,'dmy');"><img border="0" src="../img/calendar.gif"/>#end</td>
              </tr>
            </table>
            </fieldset></td>
        </tr>
        #end
        <tr>
          <td colspan="2">&nbsp;</td>
        </tr>
        <tr>
          <td width="20%">&nbsp;</td>
          <td width="80%"> 
          	#if ($mode == 'view')
            	#if ($!{session.getAttribute("FLAG_FROM")} == 'failTugasan' || $!{session.getAttribute("FLAG_FROM")} == 'failHQ')
            		<input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onClick="kemaskiniRingkasan()"/>
            	#end
            	<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onClick="javascript:setTable('tableReportKertasRingkasan')"/>
            	#if ($!{session.getAttribute("FLAG_FROM")} == 'failTugasan')
            		#if ($idStatus == '1610213')
            		<!-- BUTTON ASSIGN TUGASAN-->
            			#if ($userRole == '(PHP)PYWPenolongPegawaiTanahNegeri')
            				#if ($flagMT == 'Y')
            					<input type="button" name="cmdKembaliFail" id="cmdKembaliFail" value="Kembalikan Fail" onClick="gotoKembaliFail()"/>
            				#else
            					<input type="button" name="cmdHantarSemakan" id="cmdHantarSemakan" value="Hantar Untuk Semakan" onClick="gotoSemakanPPNegeri()"/>
            				#end
            			#end            
            			#if ($userRole == '(PHP)PYWPenolongPengarahNegeri')
            				<input type="button" name="cmdHantarSemakan" id="cmdHantarSemakan" value="Hantar Untuk Semakan" onClick="gotoSemakanPNegeri()"/>
                            <input type="button" name="cmdHantarPindaan" id="cmdHantarPindaan" value="Kembalikan Fail Untuk Pindaan" onClick="gotoPindaanMaklumat()"/>
            			#end
            			#if ($userRole == '(PHP)PYWPengarahNegeri')
            				<input type="button" name="cmdHantar" id="cmdHantar" value="Hantar Ke IbuPejabat" onClick="gotoHantarHQ()"/>
            			#end
            			#if ($userRole == '(PHP)PYWPengarahHQ')
            				<input type="button" name="cmdHantarTugasan2" id="cmdHantarTugasan2" value="Hantar Kepada Penolong Pengarah" onClick="gotoHantarTugasanPP()"/>
            			#end
            			#if ($userRole == '(PHP)PYWPenolongPengarahHQ')
            				<input type="button" name="cmdHantar" id="cmdHantar" value="Ke Mesyuarat" onClick="doSeterusnya()"/ >
            				<input type="button" name="cmdHantarTugasan" id="cmdHantarTugasan" value="Agihan Kepada PPT Utuk Semakan" onClick="gotoHantarTugasanPPT()"/>
            			#end
            			#if ($userRole == '(PHP)PYWPenolongPegawaiTanahHQ')
            				<input type="button" name="cmdHantarSemakan" id="cmdHantarSemakan" value="Kembalikan Kepada Penolong Pengarah" onClick="gotoSemakanPPHQ()"/>
            				<input type="button" name="cmdHantarSemakan" id="cmdHantarSemakan" value="Minta Maklumat Tambahan" onClick="gotoMaklumatTambahan()"/>
            			#end
            			#if ($mode == 'view')
                        <input type="button" name="cmdBatalPermohonan" id="cmdBatalPermohonan" value="Batal Permohonan" onClick="gotoBatalPermohonan()"/>
                        #end
            		#end            
            	#end
            #end
            #if ($!{session.getAttribute("FLAG_FROM")} == 'failHQ')
            #if ($idStatus == '1610213')
            <input type="button" name="cmdHantar" id="cmdHantar" value="Ke Mesyuarat" onClick="doSeterusnya()"/ >            
            #if ($mode == 'view')
            <input type="button" name="cmdBatalPermohonan" id="cmdBatalPermohonan" value="Batal Permohonan" onClick="gotoBatalPermohonan()"/>
            #end
            #end
            #end
            #if ($mode == 'update')
            <input type="button" name="cmdSimpanKemaskini" id="cmdSimpanKemaskini" value="Simpan" onClick="simpanKemaskiniRingkasan()"/>
            <input type="button" name="cmdBatalKemaskini" id="cmdBatalKemaskini" value="Batal" onClick="batalRingkasan()"/>
            #end
            #if ($!{session.getAttribute("FLAG_FROM")} == 'failKeseluruhan')
            <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="gotoSenaraiFailKeseluruhan()"/>
            #end
           </td>
        </tr>
    </table></td>
  </tr>
  #end
</table>
<fieldset id="tableReportKertasRingkasan" style="display:none;"-->
<legend><strong>SENARAI LAPORAN</strong></legend>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td ><a href="#" class="style2" onClick="javascript:cetakPYWKertasRingkasan('$idFail')">Kertas Ringkasan</a></td>
  </tr>
  <tr>
    <td ><a href="#" class="style2" onClick="javascript:cetakPYWLampiranA('$idLaporanTanah')"> Lampiran A </a></td>
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
function kemaskiniRingkasan() {
	document.${formName}.mode.value = "update";
	doAjaxCall${formName}("");	
}
function simpanKemaskiniRingkasan(){

	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "update";
		return;
	}
	
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "simpanKemaskiniRingkasan";
	document.${formName}.submit();
}
function batalRingkasan(){
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
function cetakPYWKertasRingkasan(idFail) {
	var url = "../servlet/ekptg.report.php2.PYWKertasRingkasan?ID_FAIL="+idFail;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakPYWLampiranA(idLaporanTanah) {
	var url = "../servlet/ekptg.report.php2.PYWLampiranA?ID_LAPORANTANAH="+idLaporanTanah;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}

function gotoSemakanPPNegeri(){

	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.hitButton.value = "gotoSemakanPPNegeri";
	document.${formName}.submit();
}

function gotoSemakanPNegeri(){

	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.hitButton.value = "gotoSemakanPNegeri";
	document.${formName}.submit();
}
function gotoHantarHQ(){

	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.hitButton.value = "gotoHantarHQ";
	document.${formName}.submit();
}
function gotoHantarTugasanPP(){

	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.hitButton.value = "gotoHantarTugasanPP";
	document.${formName}.submit();
}
function gotoHantarTugasanPPT(){	
	document.${formName}.step.value = "gotoHantarTugasanPPT";
	document.${formName}.submit();
}
function gotoSemakanPPHQ(){

	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.hitButton.value = "gotoSemakanPPHQ";
	document.${formName}.submit();
}
function gotoMaklumatTambahan(){	
	document.${formName}.step.value = "gotoMaklumatTambahan";
	document.${formName}.submit();
}
function gotoPindaanMaklumat(){	
	document.${formName}.step.value = "gotoPindaanMaklumat";
	document.${formName}.submit();
}

function gotoKembaliFail(){

	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.hitButton.value = "gotoKembaliFail";
	document.${formName}.submit();
}

function gotoSenaraiFail(){
	document.${formName}.action = "$EkptgUtil.getTabID("Penyewaan",$portal_role)?_portal_module=ekptg.view.php2.FrmPYWSenaraiFailView";
	document.${formName}.submit();
}

function gotoSenaraiFailKeseluruhan() {
	document.${formName}.action = "$EkptgUtil.getTabID("'My Info'",$portal_role)?_portal_module=ekptg.view.php2.FrmPYWSenaraiFailKeseluruhanView";
	document.${formName}.submit();
}
</script>
