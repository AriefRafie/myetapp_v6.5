
<script>
document.getElementById("div_viewHISTORY_$ID_SEJARAHWTRMUTAMA").style.display="";
if( $jquery('#'+'div_mainRowHISTORY_$ID_SEJARAHWTRMUTAMA').length ) 
{
	window.scrollTo(0, $jquery('#'+'div_mainRowHISTORY_$ID_SEJARAHWTRMUTAMA').offset().top-10);
}

</script>

 <script>
 var carianHistory = $jquery('#carianTerperinciHISTORY_$USER_ID').val(); 
 //alert("carianTerperinciHISTORY_$USER_ID"+"carian : "+$jquery('#carianTerperinciHISTORY_$USER_ID').val()+" total rekod : "+'$listWARTATRMHISTORY_SUB.size()'+" nama list : "+'listWARTATRMHISTORY_SUB');
 //highlight('$listWARTATRMHISTORY_SUB.size()',carianHistory,'listWARTATRMHISTORY_SUB'); 
 </script>


<tr  >
		<td colspan="9"  >
		
		
		<fieldset>
<legend>AKTIVITI TRANSAKSI : $AKTIVITI  &nbsp;[WAKTU TRANSAKSI : $TARIKH_MASUK]</legend>

<table width="100%" border="0">
		   <tr class="table_header">
		   <td  align="left" valign="top" width="20%" ><b>LABEL</b></td>
		   <td   align="left" valign="top" width="40%"><b>SEBELUM</b></td>
		   <td   align="left" valign="top" width="40%"><b>SELEPAS</b></td>
		   </tr>
			#foreach($wartaHISTORYSUB in $listWARTATRMHISTORY_SUB)
			
			<tr >
			   <td class="$wartaHISTORYSUB.rowCss"  align="left" valign="top" >
			  
              
               #set($span1= "span1listWARTATRMHISTORY_SUB"+$wartaHISTORYSUB.BIL)	   
			   <span id="$span1">$wartaHISTORYSUB.REKOD_LABEL</span>
               #if($carianTerperinciHISTORY != "")
               <script>	
			   highlight_item('$carianTerperinciHISTORY','$span1')		   
			   </script>
               #end
			   </td>
			   <td class="$wartaHISTORYSUB.rowCss"  align="left" valign="top" >
			   
               
               #set($span2= "span2listWARTATRMHISTORY_SUB"+$wartaHISTORYSUB.BIL)	   
			   <span id="$span2">$wartaHISTORYSUB.REKOD_SEBELUM</span>
               #if($carianTerperinciHISTORY != "")
               <script>	
			   highlight_item('$carianTerperinciHISTORY','$span2')		   
			   </script>
               #end
			   </td>
			   <td class="$wartaHISTORYSUB.rowCss"  align="left" valign="top" >
			   
               #set($span3= "span3listWARTATRMHISTORY_SUB"+$wartaHISTORYSUB.BIL)	   
			   <span id="$span2">$wartaHISTORYSUB.REKOD_SELEPAS</span>
               #if($carianTerperinciHISTORY != "")
               <script>	
			   highlight_item('$carianTerperinciHISTORY','$span3')		   
			   </script>
               #end
               		   
			   </td>	
			   	   	 
			</tr>
			#end
			<tr>
				
				<td valign="top" colspan="3">
				<input type="button" id="BTNCLOSEHISTORY$ID_SEJARAHWTRMUTAMA" name="BTNCLOSEHISTORY$ID_SEJARAHWTRMUTAMA" 
				onClick="doDivAjaxCall$formname('div_viewHISTORY_$ID_SEJARAHWTRMUTAMA','close_viewHistory','&ID_SEJARAHWTRMUTAMA=$ID_SEJARAHWTRMUTAMA');" value="Tutup" > 
	   		
				</td>
			</tr>
			</table>
		</fieldset>
		
		
		<br>
		
		</td>
</tr>