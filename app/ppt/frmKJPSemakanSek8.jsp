<center>
<fieldset>
  <legend><strong>Carian</strong></legend>
	<table width="100%" cellspacing="2" cellpadding="0" border="0">
   
    	 <tr>
    		<td width="25%">&nbsp;</td>
        	<td width="15%">No. Permohonan &nbsp; </td>
            <td width="60%">:&nbsp;<input type="text" name="no_permohonan" value="$carianPermohonan" style="text-transform:uppercase;" id="no_permohonan" size="27" onkeyup="this.value=this.value.toUpperCase();" /></td>
        </tr>
        
        <tr>
        	<td>&nbsp;</td>
        	<td>Tarikh Permohonan &nbsp; </td>
            <td>:&nbsp;<input name="tarikh_permohonan" id="tarikh_permohonan" size="11" type="text" value="$carianTarikh" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" >
            	<img src="../img/calendar.gif" onclick="displayDatePicker('tarikh_permohonan',false,'dmy');"></td>
        </tr>
        
        <!-- <tr>
        	<td>&nbsp;</td>
        	<td>Urusan Permohonan &nbsp; </td>
            <td>:&nbsp;$!SelectSuburusanUPT</td>
        </tr> -->
        
        <tr>
        	<td>&nbsp;</td>
        	<td>Status &nbsp; </td>
            <td>:&nbsp;$!SelectStatusPPT</td>
        </tr>
   
        <tr>
        	<td>&nbsp;</td>
        	<td>&nbsp;</td>
        	<td>
            	&nbsp;&nbsp;<input name="cari" value="Cari" type="button" onclick="javascript:search_data()" />
  				<input type=button value = "Kosongkan" onClick="javascript:clearData();">
            </td>
        </tr>
        
    </table>
</fieldset>

<br/>

<fieldset>
	<legend><strong>Senarai Permohonan</strong></legend>
	
	 #parse("app/utils/record_paging.jsp")
	 
     <table width="100%" cellspacing="2" cellpadding="2" border="0">  
     
     	<tr>
     		<td colspan="2" scope="row"><input type="button" name="cmdBaru" value ="Daftar Baru" onClick="javascript:baru();"></td>
    	</tr>
      
        <tr class="table_header">
        	<td><b>No</b></td>
        	<td><b>No.Permohonan</b></td>
            <td><b>Tarikh</b></td>
            <!-- <td><b>Urusan</b></td> -->
            <td><b>Kementerian</b></td>
            <td><b>Status</b></td> 
            <td><b>No.Fail</b></td> 
        </tr>
        
      #if($list_size!=0)
           	#foreach($list in $PermohonanListSPT)
                #set( $i = $velocityCount )
         		#if ( ($i % 2) != 1 )
              		 #set( $row = "row2" )
         		#else
               		 #set( $row = "row1" )
         		#end
        	<tr>
        		<td class="$row">$list.bil</td>
        		<td class="$row"><a href="javascript:edit_item('$list.id_permohonan')"><font color="blue">$list.no_permohonan</font></a></td> 
            	<td class="$row">$list.tarikh_permohonan</td>
            	<!-- <td class="$row">$list.nama_suburusan</td> -->
            	<td class="$row">$list.nama_kementerian</td>
            	<td class="$row">$list.status</td>
            	<td class="$row">$list.no_fail</td>
        	</tr>
        	#end
     #else
        	<tr>
        		<td colspan="6">Tiada rekod</td>
        	<tr>
     #end
        
    </table> 
</fieldset>
<input type="hidden" name="id_permohonan" value="$id_permohonan">
<input type="hidden" name="no_permohonan">
<input type="hidden" name="tarikh_permohonan">
<input type="hidden" name="status">
<input type="hidden" name="suburusan">

</center>

<script>
function edit_item(id_permohonan) {
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "semak";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanSPTSek8";
	document.${formName}.submit();
}
function clearData() {
	doAjaxCall${formName}("clearData");
}
function baru() {
	document.${formName}.command.value = "baru";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPermohonanSPTSek8";
	document.${formName}.submit();
}
function search_data(){
	doAjaxCall${formName}("cari");
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
<script language="JavaScript"> document.${formName}.no_permohonan.focus(); </script>