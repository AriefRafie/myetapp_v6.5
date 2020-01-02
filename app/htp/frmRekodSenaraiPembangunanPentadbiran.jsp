<style type="text/css">
<!--
.style1 {color: #0000FF}
-->
</style>
<fieldset>
<legend>Carian</legend>
<table border="0" width="100%">
    <tbody>
    	<tr>			  
			<td colspan="3" align="center">&nbsp;</td>
      </tr>
      <tr >
        <td align="right" width="40%"><div align="right">Negeri</div></td>
        <td width="1%"><div align="center">:</div></td>
        <td width="59%">$selectNegeri</td>
      </tr>    
      <tr>
        <td align="right" width="40%"><div align="right">Daerah</div></td>
        <td><div align="center">:</div></td>
        <td>$selectDaerah</td>
      </tr>
      <tr>
        <td align="right" width="40%"><div align="right">Bandar/Pekan/Mukim</div></td>
        <td><div align="center">:</div></td>
        <td>$selectMukim</td>
      </tr>
      <tr>
        <td align="right" width="40%"><div align="right">Jenis Hakmilik</div></td>
        <td><div align="center">:</div></td>
        <td>$selectJenisHakmilik</td>
      </tr>
      <tr>
        <td align="right" width="40%"><div align="right">No. Hakmilik</div></td>
        <td><div align="center">:</div></td>
        <td><label>
          <input name="txtNoHakmilik" type="text" id="txtNoHakmilik" value="$txtNoHakmilik" />
        </label></td>
      </tr>
      <tr>
        <td></td>
        <td>&nbsp;</td>
        <td>
        <input type="button" name="btnCari" id="btnCari" value="Cari" onclick="cari()"/>
        <input type="submit" name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" onclick="kosong()" />
        </td>
      </tr>
      <tr>			  
			<td colspan="3" align="center">&nbsp;</td>
		</tr>
    </tbody>
  </table>  

</fieldset>
<fieldset>
<legend>Senarai Fail</legend>
<table border="0" width="100%">
    <tr>
    	<td colspan="6">#parse("app/utils/record_paging.jsp") </td>
    </tr>
	<tr class="table_header">
	  <td width="4%"><strong>Bil.</strong></td>
   	  <td width="20%"><strong>Hakmilik</strong></td>
  	  <td width="16%"><strong>No Lot</strong></td>
   	  <td width="25%"><strong>Cara Dapat</strong></td>
  	  <td width="25%"><b>Status Hakmilik</b></td>
  	  <td width="10%"><b>Tarikh Terima</b></td>
</tr>
#foreach ($senaraiHakmilik in $SenaraiHakmilik)
<!--<tr class="$row" onMouseOver="this.className='highlight'" onMouseOut="this.className='$row'">-->
   	<td height="20">$senaraiHakmilik.bil</td>
    #if ($senaraiHakmilik.no != '') 
    	#if($senaraiHakmilik.statusRizab == 'T')  
   			<td><a href="javascript:pergerakanhakmilik_detail('$senaraiHakmilik.idHakmilik')" class="style1">$senaraiHakmilik.kodJenis $senaraiHakmilik.noHakmilik</a></td>
   	  #else
   			<td><a href="javascript:rizab_detail('$senaraiHakmilik.idHakmilik')" class="style1">$senaraiHakmilik.kodJenis $senaraiHakmilik.noHakmilik</a></td>
   	  #end 
    #else    
    	<td>$senaraiHakmilik.noHakmilik</td>
    #end
    <td>$senaraiHakmilik.kodLot $senaraiHakmilik.noLot</td>
    <td>$senaraiHakmilik.namaUrusan</td>
    <td>$senaraiHakmilik.statusSah</td>
    <td>$senaraiHakmilik.tarikhTerima</td>
</tr> 
  #end
</table>
</fieldset>
<script>

</script>
