<style type="text/css">
<!--
.pautanms {color: #0000FF}
-->
</style>

<table width="100%" border="0" cellspacing="2" cellpadding="2">
	<tr>
		<td>&nbsp;</td>
    </tr>
	<tr>
		<td>
			<fieldset><legend><strong>MAKLUMAT PERMOHONAN</strong></legend>
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
			                <li class="TabbedPanelsTab style1 style3" tabindex="0" title="Peguam" 
			                onclick="setSelected(3,'temujanjiview')">TEMUJANJI</li>			                
			             
			                <li class="TabbedPanelsTab style1 style3" tabindex="0" title="Peguam" 
			                onclick="setSelected(4,'16Aview')">16A</li>			                
			                <li class="TabbedPanelsTab style1 style3" tabindex="0" title="Peguam" 
			                onclick="setSelected(5,'16Ngeranview')">16N</li>				                
			              	<li class="TabbedPanelsTab style1 style3" tabindex="0" title="Peguam" 
			                onclick="setSelected(6,'Dgeranview')">BORANG D</li>	
			                			                <li class="TabbedPanelsTab style1 style3" tabindex="0" title="Peguam" 
			                onclick="setSelected(7,'Ggeranview')">BORANG G</li>	
			                			                <li class="TabbedPanelsTab style1 style3" tabindex="0" title="Peguam" 
			                onclick="setSelected(8,'Hgeranview')">BORANG H</li>	
			                			                <li class="TabbedPanelsTab style1 style3" tabindex="0" title="Peguam" 
			                onclick="setSelected(9,'Ogeranview')">BORANG O</li>	
						<!-- 
			 				<li class="TabbedPanelsTab style1 style3" tabindex="0" title="Tarikh Penghantaran/Selesai Dokumen" 
			                onclick="setSelected(3,'selesaiview')">PENGHANTARAN/SELESAI</li> 
			                -->
			              </ul>
			              
			                
			              <div class="TabbedPanelsContentGroup">
			                  
			                <div class="TabbedPanelsContent">
			                <!-- div maklumat peminjam -->
			                
			                #if($selectedTab == '0')
			                	##parse ("app/htp/gadaian/frmGadaianMaklumatPeminjamAjaxOnline.jsp")
			                	#parse ("app/htp/gadaian/frmGadaianMaklumatPeminjamAjax.jsp")
			                #end
			                      
			                </div> 
			                <!-- close div maklumat peminjam-->
			
			                <div class="TabbedPanelsContent">
			                <!-- div maklumat peguam -->
			                
			                #if($selectedTab == '1')
			                	##parse ("app/htp/gadaian/frmGadaianMaklumatPeguamAjaxOnline.jsp")
			                	#parse ("app/htp/gadaian/frmGadaianMaklumatPeguamAjax.jsp")
			                #end
			                
			                </div>
			                 <!-- close div maklumat peguam -->
			                 			                 
			               	<div class="TabbedPanelsContent">
			                <!-- div maklumat geran -->
			                
			                #if($selectedTab == '2')
			                	##parse ("app/htp/gadaian/frmGadaianMaklumatGeranOnline.jsp")
			                	#parse ("app/htp/gadaian/frmGadaianMaklumatGeran.jsp")
			                #end
			                
			                </div>
			                 <!-- close div maklumat geran -->

			               	<div class="TabbedPanelsContent">
			                <!-- div maklumat selesai -->
			                
			                #if($selectedTab == '3')
			                	#parse ("app/htp/gadaian/frmGadaianMaklumatSelesai.jsp")
			                #end
			                
			                </div>
			                <div class="TabbedPanelsContent">
			                <!-- div maklumat selesai -->
			                
			                #if($selectedTab == '4')
			                	#parse ("app/htp/6.0/gadaian/frmGadaianMaklumatTemujanji.jsp")
			                #end
			                
			                </div>
			                 <!-- close div maklumat selesai -->			                 
			                 
			                
			              </div> <!-- close div tabbed panels content group -->
			              
			            </div> <!-- close div TabbedPanels1 -->
			</fieldset>

		</td>
	</tr>
</table>

<script type="text/javascript" language="javascript">
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$selectedTab});
</script>