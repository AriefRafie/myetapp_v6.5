<p></p>
<fieldset>
	<legend>Senarai Permohonan</legend>
    <table width="100%"  cellpadding="1" cellspacing="2" border="0">
        <tr class="table_header">
          <td width="6%" align="center" style="text-transform:uppercase" scope="row">Bil</td>
            <td width="53%" style="text-transform:uppercase">No Fail Permohonan</td>
            <td width="41%" style="text-transform:uppercase">Tarikh Permohonan</td>
            <!-- <td width="31%" style="text-transform:uppercase">Status Permohonan</td> -->
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
            <td class="$row" ><div align="center">$senarai.bil</div></td>
              <td class="$row"><a href="javascript:papar_ListHakmilik('$senarai.id_fail','$senarai.id_permohonan','$senarai.id_status')"><font color="blue">$senarai.no_fail</font></a></td>  
              <td class="$row">$senarai.tarikh_permohonan</td>
              <!-- <td class="$row">$senarai.status</td> -->
          </tr>          
      #end   
      	 <tr>
          	<td colspan="9">&nbsp;</td>
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

	<table width="100%"  cellpadding="1" cellspacing="2" border="0">
		<tr>
			<td><input type="button" name="cmdKembali" id="cmdKembali" value="Kembali ke Menu Utama" onclick="javascript:menuUtama()" /></td>
		</tr>
	</table>

#set ($portal_role = "online")

<script>
function menuUtama(){
	document.${formName}.action = "$EkptgUtil.getTabID('Menu',$portal_role)?_portal_module=ekptg.view.online.FrmOnlineMenuUtama";
	document.${formName}.submit();
}
function papar_ListHakmilik(id_fail,id_permohonan,id_status){

	document.${formName}.id_fail.value = id_fail;
	document.${formName}.id_permohonan.value = id_permohonan;
	document.${formName}.id_status.value = id_status;
	
	document.${formName}.command.value = "papar_ListHakmilik";
	document.${formName}.method="POST";
	document.${formName}.action = "?_portal_module=ekptg.view.ppt.FrmBantahanDaftarOnline";
	document.${formName}.submit();	
	
}
</script>