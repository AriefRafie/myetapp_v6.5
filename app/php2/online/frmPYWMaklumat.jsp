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
#set($saizTxtTujuan="1000")
<p>
  <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
  <input name="idFail" type="hidden" id="idFail" value="$idFail"/>
  <input name="idPermohonan" type="hidden" id="idPermohonan" value="$idPermohonan"/>
  <input name="idSuburusan" type="hidden" id="idSuburusan" value="$idSuburusan"/>
  <input name="idPermohonanSewa" type="hidden" id="idPermohonanSewa" value="$idPermohonanSewa"/>
  <input name="idPemohon" type="hidden" id="idPemohon" value="$idPemohon"/>
  <input name="idHakmilik" type="hidden" id="idHakmilik" value="$idHakmilik"/>
  <input name="idHakmilikAgensi" type="hidden" id="idHakmilikAgensi" value="$idHakmilikAgensi"/>
  <input name="idStatus" type="hidden" id="idStatus" value="$idStatus"/>
  <input name="idNegeriPemohon" type="hidden" id="idNegeriPemohon" value="$idNegeriPemohon"/>
  <input name="mode" type="hidden" id="mode" value="$mode"/>
  <input name="flagPopup" type="hidden" id="flagPopup" value="$flagPopup"/>
  <input name="modePopup" type="hidden" id="modePopup" value="$modePopup"/>
  <input name="flagStatus" type="hidden" id="flagStatus" value="$flagStatus"/>
  <input name="flagAktif" type="hidden" id="flagAktif" value="$flagAktif"/> 
  <input name="selectedTabUpper" type="hidden" id="selectedTabUpper" value="$selectedTabUpper"/>
  <input name="hitButton" type="hidden" id="hitButton"/>
  <input name="statusRizab" type="hidden" id="statusRizab" value="$statusRizab"/>
  <input name="flagBorangK" type="hidden" id="flagBorangK" value="$flagBorangK"/>
  <input name="idBorangK" type="hidden" id="idBorangK" value="$idBorangK"/>
  <input name="idPPTBorangK" type="hidden" id="idPPTBorangK" value="$idPPTBorangK"/>
  <input name="idHakmilikUrusan" type="hidden" id="idHakmilikUrusan" value="$idHakmilikUrusan"/>
  <input name="idPHPBorangK" type="hidden" id="idPHPBorangK" value="$idPHPBorangK"/>
  <input name="idJenisTanah" type="hidden" id="idJenisTanah" value="$idJenisTanah"/>
  <input name="idHakmilikPermohonan" type="hidden" id="idHakmilikPermohonan"/>
  <input name="step" type="hidden" id="step" value="$step"/>
  <input name="idUlasanTeknikal" type="hidden" id="idUlasanTeknikal" value="$idUlasanTeknikal"/>
  <input name="idKementerian" type="hidden" id="idKementerian" value="$idKementerian"/>
  <input name="idAgensi" type="hidden" id="idAgensi" value="$idAgensi"/>
  <input name="idKementerianTanah" type="hidden" id="idKementerianTanah" value="$idKementerianTanah"/>
  <input name="idAgensiTanah" type="hidden" id="idAgensiTanah" value="$idAgensiTanah"/>
</p>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #if ($idFail != '')
  <tr>
    <td> #parse("app/php2/frmPYWHeader.jsp") </td>
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
                      <td>#parse("app/php2/frmPLPMaklumatTanah.jsp")</td>
                    </tr>
                  </table>
                </fieldset></td>
              </tr>
        </table>
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
            			#parse("app/php2/online/frmPYWDokumen.jsp")
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

<script type="text/javascript">
#if ($idFail != '')
	var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$selectedTabUpper});
#end
</script>
<script>
function doKemaskini() {
	document.${formName}.mode.value = "update";
	doAjaxCall${formName}("");
}
function doBatalKemaskini() {
	document.${formName}.mode.value = "view";
	doAjaxCall${formName}("");
}
</script>