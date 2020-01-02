
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
.style43 {color: #006600}
-->
</style>
<input name="idFail" type="hidden" value="$idFail" />
<input name="idLogDokumen" type="hidden" value="$idLogDokumen"/>
<input name="idDokumen" type="hidden" value="$idDokumen"/>
<input name="idFailSubjaket" type="hidden" value="$idFailSubjaket" />
<input name="idMinitarahan" type="hidden" value="$id_Minitarahan" />
<input name="idLampiran" type="hidden" value="" />
<input name="mode" type="hidden" value="$mode" />
<input name="txtJumDok" type="hidden" id="txtJumDok" value = "$jumlah_Dokumen"/>
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

<fieldset>
<legend>MAKLUMAT DOKUMEN</legend>

<div id="TabbedPanels1" class="TabbedPanels">
  <ul class="TabbedPanelsTabGroup">
    <li class="TabbedPanelsTab" tabindex="0" onclick="tabDokumenMasuk()">DOKUMEN MASUK</li>
    <li class="TabbedPanelsTab" tabindex="0" onclick="tabDokumenKeluar()">DOKUMEN KELUAR</li>
    <li class="TabbedPanelsTab" tabindex="0" >DOKUMEN SUBJAKET</li>
    <li class="TabbedPanelsTab" tabindex="0" onclick="tabDokumenDalaman()" >DOKUMEN DALAMAN</li>
  </ul>
  <div class="TabbedPanelsContentGroup">
  
    <div class="TabbedPanelsContent"></div>
    <div class="TabbedPanelsContent"></div>
    <div class="TabbedPanelsContent">
      <fieldset>
<legend>CARIAN SUBJAKET</legend>
<table width="100%">
  <tr>
    <td width="2%" valign="top" scope="row">&nbsp;</td>
    <td width="27%" align="left" valign="top" scope="row">No Fail Subjaket</td>
    <td width="1%" align="left" valign="top" scope="row">:</td>
    <td width="70%">
      <label>
      <input name="no_Fail" type="text" id="no_Fail" value = "$!no_Fail" onblur="this.value=this.value.toUpperCase();" style="text-transform:uppercase;" size="40" maxlength="40" $readonlySubjaket $disabledSubjaket/>
      </label></td>
  </tr>
  <tr>
    <td valign="top" scope="row">&nbsp;</td>
    <td align="left" valign="top" scope="row">&nbsp;</td>
    <td align="left" valign="top" scope="row">&nbsp;</td>
    <td><input type="button" name="cmdSimpan5" id="cmdSimpan5" value="Cari" onclick = "cariSubjaket()"/></td>
  </tr>
 </table>
 <table width="100%" cellspacing="2" cellpadding="1" border="0">
  <tr class="table_header">
    <td width="1%" scope="row">NO</td>
    <td width="30%">NO FAIL SUBJAKET</td>
  </tr>

  #foreach ($listSubjaket in $SenaraiSubjaket)
  #set ($counter=0)
  #if ($listSubjaket.bil == '') 
  	#set ($row = 'row1')
  #elseif ($listSubjaket.bil % 2 != 0)
  	#set ($row = 'row1')
  #else 
  	#set ($row = 'row2')
  #end 
  <tr>
    <td class="$row">$counter</td>
    #if ($listSubjaket.bil != '') 
    <td class="$row"><a href="javascript:simpan_subjaket('$listSubjaket.id_Fail','$listSubjaket.no_Fail')" class="style1">$!listSubjaket.no_Fail</a></td>
    #else
        <td class="$row">Tiada</td>   
    #end
  </tr>
  #end
</table>
</fieldset>

&nbsp;
      <fieldset>
       <legend>MAKLUMAT DOKUMEN SUBJAKET</legend>
   <table width="100%">
     <tr>
    <td width="2%" valign="top" scope="row">&nbsp;</td>
    <td width="27%" align="left" valign="top" scope="row">No Fail Subjaket</td>
    <td width="1%"align="left" valign="top" scope="row">:</td>
    <td width="70%"><input name="no_Fail" type="text" id="no_Fail" value = "$!no_Fail" size="44" onblur="this.value=this.value.toUpperCase();" style="text-transform:uppercase;" $readonly  $disabled/></td>
  </tr>
  <tr>
    <td  valign="top" scope="row">&nbsp;</td>
    <td  align="left" valign="top" scope="row">No Kertas Minit</td>
    <td  align="left" valign="top" scope="row">:</td>
    <td >
      <label>
      <input name="txtBilMinitFail" type="text" id="txtBilMinitFail" value = "$bil_Minit_Dokumen" onblur="this.value=this.value.toUpperCase();" style="text-transform:uppercase;" size="3" maxlength="3" $readonlyBil $disabledBil/>
      </label></td>
  </tr>
  <tr>
    <td width="1%" valign="top" scope="row">&nbsp;</td>
    <td align="left" valign="top" scope="row">No Rujukan Fail Subjaket</td>
    <td align="left" valign="top" scope="row">:</td>
    <td>
      <label>
        <input name="txtNoRujDokumen" type="text" id="txtNoRujDokumen" value = "$!no_Rujukan_Dokumen" size="44" onblur="this.value=this.value.toUpperCase();" style="text-transform:uppercase;" $readonlyNo  $disabledNo/>
      </label>     </td>
  </tr>
  <tr>
    <td width="1%" valign="top" scope="row"><span class="style40">*</span></td>
    <td align="left" valign="top" scope="row">Keterangan Fail Subjaket</td>
    <td align="left" valign="top" scope="row">:</td>
    <td>
      #if($mode == 'New' || $mode == 'Update')
      <textarea name="tajuk_Dokumen" cols="70" rows="6" id="tajuk_Dokumen" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase();" $readOnly $disabled>$!tajuk_Dokumen</textarea>
    											<script> 
								              	var area1 = new FCKeditor("tajuk_Dokumen");
												area1.ToolbarSet = 'PFD';
									      		area1.BasePath = '/${appname}/library/fck/' ;
									      		//area.Height = 200;
												//area.Width = 780;
												area1.ReplaceTextarea();             	
								              </script> 
    
    #elseif ($mode == 'View' || $mode == 'PaparUpdate')
    	$!tajuk_Dokumen
	
      #end
    
    
    </td>
  </tr>
  <tr>
    <td width="1%" valign="top" scope="row"><span class="style40">*</span></td>
    <td align="left" valign="top" scope="row">Tarikh Masuk</td>
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

&nbsp;

<fieldset>
<legend>SENARAI DOKUMEN</legend>
<p><span class="style5 style47 style69 style45 style6"><span class="style40">Perhatian </span>:</span><span class="style5 style47 style69 style45 style6">Dokumen berwarna <span class="style40">merah</span> - DOKUMEN MASUK, </span><span class="style5 style47 style69 style45 style6">Dokumen berwarna hitam - DOKUMEN KELUAR dan </span><span class="style5 style47 style69 style45 style6">Dokumen berwarna <span class="style43">hijau</span> - DOKUMEN DALAMAN</span></p>
<table width="124%" cellpadding="2" cellspacing="0">
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
  #set ($counter=0)
  #foreach($fail in $SenaraiDokumen)
   #if ($fail.bil == '') 
  	#set ($row = 'row1')
  #elseif ($fail.bil % 2 != 0)
  	#set ($row = 'row1')
  #else 
  	#set ($row = 'row2')
  #end 
#if ($PrevDocID != $fail.id_Dokumen)  
   #if($fail.flag_Dokumen == '1')
       #set ($counter=$counter+1)
  <tr>
    <td class="$row style40 style41">$!counter</td>
    #if ($fail.bil != '') 
    <td class="$row"><a href="javascript:papar_dokumen('$fail.id_Dokumen')" class="style1">$!fail.no_Rujukan_Dokumen</a></td>
    #else
        <td class="$row style40">$!fail.no_Rujukan_Dokumen</td>
	#end
    <td class="$row style40">$!fail.jenis_Dokumen</td>
    <td class="$row style40">$!fail.tajuk_Dokumen</td>
    <td class="$row style40">$!fail.nama_Pengirim</td>
    <td class="$row style40">$!fail.nama_Penerima</td>
	 #if($fail.id_Lampiran != '')
    <td class="$row style40"><span class="$row"><img src="../img/paper_clip.png" align="right" style="vertical-align=bottom;" /></span></td>
    #else
    <td class="$row style40"><span class="$row"></span></td>
    #end
    <td class="$row"><input type="button" name="cmdTambahLampiran2" id="cmdTambahLampiran2" value="Lampiran" onclick="tambahLampiran('$fail.id_Fail','$fail.id_Dokumen')"/></td>
  </tr>
   #elseif($fail.flag_Dokumen == '2')
       #set ($counter=$counter+1)
  <tr>
    <td class="$row style40 style41">$!counter</td>
    #if ($fail.bil != '') 
    <td class="$row"><a href="javascript:papar_dokumen('$fail.id_Dokumen')" class="style1">$!fail.no_Rujukan_Dokumen</a></td>
    #else
        <td class="$row style40 style41">$!fail.no_Rujukan_Dokumen</td>
	#end
    <td class="$row style40 style41">$!fail.jenis_Dokumen</td>
    <td class="$row style40 style41">$!fail.tajuk_Dokumen</td>
    <td class="$row style40 style41">$!fail.nama_Pengirim</td>
    <td class="$row style40 style41">$!fail.nama_Penerima</td>
	 #if($fail.id_Lampiran != '')
    <td class="$row style40"><span class="$row"><img src="../img/paper_clip.png" align="right" style="vertical-align=bottom;" /></span></td>
    #else
    <td class="$row style40"><span class="$row"></span></td>
    #end
    <td class="$row"><input type="button" name="cmdTambahLampiran2" id="cmdTambahLampiran2" value="Lampiran" onclick="tambahLampiran('$fail.id_Fail','$fail.id_Dokumen')"/></td>
  </tr>
  #elseif($fail.flag_Dokumen == '5')
    #set ($counter=$counter+1)
  <tr>
    <td class="$row style40 style41">$!counter</td>
    #if ($fail.bil != '') 
    <td class="$row"><a href="javascript:papar_dokumen('$fail.id_Dokumen')" class="style1">$fail.no_Rujukan_Dokumen</a></td>
    #else
        <td class="$row style43">$fail.no_Rujukan_Dokumen</td>
	#end
    <td class="$row style43">$fail.jenis_Dokumen</td>
    <td class="$row style43">$fail.tajuk_Dokumen</td>
    <td class="$row style43">$fail.nama_Pengirim</td>
    <td class="$row style43">$fail.nama_Penerima</td>
	 #if($fail.id_Lampiran != '')
    <td class="$row style40"><span class="$row"><img src="../img/paper_clip.png" align="right" style="vertical-align=bottom;" /></span></td>
    #else
    <td class="$row style40"><span class="$row"></span></td>
    #end
    <td class="$row"><input type="button" name="cmdTambahLampiran2" id="cmdTambahLampiran2" value="Lampiran" onClick="tambahLampiran('$fail.id_Fail','$fail.id_Dokumen')"/></td>
  </tr>
  #else
      #set ($counter=$counter+1)
  <tr>
    <td class="$row">$!counter</td>
    #if ($fail.bil != '') 
    <td class="$row"><a href="javascript:papar_dokumenSubjaket('$fail.idFailSubjaket')" class="style1">$!fail.no_Rujukan_Dokumen</a></td>
    #else
        <td class="$row style40">$!fail.no_Rujukan_Dokumen</td>
	#end
    <td class="$row style40">$!fail.jenis_Dokumen</td>
    <td class="$row style40">$!fail.tajuk_Dokumen</td>
    <td class="$row style40">$!fail.nama_Pengirim</td>
    <td class="$row style40">$!fail.nama_Penerima</td>
    #if($fail.id_Lampiran != '')
    <td class="$row style40"><span class="$row"><img src="../img/paper_clip.png" align="middle" style="vertical-align=bottom;" /></span></td>
    #else
    <td class="$row style40"><span class="$row"></span></td>
    #end
    <td class="$row"><input type="button" name="cmdTambahLampiran" id="cmdTambahLampiran" value="Lampiran" onclick="tambahLampiran('$fail.id_Fail','$fail.id_Dokumen')"/></td>
  </tr>
  #end
#end
#set ($PrevDocID = $fail.id_Dokumen)
  #end
</table>
</fieldset>
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
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=tabDokumenMasuk&pagemode=2";
	document.${formName}.submit();
}
function tabDokumenKeluar(){
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=tabDokumenKeluar";
	document.${formName}.submit();
}
function tabDokumenDalaman(){
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=tabDokumenDalaman";
	document.${formName}.submit();
}
function tabDokumenCarian(){
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=tabDokumenCarian";
	document.${formName}.submit();
}
</script>
<script>
function tambahPeg(){

	
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=tambahPeg2Keluar";
	document.${formName}.submit();
}
function tambahPeg2(){

	
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=tambahPeg2";
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
function kembaliSenarai(){

	
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
	
	document.${formName}.idMinitarahan.value = id;
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=paparMinitKeluar";
	document.${formName}.submit();
	
}
function simpanDokumen(){
	
		var editorInstance = FCKeditorAPI.GetInstance('tajuk_Dokumen');   
                var tajuk_Dokumen = editorInstance.GetHTML(true);
       			var textlength = tajuk_Dokumen.length;
                
                
                if(textlength==0)
                {
                alert('Sila masukkan " Tarikh Dokumen " terlebih dahulu.');
                oEditor.EditorDocument.body.focus();
                return;
                }
	
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
	if (document.${formName}.txtJumDok.value == "98"){
		if ( !window.confirm('Dokumen telah mencapai bilangan 98. Adakah anda ingin meneruskan simpanan dokumen? ')) return;
	}
	
	if (document.${formName}.txtJumDok.value == "99"){
		if ( !window.confirm('Dokumen telah mencapai bilangan 99. Adakah anda ingin meneruskan simpanan dokumen? ')) return;
	}
	
	if ( !window.confirm("Anda Pasti?") ) return;
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=simpanDokumenSubjaket";
	document.${formName}.submit();	
}

function updateDokumen(){

	var editorInstance = FCKeditorAPI.GetInstance('tajuk_Dokumen');   
                var tajuk_Dokumen = editorInstance.GetHTML(true);
       			var textlength = tajuk_Dokumen.length;
                
                
                if(textlength==0)
                {
                alert('Sila masukkan " Tarikh Dokumen " terlebih dahulu.');
                oEditor.EditorDocument.body.focus();
                return;
                }
				
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
function tambahLampiran(idFail,idDokumen){
	  
	//document.${formName}.idDokumen.value = id;
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=tambahLampiran&idDokumen="+idDokumen+"&idFail="+idFail;
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
	
	if (document.${formName}.txtJumDok.value == "98"){
		if ( !window.confirm('Dokumen telah mencapai bilangan 98. Adakah anda ingin meneruskan simpanan dokumen? ')) return;
	}
	
	if (document.${formName}.txtJumDok.value == "99"){
		if ( !window.confirm('Dokumen telah mencapai bilangan 99. Adakah anda ingin meneruskan simpanan dokumen? ')) return;	
		}
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
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=simpanMinitKeluar";
	document.${formName}.submit();
	
}
function hapusMinit(id){
	if ( !window.confirm("Adakah Anda Pasti?")) return;
	document.${formName}.idMinitarahan.value = id;
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=hapusMinitKeluar";
	document.${formName}.submit();
	
}
function kemaskiniMinit(){
	
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=kemaskiniMinitKeluar";
	document.${formName}.submit();

}
function updateMinit(id){
	if ( !window.confirm("Adakah Anda Pasti?")) return;
	document.${formName}.idMinitarahan.value = id;
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=updateMinitKeluar";
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
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=tambahMinitKeluar";
	document.${formName}.submit();

}
function kembaliDokumen(id){

	document.${formName}.idDokumen.value = id;
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=papar";
	document.${formName}.submit();

}
function cariSubjaket(){
	
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=tabDokumenSubjaket&modeSubjaket=1";
	document.${formName}.submit();

}
function simpan_subjaket(a,b){
	
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=tabDokumenSubjaket&modeSubjaket=2&idFailSubjaket="+a+"&noFail="+b;
	document.${formName}.submit();

}
function papar_dokumenSubjaket(idFailSubjaket){
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=paparDokumenFailSubjaket&idFailSubjaket="+idFailSubjaket;
	document.${formName}.submit();
}
</script>