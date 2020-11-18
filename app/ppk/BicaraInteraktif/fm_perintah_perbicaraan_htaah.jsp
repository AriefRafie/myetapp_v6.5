<table width="100%" border="0" cellspacing="2" cellpadding="2">
	<tr>
		<td>&nbsp;</td>
	   	<td>Keterangan Hakmilik</td>
	   	<td valign="top">:</td>
	   	<td>$beanMaklumatHTA.keteranganHakmilik</td>
	</tr>
	<tr>
	   	<td>&nbsp;</td>
	   	<td>Status Pemilikan</td>
	   	<td valign="top">:</td>
	   	<td>$beanMaklumatHTA.jenisPB</td>
	 </tr>
 	<tr>
   		<td>&nbsp;</td>
   		<td>Jenis Tanah</td>
   		<td valign="top">:</td>
   		<td>$beanMaklumatHTA.jenisTanah</td>
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
	   				#if ($beanMaklumatHTA.idJenisPerintah == $jenisPerintah.IdJenisperintah)
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
			<input type="button" name="updatePerintahPerbicaraanHTAAH" id="updatePerintahPerbicaraanHTAAH"
				value="Kemaskini" onClick="javascript:simpanKemaskiniHTA()"/>
			<input type="button" name="cmdBatalHTA" id="cmdBatalHTA" value="Kembali" onClick="javascript:batalHTA()"/>
		</td>
	</tr>
</table>