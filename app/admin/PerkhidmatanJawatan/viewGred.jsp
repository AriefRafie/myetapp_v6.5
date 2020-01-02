<script>
if( $jquery('#'+'div_viewGred$viewGred.ID_GRED').length )         // use this if you are using id to check
{
	window.scrollTo(0, $jquery('#'+'div_viewGred$viewGred.ID_GRED').offset().top);
}
else
{
	
	if( $jquery('#'+'div_viewGred$viewGred.ID_GRED').length ) 
	{
		window.scrollTo(0, $jquery('#'+'div_viewGred$viewGred.ID_GRED').offset().top);
	}
	
}
</script>

<!--#if ($result == "success")
<div class=info>Done.</div>
#end-->
<tr id="div_viewGred$viewGred.ID_GRED">
<td align="left" valign="top" colspan="14">

<fieldset>

<legend>Maklumat Gred</legend>
<div id="printableArea_$viewGred.ID_GRED">
<table width="100%" border="0">
<tr>
<td valign="top"  width="1%"></td>
<td valign="top"  width="28%"></td>
<td valign="top"  width="1%"></td>
<td valign="top"  width="70%">
<!-- $viewPengguna --></td>
</tr>
<!--  <input type=text name="ID_GRED" value="$viewGred.ID_GRED">-->
             
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
				$viewGred.NAMA_KLASIFIKASI
				</td>
			</tr>
            <tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Nama Gred
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewGred.NAMA_GRED
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
				$viewGred.KETERANGAN
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
				$viewGred.FLAG_STATUS
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
				$viewGred.ID_MASUK
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
				$viewGred.TARIKH_MASUK
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
				$viewGred.ID_KEMASKINI
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
				$viewGred.TARIKH_KEMASKINI
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
                
 <input type="button" id="cmdEditGred" name="cmdEditGred" value="Kemaskini" onClick="doDivAjaxCall$formname('div_viewGred$viewGred.ID_GRED','addGred','ID_GRED=$viewGred.ID_GRED');" >
			
<input type="button" id="cmdTutupGred" name="cmdTutupGred" value="Tutup" onClick="doDivAjaxCall$formname('div_viewGred$viewGred.ID_GRED','close_AddGred','ID_GRED=$viewGred.ID_GRED');" > 

<input type="button" 
id="BTNPRINT$viewGred.ID_GRED" 
name="BTNPRINT$viewGred.ID_GRED" 
onclick="printDivGred('printableArea_$viewGred.ID_GRED','$viewGred.ID_GRED')" value="Cetak" />  	

<br>
</td>		
</tr>	
</table>
</div>
</fieldset>
</div>
	
			