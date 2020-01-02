<style type="text/css">
<!--
  .mandatory {color: #FF0000}
-->
</style>

<fieldset>
  <legend>MAKLUMAT  ENAKMEN</legend>
<table width="100%" border="0">      
    
    <tr>
      <td width="29%" align="right" valign="top"><strong>No. Enakmen</strong></td>
      <td width="1%" align="center" valign="top">:</td>
      <td width="70%" valign="top">$Enakmen_NoEnakmen</td>
    </tr>
    <tr>
      <td align="right" valign="top"><strong>Nama Enakmen</strong></td>
      <td align="center" valign="top">:</td>
      <td valign="top">$Enakmen_NamaEnakmen</td>
    </tr>
    <tr>
      <td width="29%" align="right" valign="top"><strong>No. Fail</strong></td>
      <td width="1%" align="center" valign="top">:</td>
      <td width="70%" valign="top">$Enakmen_NoFail</td>
    </tr>
    <tr>
      <td colspan="3">&nbsp;</td>
    </tr>
    <tr>
      <td colspan="3">
      <a href="javascript:onClick=viewEnakmenBlob('$!Enakmen_IDEnakmen');" style="color:#0000FF">[Lihat Lampiran]</a>
      <a href="javascript:onClick=paparEnakmen('$!Enakmen_IDEnakmen');" style="color:#0000FF">[Papar Enakmen]</a>	 </td>
    </tr>
  </table>
</fieldset>
<br />
<fieldset>
  <legend>$MAIN_LEGEND </legend>
<div id="TabbedPanels1" class="TabbedPanels">
    <ul class="TabbedPanelsTabGroup">
      <li class="TabbedPanelsTab" tabindex="0" onclick="doChangeTab(0)">ENAKMEN</li>
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
    #parse ("app/pdt/frmEnakmenTabEnakmen.jsp")
#end
      </div>
      <div class="TabbedPanelsContent">
#if ($selectedTab == '1')
    #parse ("app/pdt/frmEnakmenTabPenggal.jsp")
#end
      </div>
      <div class="TabbedPanelsContent">
#if ($selectedTab == '2')
    #parse ("app/pdt/frmEnakmenTabBahagian.jsp")
#end
      </div>
      <div class="TabbedPanelsContent">
#if ($selectedTab == '3')
    #parse ("app/pdt/frmEnakmenTabBab.jsp")
#end
      </div>
      <div class="TabbedPanelsContent">
#if ($selectedTab == '4')
    #parse ("app/pdt/frmEnakmenTabSeksyen.jsp")
#end
      </div>
      <div class="TabbedPanelsContent">
#if ($selectedTab == '5')
    #parse ("app/pdt/frmEnakmenTabSubSeksyen.jsp")
#end
      </div>
      <div class="TabbedPanelsContent">
#if ($selectedTab == '6')
    #parse ("app/pdt/frmEnakmenTabPara.jsp")
#end
      </div>
      <div class="TabbedPanelsContent">
#if ($selectedTab == '7')
    #parse ("app/pdt/frmEnakmenTabSubPara.jsp")
#end
      </div>
      <div class="TabbedPanelsContent">
#if ($selectedTab == '8')
    #parse ("app/pdt/frmEnakmenTabJadualPara.jsp")
#end
      </div>
      <div class="TabbedPanelsContent">
#if ($selectedTab == '9')
    #parse ("app/pdt/frmEnakmenTabJadualLampiran.jsp")
#end      </div>
      <div class="TabbedPanelsContent">
#if ($selectedTab == '10')
    #parse ("app/pdt/frmEnakmenTabJadualSubSubPara.jsp")
#end
      </div>
    </div>
  </div>
</fieldset>

<p>
  <input type="hidden" id="actionX" name="actionX" value="$actionX" />
  <input type="hidden" id="selectedTab" name="selectedTab" value="$!selectedTab" />
  <input type="hidden" id="Enakmen_IDEnakmen" name="Enakmen_IDEnakmen" value="$!Enakmen_IDEnakmen" />
  <input type="hidden" id="Penggal_IDPenggal" name="Penggal_IDPenggal" value="$!Penggal_IDPenggal" />
  <input type="hidden" id="Bahagian_IDBahagian" name="Bahagian_IDBahagian" value="$!Bahagian_IDBahagian" />
  <input type="hidden" id="Bab_IDBab" name="Bab_IDBab" value="$!Bab_IDBab" />
  
</p>
<script type="text/javascript">
<!--
  var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1", {defaultTab:$selectedTab});
//-->

</script>

<script type="text/javascript" src="../app/pdt/enakmen.js"></script>

<script>

function paparEnakmen(idEnakmen) {
    var url = "../x/${securityToken}/ekptg.view.pdt.FrmViewEnakmen2?idEnakmen="+idEnakmen;
    var hWnd = window.open(url,'Cetak','width=900,height=700, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
}

</script>

<!--<script>
//alert("ssssss");
//alert("sssssss"+'$current_role');

$jquery(document).ready(function () {

	if('$current_role'=="(PDT)HQPengguna")
	{
		//$jquery('#mydiv').find('input, textarea, button, select, iframe').attr('disabled','disabled');
		//$jquery("input[type=button]").hide();
		$jquery('#mydiv').find('input, textarea, button, select, iframe').attr('disabled','');
		$jquery("input[type=button]").show();
	}
	else
	{
		//$jquery('#mydiv').find('input, textarea, button, select, iframe').attr('disabled','');
		//$jquery("input[type=button]").show();
		$jquery('#mydiv').find('input, textarea, button, select, iframe').attr('disabled','disabled');
		$jquery("input[type=button]").hide();
	}
});

</script>-->

