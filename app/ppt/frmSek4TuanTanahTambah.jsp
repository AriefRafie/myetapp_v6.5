
<input type="hidden" name="id_fail" value="$id_fail">
<input type="hidden" name="id_permohonan" value="$id_permohonan">
<input type="hidden" name="id_hakmilik" value="$id_hakmilik">
<input type="hidden" name="id_pihakberkepentingan" value="$id_pihakberkepentingan">

    #if($add=="yes")
        <fieldset>
          <legend><strong>Maklumat Tuan Tanah / Pihak Berkepentingan</strong></legend>
         
<br/>
          
          <table width="100%" cellspacing="1" border="0">
          
          <tr>
            <td width="30%"><font color="red">*</font>Nama</td>
            <td width="70%">:&nbsp;<input type="text" size="45px" name="txtNama" id="txtNama" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase();"/></td>
          </tr>
          
          <tr>
            <td width="28%"><font color="red">*</font>Jenis Pihak Berkepentingan</td>
            <td>:&nbsp;$selectJenisPB</td>
		  </tr>
		  
          <tr>
            <td><font color="red">*</font>Kod No. Pihak Berkepentingan</td>
            <td>:&nbsp;$selectJenisNoPB</td>
          </tr>
            
          <tr>
            <td>No. Pihak Berkepentingan</td>
            <td>:&nbsp;<input type="text" size="23px" name="txtNoPB" id="txtNoPB" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase();"/></td>
            </tr>  
           
          <tr>
          	<td><font color="red">*</font>Bahagian</td>
          	<td>:&nbsp;<input type="text" size="5" name="txtSyerAtas" id="txtSyerAtas" style="text-transform:uppercase;" onkeyup="validatetxtSyerAtas(this,this.value);" />
            /&nbsp;<input type="text" size="5" name="txtSyerBawah" id="txtSyerBawah" onkeyup="validatetxtSyerBawah(this,this.value);" /></td>
          </tr>  
          
          <tr>
          	<td><font color="red">*</font>Bangsa</td>
          	<td>:&nbsp;$selectBangsa</td>
          </tr>
            
          <tr>
          	<td><font color="red">*</font>Warganegara</td>
          	<td>:&nbsp;$selectWarganegara</td>
          </tr>  
            
          </table>
        </fieldset>  
<br/>

<fieldset>
   <legend><strong>Alamat Tuan Tanah / Pihak Berkepentingan</strong></legend>
         
<br/>
          <table width="100%" cellspacing="1" border="0">
          		<tr>
          			<td width="30%">Alamat</td>
          			<td width="70%">:&nbsp;<input type="text" maxlength="80" size="40px" name="alamat1PB" id="alamat1PB" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase();"></td>
          		</tr>
          		
          		<tr>
          			<td>&nbsp;</td>
          			<td><font color="white">:</font>&nbsp;<input type="text" maxlength="80" size="40px" name="alamat2PB" id="alamat2PB" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase();"></td>
          		</tr>
          		
          		<tr>
          			<td>&nbsp;</td>
          			<td><font color="white">:</font>&nbsp;<input type="text" maxlength="80" size="40px" name="alamat3PB" id="alamat3PB" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase();"></td>
          		</tr>
          		
          		<tr>
          			<td>Poskod</td>
          			<td>:&nbsp;<input type="text" maxlength="5" size="5px" name="poskodPB" id="poskodPB" onkeyup="validatetxtSyerAtas(this,this.value);" ></td>
          		</tr>
          		
          		<tr>
          			<td>Negeri</td>
          			<td>:&nbsp;$selectNegeri</td>
          		</tr>
          		
          </table>
     <br/>
</fieldset>  
           
    #end 

#if($edit=="yes")
    #foreach ( $senarai in $PermohonanListDetailPB )
        <fieldset>
          <legend><strong>Maklumat Tuan Tanah / Pihak Berkepentingan</strong></legend>
          
          
<br/>
          
          <table width="100%" cellspacing="1" border="0">
          
          <tr>
            <td width="30%">Nama</td>
            <td width="70%">:&nbsp;<input type="text" size="45px" name="txtNama" id="txtNama" value="$senarai.nama_pb" onblur="this.value=this.value.toUpperCase();" style="text-transform:uppercase"/></td>   
          </tr>
          
          <tr>
            <td>Jenis Pihak Berkepentingan</td>
            <td>:&nbsp;$selectEditJenisPB            
            <input type="hidden" name="id_existJenisPB" value="$senarai.id_jenispb" /></td>    
          </tr>
            
          <tr>
            <td>Kod No. Pihak Berkepentingan</td>
            <td>:&nbsp;$selectEditJenisNoPB
            <input type="hidden" name="id_existJenisnopb" value="$senarai.id_jenisnopb" /></td>           
          </tr>
            
          <tr>
            <td>No. Pihak Berkepentingan</td>
            <td>:&nbsp;<input type="text" size="30" name="txtNoPB" id="txtNoPB" value="$senarai.no_pb" onblur="this.value=this.value.toUpperCase();" style="text-transform:uppercase" /></td>
            <td>&nbsp;</td>
            <td></td>
          </tr>  
            
          <tr>
          	<td>Bahagian</td>
            <td>:&nbsp;<input type="text" size="5" name="txtBahagianSyerAtas" id="txtBahagianSyerAtas" value="$senarai.syer_atas" onkeyup="validatetxtSyerAtas(this,this.value);" />
            /&nbsp;<input type="text" size="5" name="txtBahagianSyerBawah" id="txtBahagianSyerBawah" value="$senarai.syer_bawah" onkeyup="validatetxtSyerBawah(this,this.value);" /></td>
          </tr>
          
          <tr>
          	<td>Bangsa</td>
            <td>:&nbsp;$selectEditBangsa
            <input type="hidden" name="id_existBangsa" value="$senarai.id_bangsa" /></td>
          </tr>
          
          <tr>
          	<td>Warganegara</td>
            <td>:&nbsp;$selectEditWarganegara
            <input type="hidden" name="id_existWarganegara" value="$senarai.id_warganegara" /></td>
          </tr>
            
          </table>
       
</fieldset>

<br/>
          
<fieldset>
   <legend><strong>Alamat Tuan Tanah / Pihak Berkepentingan</strong></legend>
         
<br/>
          <table width="100%" cellspacing="1" border="0">
          		<tr>
          			<td width="30%">Alamat</td>
          			<td width="70%">:&nbsp;<input type="text" maxlength="80" value="$senarai.alamat1" size="40px" name="alamat1PB" id="alamat1PB" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase();"></td>
          		</tr>
          		
          		<tr>
          			<td>&nbsp;</td>
          			<td><font color="white">:</font>&nbsp;<input type="text" value="$senarai.alamat2" maxlength="80" size="40px" name="alamat2PB" id="alamat2PB" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase();"></td>
          		</tr>
          		
          		<tr>
          			<td>&nbsp;</td>
          			<td><font color="white">:</font>&nbsp;<input type="text" value="$senarai.alamat3" maxlength="80" size="40px" name="alamat3PB" id="alamat3PB" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase();"></td>
          		</tr>
          		
          		<tr>
          			<td>Poskod</td>
          			<td>:&nbsp;<input type="text" maxlength="5" size="5px" value="$senarai.poskod" name="poskodPB" id="poskodPB" onkeyup="validatetxtSyerAtas(this,this.value);" ></td>
          		</tr>
          		
          		<tr>
          			<td>Negeri</td>
          			<td>:&nbsp;$SelectNegeri</td>
          		</tr>
          		
          </table>
          
</fieldset> 
 #end
    #end    
    
 #if($view=="yes")
        #foreach ( $senarai in $PermohonanListDetailPB )
        <fieldset>
          <legend><strong>Maklumat Tuan Tanah / Pihak Berkepentingan</strong></legend>
         
<br/>          
          
          
          <table width="100%" border="0">
          
          <tr>
            <td width="30%">Nama</td>
            <td width="70%">:&nbsp;<input type="text" size="45px" name="txtNama" id="txtNama" value="$senarai.nama_pb" class=disabled readonly onblur="this.value=this.value.toUpperCase();" style="text-transform:uppercase"/></td>
          </tr>
            
          <tr>
            <td>Jenis Pihak Berkepentingan</td>
            <td>:&nbsp;<input type="text" size="45px" name="socJnsPB" id="socJnsPB" value="$senarai.keterangan_jenispb" class=disabled readonly style="text-transform:uppercase" /></td>
          </tr>
            
          <tr>
            <td>Kod No. Pihak Berkepentingan</td>
            <td>:&nbsp;<input type="text" size="30" name="socKodNoPB" id="socKodNoPB" value="$senarai.keterangan_jenisnopb" class=disabled readonly style="text-transform:uppercase" /></td>
          </tr>
            
          <tr>
            <td>No. Pihak Berkepentingan</td>
            <td>:&nbsp;<input type="text" size="30" name="txtNoPB" id="txtNoPB" value="$senarai.no_pb" class=disabled readonly onblur="this.value=this.value.toUpperCase();" style="text-transform:uppercase" /></td>
          </tr>  
          
          <tr>
          	<td>Bahagian</td>
          	<td>:&nbsp;<input type="text" size="5" name="txtBahagian" id="txtBahagian" value="$senarai.syer_atas" class=disabled readonly />
            /&nbsp;
            <input type="text" size="5" name="txtBahagian3" id="txtBahagian3" value="$senarai.syer_bawah" class=disabled readonly /></td>
          </tr>
          
          <tr>
          	<td>Bangsa</td>
          	<td>:&nbsp;<input type="text" size="45px" name="socBangsa" id="socBangsa" value="$senarai.keterangan_bangsa" class=disabled readonly style="text-transform:uppercase" /></td>
          </tr>
          
          <tr>
          	<td>Warganegara</td>
          	<td>:&nbsp;<input type="text" size="30px" name="socWarganegara" id="socWarganegara" value="$senarai.keterangan_warga" class=disabled readonly style="text-transform:uppercase" /></td>
          </tr>
          
           
          </table>
  </fieldset> 
  
<br/>
  
<fieldset>
   <legend><strong>Alamat Tuan Tanah / Pihak Berkepentingan</strong></legend>
         
<br/>
          <table width="100%" cellspacing="1" border="0">
          		<tr>
          			<td width="30%">Alamat</td>
          			<td width="70%">:&nbsp;<input type="text" value="$senarai.alamat1" class="disabled" readonly maxlength="80" size="40px" name="alamat1PB" id="alamat1PB" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase();"></td>
          		</tr>
          		
          		<tr>
          			<td>&nbsp;</td>
          			<td><font color="white">:</font>&nbsp;<input type="text" value="$senarai.alamat2" class="disabled" readonly maxlength="80" size="40px" name="alamat2PB" id="alamat2PB" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase();"></td>
          		</tr>
          		
          		<tr>
          			<td>&nbsp;</td>
          			<td><font color="white">:</font>&nbsp;<input type="text" value="$senarai.alamat3" class="disabled" readonly maxlength="80" size="40px" name="alamat3PB" id="alamat3PB" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase();"></td>
          		</tr>
          		
          		<tr>
          			<td>Poskod</td>
          			<td>:&nbsp;<input type="text" maxlength="5" size="5px" value="$senarai.poskod" class="disabled" readonly name="poskodPB" id="poskodPB" onkeyup="validatetxtSyerAtas(this,this.value);" ></td>
          		</tr>
          		
          		<tr>
          			<td>Negeri</td>
          			<td>:&nbsp;$selectNegeri</td>
          		</tr>
          		
          </table>
          
</fieldset>  
 #end 
    #end        
          #if($add=="yes")
          <div align="center">
          <input type="button" name="Simpan" id="Simpan" value="Simpan" onclick="simpanPB();">
          <input name="cmdBatal3" type="button" value="Kembali" onclick="javascript:kembali('$id_hakmilik');" />
          </div>
          #end
         
          #if($edit=="yes")
          <div align="center">
          <input type="button" name="Kemaskini" id="Simpan" value="Simpan" onclick="simpanEditPB('$id_pihakberkepentingan','$id_permohonan','$id_hakmilik');" >   
          <input name="cmdBatal" type="button" value="Batal" onclick="javascript: batal_update('$id_pihakberkepentingan');" />
          </div>
          #end  
          
         #if($view=="yes")
  <div align="center">
  			  #if($status!="16")  
          <input type="button" name="Kemaskini" id="Kemaskini" value="Kemaskini" onclick="javascript:KemaskiniPB('$id_hakmilik','$id_permohonan','$id_pihakberkepentingan'); ">          
         	  #end
         <!-- <input type="button" name="Hapus" id="Hapus" value="Hapus" onClick="delete_maklumatPB('$id_pihakberkepentingan')">-->
         <input name="cmdBatal2" type="button" value="Kembali" onclick="javascript:kembali('$id_hakmilik');" />
  </div>
          #end        

<!-- do post -->
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>

<script>
function batal_update(id_pihakberkepentingan) {
	if ( !window.confirm("Adakah anda pasti?") ) return;
	document.${formName}.method = "POST";
	document.${formName}.id_pihakberkepentingan.value = id_pihakberkepentingan;
	document.${formName}.command.value = "semakPB";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek4HakmilikSenarai";
	document.${formName}.submit();
}
function kembali(id_pihakberkepentingan) {
	document.${formName}.id_pihakberkepentingan.value = id_pihakberkepentingan;
	document.${formName}.command.value = "semakHM";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek4HakmilikSenarai";
	document.${formName}.submit();
}
function delete_maklumatPB(id_pihakberkepentingan) {
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.command.value = "delete_maklumatPB";
	document.${formName}.id_pihakberkepentingan.value = id_pihakberkepentingan;
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek4HakmilikSenarai";
	document.${formName}.submit();
}
function simpanPB(){

	
	if (document.${formName}.poskodPB.value != "" && document.${formName}.poskodPB.value.length < 5) {
		alert("Sila masukkan nombor \"Poskod\" alamat perayu dengan selengkapnya");
		document.${formName}.poskodPB.focus();
	}
	if(document.${formName}.txtNama.value == ""){
		alert("Sila masukkan \"Nama\" terlebih dahulu.");
  		document.${formName}.txtNama.focus(); 
		return;
	}
	if(document.${formName}.socJnsPB.value == ""){
		alert("Sila pilih \"Jenis Pihak Berkepentingan\" terlebih dahulu.");
  		document.${formName}.socJnsPB.focus(); 
		return;
	}
	if(document.${formName}.socKodNoPB.value == ""){
		alert("Sila pilih \"Kod No. Pihak Berkepentingan\" terlebih dahulu.");
  		document.${formName}.socKodNoPB.focus(); 
		return;
	}
	if(document.${formName}.txtSyerAtas.value == ""){
		alert("Sila masukkan \"Bahagian\" terlebih dahulu.");
  		document.${formName}.txtSyerAtas.focus(); 
		return;
	}
	if(document.${formName}.txtSyerBawah.value == ""){
		alert("Sila masukkan \"Bahagian\" terlebih dahulu.");
  		document.${formName}.txtSyerBawah.focus(); 
		return;
	}
	if(document.${formName}.socBangsa.value == ""){
		alert("Sila pilih \"Bangsa\" terlebih dahulu.");
  		document.${formName}.socBangsa.focus(); 
		return;
	}
	if(document.${formName}.socWarganegara.value == ""){
		alert("Sila pilih \"Warganegara\" terlebih dahulu.");
  		document.${formName}.socWarganegara.focus(); 
		return;
	}	
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.method = "POST";
	document.${formName}.command.value = "SimpanPB";
	document.${formName}.action = "";
	document.${formName}.submit();
}
function KemaskiniPB(id_hakmilik,id_permohonan,id_pihakberkepentingan){
	document.${formName}.method = "POST";
	document.${formName}.id_hakmilik.value = id_hakmilik;
	document.${formName}.id_permohonan.value = id_permohonan;	
	document.${formName}.id_pihakberkepentingan.value = id_pihakberkepentingan;
	document.${formName}.command.value = "KemaskiniPB";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek4HakmilikSenarai";
	document.${formName}.submit();
}
function simpanEditPB(id_pihakberkepentingan,id_permohonan,id_hakmilik){
	if(document.${formName}.txtNama.value == ""){
		alert("Sila masukkan \"Nama\" terlebih dahulu.");
  		document.${formName}.txtNama.focus(); 
		return;
	}
	if(document.${formName}.EDITsocJnsPB.value == ""){
		alert("Sila pilih \"Jenis Pihak Berkepentingan\" terlebih dahulu.");
  		document.${formName}.EDITsocJnsPB.focus(); 
		return;
	}	
	if(document.${formName}.EDITsocKodNoPB.value == ""){
		alert("Sila pilih \"Kod No. Pihak Berkepentingan\" terlebih dahulu.");
  		document.${formName}.EDITsocKodNoPB.focus(); 
		return;
	}
	if(document.${formName}.txtBahagianSyerAtas.value == ""){
		alert("Sila masukkan \"Bahagian\" terlebih dahulu.");
  		document.${formName}.txtBahagianSyerAtas.focus(); 
		return;
	}	
	if(document.${formName}.txtBahagianSyerBawah.value == ""){
		alert("Sila masukkan \"Bahagian\" terlebih dahulu.");
  		document.${formName}.txtBahagianSyerBawah.focus(); 
		return;
	}	
	if(document.${formName}.EDITsocBangsa.value == ""){
		alert("Sila pilih \"Bangsa\" terlebih dahulu.");
  		document.${formName}.EDITsocBangsa.focus(); 
		return;
	}	
	if(document.${formName}.EDITsocWarganegara.value == ""){
		alert("Sila pilih \"Warganegara\" terlebih dahulu.");
  		document.${formName}.EDITsocWarganegara.focus(); 
		return;
	}			
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.method = "POST";
	document.${formName}.id_pihakberkepentingan.value = id_pihakberkepentingan;
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.id_hakmilik.value = id_hakmilik;	
	document.${formName}.command.value = "simpanEditPB";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek4HakmilikSenarai";
	document.${formName}.submit();
}
/*
function Kembali_skrin3(id_fail,id_permohonan,id_hakmilik){
	document.${formName}.id_fail.value = id_fail;
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.id_hakmilik.value = id_hakmilik;
	document.${formName}.command.value = "Kembali_skrin3";
	document.${formName}.action = "";
	document.${formName}.submit();
}
*/
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
function validatetxtSyerAtas(elmnt,content) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = RemoveNonNumeric(content);
		return;
	}
}
function validatetxtSyerBawah(elmnt,content) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = RemoveNonNumeric(content);
		return;
	}
}
function kembali(id_hakmilik){
	document.${formName}.id_fail.value = id_hakmilik;
	document.${formName}.command.value = "semakHM";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek4HakmilikSenarai";
	document.${formName}.submit();
}
</script> 