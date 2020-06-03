<style type="text/css">
<!--
.style1 {
	color: #FF0000
}
.style2 {
	color: #0000FF
}
-->
</style>

<p>

	  
  <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
  <input name="mode" type="hidden" id="mode" value="$mode"/>
  <input name="flagPopup" type="hidden" id="flagPopup" value="$flagPopup"/>
  <input name="modePopup" type="hidden" id="modePopup" value="$modePopup"/>
  <input name="selectedTabUpper" type="hidden" id="selectedTabUpper" value="$selectedTabUpper"/>
  <input name="hitButton" type="hidden" id="hitButton"/>  
  <input name="idNegeriPemohon" type="hidden" id="idNegeriPemohon" value="$idNegeriPemohon"/>
  <input name="idFail" type="hidden" id="idFail" value="$idFail"/>
  <input name="idPermohonan" type="hidden" id="idPermohonan" value="$idPermohonan"/>
  <input name="idPemohon" type="hidden" id="idPemohon" value="$idPemohon"/>
  <input name="idStatus" type="hidden" id="idStatus" value="$idStatus"/>
  <input name="idHakmilik" type="hidden" id="idHakmilik" value="$idHakmilik"/>
  <input name="idPermohonanPelepasan" type="hidden" id="idPermohonanPelepasan" value="$idPermohonanPelepasan"/>  
  <input name="step" type="hidden" id="step" value="$step"/>
    <input name="idHakmilikPermohonan" type="hidden" id="idHakmilikPermohonan" value="$!idHakmilikPermohonan"/>
	<input type="hidden" name="idHakmilikAgensi" id="idHakmilikAgensi" value="$idHakmilikAgensi" />
	<input type="hidden" name="idHakmilikSementara" id="idHakmilikSementara" value="$idHakmilikSementara" />

  <input name="idKementerianPemohon" type="hidden" id="idKementerianPemohon" value="$idKementerianPemohon"/>
<!--   <input name="idNegeriPemohon" type="hidden" id="idNegeriPemohon" value="$idNegeriPemohon"/> -->
</p>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #if ($idFail != '')
  <tr>
    <td> #parse("app/php2/frmPNWHeader.jsp") </td>
  </tr>
  #else
  <tr>
    <td>&nbsp;
      <div class="warning">SILA PILIH FAIL DI SENARAI FAIL TERLEBIH DAHULU</div></td>
  </tr>
  #end

  #if ($idFail != '')
  <tr>
    <td><div id="TabbedPanels1" class="TabbedPanels">
        <ul class="TabbedPanelsTabGroup">
          <li onClick="doChangeTabUpper(0);" class="TabbedPanelsTab" tabindex="0">MAKLUMAT TANAH</li>
          <li onClick="doChangeTabUpper(1);" class="TabbedPanelsTab" tabindex="0">MAKLUMAT TANAH BERKAITAN</li>
          <li onClick="doChangeTabUpper(2);" class="TabbedPanelsTab" tabindex="0">MAKLUMAT PERMOHONAN</li>
          <li onClick="doChangeTabUpper(3);" class="TabbedPanelsTab" tabindex="0">SENARAI SEMAK</li>
          <li onClick="doChangeTabUpper(4);" class="TabbedPanelsTab" tabindex="0">MAKLUMAT LAMPIRAN</li>
        </ul>
        <div class="TabbedPanelsContentGroup">
          <div class="TabbedPanelsContent">#if ($flagBorangK == 'Y') #parse("app/php2/frmPLPMaklumatBorangK.jsp") #else #parse("app/php2/frmPLPMaklumatTanah.jsp") #end</div>
         
          <!-- START MAKLUMAT TANAH BERKAITAN -->
          <div class="TabbedPanelsContent">
            <table width="100%" border="0" cellspacing="2" cellpadding="2">
              <tr>
                <td><fieldset>
                  <legend><b>SENARAI TANAH BERKAITAN</b></legend>
                  <table align="center" width="100%">
                    <tr>
                      <td colspan="8" scope="row">
                      	<input name="cmdDaftar" type="button" value="Pilih Hakmilik" onClick="javascript:doDaftarHakmilik('$idPermohonan','$idKategoriPemohon','$idNegeriPemohon','$idKementerianPemohon')"/>
                        <input name="cmdDaftar" type="button" value="Pilih Borang K" onClick="javascript:doDaftarBorangK('$idPermohonan','$idKategoriPemohon','$idNegeriPemohon','$idKementerianPemohon')"/>
                      </td>
                    </tr>
                    <tr class="table_header">
                      <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
                      <td width="15%"><strong>Pegangan Hakmilik</strong></td>
                      <td width="10%"><strong>Lot</strong></td>
                      <td width="10%"><strong>No. Hakmilik</strong></td>
                      <td width="10%"><strong>No. Warta</strong></td>
                      <td width="15%"><strong>Mukim</strong></td>
                      <td width="15%"><strong>Daerah</strong></td>
                      <td width="15%"><strong>Negeri</strong></td>
                      <td width="5%"><strong></strong></td>
                    </tr>
                    #set ($senaraiTanahBerkaitan = "")
                    #if ($SenaraiTanahBerkaitan.size() > 0)
                    #foreach ($senaraiTanahBerkaitan in $SenaraiTanahBerkaitan)
                    #if ($senaraiTanahBerkaitan.bil == '')
                    #set( $row = "row1" )
                    #elseif (($senaraiTanahBerkaitan.bil % 2) != 0)
                    #set( $row = "row1" )
                    #else 
                    #set( $row = "row2" )
                    #end
                    <tr>
                      <td class="$row" align="center">$senaraiTanahBerkaitan.bil</td>
                      <td class="$row">$senaraiTanahBerkaitan.peganganHakmilik</td>
                      <td class="$row">$senaraiTanahBerkaitan.noLot</td>
                      <td class="$row">$senaraiTanahBerkaitan.noHakmilik</td>
                      <td class="$row">$senaraiTanahBerkaitan.noWarta</td>
                      <td class="$row">$senaraiTanahBerkaitan.mukim</td>
                      <td class="$row">$senaraiTanahBerkaitan.daerah</td>
                      <td class="$row">$senaraiTanahBerkaitan.negeri</td>
                      <td class="$row" align="center"><a href="#" class="style2" onClick="javascript:doHapus('$senaraiTanahBerkaitan.idHakmilikPermohonan')"><img border="0" src="../img/hapus.gif"/></a></td>
                    </tr>
                    #end
                    #else
                    <tr>
                      <td class="row1" align="center">&nbsp;</td>
                      <td class="row1">Tiada Rekod</td>
                      <td class="row1">&nbsp;</td>
                      <td class="row1">&nbsp;</td>
                      <td class="row1">&nbsp;</td>
                      <td class="row1">&nbsp;</td>
                      <td class="row1">&nbsp;</td>
                      <td class="row1">&nbsp;</td>
                      <td class="row1">&nbsp;</td>
                    </tr>
                    #end
                    <tr>
                      <td colspan="8">&nbsp;</td>
                    </tr>
                    #if ($flagPopup == '')
                    <tr>
                      <td colspan="8" align="center">
                        #if($idStatus == '1610198')
                        <input type="button" name="cmdSeterusnya" id="cmdHantar" value="Seterusnya" onClick="doSeterusnya()"/>
                        <input type="button" name="cmdBatalPermohonan" id="cmdBatalPermohonan" value="Batal Permohonan" onClick="gotoBatalPermohonan()"/>
                        #end
                        <input type="button" name="cdmCetak" id="cdmCetak" value="Cetak" onClick="javascript:setTable('tableReport')"/>
                        </td>
                    </tr>
                    #end
                  </table>
                  </fieldset></td>
              </tr>
            </table>
          </div>
          <!-- END MAKLUMAT TANAH BERKAITAN -->
         
          <div class="TabbedPanelsContent">
         	<br>
            #parse("app/php2/frmPNWMaklumatPermohonanPermohonan.jsp") 
          </div>
          
          <div class="TabbedPanelsContent">
         	<br>
            #parse("app/php2/frmTKRSenaraiSemak.jsp")
          </div>
          
          <div class="TabbedPanelsContent"> 
         	<br>
            #parse("app/php2/frmPNWPelan.jsp") 
         </div>
          
        </div>
      </div></td>
  </tr>
  #end
</table>
<fieldset id="tableReport" style="display:none;"-->
<legend><strong>SENARAI LAPORAN</strong></legend>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td ><a href="#" class="style2" onClick="javascript:cetakKulitFail('$idFail')"> Kulit Fail </a></td>
  </tr>
  <tr>
    <td ><a href="#" class="style2" onClick="javascript:cetakSuratAkuanTerima('$idFail')"> Surat Akuan Terima </a></td>
  </tr>
  #if ($flagBorangK != 'Y')
  #if ($statusRizab == 'MILIK')
  <tr>
    <td ><a href="#" class="style2" onClick="javascript:cetakMaklumatHakmilik('$idHTPHakmilik')"> Maklumat Tanah </a></td>
  </tr>
  #elseif ($statusRizab == 'RIZAB')
  <tr>
    <td ><a href="#" class="style2" onClick="javascript:cetakMaklumatRizab('$idHTPHakmilik')"> Maklumat Tanah </a></td>
  </tr>
  #end
  #end
</table>
</fieldset>
<script type="text/javascript">

function doChangeTab(tabId) {
	document.${formName}.mode.value = "view";
	document.${formName}.selectedTabUpper.value = tabId;
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";	
	document.${formName}.action = "?_portal_module=ekptg.view.php2.FrmPNWMaklumatPermohonanView";
	document.${formName}.method="POST";
	document.${formName}.submit();
}


#if ($idFail != '')
	var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$selectedTabUpper});
#end
</script>
<script>
function doChangeTabUpper(tabId) {
	document.${formName}.selectedTabUpper.value = tabId;
	document.${formName}.mode.value = "view";
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	doAjaxCall${formName}("");
}
function validateLuas(elmnt,content,content2) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = content2;
		return;
	}
	
	if(content != ""){
		var num = content * 1;
		elmnt.value = num.toFixed(5);
		return;
	} else {
		elmnt.value ="";
		return;
	}
}
function doChangeLuasKegunaan() {
	doAjaxCall${formName}("doChangeLuasKegunaan");
}
function doChangeLuas() {
	doAjaxCall${formName}("doChangeLuas");
}

function doKemaskini() {
	document.${formName}.mode.value = "update";
	doAjaxCall${formName}("");
}
function doBatalKemaskini() {
	document.${formName}.mode.value = "view";
	doAjaxCall${formName}("");
}
function doSimpanKemaskiniMaklumatPenawaran(idLuas) {
	//CHECK DATE   
	var str1  = document.${formName}.tarikhTerima.value;		   
	var dt1   = parseInt(str1.substring(0,2),10);
	var mon1  = parseInt(str1.substring(3,5),10)-1;
	var yr1   = parseInt(str1.substring(6,10),10);
	var tarikhTerima = new Date(yr1, mon1, dt1);
	
	var str2  =  document.${formName}.tarikhSurat.value;		   
	var dt2   = parseInt(str2.substring(0,2),10);
	var mon2  = parseInt(str2.substring(3,5),10)-1;
	var yr2   = parseInt(str2.substring(6,10),10);
	var tarikhSurat = new Date(yr2, mon2, dt2);
	
	var currentDate = new Date();
	
	if (tarikhTerima > currentDate){
		alert('Tarikh Terima tidak boleh melebihi dari tarikh hari ini.');
  		document.${formName}.tarikhTerima.focus(); 
		return;
	}
	if (tarikhSurat > currentDate){
		alert('Tarikh Surat tidak boleh melebihi dari tarikh hari ini.');
  		document.${formName}.tarikhSurat.focus(); 
		return;
	}
	if (tarikhSurat > tarikhTerima){
		alert('Tarikh Surat tidak boleh melebihi dari Tarikh Terima.');
  		document.${formName}.tarikhSurat.focus(); 
		return;
	}
	if(document.${formName}.tarikhTerima.value == ""){
		alert('Sila masukkan Tarikh Terima.');
  		document.${formName}.tarikhTerima.focus(); 
		return; 
	}
	if(document.${formName}.tarikhSurat.value == ""){
		alert('Sila masukkan Tarikh Surat.');
  		document.${formName}.tarikhSurat.focus(); 
		return; 
	}
	if(document.${formName}.txtPerkara.value == ""){
		alert('Sila masukkan Perkara.');
  		document.${formName}.txtPerkara.focus(); 
		return; 
	}
	if(document.${formName}.socLuasKegunaan.value == ""){
		alert('Sila masukkan Luas Kegunaan.');
  		document.${formName}.socLuasKegunaan.focus(); 
		return; 
	}
	if(document.${formName}.socLuasKegunaan.value == "2"){
		if(document.${formName}.socLuas.value == "0"){
			alert('Sila pilih Unit Luas.');
			document.${formName}.socLuas.focus(); 
			return; 
		}
	
		if(idLuas == '1' || idLuas == '2' || idLuas == '3' || idLuas == '5' || idLuas == '6' || idLuas == '9'){
			if(document.${formName}.txtLuasMohon1.value == ""){
				alert('Sila masukkan Luas Penawaran .');
				document.${formName}.txtLuasMohon1.focus(); 
				return; 
			}
		}
		else
		if(idLuas == '4' || idLuas == '8'){
			if(document.${formName}.txtLuasMohon1.value == ""){
				alert('Sila masukkan Luas Penawaran.');
				document.${formName}.txtLuasMohon1.focus(); 
				return; 
			}
			if(document.${formName}.txtLuasMohon2.value == ""){
				alert('Sila masukkan Luas Penawaran.');
				document.${formName}.txtLuasMohon2.focus(); 
				return; 
			}
			if(document.${formName}.txtLuasMohon3.value == ""){
				alert('Sila masukkan Luas Penawaran.');
				document.${formName}.txtLuasMohon3.focus(); 
				return; 
			}
		} 
		else
		if(idLuas == '7'){
			if(document.${formName}.txtLuasMohon1.value == ""){
				alert('Sila masukkan Luas Penawaran.');
				document.${formName}.txtLuasMohon1.focus(); 
				return; 
			}
			if(document.${formName}.txtLuasMohon2.value == ""){
				alert('Sila masukkan Luas Penawaran.');
				document.${formName}.txtLuasMohon2.focus(); 
				return; 
			}
		} 
	}
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "update";
		return;
	}
	
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "doSimpanKemaskiniMaklumatPenawaran";
	document.${formName}.submit();
}
function cekTarikhTerima(elmnt) {
//CHECK DATE   
	var str1  = document.${formName}.tarikhTerima.value;		   
	var dt1   = parseInt(str1.substring(0,2),10);
	var mon1  = parseInt(str1.substring(3,5),10)-1;
	var yr1   = parseInt(str1.substring(6,10),10);
	var tarikhTerima = new Date(yr1, mon1, dt1);
	
	var currentDate = new Date();
	
	if (tarikhTerima > currentDate){
		alert('Tarikh Terima tidak boleh melebihi dari tarikh hari ini.');
		return;
	}
}
function cekTarikhSurat(elmnt) { 
	//CHECK DATE   
	var str1  = document.${formName}.tarikhTerima.value;		   
	var dt1   = parseInt(str1.substring(0,2),10);
	var mon1  = parseInt(str1.substring(3,5),10)-1;
	var yr1   = parseInt(str1.substring(6,10),10);
	var tarikhTerima = new Date(yr1, mon1, dt1);
	
	var str2  =  document.${formName}.tarikhSurat.value;		   
	var dt2   = parseInt(str2.substring(0,2),10);
	var mon2  = parseInt(str2.substring(3,5),10)-1;
	var yr2   = parseInt(str2.substring(6,10),10);
	var tarikhSurat = new Date(yr2, mon2, dt2);
	
	var currentDate = new Date();
	
	if (tarikhSurat > currentDate){
		alert('Tarikh Surat tidak boleh melebihi dari tarikh hari ini.');
		return;
	}
	if (tarikhSurat > tarikhTerima){
		alert('Tarikh Surat tidak boleh melebihi dari Tarikh Terima.');
		return;
	}
}
function doSeterusnya(){
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "doSeterusnya";
	document.${formName}.submit();
}
function gotoBatalPermohonan(){	
	document.${formName}.step.value = "batalPermohonan";
	document.${formName}.submit();
}
function kiraLuas(idLuas){

	  var jenisLuas = idLuas;
	  
	  // KILOMETER PERSEGI
	  if(jenisLuas == "1"){

			var luasK = 0;
			if (document.${formName}.txtLuasMohon1.value != ''){
				luasK = document.${formName}.txtLuasMohon1.value*1;
			}
			var luasH = luasK*100;
			
			if (luasH > (document.${formName}.txtLuasAsal.value)*1){
				alert('Luas dipohon telah melebihi luas asal.')
				document.${formName}.txtLuasMohon1.value = "";
				document.${formName}.txtLuasBersamaan.value = "";
				document.${formName}.txtBakiLuas.value = "";
			} else {
				document.${formName}.txtLuasBersamaan.value = luasH.toFixed(5);
				var bakiLuas = (document.${formName}.txtLuasAsal.value - luasH).toFixed(5);
				document.${formName}.txtBakiLuas.value = bakiLuas;
			}

	   } else if(jenisLuas == "2"){ //HEKTER
	  	
			var luasH = 0;
			if (document.${formName}.txtLuasMohon1.value != ''){
				luasH = document.${formName}.txtLuasMohon1.value*1;
			}
	  		
			if (luasH > (document.${formName}.txtLuasAsal.value)*1){
				alert('Luas dipohon telah melebihi luas asal.')
				document.${formName}.txtLuasMohon1.value = "";
				document.${formName}.txtLuasBersamaan.value = "";
				document.${formName}.txtBakiLuas.value = "";
			} else {
				document.${formName}.txtLuasBersamaan.value = luasH.toFixed(5);
				var bakiLuas = (document.${formName}.txtLuasAsal.value - luasH).toFixed(5);
				document.${formName}.txtBakiLuas.value = bakiLuas;
			}

	   } else if(jenisLuas == "3"){ // METER PERSEGI
	    	
			var luasM = 0;
			if (document.${formName}.txtLuasMohon1.value != ''){
				luasM = document.${formName}.txtLuasMohon1.value*1;
			}
	  	  	var luasH = (luasM*0.0001);
		  	
			if (luasH > (document.${formName}.txtLuasAsal.value)*1){
				alert('Luas dipohon telah melebihi luas asal.')
				document.${formName}.txtLuasMohon1.value = "";
				document.${formName}.txtLuasBersamaan.value = "";
				document.${formName}.txtBakiLuas.value = "";
			} else {
				document.${formName}.txtLuasBersamaan.value = luasH.toFixed(5);
				var bakiLuas = (document.${formName}.txtLuasAsal.value - luasH).toFixed(5);
				document.${formName}.txtBakiLuas.value = bakiLuas;
			}

	   } else if(jenisLuas == "4"){  //EKAR, ROOD, POLE

		  	var luasE = 0;
			if (document.${formName}.txtLuasMohon1.value != ''){
				luasE = document.${formName}.txtLuasMohon1.value*1;
			}
		  	var luasR = 0;
			if (document.${formName}.txtLuasMohon2.value != ''){
				luasR = document.${formName}.txtLuasMohon2.value*1;
			}
		  	var luasP = 0;
			if (document.${formName}.txtLuasMohon3.value != ''){
				luasP = document.${formName}.txtLuasMohon3.value*1;
			}
		  	var luasH = (luasE*0.4046864)+(luasR*0.1011716)+(luasP*0.00252929);
	  	  	
			if (luasH > (document.${formName}.txtLuasAsal.value)*1){
				alert('Luas dipohon telah melebihi luas asal.')
				document.${formName}.txtLuasMohon1.value = "";
				document.${formName}.txtLuasMohon2.value = "";
				document.${formName}.txtLuasMohon3.value = "";
				document.${formName}.txtLuasBersamaan.value = "";
				document.${formName}.txtBakiLuas.value = "";
			} else {
				document.${formName}.txtLuasBersamaan.value = luasH.toFixed(5);
				var bakiLuas = (document.${formName}.txtLuasAsal.value - luasH).toFixed(5);
				document.${formName}.txtBakiLuas.value = bakiLuas;
			}

	   } else if(jenisLuas == "5"){ //KAKI PERSEGI
	  	  
		  var luasAsal = 0;
		  if (document.${formName}.txtLuasMohon1.value != ''){
		  	  luasAsal = document.${formName}.txtLuasMohon1.value*1;
		  }
		  var luasH = luasAsal*0.0000092;
	  	  
		  if (luasH > (document.${formName}.txtLuasAsal.value)*1){
				alert('Luas dipohon telah melebihi luas asal.')
				document.${formName}.txtLuasMohon1.value = "";
				document.${formName}.txtLuasBersamaan.value = "";
				document.${formName}.txtBakiLuas.value = "";
			} else {
				document.${formName}.txtLuasBersamaan.value = luasH.toFixed(5);
			  	var bakiLuas = (document.${formName}.txtLuasAsal.value - luasH).toFixed(5);
			 	document.${formName}.txtBakiLuas.value = bakiLuas;
			}

	   } else if(jenisLuas == "6"){	//EKAR PERPULUHAN
	  	  
		  var luasAsal = 0;
		  if (document.${formName}.txtLuasMohon1.value != ''){
		  	  luasAsal = document.${formName}.txtLuasMohon1.value*1;
		  }
		  var luasH = luasAsal*0.405;
		  
		  if (luasH > (document.${formName}.txtLuasAsal.value)*1){
				alert('Luas dipohon telah melebihi luas asal.')
				document.${formName}.txtLuasMohon1.value = "";
				document.${formName}.txtLuasBersamaan.value = "";
				document.${formName}.txtBakiLuas.value = "";
			} else {
				document.${formName}.txtLuasBersamaan.value = luasH.toFixed(5);
				var bakiLuas = (document.${formName}.txtLuasAsal.value - luasH).toFixed(5);
				document.${formName}.txtBakiLuas.value = bakiLuas;
			}
	  	  
	   } else if(jenisLuas == "7"){ //EKAR,DEPA
	  	  
		  	var luasE = 0;
			if (document.${formName}.txtLuasMohon1.value != ''){
				luasE = document.${formName}.txtLuasMohon1.value*1;
			}
		  	var luasD = 0;
			if (document.${formName}.txtLuasMohon2.value != ''){
				luasD = document.${formName}.txtLuasMohon2.value*1;
			}
		  
		  var luasH = (luasE*0.4046864)+(luasD*0.00040469);
		  
		  if (luasH > (document.${formName}.txtLuasAsal.value)*1){
				alert('Luas dipohon telah melebihi luas asal.')
				document.${formName}.txtLuasMohon1.value = "";
				document.${formName}.txtLuasMohon2.value = "";
				document.${formName}.txtLuasBersamaan.value = "";
				document.${formName}.txtBakiLuas.value = "";
			} else {
				document.${formName}.txtLuasBersamaan.value = luasH.toFixed(5);
			  	var bakiLuas = (document.${formName}.txtLuasAsal.value - luasH).toFixed(5);
			  	document.${formName}.txtBakiLuas.value = bakiLuas;
			}

	   } else if(jenisLuas == "8"){ //RELONG,JEMBA,KAKI PERSEGI
	  	  
		 	var luasR = 0;
			if (document.${formName}.txtLuasMohon1.value != ''){
				luasR = document.${formName}.txtLuasMohon1.value*1;
			}
		  	var luasJ = 0;
			if (document.${formName}.txtLuasMohon2.value != ''){
				luasJ = document.${formName}.txtLuasMohon2.value*1;
			}
		  	var luasK = 0;
			if (document.${formName}.txtLuasMohon3.value != ''){
				luasK = document.${formName}.txtLuasMohon3.value*1;
			}
		  
		  var luasH = (luasR*0.2877764)+(luasJ*0.0005945)+(luasK*0.0000092);
		  
		  if (luasH > (document.${formName}.txtLuasAsal.value)*1){
				alert('Luas dipohon telah melebihi luas asal.')
				document.${formName}.txtLuasMohon1.value = "";
				document.${formName}.txtLuasMohon2.value = "";
				document.${formName}.txtLuasMohon3.value = "";
				document.${formName}.txtLuasBersamaan.value = "";
				document.${formName}.txtBakiLuas.value = "";
			} else {
				document.${formName}.txtLuasBersamaan.value = luasH.toFixed(5);
			  	var bakiLuas = (document.${formName}.txtLuasAsal.value - luasH).toFixed(5);
			  	document.${formName}.txtBakiLuas.value = bakiLuas;
			}
		}
	}
</script>
<script>
function setTable(id){
	if(document.getElementById(id).style.display=="none"){
		document.getElementById(id).style.display="block";
	}
	else if(document.getElementById(id).style.display=="block"){
		document.getElementById(id).style.display="none";
	}
}
function cetakKulitFail(idFail) {
	var url = "../servlet/ekptg.report.php2.PNWKulitFail?ID_FAIL="+idFail;
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakSuratAkuanTerima(idFail) {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmPLPPopupCetakLaporanView?idFail="+idFail+"&report=PNWsuratAkuanTerima";
    var hWnd = window.open(url,'printuser','width=900,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function cetakMaklumatRizab(idhakmilik){
	var url = "../servlet/ekptg.report.htp.MaklumatFailHakmilikRizab?template=MaklumatRizab&idHakmilik="+idhakmilik;
	var hWnd=window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	if ((document.window != null) && (!hWnd.opener))
	hWnd.opener=document.window;
	if (hWnd.focus != null) hWnd.focus();
}
function cetakMaklumatHakmilik(idhakmilik){
	var url = "../servlet/ekptg.report.htp.MaklumatFailHakmilikRizab?template=MaklumatHakmilik&idHakmilik="+idhakmilik;
	var hWnd=window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	if ((document.window != null) && (!hWnd.opener))
	hWnd.opener=document.window;
	if (hWnd.focus != null) hWnd.focus();
}

function doDaftarHakmilik(idPermohonan,idKategoriPemohon,idNegeriPemohon,idKementerianPemohon) {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmPLPPopupSenaraiTanahBerkaitanView?idPermohonan="+idPermohonan+"&idKategoriPemohon="+idKategoriPemohon+"&idNegeriPemohon="+idNegeriPemohon+"&idKementerianPemohon="+idKementerianPemohon;
    var hWnd = window.open(url,'printuser','width=900,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function doDaftarBorangK(idPermohonan,idKategoriPemohon,idNegeriPemohon,idKementerianPemohon) {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmPLPPopupSenaraiBorangKBerkaitanView?idPermohonan="+idPermohonan+"&idKategoriPemohon="+idKategoriPemohon+"&idNegeriPemohon="+idNegeriPemohon+"&idKementerianPemohon="+idKementerianPemohon;
    var hWnd = window.open(url,'printuser','width=1000,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function refreshFromPilihTanahBorangK() {
	doAjaxCall${formName}("");
}
function refreshFromPilihTanahBerkaitan(idPermohonan,idHakmilikAgensi,idHakmilikSementara) {
	document.${formName}.action = "?_portal_module=ekptg.view.php2.FrmPNWMaklumatPermohonanView&selectedTabUpper=1&hitButton=simpanDaftarHakmilikBaru&doPost=tru&idHakmilikAgensi="+idHakmilikAgensi+"&idHakmilikSementara="+idHakmilikSementara+"&idPermohonan="+idPermohonan;
	document.${formName}.submit();
}
function doHapus(idHakmilikPermohonan) {
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.idHakmilikPermohonan.value = idHakmilikPermohonan;
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "doHapus";
	doAjaxCall${formName}("");
}
function doSimpanKemaskiniSenaraiSemak() {
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.mode.value = "update";
		return;
	}
	
	document.${formName}.mode.value = "view";
	document.${formName}.hitButton.value = "doSimpanKemaskiniSenaraiSemak";
	document.${formName}.submit();
}
</script>
