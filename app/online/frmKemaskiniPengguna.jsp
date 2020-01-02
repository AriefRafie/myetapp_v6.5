<style type="text/css">
<!--
.style1 {color: #FF0000}
-->
</style>
<style type="text/css">
<!--
.style1 {color: #FF0000}
.style73 {	color: #0000FF;
	font-style: italic;
	font-size: 9px;
}
.style74 {color: #0000FF}
-->
</style>
#set ($code = $session.getAttribute("verification.code"))
#if($!errorMessage != "")
<div class="error">$!errorMessage</div>
#end
#if($!success != "")
<div class="info">$!success .Sila log masuk <a href=1191921140711>disini</a>.</div>
#else

<input name="action" type="hidden" value="$action" />
<table width="100%" border="0" cellpadding="2">

<!-- -->

  <tr>
    <td colspan="3"><fieldset>
    <legend><strong>::ID Pengguna & Kata Laluan::</strong></legend>
    <table width="100%" border="0" cellpadding="2">
      <tr>
        <td width="29%"><span class="style1">Id Pengguna</span></td>
        <td width="1%">:</td>
        <td width="70%"><label>
          <input value="$!txtIdPengguna" type="text" name="txtIdPengguna" id="txtIdPengguna" autocomplete="off"/>
        </label></td>
      </tr>
      <tr>
        <td><span class="style1">Kata Laluan</span></td>
        <td>:</td>
        <td><label>
          <input type="password" name="txtKataLaluan" id="txtKataLaluan" />
          <span class="style73">minimum 6 huruf</span></label></td>
      </tr>
      <tr>
        <td><span class="style1">Sahkan Kata Laluan</span></td>
        <td>:</td>
        <td><label>
          <input type="password" name="txtSahkanKataLaluan" id="txtSahkanKataLaluan" />
        </label></td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td><img src="../servlet/ekptg.view.online.VerificationServlet" border="0"/></td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td><label>
          <input type="text" name="txtCaptChar" id="txtCaptChar"/> 
          <span class="style74"><font size=1>* Sila masukkan aksara yg tertera diatas.</font></span>
        </label></td>
      </tr>
    </table>
    </fieldset>    </td>
  </tr>


  <tr>
    <td colspan="3"><fieldset>
    <legend><strong>::Maklumat Peribadi::</strong></legend>
    <table width="100%" border="0" cellpadding="2">
      <tr>
        <td width="29%"><span class="style1">Nama</span></td>
        <td width="1%">:</td>
        <td width="70%"><label>
          <input value="$!txtNama" type="text" name="txtNama" id="txtNama" />
        </label></td>
      </tr>
      <tr>
        <td><span class="style1">No Kad Pengenalan</span></td>
        <td>:</td>
        <td>
          <input name="txtNoKPBaru1" id="txtNoKPBaru1" style="width: 50px;" value="$!txtNoKPBaru1" size="7" maxlength="6" 
          onkeyup="javascript:validateIC(event,this,this.value,'txtNoKPBaru2')" 
          onblur="javascript:getDOBByIC(this,this.value,'txdTarikhLahir');
          getAgeByIC(this,this.value,'txtUmur')"; type="text">
	            -
	  <input name="txtNoKPBaru2" id="txtNoKPBaru2" style="width: 20px;" value="$!txtNoKPBaru2" size="3" maxlength="2" onkeyup="javascript:validateIC(event,this,this.value,'txtNoKPBaru3')" type="text">
	            -
	  <input name="txtNoKPBaru3" id="txtNoKPBaru3" style="width: 40px;" value="$!txtNoKPBaru3" size="5" maxlength="4" 
	  onkeyup="javascript:validateIC(event,this,this.value,'txtNoKPBaru3')" 
	  onblur="javascript:getGenderByIC(this,this.value,'socJantina')"
	  type="text">
             
        
        </td>
      </tr>
      <tr>
        <td>Tarikh Lahir</td>
        <td>:</td>
        <td><label>
          <input name="txdTarikhLahir" type="text" id="txdTarikhLahir" value="$!txdTarikhLahir" />
        </label><a href="javascript:displayDatePicker('txdTarikhLahir',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a></td>
      </tr>
      <tr>
        <td>Umur</td>
        <td>:</td>
        <td><label>
          <input maxlength=2 name="txtUmur" type="text" id="txtUmur" value="$!txtUmur" size="5" />
        tahun</label></td>
      </tr>
      <tr>
        <td>Jantina</td>
        <td>:</td>
        <td><label>
          <select name="socJantina" id="socJantina">
          #if ($jantina == '')
            <option value="" selected="selected">Sila Pilih Jantina</option>
          #elseif ($jantina == '1')
            <option value="1" selected="selected">Lelaki</option>
          #elseif ($jantina == '2')
            <option value="2" selected="selected">Perempuan</option>
          #else
           <option value="">Sila Pilih Jantina</option>
           <option value="1">Lelaki</option>
           <option value="2">Perempuan</option>
          #end
          </select>
        </label></td>
      </tr>
      <tr>
        <td>Taraf Perkahwinan</td>
        <td>:</td>
        <td><label>
          <select name="socTarafPerkahwinan" id="socTarafPerkahwinan">
          #if ($tarafPerkahwinan == '')
            <option value="" selected="selected">Sila Pilih Taraf Perkahwinan</option>
          #elseif ($tarafPerkahwinan == 'B')
            <option value="B" selected="selected">Bujang</option>
          #elseif ($tarafPerkahwinan == 'K')
            <option value="K" selected="selected">Berkahwin</option>
          #elseif ($tarafPerkahwinan == 'D')
            <option value="D" selected="selected">Duda</option>
          #elseif ($tarafPerkahwinan == 'J')
            <option value="J" selected="selected">Janda</option>
          #else
            <option value="">Sila Pilih Taraf Perkahwinan</option>
            <option value="B">Bujang</option>
            <option value="K">Berkahwin</option>
            <option value="D">Duda</option>
            <option value="J">Janda</option>
          #end
          </select>
        </label></td>
      </tr>
      <tr>
        <td valign="top"><span class="style1">Alamat</span></td>
        <td valign="top">:</td>
        <td><input value="$!txtAlamat1" type=text name=txtAlamat1 class="uppercase" maxlength="80" size="44"></td>
      </tr>
       <tr>
        <td valign="top"></td>
        <td valign="top">:</td>
        <td><input value="$!txtAlamat2" type=text name=txtAlamat2 class="uppercase" maxlength="80" size="44"></td>
      </tr>
       <tr>
             <td valign="top"></td>
             <td valign="top">:</td>
             <td><input value="$!txtAlamat3" type=text name=txtAlamat3 class="uppercase" maxlength="80" size="44"></td>
      </tr>
      <tr>
        <td><span class="style1">Poskod</span></td>
        <td>:</td>
        <td><label>
          <input name="txtPoskod" type="text" id="txtPoskod" value="$!txtPoskod" size="5" maxlength="5" onkeyup="javascript:validatePoskod(this,this.value)" />
        </label></td>
      </tr>
      <tr>
        <td><span class="style1">Negeri</span></td>
        <td>:</td>
        <td>$selectNegeri</td>
      </tr>
      <tr>
        <td><span class="">No Telefon (Rumah)</span></td>
        <td>:</td>
        <td><label>
          <input name="txtNoTelRumah" type="text" id="txtNoTelRumah" value="$!txtNoTelRumah" size = "14" maxlength="14" onkeyup="javascript:validateIC(event,this,this.value,'txtNoTelRumah')" />
        </label></td>
      </tr>
      <tr>
        <td><span class="">No Telefon (Bimbit)</span></td>
        <td>:</td>
        <td><label>
          <input name="txtNoTelBimbit" type="text" id="txtNoTelBimbit" value="$!txtNoTelBimbit" size="14" maxlength="14" onkeyup="javascript:validateIC(event,this,this.value,'txtNoTelBimbit')" />
        </label></td>
      </tr>
      <tr>
        <td>No Faks</td>
        <td>:</td>
        <td><label>
          <input name="txtNoFaks" type="text" id="txtNoFaks" value="$!txtNoFaks" size="12" maxlength="12" onkeyup="javascript:validateIC(event,this,this.value,'txtNoFaks')"/>
        </label></td>
      </tr>
      <tr>
        <td><span class="">Emel</span></td>
        <td>:</td>
        <td><label>
          <input name="txtEmel" type="text" id="txtEmel" value="$!txtEmel" size="34" maxlength="50" />
        </label></td>
      </tr>
    </table>
    </fieldset>    </td>
  </tr>

  <tr>
    <td colspan="3">&nbsp;</td>
  </tr>
  <tr>
    <td width="29%">&nbsp;</td>
    <td width="1%">&nbsp;</td>
    <td width="70%"><label>
    <input type="button" name="cmdHantar" id="cmdHantar" value="Hantar" onclick="hantar()" />
    <input type="button" name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" onclick="kosongkan()" />
    </label></td>
  </tr>
</table>
<input type=hidden name="txtNoKPBaru">
#end
<script>
function hantar(){
	
	var emailExp = /^[\w\-\.\+]+\@[a-zA-Z0-9\.\-]+\.[a-zA-z0-9]{2,4}$/;
	var em = document.${formName}.txtEmel.value;	
	
	if ( !window.confirm("Anda Pasti?") ) return;
	
	if (document.${formName}.txtIdPengguna.value == ""){
		alert('Sila masukkan " Id Pengguna " terlebih dahulu.');
		document.${formName}.txtIdPengguna.focus();
		return;
	} 
	if (document.${formName}.txtKataLaluan.value == ""){
		alert('Sila masukkan " Kata Laluan " terlebih dahulu.');
		document.${formName}.txtKataLaluan.focus();
		return;
	}
	if (document.${formName}.txtSahkanKataLaluan.value == ""){
		alert('Sila masukkan " Sahkan Kata Laluan " terlebih dahulu.');
		document.${formName}.txtSahkanKataLaluan.focus();
		return;
	} 
	if (document.${formName}.txtNama.value == ""){
		alert('Sila masukkan " Nama " terlebih dahulu.');
		document.${formName}.txtNama.focus();
		return;
	} 
	
	document.${formName}.txtNoKPBaru.value = document.${formName}.txtNoKPBaru1.value + document.${formName}.txtNoKPBaru2.value + document.${formName}.txtNoKPBaru3.value;

	if (document.${formName}.txtNoKPBaru.value == ""){
		alert('Sila masukkan " No KP " terlebih dahulu.');
		document.${formName}.txtNoKPBaru.focus();
		return;
	} 
	if (document.${formName}.txtAlamat1.value == ""){
		alert('Sila masukkan " Alamat " terlebih dahulu.');
		document.${formName}.txtAlamat1.focus();
		return;
	}
	if (document.${formName}.txtPoskod.value == ""){
		alert('Sila masukkan " Poskod " terlebih dahulu.');
		document.${formName}.txtPoskod.focus();
		return;
	} 
	if (document.${formName}.socNegeri.value == ""){
		alert('Sila pilih " Negeri " terlebih dahulu.');
		document.${formName}.socNegeri.focus();
		return;
	}
	if(!em.match(emailExp) && em!=""){
		alert("Alamat email tidak sah!");		
		document.${formName}.txtEmel.focus();
		return;
	}
	
	/*
	if (document.${formName}.txtNoTelRumah.value == ""){
		alert('Sila masukkan " No Tel Rumah " terlebih dahulu.');
		document.${formName}.txtNoTelRumah.focus();
		return;
	}
	if (document.${formName}.txtNoTelBimbit.value == ""){
		alert('Sila masukkan " No Tel Bimbit " terlebih dahulu.');
		document.${formName}.txtNoTelBimbit.focus();
		return;
	} 
	if (document.${formName}.txtEmel.value == ""){
		alert('Sila pilih " Emel " terlebih dahulu.');
		document.${formName}.txtEmel.focus();
		return;
	}
	*/
	document.${formName}.action.value = "simpan";
	document.${formName}.submit();

}

function emailValidator(elem, helperMsg){
	var emailExp = /^[\w\-\.\+]+\@[a-zA-Z0-9\.\-]+\.[a-zA-z0-9]{2,4}$/;
	
	if(elem.value!=""){
	if(elem.value.match(emailExp)){
		return true;
	}else{
		alert(helperMsg);		
		elem.focus();
		return false;
	}
	}
}

function kosongkan(){

	document.${formName}.reset();
	document.${formName}.txtIdPengguna.value == "";
	document.${formName}.txtKataLaluan.value == "";
	document.${formName}.txtSahkanKataLaluan.value == "";
	document.${formName}.txtCaptChar.value == "";
	document.${formName}.txtNama.value == "";
	document.${formName}.txtNoKP.value == "";
	document.${formName}.txtAlamat.value == "";
	document.${formName}.txtPoskod.value == "";
	document.${formName}.socNegeri.value == "";
	document.${formName}.txtNoTelRumah.value == "";
	document.${formName}.txtNoTelBimbit.value == "";
	document.${formName}.txtEmel.value == "";
}
</script>