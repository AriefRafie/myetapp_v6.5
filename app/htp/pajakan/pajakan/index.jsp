<p>
  <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>

  <input type="hidden" name="mode" id="mode" value="$mode"/>
  <input type="hidden" name="hitButton" id="hitButton" value="$!hitButton"/>
  <input type="hidden" name="idFail" id="idFail" value="$idFail"/>
  <input type="hidden" name="idStatus" id="idStatus" value="$idStatus"/>
  <input type="hidden" name="selectedTab" id="selectedTab" value="$selectedTab"/>
  <input type="hidden" name="idPermohonan" id="idPermohonan" value="$idPermohonan"/>
  <input type="hidden" name="actionPajakan" value="$actionPajakan"/>
  <input type="hidden" name="subUrusan" value="$subUrusan"/>
  <input type="hidden" name="idDraf" id="idDraf" value="$idDraf"/>
  <input type="hidden" name="idPajakan" id="idPajakan" value="$!idPajakan"/>
</p>

<table width="100%" border="0">

#if ($idFail != '')

	<tr>
		<td>
		#parse('app/htp/pajakan/paging.jsp')
		</td>
    </tr>
  	<tr>
    	<td>#parse("app/htp/pajakan/fail/frmPajakanHeader.jsp")</td>
  	</tr>
  	<tr>
    	<td>&nbsp;</td>
  	</tr>
#else
  	<tr>
    	<td>
    	&nbsp;<div class="warning">SILA PILIH FAIL DI SENARAI FAIL TERLEBIH DAHULU</div>
    	</td>
	</tr>
#end

#if (($idFail != '' && $idStatus != '1' && $idStatus != '6' && $idStatus != '12' && $idStatus != '63' && $idStatus != '65') || ($idFail != '' && $!page == "4") )
  <tr>
    <td>
       <div id="TabbedPanels1" class="TabbedPanels">

              <ul class="TabbedPanelsTabGroup">

              <!-- -->
              #if($subUrusan != '17')
                <li class="TabbedPanelsTab" title="Maklumat Pajakan" onclick="doChangeTab(0)" tabindex="0">MAKLUMAT PAJAKAN</li>
                <li class="TabbedPanelsTab" title="Borang 15A"  onclick="doChangeTab(1)" tabindex="0">BORANG (15A / L / LX11)</li>

              #else

                <li class="TabbedPanelsTab" title="Maklumat Pajakan" onclick="doChangeTab(0)" tabindex="0">MAKLUMAT PAJAKAN</li>
                <li class="TabbedPanelsTab" title="Borang 15A"  onclick="doChangeTab(1)" tabindex="0">BORANG (15A / L / LX11)</li>

             #end

              </ul>


              <div class="TabbedPanelsContentGroup">
                <div class="TabbedPanelsContent">
                <!-- content maklumat pajakan -->
					#if ($selectedTab == '0')
                		##parse ("app/htp/frmPajakanTabMaklumatPajakan.jsp")
                		#parse ("app/htp/pajakan/pajakan/frmPajakanTabMaklumatPajakan.jsp")
					#end
              </div>
              <!-- close content maklumat pajakan -->

              <div class="TabbedPanelsContent">
                <!-- content Borang 15A -->
					#if ($selectedTab == '1')
						#parse ("app/htp/pajakan/pajakan/frmPajakanTabBorang15A.jsp")
					#end
              </div>
              <!-- close content Borang 15A -->

         </div>
         <!-- close TabbedPanelsContentGroup -->

      </div>
      <!-- close TabbedPanels1 -->

    </td>
    #end
  </tr>
</table>

<script language="javascript" type="text/javascript">
#if (($idFail != ''
	&& $idStatus != '1'
	&& $idStatus != '6'
	&& $idStatus != '12'
	&& $idStatus != '63'
	&& $idStatus != '65')
	|| $!page == "4")
	var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$selectedTab});
#end

</script>

<script>
	//pajakan/perjanjian/index.jsp
	document.${formName}.actionPajakan.value = "paparpajakan";

	function doChangeNegeri(){
		doAjaxCall${formName}('doChangeNegeri');
	}
	/* 2017/05/12
	function doChangeTab(tabId) {
		document.${formName}.selectedTab.value = tabId;
		document.${formName}.mode.value = "view";
		doAjaxCall${formName}("");

	}*/
	function seterusnya(){
		//document.${formName}.actionPajakan.value = "";
		//document.${formName}.hitButton.value = "seterusnya";seterusnyaperjanjian
		document.${formName}.hitButton.value = "seterusnyaperjanjian";
		doAjaxCall${formName}("");

	}
	//MOA perjanjian/index
	function KemaskiniMOA(){
		//document.${formName}.actionPajakan.value = "papar";
		document.${formName}.mode.value = "update";
		doAjaxCall${formName}("");
	}
	function batalMOA_(){
		//document.${formName}.actionPajakan.value = "papar";
		document.${formName}.mode.value = "view";
		doAjaxCall${formName}("");
	}
	function SimpanMOA(){
		/*
		if(document.${formName}.txdTarikhTerimaMOA.value == ""){
			alert('Sila masukkan Tarikh Terima MOA.');
	  		document.${formName}.txdTarikhTerimaMOA.focus();
			return;
		}
		if(document.${formName}.txdTarikhTandatanganPTP.value == ""){
			alert('Sila masukkan Tarikh Tandatangan PTP.');
	  		document.${formName}.txdTarikhTandatanganPTP.focus();
			return;
		}
		if(document.${formName}.txdTarikhKembaliMOA.value == ""){
			alert('Sila masukkan Tarikh MOA Dikembalikan');
	  		document.${formName}.txdTarikhKembaliMOA.focus();
			return;
		}
		if(document.${formName}.txdTarikhDaftarMOA.value == ""){
			alert('Sila masukkan Tarikh MOA didaftarkan .');
	  		document.${formName}.txdTarikhDaftarMOA.focus();
			return;
		}
		if(document.${formName}.txdTarikhBayaranMOA.value == ""){
			alert('Sila masukkan Tarikh Bayaran MOA.');
	  		document.${formName}.txdTarikhBayaranMOA.focus();
			return;
		}
		if(document.${formName}.txtNoPerjanjianMOA.value == ""){
			alert('Sila masukkan No Perjanjian MOA.');
	  		document.${formName}.txtNoPerjanjianMOA.focus();
			return;
		}
		*/

		if ( !window.confirm("Adakah Anda Pasti ?") ){
			//document.${formName}.actionPajakan.value = "papar";
			return;
		}

		document.${formName}.mode.value = "view";
		document.${formName}.hitButton.value = "saveMOA";
		doAjaxCall${formName}("");

	}
	//15A
	function kemaskini15A_(){
		//document.${formName}.actionPajakan.value = "papar";
		//document.${formName}.actionPajakan.value = "paparperjanjian";
		document.${formName}.mode.value = "update";
		doAjaxCall${formName}("");

	}
	function batal15A()_{
		//document.${formName}.actionPajakan.value = "papar";
		//document.${formName}.actionPajakan.value = "paparperjanjian";
		document.${formName}.mode.value = "view";
		doAjaxCall${formName}("");

	}
	function Simpan15A(){
		/*
		if(document.${formName}.txdTarikhTerimaPemohon.value == ""){
			alert('Sila masukkan Tarikh Terima Pemohon.');
	  		document.${formName}.txdTarikhTerimaPemohon.focus();
			return;
		}
		if(document.${formName}.txdTarikhTandatanganPTP15A.value == ""){
			alert('Sila masukkan Tarikh Tandatangan PTP.');
	  		document.${formName}.txdTarikhTandatanganPTP15A.focus();
			return;
		}
		if(document.${formName}.txdTarikhHantarPemohon.value == ""){
			alert('Sila masukkan Tarikh Hantar Pemohon');
	  		document.${formName}.txdTarikhHantarPemohon.focus();
			return;
		}
		if(document.${formName}.txdTarikhDaftarPajakan.value == ""){
			alert('Sila masukkan Tarikh Daftar Pajakan .');
	  		document.${formName}.txdTarikhDaftarPajakan.focus();
			return;
		}
		if(document.${formName}.txdtarikhTerimaHakmilik.value == ""){
			alert('Sila masukkan Tarikh Terima Hakmilik.');
	  		document.${formName}.txdtarikhTerimaHakmilik.focus();
			return;
		}
		if(document.${formName}.txdTarikhKemaskiniHakmilik.value == ""){
			alert('Sila masukkan Tarikh Kemaskini Hakmilik.');
	  		document.${formName}.txdTarikhKemaskiniHakmilik.focus();
			return;
		}
		*/

		if ( !window.confirm("Adakah Anda Pasti ?") ){
			//document.${formName}.actionPajakan.value = "papar";
			//document.${formName}.actionPajakan.value = "paparperjanjian";
			return;
		}

		document.${formName}.mode.value = "view";
		document.${formName}.hitButton.value = "save15A";
		doAjaxCall${formName}("");

	}
	//JEMAAH MENTERI
	function KemaskiniJM(){
		document.${formName}.actionPajakan.value = "papar";
		document.${formName}.mode.value = "update";
		doAjaxCall${formName}("");
	}
	function batalJM(){
		document.${formName}.actionPajakan.value = "papar";
		document.${formName}.mode.value = "view";
		doAjaxCall${formName}("");
	}
	function SimpanJM(){
		/*
		if(document.${formName}.txdTarikhPerbadanan.value == ""){
			alert('Sila masukkan Tarikh Perbadanan Berkuatkuasa .');
	  		document.${formName}.txdTarikhPerbadanan.focus();
			return;
		}
		if(document.${formName}.txdtarikhMesyuarat.value == ""){
			alert('Sila masukkan Tarikh Mesyuarat.');
	  		document.${formName}.txdtarikhMesyuarat.focus();
			return;
		}
		if(document.${formName}.txtNoMemorandum.value == ""){
			alert('Sila masukkan No Memorandum.');
	  		document.${formName}.txtNoMemorandum.focus();
			return;
		}
		*/

		if ( !window.confirm("Adakah Anda Pasti ?") ){
			document.${formName}.actionPajakan.value = "papar";
			return;
		}

		document.${formName}.mode.value = "view";
		document.${formName}.hitButton.value = "saveJM";
		doAjaxCall${formName}("");
	}
	//PERJANJIAN
	//Skrin Senarai, Button [Daftar Baru]
	function daftarBaruDraf()_index{
		//document.${formName}.actionPajakan.value = "papar";
		//document.${formName}.actionPajakan.value = "paparperjanjian";
		document.${formName}.mode.value = "newDraf";
		doAjaxCall${formName}("");

	}
	//perjanjian/index
	//Skrin Senarai, Button [Daftar Baru]
	function daftarBaruPerjanjian_index(){
		//document.${formName}.actionPajakan.value = "papar";
		//document.${formName}.actionPajakan.value = "paparperjanjian";
		document.${formName}.mode.value = "newDraf";
		doAjaxCall${formName}("");

	}
	//Skrin Senarai, link [Tarikh Terima]
	function paparDraf(idDraf){
		document.${formName}.idDraf.value = idDraf;
		document.${formName}.mode.value = "viewDraf";
		//document.${formName}.actionPajakan.value = "papar";
		//document.${formName}.actionPajakan.value = "paparperjanjian";
		doAjaxCall${formName}("");

	}
	//Skrin Senarai |Maklumat Perjanjian , Button [Hapus]
	function HapusDraf(){
		document.${formName}.mode.value = "viewDraf";
		//document.${formName}.actionPajakan.value = "papar";
		//document.${formName}.actionPajakan.value = "paparperjanjian";
		//document.${formName}.hitButton.value = "hapusDraf";
		document.${formName}.hitButton.value = "hapusdrafper";
		doAjaxCall${formName}("");

	}
	// Skrin Maklumat Perjanjian, Button [Simpan]
	function simpanDraf(){
		if(document.${formName}.txdTarikhHantarDraf.value == ""){
			alert('Sila masukkan Tarikh Hantar Draf.');
	  		document.${formName}.txdTarikhHantarDraf.focus();
			return;
		}
	/*	if(document.${formName}.txdTarikhTerimaDraf.value == ""){
		alert('Sila masukkan Tarikh Terima Draf.');
  		document.${formName}.txdTarikhTerimaDraf.focus();
		return;
	}
	if(document.${formName}.txdTarikhHantarPKP.value == ""){
		alert('Sila masukkan Tarikh Hantar PKP');
  		document.${formName}.txdTarikhHantarPKP.focus();
		return;
	}
	if(document.${formName}.txdTarikhTerimaPKP.value == ""){
		alert('Sila masukkan Tarikh Terima PKP.');
  		document.${formName}.txdTarikhTerimaPKP.focus();
		return;
	}*/

		var StarikhHantarDraf = document.getElementById("txdTarikhHantarDraf").value;
		var StarikhTerimaDraf = document.getElementById("txdTarikhTerimaDraf").value;
		var StarikhHantarPKP = document.getElementById("txdTarikhHantarPKP").value;
		var StarikhTerimaPKP = document.getElementById("txdTarikhTerimaPKP").value;

		var today = new Date();

		//for StarikhHantarDraf
		var STHDDate  = parseInt(StarikhHantarDraf.substring(0,2),10);
	    var STHDmon = parseInt(StarikhHantarDraf.substring(3,5),10)-1;
	    var STHDyr  = parseInt(StarikhHantarDraf.substring(6,10),10);

		//for StarikhTerimaDraf
		var STTDDate  = parseInt(StarikhTerimaDraf.substring(0,2),10);
	    var STTDmon = parseInt(StarikhTerimaDraf.substring(3,5),10)-1;
	    var STTDyr  = parseInt(StarikhTerimaDraf.substring(6,10),10);

		//for StarikhHantarPKP
		var STHPKPDate  = parseInt(StarikhHantarPKP.substring(0,2),10);
	    var STHPKPmon = parseInt(StarikhHantarPKP.substring(3,5),10)-1;
	    var STHPKPyr  = parseInt(StarikhHantarPKP.substring(6,10),10);

		//for StarikhTerimaPKP
		var STTPKPDate  = parseInt(StarikhTerimaPKP.substring(0,2),10);
	    var STTPKPmon = parseInt(StarikhTerimaPKP.substring(3,5),10)-1;
	    var STTPKPyr  = parseInt(StarikhTerimaPKP.substring(6,10),10);

		var tarikhHantarDraf = new Date(STHDyr,STHDmon,STHDDate);
		var tarikhTerimaDraf = new Date(STTDyr,STTDmon,STTDDate);
		var tarikhHantarPKP = new Date(STHPKPyr,STHPKPmon,STHPKPDate);
		var tarikhTerimaPKP = new Date(STTPKPyr,STTPKPmon,STTPKPDate);

		if(tarikhHantarDraf > today){
			alert('Tarikh Hantar Draf mestilah tidak melebihi dari Hari ini.');
	  		document.${formName}.txdTarikhHantarDraf.focus();
			return;
		}

		if(tarikhTerimaDraf > today){
			alert('Tarikh Terima Draf mestilah tidak melebihi dari Hari ini.');
	  		document.${formName}.txdTarikhTerimaDraf.focus();
			return;
		}
		if(tarikhHantarPKP > today){
			alert('Tarikh Hantar PKP mestilah tidak melebihi dari Hari ini.');
	  		document.${formName}.txdTarikhHantarPKP.focus();
			return;
		}
		if(tarikhTerimaPKP > today){
			alert('Tarikh Terima PKP mestilah tidak melebihi dari Hari ini.');
	  		document.${formName}.txdTarikhTerimaPKP.focus();
			return;
		}

		if ( !window.confirm("Adakah Anda Pasti ?") ){
			//document.${formName}.actionPajakan.value = "papar";
			//document.${formName}.actionPajakan.value = "paparperjanjian";
			return;
		}

		document.${formName}.mode.value = "viewDraf";
		//document.${formName}.hitButton.value = "saveDraf";
		document.${formName}.hitButton.value = "savedrafper";
		doAjaxCall${formName}("");

	}
	// Skrin Maklumat Perjanjian, Button [Batal]|[Kembali]
	function batalDraf(){
		document.${formName}.mode.value = "viewDraf";
		//document.${formName}.actionPajakan.value = "papar";
		//document.${formName}.actionPajakan.value = "paparperjanjian";
		doAjaxCall${formName}("");

	}
	// Skrin Maklumat Perjanjian, Button [Kemaskini]
	function kemaskiniPerjanjian(){
		//document.${formName}.actionPajakan.value = "papar";
		//document.${formName}.actionPajakan.value = "paparperjanjian";
		document.${formName}.mode.value = "updateDraf";
		doAjaxCall${formName}("");

	}
	// Skrin Maklumat Perjanjian, Button [Simpan] (semasa kemaskini)
	function simpanKemaskini(){
		if(document.${formName}.txdTarikhHantarDraf.value == ""){
			alert('Sila masukkan Tarikh Hantar Draf.');
	  		document.${formName}.txdTarikhHantarDraf.focus();
			return;
		}
	/*	if(document.${formName}.txdTarikhTerimaDraf.value == ""){
			alert('Sila masukkan Tarikh Terima Draf.');
	  		document.${formName}.txdTarikhTerimaDraf.focus();
			return;
		}
		if(document.${formName}.txdTarikhHantarPKP.value == ""){
			alert('Sila masukkan Tarikh Hantar PKP');
	  		document.${formName}.txdTarikhHantarPKP.focus();
			return;
		}
		if(document.${formName}.txdTarikhTerimaPKP.value == ""){
			alert('Sila masukkan Tarikh Terima PKP.');
	  		document.${formName}.txdTarikhTerimaPKP.focus();
			return;
		}*/

		var StarikhHantarDraf = document.getElementById("txdTarikhHantarDraf").value;
		var StarikhTerimaDraf = document.getElementById("txdTarikhTerimaDraf").value;
		var StarikhHantarPKP = document.getElementById("txdTarikhHantarPKP").value;
		var StarikhTerimaPKP = document.getElementById("txdTarikhTerimaPKP").value;

		var today = new Date();

		//for StarikhHantarDraf
		var STHDDate  = parseInt(StarikhHantarDraf.substring(0,2),10);
	    var STHDmon = parseInt(StarikhHantarDraf.substring(3,5),10)-1;
	    var STHDyr  = parseInt(StarikhHantarDraf.substring(6,10),10);

		//for StarikhTerimaDraf
		var STTDDate  = parseInt(StarikhTerimaDraf.substring(0,2),10);
	    var STTDmon = parseInt(StarikhTerimaDraf.substring(3,5),10)-1;
	    var STTDyr  = parseInt(StarikhTerimaDraf.substring(6,10),10);

		//for StarikhHantarPKP
		var STHPKPDate  = parseInt(StarikhHantarPKP.substring(0,2),10);
	    var STHPKPmon = parseInt(StarikhHantarPKP.substring(3,5),10)-1;
	    var STHPKPyr  = parseInt(StarikhHantarPKP.substring(6,10),10);

		//for StarikhTerimaPKP
		var STTPKPDate  = parseInt(StarikhTerimaPKP.substring(0,2),10);
	    var STTPKPmon = parseInt(StarikhTerimaPKP.substring(3,5),10)-1;
	    var STTPKPyr  = parseInt(StarikhTerimaPKP.substring(6,10),10);

		var tarikhHantarDraf = new Date(STHDyr,STHDmon,STHDDate);
		var tarikhTerimaDraf = new Date(STTDyr,STTDmon,STTDDate);
		var tarikhHantarPKP = new Date(STHPKPyr,STHPKPmon,STHPKPDate);
		var tarikhTerimaPKP = new Date(STTPKPyr,STTPKPmon,STTPKPDate);

		if(tarikhHantarDraf > today){
			alert('Tarikh Hantar Draf mestilah tidak melebihi dari Hari ini.');
	  		document.${formName}.txdTarikhHantarDraf.focus();
			return;
		}

		if(tarikhTerimaDraf > today){
			alert('Tarikh Terima Draf mestilah tidak melebihi dari Hari ini.');
	  		document.${formName}.txdTarikhTerimaDraf.focus();
			return;
		}
		if(tarikhHantarPKP > today){
			alert('Tarikh Hantar PKP mestilah tidak melebihi dari Hari ini.');
	  		document.${formName}.txdTarikhHantarPKP.focus();
			return;
		}
		if(tarikhTerimaPKP > today){
			alert('Tarikh Terima PKP mestilah tidak melebihi dari Hari ini.');
	  		document.${formName}.txdTarikhTerimaPKP.focus();
			return;
		}

		if ( !window.confirm("Adakah Anda Pasti ?") ){
			//document.${formName}.actionPajakan.value = "papar";
			return;
		}

		document.${formName}.mode.value = "viewDraf";
		//document.${formName}.actionPajakan.value = "papar";
		//document.${formName}.actionPajakan.value = "paparperjanjian";
		//document.${formName}.hitButton.value = "saveUpdateDraf";
		document.${formName}.hitButton.value = "saveupdatedrafper";
		doAjaxCall${formName}("");

	}
	function batalPerjanjian(){
		document.${formName}.mode.value = "viewDraf";
		//document.${formName}.actionPajakan.value = "papar";
		//document.${formName}.actionPajakan.value = "paparperjanjian";
		doAjaxCall${formName}("");

	}
	//ULASAN PAJAKAN
	function daftarBaruPajakan(){
		//document.${formName}.actionPajakan.value = "papar";
		document.${formName}.actionPajakan.value = "paparpajakan";
		document.${formName}.mode.value = "newPajakan";
		doAjaxCall${formName}("");

	}
	function SimpanPajakan(){
		if(document.${formName}.txdTarikhTandatangan.value == ""){
			alert('Sila masukkan Tarikh Tandatangan.');
	  		document.${formName}.txdTarikhTandatangan.focus();
			return;
		}
	/*if(document.${formName}.txdTarikhMulaPajakan.value == ""){
		alert('Sila masukkan Tarikh Mula Pajakan.');
  		document.${formName}.txdTarikhMulaPajakan.focus();
		return;
	}
	if(document.${formName}.txdTarikhTamatPajakan.value == ""){
		alert('Sila masukkan Tarikh Tamat Pajakan');
  		document.${formName}.txdTarikhTamatPajakan.focus();
		return;
	}
	if(document.${formName}.txtTempoh.value == ""){
		alert('Sila masukkan Tempoh Pajakan.');
  		document.${formName}.txtTempoh.focus();
		return;
	}
	*/
		if(document.${formName}.socNotifikasiPeringatan.value != "" && document.${formName}.txdTarikhTamatPajakan.value == ""){
			alert('Sila pastikan Tarikh Tamat Pajakan dimasukkan sebelum memilih Papar Peringatan.');
	  		document.${formName}.txdTarikhTandatangan.focus();
			return;
		}
		if ( !window.confirm("Adakah Anda Pasti ?") ){
			//document.${formName}.actionPajakan.value = "papar";
			//document.${formName}.actionPajakan.value = "paparperjanjian";
			return;

		}

		document.${formName}.mode.value = "view";
		document.${formName}.hitButton.value = "savePajakan";
		doAjaxCall${formName}("");

	}
	function batalPajakan(){
		document.${formName}.mode.value = "view";
		//document.${formName}.actionPajakan.value = "papar";
		//document.${formName}.actionPajakan.value = "paparperjanjian";
		doAjaxCall${formName}("");

	}
	/* 2017/05/12
	function KemaskiniPajakan(){
		//document.${formName}.actionPajakan.value = "papar";
		//document.${formName}.actionPajakan.value = "paparperjanjian";
		document.${formName}.mode.value = "updatePajakan";
		doAjaxCall${formName}("");

	}*/
	function SimpanUpdatePajakan(){
		if(document.${formName}.txdTarikhTandatangan.value == ""){
			alert('Sila masukkan Tarikh Tandatangan.');
	  		document.${formName}.txdTarikhTandatangan.focus();
			return;
		}
	/*	if(document.${formName}.txdTarikhMulaPajakan.value == ""){
			alert('Sila masukkan Tarikh Mula Pajakan.');
	  		document.${formName}.txdTarikhMulaPajakan.focus();
			return;
		}
		if(document.${formName}.txdTarikhTamatPajakan.value == ""){
			alert('Sila masukkan Tarikh Tamat Pajakan');
	  		document.${formName}.txdTarikhTamatPajakan.focus();
			return;
		}
		if(document.${formName}.txtTempoh.value == ""){
			alert('Sila masukkan Tempoh Pajakan.');
	  		document.${formName}.txtTempoh.focus();
			return;
		}
		*/
		if(document.${formName}.socNotifikasiPeringatan.value != "" && document.${formName}.txdTarikhTamatPajakan.value == ""){
			alert('Sila pastikan Tarikh Tamat Pajakan dimasukkan sebelum memilih Papar Peringatan.');
	  		document.${formName}.txdTarikhTandatangan.focus();
			return;
		}
		if ( !window.confirm("Adakah Anda Pasti ?") ){
			//document.${formName}.actionPajakan.value = "papar";
			//document.${formName}.actionPajakan.value = "paparperjanjian";
			return;
		}

		document.${formName}.hitButton.value = "saveUpdatePajakan";
		document.${formName}.mode.value = "viewPajakan";
		doAjaxCall${formName}("");

	}
	function batalUpdatePajakan(){
		document.${formName}.mode.value = "viewPajakan";
		//document.${formName}.actionPajakan.value = "papar";
		//document.${formName}.actionPajakan.value = "paparperjanjian";
		doAjaxCall${formName}("");

	}
	/* 2017/05/12
	function paparPajakan(idPajakan){
		document.${formName}.idPajakan.value = idPajakan;
		document.${formName}.mode.value = "viewPajakan";
		//document.${formName}.actionPajakan.value = "papar";
		//document.${formName}.actionPajakan.value = "paparperjanjian";
		doAjaxCall${formName}("");

	}*/
	function HapusPajakan(){
		if (window.confirm("Adakah Anda Pasti ?") ){
			document.${formName}.mode.value = "view";
			//document.${formName}.actionPajakan.value = "papar";
			//document.${formName}.actionPajakan.value = "paparperjanjian";
			document.${formName}.hitButton.value = "hapusPajakan";
			doAjaxCall${formName}("");

		}

	}

	function cal_tarikh_luput(){
	  var tr = document.${formName}.txdTarikhMulaPajakan.value;
	  var tempoh = document.${formName}.txtTempoh.value;

	  var luput = "";
	  if((tr!="" && tr!=null) && (tempoh!="" && tempoh!=null)){
	  	var temp_tr = tr.substring(0,6)
	  	var year_cur = tr.substring(6,10)
		luput = temp_tr+(parseInt(year_cur)+parseInt(tempoh));
	  }

	  document.${formName}.txdTarikhTamatPajakan.value = luput;

	}
	function validateNumber(elmnt,content) {
		//if it is character, then remove it..
		if (isNaN(content)) {
			elmnt.value = RemoveNonNumeric(content);
			return;
		}
	}
	function RemoveNonNumeric( strString ){
	      var strValidCharacters = "1234567890";
	      var strReturn = "";
	      var strBuffer = "";
	      var intIndex = 0;
	      // Loop through the string
	      for( intIndex = 0; intIndex < strString.length; intIndex++ ){
	            strBuffer = strString.substr( intIndex, 1 );
	            // Is this a number
	            if( strValidCharacters.indexOf( strBuffer ) > -1 )
	            {
	                  strReturn += strBuffer;
	            }
	      }
	      return strReturn;

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
		}else {
			elmnt.value ="";
			return;
		}
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
	document.${formName}.action = "?_portal_module=ekptg.view.htp.pajakan.FrmPajakanPendaftaranView&idPermohonan="+permohonan+"&actionPajakan=BayaranPajakan";
	document.${formName}.mode.value = "view";
	document.${formName}.submit();
}

function langkah3(permohonan,idFail){
	url = "../servlet/ekptg.view.htp.FrmPajakanServlet?command=papar&idFail="+idFail;
	actionName = "setSessionIdFail";
	target = "setSessionIdFail_result";
	doAjaxUpdater(document.${formName}, url, target, actionName);
	document.${formName}.action = "?_portal_module=ekptg.view.htp.pajakan.FrmPajakanMJMView&idPermohonan="+permohonan+"&actionPajakan=paparan";
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
*/
	function kiraCukai(){
		document.${formName}.txtKadarCukai.value = document.${formName}.txtKadarCukaitemp.value;
	}
	//PENAMBAHBAIKAN FASA3. POPUP/NOTIFIKASI PERINGATAN SEMULA / LUPUT (SYAZ, 27112014)
	function doCheckTarikhTamat(){
		var date = document.${formName}.txdTarikhTamatPajakan.value;

		if(date=="" || date=="NaN"){
			alert('Sila masukkan Tarikh Tamat Pajakan terlebih dahulu.');
			document.${formName}.socNotifikasiPeringatan.value = "";
	  		document.${formName}.txdTarikhTamatPajakan.focus();
			return;
		}

	}

</script>
##parse("app/htp/utiliti/javascript/javaScriptPajakanMaklumat.jsp")
##parse("app/htp/utiliti/javascript/javaScriptPajakanPerjanjian.jsp")
##parse("app/htp/utiliti/javascript/javaScriptPajakan.jsp")
##parse("app/htp/utiliti/javaScriptUmum.jsp")
