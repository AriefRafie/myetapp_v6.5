
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
<input name="idLogDokumen" type="hidden" value="$idLogDokumen"/>
<input name="idDokumen" type="hidden" value="$idDokumen"/>
<input name="idMinitarahan" type="hidden" value="$id_Minitarahan" />
<input name="idLampiran" type="hidden" value="" />
<input name="mode" type="hidden" value="$mode" />
<input name="txtJumDok" type="hidden" value = "$jumlah_Dokumen"/>
&nbsp;
<fieldset>
<legend>SENARAI DOKUMEN</legend>
<p><span class="style5 style47 style69 style45 style6"><span class="style40">Perhatian </span>: data Jenis Dokumen berwarna <span class="style40">merah</span> menandakan dokumen masuk, manakala data Jenis Dokumen berwarna hitam menandakan dokumen keluar.</span></p>
<table width="100%" >
  <tr class="table_header">
    <td width="1%" scope="row"><strong>No</strong></td>
    <td width="30%"><strong>No Rujukan Dokumen</strong></td>
    <td width="10%"><strong>Jenis Dokumen</strong></td>
    <td width="20%"><strong>Tajuk Dokumen</strong></td>
    <td width="20%"><strong>Nama Pengirim</strong></td>
    <td width="20%"><strong>Nama Penerima</strong></td>
    <td width="10%">&nbsp;</td>
    <td width="10%">&nbsp;</td>
  </tr>
  #set ($PrevDocID = '')
  #foreach($listDokumenFailSubjaket in $SenaraiDokumen)
   #if ($listDokumenFailSubjaket.bil == '') 
  	#set ($row = 'row1')
  #elseif ($listDokumenFailSubjaket.bil % 2 != 0)
  	#set ($row = 'row1')
  #else 
  	#set ($row = 'row2')
  #end 
#if ($PrevDocID != $listDokumenFailSubjaket.id_Dokumen)  
  #if($listDokumenFailSubjaket.flag_Dokumen == '1')
  <tr>
    <td class="$row style40 style41">$listDokumenFailSubjaket.bil</td>
    #if ($listDokumenFailSubjaket.bil != '') 
    <td class="$row"><a href="javascript:papar_dokumen('$listDokumenFailSubjaket.id_Dokumen')" class="style1">$listDokumenFailSubjaket.no_Rujukan_Dokumen</a></td>
    #else
        <td class="$row style40">$listDokumenFailSubjaket.no_Rujukan_Dokumen</td>
	#end
    <td class="$row style40">$listDokumenFailSubjaket.jenis_Dokumen</td>
    <td class="$row style40">$listDokumenFailSubjaket.tajuk_Dokumen</td>
    <td class="$row style40">$listDokumenFailSubjaket.nama_Pengirim</td>
    <td class="$row style40">$listDokumenFailSubjaket.nama_Penerima</td>
	 #if($listDokumenFailSubjaket.id_Lampiran != '')
    <td class="$row style40"><span class="$row"><img src="../img/paper_clip.png" align="right" style="vertical-align=bottom;" /></span></td>
    #else
    <td class="$row style40"><span class="$row"></span></td>
    #end
    <td class="$row"><input type="button" name="cmdTambahLampiran2" id="cmdTambahLampiran2" value="Lampiran" onclick="tambahLampiran('$listDokumenFailSubjaket.id_Dokumen')"/></td>
    <td class="$row">&nbsp;</td>
  </tr>
   #elseif($listDokumenFailSubjaket.flag_Dokumen == '2')
  <tr>
    <td class="$row style40 style41">$listDokumenFailSubjaket.bil</td>
    #if ($listDokumenFailSubjaket.bil != '') 
    <td class="$row"><a href="javascript:papar_dokumen('$listDokumenFailSubjaket.id_Dokumen')" class="style1">$listDokumenFailSubjaket.no_Rujukan_Dokumen</a></td>
    #else
        <td class="$row style40 style41">$listDokumenFailSubjaket.no_Rujukan_Dokumen</td>
	#end
    <td class="$row style40 style41">$listDokumenFailSubjaket.jenis_Dokumen</td>
    <td class="$row style40 style41">$listDokumenFailSubjaket.tajuk_Dokumen</td>
    <td class="$row style40 style41">$listDokumenFailSubjaket.nama_Pengirim</td>
    <td class="$row style40 style41">$listDokumenFailSubjaket.nama_Penerima</td>
	 #if($listDokumenFailSubjaket.id_Lampiran != '')
    <td class="$row style40"><span class="$row"><img src="../img/paper_clip.png" align="right" style="vertical-align=bottom;" /></span></td>
    #else
    <td class="$row style40"><span class="$row"></span></td>
    #end
    <td class="$row"><input type="button" name="cmdTambahLampiran2" id="cmdTambahLampiran2" value="Lampiran" onclick="tambahLampiran('$listDokumenFailSubjaket.id_Dokumen')"/></td>
    <td class="$row">&nbsp;</td>
  </tr>
  #else
  <tr>
    <td class="$row">$listDokumenFailSubjaket.bil</td>
    #if ($listDokumenFailSubjaket.bil != '') 
    <td class="$row"><a href="javascript:papar_dokumenSubjaket()" class="style1">$listDokumenFailSubjaket.no_Rujukan_Dokumen</a></td>
    #else
        <td class="$row style40">$listDokumenFailSubjaket.no_Rujukan_Dokumen</td>
	#end
    <td class="$row style40">$listDokumenFailSubjaket.jenis_Dokumen</td>
    <td class="$row style40">$listDokumenFailSubjaket.tajuk_Dokumen</td>
    <td class="$row style40">$listDokumenFailSubjaket.nama_Pengirim</td>
    <td class="$row style40">$listDokumenFailSubjaket.nama_Penerima</td>
    #if($listDokumenFailSubjaket.id_Lampiran != '')
    <td class="$row style40"><span class="$row"><img src="../img/paper_clip.png" align="middle" style="vertical-align=bottom;" /></span></td>
    #else
    <td class="$row style40"><span class="$row"></span></td>
    #end
    <td class="$row"><input type="button" name="cmdTambahLampiran" id="cmdTambahLampiran" value="Lampiran" onclick="tambahLampiran('$listDokumenFailSubjaket.id_Dokumen')"/></td>
    <td class="$row">&nbsp;</td>
  </tr>
  #end
#end
#set ($PrevDocID = $listDokumenFailSubjaket.id_Dokumen)
  #end
</table>
<br>
<table width="100%" >
<tr>
<td><div align="center">#if ($mode == 'View')
  <input type="button" name="cmdKembali1" id="cmdKembali1" value="Kembali" onclick="kembaliTabMasuk()"/>
  #end</div></td>
</tr>
</table>
</fieldset>

<script type="text/javascript">
<!--
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:0});
//-->
function tabDokumenCarian(){
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=tabDokumenCarian";
	document.${formName}.submit();
}
function tabDokumenKeluar(){
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=tabDokumenKeluar";
	document.${formName}.submit();
}
function tabDokumenSubjaket(){
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=tabDokumenSubjaket";
	document.${formName}.submit();
}
</script>
<script>
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
	//document.${formName}.socPegawaiA.value = "";
	document.${formName}.txtPengirim.value = "";
	document.${formName}.socPegawaiB.value = "";
	//document.${formName}.txtTkhDokumenMasuk.value = "";
	document.${formName}.txtTarikhDiterima.value = "";
	
	return;
	
}

function tambahPeg2(id){
	 
	/*var url = "../x/${securityToken}/ekptg.view.pfd.FrmKemaskiniMinitArahan?idDokumen="+id+"&action1="+action1;
    var hWnd = window.open(url,'printuser','width=800,height=300, resizable=yes,scrollbars=yes,copyhistory=yes,location=no,directories=no,status=yes,toolbar=no,menubar=no');
      if ((document.window != null) && (!hWnd.opener))
        hWnd.opener = document.window;
      if (hWnd.focus != null) hWnd.focus();*/

	document.${formName}.idDokumen.value = id;
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=tambahPeg2Masuk";
	document.${formName}.submit();


}

function tambahPeg3(id){
	 
	/*var url = "../x/${securityToken}/ekptg.view.pfd.FrmKemaskiniMinitArahan?idDokumen="+id+"&action1="+action1;
    var hWnd = window.open(url,'printuser','width=800,height=300, resizable=yes,scrollbars=yes,copyhistory=yes,location=no,directories=no,status=yes,toolbar=no,menubar=no');
      if ((document.window != null) && (!hWnd.opener))
        hWnd.opener = document.window;
      if (hWnd.focus != null) hWnd.focus();*/

	document.${formName}.idDokumen.value = id;
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=tambahPeg3Masuk";
	document.${formName}.submit();


}

function kembaliSenarai(){

	
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=SenaraiFail";
	document.${formName}.submit();
}
function kembaliTabMasuk(){

	
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=tabDokumenMasuk";
	document.${formName}.submit();
}
function papar_dokumen(id){
	
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=papar";
	document.${formName}.idDokumen.value = id;
	document.${formName}.mode.value = "paparDokumen";
	document.${formName}.submit();	
}
function papar_dokumenSubjaket(){
	var url = "../x/${securityToken}/ekptg.view.pfd.PendaftaranDokumen&action1=paparDokumenFailSubjaket";
    var hWnd = window.open(url,'Cetak','status=1,width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus(); 
}

function openSenaraiSemak(urli,tajuk,idpermohonan,tem){
var url = "../x/${securityToken}/ekptg.view.htp.pembelian.PerjanjianPembelianModule?command=senaraisemak&tajuk="+tajuk+"&idPermohonan="+idpermohonan;
    var hWnd = window.open(url,'Cetak','status=1,width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus(); 
}
function paparMinit(id){
	
	document.${formName}.idMinitarahan.value = id;
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=paparMinitMasuk";
	document.${formName}.submit();
	
}

function simpanDokumen(){

	//var DateMasuk = document.${formName}.txtTkhDokumenMasuk.value;
	var DateDiterima = document.${formName}.tarikh_Dokumen_Diterima.value;
	var DateDokumen = document.${formName}.tarikh_Dokumen.value;
	
	var d = new Date();
	var curr_date = d.getDate();
	var curr_month = d.getMonth();
	curr_month++
	var curr_year = d.getFullYear();

   	var today = curr_date +'/' + curr_month +'/' + curr_year;
		
	if( DateDokumen > today)
	{
		alert('Sila pastikan " Tarikh Dokumen Masuk " tidak melebihi tarikh hari ini.');
		document.${formName}.tarikh_Dokumen.focus();
		return false;
	}
	if( DateDiterima > today)
	{
		alert('Sila pastikan " Tarikh Dokumen Diterima " tidak melebihi tarikh hari ini.');
		document.${formName}.tarikh_Dokumen_Diterima.focus();
		return false;
	}
	
	if (document.${formName}.socJenisDokumen.value == "0"){
		alert('Sila pilih " Jenis Dokumen " terlebih dahulu.');
		document.${formName}.socJenisDokumen.focus();
		return;
	}
	
	if (document.${formName}.bil_Minit_Dokumen.value == ""){
		alert('Sila masukkan " No Kertas Minit " terlebih dahulu.');
		document.${formName}.bil_Minit_Dokumen.focus();
		return;
	}
	if (document.${formName}.no_Rujukan_Dokumen.value == ""){
		alert('Sila masukkan " No Rujukan Dokumen " terlebih dahulu.');
		document.${formName}.no_Rujukan_Dokumen.focus();
		return;
	}
	if (document.${formName}.tajuk_Dokumen.value == ""){
		alert('Sila masukkan " Tajuk Dokumen " terlebih dahulu.');
		document.${formName}.tajuk_Dokumen.focus();
		return;
	}
//	if (document.${formName}.socPegawaiA.value == ""){
//		alert('Sila masukkan " Nama Pengirim " terlebih dahulu.');
//		document.${formName}.socPegawaiA.focus();
//		return;
//	}
	if (document.${formName}.pengirim_Dokumen.value == ""){
		alert('Sila masukkan " Nama Pengirim " terlebih dahulu.');
		document.${formName}.pengirim_Dokumen.focus();
		return;
	}
	if (document.${formName}.socPegawai.value == ""){
		alert('Sila masukkan " Nama Penerima " terlebih dahulu.');
		document.${formName}.socPegawai.focus();
		return;
	}
	
	if (document.${formName}.tarikh_Dokumen.value == ""){
		alert('Sila masukkan " Tarikh Dokumen " terlebih dahulu.');
		document.${formName}.tarikh_Dokumen.focus();
		return;
	}
		
	if (document.${formName}.tarikh_Dokumen_Diterima.value == ""){
		alert('Sila masukkan " Tarikh Dokumen Diterima " terlebih dahulu.');
		document.${formName}.tarikh_Dokumen_Diterima.focus();
		return;
	}
	
//	if (document.${formName}.txtJumDok.value == "7"){
//		if ( !window.confirm('Dokumen telah mencapai bilangan 3. Adakah anda ingin meneruskan simpanan dokumen? ')) return;
//	}
	if (document.${formName}.txtJumDok.value == "3"){
		if ( !window.confirm('Dokumen telah mencapai bilangan 3. Adakah anda ingin meneruskan simpanan dokumen? ')) return;
	}
	
	if (document.${formName}.txtJumDok.value == "4"){
		if ( !window.confirm('Dokumen telah mencapai bilangan 4. Adakah anda ingin meneruskan simpanan dokumen? ')) return;
	}

	if ( !window.confirm("Anda Pasti?") ) return;
	alert(document.${formName}.socPA.value);
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=simpanDokumenMasuk&idFail="+document.${formName}.idFail.value+"&socJenisDokumen="+document.${formName}.socJenisDokumen.value+"&bil_Minit_Dokumen="+document.${formName}.bil_Minit_Dokumen.value+"&no_Rujukan_Dokumen="+escape(document.${formName}.no_Rujukan_Dokumen.value)+"&no_Rujukan_Lain="+document.${formName}.no_Rujukan_Lain.value+"&tajuk_Dokumen="+document.${formName}.tajuk_Dokumen.value+"&pengirim_Dokumen="+document.${formName}.pengirim_Dokumen.value+"&socPegawai="+document.${formName}.socPegawai.value+"&socPA="+document.${formName}.socPA.value+"&tarikh_Dokumen_Diterima="+document.${formName}.tarikh_Dokumen_Diterima.value+"&tarikh_Dokumen="+document.${formName}.tarikh_Dokumen.value+"&txtJumDok="+document.${formName}.txtJumDok.value+"&idDokumen="+document.${formName}.idDokumen.value+"&c1="+document.${formName}.c1.checked+"&c2="+document.${formName}.c2.checked;
	
	//document.${formName}.no_Rujukan_Dokumen.value = escape(document.${formName}.no_Rujukan_Dokumen.value);
	//var x = create_request_string(document.${formName});
	//alert(x);
	alert(escape(document.${formName}.no_Rujukan_Dokumen.value));
	document.${formName}.method="post";
	document.${formName}.enctype="multipart/form-data";
	document.${formName}.encoding="multipart/form-data";
	document.${formName}.submit();	
}

function simpanDokumenLog(){

	//var DateMasuk = document.${formName}.txtTkhDokumenMasuk.value;
	var DateDiterima = document.${formName}.tarikh_Dokumen_Diterima.value;
	var DateDokumen = document.${formName}.tarikh_Dokumen.value;
	
	var d = new Date();
	var curr_date = d.getDate();
	var curr_month = d.getMonth();
	curr_month++
	var curr_year = d.getFullYear();

   	var today = curr_date +'/' + curr_month +'/' + curr_year;
		
	if( DateDokumen > today)
	{
		alert('Sila pastikan " Tarikh Dokumen Masuk " tidak melebihi tarikh hari ini.');
		document.${formName}.tarikh_Dokumen.focus();
		return false;
	}
	if( DateDiterima > today)
	{
		alert('Sila pastikan " Tarikh Dokumen Diterima " tidak melebihi tarikh hari ini.');
		document.${formName}.tarikh_Dokumen_Diterima.focus();
		return false;
	}
	
	if (document.${formName}.socJenisDokumen.value == "0"){
		alert('Sila pilih " Jenis Dokumen " terlebih dahulu.');
		document.${formName}.socJenisDokumen.focus();
		return;
	}
	
	if (document.${formName}.bil_Minit_Dokumen.value == ""){
		alert('Sila masukkan " No Kertas Minit " terlebih dahulu.');
		document.${formName}.bil_Minit_Dokumen.focus();
		return;
	}
	if (document.${formName}.no_Rujukan_Dokumen.value == ""){
		alert('Sila masukkan " No Rujukan Dokumen " terlebih dahulu.');
		document.${formName}.no_Rujukan_Dokumen.focus();
		return;
	}
	if (document.${formName}.tajuk_Dokumen.value == ""){
		alert('Sila masukkan " Tajuk Dokumen " terlebih dahulu.');
		document.${formName}.tajuk_Dokumen.focus();
		return;
	}
//	if (document.${formName}.socPegawaiA.value == ""){
//		alert('Sila masukkan " Nama Pengirim " terlebih dahulu.');
//		document.${formName}.socPegawaiA.focus();
//		return;
//	}
	if (document.${formName}.pengirim_Dokumen.value == ""){
		alert('Sila masukkan " Nama Pengirim " terlebih dahulu.');
		document.${formName}.pengirim_Dokumen.focus();
		return;
	}
	if (document.${formName}.socPegawai.value == ""){
		alert('Sila masukkan " Nama Penerima " terlebih dahulu.');
		document.${formName}.socPegawai.focus();
		return;
	}
	
	if (document.${formName}.tarikh_Dokumen.value == ""){
		alert('Sila masukkan " Tarikh Dokumen " terlebih dahulu.');
		document.${formName}.tarikh_Dokumen.focus();
		return;
	}
		
	if (document.${formName}.tarikh_Dokumen_Diterima.value == ""){
		alert('Sila masukkan " Tarikh Dokumen Diterima " terlebih dahulu.');
		document.${formName}.tarikh_Dokumen_Diterima.focus();
		return;
	}
	
//	if (document.${formName}.txtJumDok.value == "7"){
//		if ( !window.confirm('Dokumen telah mencapai bilangan 3. Adakah anda ingin meneruskan simpanan dokumen? ')) return;
//	}
	if (document.${formName}.txtJumDok.value == "3"){
		if ( !window.confirm('Dokumen telah mencapai bilangan 3. Adakah anda ingin meneruskan simpanan dokumen? ')) return;
	}
	
	if (document.${formName}.txtJumDok.value == "4"){
		if ( !window.confirm('Dokumen telah mencapai bilangan 4. Adakah anda ingin meneruskan simpanan dokumen? ')) return;
	}

	if ( !window.confirm("Anda Pasti?") ) return;
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=simpanDokumenMasuk&idFail="+document.${formName}.idFail.value+"&idLogDokumen="+document.${formName}.idLogDokumen.value+"&socJenisDokumen="+document.${formName}.socJenisDokumen.value+"&bil_Minit_Dokumen="+document.${formName}.bil_Minit_Dokumen.value+"&no_Rujukan_Dokumen="+escape(document.${formName}.no_Rujukan_Dokumen.value)+"&no_Rujukan_Lain="+document.${formName}.no_Rujukan_Lain.value+"&tajuk_Dokumen="+document.${formName}.tajuk_Dokumen.value+"&pengirim_Dokumen="+document.${formName}.pengirim_Dokumen.value+"&socPegawai="+document.${formName}.socPegawai.value+"&socPA="+document.${formName}.socPA.value+"&tarikh_Dokumen_Diterima="+document.${formName}.tarikh_Dokumen_Diterima.value+"&tarikh_Dokumen="+document.${formName}.tarikh_Dokumen.value+"&txtJumDok="+document.${formName}.txtJumDok.value+"&idDokumen="+document.${formName}.idDokumen.value+"&c1="+document.${formName}.c1.checked+"&c2="+document.${formName}.c2.checked;
	
	//document.${formName}.no_Rujukan_Dokumen.value = escape(document.${formName}.no_Rujukan_Dokumen.value);
	//var x = create_request_string(document.${formName});
	//alert(x);
	//alert(document.${formName}.socPA.value);
	document.${formName}.method="post";
	document.${formName}.enctype="multipart/form-data";
	document.${formName}.encoding="multipart/form-data";
	document.${formName}.submit();	
}

function updateDokumen(){
	//var DateMasuk = document.${formName}.txtTkhDokumenMasuk.value;
	var DateDiterima = document.${formName}.txtTarikhDiterima.value;
	
	var d = new Date();
	var curr_date = d.getDate();
	var curr_month = d.getMonth();
	curr_month++
	var curr_year = d.getFullYear();

   	var today = curr_date +'/' + curr_month +'/' + curr_year;
		
//	if( DateMasuk > today)
//	{
//		alert('Sila pastikan " Tarikh Dokumen Masuk " tidak melebihi tarikh hari ini.');
//		document.${formName}.txtTkhDokumenMasuk.focus();
//		return false;
//	}
	if( DateDiterima > today)
	{
		alert('Sila pastikan " Tarikh Dokumen Diterima " tidak melebihi tarikh hari ini.');
		document.${formName}.txtTarikhDiterima.focus();
		return false;
	}
	
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
	if (document.${formName}.txtPengirim.value == ""){
		alert('Sila masukkan " Pengirim Dokumen " terlebih dahulu.');
		document.${formName}.txtPengirim.focus();
		return;
	}
	if (document.${formName}.socPegawaiB.value == ""){
		alert('Sila masukkan " Nama Penerima " terlebih dahulu.');
		document.${formName}.socPegawaiB.focus();
		return;
	}
	
//	if (document.${formName}.txtTkhDokumenMasuk.value == ""){
//		alert('Sila masukkan " Tarikh Dokumen Masuk " terlebih dahulu.');
//		document.${formName}.txtTkhDokumenMasuk.focus();
//		return;
//	}
		
	if (document.${formName}.txtTarikhDiterima.value == ""){
		alert('Sila masukkan " Tarikh Dokumen Diterima " terlebih dahulu.');
		document.${formName}.txtTarikhDiterima.focus();
		return;
	}
	
	if ( !window.confirm("Anda Pasti?") ) return;
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=updateDokumenMasuk";
	document.${formName}.submit();	

}

function tambahMinit(id){
	 
	/*var url = "../x/${securityToken}/ekptg.view.pfd.FrmKemaskiniMinitArahan?idDokumen="+id+"&action1="+action1;
    var hWnd = window.open(url,'printuser','width=800,height=300, resizable=yes,scrollbars=yes,copyhistory=yes,location=no,directories=no,status=yes,toolbar=no,menubar=no');
      if ((document.window != null) && (!hWnd.opener))
        hWnd.opener = document.window;
      if (hWnd.focus != null) hWnd.focus();*/

	document.${formName}.idDokumen.value = id;
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=tambahMinitMasuk";
	document.${formName}.submit();


}

function kemaskini(){
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=kemaskiniDokumenMasuk";
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
		document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=hapusDokumenMasuk";
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
function simpanMinit(){
	if ( !window.confirm("Adakah Anda Pasti?")) return;
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=simpanMinitMasuk";
	document.${formName}.submit();
	
}
function hapusMinit(id){
	if ( !window.confirm("Adakah Anda Pasti?")) return;
	document.${formName}.idMinitarahan.value = id;
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=hapusMinitMasuk";
	document.${formName}.submit();
	
}
function kemaskiniMinit(){
	
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=kemaskiniMinitMasuk";
	document.${formName}.submit();

}
function updateMinit(id){
	if ( !window.confirm("Adakah Anda Pasti?")) return;
	document.${formName}.idMinitarahan.value = id;
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=updateMinitMasuk";
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
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=tambahMinitMasuk";
	document.${formName}.submit();

}
function kembaliDokumen(id){

	document.${formName}.idDokumen.value = id;
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=papar";
	document.${formName}.submit();

}
function papar_seksyenLain(id){

	document.${formName}.idDokumen.value = id;
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=tambahMinitSeksyenLain";
	document.${formName}.submit();
}
</script>