#elseif($page == '3')

<strong> Maklumat Gadaian </strong>
<br>
#if ( $SimpanStatus == "success" )
    <table width="100%" border="0">
        <tr>
            <td>
            <font color="blue">
            <b>
            #if ( $ResultSimpan == "baru" )
                Fail anda telah berjaya disimpan.
            #elseif ($ResultSimpan == "kemaskini" )
                Fail anda telah berjaya dikemaskini.
            #end
            </b>
            </font>
            </td>
        </tr>
    </table>
#end
<br>
<fieldset>
<legend> Draf Perjanjian Jual Beli</legend>
<table width="130%">
<tr>
		<td>
			#parse ("app/htp/frmPembelianInfoAjax.jsp")
		</td>
    </tr>
    <tr>
    	<td>
        	<hr size="2" width="100%" align="center" />
        </td>
    </tr>
    <tr>
    	<td>
            <div id="TabbedPanels1" class="TabbedPanels">
              <ul class="TabbedPanelsTabGroup"> 
                <li class="TabbedPanelsTab" title="Tanah" tabindex="0" onclick="javascript:setSelected(0,'Maklumat','tanahview',0)"><strong><font size="1">Maklumat Tanah</font></strong></li>
                
               
                <li class="TabbedPanelsTab" title="Tanah & Bangunan" tabindex="0" onclick="javascript:setSelected(1,'Maklumat','tbangunview',0)"><strong><font size="1">Maklumat Tanah & Bangunan</font></strong></li>
                
              </ul>
              
              <div class="TabbedPanelsContentGroup">
                <div class="TabbedPanelsContent"> <!-- content maklumat asas tanah -->
	<!--                <div id = "satu"> </div> -->
    			#if($selectedTab == '0')
        	        	#parse ("app/htp/frmPembelianTanahAjax.jsp") 
            	    #end
                #end

                </div> <!-- close content 1 -->

                
                <div class="TabbedPanelsContent"> <!-- maklumat tanah & bangunan -->
               <!--	<div id = "dua"></div> -->
          		#if($selectedTab == '1')
                	#if($tabmode == '0')
	                	#parse ("app/htp/test2.jsp")
                    
                    #else
                    	#parse("app/htp/frmPembelianTBangunAjax.jsp");
                	#end
                #end

                </div> <!-- close content maklumat tanah & bangunan -->
                
              </div>
            </div>
         </td>
      </tr>
</table>
</fieldset>

<script language="javascript" type="text/javascript">
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$selectedTab});
</script>
