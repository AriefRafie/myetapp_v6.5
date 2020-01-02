<select name="socLuas" style="width:200px;" 
$readonly class="$disabled" $disabled 
onchange="javascript:doChangeLuas(this.value)">
#set ($listUnitLuas = ["SILA PILIH",
		       "KM - KILOMETER PERSEGI",
		       "H - HEKTAR",
		       "M - METER PERSEGI",
		       "E - EKAR,ROOD,POLE",
		       "K - KAKI PERSEGI",
		       "P - EKAR PERPULUHAN",
		       "D - EKAR,DEPA",
		       "R - RELONG,JEMBA,KAKI PERSEGI",
		       "BN - BATU NAUTIKA"]
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

