<fieldset>
<legend><strong>Maklumat Tanah </strong></legend>
<table width="100%">
  <tr>
    <td width="1%"><font color="red">#if($readonly != 'readonly')*#end</font></td>
    <td width="28%">Jenis Hakmilik</td>
    <td width="1%">:</td>
    <td width="70%">$SelectJenisHakmilik</td>
  </tr>
  <tr>
    <td width="1%"><font color="red">#if($readonly != 'readonly')*#end</font></td>
    <td width="28%">No. Hakmilik</td>
    <td>:</td>
    <td><input type="text" size="26px" name="no_hakmilik" maxlength="50" value="$noHakmilik" style="text-transform:uppercase;" onblur="checklot('checklot')"  onkeyup="checklot('checklot')" id="no_hakmilik" #if ($readonly == 'readonly') $readonly class = "disabled" #end/>
     <span id="checklot" style="color:red" ></span>
    </td>
  </tr>
  <tr>
    <td width="1%"><font color="red">#if($readonly != 'readonly')*#end</font></td>
    <td width="28%">Kod Lot</td>
    <td>:</td>
    <td>$SelectLot</td>
  </tr>
  <tr>
    <td width="1%"><font color="red">#if($readonly != 'readonly')*#end</font></td>
    <td width="28%">No. Lot / No. PT</td>
    <td>:</td>
    <td><input type="text" name="no_lot" value="$noLot" maxlength="20"  style="text-transform:uppercase;" size="26px" id="no_lot" #if ($readonly == 'readonly') $readonly class = "disabled" #end/>
   
    </td>
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
    <td width="1%"><font color="red">#if($readonly != 'readonly')*#end</font></td>
    <td width="28%">Bandar / Pekan / Mukim</td>
    <td>:</td>
    <td><label>
      $SelectBandar</label></td>
  </tr>
  <tr>
    <td width="1%"><font color="red">#if($readonly != 'readonly')*#end</font></td>
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
          #if($currentStatus!="127")
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
<p>
  <fieldset>
              <legend><strong>Maklumat Tanah Terlibat</strong></legend>
              <table width="100%">
                <tr> #if($currentStatus=="11")
                  <td colspan="5"><input type="submit" name="cmdTambahTanah" id="cmdTambahTanah" value="Tambah" onclick="javascript:tambah();" />
                  </td>
                  #else
                  <td>&nbsp;</td>
                  #end </tr>
                <tr class="table_header">
                  <td><strong>No</strong></td>
                  <td><strong>No. Hakmilik</strong></td>
                  <td><strong>No. Lot / No. PT</strong></td>
                  <td><strong>Mukim / Pekan / Bandar</strong></td>
                  <td><strong>Luas Lot</strong></td>
                </tr>
                #if($saiz_listTanah!=0)
                #foreach($listTanah in $listMaklumatTanah)
                #if ($listTanah.bil == '') 
                #set ($row = 'row1')
                #elseif ($listTanah.bil % 2 != 0)
                #set ($row = 'row1')
                #else 
                #set ($row = 'row2')
                #end 
                #set($idMaklumat=$listTanah.id_hakmilik)
                #set($bilHakmilik=$listTanah.bil)
                <tr>
                  <td class="$row">$listTanah.bil</td>
                  #if ($listTanah.bil != '')
                  <td class="$row"><a href="javascript:edit_maklumat('$idMaklumat')"><font color="blue">$listTanah.no_hakmilik</font></a></td>
                  #else
                  <td class="$row">$listTanah.no_hakmilik</td>
                  #end
                  <td class="$row">$listTanah.kod_lot $listTanah.no_lot</td>
                  <td class="$row">$listTanah.nama_mukim</td>
                  <td class="$row">$listTanah.luas_lot&nbsp;$listTanah.unitluaslot</td>
                </tr>
                #end    
                #end
              </table>
            </fieldset>
<script>
function add_maklumat_tanah(id_permohonan)
{   
	luas = parseInt(document.${formName}.luas_lot.value);
	luasAmbil = parseInt(document.${formName}.anggaran_luas.value);
	
	var c = 0;

	if(document.${formName}.validation_field != null  )
	{
	
	   if (document.${formName}.validation_field.length == null)
	   {	
		
	   if(document.${formName}.validation_field.value == "invalid")
	   {  
	   
	   c++;	
	   } 
		
	   } 
	   
	   else 
	   {
	   
			for (i = 0; i < document.${formName}.validation_field.length; i++)
			{		
				if(document.${formName}.validation_field[i].value == "invalid")
				{
				
				   c++;	 
				}  	
			}
		}	
	}
	
	
	if(c>0){
	alert("Sila pastikan maklumat yang diisi adalah lengkap dan sah");
	document.${formName}.jenisHakMilik.focus(); 

	return;
	}

  
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
	if(document.${formName}.no_hakmilik.value == ""){
		alert("Sila pilih \"No Hakmilik\" terlebih dahulu.");
  		document.${formName}.no_hakmilik.focus(); 
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
	if(document.${formName}.bandar.value == ""){
		alert("Sila pilih \" Bandar/Mukim/Pekan \" terlebih dahulu.");
  		document.${formName}.bandar.focus(); 
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
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraUPT&action=addMaklumatTanah"; 
	document.${formName}.submit();
	}
	
}
function kemaskiniTanah() {
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraUPT&action=kemaskiniTanah";
	document.${formName}.submit();
}
function update_maklumat(id_hakmilik) {
	Editluas = parseInt(document.${formName}.luas_lot.value);
	EditluasAmbil = parseInt(document.${formName}.anggaran_luas.value);
	
	var c = 0;

	if(document.${formName}.validation_field != null  )
	{
	
	   if (document.${formName}.validation_field.length == null)
	   {	
		
	   if(document.${formName}.validation_field.value == "invalid")
	   {  
	   
	   c++;	
	   } 
		
	   } 
	   
	   else 
	   {
	   
			for (i = 0; i < document.${formName}.validation_field.length; i++)
			{		
				if(document.${formName}.validation_field[i].value == "invalid")
				{
				
				   c++;	 
				}  	
			}
		}	
	}
	
	
	if(c>0){
	alert("Sila pastikan maklumat yang diisi adalah lengkap dan sah");
	document.${formName}.jenisHakMilik.focus(); 

	return;
	}
	
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
	if(document.${formName}.no_hakmilik.value == ""){
		alert("Sila pilih \"No Hakmilik\" terlebih dahulu.");
  		document.${formName}.no_hakmilik.focus(); 
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
	if(document.${formName}.bandar.value == ""){
		alert("Sila pilih \" Bandar/Mukim/Pekan \" terlebih dahulu.");
  		document.${formName}.bandar.focus(); 
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
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraUPT&action=updateMaklumatTanah"; 
	document.${formName}.submit();	
	}
	
}
function tambah() {
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraUPT&action=tambah"; 
	document.${formName}.submit();
}
function edit_maklumat(id_hakmilik) {
	document.${formName}.id_hakmilik.value = id_hakmilik;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraUPT&action=edit_maklumat";
	document.${formName}.submit();
}
function kembali(id_permohonan) {
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraUPT&action=semak";
	document.${formName}.submit();
}
function delete_maklumatTanah(id_hakmilik) {
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.id_hakmilik.value = id_hakmilik;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.SementaraUPT&action=deleteMaklumatTanah";
	document.${formName}.submit();
}
function checklot(target_field)
{


	url = "../servlet/ekptg.view.ppt.PPTCheck";
	actionName = "check_no_hakmilik";
	//actionName = "check_harta";
	
	target = target_field;
	doAjaxUpdater(document.${formName}, url, target, actionName);
	//alert("hoho");
	
	
}
</script>
