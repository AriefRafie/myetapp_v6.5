<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td><fieldset>
      <legend>KEMASKINI PENGGUNA ($listUser.User_name) </legend>
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td><table width="100%" border="0" cellspacing="0" cellpadding="3">
            <tr>
              <td width="123" align="left">ID Pengguna</td>
              <td width="8" align="center" class="bold">:</td>
              <td width="681"><span id="sprytextfield1">
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
               <td width="123" align="left">Kementerian</td>
               <td width="8" align="center" class="bold">:</td>
               <td>
               $selectKementerian
               </td>
            </tr>
		<tr>
		  <td width="123" align="left">Agensi</td>
		  <td width="8" align="center" class="bold">:</td>
		  <td>
		  $selectAgensi
		  </td>
		</tr>
            <tr>
              <td align="left">&nbsp;</td>
              <td align="center" class="bold">&nbsp;</td>
              <td>
              	<input type="button" value="Simpan" onclick="update_dataKJP()"/>
                <input type="button" value="Batal" onclick="batal('batal3')"/>
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


function doChanges() {
	doAjaxCall${formName}("kemaskini",'id_user=$listUser.user_id&JenisPengguna=$listUser.user_type' );
}

function doChangesKJP() {
	//alert("OK");
}

function showhide(layer_ref,displayType) {

	if (displayType =="show") displayType = "display:inline";
	else displayType = "display:none";
	
	if (document.all) { //IS IE 4 or 5 (or 6 beta)
	eval( "document.all." + layer_ref + ".style.display = "+displayType);
	}
	if (document.layers) { //IS NETSCAPE 4 or below
	document.layers[layer_ref].display = displayType ;
	}
	if (document.getElementById &&!document.all) {
	hza = document.getElementById(layer_ref);
	hza.style.display = displayType;
	}

} 
var spryconfirm1 = new Spry.Widget.ValidationConfirm("spryconfirm1", "user_pass", {validateOn:["blur"]});
var sprytextfield1 = new Spry.Widget.ValidationTextField("sprytextfield1", "none", {validateOn:["blur"]});
var sprytextfield2 = new Spry.Widget.ValidationTextField("sprytextfield2", "none", {validateOn:["blur"]});
var sprytextfield3 = new Spry.Widget.ValidationTextField("sprytextfield3", "none", {isRequired:false});

</script>