<style type="text/css">
<!--
  .link {color: #0000FF}
  .mandatori {color:#FF0000}
-->
</style>
<br />
#if ($ID_PERMOHONAN == '')
<fieldset>
  <div>
    Sila pilih permohonan bagi semakan pihak JKSM di menu Keputusan Perbicaraan dalam tab Seksyen 8 terlebih dahulu.
  </div>
</fieldset>
#else
#if ($sendJKSM == 'true')
<div class="success">Permohonan telah berjaya dihantar ke JKSM.</div>
<br />
#end
#if ($action2 == 'deleteBorangJ' && $deleteBorangJ == 'false')
<div class="error">Permohonan tidak berjaya untuk dihapuskan. Sila cuba sebentar lagi.</div>
<br />
#end 
#if($!headerppk.size()>0) 
#parse("app/ppk/headerppk.jsp")
#end
<div id="TabbedPanels1" class="TabbedPanels">
  <ul class="TabbedPanelsTabGroup">
    <li class="TabbedPanelsTab" tabindex="0" onclick="doChangeTab(0);">PERMOHONAN</li>
    <li class="TabbedPanelsTab" tabindex="1" onclick="doChangeTab(1);">KEPUTUSAN</li>
    <li class="TabbedPanelsTab" tabindex="2" onclick="doChangeTab(2);">SIJIL FARAID</li>
  </ul>
  <div class="TabbedPanelsContentGroup">
    <div class="TabbedPanelsContent">
    <!-- content permohonan -->
    #if ($selectedTab == '0')
      #parse ("app/integrasi/frmBorangJTabPermohonan.jsp")
    #end
    <!-- end content permohonan -->
    </div>
    <div class="TabbedPanelsContent">
    <!-- content keputusan -->
    #if ($selectedTab == '1')
      #parse ("app/integrasi/frmBorangJTabKeputusan.jsp")
    #end
    <!-- end content keputusan -->
    </div>
    <div class="TabbedPanelsContent">
    <!-- content keputusan -->
    #if ($selectedTab == '2')
      #parse ("app/integrasi/frmBorangJTabSijilFaraid.jsp")
    #end
    <!-- end content keputusan -->
    </div>
  </div>
  <br />
</div>
#if ($selectedTab == '0')
<fieldset>
<legend>Maklumat Tangguh</legend>
  <table width="100%" border="0" align="center">
    <tr>
      <td align="left" valign="top" nowrap="nowrap" width="29%">Catatan / Keterangan Tangguh</td>
      <td align="center" valign="top" nowrap="nowrap" width="1%">:</td>
      <td align="left" valign="top" nowrap="nowrap" width="70%"><textarea name="MP_FAKTAGUAMAN" cols="50" rows="3" id="MP_FAKTAGUAMAN" style="text-transform:uppercase" readonly="readonly" class="disabled">$!SEBAB_TANGGUH</textarea></td>
    </tr>
    <tr>
      <td align="left" valign="top" nowrap="nowrap">Pendapat / Keputusan Mahkamah</td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap"><textarea name="MP_PENDAPAT" cols="50" rows="3" id="MP_PENDAPAT" style="text-transform:uppercase" readonly="readonly" class="disabled">$!KEPUTUSAN_MAHKAMAH</textarea></td>
    </tr>
  </table>
  
</fieldset>
  <br />
<fieldset>
  <table width="100%" border="0" align="center">
    <tr>
      <td align="left" valign="top" nowrap="nowrap" width="29%">Fakta Guaman</td>
      <td align="center" valign="top" nowrap="nowrap" width="1%">:</td>
      <td align="left" valign="top" nowrap="nowrap" width="70%"><textarea name="MP_FAKTAGUAMAN" cols="50" rows="3" id="MP_FAKTAGUAMAN" style="text-transform:uppercase" readonly="readonly" class="disabled">$!MP_FAKTAGUAMAN</textarea></td>
    </tr>
    <tr>
      <td align="left" valign="top" nowrap="nowrap">Pendapat / Keputusan / Arahan</td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap"><textarea name="MP_PENDAPAT" cols="50" rows="3" id="MP_PENDAPAT" style="text-transform:uppercase" readonly="readonly" class="disabled">$!MP_PENDAPAT</textarea></td>
    </tr>
  </table>
  </fieldset>
  <br />
  <fieldset>
    <legend><strong>SENARAI WARIS</strong></legend>
    <table width="100%" cellspacing="0" cellpadding="2">
      <tr class="table_header">
        <td width="5%" scope="row" align="center"><strong>BIL</strong></td>
        <td width="35%"><strong>NAMA</strong></td>
        <td width="35%"><strong>MyID</strong></td>
        <td width="25%"><strong>HUBUNGAN OB</strong></td>
      </tr>
#set ($list = '')
#foreach ($list in $ListWaris)
	#if ($list.bil == '') 
    	#set ($row = 'row1')
    #elseif ($list.bil % 2 != 0)
    	#set ($row = 'row1')
    #else 
    	#set ($row = 'row2')
    #end
      <tr>
    #if ($list.No != '') 
        
                        <td class="$row">$list.bil</td>
                        <td class="$row">$list.nama_ob.toUpperCase()</td>
                        <td class="$row">$list.no_kp_baru</td>
                        <td class="$row">$list.keterangan</td>
                        
                        
                  
                   
    #else
        <td colspan="4" class="$row" style="text-align:center">Tiada Rekod</td>
    #end
      </tr>
#end
      <tr>
        <td colspan="4">&nbsp;</td>
      </tr>
    </table>
  </fieldset>

#end
<div style="text-align:center">
  <br />
  <input id="cmdKembali" name="cmdKembali" type="button" value="Kembali" onclick="backBorangJ();" />&nbsp;
  <input id="cmdSimpan" name="cmdSimpan" type="button" value="Hantar ke JKSM" onclick="sendBorangJ();" />&nbsp;
#if ($haveINTData == 'true')  
  <input id="cmdHapus" name="cmdHapus" type="button" value="Batal Permohonan JKSM" onclick="deleteBorangJ();" />
#end  
</div>
<input type="hidden" id="ID_PERMOHONAN" name="ID_PERMOHONAN" value="$ID_PERMOHONAN" />
<input type="hidden" id="selectedTab" name="selectedTab" />
<input type="hidden" id="action2" name="action2" value="$action2" />


#parse("app/ppk/headerppk_script.jsp")

<script type="text/javascript">
<!--
  
  var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1", {defaultTab:$selectedTab});
//-->
</script>



<script type="text/javascript">
  function doChangeTab(TabID) {
      document.${formName}.action2.value = "viewBorangJ";
      document.${formName}.selectedTab.value = TabID;
      document.${formName}.action = "?_portal_module=ekptg.view.integrasi.FrmViewBorangJ&action2=viewBorangJ&selectedTab=" + TabID;
      //doAjaxCall${formName}("");
	  document.${formName}.submit();
  }
  function sendBorangJ() {
	  if (!window.confirm("Adakah anda pasti?")) return;
	  document.${formName}.action = "?_portal_module=ekptg.view.integrasi.FrmViewBorangJ&action2=sendBorangJ&ID_PERMOHONAN=$ID_PERMOHONAN";
	  document.${formName}.method = "POST";
	  document.${formName}.submit();
  }
  function deleteBorangJ() {
	  if (!window.confirm("Adakah anda pasti? Permohonan yang telah dihantar ke JKSM juga akan dibatalkan.")) return;
	  document.${formName}.action = "?_portal_module=ekptg.view.integrasi.FrmViewBorangJ&action2=deleteBorangJ&ID_PERMOHONAN=$ID_PERMOHONAN";
	  document.${formName}.method = "POST";
	  document.${formName}.submit();
  }
  function backBorangJ() {
	  document.${formName}.action = "?_portal_module=ekptg.view.integrasi.FrmViewMyInfoBorangJ&action2=";
	  document.${formName}.method = "POST";
	  document.${formName}.submit();
  }
</script>
#end