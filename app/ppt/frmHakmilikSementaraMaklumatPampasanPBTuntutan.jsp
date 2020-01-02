<style type="text/css">
<!--
.style1 {color: #FF0000}
-->
</style>
#parse("app/ppt/frmHakmilikSementaraMaklumatPampasanPB.jsp") 
<p>



<div id="TabbedPanels1" class="TabbedPanels">
  <ul class="TabbedPanelsTabGroup">
    <li class="TabbedPanelsTab" tabindex="0">Tuntutan</li>   
    <li class="TabbedPanelsTab" tabindex="0" onclick="tabNilaian('$idFail','$id_permohonan','$idPihakBerkepentingan')">Nilaian</li>
    <li class="TabbedPanelsTab" tabindex="0" onclick="tabTerimaCek('$idFail','$id_permohonan','$idPihakBerkepentingan')">Penerimaan Cek</li>
     #if($idBayaran != '')
    <li class="TabbedPanelsTab" tabindex="0" onclick="tabSerahCek('$idFail','$id_permohonan','$idPihakBerkepentingan')">Penyerahan Cek</li>
    #end
    <li class="TabbedPanelsTab" tabindex="0" onclick="tabEFT('$idFail','$id_permohonan','$idPihakBerkepentingan')">Bayaran Melalui EFT</li>
  </ul>
  <div class="TabbedPanelsContentGroup">
    <div class="TabbedPanelsContent">
    <fieldset>
    <table width="100%">
      <tr>
        <td width="1%" align="left" valign="top">&nbsp;</td>
        <td width="29%" align="left" valign="top">Tuan Tanah</td>
        <td width="1%" valign="top">:</td>
        <td width="70%"><label>
          <textarea name="txtTuntutTuanTnh" id="txtTuntutTuanTnh" cols="45" rows="5" $readonlyTuntutanA class="$disabledTuntutanA" $disabledTuntutanA>$TUNTUTAN_TUANTNH</textarea>
        </label></td>
        </tr>
      <tr>
        <td width="1%" align="left" valign="top">&nbsp;</td>
        <td width="29%" align="left" valign="top">Pihak Berkepentingan Berdaftar</td>
        <td width="1%" valign="top">:</td>
        <td width="70%"><label>
          <textarea name="txtTuntutPBDaftar" id="txtTuntutPBDaftar" cols="45" rows="5" $readonlyTuntutanA class="$disabledTuntutanA" $disabledTuntutanA>$TUNTUTAN_PB_BEBANAN</textarea>
        </label></td>
        </tr>
      <tr>
        <td width="1%" align="left" valign="top">&nbsp;</td>
        <td width="29%" align="left" valign="top">Pihak Berkepentingan Tidak Berdaftar</td>
        <td width="1%" valign="top">:</td>
        <td width="70%"><textarea name="txtTuntutPBTdkDaftar" id="txtTuntutPBTdkDaftar" cols="45" rows="5" $readonlyTuntutanA class="$disabledTuntutanA" $disabledTuntutanA>$TUNTUTAN_PB_TDKDAFTAR</textarea></td>
        </tr>
      <tr>
        <td width="1%" align="left" valign="top">&nbsp;</td>
        <td width="29%" align="left" valign="top">Lain-lain Tuntutan</td>
        <td width="1%" valign="top">:</td>
        <td width="70%"><textarea name="txtLainTuntutan" id="txtLainTuntutan" cols="45" rows="5" $readonlyTuntutanA class="$disabledTuntutanA" $disabledTuntutanA>$TUNTUTAN_PB_LAIN</textarea></td>
        </tr>
      <tr>
        <td width="1%" align="left" valign="top"><span class="style1">#if($readonlyTuntutanB != 'readonly')*#end</span></td>
        <td width="29%" align="left" valign="top">Status Tuntutan</td>
        <td width="1%" valign="top">:</td>
        <td width="70%">
        #if($STATUS_TUNTUTAN == '1')
        <input name="sorStatusTuntutan" type="radio" class="$disabledTuntutanB" id="sorStatusTerima" value="1" $readonlyTuntutanB $disabledTuntutanB checked="checked" />
        Diterima
        #else
         <input name="sorStatusTuntutan" type="radio" class="$disabledTuntutanB" id="sorStatusTerima" value="1" $readonlyTuntutanB $disabledTuntutanB />
        Diterima
        #end
        #if($STATUS_TUNTUTAN == '2')
          <input name="sorStatusTuntutan" type="radio" class="$disabledTuntutanB" id="sorStatusTolak" value="2" $readonlyTuntutanB $disabledTuntutanB checked="checked" />
		Ditolak
        #else
         <input name="sorStatusTuntutan" type="radio" class="$disabledTuntutanB" id="sorStatusTolak" value="2" $readonlyTuntutanB $disabledTuntutanB />
		Ditolak
        #end		</td>
        </tr>
      <tr>
        <td width="1%" align="left" valign="top">&nbsp;</td>
        <td width="29%" align="left" valign="top">&nbsp;</td>
        <td width="1%" valign="top">&nbsp;</td>
        <td width="70%">&nbsp;</td>
        </tr>
    </table>

    </fieldset>
    </div>
    <div class="TabbedPanelsContent"></div>
    <div class="TabbedPanelsContent"></div>
      #if($idBayaran != '')<div class="TabbedPanelsContent"></div>#end
    <div class="TabbedPanelsContent"></div>
  </div>
</div>
<table width="100%">
  <tr>
    <td colspan="6" align="center">
    #if($modeTuntutan == 'paparTuntutan')
      <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="kemaskiniTuntutan()" />
      <!--<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="setTable('tableReport1')" />-->
    #end
    #if($modeTuntutan == 'newTuntutan')
      <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="updateTuntutan()" />
      <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="batalNewTuntutan()" />
    #end
    #if($modeTuntutan == 'kemaskiniTuntutan')
      <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="updateTuntutan()"  />
      <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="batalViewTuntutan()" />
    #end
    <!--  <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="kembaliTuntutan()" />-->
   
    <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="kembaliTuntutanNew('$!idFail','$!id_permohonan')" />
    
    </td>
    </tr>

</table>
<p>
<fieldset id="tableReport1" style="display:none;">
<legend><strong>Senarai Laporan</strong></legend>
	<table width="100%" border="0" cellspacing="2" cellpadding="2">
       <tr>
        <td><a href="#" class="style2" onClick="javascript:cetakMMK()"><font color="blue">Pampasan </font></a></td>
      </tr>           
    </table>
</fieldset>
<input type="hidden" name="id_fail" id="id_fail" value="$idFail" />
<input name="idPB" type="hidden" value="$idPihakBerkepentingan" />
<input name="id_hakmilik" type="hidden" value="$id_hakmilik" />
<input name="idSiasatan" type="hidden" value="$ID_SIASATAN" />
<input name="idHakmilikPB" type="hidden" value="$idHakmilikPB" />
<input name="idTanah" type="hidden" value="$idTanah" />
<input name="idBayaran" type="hidden" value="$idBayaran" />
<input name="idBayaranEFT" type="hidden" value="$idBayaranEFT" />
<input type="hidden" name="id_permohonan" value="$id_permohonan" />
<input type="hidden" name="id_status" value="$!id_status">
 
<script type="text/javascript">
<!--
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:0});
//-->
</script>
<script>
function kembaliTuntutanNew(id_fail,id_permohonan){
	
	document.${formName}.id_permohonan.value = id_permohonan;	
	document.${formName}.command.value = "viewlistHM";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraPampasanPB";
	document.${formName}.submit();
}
function setTable(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}
function tabNilaian(id_fail,id_permohonan){
	
	document.${formName}.id_fail.value = id_fail;
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraPampasanPB&action=tabNilaian";
	document.${formName}.submit();
}
function tabTerimaCek(id_fail,id_permohonan){
	
	document.${formName}.id_fail.value = id_fail;
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraPampasanPB&action=tabTerimaCek";
	document.${formName}.submit();
}
function tabSerahCek(id_fail,id_permohonan){

	document.${formName}.id_fail.value = id_fail;
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraPampasanPB&action=tabSerahCek";
	document.${formName}.submit();
	
}
function tabEFT(id_fail,id_permohonan){
	
	document.${formName}.id_fail = id_fail;
	document.${formName}.id_permohonan = id_permohonan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraPampasanPB&action=tabEFT";
	document.${formName}.submit();
}
function kemaskiniTuntutan(){
	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraPampasanPB&action=kemaskiniTuntutan";
	document.${formName}.submit();
}
function batalNewTuntutan(){
	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraPampasanPB&action=tabTuntutan";
	document.${formName}.submit();
}
function batalViewTuntutan(){
	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraPampasanPB&action=tabTuntutan";
	document.${formName}.submit();
}
function updateTuntutan(){

	var radioSelected = false;
	for (j = 0;  j < ${formName}.sorStatusTuntutan.length;  j++){
	if (${formName}.sorStatusTuntutan[j].checked)
	radioSelected = true;
	}

	if(!radioSelected){
		alert("Sila masukkan \"Status Tuntutan\" terlebih dahulu.");
		return;
	}
	
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraPampasanPB&action=updateTuntutan";
	document.${formName}.submit();
}
function kembaliTuntutan(){
	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraPampasanPB&action=newPampasanPB";
	document.${formName}.submit();
}

</script>