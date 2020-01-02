<table border="0" cellpadding="0" cellspacing="0" width="100%"> 
<tr>
    <td width="8" nowrap></td>
    <td>
<fieldset>
<legend>Senarai Modul mengikut Bahagian
</legend>

<table border="0" cellspacing="1" cellpadding="1" width="100%" > 
	#if($listRole.size()>0)
	<tr >
		   <td  align="left" valign="top" colspan="14" >

		   </td>
		     
	</tr>
	#end 

	<tr class="table_header" >
		   <td   align="center" valign="top" width="3%">Bil.</td>
		   <td   align="left" valign="top"  width="45%">Bahagian</td>
		   <td   align="left" valign="top"  width="32%">Jumlah Modul</td>  
	</tr>
	#if($listModulBhgn.size()>0)
	
	
		#foreach($lm in $listModulBhgn)
		<tr id="div_rowRole$GROUPUNIK$lm.ROLEUNIK">
			   <td class="$lm.rowCss"  align="center" valign="top" >$lm.BIL </td>
			   <td class="$lm.rowCss"  align="left" valign="top">
               $lm.NAMA_SEKSYEN
			   </td>
			   <td class="$lm.rowCss"  align="left" valign="top">
			   $lm.JUMLAH
			   </td>
		
		</tr>
		<tr  >
		<td align="left" valign="top" colspan="5" id="div_viewRole$GROUPUNIK$lm.ROLEUNIK">
		
		</td>		
		</tr>
		#end
	#else
		
		<tr >
			   <td  align="left" valign="top" colspan="14" >Tiada Rekod</td>
			     
		</tr>
		
	#end
	</table>

</fieldset>

</td>
</tr>

</table>

<script>
$jquery(document).ready(function () {
	
		  doDivAjaxCall$formname('div_table','showTable','');			 	  
	  });
</script>