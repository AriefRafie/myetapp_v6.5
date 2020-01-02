
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td colspan="2"><fieldset><legend><strong>MAKLUMAT ADUAN</strong></legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
          <tr>
            <td>&nbsp;</td>
            <td align="right">Nama </td>
            <td >:</td>
            <td><input TABINDEX="1"  class="nama" type=text name="name" size="50" maxlength="50" onblur="this.value=this.value.toUpperCase();" value="$!user.name.toUpperCase()"></td>
          </tr>
        <tr>
          <td width="1%">&nbsp;</td>
          <td width="28%" align="right">No. Telefon<font color="red">*</font></td>
          <td width="1%" >:</td>
          <td width="70%"><input TABINDEX="1"  class="nama" type=text name="phone" size="14" maxlength="14"></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td align="right">Emel</td>
          <td>:</td>
          <td><input TABINDEX="1" size=50 maxlength=100 onClick="checkValidEmail()"
        	value="$!user.email" type="text" name="email" id="email" autocomplete="off"/>
        	<span id="validEmail">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span> </td>
        </tr>
        <tr>
          <td width="1%">&nbsp;</td>
          <td align="right">Jenis Aduan<font color="red">*</font></td>
          <td>:</td>
          <td><select name="idJenisAduan">
				#foreach($type in $types) 
					<option value="$!type.id">$!type.code - $!type.description</option>
				#end 
			</select></td>
        </tr>
        <tr>
          <td width="1%">&nbsp;</td>
          <td align="right" valign="top">Butiran Aduan <font color="red">*</font></td>
          <td valign="top">:</td>
          <td><textarea rows="5" cols="50" name="catatan" onblur="this.value=this.value.toUpperCase();"></textarea></td>
        </tr> 
                
      </table>
      </fieldset></td>
  </tr>
  <tr>
    <td colspan="2" valign="bottom">&nbsp;</td>
  </tr>
  <tr>
    <td width="30%">&nbsp;</td>
    <td width="50%">
    				<input type="button" name="cmdHantar" id="cmdHantar" value="Hantar" onclick="javascript:daftar()" />
    			    <input type="reset" value="Kosongkan" />
    			    <!-- <input type="reset" value="Kembali ke Menu Utama" onclick="javascript:menuUtama()"/> -->
    			     </td>
  </tr>
</table>
#set ($portal_role = "online")

<script type="text/javascript">
	daftar =function(){
		if(document.${formName}.idJenisAduan.value==""){
			alert('Sila Pilih Jenis Aduan');
			return;
		}
		else if(document.${formName}.catatan.value==""){
			alert('Sila Isi Butiran Aduan');
			return;
		}
		else if(document.${formName}.phone.value==""){
			alert('Sila Masukkan No. Telefon');
			return;
		}
		else{
			doAjaxCall${formName}("doAduan");
		}
		
	}

	checkValidEmail = function() {
		//Check Valid Email
		$jquery("#email").keyup(function(){
			var email = $jquery("#email").val();
			if(email != 0)
			{
				if(isValidEmailAddress(email))
				{
					$jquery("#validEmail").css({
						"background-image": "url('validyes.png')"
					});
				} else {
					$jquery("#validEmail").css({
						"background-image": "url('validno.png')"
					});
				}
			} else {
				$jquery("#validEmail").css({
					"background-image": "none"
				});			
			}		
			
		});
	}

	isValidEmailAddress = function (emailAddress) {
		var pattern = new RegExp(/^(("[\w-\s]+")|([\w-]+(?:\.[\w-]+)*)|("[\w-\s]+")([\w-]+(?:\.[\w-]+)*))(@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$)|(@\[?((25[0-5]\.|2[0-4][0-9]\.|1[0-9]{2}\.|[0-9]{1,2}\.))((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\.){2}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\]?$)/i);
		return pattern.test(emailAddress);
	}

	doEffect = function () {
		new Effect.Highlight('doRegisterResult', {startcolor:'#CEB089',endcolor:'#FFFFFF', restorecolor:'#EFEFEF'});
	}

	doHide = function () {
		$jquery('#RegistrationForm').hide("slow");
	}

	
	
function menuUtama(){
	document.${formName}.action = "$EkptgUtil.getTabID('Menu',$portal_role)?_portal_module=ekptg.view.online.FrmOnlineMenuUtama";
	document.${formName}.submit();
}
</script>
