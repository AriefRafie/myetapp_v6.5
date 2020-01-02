#set($frmtdate = "&nbsp;<i><font color='blue' style='font-size:10px'>dd/mm/yyyy</font></i>")

<center>

<fieldset>
	<legend><strong>Senarai Permohonan</strong></legend>
	
	 #parse("app/utils/record_paging.jsp")
	 
     <table width="100%" cellspacing="2" cellpadding="2" border="0">  
   
   		<tr>
     		<td colspan="2" scope="row"><input type="button" name="cmdDaftar" value ="Daftar Bantahan" onClick="javascript:registerBantahan();"></td>
    	</tr>
    	
        <tr class="table_header">
        	<td align="center"><b>No</b></td>
        	<td><b>No. Rujukan Bantahan</b></td> 
        	<td><b>No. LOT</b></td>
        	<td><b>Tarikh Borang N</b></td>
        	<td><b>Nama Pembantah</b></td>
        </tr>
        
      #if($listBantahan.size()!=0)
           	#foreach($list in $listBantahan)
                #set( $i = $velocityCount )
         		#if ( ($i % 2) != 1 )
              		 #set( $row = "row2" )
         		#else
               		 #set( $row = "row1" )
         		#end
        	<tr>
        		<td class="$row" align="center">$list.bil</td>
        		<td class="$row"><a href="javascript:viewBantahan('$!list.ID_MAINBANTAHAN','$!list.ID_MAINBORANGO')"><font color="blue">$!list.NO_BANTAHAN</font></a></td> 
            	<td class="$row">$list.NO_LOT</td>
            	<td class="$row">$list.TARIKH_BORANGN</td>
            	<td class="$row">$list.NAMA_PEMBANTAH</td>
        	</tr>
        	#end
     #else
        	<tr>
        		<td colspan="6">Tiada rekod</td>
        	<tr>
     #end
        
    </table> 
</fieldset>

<input type="hidden" name="id_bantahan" id="id_bantahan" value="$!id_bantahan">
<input type="hidden" name="id_borango" id="id_borango" value="$!id_borango">
<!-- Anchor -->
<input type="hidden" name="ScreenLocation" value="$!ScreenLocation">
<input type="hidden" name="CursorPoint" value="$!CursorPoint">

</center>

<script>
function viewBantahan(id_bantahan,id_borango) {
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.id_bantahan.value = id_bantahan;
	document.${formName}.id_borango.value = id_borango;
	document.${formName}.command.value = "viewBantahan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.bantahan.FrmBantahan";
	document.${formName}.submit();
}
function registerBantahan() {
	document.${formName}.ScreenLocation.value = "top";
	document.${formName}.command.value = "registerBantahan";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.bantahan.FrmBantahan";
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
