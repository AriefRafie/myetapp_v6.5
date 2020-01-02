<!DOCTYPE html>
<html>
<head>
<title>My Aduan</title>
<script>var $jquery = jQuery.noConflict();</script>
<!--
<script type="text/javascript" src="../library/js/jquery.dragndrop.js"></script>
<script type="text/javascript" src="../library/js/fn.js"></script>

<link type="text/css" rel="stylesheet" href="../library/css/style.css" />
 
Tak boleh pakai klu pki yg drag n drop nie
<script type="text/javascript" src="../library/scriptaculous/prototype.js" ></script> 
-->

<link rel="stylesheet" type="text/css" href="../css/online.css" />

<link rel="shortcut icon" href="../favicon.ico" />

<script>
function toggle_div(id) {
    var e = document.getElementById(id);
    e.style.display = ((e.style.display!='none') ? 'none' : 'block');
}
</script>
</head>

<body onmousemove="reset_interval()" onclick="reset_interval()" onkeypress="reset_interval()" onscroll="reset_interval()">

<div class="apps-container">
<div class="apps-body">
<table class="container" cellpadding="0" cellspacing="0" border="0">
<tr><td>
<div class="welcome">
<b>Online Aduan myeTaPP</b>
</div>

<div class="page_header_1">
<div class="page_header_2">
<div class="page_header_3">
<div class="page_header_4">
<div class="page_header_5">
<div class="page_header_6">
<div class="page_header_7">
<div class="page_header_8">
<div class="page_header_9">
</div>
</div>
</div>
</div>
</div>
</div>
</div>
</div>
</div>

</td></tr>
<tr><td>
<table class="tab" width="100%"  cellspacing="0" cellpadding="0" border="0">
	<tr>
		<td valign="bottom">&nbsp;
		</td>
	</tr>
</table>
</td></tr>
<tr><td>
<form id="f" name="f" method="post" autocomplete="on"> 

<table width="95%" border="0" cellspacing="2" cellpadding="2" align="center">
	<tr>
		<td colspan="2">
			<fieldset><legend><strong>MAKLUMAT ADUAN</strong></legend>
			<table width="100%" border="0" cellspacing="2" cellpadding="2">
				<tr>
					<td align="right">Nama<font color="red">*</font></td>
					<td>:</td>
					<td colspan="2"><input TABINDEX="1" class="nama" autofocues="autofocus" type="text" required="required" name="name" size="50" maxlength="50" onblur="this.value=this.value.toUpperCase();" value="" placeholder="Contoh: MOHD ALI BIN ABU BAKAR"></td>
				</tr>
				<tr>
					<td width="28%" align="right">No. Telefon</td>
					<td width="1%" >:</td>
					<td colspan="2" width="70%"><input TABINDEX="2" class="nama" type="text" name="phone" placeholder="Contoh: 0123456789"></td>
				</tr>
				<tr>
					<td align="right">Emel<font color="red">*</font></td>
					<td>:</td>
					<td colspan="2"><!-- <input TABINDEX="1" size=50 maxlength=100 onClick="checkValidEmail()" value="" type="text" name="email" id="email" autocomplete="off"/> 
					<span id="validEmail">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span> -->
					<input TABINDEX="3" size=50 maxlength=100 value="" type="email" name="email" required="required" autocomplete="off" placeholder="Contoh: aduan@jkptg.gov.my"/>
					 </td>
				</tr>
				<tr>
					<td align="right">Jenis Aduan<font color="red">*</font></td>
					<td>:</td>
					<td colspan="2"><select name="idJenisAduan">
						<option value="16101">ADUAN</option>
						<option value="16102">CADANGAN</option>
						<option value="161021">PERTANYAAN</option>
						<option value="16103">LAIN-LAIN</option> 
					</select></td>
				</tr>
				<tr>
					<td align="right" valign="top">Butiran Aduan<font color="red">*</font></td>
					<td valign="top">:</td>
					<td colspan="2"><textarea rows="5" cols="50" name="catatan" onblur="this.value=this.value.toUpperCase();"></textarea></td>
				</tr> 
				<tr>
					<td valign="middle" rowspan="3" align="right" valign="top">Lampiran</td>
					<td valign="middle" rowspan="3" valign="top">:</td>
					<td valign="middle" width="1%"><img src="../img/attachment-icon.png" alt="" border="0"/></td>
					<td><a href="#" onclick="toggle_div('toggleDiv');"><font color="blue"><i>Muatnaik Dokumen</i></font></a>
					</td>
				</tr> 
				<tr>
					<td colspan="4">
						<div id="toggleDiv" style="display:none;">
							<input name="lampiran1" type="file" id="txtLampiran" size="50" />
						</div>
					</td>
				</tr>
			</table>
			</fieldset>
		</td>
	</tr>
	<tr>
		<td colspan="2" valign="bottom">&nbsp;</td>
	</tr>
	<tr>
		<td width="30%">&nbsp;</td>
		<td width="50%">
			<input type="button" name="cmdHantar" id="cmdHantar" value="Hantar" onclick="javascript:daftar()" />
			<input type="reset" value="Kosongkan" />
		</td>
	</tr>
</table>

</form>
</td></tr>
<tr>
	<td>&nbsp;</td>
</tr>
<tr>
	<td>&nbsp;</td>
</tr>
</table>

<div class="page_footer_body">
<div class="page_footer">
<div class="page_footer_1">
</div>
</div>
</div>

</div>
</div>

<script type="text/javascript">
	daftar = function(){
		if(document.f.idJenisAduan.value==""){
			alert('Sila Pilih Jenis Aduan');
			return;
		}
		else if(document.f.catatan.value==""){
			alert('Sila Isi Butiran Aduan');
			return;
		}
		else{
			document.f.enctype= "multipart/form-data";
			document.f.encoding = "multipart/form-data";
			document.f.action = "PortalAduanSave.jsp";
			document.f.submit();
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
	
</script>
</body>

</html>