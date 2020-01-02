<fieldset>
<legend><strong>Maklumat Tanah </strong></legend>
<table width="100%">
  <tr>
    <td width="1%"><font color="red">*</font></td>
    <td width="28%">Jenis Hakmilik</td>
    <td width="1%">:</td>
    <td width="70%">$SelectJenisHakmilik</td>
  </tr>
  <tr>
    <td width="1%">&nbsp;</td>
    <td width="28%">No. Hakmilik</td>
    <td>:</td>
    <td><input type="text" size="26px" name="no_hakmilik" maxlength="50" value="$noHakmilik" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase();" id="no_hakmilik" #if ($readonly == 'readonly') $readonly class = "disabled" #end/></td>
  </tr>
  <tr>
    <td width="1%"><font color="red">*</font></td>
    <td width="28%">Kod Lot</td>
    <td>:</td>
    <td>$SelectLot</td>
  </tr>
  <tr>
    <td width="1%"><font color="red">*</font></td>
    <td width="28%">No. Lot / No. PT</td>
    <td>:</td>
    <td><input type="text" name="no_lot" value="$noLot" onblur="validate(this,this.value);" onkeyup="validate(this,this.value);" maxlength="20"  style="text-transform:uppercase;" size="26px" id="no_lot" #if ($readonly == 'readonly') $readonly class = "disabled" #end/></td>
  </tr>
  <tr>
    <td width="1%">&nbsp;</td>
    <td width="28%">Negeri</td>
    <td>:</td>
    <td><input type="text" name="existNegeri" value="$existNegeri" size="42px" class="disabled" readonly="readonly" />
    <input type="hidden" name="id_existNegeri" value="$id_existNegeri" /></td>
  </tr>
  <tr>
    <td width="1%">&nbsp;</td>
    <td width="28%">Jajahan / Daerah</td>
    <td>:</td>
    <td><input type="text" name="existDaerah" value="$existDaerah" size="42px" class="disabled" readonly="readonly" />
    <input type="hidden" name="id_existDaerah" value="$id_existDaerah" /></td>
  </tr>
  <tr>
    <td width="1%">&nbsp;</td>
    <td width="28%">Bandar / Pekan / Mukim</td>
    <td>:</td>
    <td><label>
      <input name="existMukim" type="text" id="existMukim" value="$existMukim" size="42px" class="disabled" readonly="readonly"/>
      <input name="id_existMukim" type="hidden" id="id_existMukim" value="$id_existMukim" />
    </label></td>
  </tr>
  <tr>
    <td width="1%"><font color="red">*</font></td>
    <td width="28%">Unit Luas</td>
    <td>:</td>
    <td>$SelectLuas</td>
  </tr>
  <tr>
    <td width="1%">&nbsp;</td>
    <td width="28%">Luas Lot</td>
    <td>:</td>
    <td><input type="text" name="luas_lot" value="$luasLot" maxlength="20" onkeyup="validate(this,this.value);" id="luas_lot" size="5px" #if ($readonly == 'readonly') $readonly class = "disabled" #end/></td>
  </tr>
  <tr>
    <td width="1%">&nbsp;</td>
    <td width="28%">Anggaran luas yang hendak diambil</td>
    <td>:</td>
    <td><input type="text" name="anggaran_luas" maxlength="10" value="$anggaranLuas" onkeyup="validate(this,this.value);" id="luas_pt" size="5px" #if ($readonly == 'readonly') $readonly class = "disabled" #end/></td>
  </tr>
  <tr>
    <td width="1%">&nbsp;</td>
    <td width="28%">&nbsp;</td>
    <td>&nbsp;</td>
    <td>
    #if($currentStatus=="11")
            #if($mode == 'tambahHakmilik')
              <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onKeyPress="add_maklumat_tanah($id_permohonan)" onClick=	 	"add_maklumat_tanah($id_permohonan)">
            #elseif($mode == 'paparHakmilik')
                #if($currentStatus!="128")
                    <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onClick="kemaskiniTanah()">
                    <input type="button" name="cmdHapus" id="cmdHapus" value="Hapus" onClick="delete_maklumatTanah('$idTanah')">
                #end
            #elseif($mode == 'kemaskiniHakmilik')
              <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onClick="update_maklumat('$idTanah')"/>
              <input name="cmdBatal" type="button" value="Batal" onClick="edit_maklumat('$idTanah')">
              
            #end
       #end
      <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="kembali('$id_permohonan')">
    </td>
  </tr>
</table>
<input type="hidden" name="id_permohonan" value="$id_permohonan">  
<input type="hidden" name="id_hakmilik" value="$idTanah">
</fieldset>
<script>
function add_maklumat_tanah(id_permohonan)
{   
	luas = parseInt(document.${formName}.luas_lot.value);
	luasAmbil = parseInt(document.${formName}.anggaran_luas.value);

	if (luasAmbil > luas) {
		alert ("Anggaran luas melebihi luas lot");
		document.${formName}.anggaran_luas.focus();
		return;
	}
	if(document.${formName}.jenisHakMilik.value == ""){
		alert("Sila pilih \"Jenis hakmilik\" terlebih dahulu.");
  		document.${formName}.jenisHakMilik.focus(); 
		return;
	}
	if(document.${formName}.lot.value == ""){
		alert("Sila pilih \"Kod Lot\" terlebih dahulu.");
  		document.${formName}.lot.focus(); 
		return;
	}
	if(document.${formName}.no_lot.value == ""){
		alert("Sila masukkan \"No.Lot / No.PT\" terlebih dahulu.");
  		document.${formName}.no_lot.focus(); 
		return;
	}
	if(document.${formName}.luas.value == ""){
		alert("Sila pilih \"Unit luas\" terlebih dahulu.");
  		document.${formName}.luas.focus(); 
		return;
	}
	else
	{
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraSPT&action=addMaklumatTanah"; 
	document.${formName}.submit();
	}
	
}
function kemaskiniTanah() {
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraSPT&action=kemaskiniTanah";
	document.${formName}.submit();
}
function update_maklumat(id_hakmilik) {
	Editluas = parseInt(document.${formName}.luas_lot.value);
	EditluasAmbil = parseInt(document.${formName}.anggaran_luas.value);

	if (EditluasAmbil > Editluas) {
		alert ("Anggaran luas melebihi luas lot");
		document.${formName}.anggaran_luas.focus();
		return;
	}
	if(document.${formName}.jenisHakmilik.value == ""){
		alert("Sila pilih \"Jenis hakmilik\" terlebih dahulu.");
  		document.${formName}.jenisHakmilik.focus(); 
		return;
	}
	if(document.${formName}.lot.value == ""){
		alert("Sila pilih \"Kod Lot\" terlebih dahulu.");
  		document.${formName}.lot.focus(); 
		return;
	}
	if(document.${formName}.no_lot.value == ""){
		alert("Sila masukkan \"No.Lot / No.PT\" terlebih dahulu.");
  		document.${formName}.no_lot.focus(); 
		return;
	}
	if(document.${formName}.luas.value == ""){
		alert("Sila pilih \"Unit luas\" terlebih dahulu.");
  		document.${formName}.luas.focus(); 
		return;
	}
	else
	{
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.id_hakmilik.value = id_hakmilik;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraSPT&action=updateMaklumatTanah"; 
	document.${formName}.submit();	
	}
}
function edit_maklumat(id_hakmilik) {
	document.${formName}.id_hakmilik.value = id_hakmilik;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraSPT&action=edit_maklumat";
	document.${formName}.submit();
}
function kembali(id_permohonan) {
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraSPT&action=semak";
	document.${formName}.submit();
}
function delete_maklumatTanah(id_hakmilik) {
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.id_hakmilik.value = id_hakmilik;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraSPT&action=deleteMaklumatTanah";
	document.${formName}.submit();
}
</script>