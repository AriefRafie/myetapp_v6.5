<script>
	//javaScriptPajakanPaging
	//Pagging
	function langkah1(){
		// 2017 
		//document.${formName}.actionPajakan.value = "papar";
		document.${formName}.actionPajakan.value = "";
		//document.${formName}.action = "?_portal_module=ekptg.view.htp.pajakan.FrmPajakanSenaraiFailView";
		document.${formName}.mode.value = "view";
		//document.${formName}.submit();
		doAjaxCall${formName}("");
		
	}
	
	function langkah2(permohonan,idFail){
		url = "../servlet/ekptg.view.htp.FrmPajakanServlet?command=papar&idFail="+idFail;
		actionName = "setSessionIdFail";
		target = "setSessionIdFail_result";
		doAjaxUpdater(document.${formName}, url, target, actionName);
		//document.${formName}.action = "?_portal_module=ekptg.view.htp.pajakan.FrmPajakanPendaftaranView&idPermohonan="+permohonan+"&actionPajakan=papar";
		document.${formName}.actionPajakan.value = "papardaftar";
		document.${formName}.mode.value = "view";
		document.${formName}.selectedTab.value = 0;
		//document.${formName}.submit();
		doAjaxCall${formName}("");
	}

	function langkah3(permohonan,idFail){
		url = "../servlet/ekptg.view.htp.FrmPajakanServlet?command=papar&idFail="+idFail;
		actionName = "setSessionIdFail";
		target = "setSessionIdFail_result";
		doAjaxUpdater(document.${formName}, url, target, actionName);
		//document.${formName}.action = "?_portal_module=ekptg.view.htp.pajakan.FrmPajakanMJMView&idPermohonan="+permohonan+"&actionPajakan=papar";
		document.${formName}.actionPajakan.value = "paparmjm";
		document.${formName}.mode.value = "view";
		document.${formName}.selectedTab.value = 0;
		//document.${formName}.submit();
		doAjaxCall${formName}("");
		
	}

	function langkah4(permohonan,idFail){
		url = "../servlet/ekptg.view.htp.FrmPajakanServlet?command=papar&idFail="+idFail;
		actionName = "setSessionIdFail";
		target = "setSessionIdFail_result";
		doAjaxUpdater(document.${formName}, url, target, actionName);
		//document.${formName}.action = "?_portal_module=ekptg.view.htp.pajakan.FrmPajakanPerjanjianView&idPermohonan="+permohonan+"&actionPajakan=papar";
		document.${formName}.actionPajakan.value = "paparperjanjian";
		document.${formName}.mode.value = "view";
		//
		document.${formName}.selectedTab.value = 0;
		//document.${formName}.submit();
		//
		doAjaxCall${formName}("");
		//doAjaxCall${formName}("","selectedTab=0");

	}

	function langkah5(permohonan,idFail){
		url = "../servlet/ekptg.view.htp.FrmPajakanServlet?command=papar&idFail="+idFail;
		actionName = "setSessionIdFail";
		target = "setSessionIdFail_result";
		doAjaxUpdater(document.${formName}, url, target, actionName);
		//document.${formName}.action = "?_portal_module=ekptg.view.htp.pajakan.FrmPajakanBayaranView&idPermohonan="+permohonan+"&actionPajakan=BayaranPajakan";
		document.${formName}.actionPajakan.value = "BayaranPajakan";
		document.${formName}.mode.value = "view";
		//document.${formName}.submit();
		doAjaxCall${formName}("");

	}
	function langkah6(permohonan,idFail){
		//alert('langkah 6');
		url = "../servlet/ekptg.view.htp.FrmPajakanServlet?command=papar&idFail="+idFail;
		actionName = "setSessionIdFail";
		target = "setSessionIdFail_result";
		doAjaxUpdater(document.${formName}, url, target, actionName);
		//document.${formName}.action = "?_portal_module=ekptg.view.htp.pajakan.FrmPajakanBayaranView&idPermohonan="+permohonan+"&actionPajakan=BayaranPajakan";
		document.${formName}.actionPajakan.value = "penamatan";
		document.${formName}.mode.value = "view";
		//document.${formName}.submit();
		doAjaxCall${formName}("");

	}
	function langkah7(permohonan,idFail){
		//alert('langkah 6');
		url = "../servlet/ekptg.view.htp.FrmPajakanServlet?command=papar&idFail="+idFail;
		actionName = "setSessionIdFail";
		target = "setSessionIdFail_result";
		doAjaxUpdater(document.${formName}, url, target, actionName);
		//document.${formName}.action = "?_portal_module=ekptg.view.htp.pajakan.FrmPajakanBayaranView&idPermohonan="+permohonan+"&actionPajakan=BayaranPajakan";
		document.${formName}.actionPajakan.value = "pemantauan";
		//document.${formName}.mode.value = "view";
		document.${formName}.mode.value = "";
		//document.${formName}.submit();
		doAjaxCall${formName}("");

	}	
</script>
