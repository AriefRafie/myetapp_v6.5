<fieldset>
  <legend><strong>CARIAN MESYUARAT</strong></legend>
  
<table width="100%">
    <tr>
      <td align="right" valign="top" scope="row"><div align="left">No Fail / No Rujukan Mesyuarat</div></td>
      <td align="center" valign="top" scope="row">&nbsp;</td>
      <td align="left" valign="top"><input name="Carian_NoFail" type="text" id="Carian_NoFail" onkeyup="this.value=this.value.toUpperCase();" value="$!Carian_NoFail" size="50" maxlength="255" /></td>
    </tr>
    <tr>
      <td width="29%" align="right" valign="top" scope="row"><div align="left">Tajuk Mesyuarat</div></td>
      <td width="1%" align="center" valign="top" scope="row">:</td>
      <td width="70%" align="left" valign="top"><input name="Carian_Tajuk" type="text" id="Carian_Tajuk" onkeyup="this.value=this.value.toUpperCase();" value="$!Carian_Tajuk" size="50" maxlength="255" /></td>
    </tr>
    <tr>
      <td align="right" valign="top" scope="row"><div align="left">Jenis Mesyuarat</div></td>
      <td align="center" valign="top" scope="row">:</td>
      <td align="left" valign="top">
        <select id="Carian_Kategori" name="Carian_Kategori" style="width:auto">
          <option value="0">TIADA MAKLUMAT</option>
          <option value="1">AD-HOC</option>
          <option value="2">RUTIN</option>
        </select>      </td>
    </tr>
    #if($idNegeri == '16')
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
    #else
        <tr>
      <td align="right" valign="top" scope="row"><div align="left">Urusetia / Unit</div></td>
      <td align="center" valign="top" scope="row">:</td>
      <td align="left" valign="top">$selectUnit</td>
    </tr>
    <tr>
      <td align="right" valign="top" scope="row"><div align="left">Lokasi</div></td>
      <td align="center" valign="top" scope="row">:</td>
      <td align="left" valign="top">$selectLokasi</td>
    </tr>
    #end
    <tr>
      <td align="right" valign="top" scope="row"><div align="left">Tarikh Mesyuarat</div></td>
      <td align="center" valign="top" scope="row">:</td>
      <td align="left" valign="top"><input name="Carian_Tarikh" type="text" id="Carian_Tarikh" value="$!Carian_Tarikh" size="15" maxlength="10" /> <a href="javascript:displayDatePicker('Carian_Tarikh',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a></td>
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
#parse("app/utils/record_paging.jsp")
  <table width="100%" >
    <tr class="table_header">
      <td width="5%" scope="row" align="center"><strong>No</strong></td>
      <td width="15%"><strong>NO FAIL</strong></td>
       #if($idNegeri == '16')
      <td width="20%"><strong>URUSETIA/SEKSYEN</strong></td>
      #else
      <td width="20%"><strong>URUSETIA/UNIT</strong></td>
      #end
      <td width="5%" align="center"><strong>BIL</strong></td>
      <td width="25%"><strong>TAJUK</strong></td>
      <td width="10%" align="center"><strong>TARIKH</strong></td>
      <td width="15%" align="center"><strong>MASA</strong></td>
    </tr>
#foreach ($fail in $ListMesyuarat)
	#if ($fail.ListNo == '') 
    	#set ($row = 'row1')
    #elseif ($fail.ListNo % 2 != 0)
    	#set ($row = 'row1')
    #else 
    	#set ($row = 'row2')
    #end
    <tr>
    #if ($fail.ListNo != '') 
      <td class="$row" valign="top" align="center">$fail.ListNo</td>
      <td class="$row" valign="top"><a href="javascript:viewMesyuarat('$fail.ListIDMesyuarat')" style="color:#0000FF">$fail.ListNoFail</a></td>
       #if($idNegeri == '16')
      <td class="$row" valign="top">$fail.ListUrusetiaSeksyen</td>
      #else
       <td class="$row" valign="top">$fail.ListUrusetiaUnit</td>
      #end
      <td class="$row" valign="top" align="center">$fail.ListBil</td>
      <td class="$row" valign="top">$fail.ListTajuk</td>
      <td class="$row" valign="top" align="center">$fail.ListTarikh</td>
      <td class="$row" valign="top" align="center">$fail.ListMasa</td>
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
<input type="hidden" id="idNegeri" name="idNegeri" value="$idNegeri" />
<input type="hidden" id="Mesyuarat_Unit" name="Mesyuarat_Unit" value="$Mesyuarat_Unit" />
<input name="actionx" id="actionx" type="hidden" value="$actionx" />
<script>
function viewMesyuarat(ID_MESYUARAT) {	

document.${formName}.action = "$EkptgUtil.getTabID("Mesyuarat",$portal_role)?_portal_module=ekptg.view.pfd.FrmViewMesyuaratSenarai&actionx=daftarMinit&ID_MESYUARAT=" + ID_MESYUARAT;
	document.${formName}.submit();
	
}
function searchMesyuarat() {
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.FrmViewMesyuaratSenarai&actionx=carianMesyuarat";
	document.${formName}.submit();
}
function emptyMesyuarat() {
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.FrmViewMesyuaratSenarai&actionx=";
}
function newMesyuarat() {
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.FrmViewMesyuaratSenarai&actionx=daftarMesyuarat&ID_MESYUARAT=";
	document.${formName}.submit();
}
function printList() {
		var url = "../servlet/ekptg.report.pfd.PFDMesyuarat?reportType=PDF&ID_MESYUARAT=161016";
	    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus();
}
</script>