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
    <li class="TabbedPanelsTab" tabindex="0" onclick="tabNilaian('$idFail','$idPermohonan','$idSiasatan')">Nilaian </li>
    <li class="TabbedPanelsTab" tabindex="0" onclick="tabTuntutan('$idFail','$idPermohonan','$idSiasatan')">Tuntutan</li>
    <li class="TabbedPanelsTab" tabindex="0" onclick="tabBantahan('$idFail','$idPermohonan','$idSiasatan')">Bantahan</li>
    <li class="TabbedPanelsTab" tabindex="0" >Keputusan</li>
  </ul>
  <div class="TabbedPanelsContentGroup">
    <div class="TabbedPanelsContent"></div>
    <div class="TabbedPanelsContent"></div>
    <div class="TabbedPanelsContent"></div>
    <div class="TabbedPanelsContent"></div>
    <div class="TabbedPanelsContent">
    #if($action == 'paparPB' || $action == 'kemaskiniKeputusan' || $action == 'updateKeputusan')
    <fieldset>
    <table width="100%">
  <tr>
    <td align="left" width="1%"><span class="style1">#if($readonlykeputusan != 'readonly')*#end</span></td>
    <td align="left" width="20%">Nilaian Sewaan Bulanan (RM)</td>
    <td width="1%">:</td>
    <td width="29%"><label>
    <input name="txtNilaiSewaBulan" type="text" id="txtNilaiSewaBulan" value="$NILAI_SEWA_BULANAN" onblur="validateNumber(this,this.value);validateModal(this,this.value,'$txtNilaiSewaBulan')" onkeyup="validateNumber(this,this.value);" $readonlykeputusan class="$disabledKeputusan" $disabledkeputusan />
    </label></td>
    <td width="1%" align="left"><span class="style1">#if($readonlykeputusan != 'readonly')*#end</span></td>
    <td width="20%" align="left">Amaun Pampasan (RM)</td>
    <td width="1%">:</td>
    <td width="29%"><label>
    <input name="txtAmaunPampasan" type="text" id="txtAmaunPampasan" value="$TAWARAN_PAMPASAN" onblur="validateNumber(this,this.value);validateModal(this,this.value,'$txtNilaiSeluruhan')" onkeyup="validateNumber(this,this.value);" $readonlyKeputusan class="$disabledKeputusan" $disabledKeputusan/>
    </label></td>
  </tr>
  <tr>
    <td width="1%" align="left"><span class="style1">#if($readonlykeputusan != 'readonly')*#end</span></td>
    <td align="left">Nama Pihak Berkepentingan</td>
    <td>:</td>
    <td><label>$NAMA_PB</label></td>
    <td width="1%" align="left">&nbsp;</td>
    <td align="left">Luas Tanah</td>
    <td>:</td>
    <td><label>$LUAS_SEWA</label></td>
  </tr>
  <tr>
    <td width="1%" align="left">&nbsp;</td>
    <td align="left">No Akaun</td>
    <td>:</td>
    <td><label>$NO_AKAUN</label></td>
    <td width="1%" align="left">&nbsp;</td>
    <td align="left">Tempoh Pendudukan / Penggunaan (Tahun)</td>
    <td>:</td>
    <td><label>$TEMPOH_PENDUDUKAN</label></td>
  </tr>
  <tr>
    <td width="1%" align="left">&nbsp;</td>
    <td align="left">Nama Bank</td>
    <td>:</td>
    <td>$NAMA_BANK</td>
    <td width="1%" align="left">&nbsp;</td>
    <td align="left">Tarikh Mula Sewaan</td>
    <td>:</td>
    <td><label>$TARIKH_MULA</label></td>
  </tr>
  <tr>
    <td width="1%" align="left"><span class="style1">#if($readonlykeputusan != 'readonly')*#end</span></td>
    <td align="left">Keputusan</td>
    <td>:</td>
    <td>
    #if($KEPUTUSAN_PERUNDINGAN == '1')
      <input type="radio" name="sorKeputusan" id="radio" value="1" checked="checked" $readonlyKeputusan class="$disabledKeputusan" $disabledKeputusan>
    Setuju 
    #else
     <input type="radio" name="sorKeputusan" id="radio" value="1" $readonlyKeputusan class="$disabledKeputusan" $disabledKeputusan>
    Setuju 
    #end
    #if($KEPUTUSAN_PERUNDINGAN == '2')
    <input type="radio" name="sorKeputusan" id="radio2" value="2" checked="checked" $readonlyKeputusan class="$disabledKeputusan" $disabledKeputusan>
    Tidak Setuju
    #else
    <input type="radio" name="sorKeputusan" id="radio2" value="2" $readonlyKeputusan class="$disabledKeputusan" $disabledKeputusan>
    Tidak Setuju
    #end    </td>
    <td width="1%" align="left">&nbsp;</td>
    <td align="left" valign="top">Tarikh Akhir Sewaan</td>
    <td valign="top">:</td>
    <td valign="top">$TARIKH_AKHIR</td>
  </tr>
  <tr>
    <td width="1%" align="left" valign="top"><span class="style1">#if($readonlykeputusan != 'readonly')*#end</span></td>
    <td align="left" valign="top">Ulasan Keputusan</td>
    <td valign="top">:</td>
    <td><label>
      <textarea name="txtUlasanKeputusan" id="txtUlasanKeputusan" cols="45" rows="5" $readonlyKeputusan class="$disabledKeputusan" $disabledKeputusan style="text-transform:uppercase;"  onblur="this.value=this.value.toUpperCase();" onkeyup="this.value=this.value.toUpperCase();">$ULASAN_KEPUTUSAN</textarea>
    </label></td>
    <td width="1%" align="left">&nbsp;</td>
    <td align="left" valign="top">&nbsp;</td>
    <td valign="top">&nbsp;</td>
    <td valign="top">&nbsp;</td>
  </tr>
</table>

    </fieldset>
    
    <table width="100%">
  	<tr>
    <td colspan="6" align="center">
    #if($modeKeputusan == 'paparKeputusan')
      <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="kemaskiniKeputusan()" />
      <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="setTable('tableReport1')" />
      <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="kembaliKeputusan()" />
    #end
    #if($modeKeputusan == 'updateKeputusan')
      <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="updateKeputusan()" />
      <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="batalViewKeputusan()" />
      <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="kembaliKeputusan()"/>
    #end
     
    </td>
    </tr>

	</table>
	#end
    #if($action == 'tabKeputusan' || $action == 'paparPB' || $action == 'kemaskiniKeputusan' || $action == 'updateKeputusan')
    <fieldset>
<legend><strong>Senarai Pihak Berkepentingan</strong></legend>
<table width="100%">
  <tr class="table_header">
    <td><strong>Bil</strong></td>
    <td><strong>Nama PB</strong></td>
    <td><strong>No PB</strong></td>
    <td><strong>No Hakmilik</strong></td>
    <td><strong>No Lot</strong></td>
    <td><strong>Amaun Pampasan (RM)</strong></td>
  </tr>
  #foreach ($pb in $SenaraiPB)
  
  #if ($pb.BIL == '') 
  	#set ($row = 'row1')
  #elseif ($pb.BIL % 2 != 0)
  	#set ($row = 'row1')
  #else 
  	#set ($row = 'row2')
  #end 
  <tr>
    <td>$pb.BIL</td>
     #if ($pb.BIL != '') 
    <td><a href="javascript:paparPB('$pb.ID_HAKMILIKPB')"><font color="blue">$pb.NAMA_PB</font></a></td>
    #else
      <td>$pb.NAMA_PB</td>
      #end
    <td>$pb.NO_PB</td>
    <td>$pb.NO_HAKMILIK</td>
    <td>$pb.NO_LOT</td>
    <td>$pb.TAWARAN_PAMPASAN</td>
  </tr>
  #end
</table>

</fieldset>
#end
    </div>
  </div>
</div>
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
<input name="idHakmilikPB" type="hidden" value="$idHakmilikPB" />
<input name="idHakmilik" type="hidden" value="$idHakmilik" />
<input name="idBorangQ" type="hidden" value="$ID_BORANGQ" />
<script type="text/javascript">
<!--
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:4});
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
function tabTuntutan(id_fail,id_permohonan){
	
	document.${formName}.id_fail = id_fail;
	document.${formName}.id_permohonan = id_permohonan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraPerundingan&action=tabTuntutan";
	document.${formName}.submit();
}
function paparPB(id_hakmilikpb){
	
	document.${formName}.idHakmilikPB.value = id_hakmilikpb;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraPerundingan&action=paparPB";
	document.${formName}.submit();
}
function kemaskiniKeputusan(){
	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraPerundingan&action=kemaskiniKeputusan";
	document.${formName}.submit();
}
function kembaliKeputusan(){
	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraPerundingan&action=newPerundingan";
	document.${formName}.submit();
}
function batalViewKeputusan(){
	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraPerundingan&action=paparPB";
	document.${formName}.submit();
}
function updateKeputusan(){

	if(document.${formName}.txtNilaiSewaBulan.value == ""){
		alert("Sila masukkan \"Nilai Sewaan Bulanan\" terlebih dahulu.");
  		document.${formName}.txtNilaiSewaBulan.focus(); 
		return;
	}
	if(document.${formName}.sorKeputusan.value == ""){
		alert("Sila masukkan \"Keputusan\" terlebih dahulu.");
  		document.${formName}.sorKeputusan.focus(); 
		return;
	}
	if(document.${formName}.txtUlasanKeputusan.value == ""){
		alert("Sila masukkan \"Ulasan Keputusan\" terlebih dahulu.");
  		document.${formName}.txtUlasanKeputusan.focus(); 
		return;
	}
	/*if(document.${formName}.txtNilaiSeluruhan.value == ""){
		alert("Sila masukkan \"Nilai Sewaan Keseluruhan\" terlebih dahulu.");
  		document.${formName}.txtNilaiSeluruhan.focus(); 
		return;
	}*/
	if(document.${formName}.txtAmaunPampasan.value == ""){
		alert("Sila masukkan \"Nilai Sewaan Keseluruhan\" terlebih dahulu.");
  		document.${formName}.txtAmaunPampasan.focus(); 
		return;
	}
	
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraPerundingan&action=updateKeputusan";
	document.${formName}.submit();
}
function validateModal(elmnt,content,content2) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = content2;
		return;
	}
	var num = content * 1;
	elmnt.value = num.toFixed(2);
	return;
}
function validateNumber(elmnt,content) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = RemoveNonNumeric(content);
		return;
	}
}
function doChangePB() {
    doAjaxCall${formName}("doChangePB");
}
function doChangePBUpdate() {
    doAjaxCall${formName}("doChangePBUpdate");
}
</script>