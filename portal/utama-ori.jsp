<!-- Edited by Hazwan -->
<html>
<head>
<title>eTapp Online Portal</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<meta http-equiv="Pragma" Content="cache">

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

<style type="text/css">

body {
	margin:0px 0px 0px 0px;
	padding:0px 0px 0px 0px;
	text-align:center;
	background-image: url('../img/online/Online-Background.jpg');
}

a:link, a:visited { 
	color : #0000CD;
	font-size: 0.55em;
	font-weight: bold;
	font-family: Arial;
	background : inherit;
	text-decoration : underline;
} 

a:hover { 
	color : #800000;
	background : inherit;
	text-decoration : underline;
} 
	
#logincontent {
	width: 100%;
	/*position: fixed;
	top: 0;
	right: 0;
	bottom: 0;
	left: 0;*/
	margin-right: auto;
	margin-left: auto;
	font-size:0.8em;
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
}

.label-top label {
	display:block;
	}
	
form label {
	font-size:0.8em;
	font-family: Verdana, sans-serif;
	}
		
#button {
	font-size:0.8em;
	cursor:pointer;
	padding: 0px;
	width:77px;
	height:27px;
	-moz-box-shadow:0 2px 4px #aaaaaa;
	-webkit-box-shadow:0 2px 4px #aaaaaa;
	box-shadow:0 2px 4px #aaaaaa;
	margin:0;
	border:0px;
	color:#000000;
	background:url('../portal/images/main/masuk.png');
	}
	
.email-support {
	width:137px;
	height:13px;
	cursor:pointer;
}

/*MAIN CONTAINER*/
#container { 
	width : 770px;
	margin-left : auto;
	margin-right : auto;
	margin-top : 10px;
	padding : 0;
	line-height : 1.8em;
} 

#menucontainer { 
	position : relative;
	height : 56px;
	color : #e0e0e0;
	background : #B78A3D;
	width : 100%;
	font-family : Verdana,Arial,Helvetica,Sans serif;
} 

#menunav { 
	position : relative;
	height : 33px;
	font-size : 12px;
	text-transform : uppercase;
	font-weight : bold;
	background : #FFFFFF url('../portal/images/menu_bg.gif') repeat-x bottom left;
	padding : 0 0 0 20px;
} 

#menunav ul { 
	margin : 0;
	padding : 0;
	list-style-type : none;
	width : auto;
	float : left;
} 

#menunav ul li { 
	display : block;
	float : left;
	padding : 0 1px;
} 

#menunav ul li a { 
	display : block;
	float : inherit;
	color : #eaf3f8;
	text-decoration : none;
	padding : 0 0 0 10px;
	height : 33px;
} 

#menunav ul li a span { 
	padding : 12px 20px 0 0;
	height : 21px;
	float : left;
} 

#menunav ul li a:hover { 
	color : #fff;
	background : transparent url('../portal/images/menu_bg-OVER.gif') repeat-x bottom left;
} 

#menunav ul li a:hover span { 
	display : block;
	width : auto;
	cursor : pointer;
} 

#menunav ul li a.current, #menunav ul li a.current:hover { 
	color : #fff;
	background : #1d6893 url('../portal/images/menu_left-ON.gif') no-repeat top left;
	line-height : 275%;
} 

#menunav ul li a.current span { 
	display : block;
	padding : 0 20px 0 0;
	width : auto;
	background : #1d6893 url('../portal/images/menu_right-ON.gif') no-repeat top right;
	height : 33px;
} 

#menunav li {
	list-style: none;
	float: left; 
}
	
#menunav li a {
	display: block;
	padding: 3px 8px;
	color: #000000;
	text-decoration: none; 
}

#menunav li ul {
    display: none;
    width: 10em; /* Width to help Opera out */
}

#menunav li:hover ul {
    display: block;
    position: absolute;
    margin: 0px;
    padding: 0px;
	color: #ffffff;
	background: #79551C;
	width: 200px;
}
	
#menunav li:hover li {
    float: none; 
}
	
#menunav li:hover li a {
    background-color: #79551c;
    border-bottom: 0px solid #fff;
    color: #000; 
}

#navbar li li a:hover {
    background-color: #79551C;
	width: 30px;
}

/*SEARCH BOX*/
legend { 
	margin-bottom : ;
	color : #143c55;
	background-color: #e6b73a;
} 

fieldset {
	border-color: #9c7645;
}

fieldset fieldset{
	border: 10px;
}

.heading {
	height:18px;
	padding-left:0;
}

.button a {
	text-align:center;
	padding:7px 35px;
	-moz-box-shadow:0 1px 6px #000000;
	-webkit-box-shadow:0 1px 6px #000000;
	box-shadow:0 1px 6px #000000;
	border:1px solid #515F2C;
	color:#000000;
	
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#FCF1CF', endColorstr='#A4BA6F'); /* IE9++ */
	filter: progid:DXImageTransform.Microsoft.gradient(GradientType=0,startColorstr='#FCF1CF', endColorstr='#A4BA6F'); /* IE6 & IE7 */
	-ms-filter: "progid:DXImageTransform.Microsoft.gradient(GradientType=0,startColorstr='#FCF1CF', endColorstr='#A4BA6F')"; /* IE8 */
	zoom: 1;
		
	/* IE10 */ 
	background-image: -ms-linear-gradient(bottom, #A4BA6F 30%, #FCF1CF 70%);
	
	/* Mozilla Firefox */ 
	background-image: -moz-linear-gradient(bottom, #A4BA6F 30%, #FCF1CF 70%);
	
	/* Opera */ 
	background-image: -o-linear-gradient(bottom, #A4BA6F 30%, #FCF1CF 70%);
	
	/* Webkit (Safari/Chrome 10) */ 
	background-image: -webkit-gradient(linear, left bottom, left top, color-stop(0, #A4BA6F), color-stop(3, #FCF1CF));
	
	/* Webkit (Chrome 11+) */ 
	background-image: -webkit-linear-gradient(bottom, #A4BA6F 30%, #FCF1CF 70%);
	
	/* Proposed W3C Markup */ 
	background-image: linear-gradient(bottom, #A4BA6F 30%, #FCF1CF 70%);
	
	/*background-image: -moz-linear-gradient(center bottom , rgb(164, 186, 111) 30%, rgb(199, 212, 166) 70%);*/
	width:80%;
}

.button a:hover {
	text-align:center;
	color:#000000;
	
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#A4BA6F', endColorstr='#FCF1CF'); /* IE9++ */
	filter: progid:DXImageTransform.Microsoft.gradient(GradientType=0,startColorstr='#A4BA6F', endColorstr='#FCF1CF'); /* IE6 & IE7 */
	-ms-filter: "progid:DXImageTransform.Microsoft.gradient(GradientType=0,startColorstr='#A4BA6F', endColorstr='#FCF1CF')"; /* IE8 */
	zoom: 1;
		
	/* IE10 */ 
	background-image: -ms-linear-gradient(bottom, #FCF1CF 30%, #A4BA6F 70%);
	
	/* Mozilla Firefox */ 
	background-image: -moz-linear-gradient(bottom, #FCF1CF 30%, #A4BA6F 70%);
	
	/* Opera */ 
	background-image: -o-linear-gradient(bottom, #FCF1CF 30%, #A4BA6F 70%);
	
	/* Webkit (Safari/Chrome 10) */ 
	background-image: -webkit-gradient(linear, left bottom, left top, color-stop(0, #FCF1CF), color-stop(3, #A4BA6F));
	
	/* Webkit (Chrome 11+) */ 
	background-image: -webkit-linear-gradient(bottom, #FCF1CF 30%, #A4BA6F 70%);
	
	/* Proposed W3C Markup */ 
	background-image: linear-gradient(bottom, #FCF1CF 30%, #A4BA6F 70%);
	
	/*background-image: -moz-linear-gradient(center bottom , rgb(199, 212, 166) 30%, rgb(164, 186, 111) 70%);*/
}

.button a:active {
	text-align:center;
	-moz-box-shadow:none;
	-webkit-box-shadow:none;
	box-shadow:none;
	color:#000000;
	
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#FCF1CF', endColorstr='#A4BA6F'); /* IE9++ */
	filter: progid:DXImageTransform.Microsoft.gradient(GradientType=0,startColorstr='#FCF1CF', endColorstr='#A4BA6F'); /* IE6 & IE7 */
	-ms-filter: "progid:DXImageTransform.Microsoft.gradient(GradientType=0,startColorstr='#FCF1CF', endColorstr='#A4BA6F')"; /* IE8 */
	zoom: 1;
		
	/* IE10 */ 
	background-image: -ms-linear-gradient(bottom, #A4BA6F 30%, #FCF1CF 70%);
	
	/* Mozilla Firefox */ 
	background-image: -moz-linear-gradient(bottom, #A4BA6F 30%, #FCF1CF 70%);
	
	/* Opera */ 
	background-image: -o-linear-gradient(bottom, #A4BA6F 30%, #FCF1CF 70%);
	
	/* Webkit (Safari/Chrome 10) */ 
	background-image: -webkit-gradient(linear, left bottom, left top, color-stop(0, #A4BA6F), color-stop(3, #FCF1CF));
	
	/* Webkit (Chrome 11+) */ 
	background-image: -webkit-linear-gradient(bottom, #A4BA6F 30%, #FCF1CF 70%);
	
	/* Proposed W3C Markup */ 
	background-image: linear-gradient(bottom, #A4BA6F 30%, #FCF1CF 70%);
		
	/*background-image: -moz-linear-gradient(center bottom , rgb(164, 186, 111) 100%, rgb(199, 212, 166) 0%);*/
}

#buttonMasuk {
	text-align:center;
	font-family: Verdana, sans-serif;
	font-weight: bold;
	cursor: pointer;
	padding:5px 0;
	
	-moz-box-shadow:0 1px 6px #000000;
	-webkit-box-shadow:0 1px 6px #000000;
	box-shadow:0 1px 6px #000000;
    filter:
        progid:DXImageTransform.Microsoft.Shadow(color=#aaaaaa,direction=0,strength=6),
        progid:DXImageTransform.Microsoft.Shadow(color=#aaaaaa,direction=45,strength=6),
        progid:DXImageTransform.Microsoft.Shadow(color=#aaaaaa,direction=90,strength=6),
        progid:DXImageTransform.Microsoft.Shadow(color=#aaaaaa,direction=135,strength=6),
        progid:DXImageTransform.Microsoft.Shadow(color=#aaaaaa,direction=180,strength=6),
        progid:DXImageTransform.Microsoft.Shadow(color=#aaaaaa,direction=225,strength=6),
        progid:DXImageTransform.Microsoft.Shadow(color=#aaaaaa,direction=270,strength=6),
        progid:DXImageTransform.Microsoft.Shadow(color=#aaaaaa,direction=315,strength=6);
	
	border:1px solid #CEC65F;
	color:#000000;
	
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#FCF1CF', endColorstr='#F8DC88'); /* IE9++ */
	filter: progid:DXImageTransform.Microsoft.gradient(GradientType=0,startColorstr='#FCF1CF', endColorstr='#F8DC88'); /* IE6 & IE7 */
	-ms-filter: "progid:DXImageTransform.Microsoft.gradient(GradientType=0,startColorstr='#FCF1CF', endColorstr='#F8DC88')"; /* IE8 */
	zoom: 1;
		
	/* IE10 */ 
	background-image: -ms-linear-gradient(bottom, #F8DC88 0%, #FCF1CF 100%);
	
	/* Mozilla Firefox */ 
	background-image: -moz-linear-gradient(bottom, #F8DC88 0%, #FCF1CF 100%);
	
	/* Opera */ 
	background-image: -o-linear-gradient(bottom, #F8DC88 0%, #FCF1CF 100%);
	
	/* Webkit (Safari/Chrome 10) */ 
	background-image: -webkit-gradient(linear, left bottom, left top, color-stop(0, #F8DC88), color-stop(3, #FCF1CF));
	
	/* Webkit (Chrome 11+) */ 
	background-image: -webkit-linear-gradient(bottom, #F8DC88 0%, #FCF1CF 100%);
	
	/* Proposed W3C Markup */ 
	background-image: linear-gradient(bottom, #F8DC88 0%, #FCF1CF 100%);
	
	/*background-image: -moz-linear-gradient(center bottom , rgb(248, 220, 136) 40%, rgb(252, 241, 207) 60%);*/
	width:80%;
}

#buttonMasuk:hover {
	text-align:center;
	cursor: pointer;
	padding:5px 0;
	color:#000000;
	
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#F8DC88', endColorstr='#FCF1CF'); /* IE9++ */
	filter: progid:DXImageTransform.Microsoft.gradient(GradientType=0,startColorstr='#F8DC88', endColorstr='#FCF1CF'); /* IE6 & IE7 */
	-ms-filter: "progid:DXImageTransform.Microsoft.gradient(GradientType=0,startColorstr='#F8DC88', endColorstr='#FCF1CF')"; /* IE8 */
	zoom: 1;
		
	/* IE10 */ 
	background-image: -ms-linear-gradient(bottom, #FCF1CF 0%, #F8DC88 100%);
	
	/* Mozilla Firefox */ 
	background-image: -moz-linear-gradient(bottom, #FCF1CF 0%, #F8DC88 100%);
	
	/* Opera */ 
	background-image: -o-linear-gradient(bottom, #FCF1CF 0%, #F8DC88 100%);
	
	/* Webkit (Safari/Chrome 10) */ 
	background-image: -webkit-gradient(linear, left bottom, left top, color-stop(0, #FCF1CF), color-stop(3, #F8DC88));
	
	/* Webkit (Chrome 11+) */ 
	background-image: -webkit-linear-gradient(bottom, #FCF1CF 0%, #F8DC88 100%);
	
	/* Proposed W3C Markup */ 
	background-image: linear-gradient(bottom, #FCF1CF 0%, #F8DC88 100%);
	
	/*background-image: -moz-linear-gradient(center bottom , rgb(248, 220, 136) 0%, rgb(252, 241, 207) 100%);*/
	width:80%;
}

#buttonMasuk:active {
	text-align:center;
	cursor: pointer;
	padding:5px 0;
	-moz-box-shadow:none;
	-webkit-box-shadow:none;
	box-shadow:none;
	color:#000000;
	
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#FCF1CF', endColorstr='#F8DC88'); /* IE9++ */
	filter: progid:DXImageTransform.Microsoft.gradient(GradientType=0,startColorstr='#FCF1CF', endColorstr='#F8DC88'); /* IE6 & IE7 */
	-ms-filter: "progid:DXImageTransform.Microsoft.gradient(GradientType=0,startColorstr='#FCF1CF', endColorstr='#F8DC88')"; /* IE8 */
	zoom: 1;
		
	/* IE10 */ 
	background-image: -ms-linear-gradient(bottom, #F8DC88 0%, #FCF1CF 100%);
	
	/* Mozilla Firefox */ 
	background-image: -moz-linear-gradient(bottom, #F8DC88 0%, #FCF1CF 100%);
	
	/* Opera */ 
	background-image: -o-linear-gradient(bottom, #F8DC88 0%, #FCF1CF 100%);
	
	/* Webkit (Safari/Chrome 10) */ 
	background-image: -webkit-gradient(linear, left bottom, left top, color-stop(0, #F8DC88), color-stop(3, #FCF1CF));
	
	/* Webkit (Chrome 11+) */ 
	background-image: -webkit-linear-gradient(bottom, #F8DC88 0%, #FCF1CF 100%);
	
	/* Proposed W3C Markup */ 
	background-image: linear-gradient(bottom, #F8DC88 0%, #FCF1CF 100%);
		
	/*background-image: -moz-linear-gradient(center bottom , rgb(252, 241, 207) 0%, rgb(248, 220, 136) 100%);*/
	width:80%;
}

.body-regular {
	text-align:left;
	font-size: 0.7em;
	padding:10px;
	-moz-border-radius:1em;
	-webkit-border-radius:1em;
	border-radius:1em;
	margin:25px 0;
	color:#000000;
	background:#EBEEE3;
	width:850px;	
	/*background: #f0edcc;*/
    background-clip: padding-box;           /* Background stops at border */
    border: 25px solid rgba(255,255,255,.2);
    box-shadow:
      0 0 1px rgba(255,255,255,.8),         /* Bright outer highlight */
      0 0 3px rgba(0,0,0,.8),               /* Outer shadow */
      1px 1px 0 rgba(0,0,0,.8) inset,       /* Inner shadow (top + left) */
      -1px -1px 0 rgba(0,0,0,.8) inset;
	-moz-box-shadow:
      0 0 1px rgba(255,255,255,.8),         /* Bright outer highlight */
      0 0 3px rgba(0,0,0,.8),               /* Outer shadow */
      1px 1px 0 rgba(0,0,0,.8) inset,       /* Inner shadow (top + left) */
      -1px -1px 0 rgba(0,0,0,.8) inset;
	-webkit-box-shadow:
      0 0 1px rgba(255,255,255,.8),         /* Bright outer highlight */
      0 0 3px rgba(0,0,0,.8),               /* Outer shadow */
      1px 1px 0 rgba(0,0,0,.8) inset,       /* Inner shadow (top + left) */
      -1px -1px 0 rgba(0,0,0,.8) inset;
                          
	/*filter: progid:DXImageTransform.Microsoft.Blur(PixelRadius=3,MakeShadow=true,ShadowOpacity=0.30);
	-ms-filter: "progid:DXImageTransform.Microsoft.Blur(PixelRadius=3,MakeShadow=true,ShadowOpacity=0.30)";
	zoom: 1;*/    
}
	
.login-body {
	font-size: 0.7em;
	float:right;
	
	-moz-box-shadow:0 1px 9px #000000;
	-webkit-box-shadow:0 1px 9px #000000;
	box-shadow:0 1px 9px #000000;
    filter:
        progid:DXImageTransform.Microsoft.Shadow(color=#aaaaaa,direction=0,strength=6),
        progid:DXImageTransform.Microsoft.Shadow(color=#aaaaaa,direction=45,strength=6),
        progid:DXImageTransform.Microsoft.Shadow(color=#aaaaaa,direction=90,strength=6),
        progid:DXImageTransform.Microsoft.Shadow(color=#aaaaaa,direction=135,strength=6),
        progid:DXImageTransform.Microsoft.Shadow(color=#aaaaaa,direction=180,strength=6),
        progid:DXImageTransform.Microsoft.Shadow(color=#aaaaaa,direction=225,strength=6),
        progid:DXImageTransform.Microsoft.Shadow(color=#aaaaaa,direction=270,strength=6),
        progid:DXImageTransform.Microsoft.Shadow(color=#aaaaaa,direction=315,strength=6);

	margin:50px 50px;
	color:#000000;
	background:#fff;
	width:292px;
}
	
.body-header {
	width:250px;
	height:57px;
	background : url('../portal/images/main/online_header.png') no-repeat;
}

.login-header {
	width:293px;
	height:62px;
	background : url('../portal/images/main/login-title.png') no-repeat;
}

.body-content {
	width:100%;
	padding:15px;
	background : url('../portal/images/main/top-banner-8.png') no-repeat;
	background-position: center;
}

.jata-image {
	width:86px;
	height:69px;
	background : url('../portal/images/main/jata.png') no-repeat;
}

.jkptg-image {
	float: left;
	width:126px;
	height:64px;
	background : url('../portal/images/main/jkptg.png') no-repeat;
}

.glass {
	width: 90%;
	font-family:Verdana, Geneva, sans-serif;
	text-align: left;
	color: #CC9600;
	text-shadow: rgba(255,255,255,.75) -1px -3px, rgba(0,0,0,0.3) -2px -1px, rgba(0,0,0,0.5) 0 1px, rgba(0,0,0,.27) -1px -3px;
	font-size: 2.5em;
}
    
::-moz-selection {
	background: #fff2a8; /* Firefox */
}
	
::selection {
	background: #fff2a8; /* Safari */
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

<body onLoad="document.flogin.username.focus();"> 
<!-- <center> -->
<div id="logincontent" align="center">
	<div class="body-regular">
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td colspan="6" align="center" valign="middle">
					<div class="jkptg-image"></div>
				</td>
			</tr>
			<tr height="10px">
				<td colspan="6"><hr /></td>
			</tr>
			<tr>
				<td colspan="5">&nbsp;</td>
				<td width="375px" valign="middle" align="center"><div class="button"><a href="../borang/Panduan Pendaftaran Pengguna JKPTG Online.pdf" title="Panduan Pendaftaran" onclick="../borang/Panduan Pendaftaran Pengguna JKPTG Online.pdf"><span style="font-size:1.2em; font-weight:bold">Panduan Pendaftaran</span></a></div></td>
			</tr>
			<tr height="10px">
				<td colspan="6"><hr /></td>
			</tr>
			<tr>
				<td colspan="3">
				<div class="body-content">
				<div class="glass">JKPTG <i>Online</i></div>
				<span style="font-size:0.9em; font-family:Verdana">
				<p align="justify">eTaPP adalah sistem maklumat dan pengurusan Tanah Persekutuan dan Pembahagian Pusaka.
				Ianya merupakan sistem yang bersepadu dalam melaksanakan urusan mentadbir tanah secara cekap dan bermutu.
				Kemudahan secara online dalam proses berkaitan urusan Pembahagian Pusaka Kecil (PPK), Pengurusan Pengambilan Tanah (PPT), Harta Tanah Persekutuan (HTP) dan Penguatkuasa & Hasil Persekutuan (PHP).</p>
				<p align="justify">JKPTG <i>Online</i> merupakan satu sistem yang disediakan untuk membolehkan pengguna membuat permohonan pembahagian pusaka kecil, permohonan pengambilan tanah dan permohonan sewa tanah dan bangunan persekutuan secara <i>online</i>.</p>
				<p align="justify">Pengguna hanya perlu memasukkan butiran berkaitan ke dalam sistem yang telah diisi secara <i>Online</i> kemudian dapatkan nombor rujukan yang dijana secara automatik.
				Semua urusan permohonan dengan Unit Pusaka Kecil, Jabatan Ketua Pengarah Tanah dan Galin Negeri akan menggunakan nombor rujukan ini.</p>
				<p align="justify">Pengguna juga boleh merujuk menu Panduan Permohonan di Portal JKPTG <i>Online</i> bagi mendapatkan maklumat-maklumat lain yang diperlukan.</p>
				</span>
				</div>
				</td>
				<td colspan="3">
					<div class="login-body">
						<form name="portallogin" method="post" action="javascript:doLogin()">
						<table id="Table_01" width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
					    		<td colspan="4" class="login-header"></td>
							</tr>
							<tr>
					    		<td colspan="4">&nbsp;</td>
							</tr>
							<tr>
					        	<td width="30">&nbsp;</td>
					            <td colspan="2"><label for="username"><span style="font-size:0.9em">MYID / MYCOID</span></label></td>
					          	<td width="30">&nbsp;</td>
					        </tr>
					        <tr>
					      	    <td width="30">&nbsp;</td>
					            <td colspan="2"><input id="txtNoKPBaru" name="txtNoKPBaru" type="text" class="inputusername" tabindex="1" autocomplete="off" onKeyPress="return OnKeyPressGotoNextTabIndex(this, event)" size="38" maxlength="25" bordercolor="ffffff"></td>
					            <td width="30">&nbsp;</td>
					        </tr>
					        <tr>
								<td width="30">&nbsp;</td>
					            <td colspan="2"><label for="password"><span style="font-size:0.9em">KATA LALUAN</span></label></td>
					          	<td width="30">&nbsp;</td>
					        </tr>
					        <tr>
					          	<td width="30">&nbsp;</td>
					            <td colspan="2"><input name="password" type="password" class="inputusername" tabindex="2" onKeyPress="return submitenter(this,event)" size="38" maxlength="25"></td>
					            <td width="30">&nbsp;</td>
					        </tr>
				          	<tr>
				          		<td>&nbsp;</td>
				            	<td colspan="2"style="font-size:7pt; font-family : Verdana,Arial,Helvetica,Sans serif;" colspan="2" id="result">
					              	<span style="display: none;" id="ajaxindicator">
										<img alt="Working..." src="../img/indicator.gif"/>	
									</span>
								</td>
				            	<td>&nbsp;</td>
				          	</tr> 
							<tr>
								<td width="30">&nbsp;</td>
								<td width="150" valign="middle">&infin;&nbsp;<a href="../portal/lupakatalaluan.jsp" title="Lupa Kata Laluan" onclick="Modalbox.show(this.href, {title: this.title, width: 700, evalScripts: true, afterLoad: function() { checkValidEmail();} }); return false;"><span style="font-size:1.2em; font-weight:bold">Lupa Kata Laluan?</span></a></td>
								<td align="right" rowspan="2"><input id="buttonMasuk" type="submit" value="MASUK" tabindex="3"/></td>
								<td width="30">&nbsp;</td>
							</tr>
							<tr>
					    		<td width="30">&nbsp;</td>
					    		<td width="150" valign="middle">&infin;&nbsp;<a href="../portal/daftarpengguna.jsp" title="Daftar Akaun Baru" onclick="Modalbox.show(this.href, {title: this.title, width: 700, evalScripts: true, afterLoad: function() { pwdStrength();} }); return false;"><span style="font-size:1.2em; font-weight:bold">Daftar Akaun Baru</span></a></td>
					    		<td width="30">&nbsp;</td>
							</tr>
							<tr>
					    		<td colspan="4">&nbsp;</td>
							</tr>
				          	<tr>
				            	<td align="center" colspan="4"><span style="font-size:0.55em; font-family:Arial; font-weight:bold; color:#696969">Sebarang masalah sila hubungi</span>&nbsp;<a href="mailto:etappsupport@kptg.gov.my"><i>etappsupport@kptg.gov.my</i></a></td>
				          	</tr>
						</table>
						<input type="hidden" value="" name="command"/>
						</form>
					</div>
				</td>
			</tr>
			<tr height="10px">
				<td colspan="6"><hr /></td>
			</tr>
			<tr>
				<td align="center" colspan="6"><span style="font-size:0.7em; font-family:Verdana, sans-serif; color:#000000">Hakcipta Terpelihara &copy; Kerajaan Malaysia 2017. Jabatan Ketua Pengarah Tanah Dan Galian Persekutuan</span></td>
			</tr>
		</table>
	</div>
</div>		
<!-- </center> -->

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