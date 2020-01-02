function printReport(reportfile,params)
{
//	var url = "http://etapp.my:8080/jasperserver";
//	var url = "http://ilaunch13.duckdns.org/jasperserver";
	var url = checkPathUrlByState();
	url = url + "/flow.html?_flowId=viewReportFlow&j_acegi_security_check&j_username=myetapp&j_password=myetapp1&standAlone=true&_flowId=viewReportFlow&ParentFolderUri=%2Freports%2Fmyetapp&reportUnit=%2Freports%2Fmyetapp%2F"+reportfile;
	for(var i=0;i<params.length;i++){
		param = params[i];
		url = url + "&"+param;
	}
	
	var hWnd = window.open(url,"printuser","scrollbars=1,width=800,height=700");
	//window.open(url, 'winname','directories=no,titlebar=no,toolbar=no,location=no,status=no,menubar=no,scrollbars=no,resizable=no,width=400,height=350');
	
	//var popUpWindow;
	//popUpWindow = window.open('popUpDiv');
	//popUpWindow.document.write('<iframe height="450"  allowTransparency="true" frameborder="0" scrolling="yes" style="width:100%;" src="'+url+'" type= "text/javascript"></iframe>');
	//
	
	 if ((document.window != null) && (!hWnd.opener))
	       hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus();
		hWnd.focus();
}

function printReportSelfWindow(reportfile,params)
{
//	var url = "http://etapp.my:8080/jasperserver";
//	var url = "http://ilaunch13.duckdns.org/jasperserver";
	var url = checkPathUrlByState();
	url = url + "/flow.html?_flowId=viewReportFlow&j_acegi_security_check&j_username=myetapp&j_password=myetapp1&standAlone=true&_flowId=viewReportFlow&ParentFolderUri=%2Freports%2Fmyetapp&reportUnit=%2Freports%2Fmyetapp%2F"+reportfile;
	for(var i=0;i<params.length;i++){
		param = params[i];
		url = url + "&"+param;
	}
	
	window.open(url,"_self","scrollbars=1,width=800,height=700");
	
	//window.open(url, 'winname','directories=no,titlebar=no,toolbar=no,location=no,status=no,menubar=no,scrollbars=no,resizable=no,width=400,height=350');
	
	//var popUpWindow;
	//popUpWindow = window.open('popUpDiv');
	//popUpWindow.document.write('<iframe height="450"  allowTransparency="true" frameborder="0" scrolling="yes" style="width:100%;" src="'+url+'" type= "text/javascript"></iframe>');
	//
}

function checkPathUrlByState(){
	var path = window.location.pathname;
	if(path != null && path != '' && path.length>0){
		
		if(path.indexOf("/kedah/")> -1){
			return "http://10.19.144.70/jasperserverkdh";
		}else if(path.indexOf("/penang/")> -1){
			return "http://10.19.144.70/jasperserverpng";
		}else if(path.indexOf("/ns/")> -1){
			return "http://10.19.144.70/jasperserverns";
		}else if(path.indexOf("/melaka/")> -1){
			return "http://10.19.144.70/jasperservermlk";
		}else if(path.indexOf("/pahang/")> -1){
			return "http://10.19.144.70/jasperserverphg";
		}else if(path.indexOf("/johor/")> -1){
			return "http://10.19.144.70/jasperserverjhr";
		}else if(path.indexOf("/kelantan/")> -1){
			return "http://10.19.144.70/jasperserverkel";
		}else if(path.indexOf("/hq/") > -1){
			return "http://10.19.144.70/jasperserverhq";
		}else if(path.indexOf("ilaunch13.duckdns.org/wp/")> -1){
			//return "http://10.2.94.165/jasperserverwp";
			return "http://ilaunch13.duckdns.org/jasperserverwp";
		}else if(path.indexOf("/selangor/")> -1){
			return "http://10.19.144.70/jasperserversel";
		}else if(path.indexOf("/perak/")> -1){
			return "http://10.19.144.70/jasperserverprk";
		}else if(path.indexOf("/perlis/")> -1){
			return "http://10.19.144.70/jasperserverper";
		}else if(path.indexOf("/terengganu/")> -1){
			return "http://10.19.144.70/jasperservertrg";
		}else if(path.indexOf("/test/")> -1){
			return "http://10.19.144.70/jasperservertest";
		}else if(path.indexOf("/ekptgv3/")>-1){ //opis ilaunch
			return "http://etapp.my:8080/jasperserver";
		}else if(path.indexOf("192.168.0.96/wp/")>-1){ //opis ilaunch
			//return "http://etapp.my:8080/jasperserver";
			return "http://192.168.0.96:8022/jasperserverwp";
		}
	}
}