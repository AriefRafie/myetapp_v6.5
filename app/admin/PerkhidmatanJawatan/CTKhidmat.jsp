<script>
	document.getElementById('div_CT_CARIAN_KUMPKHIDMAT').style.display = "";
	document.getElementById('span_LINK_CT_CARIAN_KUMPKHIDMAT').style.display = "none";
	//document.getElementById('div_CT_CARIAN_$internalType').className = "classFade";
</script>

	<fieldset>
	<legend>Carian</legend>
	<table width="100%" align="center" border="0" cellpadding="0" cellspacing="2">	
	<tr>
			<td valign="top"  width="1%"></td><td valign="top"  width="28%"></td><td valign="top"  width="1%"></td><td valign="top"  width="70%"><!-- $viewPengguna --></td>
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
				
				<input size="50" type="text" id="carianKhidmat" 
				name="carianKhidmat" 
				value=$!carianKhidmat
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
<input type="hidden" id="cetakReportKhidmat" name="cetakReportKhidmat" value="$cetakReportKhidmat" >
        
<input type="button" id="cmdCariKhidmat" name="cmdCariKhidmat" value="Cari" onClick="$jquery('#carianUmum').val('');doDivAjaxCall$formname('div_KumpKhidmat','showSenaraiKhidmat','');" >
                
<input type="button" id="cmdBatalCariKhidmat" name="cmdBatalCariKhidmat" value="Reset" onClick="$jquery('#carianKhidmat').val('');" >
<!--
<input type="button" id="cmdCariTutup" name="cmdCariTutup" value="Tutup" onClick="doDivAjaxCall$formname('div_link_Khidmat','tutuplinkKhidmat','');" >-->

<input style="display:none"  type="button" id="cmdCetakKhidmat" name="cmdCetakKhidmat"
onClick="$jquery('#cetakReportKhidmat').val('Y');doDivAjaxCall$formname('SenaraiForPrintKhidmat','showSenaraiKhidmat','FlagCetak=Y');" value="Cetak Laporan" />
			
</td>
</tr>
</table>
</fieldset>

<br>
</td>
</tr>
</table>

