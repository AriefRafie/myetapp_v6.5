#foreach($beanMaklumatTanah in $BeanMaklumatTanah)
<fieldset>
	<legend>
		<b>TINDAKAN</b>
	</legend>
	<table align="center" width="100%">
		<tr>
			<td align="right" valign="top"">TINDAKAN</td>
			<td width="3%" valign="top">:</td>
          	<td valign="top">
          		#if($hitButton != 'kemaskiniMaklumatTanah')
	          		<!-- <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onClick="javascript:goToKemaskini('$idKategoriPemohonTKR','$idAgensiTKR','$idNegeriTKRJKPTG')" /> -->
	    			<input type="button" name="cmdHapusFail" id="cmdHapusFail" value="Hapus Fail" onClick="javascript:gotoHapusFail()" />
	    			<input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onClick="javascript:gotoCetak('tableReportTanah')" />
    			#else
    				<input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onClick="javascript:gotoSimpan()" />
	    			<input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onClick="javascript:gotoBatal()" />
    			#end
			</td>
		</tr>     
</table>
</fieldset>

#end
