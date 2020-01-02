<style type="text/css">
<!--
.style1 {color: #FF0000}
-->
</style>
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>

<input name="actionPerletakhakan" type="hidden" value="$actionPerletakhakan" id="actionPerletakhakan" /> 
<input name="mode" type="hidden" value="$mode" id="mode"/>
<input name="hitButton" type="hidden" id="hitButton"/> 

<input name="idFail" type="hidden" value="$idFail" id="idFail" /> 
<input name="idPermohonan" type="hidden" value="$idPermohonan" id="idPermohonan" /> 
<input name="idHakmilikurusan" type="hidden" value="$idHakmilikurusan" id="idHakmilikurusan" /> 
<input name="idPihakberkepentingan" type="hidden" value="$idPihakberkepentingan" id="idPihakberkepentingan"/>
<input name="idBebanan" type="hidden" value="$idBebanan" id="idBebanan"/>

<fieldset><legend>HAKMILIK PERLETAKHAKAN</legend>
#parse("app/htp/frmPerletakhakanHak.jsp") 
<fieldset>
<legend>Senarai Urusan Sedia Ada Keatas Hakmilik</legend>
#if ($mode == "")
<input type="button" name="btnAdd" id="btnAdd" value="Tambah" onclick="tambahBebanan()" />
#end

<table width="100%" border="0">
  <tr class="table_header">
    <td width="5%">Bil</td>
    <td width="20%">No Perserahan</td>
    <td width="35%">Nama PB</td>
    <td width="30%">Urusan</td>
    <td width="10%">Tarikh Mohon</td>
  </tr> 
  
  #if ($ListBebanan.size() > 0)
  #foreach ($list in $ListBebanan)
   #set( $i = $velocityCount )
   #if ( ($i % 2) != 1 ) 
   #set( $row = "row2" ) 
   #else 
   #set( $row = "row1" ) 
   #end
  <tr>
    <td class="$row">$list.bil </td>
    <td class="$row"><a href="javascript:paparBebanan('$list.idBebanan')"><font color="blue">$list.noPerserahan</font></a></td>
    <td class="$row">$list.namaPB</td>
    <td class="$row">$list.keterangan</td>
    <td class="$row">$list.tarikhDaftar</td>
  </tr>
  #end
  #else
  <tr class="row1">
  	<td>&nbsp;</td>
    <td>Tiada Rekod</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  #end
</table>
</fieldset>

<fieldset>
#if($mode == "newBebanan" || $mode == "updateBebanan" || $mode == "viewBebanan")
	    <legend>Maklumat Urusan Sedia Ada Keatas Hakmilik</legend>
        
        <table width="100%" border="0" cellpadding="2">
          <tr>
            <td width="1%"><span class="style1">*</span></td>
            <td width="29%">No. Perserahan</td>
            <td width="70%">: 
            <input name="txtNoPerserahan" type="text" id="txtNoPerserahan" value="$txtNoPerserahan" $readonly class="$inputTextClass" onblur="this.value=this.value.toUpperCase();"/></td>
          </tr>
          <tr>
            <td width="1%"><span class="style1">*</span></td>
            <td>Jilid</td>
            <td>: 
            <input name="txtJilid" type="text" id="txtJilid" value="$txtJilid" $readonly class="$inputTextClass" onblur="this.value=this.value.toUpperCase();"/></td>
          </tr>
          <tr>
            <td width="1%"><span class="style1">*</span></td>
            <td>Folio</td>
            <td>: 
            <input name="txtFolio" type="text" id="txtFolio" value="$txtFolio" $readonly class="$inputTextClass" onblur="this.value=this.value.toUpperCase();"/></td>
          </tr>
          <tr>
            <td width="1%"><span class="style1">*</span></td>
            <td>Urusan</td>
            <td>: $selectJenisBebanan</td>
          </tr>
          <tr>
            <td width="1%"><span class="style1">*</span></td>
            <td>Tarikh Daftar</td>
            <td>: <input name="txdTarikhDaftar" type="text" id="txdTarikhDaftar" value="$txdTarikhDaftar" $readonly class="$inputTextClass" size="9" onBlur="check_date(this)"/>
              <a href="javascript:displayDatePicker('txdTarikhDaftar',false,'dmy');"><img src="../img/calendar.gif" border="0"/></a></td>
          </tr>
          <tr>
            <td width="1%"><span class="style1">*</span></td>
            <td>Kod PB</td>
            <td>: $selectJenisPB</td>
          </tr>
          <tr>
            <td width="1%"><span class="style1">*</span></td>
            <td>No PB</td>
            <td>: 
            <input name="txtNoPB" type="text" value="$txtNoPB" $readonly class="$inputTextClass" onblur="this.value=this.value.toUpperCase();"/></td>
          </tr>
          <tr>
            <td width="1%"><span class="style1">*</span></td>
            <td>Nama PB</td>
            <td>: 
            <input name="txtNamaPb" type="text" id="txtNamaPb" value="$txtNamaPb" size="45" $readonly class="$inputTextClass" onblur="this.value=this.value.toUpperCase();"/></td>
          </tr>
          <tr>
            <td width="1%"><span class="style1">*</span></td>
            <td>Alamat</td>
            <td>: 
            <input name="txtAlamat1" type="text" id="txtAlamat" value="$txtAlamat1" size="45" $readonly class="$inputTextClass" onblur="this.value=this.value.toUpperCase();"/></td>
          </tr>
          <tr>
            <td width="1%">&nbsp;</td>
            <td>&nbsp;</td>
            <td>: 
            <input name="txtAlamat2" type="text" id="txtAlamat2" value="$txtAlamat2" size="45" $readonly class="$inputTextClass" onblur="this.value=this.value.toUpperCase();"/></td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>: 
            <input name="txtAlamat3" type="text" id="txtAlamat3" value="$txtAlamat3" size="45" $readonly class="$inputTextClass" onblur="this.value=this.value.toUpperCase();"/></td>
          </tr>
          <tr>
            <td width="1%"><span class="style1">*</span></td>
            <td>Poskod</td>
            <td>: 
            <input name="txtPoskod" type="text" class="$inputTextClass" value="$txtPoskod" size="5" maxlength="5" $readonly onkeyup="validateNumber(this,this.value,'$txtPoskod')"/></td>
          </tr>
          <tr>
            <td width="1%"><span class="style1">*</span></td>
            <td>Negeri</td>
            <td>: $selectNegeriBebanan</td>
          </tr>
          <tr>
            <td width="1%"><span class="style1">*</span></td>
            <td>Daerah</td>
            <td>: $selectDaerahBebanan</td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
          <tr>
          <td colspan="3"><em><span class="style1">Perhatian </span>: Pastikan label bertanda <span class="style1">*</span> diisi.</em></td>
        </tr>
          <tr>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>
            
            #if ($mode == 'newBebanan')
              <input type="button" name="btnAdd2" id="btnAdd2" value="Simpan" onClick="simpanBebanan()" /> 
              <input name="cmdBatal" type="button" value="Batal" onClick="batal()"/> 
            #end
            #if ($mode == 'viewBebanan')
            <input type="button" name="btnUpdate2" id="btnUpdate2" value="Kemaskini" onclick="kemaskiniBebanan()" />
            <input name="cmdBatal" type="button" value="Kembali" onClick="batal()"/> 
            #end 
            #if ($mode == 'updateBebanan')
            <input id="cmdUpdate" name="cmdUpdate" type="button" value="Simpan" onclick="simpanUpdateBebanan()"/>
            <input id="cmdBatal" name="cmdBatal" type="button" value="Batal" onclick="batalUpdateBebanan()"/>
            #end 
    
            </td>
          </tr>
        </table>

#end

<div align="center">	 
    #if ($mode == "")
   <input type="button" name="btnExit2" id="btnExit2" value="Kembali" onClick="kembali()" />
   #end
</div>

</fieldset>
</fieldset>

<script>
function doChangeState(){
	document.${formName}.submit();
}
function doChangeDaerah(){
	document.${formName}.submit();
}
function kembali() {
	document.${formName}.actionPerletakhakan.value = "paparPemilik";
	document.${formName}.mode.value = "viewPemilik";
	document.${formName}.submit();
}
function tambahBebanan(){
	document.${formName}.mode.value = "newBebanan";
	document.${formName}.submit();
}
function batal(){
	document.${formName}.actionPerletakhakan.value = "paparBebanan";
	document.${formName}.mode.value = "";
	document.${formName}.submit();
}
function kemaskiniHakmilik(){
	document.${formName}.mode.value ="updateBebanan";
	document.${formName}.submit();
}
function simpanBebanan(){
	
	if(document.${formName}.txtNoPerserahan.value == ""){
		alert('Sila pilih "No Perserahan" terlebih dahulu.');
		document.${formName}.txtNoPerserahan.focus();
		return;
	}
	if(document.${formName}.txtJilid.value == ""){
		alert('Sila pilih "Jilid" terlebih dahulu.');
		document.${formName}.txtJilid.focus();
		return;
	}
	if(document.${formName}.txtFolio.value == ""){
		alert('Sila pilih "Folio" terlebih dahulu.');
		document.${formName}.txtFolio.focus();
		return;
	}
	if(document.${formName}.socJenisBebanan.value == ""){
		alert('Sila pilih "Urusan" terlebih dahulu.');
		document.${formName}.socJenisBebanan.focus();
		return;
	}
	if(document.${formName}.txdTarikhDaftar.value == ""){
		alert('Sila pilih "Tarikh Daftar" terlebih dahulu.');
		document.${formName}.txdTarikhDaftar.focus();
		return;
	}
	if(document.${formName}.socJenisPB.value == ""){
		alert('Sila pilih "Jenis No PB" terlebih dahulu.');
		document.${formName}.socJenisPB.focus();
		return;
	}
	if(document.${formName}.txtNoPB.value == ""){
		alert('Sila pilih "No PB" terlebih dahulu.');
		document.${formName}.txtNoPB.focus();
		return;
	}
	
	if(document.${formName}.txtNamaPb.value == ""){
		alert('Sila pilih "Nama PB" terlebih dahulu.');
		document.${formName}.txtNamaPb.focus();
		return;
	}
	if(document.${formName}.txtAlamat.value == ""){
		alert('Sila pilih "Alamat" terlebih dahulu.');
		document.${formName}.txtAlamat.focus();
		return;
	}
	if(document.${formName}.txtPoskod.value == ""){
		alert('Sila pilih "Poskod" terlebih dahulu.');
		document.${formName}.txtPoskod.focus();
		return;
	}
	if(document.${formName}.socNegeriBebanan.value == ""){
		alert('Sila pilih "Negeri" terlebih dahulu.');
		document.${formName}.socNegeriBebanan.focus();
		return;
	}
	if(document.${formName}.socDaerahBebanan.value == ""){
		alert('Sila pilih "Daerah" terlebih dahulu.');
		document.${formName}.socDaerahBebanan.focus();
		return;
	}
	
	document.${formName}.mode.value ="viewBebanan";
	document.${formName}.hitButton.value ="simpanBebanan";
	document.${formName}.submit();   
}
function paparBebanan(id){	
	document.${formName}.mode.value ="viewBebanan";
	document.${formName}.idBebanan.value = id;
	document.${formName}.submit();
}
function kemaskiniBebanan(){
	document.${formName}.mode.value ="updateBebanan";
	document.${formName}.submit();
}
function simpanUpdateBebanan(){
	if(document.${formName}.txtNoPerserahan.value == ""){
		alert('Sila pilih "No Perserahan" terlebih dahulu.');
		document.${formName}.txtNoPerserahan.focus();
		return;
	}
	if(document.${formName}.txtJilid.value == ""){
		alert('Sila pilih "Jilid" terlebih dahulu.');
		document.${formName}.txtJilid.focus();
		return;
	}
	if(document.${formName}.txtFolio.value == ""){
		alert('Sila pilih "Folio" terlebih dahulu.');
		document.${formName}.txtFolio.focus();
		return;
	}
	if(document.${formName}.socJenisBebanan.value == ""){
		alert('Sila pilih "Urusan" terlebih dahulu.');
		document.${formName}.socJenisBebanan.focus();
		return;
	}
	if(document.${formName}.txdTarikhDaftar.value == ""){
		alert('Sila pilih "Tarikh Daftar" terlebih dahulu.');
		document.${formName}.txdTarikhDaftar.focus();
		return;
	}
	if(document.${formName}.socJenisPB.value == ""){
		alert('Sila pilih "Jenis No PB" terlebih dahulu.');
		document.${formName}.socJenisPB.focus();
		return;
	}
	if(document.${formName}.txtNoPB.value == ""){
		alert('Sila pilih "No PB" terlebih dahulu.');
		document.${formName}.txtNoPB.focus();
		return;
	}
	
	if(document.${formName}.txtNamaPb.value == ""){
		alert('Sila pilih "Nama PB" terlebih dahulu.');
		document.${formName}.txtNamaPb.focus();
		return;
	}
	if(document.${formName}.txtAlamat.value == ""){
		alert('Sila pilih "Alamat" terlebih dahulu.');
		document.${formName}.txtAlamat.focus();
		return;
	}
	if(document.${formName}.txtPoskod.value == ""){
		alert('Sila pilih "Poskod" terlebih dahulu.');
		document.${formName}.txtPoskod.focus();
		return;
	}
	if(document.${formName}.socNegeriBebanan.value == ""){
		alert('Sila pilih "Negeri" terlebih dahulu.');
		document.${formName}.socNegeriBebanan.focus();
		return;
	}
	if(document.${formName}.socDaerahBebanan.value == ""){
		alert('Sila pilih "Daerah" terlebih dahulu.');
		document.${formName}.socDaerahBebanan.focus();
		return;
	}
	
	document.${formName}.mode.value ="viewBebanan";
	document.${formName}.hitButton.value ="simpanUpdateBebanan";
	document.${formName}.submit();   
} 
function batalUpdateBebanan(){
	document.${formName}.actionPerletakhakan.value = "paparBebanan";
	document.${formName}.mode.value = "viewBebanan";
	document.${formName}.submit();
}
function bebanan(){
	document.${formName}.actionPerletakhakan.value ="paparBebanan";
	document.${formName}.mode.value ="";
	document.${formName}.submit();
}
function validateNumber(elmnt,content,content2) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = content2;
		return;
	}
}
</script>
	    