

<select id="ID_STATUS_UI_$ID_ADUANPUBLIC" name="ID_STATUS_UI_$ID_ADUANPUBLIC"
 onchange="renameButtonHantar('BTNHANTAR$ID_ADUANPUBLIC',this.value);"
 >	   
					   #foreach ( $N in $listStatus)		
							#set ( $selected_N = "" )
							#if($ID_STATUS==$N.ID_STATUS)
							#set ( $selected_N = "selected" )
							#end
							<option $selected_N value="$N.ID_STATUS" >
							$N.KETERANGAN
							</option>							
						#end
</select>	



 			#set($displayTrEmelBahagian = "none")
            #if($ID_JENISTINDAKAN != "16176" && $ID_JENISTINDAKAN != "")
            	#set($displayTrEmelBahagian = "")
            #end
            
            #set($displayAlertEmelBahagian = "none")
            #if($ID_JENISTINDAKAN != "16172" && $ID_JENISTINDAKAN != "16171" && $ID_JENISTINDAKAN != "16176" && $ID_JENISTINDAKAN != "")
             	#set($displayAlertEmelBahagian = "")
            #end 
            
            #set($displayAlertSumberBahagian = "none")
            #set($displayTipsSumberBahagian = "none")
            #if($ID_JENISTINDAKAN == "16172" || $ID_JENISTINDAKAN == "16171")
            	#set($displayAlertSumberBahagian = "")
                #set($displayTipsSumberBahagian = "")
            #end
             
           
            <script>
			document.getElementById('emel_bahagian_alert_$ID_ADUANPUBLIC').style.display = "$displayAlertEmelBahagian";
			document.getElementById('sumber_bahagian_alert_$ID_ADUANPUBLIC').style.display = "$displayAlertSumberBahagian";
			document.getElementById('sumber_bahagian_tips_$ID_ADUANPUBLIC').style.display = "$displayTipsSumberBahagian";
			document.getElementById('tr_EMEL_BAHAGIAN_$ID_ADUANPUBLIC').style.display = "$displayTrEmelBahagian";	
			

			$jquery(document).ready(function () {
				renameButtonHantar('BTNHANTAR$ID_ADUANPUBLIC','$ID_STATUS');
				
				doDivAjaxCall3$formname('tdSENARAI_PEGAWAIBAHAGIAN$ID_ADUANPUBLIC','showPegawaiBahagian','ID_ADUANPUBLIC=$ID_ADUANPUBLIC&mode=edit&ID_SUMBERTINDAKAN='+document.getElementById('ID_SUMBERTINDAKAN_$ID_ADUANPUBLIC').value+'&ID_NEGERI='+document.getElementById('ID_NEGERI_BAHAGIAN_$ID_ADUANPUBLIC').value+'&ID_BAHAGIAN='+document.getElementById('ID_BAHAGIAN_$ID_ADUANPUBLIC').value);		
				
			});
			
			</script>