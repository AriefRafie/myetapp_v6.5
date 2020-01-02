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
 <input type="hidden" id="ID_JENISPEJABAT" name="ID_JENISPEJABAT" value="$viewDetailsJenisPejabat.ID_JENISPEJABAT" >

<!--<tr>
    <td valign="top" ></td>			
	<td >Jenis Pejabat</td>
    <td valign="top" >
	:
	</td>
    <td ><input type="radio" id="radioPejabat" name="radioPejabat" value="1" 
	onClick="hideInsPejUrus();sendValueJenisPejabat(this,this.value,'jenisPejabat');"  >Pejabat JKPTG
	<input type="radio" id="radioPejabat" name="radioPejabat" value="2"
	onClick="hideInsPejUrus();sendValueJenisPejabat(this,this.value,'jenisPejabat');"   >Pejabat Urusan
	
	<input type="hidden" id="jenisPejabat" name="jenisPejabat" value="$jenisPejabat" >	
     </td>
    </tr>
-->    
<div id ="insPejabatUrusan" style="display:none" > 
<tr>
    <td valign="top" ></td>			
	<td >Kod Jenis Pejabat Urusan</td>
    <td valign="top" >
	:
	</td>
    <td >
    <input  size="2" type="text" id="KOD_JENIS_PEJABAT_$viewPejabat.ID_PEJABAT" maxlength="2" style="text-transform:uppercase;"
	name="KOD_JENIS_PEJABAT_$viewPejabat.ID_PEJABAT" value="$viewDetailsJenisPejabat.KOD_JENIS_PEJABAT" 
	onKeyUp="checkKodJenisPjbt(this.value,'$viewPejabat.ID_PEJABAT');">
	<div id="div_CHECK_KODJENISPEJABAT_$viewPejabat.ID_PEJABAT" >
	<input type="hidden" id="CHECK_KOD_$ID_PEJABAT" name="CHECK_KOD_$ID_PEJABAT" value="$checkKod" ></div>
    </td>
    </tr>

	<tr>
    <td valign="top" ></td>			
	<td >Nama Jenis Pejabat Urusan</td>
    <td valign="top" >
	:
	</td>
    <td >
    <input  size="50" type="text" id="KETERANGAN_$viewPejabat.ID_PEJABAT" style="text-transform:uppercase;"
	name="KETERANGAN_$viewPejabat.ID_PEJABAT" value="$viewDetailsJenisPejabat.KETERANGAN" >
    </td>
    </tr>
	
	<tr>
	<td valign="top" ></td>			
	<td valign="top" >Nama Lain Jenis Pejabat Urusan</td>
	<td valign="top" >:</td>
	<td valign="top" >
	<input  size="50" type="text" id="NAMA_LAIN_JENIS_PEJABAT_$viewPejabat.ID_PEJABAT" style="text-transform:uppercase;"
	name="NAMA_LAIN_JENIS_PEJABAT_$viewPejabat.ID_PEJABAT" value="$viewDetailsJenisPejabat.NAMA_LAIN_JENIS_PEJABAT" >
	</td>
	</tr>
         
    <tr>
	<td valign="top" ></td>			
	<td valign="top" >Penerangan</td>
	<td valign="top" >:</td>
	<td valign="top" >
	<textarea name="PENERANGAN_LANJUT_$viewPejabat.ID_PEJABAT" cols="40" rows="3" 
	id="PENERANGAN_LANJUT_$viewPejabat.ID_PEJABAT" >$viewDetailsJenisPejabat.PENERANGAN_LANJUT</textarea>
	</td>
</tr>
<tr>
<td valign="top" ></td>			
<td valign="top" ></td>
<td valign="top" ></td>
<td valign="top" >
<input type="button" id="cmdEditPejabat" name="cmdEditPejabat" value="Simpan" 
onClick="doDivAjaxCall$formname('div_TambahJenisPejabat$viewPejabat.ID_PEJABAT','simpanJenisPejabat','');" >
#if ($viewDetailsJenisPejabat.ID_JENISPEJABAT!= "")
<input type="button" id="cmdTutupPejabat" name="cmdTutupPejabat" value="Tutup" 
onClick="doDivAjaxCall$formname('div_TambahJenisPejabat$viewPejabat.ID_PEJABAT','close_Pejabat','ID_PEJABAT=$viewPejabat.ID_PEJABAT');" >
#else	
<input type="button" id="cmdTutupPejabat" name="cmdTutupPejabat" 
onclick="doDivAjaxCall$formname('div_TambahJenisPejabat$viewPejabat.ID_PEJABAT','close_Pejabat','ID_PEJABAT=$ID_PEJABAT');" value="Tutup" >	
#end
</td>
</tr>	   		
</div>
</table>
</fieldset>


	<br>
</td>		
</tr>
