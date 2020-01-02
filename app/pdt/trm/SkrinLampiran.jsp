
<table width="100%" border="0"  >
			<tr><td colspan="4" style=" border-top:1px solid black;" ></td></tr>
			<tr>
			<td valign="top"  width="1%"></td>
			<td valign="top"  width="28%"></td>
			<td valign="top"  width="1%"></td>
			<td valign="top"  width="70%">
			</td>
			</tr>
            
            
            <tr>
				<td valign="top" >
               	
				</td>			
				<td valign="top" >
				$label_lampiran
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
                
                <div id="ShowFieldFile$view.ID_WARTATRM"> 
                
                <input size="50" type="file" id="AddLampiran$view.ID_WARTATRM" name="AddLampiran$view.ID_WARTATRM"  onClick="valEditUpload(event,'$view.ID_WARTATRM','$mode','$view.BIL','$view.rowCss','$view.ID_STATUS','$view.ID_PENGADU');" >           
                <div class="blink"><font color="red">$alert_cannot_exceed_3MB</font></div>
                
                </div>
                
                #if($flagUpload=="Y")
                <script>
				//page autoload from autosave to call click function on upload field
				$jquery(document).ready(function () {
				//alert('a');
				   $jquery('#AddLampiran$view.ID_WARTATRM').trigger('click');
				});
				</script>
                #end
                
                
                
                <div id="divSenaraiLampiran$view.ID_WARTATRM">
                 <script>
				 $jquery(document).ready(function () {
							 doDivAjaxCall3$formname('divSenaraiLampiran$view.ID_WARTATRM','showLampiran','ID_WARTATRM=$view.ID_WARTATRM&ID_STATUS=$view.ID_STATUS&ID_PENGADU=$view.ID_PENGADU&mode=$mode');			 	  
				 });	
				</script>
                </div>
				</td>
			</tr>
</table>