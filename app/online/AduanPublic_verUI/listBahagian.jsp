
#set($displayTrBahagian2 = "none")
#if($ID_JENISTINDAKAN != "" && ($ID_JENISTINDAKAN == "16171" || $ID_JENISTINDAKAN == "16172"))
    #set($displayTrBahagian2 = "")
#end
<script>
//alert("s : $displayTrBahagian2");
document.getElementById('tr_ID_BAHAGIAN_$ID_ADUANPUBLIC').style.display = "$displayTrBahagian2";
//alert(document.getElementById('tr_ID_BAHAGIAN_$ID_ADUANPUBLIC').style.display);
</script>

<select id="ID_BAHAGIAN_$ID_ADUANPUBLIC" name="ID_BAHAGIAN_$ID_ADUANPUBLIC"

 onChange = "doDivAjaxCall3$formname('tdSENARAI_PEGAWAIBAHAGIAN$ID_ADUANPUBLIC','showPegawaiBahagian','ID_ADUANPUBLIC=$ID_ADUANPUBLIC&mode=edit&ID_SUMBERTINDAKAN='+document.getElementById('ID_SUMBERTINDAKAN_$ID_ADUANPUBLIC').value+'&ID_NEGERI='+document.getElementById('ID_NEGERI_BAHAGIAN_$ID_ADUANPUBLIC').value+'&ID_BAHAGIAN='+document.getElementById('ID_BAHAGIAN_$ID_ADUANPUBLIC').value);"


 >	
   
					   <option value = "" >$label_sila_pilih</option>
						#foreach ( $N in $listBahagian)		
							#set ( $selected_N = "" )							
							<option $selected_N value="$N.ID_SEKSYEN" >
							$N.NAMA_SEKSYEN
							</option>							
						#end
</select>

<script>
//var id_status = document.getElementById('ID_STATUS_UI_$ID_ADUANPUBLIC').value;
//alert(id_status);
$jquery(document).ready(function () {
	//renameButtonHantar('BTNHANTAR$ID_ADUANPUBLIC',id_status);
	doDivAjaxCall3$formname('tdSumberTindakan$ID_ADUANPUBLIC','showSumberBahagian','ID_ADUANPUBLIC=$ID_ADUANPUBLIC&mode=edit&ID_JENISTINDAKAN=$ID_JENISTINDAKAN');

	doDivAjaxCall3$formname('tdNegeriBahagian$ID_ADUANPUBLIC','showNegeriBahagian','ID_ADUANPUBLIC=$ID_ADUANPUBLIC&mode=edit&ID_JENISTINDAKAN=$ID_JENISTINDAKAN');

	doDivAjaxCall3$formname('tdNegeriPT$ID_ADUANPUBLIC','showNegeriPT','ID_ADUANPUBLIC=$ID_ADUANPUBLIC&mode=edit&ID_JENISTINDAKAN=$ID_JENISTINDAKAN');

	doDivAjaxCall3$formname('tdStatusAduanUI$ID_ADUANPUBLIC','showStatusSkrinUI','ID_ADUANPUBLIC=$ID_ADUANPUBLIC&mode=edit&ID_JENISTINDAKAN=$ID_JENISTINDAKAN');
});
</script>
