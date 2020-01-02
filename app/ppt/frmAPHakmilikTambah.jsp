<br/>
#foreach($data in $dataMaklumatTanah)
	#set($idPermohonan=$data.id_permohonan)
	#set($idTanah=$data.id_hakmilik)
	#set($noHakmilik=$data.no_hakmilik)
	#set($noLot=$data.no_lot)
	#set($noPT=$data.no_pt)
	#set($luas=$data.luas_lot)
	#set($luasAmbil=$data.luas_ambil)
	#set($catatan=$data.catatan)
#end



#if($semakTanah=="no")

<!-- SEKSYEN 4 -->
#if($id_suburusan=="51")
<fieldset>
<legend><strong>Maklumat Tanah</strong></legend>
    <table width="100%"  cellpadding="0" border="0">
    	
    	<tr>
            <td width="20%">Negeri</td>
            <td width="1%">:</td>
            <td width="79%"><input type="text" name="existNegeri" value="$!existNegeri" size="42" class=disabled readonly>
                <input type="hidden" name="id_existNegeri" value="$id_existNegeri"></td>
        </tr>
            
        <tr>
            <td>Jajahan/Daerah</td>
            <td>:</td>
            <td><input type="text" name="existDaerah" value="$!existDaerah" size="42" class=disabled readonly>
                <input type="hidden" name="id_existDaerah" value="$id_existDaerah"></td>
        </tr>
            
        <tr>
            <td><font color="red">*</font>Bandar/Pekan/Mukim</td>
            <td>:</td>
            <td>$!SelectMukim</td>
        </tr>
    	
    	<tr>
            <td valign="top"><font color=red>*</font>Catatan</td>
            <td valign="top">:</td>
            <td><textarea name="txtCatatan" id="txtCatatan" cols="90%" rows="15" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" onKeyUp="textCounter(this.form.txtCatatan,this.form.remLen1,4000);" onKeyDown="textCounter(this.form.txtCatatan,this.form.remLen1,4000);" ></textarea></td>
        </tr>
        
        <tr>
        	<td>&nbsp;</td>
            <td>&nbsp;</td>
            <td valign="top">Baki Aksara :&nbsp;<input type="text" readonly class="disabled" name="remLen1" size="3" maxlength="3" value="4000"></td>
        </tr> 
    	
	</table>
</fieldset>


<!-- SEKSYEN 8 & SEMENTARA -->
#else
<fieldset>
	<legend><strong>Maklumat Tanah</strong></legend>
    	<table width="100%"  cellpadding="0" border="0">
        
        	<tr>
        		<td width="30%"><font color="red">*</font>Jenis Hakmilik</td>
        		<td width="1%">:</td>
                <td width="69%">$!SelectJenisHakmilik</td>
            </tr>
            
            <tr>
            	<td>No.Hakmilik</td>
            	<td>:</td>
                <td><input type="text" size="26" name="no_hakmilik" maxlength="50" value="" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase();" id="no_hakmilik" /></td>
            </tr>
            
            <tr>
            	<td><font color="red">*</font>Kod Lot</td>
            	<td>:</td>
            	<td>$!SelectLot</td>
            </tr>
            
            <tr>
            	<td><font color="red">*</font>No.Lot / No.PT</td>
            	<td>:</td>
            	<td><input type="text" name="no_lot" value="" maxlength="20"  style="text-transform:uppercase;" size="26" onblur="this.value=this.value.toUpperCase();" id="no_lot" /></td>
            </tr>
        
            <tr>
            	<td>Negeri</td>
            	<td>:</td>
                <td><input type="text" name="existNegeri" value="$existNegeri" size="42" class=disabled readonly>
                	<input type="hidden" name="id_existNegeri" value="$id_existNegeri"></td>
            </tr>
            
            <tr>
            	<td>Jajahan/Daerah</td>
            	<td>:</td>
                <td><input type="text" name="existDaerah" value="$existDaerah" size="42" class=disabled readonly>
                <input type="hidden" name="id_existDaerah" value="$id_existDaerah"></td>
            </tr>
            
            <tr>
            	<td><font color="red">*</font>Bandar/Pekan/Mukim</td>
            	<td>:</td>
                <td>$!SelectMukim</td>
            </tr>
            
            <tr>
            	<td><font color="red">*</font>Unit Luas</td>
            	<td>:</td>
                <td>$!SelectLuas</td>
            </tr>
            
            <tr>
            	<td>Luas Lot</td>
            	<td>:</td>
                <td><input type="text" name="luas_lot" value="" maxlength="20" onkeyup="validate(this,this.value);" id="luas_lot" size="5" /></td>
            </tr>
            
            <tr>
            	<td>Anggaran luas yang hendak diambil</td>
            	<td>:</td>
                <td><input type="text" name="anggaran_luas" maxlength="10" value="" onkeyup="validate(this,this.value);" id="luas_pt" size="5" /></td>
            </tr>
            
        </table>
    </fieldset>
#end    
    
#end




<!-- View n Edit -->
#if($semakTanah=="yes")

<fieldset id="changeTanah">
	<legend><strong>Maklumat Tanah</strong></legend>
    	<table width="100%"  cellpadding="1" border="0">
        
        #if($wantedit=="no")
        
        #if($id_suburusan=="51")
        
        	<tr>
            	<td width="20%">Negeri</td>
            	<td width="1%">:</td>
                <td width="79%"><input type="text" name="existNegeri" value="$existNegeri" size="42" class=disabled readonly>
                	<input type="hidden" name="id_existNegeri" value="$id_existNegeri"></td>
            </tr>
            
            <tr>
            	<td>Jajahan/Daerah</td>
            	<td>:</td>
                <td><input type="text" name="existDaerah" value="$existDaerah" size="42" class=disabled readonly>
                <input type="hidden" name="id_existDaerah" value="$id_existDaerah"></td>
            </tr>
            
            <tr>
            	<td>Bandar/Pekan/Mukim</td>
            	<td>:</td>
                <td>$!selectMukim</td>
            </tr>
            
            <tr>
            	<td valign="top">Catatan</td>
           	 	<td valign="top">:</td>
           		<td><textarea name="txtCatatan" id="txtCatatan" class="disabled" readonly cols="90%" rows="15" style="text-transform:uppercase;" >$!catatan</textarea></td>
        	</tr>
            
        
        #else
        
        	<tr>
            	<td width="30%">Jenis Hakmilik</td>
            	<td width="1%">:</td>
                <td width="69%">$!selectJenisHakmilik</td>
            </tr>
            
            <tr>
            	<td>No.Hakmilik</td>
            	<td>:</td>
                <td><input type="text" name="no_hakmilik" maxlength="50" size="27" value="$noHakmilik" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase();" id="no_hakmilik" class=disabled readonly /></td>
            </tr>
            
            <tr>
            	<td>Kod Lot</td>
            	<td>:</td>
            	<td>$!selectLot</td>
            </tr>
            
            <tr>
            	<td>No.Lot/No.PT</td>
            	<td>:</td>
            	<td><input type="text" name="no_lot" value="$noLot" maxlength="20"  class=disabled readonly style="text-transform:uppercase;" size="27" onblur="this.value=this.value.toUpperCase();" id="no_lot" /></td>
            </tr>
            
            <tr>
            	<td>Negeri</td>
            	<td>:</td>
                <td><input type="text" name="existNegeri" value="$existNegeri" size="42" class=disabled readonly>
                	<input type="hidden" name="id_existNegeri" value="$id_existNegeri"></td>
            </tr>
            
            <tr>
            	<td>Jajahan/Daerah</td>
            	<td>:</td>
                <td><input type="text" name="existDaerah" value="$existDaerah" size="42" class=disabled readonly>
                <input type="hidden" name="id_existDaerah" value="$id_existDaerah"></td>
            </tr>
            
            <tr>
            	<td>Bandar/Pekan/Mukim</td>
            	<td>:</td>
                <td>$!selectMukim</td>
            </tr>
            
            <tr>
            	<td>Unit Luas</td>
            	<td>:</td>
                <td>$!selectLuas</td>
            </tr>
            
            <tr>
            	<td>Luas Lot</td>
            	<td>:</td>
                <td><input type="text" name="luas_lot" value="$luas" maxlength="20" id="luas_lot" onkeyup="validate(this,this.value);" size="5" class=disabled readonly /></td>
            </tr>
            
            <tr>
            	<td>Anggaran luas yang hendak diambil</td>
            	<td>:</td>
                <td><input type="text" name="anggaran_luas" maxlength="10" value="$luasAmbil" onkeyup="validate(this,this.value);" id="luas_pt" size="5" class=disabled readonly /></td>
            </tr>
        #end    
        #end    
        
   		#if($wantedit=="yes")
        
        #if($id_suburusan=="51")
        
        	<tr>
            	<td width="20%">Negeri</td>
            	<td width="1%">:</td>
                <td width="79%"><input type="text" name="existNegeri" value="$existNegeri" size="42" class="disabled" readonly>
                	<input type="hidden" name="id_existNegeri" value="$id_existNegeri"></td>
             </tr>
            
            <tr>
            	<td>Jajahan/Daerah</td>
            	<td>:</td>
                <td><input type="text" name="existDaerah" value="$existDaerah" size="42" class="disabled" readonly>
                <input type="hidden" name="id_existDaerah" value="$id_existDaerah"></td>
            </tr>
            
            <tr>
            	<td><font color="red">*</font>Bandar/Pekan/Mukim</td>
            	<td>:</td>
                <td>$!selectMukim</td>
            </tr>
        
        	<tr>
            	<td valign="top"><font color=red>*</font>Catatan</td>
            	<td valign="top">:</td>
            	<td><textarea name="txtCatatan" id="txtCatatan" cols="90%" rows="15" style="text-transform:uppercase;" onBlur="this.value=this.value.toUpperCase();" onKeyUp="textCounter(this.form.txtCatatan,this.form.remLen1,4000);" onKeyDown="textCounter(this.form.txtCatatan,this.form.remLen1,4000);" >$!catatan</textarea></td>
       		</tr>
        
       	 	<tr>
        		<td>&nbsp;</td>
            	<td>&nbsp;</td>
            	<td valign="top">Baki Aksara :&nbsp;<input type="text" readonly class="disabled" name="remLen1" size="3" maxlength="3" value="4000"></td>
        	</tr> 
        
        #else
        
        	<tr>
        		<td width="30%"><font color="red">*</font>Jenis Hakmilik</td>
        		<td width="1%">:</td>
                <td width="69%">$!selectJenisHakmilik</td>
            </tr>
            
            <tr>
            	<td>No.Hakmilik</td>
            	<td>:</td>
                <td><input type="text" name="edit_no_hakmilik" size="26" maxlength="50" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase();" value="$noHakmilik" id="no_hakmilik" /></td>
            </tr>
            
            <tr>
            	<td><font color="red">*</font>Kod Lot</td>
            	<td>:</td>
            	<td>$!selectLot</td>
            </tr>
            
            <tr>
            	<td><font color="red">*</font>No.Lot / No.PT</td>
            	<td>:</td>
            	<td><input type="text" size="26" name="edit_no_lot" maxlength="20" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase();" value="$noLot" id="no_lot" /></td>
            </tr>
            
             <tr>
            	<td>Negeri</td>
            	<td>:</td>
                <td><input type="text" name="existNegeri" value="$existNegeri" size="42" class="disabled" readonly>
                	<input type="hidden" name="id_existNegeri" value="$id_existNegeri"></td>
             </tr>
            
            <tr>
            	<td>Jajahan/Daerah</td>
            	<td>:</td>
                <td><input type="text" name="existDaerah" value="$existDaerah" size="42" class="disabled" readonly>
                <input type="hidden" name="id_existDaerah" value="$id_existDaerah"></td>
            </tr>
            
            <tr>
            	<td><font color="red">*</font>Bandar/Pekan/Mukim</td>
            	<td>:</td>
                <td>$!selectMukim</td>
            </tr>
            
            <tr>
            	<td><font color="red">*</font>Unit Luas</td>
            	<td>:</td>
                <td>$!selectLuas</td>
            </tr>
            
            <tr>
            	<td>Luas Lot</td>
            	<td>:</td>
                <td><input type="text" name="edit_luas_lot" maxlength="20" value="$luas" onkeyup="validate(this,this.value);" id="luas_lot" size="5" /></td>
            </tr>
            
            <tr>
            	<td>Anggaran luas yang hendak diambil</td>
            	<td>:</td>
                <td><input type="text" name="edit_anggaran_luas" maxlength="10" value="$luasAmbil" id="luas_pt" onkeyup="validate(this,this.value);" size="5" /></td>
            </tr>
            
        #end    
        #end  
          
        </table>
    </fieldset>
#end

	<table width="100%"  cellpadding="0" border="0">
    	<tr align="center">
        	<td>
        	#if($semakTanah=="yes")
        		#if($wantedit=="yes")
        		<input name="cmdUpdate" type="button" value="Simpan" onClick="update_maklumat('$idTanah','$id_suburusan')">
        		<input name="cmdBatal" type="button" value="Batal" onClick="edit_maklumat('$idTanah')">
        		<input type="submit" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="kembali('$id_permohonan')">
                #end
        		#if($wantedit=="no")
        			#if($currentStatus=="113")
        		<input name="cmdKemaskini" type="button" value="Kemaskini" onClick="kemaskiniTanah('$id_suburusan')">
        		<input name="cmdHapus" type="button" value="Hapus" onClick="delete_maklumatTanah('$idTanah')">
        			#end
        		<input type="submit" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="kembali('$id_permohonan')">
                #end
        	#end
        		
        		#if($semakTanah=="no")
                <input name="cmdSimpan" type="button" value="Simpan" onclick="add_maklumat_tanah('$id_permohonan','$id_suburusan')" >
                <input type="submit" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="kembali('$id_permohonan')">
                #end
                
              
       	    </td>
        </tr>
    </table>
<input type="hidden" name="id_permohonan" value="$id_permohonan">  
<input type="hidden" name="id_hakmilik" value="$idTanah">
<input type="hidden" name="id_suburusan" value="$id_suburusan">

<!-- Anchor -->
<input type="hidden" name="ScreenLocation" value="$ScreenLocation">
<input type="hidden" name="CursorPoint" value="$CursorPoint">

<!-- do post -->
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>

<script>
window.onload = submitForm;

function add_maklumat_tanah(id_permohonan,id_suburusan)
{   

if(id_suburusan=="51"){

	if(document.${formName}.mukim.value == ""){
		alert("Sila pilih \"Bandar/Pekan/Mukim\" terlebih dahulu.");
  		document.${formName}.mukim.focus(); 
		return;
	}
	if(document.${formName}.txtCatatan.value == ""){
		alert("Sila masukkan \"Catatan\" terlebih dahulu.");
  		document.${formName}.txtCatatan.focus(); 
		return;
	}
	
}else{

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
	if(document.${formName}.mukim.value == ""){
		alert("Sila pilih \"Bandar/Pekan/Mukim\" terlebih dahulu.");
  		document.${formName}.mukim.focus(); 
		return;
	}
	if(document.${formName}.luas.value == "" || document.${formName}.luas.value == "0" || document.${formName}.luas.value == "00"){
		alert("Sila pilih \"Unit luas\" terlebih dahulu.");
  		document.${formName}.luas.focus(); 
		return;
	}
	
}

	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.id_suburusan.value = id_suburusan;
	document.${formName}.command.value = "addMaklumatTanah";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmAPDaftarSenarai";
	document.${formName}.submit();
	
}
function kemaskiniTanah(id_suburusan) {

	document.${formName}.ScreenLocation.value = "changeTanah";

	if(id_suburusan=="51"){
		document.${formName}.CursorPoint.value = "editMukim";
	}else{
		document.${formName}.CursorPoint.value = "editJenisHakmilik";	
	}

	document.${formName}.command.value = "kemaskiniTanah";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmAPDaftarSenarai"; 
	document.${formName}.submit();
}
function edit_maklumat(id_hakmilik) {
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.id_hakmilik.value = id_hakmilik;
	document.${formName}.command.value = "edit_maklumat";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmAPDaftarSenarai";
	document.${formName}.submit();
}
function kembali(id_permohonan) {
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "semak";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmAPDaftarSenarai";
	document.${formName}.submit();
}
function update_maklumat(id_hakmilik,id_suburusan) {

	document.${formName}.ScreenLocation.value = "changeTanah";

if(id_suburusan=="51"){

	if(document.${formName}.editMukim.value == ""){
		alert("Sila pilih \"Bandar/Pekan/Mukim\" terlebih dahulu.");
  		document.${formName}.editMukim.focus(); 
		return;
	}
	if(document.${formName}.txtCatatan.value == ""){
		alert("Sila masukkan \"Catatan\" terlebih dahulu.");
	  	document.${formName}.txtCatatan.focus(); 
		return;
	}
		
}else{
		
	Editluas = parseInt(document.${formName}.edit_luas_lot.value);
	EditluasAmbil = parseInt(document.${formName}.edit_anggaran_luas.value);

	if (EditluasAmbil > Editluas) {
		alert ("Anggaran luas melebihi luas lot");
		document.${formName}.edit_anggaran_luas.focus();
		return;
	}
	if(document.${formName}.editJenisHakmilik.value == ""){
		alert("Sila pilih \"Jenis hakmilik\" terlebih dahulu.");
  		document.${formName}.editJenisHakmilik.focus(); 
		return;
	}
	if(document.${formName}.editLot.value == ""){
		alert("Sila pilih \"Kod Lot\" terlebih dahulu.");
  		document.${formName}.editLot.focus(); 
		return;
	}
	if(document.${formName}.edit_no_lot.value == ""){
		alert("Sila masukkan \"No.Lot / No.PT\" terlebih dahulu.");
  		document.${formName}.edit_no_lot.focus(); 
		return;
	}
	if(document.${formName}.editMukim.value == ""){
		alert("Sila pilih \"Bandar/Pekan/Mukim\" terlebih dahulu.");
  		document.${formName}.editMukim.focus(); 
		return;
	}
	if(document.${formName}.editLuas.value == ""){
		alert("Sila pilih \"Unit luas\" terlebih dahulu.");
  		document.${formName}.editLuas.focus(); 
		return;
	}
}
	
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.id_hakmilik.value = id_hakmilik;
	document.${formName}.id_suburusan.value = id_suburusan;
	document.${formName}.command.value = "updateMaklumatTanah";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmAPDaftarSenarai"; 
	document.${formName}.submit();		
}
function delete_maklumatTanah(id_hakmilik) {
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.command.value = "deleteMaklumatTanah";
	document.${formName}.id_hakmilik.value = id_hakmilik;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmAPDaftarSenarai";
	document.${formName}.submit();
}
function validate(elmnt,content) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = RemoveNonNumeric(content);
		return;
	}
}

function RemoveNonNumeric( strString )
{
      var strValidCharacters = "1234567890";
      var strReturn = "";
      var strBuffer = "";
      var intIndex = 0;
      // Loop through the string
      for( intIndex = 0; intIndex < strString.length; intIndex++ )
      {
            strBuffer = strString.substr( intIndex, 1 );
            // Is this a number
            if( strValidCharacters.indexOf( strBuffer ) > -1 )
            {
                  strReturn += strBuffer;
            }
      }
      return strReturn;
}
function textCounter(field, countfield, maxlimit) {
	if (field.value.length > maxlimit) // if too long...trim it!
		field.value = field.value.substring(0, maxlimit);
		// otherwise, update 'characters left' counter
	else 
		countfield.value = maxlimit - field.value.length;
}
function submitForm(){

	if('$ScreenLocation' != ""){
		window.location.hash='$ScreenLocation';
		goTo('$CursorPoint');
	}
}
</script>