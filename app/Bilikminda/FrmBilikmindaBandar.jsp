<select id="bandar" name="bandar">
<option value = "" >Sila Pilih</option>
#foreach ( $bandar in $senarai_bandar)

#set ( $selected_bandar = "" )
#if($id_bandar==$bandar.ID_BANDAR)
#set ( $selected_bandar = "selected" )
#end

<option $selected_bandar value="$bandar.ID_BANDAR" >
$bandar.NAMA_BANDAR
</option>
#end
</select>
