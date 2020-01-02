  <style type="text/css">
  <!--
  .style1 {color: #0033FF}
  -->
  </style>
<fieldset>
  <legend>$title CUKAI</legend>

  <table width="100%" cellspacing="1" cellpadding="2" border="0">
  <tr class="table_header">
  	<td>Bil#</td>
  	<td>Negeri</td>
  	<td>No Fail Seksyen</td>
  	<td>Status Fail</td>
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
  	
	$senarai.tajuk
	
  </td>

    <td class="$row"><a href="javascript:uploadFail('$senarai.idpermohonan')" class="style1">$senarai.nofail</a></td>
    <td class="$row">$senarai.keterangan</td>
  </tr>
  #end
  
#if ($cnt == 0)
  	<tr> 
  		<td colspan="4" scope="row"><font color="#FF0000">Tiada Rekod.</font></td>
  	</tr>
#end
  </table>
    <input type="hidden" name="idpermohonan" >
   	<input type="hidden" name="idkemaskini" >
   	<input type="hidden" name="fail" >
   	<input type="hidden" name="pagemode" >
   	<input type="hidden" name="langkah" value="0" >
 <!-- </form> -->
</fieldset>

<script>
function cancel() {
	document.${formName}.reset();
}

function selectBayaran() {
	var type = document.${formName}.socbayaran.value;
	
	document.${formName}.command.value = "cukaiperingkatbayar";
	//document.${formName}.langkah.value = '0->0';
	document.${formName}.action = "";
	document.${formName}.submit();
}

function uploadFail(id) {
	document.${formName}.command.value = "cukaifailupload";
	document.${formName}.idpermohonan.value = id;
	document.${formName}.action = "";
	document.${formName}.submit();
}

function tambahPermohonan() {
	document.${formName}.command.value = "pkfailbaru";
	document.${formName}.langkah.value = '0->1';
	//document.${formName}.method = "post";
	document.${formName}.pagemode.value = "0";
	document.${formName}.action = "";
	document.${formName}.submit();
	
}
</script>

<script language="JavaScript"> 
//document.forms[0].no_fail.focus(); 
</script>