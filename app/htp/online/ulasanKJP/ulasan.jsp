
<p>
  <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
  <input type="hidden" name="mode" id="mode" value="$mode"/>
  <input type="hidden" name="hitButton" id="hitButton" />
  <input type="hidden" name="idFail" id="idFail" value="$idFail"/>
  <input type="hidden" name="idStatus" id="idStatus" value="$idStatus"/>
  <input type="hidden" name="selectedTab" id="selectedTab" value="$selectedTab"/>
  <input type="hidden" name="selectedTabLower" id="selectedTabLower" value="$selectedTabLower"/>
  <input type="hidden" name="idPermohonan" id="idPermohonan" value="$idPermohonan"/>
  <input type="hidden" name="actionPajakan" value="$actionPajakan"/>
  <input type="hidden" name="subUrusan" value="$subUrusan"/>
  <input type="hidden" name="idUlasanTeknikal" id="idUlasanTeknikal" value="$idUlasanTeknikal"/>
  <input type="hidden" name="idUlasanNilai" id="idUlasanNilai" value="$idUlasanNilai"/>
  <input type="hidden" name="idUlasanKJP" id="idUlasanKJP" value="$idUlasanKJP"/>
  <input type="hidden" name="idDraf" id="idDraf" value="$idDraf"/>
  <input type="hidden" name="idUlasanSPHP" id="idUlasanSPHP" value="$idUlasanSPHP"/>
</p>

<table width="100%" border="0" >
  	<tr>
    	<td>
       		<div id="TabbedPanels1" class="TabbedPanels">

           		<ul class="TabbedPanelsTabGroup">
                	<li class="TabbedPanelsTab" title="Ulasan">ULASAN KJP</li>
              	</ul>

              	<div class="TabbedPanelsContentGroup">
                	<div class="TabbedPanelsContent">
                	<!-- content Ulasan KJP -->
                        #parse ("app/htp/online/ulasanKJP/editor.jsp")
              		<!-- close content Ulasan KJP -->
              		</div>
         		</div>
         		<!-- close TabbedPanelsContentGroup -->
      		</div>
      	<!-- close TabbedPanels1 -->
 	  	</td>
  	</tr>
</table>
<script type="text/javascript">
##if ($idFail != '' && $idStatus != '1' && $idStatus != '6' && $idStatus != '12' && $idStatus != '63')
	var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$selectedTab});
##end

</script>

<script type="text/javascript">
	// kmie, 23112011
	function sendJPPH(ID_PERMOHONAN) {
		document.${formName}.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.integrasi.FrmJPPHViewNilaianPajakan&action2=view&ID_PERMOHONAN=$idPermohonan";
		document.${formName}.method = "POST";
		document.${formName}.submit();
	}

	function paparJPPHOnline(ID_PERMOHONAN) {
		document.${formName}.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.integrasi.FrmJPPHViewNilaianPajakan&action2=view&ID_PERMOHONAN=$idPermohonan";
		document.${formName}.method = "POST";
		document.${formName}.submit();
	}
</script>
<!-- Diguna selepas Upload Draf Perjanjian -->
#parse("app/htp/utiliti/javascript/javaScriptPajakanTindakan.jsp")
#parse("app/htp/utiliti/javascript/javaScriptPajakanPenamatan.jsp")
#parse("app/htp/utiliti/javascript/javaScriptPajakanBayaran.jsp")
#parse("app/htp/utiliti/javascript/javaScriptPajakanPerjanjian.jsp")
#parse("app/htp/utiliti/javascript/javaScriptPajakanMJM.jsp")
#parse("app/htp/utiliti/javascript/javaScriptPajakanPermohonan.jsp")
#parse("app/htp/utiliti/javascript/javaScriptPajakanPaging.jsp")
#parse("app/htp/utiliti/javascript/javaScriptPajakanDaftar.jsp")
#parse("app/htp/utiliti/javascript/javaScriptPajakanIndex.jsp")
#parse("app/htp/utiliti/javascript/javaScriptPajakan.jsp")
#parse("app/htp/utiliti/javaScriptUmum.jsp")
