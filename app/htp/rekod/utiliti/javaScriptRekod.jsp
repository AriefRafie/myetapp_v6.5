<script>
	//	Start frmRekodSenaraiHakmilikRizabReadOnly
	
	//[Radio Button ] Jenis Tanah, [ Cari] Butang Cari
	function cari(){   
		field_ = document.${formName}.socJenisTanahtemp;
		for (i = 0; i < field_.length; i++){
			if(field_[i].checked==true){
				document.${formName}.socJenisTanah.value = field_[i].value;
			}
		}
		doAjaxCall${formName}("","firstAction=carianHakmilikRizab");
		
	}
	
	//[ Kosongkan] Butang Kosongkan
	function kosongCarianRekod(idJenisTanah){
		if (idJenisTanah == '1'){
			document.${formName}.txtNoHakmilikC.value = "";
		} else if (idJenisTanah == '2'){
			document.${formName}.txtNoWartaC.value = "";
		} 
		document.${formName}.socJenisTanah.value = "0";
		document.${formName}.socStatus.value = "0";
		document.${formName}.socNegeri.value = "";
		document.${formName}.socDaerah.value = "";
		document.${formName}.socMukim.value = "";	
		document.${formName}.txtNoLotC.value = "";
		document.${formName}.txtNoFailC.value = "";
		document.${formName}.action = "?_portal_module=ekptg.view.online.htp.rekod.FrmRekodPendaftaranTanah&firstAction=carianHakmilikRizab";
		document.${formName}.submit();
	
	}
	
	//[ Kosongkan] Butang Kosongkan
	function kosongCarian(idJenisTanah){
		if (idJenisTanah == '1'){
			document.${formName}.txtNoHakmilikC.value = "";
		} else if (idJenisTanah == '2'){
			document.${formName}.txtNoWartaC.value = "";
		} 
		document.${formName}.socJenisTanah.value = "0";
		document.${formName}.socStatus.value = "0";
		document.${formName}.socNegeri.value = "";
		document.${formName}.socDaerah.value = "";
		document.${formName}.socMukim.value = "";	
		document.${formName}.txtNoLotC.value = "";
		document.${formName}.txtNoFailC.value = "";
		document.${formName}.action = "?_portal_module=ekptg.view.htp.negeri.rekod.FrmRekodTanahNegeri&firstAction=carianHakmilikRizab";
		document.${formName}.submit();
	
	}
	
	//[link] Buka Carian Terperinci
	function more(){
		document.${formName}.flagAdvSearch.value = "open";
		doAjaxCall${formName}("");
		
	}
	
	//[link] Tutup Carian Terperinci
	function less(){
		document.${formName}.flagAdvSearch.value = "";
		doAjaxCall${formName}("");
		
	}
	
	//[Selected Item] Daerah
	function doChangeDaerah() {
		doAjaxCall${formName}("","firstAction=carianHakmilikRizab&nextAction=doChangeDaerah");
	}

	//[Selected Item] Mukim
	function doChangeMukim() {
		doAjaxCall${formName}("","firstAction=carianHakmilikRizab&nextAction=doChangeMukim");
	}
	
	//[Selected Item] Kementerian
	function doChangeKementerian() {
		doAjaxCall${formName}("","firstAction=carianHakmilikRizab&nextAction=doChangeKementerian");
	}
			
	//[link] No. Hakmilik
	function hakmilik_detail(id,status){
		doAjaxCall${formName}("paparterperincihakmilik","firstAction=PendaftaranHakmilik&nextAction=paparDetailHakmilik&idHakmilik="+id+"&statusSah="+status);	
	}
	
	//[link] No. Warta
	function hakmilikDetailLagi(id,status){
		document.${formName}.idHakmilik.value = id;
		doAjaxCall${formName}("paparterperincihakmilik","firstAction=PendaftaranHakmilik&nextAction=paparDetailHakmilik&XidHakmilik="+id+"&statusSah="+status);

	}
	
	//	End frmRekodSenaraiHakmilikRizabReadOnly
	//	Start frmRekodMaklumatHakmilik.jsp
	
	function viewTransaksiCukai(id) {
		var url = "../x/${securityToken}/ekptg.view.htp.FrmRekodTransaksiCukai?idHakmilik="+id;
	    var hWnd = window.open(url,'printuser','width=300,height=300, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
	       hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus();
		hWnd.focus();
	}
	//[link] Maklumat Pergerakan
	function pergerakanhakmilik_detail(id){
		document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPergerakanHakmilik&firstAction=paparDetailPergerakanHakmilik&idHakmilik="+id;	
		document.${formName}.submit();
		//doAjaxCall${formName}("","firstAction=paparDetailPergerakanHakmilik&idHakmilik="+id);
	}
	
	function cetakMaklumatHakmilik(idhakmilik){
		var url = "../servlet/ekptg.report.htp.MaklumatFailHakmilikRizab?template=MaklumatHakmilik&idHakmilik="+idhakmilik;
	    var hWnd=window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
		hWnd.opener=document.window;
	    if (hWnd.focus != null) hWnd.focus();
	}
	
	function kembaliHakmilik(){
		doAjaxCall${formName}("","firstAction=carianHakmilikRizab");		
	}
	
	//	End frmRekodMaklumatHakmilik.jsp
	//	Start frmRekodMaklumatHakmilikSambungan.jsp
	
	//[Link] Skrin Hakmilik Sambungan, No. Hakmilik
	//21/07/2012
	function hakmilikDetailSamb(id,status){
		document.${formName}.idHakmilik.value = id;
		doAjaxCall${formName}("","firstAction=PendaftaranHakmilik&nextAction=paparDetailHakmilik&statusSah="+status);
	
	}
	
	//	Start frmRekodMaklumatHakmilikSambungan.jsp	
	//	Start frmRekodMaklumatRizab.jsp
	
		
	function cetakMaklumatRizab(idhakmilik){
		var url = "../servlet/ekptg.report.htp.MaklumatFailHakmilikRizab?template=MaklumatRizab&idHakmilik="+idhakmilik;
	    var hWnd=window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
		hWnd.opener=document.window;
	    if (hWnd.focus != null) hWnd.focus();
	}
	
	//	End frmRekodMaklumatRizab.jsp
	//	Start frmRekodPembangunanIndex.jsp
	
	function kembali(id,jenis){
	   	if(jenis == 'Y' || jenis == 'T'){
			//document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPendaftaranTanah&firstAction=PendaftaranHakmilik&nextAction=paparDetailHakmilik&idHakmilik="+id;
			doAjaxCall${formName}("","firstAction=PendaftaranHakmilik&nextAction=paparDetailHakmilik");
		}else{
			//document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPendaftaranTanah&firstAction=PendaftaranHakmilik&nextAction=paparDetailRizab&idHakmilik="+id;
			doAjaxCall${formName}("","firstAction=PendaftaranHakmilik&nextAction=paparDetailRizab");
		}
		//document.${formName}.submit();
	}
	
		 //[Simpan]
	 function tambahPerihalHakmilik(id,luasAsal,luasCurrent,luasJumlahGuna){
		
			 var diff1;var diff2;var combine;
		 
		 combine = eval(luasJumlahGuna) + eval(document.${formName}.txtLuas.value);
		 diff1 = eval(luasAsal) - eval(document.${formName}.txtLuas.value);
		 diff2 = luasAsal - combine;
		 //alert(diff1);
		 // alert(diff2);
		 
		 //VALIDATAION
		if(document.${formName}.socJenisBinaan.value == ""){ 
			alert('Sila masukkan " Jenis Binaan " terlebih dahulu.'); 
		 	document.${formName}.socJenisBinaan.focus();
		 	return; 
		
		}
		if(document.${formName}.socLuas.value == ""){ 
		 	alert('Sila masukkan "Unit Luas " terlebih dahulu.'); 
		 	document.${formName}.socLuas.focus();
		 	return; 
		
		}
		if(document.${formName}.txtLuas.value == "" || document.${formName}.txtLuas.value == "0"){ 
			alert('Sila masukkan " Luas " terlebih dahulu.'); 
		 	//document.${formName}.txtLuas.focus(); 
		 	return; 
		
		}	
		//if(document.${formName}.txtLuas.value>luasAsal){
		if(diff1 != 0 && diff1<0){
			alert("Jumlah luas guna melebihi luas asal.");
		 	//alert(luasCurrent);
		 	return;
		
		}
		//if(luasCurrent!=""){
		if (diff2 != 0 && diff2 < 0) {
		   		//if(document.${formName}.txtLuas.value>luasCurrent){
		   		//if(document.${formName}.txtLuas.value.toFixed(4)>luasCurrent.toFixed(4)){
			alert("Jumlah luas guna melebihi baki luas yang ada.");
		 	return;
		
		}
		//}		 
		//END OF VALIDATAION
		
		//document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPendaftaranTanah&firstAction=PendaftaranPembangunan&nextAction=tambahDetailKeluasan&idHakmilik="+id;
		//document.${formName}.submit();
		if ( !window.confirm("Adakah Anda Pasti?")) return;
		doAjaxCall${formName}("","firstAction=PendaftaranPembangunan&nextAction=tambahDetailKeluasan&idHakmilik="+id);
	
	}
	
			 //[Simpan]
	 function tambahPerihalHakmilikPengesahan(langkah,id,luasAsal,luasCurrent,luasJumlahGuna){
		 var diff1;var diff2;var combine;
			 alert(langkah);
			 alert(id);
		 
		 combine = eval(luasJumlahGuna) + eval(document.${formName}.txtLuas.value);
		 diff1 = eval(luasAsal) - eval(document.${formName}.txtLuas.value);
		 diff2 = luasAsal - combine;
		 
		 //VALIDATAION
		if(document.${formName}.socJenisBinaan.value == ""){ 
			alert('Sila masukkan " Jenis Binaan " terlebih dahulu.'); 
		 	document.${formName}.socJenisBinaan.focus();
		 	return; 
		
		}
		if(document.${formName}.socLuas.value == ""){ 
		 	alert('Sila masukkan "Unit Luas " terlebih dahulu.'); 
		 	document.${formName}.socLuas.focus();
		 	return; 
		
		}
		if(document.${formName}.txtLuas.value == ""){ 
			alert('Sila masukkan " Luas " terlebih dahulu.'); 
		 	document.${formName}.txtLuas.focus(); 
		 	return; 
		
		}	
		//if(document.${formName}.txtLuas.value>luasAsal){
		if(diff1 != 0 && diff1<0){
			alert("Jumlah luas guna melebihi luas asal.");
		 	//alert(luasCurrent);
		 	return;
		
		}
		//if(luasCurrent!=""){
		if (diff2 != 0 && diff2 < 0) {
		   		//if(document.${formName}.txtLuas.value>luasCurrent){
		   		//if(document.${formName}.txtLuas.value.toFixed(4)>luasCurrent.toFixed(4)){
			alert("Jumlah luas guna melebihi baki luas yang ada.");
		 	return;
		
		}
		//}		 
		//END OF VALIDATAION
		
		doAjaxCall${formName}("","firstAction=PendaftaranPembangunan&nextAction=tambahDetailKeluasanLangkah&idHakmilik="+id+"&langkah="+langkah);
	
	}
	//[Kembali]	Butang Kembali	
 	function dariPembangunan(id,jenis){
  		screen2(id,jenis);
 	}
	
	
	//	End frmRekodPembangunanIndex.jsp
	//	Start Paging
	
	function screen1(id){
		doAjaxCall${formName}("","firstAction=");		
	}
	
	function screen2(id,jenis){
		if(jenis == 'M'){
			doAjaxCall${formName}("","firstAction=PendaftaranHakmilik&nextAction=paparDetailHakmilik&idHakmilik="+id);		
		}else{
			doAjaxCall${formName}("","firstAction=PendaftaranHakmilik&nextAction=paparDetailRizab&idHakmilik="+id);
		}
		
	}
	
	function screen3(id){
		doAjaxCall${formName}("","firstAction=PendaftaranPembangunan&idHakmilik="+id);		
	}
	
	function screen4(id){
		doAjaxCall${formName}("","firstAction=PendaftaranImej&idHakmilik="+id);	
	}
	
	function screen5(id){
		doAjaxCall${formName}("","firstAction=PendaftaranHakmilik&nextAction=paparDetailRizab&idHakmilik="+id+"&statusSah="+status);		
	}
	
	//	End Paging
	//	Start Tab
	
	//[Tab]
	function selectTab(id){
		doAjaxCall${formName}("","firstAction=PendaftaranHakmilik&nextAction=paparDetailHakmilik&tabId="+id);		
	}
	
	
	//	End Tab
	
		//[Cetak]
	//15/08/2012
	function cetakPembangunan(idhakmilik,template){
		var url = "../servlet/ekptg.report.htp.MaklumatFailHakmilikRizab?template="+template+"&idHakmilik="+idhakmilik;
	    var hWnd=window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
		hWnd.opener=document.window;
	    if (hWnd.focus != null) hWnd.focus();
	    
	}
	
	//[link No. Warta]	
	function rizab_detail(id,status){
		// Asal, kemaskini pada 2017/07/27
		//doAjaxCall${formName}("","firstAction=PendaftaranHakmilik&nextAction=paparDetailRizab&idHakmilik="+id+"&statusSah="+status);
		//Keguna Bagi role online_kjp
		doAjaxCall${formName}("paparterperincirizab","firstAction=PendaftaranHakmilik&nextAction=paparDetailRizab&idHakmilik="+id+"&statusSah="+status);
		
	}
	
		//[Tab Rizab]
	function selectTabRizab(id){
		doAjaxCall${formName}("","firstAction=PendaftaranHakmilik&nextAction=paparDetailRizab&tabId="+id);		
	}
	
 function kiraLuas(idLuas){
   var jenisLuas = idLuas;
   // KILOMETER PERSEGI
   if(jenisLuas == "1"){
   	var luasK = (document.${formName}.txtLuas1.value);
 	var luasH = luasK*100;
   	document.${formName}.txtLuas.value = luasH;
    }
    else
    //HEKTER
     if(jenisLuas == "2"){
   	var luasH = (document.${formName}.txtLuas1.value);
   	document.${formName}.txtLuas.value = luasH;
    }
    else
    // METER PERSEGI
    if(jenisLuas == "3"){
   	  var luasM = document.${formName}.txtLuas1.value;
   	  var luasH = (luasM*0.0001);
 	  document.${formName}.txtLuas.value = luasH;
    }
    else
    //EKAR, ROOD, POLE
    if(jenisLuas == "4"){
   	  var luasE = document.${formName}.txtLuas2.value;
 	  var luasR = document.${formName}.txtLuas3.value;
 	  var luasP = document.${formName}.txtLuas4.value;
 	  var luasH = (luasE*0.4046864)+(luasR*0.1011716)+(luasP*0.00252929);
   	  document.${formName}.txtLuas.value = luasH;
    }
    else
    //KAKI PERSEGI
    if(jenisLuas == "5"){
   	  var luasAsal = document.${formName}.txtLuas1.value;
 	  var luasK = luasAsal*0.0000092;
   	  document.${formName}.txtLuas.value = luasK;
   	  
    }else if(jenisLuas == "6"){	//EKAR PERPULUHAN
   	  var luasAsal = document.${formName}.txtLuas1.value;
 	  /* AZAM */
 	  var luasK = luasAsal*0.405;
   	  document.${formName}.txtLuas.value = luasK.toFixed(4);
   	  
    }
    else
    //EKAR,DEPA
    if(jenisLuas == "7"){
   	  var luasE = document.${formName}.txtLuas5.value;
 	  var luasD = document.${formName}.txtLuas6.value;
 	  
 	  var luasH = (luasE*0.4046864)+(luasD*0.00040469);
   	  document.${formName}.txtLuas.value = luasH;
    }
     else
    //RELONG,JEMBA,KAKI PERSEGI
    if(jenisLuas == "8"){
   	  var luasR = document.${formName}.txtLuas2.value;
 	  var luasJ = document.${formName}.txtLuas3.value;
 	  var luasK = document.${formName}.txtLuas4.value;
 	  
 	  var luasH = (luasR*0.2877764)+(luasJ*0.0005945)+(luasK*0.0000092);
   	  document.${formName}.txtLuas.value = luasH;
    }
 }	
	
	 
 function doChangeTaraf2(mode) {
 	doAjaxCall${formName}("","mode="+mode+"&firstAction=PendaftaranPembangunan&nextAction=doChangeLuas");
 }
 
	function deleteDetailPembangunan(id){
	 	if ( !window.confirm("Adakah Anda Pasti?")) return;
	 	document.${formName}.idHakmilikPerihal.value = id;
 		doAjaxCall${formName}("","firstAction=PendaftaranPembangunan&nextAction=deleteDetailPembangunan");
	 	
	 }
		
	 function viewDetailPerihal(id_){
	 	document.${formName}.idHakmilikPerihal.value = id_; 	
		doAjaxCall${formName}("","firstAction=PendaftaranPembangunan&nextAction=viewDetailKeluasan");
	
	 }	
	//Guna as sebagai ganti kepada fungsi viewDetailPerihal(id_)
	function viewTindakan(id_,idSusulanStatus,idStatusFail,pautan){
	 	document.${formName}.idHakmilikPerihal.value = id_; 	
		doAjaxCall${formName}("","firstAction=PendaftaranPembangunan&nextAction=viewDetailKeluasan&idsusulanstatus="+idSusulanStatus+"&idstatusfail="+idStatusFail+"&pautan="+pautan);
	
	 } 
	 
	//Simpan pengesahan 
	function simpanTindakanRole(id_,idSusulanStatus,idStatusFail) {
	 	document.${formName}.idHakmilikPerihal.value = id_; 	
		doAjaxCall${formName}("","firstAction=PendaftaranPembangunan&nextAction=simpanpengesahan&idsusulanstatus="+idSusulanStatus+"&idstatusfail="+idStatusFail);
	
	}
	 	// Skrin Tindakan	
	function skrinTindakanTambah(id_) {
		document.${formName}.id_kemaskini.value = id_;
		document.${formName}.pagemode.value = 'tambah';
		doAjaxCall${formName}('tindakan',"");
	
	}
	
	function simpanTindakan(id_) {
		document.${formName}.id_kemaskini.value = id_;
		document.${formName}.pagemode.value = 'simpan';
		doAjaxCall${formName}('tindakan',"");
	
	}

	function batalTindakan(id_,idSusulanStatus,idSusulan) {
		document.${formName}.id_kemaskini.value = id_;
		document.${formName}.pagemode.value = 'view';
		doAjaxCall${formName}('tindakan','idsusulanstatus='+idSusulanStatus+'&idsusulan='+idSusulan);
	
	}
	
	function kemaskiniTindakan(id_,idSusulanStatus,idSusulan) {
		document.${formName}.id_kemaskini.value = id_;
		document.${formName}.pagemode.value = 'kemaskini';
		doAjaxCall${formName}('tindakan','idsusulanstatus='+idSusulanStatus+'&idsusulan='+idSusulan);
	
	}
	
	function doKemaskiniTindakan(id_,idSusulanStatus,idSusulan) {
		document.${formName}.id_kemaskini.value = id_;
		document.${formName}.pagemode.value = 'dokemaskini';
		doAjaxCall${formName}('tindakan','idsusulanstatus='+idSusulanStatus+'&idsusulan='+idSusulan);
	
	}
	 
	 function kemaskiniDetailPerihal(id){
		doAjaxCall${formName}("","firstAction=PendaftaranPembangunan&nextAction=kemaskiniDetailKeluasan&idHakmilikPerihal="+id);
	 }	 

	 function baruPembangunan(id){
		doAjaxCall${formName}("","firstAction=PendaftaranPembangunan&idHakmilik="+id);
	 }	 	
	 
	 function updatePerihalHakmilik(id_,luasAsal,luasCurrent,luasJumlahGuna,luasYgDiUpdate){
 		var diff1;var diff2;var combine;
		combine = eval(luasJumlahGuna) + eval(document.${formName}.txtLuas.value) - eval(luasYgDiUpdate);
		diff1 = eval(luasAsal) - eval(document.${formName}.txtLuas.value);
		diff2 = luasAsal - combine;
	 	//VALIDATAION
	  	if(document.${formName}.socJenisBinaan.value == ""){ 
	 		alert('Sila masukkan " Jenis Binaan " terlebih dahulu.'); 
	 		document.${formName}.socJenisBinaan.focus();
	 		return; 
	  	}
	  	if(document.${formName}.socLuas.value == ""){ 
	 		alert('Sila masukkan "Unit Luas " terlebih dahulu.'); 
	 		document.${formName}.socLuas.focus();
	 		return; 
	  	}
	  	if(document.${formName}.txtLuas.value == ""){ 
	 		alert('Sila masukkan " Luas " terlebih dahulu.'); 
	 		document.${formName}.txtLuas.focus(); 
	 		return; 
	  	}	
	  	if(diff1 != 0 && diff1<0){
	   		alert("Jumlah luas guna melebihi luas asal.");
	 		return;
	  	}
	  	if (diff2 != 0 && diff2 < 0) {
	 		alert("Jumlah luas guna melebihi baki luas yang ada.");
	 		return;
	 	}
  		//END OF VALIDATAION
 		document.${formName}.idHakmilikPerihal.value = id_;
  		doAjaxCall${formName}("","firstAction=PendaftaranPembangunan&nextAction=updateDetailKeluasan&idHakmilikPerihal="+id_);
 	
 	}
 	
 	//	imej/frmRekodPembangunanImejIndex

	//[]
	function baru(id){
		//document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPendaftaranTanah&firstAction=PendaftaranImej&idHakmilik="+id;
		//document.${formName}.submit();
		doAjaxCall${formName}("","firstAction=PendaftaranImej&idHakmilik="+id);
	}
	
	//[Simpan]
	function simpanDetailImej(id){
		if(document.${formName}.fileupload.value == ""){
			alert("Sila pilih fail terlebih dahulu");
			return;
		}
		
		if ( !window.confirm("Adakah Anda Pasti?")) return;
		var perihal = document.${formName}.txtPerihalImej.value;
		var ringkas = document.${formName}.txtRingkas.value ;
		document.${formName}.action = "?_portal_module=ekptg.view.htp.rekod.FrmRekodPendaftaranTanah&firstAction=PendaftaranImej&nextAction=tambahDetailImej&idHakmilik="+id+"&txtPerihalImej="+perihal+"&txtRingkas="+ringkas;
		document.${formName}.method="post";
		document.${formName}.enctype="multipart/form-data";
	    document.${formName}.encoding="multipart/form-data";
		document.${formName}.submit();
		
	}

	function viewDetailImej(id){
		//document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPendaftaranTanah&firstAction=PendaftaranImej&nextAction=viewDetailImej&idGambar="+id;
		//document.${formName}.submit();
		doAjaxCall${formName}("","firstAction=PendaftaranImej&nextAction=viewDetailImej&idGambar="+id);
	}
	
	function viewDetailInfo(id){
		//document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPendaftaranTanah&firstAction=PendaftaranImej&nextAction=viewDetailInfo&idGambar="+id;
		//document.${formName}.submit();
		doAjaxCall${formName}("","firstAction=PendaftaranImej&nextAction=viewDetailInfo&idGambar="+id);
	}
	
	function kemaskininDetailImej(id){
		//document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPendaftaranTanah&firstAction=PendaftaranImej&nextAction=kemaskiniDetailImej&idGambar="+id;
		//document.${formName}.submit();
		doAjaxCall${formName}("","firstAction=PendaftaranImej&nextAction=kemaskiniDetailImej&idGambar="+id);
	}
	
	function updateDetailImej(id){
		//document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPendaftaranTanah&firstAction=PendaftaranImej&nextAction=updateDetailImej&idGambar="+id;
		//document.${formName}.submit();
		doAjaxCall${formName}("","firstAction=PendaftaranImej&nextAction=updateDetailImej&idGambar="+id);
	}
	
	function cetakImej(id){
		var url = "../servlet/ekptg.view.htp.FrmRekodDisplayImej?id="+id;
	    var hWnd=window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes,menubar=1');
	    if ((document.window != null) && (!hWnd.opener))
		hWnd.opener=document.window;
	    if (hWnd.focus != null) hWnd.focus();
	}
	
	function deleteDetailImej(id){
		if ( !window.confirm("Adakah Anda Pasti?")) return;
		//document.${formName}.command.value = "";
		//document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPendaftaranTanah&firstAction=PendaftaranImej&nextAction=deleteDetailImej&idGambar="+id;
		//document.${formName}.submit();
		doAjaxCall${formName}("","firstAction=PendaftaranImej&nextAction=deleteDetailImej&idGambar="+id);
	}
	
	function doChangeJumlahLampiran(IDJadualLampiran,j,a) {
		if (j.value < 1) {
			alert("Sila masukkan nombor yang sah");
			j.value = 1;
			return;
		}//&nextAction=viewDetailImej
	 	doAjaxCall${formName}("imej","firstAction=PendaftaranImej&X="+a+"&action=changeLampiran&IDJadualLampiran="+IDJadualLampiran);
	
	}
	
	//12/07/2012
	function maklumatImej(idhakmilik){
		//var url = "../servlet/ekptg.view.htp.utiliti.FrmSenaraiImej?template=MaklumatHakmilik&idHakmilik="+idhakmilik;
		var url = "../x/${securityToken}/ekptg.view.htp.utiliti.FrmSenaraiImej?idHakmilik="+idhakmilik;
	    var hWnd=window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
		hWnd.opener=document.window;
	    if (hWnd.focus != null) hWnd.focus();
	    
	}
	
	function cetakImejSenarai(id){
		doAjaxCall${formName}("view","idGambar="+id);
	}
	
	function cetakImejSenaraiFull(id){
		doAjaxCall${formName}("viewFull","idGambar="+id);
	}
	
	function openPU(IDFail) {	
      document.${formName}.action = "$EkptgUtil.getTabID('Utiliti',$portal_role)?_portal_module=ekptg.view.integrasi.FrmViewEKadaster&action2=";
      document.${formName}.method = "POST";
      document.${formName}.submit();
      
  	}
	
	//state hantar pengesahan kepada penyemak di state
	function doStateHantarPengesahan(idHakmilikPerihal) {
	 	document.${formName}.idHakmilikPerihal.value = idHakmilikPerihal; 	
		doAjaxCall${formName}("","firstAction=PendaftaranPembangunan&nextAction=hantarPengesahanPenyemakNegeri");
	}
	
	function doPengesahanNegeri(idHakmilikPerihal) {
	 	document.${formName}.idHakmilikPerihal.value = idHakmilikPerihal; 	
		doAjaxCall${formName}("","firstAction=PendaftaranPembangunan&nextAction=pengesahanNegeri");
	}
	
	function doPengesahanHq(idHakmilikPerihal) {
	 	document.${formName}.idHakmilikPerihal.value = idHakmilikPerihal; 	
		doAjaxCall${formName}("","firstAction=PendaftaranPembangunan&nextAction=pengesahanHq");
	}
	
</script>
#parse("app/htp/utiliti/javaScriptUmum.jsp")
