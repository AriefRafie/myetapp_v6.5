<table>
	<tr>
		<td>
			<select name="category" onchange="changeCategory()">
				#foreach($category in $categories)
					<option value="$category.id">$category.name</option>
				
				#end
			</select>
		</td>
	</tr>


</table>
<script>
function changeCategory(){
	doAjaxCall${formName}("viewCategory");
	
}
function simpan(){
	doAjaxCall${formName}("saveCategory");
	
}

</script>