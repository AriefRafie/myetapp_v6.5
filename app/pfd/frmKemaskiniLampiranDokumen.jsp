
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
<input name="idMinitArahan" type="hidden" value="$id_Minitarahan" />
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
#if($action != 'tambahLampiran' && $action != 'tambahMinit')
<fieldset>
<legend>MAKLUMAT DOKUMEN</legend>

<div id="TabbedPanels1" class="TabbedPanels">
  <ul class="TabbedPanelsTabGroup">
    <li class="TabbedPanelsTab" tabindex="0" >DOKUMEN MASUK</li>
    <li class="TabbedPanelsTab" tabindex="0" onclick="tabDokumenKeluar()">DOKUMEN KELUAR</li>
  </ul>
  <div class="TabbedPanelsContentGroup">
  
  	<div class="TabbedPanelsContent">
     <fieldset>
       <legend>MAKLUMAT DOKUMEN MASUK</legend>
    <table width="100%">
  <tr>
    <td width="2%" scope="row"><span class="style40">*</span></td>
    <td  width="27%" scope="row">Jenis Dokumen</td>
    <td width="1%" scope="row">:</td>
    <td width="70%">
      <label></label>    <select name="socJenisDokumen" id="socJenisDokumen" class="$disabled" $disabled>
        
		#if ($jenis_Dokumen == '1')
        
        <option value="0">SILA PILIH</option>
        <option value="1" selected="selected">SURAT</option>
        <option value="2">MEMO</option>
        <option value="3">LAPORAN</option>
        <option value="4">MINIT MESYUARAT</option>
        
        
        
        
        #elseif ($jenis_Dokumen == '2')
        
        <option value="0">SILA PILIH</option>
        <option value="1">SURAT</option>
        <option value="2" selected="selected">MEMO</option>
        <option value="3">LAPORAN</option>
        <option value="4">MINIT MESYUARAT</option>
        
        
        
    
        #elseif ($jenis_Dokumen == '3')
        
        <option value="0">SILA PILIH</option>
        <option value="1">SURAT</option>
        <option value="2">MEMO</option>
        <option value="3" selected="selected">LAPORAN</option>
        <option value="4">MINIT MESYUARAT</option>
        
        
        
        
        #elseif ($jenis_Dokumen == '4')
        
        <option value="0">SILA PILIH</option>
        <option value="1">SURAT</option>
        <option value="2">MEMO</option>
        <option value="3">LAPORAN</option>
        <option value="4" selected="selected">MINIT MESYUARAT</option>
        
       
        
        
        #else
        
        <option value="0" selected="selected">SILA PILIH</option>
        <option value="1">SURAT</option>
        <option value="2">MEMO</option>
        <option value="3">LAPORAN</option>
        <option value="4">MINIT MESYUARAT</option>
        
        #end
      
      
      </select>      </td>
  </tr>
  <tr>
    <td width="1%" scope="row"><span class="style40">*</span></td>
    <td scope="row">No Kertas Minit</td>
    <td scope="row">:</td>
    <td>
      <label>
      <input name="txtBilMinitFail" type="text" id="txtBilMinitFail" value = "$bil_Minit_Dokumen" size="3" maxlength="3" $readOnly class="$disabled" $disabled/>
      </label></td>
  </tr>
  <tr>
    <td width="1%" scope="row"><span class="style40">*</span></td>
    <td scope="row">No Rujukan Dokumen</td>
    <td scope="row">:</td>
    <td>
      <label>
        <input name="txtNoRujDokumen" type="text" id="txtNoRujDokumen" value = "$no_Rujukan_Dokumen" size="44" $readOnly class="$disabled" $disabled/>
      </label>     </td>
  </tr>
  <tr>
    <td width="1%" valign="top" scope="row"><span class="style40">*</span></td>
    <td valign="top" scope="row">Tajuk Dokumen</td>
    <td scope="row" valign="top">:</td>
    <td><label>
      <textarea name="txtTajukDokumen" cols="41" id="txtTajukDokumen" onblur="this.value=this.value.toUpperCase();" onkeyup="this.value=this.value.toUpperCase();" $readOnly class="$disabled" $disabled>$tajuk_Dokumen</textarea>
    </label></td>
  </tr>
  <tr>
    <td width="1%" scope="row"><span class="style40">*</span></td>
    <td scope="row">Nama Pengirim</td>
    <td scope="row">:</td>
    <td>$selectPegawaiA</td>
  </tr>
  <tr>
    <td width="1%" scope="row"><span class="style40">*</span></td>
    <td scope="row">Nama Penerima</td>
    <td scope="row">:</td>
    <td>
      <label></label>      
            $selectPegawaiB</td>
  </tr>
  <tr>
    <td width="1%" scope="row"><span class="style40">*</span></td>
    <td scope="row">Tarikh Dokumen Masuk</td>
    <td scope="row">:</td>
    <td><input name="txtTkhDokumenMasuk" type="text" id="txtTkhDokumenMasuk" value="$tarikh_Dokumen_Masuk" size="10" $readOnly class="$disabled" $disabled/>
        <a href="javascript:displayDatePicker('txtTkhDokumenMasuk',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>       </td>
  </tr>
  <tr>
    <td width="1%" scope="row"><span class="style40">*</span></td>
    <td scope="row" id="tarikhDokumen">Tarikh Dokumen Diterima</td>
    <td scope="row">:</td>
    <td><input name="txtTarikhDiterima" type="text" id="txtTarikhDiterima" value="$tarikh_Dokumen_Diterima" size="10" $readOnly class="$disabled" $disabled/>
     <a href="javascript:displayDatePicker('txtTarikhDiterima',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>    </td>
  </tr>
  <tr>
    <td width="1%" scope="row">&nbsp;</td>
    <td scope="row">&nbsp;</td>
    <td scope="row">&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td colspan="4" scope="row"><span class="style5 style47 style69 style45 style6"><span class="style40">Perhatian </span>: Pastikan label berwarna <span class="style40">*</span> wajib diisi.</span></td>
  </tr>
  <tr>
    <td colspan="4" align="center" scope="row">
    #if ($id_Status != '42' && $id_Status != '114' )
    	#if ($mode == 'View')
        
            <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick = "kemaskini()"/>
            <input name="cmdTambahDokumen" type="button" value="Tambah" onclick="tambahDokumen()" />
            <input name="cmdHapus" type="button" value="Hapus" onclick="hapus()" />
            <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="kembali()"/>
        #end
    	#if($mode == 'New')
       		
            <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick = "simpanDokumen()"/>
            <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="batal()"/>
            <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="kembali()"/>
        #end
        #if($mode == 'Update')
            <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick = "updateDokumen()"/>
            <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="batalView()"/>
            <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="kembali()"/>
        #end
        #if($mode == 'PaparUpdate')
      	<input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="kembali()"/>
        #end
     #else
      <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="kembali()"/>
     #end       </td>
  </tr>
</table>
</fieldset>
    </div>  	
    <div class="TabbedPanelsContent"></div>
    
    
  </div>
</div>
</fieldset>
&nbsp;
<fieldset>
<legend>SENARAI DOKUMEN</legend>
<p><span class="style5 style47 style69 style45 style6"><span class="style40">Perhatian </span>: data Jenis Dokumen berwarna <span class="style40">merah</span> menandakan dokumen masuk, manakala data Jenis Dokumen berwarna hitam menandakan dokumen keluar.</span></p>
<table width="100%" >
  <tr class="table_header">
    <td width="1%" scope="row"><strong>No</strong></td>
    <td width="10%"><strong>No Rujukan Dokumen</strong></td>
    <td width="10%"><strong>Jenis Dokumen</strong></td>
    <td width="40%"><strong>Tajuk Dokumen</strong></td>
    <td width="10%"><strong>Nama Pengirim</strong></td>
    <td width="10%"><strong>Nama Penerima</strong></td>
    <td width="10%">&nbsp;</td>
  </tr>
  #foreach($dokumen in $SenaraiDokumen)
   #if ($dokumen.bil == '') 
  	#set ($row = 'row1')
  #elseif ($dokumen.bil % 2 != 0)
  	#set ($row = 'row1')
  #else 
  	#set ($row = 'row2')
  #end 
  #if($dokumen.flag_Dokumen == '1')
  <tr>
    <td class="$row style40 style41">$dokumen.bil</td>
    #if ($dokumen.bil != '') 
    <td class="$row"><a href="javascript:papar_dokumen('$dokumen.id_Dokumen')" class="style1">$dokumen.no_Rujukan_Dokumen</a></td>
    #else
        <td class="$row style40">$dokumen.no_Rujukan_Dokumen</td>
	#end
    <td class="$row style40">$dokumen.jenis_Dokumen</td>
    <td class="$row style40">$dokumen.tajuk_Dokumen</td>
    <td class="$row style40">$dokumen.nama_Pengirim</td>
    <td class="$row style40">$dokumen.nama_Penerima</td>
    <td class="$row">
    <input type="button" name="cmdTambahLampiran" id="cmdTambahLampiran" value="Lampiran" onclick="tambahLampiran('$dokumen.id_Dokumen')"/>    </td>
  </tr>
  #else
  <tr>
    <td class="$row">$dokumen.bil</td>
    #if ($dokumen.bil != '') 
    <td class="$row"><a href="javascript:papar_dokumen('$dokumen.id_Dokumen')" class="style1">$dokumen.no_Rujukan_Dokumen</a></td>
    #else
        <td class="$row">$dokumen.no_Rujukan_Dokumen</td>
	#end
    <td class="$row">$dokumen.jenis_Dokumen</td>
    <td class="$row">$dokumen.tajuk_Dokumen</td>
    <td class="$row">$dokumen.nama_Pengirim</td>
    <td class="$row">$dokumen.nama_Penerima</td>
    <td class="$row"><input type="button" name="cmdTambahLampiran" id="cmdTambahLampiran" value="Lampiran" onclick="tambahLampiran('$dokumen.id_Dokumen')"/></td>
  </tr>
  #end
  #end
</table>
</fieldset>
#end
&nbsp;
#if($action == 'tambahMinit')
<fieldset>
<legend>MAKLUMAT MINIT ARAHAN</legend>
<table width="100%">
 
   <tr>
     <td scope="row" valign="top">No Rujukan Dokumen</td>
     <td scope="row" valign="top">:</td>
     <td>$no_Rujukan_Dokumen</td>
   </tr>
   <tr>
     <td scope="row" valign="top">Jenis Dokumen</td>
     <td scope="row" valign="top">:</td>
     <td>$jenis_Dokumen</td>
   </tr>
  <tr>
  <input name="idMinitArahan" type="hidden" value="$minit.id_Minitarahan" />
    <td width="29%" align="left" valign="top" scope="row">Minit Arahan</td>
    <td width="1%" scope="row" valign="top">:</td>
    <td width="70%">
      <label>
        <textarea name="txtMinitArahan" cols="41" onkeyup="this.value=this.value.toUpperCase()" id="txtMinitArahan" $readOnlyMinit  class = "$disabledMinit" $disabledMinit>$minit_Arahan</textarea>
        </label>     </td>
  </tr>
  <tr>
    <td align="left" scope="row">Pegawai Yang Memberi Arahan</td>
    <td scope="row">:</td>
    <td>
     
      $selectPegawaiA</td>
  </tr>
  <tr>
    <td align="left" scope="row">Pegawai Yang Menerima Arahan</td>
    <td scope="row">:</td>
    <td>
      $selectPegawaiB      </td>
  </tr>
  <tr>
    <td align="left" scope="row">Status Tindakan</td>
    <td scope="row">:</td>
    <td>
      <label>$selectStatusTindakan</label>       </td>
  </tr>
  <tr>
    <td align="left" scope="row">Tarikh Minit Arahan</td>
   <td scope="row">:</td>
    <td>
      <label>
        <input name="txdTarikhMinitArahan" type="text" id="txdTarikhMinitArahan" value="$tarikh_Minit_Arahan" size="10" $readOnlyMinit class = "$disabledMinit" $disabledMinit/>
        </label>
       <a href="javascript:displayDatePicker('txdTarikhMinitArahan',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>   </td>
  </tr>
  <tr>
    <td colspan="3" scope="row">&nbsp;</td>
    </tr>
  <tr>
    <td colspan="3" align="center" scope="row">
    #if ($modeMinit == 'paparMinit')
        <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="kemaskiniMinit()" />
        <input type="button" name="cmdHapus" id="cmdHapus" value="Hapus" onclick="hapusMinit()" />
        
    #elseif ($modeMinit == 'newMinit')
        <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick = "simpanMinit()" />
        <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="batalNewMinit()"/>
	#elseif ($modeMinit == 'kemaskiniMinit')
        <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick = "updateMinit()" />
        <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="batalViewMinit()"/>	
    #end    
    <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="kembaliMinit('$id_Dokumen')" />
    </td>
  </tr>
</table>
</fieldset>
#end
&nbsp;
#if($action == 'papar' || $action == 'tambahMinit')
<fieldset>
<legend>SENARAI MINIT ARAHAN</legend>
<input name="cmdTambahMinit" type="button" value="Tambah" onclick = "tambahMinit('$id_Dokumen')"/>
<table width="100%">
  <tr class="table_header">
    <td width="1%" scope="row"><strong>No</strong></td>
    <td width="10%"><strong>No Rujukan Dokumen</strong></td>
    <td width="20%"><strong>Minit Arahan</strong></td>
    <td width="10%"><strong>Pegawai Yang Memberi Arahan</strong></td>
    <td width="10%"><strong>Pegawai Yang Menerima Arahan</strong></td>
    <td width="10%"><strong>Tarikh Minit Arahan</strong></td>
    <td width="5%"><strong>Status Tindakan</strong></td>
  </tr>
  #foreach($minit in $SenaraiMinit)
    #if ($minit.bil == '') 
  	#set ($row = 'row1')
  #elseif ($minit.bil % 2 != 0)
  	#set ($row = 'row1')
  #else 
  	#set ($row = 'row2')
  #end 
  <tr>
    <td class="$row">$minit.bil</td>
    <td class="$row">$minit.no_Rujukan_Dokumen</td>
     #if ($minit.bil != '') 
    <td class="$row"><a href="javascript:paparMinit('$minit.id_Minitarahan')" class="style1">$minit.minit_Arahan</a></td>
    #else
        <td class="$row">$minit.minit_Arahan</td>
	#end
    <td class="$row">$minit.PegawaiMengarah</td>
    <td class="$row">$minit.PegawaiMenerima</td>
    <td class="$row">$minit.tarikh_Minit_Arahan</td>
    <td class="$row">$minit.status_Tindakan</td>
  </tr>
  #end
</table>
</fieldset>
#end
&nbsp;
#if($action == 'tambahLampiran')
<fieldset>
<legend>MAKLUMAT LAMPIRAN</legend>
<table width="100%">
  <tr>
    <td width="29%" scope="row">No Rujukan Dokumen</td>
    <td width="1%" scope="row"><div align="right">:</div></td>
    <td width="70%">$no_Rujukan_Dokumen</td>
  </tr>
  <tr>
    <td scope="row" valign="top">Jenis Dokumen</td>
    <td scope="row" valign="top"><div align="right">:</div></td>
    <td>$jenis_Dokumen</td>
  </tr>

  <tr>
    <td scope="row">Lampiran</td>
    <td scope="row"><div align="right">:</div></td>
    <td><label>
      <input name="txtLampiran" type="file" id="txtLampiran" size="50" />
    </label></td>
  </tr>
  <tr>
    <td colspan="3" scope="row">&nbsp;</td>
  </tr>

  <tr>
    <td colspan="3" align="center" scope="row">
      <label>
        <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="simpan()" />
      </label>
      <label>
        <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="kembaliLampiran('$id_Dokumen')" />
      </label>    </td>
  </tr>
</table>
</fieldset>
&nbsp;
<fieldset>
  <legend>SENARAI LAMPIRAN</legend>
<table width="100%" border="0" >
  <tr class="table_header">
    <td width="1%" scope="row"><strong>No</strong></td>
    <td width="40%"><strong>Nama Fail</strong></td>
    <td width="20%"><strong>Jenis Fail</strong></td>
    <td width="2%">&nbsp;</td>
  </tr>
  #foreach ($lampiran in $SenaraiLampiran)
   #if ($lampiran.bil == '') 
  	#set ($row = 'row1')
  #elseif ($lampiran.bil % 2 != 0)
  	#set ($row = 'row1')
  #else 
  	#set ($row = 'row2')
  #end 
  <tr>
    <td class="$row">$lampiran.bil</td>
    #if ($lampiran.bil != '') 
    <td class="$row"><a href="javascript:papar_Lampiran('$lampiran.id_Lampiran')"><font color="blue">$lampiran.nama_Fail</font></a></td>
    #else
    <td class="$row">$lampiran.nama_Fail</td>
    #end
    <td class="$row">$lampiran.jenis_Mime</td>
    <td class="$row"><input type="button" name="cmdHapus" id="cmdHapus" value="Hapus" onclick="hapusLampiran('$lampiran.id_Lampiran')" /></td>
  </tr>
 #end
</table>
</fieldset>    
#end
<table width="100%" border="0" >
  <tr>
    <td align="right"><strong>CL-05-06</strong></td>
  </tr>
</table>
<script type="text/javascript">
<!--
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:0});
//-->
function tabDokumenKeluar(){
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action=tabDokumenKeluar";
	document.${formName}.submit();
}
</script>
<script>
function batalView(){

	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action=papar";
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
	document.${formName}.socPegawaiA.value = "";
	document.${formName}.txtTkhDokumenMasuk.value = "";
	document.${formName}.txtTarikhDiterima.value = "";
	
	return;
	
}
function kembali(){

	
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action=SenaraiFail";
	document.${formName}.submit();
}
function papar_dokumen(id){
	
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action=papar";
	document.${formName}.idDokumen.value = id;
	document.${formName}.mode.value = "paparDokumen";
	document.${formName}.submit();	
}
function paparMinit(id){
	
	document.${formName}.idMinitArahan.value = id;
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action=paparMinit";
	document.${formName}.submit();
	
}
function simpanDokumen(){

	var DateMasuk = document.${formName}.txtTkhDokumenMasuk.value;
	var DateDiterima = document.${formName}.txtTarikhDiterima.value;
	
	var d = new Date();
	var curr_date = d.getDate();
	var curr_month = d.getMonth();
	curr_month++
	var curr_year = d.getFullYear();

   	var today = curr_date +'/' + curr_month +'/' + curr_year;
		
	if( DateMasuk > today)
	{
		alert('Sila pastikan " Tarikh Dokumen Masuk " tidak melebihi tarikh hari ini.');
		document.${formName}.txtTkhDokumenMasuk.focus();
		return false;
	}
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
	if (document.${formName}.socPegawaiA.value == ""){
		alert('Sila masukkan " Nama Pengirim " terlebih dahulu.');
		document.${formName}.socPegawaiA.focus();
		return;
	}
	if (document.${formName}.socPegawaiB.value == ""){
		alert('Sila masukkan " Nama Penerima " terlebih dahulu.');
		document.${formName}.socPegawaiB.focus();
		return;
	}
	
	if (document.${formName}.txtTkhDokumenMasuk.value == ""){
		alert('Sila masukkan " Tarikh Dokumen Masuk " terlebih dahulu.');
		document.${formName}.txtTkhDokumenMasuk.focus();
		return;
	}
		
	if (document.${formName}.txtTarikhDiterima.value == ""){
		alert('Sila masukkan " Tarikh Dokumen Diterima " terlebih dahulu.');
		document.${formName}.txtTarikhDiterima.focus();
		return;
	}
	
	if ( !window.confirm("Anda Pasti?") ) return;
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action=simpanDokumenMasuk";
	document.${formName}.submit();	
}

function updateDokumen(){
var DateMasuk = document.${formName}.txtTkhDokumenMasuk.value;
	var DateDiterima = document.${formName}.txtTarikhDiterima.value;
	
	var d = new Date();
	var curr_date = d.getDate();
	var curr_month = d.getMonth();
	curr_month++
	var curr_year = d.getFullYear();

   	var today = curr_date +'/' + curr_month +'/' + curr_year;
		
	if( DateMasuk > today)
	{
		alert('Sila pastikan " Tarikh Dokumen Masuk " tidak melebihi tarikh hari ini.');
		document.${formName}.txtTkhDokumenMasuk.focus();
		return false;
	}
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
	if (document.${formName}.socPegawaiA.value == ""){
		alert('Sila masukkan " Nama Pengirim " terlebih dahulu.');
		document.${formName}.socPegawaiA.focus();
		return;
	}
	if (document.${formName}.socPegawaiB.value == ""){
		alert('Sila masukkan " Nama Penerima " terlebih dahulu.');
		document.${formName}.socPegawaiB.focus();
		return;
	}
	
	if (document.${formName}.txtTkhDokumenMasuk.value == ""){
		alert('Sila masukkan " Tarikh Dokumen Masuk " terlebih dahulu.');
		document.${formName}.txtTkhDokumenMasuk.focus();
		return;
	}
		
	if (document.${formName}.txtTarikhDiterima.value == ""){
		alert('Sila masukkan " Tarikh Dokumen Diterima " terlebih dahulu.');
		document.${formName}.txtTarikhDiterima.focus();
		return;
	}
	
	if ( !window.confirm("Anda Pasti?") ) return;
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action=updateDokumenMasuk";
	document.${formName}.submit();	

}
//	var radioSelected = false;
//	for (j = 0;  j < ${formName}.sorKategoriDokumen.length;  j++){
//	if (${formName}.sorKategoriDokumen[j].checked)
//	radioSelected = true;
//	}
//	
//	var date1= document.getElementById("txtTkhDokumenKeluar").value;
//    var dt1   = parseInt(date1.substring(0,2),10);
//	var mon1  = parseInt(date1.substring(3,5),10);
//    var yr1   = parseInt(date1.substring(6,10),10);   
//    var dateKeluar = new Date(yr1, mon1, dt1);
//	
//	if (document.${formName}.sorKategoriDokumen.value == "1"){
//		var date2 = document.getElementById("txtTarikhDiterima").value;
//    	var dt2   = parseInt(date2.substring(0,2),10);
//		var mon2  = parseInt(date2.substring(3,5),10);
//    	var yr2   = parseInt(date2.substring(6,10),10);   
//    	var dateTerima = new Date(yr2, mon2, dt2);
//		
//		if (dateKeluar > dateTerima){
//		alert ("Tarikh Dokumen tidak boleh melebihi Tarikh Dokumen Diterima");
//		document.${formName}.txtTkhDokumenKeluar.focus();
//		return;
//		}
//	}
//	
//	
//
//	if (!radioSelected){
//		alert('Sila pilih " Kategori Dokumen " terlebih dahulu.');
//		return;
//	}
//	else if (document.${formName}.txtBilMinitFail.value == ""){
//		alert('Sila masukkan " Bil Minit Fail " terlebih dahulu.');
//		document.${formName}.txtBilMinitFail.focus();
//		return;
//	}
//	else if (document.${formName}.socJenisDokumen.value == "0"){
//		alert('Sila pilih " Jenis Dokumen " terlebih dahulu.');
//		document.${formName}.socJenisDokumen.focus();
//		return;
//	}
//	else if (document.${formName}.txtNoRujDokumen.value == ""){
//		alert('Sila masukkan " No Rujukan Dokumen " terlebih dahulu.');
//		document.${formName}.txtNoRujDokumen.focus();
//		return;
//	}
//	else if (document.${formName}.txtNamaPengirim.value == ""){
//		alert('Sila masukkan " Nama Pengirim " terlebih dahulu.');
//		document.${formName}.txtNamaPengirim.focus();
//		return;
//	}
//	else if (document.${formName}.txtNamaPenerima.value == ""){
//		alert('Sila masukkan " Nama Penerima " terlebih dahulu.');
//		document.${formName}.txtNamaPenerima.focus();
//		return;
//	}
//	else if (document.${formName}.txtTkhDokumenKeluar.value == ""){
//		alert('Sila masukkan " Tarikh Dokumen Keluar " terlebih dahulu.');
//		document.${formName}.txtTkhDokumenKeluar.focus();
//		return;
//	}
//		
//	else if (document.${formName}.sorKategoriDokumen.value == "1"){
//		if (document.${formName}.txtTarikhDiterima.value == ""){
//		alert('Sila masukkan " Tarikh Dokumen Diterima " terlebih dahulu.');
//		document.${formName}.txtTarikhDiterima.focus();
//		return;
//		}
//	}
//	
//	
//	if ( !window.confirm("Anda Pasti?") ) return;
//	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action=update";
//	document.${formName}.submit();	

//}
function tambahMinit(id){
	 
	/*var url = "../x/${securityToken}/ekptg.view.pfd.FrmKemaskiniMinitArahan?idDokumen="+id+"&action="+action;
    var hWnd = window.open(url,'printuser','width=800,height=300, resizable=yes,scrollbars=yes,copyhistory=yes,location=no,directories=no,status=yes,toolbar=no,menubar=no');
      if ((document.window != null) && (!hWnd.opener))
        hWnd.opener = document.window;
      if (hWnd.focus != null) hWnd.focus();*/

	document.${formName}.idDokumen.value = id;
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action=tambahMinit";
	document.${formName}.submit();


}

function kemaskini(){
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action=kemaskiniDokumenMasuk";
	document.${formName}.submit();
}
function tambahDokumen(){
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action=tambahDokumen";
	document.${formName}.submit();
}
function tambahLampiran(id){
	  
	document.${formName}.idDokumen.value = id;
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action=tambahLampiran";
	document.${formName}.submit();

}
function dokumenMsk(){
	//document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action=dokumenMsk";
	//document.${formName}.submit();
	document.getElementById('tarikhDokumen').innerHTML = "Tarikh Dokumen Masuk";
}
function dokumenKeluar(){
	//document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action=dokumenKeluar";
	//document.${formName}.submit();
	document.getElementById('tarikhDokumen').innerHTML = "Tarikh Dokumen Keluar";
}
function refreshValue() {
	
    document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action=papar";
	document.${formName}.mode.value = "paparDokumen";
    doAjaxCall${formName}("doRefresh");

}
function hapus(){	
	if ( !window.confirm("Anda Pasti Untuk Hapus?"))return;
		document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action=hapusDokumen";
		document.${formName}.submit();
}
function hapusLampiran(id){	
	if ( !window.confirm("Anda Pasti Untuk Hapus?"))return;
	    document.${formName}.idLampiran.value = id;
		document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action=hapusLampiran";
		document.${formName}.submit();
}
function simpan(){
	
if ( !window.confirm("Adakah Anda Pasti?")) return;
	var id = document.${formName}.idDokumen.value ;
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action=uploadFile&idDokumen="+id;
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
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action=tabDokumenMasuk";
	document.${formName}.submit();
}
function simpanMinit(){
	if ( !window.confirm("Adakah Anda Pasti?")) return;
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action=simpanMinit";
	document.${formName}.submit();
	
}
function hapusMinit(){
	if ( !window.confirm("Adakah Anda Pasti?")) return;
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action=hapusMinit";
	document.${formName}.submit();
	
}
function kemaskiniMinit(){
	
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action=kemaskiniMinit";
	document.${formName}.submit();

}
function updateMinit(){
	if ( !window.confirm("Adakah Anda Pasti?")) return;
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action=updateMinit";
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

	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action=paparMinit";
	document.${formName}.submit();


}
function kembaliMinit(id){

	document.${formName}.idDokumen.value = id;
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action=papar";
	document.${formName}.submit();

}
</script>