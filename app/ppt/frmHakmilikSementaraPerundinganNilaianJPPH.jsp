<style type="text/css">
<!--
.style1 {color: #FF0000}
.style2 {color: #000000}
-->
</style>
<input name="action" type="hidden"/>
#parse("app/ppt/frmHakmilikSementaraMaklumatPermohonan.jsp")
<p>
#parse("app/ppt/frmHakmilikSementaraMaklumatPerundingan.jsp")
<div id="TabbedPanels1" class="TabbedPanels">
  <ul class="TabbedPanelsTabGroup">
    <li class="TabbedPanelsTab" tabindex="0" onclick="tabTuanTanah('$idFail','$idPermohonan','$idSiasatan')">Keterangan Tuan Tanah</li>
    <li class="TabbedPanelsTab" tabindex="0" >Nilaian</li>
    <li class="TabbedPanelsTab" tabindex="0" onclick="tabTuntutan('$idFail','$idPermohonan','$idSiasatan')">Tuntutan</li>
    <li class="TabbedPanelsTab" tabindex="0" onclick="tabBantahan('$idFail','$idPermohonan','$idSiasatan')">Bantahan</li>
    <li class="TabbedPanelsTab" tabindex="0" onclick="tabKeputusan('$idFail','$idPermohonan','$idSiasatan')">Keputusan</li>
  </ul>
  <div class="TabbedPanelsContentGroup">
    <div class="TabbedPanelsContent"></div>
    <div class="TabbedPanelsContent">
    
    <div id="error"></div>
    <div id="progressBar" style="display: none;">
      <div id="progressBarBoxContent"></div>
    </div>
    <fieldset><legend><strong>
    Anggaran Nilai Pegawai Petempatan</strong></legend>
    <table width="100%">
      <tr>
        <td align="left" width="1%">&nbsp;</td>
        <td align="left" width="18%">Harga Seunit (RM)</td>
        <td width="1%">:</td>
        <td width="32%"><label>
          <input name="txtAnggaranHrgSeunit" type="text" class="$disabledNilaian" id="txtAnggaranHrgSeunit" value="$HARGA_SEUNIT_SO" $readonlyNilaian $disabledNilaian onblur="validateNumber(this,this.value);validateModal(this,this.value,'$txtAnggaranHrgSeunit')" onkeyup="validateNumber(this,this.value);" >
          <select name="socUnitHargaSO" id="socUnitHargaSO" style="width:85px" class="$disabledNilaian" $readonlyNilaian $disabledNilaian>
          #if($UNIT_HARGA_SO == '0')
            <option value="0" selected="selected">SILA PILIH</option>
            <option value="M">METER PERSEGI</option>
            <option value="K">KAKI PERSEGI</option>
          #end
          #if($UNIT_HARGA_SO == 'M')
            <option value="0">SILA PILIH</option>
            <option value="M" selected="selected">METER PERSEGI</option>
            <option value="K">KAKI PERSEGI</option>
          #end
          #if($UNIT_HARGA_SO == 'K')
            <option value="0">SILA PILIH</option>
            <option value="M">METER PERSEGI</option>
            <option value="K" selected="selected">KAKI PERSEGI</option>
          #end
          </select>
        </label></td>
        <td width="1%" align="left">&nbsp;</td>
        <td width="14%" align="left">Pecahpisah (RM)</td>
        <td width="1%">:</td>
        <td width="32%"><label>
          <input name="txtAnggaranPecahpisah" type="text" class="$disabledNilaian" id="txtAnggaranPecahpisah" value="$BAYAR_PECAHPISAH" $readonlyNilaian $disabledNilaian onblur="validateNumber(this,this.value);validateModal(this,this.value,'$txtAnggaranPecahpisah')" onkeyup="validateNumber(this,this.value);" >
        </label></td>
      </tr>
      <tr>
        <td width="1%" align="left">&nbsp;</td>
        <td align="left">Harga Pasaran (RM)</td>
        <td>:</td>
        <td><label>
          <input name="txtAnggaranHrgPasaran" type="text" class="$disabledNilaian" id="txtAnggaranHrgPasaran" value="$HARGA_PASARAN_SO" $readonlyNilaian $disabledNilaian onblur="validateNumber(this,this.value);validateModal(this,this.value,'$txtAnggaranHrgPasaran')" onkeyup="validateNumber(this,this.value);" >
        </label></td>
        <td width="1%" align="left">&nbsp;</td>
        <td align="left"> Kenaikan Nilai (RM)</td>
        <td>:</td>
        <td><label>
          <input name="txtAnggaranKenaikanNilai" type="text" class="$disabledNilaian" id="txtAnggaranKenaikanNilai" value="$BAYAR_NAIK_NILAISO" $readonlyNilaian $disabledNilaian onblur="validateNumber(this,this.value);validateModal(this,this.value,'$txtAnggaranKenaikanNilai')" onkeyup="validateNumber(this,this.value);" >
        </label></td>
      </tr>
      <tr>
        <td width="1%" align="left">&nbsp;</td>
        <td align="left">Penjejasan Terbabit (RM)</td>
        <td>:</td>
        <td><label>
          <input name="txtAnggaranPenjejasan" type="text" class="$disabledNilaian" id="txtAnggaranPenjejasan" value="$BAYAR_PENJEJASAN" $readonlyNilaian $disabledNilaian onblur="validateNumber(this,this.value);validateModal(this,this.value,'$txtAnggaranPenjejasan')" onkeyup="validateNumber(this,this.value);" >
        </label></td>
        <td width="1%" align="left">&nbsp;</td>
        <td align="left">&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
    </table>

    </fieldset>
    <fieldset><legend><strong>Nilai JPPH</strong></legend>
    <table width="100%">
      <tr>
        <td align="left" width="1%">&nbsp;</td>
        <td align="left" width="18%">Harga Seunit (RM)</td>
        <td width="1%">:</td>
        <td width="32%"><label>
          <input name="txtJPPHHrgSeunit" type="text" class="$disabledNilaian" id="txtJPPHHrgSeunit" value="$HARGA_SEUNIT_JPPH" $readonlyNilaian $disabledNilaian onblur="validateNumber(this,this.value);validateModal(this,this.value,'$txtAmaunResit')" onkeyup="validateNumber(this,this.value);" >
          <select name="socUnitHargaJPPH" id="socUnitHargaJPPH" style="width:85px" class="$disabledNilaian" $readonlyNilaian $disabledNilaian>
          #if($UNIT_HARGA == '0')
            <option value="0" selected="selected">SILA PILIH</option>
            <option value="M">METER PERSEGI</option>
            <option value="K">KAKI PERSEGI </option>
          #end
          #if($UNIT_HARGA == 'M')
            <option value="0">SILA PILIH</option>
            <option value="M" selected="selected">METER PERSEGI</option>
            <option value="K">KAKI PERSEGI </option>
          #end
          #if($UNIT_HARGA == 'K')
            <option value="0">SILA PILIH</option>
            <option value="M">METER PERSEGI</option>
            <option value="K" selected="selected">KAKI PERSEGI </option>
          #end
          </select>
        </label></td>
        <td width="1%" align="left">&nbsp;</td>
        <td width="14%" align="left">Pecahpisah (RM)</td>
        <td width="1%">:</td>
        <td width="32%"><label>
          <input name="txtJPPHPecahpisah" type="text" class="$disabledNilaian" id="txtJPPHPecahpisah" value="$AMAUN_PECAHPISAH_JPPH" $readonlyNilaian $disabledNilaian onblur="validateNumber(this,this.value);validateModal(this,this.value,'$txtJPPHPecahpisah')" onkeyup="validateNumber(this,this.value);" >
        </label></td>
      </tr>
      <tr>
        <td width="1%" align="left">&nbsp;</td>
        <td align="left">Harga Pasaran (RM)</td>
        <td>:</td>
        <td><label>
          <input name="txtJPPHHrgPasaran" type="text" class="$disabledNilaian" id="txtJPPHHrgPasaran" value="$HARGA_PASARAN" $readonlyNilaian $disabledNilaian onblur="validateNumber(this,this.value);validateModal(this,this.value,'$txtJPPHHrgPasaran')" onkeyup="validateNumber(this,this.value);" >
        </label></td>
        <td width="1%" align="left">&nbsp;</td>
        <td align="left">Kenaikan Nilai (RM)</td>
        <td>:</td>
        <td><label>
          <input name="txtJPPHKenaikanNilai" type="text" class="$disabledNilaian" id="txtJPPHKenaikanNilai" value="$NAIK_NILAI_JPPH" $readonlyNilaian $disabledNilaian onblur="validateNumber(this,this.value);validateModal(this,this.value,'$txtJPPHKenaikanNilai')" onkeyup="validateNumber(this,this.value);" >
        </label></td>
      </tr>
      <tr>
        <td width="1%" align="left">&nbsp;</td>
        <td align="left">Penjejasan Terbabit (RM)</td>
        <td>:</td>
        <td><label>
          <input name="txtJPPHPenjejasan" type="text" class="$disabledNilaian" id="txtJPPHPenjejasan" value="$AMAUN_PENJEJASAN_JPPH" $readonlyNilaian $disabledNilaian onblur="validateNumber(this,this.value);validateModal(this,this.value,'$txtJPPHPenjejasan')" onkeyup="validateNumber(this,this.value);">
        </label></td>
        <td width="1%" align="left">&nbsp;</td>
        <td align="left">&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
    </table>

    </fieldset>
   
    </div>
    <div class="TabbedPanelsContent"></div>
    <div class="TabbedPanelsContent"></div>
    <div class="TabbedPanelsContent"></div>
  </div>
</div>
<table width="100%">
  <tr>
    <td colspan="6" align="center">
    #if($modeNilaian == 'paparNilaian')
      <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="kemaskiniNilaian()" />
      <!--<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="setTable('tableReport1')"/>-->
    #end
    #if($modeNilaian == 'newNilaian')
      <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="simpanNilaian()" />
      <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="batalNewNilaian()" />
    #end
    #if($modeNilaian == 'updateNilaian')
      <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="updateNilaian()" />
      <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="batalViewNilaian()" />
    #end
      <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="kembaliNilaian()" />
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
<input name="idTanah" type="hidden" value="$idTanah" />
<input name="idHakmilik" type="hidden" value="$idHakmilik" />

<script type="text/javascript">
<!--
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:1});
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
function tabTuntutan(id_fail,id_permohonan){
	
	document.${formName}.id_fail = id_fail;
	document.${formName}.id_permohonan = id_permohonan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraPerundingan&action=tabTuntutan";
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
function simpanNilaian(){

	/*if(document.${formName}.txtAnggaranHrgSeunit.value == ""){
		alert("Sila masukkan \"Harga Seunit\" terlebih dahulu.");
  		document.${formName}.txtAnggaranHrgSeunit.focus(); 
		return;
	}
	if(document.${formName}.socUnitHargaSO.value == "0"){
		alert("Sila masukkan \"Unit Harga Seunit\" terlebih dahulu.");
  		document.${formName}.socUnitHargaSO.focus(); 
		return;
	}
	if(document.${formName}.txtAnggaranHrgPasaran.value == ""){
		alert("Sila masukkan \"Harga Pasaran\" terlebih dahulu.");
  		document.${formName}.txtAnggaranHrgPasaran.focus(); 
		return;
	}
	if(document.${formName}.txtAnggaranPenjejasan.value == ""){
		alert("Sila masukkan \"Penjejasan Terbabit\" terlebih dahulu.");
  		document.${formName}.txtAnggaranPenjejasan.focus(); 
		return;
	}
	if(document.${formName}.txtAnggaranPecahpisah.value == ""){
		alert("Sila masukkan \"Pecahpisah\" terlebih dahulu.");
  		document.${formName}.txtAnggaranPecahpisah.focus(); 
		return;
	}
	if(document.${formName}.txtAnggaranKenaikanNilai.value == ""){
		alert("Sila masukkan \"Kenaikan Nilai\" terlebih dahulu.");
  		document.${formName}.txtAnggaranKenaikanNilai.focus(); 
		return;
	}
	if(document.${formName}.txtJPPHHrgSeunit.value == ""){
		alert("Sila masukkan \"Harga Seunit\" terlebih dahulu.");
  		document.${formName}.txtJPPHHrgSeunit.focus(); 
		return;
	}
	if(document.${formName}.socUnitHargaJPPH.value == "0"){
		alert("Sila masukkan \"Unit Harga Seunit\" terlebih dahulu.");
  		document.${formName}.socUnitHargaJPPH.focus(); 
		return;
	}
	if(document.${formName}.txtJPPHHrgPasaran.value == ""){
		alert("Sila masukkan \"Harga Pasaran\" terlebih dahulu.");
  		document.${formName}.txtJPPHHrgPasaran.focus(); 
		return;
	}
	if(document.${formName}.txtJPPHPenjejasan.value == ""){
		alert("Sila masukkan \"Penjejasan Terbabit\" terlebih dahulu.");
  		document.${formName}.txtJPPHPenjejasan.focus(); 
		return;
	}
	if(document.${formName}.txtJPPHPecahpisah.value == ""){
		alert("Sila masukkan \"Pecahpisah\" terlebih dahulu.");
  		document.${formName}.txtJPPHPecahpisah.focus(); 
		return;
	}
	if(document.${formName}.txtJPPHKenaikanNilai.value == ""){
		alert("Sila masukkan \"Kenaikan Nilai\" terlebih dahulu.");
  		document.${formName}.txtJPPHKenaikanNilai.focus(); 
		return;
	}*/
	
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraPerundingan&action=simpanNilaian";
	document.${formName}.submit();
}
function kemaskiniNilaian(){
	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraPerundingan&action=kemaskiniNilaian";
	document.${formName}.submit();
}
function updateNilaian(){

	/*if(document.${formName}.txtAnggaranHrgSeunit.value == ""){
		alert("Sila masukkan \"Harga Seunit\" terlebih dahulu.");
  		document.${formName}.txtAnggaranHrgSeunit.focus(); 
		return;
	}
	if(document.${formName}.socUnitHargaSO.value == "0"){
		alert("Sila masukkan \"Unit Harga Seunit\" terlebih dahulu.");
  		document.${formName}.socUnitHargaSO.focus(); 
		return;
	}
	if(document.${formName}.txtAnggaranHrgPasaran.value == ""){
		alert("Sila masukkan \"Harga Pasaran\" terlebih dahulu.");
  		document.${formName}.txtAnggaranHrgPasaran.focus(); 
		return;
	}
	if(document.${formName}.txtAnggaranPenjejasan.value == ""){
		alert("Sila masukkan \"Penjejasan Terbabit\" terlebih dahulu.");
  		document.${formName}.txtAnggaranPenjejasan.focus(); 
		return;
	}
	if(document.${formName}.txtAnggaranPecahpisah.value == ""){
		alert("Sila masukkan \"Pecahpisah\" terlebih dahulu.");
  		document.${formName}.txtAnggaranPecahpisah.focus(); 
		return;
	}
	if(document.${formName}.txtAnggaranKenaikanNilai.value == ""){
		alert("Sila masukkan \"Kenaikan Nilai\" terlebih dahulu.");
  		document.${formName}.txtAnggaranKenaikanNilai.focus(); 
		return;
	}
	if(document.${formName}.txtJPPHHrgSeunit.value == ""){
		alert("Sila masukkan \"Harga Seunit\" terlebih dahulu.");
  		document.${formName}.txtJPPHHrgSeunit.focus(); 
		return;
	}
	if(document.${formName}.socUnitHargaJPPH.value == "0"){
		alert("Sila masukkan \"Unit Harga Seunit\" terlebih dahulu.");
  		document.${formName}.socUnitHargaJPPH.focus(); 
		return;
	}
	if(document.${formName}.txtJPPHHrgPasaran.value == ""){
		alert("Sila masukkan \"Harga Pasaran\" terlebih dahulu.");
  		document.${formName}.txtJPPHHrgPasaran.focus(); 
		return;
	}
	if(document.${formName}.txtJPPHPenjejasan.value == ""){
		alert("Sila masukkan \"Penjejasan Terbabit\" terlebih dahulu.");
  		document.${formName}.txtJPPHPenjejasan.focus(); 
		return;
	}
	if(document.${formName}.txtJPPHPecahpisah.value == ""){
		alert("Sila masukkan \"Pecahpisah\" terlebih dahulu.");
  		document.${formName}.txtJPPHPecahpisah.focus(); 
		return;
	}
	if(document.${formName}.txtJPPHKenaikanNilai.value == ""){
		alert("Sila masukkan \"Kenaikan Nilai\" terlebih dahulu.");
  		document.${formName}.txtJPPHKenaikanNilai.focus(); 
		return;
	}*/
	
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraPerundingan&action=updateNilaian";
	document.${formName}.submit();
}
function batalNewNilaian(){
	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraPerundingan&action=tabNilaian";
	document.${formName}.submit();
}
function batalViewNilaian(){
	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraPerundingan&action=tabNilaian";
	document.${formName}.submit();
}
function kembaliNilaian(){
	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraPerundingan&action=newPampasanPB";
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

</script>