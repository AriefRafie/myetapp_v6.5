<table>
	<tr>
		<td>
			<input type="button" value="Kembali" onclick="kembaliAgih()">
			#if($response.tindakan.category.id =="4" || $response.tindakan.category.id =="5" || $response.tindakan.category.id =="1")
			<input type="button" value="Simpan & Selesai" onclick="updateRespon()">
			<input type="button" value="Hantar e-mel" onclick="parent.location='mailto:?subject=ADUAN'"/>
			#else
				<input type="button" value="Hantar e-mel" onclick="parent.location='mailto:$!response.getTindakan().getGroupEmail()?subject=ADUAN ONLINE NO $complaint.id'"/>
			#end
			
			
		</td>
	
	
	</tr>

</table>
<script>

</script>