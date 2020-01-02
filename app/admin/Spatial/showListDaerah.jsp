<td valign="top" id="div_DAERAH_ID">
<select id="ID_DAERAH"  name="ID_DAERAH"
onChange = "doDivAjaxCall$formname('divDropdownMukim','showListMukim','ID_DAERAH='+$jquery('#ID_DAERAH').val());">	   
<option value = "" >SILA PILIH</option>
#foreach ( $ruj in $list_TBLRUJDAERAH )		
#set ( $selected_ruj = "" )
<option $selected_ruj value="$ruj.ID" >
$ruj.KETERANGAN
</option>					
#end
</select>
</td>