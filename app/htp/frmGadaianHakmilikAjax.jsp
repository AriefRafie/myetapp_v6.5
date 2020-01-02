<style type="text/css">
<!--
.pautanms {color: #0000FF}
-->
</style>
<fieldset>
<legend>MAKLUMAT GADAIAN</legend>
<table width="100%">
	<tr>
		<td>
			#parse ("app/htp/frmGadaianInfoAjax.jsp")
		</td>
    </tr>
    <tr>
    	<td>
        	<hr size="2" width="100%" align="center" />
        </td>
    </tr>
    <tr>
    	<td>
         </td>
      </tr>
</table>
 <div id="TabbedPanels1" class="TabbedPanels">

              <ul class="TabbedPanelsTabGroup">
                <li class="TabbedPanelsTab style1 style3" tabindex="0" title="Pemilik" 
                onclick="setSelected(0,'hakmilikview')">PEMINJAM</li>
                
                
                <li class="TabbedPanelsTab style1 style3" tabindex="0" title="Peguam" 
                onclick="setSelected(1,'peguamview')">MAKLUMAT PEGUAM</li>
                
                <li class="TabbedPanelsTab style1 style3" tabindex="0" title="Peguam" 
                onclick="setSelected(2,'geranview')">GERAN</li>

              </ul>
              
                
              <div class="TabbedPanelsContentGroup">
                  
                <div class="TabbedPanelsContent">
                <!-- div maklumat peminjam -->
                
                #if($selectedTab == '0')
                	#parse ("app/htp/frmGadaianMaklumatPeminjamAjax.jsp")
                #end
                      
                </div> 
                <!-- close div maklumat peminjam-->

                <div class="TabbedPanelsContent">
                <!-- div maklumat peguam -->
                
                #if($selectedTab == '1')
                	#parse ("app/htp/frmGadaianMaklumatPeguamAjax.jsp")
                #end
                
                </div>
                 <!-- close div maklumat peguam -->
                 
                 
                  <div class="TabbedPanelsContent">
                <!-- div maklumat geran -->
                
                #if($selectedTab == '2')
                	#parse ("app/htp/frmGadaianTabGeran.jsp")
                #end
                
                </div>
                 <!-- close div maklumat geran -->
                 
                 
                
              </div> <!-- close div tabbed panels content group -->
              
            </div> <!-- close div TabbedPanels1 -->
</fieldset>



<script type="text/javascript" language="javascript">
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$selectedTab});
</script>