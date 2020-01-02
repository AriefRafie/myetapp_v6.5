#set ( $id_status = $statusFail.get("id_status") )

#if ($clearForm == "yes")    
    #set ($txtNoBantahan = "")		
    #set ($txdTkhMasuk = "")
    #set ($txdTkhTerimaBrgN = "")
    #set ($txdBrgN = "")
    #set ($txtNoLot = "")
    #set ($txtNoPt = "")
    #set ($txdTkhBrgH = "")
    #set ($txdTkhAward = "")
    #set ($txtNoHakmilik = "")
    #set ($txtNama = "")
    #set ($txtAlamat1 = "")
    #set ($txtAlamat2 = "")
    #set ($txtAlamat3 = "")
    #set ($txtPoskod = "")
    #set ($txtKptgnAtasTnh = "")
    #set ($txtAlasanBantahan = "")
    #set ($sbcBantahan = "") 
    #set ($txtUnitLot = "")   
    #set ($txtUnitPt = "")
    #set ($txtNoPt = "") 
    #set ($jenis_pembantah = "") 
#end

#foreach ( $senarai in $Header )
	#set ($nama_kementerian=$senarai.nama_kementerian)
    #set ($id_kementerian=$senarai.id_kementerian)
    #set ($id_agensi=$senarai.id_agensi)
    #set ($no_fail=$senarai.no_fail)
    #set ($no_permohonan=$senarai.no_permohonan)
    #set ($tarikh_terima=$senarai.tarikh_terima)
    #set ($projek_negeri=$senarai.projek_negeri)
    #set ($nama_daerah=$senarai.nama_daerah)
    #set ($tujuan=$senarai.tujuan)
    #set ($tarikh_kehendaki=$senarai.tarikh_kehendaki)
    #set ($no_rujukan_surat=$senarai.no_rujukan_surat)
    #set ($no_rujukan_ptd=$senarai.no_rujukan_ptd)
    #set ($no_rujukan_ptg=$senarai.no_rujukan_ptg)
    #set ($no_rujukan_upt=$senarai.no_rujukan_upt)
    #set ($keterangan=$senarai.keterangan) 
    #set ($nama_agensi=$senarai.nama_agensi)
    #set ($tarikh_permohonan=$senarai.tarikh_permohonan)   
#end 

#if ($flag == "semak")
    #foreach ( $info in $getHakmilik )
        #set ($id_hakmilik=$info.id_hakmilik)
        #set ($no_lot=$info.no_lot) 
        #set ($txtUnitLot=$info.unitlot) 
        #set ($txtNoPt=$info.no_pt)
        #set ($txtUnitPt=$info.unitpt)
        #if($txtNoPt=="")
        	#set ($txtNoPt="")   
    		#set ($txtUnitPt="")
        #end        
    #end 
    
    #foreach ( $getAlamat in $getAlamatPembantah )
        #set ($id_pihakberkepentingan=$getAlamat.id_pihakberkepentingan)
        #set ($txtAlamat1=$getAlamat.alamat1) 
        #set ($txtAlamat2=$getAlamat.alamat2) 
        #set ($txtAlamat3=$getAlamat.alamat3) 
        #set ($txtPoskod=$getAlamat.poskod)        
	#end   
#end

#if ($clear == "alamat")
	#set ($txtAlamat1="")
    #set ($txtAlamat2="")
    #set ($txtAlamat3="")
    #set ($txtPoskod="")
#end

<fieldset>
	<legend>PERMOHONAN BANTAHAN KE MAHKAMAH</legend>

<!------------------------------------------- HEADER ---------------------------------------------->

     <table width="100%" border="0">
           <tr>	
             <td width="25%" style="text-transform:uppercase">Kementerian</td>
             <td width="1%">:</td>
             <td width="74%">$!nama_kementerian<input type="hidden" name="id_kementerian" id="id_kementerian" value="$!id_kementerian" /></td>
           </tr>
           <tr>
             <td style="text-transform:uppercase">No. Fail</td>
             <td>:</td>
             <td>$!no_fail</td>
           </tr>
           <tr>
             <td style="text-transform:uppercase">No. Permohonan</td>
             <td>:</td>
             <td>$!no_permohonan</td>
           </tr>
           <tr>
             <td style="text-transform:uppercase">Tarikh Permohonan</td>
             <td>:</td>
             <td>$!tarikh_permohonan</td>           
           </tr>
           <tr>
             <td style="text-transform:uppercase">Projek Negeri</td>
             <td>:</td>
             <td>$!projek_negeri</td>           
           </tr>
           <tr>
             <td style="text-transform:uppercase">Daerah / Jajahan</td>
             <td>:</td>
             <td>$!nama_daerah</td>            
           </tr>
           <tr>
             <td height="30" valign="top" style="text-transform:uppercase">Tujuan Pengambilan</td>
             <td valign="top">:</td>
             <td valign="top">$!tujuan</td>            
           </tr>          
           <tr>
           <td style="text-transform:uppercase">Agensi</td>
             <td>:</td>
             <td>$!nama_agensi<input type="hidden" name="id_agensi" id="id_agensi" value="$!id_agensi" /> </td>
           </tr>           
           <tr>
           <td style="text-transform:uppercase">Tarikh Dikehendaki</td>
             <td>:</td>
             <td>$!tarikh_kehendaki</td>
           </tr>
           <tr>
           <td style="text-transform:uppercase">No. Rujukan Kementerian</td>
             <td>:</td>
             <td>$!no_rujukan_surat</td>
           </tr>         
           <tr>
            <td style="text-transform:uppercase">No. Rujukan PTD</td>
             <td>:</td>
             <td>$!no_rujukan_ptd</td>
           </tr>           
           <tr>
            <td style="text-transform:uppercase">No. Rujukan PTG</td>
             <td>:</td>
             <td>$!no_rujukan_ptg</td>
           </tr>           
           <tr>
            <td style="text-transform:uppercase">No. Fail Permohonan</td>
             <td>:</td>
             <td>$!no_rujukan_upt</td>
           </tr>           
           <tr>
           <td style="text-transform:uppercase">Status Permohonan</td>
             <td>:</td>
             <td>$!keterangan</td>
           </tr>
 	 </table>
<!------------------------------------------- END HEADER ---------------------------------------------->  

<!-------------------------------------- MAKLUMAT BANTAHAN -------------------------------------------->

<fieldset>
<legend>Maklumat Bantahan</legend>
    <table width="100%" border="0"> 
        <tr>
            <td width="26%"><font color="red">*</font>&nbsp;No Bantahan</td>
          <td width="1%">:</td>
          <td width="26%"><input type="text" name="txtNoBantahan" id="txtNoBantahan" value="$!txtNoBantahan" onblur="this.value=this.value.toUpperCase();" style="text-transform:uppercase;" tabindex="1" /></td>
          <td width="1%">&nbsp;</td>
          <td width="20%">Status Bantahan</td>
          <td width="1%">:</td>
          <td width="25%"><select name="flag_syarat" id="flag_syarat" style="text-transform:uppercase;" onblur="uppercase()" class="autoselect" >                   
			#if($flag_syarat=="1")                                                   
            <option value="1">01-Syarat Dipenuhi</option>
            <option value="2">02-Syarat Tidak Dipenuhi</option>                      
            #elseif($flag_syarat=="2")                                                 
            <option value="2">02-Syarat Tidak Dipenuhi</option>
            <option value="1">01-Syarat Dipenuhi</option>           
	        #else                                                     
            <option value="1">01-Syarat Dipenuhi</option>
            <option value="2">02-Syarat Tidak Dipenuhi</option>            
            #end         
          </select>
          </td>
      </tr>
        <tr>
            <td rowspan="2" valign="top">&nbsp;&nbsp;&nbsp;Tarikh Kemasukan</td>
            <td rowspan="2" valign="top">:</td>
            <td rowspan="2" valign="top"><input type="text" name="txdTkhMasuk" id="txdTkhMasuk" value="$!tarikh_permohonan" size="10" onblur="check_date(this);" onkeyup="validateNumber(this,this.value);" tabindex="2" class="disabled" readonly />
           <!-- <img src="../img/calendar.gif" alt="" onclick="displayDatePicker('txdTkhMasuk',false,'dmy');" />--></td>
            <td rowspan="2">&nbsp;</td>
            <td rowspan="2" valign="top">No Lot</td>
            <td rowspan="2" valign="top">:</td>
            <td>$selectNoLot</td>
        </tr>
        <tr>
          <td><!--<input type="text" name="txtUnitLot" id="txtUnitLot" value="$!txtUnitLot" class="disabled" readonly tabindex="8" />--></td>
        </tr>  
        <tr>
            <td valign="top"><font color="red">*</font>&nbsp;Tarikh Terima Borang N</td>
            <td valign="top">:</td>
            <td valign="top"><input type="text" name="txdTkhTerimaBrgN" id="txdTkhTerimaBrgN" value="$!txdTkhTerimaBrgN" size="10" onblur="check_date(this);" onkeyup="validateNumber(this,this.value);" tabindex="3" />
            <img src="../img/calendar.gif" alt="" onclick="displayDatePicker('txdTkhTerimaBrgN',false,'dmy');" /></td>
            <td rowspan="2">&nbsp;</td>
            <td valign="top">No PT</td>
            <td valign="top">:</td>
            <td><input type="text" name="txtNoPt" id="txtNoPt" value="$!txtNoPt" onblur="this.value=this.value.toUpperCase();" style="text-transform:uppercase;" class="disabled" readonly tabindex="8" /></td>
        </tr>
        <tr>
          <td valign="top"><font color="red">*</font>&nbsp;Tarikh Borang N</td>
          <td valign="top">:</td>
          <td valign="top"><input type="text" name="txdBrgN" id="txdBrgN" value="$!txdBrgN" size="10" onblur="check_date(this);" onkeyup="validateNumber(this,this.value);" tabindex="4" />
            <img src="../img/calendar.gif" alt="" onclick="displayDatePicker('txdBrgN',false,'dmy');" /></td>
          <td valign="top">Tarikh Borang H</td>
          <td valign="top">:</td>
          <td><!--<input type="text" name="txtUnitPt" id="txtUnitPt" value="$!txtUnitPt" class="disabled" readonly tabindex="10" />-->
            <input type="text" name="txdTkhBrgH" id="txdTkhBrgH" value="$!txdTkhBrgH" size="10" onblur="check_date(this);" onkeyup="validateNumber(this,this.value);" tabindex="9" />
            <img src="../img/calendar.gif" alt="" onclick="displayDatePicker('txdTkhBrgH',false,'dmy');" /></td>
        </tr> 
        <tr>
            <td valign="top"><font color="red">*</font>&nbsp;No. Hakmilik</td>
            <td valign="top">:</td>
            <td valign="top">$selectHakmilik<input type="hidden" name="id_hakmilik" id="id_hakmilik" value="$!id_hakmilik" /></td>
            <td>&nbsp;</td>
            <td valign="top">Tarikh Award</td>
            <td valign="top">:</td>
            <td valign="top"><input type="text" name="txdTkhAward" id="txdTkhAward" value="$!txdTkhAward" size="10" onblur="check_date(this);" onkeyup="validateNumber(this,this.value);" tabindex="10" />
              <img src="../img/calendar.gif" alt="" onclick="displayDatePicker('txdTkhAward',false,'dmy');" /></td>
        </tr> 
        <tr>
          <td valign="top">&nbsp;&nbsp;&nbsp;Pihak yang membantah</td>
          <td valign="top">:</td>
          <td valign="top"><select name="jenis_pembantah" tabindex="6" id="jenis_pembantah" style="text-transform:uppercase;" onblur="uppercase()" class="autoselect" >
                    
			#if($jenis_pembantah=="1")                                        
            <option value="1">01-Pihak Berkepentingan</option>
            <option value="2">02-Agensi Pemohon</option>
            
            #elseif($jenis_pembantah=="2")                                     
            <option value="2">02-Agensi Pemohon</option>
            <option value="1">01-Pihak Berkepentingan</option>
            
	        #else                                         
            <option value="1">01-Pihak Berkepentingan</option>
            <option value="2">02-Agensi Pemohon</option>
            #end 
                                 
          </select>
          </td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
<!--        <tr>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          <td>&nbsp;</td>
            <td>No. Hakmilik</td>
            <td>:</td>
            <td><input type="text" name="txtNoHakmilik" id="txtNoHakmilik" value="$!txtNoHakmilik" onblur="this.value=this.value.toUpperCase();" style="text-transform:uppercase;" tabindex="13" /></td>
        </tr>-->                            
    </table>   
</fieldset>

<!------------------------------------ END MAKLUMAT BANTAHAN ------------------------------------------> 

<!------------------------------------ MAKLUMAT PEMBANTAH --------------------------------------------->  

<fieldset>
	<legend>Maklumat Pembantah</legend>
    <table width="100%" border="0">
        <tr>
            <td width="22%"><font color="red">*</font>&nbsp;Nama Pembantah</td>
            <td width="1%">:</td>
          <td colspan="5">$selectNamaPembantah<input type="hidden" name="id_pihakberkepentingan" id="id_pihakberkepentingan" value="$!id_pihakberkepentingan" /></td>
        </tr>
        <tr>
            <td>&nbsp;&nbsp;&nbsp;Alamat</td>
            <td>:</td>
            <td colspan="5"><input type="text" name="txtAlamat1" id="txtAlamat1" value="$!txtAlamat1" size="50" onblur="this.value=this.value.toUpperCase();" style="text-transform:uppercase;" class="disabled" readonly tabindex="12" /></td>
      </tr>  
        <tr>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td colspan="5"><input type="text" name="txtAlamat2" id="txtAlamat2" value="$!txtAlamat2" size="50" onblur="this.value=this.value.toUpperCase();" style="text-transform:uppercase;" class="disabled" readonly tabindex="13" /></td>
      </tr> 
        <tr>
            <td>&nbsp;</td>
            <td>:</td>
            <td colspan="5"><input type="text" name="txtAlamat3" id="txtAlamat3" value="$!txtAlamat3" size="50" onblur="this.value=this.value.toUpperCase();" style="text-transform:uppercase;" class="disabled" readonly tabindex="14" /></td>
      </tr> 
        <tr>
          <td valign="top">&nbsp;&nbsp;&nbsp;Poskod</td>
          <td valign="top">:</td>
          <td colspan="5" valign="top"><input type="text" name="txtPoskod" id="txtPoskod" value="$!txtPoskod" maxlength="5" size="5" onkeyup="validateNumber(this,this.value);" class="disabled" readonly tabindex="15" /></td>
        </tr>
        <tr>
          <td valign="top">&nbsp;&nbsp;&nbsp;Negeri</td>
          <td valign="top">:</td>
          <td colspan="5" valign="top">$selectNegeri</td>
        </tr>
        <tr>
            <td valign="top">&nbsp;&nbsp;&nbsp;Bandar</td>
            <td valign="top">:</td>
            <td colspan="5" valign="top">$selectBandar</td>
        </tr> 
        <tr>
          <td valign="top">&nbsp;&nbsp;&nbsp;Jenis Pihak Pembantah</td>
          <td valign="top">:</td>
          <td colspan="5">$selectPihakBantah</td>
        </tr>
        <tr>
          <td valign="top"><font color="red">*</font>&nbsp;Kepentingan</td>
          <td valign="top">:</td>
          <td colspan="5"><textarea name="txtKptgnAtasTnh" id="txtKptgnAtasTnh" cols="50" rows="6" onblur="this.value=this.value.toUpperCase();" style="text-transform:uppercase;" tabindex="16">$!txtKptgnAtasTnh</textarea></td>
        </tr>
        <tr>
            <td valign="top"><font color="red">*</font>&nbsp;Bantahan Terhadap</td>
            <td valign="top">:</td>
            <td width="4%" valign="top"><input type="radio" name="sbcBantahan" id="sbcBantahan" value="1" tabindex="17" $TEMPchecked1 /></td>
            <td width="73%" colspan="4" valign="top">Ukuran Luas Tanah</td>
        </tr>
        <tr>
            <td valign="top">&nbsp;</td>
            <td valign="top">&nbsp;</td>
            <td valign="top"><input type="radio" name="sbcBantahan" id="sbcBantahan" value="2" tabindex="18" $TEMPchecked2 /></td>
            <td colspan="4" valign="top">Amaun Pampasan</td>
        </tr>    
        <tr>
            <td valign="top">&nbsp;</td>
            <td valign="top">&nbsp;</td>
            <td valign="top"><input type="radio" name="sbcBantahan" id="sbcBantahan" value="3" tabindex="19" $TEMPchecked3 /></td>
            <td colspan="4" valign="top">Orang-orang yang akan menerima pampasan</td>
        </tr>
        <tr>
            <td valign="top">&nbsp;</td>
            <td valign="top">&nbsp;</td>
            <td valign="top"><input type="radio" name="sbcBantahan" id="sbcBantahan" value="4" tabindex="20" $TEMPchecked4 /></td>
            <td colspan="4" valign="top">Pengumpukan Pampasan</td>
        </tr>                              
        <tr>
          <td valign="top"><font color="red">*</font>&nbsp;Alasan Bantahan</td>
          <td valign="t">:</td>
          <td colspan="5"><textarea name="txtAlasanBantahan" id="txtAlasanBantahan" cols="50" rows="6" onblur="this.value=this.value.toUpperCase();" style="text-transform:uppercase;" tabindex="21">$!txtAlasanBantahan</textarea></td>
        </tr>                                   
    </table> 
    
</fieldset>

<!------------------------------------------------------ END MAKLUMAT PEMBANTAH ---------------------------------------->  

<!----------------------------------------- SENARAI DOKUMEN YANG DISERTAKAN --------------------------------------------->

<!--<fieldset>
<legend>Senarai Dokumen Yang Disertakan</legend>
    
      <table width="100%"  cellpadding="1" cellspacing="2" border="0">
      </br>
      	<tr>
        	<td colspan="4"><input type="button" name="cmdTambah" id="cmdTambah" value="Tambah" onclick="javascript:UploadDokumen()" />
        	  <input type="button" name="cmdPapar" id="cmdPapar" value="Papar" onclick="" />
            <input type="button" name="cmdHapus" id="cmdHapus" value="Hapus" onclick="" /></td>
        </tr>
        <tr class="table_header">
            <td width="7%" align="center" style="text-transform:uppercase" scope="row">Bil</td>
            <td width="23%" style="text-transform:uppercase">Nama Dokumen</td>
            <td width="30%" style="text-transform:uppercase">Keterangan</td>
            <td width="40%" style="text-transform:uppercase">Dokumen Sokongan</td>
        </tr>
        <tr>                                      
            <td class="$row"></td>               
            <td class="$row"></td>
            <td class="$row"></td>
            <td class="$row"></td>
        </tr>	       
      </table>    

</fieldset>-->
<!--</br>-->
<!----------------------------------------- END SENARAI DOKUMEN --------------------------------------------->

<div align="center">
  <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:add_bantahan()" /> 
  <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="javascript:batal()" /> 
  <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="javascript:kembaliList()" />
</div> 

</fieldset>

<!------------------------------------------ OUTPUT LAPORAN/SURAT ----------------------------------------------->
<br/>
<fieldset id="tableReport1" style="display:none;">
<legend><strong>Senarai Laporan</strong></legend>
	<table width="100%" border="0" cellspacing="2" cellpadding="2">
       <tr>
        <td><a href="#" class="style2" onClick=""><font color="blue"> Borang N - Permohonan Supaya Bantahan Dirujukkan Kepada Mahkamah </font></a></td>
      </tr>           
    </table>
</fieldset>
<!------------------------------------------ END OUTPUT LAPORAN/SURAT ------------------------------------------>

<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
<input type="hidden" name="id_permohonan" id="id_permohonan" value="$id_permohonan" />
<input type="hidden" name="id_fail" id="id_fail" value="$id_fail" />

<script>
function kembaliList(){
	document.${formName}.command.value = "";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanSenaraiCarian";
	document.${formName}.submit();
}
function batal(){
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.command.value = "batal";	
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanSenaraiCarian";
	document.${formName}.submit();
}
function doChangeidNegeri() {
	document.${formName}.command.value = "doChangeidNegeri";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanSenaraiCarian";
	document.${formName}.submit();		
}
function doChangeNoLot() {
	document.${formName}.command.value = "doChangeNoLot";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanSenaraiCarian";
	document.${formName}.submit();		
}
function doChangeAlamatPembantah() {
	document.${formName}.command.value = "doChangeAlamatPembantah";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanSenaraiCarian";
	document.${formName}.submit();		
}
function setTable(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}
function add_bantahan(){

	if(document.${formName}.txtNoBantahan.value == ""){
		alert("Sila masukkan \"No Bantahan\" terlebih dahulu.");
  		document.${formName}.txtNoBantahan.focus(); 
		return;		
	}
	if(document.${formName}.txdTkhTerimaBrgN.value == ""){
		alert("Sila masukkan \"Tarikh Terima Borang N\" terlebih dahulu.");
  		document.${formName}.txdTkhTerimaBrgN.focus(); 
		return;		
	}
	if(document.${formName}.txdBrgN.value == ""){
		alert("Sila masukkan \"Tarikh Borang N\" terlebih dahulu.");
  		document.${formName}.txdBrgN.focus(); 
		return;		
	}	
	if(document.${formName}.socHakmilik.value == ""){
		alert("Sila pilih \"No. Hakmilik\" terlebih dahulu.");
  		document.${formName}.socHakmilik.focus(); 
		return;		
	}	
	if(document.${formName}.socNamaPembantah.value == ""){
		alert("Sila pilih \"Nama Pembantah\" terlebih dahulu.");
  		document.${formName}.socNamaPembantah.focus(); 
		return;		
	}	
	if(document.${formName}.txtKptgnAtasTnh.value == ""){
		alert("Sila masukkan \"Kepentingan\" terlebih dahulu.");
  		document.${formName}.txtKptgnAtasTnh.focus(); 
		return;		
	}	
	var radioSelected = false;	
	for (i = 0;  i < ${formName}.sbcBantahan.length;  i++){
		if (${formName}.sbcBantahan[i].checked)
		radioSelected = true;
	}	
	if (!radioSelected){
		alert("Sila pilih \"Sila Pilih Alasan Bantahan\" terlebih dahulu.");
		return (false);
	}		
	else{
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.${formName}.command.value = "add_bantahan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanSenaraiCarian";
	document.${formName}.submit();
	}
}
function UploadDokumen(){
	document.${formName}.command.value = "UploadDokumen";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanSenaraiCarian";
	document.${formName}.submit();
}
function validateNumber(elmnt,content) {
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

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          