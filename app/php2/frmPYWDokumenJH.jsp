<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #if ($flagPopup == 'openJH')
  <tr>
    <td> #parse("app/php2/frmPYWMaklumatJH.jsp") </td>
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
          <td colspan="9" scope="row"><input name="cmdTambah" type="button" value="Tambah" onclick="javascript:doDaftarMaklumatJH()"/></td>
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
        #if ($SenaraiJH.size() > 0)
        #foreach ($list in $SenaraiJH)
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
          <td class="$row"><a href="javascript:paparMaklumatJH('$list.idUlasanTeknikal')" class="style2">$list.namaDokumen</a></td>
          #else
          <td class="$row"><a href="javascript:paparMaklumatJH('$list.idUlasanTeknikal')" class="style2">$list.namaDokumen</a>&nbsp;&nbsp;(ULANGAN $list.bilUlangan)</td>
          #end
          <td class="$row">$list.namaPejabat</td>
          <td class="$row" align="center">$list.tarikhHantar</td>
          <td class="$row" align="center">$list.tarikhJangkaTerima</td>
          <td class="$row">$list.status</td>
          #if ($list.bilUlangan == '' || $list.bilUlangan == '0')
          <td class="$row" align="center"><a href="#" class="style2" onClick="javascript:cetakPYWSMintaUlasanJH('$idFail','$list.idUlasanTeknikal')"><img border="0" src="../img/print.gif"/></a></td>
          #else
          <td class="$row" align="center"><a href="#" class="style2" onClick="javascript:cetakPYWSuratUlanganJH('$idFail','$list.idUlasanTeknikal')"><img border="0" src="../img/print.gif"/></a></td>
          #end </tr>
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
      <input type="button" name="cmdHantar" id="cmdHantar" value="Ke Lawatan Tapak" onClick="doSeterusnya()"/>
      <input type="button" name="cmdBatalPermohonan" id="cmdBatalPermohonan" value="Batal Permohonan" onClick="gotoBatalPermohonan()"/>
      #end
      #if ($!{session.getAttribute("FLAG_FROM")} == 'failKeseluruhan')
      <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="gotoSenaraiFailKeseluruhan()"/>
      #end</td>
  </tr>
</table>
<script>
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
function paparMaklumatJH(idUlasanTeknikal){
	document.${formName}.flagPopup.value = "openJH";
	document.${formName}.modePopup.value = "view";
	document.${formName}.idUlasanTeknikal.value = idUlasanTeknikal;
	doAjaxCall${formName}("");
}
function doDaftarMaklumatJH(){
	document.${formName}.flagPopup.value = "openJH";
	document.${formName}.modePopup.value = "new";
	document.${formName}.flagStatus.value = "1";
	doAjaxCall${formName}("");
}
function doSimpanMaklumatJH(){
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.flagPopup.value = "openJH";
	document.${formName}.modePopup.value = "view";
	document.${formName}.hitButton.value = "simpanMaklumatJH";
	doAjaxCall${formName}("");
}
function doSimpanMaklumatUlanganJH(){
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.flagPopup.value = "openJH";
	document.${formName}.modePopup.value = "view";
	document.${formName}.hitButton.value = "simpanMaklumatUlanganJH";
	doAjaxCall${formName}("");
}
function doBatalMaklumatJH(){
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	doAjaxCall${formName}("");
}
function doKemaskiniMaklumatJH(){
	document.${formName}.flagPopup.value = "openJH";
	document.${formName}.modePopup.value = "update";
	doAjaxCall${formName}("");
}
function doBatalKemaskiniMaklumatJH(){
	document.${formName}.flagPopup.value = "openJH";
	document.${formName}.modePopup.value = "view";
	doAjaxCall${formName}("");
}
function doSimpanKemaskiniMaklumatJH(){
	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.flagPopup.value = "openJH";
	document.${formName}.modePopup.value = "view";
	document.${formName}.hitButton.value = "simpanKemaskiniMaklumatJH";
	doAjaxCall${formName}("");
}
function doHapusMaklumatJH(){
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.flagPopup.value = "";
	document.${formName}.modePopup.value = "";
	document.${formName}.hitButton.value = "hapusMaklumatKJPKJT";
	doAjaxCall${formName}("");
}
function doTerimaJH(){
	document.${formName}.flagPopup.value = "openJH";
	document.${formName}.modePopup.value = "update";
	document.${formName}.flagStatus.value = "2";
	doAjaxCall${formName}("");
}
function doUlanganJH(){
	document.${formName}.flagPopup.value = "openJH";
	document.${formName}.modePopup.value = "newUlangan";
	document.${formName}.flagStatus.value = "1";
	doAjaxCall${formName}("");
}
</script>
