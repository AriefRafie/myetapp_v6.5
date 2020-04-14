<style type="text/css">
<!--
.style40 {color: #0000FF}
-->
</style>

<table width="100%" id="paging">
	
	<tr>
		<td width="100%">
			<div align="right">
	
	<!-- PENDAFTARAN HAKMLIK DAN RIZAB-->
	#if($jenis_button == "1")<img src="../img/1current.png" alt="" border="0" title="Senarai Hakmilik/Rizab"/>
	#else<img src="../img/1enable.png" alt="" border="0" title="Senarai Hakmilik/Rizab" onclick="screen1()" onMouseOver="this.style.cursor='pointer';"/>
	#end	
	<img src="../img/arrowgaris.png" alt="" border="0"/>
	
	#if($jenis_button == "2")<img src="../img/2current.png" alt="" border="0" title="Hakmilik/Rizab"/>
	#else<img src="../img/2enable.png" alt="" border="0" title="Hakmilik/Rizab" onclick="screen2('$idHakmilik','$!jenisTanah')" onMouseOver="this.style.cursor='pointer';"/>
	#end
	<img src="../img/arrowgaris.png" alt="" border="0"/>
	
	#if($jenis_button == "3")<img src="../img/3current.png" alt="" border="0" title="Pembangunan"/>
	#else<img src="../img/3enable.png" alt="" border="0" title="Pembangunan" onclick="screen3('$idHakmilik')" onMouseOver="this.style.cursor='pointer';"/>
	#end
	<img src="../img/arrowgaris.png" alt="" border="0"/><!-- PENDAFTARAN IMEJAN-->
	
	#if($jenis_button == "4")<img src="../img/4current2.png" alt="" border="0" title="Imejan"/>
	#else<img src="../img/4enable2.png" alt="" border="0" title="Imejan" onclick="screen4('$idHakmilik')" onMouseOver="this.style.cursor='pointer';"/>
	#end
	
	
	
	<!-- @author : Firzan
		 @comment : to cater hakmilik sambungan in Rekod hakmilik/rizab
	-->
	<!-- HAKMILIK SAMBUNGAN -->
	<!--
	#if($jenis_button == "5")
	<img src="../img/5current.png" alt="" border="0" title="Hakmilik Sambungan"/>
	#else
	<img src="../img/5enable.png" alt="" border="0" title="Hakmilik Sambungan" onclick="screen5('$idHakmilik')" onMouseOver="this.style.cursor='pointer';"/>
	#end
	-->
			</div>
		</td>
	</tr>

</table>
<input type="hidden" name="socNegeri"  value="$!cariIdNegeri" />
<input type="hidden" name="socDaerah"  value="$!cariIdDaerah" />
<input type="hidden" name="socMukim"  value="$!cariIdMukim" />
<input type="hidden" name="socJenisTanah" value="$!cariIdJenisTanah">
<input type="hidden" name="socStatus" value="$!cariIdStatus">

<script>
	
	function cari(){   
		field_ = document.${formName}.socJenisTanahtemp;
		for (i = 0; i < field_.length; i++){
			if(field_[i].checked==true){
				document.${formName}.socJenisTanah.value = field_[i].value;
			}
		}
		doAjaxCall${formName}("","firstAction=carianHakmilikRizab");
		
	}
	
	function XkosongCarian(idJenisTanah){
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
		//document.${formName}.action = "?_portal_module=ekptg.view.htp.rekod.FrmRekodPendaftaranTanah&firstAction=carianHakmilikRizab";
		//document.${formName}.submit();
	 	doAjaxCall${formName}("","firstAction=carianHakmilikRizab");
	 	
	}
	
	function kosongCarian(idJenisTanah){
		document.${formName}.flagAdvSearch.value = "N";
		document.${formName}.reset();
	 	doAjaxCall${formName}("","nextAction=reset");
	 	
	}
	
	function doChangeState() {
	  doAjaxCall${formName}("","firstAction=carianHakmilikRizab&nextAction=doChangeState");
	}
	
	function doChangeDaerah() {
		doAjaxCall${formName}("","firstAction=carianHakmilikRizab&nextAction=doChangeDaerah");
	}
	
	function doChangeMukim() {
		doAjaxCall${formName}("","firstAction=carianHakmilikRizab&nextAction=doChangeMukim");
	}
	
	function doChangeKementerian() {
		doAjaxCall${formName}("","firstAction=carianHakmilikRizab&nextAction=doChangeKementerian");
	}
	
	function deleteHakmilikBaru(id,id2){
		if ( !window.confirm("Adakah Anda Pasti?")) return;
			document.${formName}.command.value = "";
		
		doAjaxCall${formName}("","firstAction=deleteHakmilikBaru&idHakmilik="+id+"&idHakmilikBaru="+id2);
		
	}

	function doChangeLuasRizab(id_) { 
		
		//doAjaxCall${formName}("","firstAction=PendaftaranHakmilik&nextAction=kemaskiniDetailRizab&lastAction=doChange&idHakmilik=");			
	}

	function more(){
		document.${formName}.flagAdvSearch.value = "open";
		//document.${formName}.submit();
		//doAjaxCall${formName}("","flagAdvSearch=open");
		doAjaxCall${formName}("");
		
	}
	
	function less(){
		document.${formName}.flagAdvSearch.value = "";
		//document.${formName}.submit();
		doAjaxCall${formName}("");
		
	}
	
	//HAKMILIK
	//[link No. Hakmilik]	
	function hakmilik_detail(id,status){
		//document.${formName}.action = "?_portal_module=ekptg.view.htp.rekod.FrmRekodPendaftaranTanah&firstAction=PendaftaranHakmilik&nextAction=paparDetailHakmilik&idHakmilik="+id+"&statusSah="+status;
		//document.${formName}.submit();
		doAjaxCall${formName}("","firstAction=PendaftaranHakmilik&nextAction=paparDetailHakmilik&idHakmilik="+id+"&statusSah="+status);
	}
	
	function doChangeStateHR() {
	  doAjaxCall${formName}("","firstAction=PendaftaranHakmilik&nextAction=kemaskiniDetailHakmilik&lastAction=doChangeStateHR");
	}
	
	function doChangeDaerahHR() {
		doAjaxCall${formName}("","firstAction=PendaftaranHakmilik&nextAction=kemaskiniDetailHakmilik&lastAction=doChangeDaerahHR");
	}
	
	function doChangeMukimHR() {
		doAjaxCall${formName}("","firstAction=PendaftaranHakmilik&nextAction=kemaskiniDetailHakmilik&lastAction=doChangeMukimHR");
	}
	
	
	//[Kembali]
	function kembaliHakmilik(){
		//document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPendaftaranTanah&firstAction=carianHakmilikRizab";	
		//document.${formName}.submit();
		doAjaxCall${formName}("","firstAction=carianHakmilikRizab");
	}
	
	//[Kemaskini]
	function kemaskini_detailHakmilik(id){
		//document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPendaftaranTanah&firstAction=PendaftaranHakmilik&nextAction=kemaskiniDetailHakmilik&idHakmilik="+id;
		//document.${formName}.submit();
		doAjaxCall${formName}("","firstAction=PendaftaranHakmilik&nextAction=kemaskiniDetailHakmilik&idHakmilik="+id);
	}
	
	function updateKegunaanTanah(idHakmilik) {
		var url = "../x/${securityToken}/ekptg.view.htp.FrmPopupIntergrasiEtanahView?idHakmilik="+idHakmilik;
		var hWnd = window.open(url,'printuser','width=1000,height=500, resizable=yes,scrollbars=yes');
		if ((document.window != null) && (!hWnd.opener))
		   hWnd.opener = document.window;
		if (hWnd.focus != null) hWnd.focus();
		hWnd.focus();
	}
	
	//[Simpan]
	function update_detailHakmilik(id){
		
		//VALIDATAION
		 if(document.${formName}.socNegeriHR.value == 99999){ 
			alert('Sila masukkan " Negeri " terlebih dahulu.'); 
			document.${formName}.socNegeriHR.focus();
			return; 
		 }
		 
		 if(document.${formName}.socDaerahHR.value == ""){ 
			alert('Sila masukkan " Daerah " terlebih dahulu.'); 
			document.${formName}.socDaerahHR.focus();
			return; 
		 }
		 if(document.${formName}.socMukimHR.value == ""){ 
			alert('Sila masukkan " Bandar/Pekan/Mukim " terlebih dahulu.'); 
			document.${formName}.socMukimHR.focus();
			return; 
		 }
		 if(document.${formName}.socJenisHakmilikHR.value == ""){ 
			alert('Sila masukkan " Jenis Hakmilik " terlebih dahulu.'); 
			document.${formName}.socJenisHakmilikHR.focus();
			return; 
		 }
		 if(document.${formName}.txtNoHakmilik.value == ""){ 
				alert('Sila masukkan " Nombor Hakmilik " terlebih dahulu.'); 
				document.${formName}.txtNoHakmilik.focus();
				return; 
		 }
		 
		 if(document.${formName}.socLotHR.value == ""){ 
				alert('Sila masukkan " Kod LOT/PT " terlebih dahulu.'); 
				document.${formName}.socLotHR.focus();
				return; 
		 }
		 if(document.${formName}.txtNoLot.value == ""){ 
				alert('Sila masukkan " No LOT/PT " terlebih dahulu.'); 
				document.${formName}.txtNoLot.focus();
				return; 
		 }
		 if(document.${formName}.socTaraf.value == ""){ 
				alert('Sila masukkan " Taraf Hakmilik " terlebih dahulu.'); 
				document.${formName}.socTaraf.focus(); 
				return; 
		 }	
		 if(document.${formName}.socTaraf.value == "P" && document.${formName}.txtTempoh.value == ""){ 
				alert('Sila masukkan " Tempoh " terlebih dahulu.'); 
				document.${formName}.txtTempoh.focus();
				return; 
		 }
		 if(document.${formName}.txtCukaiTerkini.value == ""){ 
				alert('Sila masukkan " Tempoh " terlebih dahulu.'); 
				document.${formName}.txtCukaiTerkini.focus();
				return; 
		 }
		 
		 if(document.${formName}.socLuas.value == ""){ 
				alert('Sila masukkan " Jenis Luas " terlebih dahulu.'); 
				document.${formName}.socLuas.focus();
				return; 
	     }   
		 if(document.${formName}.txtLuas1.value == "" &&  document.${formName}.txtLuas2.value == "" && 
			document.${formName}.txtLuas3.value == "" &&  document.${formName}.txtLuas4.value == "" &&
			document.${formName}.txtLuas5.value == "" &&  document.${formName}.txtLuas6.value == ""){ 
				alert('Sila masukkan " Luas " terlebih dahulu.'); 
				//document.${formName}.txtLuas.focus();
				return; 
		 }
		 if(document.${formName}.socRizab.value == ""){ 
				alert('Sila masukkan " Rizab " terlebih dahulu.'); 
				document.${formName}.socRizab.focus();
				return; 
	     } 
		 if(document.${formName}.txdTarikhTerima.value == ""){ 
			alert('Sila masukkan " Tarikh Terima " terlebih dahulu.'); 
			document.${formName}.txdTarikhTerima.focus();
			return; 
		 }
		 
		 if(document.${formName}.txdTarikhDaftar.value == ""){ 
			alert('Sila masukkan " Tarikh Daftar " terlebih dahulu.'); 
			document.${formName}.txdTarikhDaftar.focus();
			return; 
		 }
		 
		 if(document.${formName}.txtCukaiTahun.value == ""){ 
			alert('Sila masukkan " Cukai Tahun Pertama " terlebih dahulu.'); 
			document.${formName}.txtCukaiTahun.focus();
			return; 
		 }
		 if(document.${formName}.txtLokasi.value == ""){ 
			alert('Sila masukkan " Lokasi " terlebih dahulu.'); 
			document.${formName}.txtLokasi.focus();
			return; 
		 }
		 if(document.${formName}.txtKegunaanTanah.value == ""){ 
			alert('Sila masukkan " Kegunaan Tanah " terlebih dahulu.'); 
			document.${formName}.txtKegunaanTanah.focus();
			return; 
		 }		
		 
		 if(document.${formName}.socKategori.value == ""){ 
			alert('Sila masukkan " Kategori " terlebih dahulu.'); 
			document.${formName}.socKategori.focus();
			return; 
		 }
		 if(document.${formName}.socStatusDaftar.value == ""){ 
			alert('Sila masukkan " Status Sah " terlebih dahulu.'); 
			document.${formName}.socStatusDaftar.focus();
			return; 
		 }        
		 
		  var str1 = document.${formName}.txdTarikhTerima.value; 
		  var dt1 = parseInt(str1.substring(0,2),10); 
		  var mon1 = parseInt(str1.substring(3,5),10)-1; 
		  var yr1 = parseInt(str1.substring(6,10),10);
		  var tarikhTerima = new Date(yr1, mon1, dt1);
		  var str2 = document.${formName}.txdTarikhDaftar.value; 
		  var dt2 = parseInt(str2.substring(0,2),10); 
		  var mon2 = parseInt(str2.substring(3,5),10)-1; 
		  var yr2 = parseInt(str2.substring(6,10),10); 
		  var tarikhDaftar = new Date(yr2, mon2, dt2); 
		  var currentDate = new Date(); 
		  if (tarikhTerima > currentDate){ 
		  	 alert('Tarikh Terima tidak boleh melebihi dari tarikh hari ini.'); 
		   	 document.${formName}.txdTarikhTerima.focus(); return; 
		  } 
		  if (tarikhDaftar > currentDate){ 
		   	  alert('Tarikh Daftar tidak boleh melebihi dari tarikh hari ini.'); 
		   	  document.${formName}.txdTarikhDaftar.focus(); return; 
		  } 
		  if (tarikhDaftar > tarikhTerima){ 
		      alert('Tarikh Daftar tidak boleh melebihi dari Tarikh Terima.'); 
		     document.${formName}.txdTarikhTerima.focus(); return; 
		  }
		  
			if ( !window.confirm("Adakah Anda Pasti ?") ){
			   return;
		 	}
	  
		//END OF VALIDATION
		//document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPendaftaranTanah&firstAction=PendaftaranHakmilik&nextAction=updateDetailHakmilik&idHakmilik="+id;	
		//document.${formName}.submit();
		doAjaxCall${formName}("","firstAction=PendaftaranHakmilik&nextAction=updateDetailHakmilik&idHakmilik="+id);
		
	}
	
	//[Link] Skrin Hakmilik Sambungan, No. Hakmilik
	//27/06/2012
	function hakmilikDetailSamb(id,status){
		document.${formName}.idHakmilik.value = id;
		doAjaxCall${formName}("","firstAction=PendaftaranHakmilik&nextAction=paparDetailHakmilik&statusSah="+status);
	
	}

	//20/10/2010
	//05/02/2013 
	//[Link] Skrin Hakmilik Sambungan, No. Hakmilik (Selepas simpan)
	function hakmilikDetailSambungan(id,status){
		//document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPendaftaranTanah&firstAction=PendaftaranHakmilik&nextAction=papardetailhakmiliksamb&idHakmilik="+id+"&statusSah="+status;
		//document.${formName}.submit();
		hakmilikDetailSamb(id,status);

	}
	
	

		
	//[Cetak]
	//01/06/2010
	function cetakMaklumatHakmilik(idhakmilik){
		var url = "../servlet/ekptg.report.htp.MaklumatFailHakmilikRizab?template=MaklumatHakmilik&idHakmilik="+idhakmilik;
	    var hWnd=window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
		hWnd.opener=document.window;
	    if (hWnd.focus != null) hWnd.focus();
	    
	}
	
// START PAGING

	function screen1(id){
		//document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPendaftaranTanah&firstAction=";
		//document.${formName}.submit();
		doAjaxCall${formName}("","firstAction=");
		
	}
	
	function screen2(id,jenis){
	   	//if(jenis == 'Y' || jenis == 'T'){
		if(jenis == 'M'){
			//document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPendaftaranTanah&firstAction=PendaftaranHakmilik&nextAction=paparDetailHakmilik&idHakmilik="+id;
		 	doAjaxCall${formName}("","firstAction=PendaftaranHakmilik&nextAction=paparDetailHakmilik&idHakmilik="+id);
		}else{
			//document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPendaftaranTanah&firstAction=PendaftaranHakmilik&nextAction=paparDetailRizab&idHakmilik="+id;
			doAjaxCall${formName}("","firstAction=PendaftaranHakmilik&nextAction=paparDetailRizab&idHakmilik="+id);
		}
		//document.${formName}.submit();
	}
	
	function screen3(id){
		//document.${formName}.command.value = "";
		//document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPendaftaranTanah&firstAction=PendaftaranPembangunan&idHakmilik="+id;
		//document.${formName}.submit();
		doAjaxCall${formName}("","firstAction=PendaftaranPembangunan&idHakmilik="+id);
	}
	
	function screen4(id){
		//document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPendaftaranTanah&firstAction=PendaftaranImej&idHakmilik="+id;
		//document.${formName}.submit();
		doAjaxCall${formName}("","firstAction=PendaftaranImej&idHakmilik="+id);
	}
	
	function screen5(id){
		//document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPendaftaranTanah&firstAction=paparDetailHakmilik&idHakmilik="+id;
		//document.${formName}.submit();
		doAjaxCall${formName}("","firstAction=paparDetailHakmilik&idHakmilik="+id);
	}
	//END PAGING	
	
	//TAB
	
	function selectTab2(tabId,command,mode,tabmode){
		doAjaxCall${formName}(command,'mode='+mode+'&tabId='+tabId+'&tabmode='+tabmode);
	}
	
	//[Tab]
	function selectTab(id){
		//alert('selectTab');
		//document.${formName}.command.value = "";
		//document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPendaftaranTanah&firstAction=PendaftaranHakmilik&nextAction=paparDetailHakmilik&tabId="+id;
		//document.${formName}.submit();
		doAjaxCall${formName}("","firstAction=PendaftaranHakmilik&nextAction=paparDetailHakmilik&tabId="+id);
		
	}
	
	// SELECTED ITEM STATUS SAH (UPDATE) 
	function doChangeTaraf() {
		if(document.${formName}.socStatusDaftar.value=='S'){
			doAjaxCall${formName}("","firstAction=PendaftaranHakmilik&nextAction=kemaskinidetailhakmiliksamb&lastAction=doChange");	
		}else{
			doAjaxCall${formName}("","firstAction=PendaftaranHakmilik&nextAction=kemaskiniDetailHakmilik&lastAction=doChange");		
		}
		
	}
	
	//[Simpan]- Hakmilik Sambungan
	function updateDetailHakmilikSamb(id,status){
		//updateHakmilikVerifikasi(id);
		
		if(document.${formName}.socNegeriHR.value == 99999){ 
			alert('Sila masukkan " Negeri " terlebih dahulu.'); 
			document.${formName}.socNegeriHR.focus();
			return; 
	 	}
	 	if(document.${formName}.socDaerahHR.value == ""){ 
			alert('Sila masukkan " Daerah " terlebih dahulu.'); 
			document.${formName}.socDaerahHR.focus();
			return; 
		}
	 	if(document.${formName}.socMukimHR.value == ""){ 
			alert('Sila masukkan " Bandar/Pekan/Mukim " terlebih dahulu.'); 
			document.${formName}.socMukimHR.focus();
			return; 
		}
	 	if(document.${formName}.socJenisHakmilikHR.value == ""){ 
			alert('Sila masukkan " Jenis Hakmilik " terlebih dahulu.'); 
			document.${formName}.socJenisHakmilikHR.focus();
			return; 
		 }
		 if(document.${formName}.txtNoHakmilik.value == ""){ 
				alert('Sila masukkan " Nombor Hakmilik " terlebih dahulu.'); 
				document.${formName}.txtNoHakmilik.focus();
				return; 
		 }
		 
		 if(document.${formName}.socLotHR.value == ""){ 
				alert('Sila masukkan " Kod LOT/PT " terlebih dahulu.'); 
				document.${formName}.socLotHR.focus();
				return; 
		 }
		 if(document.${formName}.txtNoLot.value == ""){ 
				alert('Sila masukkan " No LOT/PT " terlebih dahulu.'); 
				document.${formName}.txtNoLot.focus();
				return; 
		 }
	 	if(document.${formName}.txdTarikhTerima.value == ""){ 
			alert('Sila masukkan " Tarikh Terima " terlebih dahulu.'); 
			document.${formName}.txdTarikhTerima.focus();
			return; 
	 	}
		if(document.${formName}.txdTarikhDaftar.value == ""){ 
			alert('Sila masukkan " Tarikh Daftar " terlebih dahulu.'); 
			document.${formName}.txdTarikhDaftar.focus();
			return; 
	 	}
	 	if(document.${formName}.socTaraf.value == ""){ 
			alert('Sila masukkan " Taraf Hakmilik " terlebih dahulu.'); 
			document.${formName}.socTaraf.focus(); 
			return; 
	 	}	
	 	if(document.${formName}.txtCukaiTahun.value == ""){ 
			alert('Sila masukkan " Cukai Tahun Pertama " terlebih dahulu.'); 
			document.${formName}.txtCukaiTahun.focus();
			return; 
	 	}
	  	if(document.${formName}.txtCukaiTerkini.value == ""){ 
			alert('Sila masukkan " Cukai Tahun Semasa " terlebih dahulu.'); 
			document.${formName}.txtCukaiTerkini.focus();
			return; 
		}
	 	if(document.${formName}.txtLokasi.value == ""){ 
			alert('Sila masukkan " Lokasi " terlebih dahulu.'); 
			document.${formName}.txtLokasi.focus();
			return; 
	 	}
	 	if(document.${formName}.txtKegunaanTanah.value == ""){ 
			alert('Sila masukkan " Kegunaan Tanah " terlebih dahulu.'); 
			document.${formName}.txtKegunaanTanah.focus();
			return; 
	 	}			
	 	/*if(document.${formName}.socLuas.value == ""){ 
			alert('Sila masukkan " Jenis Luas " terlebih dahulu.'); 
			document.${formName}.socLuas.focus();
			return; 
	 	}   
	 	if(document.${formName}.txtLuas.value == ""){ 
			alert('Sila masukkan " Luas " terlebih dahulu.'); 
			document.${formName}.txtLuas.focus();
			return; 
	 	}*/
	 	if(document.${formName}.socLuas.value == "" || document.${formName}.socLuas.value == "0"){ 
			alert('Sila masukkan " Unit Luas " terlebih dahulu.'); 
			document.${formName}.socLuas.focus();
			return false; 
	 	}   
	 	if(document.${formName}.socLuas.value == '7'){
	 		if(document.${formName}.txtLuas5.value == ""){ 
				alert('Sila masukkan " Luas " terlebih dahulu.'); 
				document.${formName}.txtLuas5.focus();
				return false; 
	 		}
			if(document.${formName}.txtLuas6.value == ""){ 
				alert('Sila masukkan " Luas " terlebih dahulu.'); 
				document.${formName}.txtLuas6.focus();
				return false; 
	 		} 
	 	}else if(document.${formName}.socLuas.value == "4" || document.${formName}.socLuas.value == "7" ||document.${formName}.socLuas.value == "8"){
	
	 		if(document.${formName}.txtLuas2.value == ""){ 
				alert('Sila masukkan " Luas " terlebih dahulu.'); 
				document.${formName}.txtLuas2.focus();
				return false; 
	 		}
			if(document.${formName}.txtLuas3.value == ""){ 
				alert('Sila masukkan " Luas " terlebih dahulu.'); 
				document.${formName}.txtLuas3.focus();
				return false; 
	 		}
	 		if(document.${formName}.txtLuas3.value == ""){ 
				alert('Sila masukkan " Luas " terlebih dahulu.'); 
				document.${formName}.txtLuas3.focus();
				return false; 
	 		}
	 	
	 	}else{
	 		if(document.${formName}.txtLuas1.value == ""){ 
				alert('Sila masukkan " Luas " terlebih dahulu.'); 
				document.${formName}.txtLuas1.focus();
				return false;
			}
			 
	 	} 	
	 	if(document.${formName}.socRizab.value == ""){ 
			alert('Sila masukkan " Rizab " terlebih dahulu.'); 
			document.${formName}.socRizab.focus();
			return; 
     	} 
	 	if(document.${formName}.socKategori.value == ""){ 
			alert('Sila masukkan " Kategori " terlebih dahulu.'); 
			document.${formName}.socKategori.focus();
			return; 
	 	}
	 	if(document.${formName}.socStatusDaftar.value == ""){ 
			alert('Sila masukkan " Status Sah " terlebih dahulu.'); 
			document.${formName}.socStatusDaftar.focus();
			return; 
	 	}        
	
	  	var str1 = document.${formName}.txdTarikhTerima.value; 
	  	var dt1 = parseInt(str1.substring(0,2),10); 
	  	var mon1 = parseInt(str1.substring(3,5),10)-1; 
	  	var yr1 = parseInt(str1.substring(6,10),10);
	  	var tarikhTerima = new Date(yr1, mon1, dt1);
	 	var str2 = document.${formName}.txdTarikhDaftar.value; 
	  	var dt2 = parseInt(str2.substring(0,2),10); 
	  	var mon2 = parseInt(str2.substring(3,5),10)-1; 
	  	var yr2 = parseInt(str2.substring(6,10),10); 
	  	var tarikhDaftar = new Date(yr2, mon2, dt2); 
	  	var currentDate = new Date(); 
	  	if (tarikhTerima > currentDate){ 
	  		alert('Tarikh Terima tidak boleh melebihi dari tarikh hari ini.'); 
	   	 	document.${formName}.txdTarikhTerima.focus(); 
	   	 	return; 
	  	} 
	  	if (tarikhDaftar > currentDate){ 
	   		alert('Tarikh Daftar tidak boleh melebihi dari tarikh hari ini.'); 
	   	  	document.${formName}.txdTarikhDaftar.focus(); 
	   	  	return; 
	  	} 
	  	if (tarikhDaftar > tarikhTerima){ 
	  		alert('Tarikh Daftar tidak boleh melebihi dari Tarikh Terima.'); 
	  		document.${formName}.txdTarikhTerima.focus(); return; 
	  	}
	  
	  	if(status == 'S'){
	 		if(document.${formName}.socJenisHakmilikBaru.value == ""){ 
				alert('Sila masukkan " Jenis Hakmilik Sambungan " terlebih dahulu.'); 
				document.${formName}.socJenisHakmilikBaru.focus();
				return;
		 	}
		 	if(document.${formName}.txtHakmilikBerikut.value == ""){ 
				alert('Sila masukkan " No Hakmilik Sambungan " terlebih dahulu.'); 
				document.${formName}.txtHakmilikBerikut.focus();
				return; 
		 	}
	  	}
		if ( !window.confirm("Adakah Anda Pasti ?") ){
		   return;
		}
		
		//document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPendaftaranTanah&firstAction=PendaftaranHakmilik&nextAction=updateDetailHakmilik&idHakmilik="+id;	
		//document.${formName}.submit();
		doAjaxCall${formName}("","firstAction=PendaftaranHakmilik&nextAction=updateDetailHakmilik&tabId="+id);
	
	}

	//semakan Tarikh semasa
	function validateTarikhSemasa(inputfield) {
		var today = new Date();	
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
	
	//[link] Maklumat Pergerakan
	function pergerakanhakmilik_detail(id){
		document.${formName}.action = "?_portal_module=ekptg.view.htp.rekod.FrmRekodPergerakanHakmilik&firstAction=paparDetailPergerakanHakmilik&idHakmilik="+id;	
		document.${formName}.submit();
		//doAjaxCall${formName}("","firstAction=paparDetailPergerakanHakmilik&idHakmilik="+id);
	}
	
//	pembangunan/frmRekodPembangunanPentadbiranIndex
 
	 //[Simpan]
	 function tambahPerihalHakmilik(id,luasAsal,luasCurrent,luasJumlahGuna,urljava){

	     var diff1;var diff2;var combine;
		 
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
	
		//aeda 
		var socJenisBinaan = document.${formName}.socJenisBinaan.value;
		var txtNoJKR = document.${formName}.txtNoJKR.value;
		var txdTarikhBina = document.${formName}.txdTarikhBina.value;
		var txtHarga = document.${formName}.txtHarga.value;
		var socLuas = document.${formName}.socLuas.value;
		var txtLuas1 = document.${formName}.txtLuas1.value;
		var txtCatatan = document.${formName}.txtCatatan.value;		
		var perihal = document.${formName}.txtPerihalImej.value;
		
		if (document.${formName}.fileupload.value == ""){ 			
    		doAjaxCall${formName}("","firstAction=PendaftaranPembangunan&nextAction=tambahDetailKeluasanNoAttachement&idHakmilik="+id);
			
		}else{
    		
    		//var ringkas = document.${formName}.txtRingkas.value ;
    	    document.${formName}.action = "?_portal_module="+urljava+"&firstAction=PendaftaranPembangunan&nextAction=tambahDetailKeluasan&idHakmilik="+id+"&socJenisBinaan="+socJenisBinaan+"&txtNoJKR="+txtNoJKR+"&txdTarikhBina="+txdTarikhBina+"&txtHarga="+txtHarga+"&socLuas="+socLuas+"&txtLuas1="+txtLuas1+"&txtCatatan="+txtCatatan+"&txtPerihalImej="+perihal;
    		document.${formName}.method="post";
    		document.${formName}.enctype="multipart/form-data";
    	    document.${formName}.encoding="multipart/form-data";
    		document.${formName}.submit();
    	
    	}
		
	}	
	
	 function XviewDetailPerihal(id){
	 	//document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPendaftaranTanah&firstAction=PendaftaranPembangunan&nextAction=viewDetailKeluasan&idHakmilikPerihal="+id;
	 	//document.${formName}.submit();
	 	document.${formName}.idHakmilikPerihal.value = id; 	
		doAjaxCall${formName}("","firstAction=PendaftaranPembangunan&nextAction=viewDetailKeluasan");
	 }
	 
	 function kemaskiniDetailPerihal(id,idGambar){
	 	//document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPendaftaranTanah&firstAction=PendaftaranPembangunan&nextAction=kemaskiniDetailKeluasan&idHakmilikPerihal="+id;
	 	//document.${formName}.submit();
	 	
		doAjaxCall${formName}("","firstAction=PendaftaranPembangunan&nextAction=kemaskiniDetailKeluasan&idHakmilikPerihal="+id+"&idGambar="+idGambar);
	 }
 
 	function XupdatePerihalHakmilik(id,luasAsal,luasCurrent,luasJumlahGuna,luasYgDiUpdate){
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
 	document.${formName}.idHakmilikPerihal.value = id;
 	//document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPendaftaranTanah&firstAction=PendaftaranPembangunan&nextAction=updateDetailKeluasan&idHakmilikPerihal="+id;
 	//document.${formName}.submit();
 	doAjaxCall${formName}("","firstAction=PendaftaranPembangunan&nextAction=updateDetailKeluasan&idHakmilikPerihal="+id);
 	
 }
 
	function kembali(id,jenis){
    	if(jenis == 'Y' || jenis == 'T'){
 			document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPendaftaranTanah&firstAction=PendaftaranHakmilik&nextAction=paparDetailHakmilik&idHakmilik="+id;
 		}else{
 			document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPendaftaranTanah&firstAction=PendaftaranHakmilik&nextAction=paparDetailRizab&idHakmilik="+id;
 		}
 		document.${formName}.submit();
 	
 	}
 	
 	function dariPembangunan(id,jenis){
  		screen2(id,jenis);
 	}
 
	 function validateNumber(elmnt,content) {
	 	//if it is character, then remove it..
	 	if (isNaN(content)) {
	 		elmnt.value = RemoveNonNumeric(content);
	 		return;
	 	}
	 }
	 
	function calculate(valueMohon,valueBaki){
  		var luasPelepasan = document.${formName}.txtLuasPelepasan.value * 1;
  		var luasAsal = document.${formName}.txtLuasAsal.value * 1; 
  		if (luasPelepasan != ""){
   			if (luasPelepasan > luasAsal){
    			//alert('Luas yang dimasukkan telah melebihi luas asal.'); 
    			document.${formName}.txtLuasPelepasan.value = valueMohon; 
    			document.${formName}.txtBakiLuas.value = valueBaki;
    			return;
   			
   			}else {
    			var luasBaki = (luasAsal - luasPelepasan) * 1; 
    			document.${formName}.txtBakiLuas.value = luasBaki.toFixed(5); 
    		}
   		}
 	} 
 	
	 function XdeleteDetailPembangunan(id){
	 	if ( !window.confirm("Adakah Anda Pasti?")) return;
	 	//document.${formName}.command.value = "";
	 	//document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPendaftaranTanah&firstAction=PendaftaranPembangunan&nextAction=deleteDetailPembangunan&idHakmilikPerihal="+id;
	 	//document.${formName}.submit();
	 	document.${formName}.idHakmilikPerihal.value = id;
 		doAjaxCall${formName}("","firstAction=PendaftaranPembangunan&nextAction=deleteDetailPembangunan");
	 	
	 }
	 
 //function x3baru(id){
 function XbaruPembangunan(id){
 	//document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPendaftaranTanah&firstAction=PendaftaranPembangunan&idHakmilik="+id;
 	//document.${formName}.submit();
	doAjaxCall${formName}("","firstAction=PendaftaranPembangunan&idHakmilik="+id);
 }
 
 function x3doChangeTaraf() {
 	doAjaxCall${formName}("","firstAction=PendaftaranPembangunan&nextAction=doChangeLuas");
 }
 
 function XdoChangeTaraf2(mode) {
 	doAjaxCall${formName}("","mode="+mode+"&firstAction=PendaftaranPembangunan&nextAction=doChangeLuas");
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
	
	
	
//	imej/frmRekodPembangunanImejIndex

	//[]
	function baru(id){
		//document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPendaftaranTanah&firstAction=PendaftaranImej&idHakmilik="+id;
		//document.${formName}.submit();
		doAjaxCall${formName}("","firstAction=PendaftaranImej&idHakmilik="+id);
	}
	
	//[Simpan]
	function XsimpanDetailImej(id){
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

	// 2013/01/08
	function simpanDetailImej(id){
		
		/*if(document.${formName}.fileupload.value == ""){
			alert("Sila pilih fail terlebih dahulu");
			return;
		}*/
		
		var urljava = "";
		
		if(document.${formName}.negeri.value=="SARAWAK" || document.${formName}.negeri.value=="SABAH"){ 
			
			urljava = "ekptg.view.htp.negeri.rekod.FrmRekodTanahNegeri";
			
		}else{
			
			urljava = "ekptg.view.htp.rekod.FrmRekodTanah";
			
		}
		
		var size = document.${formName}.fileupload.length;
		var pass=true;
	
		if(size>1){
			for(var i=0 ; i < document.${formName}.fileupload.length; i++) { 
	    		if (document.${formName}.fileupload[i].value==""){
	    			pass=false;
	  			}
			}
		}else{
			if (document.${formName}.fileupload.value==""){
				pass=false; 
	    	}
		}
	
		if(!pass){
			alert("Sila pilih fail terlebih dahulu");
	  		document.${formName}.fileupload.focus(); 
			return;
		}
		
		if ( !window.confirm("Adakah Anda Pasti?")) return;
		var perihal = document.${formName}.txtPerihalImej.value;
		var ringkas = document.${formName}.txtRingkas.value ;
		document.${formName}.action = "?_portal_module="+urljava+"&firstAction=PendaftaranImej&nextAction=tambahDetailImej&idHakmilik="+id+"&txtPerihalImej="+perihal+"&txtRingkas="+ringkas;
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
	function changeJumlahLampiran(fa,IDJadualLampiran,j,a) {
		if (j.value < 1) {
			alert("Sila masukkan nombor yang sah");
			j.value = 1;
			return;
		}//&nextAction=viewDetailImej
	 	doAjaxCall${formName}("imej","firstAction="+fa+"&X="+a+"&action=changeLampiran&IDJadualLampiran="+IDJadualLampiran);
	
	}
	
	function doChangeJumlahLampiran1(IDJadualLampiran,j,a) {
		if (j.value < 1) {
			alert("Sila masukkan nombor yang sah");
			j.value = 1;
			return;
		}//&nextAction=viewDetailImej
		doDivAjaxCall${formName}("imejId","ImejPembangunan","firstAction=ImejPembangunan&X="+a+"&action=changeLampiran1&IDJadualLampiran="+IDJadualLampiran);
		
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
	
	//RIZAB
	
	//[link No. Warta]	
	function rizab_detail(id_,status){
		//document.${formName}.action = "?_portal_module=ekptg.view.htp.rekod.FrmRekodPendaftaranTanah&firstAction=PendaftaranHakmilik&nextAction=paparDetailRizab&idHakmilik="+id;
		//document.${formName}.submit();
		doAjaxCall${formName}("","firstAction=PendaftaranHakmilik&nextAction=paparDetailRizab&idHakmilik="+id_+"&statusSah="+status);
	}
	
	//[Tab Rizab]
	function selectTabRizab(id){
		doAjaxCall${formName}("","firstAction=PendaftaranHakmilik&nextAction=paparDetailRizab&tabId="+id);		
	}
	
	function kemaskini_detailRizab(id_){
		//document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPendaftaranTanah&firstAction=PendaftaranHakmilik&nextAction=kemaskiniDetailRizab&idHakmilik="+id;
		//document.${formName}.submit();
		doAjaxCall${formName}("","firstAction=PendaftaranHakmilik&nextAction=kemaskiniDetailRizab&idHakmilik="+id_);		
	}
	
	
	//01/06/2010
	function cetakMaklumatRizab(idhakmilik){
		var url = "../servlet/ekptg.report.htp.MaklumatFailHakmilikRizab?template=MaklumatRizab&idHakmilik="+idhakmilik;
	    var hWnd=window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
		hWnd.opener=document.window;
	    if (hWnd.focus != null) hWnd.focus();
	    
	}
		
	function update_detailRizab(id){
		//VALIDATAION
		 if(document.${formName}.socNegeriHR.value == 99999){ 
			alert('Sila masukkan " Negeri " terlebih dahulu.'); 
			document.${formName}.socNegeriHR.focus();
			return; 
		 }
		 if(document.${formName}.txdTarikhTerima.value == ""){ 
			alert('Sila masukkan " Tarikh Terima " terlebih dahulu.'); 
			document.${formName}.txdTarikhTerima.focus();
			return; 
		 }
		 if(document.${formName}.txtNoWarta.value == ""){ 
			alert('Sila masukkan " No Warta " terlebih dahulu.'); 
			document.${formName}.txtNoWarta.focus();
			return; 
		 }
		 if(document.${formName}.txdTarikhWarta.value == ""){ 
			alert('Sila masukkan " Tarikh Warta " terlebih dahulu.'); 
			document.${formName}.txdTarikhWarta.focus();
			return; 
		 }
		 if(document.${formName}.txtLokasi.value == ""){ 
			alert('Sila masukkan " Lokasi " terlebih dahulu.'); 
			document.${formName}.txtLokasi.focus();
			return; 
		 }
		 if(document.${formName}.txtKegunaanTanah.value == ""){ 
			alert('Sila masukkan " Kegunaan Tanah " terlebih dahulu.'); 
			document.${formName}.txtKegunaanTanah.focus();
			return; 
		 }			
		 if(document.${formName}.socLuas.value == ""){ 
			alert('Sila masukkan " Jenis Luas " terlebih dahulu.'); 
			document.${formName}.socLuas.focus();
			return; 
		 }   
		 if(document.${formName}.txtKegunaanTanah.value == ""){ 
			alert('Sila masukkan " Kegunaan Tanah " terlebih dahulu.'); 
			document.${formName}.txtKegunaanTanah.focus();
			return; 
		 }
		 if(document.${formName}.txtLuas.value == ""){ 
			alert('Sila masukkan " Luas " terlebih dahulu.'); 
			document.${formName}.txtLuas.focus();
			return; 
		 }
		 if(document.${formName}.socStatusDaftar.value == ""){ 
			alert('Sila masukkan " Status Sah " terlebih dahulu.'); 
			document.${formName}.socStatusDaftar.focus();
			return; 
		 }     
		var str1 = document.${formName}.txdTarikhTerima.value; 
	  	var dt1 = parseInt(str1.substring(0,2),10); 
	  	var mon1 = parseInt(str1.substring(3,5),10)-1; 
	  	var yr1 = parseInt(str1.substring(6,10),10);
	  	var tarikhTerima = new Date(yr1, mon1, dt1);
	  	var str2 = document.${formName}.txdTarikhWarta.value; 
	  	var dt2 = parseInt(str2.substring(0,2),10); 
	  	var mon2 = parseInt(str2.substring(3,5),10)-1; 
	  	var yr2 = parseInt(str2.substring(6,10),10); 
	  	var tarikhDaftar = new Date(yr2, mon2, dt2); 
	  	var currentDate = new Date(); 
	  	if (tarikhTerima > currentDate){ 
	  		alert('Tarikh Terima tidak boleh melebihi dari tarikh hari ini.'); 
	   	 	document.${formName}.txdTarikhTerima.focus(); return; 
	  	} 
	  	if (tarikhDaftar > currentDate){ 
	   		alert('Tarikh Daftar tidak boleh melebihi dari tarikh hari ini.'); 
	   	  	document.${formName}.txdTarikhWarta.focus(); return; 
	  	} 
	  	if (tarikhDaftar > tarikhTerima){ 
	    	alert('Tarikh Daftar tidak boleh melebihi dari Tarikh Terima.'); 
	     	document.${formName}.txdTarikhTerima.focus(); return; 
	  	}
	    
	 	if ( !window.confirm("Adakah Anda Pasti ?") ){
		   return;
	 	}
		//END OF VALIDATION
		//document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodPendaftaranTanah&firstAction=PendaftaranHakmilik&nextAction=updateDetailRizab&idHakmilik="+id;
		//document.${formName}.submit();
		doAjaxCall${formName}("","firstAction=PendaftaranHakmilik&nextAction=updateDetailRizab&tabId="+id);
	
	}	
	

	//END OF RIZAB
	
	//START LAIN2
	
	function viewTransaksiCukai(id) {
		var url = "../x/${securityToken}/ekptg.view.htp.FrmRekodTransaksiCukai?idHakmilik="+id;
	    var hWnd = window.open(url,'printuser','width=300,height=300, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
	       hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus();
		hWnd.focus();
	}
	
	function viewTransaksiAgensi(id_) {
		var url = "../x/${securityToken}/ekptg.view.htp.rekod.FrmRekodTransaksiHakmilikAgensi?idHakmilik="+id_;
	    var hWnd = window.open(url,'printuser','width=500,height=250, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
	       hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus();
		hWnd.focus();
	}
	
	function doPengesahanHq(idHakmilikPerihal) {
	 	document.${formName}.idHakmilikPerihal.value = idHakmilikPerihal; 	
		doAjaxCall${formName}("","firstAction=PendaftaranPembangunan&nextAction=pengesahanHq");
	}
	
</script>
#parse("app/htp/rekod/utiliti/javaScriptRekodPembangunan.jsp")
#parse("app/htp/utiliti/javaScriptUmum.jsp")
#parse("app/htp/rekod/utiliti/pindahfail/javaScriptRekodFailBaru.jsp")

<script>

</script>

<script>
function viewDetailSewa(){
	doDivAjaxCall$formname('div_maklumatPenyewaan','doViewDetailSewa','');
}
</script>


