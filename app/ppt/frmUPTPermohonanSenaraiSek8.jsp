
      <input type="hidden" name="id_fail">       
      <input type="hidden" name="id_permohonan">
      
<fieldset>
    <legend><strong>Carian</strong></legend>
    
      <table width="100%" cellspacing="2" cellpadding="0" border="0">
          <tr>
            <td width="25%">&nbsp;</td>
          	<td width="15%">No. Fail</td>
            <td width="60%">:&nbsp;<input name="no_fail" onBlur="this.value=this.value.toUpperCase();" style="text-transform:uppercase;" type="text" value="$carianFail" size="27" ></td>
      	  </tr>
          
          <tr>
            <td>&nbsp;</td>
            <td>Tarikh</td>
            <td>:&nbsp;<input name="txdTarikhMohon" value="$CarianTarikhMohon" size="11" type="text" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" />
            <a href="javascript:displayDatePicker('txdTarikhMohon',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a></td>
      	  </tr>
          
          <tr>
            <td>&nbsp;</td>
            <td>Urusan Permohonan</td>
            <td>:&nbsp;$selectSubUrusanUPT</td>
          </tr>
      	  
      	  <tr>
        	<td>&nbsp;</td>
        	<td>Status</td>
            <td>:&nbsp;<select name="cStatus" class="autoselect">
				<option value="">SILA PILIH</option>
				<option value="148">TINDAKAN PEGAWAI</option>
				<option value="149">SEMAKAN CAWANGAN</option>
				</select></td>
          </tr>  
      	  
      	  <tr>
      	    <td>&nbsp;</td>
          	<td>&nbsp;</td>
          	<td>&nbsp;&nbsp;<input name="cari" value="Cari" type="button" onclick="javascript:search_data();" />
                <input name="kosongkan" value = "Kosongkan" type="button" onclick="javascript:cancel();" /></td>
          </tr>
          
     </table>  
 
          
</fieldset> 
    
<br/>    
 
<fieldset>
    <legend><strong>Senarai Permohonan</strong></legend>
    #parse("app/utils/record_paging.jsp")
       
       <table width="100%" border="0" cellspacing="2">                    
          <tr class="table_header">
              <td><b>No</b></td>
              <td><b>No Fail</b></td>
              <td><b>No Permohonan</b></td>
              <td><b>Tarikh</b></td>
              <td><b>Urusan</b></td>
              <td><b>Kementerian</b></td>
              <td><b>Status</b></td>
          </tr>
   
   #if($list_size!=0)     
       #foreach($senarai in $PermohonanList)
                   	 #set( $i = $velocityCount )
         		#if ( ($i % 2) != 1 )
              		 #set( $row = "row2" )
         		#else
               		 #set( $row = "row1" )
         		#end
          	
          <tr valign="top">
              <td class="$row" >$senarai.bil</td>
              <td class="$row"><a href="javascript:edit_item('$senarai.id_fail','$senarai.id_permohonan')"><font color="blue">$senarai.no_fail</font></a></td>  
              <td class="$row">$senarai.no_permohonan</td>
              <td class="$row">$senarai.tarikh_permohonan</td>
              <td class="$row">$senarai.nama_suburusan</td>
              <td class="$row">$senarai.nama_kementerian</td>
              <td class="$row">$senarai.keterangan</td>
          </tr>
          
      #end   
   #else  
   		  <tr>
        	<td colspan="6">Tiada rekod</td>
          <tr>  
   #end  
   		     
      </table> 
    </fieldset> 
 
<script>
function doChanges() {
	doAjaxCall${formName}("doChanges");
}
function search_data(){
		document.${formName}.command.value = "carian";
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTPermohonanSenaraiSek8";
		document.${formName}.submit();
}
function edit_item(id_fail,id_permohonan) {
	document.${formName}.id_fail.value = id_fail;
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "semak";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTPermohonanSenaraiSek8";
	document.${formName}.submit();
}
function cancel() {
	document.${formName}.command.value = "cancel";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTPermohonanSenaraiSek8";
	document.${formName}.submit();
	}

function validateTarikh(elmnt,content) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = RemoveNonNumeric2(content);
		return;
	}
}
function RemoveNonNumeric2( strString )
{
      var strValidCharacters = "1234567890/.";
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

<script language="JavaScript"> document.forms[0].no_fail.focus(); </script>