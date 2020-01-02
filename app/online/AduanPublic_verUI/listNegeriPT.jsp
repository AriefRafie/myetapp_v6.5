#set($displayTrNegeriPT = "none")
#set($displayTrPejabatTanah = "none")
#if($ID_JENISTINDAKAN == "16173" || $ID_JENISTINDAKAN == "16174")
    #set($displayTrNegeriPT = "")
    #set($displayTrPejabatTanah = "")
    
#end

#set($displayTrDaerahPT = "none")
#if($ID_JENISTINDAKAN == "16174")
    #set($displayTrDaerahPT = "")
#end


<script>
//alert("s : $displayTrBahagian2");
document.getElementById('tr_ID_NEGERI_PT_$ID_ADUANPUBLIC').style.display = "$displayTrNegeriPT";
document.getElementById('tr_ID_DAERAH_PT_$ID_ADUANPUBLIC').style.display = "$displayTrDaerahPT";
document.getElementById('tr_ID_PEJABATTANAH_$ID_ADUANPUBLIC').style.display = "$displayTrPejabatTanah";
//alert(document.getElementById('tr_ID_BAHAGIAN_$ID_ADUANPUBLIC').style.display);
document.getElementById('ID_NEGERI_PT_$ID_ADUANPUBLIC').value = "";
document.getElementById('ID_DAERAH_PT_$ID_ADUANPUBLIC').value = "";
document.getElementById('ID_PEJABATTANAH_$ID_ADUANPUBLIC').value = "";
</script>

<select id="ID_NEGERI_PT_$ID_ADUANPUBLIC" name="ID_NEGERI_PT_$ID_ADUANPUBLIC" 
 onChange = "doDivAjaxCall3$formname('tdDaerahPT$ID_ADUANPUBLIC','showDaerahPT','ID_ADUANPUBLIC=$ID_ADUANPUBLIC&mode=edit&ID_JENISTINDAKAN=$ID_JENISTINDAKAN&ID_NEGERI='+this.value);">	   
					   <option value = "" >$label_sila_pilih</option>
						#foreach ( $N in $listNegeriPT)		
							#set ( $selected_N = "" )
                            					
                            <option $selected_N value="$N.ID_NEGERI" >
                            $N.NAMA_NEGERI
                            </option>	
                            
                                                
						#end
					</select>	