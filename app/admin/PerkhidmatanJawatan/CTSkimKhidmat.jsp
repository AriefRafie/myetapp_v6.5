<script>
	document.getElementById('div_CT_CARIAN_SKIM').style.display = "";
	document.getElementById('span_LINK_CT_CARIAN_SKIM').style.display = "none";
	//document.getElementById('div_CT_CARIAN_$internalType').className = "classFade";
</script>

	<fieldset>
	<legend>Carian &nbsp;&nbsp;</legend>
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
				
				<input size="50" type="text" id="carianSkimKhidmat" 
				name="carianSkimKhidmat" 
				value="$!carianSkimKhidmat" 
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
				  <p><br>
  <input type="hidden" id="cetakReportSkimKhidmat" name="cetakReportSkimKhidmat" value="$cetakSkimKhidmat" >
				    
  <input type="button" id="cmdCariSkimKhidmat" name="cmdCariSkimKhidmat" value="Cari" onClick="doDivAjaxCall$formname('div_SkimKhidmat','showSenaraiSkimKhidmat','');" >
				    
  <input type="button" id="cmdBatalCariSkimKhidmat" name="cmdBatalCariSkimKhidmat" value="Reset" onClick="$jquery('#carianSkimKhidmat').val('');" >

<!--<input type="button" id="cmdCariTutup" name="cmdCariTutup" value="Tutup" onClick="doDivAjaxCall$formname('div_link_SkimKhidmat','tutuplinkSkimKhidmat','');" >
-->				
<input style="display:none"  type="button" id="cmdCetakSkimKhidmat" name="cmdCetakSkimKhidmat"
onClick="$jquery('#cetakReportSkimKhidmat').val('Y');doDivAjaxCall$formname('SenaraiForPrintSkimKhidmat','showSenaraiSkimKhidmat','FlagCetak=Y');" value="Cetak Laporan" />
			      </p>
			
              </td>
</tr>
</table>
</fieldset>

<br>
</td>
</tr>
</table>


