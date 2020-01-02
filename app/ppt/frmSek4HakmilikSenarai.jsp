
    <fieldset>
    <legend><strong>Carian</strong></legend>
      <table width="100%" cellspacing="2" cellpadding="0" border="0">
      <tr>
        <td width="25%">&nbsp;</td>
        <td width="15%">No. Fail</td>
        <td width="60%">:&nbsp;<input name="no_fail" type="text" value="$carianFail" onBlur="this.value=this.value.toUpperCase();" size="27" style="text-transform:uppercase"></td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>Tarikh</td>
        <td>:&nbsp;<input name="txdTarikhMohon" size="11" type="text" value="$CarianTarikhMohon" onkeyup="validateTarikh(this,this.value)" onblur="check_date(this)" />
        <a href="javascript:displayDatePicker('txdTarikhMohon',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a> </td>
      </tr>   
      <!--  <tr>
  	    <td>&nbsp;</td>
      	<td>Urusan Permohonan</td>
    	<td>:&nbsp;$selectSubUrusanUPT</td>
  	  </tr>  -->
  	 
  	  <tr>
  	    <td>&nbsp;</td>
      	<td>Status</td>
    	<td>:&nbsp;<select name="socStatusHakmilik" class="AUTOSELECT">
				<option value="">SILA PILIH</option>
				<option value="150">SEMAKAN CAWANGAN</option>
				<option value="16">MAKLUMAT HAKMILIK</option>
				</select></td>
  	  </tr>          
      
      <tr>
        <td></td>
      <td></td><td>&nbsp;&nbsp;<input name="cari" value="Cari" type="button" onclick="javascript:search_data();" />
        <input type="button" value = "Kosongkan" onclick="javascript:cancel();" /></td>
      </tr>
      </table>     
  </fieldset>    
    <p>
    <fieldset>
       <legend><strong>Senarai Permohonan</strong></legend>
       #parse("app/utils/record_paging.jsp")
       
         <table width="100%" border="0" cellspacing="2"> 
             <tr class="table_header">
               <td style="text-transform:uppercase">No</td>
               <td style="text-transform:uppercase">No Fail</td>
               <td style="text-transform:uppercase">No Permohonan</td>
               <td style="text-transform:uppercase">Tarikh</td>
               <!-- <td style="text-transform:uppercase">Urusan</td> -->
               <td style="text-transform:uppercase">Kementerian</td>
               <td style="text-transform:uppercase">Status</td>
             </tr>
     
        #if($list_size!=0)   
           #foreach ( $senarai in $PermohonanList )
           
           #if ($senarai.bil == '')
           #set ($row = 'row1')
           
           #elseif ($senarai.bil % 2 != 0)
           #set ($row = 'row1')
           
           #else 
           #set ($row = 'row2')
           #end
           <tr valign="top">
             <td class="$row">$senarai.bil</td>
             <td class="$row"><a href="javascript:edit_item('$senarai.id_fail','$senarai.id_permohonan')"><font color="blue">$senarai.no_fail</font></a></td>
             <td class="$row">$senarai.no_permohonan</td>
             <td class="$row">$senarai.tarikh_permohonan</td>
             <!-- <td class="$row">$senarai.nama_suburusan</td> -->
             <td class="$row">$senarai.nama_kementerian</td>
             <td class="$row">$senarai.keterangan</td>
           </tr>         
           #end
        #else   
           
           <tr valign="top">
             <td colspan="7">Tiada rekod</td>
           </tr> 
        #end
           
         </table>
      
    </fieldset> 

      <input type="hidden" name="no_fail"> 
      <input type="hidden" name="id_fail"> 
      <input type="hidden" name="id_permohonan">
      <input type="hidden" name="no_permohonan">     
 
<script language="javascript">
function doChanges() {
	doAjaxCall${formName}("doChanges");
}
function search_data(){
		document.${formName}.method = "POST";
		document.${formName}.command.value = "";
		document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek4HakmilikSenarai";
		document.${formName}.submit();
}
function edit_item(id_fail,id_permohonan) {
	document.${formName}.id_fail.value = id_fail;
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.command.value = "semak";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek4HakmilikSenarai";
	document.${formName}.submit();
}
function cancel() {
	document.${formName}.command.value = "cancel_HMList";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmSek4HakmilikSenarai";
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