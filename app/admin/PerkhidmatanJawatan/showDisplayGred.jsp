#if($listGred.size()>0)
<tr id="div_displayaddGred$viewSkimKhidmat.ID_JAWATAN">
<td valign="top" >				
</td>			
<td valign="top" ></td>
<td valign="top" >
</td>
<td>
<div id="div_listaddGred$viewSkimKhidmat.ID_JAWATAN">
<table width="100%" border="0"  >


#foreach($gred in $listGred)

<tr>
<td align="right" valign="top" >$gred.BIL. </td>
<td align="center" valign="top" ></td>
<td align="left" valign="top" >$gred.KETERANGAN</td>
</tr>
#end	
<tr >
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

<script>	
document.getElementById("div_displayaddGred$viewSkimKhidmat.ID_JAWATAN").style.display="";	
</script>
#else
masuk sini
<tr id="div_displayaddGred$viewSkimKhidmat.ID_JAWATAN" >
<td valign="top" >				
</td>			
<td valign="top" >
Gred
</td>
<td valign="top" >
:
</td>
<td>
<div id="div_listaddGred$viewSkimKhidmat.ID_JAWATAN">
<table width="100%" border="0" >

<tr >
<td >
- &nbsp;
<input type="button" id="cmdBttnGred$viewSkimKhidmat.ID_JAWATAN" 
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
