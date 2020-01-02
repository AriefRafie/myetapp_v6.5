<script>
if( $jquery('#'+'div_viewKlasifikasi$viewKlasifikasi.ID_KLASIFIKASI').length )         // use this if you are using id to check
{
	window.scrollTo(0, $jquery('#'+'div_viewKlasifikasi$viewKlasifikasi.ID_KLASIFIKASI').offset().top);
}
else
{
	
	if( $jquery('#'+'div_viewKlasifikasi$viewKlasifikasi.ID_KLASIFIKASI').length ) 
	{
		window.scrollTo(0, $jquery('#'+'div_viewKlasifikasi$viewKlasifikasi.ID_KLASIFIKASI').offset().top);
	}
	
}
</script>
<!--
#if ($result == "success")
<div class=info>Done.</div>
#end-->
<tr id="div_viewKlasifikasi$viewKlasifikasi.ID_KLASIFIKASI">
<td align="left" valign="top" colspan="14">

<fieldset>
<legend>Maklumat Klasifikasi</legend>
<div id="printableArea_$viewKlasifikasi.ID_KLASIFIKASI">
<table width="100%" border="0">
<tr>
<td valign="top"  width="1%"></td>
<td valign="top"  width="28%"></td>
<td valign="top"  width="1%"></td>
<td valign="top"  width="70%">
<!-- $viewPengguna --></td>
</tr>
<!-- <input type=text name="ID_KLASIFIKASI" value="$viewKlasifikasi.ID_KLASIFIKASI">-->
            <tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Nama Klasifikasi
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewKlasifikasi.NAMA_KLASIFIKASI
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
				$viewKlasifikasi.KETERANGAN
				</td>
			</tr>
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Status
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewKlasifikasi.FLAG_STATUS
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
				$viewKlasifikasi.ID_MASUK
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
				$viewKlasifikasi.TARIKH_MASUK
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
				$viewKlasifikasi.ID_KEMASKINI
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
				$viewKlasifikasi.TARIKH_KEMASKINI
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
                
<input type="button" id="cmdViewKlasifikasi" name="cmdViewKlasifikasi" value="Kemaskini" onClick="doDivAjaxCall$formname('div_viewKlasifikasi$viewKlasifikasi.ID_KLASIFIKASI','addKlasifikasi','ID_KLASIFIKASI=$viewKlasifikasi.ID_KLASIFIKASI');" >

<input type="button" id="cmdTutupKlasifikasi" name="cmdTutupKlasifikasi" value="Tutup" onClick="doDivAjaxCall$formname('div_viewKlasifikasi$viewKlasifikasi.ID_KLASIFIKASI','close_AddKlasifikasi','ID_KLASIFIKASI=$viewKlasifikasi.ID_KLASIFIKASI');" >
<!--#if ($viewKlasifikasi.ID_KLASIFIKASI!= "")
	   			<input type="button" id="cmdTutupPejabat" name="cmdTutupPejabat" value="Tutup" onClick="doDivAjaxCall$formname('div_viewKlasifikasi$viewKlasifikasi.ID_KLASIFIKASI','close_Pejabat','ID_KLASIFIKASI=$viewKlasifikasi.ID_KLASIFIKASI');" >
	   		#else	
	<input type="button" id="cmdTutupPejabat" name="cmdTutupPejabat" onclick="doDivAjaxCall$formname('div_viewKlasifikasi','close_Pejabat','ID_KLASIFIKASI=$viewKlasifikasi.ID_KLASIFIKASI');" value="Tutup" >
			#end-->

<!--<input type=text name="ID_KLASIFIKASI" value="$viewKlasifikasi.ID_KLASIFIKASI">
<input type=text name="ID_KLASIFIKASI" value="$viewKlasifikasi.ID_KLASIFIKASI">
<input type=text name="idNegeri" value="$viewKlasifikasi.ID_NEGERI">
-->

<input type="button" 
id="BTNPRINT$viewKlasifikasi.ID_KLASIFIKASI" 
name="BTNPRINT$viewKlasifikasi.ID_KLASIFIKASI" 
onclick="printDivKlasifikasi('printableArea_$viewKlasifikasi.ID_KLASIFIKASI','$viewKlasifikasi.ID_KLASIFIKASI')" value="Cetak" /> 

<br>
</td>		
</tr>	
</table>
</div>
</fieldset>
</div>
	