
<!--
SKRIN ::: $docType
-->



#if($docType=="PENGADU")
#set($mode = $mode)
#elseif($docType=="UI")
#set($mode = $modeUI)
#elseif($docType=="BAHAGIAN")
#set($mode = $modeBahagian)
#end

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
                
                <div id="ShowFieldFile$docType$view.ID_ADUANPUBLIC"> 
                <!-- ::::::::: AddLampiran$docType$view.ID_ADUANPUBLIC -->
                <input size="50" type="file" id="AddLampiran$docType$view.ID_ADUANPUBLIC" name="AddLampiran$docType$view.ID_ADUANPUBLIC"  onClick="valEditUpload(event,'$view.ID_ADUANPUBLIC','$mode','$view.BIL','$view.rowCss','$view.ID_STATUS','$view.ID_PENGADU','$view.ID_PEGAWAI_UI','$view.ID_PEGAWAI_BAHAGIAN','$docType');" >           
                <div class="blink"><font color="red">$alert_cannot_exceed_3MB</font></div>
                
                </div>
                
                #if($flagUpload=="Y" && $docType == "PENGADU")
                <script>
				//page autoload from autosave to call click function on upload field
				$jquery(document).ready(function () {
				//alert('a');
				   $jquery('#AddLampiran$docType$view.ID_ADUANPUBLIC').trigger('click');
				});
				</script>
                #end
                
              
                <!-- ::::::: divSenaraiLampiran$docType$view.ID_ADUANPUBLIC  --> 
                <div id="divSenaraiLampiran$docType$view.ID_ADUANPUBLIC">
                  
                #if($command == "editRecreateLog" && $docType == "PENGADU")               
                <script>
				//alert('1 : $docType');
                doDivAjaxCall3$formname('div_row$ID_ADUANPUBLICASAL','close','ID_ADUANPUBLIC=$ID_ADUANPUBLICASAL&ID_ADUANPUBLICBARU=$view.ID_ADUANPUBLIC&commandFrom=list&BIL=$BIL&rowCss=$rowCss');			
                </script>
                #else
                <!-- ::::: $docType divSenaraiLampiran$docType$view.ID_ADUANPUBLIC -->
                 <script>
				 $jquery(document).ready(function () {
					// alert('2 : $docType');
							 doDivAjaxCall3$formname('divSenaraiLampiran$docType$view.ID_ADUANPUBLIC','showLampiran','ID_ADUANPUBLIC=$view.ID_ADUANPUBLIC&ID_STATUS=$view.ID_STATUS&ID_PENGADU=$view.ID_PENGADU&ID_PEGAWAI_UI=$view.ID_PEGAWAI_UI&ID_PEGAWAI_BAHAGIAN=$view.ID_PEGAWAI_BAHAGIAN&mode=$mode&docType=$docType');		
							 
							 //alert('$mode');
							 if('$docType'=="UI" && '$mode' == 'edit')
							 {
							 
							 doDivAjaxCall3$formname('tdSENARAI_PEGAWAIBAHAGIAN$view.ID_ADUANPUBLIC','showPegawaiBahagian','ID_ADUANPUBLIC=$view.ID_ADUANPUBLIC&mode=edit&ID_SUMBERTINDAKAN='+document.getElementById('ID_SUMBERTINDAKAN_$view.ID_ADUANPUBLIC').value+'&ID_NEGERI='+document.getElementById('ID_NEGERI_BAHAGIAN_$view.ID_ADUANPUBLIC').value+'&ID_BAHAGIAN='+document.getElementById('ID_BAHAGIAN_$view.ID_ADUANPUBLIC').value+'&modeUI=$mode');		
							 }
			
				 	  
				 });	
				 
				 
				 
				</script>
                #end
                </div>
				</td>
			</tr>
</table>