
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

<table width="100%">
  <tr>
    <td><fieldset>
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
</td>
  </tr>
  <tr>
    <td>
    
    

<fieldset>
<legend>MAKLUMAT DOKUMEN</legend>

<div id="TabbedPanels1" class="TabbedPanels">
  <ul class="TabbedPanelsTabGroup">
    <li class="TabbedPanelsTab" tabindex="0" >CARIAN DOKUMEN</li>
    <li class="TabbedPanelsTab" tabindex="0" onclick="tabDokumenMasuk()">DOKUMEN MASUK</li>
    <li class="TabbedPanelsTab" tabindex="0" onclick="tabDokumenKeluar()">DOKUMEN KELUAR</li>
    <li class="TabbedPanelsTab" tabindex="0" onclick="tabDokumenSubjaket()">DOKUMEN SUBJAKET</li>
  </ul>
  <div class="TabbedPanelsContentGroup">
  
    <div class="TabbedPanelsContent">
    
    
    
    <table width="100%">
  <tr>
    <td>
    <fieldset>
<legend>
CARIAN</legend>
<table width="100%" border="0" align="center">
  <tr>
    <td width="29%" scope="row" align="right">No Rujukan Dokumen</td>
    <td width="1%" scope="row"><span class="style2">:</span></td>
    <td width="70%">
      <label>
      <input name="noRujDok" type="text" id="noRujDok" onblur="this.value=this.value.toUpperCase();"  onkeyup="this.value=this.value.toUpperCase();" value="$!noRujDok" size="44" />
        </label>
</td>
  </tr>
  <tr>
    <td scope="row" align="right" valign="top">Tajuk Dokumen</td>
    <td width="1%" scope="row" valign="top"><span class="style2">:</span></td>
    <td>
      <label>
      <textarea name="tajukDokumen" cols="41" onblur="this.value=this.value.toUpperCase();"  onkeyup="this.value=this.value.toUpperCase();" id="tajukDokumen">$!tajukDokumen</textarea>
        </label>    </td>
  </tr>
  <tr>
    <td scope="row" align="right">Tarikh Dokumen (Surat/Memo)</td>
    <td width="1%" scope="row"><span class="style2">:</span></td>
    <td>  <input name="tarikhDok" type="text" id="tarikhDok" value="$!tarikhDok" size="10" />
      <a href="javascript:displayDatePicker('tarikhDok',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>    </td>
  </tr>
  <tr>
    <td scope="row" align="right">Tarikh Terima Dokumen</td>
    <td width="1%" scope="row"><span class="style2">:</span></td>
    <td>
      <input name="tarikhPenerimaan" type="text" id="tarikhPenerimaan" value="$!tarikhPenerimaan" size="10" />
      <a href="javascript:displayDatePicker('tarikhPenerimaan',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>    </td>
  </tr>
  <tr>
    <td colspan="2" scope="row">
      <label></label>    </td>
    <td>
      <label>
        <input type="button" name="cmdCari" id="cmdCari" value="Cari" onclick="carian()"/>
        <input type="submit" name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" onclick="kosongkan()" />
        </label>    </td>
  </tr>
</table>
</fieldset>
</td>
  </tr>
  <tr>
    <td>
    
<fieldset>
<legend>Senarai Log Dokumen</legend>


 
 <table width="100%" >
  <tr class="table_header">
    <td>NO</td>
    <td>STATUS DOKUMEN</td>
    <td>NO RUJUKAN DOKUMEN</td>
    <td>TAJUK DOKUMEN</td>
    <td>TARIKH DOKUMEN</td>
    <td>TARIKH TERIMA DOKUMEN</td>
  </tr>
  #foreach ($listLogDokumenById in $SenaraiDokumenByUserId)
  
  #if ($listLogDokumenById.bil == '') 
  	#set ($row = 'row1')
  #elseif ($listLogDokumenById.bil % 2 != 0)
  	#set ($row = 'row1')
  #else 
  	#set ($row = 'row2')
  #end 
  <tr>
    <td class="$row">$listLogDokumenById.bil</td>
    #if ($listLogDokumenById.status_Logdokumen == '1') 
     <td class="$row">BELUM DILULUSKAN</td>
    #elseif($listLogDokumenById.status_Logdokumen == '2') 
     <td class="$row">TELAH DILULUSKAN</td>
    #else 
     <td class="$row"></td>
  	#end
    #if ($listLogDokumenById.bil != '') 
    <td class="$row"><a href="javascript:edit_item('$listLogDokumenById.idLogDokumen')" class="style1">$listLogDokumenById.no_Rujukan_Lain</a></td>
    #else
        <td class="$row">$listLogDokumenById.no_rujukan_Lain</td>   
    #end
    <td class="$row">$listLogDokumenById.tajuk_Dokumen</td>
	<td class="$row">$listLogDokumenById.tarikh_Dokumen</td>
    <td class="$row">$listLogDokumenById.tarikh_DokumenDiterimaDihantar</td>
  </tr>
  #end
</table>

</fieldset>

    </td>
  </tr>
</table>

    
    
    




    </div>
    
    <div class="TabbedPanelsContent"></div>
    <div class="TabbedPanelsContent"></div>
    <div class="TabbedPanelsContent"></div>
    
 
  </div>
</div>
</fieldset>
    </td>
  </tr>
</table>







<script type="text/javascript">
<!--
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:0});
//-->
function tabDokumenMasuk(){
	document.${formName}.action1 = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=tabDokumenMasuk";
	document.${formName}.submit();
}
function tabDokumenKeluar(){
	document.${formName}.action1 = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=tabDokumenKeluar";
	document.${formName}.submit();
}
function tabDokumenSubjaket(){
	document.${formName}.action1 = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=tabDokumenSubjaket";
	document.${formName}.submit();
}
</script>
<script>
function edit_item(idLogDokumen){
	
	document.${formName}.action1 = "?_portal_module=ekptg.view.pfd.PendaftaranDokumen&action1=tabDokumenMasuk&pagemode=1&mode=New";
	document.${formName}.idLogDokumen.value = idLogDokumen;
	document.${formName}.submit();
}
</script>
