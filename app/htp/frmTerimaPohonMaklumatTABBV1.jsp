
	#set ($inputstyle = "class=disabled" )
	#set ($inputstyleread = "readonly class=disabled" )
	#set ($selectstyle = "disabled class=disabled" )
<table width="100%" border="0">
  <tr>
    <td>#parse("app/htp/frmTerimaPohonInfo.jsp")</td>
  </tr>
   
  <tr>
    <td><fieldset><legend> Maklumat Tanah Lanjutan</legend><div id="TabbedPanels1" class="TabbedPanels">
      <ul class="TabbedPanelsTabGroup">
        <li class="TabbedPanelsTab" title="Maklumat Asas Tanah" tabindex="0" onclick="javascript:selectTab(0,'kemaskinipermohonan','MakAsasTanah',0)" tabindex="0">Maklumat Asas Tanah</li>
        <li class="TabbedPanelsTab" title="Borang K" tabindex="0" onclick="javascript:selectTab(1,'kemaskinipermohonan','BorangK',0)" tabindex="0">Borang K</li>
        <li  class="TabbedPanelsTab" title="Lokasi Tanah" tabindex="0" onclick="javascript:selectTab(2,'kemaskinipermohonan','LokasiTanah',0)" tabindex="0">Lokasi Tanah</li>
        <li class="TabbedPanelsTab" title="Lakaran/Pelan Tanah" tabindex="0" onclick="javascript:selectTab(3,'kemaskinipermohonan','LakaranPelan',0)" tabindex="0" >Lakaran/Pelan Tanah</li>
        <li class="TabbedPanelsTab" title="Keputusan/Pembayaran Permohonan" tabindex="0" onclick="javascript:selectTab(4,'kemaskinipermohonan','Pembayaran',0)" tabindex="0" >Keputusan/Pembayaran Permohona</li>
      </ul>
      <div class="TabbedPanelsContentGroup">
        <div class="TabbedPanelsContent">#if($selectedTab == '0')
                	#if($tabmode == '0')
                	
                		#if($!id_jenistanah=="3")
                			#parse ("app/htp/frmTerimaPohonMaklumatAsasTanahPPT.jsp") 
                		#else
	                		#parse ("app/htp/frmTerimaPohonMaklumatAsasTanah.jsp")  
	                	#end
	                	
    	            #elseif($tabmode == '9')
    	            	
    	            	#parse ("app/htp/frmTerimaPohonMaklumatAsasTanahPPTHakmilik.jsp") 
    	            	
    	            #else
        	        	#parse ("app/htp/frmTerimaPohonMaklumatAsasTanahTambah.jsp") 
            	    #end
                #end</div>
        <div class="TabbedPanelsContent"> #if($selectedTab == '1')
                	#if($tabmode == '0')
	                	#parse ("app/htp/frmTerimaPohonMaklumatBorangK.jsp")       
                    #else
                    	#parse("app/htp/frmPembelianTBangunAjax.jsp");
                	#end
                #end</div>
        <div class="TabbedPanelsContent">
        #if($selectedTab == '2')
                	#if($tabmode == '0')
	                	#parse ("app/htp/frmTerimaPohonLokasiTanah.jsp")
                    #else
                    	#parse("app/htp/frmTerimaPohonLokasiTanahTambah.jsp");
                	#end
                #end
        </div>
        <div class="TabbedPanelsContent"> 
        #if($selectedTab == '3')
                	#if($tabmode == '0')
	                	#parse ("app/htp/frmTerimaPohonPelan.jsp") 
                    #else
                    	#parse("app/htp/frmTerimaPohonPelanTambah.jsp");
                	#end
                #end</div>
        <div class="TabbedPanelsContent"> 

        <!--#if($selectedTab == '4')
                	#if($tabmode == '0')
	                	#parse ("app/htp/frmTerimaPohonPelan.jsp") 
                    #else
                    	#parse("app/htp/frmTerimaPohonPelanTambah.jsp");
                	#end
                #end-->
                </div>
      </div>
    </div></fieldset></td>
  </tr>
</table>
<script type="text/javascript">

var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$selectedTab});

</script>
