<head>
<title></title>
<!-- <link href="./portal/style.css" rel="stylesheet" type="text/css" /> -->
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<meta http-equiv="Pragma" Content="cache">
<link rel="shortcut icon" href="favicon.ico" />
<script type="text/javascript" src="./library/scriptaculous/prototype.js" ></script>
<script type="text/javascript" src="./library/scriptaculous/scriptaculous.js" ></script>
<script type="text/javascript" src="./library/scriptaculous/modalbox.js" ></script>
<link rel="stylesheet" type="text/css" href="./library/scriptaculous/modalbox.css" />
<!-- auto complete part-->
<script type="text/javascript" src="./library/scriptaculous/effects.js" ></script>
<script type="text/javascript" src="./library/scriptaculous/controls.js" ></script>
<script type="text/javascript" src="./library/scriptaculous/ajax.js" ></script>
<!-- Jquery -->
<script type="text/javascript" src="./library/js/jquery-1.3.2.min.js"></script>
<script>var $jquery = jQuery.noConflict();</script>
<script type="text/javascript" src="./library/js/jquery.pstrength-min.1.2.js"></script>
<script type="text/javascript" src="./library/js/jquery.captchaRefresh.js"></script>
<script type="text/javascript" src="./library/js/ekptgTools.js" ></script>
</head>
<link rel="shortcut icon" href="./favicon.ico" />
<style>
.blink {
	animation: blink-animation 1s steps(5, start) infinite;
	-webkit-animation: blink-animation 1s steps(5, start) infinite;
}
@keyframes blink-animation {
 to {
 visibility: hidden;
}
}
@-webkit-keyframes blink-animation {
 to {
 visibility: hidden;
}
}
#validEmail {
	margin-top: 4px;
	margin-left: 9px;
	position: absolute;
	width: 16px;
	height: 16px;
}
.style7 {
	font-size: 12px
}
img {
  display: inline-block;
  src:../myetapp65/img/jkptg.png;
}

</style>
<div id="RegistrationForm">
  <form name='f1' id='f1'>
    <input id="jenis"type="hidden" name="jenis" value="$!jenis">

    <table width="100%" border="0" cellpadding="2" cellspacing="2" id="ID_TABLE_PIP">
      <tr>
        <td valign="top"  width="1%"></td>
        <td valign="top"  width="28%"></td>
        <td valign="top"  width="1%"></td>
        <td valign="top"  width="40%"><!-- $viewPengguna --></td>
        <td valign="top"  width="30%"><!-- $viewPengguna --></td>
      </tr>
      <tr>
        <td valign="top" colspan="5">
          <!--<img border="0" src="../myetapp65/img/jkptg.png" width="100%" height="100%"/>-->
          <img class="img"/>
        </td>
      </tr>
  </form>
</div>

<script>
      var element = document.getElementById("jenis");

  alert(element.value);
</script>

