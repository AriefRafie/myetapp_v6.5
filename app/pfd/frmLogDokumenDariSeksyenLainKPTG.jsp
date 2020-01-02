
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

<input name="idFail" type="hidden" value="$idFail" />
<input name="idLogDokumenKPTG" type="hidden" value="$!idLogDokumenKPTG"/>
<input name="idLogDokumenKPTGSekLain" type="hidden" value="$!idLogDokumenKPTGSekLain"/>
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
    <li class="TabbedPanelsTab" tabindex="0" onclick="tabLogDokumenMasuk()">LOG DOKUMEN MASUK</li>
    <li class="TabbedPanelsTab" tabindex="0" onclick="tabLogDokumenKeluar()">LOG DOKUMEN KELUAR</li>
    <li class="TabbedPanelsTab" tabindex="0" >LOG DOKUMEN DARI SEKSYEN LAIN</li>
  </ul>
  <div class="TabbedPanelsContentGroup">
  
  	<div class="TabbedPanelsContent"></div>
    <div class="TabbedPanelsContent"></div>
    <div class="TabbedPanelsContent">
 <fieldset>
       <legend>MAKLUMAT LOG DOKUMEN DARI SEKSYEN LAIN</legend>
 <table width="100%">
  <tr>
    <td align="left" valign="top" scope="row">&nbsp;</td>
    <td align="left" valign="top" scope="row">Arahan Daripada</td>
    <td align="left" valign="top" scope="row">:</td>
    <td><input name="nama_Pegawai" type="text" id="nama_Pegawai" value = "$!nama_Pegawai" size="44" $readOnly $disabled/></td>
  </tr>
  <tr>
    <td align="left" valign="top" scope="row">&nbsp;</td>
    <td align="left" valign="top" scope="row">Catatan</td>
    <td align="left" valign="top" scope="row">:</td>
    <td><textarea name="catatan" cols="41" id="catatan" onblur="this.value=this.value.toUpperCase();" onkeyup="this.value=this.value.toUpperCase();" $readOnly $disabled>$!catatan</textarea></td>
  </tr>
  <tr>
    <td width="2%" align="left" valign="top" scope="row"><span class="style40">*</span></td>
    <td width="27%" align="left" valign="top" scope="row">Jenis Dokumen</td>
    <td width="1%" align="left" valign="top" scope="row">:</td>
    <td width="70%"><select name="socJenisDokumen" id="socJenisDokumen" $readOnly $disabled>
        
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

  <tr>
    <td align="left" valign="top" scope="row">&nbsp;</td>
    <td align="left" valign="top" scope="row">Lampiran</td>
    <td align="left" valign="top" scope="row">:</td>
    <td><input type="checkbox" name="c1" value="1" $checkedc1  $disabled/>
Minit Mesyuarat
  <input type="checkbox" name="c2" value="1" $checkedc2  $disabled/>
Laporan</td>
  </tr>
   #if($mode == 'NewLog')
 <tr>
    <td align="left" valign="top" scope="row">&nbsp;</td>
    <td align="left" valign="top" scope="row">Senarai Lampiran</td>
    <td align="left" valign="top" scope="row">:</td>
    <td>#foreach($paparLampiranLogDokumenTKPKDariSeksyenLain in $SenaraiListLampiranLogDokumenTKPKDariSeksyenLain)
      <table width="100%" border="0">
        <tr>
          <td>$paparLampiranLogDokumenTKPKDariSeksyenLain.bil - <a href="javascript:papar_Lampiran('$paparLampiranLogDokumenTKPKDariSeksyenLain.id_lampiran')" class="style1">$paparLampiranLogDokumenTKPKDariSeksyenLain.nama_fail</a></td>
        </tr>
      </table>
      #end </td>
  </tr>
  #elseif($mode == 'New')
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
    <td>#foreach($paparLampiranLogDokumenTKPKDariSeksyenLain in $SenaraiListLampiranLogDokumenTKPKDariSeksyenLain)
      <table width="100%" border="0">
        <tr>
          <td>$paparLampiranLogDokumenTKPKDariSeksyenLain.bil - <a href="javascript:papar_Lampiran('$paparLampiranLogDokumenTKPKDariSeksyenLain.id_lampiran')" class="style1">$paparLampiranLogDokumenTKPKDariSeksyenLain.nama_fail</a></td>
        </tr>
      </table>
      #end </td>
  </tr>
  #end
  <tr>
    <td align="left" valign="top" scope="row"><span class="style40">*</span></td>
    <td align="left" valign="top" scope="row">No Rujukan Dokumen</td>
    <td align="left" valign="top" scope="row">:</td>
    <td><input name="no_Rujukan_Lain" type="text" id="no_Rujukan_Lain" value = "$!no_Rujukan_Lain" size="44" $readOnly $disabled/></td>
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
    <td><textarea name="pengirim_Dokumen" cols="41" id="pengirim_Dokumen" onblur="this.value=this.value.toUpperCase();" onkeyup="this.value=this.value.toUpperCase();" $readOnly $disabled>$!pengirim_Dokumen</textarea></td>
  </tr>
 <tr>
    <td width="1%" align="left" valign="top" scope="row"><span class="style40">*</span></td>
    <td align="left" valign="top" scope="row">Perkara</td>
    <td align="left" valign="top" scope="row">:</td>
    <td><label>
      <textarea name="tajuk_Dokumen" cols="41" id="tajuk_Dokumen" onblur="this.value=this.value.toUpperCase();" onkeyup="this.value=this.value.toUpperCase();" $readOnly $disabled>$!tajuk_Dokumen</textarea>
    </label></td>
  </tr>
      <tr>
    <td align="left" valign="top" scope="row"><span class="style40">*</span></td>
    <td align="left" valign="top" scope="row">Tarikh Penerimaan Dokumen</td>
    <td align="left" valign="top" scope="row">:</td>
    <td><input name="tarikh_Dokumen_Diterima" type="text" id="tarikh_Dokumen_Diterima" value="$!tarikh_Dokumen_Diterima" size="10" $readOnlyTPD  $disabledTPD/>
      <a href="javascript:displayDatePicker('tarikh_Dokumen_Diterima',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a></td>
  </tr>
  <tr>
    <td width="1%" align="left" valign="top" scope="row"><span class="style40">*</span></td>
    <td align="left" valign="top" scope="row">Dirujukkan Kepada</td>
    <td align="left" valign="top" scope="row">:</td>
    <td><select name="socPegawai" id="socPegawai" $readOnlyRujuk $disabledRujuk>
    			#if ($mode == 'New' || $mode == 'PaparUpdate')
                    <option value="" >SILA PILIH</option>
                #end
                  #foreach($listPegawai in $SenaraiPegawai)
                  	#if($selectidorang == $listPegawai.user_id)
                    <option value="$listPegawai.user_id" selected="selected">$listPegawai.user_name </option>
                    #else
                    <option value="$listPegawai.user_id" >$listPegawai.user_name </option>
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
         
         <input type="button" name="cmdKembali1" id="cmdKembali1" value="Kembali" onclick="kembali()"/>
	#end
    #if($mode == 'New')
        <input type="button" name="cmdSimpan2" id="cmdSimpan2" value="Simpan" onclick = "simpanLogDokumen()"/>
        <input type="button" name="cmdBatal3" id="cmdBatal3" value="Batal" onclick="batal()"/>
        <input type="button" name="cmdKembali2" id="cmdKembali2" value="Kembali" onclick="kembali()"/>
	#end 
    #if($mode == 'NewLog')
        <input type="button" name="cmdSimpan2" id="cmdSimpan2" value="Simpan" onclick = "updateLogDokumenKPTG('$idLogDokumenKPTG')"/>
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
    </div>  
  </div>
</div>
</fieldset>

<script type="text/javascript">
<!--
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:2});
//-->
function tabLogDokumenMasuk(){
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.LogDokumenTKPK&action1=tabLogDokumenMasukKPTG";
	document.${formName}.submit();
}
function tabLogDokumenKeluar(){
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.LogDokumenTKPK&action1=tabLogDokumenKeluarKPTG";
	document.${formName}.submit();
}
function papar_Lampiran(id) {
	    var url = "../servlet/ekptg.view.pfd.DisplayBlob?id="+id;
	    var hWnd = window.open(url,'displayfile','width=800,height=500, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
	    hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus();
}
function updateLogDokumen(){
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.LogDokumenTKPK&action1=updateLogDokumenSeksyenLain&pagemode=1";
	document.${formName}.submit();
}
function updateLogDokumenKPTG(){
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.LogDokumenTKPK&action1=updateLogDokumenDariSeksyenLainKPTG&pagemode=2";
	document.${formName}.submit();
}
function kembali(){
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.LogDokumenTKPK&action1=";
	document.${formName}.submit();
}
</script>