<fieldset>
  <legend><strong>CARIAN MESYUARAT</strong></legend>
  <table width="100%">
    <tr>
      <td width="29%" align="right" valign="top" scope="row"><div align="left">Tajuk Mesyuarat</div></td>
      <td width="1%" align="center" valign="top" scope="row">:</td>
      <td width="70%" align="left" valign="top"><textarea name="Mesyuarat_Tajuk" cols="41" rows="5" id="Mesyuarat_Tajuk" onblur="this.value=ucwords(this.value);">$!Mesyuarat_Tajuk</textarea></td>
    </tr>
    <tr>
      <td align="right" valign="top" scope="row"><div align="left">Urusetia / Seksyen</div></td>
      <td align="center" valign="top" scope="row">:</td>
      <td align="left" valign="top">$selectSeksyen</td>
    </tr>
    <tr>
      <td align="right" valign="top" scope="row"><div align="left">Lokasi</div></td>
      <td align="center" valign="top" scope="row">:</td>
      <td align="left" valign="top">$selectLokasi</td>
    </tr>
    <tr>
      <td align="right" valign="top" scope="row"><div align="left">Tarikh Mesyuarat</div></td>
      <td align="center" valign="top" scope="row">:</td>
      <td align="left" valign="top"><input id="Mesyuarat_Tarikh" name="Mesyuarat_Tarikh" type="text" value="$!Mesyuarat_Tarikh" /><a href="javascript:displayDatePicker('Mesyuarat_Tarikh',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a></td>
    </tr>
    <tr>
      <td colspan="2"><div align="left"></div></td>
      <td valign="top" scope="row"><input id="cmdCari" name="cmdCari" type="button" value="Cari" onclick="searchMesyuarat();" />
        <input id="cmdKosongkan" name="cmdKosongkan" type="button" value="Kosongkan" onclick="emptyMesyuarat();" /></td>
    </tr> 
    <tr>
      <td colspan="3" align="center" valign="top" scope="row"><div align="left"></div></td>
    </tr>
  </table>
</fieldset>
<br />
<fieldset>
  <legend><strong>SENARAI MESYUARAT</strong></legend>
  <table width="100%" >
    <tr>
      <td colspan="4"><input id="cmdBaru" name="cmdBaru" type="button" value="Tambah Mesyuarat" onclick="newMesyuarat();" /></td>
    </tr>
    <tr class="table_header">
      <td width="5%" scope="row"><strong>No</strong></td>
      <td width="15%"><strong>URUSETIA/SEKSYEN</strong></td>
      <td width="5%"><strong>BIL</strong></td>
      <td width="15%"><strong>TAJUK</strong></td>
      <td width="15%"><strong>TARIKH</strong></td>
      <td width="15%"><strong>MASA</strong></td>
      <td width="15%"><strong>LOKASI</strong></td>
      <td width="15%"><strong>STATUS</strong></td>
    </tr>
#set ($list = '')
#foreach ($list in $ListMesyuarat)
	#if ($list.ListNo == '') 
    	#set ($row = 'row1')
    #elseif ($list.ListNo % 2 != 0)
    	#set ($row = 'row1')
    #else 
    	#set ($row = 'row2')
    #end
    <tr>
    #if ($list.ListNo != '') 
      <td height="21" class="$row" valign="top">$list.ListNo</td>
      <td class="$row" valign="top">$list.ListUrusetiaSeksyen</td>
      <td class="$row" valign="top">$list.ListBil</td>
      <td class="$row" valign="top"><a href="javascript:viewMesyuarat('$list.ListIDMesyuarat', '0')" style="color:#0000FF">$list.ListTajuk</a></td>
      <td class="$row" valign="top">$list.ListTarikh</td>
      <td class="$row" valign="top">$list.ListMasa</td>
      <td class="$row" valign="top">$list.ListLokasi</td>
      <td class="$row" valign="top">$list.ListStatus</td>
    #else
      <td colspan="8" class="$row" style="text-align:center">Tiada Rekod</td>
    #end
    </tr>
#end
    <tr>
      <td colspan="8">&nbsp;</td>
    </tr>
  </table>
</fieldset>
<input type="hidden" id="ID_MESYUARAT" name="ID_MESYUARAT" value="$ID_MESYUARAT" />
<input type="hidden" id="selectedTab" name="selectedTab" value="$selectedTab" />
<input type="hidden" id="mode" name="mode" value="$mode" />
<input name="action" id="action" type="hidden" value="$action" />
<script>
function viewMesyuarat(ID_MESYUARAT, TabID) {	
	document.${formName}.ID_MESYUARAT.value = ID_MESYUARAT;
	document.${formName}.selectedTab.value = TabID;	
	document.${formName}.action.value = 'agenda';	
	document.${formName}.submit();
}
function searchMesyuarat() {
	document.${formName}.action.value = 'search';	
	document.${formName}.submit();
}
function emptyMesyuarat() {
	document.${formName}.reset();
}
function newMesyuarat() {
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranMesyuarat&action=&tabId=1&kategori_MesyuaratSrc=1";
	document.${formName}.submit();
}
</script>