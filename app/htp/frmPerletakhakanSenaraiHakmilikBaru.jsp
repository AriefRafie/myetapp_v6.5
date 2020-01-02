<!--#parse("app/htp/frmButtonParse.jsp") -->

<fieldset><legend>HAKMILIK PERLETAKHAKAN</legend>
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
<input name="actionPerletakhakan" type="hidden" />
<input name="idFail" type="hidden" value="$idFail"/>
<input name="idHakmilikurusan" type="hidden" value="$idHakmilikurusan"/>
<table width="100%">
<tr>
	<td>
<fieldset>
<legend>Carian</legend>
<table width="100%" border="0">
  <tr>
    <td width="29%">No Fail</td>
    <td width="1%">:</td>
    <td width="70%"><input type="text" name="txtNoFail" id="txtNoFail"></td>
  </tr>
  <tr>
    <td width="29%">&nbsp;</td>
    <td width="1%">&nbsp;</td>
    <td width="70%">&nbsp;</td>
  </tr>
  <tr>
    <td width="29%">&nbsp;</td>
    <td width="1%">&nbsp;</td>
    <td width="70%"><input type="button" name="cmdCari" id="cmdCari" value="Cari" onClick="carian()"/>
    <input type="submit" name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" onClick="kosongkan"/></td>
  </tr>
</table>
</fieldset>
</td>
</tr>
</table>
<table width="100%">
<tr>
<td>
<fieldset>
<legend>SENARAI FAIL</legend>
#parse("app/utils/record_paging.jsp")
<table width="100%" border="0">
  <tr class="table_header">
    <td>Bil</td>
    <td>No Fail</td>
    <td>Tarikh Mohon</td>
    <td>Tujuan Mohon</td>
    <td>Status Fail</td>
  </tr>
 #foreach ($fail in $SenaraiFail)
  
  #if ($fail.bil == '') 
  	#set ($row = 'row1')
  #elseif ($fail.bil % 2 != 0)
  	#set ($row = 'row1')
  #else 
  	#set ($row = 'row2')
  #end 
  <tr>
    <td class="$row">$fail.bil </td>
    #if ($fail.bil == '') 
    <td  class="$row">$fail.noFail</td>
    #else
    <td class="$row"><a href="javascript:viewFailById('$fail.idFail')"><font color="blue">$fail.noFail</font></a></td>
    #end
    <td class="$row">$fail.tarikhDaftar</td>
    <td class="$row">$fail.tujuan</td>
    <td class="$row">$fail.keterangan</td>
  </tr>
  #end
</table></fieldset></td></tr></table>
</fieldset>
<script>
function viewFailById(id){
	document.${formName}.actionPerletakhakan.value = "papar";
	alert(id);
	document.${formName}.idFail.value = id;
	document.${formName}.submit();
}


</script>