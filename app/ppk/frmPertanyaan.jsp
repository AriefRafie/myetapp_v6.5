<br/>
	<fieldset><legend><b>Carian Fail</b></legend>
		
		<table width="100%" align="center" border="0">
		<tr>
      			<td scope="row" align="right">Nama Simati : </td>
      			<td width="70%"><input name="txtNoFail" id="txtNoFail" type="text" 
      			value="$!nofail" size="50" maxlength="100" 
      			style="text-transform:uppercase;" 
      			onBlur="this.value=this.value.toUpperCase();" /></td>
    		</tr>		<tr>
      			<td scope="row" align="right">No Fail : </td>
      			<td width="70%"><input name="txtNoFail" id="txtNoFail" type="text" 
      			value="$!nofail" size="30" maxlength="50" 
      			style="text-transform:uppercase;" 
      			onBlur="this.value=this.value.toUpperCase();" /></td>
    		</tr>
    		<tr>
      			<td scope="row"></td>
      			<td>
      			<input name="cmdSemak" id="cmdSemak" value="Semak" type="button"  onClick="javascript:doAjaxCall${formname}('doCarian')">
        		<input name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" type="reset"></td>
    		</tr>
    		
		</table>
		
	</fieldset>

#if($listSemak_size!=0 && $listSemak_size!="")	
 
<br/>

	<fieldset>
	<legend><strong>Senarai Fail</strong></legend>
		<table width="100%"  cellpadding="1" cellspacing="2" border="0">
			<tr class="table_header">
			<td><b>Bil</b></td>
			<td><b>No Fail</b></td>
			<td><b>Nama Simati</b></td>
			<td><b>Nama Pemohon</b></td>
			</tr>
			
		#foreach($list in $lists)        
                   	#set( $i = $velocityCount )
         		#if ( ($i % 2) != 1 )
              		 #set( $row = "row2" )
         		#else
               		 #set( $row = "row1" )
         		#end
         		
         		<tr>
         		<td class="$row">$i</td>
            		<td class="$row">
            		<a href="#" onClick = "janaFaraid('$list.ID_SIMATI','$list.ID_PERMOHONAN')">$list.NO_FAIL</a></td>
        			
        		 <td class="$row">
			 $list.NAMA_SIMATI</td>
        		
        		 <td class="$row">
			 $list.NAMA_PEMOHON</td>
        		</tr>
       #end
			
		</table>
	</fieldset>

<br/>
#end

<script>
function janaFaraid(id_simati,id_permohonan){
	  
	var url = "../x/${securityToken}/ekptg.faraid.FrmFaraid?id_simati="+id_simati+"&id_permohonan="+id_permohonan;
    var hWnd = window.open(url,'printuser','width=800,height=300, resizable=yes,scrollbars=yes,copyhistory=yes,location=no,directories=no,status=yes,toolbar=no,menubar=no');
      if ((document.window != null) && (!hWnd.opener))
        hWnd.opener = document.window;
      if (hWnd.focus != null) hWnd.focus();

}
</script>
