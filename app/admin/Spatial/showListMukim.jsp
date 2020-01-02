<td valign="top" id="div_MUKIM_ID">
<select id="ID_MUKIM"  name="ID_MUKIM">	   
<option value = "" >SILA PILIH</option>
#foreach ( $ruj in $list_TBLRUJMUKIM )		
#set ( $selected_ruj = "" )
<option $selected_ruj value="$ruj.ID" >
$ruj.KETERANGAN
</option>					
#end
</select>
</td>