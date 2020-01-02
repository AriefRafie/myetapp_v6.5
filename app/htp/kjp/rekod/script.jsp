
<script>

	function doConvert(){
		
		var idJenisLuas = document.${formName}.socLuas.value;	
		//default/asal
		var luasKeseluruhan = '$!luasKeseluruhan';
		var luasMilik = '$!luasTanahMilik';
		var luasRizab = '$!luasTanahRizab';
		var unit = 'ha';
		
		if(idJenisLuas==""){
			alert("Sila pilih \"Jenis Keluasan\" terlebih dahulu.");
	  		document.${formName}.socLuas.focus(); 
			return;
		}else{
			
			luasKeseluruhan = luasKeseluruhan.toString().replace(/[^\d\.eE-]/g,'');
			luasMilik = luasMilik.toString().replace(/[^\d\.eE-]/g,'');
			luasRizab = luasRizab.toString().replace(/[^\d\.eE-]/g,'');
			
			//convert hektar ke lain
			if(idJenisLuas=="3"){	
				luasKeseluruhan = luasKeseluruhan*10000;
				luasMilik = luasMilik*10000;
				luasRizab = luasRizab*10000;
				unit = 'm&#178;';
			}else if(idJenisLuas=="5"){
				luasKeseluruhan = luasKeseluruhan*107639.104167;
				luasMilik = luasMilik*107639.104167;
				luasRizab = luasRizab*107639.104167;
				unit = 'ft&#178;';
			}else if(idJenisLuas=="6"){
				luasKeseluruhan = luasKeseluruhan*2.47105381467;
				luasMilik = luasMilik*2.47105381467;
				luasRizab = luasRizab*2.47105381467;
				unit = 'ac';
			}else{
				luasKeseluruhan=luasKeseluruhan*1;
				luasMilik=luasMilik*1;
				luasRizab=luasRizab*1;
				unit = 'ha';
			}
			
			document.getElementById('divLuasKeseluruhan').innerHTML = luasKeseluruhan.toFixed(5).replace(/\d(?=(\d{3})+\.)/g, '$&,');
			document.getElementById('divLuasMilik').innerHTML = luasMilik.toFixed(5).replace(/\d(?=(\d{3})+\.)/g, '$&,');
			document.getElementById('divLuasRizab').innerHTML = luasRizab.toFixed(5).replace(/\d(?=(\d{3})+\.)/g, '$&,');
			document.getElementById('divUnit1').innerHTML = unit;
			document.getElementById('divUnit2').innerHTML = unit;
			document.getElementById('divUnit3').innerHTML = unit;
		}
		
	}

	function doViewReportNegeri(){
		document.${formName}.command.value = "viewMainReportNegeri";
		//document.${formName}.action = "?_portal_module=ekptg.view.htp.rekod.kjp.KjpRekodController";
		getAction();
		document.${formName}.submit();
	}
	
	function doViewReportKementerian(){
		document.${formName}.command.value = "viewMainReportKementerian";
		//document.${formName}.action = "?_portal_module=ekptg.view.htp.rekod.kjp.KjpRekodController";
		getAction();
		document.${formName}.submit();
	}
	function doViewMain(){
		document.${formName}.command.value = "";
		//document.${formName}.action = "?_portal_module=ekptg.view.htp.rekod.kjp.KjpRekodController";
		getAction();
		document.${formName}.submit();
	}
	function doViewNegeriDetail(idNegeri){
		document.${formName}.command.value = "viewMainReportNegeriDetail";
		document.${formName}.idNegeri.value = idNegeri;
		//document.${formName}.action = "?_portal_module=ekptg.view.htp.rekod.kjp.KjpRekodController";
		getAction();
		document.${formName}.submit();
	}
	function doViewLot(idHakmilik){
		document.${formName}.command.value = "viewLot";
		document.${formName}.idHakmilik.value = idHakmilik;
		//document.${formName}.action = "?_portal_module=ekptg.view.htp.rekod.kjp.KjpRekodController";
		getAction();
		document.${formName}.submit();
	}
	
	function doViewKementerianDetail(idKementerian){
		document.${formName}.command.value = "viewMainReportKementerianDetail";
		document.${formName}.idKementerian.value = idKementerian;
		//document.${formName}.action = "?_portal_module=ekptg.view.htp.rekod.kjp.KjpRekodController";
		getAction();
		document.${formName}.submit();
	}
	
	function doViewAgensiDetail(idAgensi){
		document.${formName}.command.value = "viewMainReportAgensiDetail";
		document.${formName}.idAgensi.value = idAgensi;
		//document.${formName}.action = "?_portal_module=ekptg.view.htp.rekod.kjp.KjpRekodController";
		getAction();
		document.${formName}.submit();
	}
	function doCarian(){
		//document.${formName}.action = "?_portal_module=ekptg.view.htp.rekod.kjp.KjpRekodCarianController";
		getActionCarian();
		document.${formName}.submit();
	}

	function doCetakReportNegeri(){
		var url = "../servlet/ekptg.report.htp.kjp.LaporanRekodNegeri?template=TIADA"; 
		var hWnd = window.open(url,'Cetak','full=yes, resizable=yes,scrollbars=yes');
		if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
		if (hWnd.focus != null) hWnd.focus();
	}
	
	function doCetakReportNegeriDetail(idNegeri){ 
		//var url = "../servlet/ekptg.report.htp.kjp.LaporanRekodNegeriPilihan?idNegeri="+idNegeri; 
		var params = "idNegeri="+idNegeri+"&template=LaporanMOFMengikutPilihanNegeri";
		var url = "../servlet/ekptg.report.htp.kjp.LaporanRekodNegeri?"+params; 
		var hWnd = window.open(url,'Cetak','full=yes, resizable=yes,scrollbars=yes');
		if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
		if (hWnd.focus != null) hWnd.focus();
	}
	// 2017/09/24
	//function doCetakReportLot(idHakmilik){
	function doCetakReportLot(idHakmilik,jenisTanah){
		// 2017/09/24 ()
		// 'MILIK'
		var temp = 'template=MaklumatHakmilik';
		//var url = "../servlet/ekptg.report.htp.kjp.LaporanRekodLotByHakmilik?idHakmilik="+idHakmilik; 
		if(jenisTanah == 'RIZAB')
			temp = 'template=MaklumatRizab';
		//
		var url = "../servlet/ekptg.report.htp.MaklumatFailHakmilikRizab?"+temp+"&idHakmilik="+idHakmilik;

		var hWnd = window.open(url,'Cetak','full=yes, resizable=yes,scrollbars=yes');
		if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
		if (hWnd.focus != null) hWnd.focus();
	}
	function doCetakKementerian(){
		var url = "../servlet/ekptg.report.htp.kjp.LaporanRekodKementerian?template=TIADA" 
		var hWnd = window.open(url,'Cetak','full=yes, resizable=yes,scrollbars=yes');
		if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
		if (hWnd.focus != null) hWnd.focus();
	}
	function doCetakKementerianDetail(idKementerian){
		//var url = "../servlet/ekptg.report.htp.kjp.LaporanRekodKementerianPilihan?idKementerian="+idKementerian; 
		var params = "idKementerian="+idKementerian+"&template=LaporanMOFMengikutPilihanKementerian";
		var url = "../servlet/ekptg.report.htp.kjp.LaporanRekodKementerian?"+params; 
		var hWnd = window.open(url,'Cetak','full=yes, resizable=yes,scrollbars=yes');
		if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
		if (hWnd.focus != null) hWnd.focus();
	}
	function doCetakAgensiDetail(idAgensi){
		var url = "../servlet/ekptg.report.htp.kjp.LaporanRekodAgensiPilihan?idAgensi="+idAgensi; 
		var hWnd = window.open(url,'Cetak','full=yes, resizable=yes,scrollbars=yes');
		if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
		if (hWnd.focus != null) hWnd.focus();
	}
	
	function getAction(){
		document.${formName}.action = "?_portal_module=ekptg.view.htp.rekod.kjp.KJPRekodController";
	}
	
	function getActionCarian(){
		document.${formName}.action = "?_portal_module=ekptg.view.htp.rekod.kjp.KJPRekodCarianController";
	}
</script>