<script>
//javaScriptPajakanIndex.jsp
//NO.1 PENDAFTARAN

	function daftarBaru(){
		document.${formName}.actionPajakan.value = "daftarbaru";
		//document.${formName}.submit();
		doAjaxCall${formName}("");

	}
	//function papar(idFail,idStatus){
	function papar(idFail,idStatus,idPermohonan){
		//alert("idfail : "+idFail+" idstatus : "+idStatus+" idpermohonan : "+idPermohonan);
		var params = "";
		document.${formName}.idFail.value = idFail;
		url = "../servlet/ekptg.view.htp.FrmPajakanServlet?command=papar&idFail="+idFail;
		actionName = "setSessionIdFail";
		target = "setSessionIdFail_result";
		doAjaxUpdater(document.${formName}, url, target, actionName);

		if (idStatus == '6' || idStatus == '1' || idStatus == '12' || idStatus == '63'){
			//document.${formName}.action = "$EkptgUtil.getTabID('Pajakan',$portal_role)?_portal_module=ekptg.view.htp.FrmPajakanPendaftaranView";
			//Pendaftaran 24/01/2017
			document.${formName}.actionPajakan.value = "papardaftar";
			//document.${formName}.action = "?_portal_module=ekptg.view.htp.pajakan.FrmPajakanPendaftaranView";

		} else if (idStatus == '86' || idStatus == '87'){
			//document.${formName}.action = "$EkptgUtil.getTabID('Pajakan',$portal_role)?_portal_module=ekptg.view.htp.FrmPajakanMemorandumJemaahMenteriView";
			//document.${formName}.action = "?_portal_module=ekptg.view.htp.pajakan.FrmPajakanMJMView";
			//Pendaftaran 24/01/2017
			document.${formName}.actionPajakan.value = "paparmjm";
/*
			if(idStatus == '86' || idStatus == '87')
				params = "selectedTab=0"; */

		} else if (idStatus == '65' || idStatus == '148' || idStatus == '240' ){
			//document.${formName}.action = "$EkptgUtil.getTabID('Pajakan',$portal_role)?_portal_module=ekptg.view.htp.FrmPajakanMemorandumJemaahMenteriView";
			//document.${formName}.action = "?_portal_module=ekptg.view.htp.pajakan.FrmPajakanMJMView";
			//Pendaftaran 24/01/2017
			document.${formName}.actionPajakan.value = "paparpemohon";

		} else if (idStatus == '69'){
			//document.${formName}.action = "$EkptgUtil.getTabID('Pajakan',$portal_role)?_portal_module=ekptg.view.htp.FrmPajakanPerjanjianPajakanView";
			//document.${formName}.action = "?_portal_module=ekptg.view.htp.pajakan.FrmPajakanPerjanjianView";
			//Senarai Fail 24/01/2017
			document.${formName}.actionPajakan.value = "paparperjanjian";

		} else if (idStatus == '71'){
			//document.${formName}.action = "$EkptgUtil.getTabID('Pajakan',$portal_role)?_portal_module=ekptg.view.htp.FrmPajakanBayaranPajakanCukaiTanahView&actionPajakan";
			//document.${formName}.action = "?_portal_module=ekptg.view.htp.pajakan.FrmPajakanBayaranView";
			document.${formName}.actionPajakan.value = "BayaranPajakan";

		}else{
			//document.${formName}.action = "?_portal_module=ekptg.view.htp.pajakan.FrmPajakanPendaftaranView";
			document.${formName}.actionPajakan.value = "penamatan";

		}

		document.${formName}.mode.value = "view";
		document.${formName}.idPermohonan.value = idPermohonan;
		//2017/05/05 document.${formName}.submit();
		//doAjaxCall${formName}("");
		doAjaxCall${formName}("",params);

	}
	// Senarai fail, pautan [Buka Carian Terperinci]
	function more(){
		document.${formName}.actionPajakan.value = "carian";
		document.${formName}.flagAdvSearch.value = "Y";
		//document.${formName}.submit();
		doAjaxCall${formName}("");

	}
	// Senarai fail, pautan [Tutup Carian Terperinci]
	function less(){
		document.${formName}.actionPajakan.value = "carian";
		document.${formName}.flagAdvSearch.value = "";
		//document.${formName}.submit();
		doAjaxCall${formName}("");

	}
	// Senarai fail, butang [Carian]
	function carian(){
		if (document.${formName}.flagAdvSearch.value != ''){
			if(document.${formName}.socsuburusancarian.value == -1){
				alert("Sila Pilih Jenis Pajakan");
				return;
			}
		}
		document.${formName}.actionPajakan.value = "carian";
		//document.${formName}.submit();
		doAjaxCall${formName}("");

	}

//NO.2 PERMOHONAN

//NO.3 MAKLUMAT MJM
	//PEMOHON
	//ULASAN
	//DRAF PERJANJIAN

//NO.4 PERJANJIAN

//NO.5 BAYARAN

//NO.6 PENAMATAN

//NO.7 PEMANTAUAN
</script>
