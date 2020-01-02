
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

<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
<input name="idNegeri" type="hidden" value="$idNegeri"/>
<input name="idFail" type="hidden" value="$idFail" />
<input name="idLogDokumen" type="hidden" value="$idLogDokumen"/>
<input name="idDokumen" type="hidden" value="$idDokumen"/>
<input name="idMinitarahan" type="hidden" value="$id_Minitarahan" />
<input name="user_Id" type="hidden" value="$user_Id" />
<input name="idLampiran" type="hidden" value="" />
<input name="mode" type="hidden" value="$mode" />
<input type="hidden" name="levelArahan" value="$!levelArahan"/>
<input name="txtJumDok" type="hidden" id="txtJumDok" value = "$jumlah_Dokumen"/>
<input name="listLampiranLogDokumen_size" type="hidden" value="$!listLampiranLogDokumen_size"/>
<input name="paparLampiranDokumenMasuk_size" type="hidden" value="$!paparLampiranDokumenMasuk_size"/>

&nbsp;
<fieldset>
<legend>MAKLUMAT FAIL</legend>
<table width="100%">

 
   
  <tr>
    <td width="29%" scope="row">NO FAIL</td>
    <td width="1%" scope="row"><div align="right">:</div></td>
    <td width="70%">
      <input name="txtNoFail" type="text" id="txtNoFail" value="$!noFail" size="28" $readonly $disabled/>
      <input type="button" name="cmdPilihFail" id="cmdPilihFail" value="Pilih No Fail" onClick="pilihfail()"/>  
      </td>
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
#if($action1 != 'tambahLampiran' && $action1 != 'tambahMinitMasuk')
<fieldset>
<legend>MAKLUMAT DOKUMEN</legend>

<div id="TabbedPanels1" class="TabbedPanels">
  <ul class="TabbedPanelsTabGroup">
    <li class="TabbedPanelsTab" tabindex="0" >DOKUMEN MASUK</li>
    <li class="TabbedPanelsTab" tabindex="0" onclick="tabDokumenKeluar()">DOKUMEN KELUAR</li>
    <li class="TabbedPanelsTab" tabindex="0" onclick="tabDokumenSubjaket()" >DOKUMEN SUBJAKET</li>
    <li class="TabbedPanelsTab" tabindex="0" onclick="tabDokumenDalaman()" >DOKUMEN DALAMAN</li>
  </ul>
  <div class="TabbedPanelsContentGroup">
  
  	<div class="TabbedPanelsContent">
     <fieldset>
       <legend>MAKLUMAT DOKUMEN MASUK</legend>
    <table width="100%">
  <tr>
    <td width="2%" align="left" valign="top" scope="row"><span class="style40">*</span></td>
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
    <td align="left" valign="top" scope="row">&nbsp;</td>
    <td align="left" valign="top" scope="row">Lampiran</td>
    <td align="left" valign="top" scope="row">:</td>
    <td><label>
    <input type="checkbox" name="c1" value="1" $checkedc1  $readonly $disabled/>
Minit Mesyuarat
<input type="checkbox" name="c2" value="1" $checkedc2  $readonly $disabled/>
Laporan
<input type="checkbox" name="c3" value="1" $checkedc3  $readonly $disabled/>
CD</label></td>
  </tr>
  #if($mode == 'NewLog')
  	#if($listLampiranLogDokumen_size == '0')
    	<tr>
    <td align="left" valign="top" scope="row">&nbsp;</td>
    <td align="left" valign="top" scope="row">Muat Naik Lampiran</td>
    <td align="left" valign="top" scope="row">:</td>
    <td><span class="style40"><em>Tiada lampiran di masukkan dalam sistem eTapp, dokumen fizikal akan dihantar ke pegawai yang berkenaan.</em></span></td>
  </tr>
    #else
    	<tr>
    <td align="left" valign="top" scope="row">&nbsp;</td>
    <td align="left" valign="top" scope="row">Muat Naik Lampiran</td>
    <td align="left" valign="top" scope="row">:</td>
    <td>
    #foreach($listLampiranLogDokumen in $SenaraiListLampiranLogDokumen)
      <table width="100%" border="0">
      <tr>
        <td>$listLampiranLogDokumen.bil - <a href="javascript:papar_Lampiran('$listLampiranLogDokumen.id_lampiran')" class="style1">$listLampiranLogDokumen.nama_fail</a></td>
      </tr>
    </table>
      #end</td>
  </tr>
    #end
  #elseif($mode == 'View' || $mode == 'Update' || $mode == 'PaparUpdate')
   	#if($paparLampiranDokumenMasuk_size == '0')
    	<tr>
    <td align="left" valign="top" scope="row">&nbsp;</td>
    <td align="left" valign="top" scope="row">Muat Naik Lampiran</td>
    <td align="left" valign="top" scope="row">:</td>
    <td><span class="style40"><em>Tiada lampiran di masukkan dalam sistem eTapp, dokumen fizikal akan dihantar ke pegawai yang berkenaan.</em></span></td>
  </tr>
    #else
    	<tr>
    <td align="left" valign="top" scope="row">&nbsp;</td>
    <td align="left" valign="top" scope="row">Muat Naik Lampiran</td>
    <td align="left" valign="top" scope="row">:</td>
    <td>
    #foreach($paparLampiranDokumenMasuk in $SenaraiListLampiranDokumenMasuk)
      <table width="100%" border="0">
      <tr>
        <td>$paparLampiranDokumenMasuk.bil - <a href="javascript:papar_Lampiran('$paparLampiranDokumenMasuk.id_lampiran')" class="style1">$paparLampiranDokumenMasuk.nama_fail</a></td>
      </tr>
    </table>
      #end</td>
  </tr>
    #end
  #elseif($mode == 'New')
 <tr>
    <td align="left" valign="top" scope="row">&nbsp;</td>
    <td align="left" valign="top" scope="row">Muat Naik Lampiran</td>
    <td align="left" valign="top" scope="row">:</td>
    <td><input name="txtLampiran" type="file" id="txtLampiran" size="50" /></td>
  </tr>
    <tr>
    <td align="left" valign="top" scope="row">&nbsp;</td>
    <td align="left" valign="top" scope="row"></td>
    <td align="left" valign="top" scope="row"></td>
    <td><span class="style40">* <em>Jika dokumen tidak dimasukkan ke sistem eTapp, sila bawa dokumen fizikal kepada pegawai yang berkenaan.</em></span></td>
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
    <td width="1%" align="left" valign="top" scope="row">&nbsp;</td>
    <td align="left" valign="top" scope="row">No Kertas Minit</td>
    <td align="left" valign="top" scope="row">:</td>
    <td>
      <label>
      <input name="bil_Minit_Dokumen" type="text" id="bil_Minit_Dokumen" value = "$!bil_Minit_Dokumen" onblur="this.value=this.value.toUpperCase();" onkeyup="this.value=this.value.toUpperCase();" size="3" maxlength="3" $readonlyBil $disabledBil />
      </label></td>
  </tr>

  <tr>
    <td width="1%" align="left" valign="top" scope="row">&nbsp;</td>
    <td align="left" valign="top" scope="row">No Rujukan Dokumen</td>
    <td align="left" valign="top" scope="row">:</td>
    <td>
      <label>
      #if($no_Rujukan_Dokumen != '')
       <input name="no_Rujukan_Dokumen" type="text" id="no_Rujukan_Dokumen" value = "$!no_Rujukan_Dokumen" size="44" onblur="this.value=this.value.toUpperCase();" style="text-transform:uppercase;" $readonlyNo $disabledNo/>
       #else
        <input name="no_Rujukan_Dokumen" type="text" id="no_Rujukan_Dokumen" value = "$!no_Rujukan_Lain" size="44" onblur="this.value=this.value.toUpperCase();" style="text-transform:uppercase;" $readonlyNo $disabledNo/>
        #end
      </label></td>
  </tr>

  <tr>
    <td align="left" valign="top" scope="row"><span class="style40">*</span></td>
    <td align="left" valign="top" scope="row">Tarikh Penerimaan Dokumen</td>
    <td align="left" valign="top" scope="row">:</td>
    <td><input name="tarikh_Dokumen_Diterima" type="text" id="tarikh_Dokumen_Diterima" value="$!tarikh_Dokumen_Diterima" size="10" $readonly $disabled/>
      <a href="javascript:displayDatePicker('tarikh_Dokumen_Diterima',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a></td>
  </tr>
  <tr>
    <td align="left" valign="top" scope="row"><span class="style40">*</span></td>
    <td align="left" valign="top" scope="row">No Rujukan Lain</td>
    <td align="left" valign="top" scope="row">&nbsp;</td>
    <td><input name="no_Rujukan_Lain" type="text" id="no_Rujukan_Lain" value = "$!no_Rujukan_Lain" size="44" onblur="this.value=this.value.toUpperCase();" style="text-transform:uppercase;" $readonly $disabled/></td>
  </tr>
  <tr>
    <td align="left" valign="top" scope="row"><span class="style40">*</span></td>
    <td align="left" valign="top" scope="row">Tarikh Dokumen</td>
    <td align="left" valign="top" scope="row">:</td>
    <td><input name="tarikh_Dokumen" type="text" id="tarikh_Dokumen" value="$!tarikh_Dokumen" size="10" $readonly $disabled/>
      <a href="javascript:displayDatePicker('tarikh_Dokumen',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a></td>
  </tr>
  <tr>
    <td align="left" valign="top" scope="row"><span class="style40">*</span></td>
    <td align="left" valign="top" scope="row">Daripada Siapa</td>
    <td align="left" valign="top" scope="row">:</td>
    <td><textarea name="pengirim_Dokumen" cols="70" rows="6" id="pengirim_Dokumen" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase();" $readonly $disabled>$!pengirim_Dokumen</textarea></td>
  </tr>
  <tr>
    <td align="left" valign="top" scope="row"><span class="style40">*</span></td>
    <td align="left" valign="top" scope="row">Perkara</td>
    <td align="left" valign="top" scope="row">:</td>
    <td>
     #if($mode == 'New' || $mode == 'Update' || $mode == 'NewLog')
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
    <td width="1%" align="left" valign="top" scope="row"><span class="style40">*</span></td>
    <td align="left" valign="top" scope="row">Dirujukkan Kepada</td>
    <td align="left" valign="top" scope="row">:</td>
    <td><select name="socPegawai" id="socPegawai" $readonly $disabled>
      
    		#if ($mode == 'New' || $mode == 'NewLog')
                    
      <option value="" >SILA PILIH</option>
      
                #end
                 #foreach($listPegawaiTinggi in $SenaraiPegawaiTinggi)
                  	#if($selectidPegawaiTinggi == $listPegawaiTinggi.user_id)
                    
      <option value="$listPegawaiTinggi.user_id" selected="selected">$listPegawaiTinggi.user_name</option>
      
                    #else
                    
      <option value="$listPegawaiTinggi.user_id" >$listPegawaiTinggi.user_name</option>
      
                  #end
                  #end
        
    </select></td>
  </tr>
  
  <tr>
    <td scope="row">&nbsp;</td>
    <td scope="row">Salinan Kepada</td>
    <td align="left" valign="top" scope="row">:</td>
    <td><select name="socPA" id="socPA" $readonly $disabled>
     #if ($mode == 'View' || $mode == 'New' || $mode=='NewLog' || $mode == 'Update')
      <option value="" >SILA PILIH</option>
     #end
                 #foreach($listPA in $SenaraiPA)
                  	#if($selectidPA == $listPA.user_id)
                    
      
      <option value="$listPA.user_id" selected="selected">$listPA.user_name </option>
      
      
                    #else
                    
      
      <option value="$listPA.user_id" >$listPA.user_name </option>
      
      
                  #end
                  #end
        
    
    </select>
    </td>
  </tr>
  <tr>
    <td scope="row">&nbsp;</td>
    <td scope="row">&nbsp;</td>
    <td align="left" valign="top" scope="row">&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td colspan="4" scope="row"><span class="style5 style47 style69 style45 style6"><span class="style40">Perhatian </span>: Pastikan label berwarna <span class="style40">*</span> wajib diisi.</span></td>
  </tr>
   #if($portal_role == 'adminpfd')
  <tr>
    <td colspan="4" align="center" scope="row">
    #if ($mode == 'View')         #end   </td>
  </tr>
  #else
  <tr>
    <td colspan="4" align="center" scope="row">
   
   
#if($paparArahan == 'false')
  
    #if ($mode == 'View')
        <input type="button" name="cmdKemaskini3" id="cmdKemaskini3" value="Kemaskini" onclick = "kemaskini()"/>
        <input name="cmdTambahDokumen2" type="button" value="Tambah" onclick="tambahDokumen()" />
        <input name="cmdHapus3" type="button" value="Hapus" onclick="hapus()" />
        <input type="button" name="cmdKembali1" id="cmdKembali1" value="Kembali" onclick="kembaliTabMasuk('$!idDokumen')"/>
    #end
    #if($mode == 'New')
    
        <input type="button" name="cmdSimpan2" id="cmdSimpan2" value="Simpan" onclick = "simpanDokumen()"/>
        <input type="button" name="cmdBatal3" id="cmdBatal3" value="Batal" onclick="batal()"/>
        <input type="button" name="cmdKembali2" id="cmdKembali2" value="Kembali" onclick="kembaliSenarai()"/> 
    #end
    #if($mode == 'NewLog')
        <input type="button" name="cmdSimpan2" id="cmdSimpan2" value="Simpan" onclick = "simpanDokumenLog()"/>
        <input type="button" name="cmdBatal3" id="cmdBatal3" value="Batal" onclick="batal()"/>
        <input type="button" name="cmdKembali2" id="cmdKembali2" value="Kembali" onclick="kembaliSenarai()"/>
    #end
#end
#if($mode == 'Disable')

<span class="style40">* Dokumen telah mencapai bilangan 100. Anda diminta membuka Fail Jilid Baru.</span>#end 
        #if($mode == 'Update')
        <input type="button" name="cmdSimpan3" id="cmdSimpan3" value="Simpan" onclick = "updateDokumen()"/>
<input type="button" name="cmdBatal3" id="cmdBatal3" value="Batal" onclick="batalView()"/>
<input type="button" name="cmdKembali3" id="cmdKembali3" value="Kembali" onclick="kembaliTabMasuk('$!idDokumen')"/>
#end
        #if($mode == 'PaparUpdate')
<input type="button" name="cmdKembali4" id="cmdKembali4" value="Kembali" onclick="kembaliTabMasuk('$!idDokumen')"/>
#end</td>
  </tr>
  #end
</table>
<input name="user_Role" id="user_Role" type="hidden" value="$!user_Role"/>
</fieldset>
    </div>  	
    <div class="TabbedPanelsContent"></div>
    <div class="TabbedPanelsContent"></div>
    
    
  </div>
</div>
</fieldset>
&nbsp;

#if($action1 != 'papar')
<fieldset>
<legend>SENARAI DOKUMEN</legend>
<p><span class="style5 style47 style69 style45 style6"><span class="style40">Perhatian </span>:</span><span class="style5 style47 style69 style45 style6">Dokumen berwarna <span class="style40">merah</span> - DOKUMEN MASUK, </span><span class="style5 style47 style69 style45 style6">Dokumen berwarna hitam - DOKUMEN KELUAR dan </span><span class="style5 style47 style69 style45 style6">Dokumen berwarna <span class="style43">hijau</span> - DOKUMEN DALAMAN</span></p>
<table width="100%">
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
    <td class="$row"><span class="$row style40 style41">$!counter</span></td>
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
    <td class="$row"><input type="button" name="cmdTambahLampiran" id="cmdTambahLampiran" value="Lampiran" onclick="tambahLampiran1()"/></td>
  </tr>
  #end
#end
#set($PrevDocID = $fail.id_Dokumen)
  #end
</table>
</fieldset>
#end
#end

#parse("app/pfd/check_roles.jsp")
#if ($rolePFD_Dokumen == true)
    #if($paparArahan == 'true' || $action1 == 'tambahMinitMasuk' || $action1 == 'simpanMinitMasuk')
    <fieldset>
    <legend>SENARAI TUGASAN</legend>
    <p>
    #if($levelArahan == '1')
    	 #if($idNegeri == '16')
    <input name="cmdTambahMinit" type="button" value="Agihan Tugasan / Tugasan Selesai" onclick = "tambahMinit('$id_Minitarahan','$idDokumen')"/>
        <input name="cmdTambahMinit2" type="button" value="Hantar Dokumen ke Seksyen Lain" onclick = "papar_seksyenLain('$idDokumen')"/>
        #else
        <input name="cmdTambahMinit" type="button" value="Agihan Tugasan / Tugasan Selesai" onclick = "tambahMinit('$id_Minitarahan','$idDokumen')"/>
        #end
    #else
    	#if($getUser_size == '0')
     <input name="cmdTambahMinit" type="button" value="Agihan Tugasan / Tugasan Selesai" onclick = "tambahMinit('$id_Minitarahan','$idDokumen')"/>
     	#else

        #end
    #end
    <p>
#if($hiddenSeksyenLain == 'true')
    #if($hidden1 == 'false')
         #if($modeLevel == '1' || $action1 == 'papar')
    <fieldset>
        #set ($PrevPegawaiMengarah = '')
         #foreach ($listMinitArahanPengarah in $SenaraiMinitArahanPengarah)
          #if ($PrevPegawaiMengarah != $listMinitArahanPengarah.pegawaiMengarah)
    <p><strong>Pegawai Yang Mengarah</strong> : $listMinitArahanPengarah.pegawaiMengarah</p>
           #if($listMinitArahanPengarah.idPegawaiPengarah == $user_Id )
    <p><strong>Arahan</strong> :<a href="javascript:edit_item1('$listMinitArahanPengarah.idMinitArahan')" class="style1"> $listMinitArahanPengarah.minitArahan </a> </p>
            #else
    <p><strong>Arahan</strong> : $listMinitArahanPengarah.minitArahan </a> </p>
            #end
    <table width="100%" align="center" >
            <tr class="table_header">
            <td width="1%" scope="row"><strong>No</strong></td>
            <td width="20%"><strong>No Rujukan Dokumen</strong></td>
            <td width="29%"><strong>Pegawai Terima Arahan</strong></td>
            <td width="20%"><strong>Tarikh</strong></td>
          </tr>
            #set ($PrevPegawaiMengarah = $listMinitArahanPengarah.pegawaiMengarah)
          #end
           #if ($listMinitArahanPengarah.bil == '') 
            #set ($row = 'row1')
          #elseif ($listMinitArahanPengarah.bil % 2 != 0)
            #set ($row = 'row1')
          #else 
            #set ($row = 'row2')
          #end 
      <tr>
        <td class="$row">$listMinitArahanPengarah.bil</td>
                #if ($listMinitArahanPengarah.bil != '') 
                <td class="$row">$!listMinitArahanPengarah.noRujukanDokumen</a></td>
                #else
                <td class="$row">$!listMinitArahanPengarah.noRujukanDokumen</td>
                #end
        <td class="$row">$listMinitArahanPengarah.pegawaiMenerima</td>
            <td class="$row">$listMinitArahanPengarah.tarikh</td>
          </tr>
          #end
    </table>
        </fieldset>
        #end
    #else
    #end
    <br>
    #if($hidden2 == 'false')
         #if($modeLevel == '2' || $action1 == 'papar')
         
            <fieldset>
        #set ($PrevPegawaiMengarah = '')
         #foreach ($listMinitArahanPegawai1 in $SenaraiMinitArahanPegawai1)
          #if ($PrevPegawaiMengarah != $listMinitArahanPegawai1.pegawaiMengarah)
            <p><strong>Pegawai Yang Menerima Arahan</strong> : $listMinitArahanPegawai1.pegawaiMengarah</p>
            <p><strong>Status :</strong> <span class="$row">$listMinitArahanPegawai1.statusTindakan</span></p>
           #if($listMinitArahanPegawai1.idPegawaiPengarah == $user_Id )
            <p><strong>Arahan</strong> :<a href="javascript:edit_item1('$listMinitArahanPegawai1.idMinitArahan')" class="style1"> $listMinitArahanPegawai1.minitArahan </a> </p>
            #else
                    <p><strong>Arahan</strong> : $listMinitArahanPegawai1.minitArahan </a> </p>
            #end
            <table width="100%" align="center" >
            <tr class="table_header">
            <td width="1%" scope="row"><strong>No</strong></td>
            <td width="20%"><strong>No Rujukan Dokumen</strong></td>
            <td width="29%"><strong>Pegawai Terima Arahan</strong></td>
            <td width="20%"><strong>Tarikh</strong></td>
          </tr>
            #set ($PrevPegawaiMengarah = $listMinitArahanPegawai1.pegawaiMengarah)
          #end
           #if ($listMinitArahanPegawai1.bil == '') 
            #set ($row = 'row1')
          #elseif ($listMinitArahanPegawai1.bil % 2 != 0)
            #set ($row = 'row1')
          #else 
            #set ($row = 'row2')
          #end 
          <tr>
            <td class="$row">$listMinitArahanPegawai1.bil</td>
                #if ($listMinitArahanPegawai1.bil != '') 
                <td class="$row">$!listMinitArahanPegawai1.noRujukanDokumen</td>
                #else
                <td class="$row">$!listMinitArahanPegawai1.noRujukanDokumen</td>
                #end
            <td class="$row">$listMinitArahanPegawai1.pegawaiMenerima</td>
            <td class="$row">$listMinitArahanPegawai1.tarikh</td>
          </tr>
          #end
        </table>
        </fieldset>
        #end
    #else
    #end
    <br>
    #if($hidden3 == 'false')
        #if($modeLevel == '3' || $action1 == 'papar')
         <fieldset $hidden3>
        #set ($PrevPegawaiMengarah = '')
         #foreach ($listMinitArahanPegawai2 in $SenaraiMinitArahanPegawai2)
          #if ($PrevPegawaiMengarah != $listMinitArahanPegawai2.pegawaiMengarah)
            <p><strong>Pegawai Yang Menerima Arahan</strong> : $listMinitArahanPegawai2.pegawaiMengarah</p>
            <p><strong>Status :</strong> <span class="$row">$listMinitArahanPegawai2.statusTindakan</span></p>
                #if($listMinitArahanPegawai2.idPegawaiPengarah == $user_Id )
            <p><strong>Arahan</strong> :<a href="javascript:edit_item1('$listMinitArahanPegawai2.idMinitArahan')" class="style1"> $listMinitArahanPegawai2.minitArahan </a> </p>
            #else
                    <p><strong>Arahan</strong> : $listMinitArahanPegawai2.minitArahan </a> </p>
            #end
            <table width="100%" align="center" >
            <tr class="table_header">
            <td width="1%" scope="row"><strong>No</strong></td>
            <td width="20%"><strong>No Rujukan Dokumen</strong></td>
            <td width="29%"><strong>Pegawai Terima Arahan</strong></td>
            <td width="20%"><strong>Tarikh</strong></td>
          </tr>
            #set ($PrevPegawaiMengarah = $listMinitArahanPegawai2.pegawaiMengarah)
          #end
           #if ($listMinitArahanPegawai2.bil == '') 
            #set ($row = 'row1')
          #elseif ($listMinitArahanPegawai2.bil % 2 != 0)
            #set ($row = 'row1')
          #else 
            #set ($row = 'row2')
          #end 
          <tr>
            <td class="$row">$listMinitArahanPegawai2.bil</td>
                #if ($listMinitArahanPegawai2.bil != '') 
                <td class="$row">$!listMinitArahanPegawai2.noRujukanDokumen</td>
                #else
                <td class="$row">$!listMinitArahanPegawai2.noRujukanDokumen</td>
                #end
            <td class="$row">$listMinitArahanPegawai2.pegawaiMenerima</td>
            <td class="$row">$listMinitArahanPegawai2.tarikh</td>
          </tr>
          #end
        </table>
        </fieldset>
        #end
    #else
    #end
    <br>
    #if($hidden4 == 'false')
        #if($modeLevel == '4' || $action1 == 'papar')
         <fieldset $hidden3>
        #set ($PrevPegawaiMengarah = '')
         #foreach ($listMinitArahanPegawai3 in $SenaraiMinitArahanPegawai3)
          #if ($PrevPegawaiMengarah != $listMinitArahanPegawai3.pegawaiMengarah)
            <p><strong>Pegawai Yang Menerima Arahan</strong> : $listMinitArahanPegawai3.pegawaiMengarah</p>
            <p><strong>Status :</strong> <span class="$row">$listMinitArahanPegawai3.statusTindakan</span></p>
            #if($listMinitArahanPegawai3.idPegawaiPengarah == $user_Id )
            <p><strong>Arahan</strong> :<a href="javascript:edit_item1('$listMinitArahanPegawai3.idMinitArahan')" class="style1"> $listMinitArahanPegawai3.minitArahan </a> </p>
            #else
                    <p><strong>Arahan</strong> : $listMinitArahanPegawai3.minitArahan </a> </p>
            #end
            <table width="100%" align="center" >
            <tr class="table_header">
            <td width="1%" scope="row"><strong>No</strong></td>
            <td width="20%"><strong>No Rujukan Dokumen</strong></td>
            <td width="29%"><strong>Pegawai Terima Arahan</strong></td>
            <td width="20%"><strong>Tarikh</strong></td>
          </tr>
            #set ($PrevPegawaiMengarah = $listMinitArahanPegawai3.pegawaiMengarah)
          #end
           #if ($listMinitArahanPegawai3.bil == '') 
            #set ($row = 'row1')
          #elseif ($listMinitArahanPegawai3.bil % 2 != 0)
            #set ($row = 'row1')
          #else 
            #set ($row = 'row2')
          #end 
          <tr>
            <td class="$row">$listMinitArahanPegawai3.bil</td>
                #if ($listMinitArahanPegawai3.bil != '') 
                <td class="$row">$!listMinitArahanPegawai3.noRujukanDokumen</td>
                #else
                <td class="$row">$!listMinitArahanPegawai3.noRujukanDokumen</td>
                #end
            <td class="$row">$listMinitArahanPegawai3.pegawaiMenerima</td>
            <td class="$row">$listMinitArahanPegawai3.tarikh</td>
          </tr>
          #end
        </table>
        </fieldset>
        #end
    #else
    #end
    <br>
    #if($hidden5 == 'false')
        #if($modeLevel == '5' || $action1 == 'papar')
         <fieldset $hidden4>
        #set ($PrevPegawaiMengarah = '')
         #foreach ($listMinitArahanPegawai4 in $SenaraiMinitArahanPegawai4)
          #if ($PrevPegawaiMengarah != $listMinitArahanPegawai4.pegawaiMengarah)
            <p><strong>Pegawai Yang Menerima Arahan</strong> : $listMinitArahanPegawai4.pegawaiMengarah</p>
            <p><strong>Status :</strong> <span class="$row">$listMinitArahanPegawai4.statusTindakan</span></p>
            #if($listMinitArahanPegawai4.idPegawaiPengarah == $user_Id )
            <p><strong>Arahan</strong> :<a href="javascript:edit_item1('$listMinitArahanPegawai4.idMinitArahan')" class="style1"> $listMinitArahanPegawai4.minitArahan </a> </p>
            #else
                    <p><strong>Arahan</strong> : $listMinitArahanPegawai4.minitArahan </a> </p>
            #end
            <table width="100%" align="center" >
            <tr class="table_header">
            <td width="1%" scope="row"><strong>No</strong></td>
            <td width="20%"><strong>No Rujukan Dokumen</strong></td>
            <td width="29%"><strong>Pegawai Terima Arahan</strong></td>
            <td width="20%"><strong>Tarikh</strong></td>
          </tr>
            #set ($PrevPegawaiMengarah = $listMinitArahanPegawai4.pegawaiMengarah)
          #end
           #if ($listMinitArahanPegawai4.bil == '') 
            #set ($row = 'row1')
          #elseif ($listMinitArahanPegawai4.bil % 2 != 0)
            #set ($row = 'row1')
          #else 
            #set ($row = 'row2')
          #end 
          <tr>
            <td class="$row">$listMinitArahanPegawai4.bil</td>
                #if ($listMinitArahanPegawai4.bil != '') 
                <td class="$row">$!listMinitArahanPegawai4.noRujukanDokumen</td>
                #else
                <td class="$row">$!listMinitArahanPegawai4.noRujukanDokumen</td>
                #end
            <td class="$row">$listMinitArahanPegawai4.pegawaiMenerima</td>
            <td class="$row">$listMinitArahanPegawai4.tarikh</td>
          </tr>
          #end
        </table>
        </fieldset>
        #end
    #else
    #end
    <br>
    #if($hiddenSelesai == 'false')
        #if($modeLevel == 'selesai' || $action1 == 'papar')
            <fieldset $hiddenSelesai>
            <br>
            <table width="100%" align="center" >
            <tr class="table_header">
            <td width="1%" scope="row"><strong>No</strong></td>
            <td width="20%"><strong>Pegawai Terima Arahan</strong></td>
            <td width="20%"><strong>Tarikh</strong></td>
            <td width="20%"><strong>Status</strong></td>
          </tr>
           #foreach ($listMinitArahanSelesai in $SenaraiMinitArahanSelesai)
           #if ($listMinitArahanSelesai.bil == '') 
            #set ($row = 'row1')
          #elseif ($listMinitArahanSelesai.bil % 2 != 0)
            #set ($row = 'row1')
          #else 
            #set ($row = 'row2')
          #end 
          <tr>
            <td class="$row">$listMinitArahanSelesai.bil</td>
                #if ($listMinitArahanSelesai.bil != '') 
                <td class="$row">$listMinitArahanSelesai.pegawaiMengarah</td>
                #else
                <td class="$row">$listMinitArahanSelesai.pegawaiMengarah</td>
                #end
    
            <td class="$row">$listMinitArahanSelesai.tarikh</td>
            <td class="$row">$listMinitArahanSelesai.statusTindakan</td>
          </tr>
          #end
        </table>
        </fieldset>
        #end
    #else
    #end
<br>
#else
 #if($hiddenSeksyenLain == 'false')
    #if($modeLevel == 'seksyenLain' || $action1 == 'papar')
     <fieldset $hiddenSeksyenLain>
    #set ($PrevPegawaiMengarah = '')
     #foreach ($listMinitArahanSeksyenLain in $SenaraiMinitArahanSeksyenLain)
      #if ($PrevPegawaiMengarah != $listMinitArahanSeksyenLain.pegawaiMengarah)
        <p><strong>Pegawai Yang Menerima Arahan</strong> : $listMinitArahanSeksyenLain.pegawaiMengarah</p>
        <p><strong>Status :</strong> <span class="$row">Hantar ke Seksyen Lain</span></p>
            #if($listMinitArahanSeksyenLain.idPegawaiPengarah == $user_Id )
        <p><strong>Catatan</strong> :<a href="javascript:edit_item1('$listMinitArahanSeksyenLain.id_minitarahanseklain')" class="style1"> $listMinitArahanSeksyenLain.catatan </a> </p>
        #else
                <p><strong>Catatan</strong> : $listMinitArahanSeksyenLain.catatan </a> </p>
        #end
        <table width="100%" align="center" >
        <tr class="table_header">
        <td width="1%" scope="row"><strong>No</strong></td>
        <td width="20%"><strong>Nama Seksyen</strong></td>
        <td width="29%"><strong>Pegawai Terima Arahan</strong></td>
        <td width="20%"><strong>Tarikh</strong></td>
      </tr>
        #set ($PrevPegawaiMengarah = $listMinitArahanSeksyenLain.pegawaiMengarah)
      #end
       #if ($listMinitArahanSeksyenLain.bil == '') 
        #set ($row = 'row1')
      #elseif ($listMinitArahanSeksyenLain.bil % 2 != 0)
        #set ($row = 'row1')
      #else 
        #set ($row = 'row2')
      #end 
      <tr>
        <td class="$row">$listMinitArahanSeksyenLain.bil</td>
            #if ($listMinitArahanSeksyenLain.bil != '') 
            <td class="$row">$!listMinitArahanSeksyenLain.namaSeksyen</td>
            #else
            <td class="$row">$!listMinitArahanSeksyenLain.namaSeksyen</td>
            #end
        <td class="$row">$listMinitArahanSeksyenLain.pegawaiMenerima</td>
        <td class="$row">$listMinitArahanSeksyenLain.tarikh</td>
      </tr>
      #end
    </table>
    </fieldset>
    #end
#else
#end
<br>
#end
 
</fieldset>
    #end
#else

#end
<script type="text/javascript">
<!--
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:0});
//-->
</script>
<script>
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
function tabDokumenDalaman(){
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=tabDokumenDalaman";
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
	
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=papar&paparArahan=false";
	document.${formName}.idDokumen.value = id;
	document.${formName}.mode.value = "paparDokumen";
	document.${formName}.submit();	
}
function papar_dokumenSubjaket(idFailSubjaket){
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=paparDokumenFailSubjaket&idFailSubjaket="+idFailSubjaket;
	document.${formName}.submit();
}
function paparMinit(id){
	
	document.${formName}.idMinitarahan.value = id;
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=paparMinitMasuk";
	document.${formName}.submit();
	
}

function simpanDokumen(){
	//var DateMasuk = document.${formName}.txtTkhDokumenMasuk.value;
	
	var editorInstance = FCKeditorAPI.GetInstance('tajuk_Dokumen');   
                var tajuk_Dokumen = editorInstance.GetHTML(true);
       			var textlength = tajuk_Dokumen.length;
                
                
                if(textlength==0)
                {
                alert('Sila masukkan " Tarikh Dokumen " terlebih dahulu.');
                oEditor.EditorDocument.body.focus();
                return;
                }

		
	
	
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
	if (document.${formName}.txtJumDok.value == "98"){
		if ( !window.confirm('Dokumen telah mencapai bilangan 98. Adakah anda ingin meneruskan simpanan dokumen? ')) return;
	}
	
	if (document.${formName}.txtJumDok.value == "99"){
		if ( !window.confirm('Dokumen telah mencapai bilangan 99. Adakah anda ingin meneruskan simpanan dokumen? ')) return;
	}

	if ( !window.confirm("Anda Pasti?") ) return;
	
	var ft = document.${formName}.form_token.value;
	
	alert(document.${formName}.socPA.value);
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=simpanDokumenMasuk&idFail="+document.${formName}.idFail.value+"&socJenisDokumen="+document.${formName}.socJenisDokumen.value+"&bil_Minit_Dokumen="+document.${formName}.bil_Minit_Dokumen.value+"&no_Rujukan_Dokumen="+escape(document.${formName}.no_Rujukan_Dokumen.value)+"&no_Rujukan_Lain="+document.${formName}.no_Rujukan_Lain.value+"&tajuk_Dokumen="+escape(tajuk_Dokumen)+"&pengirim_Dokumen="+document.${formName}.pengirim_Dokumen.value+"&socPegawai="+document.${formName}.socPegawai.value+"&socPA="+document.${formName}.socPA.value+"&tarikh_Dokumen_Diterima="+document.${formName}.tarikh_Dokumen_Diterima.value+"&tarikh_Dokumen="+document.${formName}.tarikh_Dokumen.value+"&txtJumDok="+document.${formName}.txtJumDok.value+"&c1="+document.${formName}.c1.checked+"&c2="+document.${formName}.c2.checked+"&form_token="+ft+"&tag_dokumen="+escape(document.${formName}.tag_dokumen.value);
	//&idDokumen="+document.${formName}.idDokumen.value+"
	
	
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

	//alert("Test");
	var ft = document.${formName}.form_token.value;
	//alert("x= "+document.${formName}.form_token.value);
	
	//var DateMasuk = document.${formName}.txtTkhDokumenMasuk.value;
	//var DateDiterima = document.${formName}.tarikh_Dokumen_Diterima.value;
	//var DateDokumen = document.${formName}.tarikh_Dokumen.value;
	
	var d = new Date();
	var curr_date = d.getDate();
	var curr_month = d.getMonth()+1;
	//curr_month++
	var curr_year = d.getFullYear();

   	var today = curr_date +'/' + curr_month +'/' + curr_year;
	
	
	       var str1  = document.${formName}.tarikh_Dokumen_Diterima.value;	   
		   var dt1   = parseInt(str1.substring(0,2),10);
		   var mon1  = parseInt(str1.substring(3,5),10)-1;
		   var yr1   = parseInt(str1.substring(6,10),10);		   
		   var DateDiterima = new Date(yr1, mon1, dt1);
		   
		   var str2  = document.${formName}.tarikh_Dokumen.value;	   
		   var dt2   = parseInt(str2.substring(0,2),10);
		   var mon2  = parseInt(str2.substring(3,5),10)-1;
		   var yr2  = parseInt(str2.substring(6,10),10);		   
		   var DateDokumen = new Date(yr2, mon2, dt2);
	
	
	  /*     var currentTime = new Date();
		   var month = currentTime.getMonth() + 1;
		   var day = currentTime.getDate();
		   var year = currentTime.getFullYear();
		   var currentDate = day + "/" + month + "/" + year;
			
		   var str1  = DateField.value;
		   
		   var dt1   = parseInt(str1.substring(0,2),10);
		   var mon1  = parseInt(str1.substring(3,5),10)-1;
		   var yr1   = parseInt(str1.substring(6,10),10);
		   
		   var date = new Date(yr1, mon1, dt1);
		*/
	if( DateDokumen > d)
	{
		alert('Sila pastikan " Tarikh Dokumen Masuk " tidak melebihi tarikh hari ini.');
		document.${formName}.tarikh_Dokumen.focus();
		return false;
	}
	if( DateDiterima > d)
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
	
	/*if (document.${formName}.bil_Minit_Dokumen.value == ""){
		alert('Sila masukkan " No Kertas Minit " terlebih dahulu.');
		document.${formName}.bil_Minit_Dokumen.focus();
		return;
	}
	if (document.${formName}.no_Rujukan_Dokumen.value == ""){
		alert('Sila masukkan " No Rujukan Dokumen " terlebih dahulu.');
		document.${formName}.no_Rujukan_Dokumen.focus();
		return;
	}*/
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
	if (document.${formName}.txtJumDok.value == "98"){
		if ( !window.confirm('Dokumen telah mencapai bilangan 98. Adakah anda ingin meneruskan simpanan dokumen? ')) return;
	}
	
	if (document.${formName}.txtJumDok.value == "99"){
		if ( !window.confirm('Dokumen telah mencapai bilangan 99. Adakah anda ingin meneruskan simpanan dokumen? ')) return;
	}

	//alert("Test1");
	if ( !window.confirm("Anda Pasti?") ) return;
	

	
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=simpanDokumenMasuk&idFail="+document.${formName}.idFail.value+"&idLogDokumen="+document.${formName}.idLogDokumen.value+"&socJenisDokumen="+document.${formName}.socJenisDokumen.value+"&bil_Minit_Dokumen="+document.${formName}.bil_Minit_Dokumen.value+"&no_Rujukan_Dokumen="+escape(document.${formName}.no_Rujukan_Dokumen.value)+"&no_Rujukan_Lain="+escape(document.${formName}.no_Rujukan_Lain.value)+"&tajuk_Dokumen="+escape(document.${formName}.tajuk_Dokumen.value)+"&pengirim_Dokumen="+escape(document.${formName}.pengirim_Dokumen.value)+"&socPegawai="+document.${formName}.socPegawai.value+"&socPA="+document.${formName}.socPA.value+"&tarikh_Dokumen_Diterima="+document.${formName}.tarikh_Dokumen_Diterima.value+"&tarikh_Dokumen="+document.${formName}.tarikh_Dokumen.value+"&txtJumDok="+document.${formName}.txtJumDok.value+"&c1="+document.${formName}.c1.checked+"&c2="+document.${formName}.c2.checked+"&c3="+document.${formName}.c3.checked+"&form_token="+ft;
	
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
	
	
	       var str1  = document.${formName}.tarikh_Dokumen_Diterima.value;	   
		   var dt1   = parseInt(str1.substring(0,2),10);
		   var mon1  = parseInt(str1.substring(3,5),10)-1;
		   var yr1   = parseInt(str1.substring(6,10),10);		   
		   var DateDiterima = new Date(yr1, mon1, dt1);
		   
		   var str2  = document.${formName}.tarikh_Dokumen.value;	   
		   var dt2   = parseInt(str2.substring(0,2),10);
		   var mon2  = parseInt(str2.substring(3,5),10)-1;
		   var yr2  = parseInt(str2.substring(6,10),10);		   
		   var DateDokumen = new Date(yr2, mon2, dt2);
	
	
	  /*     var currentTime = new Date();
		   var month = currentTime.getMonth() + 1;
		   var day = currentTime.getDate();
		   var year = currentTime.getFullYear();
		   var currentDate = day + "/" + month + "/" + year;
			
		   var str1  = DateField.value;
		   
		   var dt1   = parseInt(str1.substring(0,2),10);
		   var mon1  = parseInt(str1.substring(3,5),10)-1;
		   var yr1   = parseInt(str1.substring(6,10),10);
		   
		   var date = new Date(yr1, mon1, dt1);
		*/
	if( DateDokumen > d)
	{
		alert('Sila pastikan " Tarikh Dokumen Masuk " tidak melebihi tarikh hari ini.');
		document.${formName}.tarikh_Dokumen.focus();
		return false;
	}
	if( DateDiterima > d)
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

	if ( !window.confirm("Anda Pasti?") ) return;
	
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=updateDokumenMasuk";
	document.${formName}.submit();	

}

function tambahMinit(idMinitArahan,id){
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=tambahPeg3Masuk&idMinitArahan="+idMinitArahan+"&idDokumen="+id;
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
function kemaskiniMinit(id){
	document.${formName}.id_Minitarahan.value = id;
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
function edit_item1(id_Minitarahan){
	document.${formName}.idMinitarahan.value = id_Minitarahan;
	document.${formName}.action ="?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=paparMinitMasuk";
	document.${formName}.submit();
}

function pilihfail() {
	var url = "../x/${securityToken}/ekptg.view.pfd.FrmPopupSenaraiFailAll";
    var hWnd = window.open(url,'printuser','width=900,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function refreshFromPilihFailLama(idFail) {
	document.${formName}.idFail.value = idFail;
	document.${formName}.submit();
	
}
</script>