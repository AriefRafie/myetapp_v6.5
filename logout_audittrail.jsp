<html>
<head>


<!-- Create By zulfazdliabuas@gmail.com Date 18-04-2017 for Paparkan maklumat audittrail selepas logout. Source File : logout_audittrail.jsp -->
<title></title>
<link href="./css/bootstrap.css" rel="stylesheet" type="text/css" />
<link href="./css/login.css" rel="stylesheet" type="text/css" />
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
<style type="text/css">
<!--
.style1 {font-size: small}
-->
#zpopup {
    background-color: #000 !important;
    border: 0 none;
    height: 100%;
    left: 0;
    margin: auto;
    position: absolute;
    top: 0;
    width: 100%;
    /*z-index: 9999;*/
	opacity: 0.65;
}

.divRekodAudit {
    height: 700px;
    left: 324.5px;
    overflow: visible;
    width: 700px;
    position: fixed;
    background-color: #ccd7b1;
    z-index: 10000;
}

#divAuditTrail table{
   font-size:12px;
}

.zScroll{
	/*border: 1px solid #000;*/
	overflow: scroll;
	height:300px;
	background: #fff;
	width: 100%;
    margin: 30 auto;
}

.boxAudit1{
	width:75%;
	margin:0 auto;
}

.boxAudit2{
	/*height:600px;*/
	background: #fff;
	/* width: 50%; */
    /*margin: 190px auto;*/
    position: absolute;
    padding: 20px;
    border-radius: 10px;
}

.divButton{
	margin: 0 auto;
	width: 100px;
	padding-bottom: 15px;
}

.table > thead > tr > th{
	text-align: center;
}

.divButton .btn-lg{
	padding: 5px 10px;
}


</style>
</head>

<body onLoad="getAuditTrail();">

<center>
	<div id="logincontent">
		<div class="body-regular" align="center">
			<div class="body-header"></div>
			<div class="body-title"></div>
			<div class="body-content">
				<div class="login-body">
				
				</div>
				<form id="f1" name="f1">
					
					
					<div id="zpopup">
						<input type="hidden" name="command" value="">
						<input type="hidden" id="user_id" name="user_id" value="<% out.print(session.getAttribute("_ekptg_user_id"));%>" >
						
					</div>
					<div class="boxAudit1">
						<div class="boxAudit2">
							<br />
							<h4>REKOD TINDAKAN</h4>
							<div id="divAuditTrail" class="zScroll">
								Tiada Rekod
							</div>	
						<!-- <input type="button" id="okAudit" name="okAudit" value="OK"> -->
							<div id="divButton" name="divButton" class="divButton">
									<button type="button" class="btn btn-primary btn-lg"  id="logoutOk" value="logoutOk" onclick="getLogoutOk()">OK</button>
									<!-- <a href='javascript:document.location.href = "logout"' class="font_welcome"><u>Logout</u></a> -->
							</div>
						</div>
					</div>
				</form>
			</div>
			<div class="body-banner"></div>
			<div class="body-footer"></div>
		</div>
	</div>
</center>

	
</body>
</html>

<script>
//alert("1 : "+document.getElementById("user_id").value);
getAuditTrail = function() {
	//alert("MASUK");
	//var user_id = document.getElementById("user_id").value;
	url = "./servlet/ekptg.view.online.LogoutAuditTrail";
	actionName = "getAuditTrail";
	target = "divAuditTrail";
	doAjaxUpdater(document.f1, url, target, actionName);
}

getLogoutOk = function(){
	//alert("MASUK X");
//	url = "./servlet/ekptg.view.online.LogoutAuditTrail";
//	actionName = "getLogoutOk";
	//target = "divButton";
//	target = "./logout_online.jsp";
//	doAjaxUpdater(document.f1, url, target, actionName);
	window.top.location.replace("logout_online.jsp");
	
}
</script>

