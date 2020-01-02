
<center>

<fieldset>   
 <legend><strong>Carian</strong></legend>  
  <table width="100%" cellspacing="2" cellpadding="0" border="0">
  
  <tr>
	<td width="20%">&nbsp;</td>
  	<td width="25%">No.Fail Permohonan / PTG / PTD / UPT</td>
	<td width="55%">:&nbsp;<input type="text" name="no_fail" value="$!carianPermohonan" size="35" style="text-transform:uppercase;" onblur="this.value=this.value.toUpperCase();"></td>
  </tr>

  <tr>
      <td>&nbsp;</td>
      <td>Status &nbsp; </td>
      <td>:&nbsp;<select name="carianStatus" class="autoselect">
      		<option value="">SILA PILIH</option>
      		<option value="148">TINDAKAN PEGAWAI</option>
			<option value="147">PENYEDIAAN LAPORAN AWAL</option>
			<option value="26">PENYEDIAAN MAKLUMAT MMK</option>
			<option value="31">PENGWARTAAN</option>
			
			</select></td>
  </tr> 
    
  <tr><td colspan="3">&nbsp;</td></tr>
  
  <tr>
  	<td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;&nbsp;<input name="cari" value="Cari" type="button" onclick="javascript:search_data()" />
  		<input type=button value = "Kosongkan" onClick="javascript:cancel();">
  	</td>
  </tr>
  </table>
</fieldset>  


<br/>

<fieldset>
	<legend><strong>Senarai Permohonan</strong></legend>
  		
  		#parse("app/utils/record_paging.jsp")
  		
  		<table width="100%" cellspacing="2" cellpadding="0" border="0">
  		
  			<tr class="table_header">
  				<td align="center" width="4%"><b>No</b></td>
  				<td><b>No.Fail Permohonan</b></td> 
        		<td><b>No.Fail PTG</b></td>
        		<td><b>No.Fail PTD</b></td>
        		<td><b>No.Fail UPT</b></td>
  				<td><b>Kementerian</b></td>
  				<td><b>Status MMK</b></td>
  				<td><b>Status Permohonan</b></td>
  			</tr>
  
  
   #if($list_size!=0)
        #foreach ( $senarai in $PermohonanList )
       		 #set ($id_status = $PermohonanList.id_status)
       		 
                   	 #set( $i = $velocityCount )
         		#if ( ($i % 2) != 1 )
              		 #set( $row = "row2" )
         		#else
               		 #set( $row = "row1" )
         		#end
  
  		<tr>
  			<td class="$row" align="center">$senarai.bil</td>
  			<td class="$row"><a href="javascript:viewSenaraiMMK('$!senarai.id_permohonan')"><font color="blue">$senarai.no_fail</font></a></td>
  			<td class="$row"><a href="javascript:viewSenaraiMMK('$!senarai.id_permohonan')"><font color="blue">$senarai.no_rujukan_ptg</font></a></td>
  			<td class="$row"><a href="javascript:viewSenaraiMMK('$!senarai.id_permohonan')"><font color="blue">$senarai.no_rujukan_ptd</font></a></td>
  			<td class="$row"><a href="javascript:viewSenaraiMMK('$!senarai.id_permohonan')"><font color="blue">$senarai.no_rujukan_upt</font></a></td>
 			<td class="$row">$senarai.nama_kementerian</td>
 			<td class="$row">$senarai.flag_semakan_pengarah</td>
  			<td class="$row">$senarai.status</td>
  		</tr>
  #end
  
  #else
  <tr>
  <td colspan="5">Tiada rekod</td>
  </tr>
  #end
  </table>
  
<input type="hidden" name="id_permohonan">
<input type="hidden" name="no_fail">
<input type="hidden" name="no_rujukan">
<input type="hidden" name="id_status">

<!-- Anchor -->
<input type="hidden" name="ScreenLocation" value="$!ScreenLocation">
<input type="hidden" name="CursorPoint" value="$!CursorPoint">

</fieldset>


</center>


<script>
function cancel() {
	doAjaxCall${formName}("cancel");
}
function search_data(){
	doAjaxCall${formName}("cari");
}
function viewSenaraiMMK(id_permohonan) {
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "viewSenaraiMMK";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmUPTSek4MMKSenarai";
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

<script>
window.onload = submitForm;
function submitForm(){

	if('$ScreenLocation' != ""){
		window.location.hash='$ScreenLocation';
		goTo('$CursorPoint');
	}
}
</script>