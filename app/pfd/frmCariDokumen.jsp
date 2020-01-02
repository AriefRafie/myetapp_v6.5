<style type="text/css">
<!--
.style1 {color: #0033FF}
.style2 {font-size: 10px}
.style3 {color: #FF0000}
-->
</style>

<fieldset>
<legend>
CARIAN</legend>
<input name="idNegeri" id="idNegeri" type="hidden" value="$!idNegeri"/>
<input name="idSeksyen" id="idSeksyen" type="hidden" value="$!idSeksyen"/>
<table width="100%" border="0" align="center">
  <tr>
    <td width="29%" scope="row" align="right">No Rujukan Dokumen</td>
    <td width="1%" scope="row"><span class="style2">:</span></td>
    <td width="70%">
      <label>
      <input name="no_Rujukan_Lain" onblur="this.value=this.value.toUpperCase();" style="text-transform:uppercase;" type="text" id="no_Rujukan_Lain" value="$!no_Rujukan_Lain" size="44" />
        </label><font color="#FF0000">(No Rujukan Surat/Memo/Laporan)</td>
  </tr>
  <tr>
    <td scope="row" align="right" valign="top">Tajuk Dokumen</td>
    <td width="1%" scope="row" valign="top"><span class="style2">:</span></td>
    <td>
      <label>
      <textarea name="tajuk_Dokumen" cols="42" rows="1" onblur="this.value=this.value.toUpperCase();" style="text-transform:uppercase;" id="tajuk_Dokumen">$!tajuk_Dokumen</textarea>
        </label>    </td>
  </tr>
  <tr>
   
    <td valign="top" scope="row"><div align="right">Tag Dokumen</div></td>
    <td align="left" valign="top" scope="row">:</td>
    <td><textarea id="tag_dokumen" name="tag_dokumen" cols="42" rows="3" $readonly $disabled>$!tag_Dokumen</textarea>
    <input name="id_tagdokumen" type="hidden" value="$id_tagdokumen"/></td>
  </tr>
  <tr>
    <td scope="row" align="right">Tarikh Dokumen (Surat/Memo)</td>
    <td width="1%" scope="row"><span class="style2">:</span></td>
    <td>  <input name="tarikh_Dokumen" type="text" id="tarikh_Dokumen" value="$!tarikh_Dokumen" size="10" />
      <a href="javascript:displayDatePicker('tarikh_Dokumen',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>    </td>
  </tr>
  <tr>
    <td scope="row" align="right">Tarikh Terima Dokumen</td>
    <td scope="row">:</td>
    <td><input name="tarikh_Dokumen_Diterima" type="text" id="tarikh_Dokumen_Diterima" value="$!tarikh_Dokumen_Diterima" size="10" />
      <a href="javascript:displayDatePicker('tarikh_Dokumen_Diterima',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a></td>
  </tr>
  <tr>
    <td scope="row" align="right">Tarikh Hantar Dokumen</td>
    <td width="1%" scope="row">:</td>
    <td><input name="tarikh_Dokumen_Dihantar" type="text" id="tarikh_Dokumen_Dihantar" value="$!tarikh_Dokumen_Dihantar" size="10" />
      <a href="javascript:displayDatePicker('tarikh_Dokumen_Dihantar',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a></td>
  </tr>
  <tr>
    <td colspan="2" scope="row">
      <label></label>    </td>
    <td>
     <label>
        <input type="button" name="cmdCari" id="cmdCari" value="Cari" onclick="carian()" />
        <input type="submit" name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" onclick="kosongkan()" />
        </label>      </td>
  </tr>
</table>
</fieldset>
<fieldset>
<legend>SENARAI DOKUMEN</legend>
Jumlah Keseluruhan Dokumen : $JumlahDokumen
#set ($pagingTitle = "Jumlah Carian") 
#parse("app/utils/record_paging.jsp") 
<table width="100%" cellspacing="2" >
 #if($myrole == '(PFD)Pembantu Am Rendah' || $myrole == 'adminpfd' || $myrole == '(PFD)Pembantu Tadbir' )
  <tr>
    <td colspan="6" scope="row"><input type="button" name="cmdTambah" id="cmdTambah" value="Daftar Dokumen" onclick="tambahDokumen()"/>
    <input type="button" name="cmdTambah2" id="cmdTambah2" value="Cetak Log Dokumen" onclick="cetakLogDokumen()"/>
    </td>
  </tr>
  #end
  </br>
  <tr>
   <td colspan="6" scope="row"><p><span class="style40 style3"><em>Perhatian </em></span>: <em>data Jenis Dokumen berwarna <span class="style40 style3">merah</span> menandakan dokumen masuk, manakala data Jenis Dokumen berwarna hitam menandakan dokumen keluar.</em></p>
   </td>
  </tr>
  <tr class="table_header">
    <td width="1%" scope="row">NO</td>
    <td width="30%">NO RUJUKAN DOKUMEN</td>
    <td width="50%">TAJUK DOKUMEN</td>
    <td width="50%">TAG DOKUMEN</td>
    <td width="30%">TARIKH DOKUMEN</td>
    <td width="30%">TARIKH TERIMA/HANTAR DOKUMEN</td>
    <td width="10%">&nbsp;</td>
  </tr>
  #foreach ($listLogDokumen in $SenaraiFail)
  
  #if ($listLogDokumen.bil == '') 
  	#set ($row = 'row1')
  #elseif ($listLogDokumen.bil % 2 != 0)
  	#set ($row = 'row1')
  #else 
  	#set ($row = 'row2')
  #end 
  #if($listLogDokumen.flag_logdokumen == '1')
   <tr>
    <td class="$row">$!listLogDokumen.bil</td>
    #if ($listLogDokumen.bil != '') 
    <td class="$row"><a href="javascript:edit_item('$!listLogDokumen.idLogDokumen')" class="style1">$!listLogDokumen.no_Rujukan_Lain</a>
    </td>
    #else
        <td class="$row style3">$!listLogDokumen.no_Rujukan_Lain</td>   
    #end
    <td class="$row style3">$!listLogDokumen.tajuk_Dokumen</td>
    <td class="$row style3">$!listLogDokumen.tag_dokumen</td>
	<td class="$row style3">$!listLogDokumen.tarikh_Dokumen</td>
    <td class="$row style3">$!listLogDokumen.tarikh_Dokumen_Diterima$!listLogDokumen.tarikh_Dokumen_Dihantar</td>
    <td class="$row"><input type="button" name="cmdTambahLampiran2" id="cmdTambahLampiran2" value="Lampiran" onclick="tambahLampiran('$listLogDokumen.idLogDokumen')"/></td>
  </tr>
  #else
  <tr>
    <td class="$row">$!listLogDokumen.bil</td>
    #if ($listLogDokumen.bil != '') 
    <td class="$row"><a href="javascript:edit_item('$listLogDokumen.idLogDokumen')" class="style1">$!listLogDokumen.no_Rujukan_Lain</a></td>
    #else
        <td class="$row">$!listLogDokumen.no_Rujukan_Lain</td>   
    #end
    <td class="$row">$!listLogDokumen.tajuk_Dokumen</td>
    <td class="$row">$!listLogDokumen.tag_dokumen</td>
	<td class="$row">$!listLogDokumen.tarikh_Dokumen</td>
    <td class="$row">$!listLogDokumen.tarikh_Dokumen_Diterima$!listLogDokumen.tarikh_Dokumen_Dihantar</td>
    <td class="$row"><input type="button" name="cmdTambahLampiran2" id="cmdTambahLampiran2" value="Lampiran" onclick="tambahLampiran('$listLogDokumen.idLogDokumen')"/></td>
  </tr>
  #end
  #end
</table>

<input type="hidden" name="idFail" />
      <input type="hidden" name="mode" />	
</fieldset>

<script>
function carian(){
	document.${formName}.submit();
}
function cetakLogDokumen() {
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.LogDokumen&action1=cetakLogDokumen";
	document.${formName}.mode.value = "";
	document.${formName}.submit();
	
}
function tambahDokumen() {
	
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.LogDokumen&action1=tabLogDokumenMasuk&pagemode=1";
	document.${formName}.mode.value = "";
	document.${formName}.submit();
	
}
function edit_item(id){
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.LogDokumen&action1=papar&idLogDokumen="+id;
	document.${formName}.submit();

}
function kosongkan(){
	document.${formName}.reset();
	document.${formName}.no_Rujukan_Lain.value = "";
	document.${formName}.tajuk_Dokumen.value = "";
	document.${formName}.tarikh_Dokumen.value = "";
	document.${formName}.tarikh_Dokumen_Diterima.value = "";
	document.${formName}.tag_dokumen.value = "";
	
}

function tambahLampiran(id){
	  
	//document.${formName}.idDokumen.value = id;
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.LogDokumen&action1=tambahLampiran&idLogDokumen="+id;
	document.${formName}.submit();

}
</script>