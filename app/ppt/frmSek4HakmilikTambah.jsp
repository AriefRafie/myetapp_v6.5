
<input type="hidden" name="idDaerah" value="$idDaerah">
<input type="hidden" name="idProjekNegeri" value="$idProjekNegeri">
<input type="hidden" name="id_fail" value="$id_fail">
<input type="hidden" name="id_permohonan" value="$id_permohonan">
<input type="hidden" name="id_hakmilik" value="$id_hakmilik">
<input type="hidden" name="id_pihakberkepentingan" value="$id_pihakberkepentingan">
<input type="hidden" name="status" value="$status">

<!---------------------------------------------------- add mode -------------------------------------------------->
#if($add=="yes")

        <fieldset>
          <legend><strong>Maklumat Tanah</strong></legend>
          <table width="100%" border="0" cellspacing="2" cellpading="0">
          
          <tr>
            <td width="35%"><font color="red">*</font>Jenis Hakmilik</td>
            <td width="65%">:&nbsp;$selectJenisHakmilik</td>
          </tr>
            
          <tr>
            <td width="15%"><font color="red">*</font>No.Hakmilik</td>
            <td>:&nbsp;<input type="text" size="26px" name="txtNoHM" onBlur="this.value=this.value.toUpperCase();" id="txtNoHM" style="text-transform:uppercase;"  /></td>
          </tr>
          
          <tr>
            <td><font color="red">*</font>Kod Lot</td>
            <td>:&nbsp;$selectLot</td>
          </tr>
            
          <tr>
            <td><font color="red">*</font>No.Lot / No.PT</td>
            <td>:&nbsp;<input type="text" name="txtNoLot" value="" onblur="validate(this,this.value);" onkeyup="validate(this,this.value);" maxlength="20"  style="text-transform:uppercase;" size="26px" onblur="this.value=this.value.toUpperCase();" id="txtNoLot" /></td>
          </tr>
          
          <!-- <tr>
            <td><input type="radio" name="check" value="1" checked onClick="disableNoPT()">No. Lot</td>
            <td>:&nbsp;<input type="text" name="txtNoLot" size="20" value="" maxlength="20"  style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase();" id="txtNoLot" /></td>
          </tr>
            
          <tr>
          	<td><input type="radio" name="check" value="2" onClick="disableNoLot()">No. PT</td>
            <td>:&nbsp;<input type="text" name="txtNoPT" size="20" value="" disabled maxlength="25"  style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase();" id="txtNoPT" /></td>
          </tr> -->  
           
          <tr>
            <td>Negeri</td>
            <td>:&nbsp;$selectNegeri</td>
          </tr> 
            
          <tr>
          	<td>Jajahan / Daerah</td>
          	<td>:&nbsp;$selectDaerah</td>
          </tr>  
          
          <tr>
          	<td><font color="red">*</font>Bandar/Pekan/Mukim</td>
          	<td>:&nbsp;$selectMukim</td>
          </tr>  
          
          <tr>
          	<td><font color="red">*</font>Unit Luas</td>
          	<td>:&nbsp;$selectLuas</td>
          </tr>  
          
          <tr>
          	<td><font color="red">*</font>Luas Lot</td>
          	<td>:&nbsp;<input type="text" size="6" name="txtKeluasan" id="txtKeluasan" onblur="validate(this,this.value);" onkeyup="validate(this,this.value);"  /></td>
          </tr>  
          
          <tr>
          	<td><font color="red">*</font>Anggaran Kawasan Yang Hendak Diambil</td>
          	<td>:&nbsp;<input type="text" size="6" name="txtLuasAmbil" onkeyup="validate(this,this.value);" onblur="validate(this,this.value);" id="txtLuasAmbil" /></td>
          </tr>  
             
          </table>
  </fieldset> 
#end  

<!---------------------------------------------------- view mode -------------------------------------------------->
#if($view=="yes")

        <fieldset>
          <legend><strong>Maklumat Tanah</strong></legend>
          #foreach ( $senarai in $PermohonanListHM )
          <table width="100%" border="0">
          
          <tr>
            <td width="35%" valign="top">Jenis Hakmilik</td>
            <!-- <td width="65%">:&nbsp;<textarea name="txtJnsHMView" cols="25%" rows="3" id="txtJnsHMView" readonly="true" style="text-transform:uppercase"></textarea></td> -->
            <td width="65%">:&nbsp;$selectViewJenisHakmilik</td>
            <!-- <td width="65%">:&nbsp;<input type="text" name="txtJnsHMView" size="55px" value="$senarai.keteranganHM" id="txtJnsHMView" readonly></td> -->
            </tr>
            
          <tr>
            <td width="13%" height="27">No.Hakmilik</td>
            <td>:&nbsp;<input type="text" size="27" onkeyup="validatetxtNoHM(this,this.value);" name="txtNoHM" id="txtNoHM" value="$senarai.no_hakmilik"  style="text-transform:uppercase" class="disabled" readonly /></td>
           
          </tr>
            
          <tr>
            <td>Kod Lot</td>
            <td>:&nbsp;$selectViewLot</td>
          </tr>
            
            <tr>
            	<td>No.Lot / No.PT</td>
            	<td>:&nbsp;<input type="text" name="txtNoLotView" value="$senarai.no_lot" onblur="validate(this,this.value);" onkeyup="validate(this,this.value);" maxlength="20"  class="disabled" readonly style="text-transform:uppercase;" size="27px" onblur="this.value=this.value.toUpperCase();" id="txtNoLotView" /></td>
            </tr>  
            
          <!-- <tr>
            <td>No. Lot</td>
			<td>:&nbsp;<input type="text" size="28" name="txtNoLotView" id="txtNoLotView" value="$senarai.no_lot" readonly="true"  style="text-transform:uppercase" /></td>
            </tr>
            
          <tr>
            <td>No. PT</td>
            <td>:&nbsp;<input type="text" size="28" name="txtNoPTView" id="txtNoPTView" value="$senarai.no_pt" readonly="true"  style="text-transform:uppercase"/></td>
            </tr> -->  
          
          <tr>
            <td>Negeri</td>
            <td>:&nbsp;$selectViewNegeri</td>
            <!-- <td>:&nbsp;<input type="text" size="45px" name="txtnegeriView" id="txtnegeriView" value="$senarai.nama_negeri" readonly="true"  style="text-transform:uppercase"/></td>
          	<input type="hidden" name="EDITsocNegeri" value="$senarai.id_negeri"> -->
            
          </tr>  
            
           <tr>
          	<td>Jajahan / Daerah</td>
          	<td>:&nbsp;$selectViewDaerah</td>
          	<!-- <td>:&nbsp;<input type="text" size="45px" name="txtDaerahView" id="txtDaerahView" value="$senarai.nama_daerah" readonly="true"/></td> -->
          </tr>  
          
          <tr>
          	<td>Bandar/Pekan/Mukim</td>
          	<td>:&nbsp;$selectViewMukim</td>
          	<!-- <td>:&nbsp;<input type="text" size="45px" name="txtMukimView" id="txtMukimView" value="$senarai.nama_mukim" readonly="true"/></td> -->
           </tr>  
          
          <tr>
          	<td>Unit Luas</td>
          	<td>:&nbsp;$selectViewLuasLot</td>
          	<!-- <td>:&nbsp;<input type="text" size="28" name="txtunitluasambilView" id="txtunitluasambilView" value="$senarai.keterangan" readonly="true"/></td> -->
          </tr>  
          
          <tr>
          	<td>Luas Lot</td>
          	<td>:&nbsp;<input type="text" size="8" name="txtKeluasanView" id="txtKeluasanView" value="$senarai.luas_lot" class="disabled" readonly /></td>
          </tr>  
          
          <tr>
          	<td>Anggaran Kawasan Yang Hendak Diambil</td>
          	<td>:&nbsp;<input type="text" size="8" name="txtLuasAmbilView" id="txtLuasAmbilView" value="$senarai.luas_ambil" class="disabled" readonly /></td>
          </tr>
           
          </table>
      #end
      </fieldset> 
      <p>
      <fieldset>
      <legend><strong>Maklumat Tuan Tanah / Pihak Berkepentingan</strong></legend>
           #if($status!="16")
           <table width="100%" border="0" cellspacing="2">
          	 	<tr>
           			<td><input name="cmdTambah" type="button" value="Tambah" onclick="javascript:Tambah();" /></td>
           		</tr>
           </table>
           #end
         <table width="100%" border="0" cellspacing="2">
 		 	<tr class="table_header">
			  	<td align="center"><b>No.</b></td>
  				<td><b>No. PB</b></td>
  				<td><b>Nama</b></td>
  				<td><b>Bahagian</b></td>
  				<td><b>Jenis Kepentingan</b></td>
  			</tr> 
  		#if($size_PB!=0)	
            #foreach ( $senarai in $PermohonanListPB )
            
            #if ($senarai.bil == '')
          	#set ($row = 'row1')
            
			#elseif ($senarai.bil % 2 != 0)
            		#set ($row = 'row1')
                
            #else 
            	#set ($row = 'row2')
            #end
            
  			<tr>
  				<td class="$row" align="center">$senarai.bil</td>
                <td class="$row"><a href="javascript:semakPB('$senarai.id_pihakberkepentingan')"><font color="blue">$senarai.no_pb</font></a></td>
  				<td class="$row">$senarai.nama_pb</td>
  				<td class="$row">$senarai.syer_atas / $senarai.syer_bawah</td>
  				<td class="$row">$senarai.keteranganjenispb</td>
		   </tr>
  			#end
  		#else
  			<tr>
  				<td colspan="5">Tiada Rekod</td>
  			</tr>
  		#end	
	  	</table>
      </fieldset>
#end   

<!---------------------------------------------------- edit mode -------------------------------------------------->
#if($edit=="yes")
        <fieldset>
          <legend><strong>Maklumat Tanah<strong></legend>
          #foreach ( $senarai in $PermohonanListHM )
          <table width="100%" border="0">
          <tr>
            <td width="35%"><font color="red">*</font>Jenis Hakmilik</td>
            <td width="65%">:&nbsp;$selectJenisHakmilik
            <input type="hidden" name="id_existJenisHakmilik" value="$senarai.id_jenishakmilik" /></td>
          </tr>
            
          <tr>
            <td width="14%"><font color="red">*</font>No.Hakmilik</td>
            <td>:&nbsp;<input type="text" size="26px" name="txtNoHM" id="txtNoHM" onBlur="this.value=this.value.toUpperCase();" value="$senarai.no_hakmilik" style="text-transform:uppercase" /></td>
          </tr>
          
          <tr>
            	<td><font color="red">*</font>Kod Lot</td>
            	<td>:&nbsp;$selectLot</td>
            </tr>
            
            <tr>
            	<td><font color="red">*</font>No.Lot / No.PT</td>
            	<td>:&nbsp;<input type="text" size="26px" name="txtNoLot" maxlength="20" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase();" value="$senarai.no_lot" id="txtNoLot" /></td>
            </tr>
          
          <!-- <tr>
          	<td><input type="radio" name="check" value="1" $checkA onClick="disableNoPTedit()">No. Lot</td>
            <td>:&nbsp;<input type="text" size="20" name="txtNoLot" $disA maxlength="20" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase();" value="$senarai.no_lot" id="txtNoLot" /></td>
          </tr>
          
          <tr>
            <td><input type="radio" name="check" value="2" $checkB onClick="disableNoLotedit()">No. PT</td>
            <td>:&nbsp;<input type="text" size="20" name="txtNoPT" $disB maxlength="25" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase();" value="$senarai.no_pt" id="txtNoPT" /></td>          
          </tr> -->
          
            
          <tr>
            <td>Negeri</td>
            <td>:&nbsp;$selectEditNegeri
            <input type="hidden" name="id_existNegeri" value="$senarai.id_negeri" /></td> 
          </tr>  
          
          <tr>
          	<td>Jajahan / Daerah</td>
          	<td>:&nbsp;$selectEditDaerah</td>
          	<input type="hidden" name="id_existDaerah" value="$senarai.id_daerah" />
          </tr>  
          
          <tr>
          	<td><font color="red">*</font>Bandar/Pekan/Mukim</td>
          	<td>:&nbsp;$selectEditMukim</td>
          	<input type="hidden" name="id_existMukim" value="$senarai.id_mukim" />
          </tr> 
          
          <tr>
          	<td><font color="red">*</font>Unit Luas</td>
          	<td>:&nbsp;$selectLuasLot</td>
          	<input type="hidden" name="id_UnitLuas" value="$senarai.id_unitluaslot" />
          </tr> 
          
          <tr>
          	<td><font color="red">*</font>Luas Lot</td>
          	<td>:&nbsp;<input type="text" size="8" name="txtKeluasan" id="txtKeluasan" value="$senarai.luas_lot" onblur="validate(this,this.value);" onkeyup="validate(this,this.value);"  /></td>
          </tr> 
          
          <tr>
          	<td><font color="red">*</font>Anggaran Kawasan Yang Hendak Diambil</td>
          	<td>:&nbsp;<input type="text" size="8" name="txtLuasAmbil" id="txtLuasAmbil" value="$senarai.luas_ambil" onblur="validate(this,this.value);" onkeyup="validate(this,this.value);" /></td>
          </tr> 
            
          </table>
      	#end
      </fieldset> 
#end   
	<p>
    <div align="center">
    #if($add == "yes")
      <input type="button" name="Simpan" id="Simpan" value="Simpan" onclick="javascript:Simpan_MT();">          
      <input name="cmdBatal" type="button" value="Kembali" onclick="javascript: kembaliSemak('$id_fail','$id_permohonan');" />
    #end
    #if($view == "yes")
      #if($status!="16")
      <input type="button" name="Kemaskini" id="Kemaskini" value="Kemaskini" onclick="javascript:KemaskiniHM('$id_hakmilik','$id_permohonan');" />        
      #end
      <!--<input type="button" name="Hapus" id="Hapus" value="Hapus">-->
      <input name="cmdBatal2" type="button" value="Kembali" onclick="javascript: kembaliSemak('$id_fail','$id_permohonan');" />
    #end  
    #if($edit == "yes")
      <input type="button" name="Simpan" id="Simpan" value="Simpan" onclick="javascript:Kemaskini_MT('$id_hakmilik','$id_permohonan');">          
      <input name="cmdBatal3" type="button" value="Batal" onclick="javascript: batal('$id_fail','$id_permohonan');" />
    #end  </div>

<!-- do post -->
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>

<script>
function doChanges() {
	doAjaxCall${formName}("doChanges");
}
function kembaliSemak(id_fail,id_permohonan) {
	document.${formName}.id_fail.value = id_fail;
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "semak";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek4HakmilikSenarai";
	document.${formName}.submit();
}
function Simpan_MT(){	
	Editluas = parseInt(document.${formName}.txtKeluasan.value);
	EditluasAmbil = parseInt(document.${formName}.txtLuasAmbil.value);

	if (EditluasAmbil > Editluas) {
		alert ("Anggaran luas melebihi luas lot");
		document.${formName}.txtLuasAmbil.focus();
		return;
	}
	
	if(document.${formName}.socHakmilik.value == ""){
		alert("Sila pilih \"Jenis Hakmilik\" terlebih dahulu.");
  		document.${formName}.socHakmilik.focus(); 
		return;
	}
		if(document.${formName}.txtNoHM.value == ""){
		alert("Sila masukkan \"No Hakmilik\" terlebih dahulu.");
  		document.${formName}.txtNoHM.focus(); 
		return;
	}
		if(document.${formName}.lot.value == ""){
			alert("Sila pilih \"Kod Lot\" terlebih dahulu.");
	  		document.${formName}.lot.focus(); 
			return;
		}
		if(document.${formName}.txtNoLot.value == ""){
			alert("Sila masukkan \"No.Lot / No.PT\" terlebih dahulu.");
	  		document.${formName}.txtNoLot.focus(); 
			return;
		}
		if(document.${formName}.socNegeri.value == ""){
		alert("Sila pilih \"Negeri\" terlebih dahulu.");
  		document.${formName}.socNegeri.focus(); 
		return;
	}
		if(document.${formName}.socDaerah.value == ""){
		alert("Sila pilih \"Jajahan/Daerah\" terlebih dahulu.");
  		document.${formName}.socDaerah.focus(); 
		return;
	}	
		if(document.${formName}.socMukim.value == ""){
		alert("Sila pilih \"Bandar/Pekan/Mukim\" terlebih dahulu.");
  		document.${formName}.socMukim.focus(); 
		return;
	}	
		if(document.${formName}.socLuas.value == ""){
		alert("Sila pilih \"Unit Luas\" terlebih dahulu.");
  		document.${formName}.socMukim.focus(); 
		return;
	}
		if(document.${formName}.txtKeluasan.value == ""){
		alert("Sila masukkan \"Luas Lot\" terlebih dahulu.");
  		document.${formName}.txtKeluasan.focus(); 
		return;
	}		
		if(document.${formName}.txtLuasAmbil.value == ""){
		alert("Sila masukkan \"Anggaran Kawasan Yang Hendak Diambil\" terlebih dahulu.");
  		document.${formName}.txtLuasAmbil.focus(); 
		return;
	}
	else{	
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.method = "POST";
	document.${formName}.command.value = "Simpan_MT";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek4HakmilikSenarai";
	document.${formName}.submit();
		}
}
function Tambah(){
	document.${formName}.method = "POST";
	document.${formName}.command.value = "TambahHM";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek4HakmilikSenarai";
	document.${formName}.submit();
}
function KemaskiniHM(id_hakmilik,id_permohonan){
	document.${formName}.method = "POST";
	document.${formName}.id_hakmilik.value = id_hakmilik;
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "KemaskiniHM";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek4HakmilikSenarai";
	document.${formName}.submit();
}
function Kemaskini_MT(id_hakmilik,id_permohonan){

	Editluas = parseInt(document.${formName}.txtKeluasan.value);
	EditluasAmbil = parseInt(document.${formName}.txtLuasAmbil.value);
	
	if (EditluasAmbil > Editluas) {
		alert ("Anggaran luas melebihi luas lot");
		document.${formName}.edit_anggaran_luas.focus();
		return;
	}
	if(document.${formName}.EDITsocJenisHM.value == ""){
		alert("Sila pilih \"Jenis Hakmilik\" terlebih dahulu.");
  		document.${formName}.EDITsocJenisHM.focus(); 
		return;
	}
	if(document.${formName}.EDITlot.value == ""){
		alert("Sila pilih \"Kod Lot\" terlebih dahulu.");
  		document.${formName}.EDITlot.focus(); 
		return;
	}
	if(document.${formName}.txtNoLot.value == ""){
		alert("Sila masukkan \"No.Lot / No.PT\" terlebih dahulu.");
  		document.${formName}.txtNoLot.focus(); 
		return;
	}
	if(document.${formName}.txtNoHM.value == ""){
		alert("Sila masukkan \"No. Hakmilik\" terlebih dahulu.");
  		document.${formName}.txtNoHM.focus(); 
		return;
	}
	
	if(document.${formName}.EDITsocNegeri.value == ""){
		alert("Sila pilih \"Negeri\" terlebih dahulu.");
  		document.${formName}.EDITsocNegeri.focus(); 
		return;
	}	
	if(document.${formName}.EDITsocDaerah.value == ""){
		alert("Sila pilih \"Jajahan / Daerah\" terlebih dahulu.");
  		document.${formName}.EDITsocDaerah.focus(); 
		return;
	}	
	if(document.${formName}.EDITsocMukim.value == ""){
		alert("Sila pilih \" Bandar/Pekan/Mukim \" terlebih dahulu.");
  		document.${formName}.EDITsocNegeri.focus(); 
		return;
	}	
	if(document.${formName}.EDITsocLuas.value == ""){
		alert("Sila pilih \"Unit Luas\" terlebih dahulu.");
  		document.${formName}.EDITsocLuas.focus(); 
		return;
	}	
	if(document.${formName}.txtKeluasan.value == ""){
		alert("Sila masukkan \"Luas Lot\" terlebih dahulu.");
  		document.${formName}.txtKeluasan.focus(); 
		return;
	}
	if(document.${formName}.txtLuasAmbil.value == ""){
		alert("Sila masukkan \"Anggaran Kawasan Yang Hendak Diambil\" terlebih dahulu.");
  		document.${formName}.txtLuasAmbil.focus(); 
		return;
	}					
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.id_hakmilik.value = id_hakmilik;
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "Kemaskini_MT";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek4HakmilikSenarai";
	document.${formName}.submit();
}
function semakPB(id_pihakberkepentingan) {
	document.${formName}.method = "POST";
	document.${formName}.id_pihakberkepentingan.value = id_pihakberkepentingan;
	document.${formName}.command.value = "semakPB";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek4HakmilikSenarai";
	document.${formName}.submit();
}
function batal(id_fail,id_permohonan){
	if ( !window.confirm("Adakah anda pasti?") ) return;
	document.${formName}.id_fail.value = id_fail;
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "semakHM";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek4HakmilikSenarai";
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
</script> 