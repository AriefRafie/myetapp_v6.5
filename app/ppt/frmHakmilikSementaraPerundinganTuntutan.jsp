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
    <li class="TabbedPanelsTab" tabindex="0" >Tuntutan</li>
    <li class="TabbedPanelsTab" tabindex="0" onclick="tabBantahan('$idFail','$idPermohonan','$idSiasatan')">Bantahan</li>
    <li class="TabbedPanelsTab" tabindex="0" onclick="tabKeputusan('$idFail','$idPermohonan','$idSiasatan')">Keputusan</li>
  </ul>
  <div class="TabbedPanelsContentGroup">
    <div class="TabbedPanelsContent"></div>
    <div class="TabbedPanelsContent"></div>
    <div class="TabbedPanelsContent">
    <table width="100%">
  <tr>
    <td width="1%" align="left" valign="top">&nbsp;</td>
    <td width="20%" align="left" valign="top">Tuanpunya Tanah</td>
    <td width="1%" valign="top">:</td>
    <td width="29%"><label>
      <textarea name="txtTuntutanTuanTnh" id="txtTuntutanTuanTnh" cols="45" rows="5" style="text-transform:uppercase;"  onblur="this.value=this.value.toUpperCase();" onkeyup="this.value=this.value.toUpperCase();" $readonlyTuntutan class="$disabledTuntutan" $disabledTuntutan>$TUNTUTAN_TUANTNH</textarea>
    </label></td>
    <td width="1%" align="left" valign="top">&nbsp;</td>
    <td width="20%" align="left" valign="top">Pihak Berkepentingan Berdaftar</td>
    <td width="1%" valign="top">:</td>
    <td width="29%"><label>
      <textarea name="txtTuntutanPenyewa" id="txtTuntutanPenyewa" cols="45" rows="5" style="text-transform:uppercase;"  onblur="this.value=this.value.toUpperCase();" onkeyup="this.value=this.value.toUpperCase();" $readonlyTuntutan class="$disabledTuntutan" $disabledTuntutan>$TUNTUTAN_PB_BEBANAN</textarea>
    </label></td>
  </tr>
  <tr>
    <td width="1%" align="left" valign="top">&nbsp;</td>
    <td align="left" valign="top">Lain-lain Tuntutan</td>
    <td valign="top">:</td>
    <td><label>
      <textarea name="txtTuntutanLain" id="txtTuntutanLain" cols="45" rows="5" style="text-transform:uppercase;"  onblur="this.value=this.value.toUpperCase();" onkeyup="this.value=this.value.toUpperCase();" $readonlyTuntutan class="$disabledTuntutan" $disabledTuntutan>$TUNTUTAN_PB_LAIN</textarea>
    </label></td>
    <td width="1%" align="left" valign="top">&nbsp;</td>
    <td align="left" valign="top">Pihak Berkepentingan Tidak Berdaftar</td>
    <td valign="top">:</td>
    <td><label>
      <textarea name="txtTuntutanCaveat" id="txtTuntutanCaveat" cols="45" rows="5" style="text-transform:uppercase;"  onblur="this.value=this.value.toUpperCase();" onkeyup="this.value=this.value.toUpperCase();" $readonlyTuntutan class="$disabledTuntutan" $disabledTuntutan>$TUNTUTAN_PB_TDKDAFTAR</textarea>
    </label></td>
  </tr>
</table>
    </div>
    <div class="TabbedPanelsContent"></div>
    <div class="TabbedPanelsContent"></div>
  </div>
</div>
<table width="100%">
  <tr>
    <td colspan="6" align="center">
    #if($modeTuntutan == 'paparTuntutan')
      <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="kemaskiniTuntutan()" />
      <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="setTable('tableReport1')"/>
    #end
    #if($modeTuntutan == 'newTuntutan')
      <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="updateTuntutan()" />
      <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="batalNewTuntutan()" />
    #end
    #if($modeTuntutan == 'kemaskiniTuntutan')
      <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="updateTuntutan()"  />
      <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="batalViewTuntutan()" />
    #end
      <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="kembaliTuntutan()" />
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
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:2});
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
function tabBantahan(id_fail,id_permohonan){
	
	document.${formName}.id_fail = id_fail;
	document.${formName}.id_permohonan = id_permohonan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraPerundingan&action=tabBantahan";
	document.${formName}.submit();
}
function tabKeputusan(id_fail,id_permohonan){
	
	document.${formName}.id_fail = id_fail;
	document.${formName}.id_permohonan = id_permohonan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraPerundingan&action=tabKeputusan";
	document.${formName}.submit();
}
function kemaskiniTuntutan(){
	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraPerundingan&action=kemaskiniTuntutan";
	document.${formName}.submit();
}
function batalNewTuntutan(){
	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraPerundingan&action=tabTuntutan";
	document.${formName}.submit();
}
function batalViewTuntutan(){
	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraPerundingan&action=tabTuntutan";
	document.${formName}.submit();
}
function updateTuntutan(){

	/*if(document.${formName}.txtTuntutanTuanTnh.value == ""){
		alert("Sila masukkan \"Tuntutan Tuan Tanah\" terlebih dahulu.");
  		document.${formName}.txtTuntutanTuanTnh.focus(); 
		return;
	}
	if(document.${formName}.txtTuntutanLain.value == ""){
		alert("Sila masukkan \"Lain - lain Tuntutan\" terlebih dahulu.");
  		document.${formName}.txtTuntutanLain.focus(); 
		return;
	}
	if(document.${formName}.txtTuntutanPenyewa.value == ""){
		alert("Sila masukkan \"Tuntutan Pihak Berkepentingan Berdaftar\" terlebih dahulu.");
  		document.${formName}.txtTuntutanPenyewa.focus(); 
		return;
	}
	if(document.${formName}.txtTuntutanCaveat.value == ""){
		alert("Sila masukkan \"Tuntutan Pihak Berkepentingan Tidak Berdaftar\" terlebih dahulu.");
  		document.${formName}.txtTuntutanCaveat.focus(); 
		return;
	}*/
	
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraPerundingan&action=updateTuntutan";
	document.${formName}.submit();
}
function kembaliTuntutan(){
	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraPerundingan&action=newPerundingan";
	document.${formName}.submit();
}
</script>