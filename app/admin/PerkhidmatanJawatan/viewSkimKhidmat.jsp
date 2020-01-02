<script>
if( $jquery('#'+'div_viewSkimKhidmat$viewSkimKhidmat.ID_JAWATAN').length )         // use this if you are using id to check
{
	window.scrollTo(0, $jquery('#'+'div_viewSkimKhidmat$viewSkimKhidmat.ID_JAWATAN').offset().top);
}
else
{
	
	if( $jquery('#'+'div_viewSkimKhidmat$viewSkimKhidmat.ID_JAWATAN').length ) 
	{
		window.scrollTo(0, $jquery('#'+'div_viewSkimKhidmat$viewSkimKhidmat.ID_JAWATAN').offset().top);
	}
	
}
</script>

<!--#if ($result == "success")
<div class=info>Done.</div>
#end-->
<tr id="div_viewSkimKhidmat$viewSkimKhidmat.ID_JAWATAN">
<td align="left" valign="top" colspan="14">

<fieldset>
<legend>Maklumat Skim Khidmat</legend>
<div id="printableArea_$viewSkimKhidmat.ID_JAWATAN">
<table width="100%" border="0">
<tr>
<td valign="top"  width="1%"></td>
<td valign="top"  width="28%"></td>
<td valign="top"  width="1%"></td>
<td valign="top"  width="70%">
<!-- $viewPengguna --></td>
</tr>
<!--  <input type=text name="ID_JAWATAN" value="$viewSkimKhidmat.ID_JAWATAN">-->
             
              <tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Nama Skim Perkhidmatan/ Nama Jawatan
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewSkimKhidmat.KETERANGAN
				</td>
			</tr>
            <tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Kod Skim
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewSkimKhidmat.KOD_SKIM
				</td>
			</tr>
			<tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Kumpulan Perkhidmatan
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewSkimKhidmat.NAMA_KHIDMAT
				</td>
			</tr>	
          <!--  <tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Gred
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewSkimKhidmat.NAMA_GRED
				</td>
			</tr>	-->
<!---->

#if($listGred.size()>0)
<tr id="div_displayaddGred$viewSkimKhidmat.ID_JAWATAN">
<td valign="top" >				
</td>			
<td valign="top" >Gred</td>
<td valign="top" >
:
</td>
<td>
<div id="div_listaddGred$viewSkimKhidmat.ID_JAWATAN">
<table width="100%" border="0"  >
<tr>
<td align="right" width="1%"  valign="top" >
</td>
<td align="center"  width="1%"  valign="top" ></td>
<td align="left"  width="94%"  valign="top" ></td>
</tr>

#foreach($gred in $listGred)

<tr>
<td align="right"   
valign="top" >$gred.BIL. </td>
<td align="center"   
valign="top" ></td>
<td align="left"   
valign="top" >$gred.KETERANGAN</td>
</tr>
#end	

<tr>
<td></td><td></td>
<td >
<input 
type="button" id="cmdBttnGred$viewSkimKhidmat.ID_JAWATAN" 
name="cmdBttnGred$viewSkimKhidmat.ID_JAWATAN" 
onClick="doDivAjaxCall$formname('div_listaddGred$viewSkimKhidmat.ID_JAWATAN','editAddGred','ID_JAWATAN=$viewSkimKhidmat.ID_JAWATAN&ID_GRED=$viewSkimKhidmat.ID_GRED&ID_KLASIFIKASI=$viewSkimKhidmat.ID_KLASIFIKASI');" 
value="Kemaskini / Tambah Gred" >   
</td>
</tr>					
</table>
</div>
</td>
</tr> 
<!---->
<script>	
document.getElementById("div_displayaddGred$viewSkimKhidmat.ID_JAWATAN").style.display="";	
</script>
#else
<tr id="div_displayaddGred$viewSkimKhidmat.ID_JAWATAN" >
<td valign="top" >				</td>			
<td valign="top" >Gred</td>
<td valign="top" >
:
</td>
<td>
<div id="div_listaddGred$viewSkimKhidmat.ID_JAWATAN">
<table width="100%" border="0" >
						
<tr >
<td >
- &nbsp;
<input 
type="button" id="cmdBttnGred$viewSkimKhidmat.ID_JAWATAN" 
name="cmdBttnGred$viewSkimKhidmat.ID_JAWATAN" 
onclick="doDivAjaxCall$formname('div_listaddGred$viewSkimKhidmat.ID_JAWATAN','editAddGred','ID_JAWATAN=$viewSkimKhidmat.ID_JAWATAN&ID_GRED=$viewSkimKhidmat.ID_GRED&ID_KLASIFIKASI=$viewSkimKhidmat.ID_KLASIFIKASI');" 
value="Kemaskini / Tambah Gred" />
</td>
</tr>					
</table>
</div>
</td>
</tr>
#end
            <!---->
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
				$viewSkimKhidmat.SUMBER_MAKLUMAT
				</td>
			</tr>
            
             <tr>
				<td valign="top" >				
				</td>			
				<td valign="top" >
				Keterangan Tugasan
				</td>
				<td valign="top" >
				:
				</td>
				<td valign="top" >
				$viewSkimKhidmat.KETERANGAN_TUGASAN
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
				$viewSkimKhidmat.ID_MASUK
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
				$viewSkimKhidmat.TARIKH_MASUK
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
				$viewSkimKhidmat.ID_KEMASKINI
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
				$viewSkimKhidmat.TARIKH_KEMASKINI
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
                
 <input type="button" id="cmdEditSkimKhidmat" name="cmdEditSkimKhidmat" value="Kemaskini" onClick="doDivAjaxCall$formname('div_viewSkimKhidmat$viewSkimKhidmat.ID_JAWATAN','addSkimKhidmat','ID_JAWATAN=$viewSkimKhidmat.ID_JAWATAN');" >
			
<input type="button" id="cmdTutupSkimKhidmat" name="cmdTutupSkimKhidmat" value="Tutup" onClick="doDivAjaxCall$formname('div_viewSkimKhidmat$viewSkimKhidmat.ID_JAWATAN','close_AddSkimKhidmat','ID_JAWATAN=$viewSkimKhidmat.ID_JAWATAN');" >   

<input type="button" 
id="BTNPRINT$viewSkimKhidmat.ID_JAWATAN" 
name="BTNPRINT$viewSkimKhidmat.ID_JAWATAN" 
onclick="printDivSkimKhidmat('printableArea_$viewSkimKhidmat.ID_JAWATAN','$viewSkimKhidmat.ID_JAWATAN')" value="Cetak" />  	

<br>
</td>		
</tr>	
</table>
</div>
</fieldset>
</div>
	
			