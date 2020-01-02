
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
<input name="idFailSubjaket" type="hidden" value="$idFailSubjaket" />
<input name="idDokumen" type="hidden" value="$idDokumen"/>
<input name="idMinitarahan" type="hidden" value="$id_Minitarahan" />
<input name="idLampiran" type="hidden" value="" />
<input name="mode" type="hidden" value="$mode" />
<input name="txtJumDok" type="hidden" id="txtJumDok" value = "$jumlah_Dokumen"/>
&nbsp;
 <fieldset>
       <legend>MAKLUMAT DOKUMEN SUBJAKET</legend>
   <table width="100%">
     <tr>
    <td valign="top" scope="row">&nbsp;</td>
    <td align="left" valign="top" scope="row">No Fail Subjaket</td>
    <td align="left" valign="top" scope="row">:</td>
    <td><input name="no_Fail" type="text" id="no_Fail" value = "$!no_Fail" size="44" $readonly  $disabled/></td>
  </tr>
  <tr>
    <td width="1%" valign="top" scope="row">&nbsp;</td>
    <td width="31%" align="left" valign="top" scope="row">No Kertas Minit</td>
    <td width="1%" align="left" valign="top" scope="row">:</td>
    <td width="67%">
      <label>
      <input name="txtBilMinitFail" type="text" id="txtBilMinitFail" value = "$bil_Minit_Dokumen" onblur="this.value=this.value.toUpperCase();" onkeyup="this.value=this.value.toUpperCase();" size="3" maxlength="3" $readonlyBil $disabledBil/>
      </label></td>
  </tr>
  <tr>
    <td width="1%" valign="top" scope="row">&nbsp;</td>
    <td align="left" valign="top" scope="row">No Rujukan Fail Subjaket</td>
    <td align="left" valign="top" scope="row">:</td>
    <td>
      <label>
        <input name="txtNoRujDokumen" type="text" id="txtNoRujDokumen" value = "$!no_Rujukan_Dokumen" size="44" $readonlyNo  $disabledNo/>
      </label>     </td>
  </tr>
  <tr>
    <td width="1%" valign="top" scope="row"><span class="style40">*</span></td>
    <td align="left" valign="top" scope="row">Tajuk Dokumen</td>
    <td align="left" valign="top" scope="row">:</td>
    <td><label>
      <textarea name="txtTajukDokumen" cols="41" id="txtTajukDokumen" onblur="this.value=this.value.toUpperCase();" onkeyup="this.value=this.value.toUpperCase();" $readOnly $disabled>$!tajuk_Dokumen</textarea>
    </label></td>
  </tr>
  <tr>
    <td width="1%" valign="top" scope="row"><span class="style40">*</span></td>
    <td align="left" valign="top" scope="row">Tarikh Dokumen Masuk</td>
    <td align="left" valign="top" scope="row">:</td>
    <td>
      <label>
      <input name="txtTkhDokumenMasuk" type="text" id="txtTkhDokumenMasuk" value="$!tarikh_Dokumen_Masuk" size="10" $readOnly $disabled/>
      </label>
        <a href="javascript:displayDatePicker('txtTkhDokumenMasuk',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>       </td>
  </tr>
  <tr>
    <td width="1%" scope="row">&nbsp;</td>
    <td scope="row">&nbsp;</td>
    <td align="left" valign="top" scope="row">&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td colspan="4" scope="row"><span class="style5 style47 style69 style45 style6"><span class="style40">Perhatian </span>: Pastikan label berwarna <span class="style40">*</span> wajib diisi.</span></td>
  </tr>
  <tr>
    <td colspan="4" align="center" scope="row">#if ($mode == 'View')
      <input type="button" name="cmdKemaskini3" id="cmdKemaskini3" value="Kemaskini" onclick = "kemaskini()"/>
      <input name="cmdTambahDokumen2" type="button" value="Tambah" onclick="tambahDokumen()" />
      <input name="cmdHapus3" type="button" value="Hapus" onclick="hapus()" />
      <input type="button" name="cmdKembali1" id="cmdKembali1" value="Kembali" onclick="kembaliTabKeluar()"/>
#end
    	#if($mode == 'New')
<input type="button" name="cmdSimpan2" id="cmdSimpan2" value="Simpan" onclick = "simpanDokumen()"/>
<input type="button" name="cmdBatal3" id="cmdBatal3" value="Batal" onclick="batal()"/>
<input type="button" name="cmdKembali2" id="cmdKembali2" value="Kembali" onclick="kembaliSenarai()"/>
#end#if($mode == 'Disable') <span class="style40">* Dokumen telah mencapai bilangan 100. Anda diminta membuka Fail Jilid Baru.</span>#end 
        #if($mode == 'Update')
<input type="button" name="cmdSimpan3" id="cmdSimpan3" value="Simpan" onclick = "updateDokumen()"/>
<input type="button" name="cmdBatal3" id="cmdBatal3" value="Batal" onclick="batalView()"/>
<input type="button" name="cmdKembali3" id="cmdKembali3" value="Kembali" onclick="kembaliTabKeluar()"/>
#end
        #if($mode == 'PaparUpdate')
<input type="button" name="cmdKembali4" id="cmdKembali4" value="Kembali" onclick="kembaliTabKeluar()"/>
#end</td>
  </tr>
</table>
 </fieldset>
    </div>
  </div>
</div>
</fieldset>
#end
<table width="100%" border="0" cellpadding="2">
  <tr>
    <td align="right"><strong>CL-05-06</strong></td>
  </tr>
</table>
<script type="text/javascript">
<!--
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:2});
//-->
function tabDokumenMasuk(){
	document.${formName}.action1 = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=tabDokumenMasuk";
	document.${formName}.submit();
}
function tabDokumenKeluar(){
	document.${formName}.action1 = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=tabDokumenKeluar";
	document.${formName}.submit();
}
function tabDokumenCarian(){
	document.${formName}.action1 = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=tabDokumenCarian";
	document.${formName}.submit();
}
</script>
<script>
function tambahPeg(){

	
	document.${formName}.action1 = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=tambahPeg2Keluar";
	document.${formName}.submit();
}
function tambahPeg2(){

	
	document.${formName}.action1 = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=tambahPeg2";
	document.${formName}.submit();
}
function batalView(){

	document.${formName}.action1 = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=papar";
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
function kembaliSenarai(){

	
	document.${formName}.action1 = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=SenaraiFail";
	document.${formName}.submit();
}
function kembaliTabKeluar(){

	
	document.${formName}.action1 = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=tabDokumenKeluar";
	document.${formName}.submit();
}
function papar_dokumen(id){
	
	document.${formName}.action1 = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=papar";
	document.${formName}.idDokumen.value = id;
	document.${formName}.mode.value = "paparDokumen";
	document.${formName}.submit();	
}
function paparMinit(id){
	
	document.${formName}.idMinitarahan.value = id;
	document.${formName}.action1 = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=paparMinitKeluar";
	document.${formName}.submit();
	
}
function simpanDokumen(){

	var DateMasuk = document.${formName}.txtTkhDokumenMasuk.value;
	//var DateDiterima = document.${formName}.txtTarikhDiterima.value;
	
	var d = new Date();
	var curr_date = d.getDate();
	var curr_month = d.getMonth();
	curr_month++
	var curr_year = d.getFullYear();

   	var today = curr_date +'/' + curr_month +'/' + curr_year;
		
	if( DateMasuk > today)
	{
		alert('Sila pastikan " Tarikh Dokumen Keluar " tidak melebihi tarikh hari ini.');
		document.${formName}.txtTkhDokumenMasuk.focus();
		return false;
	}
	//if( DateDiterima > today)
//	{
//		alert('Sila pastikan " Tarikh Dokumen Diterima " tidak melebihi tarikh hari ini.');
//		document.${formName}.txtTarikhDiterima.focus();
//		return false;
//	}
	
//	if (document.${formName}.socJenisDokumen.value == "0"){
//		alert('Sila pilih " Jenis Dokumen " terlebih dahulu.');
//		document.${formName}.socJenisDokumen.focus();
//		return;
//	}
	
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
//	if (document.${formName}.socPegawaiA.value == ""){
//		alert('Sila masukkan " Nama Pengirim " terlebih dahulu.');
//		document.${formName}.socPegawaiA.focus();
//		return;
//	}
//		if (document.${formName}.txtPenerima.value == ""){
//		alert('Sila masukkan " Penerima Dokumen " terlebih dahulu.');
//		document.${formName}.txtPenerima.focus();
//		return;
//	}
//	if (document.${formName}.socPegawaiB.value == ""){
//		alert('Sila masukkan " Nama Penerima " terlebih dahulu.');
//		document.${formName}.socPegawaiB.focus();
//		return;
//	}
	
	if (document.${formName}.txtTkhDokumenMasuk.value == ""){
		alert('Sila masukkan " Tarikh Dokumen Masuk " terlebih dahulu.');
		document.${formName}.txtTkhDokumenMasuk.focus();
		return;
	}
		
//	if (document.${formName}.txtJumDok.value == "7"){
//		if ( !window.confirm('Dokumen telah mencapai bilangan 7. Adakah anda ingin meneruskan simpanan dokumen? ')) return;
//	}
	if (document.${formName}.txtJumDok.value == "3"){
		if ( !window.confirm('Dokumen telah mencapai bilangan 3. Adakah anda ingin meneruskan simpanan dokumen? ')) return;
	}
	
	if (document.${formName}.txtJumDok.value == "4"){
		if ( !window.confirm('Dokumen telah mencapai bilangan 4. Adakah anda ingin meneruskan simpanan dokumen? ')) return;
	}
	
	if ( !window.confirm("Anda Pasti?") ) return;
	document.${formName}.action1 = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=simpanDokumenSubjaket";
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
	document.${formName}.action1 = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=updateDokumenKeluar";
	document.${formName}.submit();	
}
function tambahMinit(id){
	 
	/*var url = "../x/${securityToken}/ekptg.view.pfd.FrmKemaskiniMinitArahan?idDokumen="+id+"&action1="+action1;
    var hWnd = window.open(url,'printuser','width=800,height=300, resizable=yes,scrollbars=yes,copyhistory=yes,location=no,directories=no,status=yes,toolbar=no,menubar=no');
      if ((document.window != null) && (!hWnd.opener))
        hWnd.opener = document.window;
      if (hWnd.focus != null) hWnd.focus();*/

	document.${formName}.idDokumen.value = id;
	document.${formName}.action1 = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=tambahMinitKeluar";
	document.${formName}.submit();


}

function kemaskini(){
	document.${formName}.action1 = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=kemaskiniDokumenKeluar";
	document.${formName}.submit();
}
function tambahDokumen(){
	document.${formName}.action1 = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=tabDokumenMasuk";
	document.${formName}.submit();
}
function tambahLampiran(id){
	  
	document.${formName}.idDokumen.value = id;
	document.${formName}.action1 = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=tambahLampiran";
	document.${formName}.submit();

}
function dokumenMsk(){
	//document.${formName}.action1 = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=dokumenMsk";
	//document.${formName}.submit();
	document.getElementById('tarikhDokumen').innerHTML = "Tarikh Dokumen Masuk";
}
function dokumenKeluar(){
	//document.${formName}.action1 = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=dokumenKeluar";
	//document.${formName}.submit();
	document.getElementById('tarikhDokumen').innerHTML = "Tarikh Dokumen Keluar";
}
function refreshValue() {
	
    document.${formName}.action1 = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=papar";
	document.${formName}.mode.value = "paparDokumen";
    doAjaxCall${formName}("doRefresh");

}
function hapus(){	
	if ( !window.confirm("Anda Pasti Untuk Hapus?"))return;
		document.${formName}.action1 = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=hapusDokumenKeluar";
		document.${formName}.submit();
}
function hapusLampiran(id){	
	if ( !window.confirm("Anda Pasti Untuk Hapus?"))return;
	    document.${formName}.idLampiran.value = id;
		document.${formName}.action1 = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=hapusLampiran";
		document.${formName}.submit();
}
function simpan(){
	
if ( !window.confirm("Adakah Anda Pasti?")) return;
	var id = document.${formName}.idDokumen.value ;
	document.${formName}.action1 = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=uploadFile&idDokumen="+id;
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
	document.${formName}.action1 = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=papar";
	document.${formName}.submit();
}
function simpanMinit(){
	if ( !window.confirm("Adakah Anda Pasti?")) return;
	document.${formName}.action1 = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=simpanMinitKeluar";
	document.${formName}.submit();
	
}
function hapusMinit(id){
	if ( !window.confirm("Adakah Anda Pasti?")) return;
	document.${formName}.idMinitarahan.value = id;
	document.${formName}.action1 = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=hapusMinitKeluar";
	document.${formName}.submit();
	
}
function kemaskiniMinit(){
	
	document.${formName}.action1 = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=kemaskiniMinitKeluar";
	document.${formName}.submit();

}
function updateMinit(id){
	if ( !window.confirm("Adakah Anda Pasti?")) return;
	document.${formName}.idMinitarahan.value = id;
	document.${formName}.action1 = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=updateMinitKeluar";
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

	document.${formName}.action1 = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=paparMinit";
	document.${formName}.submit();


}
function kembaliMinit(){

	//document.${formName}.idDokumen.value = id;
	document.${formName}.action1 = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=tambahMinitKeluar";
	document.${formName}.submit();

}
function kembaliDokumen(id){

	document.${formName}.idDokumen.value = id;
	document.${formName}.action1 = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=papar";
	document.${formName}.submit();

}
function cariSubjaket(){
	
	document.${formName}.action1 = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=tabDokumenSubjaket&modeSubjaket=1";
	document.${formName}.submit();

}
function simpan_subjaket(a,b){
	
	document.${formName}.action1 = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=tabDokumenSubjaket&modeSubjaket=2&idFailSubjaket="+a+"&noFail="+b;
	document.${formName}.submit();

}
function papar_dokumenSubjaket(idFailSubjaket){
	document.${formName}.action1 = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=paparDokumenFailSubjaket&idFailSubjaket="+idFailSubjaket;
	document.${formName}.submit();
}
</script>