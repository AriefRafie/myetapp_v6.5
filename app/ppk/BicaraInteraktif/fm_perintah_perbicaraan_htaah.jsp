--- perintah perbicaraan htaah
#foreach($maklumatHTA in $beanMaklumatHTA)
--//  $maklumatHTA
#end

beanMaklumatHTA = $beanMaklumatHTA

$idHTA



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
	   	<td></td>
	 </tr>
 	<tr>
   		<td>&nbsp;</td>
   		<td>Jenis Tanah</td>
   		<td valign="top">:</td>
   		<td></td>
 	</tr>
 	<tr>
   		<td width="1%"><span class="red">*</span></td>
   		<td width="20%">Jenis Perintah</td>
   		<td valign="top" width="1%">:</td>
   		<td width="78%"></td>
 	</tr>
 	<tr>
        <td valign="top">&nbsp;</td>
        <td valign="top">Catatan</td>
        <td valign="top">:</td>
        <td>
        	<textarea name="txtCatatanHTA" cols="45" rows="5" id="txtCatatanHTA" $readonly
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
		</td>
</table>