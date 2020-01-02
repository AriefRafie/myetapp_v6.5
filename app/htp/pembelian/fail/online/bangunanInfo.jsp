<fieldset><legend>Maklumat Fail</legend>#parse ("app/htp/pembelian/fail/fileInfo.jsp")</fieldset>
<fieldset>
<legend>Maklumat Bangunan</legend>

<table width="80%">
	<tr>
		<td  colspan="4">
		
		
		
		</td>


	</tr>
	<tr>
	  <td>No Hakmilik</td>
	 
	  <td>
	  	<select name="idHakmilikUrusan">
	  		#foreach($list in $hmu)
	  			<option value="$!list.getIdhakmilikurusan()" #if($!list.getIdhakmilikurusan() == $!bangunan.hakmilikUrusan.idhakmilikurusan) selected #end>$!list.getJenisKeterangan() - $!list.nohakmilik</option>
	  		
	  		#end
	  	</select>
	  </td>
	  
	  
	  <td>No Petak</td>
	  
	  <td><input type="text" name="noPetak" value="$!bangunan.noPetak"></td>
	</tr>
	
	<tr>
	  <td>No Bangunan</td>
	 
	  <td><input type="text" name="noBangunan" value="$!bangunan.noBangunan"></td>
	  
	  
	  <td>No Tingkat</td>
	  
	  <td><input type="text" name="noTingkat" value="$!bangunan.noTingkat"></td>
	</tr>
	<tr>
		<td colspan="4" align="center">
			#if($bangunanMode =="update")
				<input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="updateBangunan()">
			#else
				<input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="simpanBangunan()">
			
			#end
			<input type="button" name="cmdSimpan" id="cmdSimpan" value="Kembali" onclick="kembaliBangunan()">
		
		</td>
	
	</tr>
</table>
</fieldset>
<input type="hidden" name="idBangunan" value="$!bangunan.idBangunan">

<input type="hidden" name="txtidPermohonan" value="$!htpPermohonan.permohonan.getIdPermohonan()"/>
<input type="hidden" name="txtidHtpPermohonan" value="$!htpPermohonan.getIdHtpPermohonan()"/>
<input type="hidden" name="txtidPermohonan2" value="$!urusan.getIdPermohonan()"/>
<input type="hidden" name="txtIdHakmilikUrusan" value="$!urusan.getIdhakmilikurusan()"/>