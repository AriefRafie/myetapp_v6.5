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

<fieldset><legend>HAKMILIK PERLETAKHAKAN</legend>

#parse("app/htp/frmPerletakhakanHak.jsp") 
<fieldset>
<legend>SENARAI PEMILIK ASAL</legend>
#if ($mode == "")
  <input type="button" name="btnAdd" id="btnAdd" value="Tambah" onclick="tambahPemilik()" />
  #end

<table width="100%" border="0">
  <tr class="table_header">
    <td width="5%">Bil</td>
    <td width="55%">Nama Pemilik Asal</td>
    <td width="40%">No. Pengenalan</td>
  </tr>
  
  #if ($Listpemilik.size() > 0)
  #foreach ($list in $Listpemilik)
   #set( $i = $velocityCount )
   #if ( ($i % 2) != 1 ) 
   #set( $row = "row2" ) 
   #else 
   #set( $row = "row1" ) 
   #end
  <tr>
    <td class="$row">$list.bil </td>
    <td class="$row"><a href="javascript:paparPemilik('$list.idPihakberkepentingan')"><font color="blue">$list.namaPemilik</font></a></td>
    <td class="$row">$list.noPengenalan</td>
  </tr>
  #end
  #else
  <tr class="row1">
  	<td>&nbsp;</td>
    <td>Tiada Rekod</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  #end
</table>
</fieldset>
#if($mode == "newPemilik" || $mode == "updatePemilik" || $mode == "viewPemilik")
<fieldset>
      <legend>MAKLUMAT PEMILIK ASAL</legend>
      <table width="100%" border="0">
        <tr>
          <td width="29%"><span class="style1">* </span>Nama</td>
          <td width="1%">:</td>
          <td width="70%">
            <input name="txtNama" type="text" class="$inputTextClass" id="txtNama" onBlur="this.value=this.value.toUpperCase();" value="$txtNama" size="35"  $readonly/>          </td>
        </tr>
         <tr>
          <td ><span class="style1">* </span>No. Pengenalan</td>
          <td >:</td>
          <td >
            <input name="txtNoPengenalan" type="text" class="$inputTextClass" id="txtNoPengenalan"onBlur="this.value=this.value.toUpperCase();" value="$txtNoPengenalan" size="35"  $readonly/>          </td>
        </tr>
        <tr>
          <td><span class="style1">* </span>Alamat</td>
          <td>:</td>
          <td>
         <input name="txtAlamat1" type="text" class="$inputTextClass"  id="txtAlamat1" onBlur="this.value=this.value.toUpperCase();" value="$txtAlamat1" size="35"  $readonly/>          </td>
        </tr>
        <tr>
          <td></td>
          <td>:</td>
          <td>
          <input name="txtAlamat2" type="text" class="$inputTextClass" id="txtAlamat2" onBlur="this.value=this.value.toUpperCase();" value="$txtAlamat2" size="35"  $readonly/>          
          </td>
        </tr>
        <tr>
          <td></td>
          <td>:</td>
          <td>
          <input name="txtAlamat3" type="text" class="$inputTextClass" id="txtAlamat3" onBlur="this.value=this.value.toUpperCase();" value="$txtAlamat3" size="35"  $readonly/>          </td>
        </tr>
        <tr>
          <td><span class="style1">* </span>Poskod</td>
          <td>:</td>
          <td>
          <input name="txtPoskod" type="text" class="$inputTextClass" id="txtPoskod" value="$txtPoskod" size="5" maxlength="5"  $readonly onkeyup="validateNumber(this,this.value,'$txtPoskod')"/>          </td>
        </tr>
        <tr>
          <td><span class="style1">* </span>Negeri</td>
          <td>:</td>
          <td>$selectNegeriPemilik</td>
        </tr>
        <tr>
          <td><span class="style1">* </span>Daerah</td>
          <td>:</td>
          <td>$selectDaerahPemilik</td>
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
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>
          
          	#if ($mode == 'newPemilik')
              <input type="button" name="btnAdd2" id="btnAdd2" value="Simpan" onClick="simpanPemilik()" /> 
              <input name="cmdBatal" type="button" value="Batal" onClick="batal()"/> 
            #end
            #if ($mode == 'viewPemilik')
            <input type="button" name="btnUpdate2" id="btnUpdate2" value="Kemaskini" onclick="kemaskiniPemilik()" />
            <input name="cmdBatal" type="button" value="Kembali" onClick="batal()"/> 
            <input name="cmdSeterusnya" type="button" value="Maklumat Bebanan" onClick="bebanan()"/> 
            #end 
            #if ($mode == 'updatePemilik')
            <input id="cmdUpdate" name="cmdUpdate" type="button" value="Simpan" onclick="simpanUpdatePemilik()"/>
            <input id="cmdBatal" name="cmdBatal" type="button" value="Batal" onclick="batalUpdatePemilik()"/>
            #end  
            
          </td>
        </tr>       
      </table>
      #end
</fieldset>
#if ($mode == "")
    <table width="100%" border="0" cellpadding="2">
      <tr>
        <td align="center"><input type="button" name="btnExit2" id="btnExit2" value="Kembali" onClick="kembali()" /></td>    
      </tr>
    </table>
#end    
</fieldset>

<script>
function doChangeState(){
	document.${formName}.submit();
}
function doChangeDaerah(){
	document.${formName}.submit();
}
function kembali() {
	document.${formName}.actionPerletakhakan.value = "papar";
	document.${formName}.mode.value = "viewHakmilik";
	document.${formName}.submit();
}
function tambahPemilik(){
	document.${formName}.mode.value = "newPemilik";
	document.${formName}.submit();
}
function batal(){
	document.${formName}.actionPerletakhakan.value = "paparPemilik";
	document.${formName}.mode.value = "";
	document.${formName}.submit();
}
function kemaskiniHakmilik(){
	document.${formName}.mode.value ="updatePemilik";
	document.${formName}.submit();
}
function simpanPemilik(){
	
	if(document.${formName}.txtNama.value == ""){
		alert('Sila pilih "Nama" terlebih dahulu.');
		document.${formName}.txtNama.focus();
		return;
	}
	if(document.${formName}.txtNoPengenalan.value == ""){
		alert('Sila pilih "No. Pengenalan" terlebih dahulu.');
		document.${formName}.txtNoPengenalan.focus();
		return;
	}
	if(document.${formName}.txtAlamat1.value == ""){
		alert('Sila pilih "Alamat" terlebih dahulu.');
		document.${formName}.txtAlamat1.focus();
		return;
	}
	if(document.${formName}.txtPoskod.value == ""){
		alert('Sila pilih "Poskod" terlebih dahulu.');
		document.${formName}.txtPoskod.focus();
		return;
	}
	if(document.${formName}.socNegeriPemilik.value == ""){
		alert('Sila pilih "Negeri" terlebih dahulu.');
		document.${formName}.socNegeriPemilik.focus();
		return;
	}
	if(document.${formName}.socDaerahPemilik.value == ""){
		alert('Sila pilih "Daerah" terlebih dahulu.');
		document.${formName}.socDaerahPemilik.focus();
		return;
	}
	
	document.${formName}.mode.value ="viewPemilik";
	document.${formName}.hitButton.value ="simpanPemilik";
	document.${formName}.submit();   
}
function paparPemilik(id){	
	document.${formName}.mode.value ="viewPemilik";
	document.${formName}.idPihakberkepentingan.value = id;
	document.${formName}.submit();
}
function kemaskiniPemilik(){
	document.${formName}.mode.value ="updatePemilik";
	document.${formName}.submit();
}
function simpanUpdatePemilik(){
	if(document.${formName}.txtNama.value == ""){
		alert('Sila pilih "Nama" terlebih dahulu.');
		document.${formName}.txtNama.focus();
		return;
	}
	if(document.${formName}.txtNoPengenalan.value == ""){
		alert('Sila pilih "No. Pengenalan" terlebih dahulu.');
		document.${formName}.txtNoPengenalan.focus();
		return;
	}
	if(document.${formName}.txtAlamat1.value == ""){
		alert('Sila pilih "Alamat" terlebih dahulu.');
		document.${formName}.txtAlamat1.focus();
		return;
	}
	if(document.${formName}.txtPoskod.value == ""){
		alert('Sila pilih "Poskod" terlebih dahulu.');
		document.${formName}.txtPoskod.focus();
		return;
	}
	if(document.${formName}.socNegeriPemilik.value == ""){
		alert('Sila pilih "Negeri" terlebih dahulu.');
		document.${formName}.socNegeriPemilik.focus();
		return;
	}
	if(document.${formName}.socDaerahPemilik.value == ""){
		alert('Sila pilih "Daerah" terlebih dahulu.');
		document.${formName}.socDaerahPemilik.focus();
		return;
	}
	
	document.${formName}.mode.value ="viewPemilik";
	document.${formName}.hitButton.value ="simpanUpdatePemilik";
	document.${formName}.submit();   
} 
function batalUpdatePemilik(){
	document.${formName}.actionPerletakhakan.value = "paparPemilik";
	document.${formName}.mode.value = "viewPemilik";
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
      
