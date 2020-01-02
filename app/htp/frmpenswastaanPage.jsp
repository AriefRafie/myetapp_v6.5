<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td>
        ##parse ("app/htp/frmpenswastaanPendaftaran.jsp")	
    </td>
  </tr>
  <tr>
    <td>   
     <div id="TabbedPanels1" class="TabbedPanels">
      <ul class="TabbedPanelsTabGroup">
        <li onClick="doChangeTab(0);" class="TabbedPanelsTab" tabindex="0">MESYUARAT</li>
        <li onClick="doChangeTab(1);" class="TabbedPanelsTab" tabindex="0">KEPUTUSAN MEMORANDUM</li>
        <li onClick="doChangeTab(2);" class="TabbedPanelsTab" tabindex="0">MAKLUMAT SYARIKAT</li>
        <li onClick="doChangeTab(3);" class="TabbedPanelsTab" tabindex="0">MAKLUMAT PROJEK</li>
        <li onClick="doChangeTab(4);" class="TabbedPanelsTab" tabindex="0">PINDAH MILIK TANAH</li>
        <li onClick="doChangeTab(5);" class="TabbedPanelsTab" tabindex="0">TUKAR GANTI TANAH</li>
       </ul>
      <div class="TabbedPanelsContentGroup">
        <div class="TabbedPanelsContent">
        	#parse ("app/htp/frmpenswastaanMesyuarat.jsp")
        </div>
        <div class="TabbedPanelsContent">
        	#parse ("app/htp/frmpenswastaanKeputusanMemorandum.jsp")            
        </div>
        <div class="TabbedPanelsContent">
        #parse ("app/htp/frmpenswastaanMaklumatSyarikat.jsp") 
        </div>
        <div class="TabbedPanelsContent">
        #parse ("app/htp/frmpenswastaanMaklumatProjek.jsp")  
        </div>
        <div class="TabbedPanelsContent">
        #parse ("app/htp/frmpenswastaanPindahmilik.jsp")  
        </div>
        <div class="TabbedPanelsContent">
         #parse ("app/htp/frmpenswastaanTukarGantiTanah.jsp")
        </div>
        
         </div>
      </div>
    </td>
    
  </tr>
</table>
$selectedTabUpper
<script type="text/javascript">
	var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$selectedTabUpper});
</script>
