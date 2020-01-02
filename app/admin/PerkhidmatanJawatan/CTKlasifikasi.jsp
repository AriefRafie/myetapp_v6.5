<script>
	document.getElementById('div_CT_CARIAN_KLAS').style.display = "";
	document.getElementById('span_LINK_CT_CARIAN_KLAS').style.display = "none";
	//document.getElementById('div_CT_CARIAN_$internalType').className = "classFade";
</script>

	<fieldset>
	<legend>Carian </legend>
	
	<table width="100%" align="center" border="0" cellpadding="0" cellspacing="2">	
	<tr>
			<td valign="top"  width="1%"></td>
			<td valign="top"  width="28%"></td>
			<td valign="top"  width="1%"></td>
			<td valign="top"  width="70%"></td>
			</tr>
			
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Carian 
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				
				<input size="50" type="text" id="carianKlasifikasi" 
				name="carianKlasifikasi" 
				value="$!carianKlasifikasi" 
				>
				
				</td>
			</tr>
	
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				</td>
				<td valign="top" >
				</td>
				<td valign="top" >
				<br>
<input type="hidden" id="cetakReportKlasifikasi" name="cetakReportKlasifikasi" value="$cetakReportKlasifikasi" >

<input type="button" id="cmdCariKlasifikasi" name="cmdCariKlasifikasi" value="Cari" onClick="$jquery('#carianUmum').val('');doDivAjaxCall$formname('div_Klasifikasi','showSenaraiKlasifikasi','');" >

<input type="button" id="cmdBatalCariKlasifikasi" name="cmdBatalCariKlasifikasi" value="Reset" onClick="$jquery('#carianKlasifikasi').val('');" >

<!--<input type="button" id="cmdCariTutup" name="cmdCariTutup" value="Tutup" onClick="doDivAjaxCall$formname('div_link_klasifikasi','tutuplinkKlasifikasi','');" >
-->
<input style="display:none"  type="button" id="cmdCetakKlasifikasi" name="cmdCetakKlasifikasi"
onClick="$jquery('#cetakReportKlasifikasi').val('Y');doDivAjaxCall$formname('SenaraiForPrintKlasifikasi','showSenaraiKlasifikasi','FlagCetak=Y');" value="Cetak Laporan" />

</td>
</tr>
</table>
</fieldset>

<br>
</td>
</tr>
</table>


