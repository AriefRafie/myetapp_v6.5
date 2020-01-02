<script>
	document.getElementById('div_CT_CARIAN_GRED').style.display = "";
	document.getElementById('span_LINK_CT_CARIAN_GRED').style.display = "none";
	//document.getElementById('div_CT_CARIAN_$internalType').className = "classFade";
</script>

<table width="100%" align="center" border="0" cellpadding="0" cellspacing="0">
<tr  >
<td  width="100%"  valign="top">
	
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
        
        <input size="50" type="text" id="CarianGred" 
        name="CarianGred" 
        value="$!CarianGred" 
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
        
<input type="hidden" id="cetakReportGred" name="cetakReportGred" value="$cetakReportGred" >
        

<input type="button" id="cmdCariGred" name="cmdCariGred" value="Cari" onClick="$jquery('#cetakReportGred').val('Y');$jquery('#carianUmum').val('');doDivAjaxCall$formname('div_Gred','showSenaraiGred','');" >

<input type="button" id="cmdBatalCariGred" name="cmdBatalCariGred" value="Reset" onClick="$jquery('#CarianGred').val('');" >

<!--<input type="button" id="cmdCariTutup" name="cmdCariTutup" value="Tutup" onClick="doDivAjaxCall$formname('div_link_gred','tutuplink','');" >
-->
<input style="display:none"  type="button" id="cmdCetakGred" name="cmdCetakGred"
onClick="$jquery('#cetakReportGred').val('Y');doDivAjaxCall$formname('SenaraiForPrint','showSenaraiGred','FlagCetak=Y');" value="Cetak Laporan" />

        
        </td>
    </tr>
</table>

</fieldset>

<br>
</td>
</tr>
</table>

