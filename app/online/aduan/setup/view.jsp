<table>
	<tr>
		<td>
			<table>
				<tr>
					<td>
					
					
					</td>
					<td>
						<input type="text" name="category" size="10" readonly value="$category.id">
					</td>
				</tr>
				<tr>
					<td>
						Negeri
					
					</td>
					<td>
						<select name="negeri">
						#foreach($state in $states)
							<option value="$state.idNegeri">$state.namaNegeri</option>
						#end
						</select>
					
					</td>
					<td>
						Seksyen
					
					</td>
					<td>
						<select name="seksyen">
						#foreach($seksyen in $sections)
							<option value="$seksyen.idSeksyen">$seksyen.kodSeksyen - $seksyen.namaSeksyen</option>
						#end
						</select>
					
					</td>
				
				</tr>
				<tr>
					<td>
						E-Mail
					
					</td>
					<td>
						<input type="text" name="email" size="100">
					
					</td>
				</tr>
				<tr>
					<td>
						Description
					
					</td>
					<td>
						<input type="text" name="description" size="100">
					
					</td>
				</tr>
				<tr>
					<td>
						<input type="button" value="simpan" onclick="simpan()">
					
					</td>
				
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td>
			<table>
				<tr>
					<td>
						
					
					</td>
				
				</tr>
				#foreach($cat in $category.actions)
				<tr>
					<td>
						
						$cat.name $cat.groupEmail
					</td>
				
				</tr>
				
				
				#end
			
			</table>
		
		</td>
	
	
	</tr>


</table>