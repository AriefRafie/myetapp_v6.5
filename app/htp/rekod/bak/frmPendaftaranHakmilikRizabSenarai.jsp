<style type="text/css">
<!--
.style1 {color: #0000FF}
-->
</style>
<fieldset>
<legend>CARIAN</legend>
<table border="0" width="100%">
    <tbody>
      <tr >
        <td align="right" width="40%"><div align="right">No Fail</div></td>
        <td width="1%"><div align="center">:</div></td>
        <td width="59%"><input name="nofail" id="nofail" type="text" value="$!txtNoFail" size="50" maxlength="50" style="text-transform:uppercase;" > </td>
      </tr>   
      <tr >
        <td align="right" width="40%"><div align="right">Nama Fail</div></td>
        <td width="1%"><div align="center">:</div></td>
        <td width="59%"><input name="txtTajukFail" id="txtTajukFail" type="text" value="$!txtTajukFail" size="50" maxlength="50" style="text-transform:uppercase;" > </td>
      </tr> 
       <tr >
        <td align="right" width="40%"><div align="right">Kementerian</div></td>
        <td width="1%"><div align="center">:</div></td>
        <td width="59%">$socKementerian</td>
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
      <!--<tr>
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
      </tr>-->
      <tr>
        <td></td>
        <td>&nbsp;</td>
        <td>
        <input type="button" class="stylobutton" name="btnCari" id="btnCari" value="Cari" onclick="cari()"/>
        <input type="button" class="stylobutton" name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" onclick="kosongCarian()" />
        </td>
      </tr>
      <!-- <tr>			  
			<td colspan="3" align="center">&nbsp;</td>
		</tr> -->
    </tbody>
  </table>  

</fieldset>
<fieldset>
<legend>SENARAI FAIL</legend>
<table border="0" width="100%">
    <tr>
    	<td colspan="6">#parse("app/utils/record_paging.jsp") </td>
    </tr>
	<tr class="table_header">
	  <td width="4%"><b>BIL.</b></td>
   	  <td width="20%"><b>NO FAIL</b></td>
  	  <td width="46%"><b>NAMA FAIL</b></td>
   	  <td width="15%"><b>NO FAIL PTD</b></td>
  	  <td width="15%"><b>NO FAIL PTG</b></td>
  	  <!--<td width="10%">TARIKH TERIMA</td> -->
</tr>
#set ($count = 0)
#foreach ($senaraiHakmilik in $SenaraiHakmilik)
	#set ($count = $count+1)
  #set( $i = $velocityCount )
    #if ( ($i % 2) != 1 )
       #set( $row = "row2" )
    #else
       #set( $row = "row1" )
    #end
   <tr class="$row">
   	<td height="20">$count</td> 
      #if($senaraiHakmilik.flagTanah == '1' || $senaraiHakmilik.flagTanah == '2' || $senaraiHakmilik.flagTanah == '5' )  
   			<td><a href="javascript:hakmilik_detail2('$senaraiHakmilik.idpermohonan');" class="style1">$!senaraiHakmilik.no</a></td>
   	  #elseif($senaraiHakmilik.flagTanah == '10')
   			<td><a href="javascript:rizab_detail2('$senaraiHakmilik.idpermohonan');" class="style1">$!senaraiHakmilik.no</a></td>
 	  #else  
 	  	<td>$!senaraiHakmilik.no</td>
   	  #end
   	   
      #if($senaraiHakmilik.flagTanah == '1' || $senaraiHakmilik.flagTanah == '2' || $senaraiHakmilik.flagTanah == '5' )  
    	<td>$!senaraiHakmilik.tajuk</td>
   	  #elseif($senaraiHakmilik.flagTanah == '10')
    	<td>$!senaraiHakmilik.tajuk</td>
 	  #end  
    <td>$!senaraiHakmilik.ptd</td>
    <td>$!senaraiHakmilik.ptg</td>
    <!--<td>$senaraiHakmilik.tarikhTerima</td> -->
</tr> 
  #end
</table>
</fieldset>
<script>

function hakmilik_detail2(id){
	doAjaxCall${formName}("paparDetailHakmilik2","idHakmilik="+id);
}

function rizab_detail2(id){
	doAjaxCall${formName}("paparDetailRizab2","idPermohonan="+id);
}

function kembaliRizab(){
	doAjaxCall${formName}("");
}

</script>
