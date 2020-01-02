
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
<input name="idDokumen" type="hidden" value="$id_Dokumen"/>
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
#if($action1 != 'tambahLampiran' && $action1 != 'tambahMinitKeluar')
<fieldset>
<legend>MAKLUMAT DOKUMEN</legend>

<div id="TabbedPanels1" class="TabbedPanels">
  <ul class="TabbedPanelsTabGroup">
    <li class="TabbedPanelsTab" tabindex="0" onclick="tabDokumenMasuk()">DOKUMEN MASUK</li>
    <li class="TabbedPanelsTab" tabindex="0" >DOKUMEN KELUAR</li>
    <li class="TabbedPanelsTab" tabindex="0" onclick="tabDokumenSubjaket()" >DOKUMEN SUBJAKET</li>
    <li class="TabbedPanelsTab" tabindex="0" onclick="tabDokumenDalaman()" >DOKUMEN DALAMAN</li>
  </ul>
  <div class="TabbedPanelsContentGroup">
  
  	<div class="TabbedPanelsContent"></div>
    <div class="TabbedPanelsContent">
 <fieldset>
       <legend>MAKLUMAT DOKUMEN KELUAR</legend>
   <table width="100%">
  <tr>
    <td width="2%" valign="top" scope="row"><span class="style40">*</span></td>
    <td width="27%" align="left" valign="top" scope="row">Jenis Dokumen</td>
    <td width="1%" align="left" valign="top" scope="row">:</td>
    <td width="70%">
      <label></label>    <select name="socJenisDokumen" id="socJenisDokumen" $readonly $disabled>
        
      #if ($jenis_Dokumen == '1')
        
        <option value="0">SILA PILIH</option>
        <option value="1" selected="selected">SURAT/FAX</option>
        <option value="2">MEMO</option>
        <option value="3">E-MAIL</option>
        <option value="4">DOKUMEN TERPERINGKAT</option>

        #elseif ($jenis_Dokumen == '2')
        
        <option value="0">SILA PILIH</option>
        <option value="1">SURAT/FAX</option>
        <option value="2" selected="selected">MEMO</option>
        <option value="3">E-MAIL</option>
        <option value="4">DOKUMEN TERPERINGKAT</option>
        
        #elseif ($jenis_Dokumen == '3')
        
        <option value="0" selected="selected">SILA PILIH</option>
        <option value="1">SURAT/FAX</option>
        <option value="2">MEMO</option>
        <option value="3" selected="selected">E-MAIL</option>
        <option value="4">DOKUMEN TERPERINGKAT</option>
         
        #elseif ($jenis_Dokumen == '4')
        
        <option value="0">SILA PILIH</option>
        <option value="1">SURAT/FAX</option>
        <option value="2">MEMO</option>
        <option value="3">E-MAIL</option>
        <option value="4" selected="selected">DOKUMEN TERPERINGKAT</option>
        
        #else
        
        <option value="0" selected="selected">SILA PILIH</option>
        <option value="1">SURAT/FAX</option>
        <option value="2">MEMO</option>
        <option value="3">E-MAIL</option>
        <option value="4">DOKUMEN TERPERINGKAT</option>
        
        #end
      
      </select>
      <label></label></td>
  </tr>
  <tr>
    <td valign="top" scope="row">&nbsp;</td>
    <td align="left" valign="top" scope="row">Lampiran</td>
    <td align="left" valign="top" scope="row">:</td>
    <td><label>
    <input type="checkbox" name="c1" value="1" $checkedc1  $readonly $disabled/>
Minit Mesyuarat
<input type="checkbox" name="c2" value="1" $checkedc2  $readonly $disabled/>
Laporan
<input type="checkbox" name="c3" value="1" $checkedc3  $readonly $disabled/>
CD</label></td></td>
  #if($mode == 'View' || $mode == 'Update' || $mode == 'PaparUpdate')
   <tr>
    <td align="left" valign="top" scope="row">&nbsp;</td>
    <td align="left" valign="top" scope="row">Muat Naik Lampiran</td>
    <td align="left" valign="top" scope="row">:</td>
    <td>
    #foreach($paparLampiranDokumenKeluar in $SenaraiListLampiranDokumenKeluar)
      <table width="100%" border="0">
      <tr>
        <td>$paparLampiranDokumenKeluar.bil - <a href="javascript:papar_Lampiran('$paparLampiranDokumenKeluar.id_lampiran')" class="style1">$paparLampiranDokumenKeluar.nama_fail</a></td>
      </tr>
    </table>
      #end</td>
  </tr>
  #elseif($mode == 'New')
 <tr>
    <td align="left" valign="top" scope="row">&nbsp;</td>
    <td align="left" valign="top" scope="row">Muat Naik Lampiran</td>
    <td align="left" valign="top" scope="row">:</td>
    <td>
    #foreach( $i in [1..10] )
        <input name="txtLampiran" type="file" id="txtLampiran" size="50" /><br>
     #end
    </td>
  </tr>
    #else
     <tr>
    <td align="left" valign="top" scope="row">&nbsp;</td>
    <td align="left" valign="top" scope="row">&nbsp;</td>
    <td align="left" valign="top" scope="row">&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  #end
  <tr>
    <td align="left" valign="top" scope="row">&nbsp;</td>
    <td align="left" valign="top" scope="row">Tag Dokumen</td>
    <td align="left" valign="top" scope="row">:</td>
    <td><textarea id="tag_dokumen" name="tag_dokumen" cols="70" rows="6" $readonly $disabled>$!tag_Dokumen</textarea>
     <font color="#ff0000"><i> Keyword</i> yang dicadangkan untuk carian</font>
    <input name="id_tagdokumen" type="hidden" value="$id_tagdokumen"/></td>
  </tr>
  <tr>
    <td valign="top" scope="row">&nbsp;</td>
    <td align="left" valign="top" scope="row">No Kertas Minit</td>
    <td align="left" valign="top" scope="row">:</td>
    <td>
      <label>
      <input name="bil_Minit_Dokumen" type="text" id="bil_Minit_Dokumen" value = "$!bil_Minit_Dokumen" onblur="this.value=this.value.toUpperCase();" onkeyup="this.value=this.value.toUpperCase();" size="3" maxlength="3" $disabledBil $readonlyBil/>
      </label></td>
  </tr>


  <tr>
    <td width="1%" valign="top" scope="row">&nbsp;</td>
    <td align="left" valign="top" scope="row">No Rujukan Dokumen</td>
    <td align="left" valign="top" scope="row">:</td>
    <td>
      <label>
        <input name="no_Rujukan_Dokumen" type="text" id="no_Rujukan_Dokumen" value = "$!no_Rujukan_Dokumen" size="44" onblur="this.value=this.value.toUpperCase();" style="text-transform:uppercase;" $disabledNo $readonlyNo/>
      </label>     </td>
  </tr>
  <tr>
    <td valign="top" scope="row">&nbsp;</td>
    <td align="left" valign="top" scope="row">No Rujukan Tuan</td>
    <td align="left" valign="top" scope="row">:</td>
    <td><input name="no_Rujukan_Lain" type="text" id="no_Rujukan_Lain" value = "$!no_Rujukan_Lain" size="44" onblur="this.value=this.value.toUpperCase();" style="text-transform:uppercase;" $readonly $disabled/></td>
  </tr>
  <tr>
    <td valign="top" scope="row"><span class="style40">*</span></td>
    <td align="left" valign="top" scope="row">Tarikh Dokumen</td>
    <td align="left" valign="top" scope="row">:</td>
    <td><input name="tarikh_Dokumen" type="text" id="tarikh_Dokumen" value="$!tarikh_Dokumen" size="10" $readonly $disabled/>
      <a href="javascript:displayDatePicker('tarikh_Dokumen',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a></td>
  </tr>
  <tr>
    <td valign="top" scope="row"><span class="style40">*</span></td>
    <td align="left" valign="top" scope="row">Pengirim</td>
    <td align="left" valign="top" scope="row">:</td>
    <td><select name="socPegawai" id="socPegawai" $readonly $disabled>
      
      
      
    				#if ($mode == 'Papar' || $mode == 'New')
                    
      
      
      <option value="" >SILA PILIH</option>
      
      
      
                #end
                  #foreach($listPegawai in $SenaraiPegawai)
                  	#if($selectidorang == $listPegawai.user_id)
                    
      
      
      <option value="$listPegawai.user_id" selected="selected">$listPegawai.user_name </option>
      
      
      
                    #else
                    
      
      
      <option value="$listPegawai.user_id" >$listPegawai.user_name </option>
      
      
      
                  #end
                  #end
        
    
    
    </select>    </td>
  </tr>
  <tr>
    <td width="1%" valign="top" scope="row"><span class="style40">*</span></td>
    <td align="left" valign="top" scope="row">Perkara</td>
    <td align="left" valign="top" scope="row">:</td>
    <td>
     #if($mode == 'New' || $mode == 'Update')
      <textarea name="tajuk_Dokumen" cols="70" rows="6" id="tajuk_Dokumen" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase();" $readonly $disabled>$!tajuk_Dokumen</textarea>
    
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
    <td align="left" valign="top" scope="row">Nama Penerima</td>
    <td align="left" valign="top" scope="row">:</td>
    <td>
      <label></label>
      <textarea name="penerima_Dokumen" cols="70" rows="6" id="penerima_Dokumen" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase();" $readonly $disabled>$!penerima_Dokumen</textarea></td>
  </tr>
  <tr>
    <td scope="row"><span class="style40">*</span></td>
    <td scope="row">Nama PT Fail / PAR</td>
    <td align="left" valign="top" scope="row">:</td>
    <td><select name="socPT" id="socPT" $readOnly $disabled>
    				#if ($mode == 'View' || $mode == 'New')
                    <option value="" >SILA PILIH</option>
             	#end
                  #foreach($listPTFail in $SenaraiPTFail)
                  	#if($selectPTFail == $listPTFail.user_id)
                    <option value="$listPTFail.user_id" selected="selected">$listPTFail.user_name </option>
                    #else
                    <option value="$listPTFail.user_id" >$listPTFail.user_name </option>
                  	#end
                  #end
        </select></td>
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
    <td colspan="4" align="center" scope="row">
    #if ($mode == 'View')
      <input type="button" name="cmdKemaskini3" id="cmdKemaskini3" value="Kemaskini" onclick = "kemaskini('$idDokumen')"/>
      <input name="cmdTambahDokumen2" type="button" value="Tambah" onclick="tambahDokumen()" />
      <input name="cmdHapus3" type="button" value="Hapus" onclick="hapus('$idDokumen')" />
      <input type="button" name="cmdKembali1" id="cmdKembali1" value="Kembali" onclick="kembaliTabKeluar()"/>
	#end
    #if($mode == 'New')
<input type="button" name="cmdSimpan2" id="cmdSimpan2" value="Simpan" onclick = "simpanDokumen1()"/>
<input type="button" name="cmdBatal3" id="cmdBatal3" value="Batal" onclick="batal()"/>
<input type="button" name="cmdKembali2" id="cmdKembali2" value="Kembali" onclick="kembaliSenarai()"/>
	#end
	#if($mode == 'Disable') <span class="style40">* Dokumen telah mencapai bilangan 100. Anda diminta membuka Fail Jilid Baru.</span>#end 
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
    <div class="TabbedPanelsContent"></div>

  </div>
</div>
</fieldset>
&nbsp;
#if($action1 != 'papar')
<fieldset>
<legend>SENARAI DOKUMEN</legend>
<p><span class="style5 style47 style69 style45 style6"><span class="style40">Perhatian </span>:</span><span class="style5 style47 style69 style45 style6">Dokumen berwarna <span class="style40">merah</span> - DOKUMEN MASUK, </span><span class="style5 style47 style69 style45 style6">Dokumen berwarna hitam - DOKUMEN KELUAR dan </span><span class="style5 style47 style69 style45 style6">Dokumen berwarna <span class="style43">hijau</span> - DOKUMEN DALAMAN</span></p>
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
    <td class="$row"><a href="javascript:papar_dokumen('$fail.id_Dokumen')" class="style1">$fail.no_Rujukan_Dokumen</a></td>
    #else
        <td class="$row style40">$fail.no_Rujukan_Dokumen</td>
	#end
    <td class="$row style40">$fail.jenis_Dokumen</td>
    <td class="$row style40">$fail.tajuk_Dokumen</td>
    <td class="$row style40">$fail.nama_Pengirim</td>
    <td class="$row style40">$fail.nama_Penerima</td>
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
    <td class="$row"><a href="javascript:papar_dokumen('$fail.id_Dokumen')" class="style1">$fail.no_Rujukan_Dokumen</a></td>
    #else
        <td class="$row style40 style41">$fail.no_Rujukan_Dokumen</td>
	#end
    <td class="$row style40 style41">$fail.jenis_Dokumen</td>
    <td class="$row style40 style41">$fail.tajuk_Dokumen</td>
    <td class="$row style40 style41">$fail.nama_Pengirim</td>
    <td class="$row style40 style41">$fail.nama_Penerima</td>
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
    <td class="$row"><a href="javascript:papar_dokumenSubjaket('$fail.idFailSubjaket')" class="style1">$fail.no_Rujukan_Dokumen</a></td>
    #else
        <td class="$row style40">$fail.no_Rujukan_Dokumen</td>
	#end
    <td class="$row style40">$fail.jenis_Dokumen</td>
    <td class="$row style40">$fail.tajuk_Dokumen</td>
    <td class="$row style40">$fail.nama_Pengirim</td>
    <td class="$row style40">$fail.nama_Penerima</td>
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
#end
#end
<table width="100%" border="0" cellpadding="2">
  <tr>
    <td align="right"><strong>CL-05-06</strong></td>
  </tr>
</table>
<script type="text/javascript">
<!--
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:1});
//-->
function tabDokumenCarian(){
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=tabDokumenCarian";
	document.${formName}.submit();
}
function tabDokumenMasuk(){
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=tabDokumenMasuk&pagemode=2";
	document.${formName}.submit();
}
function tabDokumenSubjaket(){
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=tabDokumenSubjaket";
	document.${formName}.submit();
}
function tabDokumenDalaman(){
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=tabDokumenDalaman";
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
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=tabDokumenMasuk&paparArahan=false";
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

function simpanDokumen1(){

	//var ft = document.${formName}.form_token.value;
	//alert("x= "+document.${formName}.form_token.value);
	
	//var DateMasuk = document.${formName}.txtTkhDokumenMasuk.value;
	//var DateDiterima = document.${formName}.tarikh_Dokumen_Diterima.value;
	//var DateDokumen = document.${formName}.tarikh_Dokumen.value;
	var editorInstance = FCKeditorAPI.GetInstance('tajuk_Dokumen');   
                var tajuk_Dokumen = editorInstance.GetHTML(true);
       			var textlength = tajuk_Dokumen.length;
                
                
                if(textlength==0)
                {
                alert('Sila masukkan " Tarikh Dokumen " terlebih dahulu.');
                oEditor.EditorDocument.body.focus();
                return;
                }
	var d = new Date();
	var curr_date = d.getDate();
	var curr_month = d.getMonth()+1;
	//curr_month++
	var curr_year = d.getFullYear();

   	var today = curr_date +'/' + curr_month +'/' + curr_year;
		   
		   var str2  = document.${formName}.tarikh_Dokumen.value;	   
		   var dt2   = parseInt(str2.substring(0,2),10);
		   var mon2  = parseInt(str2.substring(3,5),10)-1;
		   var yr2  = parseInt(str2.substring(6,10),10);		   
		   var DateDokumen = new Date(yr2, mon2, dt2);
		
	if( DateDokumen > d)
	{
		alert('Sila pastikan " Tarikh Dokumen " tidak melebihi tarikh hari ini.');
		document.${formName}.tarikh_Dokumen.focus();
		return false;
	}
//	if( DateDihantar > today)
//	{
//		alert('Sila pastikan " Tarikh Dokumen Dihantar " tidak melebihi tarikh hari ini.');
//		document.${formName}.tarikh_Hantar_Dokumen.focus();
//		return false;
//	}
	
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

	
//	if (document.${formName}.socPegawaiA.value == ""){
//		alert('Sila masukkan " Nama Pengirim " terlebih dahulu.');
//		document.${formName}.socPegawaiA.focus();
//		return;
//	}
	if (document.${formName}.penerima_Dokumen.value == ""){
		alert('Sila masukkan " Nama Penerima " terlebih dahulu.');
		document.${formName}.penerima_Dokumen.focus();
		return;
	}
	if (document.${formName}.socPegawai.value == ""){
		alert('Sila masukkan " Nama Pengirim " terlebih dahulu.');
		document.${formName}.socPegawai.focus();
		return;
	}
	
	if (document.${formName}.tarikh_Dokumen.value == ""){
		alert('Sila masukkan " Tarikh Dokumen " terlebih dahulu.');
		document.${formName}.tarikh_Dokumen.focus();
		return;
	}
	
	if (document.${formName}.txtJumDok.value == "98"){
		if ( !window.confirm('Dokumen telah mencapai bilangan 98. Adakah anda ingin meneruskan simpanan dokumen? ')) return;
	}
	
	if (document.${formName}.txtJumDok.value == "99"){
		if ( !window.confirm('Dokumen telah mencapai bilangan 99. Adakah anda ingin meneruskan simpanan dokumen? ')) return;
	}
	
	if ( !window.confirm("Anda Pasti?") ) return;
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=simpanDokumenKeluar&idFail="+document.${formName}.idFail.value+"&socJenisDokumen="+document.${formName}.socJenisDokumen.value+"&bil_Minit_Dokumen="+document.${formName}.bil_Minit_Dokumen.value+"&no_Rujukan_Dokumen="+escape(document.${formName}.no_Rujukan_Dokumen.value)+"&no_Rujukan_Lain="+escape(document.${formName}.no_Rujukan_Lain.value)+"&tajuk_Dokumen="+escape(tajuk_Dokumen)+"&penerima_Dokumen="+escape(document.${formName}.penerima_Dokumen.value)+"&socPegawai="+document.${formName}.socPegawai.value+"&socPT="+document.${formName}.socPT.value+"&tarikh_Dokumen="+document.${formName}.tarikh_Dokumen.value+"&txtJumDok="+document.${formName}.txtJumDok.value+"&idDokumen="+document.${formName}.idDokumen.value+"&c1="+document.${formName}.c1.checked+"&c2="+document.${formName}.c2.checked+"&c3="+document.${formName}.c3.checked+"&tag_dokumen="+escape(document.${formName}.tag_dokumen.value);
	
//	alert(escape(document.${formName}.no_Rujukan_Dokumen.value));
	document.${formName}.method="post";
	document.${formName}.enctype="multipart/form-data";
	document.${formName}.encoding="multipart/form-data";
	document.${formName}.submit();	
}

function updateDokumen(){

	//var DateDihantar = document.${formName}.tarikh_Hantar_Dokumen.value;
	//var DateDiterima = document.${formName}.txtTarikhDiterima.value;
	var editorInstance = FCKeditorAPI.GetInstance('tajuk_Dokumen');   
                var tajuk_Dokumen = editorInstance.GetHTML(true);
       			var textlength = tajuk_Dokumen.length;
                
                
                if(textlength==0)
                {
                alert('Sila masukkan " Tarikh Dokumen " terlebih dahulu.');
                oEditor.EditorDocument.body.focus();
                return;
                }
	var d = new Date();
	var curr_date = d.getDate();
	var curr_month = d.getMonth()+1;
	//curr_month++
	var curr_year = d.getFullYear();

   	var today = curr_date +'/' + curr_month +'/' + curr_year;
		   
		   var str2  = document.${formName}.tarikh_Dokumen.value;	   
		   var dt2   = parseInt(str2.substring(0,2),10);
		   var mon2  = parseInt(str2.substring(3,5),10)-1;
		   var yr2  = parseInt(str2.substring(6,10),10);		   
		   var DateDokumen = new Date(yr2, mon2, dt2);
		
	if( DateDokumen > d)
	{
		alert('Sila pastikan " Tarikh Dokumen " tidak melebihi tarikh hari ini.');
		document.${formName}.tarikh_Dokumen.focus();
		return false;
	}
//	if( DateDihantar > today)
//	{
//		alert('Sila pastikan " Tarikh Dokumen Dihantar " tidak melebihi tarikh hari ini.');
//		document.${formName}.tarikh_Hantar_Dokumen.focus();
//		return false;
//	}
	
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

	
//	if (document.${formName}.socPegawaiA.value == ""){
//		alert('Sila masukkan " Nama Pengirim " terlebih dahulu.');
//		document.${formName}.socPegawaiA.focus();
//		return;
//	}
	if (document.${formName}.penerima_Dokumen.value == ""){
		alert('Sila masukkan " Nama Penerima " terlebih dahulu.');
		document.${formName}.penerima_Dokumen.focus();
		return;
	}
	if (document.${formName}.socPegawai.value == ""){
		alert('Sila masukkan " Nama Pengirim " terlebih dahulu.');
		document.${formName}.socPegawai.focus();
		return;
	}
	
	if (document.${formName}.tarikh_Dokumen.value == ""){
		alert('Sila masukkan " Tarikh Dokumen " terlebih dahulu.');
		document.${formName}.tarikh_Dokumen.focus();
		return;
	}
	
	if (document.${formName}.txtJumDok.value == "98"){
		if ( !window.confirm('Dokumen telah mencapai bilangan 98. Adakah anda ingin meneruskan simpanan dokumen? ')) return;
	}
	
	if (document.${formName}.txtJumDok.value == "99"){
		if ( !window.confirm('Dokumen telah mencapai bilangan 99. Adakah anda ingin meneruskan simpanan dokumen? ')) return;
	}
	
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

function kemaskini(id){
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=kemaskiniDokumenKeluar&idDokumen"+id;
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
function hapus(id){	
	if ( !window.confirm("Anda Pasti Untuk Hapus?"))return;
		document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=hapusDokumenKeluar&idDokumen"+id;
		document.${formName}.submit();
}
function hapusLampiran(id){	
	if ( !window.confirm("Anda Pasti Untuk Hapus?"))return;
	    document.${formName}.idLampiran.value = id;
		document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=hapusLampiran";
		document.${formName}.submit();
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
function papar_dokumenSubjaket(idFailSubjaket){
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=paparDokumenFailSubjaket&idFailSubjaket="+idFailSubjaket;
	document.${formName}.submit();
}
</script>