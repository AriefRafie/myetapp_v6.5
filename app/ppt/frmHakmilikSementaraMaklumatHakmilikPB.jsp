<style type="text/css">
<!--
.style1 {color: #FF0000}
.style52 {font-size: 9px; font-style: italic; color: #0000FF; }
-->
</style>

<fieldset>
<strong>
<legend>Hakmilik Sementara</legend>
</strong>
<table width="100%">
  <tr>
    <td width="29%" align="left">No. Ruj. JKPTG </td>
    <td width="1%">:</td>
    <td width="70%">$no_fail</td>
  </tr>
 <!-- <tr>
    <td align="left">&nbsp;</td>
    <td>:</td>
    <td>$no_rujukan_upt</td>
  </tr>-->
</table>
</fieldset>
<p>

<fieldset><legend><strong>Maklumat Hakmilik</strong></legend>
<table width="100%">
  <tr>
    <td width="1%" align="left"><span class="style1">#if($readonlyHakmilik != 'readonly')*#end</span></td>
    <td width="20%" align="left">Jenis Hakmilik</td>
    <td width="1%">:</td>
    <td width="29%" >$SelectJenisHakmilik</td>
    <td width="1%" align="left"><span class="style1">#if($readonlyHakmilik != 'readonly')*#end</span></td>
    <td width="20%" align="left">No. Lot / No. PT</td>
    <td width="1%">:</td>
    <td width="29%"><label>
        $SelectLot <input name="txtNoLot" type="text" class="$disabledHakmilik" id="txtNoLot" tabindex="9" value="$NO_LOT" size="15"  $readonlyHakmilik/>
    </label></td>
  </tr>
  <tr>
    <td width="1%" align="left"><span class="style1">#if($readonlyHakmilik != 'readonly')*#end</span></td>
    <td align="left">No. Hakmilik</td>
    <td>:</td>
    <td><label><input name="txtNoHakmilik" type="text" value="$NO_HAKMILIK" tabindex="2" $readonlyHakmilik class="$disabledHakmilik" style="text-transform:uppercase;"  onblur="this.value=this.value.toUpperCase();" onkeyup="this.value=this.value.toUpperCase();"/>
    </label></td>
    <td width="1%" align="left"><span class="style1">#if($readonlyHakmilik != 'readonly')*#end</span></td>
    <td align="left">Luas Lot (Keluasan)</td>
    <td>:</td>
    <td><label>
    <input name="txtLuasLot" type="text" class="$disabledHakmilik" id="txtLuasLot" tabindex="10" value="$LUAS_LOT" size="15" $readonlyHakmilik/> 
    $SelectLuasLot</label></td>
  </tr>
  <tr>
    <td width="1%" align="left"><span class="style1">#if($readonlyHakmilik != 'readonly')*#end</span></td>
    <td align="left">Tarikh Didaftar</td>
    <td>:</td>
    <td><label>
      
          <input name="txdTkhdaftar" type="text" id="txdTkhdaftar" value="$TARIKH_DAFTAR" size="10" $readonlyHakmilik class="$disabledHakmilik" tabindex="3" onblur="checking_validation(this,'tarikh_daftar_check','yes','daftar','tarikh');"> 
       #if($readonlyHakmilik != 'readonly') <a href="javascript:displayDatePicker('txdTkhdaftar',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a> #end
    <span class="style52">dd/mm/yyyy</span></label></td>
    <td width="1%" align="left"><span class="style1">#if($readonlyHakmilik != 'readonly')*#end</span></td>
    <td align="left">Kategori Penggunaan Tanah</td>
    <td>:</td>
    <td><label>$SelectKategoriTanah</label></td>
  </tr>
  <tr>
    <td width="1%" align="left">&nbsp;</td>
    <td align="left">Negeri</td>
    <td>:</td>
    <td> $SelectNegeri</td>
    <td width="1%" align="left"><span class="style1">#if($readonlyHakmilik != 'readonly')*#end</span></td>
    <td align="left">No. Lembaran Piawai</td>
    <td>:</td>
    <td valign="top"><input name="txtNoLembaranPiawai" type="text" id="txtNoLembaranPiawai" value="$NO_SYIT" tabindex="13" $readonlyHakmilik class="$disabledHakmilik" style="text-transform:uppercase;"  onblur="this.value=this.value.toUpperCase();" onkeyup="this.value=this.value.toUpperCase();"/></td>
  </tr>
  <tr>
    <td width="1%" align="left">&nbsp;</td>
    <td align="left">Daerah</td>
    <td>:</td>
    <td>$SelectDaerah</td>
    <td width="1%" align="left"><span class="style1">#if($readonlyHakmilik != 'readonly')*#end</span></td>
    <td align="left">No.Pelan Diperakui</td>
    <td>:</td>
    <td><label>
    <input name="txtNoPA" type="text" id="txtNoPA" value="$NO_PELAN" tabindex="14" $readonlyHakmilik class="$disabledHakmilik" style="text-transform:uppercase;"  onblur="this.value=this.value.toUpperCase();" onkeyup="this.value=this.value.toUpperCase();"/>
    </label></td>
  </tr>
  <tr>
    <td width="1%" align="left" valign="top">&nbsp;</td>
    <td align="left" valign="top">Bandar/Pekan/Mukim</td>
    <td valign="top">:</td>
    <td valign="top">$SelectBandar</td>
    <td width="1%" rowspan="2" align="left" valign="top"><span class="style1">#if($readonlyHakmilik != 'readonly')*#end</span></td>
    <td rowspan="2" align="left" valign="top">Sekatan Kepentingan</td>
    <td rowspan="2" valign="top">:</td>
    <td rowspan="2" valign="top"><label></label>      <label>
    <textarea name="txtSekatanKepentingan" id="txtSekatanKepentingan" cols="30" rows="5" tabindex="15" $readonlyHakmilik class="$disabledHakmilik" style="text-transform:uppercase;"  onblur="this.value=this.value.toUpperCase();" onkeyup="this.value=this.value.toUpperCase();">$SEKATAN_KEPENTINGAN</textarea>
      </label></td>
  </tr>
  <tr>
    <td width="1%" align="left" valign="top"><span class="style1">#if($readonlyHakmilik != 'readonly')*#end</span></td>
    <td align="left" valign="top">Syarat Nyata</td>
    <td valign="top">:</td>
    <td><label></label>
    <textarea name="txtSyaratNyata" id="txtSyaratNyata" cols="30" rows="5" tabindex="7" $readonlyHakmilik class="$disabledHakmilik" style="text-transform:uppercase;"  onblur="this.value=this.value.toUpperCase();" onkeyup="this.value=this.value.toUpperCase();">$SYARAT_NYATA</textarea></td>
  </tr>
  <tr>
    <td align="left" valign="top">&nbsp;</td>
    <td align="left" valign="top">Syarat Khas</td>
    <td valign="top">:</td>
    <td><textarea name="txtSyaratKhas" id="txtSyaratKhas" cols="30" rows="5" $readonlyHakmilik class="$disabledHakmilik" style="text-transform:uppercase;"  onblur="this.value=this.value.toUpperCase();" onkeyup="this.value=this.value.toUpperCase();">$SYARAT_KHAS</textarea></td>
    <td align="left" valign="top">&nbsp;</td>
    <td align="left" valign="top">&nbsp;</td>
    <td valign="top">&nbsp;</td>
    <td valign="top">&nbsp;</td>
  </tr>
</table>
</fieldset>
<p>
<fieldset><legend><strong>Maklumat Pengambilan Tanah</strong></legend>
<table width="100%">
  <tr>
    <td width="1%" align="left"><span class="style1">#if($readonlyHakmilik != 'readonly')*#end</span></td>
    <td width="20%" align="left">Luas Diguna/Disewa</td>
    <td width="1%">:</td>
    <td width="29%"><label>
        <input name="txtLuasAmbil" type="text" class="$disabledHakmilik" id="txtLuasAmbil" value="$LUAS_SEWA" size="15" $readonlyHakmilik/>
    $SelectLuasAmbil</label></td>
    <td width="1%" align="left"><span class="style1">#if($readonlyHakmilik != 'readonly')*#end</span></td>
    <td width="20%" align="left">Tarikh Mula</td>
    <td width="1%">:</td>
    <td width="29%"><label>
     
    <input name="txdTkhMula" type="text" id="txdTkhMula" value="$TARIKH_MULA" size="10" $readonlyHakmilik class="$disabledHakmilik" onblur="checking_validation(this,'tarikh_mula_check','yes','mula','tarikh');setTarikhAkhir()">
       #if($readonlyHakmilik != 'readonly') <a href="javascript:displayDatePicker('txdTkhMula',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a> #end
    <span class="style52">dd/mm/yyyy</span></label></td>
  </tr>
  <tr>
    <td width="1%" align="left"><span class="style1">#if($readonlyHakmilik != 'readonly')*#end</span></td>
    <td align="left">Tempoh</td>
    <td>:</td>
    <td><label>
     
      <input name="txtTempoh" type="text" class="$disabledHakmilik" id="txtTempoh" value="#if($TEMPOH_PENDUDUKAN=='')3 #else $TEMPOH_PENDUDUKAN #end" size="15" $readonlyHakmilik> 
      <select name="socUnitTempoh" id="socUnitTempoh" style="width:90px" $readonlyHakmilik class="$disabledHakmilik" $disabledHakmilik>
        
        #if($UNIT_TEMPOH == '')
        <option value="0">SILA PILIH</option>
        <option value="1">BULAN</option>
        <option value="2" selected="selected">TAHUN</option>
        
        #end
         #if($UNIT_TEMPOH == '1')
        <option value="0">SILA PILIH</option>
        <option value="1" selected="selected">BULAN</option>
        <option value="2">TAHUN</option>
        
        
        #end
         #if($UNIT_TEMPOH == '2')
        <option value="0">SILA PILIH</option>
        <option value="1">BULAN</option>
        <option value="2" selected="selected">TAHUN</option>
        #end
      
      </select>
    </label></td>
    <td width="1%" align="left"><span class="style1">#if($readonlyHakmilik != 'readonly')*#end</span></td>
    <td align="left">Tarikh Akhir</td>
    <td>:</td>
    <td><label>
     
     <input name="txdTkhAkhir" type="text" id="txdTkhAkhir" value="$TARIKH_AKHIR" size="10" $readonlyHakmilik class="$disabledHakmilik" onblur="checking_validation(this,'tarikh_akhir_check','yes','akhir','tarikh');">
    #if($readonlyHakmilik != 'readonly') <a href="javascript:displayDatePicker('txdTkhAkhir',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>#end <span class="style52">dd/mm/yyyy</span></label></td>
  </tr>
  <tr>
    <td width="1%" align="left">&nbsp;</td>
    <td align="left">&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td width="1%" align="left">&nbsp;</td>
    <td align="left">&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td colspan="8">
      <div align="center">
        #if($modeHakmilik == 'newHakmilik') 
         <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="simpanHakmilik()" />
         <input type="submit" name="cmdBatal" id="cmdBatal" value="Batal" onclick="batal()" />
        #end
        #if($modeHakmilik == 'paparHakmilik')
         <input type="submit" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onClick="kemaskiniHakmilik()" />
         <input type="submit" name="cmdHapus" id="cmdHapus" value="Hapus" onClick="delete_maklumatTanah('$idHakmilik')"/>
         <!--<input name="cmdCetak" type="submit" value="Cetak" id="cmdCetak"  onclick="setTable('tableReport1')">-->
        #end
        #if($modeHakmilik == 'kemaskiniHakmilik')
         <input type="button" name="cmdSimpan2" id="cmdSimpan2" value="Simpan" onClick="updateHakmilik('$idHakmilik')"/>
         <input type="button" name="cmdBatal2" id="cmdBatal2" value="Batal" onClick="batalHakmilik()"/>
        #end
         <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="kembali()" />
    </div></td>
  </tr>
</table>
</fieldset>


<p>
#if($modeHakmilik != 'newHakmilik')
<fieldset><legend><strong>Senarai Pihak Berkepentingan</strong></legend>
<table width="100%">
  <tr>
    <td colspan="4">
    <input type="button" name="cmdTambah" id="cmdTambah" value="Tambah" onClick="addPB()"/>
    </td>
  </tr>
  <tr class="table_header">
    <td><strong>Bil</strong></td>
    <td><strong>Nama Pihak Berkepentingan</strong></td>
    <td><strong>No Pihak Berkepentingan</strong></td>
    <td><strong>Bahagian</strong></td>
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
    <td class="$row">$pb.BIL</td>
    #if ($pb.BIL != '') 
    <td class="$row"><a href="javascript:view_PB('$pb.ID_PIHAKBERKEPENTINGAN')"><font color="blue">$pb.NAMA_PB</font></a></td>
    #else
    <td class="$row">$pb.NAMA_PB</td>
    #end
    <td class="$row">$pb.NO_PB</td>
    <td class="$row">$pb.BAHAGIAN</td>
  </tr>
  #end
</table>
#end
<input name="idHakmilik" type="hidden" value="$idHakmilik" />
<input type="hidden" name="id_fail" value="$id_fail">
<input type="hidden" name="no_fail">          
<input type="hidden" name="id_permohonan" value="$id_permohonan">
<input type="hidden" name="no_permohonan">
<input name="idNegeri" type="hidden" value="$idNegeri" />
<input name="idDaerah" type="hidden" value="$idDaerah" />
<input name="idBandar" type="hidden" value="$idBandar" />
<input name="idPB" type="hidden" value="$idPB" />
<input type="hidden" name="alert_message" id="alert_message" />
<script>
function setTable(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}
function cetakBorangQ() {
	<!--var url = "../servlet/ekptg.report.ppt.SuratRujukan?idfail="+id_fail;-->
	var url = "../servlet/ekptg.report.ppt.BorangQ";
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();	
}
function addPB() {
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraHakmilikPB&action=addPB";
	document.${formName}.submit();
	
}
function kemaskiniHakmilik() {
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraHakmilikPB&action=kemaskiniHakmilik"; 
	document.${formName}.submit();
}
function simpanHakmilik() {
	luas = parseInt(document.${formName}.txtLuasLot.value);
	luasSewa = parseInt(document.${formName}.txtLuasAmbil.value);
	tempoh = parseInt(document.${formName}.txtTempoh.value);

	if (luasSewa > luas) {
		alert ("Luas Diguna/Disewa melebihi Luas Lot");
		document.${formName}.txtLuasAmbil.focus();
		return;
	}
	if (tempoh > 3){
		alert ("Tempoh pendudukan melebihi 3 tahun.");
		document.${formName}.txtTempoh.focus();
		return;
	}
	
	if(document.${formName}.socJenisHakmilik.value == ""){
		alert("Sila pilih \"Jenis Hakmilik\" terlebih dahulu.");
  		document.${formName}.socJenisHakmilik.focus(); 
		return;
	}
	else if(document.${formName}.txtNoHakmilik.value == ""){
		alert("Sila masukkan \"No Hakmilik\" terlebih dahulu.");
  		document.${formName}.txtNoHakmilik.focus(); 
		return;
	}
	else if(document.${formName}.txdTkhdaftar.value == ""){
		alert("Sila masukkan \"Tarikh Didaftar\" terlebih dahulu.");
  		document.${formName}.txdTkhdaftar.focus(); 
		return;
	}
	else if(document.${formName}.txtSyaratNyata.value == ""){
		alert("Sila masukkan \"Syarat Nyata\" terlebih dahulu.");
  		document.${formName}.txtSyaratNyata.focus(); 
		return;
	}
	else if(document.${formName}.txtNoLot.value == ""){
		alert("Sila masukkan \"No Lot\" terlebih dahulu.");
  		document.${formName}.txtNoLot.focus(); 
		return;
	}
	else if(document.${formName}.socLot.value == ""){
		alert("Sila pilih \"Kod Lot\" terlebih dahulu.");
  		document.${formName}.socLot.focus(); 
		return;
	}
	else if(document.${formName}.txtLuasLot.value == ""){
		alert("Sila masukkan \" Luas Lot\" terlebih dahulu.");
  		document.${formName}.txtLuasLot.focus(); 
		return;
	}
	else if(document.${formName}.socLuasLot.value == ""){
		alert("Sila pilih \" Jenis Luas Lot \" terlebih dahulu.");
  		document.${formName}.socLuasLot.focus(); 
		return;
	}
	else if(document.${formName}.txtNoLembaranPiawai.value == ""){
		alert("Sila masukkan \"No Lembaran Piawai\" terlebih dahulu.");
  		document.${formName}.txtNoLembaranPiawai.focus(); 
		return;
	}
	else if(document.${formName}.txtNoPA.value == ""){
		alert("Sila masukkan \"No Pelan Diperakui\" terlebih dahulu.");
  		document.${formName}.txtNoPA.focus(); 
		return;
	}
	else if(document.${formName}.txtSekatanKepentingan.value == ""){
		alert("Sila masukkan \"Sekatan Kepentingan\" terlebih dahulu.");
  		document.${formName}.txtSekatanKepentingan.focus(); 
		return;
	}
	else if(document.${formName}.txtLuasAmbil.value == ""){
		alert("Sila masukkan \"Luas Diguna/Disewa\" terlebih dahulu.");
  		document.${formName}.txtLuasAmbil.focus(); 
		return;
	}
	else if(document.${formName}.socLuasAmbil.value == ""){
		alert("Sila pilih \"Jenis Luas Disewa/Diguna\" terlebih dahulu.");
  		document.${formName}.socLuasAmbil.focus(); 
		return;
	}
	else if(document.${formName}.txtTempoh.value == ""){
		alert("Sila masukkan \"Tempoh\" terlebih dahulu.");
  		document.${formName}.txtTempoh.focus(); 
		return;
	}
	else if(document.${formName}.socUnitTempoh.value == ""){
		alert("Sila pilih \"Unit Tempoh\" terlebih dahulu.");
  		document.${formName}.socUnitTempoh.focus(); 
		return;
	}
	else if(document.${formName}.txdTkhMula.value == ""){
		alert("Sila masukkan \"Tarikh Mula Sewa\" terlebih dahulu.");
  		document.${formName}.txdTkhMula.focus(); 
		return;
	}
	else if(document.${formName}.txdTkhAkhir.value == ""){
		alert("Sila masukkan \"Tarikh Akhir Sewa\" terlebih dahulu.");
  		document.${formName}.txdTkhAkhir.focus(); 
		return;
	}
	else if(document.${formName}.socKategoriTanah.value == ""){
		alert("Sila pilih \"Kategori Tanah\" terlebih dahulu.");
  		document.${formName}.socKategoriTanah.focus(); 
		return;
	}
	if( !window.confirm("Adakah Anda Pasti?")) return;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraHakmilikPB&action=simpanHakmilik"; 
	document.${formName}.submit();
	
}
function doChangeidNegeri() {
    doAjaxCall${formName}("doChangeidNegeri");
}
function doChangeidMukim() {
    doAjaxCall${formName}("doChangeidMukim");
}
function delete_maklumatTanah(id_hakmilik) {
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.idHakmilik.value = id_hakmilik;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraHakmilikPB&action=deleteMaklumatTanah";
	document.${formName}.submit();
}
function kembali() {
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraHakmilikPB&action=viewHakmilik";
	document.${formName}.submit();
}
function updateHakmilik(id_hakmilik) {
	
	luas = parseInt(document.${formName}.txtLuasLot.value);
	luasSewa = parseInt(document.${formName}.txtLuasAmbil.value);
	tempoh = parseInt(document.${formName}.txtTempoh.value);

	if (luasSewa > luas) {
		alert ("Luas Diguna/Disewa melebihi Luas Lot");
		document.${formName}.txtLuasAmbil.focus();
		return;
	}
	if (tempoh > 3){
		alert ("Tempoh pendudukan melebihi 3 tahun.");
		document.${formName}.txtTempoh.focus();
		return;
	}
	
		if(document.${formName}.socJenisHakmilik.value == ""){
		alert("Sila pilih \"Jenis Hakmilik\" terlebih dahulu.");
  		document.${formName}.socJenisHakmilik.focus(); 
		return;
	}
	else if(document.${formName}.txtNoHakmilik.value == ""){
		alert("Sila masukkan \"No Hakmilik\" terlebih dahulu.");
  		document.${formName}.txtNoHakmilik.focus(); 
		return;
	}
	else if(document.${formName}.txdTkhdaftar.value == ""){
		alert("Sila masukkan \"Tarikh Didaftar\" terlebih dahulu.");
  		document.${formName}.txdTkhdaftar.focus(); 
		return;
	}
	else if(document.${formName}.txtSyaratNyata.value == ""){
		alert("Sila masukkan \"Syarat Nyata\" terlebih dahulu.");
  		document.${formName}.txtSyaratNyata.focus(); 
		return;
	}
	else if(document.${formName}.txtNoLot.value == ""){
		alert("Sila masukkan \"No Lot\" terlebih dahulu.");
  		document.${formName}.txtNoLot.focus(); 
		return;
	}
	else if(document.${formName}.socLot.value == ""){
		alert("Sila pilih \"Kod Lot\" terlebih dahulu.");
  		document.${formName}.socLot.focus(); 
		return;
	}
	else if(document.${formName}.txtLuasLot.value == ""){
		alert("Sila masukkan \" Luas Lot\" terlebih dahulu.");
  		document.${formName}.txtLuasLot.focus(); 
		return;
	}
	else if(document.${formName}.socLuasLot.value == ""){
		alert("Sila pilih \" Jenis Luas Lot \" terlebih dahulu.");
  		document.${formName}.socLuasLot.focus(); 
		return;
	}
	else if(document.${formName}.txtNoLembaranPiawai.value == ""){
		alert("Sila masukkan \"No Lembaran Piawai\" terlebih dahulu.");
  		document.${formName}.txtNoLembaranPiawai.focus(); 
		return;
	}
	else if(document.${formName}.txtNoPA.value == ""){
		alert("Sila masukkan \"No Pelan Diperakui\" terlebih dahulu.");
  		document.${formName}.txtNoPA.focus(); 
		return;
	}
	else if(document.${formName}.txtSekatanKepentingan.value == ""){
		alert("Sila masukkan \"Sekatan Kepentingan\" terlebih dahulu.");
  		document.${formName}.txtSekatanKepentingan.focus(); 
		return;
	}
	else if(document.${formName}.txtLuasAmbil.value == ""){
		alert("Sila masukkan \"Luas Diguna/Disewa\" terlebih dahulu.");
  		document.${formName}.txtLuasAmbil.focus(); 
		return;
	}
	else if(document.${formName}.socLuasAmbil.value == ""){
		alert("Sila pilih \"Jenis Luas Disewa/Diguna\" terlebih dahulu.");
  		document.${formName}.socLuasAmbil.focus(); 
		return;
	}
	else if(document.${formName}.txtTempoh.value == ""){
		alert("Sila masukkan \"Tempoh\" terlebih dahulu.");
  		document.${formName}.txtTempoh.focus(); 
		return;
	}
	else if(document.${formName}.socUnitTempoh.value == ""){
		alert("Sila pilih \"Unit Tempoh\" terlebih dahulu.");
  		document.${formName}.socUnitTempoh.focus(); 
		return;
	}
	else if(document.${formName}.txdTkhMula.value == ""){
		alert("Sila masukkan \"Tarikh Mula Sewa\" terlebih dahulu.");
  		document.${formName}.txdTkhMula.focus(); 
		return;
	}
	else if(document.${formName}.txdTkhAkhir.value == ""){
		alert("Sila masukkan \"Tarikh Akhir Sewa\" terlebih dahulu.");
  		document.${formName}.txdTkhAkhir.focus(); 
		return;
	}
	else if(document.${formName}.socKategoriTanah.value == ""){
		alert("Sila pilih \"Kategori Tanah\" terlebih dahulu.");
  		document.${formName}.socKategoriTanah.focus(); 
		return;
	}
	
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.idHakmilik.value = id_hakmilik;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraHakmilikPB&action=updateHakmilik"; 
	document.${formName}.submit();	
	
}
function batalHakmilik(){
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraHakmilikPB&action=viewMaklumatHakmilik";
	document.${formName}.submit();
}
function batal(){

	document.${formName}.socJenisHakmilik.value == "";
	document.${formName}.txtNoHakmilik.value == "";
	document.${formName}.txdTkhdaftar.value == "";
	document.${formName}.txtSyaratNyata.value == "";
	document.${formName}.txtNoLot.value == "";
	document.${formName}.socLot.value == "";
	document.${formName}.txtLuasLot.value == "";
	document.${formName}.socLuasLot.value == "";
	document.${formName}.txtNoLembaranPiawai.value == "";
	document.${formName}.txtNoPA.value == "";
	document.${formName}.txtSekatanKepentingan.value == "";
	document.${formName}.txtLuasAmbil.value == "";
	document.${formName}.socLuasAmbil.value == "";
	document.${formName}.txtTempoh.value == "";
	document.${formName}.socUnitTempoh.value == "";
	document.${formName}.txdTkhMula.value == "";
	document.${formName}.txdTkhAkhir.value == "";
	document.${formName}.socKategoriTanah.value == "";
	return;

	
}
function view_PB(idPB) {
	
	document.${formName}.idPB.value = idPB;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraHakmilikPB&action=viewMaklumatPB";
	document.${formName}.submit();
	
}
function setTarikhAkhir(){

	var TB  = document.getElementById("txdTkhMula").value;

	var a = TB.substring(0,2);
	var b = TB.substring(3,5);
   	var c = TB.substring(6,10);
   	
	var dt1   = parseInt(TB.substring(0,2),10);
   	var mon1  = parseInt(TB.substring(3,5),10)-1;
   	var yr1   = parseInt(TB.substring(6,10),10)+3;

	var myDate=new Date(yr1, mon1, dt1);

	var day = myDate.getDate();
	var month = myDate.getMonth()+1;
	var year = myDate.getFullYear();

	var tarikhAkhir = "";
	if(month>=10){
		if(day>=10){
			tarikhAkhir = day + "/" + month + "/" + year;	
		}else{
			tarikhAkhir = "0"+ day + "/" + month + "/" + year;	
		}
			
	}else{
		if(day>=10){
			tarikhAkhir = day + "/0" + month + "/" + year;	
		}else{
			tarikhAkhir = "0"+ day + "/0" + month + "/" + year;	
		}
	}
	
	
	if(a!="" && b!="" && c!=""){
	document.getElementById("txdTkhAkhir").value = tarikhAkhir ;
	}else{
	document.getElementById("txdTkhAkhir").value = "" ;
	} 
	  
	
}
function checking_validation(field,point,mandatory,value_field,jenis_field){	
    	
	var lepas_or_xlepas = 2;	
	
	
	if(jenis_field == "tarikh")
	{
	var checkstr = "0123456789";
	var DateField = field;
	var Datevalue = "";
	var DateTemp = "";
	var seperator = "/";
	var day;
	var month;
	var year;
	var leap = 0;
	var err = 0;
	var i;
    err = 0;
	
	
	   DateValue = DateField.value;
	   
	   if(DateValue == "" && mandatory == "yes")
	   {
	   lepas_or_xlepas = 2;
	   }
	   else
	   {
	   
	   for (i = 0; i < DateValue.length; i++) {
		  if (checkstr.indexOf(DateValue.substr(i,1)) >= 0) {
		     DateTemp = DateTemp + DateValue.substr(i,1);
		  }
	   }
	   DateValue = DateTemp;
	   /* Always change date to 8 digits - string*/
	   /* if year is entered as 2-digit / always assume 20xx */
	   if (DateValue.length == 6) {
	      DateValue = DateValue.substr(0,4) + '20' + DateValue.substr(4,2); }
	   if (DateValue.length != 8) {
	      err = 19;}
	   /* year is wrong if year = 0000 */
	   year = DateValue.substr(4,4);
	   if (year == 0) {
	      err = 20;
	   }
	   /* Validation of month*/
	   month = DateValue.substr(2,2);
	   if ((month < 1) || (month > 12)) {
	      err = 21;
	   }
	   /* Validation of day*/
	   day = DateValue.substr(0,2);
	   if (day < 1) {
	     err = 22;
	   }
	   /* Validation leap-year february / day */
	   if ((year % 4 == 0) || (year % 100 == 0) || (year % 400 == 0)) {
	      leap = 1;
	   }
	   if ((month == 2) && (leap == 1) && (day > 29)) {
	      err = 23;
	   }
	   if ((month == 2) && (leap != 1) && (day > 28)) {
	      err = 24;
	   }
	   /* Validation of other months */
	   if ((day > 31) && ((month == "01") || (month == "03") || (month == "05") || (month == "07") || (month == "08") || (month == "10") || (month == "12"))) {
	      err = 25;
	   }
	   if ((day > 30) && ((month == "04") || (month == "06") || (month == "09") || (month == "11"))) {
	      err = 26;
	   }
	   /* if 00 ist entered, no error, deleting the entry */
	   if ((day == 0) && (month == 0) && (year == 00)) {
	      err = 0; day = ""; month = ""; year = ""; seperator = "";
	   }
	   /* if no error, write the completed date to Input-Field (e.g. 13.12.2001) */
	   if (err == 0) {
	      DateField.value = day + seperator + month + seperator + year;
		   
	   }
	  
	   else { 
	   
		  
	      DateField.select();
		 // DateField.focus();
		  lepas_or_xlepas = "3";
		 
	  
	   
	   
	   }
	  } 
	   //alert(lepas_or_xlepas);
	   if(lepas_or_xlepas == "1")
	   {
	       var tarikh_valid = "valid";
	       var currentTime = new Date();
		   var month = currentTime.getMonth() + 1;
		   var day = currentTime.getDate();
		   var year = currentTime.getFullYear();
		   var currentDate = day + "/" + month + "/" + year;
			
		   var str1  = DateField.value;
		   
		   var dt1   = parseInt(str1.substring(0,2),10);
		   var mon1  = parseInt(str1.substring(3,5),10)-1;
		   var yr1   = parseInt(str1.substring(6,10),10);
		   
		   var date = new Date(yr1, mon1, dt1);
		 
		  if (date > currentTime)
		  {			  
		  tarikh_valid = "xvalid";			
		  }
	   
	   
	   
	   if(tarikh_valid == "valid")
	   {	   
	   document.${formName}.alert_message.value  = "";	  
	   url = "../servlet/ekptg.view.ppt.SementaraCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);
	   }
	   else
	   {
	  
	   
	   document.${formName}.alert_message.value  = "Sila pastikan tarikh "+value_field+" tidak melebihi dari tarikh hari ini";	  
	   url = "../servlet/ekptg.view.ppt.SementaraCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);
	 //  DateField.focus();
	   
	   }
	   
	   
	   }
	   
	   
	   
	   if(lepas_or_xlepas == "2")
	   {
	   document.${formName}.alert_message.value  = "Sila masukkan tarikh "+value_field+"";	  
	   url = "../servlet/ekptg.view.ppt.SementaraCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);
	 //  DateField.select();
	//   DateField.focus();
	   }
	   
	   if(lepas_or_xlepas == "3")
	   {
	   document.${formName}.alert_message.value  = "Sila masukkan tarikh "+value_field+" dengan betul";	  
	   url = "../servlet/ekptg.view.ppt.SementaraCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);
	//   DateField.select();
	//   DateField.focus();
	   
	   }
	   
	   }
	   
	   
	   if(jenis_field == "normal")
	   {
	   
	   if(field.value == "" && mandatory == "yes")
	   {
	   lepas_or_xlepas = 2;
	   }   
	   
	   
	   if(lepas_or_xlepas == "2")
	   {
	   document.${formName}.alert_message.value  = "Sila masukkan "+value_field+"";	  
	   url = "../servlet/ekptg.view.ppt.SementaraCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);
	
	   }
	   else
	   {
	    document.${formName}.alert_message.value  = "";	  
	   url = "../servlet/ekptg.view.ppt.SementaraCheck";
	   actionName = "checking_validation";
	   target = point;
	   doAjaxUpdater(document.${formName}, url, target, actionName);	
	   }
	   
	   	   
	   }
	   
	   
	   
	
	}
	


	

</script>