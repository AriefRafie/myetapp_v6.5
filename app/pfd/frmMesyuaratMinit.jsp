<style type="text/css">
<!--
  .link {color: #0000FF}
  .mandatori {color:#FF0000}
-->
</style>
<input type="hidden" id="idNegeri" name="idNegeri" value="$idNegeri" />
<input type="hidden" id="SOC_UNIT" name="SOC_UNIT" value="$SOC_UNIT" />
<input type="hidden" id="Mesyuarat_Unit" name="Mesyuarat_Unit" value="$Mesyuarat_Unit" />
<fieldset>
  <legend><strong>MAKLUMAT MESYUARAT</strong></legend>
  <br />
  <table width="100%" border="0" align="center">
    <tr>
      <td width="29%" align="right" valign="top" nowrap="nowrap"><div align="left"><strong>No Fail / No Rujukan Mesyuarat</strong></div></td>
      <td width="1%" align="center" valign="top" nowrap="nowrap">:</td>
      <td width="70%" align="left" valign="top" nowrap="nowrap" class="link">$Mesyuarat_NoFail </td>
    </tr>
    <tr>
      <td  align="right" valign="top" nowrap="nowrap"><div align="left"><strong>Tajuk Mesyuarat</strong></div></td>
      <td  align="center" valign="top" nowrap="nowrap">:</td>
      <td  align="left" valign="top" nowrap="nowrap" class="link">
        $Mesyuarat_Tajuk      </td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><div align="left"><strong>Kategori Mesyuarat</strong></div></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap" class="link">#if($Mesyuarat_Kategori == '1')
      AD-HOC
      #else
      RUTIN
      #end </td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><div align="left"><strong>Tarikh</strong></div></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap" class="link">$Mesyuarat_Tarikh</td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><div align="left"><strong>Masa</strong></div></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap" class="link">$Mesyuarat_Masa</td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><div align="left"><strong>Lokasi</strong></div></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap" class="link">$Mesyuarat_Lokasi</td>
    </tr>
        #if($idNegeri == '16')
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><div align="left"><strong>Urusetia / Seksyen</strong></div></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap" class="link">$Mesyuarat_Seksyen</td>
    </tr>
        #else
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><div align="left"><strong>Urusetia / Unit</strong></div></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap" class="link">$Mesyuarat_Unit</td>
    </tr>
    	#end	
    <tr>
      <td colspan="3"><div align="left"></div></td>
    </tr>
  </table>  
</fieldset>
<br />
<fieldset>
  <legend><strong>DAFTAR MINIT MESYUARAT</strong></legend>
  <br />
  <div id="TabbedPanels1" class="TabbedPanels">
    <ul class="TabbedPanelsTabGroup">
      <li class="TabbedPanelsTab" tabindex="0" onclick="doChangeTab(0, 'daftarMinit');"><strong>KEHADIRAN</strong></li>
      <li class="TabbedPanelsTab" tabindex="1" onclick="doChangeTab(1, 'daftarMesyuaratAgenda');"><strong>AGENDA</strong></li>
      <li class="TabbedPanelsTab" tabindex="2" onclick="doChangeTab(2, 'daftarMesyuaratMinit');"><strong>MINIT</strong></li>
      <li class="TabbedPanelsTab" tabindex="3" onclick="doChangeTab(3, 'daftarMesyuaratSubMinit');"><strong>SUB-MINIT</strong></li>
    </ul>
    <div class="TabbedPanelsContentGroup">
      <div class="TabbedPanelsContent">
      <!-- content agenda -->
      #if ($selectedTab == '0')
        #parse ("app/pfd/frmMesyuaratMinitTabKehadiran.jsp")
      #end
      <!-- end content agenda -->
      </div>
      <div class="TabbedPanelsContent">
      <!-- content minit -->
      #if ($selectedTab == '1')
        #parse ("app/pfd/frmMesyuaratMinitTabAgenda.jsp")
      #end
      <!-- end content minit -->
      </div>
      <div class="TabbedPanelsContent">
      <!-- content subminit -->
      #if ($selectedTab == '2')
        #parse ("app/pfd/frmMesyuaratMinitTabMinit.jsp")
      #end
      <!-- end content subminit -->
      </div>
      <div class="TabbedPanelsContent">
      <!-- content subminit -->
      #if ($selectedTab == '3')
        #parse ("app/pfd/frmMesyuaratMinitTabSubMinit.jsp")
      #end
      <!-- end content subminit -->
      </div>
    </div>
    <br />
  </div>
  <br />
  <div style="text-align:center">
    <input type="button" id="cmdPrev" name="cmdPrev" value=" Kembali" onclick="daftarAhli();" />
    <input type="button" id="cmdNext" name="cmdNext" value="Seterusnya" onclick="selesaiMesyuarat();" />
  </div>
</fieldset>
<br />
<fieldset>
#if ($selectedTab == '1')
  <legend><strong>SENARAI AGENDA MESYUARAT</strong></legend>
#elseif ($selectedTab == '2')
  <legend><strong>SENARAI MINIT MESYUARAT</strong></legend>
#elseif ($selectedTab == '3')
  <legend><strong>SENARAI SUB-MINIT MESYUARAT</strong></legend>
#end
  <br />
#if ($selectedTab != '0')  
  <table style="width:100%; border:1px solid #000000" align="center">
    <tr class="table_header">
	#if ($selectedTab == '1')
      <td width="5%" align="center" valign="top" nowrap="nowrap">No</td>
      <td width="75%" valign="top" nowrap="nowrap">Agenda</td>
      <td width="15%" valign="top" nowrap="nowrap">&nbsp;</td>
	#elseif ($selectedTab == '2')
      <td width="5%" align="center" valign="top" nowrap="nowrap">No</td>
      <td width="35%" valign="top" nowrap="nowrap">Agenda</td>
      <td width="45%" valign="top" nowrap="nowrap">Minit</td>
      <td width="15%" valign="top" nowrap="nowrap">&nbsp;</td>
	#elseif ($selectedTab == '3')
      <td width="5%" align="center" valign="top" nowrap="nowrap">No</td>
      <td width="20%" valign="top" nowrap="nowrap">Agenda</td>
      <td width="20%" valign="top" nowrap="nowrap">Minit</td>
      <td width="40%" valign="top" nowrap="nowrap">Sub-Minit</td>
      <td width="15%" valign="top" nowrap="nowrap">&nbsp;</td>
	#end
    </tr>
	#set ($fail = '')
	#foreach ($fail in $List_CarianMinit)
		#if ($fail.No == '') 
	    	#set ($row = 'row1')
	    #elseif ($fail.No % 2 != 0)	
		    #set ($row = 'row1')
	    #else 
		    #set ($row = 'row2')
	    #end
    <tr>
      <td class="$row" align="center">$fail.No</td>
    	#if ($fail.No != '') 
			#if ($selectedTab == '1')
      <td class="$row"><a href="javascript:viewMesyuaratAgenda('$fail.IDMinit')" style="color:#0000FF">$fail.Agenda</a></td>
			#elseif ($selectedTab == '2')
      <td class="$row"><a href="javascript:viewMesyuaratMinit('$fail.IDMinit')" style="color:#0000FF">$fail.Agenda</a></td>
			#elseif ($selectedTab == '3')
      <td class="$row"><a href="javascript:viewMesyuaratSubMinit('$fail.IDMinit')" style="color:#0000FF">$fail.Agenda</a></td>
			#end
	    #else
      <td class="$row">$fail.Agenda</td>
    	#end
		#if ($selectedTab == '1')
      <td class="$row" align="center">
			#if ($hideSaveButton != 'true')  
        <input type="button" id="cmdHapusMinit" name="cmdHapusMinit" value="Hapus" onclick="hapusMesyuaratAgenda('$fail.IDMinit')" />
			#end
      </td>
		#elseif ($selectedTab == '2')
      <td class="$row">$fail.Minit</td>
      <td class="$row" align="center">
			#if ($hideSaveButton != 'true')  
        <input type="button" id="cmdTambahMinit" name="cmdTambahMinit" value="Tambah" onclick="doClickAddButton('1', '$fail.IDMinit')" />&nbsp;<input type="button" id="cmdHapusMinit" name="cmdHapusMinit" value="Hapus" onclick="hapusMesyuaratMinit('$fail.IDMinit')" />
			#end
      </td>
		#elseif ($selectedTab == '3')
      <td class="$row">$fail.Minit</td>
      <td class="$row">$fail.SubMinit</td>
      <td class="$row" align="center">
			#if ($hideSaveButton != 'true')  
      <input type="button" id="cmdTambahSubMinit" name="cmdTambahSubMinit" value="Tambah" onclick="doClickAddButton('2', '$fail.IDMinit')" />&nbsp;<input type="button" id="cmdHapusMinit" name="cmdHapusMinit" value="Hapus" onclick="hapusMesyuaratSubMinit('$fail.IDMinit')" />
			#end
      </td>
		#end
    </tr>
	#end
  </table>
#end
</fieldset>
<input type="hidden" id="ID_MESYUARAT" name="ID_MESYUARAT" value="$ID_MESYUARAT" />
<input type="hidden" id="ID_AHLI" name="ID_AHLI" value="$ID_AHLI" />
<input type="hidden" id="ID_AGENDA" name="ID_AGENDA" value="$ID_AGENDA" />
<input type="hidden" id="ID_MINIT" name="ID_MINIT" value="$ID_MINIT" />
<input type="hidden" id="ID_SUBMINIT" name="ID_SUBMINIT" value="$ID_SUBMINIT" />
<input type="hidden" id="addType" name="addType" />
<input type="hidden" id="addId" name="addId" />
<input type="hidden" id="PrevPage" name="PrevPage" value="3" />
<input type="hidden" id="selectedTab" name="selectedTab" value="$selectedTab" />
<input type="hidden" id="actionx" name="actionx" value="$actionx" />
<script type="text/javascript">
<!--
  var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1", {defaultTab:$selectedTab});
//-->
</script>
<script type="text/javascript">
  function simpanKehadiran() {
	  document.${formName}.action = "?_portal_module=ekptg.view.pfd.FrmViewMesyuarat&actionx=simpanKehadiran";
	  document.${formName}.submit();
  }
  function tambahMesyuaratAgenda() {
	  document.${formName}.action = "?_portal_module=ekptg.view.pfd.FrmViewMesyuarat&actionx=tambahMesyuaratAgenda";
	  document.${formName}.submit();
  }
  function viewMesyuaratAgenda(ID_Agenda) {
	  document.${formName}.action = "?_portal_module=ekptg.view.pfd.FrmViewMesyuarat&actionx=viewMesyuaratAgenda&ID_AGENDA=" + ID_Agenda;
	  document.${formName}.submit();
  }
  function simpanMesyuaratAgenda() {
	  document.${formName}.action = "?_portal_module=ekptg.view.pfd.FrmViewMesyuarat&actionx=simpanMesyuaratAgenda";
	  document.${formName}.submit();
  }
  function hapusMesyuaratAgenda(ID_Agenda) {
	  document.${formName}.action = "?_portal_module=ekptg.view.pfd.FrmViewMesyuarat&actionx=hapusMesyuaratAgenda&ID_AGENDA=" + ID_Agenda;
	  document.${formName}.submit();
  }
  function tambahMesyuaratMinit() {
	  document.${formName}.action = "?_portal_module=ekptg.view.pfd.FrmViewMesyuarat&actionx=tambahMesyuaratMinit";
	  document.${formName}.submit();
  }
  function viewMesyuaratMinit(ID_Minit) {
	  document.${formName}.action = "?_portal_module=ekptg.view.pfd.FrmViewMesyuarat&actionx=viewMesyuaratMinit&ID_MINIT=" + ID_Minit;
	  document.${formName}.submit();
  }
  function simpanMesyuaratMinit() {
	  if (document.${formName}.SOC_AGENDA.value == '') {
		  alert('Sila pilih agenda mesyuarat.');
		  document.${formName}.SOC_AGENDA.focus();
		  return;
	  }
	  document.${formName}.action = "?_portal_module=ekptg.view.pfd.FrmViewMesyuarat&actionx=simpanMesyuaratMinit";
	  document.${formName}.submit();
  }
  function hapusMesyuaratMinit(ID_Minit) {
	  document.${formName}.action = "?_portal_module=ekptg.view.pfd.FrmViewMesyuarat&actionx=hapusMesyuaratMinit&ID_MINIT=" + ID_Minit;
	  document.${formName}.submit();
  }
  function tambahMesyuaratSubMinit() {
	  if (document.${formName}.SOC_AGENDA.value == '') {
		  alert('Sila pilih agenda mesyuarat.');
		  document.${formName}.SOC_AGENDA.focus();
		  return;
	  }
	  if (document.${formName}.SOC_MINIT.value == '') {
		  alert('Sila pilih minit mesyuarat.');
		  document.${formName}.SOC_MINIT.focus();
		  return;
	  }
	  document.${formName}.action = "?_portal_module=ekptg.view.pfd.FrmViewMesyuarat&actionx=tambahMesyuaratSubMinit";
	  document.${formName}.submit();
  }
  function viewMesyuaratSubMinit(ID_SubMinit) {
	  document.${formName}.action = "?_portal_module=ekptg.view.pfd.FrmViewMesyuarat&actionx=viewMesyuaratSubMinit&ID_SUBMINIT=" + ID_SubMinit;
	  document.${formName}.submit();
  }
  function simpanMesyuaratSubMinit() {
	  document.${formName}.action = "?_portal_module=ekptg.view.pfd.FrmViewMesyuarat&actionx=simpanMesyuaratSubMinit";
	  document.${formName}.submit();
  }
  function hapusMesyuaratSubMinit(ID_SubMinit) {
	  document.${formName}.action = "?_portal_module=ekptg.view.pfd.FrmViewMesyuarat&actionx=hapusMesyuaratSubMinit&ID_SUBMINIT=" + ID_SubMinit;
	  document.${formName}.submit();
  }
  function daftarAhli() {
	  document.${formName}.action = "?_portal_module=ekptg.view.pfd.FrmViewMesyuarat&actionx=daftarAhli";
	  document.${formName}.submit();
  }
  function selesaiMesyuarat() {
	  document.${formName}.action = "?_portal_module=ekptg.view.pfd.FrmViewMesyuarat&actionx=selesaiMesyuarat";
	  document.${formName}.submit();
  }
  function cetakMesyuarat() {
	  var url = "../servlet/ekptg.report.pfd.PFDReport?reportType=PDF&TYPE=4&MINITMESYUARAT_ID=$ID_MESYUARAT";
	  var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	  if ((document.window != null) && (!hWnd.opener))
	  hWnd.opener = document.window;
	  if (hWnd.focus != null) hWnd.focus();
  }
  function doChangeTab(tabId, action) {
	  document.${formName}.ID_AGENDA.value = "";
	  document.${formName}.ID_MINIT.value = "";
	  document.${formName}.ID_SUBMINIT.value = "";
   	  document.${formName}.selectedTab.value = tabId;
      document.${formName}.action.value = action;
	  document.${formName}.actionx.value = action;
	 
      doAjaxCall${formName}("");
  }
  function doChangeSOC(submitType) {
      document.${formName}.action = "?_portal_module=ekptg.view.pfd.FrmViewMesyuarat&actionx=$actionx";
      doAjaxCall${formName}(submitType);
  }
  function doClickAddButton(AddType, AddId) {
	  var doAction = '';
	  if (AddType == '1') {
		  doAction = 'daftarMesyuaratMinit';
	  } else if (AddType == '2') {
		  doAction = 'daftarMesyuaratSubMinit';
	  } else {
		  doAction = 'daftarMesyuaratMinit';
	  }
	  document.${formName}.addType.value = AddType;
	  document.${formName}.addId.value = AddId;
      document.${formName}.action = "?_portal_module=ekptg.view.pfd.FrmViewMesyuarat&actionx=" + doAction + "&addType=" + AddType + "&addId=" + AddId;
      doAjaxCall${formName}("clickAddButton");
  }
  function checkMaklumanMinit() {
	  if (document.${formName}.Minit_Makluman.checked) {
		  document.${formName}.Minit_Makluman.disabled = 'disabled';
	  } else {
		  document.${formName}.Minit_Makluman.disabled = '';
	  }
  }
  function checkMaklumanSubMinit() {
	  if (document.${formName}.SubMinit_Makluman.checked) {
		  document.${formName}.SubMinit_Makluman.disabled = 'disabled';
	  } else {
		  document.${formName}.SubMinit_Makluman.disabled = '';
	  }
  }
</script>