
<script>
if( $jquery('#'+'div_rowFolderUtama$viewTajukUtama.ID_PANDANGANUNDANGUTAMA').length ) 
{
	window.scrollTo(0, $jquery('#'+'div_rowFolderUtama$viewTajukUtama.ID_PANDANGANUNDANGUTAMA').offset().top);
}
</script>

<tr >
<td align="left" valign="top" >
	<fieldset>
	<legend>
	
	#if($viewTajukUtama.ID_PANDANGANUNDANGUTAMA!= "")
	Kemaskini Direktori Utama
	#else
	Kemasukan Rujukan Utama
	#end
	</legend>
		<table width="100%" border="0">
			<tr>
			<td valign="top"  width="1%"></td><td valign="top"  width="28%"></td><td valign="top"  width="1%"></td><td valign="top"  width="70%"><!-- $viewPengguna --></td>
			</tr>
			
			<tr>
				<td valign="top" >
				<font color="red" >*</font>				
				</td>			
				<td valign="top" >
				Rujukan Utama
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				
				#set($spanvalidateMaindir = "validate_maindir_")
				#set($fieldvalidateMainDir = "fieldvalidateMainDir_")
				
				<input style="text-transform:uppercase;" size="50" type="text" id="TAJUK_UTAMA_$viewTajukUtama.ID_PANDANGANUNDANGUTAMA" 
				name="TAJUK_UTAMA_$viewTajukUtama.ID_PANDANGANUNDANGUTAMA" 
				value="$viewTajukUtama.TAJUK_UTAMA" 
				onKeyUp="doDivAjaxCall3$formname('$spanvalidateMaindir','validateMainDir','TAJUK='+this.value+'&ID_PANDANGANUNDANGUTAMA=');"

				>
				
				<br>
				<span id="$spanvalidateMaindir"><input type="hidden" id="$fieldvalidateMainDir" name="$fieldvalidateMainDir" value="N" ></span>
					
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
				<input type="button" id="BTNSAVE$viewTajukUtama.ID_PANDANGANUNDANGUTAMA" name="BTNSAVE$viewTajukUtama.ID_PANDANGANUNDANGUTAMA" 
				onClick="if(validateTajukUtama('$viewTajukUtama.ID_PANDANGANUNDANGUTAMA')==true){doDivAjaxCall$formname('div_senaraiUtama','saveTajukUtama','ID_PANDANGANUNDANGUTAMA=$viewTajukUtama.ID_PANDANGANUNDANGUTAMA');}" value="Simpan" > 
	   			
	   			<input type="button" id="BTNBTL$viewTajukUtama.ID_PANDANGANUNDANGUTAMA" name="BTNBTL$viewTajukUtama.ID_PANDANGANUNDANGUTAMA" 
				onClick="doDivAjaxCall$formname('div_rowFolderUtama','batalTajukUtama','ID_PANDANGANUNDANGUTAMA=$viewTajukUtama.ID_PANDANGANUNDANGUTAMA');" value="Batal" > 
	   			
	   			<input type="button" id="BTNCLS$viewTajukUtama.ID_PANDANGANUNDANGUTAMA" name="BTNCLS$viewTajukUtama.ID_PANDANGANUNDANGUTAMA" 
				onClick="doDivAjaxCall$formname('div_rowFolderUtama','closeTajukUtama','ID_PANDANGANUNDANGUTAMA=$viewTajukUtama.ID_PANDANGANUNDANGUTAMA');" value="Tutup" > 
	   			
	   			</td>
			</tr>
		
		
		</table>
	</fieldset>
	<br>
</td>		
</tr>
