<script>
function toggle_div(id) {
    var e = document.getElementById(id);
    e.style.display = ((e.style.display!='none') ? 'none' : 'block');
}
</script>

<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td colspan="2"><fieldset><legend><strong>MAKLUMAT ADUAN</strong></legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
          <tr>
            <td>&nbsp;</td>
            <td align="right">Nama</td>
            <td >:</td>
            <td><input TABINDEX="1"  class="nama" type=text name="name" size="50" maxlength="50" onblur="this.value=this.value.toUpperCase();" readonly="readonly" value="$!user.name.toUpperCase()"></td>
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
        	value="$!user.email" type="text" name="email" id="email" autocomplete="off" readonly="readonly"/>
        	<span id="validEmail">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span> </td>
        </tr>
        <tr>
          <td width="1%">&nbsp;</td>
          <td align="right">Jenis Aduan<font color="red">*</font></td>
          <td>:</td>
          <td><select name="idJenisAduan" $!readOnly>
				#foreach($type in $types) 
					#set ($optionSelect ="")
					#if ($!type.code.equals("04"))
						#set ($optionSelect = "selected")
					#end
					<option value="$!type.id" $optionSelect>$!type.description</option>
				#end 
			</select></td>
        </tr>
        <tr>
          <td width="1%">&nbsp;</td>
          <td align="right" valign="top">Negeri<!-- <font color="red">*</font> --></td>
          <td valign="top">:</td>
          <td>$selectNegeri</td>
        </tr> 
      	<tr>
          <td width="1%">&nbsp;</td>
          <td align="right" valign="top">Daerah<!-- <font color="red">*</font> --></td>
          <td valign="top">:</td>
          <td>$selectDaerah</td>
        </tr> 
     	<tr>
          <td width="1%">&nbsp;</td>
          <td align="right" valign="top">Mukim<!-- <font color="red">*</font> --></td>
          <td valign="top">:</td>
          <td>$selectMukim</td>
        </tr> 
     	<tr>
          <td width="1%">&nbsp;</td>
          <td align="right" valign="top">Seksyen<!-- <font color="red">*</font> --></td>
          <td valign="top">:</td>
          <td>$selectSeksyen</td>
        </tr>     
      	<tr>
          <td width="1%">&nbsp;</td>
          <td align="right" valign="top">Jenis Hakmilik<!-- <font color="red">*</font> --></td>
          <td valign="top">:</td>
          <td>$selectJenisHakmilik</td>
        </tr> 
             	<tr>
          <td width="1%">&nbsp;</td>
          <td align="right" valign="top">No. hakmilik<!-- <font color="red">*</font> --></td>
          <td valign="top">:</td>
          <td>
          	<input TABINDEX="1" size=50 maxlength=20 
        	value="" type="text" name="nohakmilik" id="nohakmilik" autocomplete="off" />
        	</td>
        </tr> 
        <tr>
          <td width="1%">&nbsp;</td>
          <td align="right" valign="top">Jenis PT/ LOT<!-- <font color="red">*</font> --></td>
          <td valign="top">:</td>
          <td>$selectJenisLot</td>
        </tr> 
     	<tr>
          <td width="1%">&nbsp;</td>
          <td align="right" valign="top">No. PT/ LOT<!-- <font color="red">*</font> --></td>
          <td valign="top">:</td>
          <td><input TABINDEX="1" size=50 maxlength=20 
        	value="" type="text" name="nolot" id="nolot" autocomplete="off" /></td>
        </tr>     
    <!--   	<tr>
          <td width="1%">&nbsp;</td>
          <td align="right" valign="top">Jenis Hakmilik<font color="red">*</font></td>
          <td valign="top">:</td>
          <td>$selectJenisHakmilik</td>
        </tr> -->
            
        <tr>
          <td width="1%">&nbsp;</td>
          <td align="right" valign="top">Butiran Aduan <font color="red">*</font></td>
          <td valign="top">:</td>
          <td><textarea rows="5" cols="50" name="catatan" onblur="this.value=this.value.toUpperCase();"></textarea></td>
        </tr> 
		<tr>
			<td rowspan="3" width="1%">&nbsp;</td>
			<td valign="middle" rowspan="3" align="right" valign="top">Lampiran</td>
			<td valign="middle" rowspan="3" valign="top">:</td>
			<td>
				<table border="0">
					<tr>
						<td><img src="../img/attachment-icon.png" alt="" border="0"/></td>
						<td><a href="#" onclick="toggle_div('toggleDiv');"><font color="blue"><i>Muatnaik Dokumen</i></font></a></td>
					</tr>
				</table>
			</td>
		</tr> 
		<tr>
			<td colspan="2">
				<div id="toggleDiv" style="display:none;">
					<input name="lampiran1" type="file" id="txtLampiran" size="50" />
				</div>
			</td>
		</tr>
      </table>
      </fieldset></td>
  </tr>
  <tr>
    <td colspan="2" valign="bottom">&nbsp;</td>
  </tr>
  <tr>
    <td width="30%">&nbsp;</td>
    <td width="70%">
    				<input type="button" name="cmdHantar" id="cmdHantar" value="Hantar" onclick="javascript:daftar()" />
    			    <input type="reset" value="Kosongkan" />
    			    <input type="reset" value="Kembali ke Menu Utama" onclick="javascript:menuUtama()"/>
    			     </td>
  </tr>
</table>
#set ($portal_role = "online")

<script type="text/javascript">
	function doChangeNegeri() {
		doAjaxCall${formName}("");
	}

	function doChangeDaerahTanah() {
	  	doAjaxCall${formName}("");
	}
	function doChangeMukimTanah() {
	  	doAjaxCall${formName}("");
	}
	
	function doChangeJenisHakmilik() {
		doAjaxCall${formName}("");
	}

	function doChangeJenisLot() {
	  	doAjaxCall${formName}("");
	}
  
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
			document.${formName}.enctype= "multipart/form-data";
			document.${formName}.encoding = "multipart/form-data";
			document.${formName}.action='?_portal_module=ekptg.view.online.aduan.ComplaintSenderModule&command=doAduan';
			document.${formName}.submit();
			//doAjaxCall${formName}("doAduan");
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
						"background-image": "url('../portal/validyes.png')"
					});
				} else {
					$jquery("#validEmail").css({
						"background-image": "url('../portal/validno.png')"
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
	document.${formName}.action = "$EkptgUtil.getTabID("Menu",$myrole)?_portal_module=ekptg.view.online.FrmOnlineMenuUtama";
	document.${formName}.submit();
}
</script>
