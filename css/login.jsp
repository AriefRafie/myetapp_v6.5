<!-- Edited by Hazwan -->
<html>
<head>
<title>My eTaPP</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<meta http-equiv=="Pragma" Content="cache">
<link rel="shortcut icon" href="favicon.ico" />

<script type="text/javascript" src="library/js/ekptgTools.js" ></script>

<style type="text/css">

body {
	margin:0px 0px 0px 0px;
	padding:0px 0px 0px 0px;
	text-align:center;
	background-color: #666;
}
	
#logincontent {
	width:100%;
	position: absolute;
	top: 0;
	right: 0;
	bottom: 0;
	left: 0;
	margin-right: auto;
	margin-left: auto;
	
}

#loginfooter {
	font-size:10px;
	font-weight: bold;
	font-family: Verdana, sans-serif;
}

input{
	border:1px solid #93C2AE;
	padding: 0px;
}

input.blueinput:focus, textarea.blueinput:focus {
	border:1px solid #93C2AE;
	/*background-color: #FAF8CC;*/
}
.label-top label {
	display:block;
	}
form label {
	font-size:0.8em;
	font-family: Verdana, sans-serif;
	/*margin-bottom:0.4em;*/
	}
#button {
	font-size:0.8em;
	cursor:pointer;
	/*float:left;*/
	padding: 0px;
	width:77px;
	height:27px;
	/*-moz-border-radius:.3em;
	-webkit-border-radius:.3em;
	border-radius:.3em;*/
	-moz-box-shadow:0 2px 4px #aaaaaa;
	-webkit-box-shadow:0 2px 4px #aaaaaa;
	box-shadow:0 2px 4px #aaaaaa;
	margin:0;
	border:0px;
	color:#000000;
	background:url('img/main/masuk.png');
	}
.email-support {
	width:137px;
	height:13px;
	cursor:pointer;
s	/*position:absolute;*/
}
</style>

<script type="text/javascript">
var _gaq = _gaq || [];
_gaq.push(['_setAccount', 'UA-21977563-1']);
_gaq.push(['_trackPageview']);

(function() {
  var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
  ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
  var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
})();


</script>
</head>
<!--
<body leftmargin="0" topmargin="5" rightmargin="0" bottommargin="0" onLoad="document.flogin.username.focus();" marginwidth="0">
-->
<body onLoad="document.flogin.username.focus();"> 
<div id="logincontent">
<table id="Table_01" width="1024" border="0" cellpadding="0" cellspacing="0" align="center">
	<tr>
    	<td colspan="2" background="img/main/top-banner.png" height="35"></td>
	</tr>
	<tr>
		<td colspan="2" background="img/main/banner-JKPTG.png" height="126"></td>
	</tr>
	<tr>
	  <td background="img/main/middle-banner-left.png" height="313" width="712"></td>
      <td background="img/main/middle-banner-right.png" height="313" width="312">
      <form name="flogin" method="post" action="javascript:submitLogin()">
      <table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
          <tr>
            <td colspan="4">&nbsp;</td>
          </tr>
          <tr>
            <td colspan="4">&nbsp;</td>
          </tr>
          <tr>
            <td colspan="4">&nbsp;</td>
          </tr>
          <tr>
            <td colspan="4" height="30">&nbsp;</td>
          </tr>
          <tr>
            <td colspan="4">&nbsp;</td>
          </tr>
          <tr>
          <td width="30">&nbsp;</td>
            <td colspan="2"><label for="username">ID PENGGUNA</label></td>
          <td width="30">&nbsp;</td>
          </tr>
          <tr>
          <td width="30">&nbsp;</td>
            <td colspan="3"><input name="username" type="text" class="loginbutton" tabindex="1" autocomplete="off" onKeyPress="return OnKeyPressGotoNextTabIndex(this, event)" size="35" maxlength="25" bordercolor="ffffff"></td>
          </tr>
          <tr>
          <td width="30">&nbsp;</td>
            <td colspan="2"><label for="password">KATA LALUAN</label></td>
          <td width="30">&nbsp;</td>
          </tr>
          <tr>
          <td width="30">&nbsp;</td>
            <td colspan="3"><input name="password" type="password" class="loginbutton" tabindex="2" onKeyPress="return submitenter(this,event)" size="35" maxlength="25"></td>
          </tr>
          <tr>
          	<td colspan="4">&nbsp;</td>
          </tr>
          <!-- <tr>
          <td width="30">&nbsp;</td>
            <td width="20"><input type="checkbox" name="radio" id="radio" value="radio"></td>
            <td width="232"><label for="checkbox"><span style="font-size:0.8em">Kekal login sistem</span></label></td>
          <td width="30">&nbsp;</td>
          </tr> -->		  
          <tr>
          <td colspan="2">&nbsp;</td>
            <td align="right" height="40"><input id="button" type="submit" value="" onKeyPress="javascript:submitLogin();" onClick="javascript:submitLogin();" tabindex="3"/></td>
          <td width="30">&nbsp;</td>
          </tr>
          <tr>
            <td colspan="4">&nbsp;</td>
          </tr>
          <tr>
            <td colspan="2">&nbsp;</td>
            <td colspan="2">
            <table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
            <tr>
            <td width="113"></td>
            <td width="137"><a href="mailto:etappsupport@kptg.gov.my"><div class="email-support"></div></a></td>
            <td width="12"></td></tr></table></td>
          </tr>
    </table>
    <input type="hidden" name="loginType" value="internal">
    </form>
    </td>
	</tr>
	<tr>
		<td colspan="2" background="img/main/banner-MyEtapp.png" height="214"></td>
	</tr>
	<tr>
		<td colspan="2" background="img/main/bottom-banner.png" height="38"></td>
	</tr>
</table>
</div>
<script>
function submitLogin() {
    if ( document.flogin.username.value == "" ) { alert("Sila masukkan ID Pengguna!"); document.flogin.username.focus(); return; }
    if ( document.flogin.password.value == "" ) { alert("Sila masukkan katalaluan!"); document.flogin.password.focus(); return; }
    
    document.flogin.action = "c/login";
    document.flogin.submit();
}

function submitenter(myfield,e)
{
var keycode;
if (window.event) keycode = window.event.keyCode;
else if (e) keycode = e.which;
else return true;

if (keycode == 13)
{
	document.flogin.submit();
	return false;
}
else
   return true;
}


function OnKeyPressGotoNextTabIndex(field, event)
{
if (event.keyCode == 13) {
for (i = 0; i < field.form.elements.length; i++)
if (field.form.elements[i].tabIndex == field.tabIndex+1) {
field.form.elements[i].focus();
if (field.form.elements[i].type == "text")
field.form.elements[i].select();
break;
}
return false;
}
return true;
}

</script>
</body>
</html>