<style type="text/css">
<!--
.bold {	font-weight: bold;
}
.inputField { 
 border: 1px solid #ebb33c;
 background-color:#ffffff;
 font: 11px Arial, Helvetica, sans-serif; color: #000;
 text-align: left;
 width:200px; height:18px;
}
.inputField2 { 
border: 1px solid #ebb33c;
 background-color:#ffffff;
 font: 11px Arial, Helvetica, sans-serif; color: #000;
 text-align: left;
 width:100px; height:18px;
}
.inputField3 { 
border: 1px solid #ebb33c;
 background-color:#ffffff;
 font: 11px Arial, Helvetica, sans-serif; color: #000;
 text-align: left;
 width:300px; height:18px;
}
.inputField4 { 
border: 1px solid #ebb33c;
 background-color:#ffffff;
 font: 11px Arial, Helvetica, sans-serif; color: #000;
 text-align: left;
 width:150px; height:18px;
}
.inputField6 { 
border: 1px solid #ebb33c;
 background-color:#ffffff;
 font: 11px Arial, Helvetica, sans-serif; color: #000;
 text-align: left;
 width:18px; height:18px;
}
.inputField7 { 
border: 1px solid #ebb33c;
 background-color:#ffffff;
 font: 11px Arial, Helvetica, sans-serif; color: #000;
 text-align: left;
 width:42px; height:18px;
}
.inputField8 { 
border: 1px solid #ebb33c;
 background-color:#ffffff;
 font: 11px Arial, Helvetica, sans-serif; color: #000;
 text-align: left;
 width:35px; height:18px;
}
.inputField9 { 
 border: 1px solid #ebb33c;
 background-color:#ffffff;
 font: 11px Arial, Helvetica, sans-serif; color: #000;
 text-align: left;
 width:250px; height:18px;
}
.infoTarikhLahir {
	font-family: Verdana, Geneva, sans-serif;
	font-size: 10px;
}
.infoTarikhLahir {
}
.infoTarikhLahir .infoTarikhLahir {
	color: #000;
}
.noTel {
	font-style: italic;
}
.noTel {
	font-size: 12px;
}
-->
</style>
<script src="../library/js/SpryValidationConfirm.js" type="text/javascript"></script>
<link href="../library/js/SpryValidationConfirm.css" rel="stylesheet" type="text/css" />
<script src="../library/js/SpryValidationTextField.js" type="text/javascript"></script>
<link href="../library/js/SpryValidationTextField.css" rel="stylesheet" type="text/css" />
<style type="text/css">
<!--
.inputField21 { 
 border: 1px solid #ece6b6;
 background-color:#ece6b6;
 font: 11px Arial, Helvetica, sans-serif; color: #000;
 text-align: left;
 width:100px; height:18px;
}
-->
</style>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td><fieldset>
      <legend>KEMASKINI PENGGUNA ($listUser.User_name)      </legend>
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td><table width="100%" border="0" cellspacing="0" cellpadding="3">
            <tr>
              <td width="123" align="left">ID Pengguna</td>
              <td width="8" align="center" class="bold">:</td>
              <td><span id="sprytextfield1">
                <input name="id_pengguna" type="text" class="inputField" id="id_pengguna" value="$listUser.User_login"/>
                <span class="textfieldRequiredMsg">Sila masukkan maklumat ID pengguna.</span></span></td>
              </tr>
            <tr>
              <td width="123" align="left">Nama</td>
              <td width="8" align="center" class="bold">:</td>
              <td><span id="sprytextfield2">
                <input name="user_name" type="text" class="inputField" id="user_name" value="$listUser.User_name"/>
                <span class="textfieldRequiredMsg">Sila masukkan maklumat nama.</span></span></td>
              </tr>
            <tr>
              <td align="left">Katalaluan</td>
              <td align="center" class="bold">:</td>
              <td><span id="sprytextfield3">
                <input type="password" name="user_pass" id="user_pass" class="inputField"/>
                <span class="textfieldRequiredMsg">Sila masukkan katalaluan.</span></span></td>
            </tr>
            <tr>
              <td align="left">Sahkan Katalaluan</td>
              <td align="center" class="bold">:</td>
              <td><span id="spryconfirm1">
                <input type="password" name="password1" id="password1" class="inputField" />
                <span class="confirmRequiredMsg">Sila masukkan katalaluan.</span><span class="confirmInvalidMsg">Katalaluan tidak sama. Sila sahkan katalaluan semula.</span></span></td>
            </tr>
            <tr>
              <td width="123" align="left">Role</td>
              <td width="8" align="center" class="bold">:</td>
              <td><input name="textfield3" type="text" class="inputField21" id="textfield3" size="15" readonly="readonly" value="$listUser.user_role"/></td>
              </tr>
            <tr>
              <td width="123" align="left">Jenis Pengguna</td>
              <td width="8" align="center" class="bold">:</td>
              <td><input name="jenisPengguna" type="text" class="inputField21" id="jenisPengguna" size="15" readonly="readonly" value="$listUser.user_type"/></td>
              </tr>
            <tr>
              <td width="123" align="left">Alamat</td>
              <td width="8" align="center" class="bold">:</td>
              <td><input name="alamat1" type="text" class="inputField3" id="alamat1" size="45"  value="$listUser.alamat1"/></td>
              </tr>
            <tr>
              <td width="123" align="left">&nbsp;</td>
              <td width="8" align="center" class="bold">&nbsp;</td>
              <td><input name="alamat2" type="text" class="inputField3" id="alamat2" size="45"  value="$listUser.alamat2"/></td>
              </tr>
            <tr>
              <td width="123" align="left">&nbsp;</td>
              <td width="8" align="center" class="bold">&nbsp;</td>
              <td><input name="alamat3" type="text" class="inputField3" id="alamat3" size="45"  value="$listUser.alamat3"/></td>
              </tr>
            <tr>
              <td width="123" align="left">Negeri</td>
              <td width="8" align="center" class="bold">:</td>
              <td>              $selectNegeri</td>
              </tr>
            <tr>
              <td width="123" align="left">Poskod</td>
              <td width="8" align="center" class="bold">:</td>
              <td><span id="sprytextfield4">
                <input name="poskod" type="text" class="inputField2" id="poskod" value="$listUser.poskod"/>
                <span class="textfieldInvalidFormatMsg">Format poskod tidak sah. Sila masukkan format poskod yang betul.</span></span></td>
              </tr>
            <tr>
              <td width="123" align="left">Emel</td>
              <td width="8" align="center" class="bold">:</td>
              <td><span id="sprytextfield5">
                <input name="emel" type="text" class="inputField" id="emel" value="$listUser.emel"/>
                <span class="textfieldInvalidFormatMsg">Format emel tidak sah. Sila masukkan format emel yang betul.</span></span></td>
              </tr>
            <tr>
              <td width="123" align="left">No Telefon Bimbit</td>
              <td width="8" align="center" class="bold">:</td>
              <td><span id="sprytextfield6">
                <input name="no_cellphone" type="text" class="inputField4" id="no_cellphone" value="$listUser.no_hp"/>
                <span class="textfieldInvalidFormatMsg">Format no. telefon bimbit tidak sah. Sila masukkan no. telefon bimbit yang betul seperti cth (0128765432).</span></span></td>
              </tr>
            <tr>
              <td width="123" align="left">No. Telefon</td>
              <td width="8" align="center" class="bold">:</td>
              <td><span id="sprytextfield7">
                <input name="no_tel" type="text" class="inputField4" id="no_tel" value="$listUser.no_tel" />
                <span class="textfieldInvalidFormatMsg">Format no. telefon tidak sah. Sila masukkan format no. telefon yang betul seperti cth (0398712343).</span></span></td>
              </tr>
            <tr>
              <td width="123" align="left">No. Faks</td>
              <td width="8" align="center" class="bold">:</td>
              <td><span id="sprytextfield8">
                <input name="no_faks" type="text" class="inputField4" id="no_faks" value="$listUser.no_fax" />
                <span class="textfieldInvalidFormatMsg">Format no. faks tidak sah. Sila masukkan format no. faks yang betul seperti cth (0398732139).</span></span></td>
              </tr>
            <tr>
              <td width="123" align="left">No. Kad Pengenalan Baru</td>
              <td width="8" align="center" class="bold">:</td>
              <td width="681"><input name="kp1" type="text" class="inputField7" id="kp1" size="10" value="$listUser.Kp1"/>
                -
                <input name="kp2" type="text" class="inputField6" id="kp2" size="5"  value="$listUser.Kp2"/>
                -
                <label>
                  <input name="kp3" type="text" class="inputField8" id="kp3" size="8"  value="$listUser.Kp3"/>
                  </label></td>
              </tr>
            <tr>
              <td width="123" align="left">Umur</td>
              <td width="8" align="center" class="bold">:</td>
              <td><span id="sprytextfield10">
                <input name="umur" type="text" id="umur" value="$listUser.umur" class="inputField2"/>
                <span class="textfieldInvalidFormatMsg">Format umur tidak sah. Sila masukkan format umur yang betul.</span></span></td>
              </tr>
            <tr>
              <td width="123" align="left">Jantina</td>
              <td width="8" align="center" class="bold">:</td>
              <td><select name="selectJantina" id="selectJantina" class="inputField4">
                <option>Sila Pilih Jantina</option>
                  <option value="L" #if($selectJantina=="L") selected #end>Lelaki</option>
                  <option value="P" #if($selectJantina=="P") selected #end>Perempuan</option>
                </select></td>
              </tr>
            <tr>
              <td width="123" align="left">Taraf Perkahwinan</td>
              <td width="8" align="center" class="bold">:</td>
              <td><select name="selectTarafPerkahwinan" id="selectTarafPerkahwinan" class="inputField">
                <option>Sila Pilih Taraf Perkahwinan</option>
                <option value="B" #if($selectTP=="B") selected #end>Bujang</option>
                <option value="K" #if($selectTP=="K") selected #end>Sudah Berkahwin</option>
              </select></td>
              </tr>
            <tr>
              <td width="123" align="left">Tarikh Lahir</td>
              <td width="8" align="center" class="bold">:</td>
              <td><span id="sprytextfield9">
                <input name="txdTarikhLahir" type="text" class="inputField4" id="txdTarikhLahir" value="$listUser.tarikh_lahir" />
                <a href="javascript:displayDatePicker('txdTarikhLahir',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/><em class="infoTarikhLahir"> (Hari/Bulan/Tahun)</em> <span class="infoTarikhLahir">Cth: 24/01/1985</span>
                <span class="textfieldInvalidFormatMsg">Format tarikh lahir tidak sah. Sila masukkan format tarikh lahir yang betul.</span></td>
            </tr>
            <tr>
              <td align="left">&nbsp;</td>
              <td align="center" class="bold">&nbsp;</td>
              <td><input type="submit" name="button3" id="button3" value="Simpan" onclick="simpan_data('simpanDataOnilne')"/>
                <input type="submit" name="button4" id="button4" value="Batal" onclick="batal('batal')"/>
                <input type="submit" name="button2" id="button2" value="Kembali ke Senarai Pengguna" onclick="kembali('')"/></td>
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
    <td><input name="id_user" type="hidden" id="id_user" value="$listUser.user_id" />
    <input name="JenisPengguna" type="hidden" id="JenisPengguna" value="$listUser.user_type" />
    <input name="userLoginOld" type="hidden" id="userLoginOld" value="$listUser.User_login" /></td>
  </tr>
</table>
<script>
function kembali(s) {	
    document.${formName}.command.value = s;
    document.${formName}.action = "";
    document.${formName}.submit();
}
function batal(s) {	
    document.${formName}.command.value = s;
    document.${formName}.action = "";
    document.${formName}.submit();
}

function simpan_data(s) {	
    document.${formName}.command.value = s;
    document.${formName}.action = "";
    document.${formName}.submit();
}
var spryconfirm1 = new Spry.Widget.ValidationConfirm("spryconfirm1", "user_pass", {validateOn:["blur"]});
var sprytextfield1 = new Spry.Widget.ValidationTextField("sprytextfield1", "none", {validateOn:["blur"]});
var sprytextfield2 = new Spry.Widget.ValidationTextField("sprytextfield2", "none", {validateOn:["blur"]});
var sprytextfield3 = new Spry.Widget.ValidationTextField("sprytextfield3", "none", {isRequired:false});
var sprytextfield4 = new Spry.Widget.ValidationTextField("sprytextfield4", "zip_code", {validateOn:["blur"], isRequired:false});
var sprytextfield5 = new Spry.Widget.ValidationTextField("sprytextfield5", "email", {validateOn:["blur"], isRequired:false});
var sprytextfield6 = new Spry.Widget.ValidationTextField("sprytextfield6", "integer", {validateOn:["blur"], isRequired:false});
var sprytextfield7 = new Spry.Widget.ValidationTextField("sprytextfield7", "integer", {validateOn:["blur"], isRequired:false});
var sprytextfield8 = new Spry.Widget.ValidationTextField("sprytextfield8", "integer", {validateOn:["blur"], isRequired:false});
var sprytextfield9 = new Spry.Widget.ValidationTextField("sprytextfield9", "date", {format:"dd/mm/yyyy", validateOn:["blur"], isRequired:false});
var sprytextfield10 = new Spry.Widget.ValidationTextField("sprytextfield10", "integer", {validateOn:["blur"], isRequired:false});
</script>