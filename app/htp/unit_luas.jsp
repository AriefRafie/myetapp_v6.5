<select name="socLuas" style="width:200px;" 
	$readonly class="$disabled" $disabled 
	onchange="javascript:doChangeKodLuas(this.value)">
	#set ($listUnitLuas = ["SILA PILIH",
			       "KILOMETER PERSEGI",
			       "HEKTAR",
			       "METER PERSEGI",
			       "EKAR,ROOD,POLE",
			       "KAKI PERSEGI",
			       "EKAR",
			       "EKAR,DEPA",
			       "RELONG,JEMBA,KAKI PERSEGI",
			       "BATU NAUTIKA"]
	      )
	#set( $counter = 0 )
	#foreach ($i in $listUnitLuas)
	
	#if ($socLuas == $counter) 
		<option selected value="$counter">$i</option>
	#else
		<option value="$counter">$i</option>
	#end
	
	#set ($counter = $counter+1)

#end
</select>

