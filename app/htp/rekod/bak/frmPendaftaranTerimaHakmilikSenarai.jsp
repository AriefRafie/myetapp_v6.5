<style type="text/css">
<!--
.style1 {color: #0000FF}
-->
</style>
<fieldset>
<legend>SENARAI HAKMILIK</legend>
<table border="0" width="100%">
   <!-- <tr>
    	<td colspan="8">#parse("app/utils/record_paging.jsp") </td>
    </tr> -->
	<tr class="table_header">
	  <td width="1%">Bil.</td>
   	  <td width="13%"><div align="left">No Fail</div></td>
   	  <td width="8%"><div align="left">No Hakmilik</div></td>
  	  <td width="8%"><div align="left">No Lot/PT</div></td>
   	  <td width="8%"><div align="left">Status Hakmilik</div></td>
  	  <td width="25%"><div align="left">Kegunaan Tanah</div></td>
  </tr>
#foreach ($senarai in $SenaraiTanah)
  #set( $i = $velocityCount )
    #if ( ($i % 2) != 1 )
       #set( $row = "row2" )
    #else
       #set( $row = "row1" )
    #end
    <tr class="$row">
    <td width="1%">$senarai.bil</td>
    #if($senarai.bil != '')
      #if($senarai.jenisTanah == 'M')     	
       <td width="18%"><a href="javascript:hakmilikTerperinci('$senarai.idHakmilik','$senarai.statusSah','$senarai.idPermohonan');" class="style1">$senarai.noFail</a></td>
   	  #else
   	    <td width="18%"><a href="javascript:rizab_detail('$senarai.idHakmilik','$senarai.statusSah');" class="style1">$senarai.noFail</a></td>
      #end    
    #else
    	<td width="13%">$senarai.noFail</td>
    #end
   	<td width="8%"><div align="left">$senarai.kodJenis $senarai.noHakmilik $senarai.noWarta</div></td>
    <td width="8%"><div align="left">$senarai.kodLot$senarai.noLot</div></td>
    <td width="5%"><div align="center">$senarai.statusSah</div></td>
    <td width="23%">$senarai.kegunaanTanah</td>
	</tr> 
 #end
</table>
</fieldset>

