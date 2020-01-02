<style type="text/css">
<!--
.style1 {color: #FF0000}
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
  <!--<tr>
    <td align="left">No. Ruj. JKPTG Negeri</td>
    <td>:</td>
    <td>$no_rujukan_upt</td>
  </tr>-->
</table>
</fieldset>
<p>
<fieldset><legend><strong>Maklumat Pihak Berkepentingan</strong></legend>
<table width="100%">
  <tr>
    <td width="1%" align="left"><span class="style1">#if($readonlyPB != 'readonly')*#end</span></td>
    <td width="20%" align="left">Nama</td>
    <td width="1%">:</td>
    <td width="29%"><label>
      <input name="txtNama" type="text" id="txtNama" value="$NAMA_PB" size="40" $readonlyPB class="$disabledPB" style="text-transform:uppercase;"  onblur="this.value=this.value.toUpperCase();" onkeyup="this.value=this.value.toUpperCase();">
    </label></td>
    <td width="1%" align="left"><span class="style1">#if($readonlyPB != 'readonly')*#end</span></td>
    <td width="20%" align="left">Alamat</td>
    <td width="1%">:</td>
    <td width="29%"><label>
      <input name="txtAlamat1" type="text" class="$disabledPB" id="txtAlamat1" value="$ALAMAT1" size="40" $readonlyPB style="text-transform:uppercase;"  onblur="this.value=this.value.toUpperCase();" onkeyup="this.value=this.value.toUpperCase();">
    </label></td>
  </tr>
  <tr>
    <td width="1%" align="left"><span class="style1">#if($readonlyPB != 'readonly')*#end</span></td>
    <td align="left">Kod Jenis PB</td>
    <td>:</td>
    <td><label>$SelectJenisPB</label></td>
    <td width="1%" align="left">&nbsp;</td>
    <td align="left">&nbsp;</td>
    <td>&nbsp;</td>
    <td><label>
      <input name="txtAlamat2" type="text" class="$disabledPB" id="txtAlamat2" value="$ALAMAT2" size="40" $readonlyPB style="text-transform:uppercase;"  onblur="this.value=this.value.toUpperCase();" onkeyup="this.value=this.value.toUpperCase();">
    </label></td>
  </tr>
  <tr>
    <td width="1%" align="left"><span class="style1">#if($readonlyPB != 'readonly')*#end</span></td>
    <td align="left">Kod No. PB</td>
    <td>:</td>
    <td><label>$SelectJenisNoPB</label></td>
    <td width="1%" align="left">&nbsp;</td>
    <td align="left">&nbsp;</td>
    <td>&nbsp;</td>
    <td><label>
      <input name="txtAlamat3" type="text" class="$disabledPB" id="txtAlamat3" value="$ALAMAT3" size="40" $readonlyPB style="text-transform:uppercase;"  onblur="this.value=this.value.toUpperCase();" onkeyup="this.value=this.value.toUpperCase();">
    </label></td>
  </tr>
  <tr>
    <td width="1%" align="left"><span class="style1">#if($readonlyPB != 'readonly')*#end</span></td>
    <td align="left">No. PB</td>
    <td>:</td>
    <td><input name="txtNoPB" type="text" id="txtNoPB" value="$NO_PB" $readonlyPB class="$disabledPB"/></td>
    <td width="1%" align="left">&nbsp;</td>
    <td align="left">Poskod</td>
    <td>:</td>
    <td><label>
      <input name="txtPoskod" type="text" id="txtPoskod" value="$POSKOD" size="6" $readonlyPB class="$disabledPB">
    </label></td>
  </tr>
  <tr>
    <td width="1%" align="left">&nbsp;</td>
    <td align="left">Bangsa</td>
    <td>:</td>
    <td>$SelectBangsa</td>
    <td width="1%" align="left"><span class="style1">#if($readonlyPB != 'readonly')*#end</span></td>
    <td align="left">Negeri</td>
    <td>:</td>
    <td>$SelectNegeri</td>
  </tr>
  <tr>
    <td width="1%" align="left">&nbsp;</td>
    <td align="left">Warganegara</td>
    <td>:</td>
    <td><label>$SelectWarganegara</label></td>
    <td width="1%" align="left"><span class="style1">#if($readonlyPB != 'readonly')*#end</span></td>
    <td align="left">Bandar</td>
    <td>:</td>
    <td>$SelectBandar</td>
  </tr>
  <tr>
    <td width="1%" align="left"><span class="style1">#if($readonlyPB != 'readonly')*#end</span></td>
    <td align="left">Bahagian</td>
    <td>:</td>
    <td><input name="txtBahagianAtas" type="text" id="txtBahagianAtas" value="$SYER_ATAS" size="6" $readonlyPB class="$disabledPB"/>     
       /  <input name="txtBahagianBwh" type="text" id="txtBahagianBwh" value="$SYER_BAWAH" size="6" $readonlyPB class="$disabledPB"/></td>
    <td width="1%" align="left">&nbsp;</td>
    <td align="left">No Telefon Rumah</td>
    <td>:</td>
    <td><label>
      <input name="txtNoTel" type="text" id="txtNoTel" value="$NO_TEL_RUMAH" maxlength="12" $readonlyPB class="$disabledPB">
    </label></td>
  </tr>
  <tr>
    <td width="1%" align="left">&nbsp;</td>
    <td align="left">&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td width="1%" align="left">&nbsp;</td>
    <td align="left">No Telefon Bimbit</td>
    <td>:</td>
    <td><label>
      <input name="txtNoTelBimbit" type="text" id="txtNoTelBimbit" value="$NO_HP" maxlength="12" $readonlyPB class="$disabledPB"/>
    </label></td>
  </tr>
  <tr>
    <td width="1%" align="left">&nbsp;</td>
    <td align="left">&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td width="1%" align="left">&nbsp;</td>
    <td align="left">No. Faks</td>
    <td>:</td>
    <td><input name="txtNoFaks" type="text" id="txtNoFaks" value="$NO_FAX" maxlength="12" $readonlyPB class="$disabledPB"/></td>
  </tr>
  <tr>
    <td width="1%" align="left">&nbsp;</td>
    <td align="left">&nbsp;</td>
    <td>&nbsp;</td>
    <td><label></label></td>
    <td width="1%" align="left">&nbsp;</td>
    <td align="left">&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td colspan="8" align="center">
    #if($modePB == 'newPB')
      <input type="button" name="cmdSimpan3" id="cmdSimpan3" value="Simpan" onClick="simpanPB('$idHakmilik')"/>
      <input type="submit" name="cmdBatal3" id="cmdBatal3" value="Batal" onClick="batal()"/>
    #end
    #if($modePB == 'paparPB')
      <input type="button" name="cmdKemaskini2" id="cmdKemaskini2" value="Kemaskini" onClick="kemaskiniPB()"/>
      <input type="button" name="cmdHapus2" id="cmdHapus2" value="Hapus" onClick="delete_maklumatPB('$idPB')"/>
    #end 
    #if($modePB == 'kemaskiniPB')
      <input type="button" name="cmdSimpan4" id="cmdSimpan4" value="Simpan" onClick="updatePB()"/>
      <input type="button" name="cmdBatal4" id="cmdBatal4" value="Batal" onClick="batalPB()"/>
    #end
     
      <input type="button" name="cmdKembali2" id="cmdKembali2" value="Kembali" onClick="kembali()"/>    </td>
  </tr>
</table>
</fieldset>
<input name="idHakmilik" type="hidden" value="$idHakmilik" />
<input type="hidden" name="id_fail" value="$id_fail">
<input type="hidden" name="no_fail">          
<input type="hidden" name="id_permohonan" value="$id_permohonan">
<input type="hidden" name="no_permohonan">
<input name="idNegeri" type="hidden" value="$idNegeri" />
<input name="idDaerah" type="hidden" value="$idDaerah" />
<input name="idBandar" type="hidden" value="$idBandar" />
<input name="idPB" type="hidden" value="$idPB" />
<script>
function simpanPB(id_hakmilik) {

	if(document.${formName}.txtNama.value == ""){
		alert("Sila masukkan \"Nama Pihak Berkepentingan\" terlebih dahulu.");
  		document.${formName}.txtNama.focus(); 
		return;
	}
	else if(document.${formName}.txtNoPB.value == ""){
		alert("Sila masukkan \"Nombor Pihak Berkepentingan\" terlebih dahulu.");
  		document.${formName}.txtNoPB.focus(); 
		return;
	}
	else if(document.${formName}.socKodNoPB.value == ""){
		alert("Sila pilih \"Kod Nombor Pihak Berkepentingan\" terlebih dahulu.");
  		document.${formName}.socKodNoPB.focus(); 
		return;
	}
	else if(document.${formName}.txtAlamat1.value == ""){
		alert("Sila masukkan \"Alamat\" terlebih dahulu.");
  		document.${formName}.txtAlamat1.focus(); 
		return;
	}
	else if(document.${formName}.socKodJenisPB.value == ""){
		alert("Sila pilih \"Jenis Pihak Berkepentingan\" terlebih dahulu.");
  		document.${formName}.socKodJenisPB.focus(); 
		return;
	}
	else if(document.${formName}.socNegeri.value == ""){
		alert("Sila pilih \"Negeri\" terlebih dahulu.");
  		document.${formName}.socNegeri.focus(); 
		return;
	}
	else if(document.${formName}.socBandar.value == ""){
		alert("Sila pilih \"Bandar\" terlebih dahulu.");
  		document.${formName}.socBandar.focus(); 
		return;
	}
	
	if( !window.confirm("Adakah Anda Pasti?")) return;
	document.${formName}.idHakmilik.value = id_hakmilik;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraHakmilikPB&action=simpanPB"; 
	document.${formName}.submit();
	
}
function kemaskiniPB() {
	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraHakmilikPB&action=kemaskiniPB"; 
	document.${formName}.submit();
	
}
function updatePB() {
	
	if(document.${formName}.txtNama.value == ""){
		alert("Sila masukkan \"Nama Pihak Berkepentingan\" terlebih dahulu.");
  		document.${formName}.txtNama.focus(); 
		return;
	}
	else if(document.${formName}.txtNoPB.value == ""){
		alert("Sila masukkan \"Nombor Pihak Berkepentingan\" terlebih dahulu.");
  		document.${formName}.txtNoPB.focus(); 
		return;
	}
	else if(document.${formName}.socKodNoPB.value == ""){
		alert("Sila pilih \"Kod Nombor Pihak Berkepentingan\" terlebih dahulu.");
  		document.${formName}.socKodNoPB.focus(); 
		return;
	}
	else if(document.${formName}.txtAlamat1.value == ""){
		alert("Sila masukkan \"Alamat\" terlebih dahulu.");
  		document.${formName}.txtAlamat1.focus(); 
		return;
	}
	else if(document.${formName}.socKodJenisPB.value == ""){
		alert("Sila pilih \"Jenis Pihak Berkepentingan\" terlebih dahulu.");
  		document.${formName}.socKodJenisPB.focus(); 
		return;
	}
	else if(document.${formName}.socNegeri.value == ""){
		alert("Sila pilih \"Negeri\" terlebih dahulu.");
  		document.${formName}.socNegeri.focus(); 
		return;
	}
	else if(document.${formName}.socBandar.value == ""){
		alert("Sila pilih \"Bandar\" terlebih dahulu.");
  		document.${formName}.socBandar.focus(); 
		return;
	}
	
	if( !window.confirm("Adakah Anda Pasti?")) return;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraHakmilikPB&action=updatePB"; 
	document.${formName}.submit();
	
}
function doChangeidNegeriPB() {
    doAjaxCall${formName}("doChangeidNegeriPB");
}
function doChangeidNegeriUpdatePB() {
    doAjaxCall${formName}("doChangeidNegeriUpdatePB");
}
function kembali(){
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraHakmilikPB&action=viewMaklumatHakmilik"; 
	document.${formName}.submit();
}
function batal(){
	
	document.${formName}.txtNama.value == "";
	document.${formName}.txtNoPB.value == "";
	document.${formName}.socKodNoPB.value == "";
	document.${formName}.txtAlamat1.value == "";
	document.${formName}.txtPoskod.value == "";
	document.${formName}.txtNoTel.value == "";
	document.${formName}.txtNoTelBimbit.value == "";
	document.${formName}.txtNoFaks.value == "";
	document.${formName}.socKodJenisPB.value == "";
	document.${formName}.socBangsa.value == "";
	document.${formName}.socWarganegara.value == "";
	document.${formName}.socNegeri.value == "";
	document.${formName}.socBandar.value == "";
	return;
	
	
}
function batalPB(){
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraHakmilikPB&action=viewMaklumatPB"; 
	document.${formName}.submit();
}
function delete_maklumatPB(id_PB) {
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.idPB.value = id_PB;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraHakmilikPB&action=deleteMaklumatPB";
	document.${formName}.submit();
}
</script>