#set($displayTrNegeriBahagian = "none")
#if($ID_JENISTINDAKAN == "16171" || $ID_JENISTINDAKAN == "16172")
    #set($displayTrNegeriBahagian = "")
#end
<script>
//alert("s : $displayTrBahagian2");
document.getElementById('tr_ID_NEGERI_BAHAGIAN_$ID_ADUANPUBLIC').style.display = "$displayTrNegeriBahagian";
//alert(document.getElementById('tr_ID_BAHAGIAN_$ID_ADUANPUBLIC').style.display);
</script>

<select id="ID_NEGERI_BAHAGIAN_$ID_ADUANPUBLIC" name="ID_NEGERI_BAHAGIAN_$ID_ADUANPUBLIC" 
 onChange = "doDivAjaxCall3$formname('tdSENARAI_PEGAWAIBAHAGIAN$ID_ADUANPUBLIC','showPegawaiBahagian','ID_ADUANPUBLIC=$ID_ADUANPUBLIC&mode=edit&ID_SUMBERTINDAKAN='+document.getElementById('ID_SUMBERTINDAKAN_$ID_ADUANPUBLIC').value+'&ID_NEGERI='+document.getElementById('ID_NEGERI_BAHAGIAN_$ID_ADUANPUBLIC').value+'&ID_BAHAGIAN='+document.getElementById('ID_BAHAGIAN_$ID_ADUANPUBLIC').value);"

>	   
					   <option value = "" >$label_sila_pilih</option>
						#foreach ( $N in $listNegeriBahagian)		
							#set ( $selected_N = "" )
                            
                            #if($ID_JENISTINDAKAN == "16171" &&  $N.ID_NEGERI == "16" )	
                            	#set ( $selected_N = "selected" )
                            #end						
                            <option $selected_N value="$N.ID_NEGERI" >
                            $N.NAMA_NEGERI
                            </option>	
                            
                                                
						#end
					</select>	