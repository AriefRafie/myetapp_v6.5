<p>
  <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>

  <input name="mode" type="hidden" id="mode" value="$mode"/>
  <input name="hitButton" type="hidden" id="hitButton" value="$hitButton"/>
  
  <input name="idFail" type="hidden" id="idFail" value="$idFail"/>
  <input name="idStatus" type="hidden" id="idStatus" value="$idStatus"/>
  <input name="selectedTab" type="hidden" id="selectedTab" value="$selectedTab"/>
  <input name="idPermohonan" type="hidden" id="idPermohonan" value="$idPermohonan"/>
  <input type="hidden" name="actionPajakan" value="$actionPajakan"/>
  <input type="hidden" name="subUrusan" value="$subUrusan"/>
  <input name="idDraf" type="hidden" id="idDraf" value="$idDraf"/>
  <input name="idPajakan" type="hidden" id="idPajakan" value="$idPajakan"/>
</p>

<table width="100%" border="0">
 #if ($idFail != '')
  <tr>
    <td>#parse("app/htp/frmPajakanHeader.jsp")</td>
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
  #if ($idFail != '' && $idStatus != '1' && $idStatus != '6' && $idStatus != '12' && $idStatus != '63' && $idStatus != '65')
  <tr>
    <td>
       <div id="TabbedPanels1" class="TabbedPanels">
            

              <ul class="TabbedPanelsTabGroup">
              
              <!-- -->
              #if($subUrusan != '17')
              
                <li class="TabbedPanelsTab" title="Draf Perjanjian" onclick="doChangeTab(0)" tabindex="0">DRAF PERJANJIAN II</li>
                <li class="TabbedPanelsTab" title="Maklumat Pajakan" onclick="doChangeTab(1)" tabindex="0">MAKLUMAT PAJAKAN</li>
                <li class="TabbedPanelsTab" title="MOA"  onclick="doChangeTab(2)" tabindex="0" style="display:none">MOA</li>
                <li class="TabbedPanelsTab" title="Borang 15A"  onclick="doChangeTab(3)" tabindex="0">BORANG (15A / L / LX11)</li>
                <li class="TabbedPanelsTab" title="Kelulusan Jemaah Menteri"  onclick="doChangeTab(4)" tabindex="0" style="display:none">KELULUSAN JEMAAH MENTERI</li>
                
              #else
              
              <li class="TabbedPanelsTab" title="Draf Perjanjian" onclick="doChangeTab(0)" tabindex="0">DRAF PERJANJIAN II</li>
                <li class="TabbedPanelsTab" title="Maklumat Pajakan" onclick="doChangeTab(1)" tabindex="0">MAKLUMAT PAJAKAN</li>
                
                <li class="TabbedPanelsTab" title="MOA"  onclick="doChangeTab(2)" tabindex="0">MOA</li>
                
                <li class="TabbedPanelsTab" title="Borang 15A"  onclick="doChangeTab(3)" tabindex="0">BORANG (15A / L / LX11)</li>
             #end   
                
              </ul>
              
              
              <div class="TabbedPanelsContentGroup">
              
                <div class="TabbedPanelsContent">
                <!-- content draf perjanjian -->
					#if ($selectedTab == '0')
                		#parse ("app/htp/frmPajakanTabDrafPerjanjianSenarai.jsp")
             		#end
                </div>
                    <!-- close content draf perjanjian -->
                    
                <div class="TabbedPanelsContent">
                <!-- content maklumat pajakan -->
					#if ($selectedTab == '1')
                		#parse ("app/htp/frmPajakanTabMaklumatPajakan.jsp")
					#end               	
              </div>
              <!-- close content maklumat pajakan -->
              
                <div class="TabbedPanelsContent">
                <!-- content MOA -->
					#if ($selectedTab == '2')
	                	#parse ("app/htp/frmPajakanTabMOA.jsp")
					#end               	
              </div>
              <!-- close content MOA -->
              
              <div class="TabbedPanelsContent">
                <!-- content Borang 15A -->
					#if ($selectedTab == '3')
						#parse ("app/htp/frmPajakanTabBorang15A.jsp")
					#end               	
              </div>
              <!-- close content Borang 15A -->
              
              <div class="TabbedPanelsContent">
                <!-- content Kelulusan Jemaah Menteri -->
					#if ($selectedTab == '4')
	                	#parse ("app/htp/frmPajakanTabKelulusanJemaahMenteri.jsp")
					#end               	
              </div>
              <!-- close content Kelulusan Jemaah Menteri -->
              
         </div>
         <!-- close TabbedPanelsContentGroup -->
              
      </div>
      <!-- close TabbedPanels1 -->
      
    </td>
    #end
  </tr>
</table>

<script language="javascript" type="text/javascript">
#if ($idFail != '' && $idStatus != '1' && $idStatus != '6' && $idStatus != '12' && $idStatus != '63' && $idStatus != '65')
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$selectedTab});
#end
</script>


<script>
function doChangeNegeri(){
	doAjaxCall${formName}('doChangeNegeri');
}
function doChangeTab(tabId) {
	document.${formName}.selectedTab.value = tabId;
	document.${formName}.mode.value = "view";
	doAjaxCall${formName}("");
}
function seterusnya(){
	document.${formName}.actionPajakan.value = "";
	document.${formName}.hitButton.value = "seterusnya";
	doAjaxCall${formName}("");
}

//MOA
function KemaskiniMOA(){
	document.${formName}.actionPajakan.value = "papar";
	document.${formName}.mode.value = "update";
	doAjaxCall${formName}("");
}
function batalMOA(){
	document.${formName}.actionPajakan.value = "papar";
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
		document.${formName}.actionPajakan.value = "papar";
		return;
	}
	
	
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "saveMOA";
	doAjaxCall${formName}("");
}

//15A
function Kemaskini15A(){
	document.${formName}.actionPajakan.value = "papar";
	document.${formName}.mode.value = "update";
	doAjaxCall${formName}("");
}
function batal15A(){
	document.${formName}.actionPajakan.value = "papar";
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
		document.${formName}.actionPajakan.value = "papar";
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

//ULASAN DRAF
function daftarBaruDraf(){
	document.${formName}.actionPajakan.value = "papar";
	document.${formName}.mode.value = "newDraf";
	doAjaxCall${formName}("");
}
function SimpanDraf(){

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
		document.${formName}.actionPajakan.value = "papar";
		return;
	}

	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "saveDraf";
	doAjaxCall${formName}("");
}
function batalDraf(){
	document.${formName}.mode.value = "view";
	document.${formName}.actionPajakan.value = "papar";
	doAjaxCall${formName}("");
}
function KemaskiniDraf(){
	document.${formName}.actionPajakan.value = "papar";
	document.${formName}.mode.value = "updateDraf";
	doAjaxCall${formName}("");
}
function SimpanUpdateDraf(){
	
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
		document.${formName}.actionPajakan.value = "papar";
		return;
	}
	
	
	document.${formName}.mode.value = "viewDraf";
	document.${formName}.actionPajakan.value = "papar";
	document.${formName}.hitButton.value = "saveUpdateDraf";
	doAjaxCall${formName}("");
}
function batalUpdateDraf(){
	document.${formName}.mode.value = "viewDraf";
	document.${formName}.actionPajakan.value = "papar";
	doAjaxCall${formName}("");
}
function paparDraf(idDraf){
	document.${formName}.idDraf.value = idDraf;
	document.${formName}.mode.value = "viewDraf";
	document.${formName}.actionPajakan.value = "papar";
	doAjaxCall${formName}("");
}
function HapusDraf(){
	document.${formName}.mode.value = "view";
	document.${formName}.actionPajakan.value = "papar";
	document.${formName}.hitButton.value = "hapusDraf";
	doAjaxCall${formName}("");
}

//ULASAN PAJAKAN
function daftarBaruPajakan(){
	document.${formName}.actionPajakan.value = "papar";
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
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.actionPajakan.value = "papar";
		return;
	}
	

	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "savePajakan";
	doAjaxCall${formName}("");
}
function batalPajakan(){
	document.${formName}.mode.value = "view";
	document.${formName}.actionPajakan.value = "papar";
	doAjaxCall${formName}("");
}
function KemaskiniPajakan(){
	document.${formName}.actionPajakan.value = "papar";
	document.${formName}.mode.value = "updatePajakan";
	doAjaxCall${formName}("");
}
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
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.actionPajakan.value = "papar";
		return;
	}
	
	document.${formName}.mode.value = "viewPajakan";
	document.${formName}.hitButton.value = "saveUpdatePajakan";
	doAjaxCall${formName}("");
}
function batalUpdatePajakan(){
	document.${formName}.mode.value = "viewPajakan";
	document.${formName}.actionPajakan.value = "papar";
	doAjaxCall${formName}("");
}
function paparPajakan(idPajakan){
	document.${formName}.idPajakan.value = idPajakan;
	document.${formName}.mode.value = "viewPajakan";
	document.${formName}.actionPajakan.value = "papar";
	doAjaxCall${formName}("");
}
function HapusPajakan(){
	document.${formName}.mode.value = "view";
	document.${formName}.actionPajakan.value = "papar";
	document.${formName}.hitButton.value = "hapusPajakan";
	doAjaxCall${formName}("");
}
function HapusDraf(id){
	document.${formName}.idDraf.value = id;
	document.${formName}.mode.value = "view";
	document.${formName}.actionPajakan.value = "papar";
	document.${formName}.hitButton.value = "hapusDraf";
	doAjaxCall${formName}("");
}
function cal_tarikh_luput(){
  var tr = document.${formName}.txdTarikhMulaPajakan.value;
  var temp_tr = tr.substring(0,6)
  var year_cur = tr.substring(6,10)
  var tempoh = document.${formName}.txtTempoh.value; 
  var luput = temp_tr+(parseInt(year_cur)+parseInt(tempoh));
  document.${formName}.txdTarikhTamatPajakan.value = luput;
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
} 
else {
	elmnt.value ="";
	return;
	}
}


</script>