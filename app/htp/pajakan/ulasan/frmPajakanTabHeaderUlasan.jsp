<table width="100%" border="0" >
  <tr>
    <td>
       <div id="TabbedPanels2" class="TabbedPanels">
         <ul class="TabbedPanelsTabGroup">
           <li class="TabbedPanelsTab" title="Ulasan KJP" onclick="doChangeTabLower(0)" tabindex="0">ULASAN KJP</li>
           <li class="TabbedPanelsTab" title="Ulasan JPPH" onclick="doChangeTabLower(1)" tabindex="0">ULASAN JPPH</li>
            <li class="TabbedPanelsTab" title="Ulasan SPHP" onclick="doChangeTabLower(2)" tabindex="0">ULASAN BPHP</li>
           </ul>

              <div class="TabbedPanelsContentGroup">

                <div class="TabbedPanelsContent">
                <!-- content Ulasan KJP -->
					#if ($selectedTabLower == '0')
                		#parse ("app/htp/pajakan/ulasan/ulasan/frmPajakanTabUlasan.jsp")
					#end
              </div>
              <!-- close content Ulasan KJP -->

               <div class="TabbedPanelsContent">
                <!-- content Ulasan JPPH-->
					#if ($selectedTabLower == '1')
                		#parse ("app/htp/pajakan/ulasan/ulasan/frmPajakanTabUlasanJPPH.jsp")
					#end
              </div>
              <!-- close content Ulasan JPPH-->

                 <div class="TabbedPanelsContent">
                <!-- content  Ulasan JPPH -->
					#if ($selectedTabLower == '2')
	                	#parse ("app/htp/pajakan/ulasan/ulasan/frmPajakanTabUlasanSPHP.jsp")
					#end
              </div>
              <!-- close content  Ulasan JPPH -->


         </div>
         <!-- close TabbedPanelsContentGroup -->

      </div>
      <!-- close TabbedPanels1 -->

    </td>
  </tr>

</table>

<script language="javascript" type="text/javascript">
	var TabbedPanels2 = new Spry.Widget.TabbedPanels("TabbedPanels2",{defaultTab:$selectedTabLower});
</script>

