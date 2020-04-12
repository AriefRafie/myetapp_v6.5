<!-- Edited by Hazwan -->
<html>
<head>
<title>JKPTG Online</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<!-- <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" /> -->
<meta http-equiv="Pragma" Content="cache">
<link rel="shortcut icon" href="favicon.ico" />

<link rel="shortcut icon" href="favicon.ico" />
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

<!-- AddBy zulfazdliabuas@gmail.com For Design baru Online Date 09/10/2017 -->
<link type="text/css" rel="stylesheet" href="../css/eTapp_Login/login_online.css">

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

<script type="text/javascript">
<!--
var BrowserDetect = {
	init: function () {
		this.browser = this.searchString(this.dataBrowser) || "An unknown browser";
		this.version = this.searchVersion(navigator.userAgent)
			|| this.searchVersion(navigator.appVersion)
			|| "an unknown version";
		this.OS = this.searchString(this.dataOS) || "an unknown OS";
	},
	searchString: function (data) {
		for (var i=0;i<data.length;i++)	{
			var dataString = data[i].string;
			var dataProp = data[i].prop;
			this.versionSearchString = data[i].versionSearch || data[i].identity;
			if (dataString) {
				if (dataString.indexOf(data[i].subString) != -1)
					return data[i].identity;
			}
			else if (dataProp)
				return data[i].identity;
		}
	},
	searchVersion: function (dataString) {
		var index = dataString.indexOf(this.versionSearchString);
		if (index == -1) return;
		return parseFloat(dataString.substring(index+this.versionSearchString.length+1));
	},
	dataBrowser: [
		{
			string: navigator.userAgent,
			subString: "Chrome",
			identity: "Chrome"
		},
		{ 	string: navigator.userAgent,
			subString: "OmniWeb",
			versionSearch: "OmniWeb/",
			identity: "OmniWeb"
		},
		{
			string: navigator.vendor,
			subString: "Apple",
			identity: "Safari",
			versionSearch: "Version"
		},
		{
			prop: window.opera,
			identity: "Opera",
			versionSearch: "Version"
		},
		{
			string: navigator.vendor,
			subString: "iCab",
			identity: "iCab"
		},
		{
			string: navigator.vendor,
			subString: "KDE",
			identity: "Konqueror"
		},
		{
			string: navigator.userAgent,
			subString: "Firefox",
			identity: "Firefox"
		},
		{
			string: navigator.vendor,
			subString: "Camino",
			identity: "Camino"
		},
		{		// for newer Netscapes (6+)
			string: navigator.userAgent,
			subString: "Netscape",
			identity: "Netscape"
		},
		{
			string: navigator.userAgent,
			subString: "MSIE",
			identity: "Explorer",
			versionSearch: "MSIE"
		},
		{
			string: navigator.userAgent,
			subString: "Gecko",
			identity: "Mozilla",
			versionSearch: "rv"
		},
		{ 		// for older Netscapes (4-)
			string: navigator.userAgent,
			subString: "Mozilla",
			identity: "Netscape",
			versionSearch: "Mozilla"
		}
	],
	dataOS : [
		{
			string: navigator.platform,
			subString: "Win",
			identity: "Windows"
		},
		{
			string: navigator.platform,
			subString: "Mac",
			identity: "Mac"
		},
		{
			   string: navigator.userAgent,
			   subString: "iPhone",
			   identity: "iPhone/iPod"
	    },
		{
			string: navigator.platform,
			subString: "Linux",
			identity: "Linux"
		}
	]

};
BrowserDetect.init();

// -->
</script>

<script>
function checkBrowser() {
	if (BrowserDetect.browser != "Firefox") {
		var browser = BrowserDetect.browser + " " + BrowserDetect.version + " pada " + BrowserDetect.OS;
		alert("Anda menggunakan " + browser + "\nSila gunakan browser Mozilla Firefox untuk paparan terbaik");		
	
	}
	
}
</script>

</head>
<!--
<body leftmargin="0" topmargin="5" rightmargin="0" bottommargin="0" onLoad="document.flogin.username.focus();" marginwidth="0">
-->
<body>
<!-- <body document.portallogin.username.focus() onLoad="checkBrowser();"> -->
<center>
	<div id="logincontent">
	
		<div align="center" class="body-regular">
		<div class="body-header"></div>
		<div class="body-title"></div>
		<div class="body-content">
		
			<!-- <p align="center">
				<font size="1"><b>Sebarang masalah sila hubungi <a href="mailto:etappsupport@kptg.gov.my"><font size="1"><b>etappsupport@kptg.gov.my</b></font></a><br />
				<font size="2"><b>Paparan terbaik menggunakan Mozilla Firefox 3 ke atas dengan resolusi 1280 x 960</b></font>
			</p> -->
			
			<div class="login-body">
				<form name="portallogin" method="post" action="javascript:doLogin()">
					<table id="Table_01" align="right" width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
				    		<td colspan="4" class="login-header">
				    			<div class="header_login">
				    				<div class="log_masuk_icon"></div>
				    				<div class="log_masuk"><span>LOG MASUK</span></div>
				    			</div>
				    		</td>
						</tr>
						<tr>
				    		<td colspan="4" height="15"></td>
						</tr>
						<tr>
				        	<td rowspan="5">&nbsp;</td>
				            <td colspan="2" style="padding-left: 3px; padding-bottom: 3px;">
				            	<label for="username"><span style="font-size:0.9em">MyID / MyCoID</span></label>
				            </td>
				          	<td rowspan="5">&nbsp;</td>
				        </tr>
				        <tr>
				            <td colspan="2" align="center">
				            	<input class="form-control" id="txtNoKPBaru" name="txtNoKPBaru" type="text" tabindex="1" autocomplete="off" onKeyPress="return OnKeyPressGotoNextTabIndex(this, event)" style="width:100%" maxlength="25" bordercolor="ffffff">
				            </td>
				        </tr>
				  		<tr>
				    		<td colspan="2" height="10"></td>
						</tr>
				        <tr>
				            <td colspan="2" style="padding-left: 3px; padding-bottom: 3px;">
				            	<label for="password"><span style="font-size:0.9em">KATA LALUAN</span></label>
				            </td>
				        </tr>
				        <tr>
				            <td colspan="2" align="center">
				            	<input class="form-control" name="password" type="password" tabindex="2" onKeyPress="return submitenter(this,event)" style="width:100%" maxlength="25">
				            </td>
				        </tr>
						<tr>
							<td>&nbsp;</td>
				 			<td colspan="2"style="font-size:7pt; font-family : Verdana,Arial,Helvetica,Sans serif;"id="result">
								<span style="display: none;" id="ajaxindicator">
									<img alt="Working..." src="../img/indicator.gif"/>	
								</span>
							</td>
							<td>&nbsp;</td>
				      	</tr> 
						<tr>
							<td rowspan="4">&nbsp;</td>
							<td width="150" style="padding-left: 5px;" valign="middle">&raquo;&nbsp;
								<a href="../portal/lupakatalaluan.jsp" title="Lupa Kata Laluan" onClick="Modalbox.show(this.href, {title: this.title, width: 700, evalScripts: true, afterLoad: function() { checkValidEmail();} }); return false;">
									<span style="font-size:1.2em; font-weight:bold">Lupa Kata Laluan?</span>
								</a>
							</td>
							<td align="center" rowspan="3">
								<input id="button" type="submit" value="" tabindex="3"/>
							</td>
							<td rowspan="4">&nbsp;</td>
						</tr>
						<tr>
				    		<td width="150" style="padding-left: 5px;" valign="middle">&raquo;&nbsp;
					    		<a href="../portal/daftarpengguna.jsp" title="Daftar Akaun Baru" onClick="Modalbox.show(this.href, {title: this.title, width: 700, evalScripts: true, afterLoad: function() { pwdStrength();} }); return false;">
					    			<span style="font-size:1.2em; font-weight:bold">Daftar Akaun Baru</span>
					    		</a>
				    		</td>
						</tr>
						<tr>
				    		<td width="150" style="padding-left: 5px;" valign="middle">&raquo;&nbsp;
				    			<a href="../borang/Panduan Pendaftaran Pengguna JKPTG Online.pdf" title="Panduan Pendaftaran" onClick="../borang/Panduan Pendaftaran Pengguna JKPTG Online.pdf">
				    				<span style="font-size:1.2em; font-weight:bold">Panduan Pendaftaran</span>
				    			</a>
				    		</td>
						</tr>
				 		<tr>
				    		<td colspan="2" height="5"></td>
						</tr>
				       	<tr>
				           	<td align="center" colspan="4">
				           		<span style="font-size:0.55em; font-family:Arial; font-weight:bold; color:#696969">Sebarang masalah sila hubungi</span>&nbsp;
				           		<a href="mailto:etappsupport@kptg.gov.my">
				           			<i>etappsupport@jkptg.gov.my</i>
				           		</a>
				           	</td>
				       	</tr>
					</table>
					<input type="hidden" value="" name="command"/>
				</form>
			</div>
		</div>
		<div class="body-banner"></div>
		<div class="body-footer"></div>
		</div>
	</div>
</center>
<!-- End Ediby zulfazdliabuas@gmail.com for New design Login Page Online Date 09/10/2017-->

<script type="text/javascript">
function doLogin() {
	url = "../servlet/ekptg.view.online.Portal";
	target = "";
	actionName = "doLogin";
	target = "result";
	doAjaxUpdater(document.portallogin, url, target, actionName);
}

function doEffect() {
	new Effect.Highlight('result', {startcolor:'#800000',endcolor:'#FFFFFF', restorecolor:'#FFFFFF'});
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
</body>
</html>