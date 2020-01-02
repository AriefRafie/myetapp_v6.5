<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td><fieldset>
      <legend>MAKLUMAT PENGGUNA ($listUser.getUser_name()) TERPERINCI</legend>
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td><table width="100%" border="0" cellspacing="0" cellpadding="3">
            <tr>
              <td width="123" align="left">ID Pengguna</td>
              <td width="8" align="center" class="bold">:</td>
              <td width="681"><input name="textfield" type="text" class="inputField" id="textfield" size="30" readonly="readonly" 
              value="$listUser.getUser_login()"/></td>
            </tr>
            <tr>
              <td width="123" align="left">Nama</td>
              <td width="8" align="center" class="bold">:</td>
              <td><input name="textfield2" type="text" class="inputField" id="textfield2" 
              size="45" readonly="readonly" value="$listUser.getUser_name()"/></td>
            </tr>
             <tr>
               <td width="123" align="left">Kementerian</td>
               <td width="8" align="center" class="bold">:</td>
               <td><input name="textfield2" type="text" 
               class="inputField" id="textfield2" 
               size="75" 
               readonly="readonly" value="$listUser.getNama_kementerian()"/></td>
            </tr>
		<tr>
		  <td width="123" align="left">Agensi</td>
		  <td width="8" align="center" class="bold">:</td>
		  <td><input name="textfield2" type="text" class="inputField" 
		  id="textfield2" size="75" readonly="readonly" 
		  value="$listUser.getNama_agensi()"/></td>
		</tr>
            <tr>
              <td align="left">&nbsp;</td>
              <td align="center" class="bold">&nbsp;</td>
              <td>
              <input type="button" value="Kemaskini" onclick="submitKemaskini('kemaskini', '$listUser.getUser_id()', 'KJP')"/>
              <input type="button" value="kembali" onclick="batal('batal3')"/>
              </td>
            </tr>
          </table></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
        </tr>
        </table>
    </fieldset></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>
    <input name="id_user" type="hidden" id="id_user" value="$listUser.user_id" />
    <input name="JenisPengguna" type="hidden" id="JenisPengguna" value="$listUser.user_type" />
  </tr>
</table>
<script>
function kembali(s) {	
    document.${formName}.command.value = s;
    document.${formName}.action = "";
    document.${formName}.submit();
}
function submitKemaskini(s, t, u) {	
   doAjaxCall${formname}('kemaskini','id_user=t&JenisPengguna=u');
}
</script>