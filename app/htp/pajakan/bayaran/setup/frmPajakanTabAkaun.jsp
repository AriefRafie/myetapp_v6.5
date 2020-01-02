
<p>
  <input type="hidden" name="idPermohonan"  id="idPermohonan" value="$idPermohonan"/>
  
  <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
  <input name="mode" type="hidden" id="mode" value="$mode"/>
  <input name="hitButton" type="hidden" id="hitButton" /> 
  <input name="idFail" type="hidden" id="idFail" value="$idFail"/>
  <input name="idStatus" type="hidden" id="idStatus" value="$idStatus"/>
  <input name="selectedTab" type="hidden" id="selectedTab" value="$selectedTab"/>
  <input name="selectedTabLower" type="hidden" id="selectedTabLower" value="$selectedTabLower"/>
  <input type="hidden" name="actionPajakan" value="$actionPajakan"/>
  <input type="hidden" name="subUrusan" value="$subUrusan"/>
  <input name="idUlasanTeknikal" type="hidden" id="idUlasanTeknikal" value="$idUlasanTeknikal"/>
  <input name="idUlasanNilai" type="hidden" id="idUlasanNilai" value="$idUlasanNilai"/>
  <input name="idUlasanKJP" type="hidden" id="idUlasanKJP" value="$idUlasanKJP"/>
  <input name="idDraf" type="hidden" id="idDraf" value="$idDraf"/>
  <input name="idUlasanSPHP" type="hidden" id="idUlasanSPHP" value="$idUlasanSPHP"/>  
</p>

<table width="100%" border="0" >
	<tr>
		<td>
			&nbsp;
		</td>
    </tr>
	#if ($idFail != '')
	<tr>
		<td>
		##parse('app/htp/pajakan/paging.jsp')
		</td>
    </tr>
	<tr>
	    <td>#parse("app/htp/pajakan/fail/frmPajakanHeader.jsp")</td>
	  </tr>
	#else
	<tr>
	    <td>
	    	<div class="warning">SILA PILIH FAIL DI SENARAI FAIL TERLEBIH DAHULU</div>
		</td>
	</tr>
  	#end
  	
  	#if ($idFail != '')
  	<tr>
    	<td>
       		<div id="TabbedPanels1" class="TabbedPanels">
            
           		<ul class="TabbedPanelsTabGroup">              
                	<li class="TabbedPanelsTab" title="Setup Bayaran" onclick="doChangeTab(0)" tabindex="0">SETUP BAYARAN</li>
                	<li class="TabbedPanelsTab" title="Bil" onclick="doChangeTab(1)" tabindex="1">BIL</li>
                	<!-- 
                	<li class="TabbedPanelsTab" title="Draf"  onclick="doChangeTab(2)" tabindex="2">DRAF PERJANJIAN</li>
                	<li class="TabbedPanelsTab" title="Memorandum Jemaah Menteri"  onclick="doChangeTab(3)" tabindex="3">MEMORANDUM JEMAAH MENTERI</li>
              		-->
              	</ul>
              
              	<div class="TabbedPanelsContentGroup">              
                	<div class="TabbedPanelsContent">
                	<!-- content pemohon pajakan -->
					#if ($selectedTab == '0')
	                	#parse ("app/htp/pajakan/bayaran/setup/frmPajakanTabAkuanMaklumatBayaran.jsp")
                    #end             
               		<!-- close content pemohon pajakan -->
                	</div>
                    
                	<div class="TabbedPanelsContent">
                	<!-- content Ulasan KJP -->
					#if ($selectedTab == '1')
                        #parse ("app/htp/pajakan/bayaran/setup/frmPajakanTabAkuanMaklumatBilSenarai.jsp")
					#end               	
              		<!-- close content Ulasan KJP -->
              		</div>
              
                	<div class="TabbedPanelsContent">
                	<!-- content Draf -->
					#if ($selectedTab == '2')
	                	#parse ("app/htp/pajakan/mjm/frmPajakanTabDrafMJM.jsp")
					#end               	
              		<!-- close content Draf -->
              		</div>
              
              		<div class="TabbedPanelsContent">
                	<!-- content Memorandum Jemaah Menteri -->
					#if ($selectedTab == '3')
	                	#parse ("app/htp/pajakan/mjm/frmPajakanTabMJM.jsp")
					#end               	
              		<!-- close content Memorandum Jemaah Menteri -->
              		</div>
              
         		</div>
         		<!-- close TabbedPanelsContentGroup -->
              
      		</div>
      	<!-- close TabbedPanels1 -->
    
 	  	</td>
  	</tr>
	#end

</table>
 
<script type="text/javascript">
	var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$selectedTab});

</script>



