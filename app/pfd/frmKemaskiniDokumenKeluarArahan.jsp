
<style type="text/css">
<!--
.style1 {color: #0033FF}
.style5 {font-size: 10px; color: #FF0000; }
.style40 {color: #FF0000}
.style6 {
	color: #000000;
	font-style: italic;
}
.style41 {color: #000000}
.style42 {color: #0000FF}
-->
</style>


<input name="idFail" type="hidden" value="$idFail" />
<input name="idDokumen" type="hidden" value="$id_Dokumen"/>
<input name="idMinitarahan" type="hidden" value="$id_Minitarahan" />
<input name="idLampiran" type="hidden" value="" />
<input name="mode" type="hidden" value="$mode" />
&nbsp;
<fieldset>
<legend>MAKLUMAT FAIL</legend>
<table width="100%">  
  <tr>
    <td width="29%" scope="row">NO FAIL</td>
    <td width="1%" scope="row"><div align="right">:</div></td>
    <td width="70%">
      <span class="style42">
      <label>$noFail.toUpperCase()</label>
      </span> </td>
  </tr>
  <tr>
    <td scope="row">TAJUK FAIL</td>
    <td scope="row"><div align="right">:</div></td>
    <td class="style42">$tajukFail.toUpperCase()</td>
  </tr>
  <tr>
    <td scope="row">STATUS FAIL</td>
    <td scope="row"><div align="right">:</div></td>
    <td class="style42">$keterangan.toUpperCase()</td>
  </tr>
  <tr>
    <td scope="row">TARIKH DAFTAR FAIL</td>
    <td scope="row"><div align="right">:</div></td>
    <td class="style42">$tarikh_Daftar_Fail.toUpperCase()</td>
  </tr>
</table>
</fieldset>
&nbsp;
#if($action1 == 'tambahPeg2Keluar')
<fieldset>
<legend>MAKLUMAT AGIHAN TUGASAN</legend>
<table width="100%">
  <tr>
    <td scope="row" valign="top">&nbsp;</td>
    <td align="left" valign="top" scope="row">No Rujukan Dokumen</td>
    <td align="left" valign="top" scope="row">:</td>
    <td><span class="style41">$no_Rujukan_Dokumen</span></td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td scope="row" valign="top">&nbsp;</td>
    <td align="left" valign="top" scope="row">Tajuk Dokumen</td>
    <td align="left" valign="top" scope="row">:</td>
    <td><span class="style41">$tajuk_Dokumen</span></td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td width="1%" align="left" valign="top" scope="row"><span class="style40">*</span></td>
    <input name="idMinitArahan2" type="hidden" value="$minit.id_Minitarahan" />
    <td width="28%" align="left" valign="top" scope="row">Arahan</td>
    <td width="1%" align="left" valign="top" scope="row">:</td>
    <td width="31%"><textarea name="txtMinitArahan" cols="41" onkeyup="this.value=this.value.toUpperCase()" id="txtMinitArahan" $readOnlyMinit $disabledMinit>$minit_Arahan</textarea></td>
    <td width="39%"><label></label>    </td>
  </tr>
  <tr>
    <td align="left" valign="top" scope="row"><span class="style40">*</span></td>
    <td align="left" valign="top" scope="row">Pegawai Yang Memberi Arahan</td>
    <td align="left" valign="top" scope="row">:</td>
    <td>$selectPegawaiA </td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td align="left" valign="top" scope="row"><span class="style40">*</span></td>
    <td align="left" valign="top" scope="row">Pegawai Yang Menerima Arahan</td>
    <td align="left" valign="top" scope="row">:</td>
    <td>$selectPegawaiB </td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td align="left" valign="top" scope="row"><span class="style40">*</span></td>
    <td align="left" valign="top" scope="row">Pegawai ke-2 Yang Menerima Arahan</td>
    <td align="left" valign="top" scope="row">:</td>
    <td>$selectPegawaiC </td>#if ($modeMinit == 'newMinit')
    <td><input type="button" name="cmdTmbhPeg" id="cmdTmbhPeg" value="Tambah Pegawai" onclick="tambahPeg3('$id_Dokumen')" /></td>#else
   #end  </tr>
  <tr>
    <td align="left" valign="top" scope="row"><span class="style40">*</span></td>
    <td align="left" valign="top" scope="row">Status Tindakan</td>
    <td align="left" valign="top" scope="row">:</td>
    <td><label>$selectStatusTindakan</label></td>
    <td><label></label></td>
  </tr>
  <tr>
    <td align="left" valign="top" scope="row">&nbsp;</td>
    <td align="left" valign="top" scope="row">Catatan</td>
    <td align="left" valign="top" scope="row">:</td>
    <td><textarea name="txtCatatan" cols="41" onkeyup="this.value=this.value.toUpperCase()" id="txtCatatan" $readOnlyMinit   $disabledMinit>$catatan</textarea></td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td align="left" valign="top" scope="row"><span class="style40">*</span></td>
    <td align="left" valign="top" scope="row">Tarikh Arahan</td>
    <td align="left" valign="top" scope="row">:</td>
    <td><label>
      <input name="txdTarikhMinitArahan" type="text" id="txdTarikhMinitArahan" value="$!tarikh_Minit_Arahan" size="10" $readOnlyMinit $disabledMinit/>
    </label>
      <a href="javascript:displayDatePicker('txdTarikhMinitArahan',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a></td>
    <td><label></label></td>
  </tr>
  <tr>
    <td colspan="5" scope="row">&nbsp;</td>
  </tr>
  <tr>
    <td colspan="5" align="center" scope="row"> #if ($modeMinit == 'paparMinit')
      <input type="button" name="cmdKembali8" id="cmdKembali8" value="Kembali" onclick="kembaliMinit()" />
      
      #elseif ($modeMinit == 'newMinit')
      <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick = "simpanMinitPeg2()" />
      <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="batalNewMinit()"/>
      <input type="button" name="cmdKembali5" id="cmdKembali5" value="Kembali" onclick="kembaliMinit('$id_Dokumen')" />
      #elseif ($modeMinit == 'kemaskiniMinit')
      
      <input type="button" name="cmdKembali4" id="cmdKembali4" value="Kembali" onclick="kembaliMinit('$id_Dokumen')" />
      #else
      <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="kembaliMinit('$id_Dokumen')" />
      #end</td>
  </tr>
</table>
</fieldset>
#end
&nbsp;
#if($action1 == 'tambahPeg3Keluar')
<fieldset>
<legend>MAKLUMAT AGIHAN TUGASAN</legend>
<table width="100%">
  <tr>
    <td align="left" valign="top" scope="row">&nbsp;</td>
    <td align="left" valign="top" scope="row">No Rujukan Dokumen</td>
    <td scope="row" valign="top">:</td>
    <td><span class="style41">$no_Rujukan_Dokumen</span></td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td align="left" valign="top" scope="row">&nbsp;</td>
    <td align="left" valign="top" scope="row">Tajuk Dokumen</td>
    <td scope="row" valign="top">:</td>
    <td><span class="style41">$tajuk_Dokumen</span></td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td width="1%" align="left" valign="top" scope="row"><span class="style40">*</span></td>
    <input name="idMinitArahan2" type="hidden" value="$minit.id_Minitarahan" />
    <td width="28%" align="left" valign="top" scope="row">Arahan</td>
    <td width="1%" scope="row" valign="top">:</td>
    <td width="31%"><textarea name="txtMinitArahan" cols="41" onkeyup="this.value=this.value.toUpperCase()" id="txtMinitArahan" $readOnlyMinit $disabledMinit>$minit_Arahan</textarea></td>
    <td width="39%"><label></label>    </td>
  </tr>
  <tr>
    <td align="left" valign="top" scope="row"><span class="style40">*</span></td>
    <td align="left" valign="top" scope="row">Pegawai Yang Memberi Arahan</td>
    <td valign="top" scope="row">:</td>
    <td>$selectPegawaiA </td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td align="left" valign="top" scope="row"><span class="style40">*</span></td>
    <td align="left" valign="top" scope="row">Pegawai Yang Menerima Arahan</td>
    <td valign="top" scope="row">:</td>
    <td>$selectPegawaiB </td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td align="left" valign="top" scope="row"><span class="style40">*</span></td>
    <td align="left" valign="top" scope="row">Pegawai ke-2 Yang Menerima Arahan</td>
    <td valign="top" scope="row">:</td>
    <td>$selectPegawaiC </td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td align="left" valign="top" scope="row"><span class="style40">*</span></td>
    <td align="left" valign="top" scope="row">Pegawai ke-3 Yang Menerima Arahan</td>
    <td valign="top" scope="row">:</td>
    <td>$selectPegawaiD </td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td align="left" valign="top" scope="row"><span class="style40">*</span></td>
    <td align="left" valign="top" scope="row">Status Tindakan</td>
    <td valign="top" scope="row">:</td>
    <td><label>$selectStatusTindakan</label></td>
    <td><label></label></td>
  </tr>
  <tr>
    <td align="left" valign="top" scope="row">&nbsp;</td>
    <td align="left" valign="top" scope="row">Catatan</td>
    <td valign="top" scope="row">:</td>
    <td><textarea name="txtCatatan" cols="41" onkeyup="this.value=this.value.toUpperCase()" id="txtCatatan" $readOnlyMinit   $disabledMinit>$catatan</textarea></td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td align="left" valign="top" scope="row"><span class="style40">*</span></td>
    <td align="left" valign="top" scope="row">Tarikh Arahan</td>
    <td valign="top" scope="row">:</td>
    <td><label>
      <input name="txdTarikhMinitArahan" type="text" id="txdTarikhMinitArahan" value="$tarikh_Minit_Arahan" size="10" $readOnlyMinit $disabledMinit/>
    </label>
      <a href="javascript:displayDatePicker('txdTarikhMinitArahan',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a></td>
    <td><label></label></td>
  </tr>
  <tr>
    <td colspan="5" scope="row">&nbsp;</td>
  </tr>
  <tr>
    <td colspan="5" align="center" scope="row"> #if ($modeMinit == 'paparMinit')
      
      <input type="button" name="cmdKembali7" id="cmdKembali7" value="Kembali" onclick="kembaliMinit()" />
      #elseif ($modeMinit == 'newMinit')
      <input type="button" name="cmdSimpan2" id="cmdSimpan2" value="Simpan" onclick = "simpanMinitPeg3()" />
      <input type="button" name="cmdBatal2" id="cmdBatal2" value="Batal" onclick="batalNewMinit()"/>
      <input type="button" name="cmdKembali3" id="cmdKembali3" value="Kembali" onclick="kembaliDokumen('$id_Dokumen')" />
      #elseif ($modeMinit == 'kemaskiniMinit')
      
      <input type="button" name="cmdKembali6" id="cmdKembali6" value="Kembali" onclick="kembaliMinit()" />
      #else
      <input type="button" name="cmdKembali2" id="cmdKembali2" value="Kembali" onclick="kembaliMinit()" />
      #end</td>
  </tr>
</table>
</fieldset>
#end
&nbsp;
#if($action1 == 'tambahPeg2Keluar' || $action1 == 'tambahPeg3Keluar')
<fieldset>
<legend>SENARAI AGIHAN TUGASAN</legend>
<input name="cmdTambahMinit" type="button" value="Tambah" onclick = "tambahMinit('$id_Dokumen')"/>
<table width="100%" cellpadding="2" cellspacing="0">
  <tr>
    <td colspan="5">&nbsp;</td>
  </tr>
	#set ($firstData = '1')
	#foreach($minit in $SenaraiMinit)
        #if ($prevPegawai != $minit.PegawaiMengarah && $prevArahan != $minit.minit_Arahan)
        	#set ($countNo = 1)
	        #set ($prevPegawai = $minit.PegawaiMengarah)
            #set ($prevArahan = $minit.minit_Arahan)
        	#if ($firstData != '1')
      </table>
    </td>
  </tr>
  <tr>
    <td colspan="5">&nbsp;</td>
  </tr>
			#end
            #set ($firstData = '2')        
  <tr>
    <td colspan="5" style="border:1px solid #000000">
      <table width="100%" >
        <tr>
          <td colspan="5" class="row1">Pegawai yang memberi arahan: $prevPegawai</td>
        </tr>
        <tr>
          <td colspan="5" class="row1">Arahan: <a href="javascript:paparMinit('$minit.id_Minitarahan')" class="style1">$prevArahan</a></td>
        </tr>
        <tr>
          <td class="row2">No</td>
          <td class="row2">No Ruj Dok</td>
          <td class="row2">Peg Terima Arahan</td>
          <td class="row2">Tarikh Arahan</td>
          <td class="row2">Status</td>
        </tr>
  		#end
        <tr>
          <td>$countNo</td>
          <td>$minit.no_Rujukan_Dokumen</td>
          <td>$minit.PegawaiMenerima</td>
          <td>$minit.tarikh_Minit_Arahan</td>
          <td>$minit.status_Tindakan</td>
        </tr>
        #if ($prevPegawai != $minit.PegawaiMengarah && $prevArahan != $minit.minit_Arahan)
            #set ($countNo = $countNo + 1)
  		#end
  	#end
    #if ($firstData != '1')
      </table>
    </td>
  </tr>
  <tr>
    <td colspan="5">&nbsp;</td>
  </tr>
    #end
</table>
</fieldset>
#end
<table width="100%" border="0" cellpadding="2">
  <tr>
    <td align="right"><strong>CL-05-06</strong></td>
  </tr>
</table>
<script type="text/javascript">
<!--
//-->
function tabDokumenMasuk(){
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=tabDokumenMasuk";
	document.${formName}.submit();
}
</script>
<script>
function tambahPeg2(id){
	 
	/*var url = "../x/${securityToken}/ekptg.view.pfd.FrmKemaskiniMinitArahan?idDokumen="+id+"&action1="+action1;
    var hWnd = window.open(url,'printuser','width=800,height=300, resizable=yes,scrollbars=yes,copyhistory=yes,location=no,directories=no,status=yes,toolbar=no,menubar=no');
      if ((document.window != null) && (!hWnd.opener))
        hWnd.opener = document.window;
      if (hWnd.focus != null) hWnd.focus();*/

	document.${formName}.idDokumen.value = id;
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=tambahPeg2Keluar";
	document.${formName}.submit();


}

function tambahPeg3(id){
	 
	/*var url = "../x/${securityToken}/ekptg.view.pfd.FrmKemaskiniMinitArahan?idDokumen="+id+"&action1="+action1;
    var hWnd = window.open(url,'printuser','width=800,height=300, resizable=yes,scrollbars=yes,copyhistory=yes,location=no,directories=no,status=yes,toolbar=no,menubar=no');
      if ((document.window != null) && (!hWnd.opener))
        hWnd.opener = document.window;
      if (hWnd.focus != null) hWnd.focus();*/

	document.${formName}.idDokumen.value = id;
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=tambahPeg3Keluar";
	document.${formName}.submit();


}
function batalView(){

	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=papar";
	document.${formName}.mode.value = "paparDokumen";
	document.${formName}.submit();
}
function batal(){

	document.${formName}.reset();
	document.${formName}.sorKategoriDokumen.value = "1";
	document.${formName}.txtBilMinitFail.value = "";
	document.${formName}.socJenisDokumen.value = "";
	document.${formName}.txtNoRujDokumen.value = "";
	document.${formName}.txtTajukDokumen.value = "";
	document.${formName}.socPegawaiA.value = "";
	document.${formName}.txtPenerima.value = "";
	document.${formName}.txtTkhDokumenKeluar.value = "";
	//document.${formName}.txtTarikhDiterima.value = "";
	
	return;
	
}

function kembali(){

	
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=SenaraiFail";
	document.${formName}.submit();
}
function kembaliTabKeluar(){

	
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=tabDokumenKeluar";
	document.${formName}.submit();
}
function papar_dokumen(id){
	
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=papar";
	document.${formName}.idDokumen.value = id;
	document.${formName}.mode.value = "paparDokumen";
	document.${formName}.submit();	
}
function paparMinit(id){
	
	document.${formName}.idMinitArahan.value = id;
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=paparMinitKeluar";
	document.${formName}.submit();
	
}
function simpanDokumen(){

	var DateKeluar = document.${formName}.txtTkhDokumenKeluar.value;
	//var DateDiterima = document.${formName}.txtTarikhDiterima.value;
	
	var d = new Date();
	var curr_date = d.getDate();
	var curr_month = d.getMonth();
	curr_month++
	var curr_year = d.getFullYear();

   	var today = curr_date +'/' + curr_month +'/' + curr_year;
		
	if( DateKeluar > today)
	{
		alert('Sila pastikan " Tarikh Dokumen Keluar " tidak melebihi tarikh hari ini.');
		document.${formName}.txtTkhDokumenKeluar.focus();
		return false;
	}
	//if( DateDiterima > today)
//	{
//		alert('Sila pastikan " Tarikh Dokumen Diterima " tidak melebihi tarikh hari ini.');
//		document.${formName}.txtTarikhDiterima.focus();
//		return false;
//	}
	
	if (document.${formName}.socJenisDokumen.value == "0"){
		alert('Sila pilih " Jenis Dokumen " terlebih dahulu.');
		document.${formName}.socJenisDokumen.focus();
		return;
	}
	
	if (document.${formName}.txtBilMinitFail.value == ""){
		alert('Sila masukkan " No Kertas Minit " terlebih dahulu.');
		document.${formName}.txtBilMinitFail.focus();
		return;
	}
	//if (document.${formName}.txtNoRujDokumen.value == ""){
//		alert('Sila masukkan " No Rujukan Dokumen " terlebih dahulu.');
//		document.${formName}.txtNoRujDokumen.focus();
//		return;
//	}
	if (document.${formName}.txtTajukDokumen.value == ""){
		alert('Sila masukkan " Tajuk Dokumen " terlebih dahulu.');
		document.${formName}.txtTajukDokumen.focus();
		return;
	}
	if (document.${formName}.socPegawaiA.value == ""){
		alert('Sila masukkan " Nama Pengirim " terlebih dahulu.');
		document.${formName}.socPegawaiA.focus();
		return;
	}
		if (document.${formName}.txtPenerima.value == ""){
		alert('Sila masukkan " Penerima Dokumen " terlebih dahulu.');
		document.${formName}.txtPenerima.focus();
		return;
	}
//	if (document.${formName}.socPegawaiB.value == ""){
//		alert('Sila masukkan " Nama Penerima " terlebih dahulu.');
//		document.${formName}.socPegawaiB.focus();
//		return;
//	}
	
	if (document.${formName}.txtTkhDokumenKeluar.value == ""){
		alert('Sila masukkan " Tarikh Dokumen Keluar " terlebih dahulu.');
		document.${formName}.txtTkhDokumenKeluar.focus();
		return;
	}
		
//	if (document.${formName}.txtTarikhDiterima.value == ""){
//		alert('Sila masukkan " Tarikh Dokumen Diterima " terlebih dahulu.');
//		document.${formName}.txtTarikhDiterima.focus();
//		return;
//	}
	
	if ( !window.confirm("Anda Pasti?") ) return;
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=simpanDokumenKeluar";
	document.${formName}.submit();	
}

function updateDokumen(){

		var DateKeluar = document.${formName}.txtTkhDokumenKeluar.value;
	//var DateDiterima = document.${formName}.txtTarikhDiterima.value;
	
	var d = new Date();
	var curr_date = d.getDate();
	var curr_month = d.getMonth();
	curr_month++
	var curr_year = d.getFullYear();

   	var today = curr_date +'/' + curr_month +'/' + curr_year;
		
	if( DateKeluar > today)
	{
		alert('Sila pastikan " Tarikh Dokumen Keluar " tidak melebihi tarikh hari ini.');
		document.${formName}.txtTkhDokumenKeluar.focus();
		return false;
	}
//	if( DateDiterima > today)
//	{
//		alert('Sila pastikan " Tarikh Dokumen Diterima " tidak melebihi tarikh hari ini.');
//		document.${formName}.txtTarikhDiterima.focus();
//		return false;
//	}
	
	if (document.${formName}.socJenisDokumen.value == "0"){
		alert('Sila pilih " Jenis Dokumen " terlebih dahulu.');
		document.${formName}.socJenisDokumen.focus();
		return;
	}
	
	if (document.${formName}.txtBilMinitFail.value == ""){
		alert('Sila masukkan " No Kertas Minit " terlebih dahulu.');
		document.${formName}.txtBilMinitFail.focus();
		return;
	}
	//if (document.${formName}.txtNoRujDokumen.value == ""){
//		alert('Sila masukkan " No Rujukan Dokumen " terlebih dahulu.');
//		document.${formName}.txtNoRujDokumen.focus();
//		return;
//	}
	if (document.${formName}.txtTajukDokumen.value == ""){
		alert('Sila masukkan " Tajuk Dokumen " terlebih dahulu.');
		document.${formName}.txtTajukDokumen.focus();
		return;
	}
	if (document.${formName}.socPegawaiA.value == ""){
		alert('Sila masukkan " Nama Pengirim " terlebih dahulu.');
		document.${formName}.socPegawaiA.focus();
		return;
	}
	if (document.${formName}.txtPenerima.value == ""){
		alert('Sila masukkan " Penerima Dokumen " terlebih dahulu.');
		document.${formName}.txtPenerima.focus();
		return;
	}
//	if (document.${formName}.socPegawaiB.value == ""){
//		alert('Sila masukkan " Nama Penerima " terlebih dahulu.');
//		document.${formName}.socPegawaiB.focus();
//		return;
//	}
	
	if (document.${formName}.txtTkhDokumenKeluar.value == ""){
		alert('Sila masukkan " Tarikh Dokumen Keluar " terlebih dahulu.');
		document.${formName}.txtTkhDokumenKeluar.focus();
		return;
	}
		
//	if (document.${formName}.txtTarikhDiterima.value == ""){
//		alert('Sila masukkan " Tarikh Dokumen Diterima " terlebih dahulu.');
//		document.${formName}.txtTarikhDiterima.focus();
//		return;
//	}
	
	if ( !window.confirm("Anda Pasti?") ) return;
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=updateDokumenKeluar";
	document.${formName}.submit();	
}
function tambahMinit(id){
	 
	/*var url = "../x/${securityToken}/ekptg.view.pfd.FrmKemaskiniMinitArahan?idDokumen="+id+"&action1="+action1;
    var hWnd = window.open(url,'printuser','width=800,height=300, resizable=yes,scrollbars=yes,copyhistory=yes,location=no,directories=no,status=yes,toolbar=no,menubar=no');
      if ((document.window != null) && (!hWnd.opener))
        hWnd.opener = document.window;
      if (hWnd.focus != null) hWnd.focus();*/

	document.${formName}.idDokumen.value = id;
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=tambahMinitKeluar";
	document.${formName}.submit();


}

function kemaskini(){
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=kemaskiniDokumenKeluar";
	document.${formName}.submit();
}
function tambahDokumen(){
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=tabDokumenMasuk";
	document.${formName}.submit();
}
function tambahLampiran(id){
	  
	document.${formName}.idDokumen.value = id;
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=tambahLampiran";
	document.${formName}.submit();

}
function dokumenMsk(){
	//document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=dokumenMsk";
	//document.${formName}.submit();
	document.getElementById('tarikhDokumen').innerHTML = "Tarikh Dokumen Masuk";
}
function dokumenKeluar(){
	//document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=dokumenKeluar";
	//document.${formName}.submit();
	document.getElementById('tarikhDokumen').innerHTML = "Tarikh Dokumen Keluar";
}
function refreshValue() {
	
    document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=papar";
	document.${formName}.mode.value = "paparDokumen";
    doAjaxCall${formName}("doRefresh");

}
function hapus(){	
	if ( !window.confirm("Anda Pasti Untuk Hapus?"))return;
		document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=hapusDokumenKeluar";
		document.${formName}.submit();
}
function hapusLampiran(id){	
	if ( !window.confirm("Anda Pasti Untuk Hapus?"))return;
	    document.${formName}.idLampiran.value = id;
		document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=hapusLampiran";
		document.${formName}.submit();
}
function simpan(){
	
if ( !window.confirm("Adakah Anda Pasti?")) return;
	var id = document.${formName}.idDokumen.value ;
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=uploadFile&idDokumen="+id;
	document.${formName}.method="post";
	document.${formName}.enctype="multipart/form-data";
	document.${formName}.encoding="multipart/form-data";
	document.${formName}.submit();
	return;
}
function papar_Lampiran(id_lampiran) {
    var url = "../servlet/ekptg.view.pfd.DisplayBlob?id="+id_lampiran;
    var hWnd = window.open(url,'displayfile','width=800,height=600, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
    hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
function kembaliLampiran(id){	

	document.${formName}.idDokumen.value = id;
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=papar";
	document.${formName}.submit();
}
function simpanMinitPeg2(){
	if ( !window.confirm("Adakah Anda Pasti?")) return;
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=simpanMinitKeluarPeg2";
	document.${formName}.submit();
	
}
function simpanMinitPeg3(){
	if ( !window.confirm("Adakah Anda Pasti?")) return;
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=simpanMinitKeluarPeg3";
	document.${formName}.submit();
	
}
function hapusMinit(){
	if ( !window.confirm("Adakah Anda Pasti?")) return;
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=hapusMinit";
	document.${formName}.submit();
	
}
function kemaskiniMinit1(){
	
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=kemaskiniMinit";
	document.${formName}.submit();

}
function updateMinit(){
	if ( !window.confirm("Adakah Anda Pasti?")) return;
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=updateMinit";
	document.${formName}.submit();

}
function batalNewMinit(){
	
	document.${formName}.reset();
	document.${formName}.txtMinitArahan.value = "";
	document.${formName}.socStatusTindakan.value = "0";
	document.${formName}.txdTarikhMinitArahan.value = "";
	document.${formName}.socPegawaiA.value = "";
	document.${formName}.socPegawaiB.value = "";
	return;

}
function batalViewMinit(){

	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=paparMinit";
	document.${formName}.submit();


}
function kembaliMinit(){

	//document.${formName}.idDokumen.value = id;
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=tambahMinitKeluar&&paparArahan=true";
	document.${formName}.submit();

}
function kembaliDokumen(id){

	document.${formName}.idDokumen.value = id;
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=papar";
	document.${formName}.submit();

}
</script>