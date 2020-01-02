
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
<input name="idLogDokumenKPTG" type="hidden" value="$!idLogDokumenKPTG"/>
<input name="idDokumen" type="hidden" value="$idDokumen"/>
<input name="idMinitarahan" type="hidden" value="$id_Minitarahan" />
<input name="idLampiran" type="hidden" value="" />
<input name="mode" type="hidden" value="$mode" />
<input name="txtJumDok" type="hidden" value = "$jumlah_Dokumen"/>
<input name="txtJumDok" type="hidden" value = "$jumlah_Dokumen"/>
<input type="hidden" name="levelArahan" value="$!levelArahan"/>
&nbsp;
<fieldset>
<legend>MAKLUMAT DOKUMEN</legend>

<div id="TabbedPanels1" class="TabbedPanels">
  <ul class="TabbedPanelsTabGroup">
    <li class="TabbedPanelsTab" tabindex="0" onclick="tabLogDokumenMasuk()">DOKUMEN MASUK</li>
    <li class="TabbedPanelsTab" tabindex="0" >DOKUMEN KELUAR</li>
    <li class="TabbedPanelsTab" tabindex="0" onclick="tabLogDokumenDariSeksyenlain()">LOG DOKUMEN DARI SEKSYEN LAIN</li>
  </ul>
   <div class="TabbedPanelsContentGroup">
  
   <div class="TabbedPanelsContent"></div>
  	<div class="TabbedPanelsContent">
<fieldset>
       <legend>MAKLUMAT LOG DOKUMEN KELUAR</legend>
    <table width="100%">
  <tr>
    <td width="1%" align="left" valign="top" scope="row"><span class="style40">*</span></td>
    <td width="27%" align="left" valign="top" scope="row">Jenis Dokumen</td>
    <td width="1%" align="left" valign="top" scope="row">:</td>
    <td width="71%"><select name="socJenisDokumen" id="socJenisDokumen" $readOnly $disabled>
        
		#if ($jenis_Dokumen == '1')
        
        <option value="0">SILA PILIH</option>
        <option value="1" selected="selected">SURAT</option>
        <option value="2">MEMO</option>
        <option value="3">DOKUMEN TERPERINGKAT</option>

        #elseif ($jenis_Dokumen == '2')
        
        <option value="0">SILA PILIH</option>
        <option value="1">SURAT</option>
        <option value="2" selected="selected">MEMO</option>
        <option value="3">DOKUMEN TERPERINGKAT</option>
        
        #elseif ($jenis_Dokumen == '3')
        
        <option value="0" selected="selected">SILA PILIH</option>
        <option value="1">SURAT</option>
        <option value="2">MEMO</option>
        <option value="3" selected="selected">DOKUMEN TERPERINGKAT</option>
        
        #else
        
        <option value="0" selected="selected">SILA PILIH</option>
        <option value="1">SURAT</option>
        <option value="2">MEMO</option>
        <option value="3">DOKUMEN TERPERINGKAT</option>
        
        #end
      
      
      </select></td>
  </tr>
  #if($mode == 'New')
  #end

  <tr>
    <td align="left" valign="top" scope="row">&nbsp;</td>
    <td align="left" valign="top" scope="row">Lampiran</td>
    <td align="left" valign="top" scope="row">:</td>
    <td><input type="checkbox" name="c1" value="1" $checkedc1  $readOnly $disabled/>
Minit Mesyuarat
  <input type="checkbox" name="c2" value="1" $checkedc2  $readOnly $disabled/>
Laporan</td>
  </tr>
  #if($mode == 'New')
 <tr>
    <td align="left" valign="top" scope="row">&nbsp;</td>
    <td align="left" valign="top" scope="row">Muat Naik Lampiran</td>
    <td align="left" valign="top" scope="row">:</td>
    <td>
    #foreach( $i in [1..5] )
    <input name="txtLampiran" type="file" id="txtLampiran" size="50" /><br>
    #end
    </td>
  </tr>
  #elseif($mode == 'View')
    <tr>
    <td align="left" valign="top" scope="row">&nbsp;</td>
    <td align="left" valign="top" scope="row">Senarai Lampiran</td>
    <td align="left" valign="top" scope="row">:</td>
    <td>#foreach($paparLampiranLogDokumenKeluar in $SenaraiListLampiranLogDokumenKeluar)
      <table width="100%" border="0">
        <tr>
          <td>$paparLampiranLogDokumenKeluar.bil - <a href="javascript:papar_Lampiran('$paparLampiranLogDokumenKeluar.id_lampiran')" class="style1">$paparLampiranLogDokumenKeluar.nama_fail</a></td>
        </tr>
      </table>
      #end </td>
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
    <td align="left" valign="top" scope="row"><span class="style40">*</span></td>
    <td align="left" valign="top" scope="row">No Rujukan Dokumen</td>
    <td align="left" valign="top" scope="row">:</td>
    <td><input name="no_Rujukan_Lain" type="text" id="no_Rujukan_Lain" onblur="this.value=this.value.toUpperCase();" style="text-transform:uppercase;" value = "$!no_Rujukan_Lain" size="44" $readOnly $disabled/></td>
  </tr>
  <tr>
    <td align="left" valign="top" scope="row"><span class="style40">*</span></td>
    <td align="left" valign="top" scope="row">Tarikh Dokumen</td>
    <td align="left" valign="top" scope="row">:</td>
    <td><input name="tarikh_Dokumen" type="text" id="tarikh_Dokumen" value="$!tarikh_Dokumen" size="10" $readOnly  $disabled/>
      <a href="javascript:displayDatePicker('tarikh_Dokumen',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a></td>
  </tr>
  <tr>
    <td align="left" valign="top" scope="row"><span class="style40">*</span></td>
    <td align="left" valign="top" scope="row">Daripada Siapa</td>
    <td align="left" valign="top" scope="row">:</td>
    <td><select name="socTKP" id="socTKP" $readOnly $disabled>
                  #foreach($listTKP in $SenaraiTKP)
                  	#if($selectidorang == $listTKP.user_id)
                    <option value="$listTKP.user_id" selected="selected">$listTKP.user_name </option>
                    #else
                    <option value="$listTKP.user_id" >$listTKP.user_name </option>
                  	#end
                  #end
        </select></td>
  </tr>

  <tr>
    <td width="1%" align="left" valign="top" scope="row"><span class="style40">*</span></td>
    <td align="left" valign="top" scope="row">Perkara</td>
    <td align="left" valign="top" scope="row">:</td>
    <td><label>
      <textarea name="tajuk_Dokumen" cols="41" id="tajuk_Dokumen" onblur="this.value=this.value.toUpperCase();" style="text-transform:uppercase;" $readOnly $disabled>$!tajuk_Dokumen</textarea>
    </label></td>
  </tr>
  <tr>
    <td width="1%" align="left" valign="top" scope="row"><span class="style40">*</span></td>
    <td align="left" valign="top" scope="row">Dirujukkan Kepada</td>
    <td align="left" valign="top" scope="row">:</td>
    <td><textarea name="penerima_Dokumen" cols="41" id="penerima_Dokumen" onblur="this.value=this.value.toUpperCase();" style="text-transform:uppercase;" $readOnly $disabled>$!penerima_Dokumen</textarea></td>
  </tr>
    <tr>
    <td width="1%" align="left" valign="top" scope="row"><span class="style40">*</span></td>
    <td align="left" valign="top" scope="row">Cara Penghantaran</td>
    <td align="left" valign="top" scope="row">:</td>
    <td><select name="socJenisPenghantaran" id="socJenisPenghantaran" $readOnly $disabled>
        
		#if ($jenis_Penghantaran == '1')
        
        <option value="0" >SILA PILIH</option>
        <option value="1" selected="selected">TANGAN</option>
        <option value="2" >POS</option>
        <option value="3" >POS BERDAFTAR</option>
        <option value="4" >FAX</option>

        #elseif ($jenis_Penghantaran == '2')
        
        <option value="0" >SILA PILIH</option>
        <option value="1" >TANGAN</option>
        <option value="2" selected="selected">POS</option>
        <option value="3" >POS BERDAFTAR</option>
        <option value="4" >FAX</option>
        
        #elseif ($jenis_Penghantaran == '3')
        
        <option value="0" >SILA PILIH</option>
        <option value="1" >TANGAN</option>
        <option value="2" >POS</option>
        <option value="3" selected="selected">POS BERDAFTAR</option>
        <option value="4" >FAX</option>
        
        #elseif ($jenis_Penghantaran == '4')
        
        <option value="0" >SILA PILIH</option>
        <option value="1" >TANGAN</option>
        <option value="2" >POS</option>
        <option value="3" >POS BERDAFTAR</option>
        <option value="4" selected="selected">FAX</option>
        
        
        #else
        
        <option value="0" selected="selected">SILA PILIH</option>
        <option value="1" >TANGAN</option>
        <option value="2" >POS</option>
        <option value="3" >POS BERDAFTAR</option>
        <option value="4" >FAX</option>
        
        #end
      
      
      </select></td>
  </tr>
  <tr>
    <td align="left" valign="top" scope="row"><span class="style40">*</span></td>
    <td align="left" valign="top" scope="row">Tarikh Dokumen Dihantar</td>
    <td align="left" valign="top" scope="row">:</td>
    <td><input name="tarikh_Diterima_Dihantar" type="text" id="tarikh_Diterima_Dihantar" value="$!tarikh_Diterima_Dihantar" size="10" $readOnly $disabled/>
      <a href="javascript:displayDatePicker('tarikh_Diterima_Dihantar',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a></td>
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
  <tr>
    <td colspan="4" align="center" scope="row">
    #if ($mode == 'View')
         <input type="button" name="cmdKemaskini3" id="cmdKemaskini3" value="Kemaskini" onclick = "kemaskini()"/>
         <input type="button" name="cmdKembali1" id="cmdKembali1" value="Kembali" onclick="kembali()"/>
	#end
    #if($mode == 'New')
        <input type="button" name="cmdSimpan2" id="cmdSimpan2" value="Simpan" onclick = "simpanLogDokumen()"/>
        <input type="button" name="cmdBatal3" id="cmdBatal3" value="Batal" onclick="batal()"/>
        <input type="button" name="cmdKembali2" id="cmdKembali2" value="Kembali" onclick="kembaliSenarai()"/>
	#end 
    #if($mode == 'NewLog')
        <input type="button" name="cmdSimpan2" id="cmdSimpan2" value="Simpan" onclick = "updateLogDokumen('$idLogDokumen')"/>
        <input type="button" name="cmdBatal3" id="cmdBatal3" value="Batal" onclick="batal()"/>
        <input type="button" name="cmdKembali2" id="cmdKembali2" value="Kembali" onclick="kembaliSenarai()"/>
	#end 
    #if($mode == 'Update')
        <input type="button" name="cmdSimpan3" id="cmdSimpan3" value="Simpan" onclick = "update1()"/>
   	 	<input type="button" name="cmdBatal3" id="cmdBatal3" value="Batal" onclick="batalView()"/>
    	<input type="button" name="cmdKembali3" id="cmdKembali3" value="Kembali" onclick="kembaliTabLogKeluar()"/>
	#end
    #if($mode == 'PaparUpdate')
    	<input type="button" name="cmdKembali4" id="cmdKembali4" value="Kembali" onclick="kembali()"/>
    #end	</td>
  </tr>
</table>
<input name="idNegeri" id="idNegeri" type="hidden" value="$!idNegeri"/>
<input name="idSeksyen" id="idSeksyen" type="hidden" value="$!idSeksyen"/>
</fieldset>

    </div>
    <div class="TabbedPanelsContent"></div>
   </div>
</div>
</fieldset>
<script type="text/javascript">
<!--
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:1});
//-->
function tabLogDokumenMasuk(){
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.LogDokumenTKPK&action1=tabLogDokumenMasukKPTG&paparArahan=false";
	document.${formName}.submit();
}
function tabLogDokumenDariSeksyenlain(){
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.LogDokumenTKPK&action1=tabLogDokumenDariSeksyenLainKPTG&paparArahan=false&pagemode=2";
	document.${formName}.submit();
}
function simpanLogDokumen(){
	if ( !window.confirm("Anda Pasti?") ) return;
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.LogDokumenTKPK&action1=simpanLogDokumenKeluarKPTG&socJenisDokumen="+document.${formName}.socJenisDokumen.value+"&c1="+document.${formName}.c1.checked+"&c2="+document.${formName}.c2.checked+"&no_Rujukan_Lain="+escape(document.${formName}.no_Rujukan_Lain.value)+"&tajuk_Dokumen="+escape(document.${formName}.tajuk_Dokumen.value)+"&socTKP="+document.${formName}.socTKP.value+"&tarikh_Diterima_Dihantar="+document.${formName}.tarikh_Diterima_Dihantar.value+"&tarikh_Dokumen="+document.${formName}.tarikh_Dokumen.value+"&penerima_Dokumen="+escape(document.${formName}.penerima_Dokumen.value)+"&idSeksyen="+document.${formName}.idSeksyen.value+"&idNegeri="+document.${formName}.idNegeri.value+"&socJenisPenghantaran="+document.${formName}.socJenisPenghantaran.value+"&paparArahan=false";
	document.${formName}.method="post";
	document.${formName}.enctype="multipart/form-data";
	document.${formName}.encoding="multipart/form-data";
	document.${formName}.submit();	
}
function kembali(){
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.LogDokumenTKPK&action1=";
	document.${formName}.submit();
}
</script>