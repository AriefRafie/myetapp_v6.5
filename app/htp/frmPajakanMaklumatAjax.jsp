<br/>
<table width="100%">
<tr>
		<td>
			#parse ("app/htp/frmPajakanInfoAjax.jsp")
		</td>
    </tr>
    <tr>
    	<td>
            <div id="TabbedPanels1" class="TabbedPanels">
            
              <ul class="TabbedPanelsTabGroup">
                <li class="TabbedPanelsTab" title="Pemohon" onclick="setSelected(0);PemohonView();"><strong><font size="1">Pemohon Pajakan</font></strong></li>
                <li class="TabbedPanelsTab" title="Ulasan" onclick="setSelected(1);UlasanView();"><strong><font size="1">Ulasan</font></strong></li>
                <li class="TabbedPanelsTab" title="Draf" onclick="setSelected(2);DrafView();"><strong><font size="1">Draf</font></strong></li>
                <li class="TabbedPanelsTab" title="Memorandum" onclick="setSelected(3);MJMView();"><strong><font size="1">Memorandum Jemaah Menteri</font></strong></li>
              </ul>
              
              <div class="TabbedPanelsContentGroup">
              
              <div class="TabbedPanelsContent">
              <!-- content pemohon pajakan -->
               <!-- frmPajakanTabPemohonPajakan.jsp -->
               #if($selectedTab == '0')
        	        	#parse ("app/htp/frmPajakanTabPemohonPajakan.jsp") 
                #end
              
                </div>
              <!-- close content pemohon pajakan-->  
                
                
               <div class="TabbedPanelsContent">
               <!-- content  ulasan--> 
               <!-- frmPajakanTabUlasan.jsp -->
               #if($selectedTab == '1')
        	        	#parse ("app/htp/frmPajakanTabUlasan.jsp") 
                #end
               

               
               </div>
               <!-- close content ulasan -->
               
                <div class="TabbedPanelsContent">
              <!-- content draf -->
              <!-- frmPajakanTabDraf.jsp -->
              #if($selectedTab == '2')
        	        	#parse ("app/htp/frmPajakanTabDraf.jsp") 
                #end
              

                </div>
                <!-- close content draf -->
                
                
                <div class="TabbedPanelsContent">
               <!-- content mmemo jemaah menteri -->
                <!-- frmPajakanTabMJM -->
               #if($selectedTab == '3')
        	        	#parse ("app/htp/frmPajakanTabMJM.jsp") 
                #end


                </div>
                
                <!-- close content mjm -->
                
                
              </div>
              <!-- close TabbedPanelsContentGroup -->
              
            </div>
             <!-- close TabbedPanels1 -->
            
         </td>
      </tr>
</table>
<script type="text/javascript">

var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$selectedTab});

</script>
