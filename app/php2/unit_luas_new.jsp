<select name="socLuas" style="width:200px;" $inputTextClass class="$inputTextClass" onchange="javascript:doChangeLuas(this.value)">
#set ($listUnitLuas = ["SILA PILIH",
		       "H - HEKTAR",
		       "M - METER PERSEGI",
		       "P - EKAR PERPULUHAN"]
      )
#set( $counter = 0 )
#foreach ($i in $listUnitLuas)

#if ($idLuas == $counter) 
	<option selected value="$counter">$i</option>
#else
	<option value="$counter">$i</option>
#end

#set ($counter = $counter+1)

#end
</select>

