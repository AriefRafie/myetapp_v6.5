<style type="text/css">
<!--
.style1 {
	color: #0033FF
}
-->
</style>
<p>
  <input type="hidden" name="actionMesyuarat" id="actionMesyuarat" value="$actionMesyuarat"/>
  <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
  <input type="hidden" name="idPermohonan" id="idPermohonan" value="$idPermohonan"/>
  <input type="hidden" name="idMesyuarat" id="idMesyuarat" value="$idMesyuarat"/>
  <input type="hidden" name="flagDetail" id="flagDetail" value="$flagDetail"/>
  <input type="hidden" name="initiateFlagBuka" id="initiateFlagBuka"/>
  <input type="hidden" name="mode" id="mode" value="$mode"/>
  <input type="hidden" name="flagFrom" id="flagFrom"/>
</p>

#if ($errMsg != "")
<div class="info"><strong>$errMsg</strong></div>
#end

<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td><fieldset>
      <legend><b>CARIAN</b></legend>
      <table width="100%" align="center" border="0">
        <tr>
          <td width="30%" height="24" scope="row" align="right">Tajuk Mesyuarat : </td>
          <td width="70%"><input name="txtTajukMesyuarat" id="txtTajukMesyuarat" type="text" value="$txtTajukMesyuarat" size="50" maxlength="50" style="text-transform:uppercase;" >
            <input type="hidden" name="idPermohonan" />
            <input type="hidden" name="actionPenyewaan" />
          </td>
        </tr>
        <tr>
          <td width="30%" height="24" scope="row" align="right">Bil. Mesyuarat : </td>
          <td width="70%"><input name="txtBilMesyuarat" id="txtBilMesyuarat" type="text" value="$txtBilMesyuarat" size="50" maxlength="50" style="text-transform:uppercase;" ></td>
        </tr>
        <tr>
          <td width="30%" height="24" scope="row" align="right">Tarikh Mesyuarat : </td>
          <td width="70%"><input type="text" name="txtTarikhMesyuarat" id="txtTarikhMesyuarat" value="$!txtTarikhMesyuarat" onblur="check_date(this)" size="9"/>
            <a href="javascript:displayDatePicker('txtTarikhMesyuarat',false,'dmy');"><img border="0" src="../img/calendar.gif"/></td>
        </tr>
        <tr>
          <td scope="row"></td>
          <td>
            <input name="cmdCari" id="cmdCari" value="Cari" type="button" onclick="javascript:carian()">
            <input name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" type="reset" onClick="javascript:kosongkan('$')">
          </td>
        </tr>
        <tr>
          <td scope="row">&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
      </table>
      </fieldset></td>
  </tr>
  <tr>
    <td><fieldset>
      <legend><b>SENARAI PERMOHONAN</b></legend>
      #parse("app/utils/record_paging.jsp")
      <table align="center" width="100%">
        <tr>
          <td colspan="6" scope="row">
            <input name="cmdDaftar" type="button" value="Daftar Baru" onclick="javascript:daftarBaruMesyuarat()"/>
            &nbsp;
        </tr>
        <tr class="table_header" align="center">
          <td scope="row" width="4%" align="center"><strong>Bil</strong></td>
          <td width="36%"><strong>Tajuk Mesyuarat</strong></td>
          <td width="10%"><strong>Bil. Mesyuarat</strong></td>
          <td width="11%"><strong>Tarikh Mesyuarat</strong></td>
          <td width="20%"><strong>Lokasi</strong></td>
          <td width="13%"><strong>Status Mesyuarat</strong></td>
        </tr>
        #set ($list = "")
        #if ($SenaraiMesyurat.size() > 0)
        #foreach ($list in $SenaraiMesyurat)
        #if ($list.bil == '')
        #set( $row = "row1" )
        #elseif (($list.bil % 2) != 0)
        #set( $row = "row1" )
        #else 
        #set( $row = "row2" )
        #end
        <tr>
          <td class="$row" align="center">$list.bil</td>
          <td class="$row"><a href="javascript:papar('$list.idMesyuarat')" class="style1">$list.tajukMesyuarat</a></td>
          <td class="$row">$list.bilMesyuarat</td>
          <td class="$row" align="center">$list.tarikhMesyuarat</td>
          <td class="$row" align="center">$list.lokasiMesyuarat</td>
          <td class="$row" align="center">$list.statusMesyuarat</td>
        </tr>
        #end
        #else
        <tr>
          <td class="row1" align="center">&nbsp;</td>
          <td class="row1">Tiada Rekod</td>
          <td class="row1">&nbsp;</td>
          <td class="row1" align="center">&nbsp;</td>
          <td class="row1">&nbsp;</td>
        </tr>
        #end
      </table>
      </fieldset></td>
  </tr>
</table>
<script>
function doChangeNegeri() {
	doAjaxCall${formName}("doChangeNegeri");
}
function doChangeDaerah() {
	doAjaxCall${formName}("doChangeDaerah");
}
function bukaCarian(){
	document.${formName}.flagDetail.value = "buka";
	document.${formName}.actionPenyewaan.value = "";
	doAjaxCall${formName}("");
}
function tutupCarian(){
	document.${formName}.flagDetail.value = "";
	document.${formName}.actionPenyewaan.value = "";
	document.${formName}.txtNoFail.value = "";
	document.${formName}.txtNoFailNegeri.value = "";
	document.${formName}.txtPemohon.value = "";
	document.${formName}.txtNoPengenalan.value = "";
	document.${formName}.txdTarikhTerima.value = "";
	document.${formName}.txtNoPegangan.value = "";
	document.${formName}.socJenisHakmilikC.value = "";
	document.${formName}.txtNoHakmilik.value = "";	
	document.${formName}.txtNoWarta.value = "";
	document.${formName}.socLotC.value = "";
	document.${formName}.txtNoLot.value = "";		
	document.${formName}.socNegeriC.value = "";
	document.${formName}.socDaerahC.value = "";
	document.${formName}.socMukimC.value = "";		
	document.${formName}.socStatusC.value = "";
	doAjaxCall${formName}("");
}
function carian(){
	document.${formName}.actionPenyewaan.value = "";
	doAjaxCall${formName}("");
}
function kosongkan() {
	document.${formName}.reset();
	document.${formName}.txtNoFail.value = "";
	document.${formName}.txtNoFailNegeri.value = "";
	document.${formName}.txtPemohon.value = "";
	document.${formName}.txtNoPengenalan.value = "";
	document.${formName}.txdTarikhTerima.value = "";
	document.${formName}.socStatusC.value = "";
	doAjaxCall${formName}("");
}
function papar(idMesyuarat) {

	document.${formName}.idMesyuarat.value = idMesyuarat;
	document.${formName}.initiateFlagBuka.value = "Y";
	document.${formName}.flagFrom.value = "failTugasan";
	document.${formName}.actionMesyuarat.value = "papar";
	document.${formName}.submit();
}
function daftarBaruMesyuarat(){
	document.${formName}.actionMesyuarat.value = "daftarBaru";
	document.${formName}.mode.value = "new";
	document.${formName}.submit();
}
</script>
