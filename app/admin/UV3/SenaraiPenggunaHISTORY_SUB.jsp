
<script>
document.getElementById("div_viewHISTORY_$internalType$ID_SEJARAHPENGGUNAUTAMA").style.display="";
if( $jquery('#'+'div_mainRowHISTORY_$internalType$ID_SEJARAHPENGGUNAUTAMA').length ) 
{
	window.scrollTo(0, $jquery('#'+'div_mainRowHISTORY_$internalType$ID_SEJARAHPENGGUNAUTAMA').offset().top);
}

</script>

 <script>
 var carianHistory = $jquery('#carianTerperinciHISTORY_$internalType$USER_ID').val(); 
 //alert("carianTerperinciHISTORY_$internalType$USER_ID"+"carian : "+$jquery('#carianTerperinciHISTORY_$internalType$USER_ID').val()+" total rekod : "+'$listPengunaHISTORY_SUB.size()'+" nama list : "+'listPengunaHISTORY_SUB');
 highlight('$listPengunaHISTORY_SUB.size()',carianHistory,'listPengunaHISTORY_SUB'); 
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
			#foreach($userHISTORY in $listPengunaHISTORY_SUB)
			
			<tr >
			   <td class="$userHISTORY.rowCss"  align="left" valign="top" >
			   #set($span1 = "span1listPengunaHISTORY_SUB"+$userHISTORY.BIL)
			   <span id="$span1">$userHISTORY.REKOD_LABEL</span>
			   </td>
			   <td class="$userHISTORY.rowCss"  align="left" valign="top" >
			   #set($span2 = "span2listPengunaHISTORY_SUB"+$userHISTORY.BIL)
			   <span id="$span2">$userHISTORY.REKOD_SEBELUM</span>
			   </td>
			   <td class="$userHISTORY.rowCss"  align="left" valign="top" >
			   #set($span3 = "span3listPengunaHISTORY_SUB"+$userHISTORY.BIL)
			   <span id="$span3">$userHISTORY.REKOD_SELEPAS</span>			   
			   </td>	
			   #set($span4 = "span4listPengunaHISTORY_SUB"+$userHISTORY.BIL)
			   <span id="$span4"></span>
			   #set($span5 = "span5listPengunaHISTORY_SUB"+$userHISTORY.BIL)
			   <span id="$span5"></span>
			   #set($span6 = "span6listPengunaHISTORY_SUB"+$userHISTORY.BIL)
			   <span id="$span6"></span>
			   #set($span7 = "span7listPengunaHISTORY_SUB"+$userHISTORY.BIL)
			   <span id="$span7"></span>		   	 
			</tr>
			#end
			<tr>
				
				<td valign="top" colspan="3">
				<input type="button" id="BTNCLOSEHISTORY$internalType$ID_SEJARAHPENGGUNAUTAMA" name="BTNCLOSEHISTORY$internalType$ID_SEJARAHPENGGUNAUTAMA" 
				onClick="doDivAjaxCall$formname('div_viewHISTORY_$internalType$ID_SEJARAHPENGGUNAUTAMA','close_viewHistory','internalType=$internalType&ID_SEJARAHPENGGUNAUTAMA=$ID_SEJARAHPENGGUNAUTAMA');" value="Tutup" > 
	   		
				</td>
			</tr>
			</table>
		</fieldset>
		
		
		<br>
		
		</td>
</tr>