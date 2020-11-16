fm perintah perbicaraan ha.jsp

$maklumatHA

<table width="100%" border="0" cellspacing="2" cellpadding="2">
	<tr>
		<td>&nbsp;</td>
	   	<td>Jenis Harta Alih</td>
	   	<td valign="top">:</td>
	   	<td>$maklumatHA.jenisHartaAlih $maklumatHA.keterangan</td>
	</tr>
 	<tr>
   		<td width="1%"><span class="red">*</span></td>
   		<td width="20%">Jenis Perintah</td>
   		<td valign="top" width="1%">:</td>
   		<td width="78%">
   			<select name="jenisPerintahHTAAH">
   				<option value="">SILA PILIH</option>
	   			#foreach ($jenisPerintah in $jenisPerintahs)
	   				#set ($selected = "")
	   				#if ($beanMaklumatHA.idJenisPerintah == $jenisPerintah.IdJenisperintah)
	   					#set ($selected = "selected ")
	   				#end
	   				<option $selected value='$jenisPerintah.IdJenisperintah'>$jenisPerintah.kod - $jenisPerintah.keterangan</option>
	   			#end
   			</select>
   		</td>
 	</tr>
 	<tr>
        <td valign="top">&nbsp;</td>
        <td valign="top">Catatan</td>
        <td valign="top">:</td>
        <td>
        	<textarea name="txtCatatanHTA" cols="45" rows="5" id="txtCatatanHTA"
        		class="$inputTextClass"></textarea>
        </td>
	</tr>
	<tr>
    	<td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>
			<input type="button" name="updatePerintahPerbicaraanHA" id="updatePerintahPerbicaraanHA"
				value="Kemaskini" onClick="javascript:simpanKemaskiniHA()"/>
			<input type="button" name="cmdBatalHA" id="cmdBatalHA" value="Kembali" onClick="javascript:batalHA()"/>
		</td>
	</tr>
</table>