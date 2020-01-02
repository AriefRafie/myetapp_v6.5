
<script>
if( $jquery('#'+'div_rowFolderUtama$viewTajukUtama.ID_DOCTRMUTAMA').length ) 
{
	window.scrollTo(0, $jquery('#'+'div_rowFolderUtama$viewTajukUtama.ID_DOCTRMUTAMA').offset().top);
}
</script>

<tr >
<td align="left" valign="top" >
	<fieldset>
	<legend>
	
	#if($viewTajukUtama.ID_DOCTRMUTAMA!= "")
	Kemaskini Direktori Utama
	#else
	Kemasukan Jenis Dokumen
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
				Jenis Dokumen
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
                
				
				#set($spanvalidateMaindir = "validate_maindir_")
				#set($fieldvalidateMainDir = "fieldvalidateMainDir_")
				
				<input style="text-transform:uppercase;" size="50" type="text" id="TAJUK_UTAMA_$viewTajukUtama.ID_DOCTRMUTAMA" 
				name="TAJUK_UTAMA_$viewTajukUtama.ID_DOCTRMUTAMA" 
				value="$viewTajukUtama.TAJUK_UTAMA" 
				onKeyUp="doDivAjaxCall3$formname('$spanvalidateMaindir','validateMainDir','TAJUK='+this.value+'&ID_DOCTRMUTAMA=');"

				>
				
				<br>
				<span id="$spanvalidateMaindir"><input type="hidden" id="$fieldvalidateMainDir" name="$fieldvalidateMainDir" value="N" ></span>
					
				</td>
				
			</tr>
            <!--TAMBAH BAHAGIAN/SEKSYEN-->
			<!--<tr>
				<td valign="top" >
				<font color="red" >*</font>				
				</td>			
				<td valign="top" >
				Bahagian / Urusetia
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >$selectSeksyen</td>-->
			
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >				
				</td>
				<td valign="top" >				
				</td>
				<td valign="top" >
				<input type="button" id="BTNSAVE$viewTajukUtama.ID_DOCTRMUTAMA" name="BTNSAVE$viewTajukUtama.ID_DOCTRMUTAMA" 
				onClick="if(validateTajukUtama('$viewTajukUtama.ID_DOCTRMUTAMA')==true){doDivAjaxCall$formname('div_senaraiUtama','saveTajukUtama','ID_DOCTRMUTAMA=$viewTajukUtama.ID_DOCTRMUTAMA');}" value="Simpan" > 
	   			
	   			<input type="button" id="BTNBTL$viewTajukUtama.ID_DOCTRMUTAMA" name="BTNBTL$viewTajukUtama.ID_DOCTRMUTAMA" 
				onClick="doDivAjaxCall$formname('div_rowFolderUtama','batalTajukUtama','ID_DOCTRMUTAMA=$viewTajukUtama.ID_DOCTRMUTAMA');" value="Batal" > 
	   			
	   			<input type="button" id="BTNCLS$viewTajukUtama.ID_DOCTRMUTAMA" name="BTNCLS$viewTajukUtama.ID_DOCTRMUTAMA" 
				onClick="doDivAjaxCall$formname('div_rowFolderUtama','closeTajukUtama','ID_DOCTRMUTAMA=$viewTajukUtama.ID_DOCTRMUTAMA');" value="Tutup" > 
	   			
	   			</td>
			</tr>
		
		
		</table>
	</fieldset>
	<br>
</td>		
</tr>
