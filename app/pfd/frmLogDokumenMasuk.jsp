<style type="text/css">
<!--
.style1 {color: #0033FF}
.style5 {font-size: 10px; color: #FF0000; }
.style40 {color: #FF0000}
.style6 {
	color: #000000;
	font-style: italic;
}
-->
</style>



<input name="paparLampiranLogDokumenMasuk_size" type="hidden" value="$!paparLampiranLogDokumenMasuk_size"/>
<input name="idLogDokumen" type="hidden" value="$!idLogDokumen"/>
<input name="mode" type="hidden" value="$mode" />
<input name="modeJenisDokumen" type="hidden" value="$modeJenisDokumen" />
<input name="action1" type="hidden" value="$action1" />

&nbsp;
<fieldset>
<legend>MAKLUMAT LOG DOKUMEN</legend>

<div id="TabbedPanels1" class="TabbedPanels">
  <ul class="TabbedPanelsTabGroup">
    <li class="TabbedPanelsTab" tabindex="0" >LOG DOKUMEN MASUK</li>
    <li class="TabbedPanelsTab" tabindex="0" onclick="tabLogDokumenKeluar()">LOG DOKUMEN KELUAR</li>
    <li class="TabbedPanelsTab" tabindex="0" onclick="tabLogDokumenDariSeksyenLain()">LOG DOKUMEN DARI SEKSYEN LAIN</li>
    <li class="TabbedPanelsTab" tabindex="0" onclick="tabLogDokumenDalaman()">LOG DOKUMEN DALAMAN</li>
  </ul>
  <div class="TabbedPanelsContentGroup">
  	<div class="TabbedPanelsContent">
     <fieldset>
       <legend>MAKLUMAT LOG DOKUMEN MASUK</legend>
    <table width="100%">
       #if($modeJenisDokumen == '3')
    <tr>
    <td width="2%" align="left" valign="top" scope="row"><span class="style40">*</span></td>
    <td width="27%" align="left" valign="top" scope="row">Jenis Dokumen</td>
    <td width="1%" align="left" valign="top" scope="row">:</td>
    <td width="70%">
      <label></label>    <select name="socJenisDokumen" id="socJenisDokumen" onchange="doChangeJenisDokumen()" $readonly $disabled>
        
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
      
      
      </select></td>
  </tr>
  #if($mode == 'New')
  #end

  <tr>
    <td align="left" valign="top" scope="row">&nbsp;</td>
    <td align="left" valign="top" scope="row">Lampiran</td>
    <td align="left" valign="top" scope="row">:</td>
    <td><input type="checkbox" name="c1" value="1" $checkedc1  $disabled/>
Minit Mesyuarat
  <input type="checkbox" name="c2" value="1" $checkedc2  $disabled/>
Laporan
<input type="checkbox" name="c3" value="1" $checkedc3  $disabled/>
CD</td>
  </tr>
  #if ($mode == 'New' )
  <tr>
    <td align="left" valign="top" scope="row">&nbsp;</td>
    <td align="left" valign="top" scope="row">Muatnaik Lampiran</td>
    <td align="left" valign="top" scope="row">:</td>
    <td>    
    #foreach( $i in [1..10] )
      <input name="txtLampiran" type="file" id="txtLampiran" size="50" /><br>
    #end</td>
  </tr>
  <tr>
    <td align="left" valign="top" scope="row">&nbsp;</td>
    <td align="left" valign="top" scope="row">&nbsp;</td>
    <td align="left" valign="top" scope="row">&nbsp;</td>
    <td><span class="style40">* <em>Jika dokumen tidak dimasukkan ke sistem eTapp, sila bawa dokumen fizikal kepada pegawai yang berkenaan.</em></span></td>
  </tr>
  #else
   #if($paparLampiranLogDokumenMasuk_size == '0')
            <tr>
            <td align="left" valign="top" scope="row">&nbsp;</td>
            <td align="left" valign="top" scope="row">Senarai Lampiran</td>
            <td align="left" valign="top" scope="row">:</td>
            <td><span class="style40"><em>Tiada lampiran di masukkan dalam sistem eTapp, dokumen fizikal akan dihantar ke pegawai yang berkenaan.</em></span></td>
          </tr>
      #else
            <tr>
            <td align="left" valign="top" scope="row">&nbsp;</td>
            <td align="left" valign="top" scope="row">Senarai Lampiran</td>
            <td align="left" valign="top" scope="row">:</td>
            <td>
            #foreach($paparLampiranLogDokumenMasuk in $SenaraiListLampiranLogDokumenMasuk)
              <table width="100%" border="0">
              <tr>
                <td>$paparLampiranLogDokumenMasuk.bil - <a href="javascript:papar_Lampiran('$paparLampiranLogDokumenMasuk.id_lampiran')" class="style1">$paparLampiranLogDokumenMasuk.nama_fail</a></td>
              </tr>
            </table>
              #end    </td>
          </tr>
      #end
  #end
  <tr>
    <td align="left" valign="top" scope="row">&nbsp;</td>
    <td align="left" valign="top" scope="row">Tag Dokumen</td>
    <td align="left" valign="top" scope="row">:</td>
    <td valign="top"><textarea id="tag_dokumen" name="tag_dokumen" cols="70" rows="6" $readonly $disabled>$!tag_Dokumen</textarea>
    <font color="#ff0000"><i> Keyword</i> yang dicadangkan untuk carian</font>
    <input name="id_tagdokumen" type="hidden" value="$id_tagdokumen"/></td>
  </tr>
  <tr>
    <td align="left" valign="top" scope="row">&nbsp;</td>
    <td align="left" valign="top" scope="row">Tarikh Penerimaan Dokumen</td>
    <td align="left" valign="top" scope="row">:</td>
    <td><input name="tarikh_Dokumen_Diterima" type="text" id="tarikh_Dokumen_Diterima" value="$!tarikh_Dokumen_Diterima" size="10" onfocus="tarikh_Dokumen_Diterima();" $readonly  $disabled/>
    <a href="javascript:displayDatePicker('tarikh_Dokumen_Diterima',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a></td>
  </tr>
  <tr>
    <td align="left" valign="top" scope="row">&nbsp;</td>
    <td align="left" valign="top" scope="row">No Rujukan Dokumen</td>
    <td align="left" valign="top" scope="row">:</td>
    <td><input name="no_Rujukan_Lain" type="text" id="no_Rujukan_Lain" value = "$!no_Rujukan_Lain" size="44" onblur="this.value=this.value.toUpperCase();" style="text-transform:uppercase;" $readonly $disabled/></td>
  </tr>
  <tr>
    <td align="left" valign="top" scope="row"><span class="style40">*</span></td>
    <td align="left" valign="top" scope="row">Tarikh Dokumen</td>
    <td align="left" valign="top" scope="row">:</td>
    <td><input name="tarikh_Dokumen" type="text" id="tarikh_Dokumen" value="$!tarikh_Dokumen" size="10" onfocus="tarikh_Dokumen()" $readonly  $disabled/>
    <a href="javascript:displayDatePicker('tarikh_Dokumen',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a></td>
  </tr>
  <tr>
    <td align="left" valign="top" scope="row"><span class="style40">*</span></td>
    <td align="left" valign="top" scope="row">Daripada Siapa</td>
    <td align="left" valign="top" scope="row">:</td>
    <td><textarea name="pengirim_Dokumen" cols="70" rows="6" id="pengirim_Dokumen" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase();" $readonly $disabled>$!pengirim_Dokumen</textarea></td>
  </tr>

  <tr>
    <td  align="left" valign="top" scope="row">&nbsp;</td>
    <td align="left" valign="top" scope="row">Perkara</td>
    <td align="left" valign="top" scope="row">:</td>
    <td> 
    #if($mode == 'New' || $mode == 'Update')
      <textarea name="tajuk_Dokumen" cols="70" rows="6" id="tajuk_Dokumen" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase();" $readonly $disabled> $!tajuk_Dokumen</textarea>
      
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
	
      #end</td>
  </tr>
  <tr>
    <td  align="left" valign="top" scope="row">&nbsp;</td>
    <td align="left" valign="top" scope="row">Dirujukkan Kepada</td>
    <td align="left" valign="top" scope="row">:</td>
    <td><select name="socPegawai" id="socPegawai" $readonly $disabled>
    			#if ($mode == 'New' || $mode == 'Update')
                    <option value="" >SILA PILIH</option>
                #end
                  #foreach($listPegawai in $SenaraiPegawai)
                 
                  	#if($nama_Pegawai == $listPegawai.user_id)
                    <option value="$listPegawai.user_id" selected="selected">$listPegawai.user_name </option>
                    #else
                    <option value="$listPegawai.user_id" >$listPegawai.user_name </option>
                 	 #end
                  #end
        </select></td>
  </tr>
  <tr>
    <td scope="row">&nbsp;</td>
    <td scope="row">Nama PT Fail / PA</td>
    <td align="left" valign="top" scope="row">: </td>
    <td><select name="socPT" id="socPT" $readonlypt $disabledpt />
    
    			#if ($mode == 'New' || $mode == 'Update')
                    <option value="" >SILA PILIH</option>
                #end
                  #foreach($listPT in $SenaraiPTFail)
                    
                  	#if($nama_PTPA == $listPT.user_id)
<option value="$listPT.user_id" selected="selected">$listPT.user_name </option>
                    #else
                    <option value="$listPT.user_id" >$listPT.user_name </option>
                  	#end
                  #end
        </select></td>
  </tr>
  <tr>
    <td scope="row">&nbsp;</td>
    <td scope="row">&nbsp;</td>
    <td align="left" valign="top" scope="row">&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  #if($id_seksyen == '5')
  <tr>
    <td scope="row">&nbsp;</td>
    <td scope="row">Seksyen</td>
    <td align="left" valign="top" scope="row">:</td>
    <td>$selectSeksyen</td>
  </tr>
  <tr>
    <td scope="row">&nbsp;</td>
    <td scope="row">&nbsp;</td>
    <td align="left" valign="top" scope="row">&nbsp;</td>
    <td></td>
  </tr>
  #end
  <tr>
    <td colspan="4" scope="row"><span class="style5 style47 style69 style45 style6"><span class="style40">Perhatian </span>: Pastikan label berwarna <span class="style40">*</span> wajib diisi.</span></td>
  </tr>
   #elseif($modeJenisDokumen == '4')
    <tr>
    <td width="2%" align="left" valign="top" scope="row"><span class="style40">*</span></td>
    <td width="27%" align="left" valign="top" scope="row">Jenis Dokumen</td>
    <td width="1%" align="left" valign="top" scope="row">:</td>
    <td width="70%">
      <label></label>    <select name="socJenisDokumen" id="socJenisDokumen" onchange="doChangeJenisDokumen()" $readonly $disabled>
        
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
      
      
      </select></td>
  </tr>
  #if($mode == 'New')
  #end

  <tr>
    <td align="left" valign="top" scope="row">&nbsp;</td>
    <td align="left" valign="top" scope="row">Lampiran</td>
    <td align="left" valign="top" scope="row">:</td>
    <td><input type="checkbox" name="c1" value="1" $checkedc1  $disabled/>
Minit Mesyuarat
  <input type="checkbox" name="c2" value="1" $checkedc2  $disabled/>
Laporan
<input type="checkbox" name="c3" value="1" $checkedc3  $disabled/>
CD</td>
  </tr>
  #if ($mode == 'New' )
  <tr>
    <td align="left" valign="top" scope="row">&nbsp;</td>
    <td align="left" valign="top" scope="row">Muatnaik Lampiran</td>
    <td align="left" valign="top" scope="row">:</td>
    <td>    
    #foreach( $i in [1..10] )
      <input name="txtLampiran" type="file" id="txtLampiran" size="50" /><br>
    #end</td>
  </tr>
    <tr>
    <td align="left" valign="top" scope="row">&nbsp;</td>
    <td align="left" valign="top" scope="row"></td>
    <td align="left" valign="top" scope="row"></td>
    <td><span class="style40">* <em>Jika dokumen tidak dimasukkan ke sistem eTapp, sila bawa dokumen fizikal kepada pegawai yang berkenaan.</em></span></td>
  </tr>
  #else
  	  #if($paparLampiranLogDokumenMasuk_size == '0')
            <tr>
            <td align="left" valign="top" scope="row">&nbsp;</td>
            <td align="left" valign="top" scope="row">Senarai Lampiran</td>
            <td align="left" valign="top" scope="row">:</td>
            <td><span class="style40"><em>Tiada lampiran di masukkan dalam sistem eTapp, dokumen fizikal akan dihantar ke pegawai yang berkenaan.</em></span></td>
          </tr>
      #else
            <tr>
            <td align="left" valign="top" scope="row">&nbsp;</td>
            <td align="left" valign="top" scope="row">Senarai Lampiran</td>
            <td align="left" valign="top" scope="row">:</td>
            <td>
            #foreach($paparLampiranLogDokumenMasuk in $SenaraiListLampiranLogDokumenMasuk)
              <table width="100%" border="0">
              <tr>
                <td>$paparLampiranLogDokumenMasuk.bil - <a href="javascript:papar_Lampiran('$paparLampiranLogDokumenMasuk.id_lampiran')" class="style1">$paparLampiranLogDokumenMasuk.nama_fail</a></td>
              </tr>
            </table>
              #end    </td>
          </tr>
      #end
  #end
  <tr>
    <td align="left" valign="top" scope="row">&nbsp;</td>
    <td align="left" valign="top" scope="row">Tag Dokumen</td>
    <td align="left" valign="top" scope="row">:</td>
    <td valign="top"><textarea id="tag_dokumen" name="tag_dokumen" cols="70" rows="6" $readonly $disabled>$!tag_Dokumen</textarea>
    <font color="#ff0000"><i> Keyword</i> yang dicadangkan untuk carian</font>
    <input name="id_tagdokumen" type="hidden" value="$id_tagdokumen"/></td>
  </tr>
  <tr>
    <td align="left" valign="top" scope="row"><span class="style40">*</span></td>
    <td align="left" valign="top" scope="row">Tarikh Penerimaan Dokumen</td>
    <td align="left" valign="top" scope="row">:</td>
    <td><input name="tarikh_Dokumen_Diterima" type="text" id="tarikh_Dokumen_Diterima" value="$!tarikh_Dokumen_Diterima" size="10" onfocus="tarikh_Dokumen_Diterima();" $readonly  $disabled/>
      <a href="javascript:displayDatePicker('tarikh_Dokumen_Diterima',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a></td>
  </tr>
  <tr>
    <td align="left" valign="top" scope="row">&nbsp;</td>
    <td align="left" valign="top" scope="row">No Rujukan Dokumen</td>
    <td align="left" valign="top" scope="row">:</td>
    <td><input name="no_Rujukan_Lain" type="text" id="no_Rujukan_Lain" value = "$!no_Rujukan_Lain" size="44" onblur="this.value=this.value.toUpperCase();" style="text-transform:uppercase;" $readonly $disabled/></td>
  </tr>
  <tr>
    <td align="left" valign="top" scope="row">&nbsp;</td>
    <td align="left" valign="top" scope="row">Tarikh Dokumen</td>
    <td align="left" valign="top" scope="row">:</td>
    <td><input name="tarikh_Dokumen" type="text" id="tarikh_Dokumen" value="$!tarikh_Dokumen" size="10" onfocus="tarikh_Dokumen()" $readonly  $disabled/>
      <a href="javascript:displayDatePicker('tarikh_Dokumen',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a></td>
  </tr>
  <tr>
    <td align="left" valign="top" scope="row">&nbsp;</td>
    <td align="left" valign="top" scope="row">Daripada Siapa</td>
    <td align="left" valign="top" scope="row">:</td>
    <td><textarea name="pengirim_Dokumen" cols="70" rows="6" id="pengirim_Dokumen" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase();" $readonly $disabled>$!pengirim_Dokumen</textarea></td>
  </tr>

  <tr>
    <td  align="left" valign="top" scope="row">&nbsp;</td>
    <td align="left" valign="top" scope="row">Perkara</td>
    <td align="left" valign="top" scope="row">:</td>
    <td> #if($mode == 'New' || $mode == 'Update')
      <textarea name="tajuk_Dokumen" cols="70" rows="6" id="tajuk_Dokumen" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase();" $readonly $disabled> $!tajuk_Dokumen</textarea>
      
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
	
    #end</td>
  </tr>
  <tr>
    <td  align="left" valign="top" scope="row"><span class="style40">*</span></td>
    <td align="left" valign="top" scope="row">Dirujukkan Kepada</td>
    <td align="left" valign="top" scope="row">:</td>
    <td><select name="socPegawai" id="socPegawai" $readonly $disabled>
    			#if ($mode == 'New' || $mode == 'Update')
                    <option value="" >SILA PILIH</option>
                #end
                  #foreach($listPegawai in $SenaraiPegawai)
                  	#if($nama_Pegawai == $listPegawai.user_id)
                    <option value="$listPegawai.user_id" selected="selected">$listPegawai.user_name </option>
                    #else
                    <option value="$listPegawai.user_id" >$listPegawai.user_name </option>
                 	 #end
                  #end
        </select></td>
  </tr>
  <tr>
    <td scope="row">&nbsp;</td>
    <td scope="row">Nama PT Fail / PA</td>
    <td align="left" valign="top" scope="row">: </td>
    <td><select name="socPT" id="socPT" $readonlypt $disabledpt />
    
    			#if ($mode == 'New' || $mode == 'Update')
                    <option value="" >SILA PILIH</option>
                #end
                  #foreach($listPT in $SenaraiPTFail)
                  	#if($nama_PTPA == $listPT.user_id)
                    <option value="$listPT.user_id" selected="selected">$listPT.user_name </option>
                    #else
                    <option value="$listPT.user_id" >$listPT.user_name </option>
                  	#end
                  #end
        </select></td>
  </tr>
  <tr>
    <td scope="row">&nbsp;</td>
    <td scope="row">&nbsp;</td>
    <td align="left" valign="top" scope="row">&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  #if($id_seksyen == '5')
  <tr>
    <td scope="row">&nbsp;</td>
    <td scope="row">Seksyen</td>
    <td align="left" valign="top" scope="row">:</td>
    <td>$selectSeksyen</td>
  </tr>
  <tr>
    <td scope="row">&nbsp;</td>
    <td scope="row">&nbsp;</td>
    <td align="left" valign="top" scope="row">&nbsp;</td>
    <td></td>
  </tr>
  #end
  <tr>
    <td colspan="4" scope="row"><span class="style5 style47 style69 style45 style6"><span class="style40">Perhatian </span>: Pastikan label berwarna <span class="style40">*</span> wajib diisi.</span></td>
  </tr>
   #else
    <tr>
    <td width="2%" align="left" valign="top" scope="row"><span class="style40">*</span></td>
    <td width="27%" align="left" valign="top" scope="row">Jenis Dokumen</td>
    <td width="1%" align="left" valign="top" scope="row">:</td>
    <td width="70%">
      <label></label>    <select name="socJenisDokumen" id="socJenisDokumen" onchange="doChangeJenisDokumen()" $readonly $disabled>
        
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
      
      
      </select></td>
  </tr>
  #if($mode == 'New')
  #end

  <tr>
    <td align="left" valign="top" scope="row">&nbsp;</td>
    <td align="left" valign="top" scope="row">Lampiran</td>
    <td align="left" valign="top" scope="row">:</td>
    <td><input type="checkbox" name="c1" value="1" $checkedc1  $disabled/>
Minit Mesyuarat
  <input type="checkbox" name="c2" value="1" $checkedc2  $disabled/>
Laporan
<input type="checkbox" name="c3" value="1" $checkedc3  $disabled/>
CD</td>
  </tr>
  #if ($mode == 'New' )
  <tr>
    <td align="left" valign="top" scope="row">&nbsp;</td>
    <td align="left" valign="top" scope="row">Muatnaik Lampiran</td>
    <td align="left" valign="top" scope="row">:</td>
    <td>
    #foreach( $i in [1..10] )
      <input name="txtLampiran" type="file" id="txtLampiran" size="50" /><br>
    #end    </td>
  </tr>
    <tr>
    <td align="left" valign="top" scope="row">&nbsp;</td>
    <td align="left" valign="top" scope="row"></td>
    <td align="left" valign="top" scope="row"></td>
    <td><span class="style40">* <em>Jika dokumen tidak dimasukkan ke sistem eTapp, sila bawa dokumen fizikal kepada pegawai yang berkenaan.</em></span></td>
  </tr>
  #else
     #if($paparLampiranLogDokumenMasuk_size == '0')
            <tr>
            <td align="left" valign="top" scope="row">&nbsp;</td>
            <td align="left" valign="top" scope="row">Senarai Lampiran</td>
            <td align="left" valign="top" scope="row">:</td>
            <td><span class="style40"><em>Tiada lampiran di masukkan dalam sistem eTapp, dokumen fizikal akan dihantar ke pegawai yang berkenaan.</em></span></td>
          </tr>
      #else
            <tr>
            <td align="left" valign="top" scope="row">&nbsp;</td>
            <td align="left" valign="top" scope="row">Senarai Lampiran</td>
            <td align="left" valign="top" scope="row">:</td>
            <td>
            #foreach($paparLampiranLogDokumenMasuk in $SenaraiListLampiranLogDokumenMasuk)
              <table width="100%" border="0">
              <tr>
                <td>$paparLampiranLogDokumenMasuk.bil - <a href="javascript:papar_Lampiran('$paparLampiranLogDokumenMasuk.id_lampiran')" class="style1">$paparLampiranLogDokumenMasuk.nama_fail</a></td>
              </tr>
            </table>
              #end    </td>
          </tr>
      #end
  #end
  <tr>
    <td align="left" valign="top" scope="row">&nbsp;</td>
    <td align="left" valign="top" scope="row">Tag Dokumen</td>
    <td align="left" valign="top" scope="row">:</td>
    <td valign="top"><textarea id="tag_dokumen" name="tag_dokumen" cols="70" rows="6" $readonly $disabled>$!tag_Dokumen</textarea>
    <font color="#ff0000"><i> Keyword</i> yang dicadangkan untuk carian</font>
    <input name="id_tagdokumen" type="hidden" value="$id_tagdokumen"/></td>
  </tr>
  <tr>
    <td align="left" valign="top" scope="row"><span class="style40">*</span></td>
    <td align="left" valign="top" scope="row">Tarikh Penerimaan Dokumen</td>
    <td align="left" valign="top" scope="row">:</td>
    <td><input name="tarikh_Dokumen_Diterima" type="text" id="tarikh_Dokumen_Diterima" value="$!tarikh_Dokumen_Diterima" size="10" $readonly  $disabled/>
      <a href="javascript:displayDatePicker('tarikh_Dokumen_Diterima',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a></td>
  </tr>
  <tr>
    <td align="left" valign="top" scope="row"><span class="style40">*</span></td>
    <td align="left" valign="top" scope="row">No Rujukan Dokumen</td>
    <td align="left" valign="top" scope="row">:</td>
    <td><input name="no_Rujukan_Lain" type="text" id="no_Rujukan_Lain" value = "$!no_Rujukan_Lain" size="44" onblur="this.value=this.value.toUpperCase();" style="text-transform:uppercase;" $readonly $disabled/></td>
  </tr>
  <tr>
    <td align="left" valign="top" scope="row"><span class="style40">*</span></td>
    <td align="left" valign="top" scope="row">Tarikh Dokumen</td>
    <td align="left" valign="top" scope="row">:</td>
    <td><input name="tarikh_Dokumen" type="text" id="tarikh_Dokumen" value="$!tarikh_Dokumen" size="10" $readonly  $disabled/>
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
    #if($mode == 'New' || $mode == 'Update')
      <textarea name="tajuk_Dokumen" cols="70" rows="6" id="tajuk_Dokumen" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase();" $readonly $disabled> $!tajuk_Dokumen</textarea>
      
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
	
    #end </td>
  </tr>
  <tr>
    <td align="left" valign="top" scope="row"><span class="style40">*</span></td>
    <td align="left" valign="top" scope="row">Dirujukkan Kepada</td>
    <td align="left" valign="top" scope="row">:</td>
    <td><select name="socPegawai" id="socPegawai" $readonly $disabled>
    			#if ($mode == 'New' || $mode == 'Update')
                    <option value="" >SILA PILIH</option>
                #end
                  #foreach($listPegawai in $SenaraiPegawai)
                  	#if($nama_Pegawai == $listPegawai.user_id)
                    <option value="$listPegawai.user_id" selected="selected">$listPegawai.user_name </option>
                    #else
                    <option value="$listPegawai.user_id" >$listPegawai.user_name </option>
                 	 #end
                  #end
                
        </select></td>
  </tr>
  <tr>
    <td scope="row"><span class="style40">*</span></td>
    <td scope="row">Nama PA / PT Fail</td>
    <td align="left" valign="top" scope="row">:</td>
    <td><select name="socPT" id="socPT"  $readonly $disabled />
    			#if ($mode == 'New' || $mode == 'Update')
                    <option value="" >SILA PILIH</option>
                #end
                  #foreach($listPT in $SenaraiPTFail)
                  	#if($nama_PTPA == $listPT.user_id)
                    <option value="$listPT.user_id" selected="selected">$listPT.user_name </option>
                    #else
                    <option value="$listPT.user_id" >$listPT.user_name </option>
                  	#end
                  #end
               
        </select></td>
  </tr>
  <tr>
    <td scope="row">&nbsp;</td>
    <td scope="row">&nbsp;</td>
    <td align="left" valign="top" scope="row">&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  #if($id_seksyen == '5')
  <tr>
    <td scope="row"><span class="style40">*</span></td>
    <td scope="row">Seksyen</td>
    <td align="left" valign="top" scope="row">:</td>
    <td>$selectSeksyen</td>
  </tr>
  <tr>
    <td scope="row">&nbsp;</td>
    <td scope="row">&nbsp;</td>
    <td align="left" valign="top" scope="row">&nbsp;</td>
    <td></td>
  </tr>
  #end
  <tr>
    <td colspan="4" scope="row"><span class="style5 style47 style69 style45 style6"><span class="style40">Perhatian </span>: Pastikan label berwarna <span class="style40">*</span> wajib diisi.</span></td>
  </tr>
   #end 
 
  <tr>
    <td colspan="4" align="center" scope="row">
    #if ($mode == 'View')
         <input type="button" name="cmdKemaskini3" id="cmdKemaskini3" value="Kemaskini" onclick = "kemaskini()"/>
         <input name="cmdHapusDokumen" type="button" value="Hapus" onclick="hapusLogDokumen('$idLogDokumen')" />
   <input name="cmdTambahDokumen2" type="button" value="Tambah" onclick="tambahLogDokumen()" />
         <input type="button" name="cmdKembali1" id="cmdKembali1" value="Kembali" onclick="kembali()"/>
	#end
    #if($mode == 'New')
        <input type="button" name="cmdSimpan2" id="cmdSimpan2" value="Simpan" onclick = "simpanLogDokumen('$modeJenisDokumen')"/>
<input type="button" name="cmdBatal3" id="cmdBatal3" value="Batal" onclick="batal()"/>
        <input type="button" name="cmdKembali2" id="cmdKembali2" value="Kembali" onclick="kembali()"/>
	#end 
    #if($mode == 'Update')
        <input type="button" name="cmdSimpan3" id="cmdSimpan3" value="Simpan" onclick = "update1()"/>
<input type="button" name="cmdBatal3" id="cmdBatal3" value="Batal" onclick="batalView()"/>
    	<input type="button" name="cmdKembali3" id="cmdKembali3" value="Kembali" onclick="kembali()"/>
	#end
    #if($mode == 'PaparUpdate')
	
   	  <input type="button" name="cmdKembali4" id="cmdKembali4" value="Kembali" onclick="kembali()"/>
    #end	</td>
  </tr>
</table>
<input name="idNegeri" id="idNegeri" type="hidden" value="$!idNegeri"/>
<input name="idSeksyen" id="idSeksyen" type="hidden" value="$!idSeksyen"/>
</fieldset>
<script type="text/javascript">
<!--
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:0});
//-->
function batal(){

	document.${formName}.reset();
	document.${formName}.tarikh_Dokumen.value = "";
	document.${formName}.tarikh_Dokumen_Diterima.value = "";
	document.${formName}.socJenisDokumen.value = "";
	document.${formName}.no_Rujukan_Lain.value = "";
	document.${formName}.pengirim_Dokumen.value = "";
	document.${formName}.tajuk_Dokumen.value = "";
	document.${formName}.socPegawai.value = "";

	
	return;
	
}
function batalView(){
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.LogDokumen&action1=papar";
	document.${formName}.submit();
}
function tambahLogDokumen(){
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.LogDokumen&action1=tabLogDokumenMasuk&pagemode=1";
	document.${formName}.submit();
}
function hapusLogDokumen(){
	if ( !window.confirm("Anda Pasti Untuk Hapus?"))return;
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.LogDokumen&action1=hapusLogDokumenMasuk";
	document.${formName}.submit();
}
function tabLogDokumenKeluar(){
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.LogDokumen&action1=tabLogDokumenKeluar&pagemode=0";
	document.${formName}.submit();
}

function tabLogDokumenDariSeksyenLain(){
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.LogDokumen&action1=tabLogDokumenDariSeksyenLain";
	document.${formName}.submit();
}
function tabLogDokumenDalaman(){
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.LogDokumen&action1=tabLogDokumenDalaman";
	document.${formName}.submit();
}

function simpanLogDokumen(modeJenisDokumen){

	if(modeJenisDokumen == '3'){
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
	
		if( DateDokumen > d)
		{
			alert('Sila pastikan " Tarikh Dokumen " tidak melebihi tarikh hari ini.');
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
		
		if (document.${formName}.tarikh_Dokumen.value == ""){
			alert('Sila masukkan " Tarikh Dokumen " terlebih dahulu.');
			document.${formName}.tarikh_Dokumen.focus();
			return;
		}
		if (document.${formName}.pengirim_Dokumen.value == ""){
			alert('Sila masukkan " Nama Pengirim " terlebih dahulu.');
			document.${formName}.pengirim_Dokumen.focus();
			return;
		}
	
		if ( !window.confirm("Anda Pasti?") ) return;
		
		
		document.${formName}.action = "?_portal_module=ekptg.view.pfd.LogDokumen&action1=simpanLogDokumenMasuk&socJenisDokumen="+document.${formName}.socJenisDokumen.value+"&c1="+document.${formName}.c1.checked+"&c2="+document.${formName}.c2.checked+"&c3="+document.${formName}.c3.checked+"&tajuk_Dokumen="+escape(tajuk_Dokumen)+"&no_Rujukan_Lain="+escape(document.${formName}.no_Rujukan_Lain.value)+"&pengirim_Dokumen="+escape(document.${formName}.pengirim_Dokumen.value)+"&tarikh_Dokumen_Diterima="+document.${formName}.tarikh_Dokumen_Diterima.value+"&tarikh_Dokumen="+document.${formName}.tarikh_Dokumen.value+"&socPegawai="+document.${formName}.socPegawai.value+"&idSeksyen="+document.${formName}.idSeksyen.value+"&idNegeri="+document.${formName}.idNegeri.value+"&socPT="+document.${formName}.socPT.value+"&tag_dokumen="+escape(document.${formName}.tag_dokumen.value);
	
		document.${formName}.method="post";
		document.${formName}.enctype="multipart/form-data";
		document.${formName}.encoding="multipart/form-data";
		document.${formName}.submit();	
		
		
	}
	
	else if(modeJenisDokumen == '4'){
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
	
		if( DateDokumen > d)
		{
			alert('Sila pastikan " Tarikh Dokumen " tidak melebihi tarikh hari ini.');
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
		
		if (document.${formName}.tarikh_Dokumen_Diterima.value == ""){
			alert('Sila masukkan " Tarikh Dokumen Diterima " terlebih dahulu.');
			document.${formName}.tarikh_Dokumen_Diterima.focus();
			return;
		}
		
		if (document.${formName}.socPegawai.value == ""){
			alert('Sila masukkan " Nama Penerima " terlebih dahulu.');
			document.${formName}.socPegawai.focus();
			return;
		}
	
		if ( !window.confirm("Anda Pasti?") ) return;
		document.${formName}.action = "?_portal_module=ekptg.view.pfd.LogDokumen&action1=simpanLogDokumenMasuk&socJenisDokumen="+document.${formName}.socJenisDokumen.value+"&c1="+document.${formName}.c1.checked+"&c2="+document.${formName}.c2.checked+"&c3="+document.${formName}.c3.checked+"&tajuk_Dokumen="+escape(tajuk_Dokumen)+"&no_Rujukan_Lain="+escape(document.${formName}.no_Rujukan_Lain.value)+"&pengirim_Dokumen="+escape(document.${formName}.pengirim_Dokumen.value)+"&tarikh_Dokumen_Diterima="+document.${formName}.tarikh_Dokumen_Diterima.value+"&tarikh_Dokumen="+document.${formName}.tarikh_Dokumen.value+"&socPegawai="+document.${formName}.socPegawai.value+"&idSeksyen="+document.${formName}.idSeksyen.value+"&idNegeri="+document.${formName}.idNegeri.value+"&socPT="+document.${formName}.socPT.value+"&tag_dokumen="+escape(document.${formName}.tag_dokumen.value);
	
		document.${formName}.method="post";
		document.${formName}.enctype="multipart/form-data";
		document.${formName}.encoding="multipart/form-data";
		document.${formName}.submit();	
	}
	
	else{
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
	
		if( DateDokumen > d)
		{
			alert('Sila pastikan " Tarikh Dokumen " tidak melebihi tarikh hari ini.');
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
		
		if (document.${formName}.tarikh_Dokumen_Diterima.value == ""){
			alert('Sila masukkan " Tarikh Dokumen Diterima " terlebih dahulu.');
			document.${formName}.tarikh_Dokumen_Diterima.focus();
			return;
		}
		if (document.${formName}.no_Rujukan_Lain.value == ""){
			alert('Sila masukkan " No Rujukan Dokumen " terlebih dahulu.');
			document.${formName}.no_Rujukan_Lain.focus();
			return;
		}
		if (document.${formName}.tarikh_Dokumen.value == ""){
			alert('Sila masukkan " Tarikh Dokumen " terlebih dahulu.');
			document.${formName}.tarikh_Dokumen.focus();
			return;
		}
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
		
	
			
	
		if ( !window.confirm("Anda Pasti?") ) return;
		document.${formName}.action = "?_portal_module=ekptg.view.pfd.LogDokumen&action1=simpanLogDokumenMasuk&socJenisDokumen="+document.${formName}.socJenisDokumen.value+"&c1="+document.${formName}.c1.checked+"&c2="+document.${formName}.c2.checked+"&c3="+document.${formName}.c3.checked+"&tajuk_Dokumen="+escape(tajuk_Dokumen)+"&no_Rujukan_Lain="+escape(document.${formName}.no_Rujukan_Lain.value)+"&pengirim_Dokumen="+escape(document.${formName}.pengirim_Dokumen.value)+"&tarikh_Dokumen_Diterima="+document.${formName}.tarikh_Dokumen_Diterima.value+"&tarikh_Dokumen="+document.${formName}.tarikh_Dokumen.value+"&socPegawai="+document.${formName}.socPegawai.value+"&idSeksyen="+document.${formName}.idSeksyen.value+"&idNegeri="+document.${formName}.idNegeri.value+"&socPT="+document.${formName}.socPT.value+"&tag_dokumen="+escape(document.${formName}.tag_dokumen.value);
	
		document.${formName}.method="post";
		document.${formName}.enctype="multipart/form-data";
		document.${formName}.encoding="multipart/form-data";
		document.${formName}.submit();	
	}
}

function kemaskini(){
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.LogDokumen&action1=kemaskiniLogDokumenMasuk";
	document.${formName}.submit();
}

function update1(){
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.LogDokumen&action1=updateLogDokumenMasuk";
	document.${formName}.submit();
}

function kembali(){
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.LogDokumen&action1=cari";
	document.${formName}.submit();
}

function papar_Lampiran(id) {
	    var url = "../servlet/ekptg.view.pfd.DisplayBlob2?id="+id;
	    var hWnd = window.open(url,'displayfile','width=800,height=500, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
	    hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus();
}
function doChangeJenisDokumen() {
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.LogDokumen&action1=tabLogDokumenMasuk&pagemode=1&socJenisDokumen="+document.${formName}.socJenisDokumen.value;
	document.${formName}.submit();
}

</script>
