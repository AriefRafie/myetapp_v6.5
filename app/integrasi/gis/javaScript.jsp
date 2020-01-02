<script>	
	function gisWindow(url,params){	
		//alert('jasvascript:gisWindow');
		var urlTarget = "../servlet/ekptg.view.htp.FrmPajakanServlet?command=getGIS";
		actionName = "setSessionIdFail";
		target = "setSessionIdFail_result";
		doAjaxUpdater(document.${formName}, urlTarget, target, actionName);
		var url_ = '$!session.getAttribute("gisURL")';
		//alert(url_);
		//alert('$gisURL');
		//window.open(url_+params,"Paparan GIS","scrollbars=1,width=800,height=700");
		var hWnd = window.open(url_+params,"Paparan GIS","scrollbars=1,width=800,height=700");
		if ((document.window != null) && (!hWnd.opener))
		      hWnd.opener = document.window;
		
		if (hWnd.focus != null) 
			hWnd.focus();

	}	
	function tambahGIS(file,perolehan) {
		var command_ = 'terimapohoncarian';
		var hittButton = 'simpanGIS';
		doAjaxCall${formName}(command_,'hittbutton='+hittButton+"&nofailgis="+file+"&statusgis="+perolehan);
	
	}
	function tambahGISC(comm,file,perolehan,idhakmilik,comm_) {
		var command_ = comm;
		var hittButton = 'simpanGIS';
		doAjaxCall${formName}(command_,comm_+'hittbutton='+hittButton+"&nofailgis="+file+"&idHakmilik="+idhakmilik+"&statusgis="+perolehan);
		
	}
	

	
</script>