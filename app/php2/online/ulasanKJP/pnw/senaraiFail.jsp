<style type="text/css">
<!--
.style1 {
	color: #0033FF
}
-->
</style>
<p>
  <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
  <input name="flagDetail" type="hidden" id="flagDetail" value="$flagDetail"/>
  <input name="idSuburusanPmhn" type="hidden" id="idSuburusanPmhn" />
  <input type="hidden" name="actionOnline" />
  <input type="hidden" name="idFail" />
  <input type="hidden" name="idStatus" />
  <input type="hidden" name="idPemohon" />
</p>

<table width="100%" border="0" cellspacing="2" cellpadding="2">
   <tr>
    <td><fieldset>
      <legend><b>CARIAN</b></legend>
      <table width="100%" align="center" border="0">
        <tr>
          <td width="30%" height="24" scope="row" align="right">No Fail : </td>
          <td width="70%"><input name="txtNoFail" id="txtNoFail" type="text" value="$txtNoFail" size="50" maxlength="50" style="text-transform:uppercase;" >
            #if($flagDetail == '')
            <a href="javascript:bukaCarian();" class="style1">Buka Carian Terperinci </a>
            #else
            <a href="javascript:tutupCarian();" class="style1">Tutup Carian Terperinci </a>
            #end
            </td>
        </tr>
        <tr>
          <td width="30%" height="24" scope="row" align="right">No Rujukan <em>Online</em>: </td>
          <td width="70%"><input name="txtNoPermohonan" id="txtNoPermohonan" type="text" value="$txtNoPermohonan" size="50" maxlength="50" style="text-transform:uppercase;" ></td>
        </tr>
        <tr>
          <td width="30%" height="24" scope="row" align="right">Tarikh Mohon : </td>
          <td width="70%"><input type="text" name="txdTarikhTerima" id="txdTarikhTerima" value="$txdTarikhTerima" onblur="check_date(this)" size="9"/>
            <a href="javascript:displayDatePicker('txdTarikhTerima',false,'dmy');"><img border="0" src="../img/calendar.gif"/></td>
        </tr>
		#if($flagDetail == 'buka')
        <tr>
          <td scope="row" align="right">No Pegangan Hakmilik :</td>
          <td><input name="txtNoPegangan" id="txtNoPegangan" type="text" value="$txtNoPegangan" size="30" maxlength="50"/></td>
        </tr>
        <tr>
          <td scope="row" align="right"> Jenis Hakmilik :</td>
          <td>$selectJenisHakmilik</td>
        </tr>
        <tr>
          <td scope="row" align="right"> No Hakmilik :</td>
          <td><input name="txtNoHakmilik" id="txtNoHakmilik" type="text" value="$txtNoHakmilik" size="30" maxlength="50" /></td>
        </tr>
        <tr>
          <td scope="row" align="right"> No Warta :</td>
          <td><input name="txtNoWarta" id="txtNoWarta" type="text" value="$txtNoWarta" size="30" maxlength="50"/></td>
        </tr>
        <tr>
          <td scope="row" align="right">Jenis Lot :</td>
          <td>$selectLot</td>
        </tr>
        <tr>
          <td scope="row" align="right">No Lot :</td>
          <td><input name="txtNoLot" id="txtNoLot" type="text" value="$txtNoLot" size="30" maxlength="50"/></td>
        </tr>
        <tr>
          <td scope="row" align="right">Negeri :</td>
          <td>$selectNegeri</td>
        </tr>
        <tr>
          <td scope="row" align="right">Daerah :</td>
          <td>$selectDaerah</td>
        </tr>
        <tr>
          <td scope="row" align="right">Mukim :</td>
          <td>$selectMukim</td>
        </tr>
		#end
        <tr>
          <td scope="row"></td>
          <td><input name="cmdCari" id="cmdCari" value="Cari" type="button" onclick="javascript:carian()">
            <input name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" type="reset" onClick="javascript:kosongkan('$flagDetail')"></td>
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
        #if ($userRole == 'online_kjp' && $layerKJP == '1')
        <tr>
          <td colspan="6" scope="row"><input name="cmdDaftar" type="button" value="Daftar Permohonan" onclick="javascript:daftarBaru()"/></td>
        </tr>
        #end
        <tr class="table_header">
          <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
          <td width="30%"><strong>No Rujukan <i>Online</i></strong></td>
          <td width="30%"><strong>No Fail</strong></td>
          <td width="25%"><strong>Tajuk Fail</strong></td>
          <td width="10%" align="center"><strong>Tarikh Mohon</strong></td>
          <td width="15%"><strong>Status</strong></td>
          <td width="15%"><strong>Urusan</strong></td>
        </tr>
        #set ($list = "")
        #if ($SenaraiFail.size() > 0)
        #foreach ($list in $SenaraiFail)
        #if ($list.bil == '')
        #set( $row = "row1" )
        #elseif (($list.bil % 2) != 0)
        #set( $row = "row1" )
        #else
        #set( $row = "row2" )
        #end
        <tr>
          <td class="$row" align="center">$list.bil</td>
          <td class="$row"><a href="javascript:papar('$list.idFail','$list.idStatus')" class="style1">$list.noPermohonan</a></td>
          <td class="$row">$list.noFail</td>
          <td class="$row">$list.tajukFail</td>
          <td class="$row" align="center">$list.tarikhTerima </td>
          <td class="$row">$list.keterangan</td>
          <td class="$row">$list.namaSuburusan</td>
        </tr>
        #end
        #else
        <tr>
          <td class="row1" align="center">&nbsp;</td>
          <td class="row1">Tiada Rekod</td>
          <td class="row1">&nbsp;</td>
          <td class="row1" align="center">&nbsp;</td>
          <td class="row1">&nbsp;</td>
          <td class="row1">&nbsp;</td>
        </tr>
        #end
      </table>
      </fieldset></td>
  </tr>
</table>
<div id="setSessionIdFail_result"></div>
<script>
function doChangeNegeri() {
	doAjaxCall${formName}("doChangeNegeri");
}
function doChangeDaerah() {
	doAjaxCall${formName}("doChangeDaerah");
}
function bukaCarian(){
	document.${formName}.flagDetail.value = "buka";
	document.${formName}.actionOnline.value = "";
	doAjaxCall${formName}("");
}
function tutupCarian(){
	document.${formName}.flagDetail.value = "";
	document.${formName}.actionOnline.value = "";
	document.${formName}.txtNoFail.value = "";
	document.${formName}.txtNoPermohonan.value = "";
	document.${formName}.txdTarikhTerima.value = "";
	document.${formName}.txtNoPegangan.value = "";
	document.${formName}.socJenisHakmilik.value = "";
	document.${formName}.txtNoHakmilik.value = "";
	document.${formName}.txtNoWarta.value = "";
	document.${formName}.socJenisLot.value = "";
	document.${formName}.txtNoLot.value = "";
	document.${formName}.socNegeriC.value = "";
	document.${formName}.socDaerahC.value = "";
	document.${formName}.socMukimC.value = "";
	doAjaxCall${formName}("");
}
function carian(){
	document.${formName}.actionOnline.value = "";
	doAjaxCall${formName}("");
}
function kosongkan(flagDetail) {
	document.${formName}.reset();
	document.${formName}.txtNoFail.value = "";
	document.${formName}.txtNoPermohonan.value = "";
	document.${formName}.txdTarikhTerima.value = "";
	if (flagDetail == 'buka'){
		document.${formName}.txtNoPegangan.value = "";
		document.${formName}.socJenisHakmilik.value = "";
		document.${formName}.txtNoHakmilik.value = "";
		document.${formName}.txtNoWarta.value = "";
		document.${formName}.socJenisLot.value = "";
		document.${formName}.txtNoLot.value = "";
		document.${formName}.socNegeriC.value = "";
		document.${formName}.socDaerahC.value = "";
		document.${formName}.socMukimC.value = "";
	}
	doAjaxCall${formName}("");
}
function papar(idFail,idStatus) {

	document.${formName}.idFail.value = idFail;
	document.${formName}.idStatus.value = idStatus;
	document.${formName}.actionOnline.value = "seterusnya";
	document.${formName}.submit();
}
function daftarBaru(){
	document.${formName}.actionOnline.value = "daftarBaru";
	document.${formName}.submit();
}
</script>
