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
</br>
<fieldset>
  <legend><strong>MAKLUMAT MESYUARAT</strong></legend>
  <table width="100%" align="center">
   <tr>
      <td style="width:30%; text-align:right">&nbsp;</td>
      <td style="width:70%">
        <input type="radio" id="optJenisReport" name="optJenisReport" value="6" checked="checked" />Cetak Surat Panggilan Mesyuarat<br />      </td>
    </tr>
    <tr>
      <td style="width:30%; text-align:right">&nbsp;</td>
      <td>
        <input type="radio" id="optJenisReport" name="optJenisReport" value="4" />Cetak Minit Mesyuarat<br />      </td>
    </tr>
    
    <tr>
      <td style="text-align:right">&nbsp;</td>
      <td>
        <input type="radio" id="optJenisReport" name="optJenisReport" value="15" />Cetak Minit Mesyuarat (Maklumbalas)<br />      </td>
    </tr>
            <tr>
      <td style="text-align:right">&nbsp;</td>
      <td>
        <input type="radio" id="optJenisReport" name="optJenisReport" value="10" />Cetak Borang Kehadiran<br />      </td>
    </tr>
  
    <!--tr>
      <td style="text-align:right">5.</td>
      <td>
        <input type="radio" id="optJenisReport" name="optJenisReport" value="8" />Cetak Maklumbalas<br />
      </td>
    </tr-->
      <td style="text-align:right">&nbsp;</td>
      <td>
      #if($idNegeri == '16')
              <input type="button" id="cmdCetakMesyuarat2" name="cmdCetakMesyuarat2" value="Cetak" onclick="cetakMesyuarat('$ID_MESYUARAT');" />
      #else
            <input type="button" id="cmdCetakMesyuarat" name="cmdCetakMesyuarat" value="Cetak" onclick="cetakMesyuaratNegeri('$ID_MESYUARAT');" />
      #end      </td>
      </tr>
  </table>
</fieldset>
<br />
<fieldset>
  <legend><strong>KEHADIRAN & MINIT MESYUARAT</strong></legend>
  <br />
  <div id="TabbedPanels1" class="TabbedPanels">
    <ul class="TabbedPanelsTabGroup">
      <li class="TabbedPanelsTab" tabindex="0" onclick="doChangeTab(0, 'daftarMinit');"><strong>KEHADIRAN</strong></li>
      <li class="TabbedPanelsTab" tabindex="1" onclick="doChangeTab(1, 'viewMesyuaratAgenda');"><strong>AGENDA</strong></li>
      <li class="TabbedPanelsTab" tabindex="2" onclick="doChangeTab(2, 'viewMesyuaratMinit');"><strong>MINIT</strong></li>
      <li class="TabbedPanelsTab" tabindex="3" onclick="doChangeTab(3, 'viewMesyuaratSubMinit');"><strong>SUB-MINIT</strong></li>
    </ul>
    <div class="TabbedPanelsContentGroup">
      <div class="TabbedPanelsContent">
      <!-- content agenda -->
      #if ($selectedTab == '0')
        #parse ("app/pfd/frmMesyuaratMinitTabKehadiranSenarai.jsp")
      #end
      <!-- end content agenda -->
      </div>
      <div class="TabbedPanelsContent">
      <!-- content minit -->
      #if ($selectedTab == '1')
        #parse ("app/pfd/frmMesyuaratMinitTabAgendaSenarai.jsp")
      #end
      <!-- end content minit -->
      </div>
      <div class="TabbedPanelsContent">
      <!-- content subminit -->
      #if ($selectedTab == '2')
        #parse ("app/pfd/frmMesyuaratMinitTabMinitSenarai.jsp")
      #end
      <!-- end content subminit -->
      </div>
      <div class="TabbedPanelsContent">
      <!-- content subminit -->
      #if ($selectedTab == '3')
        #parse ("app/pfd/frmMesyuaratMinitTabSubMinitSenarai.jsp")
      #end
      <!-- end content subminit -->
      </div>
    </div>
    <br />
  </div>
  <br />
  <div style="text-align:center"></div>
</fieldset>
<br />
<input type="hidden" id="ID_MESYUARAT" name="ID_MESYUARAT" value="$ID_MESYUARAT" />
<input type="hidden" id="ID_AHLI" name="ID_AHLI" value="$ID_AHLI" />
<input type="hidden" id="ID_AGENDA" name="ID_AGENDA" value="$ID_AGENDA" />
<input type="hidden" id="ID_MINIT" name="ID_MINIT" value="$ID_MINIT" />
<input type="hidden" id="ID_SUBMINIT" name="ID_SUBMINIT" value="$ID_SUBMINIT" />
<input type="hidden" id="addType" name="addType" />
<input type="hidden" id="addId" name="addId" />
<input type="hidden" id="PrevPage" name="PrevPage" value="3" />
<input type="hidden" id="selectedTab" name="selectedTab" value="$selectedTab" />
<input type="hidden" id="agenda" name="agenda" value="$agenda" />
<input type="hidden" id="minit" name="minit" value="$minit" />
<input type="hidden" id="subminit" name="subminit" value="$subminit" />
<input type="hidden" id="actionx" name="actionx" value="$actionx" />
<script type="text/javascript">
<!--
  var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1", {defaultTab:$selectedTab});
//-->
</script>
<script type="text/javascript">
  function simpanKehadiran() {
	  document.${formName}.action = "?_portal_module=ekptg.view.pfd.FrmViewMesyuaratSenarai&actionx=simpanKehadiran";
	  document.${formName}.submit();
  }
  function tambahMesyuaratAgenda() {
	  document.${formName}.action = "?_portal_module=ekptg.view.pfd.FrmViewMesyuaratSenarai&actionx=tambahMesyuaratAgenda";
	  document.${formName}.submit();
  }
  function viewMesyuaratAgenda(ID_Agenda) {
	  document.${formName}.action = "?_portal_module=ekptg.view.pfd.FrmViewMesyuaratSenarai&actionx=viewMesyuaratAgenda&ID_AGENDA=" + ID_Agenda;
	  document.${formName}.submit();
  }
  function viewMesyuaratAgendaEdit(ID_Agenda) {
	  document.${formName}.action = "?_portal_module=ekptg.view.pfd.FrmViewMesyuaratSenarai&actionx=viewMesyuaratAgendaEdit&ID_AGENDA=" + ID_Agenda;
	  document.${formName}.submit();
  }
  function simpanMesyuaratAgenda() {
	  document.${formName}.action = "?_portal_module=ekptg.view.pfd.FrmViewMesyuaratSenarai&actionx=simpanMesyuaratAgenda";
	  document.${formName}.submit();
  }
  function hapusMesyuaratAgenda(ID_Agenda) {
	  document.${formName}.action = "?_portal_module=ekptg.view.pfd.FrmViewMesyuaratSenarai&actionx=hapusMesyuaratAgenda&ID_AGENDA=" + ID_Agenda;
	  document.${formName}.submit();
  }
  function tambahMesyuaratMinit() {
	  document.${formName}.action = "?_portal_module=ekptg.view.pfd.FrmViewMesyuaratSenarai&actionx=tambahMesyuaratMinit";
	  document.${formName}.submit();
  }
  function viewMesyuaratMinit(ID_Minit) {
	  document.${formName}.action = "?_portal_module=ekptg.view.pfd.FrmViewMesyuaratSenarai&actionx=viewMesyuaratMinit&ID_MINIT=" + ID_Minit;
	  document.${formName}.submit();
  }
  function viewMesyuaratMinitEdit(ID_Minit) {
	  document.${formName}.action = "?_portal_module=ekptg.view.pfd.FrmViewMesyuaratSenarai&actionx=viewMesyuaratMinitEdit&ID_MINIT=" + ID_Minit;
	  document.${formName}.submit();
  }
  function simpanMesyuaratMinitEdit() {
	  document.${formName}.action = "?_portal_module=ekptg.view.pfd.FrmViewMesyuaratSenarai&actionx=simpanMesyuaratMinitEdit";
	  document.${formName}.submit();
  }
  function hapusMesyuaratMinit(ID_Minit) {
	  document.${formName}.action = "?_portal_module=ekptg.view.pfd.FrmViewMesyuaratSenarai&actionx=hapusMesyuaratMinit&ID_MINIT=" + ID_Minit;
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
	  document.${formName}.action = "?_portal_module=ekptg.view.pfd.FrmViewMesyuaratSenarai&actionx=tambahMesyuaratSubMinit";
	  document.${formName}.submit();
  }
  function viewMesyuaratSubMinit(ID_SubMinit) {
	  document.${formName}.action = "?_portal_module=ekptg.view.pfd.FrmViewMesyuaratSenarai&actionx=viewMesyuaratSubMinit&ID_SUBMINIT=" + ID_SubMinit;
	  document.${formName}.submit();
  }
  function viewMesyuaratSubMinitEdit(ID_SubMinit) {
	  document.${formName}.action = "?_portal_module=ekptg.view.pfd.FrmViewMesyuaratSenarai&actionx=viewMesyuaratSubMinitEdit&ID_SUBMINIT=" + ID_SubMinit;
	  document.${formName}.submit();
  }
  function simpanMesyuaratSubMinitEdit() {
	  document.${formName}.action = "?_portal_module=ekptg.view.pfd.FrmViewMesyuaratSenarai&actionx=simpanMesyuaratSubMinitEdit";
	  document.${formName}.submit();
  }
  function hapusMesyuaratSubMinit(ID_SubMinit) {
	  document.${formName}.action = "?_portal_module=ekptg.view.pfd.FrmViewMesyuaratSenarai&actionx=hapusMesyuaratSubMinit&ID_SUBMINIT=" + ID_SubMinit;
	  document.${formName}.submit();
  }
  function daftarAhli() {
	  document.${formName}.action = "?_portal_module=ekptg.view.pfd.FrmViewMesyuaratSenarai&actionx=daftarAhli";
	  document.${formName}.submit();
  }
  function selesaiMesyuarat() {
	  document.${formName}.action = "?_portal_module=ekptg.view.pfd.FrmViewMesyuaratSenarai&actionx=selesaiMesyuarat";
	  document.${formName}.submit();
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
      document.${formName}.action = "?_portal_module=ekptg.view.pfd.FrmViewMesyuaratSenarai&actionx=$actionx";
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
      document.${formName}.action = "?_portal_module=ekptg.view.pfd.FrmViewMesyuaratSenarai&actionx=" + doAction + "&addType=" + AddType + "&addId=" + AddId;
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
  
   function cetakMesyuarat(id_mesyuarat) {
	  var cetakmaklumbalas = "no";
	  for (var i=0; i < document.${formName}.optJenisReport.length; i++) {
		  if (document.${formName}.optJenisReport[i].checked) {
			  var TYPE = document.${formName}.optJenisReport[i].value;
			  
			  if(TYPE == "15")
			  {
				cetakmaklumbalas = "yes";  
			  }
		  }
	  }
	  
	  if(cetakmaklumbalas == "no")
	  {
	  var url = "../servlet/ekptg.report.pfd.PFDReport?reportType=PDF&TYPE=" + TYPE + "&ID_MESYUARAT="+id_mesyuarat+"&MINITMESYUARAT_ID=$ID_MESYUARAT";
	  var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	  if ((document.window != null) && (!hWnd.opener))
	  hWnd.opener = document.window;
	  if (hWnd.focus != null) hWnd.focus();
	  }
	  
	  if(cetakmaklumbalas == "yes")
	  {
	   var url = "../servlet/ekptg.report.pfd.CetakMaklumBalas?ID_MESYUARAT="+id_mesyuarat;  
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	  }
	  
  }
  
    function cetakMesyuaratNegeri(id_mesyuarat) {
	  var cetakmaklumbalas = "no";
	  for (var i=0; i < document.${formName}.optJenisReport.length; i++) {
		  if (document.${formName}.optJenisReport[i].checked) {
			  var TYPE = document.${formName}.optJenisReport[i].value;
			  
			  if(TYPE == "15")
			  {
				cetakmaklumbalas = "yes";  
			  }
		  }
	  }
	  
	  if(cetakmaklumbalas == "no")
	  {
	  var url = "../servlet/ekptg.report.pfd.PFDReportNegeri?reportType=PDF&TYPE=" + TYPE + "&ID_MESYUARAT="+id_mesyuarat+"&MINITMESYUARAT_ID=$ID_MESYUARAT";
	  var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	  if ((document.window != null) && (!hWnd.opener))
	  hWnd.opener = document.window;
	  if (hWnd.focus != null) hWnd.focus();
	  }
	  
	  if(cetakmaklumbalas == "yes")
	  {
	   var url = "../servlet/ekptg.report.pfd.CetakMaklumBalas?ID_MESYUARAT="+id_mesyuarat;  
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	  }
	  
  }
  
</script>