<style type="text/css">
<!--
  .link {color: #0000FF}
  .mandatori {color:#FF0000}
-->
</style>
#if ($changePass == 'true')
<fieldset>
  <div class="success">Kata Laluan telah berjaya ditukar.</div>
</fieldset>
<br />
##end
##if ($diffPass == 'true')
#elseif ($changePass == 'false')
<fieldset>
  <div class="success">Kata Laluan tidak berjaya ditukar. Sila semak Kata Laluan yang dimasukkan.</div>
</fieldset>
<br />
#end
<fieldset>
  <legend><strong>MAKLUMAT PENGGUNA</strong>
  </legend><table width="100%">
    <tr>
      <td align="right" valign="middle" scope="row">Nama Pengguna</td>
      <td align="center" valign="middle" scope="row">:</td>
      <td align="left" valign="middle"><strong>$!User_Name</strong></td>
    </tr>
    <tr>
      <td align="right" valign="middle" scope="row">Kata Laluan Baru</td>
      <td align="center" valign="middle" scope="row">:</td>
      <td align="left" valign="middle"><input name="User_NewPass1" type="password" id="User_NewPass1" value="$!User_NewPass1" size="50" maxlength="255" $READONLY_JPPH /></td>
    </tr>
    <tr>
      <td width="29%" align="right" valign="middle" scope="row">Pastikan Kata Laluan Baru</td>
      <td width="1%" align="center" valign="middle" scope="row">:</td>
      <td width="70%" align="left" valign="middle"><input name="User_NewPass2" type="password" id="User_NewPass2" value="$!User_NewPass2" size="50" maxlength="255" $READONLY_JPPH /></td>
    </tr>
    <tr>
      <td align="right" valign="middle" scope="row">Kata Laluan Sekarang</td>
      <td align="center" valign="middle" scope="row">:</td>
      <td align="left" valign="middle"><input name="User_CurPass" type="password" id="User_CurPass" value="$!User_CurPass" size="50" maxlength="255" $READONLY_JPPH /></td>
    </tr>
    <tr>
      <td colspan="2">&nbsp;</td>
      <td valign="top" scope="row">
        <input id="cmdUpdate" name="cmdUpdate" type="button" value="Kemaskini" onclick="updatePass();" />
        <input id="cmdKosongkan" name="cmdKosongkan" type="button" value="Kosongkan" onclick="emptyForms();" />
      </td>
    </tr> 
    <tr>
      <td colspan="3" align="center" valign="top" scope="row">&nbsp;</td>
    </tr>
  </table>
</fieldset>
<br />
<input type="hidden" id="idUser" name="idUser" value="$idUser" />
<input name="action2" id="action2" type="hidden" value="$action2" />
<script>
	function updatePass(ID_PERMOHONAN, ID_HA) {	
		//document.${formName}.action = "?_portal_module=ekptg.view.integrasi.FrmViewIntChangePassword&action2=updatePass";
		//document.${formName}.method = "POST";
		//document.${formName}.submit();
		if ( document.${formName}.User_NewPass1.value == "" ) { document.${formName}.User_NewPass1.focus(); return; }
		
		if ( document.${formName}.User_NewPass1.value != document.${formName}.User_NewPass2.value ) {
        	//alert("Confirm password does not match!");
         	alert("Pastikan Kata Laluan Tidak Sama!");
        	document.${formName}.User_NewPass2.focus();
        	return;
    	}
		
		document.${formName}.action2.value = "updatePass";
		doAjaxCall${formName}("updatePass");
			
	}
	
	function emptyForms() {
		document.${formName}.action = "?_portal_module=ekptg.view.integrasi.FrmViewIntChangePassword&action2=";
	    document.${formName}.method = "POST";
		document.${formName}.submit();
		
	}
</script>