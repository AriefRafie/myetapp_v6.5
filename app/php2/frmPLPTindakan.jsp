#foreach($beanMaklumatTanah in $BeanMaklumatTanah)
<fieldset>
	<legend>
		<b>TINDAKAN</b>
	</legend>
	<table align="center" width="100%">
		<tr>
			<td width="47%" align="right" valign="top">TINDAKAN</td>
			<td width="2%" valign="top">:</td>
          	<td width="2%" valign="top">
    			<input type="button" name="cmdHapusFail" id="cmdHapusFail" value="Hapus Fail" onClick="javascript:gotoHapusFail()" />
			</td>
          	<td width="2%" valign="top">
				#if($flagFrom=='semakanMaklumatTanah')
	    			<input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="javascript:gotoKembali()" />
				#end	
			</td>		
			<td width="47%" align="right" valign="top"></td>
		</tr>  
	</table>
</fieldset>
#end
