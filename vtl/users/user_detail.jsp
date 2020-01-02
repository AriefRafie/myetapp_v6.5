<style type="text/css">
<!--
.bold {
	font-weight: bold;
}
.inputField {
 
 border: 1px solid #ece6b6;
 background-color:#ece6b6;
 font: 11px Arial, Helvetica, sans-serif; color: #000;
 text-align: left;
 width:200px; height:18px;
}
.inputField2 {
 
 border: 1px solid #ece6b6;
 background-color:#ece6b6;
 font: 11px Arial, Helvetica, sans-serif; color: #000;
 text-align: left;
 width:100px; height:18px;
}
.inputField3 {
 
 border: 1px solid #ece6b6;
 background-color:#ece6b6;
 font: 11px Arial, Helvetica, sans-serif; color: #000;
 text-align: left;
 width:300px; height:18px;
}
.inputField4 {
 
 border: 1px solid #ece6b6;
 background-color:#ece6b6;
 font: 11px Arial, Helvetica, sans-serif; color: #000;
 text-align: left;
 width:150px; height:18px;
}
.inputField5 {
 
 border: 1px solid #ece6b6;
 background-color:#ece6b6;
 font: 11px Arial, Helvetica, sans-serif; color: #000;
 text-align: left;
 width:50px; height:18px;
}
.inputField6 {
 
 border: 1px solid #ece6b6;
 background-color:#ece6b6;
 font: 11px Arial, Helvetica, sans-serif; color: #000;
 text-align: left;
 width:18px; height:18px;
}
.inputField7 {
 
 border: 1px solid #ece6b6;
 background-color:#ece6b6;
 font: 11px Arial, Helvetica, sans-serif; color: #000;
 text-align: left;
 width:42px; height:18px;
}
.inputField8 {
 
 border: 1px solid #ece6b6;
 background-color:#ece6b6;
 font: 11px Arial, Helvetica, sans-serif; color: #000;
 text-align: left;
 width:35px; height:18px;
}

-->
</style>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td><fieldset>
      <legend>MAKLUMAT PENGGUNA ($listUser.User_name) TERPERINCI</legend>
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td><table width="100%" border="0" cellspacing="0" cellpadding="3">
            <tr>
              <td width="123" align="left">ID Pengguna</td>
              <td width="8" align="center" class="bold">:</td>
              <td><input name="textfield" type="text" class="inputField" id="textfield" size="30" readonly="readonly" value="$listUser.User_login"/></td>
            </tr>
            <tr>
              <td width="123" align="left">Nama</td>
              <td width="8" align="center" class="bold">:</td>
              <td><input name="textfield2" type="text" class="inputField" id="textfield2" size="45" readonly="readonly" value="$listUser.User_name"/></td>
            </tr>
            <tr>
              <td width="123" align="left">Role</td>
              <td width="8" align="center" class="bold">:</td>
              <td><input name="textfield3" type="text" class="inputField2" id="textfield3" size="15" readonly="readonly" value="$listUser.user_role"/></td>
            </tr>
            <tr>
              <td width="123" align="left">Jenis Pengguna</td>
              <td width="8" align="center" class="bold">:</td>
              <td><input name="textfield4" type="text" class="inputField2" id="textfield4" size="15" readonly="readonly" value="$listUser.user_type"/></td>
            </tr>
            <tr>
              <td width="123" align="left">Alamat</td>
              <td width="8" align="center" class="bold">:</td>
              <td><input name="textfield5" type="text" class="inputField3" id="textfield5" size="45" readonly="readonly" value="$listUser.alamat1"/></td>
            </tr>
            <tr>
              <td width="123" align="left">&nbsp;</td>
              <td width="8" align="center" class="bold">&nbsp;</td>
              <td><input name="textfield6" type="text" class="inputField3" id="textfield6" size="45" readonly="readonly" value="$listUser.alamat2"/></td>
            </tr>
            <tr>
              <td width="123" align="left">&nbsp;</td>
              <td width="8" align="center" class="bold">&nbsp;</td>
              <td><input name="textfield7" type="text" class="inputField3" id="textfield7" size="45" readonly="readonly" value="$listUser.alamat3"/></td>
            </tr>
            <tr>
              <td width="123" align="left">Negeri</td>
              <td width="8" align="center" class="bold">:</td>
              <td><input name="textfield8" type="text" class="inputField3" id="textfield8" readonly="readonly" value="$listUser.nama_negeri"/></td>
            </tr>
            <tr>
              <td width="123" align="left">Poskod</td>
              <td width="8" align="center" class="bold">:</td>
              <td><input name="textfield9" type="text" class="inputField2" id="textfield9" size="15" readonly="readonly" value="$listUser.poskod"/></td>
            </tr>
            <tr>
              <td width="123" align="left">Emel</td>
              <td width="8" align="center" class="bold">:</td>
              <td><input name="textfield10" type="text" class="inputField" id="textfield10" size="35" readonly="readonly" value="$listUser.emel"/></td>
            </tr>
            <tr>
              <td width="123" align="left">No Telefon Bimbit</td>
              <td width="8" align="center" class="bold">:</td>
              <td><input name="textfield11" type="text" class="inputField4" id="textfield11" readonly="readonly" value="$listUser.no_hp"/></td>
            </tr>
            <tr>
              <td width="123" align="left">No. Telefon</td>
              <td width="8" align="center" class="bold">:</td>
              <td><input name="textfield12" type="text" class="inputField4" id="textfield12" readonly="readonly" value="$listUser.no_tel"/></td>
            </tr>
            <tr>
              <td width="123" align="left">No. Faks</td>
              <td width="8" align="center" class="bold">:</td>
              <td><input name="textfield13" type="text" class="inputField4" id="textfield13" readonly="readonly" value="$listUser.no_fax"/></td>
            </tr>
            <tr>
              <td width="123" align="left">No. Kad Pengenalan Baru</td>
              <td width="8" align="center" class="bold">:</td>
              <td width="681"><input name="textfield14" type="text" class="inputField7" id="textfield14" size="10" readonly="readonly" value="$listUser.Kp1"/>
-
  <input name="textfield19" type="text" class="inputField6" id="textfield19" size="5" readonly="readonly" value="$listUser.Kp2"/>
-
<label>
  <input name="textfield20" type="text" class="inputField8" id="textfield20" size="8" readonly="readonly" value="$listUser.Kp3"/>
</label></td>
            </tr>
            <tr>
              <td width="123" align="left">Umur</td>
              <td width="8" align="center" class="bold">:</td>
              <td><input name="textfield15" type="text" class="inputField2" id="textfield15" size="10" readonly="readonly" value="$listUser.umur"/></td>
            </tr>
            <tr>
              <td width="123" align="left">Jantina</td>
              <td width="8" align="center" class="bold">:</td>
              <td><input name="textfield16" type="text" class="inputField4" id="textfield16" readonly="readonly" value="$listUser.jantina"/></td>
            </tr>
            <tr>
              <td width="123" align="left">Taraf Perkahwinan</td>
              <td width="8" align="center" class="bold">:</td>
              <td><input name="textfield17" type="text" class="inputField4" id="textfield17" readonly="readonly" value="$listUser.taraf_perkahwinan"/></td>
            </tr>
            <tr>
              <td width="123" align="left">Tarikh Lahir</td>
              <td width="8" align="center" class="bold">:</td>
              <td><input name="textfield18" type="text" class="inputField4" id="textfield18" readonly="readonly" value="$listUser.tarikh_lahir"/></td>
            </tr>
            <tr>
              <td align="left">&nbsp;</td>
              <td align="center" class="bold">&nbsp;</td>
              <td><input type="submit" name="button2" id="button2" value="Kemaskini" onclick="submitKemaskini('kemaskini', '$listUser.user_id', '$listUser.user_type')"/>
                <input type="submit" name="button" id="button" value="Kembali" onclick="kembali('')" /></td>
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
    <td><input name="id_user" type="hidden" id="id_user" value="$listUser.user_id" />
    <input name="JenisPengguna" type="hidden" id="JenisPengguna" value="$listUser.user_type" />
    <input type="hidden" name="id_negeri" id="id_negeri" value="$listUser.id_negeri" />
    <input name="user_role" type="hidden" id="user_role" value="$listUser.user_role" />
    <input name="JenisPengguna" type="hidden" id="JenisPengguna" value="$listUser.user_type" />
    <input name="selectJantina" type="hidden" id="selectJantina" value="$listUser.jantina_id" />
    <input name="selectTP" type="hidden" id="selectTP" value="$listUser.taraf_perkahwinan_id" />
    <input name="hiddenField" type="hidden" id="hiddenField" value="$listUser.User_login" /></td>
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