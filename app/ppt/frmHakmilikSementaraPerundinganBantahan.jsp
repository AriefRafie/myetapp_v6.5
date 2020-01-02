<style type="text/css">
<!--
.style1 {color: #FF0000}
-->
</style>
#parse("app/ppt/frmHakmilikSementaraMaklumatPermohonan.jsp")
<p>
#parse("app/ppt/frmHakmilikSementaraMaklumatPerundingan.jsp")
<div id="TabbedPanels1" class="TabbedPanels">
  <ul class="TabbedPanelsTabGroup">
     <li class="TabbedPanelsTab" tabindex="0" onclick="tabTuanTanah('$idFail','$idPermohonan','$idSiasatan')">Keterangan Tuan Tanah</li>
    <li class="TabbedPanelsTab" tabindex="0" onclick="tabNilaian('$idFail','$idPermohonan','$idSiasatan')">Nilaian</li>
    <li class="TabbedPanelsTab" tabindex="0" onclick="tabTuntutan('$idFail','$idPermohonan','$idSiasatan')">Tuntutan</li>
    <li class="TabbedPanelsTab" tabindex="0" >Bantahan</li>
    <li class="TabbedPanelsTab" tabindex="0" onclick="tabKeputusan('$idFail','$idPermohonan','$idSiasatan')">Keputusan</li>
  </ul>
  <div class="TabbedPanelsContentGroup">
    <div class="TabbedPanelsContent"></div>
    <div class="TabbedPanelsContent"></div>
    <div class="TabbedPanelsContent"></div>
    <div class="TabbedPanelsContent">
    <table width="100%">
  <tr>
    <td width="1%" align="left" valign="top"><span class="style1">#if($readonlyBantahan != 'readonly')*#end</span></td>
    <td width="29%" align="left" valign="top">Tuan Tanah</td>
    <td width="1%" valign="top">:</td>
    <td width="70%"><label>
      <textarea name="txtTuanTanah" id="txtTuanTanah" cols="45" rows="5" style="text-transform:uppercase;"  onblur="this.value=this.value.toUpperCase();" onkeyup="this.value=this.value.toUpperCase();" $readonlyBantahan class="$disabledBantahan" $disabledBantahan>$BANTAHAN_TUANTNH</textarea>
    </label></td>
  </tr>
  <tr>
    <td width="1%" align="left" valign="top"><span class="style1">#if($readonlyBantahan != 'readonly')*#end</span></td>
    <td align="left" valign="top">Agensi Pemohon</td>
    <td valign="top">:</td>
    <td><label>
      <textarea name="txtAgensi" id="txtAgensi" cols="45" rows="5" style="text-transform:uppercase;"  onblur="this.value=this.value.toUpperCase();" onkeyup="this.value=this.value.toUpperCase();" $readonlyBantahan class="$disabledBantahan" $disabledBantahan>$BANTAHAN_AGENSI</textarea>
    </label></td>
  </tr>
  <tr>
    <td width="1%" align="left" valign="top"><span class="style1">#if($readonlyBantahan != 'readonly')*#end</span></td>
    <td align="left" valign="top">Lain-lain Bantahan</td>
    <td valign="top">:</td>
    <td><label>
      <textarea name="txtLainBantahan" id="txtLainBantahan" cols="45" rows="5" style="text-transform:uppercase;"  onblur="this.value=this.value.toUpperCase();" onkeyup="this.value=this.value.toUpperCase();" $readonlyBantahan class="$disabledBantahan" $disabledBantahan>$BANTAHAN_LAIN</textarea>
    </label></td>
  </tr>
</table>

    </div>
    <div class="TabbedPanelsContent"></div>
  </div>
</div>
<table width="100%">
  <tr>
    <td colspan="6" align="center">
    #if($modeBantahan == 'paparBantahan')
      <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="kemaskiniBantahan()" />
      <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="setTable('tableReport1')" />
    #end
    #if($modeBantahan == 'kemaskiniBantahan')
      <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="updateBantahan()" />      
      <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="batalViewBantahan()" />
    #end
    #if($modeBantahan == 'newBantahan')
      <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="updateBantahan()" />      
      <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="batalNewBantahan()" />
    #end
      <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="kembaliBantahan()" />
    </td>
    </tr>

</table>
<p>
<fieldset id="tableReport1" style="display:none;">
<legend><strong>Senarai Laporan</strong></legend>
	<table width="100%" border="0" cellspacing="2" cellpadding="2">
       <tr>
        <td><a href="#" class="style2" onClick="javascript:cetakNotaRundingan('$idFail')"><font color="blue">Nota Rundingan </font></a></td>
      </tr>           
    </table>
</fieldset>
<input name="id_siasatan" type="hidden" value="$idSiasatan" />
<input type="hidden" name="id_fail" value="$idFail" />
<input type="hidden" name="id_permohonan" value="$idPermohonan" />
<input name="idHakmilik" type="hidden" value="$idHakmilik" />

<script type="text/javascript">
<!--
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:3});
//-->
</script>
<script>
function setTable(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}
function cetakNotaRundingan(id_fail) {
	<!--var url = "../servlet/ekptg.report.ppt.SuratRujukan?idfail="+id_fail;-->
	var url = "../servlet/ekptg.report.ppt.NotaRundingan?idfail="+id_fail;
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();	
}
function tabNilaian(id_fail,id_permohonan){
	
	document.${formName}.id_fail = id_fail;
	document.${formName}.id_permohonan = id_permohonan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraPerundingan&action=tabNilaian";
	document.${formName}.submit();
}
function tabTuanTanah(id_fail,id_permohonan){
	
	document.${formName}.id_fail = id_fail;
	document.${formName}.id_permohonan = id_permohonan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraPerundingan&action=tabTuanTanah";
	document.${formName}.submit();
}
function tabTuntutan(id_fail,id_permohonan){
	
	document.${formName}.id_fail = id_fail;
	document.${formName}.id_permohonan = id_permohonan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraPerundingan&action=tabTuntutan";
	document.${formName}.submit();
}
function tabKeputusan(id_fail,id_permohonan){
	
	document.${formName}.id_fail = id_fail;
	document.${formName}.id_permohonan = id_permohonan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraPerundingan&action=tabKeputusan";
	document.${formName}.submit();
}
function kemaskiniBantahan(){
	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraPerundingan&action=kemaskiniBantahan";
	document.${formName}.submit();
}
function batalNewBantahan(){
	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraPerundingan&action=tabBantahan";
	document.${formName}.submit();
}
function batalViewBantahan(){
	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraPerundingan&action=tabBantahan";
	document.${formName}.submit();
}
function updateBantahan(){

	if(document.${formName}.txtTuanTanah.value == ""){
		alert("Sila masukkan \"Bantahan Tuan Tanah\" terlebih dahulu.");
  		document.${formName}.txtTuanTanah.focus(); 
		return;
	}
	if(document.${formName}.txtAgensi.value == ""){
		alert("Sila masukkan \"Bantahan Agensi Pemohon\" terlebih dahulu.");
  		document.${formName}.txtAgensi.focus(); 
		return;
	}
	if(document.${formName}.txtLainBantahan.value == ""){
		alert("Sila masukkan \"Bantahan Lain\" terlebih dahulu.");
  		document.${formName}.txtLainBantahan.focus(); 
		return;
	}
	
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraPerundingan&action=updateBantahan";
	document.${formName}.submit();
}
function kembaliBantahan(){
	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraPerundingan&action=newPerundingan";
	document.${formName}.submit();
}
</script>