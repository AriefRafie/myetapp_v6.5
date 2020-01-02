<table>
	<tr>
		<td>
			No Fail
			
		</td>
		<td>
			No Fail
			
		</td>
	
	</tr>
	#foreach($letak in $perletakhakan)
	<tr>
		<td>
			$letak.noFail
			
		</td>
		<td>
			
			$button.command("viewHakmilik").param("idFail=$letak.idFail").text("View Hakmilik Urusan")
		</td>
	
	</tr>
	#end


</table>