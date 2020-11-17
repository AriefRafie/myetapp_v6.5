<style type="text/css">
<!--
.style1 {color: #0033FF}
-->
</style>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
	<tr>
    	<td>&nbsp;</td>
	</tr>
	<tr>
    	<td>
			<fieldset>
				<legend>CARIAN</legend>
				<table width="100%" border="0">
					<tr>
						<td width="29%"><div align="right">Negeri</div></td>
						<td width="1%">:</td>
						<td width="70%">$socNegeri</td>
					</tr>
					<tr>
						<td width="29%"><div align="right">Daerah</div></td>
						<td width="1%">:</td>
						<td width="70%">$socDaerah</td>
					</tr>
					<tr>
						<td width="29%"><div align="right">
						<div align="right">Bandar/Pekan/Mukim</div>
						</div></td>
						<td width="1%">:</td>
						<td width="70%">$socMukim</td>
					</tr>
					<tr>
						<td width="29%"><div align="right">Kementerian</div></td>
						<td width="1%">:</td>
						<td width="70%">$socKementerian</td>
					</tr>
					<tr>
						<td width="29%"><div align="right">Agensi</div></td>
						<td width="1%">:</td>
						<td width="70%">$socAgensi</td>
					</tr>
					<tr>
						<td width="29%"><div align="right">No Fail</div></td>
						<td width="1%">:</td>
						<td width="70%"><input name="txtNoFail" type="text" id="txtNoFail" size="43" value="$!txtNoFail"></td>
					</tr>
					<!-- start addby zul -->
					<tr>
						<td width="29%"><div align="right">No. Rujukan Online</div></td>
						<td width="1%">:</td>
						<td width="70%"><input name="txtNoRujukanOnline" type="text" id="txtNoRujukanOnline" size="43" value="$!txtNoRujukanOnline"></td>
					</tr>
					<!-- end addby zul -->
					<tr>
						<td width="29%"><div align="right">Tajuk Fail</div></td>
						<td width="1%">:</td>
						<td width="70%"><input name="txtTajukFail" type="text" id="txtTajukFail" size="43" value="$!txtTajuk"></td>
					</tr>
					<tr>
						<td width="29%"><div align="right">Urusan</div></td>
						<td width="1%">:</td>
						<td width="70%">$socUrusan</td>
					</tr>
					<!-- start addby zul -->
					<tr>
						<td width="29%"><div align="right">Status Fail</div></td>
						<td width="1%">:</td>
						<td width="70%">$!listStatus</td>
					</tr>
					<!-- end addby zul -->
					<tr>
						<td width="29%">&nbsp;</td>
						<td width="1%">&nbsp;</td>
						<td width="70%">&nbsp;</td>
					</tr>
					<tr>
						<td width="29%">&nbsp;</td>
						<td width="1%">&nbsp;</td>
						<td width="70%">
						<input class="stylobutton100"  name="cmdCari" id="cmdCari" value="Cari" type="button" onclick="javascript:carianFail()" />
						<!-- <input class="stylobutton100"  name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" type="button" onclick="javascript:cancel()" /></td> -->
						<input class="stylobutton100" type=button value = "Kosongkan" onClick="javascript:cancel();">
					</tr>
					<input type="hidden" name="flagAdvSearch" value="$!flagAdvSearch">
				</table>
			</fieldset>
		</td>
	<tr/>	
	<tr>
		<td>
			<fieldset>
				<legend>SENARAI FAIL</legend>
				<!--tambah untuk permohonan addby zul-->
				<input class="stylobutton100" value="Daftar Baru" type="button" onClick="javascript:tambahPermohonan()">
				
				#parse("app/utils/record_paging.jsp")
				<table width="100%" border="0">
					<tr class="table_header">
						<td width="3%" align="center"><b>Bil.</b></td>
						<td width="15%"><b>No. Rujukan Online</b></td><!-- addby zul -->
						<td width="15%"><b>No.<!--Permohonan/--> Fail</b></td>
						<td width="32%"><b>Tajuk Fail</b></td>
						<td width="15%"><b>Negeri</b></td>
						<td width="20%"><b>Status</b></td>
					</tr>
			
					#set ( $cnt = 0 )			
					#foreach ( $senarai in $SenaraiFail )
						#set ( $cnt = $cnt + 1 )
						#set( $i = $velocityCount )
						#if ( ($i % 2) == 0 )
							#set( $row = "row2" )
						#else
							#set( $row = "row1" )
						#end
						<tr class="$row">
							<td align="center">$cnt.</td>
							<td ><!-- NO PERMOHONAN -->
								<a href="javascript:viewMaklumatPermohonan('$senarai.id')" class="style1">$senarai.noP</a><a href="javascript:viewFailById('$list.idFail','$list.idPermohonan','$list.idHtpPermohonan','$list.idSuburusanStatusFail')"></a>
							</td>
							<td >
								<a href="javascript:viewMaklumatPermohonan('$senarai.id')" class="style1">$senarai.no </a><a href="javascript:viewFailById('$list.idFail','$list.idPermohonan','$list.idHtpPermohonan','$list.idSuburusanStatusFail')"></a>
								<!-- #if(!$senarai.no.equals('TIADA'))
									<a href="javascript:viewMaklumatPermohonan('$senarai.id')" class="style1">$senarai.no </a><a href="javascript:viewFailById('$list.idFail','$list.idPermohonan','$list.idHtpPermohonan','$list.idSuburusanStatusFail')"></a>
								#else
									<a href="javascript:maklumatPermohonan('$senarai.id')" class="style1">$senarai.noP </a><a href="javascript:viewFailById('$list.idFail','$list.idPermohonan','$list.idHtpPermohonan','$list.idSuburusanStatusFail')"></a>
								#end -->
							</td>
							<td >$senarai.tujuan</td>
							<td >$senarai.negeri</td>
							<td >$senarai.keterangan</td>
						</tr>
					#end
					#if ($cnt == 0)
						<tr>
							<td class="row1" align="center">&nbsp;</td>
							<td class="row1">Tiada Rekod</td>
							<td class="row1">&nbsp;</td>
							<td class="row1" align="center">&nbsp;</td>
							<td class="row1">&nbsp;</td>
						</tr>
					#end
				</table>
			</fieldset>
		</td>
	<tr/>
</table>

<script>

	function cetakPengesahan(idpermohonan) {
	    var url = "../servlet/ekptg.report.htp.NoFailTajukFail?template=PengesahanPermohonanOnline&idpermohonan="+idpermohonan;
	    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus();
	
	
	}
	function carianFail(){
		var command = 'terimapohoncarian';
		doAjaxCall${formName}(command);	
	}

	function cancel() {
		var command = 'kosong';
		doAjaxCall${formName}(command);	
	}
	
	function viewMaklumatPermohonan(id){
		var mode = 'viewMaklumatPermohonan';
		doAjaxCall${formName}("maklumatpermohonanonline","mode="+mode+"&idfail="+id+"&pagemode=0");
	
	}
	
	function kemaskiniterimapermohonan(id){
		var mode = 'MakAsasTanah';
		doAjaxCall${formName}("kemaskinipermohonan","mode="+mode+"&idfail="+id+"&pagemode=0");
		
	}
	
	function kembali(){
		doAjaxCall${formName}("");
	}
	
	function DetailTanah(tabId,tabmode,command,mode,button,idhakmilikurusan){
		document.${formName}.idhakmilikurusan.value=idhakmilikurusan; 
		doAjaxCall${formName}(command,'mode='+mode+'&tabId='+tabId+'&tabmode='+tabmode+'&button='+button+'&idhakmilikurusan='+idhakmilikurusan);
	}
	
	function KemaskiniAsasTanah(tabId,tabmode,command,mode,idhakmilikurusan){
		var button = "KemaskiniMaklumatInfo";
		document.${formName}.idhakmilikurusan.value = idhakmilikurusan;
		doAjaxCall${formName}(command,'mode='+mode+'&tabId='+tabId+'&tabmode='+tabmode+'&idhakmilikurusan='+idhakmilikurusan+'&button='+button);
	
	}
	
	function selectTab(tabId,command,mode,tabmode,id){
		document.${formName}.detailMode.value="View"; 
		doAjaxCall${formName}(command,'mode='+mode+'&tabId='+tabId+'&tabmode='+tabmode+'&idpermohonan='+id);
	}
	
	
	function KembaliAsas(tabId,command,mode,tabmode){
		doAjaxCall${formName}(command,'mode='+mode+'&tabId='+tabId+'&tabmode='+tabmode);
	
	}
	
//	function selectTab2(tabId,command,mode,tabmode){	
//		doAjaxCall${formName}(command,'mode='+mode+'&tabId='+tabId+'&tabmode='+tabmode);
//	
//	}
	
	function selectTab2(tabId2,command,mode,button,id){
		doAjaxCall${formName}(command,'mode='+mode+'&tabId2='+tabId2+'&button='+button+'&idpermohonan='+id);
	
	}
	
	/* function ini adalah apabila tekan button TAMBAH pada tab Senarai Notis*/
	function TambahSenaraiNotis(tabId,tabmode,command,mode){
		
		var command = 'kemaskinipermohonan';
		var mode = 'Notis5A';
		var button = 'SimpanNotis';
		var tabId = 5;
		var butang3 = '8';
		var tabmode = 1;
		
		doAjaxCall${formName}(command,'mode='+mode+'&tabId='+tabId+'&tabmode='+tabmode+'&butang3='+butang3);
	}
	function SimpanNotis(){
		var command = 'kemaskinipermohonan';
		var mode = 'Notis5A';
		var button = "SimpanNotis";
		var tabId = 5;
		var tabmode = 1;
		
		doAjaxCall${formName}(command,'mode='+mode+'&tabId='+tabId+'&tabmode='+tabmode+'&button='+button);
	}
	
	/* ini adalah apabila tekan button simpan Senarai Notis */
	
	//--- Start for view sahaja Maklumat Notis untuk user online ---
	function Notis5AView(id){
		var command = 'kemaskinipermohonan';
		 var mode = 'Notis5A';
		 var button = 'ViewNotis';
		 var tabId = 5;
		 var tabmode = 1;
		
		doAjaxCall${formName}(command,'&mode='+mode+'&tabId='+tabId+'&tabmode='+tabmode+'&button='+button+'&idNotis='+id);
	}
	//--- end ---
	
	
	//apabila click button tambah
	function tambahPermohonan(){
		//alert("test ");
		doAjaxCall${formName}("pohonfailbaru");
	}
	
	//apabila click option
	function doChanges() {
		//alert("doChanges")
		doAjaxCall${formName}("pohonfailbaru","mode=doChanges");
	}
	
	//apabila click button simpan
	function Simpan(i){
		
		if ( document.${formName}.socNegeri.value == "" ) { 
  			alert('Sila pilih negeri terlebih dahulu.');
  			document.${formName}.socNegeri.focus(); 
  			return; 
  		}
	
		if ( document.${formName}.socDaerah.value == "" ) { 
			alert('Sila pilih daerah terlebih dahulu.');
			document.${formName}.socDaerah.focus(); 
			return; 
		}
	 
		if ( document.${formName}.socKementerian.value == "" ) { 
	  		alert('Sila pilih Kementerian terlebih dahulu.');
	  		document.${formName}.socKementerian.focus(); 
	  		return; 
	  	}
		if ( document.${formName}.socAgensi.value == "" ) { 
	  		alert('Sila pilih Agensi terlebih dahulu.');
	  		document.${formName}.socAgensi.focus(); 
	  		return; 
	  	}

		if ( document.${formName}.socUrusan.value == "" ) { 
	  		alert('Sila pilih Urusan terlebih dahulu.');
	  		document.${formName}.socUrusan.focus(); 
	  		return; 
	  	}
		
	  	if ( document.${formName}.txtTajuk.value == "" ) { 
	  		alert('Sila pilih Tajuk terlebih dahulu.');
	  		document.${formName}.txtTajuk.focus(); 
	  		return; 
	  	}

	  	/*	if ( document.${formName}.txtnoFailUPT.value == "" ) { 
	  		alert('Sila pilih No Fail UPT terlebih dahulu.');
	  		document.${formName}.txtnoFailUPT.focus(); 
	  		return; 
	  	}
	  
	  	if ( document.${formName}.txtnoFailLain.value == "" ) { 
	  		alert('Sila pilih No Fail Lain terlebih dahulu.');
	  		document.${formName}.txtnoFailLain.focus(); 
	  		return; 
	  	}	
	  	*/
	  	
	  	/* if(document.${formName}.socUrusan.value == "1"){
	  	
	  		 if ( document.${formName}.txtnoFailKJP.value == "" ) { 
	  			alert('Sila pilih Fail KJP terlebih dahulu.');
	  			document.${formName}.txtnoFailKJP.focus(); 
	  			return; 
	  		}
	  		
	  		if ( document.${formName}.txdTarikhSuratKJP.value == "" ) { 
	  			alert('Sila pilih Tarikh Surat KJP terlebih dahulu.');
	  			document.${formName}.txdTarikhSuratKJP.focus(); 
	  			return; 
	  		} 
	  		
		  	if ( document.${formName}.socStatustanah.value == "" ) { 
		  		alert('Sila pilih Status Tanah terlebih dahulu.');
		  		document.${formName}.socStatustanah.focus(); 
		  		return; 
		  	}
	  	}
		if ( document.${formName}.socjenisFail.value == "" ) { 
	  		alert('Sila pilih Jenis Fail terlebih dahulu.');
	  		document.${formName}.socjenisFail.focus(); 
	  		return; 
	  	} */
	  	
	
		var mode = 'MakAsasTanah';		
		doAjaxCall${formName}("pohonfailbaru","mode="+mode+"&pagemode=0&hittButton=Simpan");
		document.${formName}.cmdSimpan.value = i;

	}
	
	/* For View Maklumat Permohonan */
	function viewMaklumatPermohonan(id){
		var mode = 'viewMaklumatPermohonan';
		doAjaxCall${formName}("viewMaklumatPermohonan","mode="+mode+"&idfail="+id+"&pagemode=0");
	
	}
	
	/* Untuk view kemaskini */
	function failKemaskini(){
		//doAjaxCall${formName}("kemaskinipermohonan","mode=MakAsasTanah&pagemode=0&hittButton=kemaskini");
		doAjaxCall${formName}("maklumatpermohonan","mode=MakAsasTanah&pagemode=0&hittButton=kemaskini");
		
	}
	/* Untuk Simpan kemaskini */
	function failKemaskiniSimpan(){
		//doAjaxCall${formName}("kemaskinipermohonan","mode=MakAsasTanah&pagemode=0&hittButton=kemaskinisimpan");
		doAjaxCall${formName}("maklumatpermohonan","mode=MakAsasTanah&pagemode=0&hittButton=kemaskinisimpan");
		
	}
	
	/* untuk button seterusnya akan paparkan page ke 3 */
	function seterusnya(id){
		//alert("seterusnya");
		var mode = 'MakAsasTanah';
		doAjaxCall${formName}("kemaskinipermohonan","mode="+mode+"&idfail="+id+"&pagemode=0");
	}
	
	/* Untuk Simpan Hantar Semakan */
	function hantarPengesahan(){
		//alert("hantarPengesahan"); */
		doAjaxCall${formName}("kemaskinipermohonan","mode=MakAsasTanah&pagemode=0&hittButton=hantarPengesahan");
		
	}
	
	function updatetxtNamaDokumen(){
		document.${formName}.txtNamaDokumenHidden.value = document.${formName}.txtNamaDokumen.value;
	}
	
	function updatetxtKeterangan(){
		document.${formName}.txtKeteranganHidden.value = document.${formName}.txtKeterangan.value;
	}
	//simpan bukti pembayaran 
	function simpanBuktiPembayaran(id_permohonan, mode){
		
		if ( !window.confirm("Adakah Anda Pasti?") ) 
			{
			return;
			}
		
			if(mode=="new"){
				
			}else{
				//doAjaxCall${formName}("kemaskinipermohonan","mode="+mode+"&pagemode=0&button=simpanBuktiPembayaran");
				//doAjaxCall${formName}("simpanBuktiBayaran");
				

					document.${formName}.method="POST";
					document.${formName}.value="simpanBuktiPembayaran1";
					document.${formName}.action = "?_portal_module=ekptg.view.online.htp.permohonan.FrmTerimaPohon1Online&command=simpanBuktiPembayaran";
					document.${formName}.submit();
					//document.${formName}.modePopup.value = "";
					//alert("Sukses");
		
		}
	}
	function doOpen(id) {
		
	    var url = "../servlet/ekptg.view.online.htp.permohonan.DisplayBayaranNotis?id="+id;
	    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
	    hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus();
	}
	//simpan maklumat dokumen
	function simpanMD(id_permohonan, mode){
		
		var txtNamaDokumen = document.${formName}.txtNamaDokumenHidden.value;
		
		if(txtNamaDokumen == ""){
			alert("Sila masukkan \"Nama Dokumen\" terlebih dahulu. ");
			document.${formName}.txtNamaDokumen.focus(); 
			return;
		}
		else if(document.${formName}.txtNamaDokumen2.value == ""){
			alert("Sila masukkan \"Lampiran Dokumen\" terlebih dahulu. ");
			document.${formName}.txtNamaDokumen2.focus(); 
			return;
		}
		else if ( !window.confirm("Adakah Anda Pasti?") ) 
			{
			return;
			}
		
			if(mode=="new"){
				
			}else{
				
			var txtNamaDokumen = document.${formName}.txtNamaDokumenHidden.value;		
			var txtKeterangan = document.${formName}.txtKeteranganHidden.value;
			document.${formName}.enctype='multipart/form-data';
			document.${formName}.encoding ='multipart/form-data';
			document.${formName}.action = "?_portal_module=ekptg.view.online.htp.permohonan.FrmTerimaPohon1Online&command=uploadDoc&command2=simpanMaklumatDokumen&txtNamaDokumen="+txtNamaDokumen+"&txtKeterangan="+txtKeterangan+"&id_permohonan="+id_permohonan;
			document.${formName}.submit();
				
		 
		}
	}
	
	/* function ini adalah apabila tekan button TAMBAH pada tab Maklumat Asas Tanah*/
	function TambahAsasTanah(tabId,tabmode,command,mode){
		doAjaxCall${formName}(command,'mode='+mode+'&tabId='+tabId+'&tabmode='+tabmode);
	}
	/* ini adalah apabila tekan button simpan maklumat asas tanah */
	
	function SimpanMaklumatAsasTanah(){
		
		if ( document.${formName}.socdaerah2.value == "" ) { 
			alert('Sila pilih daerah terlebih dahulu.');
			document.${formName}.socdaerah2.focus(); 
			return; 
		}
		if ( document.${formName}.socMukim2.value == "" ) { 
			alert('Sila pilih mukim terlebih dahulu.');
			document.${formName}.socMukim2.focus(); 
			return; 
		}
		if ( document.${formName}.socLot.value == "" ) { 
			alert('Sila masukkan kod lot terlebih dahulu.');
			document.${formName}.socLot.focus(); 
			return; 
		}
		if ( document.${formName}.txtNoLot.value == "" ) { 
			alert('Sila masukkan nombor lot terlebih dahulu.');
			document.${formName}.txtNoLot.focus(); 
			return; 
		}
		/*if ( document.${formName}.noSyit.value == "" ) { 
			alert('Sila masukkan no syit terlebih dahulu.');
			document.${formName}.noSyit.focus(); 
			return; 
		}
		
		if ( document.${formName}.kodluas.value == "" ) { 
			alert('Sila pilih kod luas terlebih dahulu.');
			document.${formName}.kodluas.focus(); 
			return; 
		}	
		*/
		/*
		if ( document.${formName}.Luas.value == "" ) { 
			alert('Sila masukkan luas terlebih dahulu.');
			document.${formName}.Luas.focus(); 
			return; 
		}
		
		if ( document.${formName}.Lokasi.value == "" ) { 
			alert('Sila masukkan lokasi terlebih dahulu.');
			document.${formName}.Lokasi.focus(); 
			return; 
		}*/
		
		/* if(document.${formName}.socLuas.value == "4" || document.${formName}.socLuas.value == "7" ||document.${formName}.socLuas.value == "8"){
			if(document.${formName}.socLuas.value == "4"){
				document.${formName}.txtLuasGabung.value = document.${formName}.txtLuas2.value +"E,"+document.${formName}.txtLuas3.value+"R,"+document.${formName}.txtLuas4.value+"P";
			}else if(document.${formName}.socLuas.value == "7"){
				document.${formName}.txtLuasGabung.value = document.${formName}.txtLuas5.value +"E,"+document.${formName}.txtLuas6.value+"D";
			}else if(document.${formName}.socLuas.value == "8"){
				document.${formName}.txtLuasGabung.value = document.${formName}.txtLuas2.value +"R,"+document.${formName}.txtLuas3.value+"J,"+document.${formName}.txtLuas4.value+"K";
			}
		 }else{
		 	if(document.${formName}.socLuas.value == "1"){
				document.${formName}.txtLuasGabung.value = document.${formName}.txtLuas1.value+"KM";
		 	}else if(document.${formName}.socLuas.value == "2"){
				document.${formName}.txtLuasGabung.value = document.${formName}.txtLuas1.value+"H";
		 	}else if(document.${formName}.socLuas.value == "3"){
				document.${formName}.txtLuasGabung.value = document.${formName}.txtLuas1.value+"MP";
		 	}else{
				document.${formName}.txtLuasGabung.value = document.${formName}.txtLuas1.value;
		 	}
		 } */
		
		var command = 'kemaskinipermohonan';
		var mode = 'MakAsasTanah';
		var button = "SimpanMaklumatAsasTanah";
		var tabId = 0;
		var tabmode = 1;
		doAjaxCall${formName}(command,'&mode='+mode+'&button='+button+'&tabId='+tabId+'&tabmode='+tabmode);
	}
	function doViewForKemaskini() {
		var command = 'kemaskinipermohonan';
		var mode = 'MakAsasTanah';
		var tabId = 0;
		var tabmode = 2;
		var button = "doViewForKemaskini";
		doAjaxCall${formName}(command,'mode='+mode+'&tabId='+tabId+'&tabmode='+tabmode+'&button='+button);
	}
	
	//##########  End Addby zulfazdli ########## 
	
	function doChangeNegeriX() {
	doAjaxCall${formName}("","mode=changeNegeri");
	}
	
	
	
	function doChanges2(t) {
		var command = 'kemaskinipermohonan';
		var mode = 'MakAsasTanah';
		var tabId = 0;
		var tabmode = t;
		var dochange = "doChanges";
		doAjaxCall${formName}(command,'mode='+mode+'&tabId='+tabId+'&tabmode='+tabmode+'&doChange='+dochange);
	}

	function doChangeDaerahX() {
		doAjaxCall${formName}("","mode=changeDaerah");
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
	function calculate(){
		var Cukai1 = document.${formname}.txtCukaiTahunPertama.value * 1;
		
		//removed
		//var BayaranNotis = document.${formname}.txtBayaranNotis.value * 1;
		
		var BayaranNotisF = document.${formname}.txtBayaranNotisF.value * 1;
		var BayaranFail = document.${formname}.txtBayaranFail.value * 1;
		var Premium = document.${formname}.txtPremium.value * 1;
		var phakmilik = document.${formname}.txtPendaftaranHakmilik.value * 1;
		var bayarukur = document.${formname}.txtBayarUkur.value * 1;
		var bayarperenggan = document.${formname}.txtBayaranPerenggan.value * 1;
		var tandasempadan = document.${formname}.txtTandaSempadan.value * 1;
		var lain = document.${formname}.txtBayaranLain.value * 1;
		var lain2 = document.${formname}.txtBayaranLain2.value * 1;
		var lain3 = document.${formname}.txtBayaranLain3.value * 1;
		var RayuanPremium = document.${formname}.txtRayuanPremium.value * 1;
		
		//var Lebihan = document.${formname}.txtLebihan.value * 1;
		var jumBayaran1 = bayarukur + bayarperenggan + tandasempadan + 
				  lain + phakmilik + Cukai1 + 
				  BayaranNotisF + Premium + BayaranFail + lain2 + lain3;
		var jumBayaran = jumBayaran1 - RayuanPremium;
	
		document.${formname}.txtJumBayaran.value = jumBayaran.toFixed(2);
	}
	function doCalculateTarikh() {
		var TB  = document.${formName}.txtTarikhNotis5A.value;
	    	var date = getDate(TB);
	
	    	var date1 = DateAdd('m',date,2);
	    	document.${formName}.txtTarikhLuputPertama.value = convertDate(date1) ;
	
	    	var date2 = DateAdd('m',date1,3);
	    	document.${formName}.txtTarikhLuputNotisKedua.value = convertDate(date2) ;
	    	
	    	var date3 = DateAdd('m',date2,3);
	    	document.${formName}.txtTarikhLuputNotisKetiga.value = convertDate(date3) ;
	}
	function getDate(strDate) {
		return new Date(strDate.substring(6,10),strDate.substring(3,5),strDate.substring(0,2));
	}
	function convertDate(myDate) {
		var day = myDate.getDate();
		var month = myDate.getMonth()+1;
		var year = myDate.getFullYear();
		var myTarikh = "";
		if(month>=10){
			if(day>=10){
				myTarikh = day + "/" + month + "/" + year;	
			}else{
				myTarikh = "0"+ day + "/" + month + "/" + year;	
			}
	
		}else{
			if(day>=10){
				myTarikh = day + "/0" + month + "/" + year;	
			}else{
				myTarikh = "0"+ day + "/0" + month + "/" + year;	
			}
		}
		return myTarikh;
	}
	function DateAdd(ItemType, DateToWorkOn, ValueToBeAdded)
	{
	    switch (ItemType)
	    {
	        //date portion        
	        case 'd': //add days
	            DateToWorkOn.setDate(DateToWorkOn.getDate() + ValueToBeAdded)
	            break;
	        case 'm': //add months
	            DateToWorkOn.setMonth(DateToWorkOn.getMonth() + ValueToBeAdded)
	            break;
	        case 'y': //add years
	            DateToWorkOn.setYear(DateToWorkOn.getFullYear() + ValueToBeAdded)
	            break;
	        //time portion        
	        case 'h': //add days
	            DateToWorkOn.setHours(DateToWorkOn.getHours() + ValueToBeAdded)
	            break;
	        case 'n': //add minutes
	            DateToWorkOn.setMinutes(DateToWorkOn.getMinutes() + ValueToBeAdded)
	            break;
	        case 's': //add seconds
	            DateToWorkOn.setSeconds(DateToWorkOn.getSeconds() + ValueToBeAdded)
	            break;
	    }
	    return DateToWorkOn;
	}
	
	function formatNumber(input ) {
	    var string=input.value;
	    var result=new Intl.NumberFormat().format(string.replace(/,/g, "")) ;
	    input.value = result;
	}
	
	
</script>