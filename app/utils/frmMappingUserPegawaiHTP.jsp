 <style type="text/css">
  <!--
  .style1 {color: #0033FF}
  -->
  </style>
  
<fieldset>
<legend>Maklumat Kemasukan </legend>
	<fieldset><legend>
<b >Carian</b>
</legend>
<table  align="center" border="0" width="80%" >
      <tbody> 
     	<tr> 
        <td scope="row" align="left">&nbsp;Negeri </td>
        <td>:&nbsp;</td>
        <td>$selectNegeri</td>
      </tr>
      <tr> 
        <td scope="row" align="left">&nbsp;Nama Pegawai </td>
        <td>:&nbsp;</td>
        <td>$selectUnit</td>
      </tr>
      <tr> 
        <td scope="row" align="left">&nbsp;Pengguna Sistem </td>
        <td>:&nbsp;</td>
        <td>$selectDaerah</td>
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
   	<td width="45%" align="center"><strong>Nama Pegawai</strong></td>
    <td width="44%" align="center"><strong>Name Pengguna Sistem</strong></td>
     <td width="6%" align="center"></td>
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
  	<td class="$row">$senarai.namapegawai</td>
  	<td class="$row" align="center">$senarai.username ($senarai.userlogin)</td>
  	<td class="$row">
		<a href="javascript:doEdit('$senarai.iduserpegawai','$senarai.idunit','$senarai.userid')"><img src="../img/edit.gif" border="0"></a>
		<a href="javascript:doDelete('$senarai.iduserpegawai')"><img src="../img/delete.gif" border="0"></a>
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
 function doChangeNegeri() {
 	if(document.${formName}.idnegeri.value=="0")
 		return;       			
	
	doAjaxCall${formName}("PilihNegeri");
}	
	
function doPilihFail(id,fail) {
	document.${formName}.txtNoFail.value = fail;
	document.${formName}.idPermohonan.value = id;
	doAjaxCall${formName}("PilihFail");
}	

function searchData() {
	if(document.${formName}.txtNoFail.value==""){
		alert('Sila isi No Fail Fail terlebih dahulu');
		return;
		}
	doAjaxCall${formName}("Carian");
}	
	
function doEdit(id,unit,userid) {
	document.${formName}.idlaporanbilfail.value = id;
	document.${formName}.socUnit.value = unit;
	document.${formName}.Form_id_Daerah.value = userid;
	document.${formName}.btnsubmit.value = "Kemaskini";
}
	
	
function doDelete(id) {
	if ( !window.confirm("Adakah anda pasti?") ) return;
	document.${formName}.idlaporanbilfail.value = id;
	doAjaxCall${formName}("Delete");
}
	
function doJenisBayaran() {
	if(document.${formName}.socJenisbayaran.value=="0")
		return;
	doAjaxCall${formName}("SelectJenisBayaran");
}

function tambah() {

	if(document.${formName}.socUnit.value=="0"){
		alert("Sila pilih Nama Pegawai terlebih dahulu");
		return ; 	
	}
	if(document.${formName}.Form_id_Daerah.value=="0"){
		alert("Sila pilih Pengguna Sistem terlebih dahulu");
		return;
	}

	doAjaxCall${formName}("simpan");
}


</script>


