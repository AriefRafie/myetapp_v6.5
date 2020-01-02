<select id="ID_AGENSI"  name="ID_AGENSI" >	   
<option value = "" >SILA PILIH</option>
#foreach ( $ruj in $list_TBLRUJAGENSI)		
    #set ( $selected_ID_AGENSI = "" )
    #if($ID_AGENSI==$ruj.ID)
    #set ( $selected_ID_AGENSI = "selected" )
    #end	
    <option $selected_CT_ID_AGENSI value="$ruj.ID" >
    $ruj.KETERANGAN
    </option>							
#end
</select>
