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
			#parse ("app/htp/gadaian/online/frmKJPGadaianInfoAjax.jsp")
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
			                onclick="setSelected(3,'maklumat16AView')">16A</li>			                
			                <li class="TabbedPanelsTab style1 style3" tabindex="0" title="Peguam" 
			                onclick="setSelected(4,'maklumat16NView')">16N</li>				                
			              	<li class="TabbedPanelsTab style1 style3" tabindex="0" title="Peguam" 
			                onclick="setSelected(5,'borangdview')">BORANG D</li>	
			                			                <li class="TabbedPanelsTab style1 style3" tabindex="0" title="Peguam" 
			                onclick="setSelected(2,'boranggview')">BORANG G</li>	
			                			                <li class="TabbedPanelsTab style1 style3" tabindex="0" title="Peguam" 
			                onclick="setSelected(2,'boranghview')">BORANG H</li>	
			                			                <li class="TabbedPanelsTab style1 style3" tabindex="0" title="Peguam" 
			                onclick="setSelected(2,'borangoview')">BORANG O</li>	
						<!-- 
			 				<li class="TabbedPanelsTab style1 style3" tabindex="0" title="Tarikh Penghantaran/Selesai Dokumen" 
			                onclick="setSelected(3,'selesaiview')">PENGHANTARAN/SELESAI</li> 
			                -->
			              </ul>
			              
			                
			              <div class="TabbedPanelsContentGroup">
			                  
			                <div class="TabbedPanelsContent">
			                <!-- div maklumat peminjam -->
			                
			                #if($selectedTab == '0')
			                	
			                	#parse ("app/htp/gadaian/online/frmKJPGadaianMaklumatPeminjamAjax.jsp")
			                #end
			                      
			                </div> 
			                <!-- close div maklumat peminjam-->
			
			                <div class="TabbedPanelsContent">
			                <!-- div maklumat peguam -->
			                
			                #if($selectedTab == '1')
			                	
			                	#parse ("app/htp/gadaian/online/frmKJPGadaianMaklumatPeguamAjax.jsp")
			                #end
			                
			                </div>
			                 <!-- close div maklumat peguam -->
			                 			                 
			               	<div class="TabbedPanelsContent">
			                <!-- div maklumat geran -->
			                
			                #if($selectedTab == '2')
			                	
			                	#parse ("app/htp/gadaian/online/frmKJPGadaianMaklumatGeran.jsp")
			                #end
			                
			                </div>
			                 <!-- close div maklumat geran -->
			                 
			                 <div class="TabbedPanelsContent">
			                <!-- div maklumat 16A -->
			                
			                #if($selectedTab == '3')
			                	
			                	#parse ("app/htp/gadaian/online/frmKJPGadaianMaklumat16A.jsp")
			                #end
			                
			                </div>
			                 <!-- close div maklumat 16A -->
			                 
			                 <div class="TabbedPanelsContent">
			                <!-- div maklumat 16N -->
			                
			                #if($selectedTab == '4')
			                	
			                	#parse ("app/htp/gadaian/online/frmKJPGadaianMaklumat16N.jsp")
			                #end
			                
			                </div>
			                 <!-- close div maklumat 16N -->
			                 
			                 <div class="TabbedPanelsContent">
			                  <!-- div maklumat BorangD -->
			                
			                #if($selectedTab == '5')
			                	
			                	#parse ("app/htp/gadaian/online/frmKJPGadaianMaklumatBorangD.jsp")
			                #end
			                
			                </div>
			                 <!-- close div maklumat BorangD -->

			               	<div class="TabbedPanelsContent">
			                <!-- div maklumat selesai -->
			                
			                #if($selectedTab == '9')
			                	#parse ("app/htp/gadaian/online/frmKJPGadaianMaklumatSelesai.jsp")
			                #end
			                
			                </div>
			                 <!-- close div maklumat selesai -->			                 
			                 
			                
			              </div> <!-- close div tabbed panels content group -->
			              
			            </div> <!-- close div TabbedPanels1 -->
			</fieldset>

		</td>
	</tr>
</table>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
	<tr>
		<td colspan="2" align="center">
			<input type="button" class="stylobutton" name="cmdSeterusnya" id="cmdSeterusnya" value="Seterusnya" onclick="javascript:fGPHA_seterusnyaHantar()">
		</td>
	</tr>
</table>

<script type="text/javascript" language="javascript">
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$selectedTab});
</script>