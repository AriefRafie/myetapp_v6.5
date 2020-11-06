<input type="hidden" name="selectedTabLower" id="selectedTabLower" value="$selectedTabLower"/>
<table width="100%" border="0" >
  <tr>
    <td>
       <div id="TabbedPanels2" class="TabbedPanels">
         <ul class="TabbedPanelsTabGroup">
           <li class="TabbedPanelsTab" title="Tindakan Mahkamah" onclick="doChangeTabLower(0)" tabindex="0">MAKLUMAT TINDAKAN MAHKAMAH</li>
           <li class="TabbedPanelsTab" title="Hapuskira" onclick="doChangeTabLower(1)" tabindex="0">MAKLUMAT HAPUSKIRA</li>
           </ul>

              <div class="TabbedPanelsContentGroup">

                <div class="TabbedPanelsContent">
                <!-- content TIndakan Mahkamah -->
					#if ($selectedTabLower == '0')
                		#parse("app/php2/frmREVTindakanMahkamah.jsp")
					#end
              </div>
              <!-- close content Tindakan Mahkamah -->

               <div class="TabbedPanelsContent">
                <!-- content Hapus Kira-->
					#if ($selectedTabLower == '1')
                		#parse("app/php2/frmREVMaklumatHapuskira.jsp")
					#end
              </div>
              <!-- close content Hapus Kira-->


         </div>
         <!-- close TabbedPanelsContentGroup -->

      </div>
      <!-- close TabbedPanels1 -->

    </td>
  </tr>

</table>

<script>
	var TabbedPanels2 = new Spry.Widget.TabbedPanels("TabbedPanels2",{defaultTab:$selectedTabLower});

	function doChangeTabLower(tabId) {
		document.${formName}.selectedTabLower.value = tabId;
		document.${formName}.mode.value = "view";
		doAjaxCall${formName}("doChangeTabLower");
	}
</script>

