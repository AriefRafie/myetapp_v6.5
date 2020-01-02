<script>
	//2017/10/05, Dibuat	
	function changeJumlahLampiran(fa,IDJadualLampiran,j,a) {
		if (j.value < 1) {
			alert("Sila masukkan nombor yang sah");
			j.value = 1;
			return;
		}//&nextAction=viewDetailImej
	 	doAjaxCall${formName}("pembangunanimg","firstAction="+fa+"&X="+a+"&action=changeLampiran&IDJadualLampiran="+IDJadualLampiran);
	
	}
	
 function XkiraLuas(idLuas){
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
 		//aeda 
		var socJenisBinaan = document.${formName}.socJenisBinaan.value;
		var txtNoJKR = document.${formName}.txtNoJKR.value;
		var txdTarikhBina = document.${formName}.txdTarikhBina.value;
		var txtHarga = document.${formName}.txtHarga.value;
		var socLuas = document.${formName}.socLuas.value;
		var txtLuas1 = document.${formName}.txtLuas1.value;
		var txtLuas = document.${formName}.txtLuas.value;
		var txtCatatan = document.${formName}.txtCatatan.value;
		
		var perihal = document.${formName}.txtPerihalImej.value;
		var idHakmilik = document.${formName}.idHakmilik.value;
		
  		//doAjaxCall${formName}("","firstAction=PendaftaranPembangunan&nextAction=updateDetailKeluasan&idHakmilikPerihal="+id_+"&idGambar="+idGambar);
 	
  	
		if (document.${formName}.sizePicYgAda.value!="0"){ 
			
			doAjaxCall${formName}("","firstAction=PendaftaranPembangunan&nextAction=updateDetailKeluasan&idHakmilikPerihal="+id_);
			
		}/* else{
			
			if(document.${formName}.fileupload.value == ""){ 
				doAjaxCall${formName}("","firstAction=PendaftaranPembangunan&nextAction=updateDetailKeluasan&idHakmilikPerihal="+id_+"&idGambar="+idGambar);
				
			} */
		else{
	    		
	    		var urljava = "";
	    		
	    		if(document.${formName}.negeri.value=="SARAWAK" || document.${formName}.negeri.value=="SABAH"){ 
	    			
	    			urljava = "ekptg.view.htp.negeri.rekod.FrmRekodTanahNegeri";
	    			
	    		}else{
	    			
	    			urljava = "ekptg.view.htp.rekod.FrmRekodTanah";
	    			
	    		}
	    		
	    		//var ringkas = document.${formName}.txtRingkas.value ;
	    	    document.${formName}.action = "?_portal_module="+urljava+"&firstAction=PendaftaranPembangunan&nextAction=updateDetailKeluasan&idHakmilikPerihal="+id_+"&socJenisBinaan="+socJenisBinaan+"&txtNoJKR="+txtNoJKR+"&txdTarikhBina="+txdTarikhBina+"&txtHarga="+txtHarga+"&socLuas="+socLuas+"&txtLuas1="+txtLuas1+"&txtCatatan="+txtCatatan+"&txtPerihalImej="+perihal+"&idHakmilik="+idHakmilik+"&txtLuas="+txtLuas;
	    		document.${formName}.method="post";
	    		document.${formName}.enctype="multipart/form-data";
	    	    document.${formName}.encoding="multipart/form-data";
	    		document.${formName}.submit();
			}
			
    	
    	//}
  		
 	}
 	
	 function cetakPembangunan(idhakmilik,template){
			//alert('me script'+template);
		var url = "../servlet/ekptg.report.htp.MaklumatFailHakmilikRizab?template="+template+"&idHakmilik="+idhakmilik;
	    var hWnd=window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
		hWnd.opener=document.window;
	    if (hWnd.focus != null) hWnd.focus();
	    
	}
	
</script>
#parse("app/htp/utiliti/javaScriptUmum.jsp")
