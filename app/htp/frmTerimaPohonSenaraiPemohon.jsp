<style type="text/css">
<!--
.style1 {color: #0033FF}
-->
</style>
<fieldset>
  <legend>Senarai Terima Permohon</legend><!--

  --><!-- <input class=button name=kembali value="Kembali" onclick="backMain()"> -->
  <input class=button name=add value="Tambah" onclick="tambahPermohonan2('$idFail')">
  <table width="100%" cellspacing="0" cellpadding="0" border="0">
  <tr class="table_header">
  	<td>No</td>
  	<td>No Fail</td>
  	<td>Nama Fail</td>
  	<td>Status Pergerakan Fail</td>
  </tr>
  
  #set ( $cnt = 0 )			
  #foreach ( $senarai in $senaraiList )
  	#set ( $cnt = $cnt + 1 )
                #set( $i = $velocityCount )
                #if ( ($i % 2) == 0 )
                    #set( $row = "row2" )
                #else
                    #set( $row = "row1" )
                #end
  <tr>
  <td class="$row">$cnt.</td>
  <td class="$row">
    	<a href="javascript:kemaskiniPermohonan('$senarai.id')" class="style1">
	$senarai.no
</td>
  <td class="$row">$senarai.tajuk</td>
  <td class="$row">$senarai.keterangan</td>
  </tr>
  #end
  </table>
  	<input type="hidden" name="id_kemaskini">
<!--	<input type="hidden" name="command">
  
  </form>
  -->
</fieldset>

<script>
function cancel() {
	document.cari.reset();
}

function kemaskiniPermohonan(id) {
	document.${formName}.id_kemaskini.value = id;
	document.${formName}.command.value = "pkpermohonankemaskini";
	document.${formName}.action = "";
	document.${formName}.submit();
}




function backMain() {
	document.${formName}.action = "";
	document.${formName}.submit();
}
</script>

<script language="JavaScript"> 
//document.forms[0].no_fail.focus(); 
</script>