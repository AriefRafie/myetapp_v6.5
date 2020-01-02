
#set($frmtdate = "&nbsp;<i><font color='blue' style='font-size:10px'>dd/mm/yyyy</font></i>")

<!-- Carian -->
#parse("app/ppt/frmCarianOnlinePPT.jsp")

<br/>
<fieldset>
	<legend>SENARAI PERMOHONAN</legend>
	
	#parse("app/utils/record_paging.jsp")

	<table width="100%"  cellpadding="1" cellspacing="2" border="0">
		<tr>
			<td><i><font style=font-size:10px>Hanya fail yang boleh dibuat Bantahan sahaja yang akan dipaparkan pada senarai ini.</font></i></td>
		</tr>
		<tr>
			<td><i><font style=font-size:10px>Proses Bantahan boleh dibuat setelah Borang H dikeluarkan.</font></i></td>
		</tr>
	</table>

      <table width="100%"  cellpadding="1" cellspacing="2" border="0">
        <tr class="table_header">
        	<td align="center" width="4%"><b>Bil</b></td>
        	<td><b>No.Fail Permohonan</b></td> 
        	<td><b>No.Fail PTG</b></td>
        	<td><b>No.Fail PTD</b></td>
        	<td><b>No.Fail UPT</b></td>
        	<!-- <td><b>Status Permohonan</b></td> -->
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
        		<td class="$row" align="center">$senarai.bil</td>
        		<td class="$row"><a href="javascript:papar_ListHakmilik('$!senarai.id_permohonan','$!senarai.id_fail','$!senarai.id_status')"><font color="blue">$!senarai.no_fail</font></a></td> 
        		<td class="$row"><a href="javascript:papar_ListHakmilik('$!senarai.id_permohonan','$!senarai.id_fail','$!senarai.id_status')"><font color="blue">$!senarai.no_rujukan_ptg</font></a></td>
        		<td class="$row"><a href="javascript:papar_ListHakmilik('$!senarai.id_permohonan','$!senarai.id_fail','$!senarai.id_status')"><font color="blue">$!senarai.no_rujukan_ptd</font></a></td>  
        		<td class="$row"><a href="javascript:papar_ListHakmilik('$!senarai.id_permohonan','$!senarai.id_fail','$!senarai.id_status')"><font color="blue">$!senarai.no_rujukan_upt</font></a></td>  
            	<!-- <td class="$row">$senarai.status</td> -->
          </tr>          
      #end   
      	 <tr>
          	<td colspan="10">&nbsp;</td>
          </tr>
   	  #else  
   		  <tr>
        	<td colspan="9">Maaf, tiada rekod bagi pengambilan tanah untuk membuat bantahan</td>
          <tr>  
   	  #end  
      </table>

<input type="hidden" name="id_fail" />
<input type="hidden" name="id_permohonan"/>
<input type="hidden" name="id_status" /> 
      
</fieldset>

<script>
function doChanges() {
	doAjaxCall${formName}("doChanges");
}

function papar_ListHakmilik(id_permohonan,id_fail,id_status){

	document.${formName}.id_fail.value = id_fail;
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.id_status.value = id_status;
	
	document.${formName}.command.value = "papar_ListHakmilik";
	document.${formName}.method="POST";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanDaftarAPOnline";
	document.${formName}.submit();	
	
}
function clearData() {
	document.${formName}.command.value = "kosong";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanDaftarAPOnline";
	document.${formName}.submit();
}
function kosongkan() {
	document.${formName}.reset();
	document.${formName}.txtNoFail.value = "";
	document.${formName}.tarikh_permohonan.value = "";
	document.${formName}.submit();
}

function search_data(){
	document.${formName}.command.value = "search_data";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanDaftarAPOnline";
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