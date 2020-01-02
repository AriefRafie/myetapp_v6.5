  <style type="text/css">
  <!--
  .style1 {color: #0033FF}
  -->
  </style><!--<strong> Senarai Fail Cukai </strong>
<br>--><br>
<fieldset> <legend>Maklumat Carian</legend>
<!--<form name="f1" method="post"> -->
  <table border="0" cellpadding="0" cellspacing="0" width="100%">
    <tbody>
    	<tr>			  
			<td colspan="2" align="center">&nbsp;</td>
		</tr>
    
      <tr >
        <td align="right" width="40%">Nama Fail : &nbsp;&nbsp;</td>
        <td><input name="NamaFail" type="text" size="43" maxlength="80" value="$carian" onkeyup="this.value=this.value.toUpperCase();"></td>
      </tr>
      <tr>
        <td align="right" width="40%">No. Fail : &nbsp;&nbsp;</td>
        <td><input name="NoFail" type="text" size="43" maxlength="43" value="$carianNoFail" onkeyup="this.value=this.value.toUpperCase();"></td>
      </tr>
      <tr >
        <td height="25"  align="right">Negeri : &nbsp;&nbsp;</td>
        <td>$selectNegeri</td>
      </tr>
      <tr>
        <td></td>
        <td>
        	<input class="button" name="cari" value="Cari" type="button" onclick="javascript:search_data()">
        	<input class="button" value="Kosongkan" onclick="javascript:cancel()" type="button">
     	</td>
      </tr>
    </tbody>
  </table>
  <!--<input type="hidden" name="command">
</form>-->
</fieldset>
<fieldset>
<legend>Senarai Fail</legend>
   <!-- <form name="tbh" method="post"> 
	<input name="add" value="Tambah" type="button" onClick="Tambah()"> -->
    <!--<input type="hidden" name="command">
</form>
<form name="f2" method="post">-->
  <table border="0" cellpadding="0" cellspacing="0" width="100%">
    <tbody>
      <tr class="table_header">
        <td ><b>No</b></td>
        <td ><b>Negeri</b></td>
        <td ><b>No Fail</b></td>
        <td ><b>Status</b></td>
      </tr>	
      #set ($count = 0)
      #foreach ( $fail in $Senaraifail )
      	#set ($count = $count+1)
          #set( $i = $velocityCount )
          #if ( ($i % 2) != 1 )
               #set( $row = "row2" )
          #else
               #set( $row = "row1" )
          #end
      <tr>
        <td class="$row" scope="row">$count</td>
        <td class="$row">$fail.negeri</td>
        <td class="$row"><a href="javascript:seterusnya('$fail.idpermohonan', '$fail.no')" class="style1">$fail.no</a></td>
        <td class="$row">$fail.keterangan</td>
      </tr>
      #end
      #if ($count == 0)
      <tr> 
        <td colspan="5" scope="row"><font color="#FF0000">Tiada Rekod.</font></td>
      </tr>
      #end
    </tbody>
  </table>
  <input type="hidden" name="idpermohonan">
  <input type="hidden" name="idFail" value="$fail.idFail">
  <input type="hidden" name="noFail">
  <input type="hidden" name="mode">
  <!-- <input type="hidden" name="command">
</form> -->
</fieldset>
<script>
	function cancel() {
	document.${formName}.reset();
	/*document.f1.NamaFail.value = "";
	document.f1.NoFail.value = "";
	document.f1.socNegeri.value = "";
	document.f1.NamaFail.focus();
*/
}

function search_data(){
	//document.f1.command.value = "";
		//document.f1.nama_fail.value = key;
	//document.f1.action = "";
	//document.f1.submit();
	doAjaxCall${formName}("carian");
	
}
function seterusnya(id, no) {
	document.f2.idpermohonan.value = id;
	document.f2.noFail.value = no;
	document.f2.mode.value = "view";
	document.f2.command.value = "surat";
	document.f2.action = "";
	document.f2.submit();
}
</script>
<script language="JavaScript"> 
	//document.forms[0].NamaFail.focus(); 
	#if ($Negeri == 20)
		//document.forms[0].socNegeri.value = "";
	#end
</script>