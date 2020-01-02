<style type="text/css">
<!--
.style1 {
	color: #FF0000
}
.style2 {
	color: #0000FF
}
-->
</style>

<script type="text/javascript" src="../../library/js/SpryTabbedPanels.js"></script>
<script type="text/javascript" src="../../library/js/ekptgTools.js"></script>
<script type="text/javascript" src="../../img"></script>
<script type="text/javascript" src="../../library/js/jquery-1.3.2.min.js" ></script>
<script>var $jquery = jQuery.noConflict();</script>
<link rel="stylesheet" type="text/css" href="../../css/SpryTabbedPanels.css">



<link rel="stylesheet" type="text/css" href="../../css/eTapp_KJP.css">


#set($saizTxtKemajuanTanah="1000")
#set($saizTxtPerkara="1000")
#set($saizTxtNamaProjek="1000")
<p>  
  <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
  <input name="mode" type="hidden" id="mode" value="$mode"/>
  <input name="flagPopup" type="hidden" id="flagPopup" value="$flagPopup"/>
  <input name="modePopup" type="hidden" id="modePopup" value="$modePopup"/>
  <input name="selectedTabUpper" type="hidden" id="selectedTabUpper" value="$selectedTabUpper"/>
  <input name="hitButton" type="hidden" id="hitButton"/>

  <input name="idFail" type="hidden" id="idFail" value="$idFail"/>
  <input name="idPermohonan" type="hidden" id="idPermohonan" value="$idPermohonan"/>
  <input name="idStatus" type="hidden" id="idStatus" value="$idStatus"/>
  <input name="idUlasanTeknikal" type="hidden" id="idUlasanTeknikal" value="$idUlasanTeknikal"/>
  <input name="idDokumen" type="hidden" id="idDokumen" value="$idDokumen"/>
  <input name="idNegeriTanah" type="hidden" id="idNegeriTanah" value="$idNegeriTanah"/>
  <input name="idKementerianTanah" type="hidden" id="idKementerianTanah" value="$idKementerianTanah"/>
  <input name="idAgensiTanah" type="hidden" id="idAgensiTanah" value="$idAgensiTanah"/>

  <input name="flagStatus" type="hidden" id="flagStatus" value="$flagStatus"/>
  <input name="flagAktif" type="hidden" id="flagAktif" value="$flagAktif"/>  
  <input name="step" type="hidden" id="step" value="$step"/>
</p>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #if ($idFail != '')
  <tr>
    <td> #parse("app/php2/frmPLPHeader.jsp") </td>
  </tr>
  #elseif ($idFail == '' )
  <tr>
    <td>
      <div class="warning">SILA PILIH FAIL DI SENARAI FAIL TERLEBIH DAHULU</div></td>
  </tr>
  #end
  #if ($idFail != '')
  <tr>
    <td><div id="TabbedPanels1" class="TabbedPanels">
          <table width="100%" border="0" cellspacing="2" cellpadding="2">
              <tr>
                <td><fieldset>
                  <legend><b>MAKLUMAT TANAH</b></legend>
                  <table align="center" width="100%">
                    <tr>
                      <td>
          		#parse("app/php2/frmPLPMaklumatTanah.jsp")
          	</td></tr></table></fieldset></td></tr></table>
        </div>
      </td>
  </tr>
  #end
  #if ($idFail != '')
  <tr>
    <td><table width="100%" border="0" cellspacing="2" cellpadding="2">
              <tr>
                <td><fieldset>
                  <legend><b>ULASAN JABATAN PENGGUNA (KJP)</b></legend>
		          <table align="center" width="100%">
                    <tr>
                      <td>
            			#parse("app/php2/online/frmPLPDokumen.jsp")
            		  </td>
            		</tr>
            	  </table>
            	</fieldset></td>
              </tr>
         </table>
    </td>
  </tr>
  #end
</table>
<script>
/* function doChangeTabUpper(tabId) {
	document.${formName}.mode.value = "view";
	document.${formName}.selectedTabUpper.value = tabId;
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	document.${formName}.submit();
} */
function doSeterusnya(){
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}

	document.${formName}.hitButton.value = "doSeterusnya";
	document.${formName}.submit();
}
function gotoBatalPermohonan(){	
	document.${formName}.step.value = "batalPermohonan";
	document.${formName}.submit();
}
</script>