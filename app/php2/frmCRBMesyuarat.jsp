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
  <input name="idPermohonan" type="hidden" id="idPermohonan" value="$idPermohonan"/>
  <input name="idStatus" type="hidden" id="idStatus" value="$idStatus"/>
  <input name="idMesyuarat" type="hidden" id="idMesyuarat" value="$idMesyuarat"/>
  <input name="idKehadiran" type="hidden" id="idKehadiran" value="$idKehadiran"/>
  <input name="actionCRB" type="hidden" id="actionCRB" value="$actionCRB"/>
  <input name="mode" type="hidden" id="mode" value="$mode"/>
  <input name="flagPopup" type="hidden" id="flagPopup" value="$flagPopup"/>
  <input name="modePopup" type="hidden" id="modePopup" value="$modePopup"/>
  <input name="hitButton" type="hidden" id="hitButton"/>
</p>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #if ($idFail != '' && $idStatus != '1610198' && $idStatus != '1610199' && $idStatus != '1610200')
  <tr>
    <td> #parse("app/php2/frmCRBHeader.jsp") </td>
  </tr>
  #elseif ($idFail == '' )
  <tr>
    <td>&nbsp;
      <div class="warning">SILA PILIH FAIL DI SENARAI FAIL TERLEBIH DAHULU</div></td>
  </tr>
  #end
  
  #if ($idFail != '' && $flagOpenDetail && $idStatus != '1610198' && $idStatus != '1610199' && $idStatus != '1610200')
  <tr>
    <td><fieldset>
      <legend><strong>SENARAI MESYUARAT</strong></legend>
      <table align="center" width="100%">
        ##<tr>
        ## #if ($!{session.getAttribute("FLAG_FROM")} == 'failTugasan' || $!{session.getAttribute("FLAG_FROM")} == 'failHQ')
        ##  <td colspan="4" scope="row">
        ##  	<input name="cmdDaftar" type="button" value="Tambah" onClick="javascript:doDaftarBaruMesyuarat()"/>
        ##  </td>
        ## #end
        </tr>
        <tr class="table_header">
          <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
          <td width="10%" align="center"><strong>Tarikh</strong></td>
          <td width="40%"><strong>Tajuk Mesyuarat</strong></td>
          <td width="40%"><strong>Keputusan Mesyuarat</strong></td>
          <td width="5%" align="center">&nbsp;</td>
        </tr>
        #set ($senaraiMesyuarat = "")
        #if ($SenaraiMesyuarat.size() > 0)
        #foreach ($senaraiMesyuarat in $SenaraiMesyuarat)
        #if ($senaraiMesyuarat.bil == '')
        #set( $row = "row1" )
        #elseif (($senaraiMesyuarat.bil % 2) != 0)
        #set( $row = "row1" )
        #else 
        #set( $row = "row2" )
        #end
        <tr>
          <td class="$row" align="center">$senaraiMesyuarat.bil</td>
          <td class="$row" align="center"><a href="javascript:paparMesyuarat('$senaraiMesyuarat.idMesyuarat')" class="style2">$senaraiMesyuarat.tarikhMesyuarat</a></td>
          <td class="$row">$senaraiMesyuarat.tajukMesyuarat</td>
          <td class="$row">$senaraiMesyuarat.tindakanMesyuarat</td>
          <td class="$row" align="center"><input type="button" name="cmdHapus" id="cmdHapus" value="Hapus" onclick="hapusMesyuarat('$senaraiMesyuarat.idMesyuarat')"/></td>
        </tr>
        #end
        #else
        <tr>
          <td class="row1" align="center">&nbsp;</td>
          <td class="row1" align="center">Tiada Rekod</td>
          <td class="row1">&nbsp;</td>
          <td class="row1">&nbsp;</td>
          <td class="row1">&nbsp;</td>
        </tr>
        #end
      </table>
      </fieldset></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td align="center"> 
    #if ($!{session.getAttribute("FLAG_FROM")} == 'failTugasan')
    	#if ($idStatus == '1610201')
      		<input type="button" name="cmdHantar" id="cmdHantar" value="Seterusnya" onClick="doSeterusnya()"/>
      		<input type="button" name="cmdSelesaiPermohonan" id="cmdSelesaiPermohonan" value="Selesai Permohonan" onClick="gotoSelesaiPermohonan()"/>
      		<input type="button" name="cmdBatalPermohonan" id="cmdBatalPermohonan" value="Batal Permohonan" onClick="gotoBatalPermohonan()"/>
      	#end 
    #end
    #if ($!{session.getAttribute("FLAG_FROM")} == 'failKeseluruhan')
    	<input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="gotoSenaraiFailKeseluruhan()"/>
    #end
    </td>
  </tr>
  #elseif ($idFail != '')
  <tr>
    <td> 
      <div class="warning"><strong>STATUS FAIL MASIH DI PERINGKAT $!status</strong></div></td>
  </tr>
  #end
</table>
<script>
function paparMesyuarat(idMesyuarat){
	document.${formName}.actionCRB.value = "papar";
	document.${formName}.mode.value = "view";
	document.${formName}.idMesyuarat.value = idMesyuarat;
	document.${formName}.submit();
}
function doDaftarBaruMesyuarat(){
	document.${formName}.actionCRB.value = "daftarBaru";
	document.${formName}.mode.value = "new";
	document.${formName}.submit();
}
function hapusMesyuarat(idMesyuarat){	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.idMesyuarat.value = idMesyuarat;
	document.${formName}.hitButton.value = "hapusMesyuarat";
	doAjaxCall${formName}("");
}
function doSeterusnya(){	
	if ( !window.confirm("Adakah Anda Pasti ?") ){
		return;
	}
	
	document.${formName}.hitButton.value = "doSeterusnya";
	doAjaxCall${formName}("");
}
function gotoSenaraiFailKeseluruhan() {
	document.${formName}.action = "$EkptgUtil.getTabID("'My Info'",$portal_role)?_portal_module=ekptg.view.php2.FrmCRBSenaraiFailKeseluruhanView";
	document.${formName}.submit();
}
</script>
<input name="step" type="hidden" id="step"/>
<script>
function gotoSelesaiPermohonan(){	
	document.${formName}.step.value = "selesaiPermohonan";
	document.${formName}.submit();
}
function gotoBatalPermohonan(){	
	document.${formName}.step.value = "batalPermohonan";
	document.${formName}.submit();
}
</script>
