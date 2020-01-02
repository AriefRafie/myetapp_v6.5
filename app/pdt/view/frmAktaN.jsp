<style type="text/css">
<!--
  .mandatory {color: #FF0000}
-->
</style>
<fieldset>
  <legend>MAKLUMAT  AKTA</legend>
<table width="100%" border="0">      
    
    <tr>
      <td width="29%" align="right" valign="top"><strong>No Akta</strong></td>
      <td width="1%" align="center" valign="top">:</td>
      <td width="70%" valign="top">$Akta_NoAkta</td>
    </tr>
    <tr>
      <td align="right" valign="top"><strong>Nama Akta</strong></td>
      <td align="center" valign="top">:</td>
      <td valign="top">$Akta_NamaAkta</td>
    </tr>
    <tr>
      <td width="29%" align="right" valign="top"><strong>No Fail</strong></td>
      <td width="1%" align="center" valign="top">:</td>
      <td width="70%" valign="top">$Akta_NoFail</td>
    </tr>
    <tr>
      <td colspan="3">&nbsp;</td>
    </tr>
    <tr>
      <td colspan="3">
      <a href="javascript:onClick=viewAktaBlob('$!Akta_IDAkta');" style="color:#0000FF">[Lihat Lampiran]</a>
      <a href="javascript:onClick=paparAkta('$!Akta_IDAkta');" style="color:#0000FF">[Papar Akta]</a>	 </td>
    </tr>
  </table>
</fieldset>
<br />
<fieldset>
  <legend>$MAIN_LEGEND </legend>
<div id="TabbedPanels1" class="TabbedPanels">
    <ul class="TabbedPanelsTabGroup">
      <li class="TabbedPanelsTab" tabindex="0" onclick="doChangeTab(0)">AKTA</li>
      <li class="TabbedPanelsTab" tabindex="1" onclick="doChangeTab(1)">PENGGAL</li>
      <li class="TabbedPanelsTab" tabindex="2" onclick="doChangeTab(2)">BAHAGIAN</li>
      <li class="TabbedPanelsTab" tabindex="3" onclick="doChangeTab(3)">BAB</li>
      <li class="TabbedPanelsTab" tabindex="4" onclick="doChangeTab(4)">SEKSYEN</li>
      <li class="TabbedPanelsTab" tabindex="5" onclick="doChangeTab(5)">SUBSEKSYEN</li>
      <li class="TabbedPanelsTab" tabindex="6" onclick="doChangeTab(6)">PARA</li>
      <li class="TabbedPanelsTab" tabindex="7" onclick="doChangeTab(7)">SUBPARA</li>
      <li class="TabbedPanelsTab" tabindex="8" onclick="doChangeTab(8)">JADUAL</li>
      <li class="TabbedPanelsTab" tabindex="9" onclick="doChangeTab(9)">LAMPIRAN JADUAL</li>
      <!--
      <li class="TabbedPanelsTab" tabindex="8" onclick="doChangeTab(8)">JADUAL(PARA)</li>
      <li class="TabbedPanelsTab" tabindex="9" onclick="doChangeTab(9)">JADUAL(SUBPARA)</li>
      <li class="TabbedPanelsTab" tabindex="10" onclick="doChangeTab(10)">JADUAL(SUB-SUBPARA)</li>
      -->
    </ul>
    <div class="TabbedPanelsContentGroup">
      <div class="TabbedPanelsContent">
#if ($selectedTab == '0')
    #parse ("app/pdt/view/frmAktaTabAkta.jsp")
#end
      </div>
      <div class="TabbedPanelsContent">
#if ($selectedTab == '1')
    #parse ("app/pdt/view/frmAktaTabPenggal.jsp")
#end
      </div>
      <div class="TabbedPanelsContent">
#if ($selectedTab == '2')
    #parse ("app/pdt/view/frmAktaTabBahagian.jsp")
#end
      </div>
      <div class="TabbedPanelsContent">
#if ($selectedTab == '3')
    #parse ("app/pdt/view/frmAktaTabBab.jsp")
#end
      </div>
      <div class="TabbedPanelsContent">
#if ($selectedTab == '4')
    #parse ("app/pdt/view/frmAktaTabSeksyen.jsp")
#end
      </div>
      <div class="TabbedPanelsContent">
#if ($selectedTab == '5')
    #parse ("app/pdt/view/frmAktaTabSubSeksyen.jsp")
#end
      </div>
      <div class="TabbedPanelsContent">
#if ($selectedTab == '6')
    #parse ("app/pdt/view/frmAktaTabPara.jsp")
#end
      </div>
      <div class="TabbedPanelsContent">
#if ($selectedTab == '7')
    #parse ("app/pdt/view/frmAktaTabSubPara.jsp")
#end
      </div>
      <div class="TabbedPanelsContent">
#if ($selectedTab == '8')
    #parse ("app/pdt/view/frmAktaTabJadualPara.jsp")
#end
      </div>
      <div class="TabbedPanelsContent">
#if ($selectedTab == '9')
    #parse ("app/pdt/view/frmAktaTabJadualLampiran.jsp")
#end      </div>
      <div class="TabbedPanelsContent">
#if ($selectedTab == '10')
    #parse ("app/pdt/view/frmAktaTabJadualSubSubPara.jsp")
#end
      </div>
    </div>
  </div>
</fieldset>
<p>
  <input type="hidden" id="actionX" name="actionX" value="$actionX" />
  <input type="hidden" id="selectedTab" name="selectedTab" value="$!selectedTab" />
  <input type="hidden" id="Akta_IDAkta" name="Akta_IDAkta" value="$!Akta_IDAkta" />
  <input type="hidden" id="Penggal_IDPenggal" name="Penggal_IDPenggal" value="$!Penggal_IDPenggal" />
  <input type="hidden" id="Bahagian_IDBahagian" name="Bahagian_IDBahagian" value="$!Bahagian_IDBahagian" />
  <input type="hidden" id="Bab_IDBab" name="Bab_IDBab" value="$!Bab_IDBab" />
  
</p>
<script type="text/javascript">

  var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1", {defaultTab:$selectedTab});

</script>
<script>
function paparAkta(idAkta) {
    var url = "../x/${securityToken}/ekptg.view.pdt.FrmViewAkta2?idAkta="+idAkta;
    var hWnd = window.open(url,'Cetak','width=900,height=700, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}
</script>