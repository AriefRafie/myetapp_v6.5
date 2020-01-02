<style type="text/css">
<!--
.style1 {color: #0000FF}
-->
</style>
<fieldset>
<legend>CARIAN</legend>
<table border="0" width="100%">
  <tr>
    <td align="right"><div align="right">No Fail</div></td>
    <td width="1%"><div align="center">:</div></td>
     <td width="59%"><input name="txtNoFail" type="text" id="txtNoFail" value="$txtNoFail" size="43" /></td>
  <tr>
    <td align="right" width="40%"><div align="right">No. Hakmilik</div></td>
    <td><div align="center">:</div></td>
    <td><label>
        <input name="txtNoHakmilikC" type="text" id="txtNoHakmilikC" value="$txtNoHakmilikC" />
    </label></td>
      
       <tr >
        <td align="right"><div align="right">No. Lot</div></td>
        <td><div align="center">:</div></td>
        <td><input name="txtNoLotC" type="text" id="txtNoLotC" value="$txtNoLotC" /></td>
      </tr>       
        <td align="right"><div align="right">Negeri</div></td>
        <td><div align="center">:</div></td>
        <td>$selectNegeri</td>
      </tr>
      <tr>
        <td align="right"><div align="right">Daerah</div></td>
        <td><div align="center">:</div></td>
        <td>$selectDaerah</td>
      </tr>
      <tr>
        <td align="right"><div align="right">Bandar/Pekan/Mukim</div></td>
        <td><div align="center">:</div></td>
        <td>$selectMukim</td>
      </tr>
      <tr>
        <td></td>
        <td>&nbsp;</td>
        <td>
        <input type="button" name="btnCari" id="btnCari" value="Cari" onclick="cari()"/>
        <input type="button" name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" onclick="kosongCarian()" />        </td>
      </tr>
      <tr>			  
			<td colspan="3" align="center">&nbsp;</td>
		</tr>
  </table>
</fieldset>
<fieldset>
<legend>SENARAI HAKMILIK/RIZAB</legend>
<table border="0" width="100%">
    <tr>
    	<td colspan="8">#parse("app/utils/record_paging.jsp") </td>
    </tr>
	<tr class="table_header">
	  <td width="1%">Bil.</td>
   	  <td width="18%"><div align="left">No Hakmilik</div></td>
   	  <td width="8%"><div align="center">No Lot</div></td>
  	  <td width="8%"><div align="center">Status<br />
      Hakmilik</div></td>
   	  <td width="5%"><div align="center">Kegunaan Tanah</div></td>
  </tr>
#foreach ($senarai in $SenaraiHakmilik)
  #set( $i = $velocityCount )
    #if ( ($i % 2) != 1 )
       #set( $row = "row2" )
    #else
       #set( $row = "row1" )
    #end
    <tr class="$row">
    <td width="1%">$senarai.bil</td>
    #if($senarai.bil != '')
    <td width="18%"><a href="javascript:hakmilik_detail('$senarai.idHakmilik');" class="style1">$senarai.kodJenis $senarai.noHakmilik</a></td>  
    #else
    	<td width="13%">$senarai.noHakmilik</td>
    #end
    <td width="8%"><div align="left">$senarai.kodLot$senarai.noLot</div></td>
    <td width="5%"><div align="center">$senarai.statusSah</div></td>
    <td width="23%">$senarai.kegunaanTanah</td>
	</tr> 
 #end
</table>
</fieldset>

<script>
//function untuk frmRekodSenaraiHakmilik
function cari(){   
	document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodHakmilikSementara&firstAction=carianHakmilikRizab";
	document.${formName}.submit();
}
function hakmilik_detail(id){
	document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodHakmilikSementara&firstAction=paparDetailHakmilik&idHakmilik="+id;
	document.${formName}.submit();
}
function kosongCarian(){

	document.${formName}.txtNoFail.value = "";
	document.${formName}.txtNoHakmilikC.value = "";
	document.${formName}.txtNoLotC.value = "";
	document.${formName}.socNegeri.value = "";
    document.${formName}.socDaerah.value = "";
	document.${formName}.socMukim.value = "";	
	document.${formName}.socJenisHakmilik.value = "";	
	document.${formName}.socAgensi.value = "";	
	document.${formName}.socJenisLot.value = "";	
	document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodHakmilikSementara&firstAction=";
	document.${formName}.submit();
}
function doChangeState() {
   doAjaxCall${formName}("","firstAction=carianHakmilikRizab&nextAction=doChangeState");
}
function doChangeDaerah() {
	doAjaxCall${formName}("doChangeDaerah");
}
function doChangeMukim() {
	doAjaxCall${formName}("doChangeMukim");
}
</script>
