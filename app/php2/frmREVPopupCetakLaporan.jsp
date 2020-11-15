<script type="text/javascript" src="../../library/js/report.js" ></script>
<style type="text/css">
<!--
.style1 {
	color: #FF0000
}
#parse("css/eTapp_PHP.css")
-->
</style>
<p>
  <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
  <input name="report" type="hidden" id="report" value="$!report"/>
  <input name="idFail" type="hidden" id="idFail" value="$!idFail"/>
  <input name="idJadualPertama" type="hidden" id="idJadualPertama" value="$!idJadualPertama"/>
  <input name="idHasil" type="hidden" id="idHasil" value="$!idHasil"/>
  <input name="idAkaun" type="hidden" id="idAkaun" value="$!idAkaun"/>
  <input name="idNotis" type="hidden" id="idNotis" value="$!idNotis"/>
  <input type="hidden" id="rFormat" name="rFormat" value='$!{session.getAttribute("rFormat")}'>
</p>
<table width="100%" cellspacing="2" cellpadding="2" border="0">
  <tr>
    <td><fieldset>
      <legend><strong>
      #if($!report == 'BorangPenyerahanSewa' || $!report == 'BorangPenyerahanSewaPenyewa' )
      CETAKAN BORANG PENYERAHAN SEWA
      #elseif($!report == 'BorangPenyerahanAPB' || $!report == 'BorangPenyerahanAPBPelesen' )
      CETAKAN BORANG PENYERAHAN APB
      #elseif($!report == 'senaraiTerimaanCek')
      CETAKAN SENARAI TERIMAAN CEK
      #elseif($!report == 'SuratPemulanganSemula')
      CETAKAN SURAT PEMULANGAN SEMULA
      #elseif($!report == 'SuratTuntutanTunggakanSewa')
      CETAKAN SURAT TUNTUTAN TUNGGAKAN SEWA
      #elseif($!report == 'SuratRampasanDeposit')
      CETAKAN SURAT RAMPASAN DEPOSIT
      #elseif($!report == 'suratIringanResit')
      CETAKAN SURAT IRINGAN RESIT
      #elseif($!report == 'suratTuntutanDeposit')
      CETAKAN SURAT TUNTUTAN DEPOSIT
      #elseif($!report == 'SuratTuntutan')
      CETAKAN SURAT TUNTUTAN
      #elseif($!report == 'BorangDaftarMelSewa')
      CETAKAN DAFTAR MEL
      #elseif($!report == 'BorangDaftarMelAPB')
      CETAKAN DAFTAR MEL
      #elseif($!report == 'suratKuiriCek')
      CETAKAN SURAT KUIRI CEK

      #end </strong></legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="1%">&nbsp;</td>
          <td width="28%">&nbsp;</td>
          <td width="1%">&nbsp;</td>
          <td width="70%">&nbsp;</td>
        </tr>
        #if($!report == 'BorangDaftarMelAPB' || $!report == 'BorangPenyerahanSewa' || $!report == 'BorangPenyerahanAPB' || $!report == 'BorangDaftarMelSewa' || $!report == 'BorangPenyerahanSewaPenyewa' || $!report == 'BorangPenyerahanAPBPelesen' || $!report == 'senaraiTerimaanCek')
        <tr>
          <td ><span class="style1">*</span></td>
          <td >Tarikh Terima</td>
          <td >:</td>
          <td ><input type="text" name="txdTarikhMula" id="txdTarikhMula" value="$!txdTarikhMula" onblur="check_date(this)" size="9"/>
            <a href="javascript:displayDatePicker('txdTarikhMula',false,'dmy');"><img border="0" src="../../img/calendar.gif"/></a> &nbsp;&nbsp;Hingga &nbsp;&nbsp;
            <input type="text" name="txdTarikhTamat" id="txdTarikhTamat" value="$!txdTarikhTamat" onblur="check_date(this)" size="9"/>
            <a href="javascript:displayDatePicker('txdTarikhTamat',false,'dmy');"><img border="0" src="../../img/calendar.gif"/></a></td>
        </tr>
        #end
        <!--#if($!report == 'SuratRampasanDeposit')
        <tr>
          <td ><span class="style1">*</span></td>
          <td >No. Rujukan Surat</td>
          <td >:</td>
          <td ><input type="text" name="txtRujSurat" id="txtRujSurat" onblur="this.value=this.value.toUpperCase();" style="width:300px"/></td>
        </tr>
        <tr>
          <td ><span class="style1">*</span></td>
          <td >Tarikh  Surat</td>
          <td >:</td>
          <td ><input type="text" name="txtRujTarikh" id="txtRujTarikh" value="$!txtRujTarikh" onblur="check_date(this)" size="9"/>
            <a href="javascript:displayDatePicker('txtRujTarikh',false,'dmy');"><img border="0" src="../../img/calendar.gif"/></a></td>
        </tr>
        #end-->
        #if($!report == 'SuratPemulanganSemula' || $!report == 'SuratTuntutanTunggakanSewa' || $!report == 'SuratRampasanDeposit' || $!report == 'suratIringanResit' || $!report == 'suratTuntutanDeposit' || $!report == 'SuratTuntutan' || $!report == 'suratKuiriCek')
        <tr>
          <td ><span class="style1">*</span></td>
          <td >Nama Pegawai yang Menandatangani</td>
          <td >:</td>
          <td >$!selectPegawai</td>
        </tr>
        #end
        #if($!report == 'BorangPenyerahanSewa' || $!report == 'BorangPenyerahanSewaPenyewa' ||  $!report == 'BorangPenyerahanAPB' || $!report == 'BorangPenyerahanAPBPelesen')
        <tr>
          <td ><span class="style1">*</span></td>
          <td >Mod Bayaran</td>
          <td >:</td>
          <td >$!selectModBayaran</td>
        </tr>
        <tr>
          <td ><span class="style1">*</span></td>
          <td >Nama Pegawai Penyedia</td>
          <td >:</td>
          <td >$!selectPegawai</td>
        </tr>
        <tr>
          <td ><span class="style1">*</span></td>
          <td >Nama Pegawai Penyemak</td>
          <td >:</td>
          <td >$!selectPegawaiPenyemak</td>
        </tr>
        <tr>
          <td ><span class="style1">*</span></td>
          <td >Nama Pegawai Pengesah</td>
          <td >:</td>
          <td >$!selectPegawaiPengesah</td>
        </tr>
        #end
        #if($!report == 'SuratPemulanganSemula')
        <tr>
          <td ><span class="style1">*</span></td>
          <td >No Tel Pegawai</td>
          <td >:</td>
          <td ><input type="text" name="txtNoTel1" id="txtNoTel1" style="width:35px" onkeypress="return validate(event)" maxlength="3"/>
            -
            <input type="text" name="txtNoTel" id="txtNoTel" onkeypress=" return validate(event)" maxlength="8"/></td>
        </tr>
        #end
        #if( $!report == 'BorangDaftarMelSewa' || $!report == 'BorangDaftarMelAPB')
        <tr>
          <td >&nbsp;</td>
          <td >No. Daftar Mel</td>
          <td >:</td>
          <td ><input type="text" name="noMel" id="noMel" onblur="this.value=this.value.toUpperCase();" style="width:300px"/></td>
        </tr>
        #end
        #if( $!report == 'suratTuntutanDeposit')
        <tr>
          <td >&nbsp;</td>
          <td >Baki Lebihan</td>
          <td >:</td>
          <td ><input type="text" name="bakiLebihan" id="bakiLebihan" onblur="this.value=this.value.toUpperCase();" style="width:300px"/></td>
        </tr>
        #end
        <tr>
          <td >&nbsp;</td>
          <td >&nbsp;</td>
          <td >&nbsp;</td>
          <td >&nbsp;</td>
        </tr>
        <tr>
          <td >&nbsp;</td>
          <td >&nbsp;</td>
          <td >&nbsp;</td>
          <td ><!-- START CETAK -->
            #if($report == 'BorangPenyerahanSewa')
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onClick="javascript:cetakBorangPenyerahanSewa()">
            #elseif($report == 'BorangPenyerahanAPB')
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onClick="javascript:cetakBorangPenyerahanAPB()">
            #elseif($report == 'senaraiTerimaanCek')
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onClick="javascript:cetakSenaraiTerimaanCek()">
            #elseif($report == 'SuratPemulanganSemula')
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onClick="javascript:cetakSuratPemulanganCek()">
            #elseif($report == 'SuratTuntutanTunggakanSewa')
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onClick="javascript:cetakSuratTuntutanTunggakanSewa()">
            #elseif($report == 'SuratRampasanDeposit')
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onClick="javascript:cetakSuratRampasanDeposit()">
            #elseif($report == 'suratIringanResit')
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onClick="javascript:cetakSuratIringanResit()">
            #elseif($report == 'BorangDaftarMelSewa')
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onClick="javascript:cetakBorangDaftarMelSewa()">
            #elseif($report == 'BorangPenyerahanSewaPenyewa')
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onClick="javascript:cetakBorangPenyerahanSewaPenyewa()">
            #elseif($report == 'BorangPenyerahanAPBPelesen')
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onClick="javascript:cetakBorangPenyerahanAPBPelesen()">
            #elseif( $report == 'BorangDaftarMelAPB')
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onClick="javascript:cetakBorangDaftarMelAPB()">
            #elseif( $report == 'suratTuntutanDeposit')
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onClick="javascript:cetakSuratTuntutanDeposit()">
            #elseif($report == 'SuratTuntutan')
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onClick="javascript:cetakSuratTuntutan()">
            #elseif($report == 'suratKuiriCek')
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onClick="javascript:cetakSuratKuiriCek()">
            #end
            <!-- END CETAK --></td>
        </tr>
      </table>
      </fieldset></td>
  </tr>
</table>
<script>
function validateNumber(elmnt,content) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = RemoveNonNumeric(content);
		return;
	} else {
		if (content.length < 2){
			document.${formName}.txtKandungan.value = "0" + content;
		}
	}
}
</script>
<!-- START CETAK  -->
<script>
function cetakBorangDaftarMelAPB() {
	if(document.${formName}.txdTarikhMula.value == ""){
		alert('Sila masukkan Tarikh Mula.');
  		document.${formName}.txdTarikhMula.focus();
		return;
	}
	if(document.${formName}.txdTarikhTamat.value == ""){
			alert('Sila masukkan Tarikh Tamat.');
  		document.${formName}.txdTarikhTamat.focus();
		return;
	}

	var url = "../../servlet/ekptg.report.php2.BorangDaftarMelAPB?tarikh_dari="+document.${formName}.txdTarikhMula.value+"&tarikh_hingga="+document.${formName}.txdTarikhTamat.value+"&noMel="+document.${formName}.noMel.value;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}

function validate(evt){
     var charCode = (evt.which) ? evt.which : evt.keyCode;
    if (charCode > 31 && (charCode < 48 || charCode > 57))
        return false;
    return true;
}
function cetakBorangPenyerahanSewa() {
	if(document.${formName}.txdTarikhMula.value == ""){
		alert('Sila masukkan Tarikh Mula.');
  		document.${formName}.txdTarikhMula.focus();
		return;
	}
	if(document.${formName}.txdTarikhTamat.value == ""){
			alert('Sila masukkan Tarikh Tamat.');
  		document.${formName}.txdTarikhTamat.focus();
		return;
	}
	if(document.${formName}.socModBayaran.value == ""){
		alert('Sila pilih Mod Bayaran.');
  		document.${formName}.socModBayaran.focus();
		return;
	}
	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih pegawai.');
  		document.${formName}.socPegawai.focus();
		return;
	}
	if(document.${formName}.socPegawaiPenyemak.value == ""){
		alert('Sila pilih pegawai.');
  		document.${formName}.socPegawaiPenyemak.focus();
		return;
	}
	if(document.${formName}.socPegawaiPengesah.value == ""){
		alert('Sila pilih pegawai.');
  		document.${formName}.socPegawaiPengesah.focus();
		return;
	}

	var url = "../../servlet/ekptg.report.php2.BorangPenyerahanSewa?TARIKH_DARI="+document.${formName}.txdTarikhMula.value+"&TARIKH_HINGGA="+document.${formName}.txdTarikhTamat.value+"&ID_MOD_BAYARAN="+document.${formName}.socModBayaran.value+"&ID_PEGAWAI="+document.${formName}.socPegawai.value+"&ID_PEGAWAI_PENYEMAK="+document.${formName}.socPegawaiPenyemak.value+"&ID_PEGAWAI_PENGESAH="+document.${formName}.socPegawaiPengesah.value;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}

function cetakBorangPenyerahanSewaPenyewa() {
	if(document.${formName}.txdTarikhMula.value == ""){
		alert('Sila masukkan Tarikh Mula.');
  		document.${formName}.txdTarikhMula.focus();
		return;
	}
	if(document.${formName}.txdTarikhTamat.value == ""){
			alert('Sila masukkan Tarikh Tamat.');
  		document.${formName}.txdTarikhTamat.focus();
		return;
	}
	if(document.${formName}.socModBayaran.value == ""){
		alert('Sila pilih Mod Bayaran.');
  		document.${formName}.socModBayaran.focus();
		return;
	}
	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih pegawai.');
  		document.${formName}.socPegawai.focus();
		return;
	}

	var url = "../../servlet/ekptg.report.php2.BorangPenyerahanSewaPenyewa?TARIKH_DARI="+document.${formName}.txdTarikhMula.value+"&TARIKH_HINGGA="+document.${formName}.txdTarikhTamat.value+"&ID_MOD_BAYARAN="+document.${formName}.socModBayaran.value+"&ID_PEGAWAI="+document.${formName}.socPegawai.value+"&ID_PEGAWAI_PENYEMAK="+document.${formName}.socPegawaiPenyemak.value+"&ID_PEGAWAI_PENGESAH="+document.${formName}.socPegawaiPengesah.value+"&ID_HASIL="+document.${formName}.idHasil.value;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakBorangPenyerahanAPB() {
	if(document.${formName}.txdTarikhMula.value == ""){
		alert('Sila masukkan Tarikh Mula.');
  		document.${formName}.txdTarikhMula.focus();
		return;
	}
	if(document.${formName}.txdTarikhTamat.value == ""){
			alert('Sila masukkan Tarikh Tamat.');
  		document.${formName}.txdTarikhTamat.focus();
		return;
	}
	if(document.${formName}.socModBayaran.value == ""){
		alert('Sila pilih Mod Bayaran.');
  		document.${formName}.socModBayaran.focus();
		return;
	}
	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih pegawai.');
  		document.${formName}.socPegawai.focus();
		return;
	}
	if(document.${formName}.socPegawaiPenyemak.value == ""){
		alert('Sila pilih pegawai.');
  		document.${formName}.socPegawaiPenyemak.focus();
		return;
	}
	if(document.${formName}.socPegawaiPengesah.value == ""){
		alert('Sila pilih pegawai.');
  		document.${formName}.socPegawaiPengesah.focus();
		return;
	}

	var url = "../../servlet/ekptg.report.php2.BorangPenyerahanAPB?TARIKH_DARI="+document.${formName}.txdTarikhMula.value+"&TARIKH_HINGGA="+document.${formName}.txdTarikhTamat.value+"&ID_MOD_BAYARAN="+document.${formName}.socModBayaran.value+"&ID_PEGAWAI="+document.${formName}.socPegawai.value+"&ID_PEGAWAI_PENYEMAK="+document.${formName}.socPegawaiPenyemak.value+"&ID_PEGAWAI_PENGESAH="+document.${formName}.socPegawaiPengesah.value;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}

function cetakBorangPenyerahanAPBPelesen() {
	if(document.${formName}.txdTarikhMula.value == ""){
		alert('Sila masukkan Tarikh Mula.');
  		document.${formName}.txdTarikhMula.focus();
		return;
	}
	if(document.${formName}.txdTarikhTamat.value == ""){
			alert('Sila masukkan Tarikh Tamat.');
  		document.${formName}.txdTarikhTamat.focus();
		return;
	}
	if(document.${formName}.socModBayaran.value == ""){
		alert('Sila pilih Mod Bayaran.');
  		document.${formName}.socModBayaran.focus();
		return;
	}
	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih pegawai.');
  		document.${formName}.socPegawai.focus();
		return;
	}

	var url = "../../servlet/ekptg.report.php2.BorangPenyerahanAPBPelesen?TARIKH_DARI="+document.${formName}.txdTarikhMula.value+"&TARIKH_HINGGA="+document.${formName}.txdTarikhTamat.value+"&ID_MOD_BAYARAN="+document.${formName}.socModBayaran.value+"&ID_PEGAWAI="+document.${formName}.socPegawai.value+"&ID_PEGAWAI_PENYEMAK="+document.${formName}.socPegawaiPenyemak.value+"&ID_PEGAWAI_PENGESAH="+document.${formName}.socPegawaiPengesah.value+"&ID_JADUAL_PERTAMA="+document.${formName}.idJadualPertama.value;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakBorangDaftarMelSewa() {
	if(document.${formName}.txdTarikhMula.value == ""){
		alert('Sila masukkan Tarikh Mula.');
  		document.${formName}.txdTarikhMula.focus();
		return;
	}
	if(document.${formName}.txdTarikhTamat.value == ""){
			alert('Sila masukkan Tarikh Tamat.');
  		document.${formName}.txdTarikhTamat.focus();
		return;
	}

	var url = "../../servlet/ekptg.report.php2.BorangDaftarMelSewa?tarikh_dari="+document.${formName}.txdTarikhMula.value+"&tarikh_hingga="+document.${formName}.txdTarikhTamat.value+"&noMel="+document.${formName}.noMel.value;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}

function cetakSenaraiTerimaanCek() {
	if(document.${formName}.txdTarikhMula.value == ""){
		alert('Sila masukkan Tarikh Mula.');
  		document.${formName}.txdTarikhMula.focus();
		return;
	}
	if(document.${formName}.txdTarikhTamat.value == ""){
			alert('Sila masukkan Tarikh Tamat.');
  		document.${formName}.txdTarikhTamat.focus();
		return;
	}

	var url = "../../servlet/ekptg.report.php2.BorangTerimaanCek?tarikh_dari="+document.${formName}.txdTarikhMula.value+"&tarikh_hingga="+document.${formName}.txdTarikhTamat.value;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}

function cetakSuratPemulanganCek() {
	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih pegawai.');
  		document.${formName}.socPegawai.focus();
		return;
	}
	if(document.${formName}.txtNoTel.value == ""){
		alert('Sila masukkan no telefon pegawai.');
  		document.${formName}.txtNoTel.focus();
		return;
	}

	var url = "../../servlet/ekptg.report.php2.REVSuratPemulanganCek?ID_PEGAWAI="+document.${formName}.socPegawai.value+"&ID_AKAUN="+document.${formName}.idAkaun.value+"&NO_TEL="+document.${formName}.txtNoTel1.value+"-"+document.${formName}.txtNoTel.value;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}

function cetakSuratTuntutanTunggakanSewa() {
	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih pegawai.');
  		document.${formName}.socPegawai.focus();
		return;
	}

	var url = "../../servlet/ekptg.report.php2.REVSuratTuntutanTunggakanSewa?ID_PEGAWAI="+document.${formName}.socPegawai.value+"&ID_NOTIS="+document.${formName}.idNotis.value;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakSuratRampasanDeposit() {
	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih pegawai.');
  		document.${formName}.socPegawai.focus();
		return;
	}
	var url = "../../servlet/ekptg.report.php2.REVSuratRampasanDeposit?ID_PEGAWAI="+document.${formName}.socPegawai.value+"&ID_NOTIS="+document.${formName}.idNotis.value;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakSuratTuntutan() {
	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih pegawai.');
  		document.${formName}.socPegawai.focus();
		return;
	}
	var url = "../../servlet/ekptg.report.php2.REVSuratTuntutan?ID_PEGAWAI="+document.${formName}.socPegawai.value+"&ID_NOTIS="+document.${formName}.idNotis.value;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}

function cetakSuratKuiriCek() {

	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih pegawai.');
  		document.${formName}.socPegawai.focus();
		return;
	}
	var url = "../../servlet/ekptg.report.php2.REVSuratKuiriCek?ID_PEGAWAI="+document.${formName}.socPegawai.value+"&ID_HASIL="+document.${formName}.idHasil.value;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakSuratIringanResit() {

	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih pegawai.');
  		document.${formName}.socPegawai.focus();
		return;
	}
	var url = "../../servlet/ekptg.report.php2.REVSuratIringanResit?ID_PEGAWAI="+document.${formName}.socPegawai.value+"&ID_HASIL="+document.${formName}.idHasil.value;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakSuratTuntutanDeposit() {

	if(document.${formName}.socPegawai.value == ""){
		alert('Sila pilih pegawai.');
  		document.${formName}.socPegawai.focus();
		return;
	}
	var url = "../../servlet/ekptg.report.php2.REVSuratTuntutanDeposit?ID_PEGAWAI="+document.${formName}.socPegawai.value+"&ID_HASIL="+document.${formName}.idHasil.value+"&BAKI_LEBIHAN="+document.${formName}.bakiLebihan.value;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
</script>
<!-- END CETAK-->
