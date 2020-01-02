<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>eTapp Online Portal</title>
<link href="../portal/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../library/scriptaculous/prototype.js" ></script>
<script type="text/javascript" src="../library/scriptaculous/scriptaculous.js" ></script>
<script type="text/javascript" src="../library/scriptaculous/modalbox.js" ></script>
<link rel="stylesheet" type="text/css" href="../library/scriptaculous/modalbox.css" />
<!-- auto complete part-->
<script type="text/javascript" src="../library/scriptaculous/effects.js" ></script>
<script type="text/javascript" src="../library/scriptaculous/controls.js" ></script>

<script type="text/javascript" src="../library/scriptaculous/ajax.js" ></script>
<!-- Jquery -->
<script type="text/javascript" src="../library/js/jquery-1.3.2.min.js"></script>
<script>var $jquery = jQuery.noConflict();</script>
<script type="text/javascript" src="../library/js/jquery.pstrength-min.1.2.js"></script>
<script type="text/javascript" src="../library/js/jquery.captchaRefresh.js"></script>
<script type="text/javascript" src="../library/js/ekptgTools.js" ></script>

<META HTTP-EQUIV="Pragma" CONTENT="no-cache"> 
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
</head>

<link rel="shortcut icon" href="../favicon.ico" />
<body>
<fieldset style="width: 400px;">
        <legend>eTapp Online</legend>
      <form name="portallogin">
      <input type="hidden" value="" name="command"/>
        <table>
         <span style="display: none;" id="ajaxindicator">
	 <img alt="Working..." src="../img/indicator.gif"/>
	 </span>
         <div id="result"> </div><td width="179"></br>
        <tbody><tr>
          <td>Emel Pengguna :
        </td></tr>   <tr>
          <td>
         <input type="text" name="username" id="username" size="25" autocomplete="off"/>

         </td>   
        </tr>
        <tr>
          <td>Katalaluan :
        </td></tr>   <tr>
          <td><input type="password" size="25" name="password" onkeypress="return submitenter(this,event)"/>
          </td>   
        </tr>
        <tr>
          <td>

         <img src="../portal/images/hantar_forget_passwordcopy.png" onclick="doLogin();"/></td>

        </tr>
        <tr>
        <td>


        </td>
        </tr>

        </tbody></table>
      </form>
      
      <a href="../portal/daftarpengguna.jsp">Daftar</a> |
      <a href="../portal/lupakatalaluan.jsp">Lupa Katalaluan</a> 
      <!--
      |
       <a onclick="Modalbox.show(this.href, {title: this.title, width: 700, evalScripts: true, afterLoad: function() { pwdStrength();} }); return false;" title="Daftar akaun baru" href="../portal/daftarpengguna.jsp">
      Daftar akaun baru</a> | 
      <a onclick="Modalbox.show(this.href, {title: this.title, width: 700, evalScripts: true, afterLoad: function() { checkValidEmail();} }); return false;" title="Lupa Katalaluan" href="../portal/lupakatalaluan.jsp">
      Lupa Katalaluan?</a> 
      -->
</fieldset>

<script type="text/javascript">

function doLogin() {
	url = "../servlet/ekptg.view.online.Portal";
	target = "";
	actionName = "doLogin";
	target = "result";
	doAjaxUpdater(document.portallogin, url, target, actionName);
}

function doEffect() {
	new Effect.Highlight('result', {startcolor:'#CEB089',endcolor:'#FFFFFF', restorecolor:'#FFFFFF'});
}

function submitenter(myfield,e)
{
var keycode;
if (window.event) keycode = window.event.keyCode;
else if (e) keycode = e.which;
if (keycode == 13)
{
	doLogin();
	return false;
}
else
   return true;
}

</script>
