<style type="text/css">
<!--
.pautanms {color: #0000FF}
-->
</style>

<table width="100%" border="0" cellspacing="2" cellpadding="2">
	<tr>
		<td>
			<fieldset>
			  <legend><strong>MAKLUMAT PERMOHONAN ONLINE</strong></legend>
			#parse ("app/htp/gadaian/frmGadaianInfoAjax.jsp")
			</fieldset>
		</td>
    </tr>
    

	<tr>
		<td>			
			<fieldset>
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
			                	#parse ("app/htp/gadaian/frmGadaianMaklumatPeminjamAjaxOnline.jsp")
			                #end
			                      
			                </div> 
			                <!-- close div maklumat peminjam-->
			
			                <div class="TabbedPanelsContent">
			                <!-- div maklumat peguam -->
			                
			                #if($selectedTab == '1')
			                	#parse ("app/htp/gadaian/frmGadaianMaklumatPeguamAjaxOnline.jsp")
			                #end
			                
			                </div>
			                 <!-- close div maklumat peguam -->
			                 
			                 
			               	<div class="TabbedPanelsContent">
			                <!-- div maklumat geran -->
			                
			                #if($selectedTab == '2')
			                	#parse ("app/htp/gadaian/frmGadaianMaklumatGeranOnline.jsp")
			                #end
			                
			                </div>
			                 <!-- close div maklumat geran -->
			                 
			                 
			                
			              </div> <!-- close div tabbed panels content group -->
			              
			            </div> <!-- close div TabbedPanels1 -->
			</fieldset>

		</td>
	</tr>
</table>

<script type="text/javascript" language="javascript">
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$selectedTab});
</script>