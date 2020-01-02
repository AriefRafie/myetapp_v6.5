#set($displayTrDaerahPT = "none")
#if($ID_JENISTINDAKAN == "16174")
    #set($displayTrDaerahPT = "")
#end
<script>
//alert("s : $displayTrBahagian2");
document.getElementById('tr_ID_DAERAH_PT_$ID_ADUANPUBLIC').style.display = "$displayTrDaerahPT";
document.getElementById('ID_DAERAH_PT_$ID_ADUANPUBLIC').value = "";
document.getElementById('ID_PEJABATTANAH_$ID_ADUANPUBLIC').value = "";
//alert(document.getElementById('tr_ID_BAHAGIAN_$ID_ADUANPUBLIC').style.display);


</script>

#if($ID_JENISTINDAKAN == "16173")
<script>

$jquery(document).ready(function () {
	doDivAjaxCall3$formname('tdPejabatPT$ID_ADUANPUBLIC','showPejabatPT','ID_ADUANPUBLIC=$ID_ADUANPUBLIC&mode=edit&ID_JENISTINDAKAN=$ID_JENISTINDAKAN&ID_NEGERI=$ID_NEGERI&ID_DAERAH=');
});
</script>
#end


<select id="ID_DAERAH_PT_$ID_ADUANPUBLIC" name="ID_DAERAH_PT_$ID_ADUANPUBLIC" 
onChange = "doDivAjaxCall3$formname('tdPejabatPT$ID_ADUANPUBLIC','showPejabatPT','ID_ADUANPUBLIC=$ID_ADUANPUBLIC&mode=edit&ID_JENISTINDAKAN=$ID_JENISTINDAKAN&ID_NEGERI=$ID_NEGERI&ID_DAERAH='+this.value);">	
>	   
					   <option value = "" >$label_sila_pilih</option>
						#foreach ( $N in $listDaerahPT)		
							#set ( $selected_N = "" )                            					
                            <option $selected_N value="$N.ID_DAERAH" >
                            $N.NAMA_DAERAH
                            </option>	        
						#end
					</select>	