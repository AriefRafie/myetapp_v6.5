<form name="f1" method="POST" > 

#set ( $idsuburusanstatusfail = $data.get("id_suburusanstatusfail") )
#set ( $id_status = $data.get("id_status") )

#foreach($list in $SenaraiFail)
 	#set($txtNoFail1=$list.no_fail)
 	#set($id_permohonan=$list.id_permohonan)
 	#set($txtNamaSimati=$list.nama_simati)
 	#set($txtNamaPemohon=$list.nama_pemohon)
    #set($txtNegeriAsal=$list.nama_negerimhn)
    #set($txtDaerahAsal=$list.nama_daerahmhn)
    #set($id_projek_negeri=$list.id_projek_negeri)
    #set($id_negeri=$list.id_negeri)
    #set($id_daerah=$list.id_daerah)
    #set($id_bke=$list.id_bke)
    #set($id_fail=$list.id_fail)
    #set($kod_negeri=$list.kod_negeri)
    #set($kod_daerah=$list.kod_daerah)    
    #set($txtNegeriPindah=$list.nama_negeri)
    #set($txtDaerahPindah=$list.nama_daerah)
    ##set($idStatus=$list.id_status)
#end

#foreach($list in $listSemak)
  #set($seksyen=$list.seksyen)
  #set($idSimati=$list.idSimati)
  #set($idpermohonan=$list.idPermohonan)
  
    <!-- case utk Seterusnya ke Pendaftaran -->

#end

#if ( $id_status == 56 )		
#if ( $mode == "add" )

 <fieldset>
  <legend>MAKLUMAT FAIL PINDAH YANG DITERIMA DARIPADA LUAR</legend>         
     <table width="100%"  cellspacing="3" cellpadding="1" border="0" >
       <tr>
         <td align="right">&nbsp;</td>
         <td>&nbsp;</td>
         <td>&nbsp;</td>
       </tr>
       <tr>
         <td align="right" style="text-transform:uppercase"><font color="red">*</font>&nbsp;Tarikh Pindah</td>
         <td>&nbsp;:</td>
         <td><input type="text" name="txdTarikhPindah" id="txdTarikhPindah" value="$!txdTarikhPindah" size="11" onkeyup="validateNumber(this,this.value);" onblur="check_date(this);" />
         <img src="../img/calendar.gif" alt="" onclick="displayDatePicker('txdTarikhPindah',false,'dmy');" /></td>
       </tr>
       <tr>
         <td align="right" style="text-transform:uppercase">&nbsp;No Fail</td>
         <td>&nbsp;:</td>
         <td><input type="text" name="txtNoFail1"  id="txtNoFail1" value="$!txtNoFail1" size="50" style="text-transform:uppercase;" readonly="true" class="disabled" />
           <input name="id_bke" type="hidden" id="id_bke" value="$id_bke"/>
           <input name="txtno_fail" type="hidden" id="txtno_fail" value="$txtNoFail1"/></td>
       </tr>
       <tr>
         <td align="right" style="text-transform:uppercase">&nbsp;Nama SiMati</td>
         <td>&nbsp;:</td>
         <td><input name="txtNamaSimati" type="text" id="txtNamaSimati" value="$!txtNamaSimati" size="50" style="text-transform:uppercase;" readonly="true" class="disabled" /></td>
       </tr>
       <tr>
         <td align="right" style="text-transform:uppercase">&nbsp;Nama Pemohon</td>
         <td>&nbsp;:</td>
         <td><input name="txtNamaPemohon" type="text" id="txtNamaPemohon" value="$!txtNamaPemohon" size="50" style="text-transform:uppercase;" readonly="true" class="disabled" /></td>
       </tr>
       <tr>
         <td width="36%" align="right" style="text-transform:uppercase">&nbsp;Negeri Asal</td>
         <td width="1%">&nbsp;:</td>
         <td width="63%"><input name="txtNegeriPindah" type="text" id="txtNegeriPindah" value="$!txtNegeriAsal" size="50" style="text-transform:uppercase;" readonly="true" class="disabled" /></td>
       </tr>
       <tr>
         <td align="right" style="text-transform:uppercase">&nbsp;Daerah Asal</td>
         <td>&nbsp;:</td>
         <td><input name="txtDaerahPindah" type="text" id="txtDaerahPindah" value="$!txtDaerahAsal" size="50" style="text-transform:uppercase;" readonly="true" class="disabled" /></td>
       </tr>
       <tr>
         <td align="right" style="text-transform:uppercase">&nbsp;Negeri Pindah</td>
         <td>&nbsp;:</td>
         <td><input name="txtNegeriPindah" type="text" id="txtNegeriPindah" value="$!txtNegeriPindah" size="50" style="text-transform:uppercase;" readonly="true" class="disabled" /><input name="id_negeri" type="hidden" id="id_negeri" value="$id_negeri"/>
         <input name="kod_negeri" type="hidden" id="kod_negeri" value="$kod_negeri"/></td>
       </tr>
       <tr>
         <td align="right" style="text-transform:uppercase">&nbsp;Daerah Pindah</td>
         <td>&nbsp;:</td>
         <td><input name="txtDaerahPindah" type="text" id="txtDaerahPindah" value="$!txtDaerahPindah" size="50" style="text-transform:uppercase;" readonly="true" class="disabled" /><input name="id_daerah" type="hidden" id="id_daerah" value="$id_daerah"/>
         <input name="kod_daerah" type="hidden" id="kod_daerah" value="$kod_daerah"/></td>
       </tr>              
     </table>	     

  <p></p>
      <div align="center">           
      <input type="button" name="Simpan" id="Simpan" value="Pindah Maklumat" onclick="javascript:Simpan_PindahFail17('$id_fail','$id_permohonan');" />
      <input name="cmdKembali" type="button" value="Kembali" onclick="javascript: Kembali('$id_fail','$id_permohonan');" />
      </div> 
      
</fieldset>        
#end  
#end



#if ( $mode == "view" )
 <fieldset>
  <legend>MAKLUMAT FAIL LAMA</legend>         
     <table width="100%"  cellspacing="3" cellpadding="1" border="0" >
       <tr>
         <td width="36%" align="right" style="text-transform:uppercase">&nbsp;No Fail Lama</td>
         <td width="1%">&nbsp;:</td>
         <td width="63%"><input type="text" name="txtNoFail1"  id="txtNoFail1" value="$!txtNoFail1" size="50" style="text-transform:uppercase;" readonly="true" class="disabled" />
         <input type="hidden" name="txtNoFail"  id="txtNoFail" value="$!txtNoFail" size="50" style="text-transform:uppercase;" readonly="true" class="disabled" />
         <input name="id_bke" type="hidden" id="id_bke" value="$id_bke"/></td>
       </tr>
       <tr>
         <td align="right" style="text-transform:uppercase">&nbsp;Nama SiMati</td>
         <td>&nbsp;:</td>
         <td><input name="txtNamaSimati" type="text" id="txtNamaSimati" value="$!txtNamaSimati" size="50" style="text-transform:uppercase;" readonly="true" class="disabled" /></td>
       </tr>
       <tr>
         <td align="right" style="text-transform:uppercase">&nbsp;Nama Pemohon</td>
         <td>&nbsp;:</td>
         <td><input name="txtNamaPemohon" type="text" id="txtNamaPemohon" value="$!txtNamaPemohon" size="50" style="text-transform:uppercase;" readonly="true" class="disabled" /></td>
       </tr>
     </table>	     
</fieldset> 
<p></p>

 <fieldset>
  <legend>MAKLUMAT FAIL YANG BARU DITERIMA</legend>
  
     <table width="100%"  cellspacing="3" cellpadding="1" border="0" >
       <tr>
         <td width="36%" align="right" style="text-transform:uppercase">&nbsp;Tarikh Pindah</td>
         <td width="1%">&nbsp;:</td>
         <td width="63%"><input type="text" name="txdTarikhPindah" value="$!txdTarikhPindah" size="15" id="txdTarikhPindah" onkeyup="validateNumber(this,this.value);" readonly="true" class="disabled" />
         <input name="id_permohonan_baru" type="hidden" id="id_permohonan_baru" value="$id_permohonan_baru"/></td>
       </tr>
       <tr>
         <td align="right" style="text-transform:uppercase">&nbsp;No Fail Baru</td>
         <td>&nbsp;:</td>
         <td><input type="text" name="txtNoFail"  id="txtNoFail" value="$!no_file_baru" size="50" style="text-transform:uppercase;" readonly="true" class="disabled" />
           <input name="id_file_baru" type="hidden" id="id_file_baru" value="$id_file_baru"/>     	 </td>
       </tr>
       <tr>
         <td align="right" style="text-transform:uppercase">&nbsp;Nama SiMati</td>
         <td>&nbsp;:</td>
         <td><input name="txtNamaSimati" type="text" id="txtNamaSimati" value="$!txtNamaSimati" size="50" style="text-transform:uppercase;" readonly="true" class="disabled" /></td>
       </tr>
       <tr>
         <td align="right" style="text-transform:uppercase">&nbsp;Nama Pemohon</td>
         <td>&nbsp;:</td>
         <td><input name="txtNamaPemohon" type="text" id="txtNamaPemohon" value="$!txtNamaPemohon" size="50" style="text-transform:uppercase;" readonly="true" class="disabled" /></td>
       </tr>

     </table>	       
  
  </fieldset>


	  <p></p>
      <div align="center">
      #if ($flagFromSenaraiFailSek8 == '' && $flagFromSenaraiPermohonanSek8 == '')
        <input name="cmdKembali" type="button" value="Kembali" onclick="javascript: Kembali('$id_fail','$id_permohonan');" />
      <!--  <input type="button" name="cmdPendaftaran" value="Seterusnya" onClick="goPendaftaran('$id_permohonan','$idSimati','$seksyen','$id_status')" /> -->
       <input type="button" name="cmdPendaftaran" value="Seterusnya" onClick="goPendaftaran('$id_permohonan_baru','$id_simati_baru','$seksyen_baru','$id_status_baru')" />
      #end
      #if ($flagFromSenaraiFailSek8 == 'yes')
		<input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="javascript:kembaliSenaraiFail('$noFail')"/>
	<!--  	<input type="button" name="cmdPendaftaran" value="Seterusnya" onClick="goPendaftaran('$id_permohonan','$idSimati','$seksyen','$id_status')" /> -->
     <input type="button" name="cmdPendaftaran" value="Seterusnya" onClick="goPendaftaran('$id_permohonan_baru','$id_simati_baru','$seksyen_baru','$id_status_baru')" />
      #end

	  #if ($flagFromSenaraiPermohonanSek8 == 'yes')
		<input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="javascript:kembaliSenaraiPermohonan('$noFail')"/>
	<!--  	<input type="button" name="cmdPendaftaran" value="Seterusnya" onClick="goPendaftaran('$id_permohonan','$idSimati','$seksyen','$id_status')" /> -->
     <input type="button" name="cmdPendaftaran" value="Seterusnya" onClick="goPendaftaran('$id_permohonan_baru','$id_simati_baru','$seksyen_baru','$id_status_baru')" />
      #end       	
      </div> 
#end  

#if ( $id_status == 169 )
#if ( $mode == "batal" )

#foreach($list in $SenaraiFailBatal)
 	#set($txtNoFail1=$list.no_fail)
 	#set($id_permohonan=$list.id_permohonan)
 	#set($txtNamaSimati=$list.nama_simati)
 	#set($txtNamaPemohon=$list.nama_pemohon)
    #set($txtNegeriAsal=$list.nama_negerimhn)
    #set($txtDaerahAsal=$list.nama_daerahmhn)
    #set($id_projek_negeri=$list.id_projek_negeri)
    #set($id_negeri=$list.id_negeri)
    #set($id_daerah=$list.id_daerah)
    #set($id_bke=$list.id_bke)
    #set($id_fail=$list.id_fail)
    #set($kod_negeri=$list.kod_negeri)
    #set($kod_daerah=$list.kod_daerah)    
    #set($txtNegeriPindah=$list.nama_negeri)
    #set($txtDaerahPindah=$list.nama_daerah)
    #set($tarikh_mohon=$list.tarikh_mohon)
    #set($no_fail_awal=$list.no_fail_awal)
#end


 <fieldset>
  <legend>MAKLUMAT FAIL LAMA</legend>         
     <table width="100%"  cellspacing="3" cellpadding="1" border="0" >
       <tr>
         <td width="36%" align="right" style="text-transform:uppercase">&nbsp;No Fail Lama</td>
         <td width="1%">&nbsp;:</td>
         <td width="63%"><input type="text" name="txtNoFail1"  id="txtNoFail1" value="$!txtNoFail1" size="50" style="text-transform:uppercase;" readonly="true" class="disabled" />
         <input type="hidden" name="no_fail_lama"  id="no_fail_lama" value="$no_fail" style="text-transform:uppercase;" readonly="true" class="disabled" />
         <input name="id_bke" type="hidden" id="id_bke" value="$id_bke"/></td>
       </tr>
       <tr>
         <td align="right" style="text-transform:uppercase">&nbsp;Nama SiMati</td>
         <td>&nbsp;:</td>
         <td><input name="txtNamaSimati" type="text" id="txtNamaSimati" value="$!txtNamaSimati" size="50" style="text-transform:uppercase;" readonly="true" class="disabled" /></td>
       </tr>
       <tr>
         <td align="right" style="text-transform:uppercase">&nbsp;Nama Pemohon</td>
         <td>&nbsp;:</td>
         <td><input name="txtNamaPemohon" type="text" id="txtNamaPemohon" value="$!txtNamaPemohon" size="50" style="text-transform:uppercase;" readonly="true" class="disabled" /></td>
       </tr>
     </table>	     
</fieldset> 
<p></p>

 <fieldset>
  <legend>MAKLUMAT FAIL YANG BARU DITERIMA</legend>
  
     <table width="100%"  cellspacing="3" cellpadding="1" border="0" >
       <tr>
         <td width="36%" align="right" style="text-transform:uppercase">&nbsp;Tarikh Pindah</td>
         <td width="1%">&nbsp;:</td>
         <td width="63%"><input type="text" name="txdTarikhPindah" value="$!tarikh_mohon" size="15" id="txdTarikhPindah" onkeyup="validateNumber(this,this.value);" readonly="true" class="disabled" /></td>
       </tr>
       <tr>
         <td align="right" style="text-transform:uppercase">&nbsp;No Fail Baru</td>
         <td>&nbsp;:</td>
         <td><input type="text" name="no_file_baru"  id="no_file_baru" value="$!no_file_baru" size="50" style="text-transform:uppercase;" readonly="true" class="disabled" />
     	 </td>
       </tr>
       <tr>
         <td align="right" style="text-transform:uppercase">&nbsp;Nama SiMati</td>
         <td>&nbsp;:</td>
         <td><input name="txtNamaSimati" type="text" id="txtNamaSimati" value="$!txtNamaSimati" size="50" style="text-transform:uppercase;" readonly="true" class="disabled" /></td>
       </tr>
       <tr>
         <td align="right" style="text-transform:uppercase">&nbsp;Nama Pemohon</td>
         <td>&nbsp;:</td>
         <td><input name="txtNamaPemohon" type="text" id="txtNamaPemohon" value="$!txtNamaPemohon" size="50" style="text-transform:uppercase;" readonly="true" class="disabled" /></td>
       </tr>

     </table>	       
  
  </fieldset>


	  <p></p>
      <div align="center">
        <input name="cmdKembali2" type="button" value="Kembali" onclick="javascript: Kembali('$id_fail','$id_permohonan');" />
        <input name="cmdCetak" type="button"  id="Cetak" value="Cetak" onclick="javascript:setTable('tableReport1')" />
       <!-- <input type="button" name="cmdPendaftaran2" value="Seterusnya" onclick="goPendaftaran('$id_permohonan','$idSimati','$seksyen','$id_status')" /> -->
       
        <input type="button" name="cmdPendaftaran" value="Seterusnya" onClick="goPendaftaran('$id_permohonan_baru','$id_simati_baru','$seksyen_baru','$id_status_baru')" />
      </div> 
      
#end  
#end

<input type="hidden" name="command">    
<input type="hidden" name="id_permohonan" class="disabled" id="id_permohonan" value="$id_permohonan"/>
<input type="hidden" name="idPermohonan" id="idPermohonan" />
<input type="hidden" name="id_fail" id="id_fail" class="disabled" value="$id_fail"/>
<input type="hidden" readonly="readonly" name="idsuburusanstatusfail" id="idsuburusanstatusfail" value="$idsuburusanstatusfail" />
<input type="hidden" readonly="true" class="disabled" name="ekptg_user_id" size="5" value="${session.getAttribute("_ekptg_user_id")}" /> 
<input type="hidden" readonly="readonly" class="disabled" name="id_status" id="id_status" value="$id_status" />
<input type="hidden" name="id_suburusan" value="$id_suburusan">
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
<input name="flagFromSenaraiFailSek8" type="hidden" id="flagFromSenaraiFailSek8" value="$flagFromSenaraiFailSek8"/>
<input name="flagFromSenaraiPermohonanSek8" type="hidden" id="flagFromSenaraiPermohonanSek8" value="$flagFromSenaraiPermohonanSek8"/>
<input type="hidden" name="seksyen" id="seksyen" value="$seksyen" /> 
<input type="hidden" name="idpermohonan" id="idpermohonan" value="$idpermohonan" />
<input type="hidden" name="idSimati" id="idSimati" value="$idSimati" /> 
   


      
</form>
<script>
/*
function goPendaftaran(id_permohonan,idSimati,seksyen,id_status){	
	document.f1.id_permohonan.value = id_permohonan;
	document.f1.idSimati.value = idSimati;
	document.f1.id_status.value = id_status;	
	if (seksyen == '8'){
	document.f1.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=FrmPrmhnnSek8Internal&command=papar";
	}else{
	document.f1.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=FrmPrmhnnSek17Senarai&command=papar";
	}
	document.f1.submit();	
}*/

function goPendaftaran(id_permohonan,idSimati,seksyen,id_status){	
	if (seksyen == '8'){
	document.f1.action = "$EkptgUtil.getTabID("Seksyen 8",$portal_role)?_portal_module=FrmPrmhnnSek8Internal&command=papar";
	}else{
	document.f1.action = "$EkptgUtil.getTabID("Seksyen 17",$portal_role)?_portal_module=FrmPrmhnnSek17Senarai&command=papar";
	}
		    document.f1.idpermohonan.value = id_permohonan;			
			document.f1.idPermohonan.value = id_permohonan;		
			//document.${formName}.command.value = "papar";			
			document.f1.idSimati.value = idSimati;
			document.f1.id_status.value = id_status;	
	document.f1.submit();	
}

function Kembali(id_fail,id_permohonan) {
	document.f1.id_fail.value = id_fail;
	document.f1.id_permohonan.value = id_permohonan;
	document.f1.command.value = "Kembali";
	document.f1.action = "";
	document.f1.submit();
}
function Simpan_PindahFail17(id_fail,id_permohonan) {
	
	var currentTime = new Date()
	var str1  = document.getElementById("txdTarikhPindah").value;   
    var dt1   = parseInt(str1.substring(0,2),10);
    var mon1  = parseInt(str1.substring(3,5),10)-1;
    var yr1   = parseInt(str1.substring(6,10),10);   
    var date1 = new Date(yr1, mon1, dt1);

	if(document.f1.txdTarikhPindah.value == ""){
		alert("Sila pilih \" Tarikh Pindah \" terlebih dahulu.");
		document.f1.txdTarikhPindah.focus(); 
		return;
	}
	if ( date1 > currentTime ){
		alert("Sila pastikan Tarikh Pindah tidak melebihi dari Tarikh Hari Ini");	
		txdTarikhPindah.focus();
		return;
	}
	else
	{
	if ( !window.confirm("Adakah Anda Pasti?") ) return;
	document.f1.id_fail.value = id_fail;
	document.f1.id_permohonan.value = id_permohonan;
	document.f1.command.value = "Simpan_PindahFail17";
	document.f1.action = "";
	document.f1.submit();
	}
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
            if( strValidCharacters.indexOf( strBuffer ) > -1 )            {
                  strReturn += strBuffer;
            }
      }
      return strReturn;
}

function kembaliSenaraiFail(noFail) {
	document.f1.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiFailSek8&txtNoFail="+noFail;
	document.f1.submit();
}


function kembaliSenaraiPermohonan(noFail) {
	document.f1.action = "$EkptgUtil.getTabID("'My Info'",$portal_role)?_portal_module=ekptg.view.ppk.FrmSenaraiPermohonanSeksyen8&txtNoFail="+noFail;
	document.f1.method="POST";
	document.f1.tarikhMohon.value = "";
	document.f1.submit();
}	
</script>
