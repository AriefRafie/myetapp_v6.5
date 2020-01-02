<!-- <script src="SpryAssets/SpryTabbedPanels.js" type="text/javascript"></script>
<link href="SpryAssets/SpryTabbedPanels.css" rel="stylesheet" type="text/css" /> 
-->
<style type="text/css">
<!--
.g {
	color: #F00;
}
.style1 {color: #FF0000}
-->
<!--
.pautanms {color: #0000FF}
-->
</style>
<input name="detailMode" type="hidden" >
<input name="idpermohonan" type="hidden" value="$!idpermohonan" /> 
<input name="idfail" type="hidden" value="$!idfail" /> 

<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
#parse ("app/htp/permohonan/paging_permohonan.jsp") 
<table width="100%" border="0">
	<tr>
	    <td>
	    ##parse("app/htp/permohonan/frmTerimaPohon.jsp")
	    ##parse("app/htp/permohonan/frmTerimaPohonInfoReadOnly.jsp")
	    </td>
 	</tr>
   
	<tr>
    	<td>
    		<fieldset><legend>MAKLUMAT TANAH LANJUTAN</legend>
     
      		<div id="TabbedPanels1" class="TabbedPanels">
      
        	<ul class="TabbedPanelsTabGroup">   
		        <li class="TabbedPanelsTab " tabindex="0"  title="Maklumat Asas Tanah" 
		        onclick="javascript:selectTab(0,'kemaskinipermohonan','MakAsasTanah',0,'$idpermohonanlokasi')">MAKLUMAT ASAS TANAH</li>
	      	#if($!id_jenistanah=="3")
		        <li class="TabbedPanelsTab" tabindex="0" onclick="javascript:selectTab(1,'kemaskinipermohonan','BorangK',0,'$idpermohonanlokasi')" title="Borang K" >BORANG K</li>
		        <li class="TabbedPanelsTab" tabindex="0" onclick="javascript:selectTab(2,'kemaskinipermohonan','Pembayaran',0,'$idPermohonan')" title="Keputusan/Pembayaran Permohonan" >KEPUTUSAN/PEMBAYARAN PERMOHONAN</li>
		        <li class="TabbedPanelsTab" tabindex="0" onclick="javascript:selectTab(3,'kemaskinipermohonan','Notis5A',0,'$idpermohonanlokasi')" title="Notis 5A" >NOTIS 5A</li>
		    #else
		        <li class="TabbedPanelsTab" tabindex="0" onclick="javascript:selectTab(1,'kemaskinipermohonan','Pembayaran',0,'$idPermohonan')" title="Keputusan/Pembayaran Permohonan" >KEPUTUSAN/PEMBAYARAN PERMOHONAN</li>
		        <li class="TabbedPanelsTab" tabindex="0" onclick="javascript:selectTab(2,'kemaskinipermohonan','Notis5A',0,'$idpermohonanlokasi')" title="Notis 5A" >NOTIS 5A</li>
		    #end
		        <li class="TabbedPanelsTab" title="Lokasi Tanah" tabindex="0" onclick="javascript:selectTab(2,'kemaskinipermohonan','LokasiTanah',0,'$idpermohonanlokasi')" style="display:none" >Lokasi Tanah</li>
		        <li class="TabbedPanelsTab" title="Lakaran/Pelan Tanah" tabindex="0" onclick="javascript:selectTab(3,'kemaskinipermohonan','LakaranPelan',0,'$idpermohonanlokasi')" style="display:none">Lakaran/Pelan Tanah</li>
      		</ul>
      
      		<div class="TabbedPanelsContentGroup">
      
        <div class="TabbedPanelsContent">
        ##$selectedTab||$tabmode||id_jenistanah
         	#if($selectedTab == '0')
                	#if($tabmode == '0')
	                	##parse ("app/htp/permohonan/frmTerimaPohonMaklumatAsasTanah.jsp")
	                	#if($!id_jenistanah=="3")
	                		#if($!bilBorangK > 0)
             	            	#parse ("app/htp/permohonan/frmTerimaPohonMaklumatAsasTanah.jsp")  
                			#else
                				#parse ("app/htp/permohonan/frmTerimaPohonMaklumatAsasTanahPPTReadOnly.jsp") 
                			#end
                		#else
	                		#parse ("app/htp/permohonan/frmTerimaPohonMaklumatAsasTanah.jsp")  
	                	#end
	              	#elseif($tabmode == '9')
    	            	#parse ("app/htp/frmTerimaPohonMaklumatAsasTanahPPTHakmilik.jsp") 
                    #elseif($tabmode == '1')
        	        	#parse ("app/htp/frmTerimaPohonMaklumatAsasTanahTambah.jsp") 
                    #elseif($tabmode == '2')
                    	##parse ("app/htp/frmTerimaPohonMaklumatAsasTanahKemaskini.jsp") 
                        ##mode=Kemaskini
                    	#parse ("app/htp/frmTerimaPohonMaklumatAsasTanahTambah.jsp") 
                    #elseif($tabmode == '3_1')
                   		#parse ("app/htp/frmTerimaPohonMaklumatAsasTanah.jsp")  
                    #else
                    	#parse ("app/htp/permohonan/frmTerimaPohonDetailTanah.jsp") 
            	    #end
                #end
                
                </div>
                
        <div class="TabbedPanelsContent"> 
        	#if($selectedTab == '1')
        		#if($!id_jenistanah=="3")
                	#if($tabmode == '0')
	                	#parse ("app/htp/permohonan/frmTerimaPohonMaklumatBorangK2.jsp")       
                    #else
                    	#parse("app/htp/frmPembelianTBangunAjax.jsp")
                	#end
          		#else
               		#if($tabmode == '0')
	               		#parse ("app/htp/permohonan/frmTerimaPohonPembayaran.jsp") 
                	#else
                   		#parse("app/htp/permohonan/frmTerimaPohonPembayaran.jsp")
            		#end
            	#end
          	#end
                
     	</div>
                
        <div class="TabbedPanelsContent">
        	#if($selectedTab == '2')
        	
        		#if($!id_jenistanah=='3')

	               	#if($tabmode == '0')
		               	#parse ("app/htp/permohonan/frmTerimaPohonPembayaran.jsp") 
	                #else
	                   	#parse("app/htp/permohonan/frmTerimaPohonPembayaran.jsp")
	            	#end
                	
          		#else
	               	#if($tabmode == '0')
	               		#parse ("app/htp/permohonan/frmTerimaPohonBayaranNotis5ASenarai.jsp") 
                	#elseif($tabmode == '1')
                		#parse("app/htp/permohonan/frmTerimaPohonBayaranNotis5A.jsp")
              		#else
                   		#parse("app/htp/permohonan/frmTerimaPohonBayaranNotis5AView.jsp")
                	#end  	            
  	            #end
              	
            #end
        </div>
        
        <div class="TabbedPanelsContent"> 
        	#if($selectedTab == '3')
        		#if($!id_jenistanah=='3')
        			#if($tabmode == '0')
		               	#parse ("app/htp/permohonan/frmTerimaPohonBayaranNotis5ASenarai.jsp") 
	                #elseif($tabmode == '1')
	                	#parse("app/htp/permohonan/frmTerimaPohonBayaranNotis5A.jsp")
	              	#else
	                   	#parse("app/htp/permohonan/frmTerimaPohonBayaranNotis5AView.jsp")
	                #end
	            #else
	            	#if($tabmode == '0')
		            	#parse ("app/htp/frmTerimaPohonPelan.jsp") 
	              	#else
	                   	#parse("app/htp/frmTerimaPohonPelanTambah.jsp")
	                #end
                
                #end
            #end
         </div>
         
        <div class="TabbedPanelsContent">
       		#if($selectedTab == '4')
            	#if($tabmode == '0')
	               	#parse ("app/htp/permohonan/frmTerimaPohonPembayaran.jsp") 
                #else
                   	#parse("app/htp/permohonan/frmTerimaPohonPembayaran.jsp")
            	#end
            #end
       </div>
       
       <div class="TabbedPanelsContent"> 
       		#if($selectedTab == '5')
            	#if($tabmode == '0')
	               	#parse ("app/htp/frmTerimaPohonBayaranNotis5ASenarai.jsp") 
                #elseif($tabmode == '1')
                	#parse("app/htp/permohonan/frmTerimaPohonBayaranNotis5A.jsp")
              	#else
                   	#parse("app/htp/permohonan/frmTerimaPohonBayaranNotis5AView.jsp")
                #end
         	#end
    	</div>
      
      </div>
    </div>
    </fieldset></td>
  </tr>
</table>
<!--$idhakmilikurusan $idpermohonanlokasi-->
<input type="hidden" name="idpermohonan" id="dipermohonan" value=" $idPermohonan" /> 
<input type="hidden" name="idfail" id="idfail" value=" $idfail" />
<input type="hidden" name="idhakmilikurusan" id="idhakmilikurusan" value="$idhakmilikurusan" />

<script type="text/javascript">
	var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$selectedTab});
</script>
