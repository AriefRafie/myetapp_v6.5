<script>


	// Skrin Cetakan (mempunyai makalumat pilihan pegawai)
	function cetakSuratExt(idPermohonan, laporan){
		var url = "../x/${securityToken}/ekptg.report.htp.utiliti.FrmPopupPilihPegawaiReportView?idpermohonan="+idPermohonan+"&report="+laporan;	
	    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	
		if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus();
	
	}
	
	function checkEmail(str) {	
		//alert('checkEmail');
/* 		var at="@"
		var dot="."
		var lat=str.indexOf(at)
		var lstr=str.length
		var ldot=str.indexOf(dot)
		if(document.${formName}.txtemail.value!=""){
			if (str.indexOf(at)==-1){
			   alert("Sila Masukan Email Dengan Betul.")
			   document.${formName}.txtemail.focus(); 
			   return false
			}
	
			if (str.indexOf(at)==-1 || str.indexOf(at)==0 || str.indexOf(at)==lstr){
			   alert("Sila Masukan Email Dengan Betul.")
			   return false
			}
	
			if (str.indexOf(dot)==-1 || str.indexOf(dot)==0 || str.indexOf(dot)==lstr){
			    alert("Sila Masukan Email Dengan Betul.")
			    return false
			}
	
			 if (str.indexOf(at,(lat+1))!=-1){
			    alert("Sila Masukan Emel Dengan Betul.")
			    return false
			 }
	
			 if (str.substring(lat-1,lat)==dot || str.substring(lat+1,lat+2)==dot){
			    alert("Sila Masukan Emel Dengan Betul.")
			    return false
			 }
	
			 if (str.indexOf(dot,(lat+2))==-1){
			    alert("Sila Masukan Emel Dengan Betul.")
			    return false
			 }
			
			 if (str.indexOf(" ")!=-1){
			    alert("Sila Masukan Emel Dengan Betul.")
			    return false
			 }
		}
	 	return true; */
	 	var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
	 	if(str.value.match(mailformat)){
	 		str.focus();
	 		return true;
	 	}else{
	 		alert("Sila Masukan Emel Dengan Betul.");
	 		str.value = "";
	 		str.focus();
	 		return false;
	 	}

	 	
	}
	
	function formatCurrencyPertama(inputfield,num) {
		num = num.toString().replace(/\$|\,/g,'');
		if(isNaN(num))
			num = "0";
		sign = (num == (num = Math.abs(num)));
		num = Math.floor(num*100+0.50000000001);
		cents = num%100;
		num = Math.floor(num/100).toString();
		if(cents<10)
			cents = "0" + cents;
		for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++)
			num = num.substring(0,num.length-(4*i+3))+','+
		num.substring(num.length-(4*i+3));
		
		inputfield.value = num + '.' + cents;
		
	}
	
	function formatCurrencyPertama1(num) {
		num = num.toString().replace(/\$|\,/g,'');
		if(isNaN(num))
			num = "0";
		sign = (num == (num = Math.abs(num)));
		num = Math.floor(num*100+0.50000000001);
		cents = num%100;
		num = Math.floor(num/100).toString();
		if(cents<10)
			cents = "0" + cents;
		for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++)
			num = num.substring(0,num.length-(4*i+3))+','+
		num.substring(num.length-(4*i+3));
		
		//inputfield.value = num + '.' + cents;
		document.${formName}.txtsewa.value = num + '.' + cents;
		
	}	

	function gisWindow(url,params){		
		//url = "../servlet/ekptg.view.integrasi.gis.ModulChartingSenarai?command=getGIS";
		var urlTarget = "../servlet/ekptg.view.htp.FrmPajakanServlet?command=getGIS";
		actionName = "setSessionIdFail";
		target = "setSessionIdFail_result";
		doAjaxUpdater(document.${formName}, urlTarget, target, actionName);
		var url_ = '$!session.getAttribute("gisURL")';
		alert(url_+":"+params);
		var hWnd = window.open(url_+params,"Paparan GIS","scrollbars=1,width=800,height=700");
		 if ((document.window != null) && (!hWnd.opener))
		       hWnd.opener = document.window;
		    if (hWnd.focus != null) hWnd.focus();
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
	
	function arkibWindow(noFail){	
		var url = "../x/${securityToken}/ekptg.view.integrasi.sid.FrmPopupPaparArkibDokumen?kodModul=HTP&noFail="+noFail;	
		var hWnd = window.open(url,"printuser","scrollbars=1,width=800,height=700");
		 if ((document.window != null) && (!hWnd.opener))
		       hWnd.opener = document.window;
		    if (hWnd.focus != null) hWnd.focus();
			hWnd.focus();
	
	}
	
	// Butang [Cetak]
	function setTable(id){
		if(document.getElementById(id).style.display=="none"){
			document.getElementById(id).style.display="block";
		}else if(document.getElementById(id).style.display=="block"){
			document.getElementById(id).style.display="none";
		}
		
	}
	
	// 2018/02/18 - Pengiraan Luas (Pertukaran kepada Hektar)
	function kiraLuas(idLuas){
		var intConv = 5;
	  	var jenisLuas = idLuas;
	  	//KE HEKTAR
	  	if(jenisLuas == "1"){ // KILOMETER PERSEGI
	  		var luasK = (document.${formName}.txtLuas1.value);
			var luasH = luasK*100;
	  		document.${formName}.txtLuas.value = luasH.toFixed(intConv);
	  	
		}else if(jenisLuas == "2"){ //HEKTAR
	  		var luasH = (document.${formName}.txtLuas1.value);
	  		document.${formName}.txtLuas.value = luasH;
		   
		}else if(jenisLuas == "3"){ // METER PERSEGI
	  		var luasM = document.${formName}.txtLuas1.value;
	  	  	var luasH = (luasM*0.0001);
		  	document.${formName}.txtLuas.value = luasH.toFixed(intConv);
	   	
		}else if(jenisLuas == "4"){ //EKAR, ROOD, POLE
	  	  	var luasE = document.${formName}.txtLuas2.value;
		  	var luasR = document.${formName}.txtLuas3.value;
		  	var luasP = document.${formName}.txtLuas4.value;
		  	var luasH = (luasE*0.4046864)+(luasR*0.1011716)+(luasP*0.00252929);
		  	document.${formName}.txtLuas.value = luasH.toFixed(intConv);
		   		
	   	}else if(jenisLuas == "5"){ //KAKI PERSEGI
	  	  	var luasAsal = document.${formName}.txtLuas1.value;
		  	var luasK = luasAsal*0.0000092;
		  	document.${formName}.txtLuas.value = luasK.toFixed(intConv);
	  	  
	   	}else if(jenisLuas == "6"){	//EKAR PERPULUHAN
	  	  	var luasAsal = document.${formName}.txtLuas1.value;
		  	/* AZAM */
		  	var luasK = luasAsal*0.405;
		  	document.${formName}.txtLuas.value = luasK.toFixed(intConv);
		  	/* var luasK = luasAsal*0.0000092;
	  	  	var num1 = (parseFloat(a) * 4046.86)/1000;  //Ekar perpuluhan to Hektar    
	  	  	*/	  	  
	   	
	   	}else if(jenisLuas == "7"){ //EKAR,DEPA
	  	  	var luasE = document.${formName}.txtLuas5.value;
		  	var luasD = document.${formName}.txtLuas6.value;		  
		  	var luasH = (luasE*0.4046864)+(luasD*0.00040469);
		  	document.${formName}.txtLuas.value = luasH.toFixed(intConv);
		  
	   	}else if(jenisLuas == "8"){ //RELONG,JEMBA,KAKI PERSEGI
	  	  	var luasR = document.${formName}.txtLuas2.value;
		  	var luasJ = document.${formName}.txtLuas3.value;
		  	var luasK = document.${formName}.txtLuas4.value;  
		  	var luasH = (luasR*0.2877764)+(luasJ*0.0005945)+(luasK*0.0000092);
		  	document.${formName}.txtLuas.value = luasH.toFixed(intConv);
	   
	   	}
	   	//by Rosli 2010/05/10
	 	if(document.${formName}.socLuas.value == "4" || document.${formName}.socLuas.value == "7" ||document.${formName}.socLuas.value == "8"){
			if(document.${formName}.socLuas.value == "4"){
				document.${formName}.txtLuasGabung.value = document.${formName}.txtLuas2.value +"E,"+document.${formName}.txtLuas3.value+"R,"+document.${formName}.txtLuas4.value+"P";
			}else if(document.${formName}.socLuas.value == "7"){
				document.${formName}.txtLuasGabung.value = document.${formName}.txtLuas5.value +"E,"+document.${formName}.txtLuas6.value+"D";
			}else if(document.${formName}.socLuas.value == "8"){
				document.${formName}.txtLuasGabung.value = document.${formName}.txtLuas2.value +"R,"+document.${formName}.txtLuas3.value+"J,"+document.${formName}.txtLuas4.value+"K";
			}
		
	 	}else{
			document.${formName}.txtLuasGabung.value = document.${formName}.txtLuas1.value;
		}
	
	}
	
	// 2020/11/19 - Set Luas
	function kiraLuasAsal(idLuas){
		var intConv = 5;
	  	var jenisLuas = idLuas;
	  	//KE HEKTAR
	  	if(jenisLuas == "1" 
	  		|| (jenisLuas == "2")
	  		|| (jenisLuas == "3")
	  		|| (jenisLuas == "5")
	  		|| (jenisLuas == "6")){ // KILOMETER PERSEGI
	  		var luasK = (document.${formName}.txtluasasal1.value);
	  		document.${formName}.txtluasasal.value = luasK;
	  		   	   	
		}else if(jenisLuas == "4"){ //EKAR, ROOD, POLE
	  	  	var luasE = document.${formName}.txtluasasal2.value;
		  	var luasR = document.${formName}.txtluasasal3.value;
		  	var luasP = document.${formName}.txtluasasal4.value;
		  	document.${formName}.txtluasasal.value = luasE + luasR + luasP;
		   			   	
	   	}else if(jenisLuas == "7"){ //EKAR,DEPA
	  	  	var luasE = document.${formName}.txtLuas5.value;
		  	var luasD = document.${formName}.txtLuas6.value;		  
		  	document.${formName}.txtluasasal.value = luasE+luasD;
		  
	   	}else if(jenisLuas == "8"){ //RELONG,JEMBA,KAKI PERSEGI
	  	  	var luasR = document.${formName}.txtluasasal2.value;
		  	var luasJ = document.${formName}.txtluasasal3.value;
		  	var luasK = document.${formName}.txtluasasal4.value;  
		  	document.${formName}.txtluasasal.value = luasR+luasJ+luasK;
	   
	   	}
	   	//by Rosli 2010/05/10
	 	if(document.${formName}.socLuas.value == "4" || document.${formName}.socLuas.value == "7" ||document.${formName}.socLuas.value == "8"){
			if(document.${formName}.socLuas.value == "4"){
				document.${formName}.txtluasgabungasal.value = document.${formName}.txtluasasal2.value +"E,"+document.${formName}.txtluasasal3.value+"R,"+document.${formName}.txtluasasal4.value+"P";
			}else if(document.${formName}.socLuas.value == "7"){
				document.${formName}.txtluasgabungasal.value = document.${formName}.txtluasasal5.value +"E,"+document.${formName}.txtluasasal6.value+"D";
			}else if(document.${formName}.socLuas.value == "8"){
				document.${formName}.txtluasgabungasal.value = document.${formName}.txtluasasal2.value +"R,"+document.${formName}.txtluasasal3.value+"J,"+document.${formName}.txtluasasal4.value+"K";
			}
		
	 	}else{
			document.${formName}.txtluasgabungasal.value = document.${formName}.txtluasasal1.value;
		}
	
	}
	// 2018/02/18 - Disalin dari frmPajakanPopupMaklumatTanah.jsp/, fungsi format 5 decimal
	function format5Decimal(elmnt,content,content2) {
		//if it is character, then remove it..
		//if (isNaN(content)) {
		//	elmnt.value = content2;
		//	return;
		//}
	
		if(content != ""){
			var num = content * 1;
			elmnt.value = num.toFixed(5);
			//elmnt.value = num.toFixed(4);
			return;
		} else {
			elmnt.value ="";
			return;
		}
		
	}
	// TARIKH
	
	//semakan tarikh, tidak lebih dari tarikh semasa
	function checkDate(inputfield) {
		var today = new Date();	
		dari_bulan = inputfield.value.substring(3,5);
		dari_hari = inputfield.value.substring(0,2);
		dari_tahun = inputfield.value.substring(6,10);
		var daritemp = dari_bulan+"/"+dari_hari+"/"+dari_tahun;	
		var myDate = Date.parse(daritemp);
	
		if (myDate>today) {
	  		alert("Tarikh yang dimasukkan tidak boleh melebihi Tarikh semasa");
	  		inputfield.value = "";
	  		inputfield.focus();
	 		return;
	 	}	
	
	}
	//semakan Tarikh semasa null
	function validateTarikhSemasaIsNull(inputfield) {
		var today = new Date();	
		if(inputfield.value != ''){
			dari_bulan = inputfield.value.substring(3,5);
			dari_hari = inputfield.value.substring(0,2);
			dari_tahun = inputfield.value.substring(6,10);
			var daritemp = dari_bulan+"/"+dari_hari+"/"+dari_tahun;	
			var myDate = Date.parse(daritemp);
		
			if (myDate>today) {
		  		alert("Tarikh yang dimasukkan tidak boleh melebihi Tarikh semasa");
		  		inputfield.value="";
		  		inputfield.focus();
		 		return;
		 		
		 	}
		}
		
	}
	//Textfield
	function textCounter(field, countfield, maxlimit) {
		if (field.value.length > maxlimit) // if too long...trim it!
			field.value = field.value.substring(0, maxlimit);
			// otherwise, update 'Baki Aksara' counter
		else 
			countfield.value = maxlimit - field.value.length;
	}
</script>