<style type="text/css">
<!--
.style1 {color: #FF0000}
-->
</style>
#parse("app/ppt/frmHakmilikSementaraMaklumatPermohonan.jsp")
<p>
#parse("app/ppt/frmHakmilikSementaraBorangMPB.jsp") 
<p>
<div id="TabbedPanels1" class="TabbedPanels">
  <ul class="TabbedPanelsTabGroup">
    <li class="TabbedPanelsTab" tabindex="0">Affidavit ke Mahkamah</li>
   #if($idBorangM != '') <li class="TabbedPanelsTab" tabindex="0" onclick="tabPerintahMahkamah('$idFail','$idPermohonan')">Perintah Mahkamah</li> #end
  </ul>
  <div class="TabbedPanelsContentGroup">
    <div class="TabbedPanelsContent">
          <table width="100%">
              <tr>
                <td width="1%">&nbsp;</td>
                <td width="28%" align="left" valign="top">Kepada</td>
                <td width="1%" valign="top">:</td>
                <td width="70%" valign="top">$SelectMahkamah</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td width="28%" align="left" valign="top">Alamat Mahkamah Tinggi</td>
                <td valign="top">:</td>
                <td width="70%" valign="top"><input name="txtAlamat1Mahkamah" type="text" class="$disabledBorangMA" id="txtAlamat1Mahkamah" value="$ALAMAT1" size="44" $readonlyBorangMA $disabledBorangMA/></td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td width="28%" align="left" valign="top">&nbsp;</td>
                <td valign="top">&nbsp;</td>
                <td width="70%" valign="top"><input name="txtAlamat2Mahkamah" type="text" class="$disabledBorangMA" id="txtAlamat2Mahkamah" value="$ALAMAT2" size="44" $readonlyBorangMA $disabledBorangMA /></td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td width="28%" align="left" valign="top">&nbsp;</td>
                <td valign="top">&nbsp;</td>
                <td width="70%" valign="top"><input name="txtAlamat3Mahkamah" type="text" class="$disabledBorangMA" id="txtAlamat3Mahkamah" value="$ALAMAT3" size="44" $readonlyBorangMA $disabledBorangMA /></td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td width="28%" align="left" valign="top">Poskod</td>
                <td valign="top">:</td>
                <td width="70%" valign="top"><input name="txtPoskodMahkamah" type="text" id="txtPoskodMahkamah" size="4px" maxlength="5" value="$POSKOD" class="$disabledBorangMA" $readonlyBorangMA $disabledBorangMA/></td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td width="28%" align="left" valign="top">Negeri</td>
                <td valign="top">:</td>
                <td width="70%" valign="top">$SelectNegeri</td>
              </tr>
              <tr>
                <td width="1%" valign="top"><span class="style1">#if($readonlyBorangMB != 'readonly')*#end</span></td>
                <td width="28%" align="left" valign="top">Tujuan</td>
                <td width="1%" valign="top">:</td>
                <td width="70%"><textarea name="txtTujuan" id="txtTujuan" cols="41" rows="5" class="$disabledBorangMB" $readonlyBorangMB  $disabledBorangMB >$TUJUAN</textarea></td>
              </tr>
              <tr>
                <td valign="top"><span class="style1">#if($readonlyBorangMB != 'readonly')*#end</span></td>
                <td width="28%" align="left" valign="top">Perkara Rujukan</td>
                <td valign="top">:</td>
                <td width="70%"><textarea name="txtPerkaraRujuk" id="txtPerkaraRujuk" cols="41" rows="5" class="$disabledBorangMB" $readonlyBorangMB $disabledBorangMB>$PERKARA_RUJUKAN</textarea></td>
              </tr>
      </table>
          
    </div>
    #if($idBorangM != '') <div class="TabbedPanelsContent"></div> #end
  </div>
</div>
<table width="100%">
  <tr>
    <td colspan="6" align="center">
      #if($modeBorangM == "baru")
        <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="simpanBorangM()" />
        <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="batalNewBorangM()" />
        #end
    #if($modeBorangM == "papar")
        <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="kemaskiniBorangM()" />
        <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="setTable('tableReport1')" />
      #end
    #if($modeBorangM == "kemaskini")
      <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="updateBorangM()" />
      <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="batalViewBorangM()" />      
    #end
    <!--
    <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="kembali()" />
    -->
    <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="kembaliNew('$!id_permohonan')" />
  </tr>
</table>
<p>
<fieldset id="tableReport1" style="display:none;">
<legend><strong>Senarai Laporan</strong></legend>
	<table width="100%" border="0" cellspacing="2" cellpadding="2">
       <tr>
        <td><a href="#" onClick="javascript:cetakBorangM('$idFail','$idHakmilikPB','$idPermohonan')"><font color="blue">Borang M – Perkara Yang Dirujuk Kepada Mahkamah </font></a></td>
      </tr>           
    </table>
</fieldset>
<input name="id_fail" type="hidden" value="$idFail" />
<input name="id_permohonan" type="hidden" value="$idPermohonan" />
<input name="idBorangM" type="hidden" value="$idBorangM" />
<input name="idHakmilikPB" type="hidden" value="$idHakmilikPB" />
<input name="idProjekNegeri" type="hidden" value="$idProjekNegeri" />   
<input type="hidden" name="id_status" value="$id_status" />
<script type="text/javascript">
<!--
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:0});
//-->
</script>
<script>
function kembaliNew(id_permohonan){
	
	document.${formName}.id_permohonan.value = id_permohonan;	
	document.${formName}.command.value = "baruBorangM";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraBorangM";
	document.${formName}.submit();
}
function cetakBorangM(id_fail,id_hakmilikpb,id_permohonan) {

	var url = "../x/${securityToken}/ekptg.report.ppt.FrmPopupPilihPegawaiReportView?idfail="+id_fail+"&id_hakmilikpb="+id_hakmilikpb+"&id_permohonan="+id_permohonan+"&report=BorangM&flagReport=S";
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();	
}
function setTable(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}
function tabPerintahMahkamah(id_fail,id_permohonan) {	
	document.${formName}.id_fail = id_fail;
	document.${formName}.id_permohonan = id_permohonan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraBorangM&action=tabPerintahMahkamah";
	document.${formName}.submit();
}

function simpanBorangM() {	

	if(document.${formName}.txtTujuan.value == ""){
		alert("Sila masukkan \"Tujuan\" terlebih dahulu.");
  		document.${formName}.txtTujuan.focus(); 
		return;
	}
	if(document.${formName}.txtPerkaraRujuk.value == ""){
		alert("Sila masukkan \"Perkara Rujukan\" terlebih dahulu.");
  		document.${formName}.txtPerkaraRujuk.focus(); 
		return;
	}
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraBorangM&action=simpanBorangM";
	document.${formName}.submit();
}

function batalNewBorangM() {	

	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraBorangM&action=tabBorangM";
	document.${formName}.submit();
}

function kemaskiniBorangM() {	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraBorangM&action=kemaskiniBorangM";
	document.${formName}.submit();
}

function updateBorangM() {	

	if(document.${formName}.txtTujuan.value == ""){
		alert("Sila masukkan \"Tujuan\" terlebih dahulu.");
  		document.${formName}.txtTujuan.focus(); 
		return;
	}
	if(document.${formName}.txtPerkaraRujuk.value == ""){
		alert("Sila masukkan \"Perkara Rujukan\" terlebih dahulu.");
  		document.${formName}.txtPerkaraRujuk.focus(); 
		return;
	}
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraBorangM&action=updateBorangM";
	document.${formName}.submit();
}

function batalViewBorangM() {	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraBorangM&action=tabBorangM";
	document.${formName}.submit();
}

function kembali() {
	//document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraBorangM&action=baruBorangM";
	document.${formName}.command.value = "baruBorangM";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraBorangM";
	document.${formName}.submit();
}
function doChangeMahkamah() {
    doAjaxCall${formName}("doChangeMahkamah");
}
function doChangeMahkamahUpdate() {
    doAjaxCall${formName}("doChangeMahkamahUpdate");
}
</script>
