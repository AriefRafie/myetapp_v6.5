#set($displayTrSumberBahagian2 = "none")
#if($ID_JENISTINDAKAN != "" && ($ID_JENISTINDAKAN != "16176"))
    #set($displayTrSumberBahagian2 = "")
#end
<script>
//alert("s : $displayTrBahagian2");
document.getElementById('tr_ID_SUMBERTINDAKAN_$ID_ADUANPUBLIC').style.display = "$displayTrSumberBahagian2";
//alert(document.getElementById('tr_ID_BAHAGIAN_$ID_ADUANPUBLIC').style.display);
</script>




<select id="ID_SUMBERTINDAKAN_$ID_ADUANPUBLIC" name="ID_SUMBERTINDAKAN_$ID_ADUANPUBLIC" 
onchange="doDivAjaxCall3$formname('tdStatusAduanUI$ID_ADUANPUBLIC','showStatusSkrinUI','ID_ADUANPUBLIC=$ID_ADUANPUBLIC&mode=edit&ID_JENISTINDAKAN=$ID_JENISTINDAKAN&ID_SUMBERTINDAKAN='+this.value);"

onBlur="doDivAjaxCall3$formname('tdStatusAduanUI$ID_ADUANPUBLIC','showStatusSkrinUI','ID_ADUANPUBLIC=$ID_ADUANPUBLIC&mode=edit&ID_JENISTINDAKAN=$ID_JENISTINDAKAN&ID_SUMBERTINDAKAN='+this.value);"

>	   
					   <option value = "" >$label_sila_pilih</option>
						#foreach ( $N in $listJenisSumberBahagian)		
							#set ( $selected_N = "" )
                            	<option $selected_N value="$N.ID_SUMBERADUAN" >
                                $N.NAMA_SUMBER
                                </option> 	
						#end
</select>
<br />
 <font color="blue" class="blink" id="sumber_bahagian_tips_$ID_ADUANPUBLIC">* $TipsSumberBahagian</font>

