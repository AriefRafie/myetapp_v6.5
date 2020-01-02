<style type="text/css">
<!--
.mandatory {
	color: #FF0000
}
-->
</style>
<br />
<p>
	<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
	<input type="hidden" name="hitButton" value="$!hitButton">
	<input type="hidden" name="idWarta" value="$!idWarta">
	<input type="hidden" name="saveMode" value="$!saveMode">
	<input type="hidden" name="mode" value="$!mode">	
	<input type="hidden" name="action" value="$!action">
	<input type="hidden" name="action2" value="$!action2">
	<input type="hidden" name="idWartaNew" value="$!idWartaNew">
	<input type="hidden" name="idPelanNew" value="$!idPelanNew">
	<input type="hidden" id="warta" name="warta" value="$!warta" />
	<input type="hidden" id="idWartaAsalNganti" name="idWartaAsalNganti" value="$!idWartaAsalNganti" />
</p>
<fieldset>
	<legend>MAKLUMAT TANAH RIZAB MELAYU</legend>
	<div id="TabbedPanels1" class="TabbedPanels">
		<ul class="TabbedPanelsTabGroup">
			<li class="TabbedPanelsTab" tabindex="0" onclick="javascript:doChangeTab(0,'viewTab')">WARTA PEWUJUDAN</li>
			<li class="TabbedPanelsTab" tabindex="1" onclick="javascript:doChangeTab(1,'viewTab')" id="warta_pembatalan" style="display:none" >WARTA PEMBATALAN</li>
			<li class="TabbedPanelsTab" tabindex="2" onclick="javascript:doChangeTab(2,'viewTab')" id="warta_penggantian" style="display:none">WARTA PENGGANTIAN</li>
		</ul>
		<div class="TabbedPanelsContentGroup">
			<div class="TabbedPanelsContent">#if ($selectedTab == '0') #parse ("app/pdt/trm/frmWartaWujud.jsp") #end</div>
			<div class="TabbedPanelsContent">#if ($selectedTab == '1') #parse ("app/pdt/trm/frmWartaBatal.jsp") #end</div>
			<div class="TabbedPanelsContent">#if ($selectedTab == '2') #parse ("app/pdt/trm/frmWartaGanti.jsp") #end</div>
		</div>
	</div>
</fieldset>
<div>
	<input type="hidden" id="actionX" name="actionX" value="$actionX" />
	<input type="hidden" id="selectedTab" name="selectedTab" value="$!selectedTab" />
	<input type="hidden" id="Wujud_IdWujud" name="Enakmen_IDEnakmen" value="$!Enakmen_IDEnakmen" />
	<input type="hidden" id="Batal_IdBatal" name="Penggal_IDPenggal" value="$!Penggal_IDPenggal" />
	<input type="hidden" id=Ganti_IdGanti name="Bahagian_IDBahagian" value="$!Bahagian_IDBahagian" />
</div>
<input type="hidden" id="showTab" name="showTab" value="$showTab" />

#if($showTab=="Y")
<script>
document.getElementById('warta_pembatalan').style.display="";
document.getElementById('warta_penggantian').style.display="";
</script>
#end

<script type="text/javascript">
	var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1", {
		defaultTab : $selectedTab
	});
	
	function kembali() {	
		document.${formName}.action = "?_portal_module=ekptg.view.pdt.FrmCariMaklumatTRM";
		document.${formName}.method="POST";
		document.${formName}.hitButton.value = "kembali";
		document.${formName}.submit();
	}
	
	function kemaskini() {	
		document.${formName}.action = "?_portal_module=ekptg.view.pdt.FrmCariMaklumatTRM";
		document.${formName}.method="POST";
		document.${formName}.hitButton.value = "kemaskini";
		document.${formName}.submit();
	}
	
	function kemaskinibatal() {
		document.${formName}.action = "?_portal_module=ekptg.view.pdt.FrmCariMaklumatTRM";
		document.${formName}.method="POST";
		document.${formName}.hitButton.value = "kemaskinibatal";
		document.${formName}.submit();
	}	
	function kemaskiniganti() {	
		document.${formName}.action = "?_portal_module=ekptg.view.pdt.FrmCariMaklumatTRM";
		document.${formName}.method="POST";
		document.${formName}.hitButton.value = "kemaskiniganti";
		document.${formName}.submit();
	}	
	
	function doChangeTab(TabID,actions) {
		document.${formName}.action="?_portal_module=ekptg.view.pdt.FrmCariMaklumatTRM";
		document.${formName}.selectedTab.value=TabID;
		document.${formName}.action2.value="viewTab";
		document.${formName}.hitButton.value="";
		document.${formName}.method="POST";
		document.${formName}.submit();
	}
	
</script>
