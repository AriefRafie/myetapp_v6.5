
#set($frmtdate = "&nbsp;<i><font color='blue' style='font-size:10px'>dd/mm/yyyy</font></i>")

<center>
  	<fieldset>
   		<legend><strong>Carian</strong></legend>
   		
  		<table width="100%" cellspacing="2" cellpadding="0" border="0">
    		
    		 <tr>
    			<td width="25%">&nbsp;</td>
        		<td width="17%">No.Fail Permohonan</td>
            	<td width="58%">:&nbsp;<input name="no_fail" type=text value="$carianFail" size="35" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase();" /></td>
        	</tr>
        	
        	<tr>
        		<td>&nbsp;</td>
        		<td>Tarikh Permohonan</td>
            	<td>:&nbsp;<input name="tarikh_permohonan" id="tarikh_permohonan" size="11" type="text" value="$carianTarikh" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)">
            	<img src="../img/calendar.gif" onclick="displayDatePicker('tarikh_permohonan',false,'dmy');">$!frmtdate</td>
        	</tr>
        	
        	<!-- <tr>
        		<td>&nbsp;</td>
        		<td>Urusan Permohonan &nbsp; </td>
            	<td>:&nbsp;$SelectSuburusanUPT</td>
        	</tr> -->
     
        	<tr>
      			<td>&nbsp;</td>
      			<td>Status &nbsp; </td>
      			<td>:&nbsp;<select name="carianStatus" class="autoselect">
      				<option value="">SILA PILIH</option>
					<option value="31">PENGWARTAAN</option>
					<option value="52">NOTIS AWAM</option>		
			</select></td>
  </tr> 
        	
        	<tr><td colspan="3">&nbsp;</td></tr>
        	
  			<tr>
  				<td>&nbsp;</td>
  				<td>&nbsp;</td>
  				<td>&nbsp;&nbsp;<input name="cari" value="Cari" type="button" onclick="javascript:search_data()" />
  					<input type=button value = "Kosongkan" onClick="javascript:cancel();"></td>	
  			</tr>
  		</table>
  </fieldset>
</center>
  
  <br/>
  
  <fieldset>
  		<legend><strong>Senarai Permohonan</strong></legend>
  		
  		#parse("app/utils/record_paging.jsp")
  		
  		<table width="100%" cellspacing="2" cellpadding="2" border="0"> 
  		
  			<tr class="table_header">
  				<td class="fixedLeft"><b>No</b></td>
  				<td><b>No.Fail Permohonan</b></td>
  				<td><b>Tarikh</b></td>
  				<!-- <td><b>Urusan</b></td> -->
  				<td><b>Kementerian</b></td>
  				<td><b>Status</b></td>
  				<td class="fixedRight"><b>Bil Permohonan</b></td>
  			</tr>
  
  	#if($list_size!=0)
  		#foreach ( $senarai in $PermohonanList )
  	   	 			#set( $i = $velocityCount )
         		#if ( ($i % 2) != 1 )
              		 #set( $row = "row2" )
         		#else
               		 #set( $row = "row1" )
         		#end
  			<tr valign="top">
  				<td class="$row">$senarai.bil</td>
  				<td class="$row"><a href="javascript:view_item('$senarai.id_permohonan')"><font color="blue">$senarai.no_fail</font></a></td>
  				<td class="$row">$senarai.tarikh_permohonan</td>
  				<!-- <td class="$row">$senarai.nama_suburusan</td> -->
  				<td class="$row">$senarai.nama_kementerian</td>
  				<td class="$row">$senarai.keterangan</td>
  				<td class="$row">$senarai.no_permohonan</td>
  			</tr> 			
  		#end
	
 	 #else
        	<tr>
        		<td colspan="7">Tiada rekod</td>
        	</tr>
        #end
  
  		</table>
  </fieldset>
  
  <input type="hidden" name="id_permohonan">
  <input type="hidden" name="no_permohonan">
  <input type="hidden" name="no_fail">
  <input type="hidden" name="tarikh_permohonan">


<script>
function cancel() {
	doAjaxCall${formName}("cancel");
}
function search_data(){
	doAjaxCall${formName}("cari");
}
function view_item(id_permohonan) {
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "Semak2";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.UPTSek4NotisAwam";
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