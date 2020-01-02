<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #if ($flagPopup == 'openJPPH')
  <tr>
    <td> #parse("app/php2/frmTKRMaklumatJPPH.jsp") </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
  #end
  <tr>
    <td><fieldset>
      <legend><b>SENARAI DOKUMEN</b></legend>
      <table align="center" width="100%">
        <tr>
          <td colspan="9" scope="row"><input name="cmdTambah" type="button" value="Tambah" onClick="javascript:doDaftarMaklumatJPPH()"/></td>
        </tr>
        <tr class="table_header">
          <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
          <td width="23%"><strong>Nama Dokumen</strong></td>
          <td width="32%"><strong>Agensi</strong></td>
          <td width="10%" align="center"><strong>Tarikh Hantar</strong></td>
          <td width="10%" align="center"><strong>Tarikh Dijangka Terima</strong></td>
          <td width="10%"><strong>Status</strong></td>
          <td width="5%"><strong></strong></td>
        </tr>
        #set ($list = "")
        #if ($SenaraiJPPH.size() > 0)
        #foreach ($list in $SenaraiJPPH)
        #if ($list.bil == '')
        #set( $row = "row1" )
        #elseif (($list.bil % 2) != 0)
        #set( $row = "row1" )
        #else 
        #set( $row = "row2" )
        #end
        <tr>
          <td class="$row" align="center">$list.bil</td>
          #if ($list.bilUlangan == '' || $list.bilUlangan == '0')
          <td class="$row"><a href="javascript:paparMaklumatJPPH('$list.idUlasanTeknikal')" class="style2">$list.namaDokumen</a>&nbsp;&nbsp;<font class="blink" ><span class="style3">$!list.statusHari</span></font></td>
          #else
          <td class="$row"><a href="javascript:paparMaklumatJPPH('$list.idUlasanTeknikal')" class="style2">$list.namaDokumen</a>&nbsp;&nbsp;(ULANGAN $list.bilUlangan)&nbsp;&nbsp;<font class="blink" ><span class="style3">$!list.statusHari</td>
          #end
          <td class="$row">$list.namaPejabat</td>
          <td class="$row" align="center">$list.tarikhHantar</td>
          <td class="$row" align="center">$list.tarikhJangkaTerima</td>
          <td class="$row">$list.status</td>
          #if ($list.bilUlangan == '' || $list.bilUlangan == '0')
              #if($list.flagSuratUlasan == 'B')
              <td class="$row" align="center"><a href="#" class="style2" onClick="javascript:cetakSuratMUJPPB('$idFail','$list.idUlasanTeknikal')"><img border="0" src="../img/print.gif"/></a></td>
              #elseif($list.flagSuratUlasan == 'P')
              <td class="$row" align="center"><a href="#" class="style2" onClick="javascript:cetakSuratMUJPPHP('$idFail','$list.idUlasanTeknikal')"><img border="0" src="../img/print.gif"/></a></td>
              #elseif($list.flagSuratUlasan == 'G')
              <td class="$row" align="center"><a href="#" class="style2" onClick="javascript:cetakSuratMUJPPHG('$idFail','$list.idUlasanTeknikal')"><img border="0" src="../img/print.gif"/></a></td>
              #else
              <td class="$row" align="center">&nbsp;</td>
              #end
          #else
          <td class="$row" align="center"><a href="#" class="style2" onClick="javascript:cetakTKRSuratUlanganJPPH('$idFail','$list.idUlasanTeknikal')"><img border="0" src="../img/print.gif"/></a></td>
          #end          
        </tr>
        #end
        #else
        <tr>
          <td class="row1" align="center">&nbsp;</td>
          <td class="row1" >Tiada Rekod</td>
          <td class="row1">&nbsp;</td>
          <td class="row1">&nbsp;</td>
          <td class="row1" align="center">&nbsp;</td>
          <td class="row1">&nbsp;</td>
          <td class="row1">&nbsp;</td>
        </tr>
        #end
      </table>
      </fieldset></td>
  </tr>
  <tr>
    <td align="center"> #if ($idStatus == '1610199')
      <input type="button" name="cmdHantar" id="cmdHantar" value="Seterusnya" onClick="doSeterusnya()"/>
      <input type="button" name="cmdBatalPermohonan" id="cmdBatalPermohonan" value="Batal Permohonan" onClick="gotoBatalPermohonan()"/>
      #end</td>
  </tr>
</table>
<script>
function doChangePejabatJPPH() {
	doAjaxCall${formName}("doChangePejabatJPPH");
}
function calcDate(){
	if (document.${formName}.txtTarikhHantar.value != "" && document.${formName}.txtJangkaMasa.value != ""){
		
		var tarikhHantar  = document.${formName}.txtTarikhHantar.value;
		var days  = parseInt(document.${formName}.txtJangkaMasa.value);
		
		var dt1   = parseInt(tarikhHantar.substring(0,2),10) + days;
		var mon1  = parseInt(tarikhHantar.substring(3,5),10)-1;
		var yr1   = parseInt(tarikhHantar.substring(6,10),10);
	 
		var myDate = new Date(yr1, mon1, dt1);
		
		var day = myDate.getDate();
		var month = myDate.getMonth()+1;
		var year = myDate.getFullYear();
		
		var tarikhJangkaTerima = "";
		if(month>=10){
			if(day>=10){
				tarikhJangkaTerima = day + "/" + month + "/" + year;	
			} else {
				tarikhJangkaTerima = "0"+ day + "/" + month + "/" + year;	
			}				
		} else {
			if(day>=10){
				tarikhJangkaTerima = day + "/0" + month + "/" + year;	
			} else {
				tarikhJangkaTerima = "0"+ day + "/0" + month + "/" + year;	
			}
		}
		document.${formName}.txtTarikhJangkaTerima.value = tarikhJangkaTerima;
	
	} else {
		document.${formName}.txtTarikhJangkaTerima.value = "";
	}
}
function paparMaklumatJPPH(idUlasanTeknikal){
	document.${formName}.flagPopup.value = "openJPPH";
	document.${formName}.modePopup.value = "view";
	document.${formName}.idUlasanTeknikal.value = idUlasanTeknikal;
	doAjaxCall${formName}("");
}
function doDaftarMaklumatJPPH(){
	document.${formName}.flagPopup.value = "openJPPH";
	document.${formName}.modePopup.value = "new";
	document.${formName}.flagStatus.value = "1";
	doAjaxCall${formName}("");
}
function doSimpanMaklumatJPPH(){	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.flagPopup.value = "openJPPH";
	document.${formName}.modePopup.value = "view";
	document.${formName}.hitButton.value = "simpanMaklumatJPPH";
	doAjaxCall${formName}("");
}
function doSimpanMaklumatUlanganJPPH(){	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.flagPopup.value = "openJPPH";
	document.${formName}.modePopup.value = "view";
	document.${formName}.hitButton.value = "simpanMaklumatUlanganJPPH";
	doAjaxCall${formName}("");
}
function doBatalMaklumatJPPH(){
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	doAjaxCall${formName}("");
}
function doKemaskiniMaklumatJPPH(){
	document.${formName}.flagPopup.value = "openJPPH";
	document.${formName}.modePopup.value = "update";
	doAjaxCall${formName}("");
}
function doBatalKemaskiniMaklumatJPPH(){
	document.${formName}.flagPopup.value = "openJPPH";
	document.${formName}.modePopup.value = "view";
	doAjaxCall${formName}("");
}
function doSimpanKemaskiniMaklumatJPPH(){	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.flagPopup.value = "openJPPH";
	document.${formName}.modePopup.value = "view";
	document.${formName}.hitButton.value = "simpanKemaskiniMaklumatJPPH";
	doAjaxCall${formName}("");
}
function doHapusMaklumatJPPH(){
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	document.${formName}.hitButton.value = "hapusMaklumatKJPKJT";
	doAjaxCall${formName}("");
}
function doTerimaJPPH(){
	document.${formName}.flagPopup.value = "openJPPH";
	document.${formName}.modePopup.value = "update";
	document.${formName}.flagStatus.value = "2";
	doAjaxCall${formName}("");
}
function doUlanganJPPH(){
	document.${formName}.flagPopup.value = "openJPPH";
	document.${formName}.modePopup.value = "newUlangan";
	document.${formName}.flagStatus.value = "1";
	doAjaxCall${formName}("");
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
</script>
