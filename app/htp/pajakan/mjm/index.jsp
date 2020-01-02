
<p>
  <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
  <input type="hidden" name="mode" id="mode" value="$mode"/>
  <input type="hidden" name="hitButton" id="hitButton" /> 
  <input type="hidden" name="idFail" id="idFail" value="$idFail"/>
  <input type="hidden" name="idStatus" id="idStatus" value="$idStatus"/>
  <input type="hidden" name="selectedTab" id="selectedTab" value="$selectedTab"/>
  <input type="hidden" name="selectedTabLower" id="selectedTabLower" value="$selectedTabLower"/>
  <input type="hidden" name="idPermohonan" id="idPermohonan" value="$idPermohonan"/>
  <input type="hidden" name="actionPajakan" value="$actionPajakan"/>
  <input type="hidden" name="subUrusan" value="$subUrusan"/>
  <input type="hidden" name="idUlasanTeknikal" id="idUlasanTeknikal" value="$idUlasanTeknikal"/>
  <input type="hidden" name="idUlasanNilai" id="idUlasanNilai" value="$idUlasanNilai"/>
  <input type="hidden" name="idUlasanKJP" id="idUlasanKJP" value="$idUlasanKJP"/>
  <input type="hidden" name="idDraf" id="idDraf" value="$idDraf"/>
  <input type="hidden" name="idUlasanSPHP" id="idUlasanSPHP" value="$idUlasanSPHP"/>  
</p>

#if($showpopup.equalsIgnoreCase("true") && $selectedTab=="1")
<script>
	alert("Maklumat JPPH dan KJP masih tidak lengkap.");
</script>
#end

<table width="100%" border="0" >

	#if ($idFail != '')
	<tr>
		<td>
		#parse('app/htp/pajakan/paging.jsp')
		</td>
    </tr>
	<tr>
	    <td>#parse("app/htp/pajakan/fail/frmPajakanHeader.jsp")</td>
	  </tr>
	#else
	<tr>
	    <td>
	    	<div class="warning">SILA PILIH FAIL DI SENARAI FAIL TERLEBIH DAHULU</div>
		</td>
	</tr>
  	#end
  	
  	##if (($idFail != '' && $idStatus != '1' && $idStatus != '6' && $idStatus != '12' && $idStatus != '63')||($idFail != '' && $!page == "3"))
  	<tr>
    	<td>
       		<div id="TabbedPanels1" class="TabbedPanels">
            
           		<ul class="TabbedPanelsTabGroup">              
                	<li class="TabbedPanelsTab" title="Pemohon Pajakan" onclick="doChangeTab(0)" tabindex="0">MAKLUMAT PEMOHON</li>
                	<li class="TabbedPanelsTab" title="Ulasan" onclick="doChangeTab(1)" tabindex="1">ULASAN</li>
                	<li class="TabbedPanelsTab" title="Draf"  onclick="doChangeTab(2)" tabindex="2">DRAF PERJANJIAN</li>
                	<li class="TabbedPanelsTab" title="Memorandum Jemaah Menteri"  onclick="doChangeTab(3)" tabindex="3">MEMORANDUM JEMAAH MENTERI</li>
              	</ul>
              
              	<div class="TabbedPanelsContentGroup">              
                	<div class="TabbedPanelsContent">
                	<!-- content pemohon pajakan -->
					#if ($selectedTab == '0')
	                	#parse ("app/htp/pajakan/mjm/frmPajakanTabPemohonPajakan.jsp")
                    #end             
               		<!-- close content pemohon pajakan -->
                	</div>
                    
                	<div class="TabbedPanelsContent">
                	<!-- content Ulasan KJP -->
					#if ($selectedTab == '1')
                        #parse ("app/htp/pajakan/mjm/frmPajakanTabHeaderUlasan.jsp")
					#end               	
              		<!-- close content Ulasan KJP -->
              		</div>
              
                	<div class="TabbedPanelsContent">
                	<!-- content Draf -->
					#if ($selectedTab == '2')
	                	#parse ("app/htp/pajakan/mjm/frmPajakanTabDrafMJM.jsp")
					#end               	
              		<!-- close content Draf -->
              		</div>
              
              		<div class="TabbedPanelsContent">
                	<!-- content Memorandum Jemaah Menteri -->
					#if ($selectedTab == '3')
	                	#parse ("app/htp/pajakan/mjm/frmPajakanTabMJM.jsp")
					#end               	
              		<!-- close content Memorandum Jemaah Menteri -->
              		</div>
              
         		</div>
         		<!-- close TabbedPanelsContentGroup -->
              
      		</div>
      	<!-- close TabbedPanels1 -->
    
 	  	</td>
  	</tr>
	##end

</table>
 
<script type="text/javascript">
##if ($idFail != '' && $idStatus != '1' && $idStatus != '6' && $idStatus != '12' && $idStatus != '63')
	var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$selectedTab});
##end

</script>

<script>
	// 2017/05/05 Dipindah ke fail javaScriptPajakan.jsp
	/*function doChangeTab(tabId) {
		document.${formName}.selectedTab.value = tabId;
		document.${formName}.mode.value = "view";
		doAjaxCall${formName}("");
		
	}*/
	
	//PEMOHON, fungsi dalam fail javaScriptPajakanPemohon.jsp
	/*function KemaskiniPemohon(){
		document.${formName}.actionPajakan.value = "papar";
		document.${formName}.mode.value = "update";
		document.${formName}.submit();	
		//doAjaxCall${formName}("");
	}*/

//TAB ULASAN

	//ULASAN KJP
	/* 2017/05/22
	function daftarBaruKJP(){
		//document.${formName}.actionPajakan.value = "papar";
		document.${formName}.actionPajakan.value = "paparmjm";
		document.${formName}.mode.value = "newKJP";
		document.${formName}.submit();	
	//	doAjaxCall${formName}("");
	}
	
	function KemaskiniUlasanKJP(){
		//document.${formName}.actionPajakan.value = "papar";
		document.${formName}.actionPajakan.value = "paparmjm";
		document.${formName}.mode.value = "updateKJP";
		document.${formName}.submit();	
	//	doAjaxCall${formName}("");
	}
	
	function batalUlasanKJP(){
		//document.${formName}.actionPajakan.value = "papar";
		document.${formName}.actionPajakan.value = "paparmjm";
		document.${formName}.mode.value = "view";
		document.${formName}.submit();	
	//	doAjaxCall${formName}("");
	}
	*/
	//function SimpanUlasanKJP(){
		/*
		if(document.${formName}.txtNoRujukanKJP.value == ""){
			alert('Sila masukkan No.Rujukan KJP.');
	  		document.${formName}.txtNoRujukanKJP.focus(); 
			return; 
		} */
		 /* 2017/05/22 if(document.${formName}.txdTarikhHantarKJP.value == ""){
			alert('Sila masukkan Tarikh Hantar.');
	  		document.${formName}.txdTarikhHantarKJP.focus(); 
			return; 
		}*//*
		if(document.${formName}.txdTarikhTerimaKJP.value == ""){
			alert('Sila masukkan Tarikh Terima.');
	  		document.${formName}.txdTarikhTerimaKJP.focus(); 
			return; 
		}*/
		
		//var StarikhTerima = document.getElementById("txdTarikhTerimaKJP").value;
		//var StarikhHantar = document.getElementById("txdTarikhHantarKJP").value;
		/* 2017/05/22
		var StarikhHantar = document.${formName}.txdTarikhHantarKJP.value;
		
		var today = new Date();
		
		//for StarikhHantar
		var STHDate  = parseInt(StarikhHantar.substring(0,2),10);
	    var STHmon = parseInt(StarikhHantar.substring(3,5),10)-1;
	    var STHyr  = parseInt(StarikhHantar.substring(6,10),10); 

		var tarikhHantar = new Date(STHyr,STHmon,STHDate);
	
		if(tarikhHantar > today){
			alert('Tarikh Hantar mestilah tidak melebihi dari Hari ini.');
	  		document.${formName}.txdTarikhHantarKJP.focus(); 
			return; 
		}	
			
		//for StarikhTerima
		if(document.${formName}.txdTarikhTerimaKJP.value != ""){
			var StarikhTerima = document.${formName}.txdTarikhHantarKJP.value;
		
			var STTDate  = parseInt(StarikhTerima.substring(0,2),10);
	    	var STTmon = parseInt(StarikhTerima.substring(3,5),10)-1;
	    	var STTyr  = parseInt(StarikhTerima.substring(6,10),10); 
			
			var tarikhTerima = new Date(STTyr,STTmon,STTDate);
			if(tarikhTerima > today){
				alert('Tarikh Terima mestilah tidak melebihi dari Hari ini.');
		  		document.${formName}.txdTarikhTerimaKJP.focus(); 
				return; 
			}
			    
	    }		
	
		if ( !window.confirm("Adakah Anda Pasti ?") ){
			//document.${formName}.actionPajakan.value = "papar";
			document.${formName}.actionPajakan.value = "paparmjm";
			return;
		}
		
		document.${formName}.mode.value = "view";
		document.${formName}.hitButton.value = "saveUlasanKJP";
			//fir test 30032010
		document.${formName}.submit();	
	//	doAjaxCall${formName}("");
	
	} */
	
	//function SimpanUpdateKJP(){
		/*
		if(document.${formName}.txtNoRujukanKJP.value == ""){
			alert('Sila masukkan No.Rujukan KJP.');
	  		document.${formName}.txtNoRujukanKJP.focus(); 
			return; 
		} */
		/* 2017/05/22
		if(document.${formName}.txdTarikhHantarKJP.value == ""){
			alert('Sila masukkan Tarikh Hantar.');
	  		document.${formName}.txdTarikhHantarKJP.focus(); 
			return; 
		}*/	/*
		if(document.${formName}.txdTarikhTerimaKJP.value == ""){
			alert('Sila masukkan Tarikh Terima.');
	  		document.${formName}.txdTarikhTerimaKJP.focus(); 
			return; 
		} */
		/** 22/05/22
		//alert(1);
		//var StarikhHantar = document.getElementById("txdTarikhHantarKJP").value;
		var StarikhHantar = document.${formName}.txdTarikhHantarKJP.value;
		//alert(2);
		
		var today = new Date();
		//alert(3);
		
		//for StarikhHantar
		var STHDate  = parseInt(StarikhHantar.substring(0,2),10);
	    var STHmon = parseInt(StarikhHantar.substring(3,5),10)-1;
	    var STHyr  = parseInt(StarikhHantar.substring(6,10),10); 		
		//alert(4);

		var tarikhHantar = new Date(STHyr,STHmon,STHDate);
	    
		if(tarikhHantar > today){
			alert('Tarikh Hantar mestilah tidak melebihi dari Hari ini.');
	  		document.${formName}.txdTarikhHantarKJP.focus(); 
			return; 
		}		
		
		
	    //for StarikhTerima
		if(document.${formName}.txdTarikhTerimaKJP.value != ""){
			//var StarikhTerima = document.getElementById("txdTarikhTerimaKJP").value;
			var StarikhTerima = document.${formName}.txdTarikhHantarKJP.value;
			
			var STTDate  = parseInt(StarikhTerima.substring(0,2),10);
		    var STTmon = parseInt(StarikhTerima.substring(3,5),10)-1;
	    	var STTyr  = parseInt(StarikhTerima.substring(6,10),10); 
		
			var tarikhTerima = new Date(STTyr,STTmon,STTDate);

			if(tarikhTerima > today){
				alert('Tarikh Terima mestilah tidak melebihi dari Hari ini.');
		  		document.${formName}.txdTarikhTerimaKJP.focus(); 
				return; 
			}
		}
	
		if ( !window.confirm("Adakah Anda Pasti ?") ){
			//document.${formName}.actionPajakan.value = "papar";
			document.${formName}.actionPajakan.value = "paparmjm";
			return;
		}
		
		document.${formName}.mode.value = "viewKJP";
		document.${formName}.hitButton.value = "saveUpdateKJP";
		document.${formName}.submit();	
	//	doAjaxCall${formName}("");
	
	}
	
	function batalUpdateKJP(){
		document.${formName}.mode.value = "viewKJP";
		//document.${formName}.actionPajakan.value = "papar";
		document.${formName}.actionPajakan.value = "paparmjm";
		document.${formName}.submit();	
	//	doAjaxCall${formName}("");
	}
	
	function HapusKJP(idUlasanKJP){
		document.${formName}.mode.value = "view";
		//document.${formName}.actionPajakan.value = "papar";
		document.${formName}.actionPajakan.value = "paparmjm";
		document.${formName}.hitButton.value = "hapusKJP";
			//fir test 30032010
		document.${formName}.idUlasanKJP.value = idUlasanKJP;
		if ( !window.confirm("Adakah Anda Pasti ?") ){
			//document.${formName}.actionPajakan.value = "papar";
			document.${formName}.actionPajakan.value = "paparmjm";
			return;
		}
		document.${formName}.submit();	
	//	doAjaxCall${formName}("");
	}

	function HapusKJP(){
		document.${formName}.mode.value = "view";
		//document.${formName}.actionPajakan.value = "papar";
		document.${formName}.actionPajakan.value = "paparmjm";
		document.${formName}.hitButton.value = "hapusKJP";
		if ( !window.confirm("Adakah Anda Pasti ?") ){
			document.${formName}.actionPajakan.value = "papar";
			return;
		}
		document.${formName}.submit();	
	//	doAjaxCall${formName}("");
	}	
	
	function paparKJP(idUlasanKJP){
		document.${formName}.idUlasanKJP.value = idUlasanKJP;
		document.${formName}.mode.value = "viewKJP";
		//document.${formName}.actionPajakan.value = "papar";
		document.${formName}.actionPajakan.value = "paparmjm";
		document.${formName}.submit();	
	//	doAjaxCall${formName}("");
	}
	*/
	//X ULASAN JPPH - index.jsp
	function daftarBaruJPPH_(){
		document.${formName}.actionPajakan.value = "paparmjm";
		document.${formName}.mode.value = "newJPPH";
		document.${formName}.submit();	
		doAjaxCall${formName}("");
	}
	
	function SimpanJPPH_(){
		/*	
		if(document.${formName}.txtNoRujJPPH.value == ""){
			alert('Sila masukkan No Ruj. JPPH');
	  		document.${formName}.txtNoRujJPPH.focus(); 
			return; 
		}	*/
		if(document.${formName}.txdTarikhHantarJPPH.value == ""){
			alert('Sila masukkan Tarikh Hantar');
	  		document.${formName}.txdTarikhHantarJPPH.focus(); 
			return; 
		} /*
		if(document.${formName}.txdTarikhTerimaJPPH.value == ""){
			alert('Sila masukkan Tarikh Terima');
	  		document.${formName}.txdTarikhTerimaJPPH.focus(); 
			return; 
		}*/
		if(document.${formName}.txtTahunNilaian.value == ""){
			alert('Sila masukkan Tahun Nilaian.');
	  		document.${formName}.txtTahunNilaian.focus(); 
			return; 
		}
		/*
		if(document.${formName}.txtNilaiTanah.value == ""){
			alert('Sila masukkan Nilaian Tanah.');
	  		document.${formName}.txtNilaiTanah.focus(); 
			return; 
		}
		if(document.${formName}.txtSyorBayaran.value == ""){
			alert('Sila masukkan Syor Bayaran.');
	  		document.${formName}.txtSyorBayaran.focus(); 
			return; 
		}
		*/
		
		var StarikhHantarJPPH = document.getElementById("txdTarikhHantarJPPH").value;
		
		var today = new Date();
		
		//for StarikhHantarJPPH
		var STHDate  = parseInt(StarikhHantarJPPH.substring(0,2),10);
	    var STHmon = parseInt(StarikhHantarJPPH.substring(3,5),10)-1;
	    var STHyr  = parseInt(StarikhHantarJPPH.substring(6,10),10); 
		
		var tarikhHantarJPPH = new Date(STHyr,STHmon,STHDate);
	
		if(tarikhHantarJPPH > today){
			alert('Tarikh Hantar mestilah tidak melebihi dari Hari ini.');
	  		document.${formName}.txdTarikhHantarJPPH.focus(); 
			return; 
		}
		
		if(document.${formName}.txdTarikhTerimaJPPH.value != ""){
			//for StarikhTerimaJPPH
			var StarikhTerimaJPPH = document.getElementById("txdTarikhTerimaJPPH").value;
				
			var STTDate  = parseInt(StarikhTerimaJPPH.substring(0,2),10);
	    	var STTmon = parseInt(StarikhTerimaJPPH.substring(3,5),10)-1;
	    	var STTyr  = parseInt(StarikhTerimaJPPH.substring(6,10),10); 
		
			var tarikhTerimaJPPH = new Date(STTyr,STTmon,STTDate);
		
			if(tarikhTerimaJPPH > today){
				alert('Tarikh Terima mestilah tidak melebihi dari Hari ini.');
		  		document.${formName}.txdTarikhTerimaJPPH.focus(); 
				return; 
			}
		
		}
		
		if ( !window.confirm("Adakah Anda Pasti ?") ){
			//document.${formName}.actionPajakan.value = "papar";
			document.${formName}.actionPajakan.value = "paparmjm";
			return;
		}
	
		document.${formName}.mode.value = "view";
		document.${formName}.hitButton.value = "saveJPPH";
		document.${formName}.submit();	
	//	doAjaxCall${formName}("");
	}
	
	function batalJPPH_(){
		document.${formName}.mode.value = "view";
		//document.${formName}.actionPajakan.value = "papar";
		document.${formName}.actionPajakan.value = "paparmjm";
		doAjaxCall${formName}("");
	}
	
	function KemaskiniJPPH_(){
		document.${formName}.actionPajakan.value = "paparmjm";
		document.${formName}.mode.value = "updateJPPH";
		document.${formName}.submit();	
	//	doAjaxCall${formName}("");
	}
	
	function SimpanUpdateJPPH_(){
		/*
		if(document.${formName}.txtNoRujJPPH.value == ""){
			alert('Sila masukkan No Ruj. JPPH');
	  		document.${formName}.txtNoRujJPPH.focus(); 
			return; 
		}*/
		if(document.${formName}.txdTarikhHantarJPPH.value == ""){
			alert('Sila masukkan Tarikh Hantar');
	  		document.${formName}.txdTarikhHantarJPPH.focus(); 
			return; 
		} /*
		if(document.${formName}.txdTarikhTerimaJPPH.value == ""){
			alert('Sila masukkan Tarikh Terima');
	  		document.${formName}.txdTarikhTerimaJPPH.focus(); 
			return; 
		}*/
		if(document.${formName}.txtTahunNilaian.value == ""){
			alert('Sila masukkan Tahun Nilaian.');
	  		document.${formName}.txtTahunNilaian.focus(); 
			return; 
		}
		/*
		if(document.${formName}.txtNilaiTanah.value == ""){
			alert('Sila masukkan Nilaian Tanah.');
	  		document.${formName}.txtNilaiTanah.focus(); 
			return; 
		}
		if(document.${formName}.txtSyorBayaran.value == ""){
			alert('Sila masukkan Syor Bayaran.');
	  		document.${formName}.txtSyorBayaran.focus(); 
			return; 
		}*/
		
		var StarikhHantarJPPH = document.getElementById("txdTarikhTerimaJPPH").value;
		
		var today = new Date();
		
		//for StarikhHantarJPPH
		var STHDate  = parseInt(StarikhHantarJPPH.substring(0,2),10);
	    var STHmon = parseInt(StarikhHantarJPPH.substring(3,5),10)-1;
	    var STHyr  = parseInt(StarikhHantarJPPH.substring(6,10),10); 
		
		var tarikhHantarJPPH = new Date(STHyr,STHmon,STHDate);
	
		if(tarikhHantarJPPH > today){
			alert('Tarikh Hantar mestilah tidak melebihi dari Hari ini.');
	  		document.${formName}.txdTarikhHantarJPPH.focus(); 
			return; 
		}
		
		if(document.${formName}.txdTarikhTerimaJPPH.value != ""){
			//for StarikhTerimaJPPH
			var StarikhTerimaJPPH = document.getElementById("txdTarikhTerimaJPPH").value;
				
			var STTDate  = parseInt(StarikhTerimaJPPH.substring(0,2),10);
	    	var STTmon = parseInt(StarikhTerimaJPPH.substring(3,5),10)-1;
	    	var STTyr  = parseInt(StarikhTerimaJPPH.substring(6,10),10); 
		
			var tarikhTerimaJPPH = new Date(STTyr,STTmon,STTDate);
		
			if(tarikhTerimaJPPH > today){
				alert('Tarikh Terima mestilah tidak melebihi dari Hari ini.');
		  		document.${formName}.txdTarikhTerimaJPPH.focus(); 
				return; 
			}
		
		}
		
		if ( !window.confirm("Adakah Anda Pasti ?") ){
			//document.${formName}.actionPajakan.value = "papar";
			document.${formName}.actionPajakan.value = "paparmjm";
			return;
		}
	
		document.${formName}.mode.value = "viewJPPH";
		document.${formName}.hitButton.value = "saveUpdateJPPH";
			//fir test 30032010
		document.${formName}.submit();	
	//	doAjaxCall${formName}("");
	}
	
	function batalUpdateJPPH_(){
		document.${formName}.mode.value = "viewJPPH";
		document.${formName}.actionPajakan.value = "paparmjm";
		document.${formName}.submit();	
	//	doAjaxCall${formName}("");
	}
	
	function paparJPPH_(idUlasanTeknikal,idUlasanNilai){
		document.${formName}.idUlasanTeknikal.value = idUlasanTeknikal;
		document.${formName}.idUlasanNilai.value = idUlasanNilai;
		document.${formName}.mode.value = "viewJPPH";
		//document.${formName}.actionPajakan.value = "papar";
		document.${formName}.actionPajakan.value = "paparmjm";
		document.${formName}.submit();	
	//	doAjaxCall${formName}("");
	}
	
	function HapusJPPH_(){
		document.${formName}.mode.value = "view";
		//document.${formName}.actionPajakan.value = "papar";
		document.${formName}.actionPajakan.value = "paparmjm";
		document.${formName}.hitButton.value = "hapusJPPH";
		document.${formName}.submit();	
	//	doAjaxCall${formName}("");
	}
	
	//ULASAN SPHP
	function daftarBaruSPHP_(){
		//document.${formName}.actionPajakan.value = "papar";
		document.${formName}.actionPajakan.value = "paparmjm";
		document.${formName}.mode.value = "newSPHP";
		document.${formName}.submit();	
	//	doAjaxCall${formName}("");
	}
	
	function KemaskiniUlasanSPHP_(){
		//document.${formName}.actionPajakan.value = "papar";
		document.${formName}.actionPajakan.value = "paparmjm";
		document.${formName}.mode.value = "updateSPHP";
		document.${formName}.submit();	
	//	doAjaxCall${formName}("");
	}
	
	function batalUlasanSPHP_(){
		//document.${formName}.actionPajakan.value = "papar";
		document.${formName}.actionPajakan.value = "paparmjm";
		document.${formName}.mode.value = "view";
		document.${formName}.submit();	
	//	doAjaxCall${formName}("");
	}
	
	function SimpanUlasanSPHP_(){
		/*
		if(document.${formName}.txtNoRujukanSPHP.value == ""){
			alert('Sila masukkan No.Rujukan SPHP.');
	  		document.${formName}.txtNoRujukanSPHP.focus(); 
			return; 
		} */
		if(document.${formName}.txdTarikhHantarSPHP.value == ""){
			alert('Sila masukkan Tarikh Hantar.');
	  		document.${formName}.txdTarikhHantarSPHP.focus(); 
			return; 
		}
		/*if(document.${formName}.txdTarikhTerimaSPHP.value == ""){
			alert('Sila masukkan Tarikh Terima.');
	  		document.${formName}.txdTarikhTerimaSPHP.focus(); 
			return; 
		}*/
		
		var StarikhHantarSPHP = document.getElementById("txdTarikhHantarSPHP").value;
		
		var today = new Date();
		
		//for StarikhHantarSPHP
		var STHDate  = parseInt(StarikhHantarSPHP.substring(0,2),10);
	    var STHmon = parseInt(StarikhHantarSPHP.substring(3,5),10)-1;
	    var STHyr  = parseInt(StarikhHantarSPHP.substring(6,10),10); 		
	
		var tarikhHantarSPHP = new Date(STHyr,STHmon,STHDate);
	
		if(tarikhHantarSPHP > today){
			alert('Tarikh Hantar mestilah tidak melebihi dari Hari ini.');
	  		document.${formName}.txdTarikhHantarSPHP.focus(); 
			return; 
		}

		
		if(document.${formName}.txdTarikhTerimaSPHP.value == ""){
			var StarikhTerimaSPHP = document.getElementById("txdTarikhTerimaSPHP").value;
		    
		    //for StarikhTerimaSPHP
			var STTDate  = parseInt(StarikhTerimaSPHP.substring(0,2),10);
		    var STTmon = parseInt(StarikhTerimaSPHP.substring(3,5),10)-1;
		    var STTyr  = parseInt(StarikhTerimaSPHP.substring(6,10),10); 			
		
			var tarikhTerimaSPHP = new Date(STTyr,STTmon,STTDate);
			
			if(tarikhTerimaSPHP > today){
				alert('Tarikh Terima mestilah tidak melebihi dari Hari ini.');
		  		document.${formName}.txdTarikhTerimaSPHP.focus(); 
				return; 
			}
		}
		if ( !window.confirm("Adakah Anda Pasti ?") ){
			//document.${formName}.actionPajakan.value = "papar";
			document.${formName}.actionPajakan.value = "paparmjm";
			return;
		}
		
		document.${formName}.mode.value = "view";
		document.${formName}.hitButton.value = "saveUlasanSPHP";
			//fir test 30032010
		document.${formName}.submit();	
	//	doAjaxCall${formName}("");
	}
	
	function SimpanUpdateSPHP_(){
		/*
		if(document.${formName}.txtNoRujukanSPHP.value == ""){
			alert('Sila masukkan No.Rujukan SPHP.');
	  		document.${formName}.txtNoRujukanSPHP.focus(); 
			return; 
		}*/
		if(document.${formName}.txdTarikhHantarSPHP.value == ""){
			alert('Sila masukkan Tarikh Hantar.');
	  		document.${formName}.txdTarikhHantarSPHP.focus(); 
			return; 
		}/*
		if(document.${formName}.txdTarikhTerimaSPHP.value == ""){
			alert('Sila masukkan Tarikh Terima.');
	  		document.${formName}.txdTarikhTerimaSPHP.focus(); 
			return; 
		} */
		
		var StarikhHantarSPHP = document.getElementById("txdTarikhHantarSPHP").value;
		
		var today = new Date();
		
		//for StarikhHantarSPHP
		var STHDate  = parseInt(StarikhHantarSPHP.substring(0,2),10);
	    var STHmon = parseInt(StarikhHantarSPHP.substring(3,5),10)-1;
	    var STHyr  = parseInt(StarikhHantarSPHP.substring(6,10),10); 
		
		var tarikhHantarSPHP = new Date(STHyr,STHmon,STHDate);
	
		if(tarikhHantarSPHP > today){
			alert('Tarikh Hantar mestilah tidak melebihi dari Hari ini.');
	  		document.${formName}.txdTarikhHantarSPHP.focus(); 
			return; 
		}
		
		
		if(document.${formName}.txdTarikhTerimaSPHP.value == ""){
			var StarikhTerimaSPHP = document.getElementById("txdTarikhTerimaSPHP").value;
		    
		    //for StarikhTerimaSPHP
			var STTDate  = parseInt(StarikhTerimaSPHP.substring(0,2),10);
		    var STTmon = parseInt(StarikhTerimaSPHP.substring(3,5),10)-1;
		    var STTyr  = parseInt(StarikhTerimaSPHP.substring(6,10),10); 			
		
			var tarikhTerimaSPHP = new Date(STTyr,STTmon,STTDate);
			
			if(tarikhTerimaSPHP > today){
				alert('Tarikh Terima mestilah tidak melebihi dari Hari ini.');
		  		document.${formName}.txdTarikhTerimaSPHP.focus(); 
				return; 
			}
		}
		
		
		if ( !window.confirm("Adakah Anda Pasti ?") ){
			//document.${formName}.actionPajakan.value = "papar";
			document.${formName}.actionPajakan.value = "paparmjm";
			return;
		}
		
		document.${formName}.mode.value = "viewSPHP";
		document.${formName}.hitButton.value = "saveUpdateSPHP";
		document.${formName}.submit();	
	//	doAjaxCall${formName}("");
	}
	
	function batalUpdateSPHP_(){
		document.${formName}.mode.value = "viewSPHP";
		//document.${formName}.actionPajakan.value = "papar";
		document.${formName}.actionPajakan.value = "paparmjm";
		document.${formName}.submit();	
	//	doAjaxCall${formName}("");
	}
	
	function HapusSPHP_(){
		document.${formName}.mode.value = "view";
		//document.${formName}.actionPajakan.value = "papar";
		document.${formName}.actionPajakan.value = "paparmjm";
		document.${formName}.hitButton.value = "hapusSPHP";
		document.${formName}.submit();	
	//	doAjaxCall${formName}("");
	}
	
	function paparSPHP_(idUlasanSPHP){
		document.${formName}.idUlasanSPHP.value = idUlasanSPHP;
		document.${formName}.mode.value = "viewSPHP";
		//document.${formName}.actionPajakan.value = "papar";
		document.${formName}.actionPajakan.value = "paparmjm";
		document.${formName}.submit();	
	//	doAjaxCall${formName}("");
	}
	
	//TAB DRAF PERJANJIAN I	
	function SDraf_(id){	
		var today = new Date();
		var StarikhTerimaDraf = document.getElementById("txdTarikhTerimaDraf").value;
		var StarikhHantarDraf = document.getElementById("txdTarikhHantarDraf").value;
		if(document.${formName}.txdTarikhHantarDraf.value == ""){
			alert('Sila masukkan maklumat Tarikh Hantar .');
  			document.${formName}.txdTarikhHantarDraf.focus(); 
			return; 
		}

		if(document.${formName}.txdTarikhTerimaDraf.value == ""){
			alert('Sila masukkan maklumat tarikh terima');
  			document.${formName}.StarikhTerimaDraf.focus(); 
			return; 
		}	
	
		//for StarikhTerimaDraf
		var STTDate  = parseInt(StarikhTerimaDraf.substring(0,2),10);
	    var STTmon = parseInt(StarikhTerimaDraf.substring(3,5),10)-1;
	    var STTyr  = parseInt(StarikhTerimaDraf.substring(6,10),10); 
		
		//for StarikhHantarDraf
		var STHDate  = parseInt(StarikhHantarDraf.substring(0,2),10);
	    var STHmon = parseInt(StarikhHantarDraf.substring(3,5),10)-1;
	    var STHyr  = parseInt(StarikhHantarDraf.substring(6,10),10); 
		
		var tarikhTerimaDraf = new Date(STTyr,STTmon,STTDate);
		var tarikhHantarDraf = new Date(STHyr,STHmon,STHDate);

		if(tarikhHantarDraf > today){
			alert('Tarikh Hantar mestilah tidak melebihi dari Hari ini.');
	  		document.${formName}.txdTarikhHantarDraf.focus(); 
			return; 
		}
	
		if(tarikhTerimaDraf > today){
			alert('Tarikh Terima mestilah tidak melebihi dari Hari ini.');
	  		document.${formName}.StarikhTerimaDraf.focus(); 
			return; 
		}
		if ( !window.confirm("Adakah Anda Pasti?") ) return;	
		
		var x = create_request_string(document.${formName});
		
		document.${formName}.mode.value = "view";
		//document.${formName}.actionPajakan.value = "papar";
		document.${formName}.actionPajakan.value = "paparmjm";
		document.${formName}.hitButton.value = "saveDraf";
		document.${formName}.method="post";
		document.${formName}.enctype="multipart/form-data";
		document.${formName}.encoding="multipart/form-data";
		//document.${formName}.action = "?_portal_module=ekptg.view.htp.pajakan.FrmPajakanMJMView&actionPajakan=papar&selectedTab=2&mode=view&hitButton=saveDraf&"+x;
		document.${formName}.submit();	
	
	}

	function batalDraf_(){
		document.${formName}.mode.value = "view";
		//document.${formName}.actionPajakan.value = "papar";
		document.${formName}.actionPajakan.value = "paparmjm";
		doAjaxCall${formName}("");
	}
		
	function paparDraf_(idDraf){
		document.${formName}.idDraf.value = idDraf;
		document.${formName}.mode.value = "viewDraf";
		//document.${formName}.actionPajakan.value = "papar";
		document.${formName}.actionPajakan.value = "paparmjm";
		doAjaxCall${formName}("");
		
	}

	function downloadPerjanjian_(idPermohonan,idDeraf){
		var url = "../servlet/ekptg.view.htp.pajakan.PajakanDisplayBlob?id="+idPermohonan+"&idderaf="+idDeraf;
	    var hWnd = window.open(url,'displayfile','width=800,height=600, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
	    hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus();
	    
	}	

	function KemaskiniDraf_(){
		//document.${formName}.actionPajakan.value = "papar";
		document.${formName}.actionPajakan.value = "paparmjm";
		document.${formName}.mode.value = "updateDraf";
		doAjaxCall${formName}("");
		
	}		

	function HapusDraf_(id){
		document.${formName}.idDraf.value = id;
		document.${formName}.mode.value = "view";
		//document.${formName}.actionPajakan.value = "papar";
		document.${formName}.actionPajakan.value = "paparmjm";
		document.${formName}.hitButton.value = "hapusDraf";
		doAjaxCall${formName}("");
	
	}
	
	function batalUpdateDraf_(){
		document.${formName}.mode.value = "viewDraf";
		//document.${formName}.actionPajakan.value = "papar";
		document.${formName}.actionPajakan.value = "paparmjm";
		doAjaxCall${formName}("");
	}	
	
	function SimpanUpdateDraf_(){		
		if(document.${formName}.txdTarikhHantarDraf.value == ""){
			alert('Sila masukkan Tarikh Hantar.');
	  		document.${formName}.txdTarikhHantarDraf.focus(); 
			return; 
		}
		if(document.${formName}.txdTarikhTerimaDraf.value == ""){
			alert('Sila masukkan Tarikh Terima.');
	  		document.${formName}.txdTarikhTerimaDraf.focus(); 
			return; 
		}
		
		var StarikhTerimaDraf = document.getElementById("txdTarikhTerimaDraf").value;
		var StarikhHantarDraf = document.getElementById("txdTarikhHantarDraf").value;
		
		var today = new Date();
		
		//for StarikhTerimaDraf
		var STTDate  = parseInt(StarikhTerimaDraf.substring(0,2),10);
	    var STTmon = parseInt(StarikhTerimaDraf.substring(3,5),10)-1;
	    var STTyr  = parseInt(StarikhTerimaDraf.substring(6,10),10); 
		
		//for StarikhHantarDraf
		var STHDate  = parseInt(StarikhHantarDraf.substring(0,2),10);
	    var STHmon = parseInt(StarikhHantarDraf.substring(3,5),10)-1;
	    var STHyr  = parseInt(StarikhHantarDraf.substring(6,10),10); 
		
		var tarikhTerimaDraf = new Date(STTyr,STTmon,STTDate);
		var tarikhHantarDraf = new Date(STHyr,STHmon,STHDate);
	
		if(tarikhHantarDraf > today){
			alert('Tarikh Hantar mestilah tidak melebihi dari Hari ini.');
	  		document.${formName}.txdTarikhHantarDraf.focus(); 
			return; 
		}
		
		if(tarikhTerimaDraf > today){
			alert('Tarikh Terima mestilah tidak melebihi dari Hari ini.');
	  		document.${formName}.txdTarikhTerimaDraf.focus(); 
			return; 
		}
	
		if ( !window.confirm("Adakah Anda Pasti ?") ){
			document.${formName}.actionPajakan.value = "papar";
			return;
		}
		//if ( !window.confirm("Maklumat akan dihapuskan. Adakah Anda Pasti?") ) return;	
		
		//document.${formName}.mode.value = "viewDraf";
		//document.${formName}.hitButton.value = "saveUpdateDraf";
		//doAjaxCall${formName}("");
		var x = create_request_string(document.${formName});
		
		document.${formName}.method="post";
		document.${formName}.enctype="multipart/form-data";
		document.${formName}.encoding="multipart/form-data";
		document.${formName}.action = "?_portal_module=ekptg.view.htp.pajakan.FrmPajakanMJMView&actionPajakan=papar&selectedTab=2&mode=viewDraf&hitButton=saveUpdateDraf&"+x;
		document.${formName}.submit();	
		
	}
	
//TAB MEMORANDUM JEMAAH MENTERI

//MEMO JEMAAH MENTERI
	function hapusMJM(){
		//document.${formName}.actionPajakan.value = "papar";
		document.${formName}.actionPajakan.value = "paparmjm";
		document.${formName}.mode.value = "view";
		document.${formName}.hitButton.value = "hapusmemo";
		
		if ( !window.confirm("Adakah Anda Pasti ?") ){
			return;
		}
		doAjaxCall${formName}("");
		
	}
	/** 2017/05/17
	mjm/index.jsp
	function KemaskiniMemo(){
		//document.${formName}.actionPajakan.value = "papar";
		document.${formName}.actionPajakan.value = "paparmjm";
		document.${formName}.mode.value = "update";
		//document.${formName}.submit();	
		doAjaxCall${formName}("");
	} */
	
	function batalMemo(){
		//document.${formName}.actionPajakan.value = "papar";
		document.${formName}.actionPajakan.value = "paparmjm";
		document.${formName}.mode.value = "view";
		//document.${formName}.submit();	
		doAjaxCall${formName}("");
	}
	
	/** 2017/05/17
	function SimpanMemo(){
		if(document.${formName}.subUrusan.value != "7"
			&& document.${formName}.subUrusan.value != "17"
			&& document.${formName}.subUrusan.value != "87"){
			alert('Sila kemaskini "Sub Urusan" terlebih dahulu.');
			return; 
			
		}	*/
		/*if(document.${formName}.txdTHPTP.value == ""){
			alert('Sila masukkan Tarikh Hantar PTP.');
	  		document.${formName}.txdTHPTP.focus(); 
			return; 
		}
		if(document.${formName}.txdTTPTP.value == ""){
			alert('Sila masukkan Tarikh Terima PTP.');
	  		document.${formName}.txdTTPTP.focus(); 
			return; 
		}
		if(document.${formName}.txdTHTUP.value == ""){
			alert('Sila masukkan Tarikh Hantar TUP');
	  		document.${formName}.txdTHTUP.focus(); 
			return; 
		}*/
		/** 2017/05/17
		if(document.${formName}.txdTMJM.value == ""){
			alert('Sila masukkan Tarikh Mesyuarat Jemaah Menteri.');
	  		document.${formName}.txdTMJM.focus(); 
			return; 
		}
		if(document.${formName}.txdTTK.value == ""){
			alert('Sila masukkan Tarikh Terima Keputusan.');
	  		document.${formName}.txdTTK.focus(); 
			return; 
		}
		if(document.${formName}.txtNoMemorandum.value == ""){
			alert('Sila masukkan No. Memorandum.');
	  		document.${formName}.txtNoMemorandum.focus(); 
			return; 
		}
		if(document.${formName}.txtKeputusan.value == ""){
			alert('Sila masukkan Keputusan.');
	  		document.${formName}.txtKeputusan.focus(); 
			return; 
		}
		if(document.${formName}.txtKeterangan.value == ""){
			alert('Sila masukkan Keterangan Kelulusan.');
	  		document.${formName}.txtKeterangan.focus(); 
			return; 
		}
		
		var StarikhTerimaPTP = document.getElementById("txdTTPTP").value;
		var StarikhHantarPTP = document.getElementById("txdTHPTP").value;
		var StarikhHantarTUP = document.getElementById("txdTHTUP").value;
		var StarikhMesyuaratJM = document.getElementById("txdTMJM").value;
		var StarikhTerimaKeputusan = document.getElementById("txdTTK").value;
		
		var today = new Date();
		
		//for StarikhTerimaPTP
		var STTDate  = parseInt(StarikhTerimaPTP.substring(0,2),10);
	    var STTmon = parseInt(StarikhTerimaPTP.substring(3,5),10)-1;
	    var STTyr  = parseInt(StarikhTerimaPTP.substring(6,10),10); 
		
		//for StarikhHantarPTP
		var STHDate  = parseInt(StarikhHantarPTP.substring(0,2),10);
	    var STHmon = parseInt(StarikhHantarPTP.substring(3,5),10)-1;
	    var STHyr  = parseInt(StarikhHantarPTP.substring(6,10),10); 
		
		//for StarikhHantarTUP
		var STTUPDate  = parseInt(StarikhHantarTUP.substring(0,2),10);
	    var STTUPmon = parseInt(StarikhHantarTUP.substring(3,5),10)-1;
	    var STTUPyr  = parseInt(StarikhHantarTUP.substring(6,10),10); 
		
		//for StarikhMesyuaratJM
		var STMJMDate  = parseInt(StarikhMesyuaratJM.substring(0,2),10);
	    var STMJMmon = parseInt(StarikhMesyuaratJM.substring(3,5),10)-1;
	    var STMJMyr  = parseInt(StarikhMesyuaratJM.substring(6,10),10); 
		
		//for StarikhTerimaKeputusan
		var STTKDate  = parseInt(StarikhTerimaKeputusan.substring(0,2),10);
	    var STTKmon = parseInt(StarikhTerimaKeputusan.substring(3,5),10)-1;
	    var STTKyr  = parseInt(StarikhTerimaKeputusan.substring(6,10),10); 
		
		
		var tarikhTerimaPTP = new Date(STTyr,STTmon,STTDate);
		var tarikhHantarPTP = new Date(STHyr,STHmon,STHDate);
		var tarikhHantarTUP = new Date(STTUPyr,STTUPmon,STTUPDate);
		var tarikhMesyuaratJM = new Date(STMJMyr,STMJMmon,STMJMDate);
		var tarikhTerimaKeputusan = new Date(STTKyr,STTKmon,STTKDate);
	
		if(tarikhHantarPTP > today){
			alert('Tarikh Hantar PTP mestilah tidak melebihi dari Hari ini.');
	  		document.${formName}.txdTHPTP.focus(); 
			return; 
		}
		
		if(tarikhTerimaPTP > today){
			alert('Tarikh Terima PTP mestilah tidak melebihi dari Hari ini.');
	  		document.${formName}.txdTTPTP.focus(); 
			return; 
		}
		if(tarikhHantarTUP > today){
			alert('Tarikh Hantar TUP mestilah tidak melebihi dari Hari ini.');
	  		document.${formName}.txdTHTUP.focus(); 
			return; 
		}
		if(tarikhMesyuaratJM > today){
			alert('Tarikh Mesuarat Jemaah Menteri mestilah tidak melebihi dari Hari ini.');
	  		document.${formName}.txdTMJM.focus(); 
			return; 
		}
		if(tarikhTerimaKeputusan > today){
			alert('Tarikh Terima Keputusan mestilah tidak melebihi dari Hari ini.');
	  		document.${formName}.txdTTK.focus(); 
			return; 
		}
	
		if ( !window.confirm("Adakah Anda Pasti ?") ){
			//document.${formName}.actionPajakan.value = "papar";
			document.${formName}.actionPajakan.value = "paparmjm";
			return;
		}
				
		//document.${formName}.mode.value = "view";
		//document.${formName}.hitButton.value = "saveMemo";
		//document.${formName}.method="post";
		//document.${formName}.enctype="multipart/form-data";
	    //document.${formName}.encoding="multipart/form-data";
		//document.${formName}.submit();	
	//	doAjaxCall${formName}("");
	
		var x = create_request_string(document.${formName});
		document.${formName}.method="post";
		document.${formName}.enctype="multipart/form-data";
		document.${formName}.encoding="multipart/form-data";
		document.${formName}.action = "?_portal_module=ekptg.view.htp.pajakan.FrmPajakanMJMView&actionPajakan=papar&selectedTab=3&mode=view&hitButton=saveMemo&"+x;
		document.${formName}.submit();
	
	}
	*/


//LAIN2
/* 	
 * 2017/05/22
 *function doChangeNegeri(){
		doAjaxCall${formName}('doChangeNegeri');
	} */
	
	
	function seterusnya(){
		document.${formName}.actionPajakan.value = "";
		document.${formName}.hitButton.value = "seterusnyamjm";
		document.${formName}.submit();	
		//doAjaxCall${formName}("");
	}
	
	function SimpanDraf(){
		
		if(document.${formName}.txdTarikhHantarDraf.value == ""){
			alert('Sila masukkan Tarikh Hantar.');
	  		document.${formName}.txdTarikhHantarDraf.focus(); 
			return; 
		}
		if(document.${formName}.txdTarikhTerimaDraf.value == ""){
			alert('Sila masukkan Tarikh Terima.');
	  		document.${formName}.txdTarikhTerimaDraf.focus(); 
			return; 
		}
		
		var StarikhTerimaDraf = document.getElementById("txdTarikhTerimaDraf").value;
		var StarikhHantarDraf = document.getElementById("txdTarikhHantarDraf").value;
		
		var today = new Date();
		
		//for StarikhTerimaDraf
		var STTDate  = parseInt(StarikhTerimaDraf.substring(0,2),10);
	    var STTmon = parseInt(StarikhTerimaDraf.substring(3,5),10)-1;
	    var STTyr  = parseInt(StarikhTerimaDraf.substring(6,10),10); 
		
		//for StarikhHantarDraf
		var STHDate  = parseInt(StarikhHantarDraf.substring(0,2),10);
	    var STHmon = parseInt(StarikhHantarDraf.substring(3,5),10)-1;
	    var STHyr  = parseInt(StarikhHantarDraf.substring(6,10),10); 
		
		var tarikhTerimaDraf = new Date(STTyr,STTmon,STTDate);
		var tarikhHantarDraf = new Date(STHyr,STHmon,STHDate);
	
		if(tarikhHantarDraf > today){
			alert('Tarikh Hantar mestilah tidak melebihi dari Hari ini.');
	  		document.${formName}.txdTarikhHantarDraf.focus(); 
			return; 
		}
		
		if(tarikhTerimaDraf > today){
			alert('Tarikh Terima mestilah tidak melebihi dari Hari ini.');
	  		document.${formName}.txdTarikhTerimaDraf.focus(); 
			return; 
		}
		
		if ( !window.confirm("Adakah Anda Pasti ?") ){
			//document.${formName}.actionPajakan.value = "papar";
			document.${formName}.actionPajakan.value = "paparmjm";
			return;
		}
		
		document.${formName}.mode.value = "view";
		document.${formName}.hitButton.value = "saveDraf";
		//document.getElementById("hitButton").value = "saveDraf"
		//alert(hitButton);
		
		//var idFail = document.getElementById("idFail").value;
		//var idStatus = document.getElementById("idStatus").value;
		//var selectedTab = document.getElementById("selectedTab").value;
		//var selectedTabLower = document.getElementById("selectedTabLower").value;
		//var idPermohonan = document.getElementById("idPermohonan").value;
		
		//document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmPajakanMemorandumJemaahMenteriView&idFail="+idFail+"&hitButton=saveDraf&idStatus="+idStatus+"&selectedTab="+selectedTab+"&selectedTabLower="+selectedTabLower+"&idPermohonan="+idPermohonan+"&actionPajakan=papar";
		document.${formName}.method="post";
		document.${formName}.enctype = "multipart/form-data";
		//document.${formName}.encoding = "multipart/form-data";
		//fir test 30032010
		document.${formName}.submit();	
		//doAjaxCall${formName}("");
	}


	function validateCurrency(elmnt,content,content2) {
		//if it is character, then remove it..
		if (isNaN(content)) {
			elmnt.value = content2;
			return;
		}
	
		if(content != ""){
			var num = content * 1;
			elmnt.value = num.toFixed(2);
			return;
		} else {
			elmnt.value ="";
			return;
		}
	}
	
/** 2017/05/22
	function doChangeTabLower(tabId) {
		document.${formName}.selectedTabLower.value = tabId;
		document.${formName}.mode.value = "view";
		doAjaxCall${formName}("");
	}	*/
	
	// 2010/06/04 -Pilih Pegawai untuk tandatangan surat
	function senaraiDokumenSPHP(id){
		if(document.getElementById(id).style.display=="none"){
			document.getElementById(id).style.display="block";
		}
		else if(document.getElementById(id).style.display=="block"){
			document.getElementById(id).style.display="none";
		}
	}
	
	// 2010/06/04 -Pilih Pegawai untuk tandatangan surat
	function openSuratPegawai(urli,param,laporan,tem){
		var url = "../x/${securityToken}/ekptg.view.htp.FrmSenaraiSuratHTP?command=pegawai&urli="+urli+"&"+param+tem+"&flagReport=S";
	    var hWnd = window.open(url,'Cetak','status=1,width=800,height=500, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus();	
	}
/* 2017/03/30	
function langkah1(){
	document.${formName}.action = "?_portal_module=ekptg.view.htp.pajakan.FrmPajakanSenaraiFailView";
	document.${formName}.mode.value = "view";
	document.${formName}.submit();
}

function langkah2(permohonan,idFail){
	url = "../servlet/ekptg.view.htp.FrmPajakanServlet?command=papar&idFail="+idFail;
	actionName = "setSessionIdFail";
	target = "setSessionIdFail_result";
	doAjaxUpdater(document.${formName}, url, target, actionName);
	document.${formName}.action = "?_portal_module=ekptg.view.htp.pajakan.FrmPajakanPendaftaranView&idPermohonan="+permohonan+"&actionPajakan=papar";
	document.${formName}.mode.value = "view";
	document.${formName}.submit();
}

	function langkah4(permohonan,idFail){
		url = "../servlet/ekptg.view.htp.FrmPajakanServlet?command=papar&idFail="+idFail;
		actionName = "setSessionIdFail";
		target = "setSessionIdFail_result";
		doAjaxUpdater(document.${formName}, url, target, actionName);
		//document.${formName}.action = "?_portal_module=ekptg.view.htp.pajakan.FrmPajakanPerjanjianView&idPermohonan="+permohonan+"&actionPajakan=papar";
		document.${formName}.actionPajakan.value = "paparperjanjian";
		document.${formName}.mode.value = "view";
		document.${formName}.submit();
		
	}

function langkah5(permohonan,idFail){
	url = "../servlet/ekptg.view.htp.FrmPajakanServlet?command=papar&idFail="+idFail;
	actionName = "setSessionIdFail";
	target = "setSessionIdFail_result";
	doAjaxUpdater(document.${formName}, url, target, actionName);
	document.${formName}.action = "?_portal_module=ekptg.view.htp.pajakan.FrmPajakanBayaranView&idPermohonan="+permohonan+"&actionPajakan=BayaranPajakan";
	document.${formName}.mode.value = "view";
	document.${formName}.submit();
}
*/ /** 2017/05/22
	function checkEmail(str) {	
			var at="@"
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
			    alert("Sila Masukan Email Dengan Betul.")
			    return false
			 }
	
			 if (str.substring(lat-1,lat)==dot || str.substring(lat+1,lat+2)==dot){
			    alert("Sila Masukan Email Dengan Betul.")
			    return false
			 }
	
			 if (str.indexOf(dot,(lat+2))==-1){
			    alert("Sila Masukan Email Dengan Betul.")
			    return false
			 }
			
			 if (str.indexOf(" ")!=-1){
			    alert("Sila Masukan Email Dengan Betul.")
			    return false
			 }
		}
	 		 return true					
	}
	*/
	function cetakPemajak(idpermohonan) {	
	    var url = "../servlet/ekptg.report.htp.NoFailTajukFail?template=MaklumatPemajak&idpermohonan="+idpermohonan;
	    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus();
	
	
	}
	
	function doChangeJumlahLampiran(tabId,j,a) {
		if (j.value < 1) {
			alert("Sila masukkan nombor yang sah");
			j.value = 1;
			return;
		}
		document.${formName}.action.value = "changelampiran";
		document.${formName}.actionPajakan.value = "papar";
		//document.${formName}.mode.value = "view";
		doAjaxCall${formName}("");
		
		//document.${formName}.submit();
	 	//doAjaxCall${formName}("imej","firstAction=PendaftaranImej&X="+a+"&action=changeLampiran&IDJadualLampiran="+IDJadualLampiran);
	
	}
	
	//mjm/index.jsp
	function cetakImej_mjm_index(id){
	    var url = "../servlet/ekptg.view.pfd.DisplayBlob?id="+id;
	    var hWnd=window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes,menubar=1');
	    if ((document.window != null) && (!hWnd.opener))
		hWnd.opener=document.window;
	    if (hWnd.focus != null) hWnd.focus();
	}
	
	//mjm/index.jsp
	function deleteDetailImej_mjm_index(id,id1){
		if ( !window.confirm("Adakah Anda Pasti?")) return;
		document.${formName}.command.value = "";
		document.${formName}.actionPajakan.value = "papar";
		document.${formName}.mode.value = "view";
		document.${formName}.hitButton.value = "hapuslampiran";
		
		//document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPendaftaranTanah&firstAction=PendaftaranImej&nextAction=deleteDetailImej&idGambar="+id;
		document.${formName}.action = "?_portal_module=ekptg.view.htp.pajakan.FrmPajakanMJMView&idLampiran="+id1+"&iddokumen="+id;
		document.${formName}.submit();
		//doAjaxCall${formName}("lampiran","&nextAction=hapus&idLampiran="+id1+"&iddokumen="+id);
		
	}
	
	// 2017/05/22 function cetakSuratUlasanKJP(idpermohonan){
		/*var url = "../servlet/ekptg.report.htp.SuratKepadaKJPPajakan?idpermohonan="+idpermohonan;
	    var hWnd = window.open(url,'Cetak','full=yes, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
	   	hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus();*/
	    
		/*var url = "../x/${securityToken}/ekptg.report.htp.utiliti.FrmPopupPilihPegawaiReportView?idpermohonan="+idpermohonan+"&report=SuratKepadaKJPPajakan&selectNoFail=no";	
	    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus();
	    
	}*/
	// 2017/05/22 function cetakSuratUlasanJPPH(idpermohonan){
		/*var url = "../servlet/ekptg.report.htp.SuratKepadaKJPPH?idpermohonan="+idpermohonan;
	    var hWnd = window.open(url,'Cetak','full=yes, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
	   	hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus();*/
	    
		/*var url = "../x/${securityToken}/ekptg.report.htp.utiliti.FrmPopupPilihPegawaiReportView?idpermohonan="+idpermohonan+"&report=SuratKepadaKJPPH&selectNoFail=no";	
	    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus();
	    
	}*/

</script>

<script type="text/javascript">
	// kmie, 23112011
	function sendJPPH(ID_PERMOHONAN) {
		document.${formName}.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.integrasi.FrmJPPHViewNilaianPajakan&action2=view&ID_PERMOHONAN=$idPermohonan";
		document.${formName}.method = "POST";
		document.${formName}.submit();
	}
	
	function paparJPPHOnline(ID_PERMOHONAN) {
		document.${formName}.action = "$EkptgUtil.getTabID("Utiliti",$portal_role)?_portal_module=ekptg.view.integrasi.FrmJPPHViewNilaianPajakan&action2=view&ID_PERMOHONAN=$idPermohonan";
		document.${formName}.method = "POST";
		document.${formName}.submit();
	}
</script>
<!-- Diguna selepas Upload Draf Perjanjian -->
#parse("app/htp/utiliti/javascript/javaScriptPajakanTindakan.jsp")
#parse("app/htp/utiliti/javascript/javaScriptPajakanPenamatan.jsp")
#parse("app/htp/utiliti/javascript/javaScriptPajakanBayaran.jsp")
#parse("app/htp/utiliti/javascript/javaScriptPajakanPerjanjian.jsp")
#parse("app/htp/utiliti/javascript/javaScriptPajakanMJM.jsp")
#parse("app/htp/utiliti/javascript/javaScriptPajakanPermohonan.jsp")
#parse("app/htp/utiliti/javascript/javaScriptPajakanPaging.jsp")
#parse("app/htp/utiliti/javascript/javaScriptPajakanDaftar.jsp")
#parse("app/htp/utiliti/javascript/javaScriptPajakanIndex.jsp")
#parse("app/htp/utiliti/javascript/javaScriptPajakan.jsp")
#parse("app/htp/utiliti/javaScriptUmum.jsp")
