#set($frmtdate = "&nbsp;<i><font color='blue' style='font-size:10px'>dd/mm/yyyy</font></i>")


<center>

<fieldset>
	<legend><strong>Senarai Permohonan</strong></legend>
	
	 #parse("app/utils/record_paging.jsp")
	 
     <table width="100%" cellspacing="2" cellpadding="2" border="0">  
   
   		<tr>
     		<td colspan="2" scope="row"><input type="button" name="cmdDaftar" value ="Daftar Permintaan Ukur" onClick="javascript:registerPU();"></td>
    	</tr>
    	
        <tr class="table_header">
        	<td align="center"><b>No</b></td>
        	<td><b>No.Fail/Rujukan Permintaan Ukur</b></td> 
        	<td><b>No.LOT Baru</b></td>
        	<td><b>No.PA</b></td>
        	<td><b>Tarikh Borang PU</b></td>
        </tr>
        
      #if($listpu.size()!=0)
           	#foreach($list in $listpu)
                #set( $i = $velocityCount )
         		#if ( ($i % 2) != 1 )
              		 #set( $row = "row2" )
         		#else
               		 #set( $row = "row1" )
         		#end
        	<tr>
        		<td class="$row" align="center">$list.bil</td>
        		<td class="$row"><a href="javascript:viewPU('$!list.ID_PU')"><font color="blue">$!list.NO_FAIL_PU</font></a></td> 
        		<td class="$row">$list.NO_LOT_BARU</td>
            	<td class="$row">$list.NO_PA</td>
            	<td class="$row">$list.TARIKH_PU</td>
        	</tr>
        	#end
     #else
        	<tr>
        		<td colspan="6">Tiada rekod</td>
        	<tr>
     #end
        
    </table> 
</fieldset>

<input type="hidden" name="id_pu">

<!-- Anchor -->
<input type="hidden" name="ScreenLocation" value="$!ScreenLocation">
<input type="hidden" name="CursorPoint" value="$!CursorPoint">

</center>

<script>
function viewPU(id_pu) {
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_pu.value = id_pu;
	document.${formName}.command.value = "viewPU";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPUstandalone";
	document.${formName}.submit();
}
function registerPU() {
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.command.value = "registerPU";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmPUstandalone";
	document.${formName}.submit();
}
function clearData() {
	doAjaxCall${formName}("clearData");
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
