<script>
if( $jquery('#'+'div_rowPengguna$internalType$viewPejabat.ID_PEJABAT').length )         // use this if you are using id to check
{
	window.scrollTo(0, $jquery('#'+'div_rowPengguna$internalType$viewPejabat.ID_PEJABAT').offset().top);
}
else
{
	
	if( $jquery('#'+'div_TambahJenisPejabat$viewPejabat.ID_PEJABAT').length ) 
	{
		window.scrollTo(0, $jquery('#'+'div_TambahJenisPejabat$viewPejabat.ID_PEJABAT').offset().top);
	}
	
}
</script>

<tr id="div_TambahJenisPejabat$viewPejabat.ID_PEJABAT">
<td align="left" valign="top" colspan="10">

<fieldset>
<legend>Maklumat Jenis Pejabat</legend>
<table width="100%" border="0">
<tr>
<td valign="top"  width="1%"></td><td valign="top"  width="28%"></td>
<td valign="top"  width="1%"></td><td valign="top"  width="70%"><!-- $viewPejabat --></td>
</tr>
<tr>
    <td valign="top" ></td>			
	<td >Kod Jenis Pejabat Urusan</td>
    <td valign="top" >
	:
	</td>
    <td>$viewDetailsJenisPejabat.KOD_JENIS_PEJABAT
    </td>
    </tr>

	<tr>
    <td valign="top" ></td>			
	<td >Nama Jenis Pejabat Urusan</td>
    <td valign="top" >
	:
	</td>
    <td >$viewDetailsJenisPejabat.KETERANGAN
    </td>
    </tr>
	
	<tr>
	<td valign="top" ></td>			
	<td valign="top" >Nama Lain Jenis Pejabat Urusan</td>
	<td valign="top" >:</td>
	<td valign="top" >$viewDetailsJenisPejabat.NAMA_LAIN_JENIS_PEJABAT
	</td>
	</tr>
         
    <tr>
	<td valign="top" ></td>			
	<td valign="top" >Penerangan</td>
	<td valign="top" >:</td>
	<td valign="top" >
	<textarea name="PENERANGAN_LANJUT_$viewPejabat.ID_PEJABAT" cols="40" rows="3" 
	id="PENERANGAN_LANJUT_$viewPejabat.ID_PEJABAT" style="text-transform:uppercase;" readOnly >$viewDetailsJenisPejabat.PENERANGAN_LANJUT</textarea>
	</td>
</tr>
<tr>
<td valign="top" ></td>			
<td valign="top" ></td>
<td valign="top" ></td>
<td valign="top" >

#if ($viewDetailsJenisPejabat.ID_JENISPEJABAT != "")
<input type="button" id="cmdTutupPejabat" name="cmdTutupPejabat" value="Tutup" 
onClick="doDivAjaxCall$formname('div_TambahJenisPejabat$viewPejabat.ID_PEJABAT','close_Pejabat','');">
#else
<input type="button" id="cmdTutupPejabat" name="cmdTutupPejabat" value="Tutup" 
onClick="doDivAjaxCall$formname('div_TambahJenisPejabat$viewPejabat.ID_PEJABAT','close_Pejabat','ID_PEJABAT=$viewPejabat.ID_PEJABAT');doDivAjaxCall$formname('div_DROPDOWNJENISPEJABAT_$viewPejabat.ID_PEJABAT','showListJenisPejabat','ID_JENISPEJABAT='+$jquery('$viewDetailsJenisPejabat.ID_JENISPEJABAT').val());">
#end
 </td>
</tr>	   		
</table>
</fieldset>


	<br>
</td>		
</tr>

