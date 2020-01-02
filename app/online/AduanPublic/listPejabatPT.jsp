#set($displayTrPejabatPT = "none")
#if($ID_JENISTINDAKAN == "16174" || $ID_JENISTINDAKAN == "16173")
    #set($displayTrPejabatPT = "")
#end
<script>
//alert("s : $displayTrBahagian2");
document.getElementById('tr_ID_PEJABATTANAH_$ID_ADUANPUBLIC').style.display = "$displayTrPejabatPT";
document.getElementById('ID_PEJABATTANAH_$ID_ADUANPUBLIC').value = "";
//alert(document.getElementById('tr_ID_BAHAGIAN_$ID_ADUANPUBLIC').style.display);
</script>
<select id="ID_PEJABATTANAH_$ID_ADUANPUBLIC" name="ID_PEJABATTANAH_$ID_ADUANPUBLIC" >	   
					   <option value = "" >$label_sila_pilih</option>
						#foreach ( $N in $listPejabatPT)		
							#set ( $selected_N = "" )                            					
                            <option $selected_N value="$N.ID_PEJABAT" >
                            $N.NAMA_PEJABAT
                            </option>	        
						#end
					</select>	