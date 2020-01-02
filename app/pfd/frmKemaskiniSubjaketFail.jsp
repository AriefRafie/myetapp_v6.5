<style type="text/css">
<!--
.style2 {color: #FF0000}
.style3 {font-size: 10px}
.style4 {
	font-style: italic;
	font-size: 9px;
}
.style5 {font-size: 9px}
.style6 {color: #0000FF}
.style7 {color: #000000}
-->
</style>
<fieldset>
<legend>MAKLUMAT FAIL</legend>
<table width="100%">
   <tr>
    <td width="29%" scope="row">NO FAIL</td>
    <td width="1%" scope="row"><div align="right">:</div></td>
    <td width="70%">
      <span class="style42 style6">
      <label><span class="style42">$noFailAsal.toUpperCase() </span></label></span></td>
  </tr>
  <tr>
    <td scope="row">TAJUK FAIL</td>
    <td scope="row"><div align="right">:</div></td>
    <td class="style42 style6">$tajukFail.toUpperCase()</td>
  </tr>
</table>
</fieldset>

<fieldset>
<legend>MAKLUMAT SUBJAKET FAIL</legend>
             #if($subjaket == 'success')
  	<div class="success">Fail Subjaket baru telah dijana.</div>
 			#end
<table width="100%">
 #if ($mode == 'papar' || $mode == 'update' || $mode == 'kemaskini')
  <tr>
          <td class="style40" scope="row">&nbsp;</td>
          <td scope="row"><span class="style43">No Fail Baru </span></td>
          <td align="left" valign="top" scope="row"><div align="center">:</div></td>
        <td><span class="style42 style6">
            <label>$noFailSubjaket.toUpperCase()</label>
          </span> </td>
  </tr>
         <tr>
          <td class="style40" scope="row">&nbsp;</td>
          <td scope="row"><span class="style43">No Fail Asal</span></td>
          <td align="left" valign="top" scope="row"><div align="center">:</div></td>
          <td><label><span class="style42">$noFailAsal.toUpperCase() </span> </label></td>
        </tr>
       #else
        <tr>
          <td class="style40" scope="row">&nbsp;</td>
          <td scope="row"><span class="style43">No Fail Asal</span></td>
          <td align="left" valign="top" scope="row"><div align="center">:</div></td>
          <td><label><span class="style42">$noFailAsal.toUpperCase() </span> </label></td>
        </tr>
        #end
  <tr>
    <td width="2%" height="23" scope="row"><span class="style2">*</span></td>
    <td scope="row" width="27%"><div align="right" class="style3">
      <div align="left"><span class="style2"><span class="style7"> Nama Pegawai Memohon </span></span></div>
    </div></td>
     <td width="1%" scope="row"><div align="right" class="style3">:</div></td>
    <td width="70%">
      <label></label>
      $user_Name</td>
  </tr>
  <tr>
    <td scope="row"><span class="style2">* </span></td>
    <td scope="row"><div align="right" class="style3">
      <div align="left"><span class="style2"><span class="style7">Tarikh Subjaket Fail Dibuka</span></span></div>
    </div></td>
     <td scope="row"><div align="right" class="style3">:</div></td>
    <td>
      <label>
        <input name="tarikhSubjaketFail" type="text" id="tarikhSubjaketFail" value="$tarikhSubjaketFail" size="10" $readonly $disabled />
        </label>
        <a href="javascript:displayDatePicker('tarikhSubjaketFail',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>    </td>
  </tr>
  <tr>
    <td scope="row">&nbsp;</td>
    <td scope="row">&nbsp;</td>
    <td scope="row">&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td colspan="4" scope="row"><span class="style5 style47 style69 style45 style4"><span class="style2">Perhatian </span>: Pastikan label berwarna <span class="style2">* </span> wajib diisi.</span></td>
    </tr>
  <tr>
    <td colspan="4" align="center" scope="row">#if($mode == 'baru')
      
      <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="simpan('$idFailAsal')"/>
      <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="kembaliSenaraiFail()"/>
      
#elseif($mode == 'papar')

<input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="kembaliSenaraiFail()"/>
#elseif($mode == 'kemaskini')
<input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="update1()"/>
<input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="kembali()"/>
#elseif($mode == 'update')
<input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="kembali()"/>
#end </td>
  </tr>
</table>
</fieldset>

<p>
  <input name="idSubjaket" type="hidden" value="$idSubjaket" />
  <input name="idFailAsal" id="idFailAsal" type="hidden" value="$idFailAsal" />
  <input name="noFailAsal" type="hidden" value="$noFailAsal" />
  <input name="noFailSubjaket" type="hidden" value="$noFailSubjaket" />
  <input name="action1" type="hidden" value="$action1" />
  <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'  />
</p>
<script>

function update1(){

	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranSubjaketFail&action1=updateSubjaket";
	document.${formName}.submit();
}
function kembali(){

	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranSubjaketFail&action1=tambahSubjaketFail";
	document.${formName}.submit();
}
function kembaliSenaraiFail(){

	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranSubjaketFail&action1=SenaraiFail";
	document.${formName}.submit();
}
function simpan(id){
	
//	if (document.${formName}.socPegawai.value == ""){
//		alert('Sila masukkan " Nama Pegawai " terlebih dahulu.');
//		document.${formName}.socPegawai.focus();
//		return;
//	} 
	if (document.${formName}.tarikhSubjaketFail.value == ""){
		alert('Sila masukkan " Tarikh Subjaket Fail " terlebih dahulu.');
		document.${formName}.tarikhSubjaketFail.focus();
		return;
	}
	if ( !window.confirm("Anda Pasti?") ) return;
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranSubjaketFail&action1=simpanSubjaket&idFailAsal="+id;
	document.${formName}.submit();
//	if (document.${formName}.idSubjaket.value == ""){
//		document.${formName}.mode.value = "tambahBaru";
//	}
//	else{
//		document.${formName}.mode.value = "kemaskiniSubjaket";
//	}
//	document.${formName}.submit();

}
function kemaskini(id){

	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranSubjaketFail&action1=kemaskiniSubjaket";
	document.${formName}.idSubjaket.value = id;
	document.${formName}.submit();
}
function simpandokuemen(){
	
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranSubjaketFail&action1=simpanDokumen";
	document.${formName}.submit();
}

function kemaskiniDokumen(){
	
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranSubjaketFail&action1=kemaskiniDokumen";
	document.${formName}.submit();
}
function updateDokumen(){
	
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranSubjaketFail&action1=updateDokumen";
	document.${formName}.submit();
}

function papar_subjaket(id){
	
	document.${formName}.idSubjaket.value = id;
	document.${formName}.action = "?_portal_module=ekptg.view.pfd.PendaftaranSubjaketFail&action1=paparSubjaket";
	//document.${formName}.mode.value = "";
	document.${formName}.submit();
}

function doCheckAll1(){    
    if (document.${formName}.checkbox_all.checked == true){
        if (document.${formName}.checkbox.length == null){
            document.${formName}.checkbox.checked = true;
        } else {
            for (i = 0; i < document.${formName}.checkbox.length; i++){
                document.${formName}.checkbox[i].checked = true;
            }
        }
    } else {
        if (document.${formName}.checkbox.length == null){
            document.${formName}.checkbox.checked = false;
        } else {
            for (i = 0; i < document.${formName}.checkbox.length; i++){
                document.${formName}.checkbox[i].checked = false;
            }
        }
    }
}

  </script>
