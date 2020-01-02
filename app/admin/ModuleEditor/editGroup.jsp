<tr 
class="table_header" 
>
<td class="underline_td_main" >
</td>
<td class="underline_td_main" align="left" valign="top" >
<input type="text" id="textGroup$MODULE_GROUP" name="textGroup$MODULE_GROUP" value="$MODULE_GROUP_ASAL">

<input 
type="button" id="UpdateGroupName" 
name="UpdateGroupName" 
onClick="if(simpanGroupName('$MODULE_GROUP')==true){doDivAjaxCall$formname('div_senaraiUtama','carianUtama','MODULE_GROUP=$MODULE_GROUP&MODULE_GROUP_ASAL=$MODULE_GROUP_ASAL&FLAG_UPDATE_GROUP=Y&carianTerperinci='+$jquery('#carianTerperinci').val());}" 
value="Simpan" > 

</td>
<td class="underline_td_sub" align="right" valign="top" colspan="2">


</td>
</tr>