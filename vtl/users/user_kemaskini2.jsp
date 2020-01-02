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
              <td width="681"><span id="sprytextfield1">
                <input name="id_pengguna" readonly="readonly"  type="text" id="id_pengguna" value="$listUser.User_login"/>
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
              <td width="123" align="left">Emel</td>
              <td width="8" align="center" class="bold">:</td>
              <td>
              <input name="emel" type="text" class="inputField" id="emel" value="$!listUser.emel"/>
              </td>
            </tr>  
            
            <tr>
              <td width="123" align="left">Role</td>
              <td width="8" align="center" class="bold">:</td>
              <td><select name="user_role" onChange="doChanges()" class="inputField4">
	  <option>Sila Pilih Role Pengguna</option>
            #foreach ( $role in $userRoles )
                #if ( $selectRole == $role.getName() )
                    #set ( $selected = "selected")
                #else
                    #set ($selected = "")
                #end
                <option value="$role.getName()" $selected>$role.getName()</option>
            #end
            </select></td>
            </tr>
            <tr>
              <td width="123" align="left">Jenis Pengguna</td>
              <td width="8" align="center" class="bold">:</td>
              <td>
              <input name="jenisPengguna" type="text" class="inputField21" id="jenisPengguna" size="15" readonly="readonly" value="$listUser.user_type"/></td>
            </tr>
            <tr>
              <td align="left">Bangsa</td>
              <td align="center" class="bold">:</td>
              <td><select name="selectBangsa" id="selectBangsa" class="inputField4">
                <option value=-1>Sila Pilih Bangsa</option>
                 #foreach($bangsa in $listBangsa)
                 <option value="$bangsa.id_bangsa" #if($selectBangsa == $bangsa.id_bangsa) selected #end>$bangsa.kod_bangsa - $bangsa.keterangan</option>
                 #end
              </select></td>
            </tr>
            <tr>
              <td align="left">Agama</td>
              <td align="center" class="bold">:</td>
              <td><select name="selectAgama" id="selectAgama" class="inputField4">
                <option value=-1>Sila Pilih Agama</option>
                #foreach ($agama in $listAgama)
                	<option value="$agama.id_agama" #if($selectAgama == $agama.id_agama) selected #end>$agama.kod_agama - $agama.keterangan </option>
                #end
              </select></td>
            </tr>
            <tr>
              <td align="left">Jawatan</td>
              <td align="center" class="bold">:</td>
              <td>$selectJawatan</td>
            </tr>
            <tr>
              <td align="left">Seksyen</td>
              <td align="center" class="bold">:</td>
              <td>$selectSeksyen </td>
            </tr>
            <tr>
              <td align="left">Negeri</td>
              <td align="center" class="bold">:</td>
              <td>$selectNegeri </td>
            </tr>
            <tr>
              <td width="123" align="left">Pejabat</td>
              <td width="8" align="center" class="bold">:</td>
              <td>$selectPejabatJKPTG </td>
            </tr>
            <tr>
              <td width="123" align="left">Nama Pejabat</td>
              <td width="8" align="center" class="bold">:</td>
              <td>$!pejabatInfo.nama_pejabat</td>
            </tr>
            <tr>
              <td width="123" align="left">Alamat</td>
              <td width="8" align="center" class="bold">:</td>
              <td>$!pejabatInfo.alamat1,</td>
            </tr>
            <tr>
              <td align="left">&nbsp;</td>
              <td align="center" class="bold">&nbsp;</td>
              <td>$!pejabatInfo.alamat2,</td>
            </tr>
            <tr>
              <td align="left">&nbsp;</td>
              <td align="center" class="bold">&nbsp;</td>
              <td>$!pejabatInfo.alamat3,</td>
            </tr>
            <tr>
              <td align="left">Daerah</td>
              <td align="center" class="bold">:</td>
              <td>$!pejabatInfo.nama_daerah</td>
            </tr>
            <tr>
              <td align="left">Negeri</td>
              <td align="center" class="bold">:</td>
              <td>$!pejabatInfo.nama_negeri</td>
            </tr>
            <tr>
              <td align="left">Negara</td>
              <td align="center" class="bold">:</td>
              <td>$!pejabatInfo.nama_negara</td>
            </tr>
            <tr>
              <td align="left">No. Telefon</td>
              <td align="center" class="bold">:</td>
              <td>$!pejabatInfo.no_tel</td>
            </tr>
            <tr>
              <td align="left">No. Fak</td>
              <td align="center" class="bold">:</td>
              <td>$!pejabatInfo.no_fax</td>
            </tr>
            
                        <tr>
              <td align="left" valign="top">Daerah Jagaan</td>
              <td align="center" valign="top" class="bold">:</td>
              <td>$!daerahJagaan </td>
            </tr>
            <!--<tr>
              <td align="left">Page Style</td>
              <td align="center" class="bold">:</td>
              <td><select name="page_style" class="inputField4">

            ##foreach ( $style in $pageStyleList )
                <option value="$style.getCssName()" #if($selectPages == $style.getCssName()) selected #end>$style.getTitle()</option>
            ##end
            </select></td>
            </tr>-->
            <tr>
              <td align="left">&nbsp;</td>
              <td align="center" class="bold">&nbsp;</td>
              <td><input type="submit" name="button3" id="button3" value="Simpan" onclick="simpan_data('simpanDataInternal')"/>
                <input type="submit" name="button4" id="button4" value="Batal" onclick="batal('batal2')"/>
                <input type="submit" name="button2" id="button2" value="Kembali" onclick="kembali('')"/></td>
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
    <input name="userLoginOld" type="hidden" id="userLoginOld" value="$listUser.User_login" />
    <input name="id_daerah" type="hidden" id="id_daerah" value="$pejabatInfo.id_daerah" />
    <input name="page_style" type="hidden" id="page_style" value="$listUser.getCss_name2() " /></td>
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
function doChanges() {
	doAjaxCall${formName}("kemaskini",'id_user=$listUser.user_id&JenisPengguna=$listUser.user_type' );
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