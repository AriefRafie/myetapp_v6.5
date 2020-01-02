<script>
if( $jquery('#'+'div_viewKhidmat$viewKhidmat.ID_KHIDMAT').length )         // use this if you are using id to check
{
	window.scrollTo(0, $jquery('#'+'div_viewKhidmat$viewKhidmat.ID_KHIDMAT').offset().top);
}
else
{
	
	if( $jquery('#'+'div_viewKhidmat$viewKhidmat.ID_KHIDMAT').length ) 
	{
		window.scrollTo(0, $jquery('#'+'div_viewKhidmat$viewKhidmat.ID_KHIDMAT').offset().top);
	}
	
}
</script>

<!--#if ($result == "success")
<div class=info>Done.</div>
#end-->
<tr id="div_viewKhidmat$viewKhidmat.ID_KHIDMAT">
<td align="left" valign="top" colspan="14">

<fieldset>
<legend>Maklumat Kumpulan PerKhidmatan</legend>
<div id="printableArea_$viewKhidmat.ID_KHIDMAT">
<table width="100%" border="0">
<tr>
<td valign="top"  width="1%"></td>
<td valign="top"  width="28%"></td>
<td valign="top"  width="1%"></td>
<td valign="top"  width="70%">
<!-- $viewPengguna --></td>
</tr>
<!--  <input type=text name="ID_KHIDMAT" value="$viewKhidmat.ID_KHIDMAT">-->
             
              <tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Nama Kumpulan Perkhidmatan
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewKhidmat.NAMA_KHIDMAT
				</td>
			</tr>
              <tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Nama Lain Kumpulan Perkhidmatan
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewKhidmat.NAMA_LAIN_KHIDMAT
				</td>
			</tr>
            <tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Keterangan 
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewKhidmat.KETERANGAN_KHIDMAT
				</td>
			</tr>
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Skop Gred
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewKhidmat.SKOP_GRED1 - $viewKhidmat.SKOP_GRED2
				</td>
			</tr>	
            <tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Catatan
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewKhidmat.CATATAN
				</td>
			</tr>
            
             <tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Sumber Maklumat
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewKhidmat.SUMBER_MAKLUMAT
				</td>
			</tr>
            <tr><td></td></tr>
            <tr><td></td></tr>
            <tr><td></td></tr>
            <tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Daftar oleh
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewKhidmat.ID_MASUK
				</td>
			</tr>
			
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Tarikh Daftar
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewKhidmat.TARIKH_MASUK
				</td>
			</tr>
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Kemaskini oleh
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewKhidmat.ID_KEMASKINI
				</td>
			</tr>
            <tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Tarikh Kemaskini
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewKhidmat.TARIKH_KEMASKINI
				</td>
			</tr> 
            <tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >				
				</td>
				<td valign="top" >				
				</td>
				<td >
                
 <input type="button" id="cmdEditKhidmat" name="cmdEditKhidmat" value="Kemaskini" onClick="doDivAjaxCall$formname('div_viewKhidmat$viewKhidmat.ID_KHIDMAT','addKhidmat','ID_KHIDMAT=$viewKhidmat.ID_KHIDMAT');" >
			
<input type="button" id="cmdTutupKhidmat" name="cmdTutupKhidmat" value="Tutup" onClick="doDivAjaxCall$formname('div_viewKhidmat$viewKhidmat.ID_KHIDMAT','close_AddKhidmat','ID_KHIDMAT=$viewKhidmat.ID_KHIDMAT');" >   	

<input type="button" 
id="BTNPRINT$viewKhidmat.ID_KHIDMAT" 
name="BTNPRINT$viewKhidmat.ID_KHIDMAT" 
onclick="printDivKhidmat('printableArea_$viewKhidmat.ID_KHIDMAT','$viewKhidmat.ID_KHIDMAT')" value="Cetak" />  

<br>
</td>		
</tr>	
</table>
</div>
</fieldset>
</div>
	
			