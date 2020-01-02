<style type="text/css">
<!--
.pautanms {color: #0000FF}
-->
<!--
.pautanms1 {color: #black}
-->
</style>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
<!--	
	<tr>
   		<td>&nbsp;</td>
	</tr>
-->
	<tr>		
    	<td>
 			#parse("app/htp/paging_pendaftaranhakmilikrizab.jsp")   		    		
	    </td>
	</tr>
	<tr>		
    	<td>
    		<fieldset> <!-- <legend>MAKLUMAT TANAH LANJUTAN </legend> -->    
      		<div id="TabbedPanels1" class="TabbedPanels">
      
	        	<ul class="TabbedPanelsTabGroup">   
			        <li class="TabbedPanelsTab " tabindex="0"  title="" 
			        	onclick="javascript:selectTab(0)">MAKLUMAT HAKMILIK</li>
		  	        <li class="TabbedPanelsTab" title="" tabindex="1" 
		  	        	onclick="javascript:selectTab(1)">URUSAN HAKMILIK</li>
			        <li class="TabbedPanelsTab" title="Urusan Hakmilik" tabindex="0" onclick="javascript:selectTab(3,'kemaskinipermohonan','LakaranPelan',0,'$idpermohonanlokasi')" style="display:none">Lakaran/Pelan Tanah</li>
	      		</ul>
      
	      		<div class="TabbedPanelsContentGroup">      
	        		<div class="TabbedPanelsContent">
	        	#if($selectedTab == '0')	        			
	        	    #parse ("app/htp/rekod/tanahmilik/frmRekodMaklumatHakmilik.jsp") 
	        	#end
    				</div>
	        		<div class="TabbedPanelsContent">
	        	#if($selectedTab == '1')	        			
	        	    #parse ("app/htp/rekod/frmRekodUrusanHakmilikRizab.jsp") 
	        	#end
    				</div>    				
	    		</div>
	    		
    		</div>
    		</fieldset>	
	    </td>
	</tr>
	
	<tr>		
    	<td>
 			#parse("app/htp/paging_pendaftaranhakmilikrizab.jsp")   		
	    </td>
	</tr>	
	
	<tr>		
    	<td>
 			<fieldset id="tableReport1" style="display:none;">
			<legend>SENARAI LAPORAN</legend>
			<table width="100%" border="0">
			  <tr>
			    <td><a href="javascript:cetakHakmilik($idHakmilik);" class="pautanms">Maklumat Hakmilik</a></td>
			  </tr>
			</table>
			</fieldset> 		
	    </td>
	</tr>	
</table>


<input type="hidden" name="idHakmilik" value="$idHakmilik" />
<input type="hidden" name="mode" value="$mode" />
<input type="hidden" name="txtIdHakmilikCukai" value="$idHakmilikCukai" />
<input type="hidden" name="txtCukaiSemasa" value="$txtCukaiTerkini" />
<input type="hidden" name="txtKodSocJenisHakmilik" value="$txtKodSocJenisHakmilik" />
<input type="hidden" name="flagAdvSearch" value="$!flagAdvSearch" />
<input type="hidden" name="skrin" value="skrinhakmilik" />
<input type="hidden" name="txtLuasGabung" value="$txtLuasLama" />

<script>
	
	var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$!selectedTab});	

</script>
 