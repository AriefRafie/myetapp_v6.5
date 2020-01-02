<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>eTapp Online Portal v1.0</title>
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
  <br>
      <fieldset>
        <legend>Log Masuk</legend>
      <form name="portallogin">
      <input type="hidden" name="command" value="">
      <table align="center">

        <div> <span>
        <tr>
        <td><strong>Emel Pengguna</strong> :        </tr>  </span> <span>
        <tr>
          <td>
         <input autocomplete="off" size=25 type="text" id="username" name="username" />         </td>  </span> </div>
        </tr>
        <tr>
        <td><strong>Katalaluan</strong> :        </tr>  </span> <span>
        <tr>
          <td><input onKeyPress="return submitenter(this,event)" type="password" name="password" size="25" />          </td>  </span> </div>
        </tr>
        <tr>
          <td>
          <span id="ajaxindicator" style="display: none">
	 <img src="../img/indicator.gif" alt="Working..." />	 </span>
          <div id="result"></div>
          <input name="button" value="Hantar" onClick="doLogin();"/></td>
         <!--<img onClick="doLogin();" src="../portal/images/hantar_forget_passwordcopy.png" />-->
        </tr>
        <tr>
        <td>        </td>
        </tr>
        </table>
      </form>
       <center>
         <p><a class="style_link" href="../portal/daftarpengguna.jsp"
            title="Daftar akaun baru"
            onclick="Modalbox.show(this.href, {title: this.title, width: 700, evalScripts: true, afterLoad: function() { pwdStrength();} }); return false;">
            <font class=style101>Daftar akaun baru</font></a>&nbsp;&nbsp;&nbsp;
           
              <a href="../portal/lupakatalaluan.jsp" title="Lupa Katalaluan"
      onclick="Modalbox.show(this.href, {title: this.title, width: 700, evalScripts: true, afterLoad: function() { checkValidEmail();} }); return false;">
                 <font class=style101>Lupa Katalaluan?</font></a></p>

      </center>

      <p>&nbsp;</p>
	  <p>&nbsp;</p>
      <p>&nbsp;</p>
      
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