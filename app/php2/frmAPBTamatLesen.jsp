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
  <input name="idFail" type="hidden" id="idFail" value="$idFail"/>
  <input name="actionTamat" type="hidden" id="actionTamat" value="$actionTamat"/> 
  <input name="idPermohonan" type="hidden" id="idPermohonan" value="$idPermohonan"/>
  <input name="idStatus" type="hidden" id="idStatus" value="$idStatus"/>
  <input name="idMohonTamat" type="hidden" id="idMohonTamat" value="$idMohonTamat"/>
  <input name="flagPopup" type="hidden" id="flagPopup" value="$flagPopup"/>
  <input name="modePopup" type="hidden" id="modePopup" value="$modePopup"/>
  <input name="mode" type="hidden" id="mode" value="$mode"/>
  <input name="hitButton" type="hidden" id="hitButton"/>
  <input name="selectedTabUpper" type="hidden" id="selectedTabUpper" value="$selectedTabUpper"/>  
  <input name="flagAktif" type="hidden" id="flagAktif" value="$flagAktif"/>  
  
</p>
<body>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #if ($idFail != '')
  <tr>
    <td> #parse("app/php2/frmAPBHeader.jsp") </td>
  </tr>
  #elseif ($idFail == '' )
  <tr>
    <td>&nbsp;
      <div class="warning">SILA PILIH FAIL DI SENARAI FAIL TERLEBIH DAHULU</div></td>
  </tr>
  #end
 #if ($idFail != '' && $idStatus != '1610198' && $idStatus != '1610199' && $idStatus != '1610235'  && $idStatus != '1610201'  && $idStatus != '1610213' && $idStatus != '1610205'  && $idStatus != '1610206' && $idStatus != '1610236' && $idStatus != '1610237' && $idStatus != '1610238' && $idStatus != '1610239')
  #if($flagAktif == '1')
  <tr>
    <td><fieldset>
      <legend><b>SENARAI PERMOHONAN TAMAT</b></legend>
      #parse("app/utils/record_paging.jsp")
      <table align="center" width="100%">
        #if ($flagPopup == '' && $idStatus != '1610244')
        <tr>
          <td colspan="9" scope="row"><input name="cmdDaftar" type="button" value="Tambah" onClick="javascript:doDaftarDaftatTamatLesen()"/></td>
        </tr>
        #end
        <tr class="table_header">
          <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
          <td width="85%"><strong>No Rujukan</strong></td>
          <td width="10%" align="center"><strong>Tarikh Terima</strong></td>
        </tr>
        #set ($list = "")
        #if ($SenaraiPermohonanTamat.size() > 0)
        #foreach ($list in $SenaraiPermohonanTamat)
        #if ($list.bil == '')
        #set( $row = "row1" )
        #elseif (($list.bil % 2) != 0)
        #set( $row = "row1" )
        #else 
        #set( $row = "row2" )
        #end
        <tr>
          <td class="$row" align="center">$list.bil</td>
          <td class="$row"><a href="javascript:paparTamatLesen('$list.idMohonTamat')" class="style2">$list.rujukan</a></td>
          <td class="$row" align="center">$list.tarikhTerima</td>
        </tr>
        #end
        #else
        <tr>
          <td class="row1" align="center">&nbsp;</td>
          <td class="row1">Tiada Rekod</td>
          <td class="row1" align="center">&nbsp;</td>
        </tr>
        #end
      </table>
      </fieldset>    </td>
  </tr>
  #end
  #if($flagAktif == '0')
  <tr>
   <td>&nbsp;
      <div class="error">PENAMATAN LESEN TIDAK BOLEH DILAKUKAN BAGI FAIL INI</div></td>
  </tr>
  #end
  #end
</table>
<script>
function doDaftarDaftatTamatLesen(){
	document.${formName}.actionTamat.value = "add";
	document.${formName}.flagPopup.value = "openPopupTamatLesen";
	document.${formName}.modePopup.value = "new";
	document.${formName}.mode.value = "view";
	doAjaxCall${formName}("");
}
function paparTamatLesen(id){
	document.${formName}.actionTamat.value = "papar";
	document.${formName}.mode.value = "view";
	document.${formName}.idMohonTamat.value = id;
	document.${formName}.selectedTabUpper.value = 0;	
	document.${formName}.submit();
}
function doBackToList(){
	document.${formName}.actionTamat.value = "";
	document.${formName}.mode.value = "";
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";	
	document.${formName}.submit();
}
function doDaftarTamatLesen() {
	if(document.${formName}.tarikhSurat.value == ""){
		alert('Sila masukkan Tarikh Surat.');
  		document.${formName}.tarikhSurat.focus(); 
		return; 
	}
	if(document.${formName}.tarikhTerima.value == ""){
		alert('Sila masukkan Tarikh Terima.');
  		document.${formName}.tarikhTerima.focus(); 
		return; 
	}
	if(document.${formName}.sebabTamat.value == ""){
		alert('Sila masukkan Sebab Penamatan.');
  		document.${formName}.sebabTamat.focus(); 
		return; 
	}
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		document.${formName}.actionTamat.value = "add";
		document.${formName}.mode.value = "view";
		document.${formName}.flagPopup.value = "openPopupTamatLesen";
		document.${formName}.modePopup.value = "new";
		return;
	}
	document.${formName}.actionTamat.value = "papar";
	document.${formName}.mode.value = "view";
	document.${formName}.selectedTabUpper.value = 0;	
	document.${formName}.hitButton.value = "simpanMaklumatMohonTamat";
	document.${formName}.submit();
}
function textCounter(field, countfield, maxlimit) {
   if (field.value.length > maxlimit) // if too long...trim it!
		 field.value = field.value.substring(0, maxlimit);
    // otherwise, update 'Baki Aksara' counter
	else
	 countfield.value = maxlimit - field.value.length;
}
function validateTarikh(elmnt){
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
}
</script>
