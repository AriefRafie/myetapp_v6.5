 <style type="text/css">
  <!--
  .style1 {color: #0033FF}
  -->
  </style>
<fieldset>
<legend>Maklumat Kemasukan Bilangan Fail</legend>
	
<fieldset>
<table border="0" align="center" width="60%" >      
     <tbody> 
      <tr> 
        <td scope="row" align="left">&nbsp;Unit </td>
        <td>:&nbsp;</td>
        <td>$selectUnit</td>
      </tr>
      <tr> 
        <td scope="row" align="left">&nbsp;Daerah </td>
        <td>:&nbsp;</td>
        <td>$selectDaerah</td>
      </tr>   
    	#set ($txtBilangan = "")
      
      <tr> 
        <td scope="row" align="left">&nbsp;Bilangan Fail </td>
        <td>:&nbsp;</td>
        <td>
			<input name="Form_bilangan" id="Form_bilangan" type="text" value="$txtBilangan" maxlength="2" onKeyUp="javascript:validatePoskod(this,this.value)" onBlur="javascript:validatePoskod(this,this.value)" size="10" />
		</td>
      </tr>  
	<tr>
		<td valign="top" align="center" colspan="3">
		<input class="button" name="btnsubmit" type="button" value="Simpan" onclick="tambah()">
		<input class="button" type="reset" value="Batal" >
		</td>
	</tr>
     
   </tbody>
 </table>
</fieldset>	

<fieldset>
<legend>Senarai Kemasukan </legend>
	
<table border="0" align="center" width="60%" >
 	#set ($cnt = 0)
 	<tr class="table_header">
    <td width="5%" align="center"><strong>##</strong></td>
   	<td width="32%" align="center"><strong>Nama Daerah</strong></td>
    <td width="25%" align="center"><strong>Bilangan</strong></td>
    <td width="30%" align="center"><strong>Tarikh</strong></td>
    <td width="8%" align="center"></td>
    </tr>
 
 	#foreach ( $senarai in $senaraiRekod )
  	#set ( $cnt = $cnt + 1 )
    	#set( $i = $velocityCount )
        #if ( ($i % 2) == 0 )
        	#set( $row = "row2" )
      	#else
        	#set( $row = "row1" )
        #end
   	<tr>
  	<td class="$row">$cnt.</td>
  	<td class="$row">$senarai.namadaerah</td>
  	<td class="$row" align="center">$senarai.bilangan</td>
  	<td class="$row" align="center">$senarai.tarikh</td>
  	<td class="$row">
		<a href="javascript:doEdit('$senarai.idlaporanbilfail','$senarai.bilangan','$senarai.iddaerah','$senarai.idpejabat')"><img src="../img/edit.gif" border="0"></a>
		<a href="javascript:doDelete('$senarai.idlaporanbilfail')"><img src="../img/delete.gif" border="0"></a>
    </td> 
   	</tr>	
	#end 
	
	#if ($cnt == 0)
  	<tr> 
   	<td colspan="4" align="center" scope="row"><font color="#FF0000">Tiada Rekod.</font></td>
  	</tr>
    #end 
</table>
</fieldset>


</fieldset>



<input type="hidden" name="idlaporanbilfail" value="0">
<input type="hidden" name="command3">	

<!--</form>	-->
	

	
<script>
function doEdit(id,bilangan,daerah,unit) {
	document.${formName}.idlaporanbilfail.value = id;
	document.${formName}.Form_bilangan.value = bilangan;
	document.${formName}.Form_id_Daerah.value = daerah;
	document.${formName}.socUnit.value = unit;
	document.${formName}.btnsubmit.value = "Kemaskini";
}
	
	
function doDelete(id) {
	if ( !window.confirm("Adakah anda pasti?") ) return;
	document.${formName}.idlaporanbilfail.value = id;
	doAjaxCall${formName}("Delete");
}
	
function doChangeUnit() {
	if(document.${formName}.socUnit.value=="")
		return;
	doAjaxCall${formName}("SelectUnit");
}

function tambah() {
	if(document.${formName}.socUnit.value=="" || document.${formName}.Form_id_Daerah.value==""){
		alert("Sila pilih Unit/Daerah terlebih dahulu");
		return;
	}
	if(document.${formName}.Form_bilangan.value==""){
		alert("Sila masukkan maklumat bilangan fail");
		return ; 	
	}
	//alert(document.${formName}.txtbilangan.value);
	doAjaxCall${formName}("simpan");
}


</script>


