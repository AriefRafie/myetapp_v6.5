<style type="text/css">
<!--
.style1 {
	color: #0033FF
}
.style2 {
	color: #FF0000;
	font-weight: bold;
}

.blink { animation: blink 1s steps(5, start) infinite; }
@keyframes blink {
    to {
        visibility: hidden;
    }
}
-->
</style>
<p>
  <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
  <input name="idNegeriUser" type="hidden" id="idNegeriUser" value="$idNegeriUser"/>
  <input name="flagDetail" type="hidden" id="flagDetail" value="$flagDetail"/>
  <input name="flagFrom" type="hidden" id="flagFrom"/>
 
</p>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td><fieldset>
      <legend><b>CARIAN</b></legend>
      <table width="100%" align="center" border="0">
        <tr>
          <td width="30%" height="24" scope="row" align="right">No. Fail : </td>
          <td width="70%"><input name="txtNoFail" id="txtNoFail" type="text" value="$txtNoFail" size="50" maxlength="50" style="text-transform:uppercase;" >
            <input type="hidden" name="idFail" />
            <input type="hidden" name="idStatus" />
            #if($flagDetail == '') <a href="javascript:bukaCarian();" class="style1">Buka Carian Terperinci </a> #else <a href="javascript:tutupCarian();" class="style1">Tutup Carian Terperinci </a> #end </td>
        </tr>
        <tr>
          <td width="30%" height="24" scope="row" align="right">No. Fail Negeri : </td>
          <td width="70%"><input name="txtNoFailNegeri" id="txtNoFailNegeri" type="text" value="$txtNoFailNegeri" size="50" maxlength="50" style="text-transform:uppercase;" ></td>
        </tr>
        <tr>
          <td width="30%" height="24" scope="row" align="right">Nama Pemohon : </td>
          <td width="70%"><input name="txtPemohon" id="txtPemohon" type="text" value="$txtPemohon" size="50" maxlength="50" style="text-transform:uppercase;" ></td>
        </tr>
        <tr>
          <td width="30%" height="24" scope="row" align="right"><em>MyID / MyCoID</em> : </td>
          <td width="70%"><input name="txtNoPengenalan" id="txtNoPengenalan" type="text" value="$txtNoPengenalan" size="50" maxlength="50" style="text-transform:uppercase;" ></td>
        </tr>
        <tr>
          <td width="30%" height="24" scope="row" align="right">Tarikh Terima : </td>
          <td width="70%"><input type="text" name="txdTarikhTerima" id="txdTarikhTerima" value="$txdTarikhTerima" onBlur="check_date(this)" size="9"/>
            <a href="javascript:displayDatePicker('txdTarikhTerima',false,'dmy');"><img border="0" src="../img/calendar.gif"/></td>
        </tr>
        <tr>
          <td scope="row" align="right">Status :</td>
          <td>$selectStatus</td>
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
          <td><input name="cmdCari" id="cmdCari" value="Cari" type="button" onClick="javascript:carian()">
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
        <tr class="table_header" align="center">
          <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
          <td width="15%"><strong>No. Fail</strong></td>
          <td width="15%"><strong>No. Fail Negeri</strong></td>
          <td width="18%"><strong>Nama Pemohon</strong></td>
          <td width="6%" align="center"><strong>Tarikh Terima</strong></td>
          <td width="6%" align="center"><strong>Tarikh Daftar</strong></td>
          <td width="10%"><strong>Status</strong></td>
          <td width="12%"><strong>Daftar Oleh</strong></td>
          <td width="10%"><strong>Tindakan Daripada</strong></td>
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
          <td class="$row">
          	<a href="javascript:papar('$list.idFail','$list.idStatus')" class="style1">$list.noFail</a>
          </td>
          <td class="$row">$list.noFailNegeri</td>
          <td class="$row">$list.namaPemohon</td>
          <td class="$row" align="center">$list.tarikhTerima</td>
          <td class="$row" align="center">$list.tarikhBukaFail</td>
          <td class="$row">$list.status</td>
          <td class="$row">$list.userLogin</td>
          #if ($list.userRole == '(PHP)PYWPenolongPegawaiTanahNegeri' || $list.userRole == '(PHP)PYWPenolongPengarahNegeri' || $list.userRole == '(PHP)PYWPengarahNegeri')
          <td class="$row" align="center">HQ</td>
          #else
          <td class="$row" align="center">Negeri</td>
          #end
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
	doAjaxCall${formName}("");
}
function kosongkan(flagDetail) {
	document.${formName}.reset();
	document.${formName}.txtNoFail.value = "";
	document.${formName}.txtNoFailNegeri.value = "";
	document.${formName}.txtPemohon.value = "";
	document.${formName}.txtNoPengenalan.value = "";
	document.${formName}.txdTarikhTerima.value = "";
	document.${formName}.socStatusC.value = "";
	if (flagDetail == 'buka'){
		document.${formName}.txtNoPegangan.value = "";
		document.${formName}.socJenisHakmilikC.value = "";
		document.${formName}.txtNoHakmilik.value = "";	
		document.${formName}.txtNoWarta.value = "";
		document.${formName}.socLotC.value = "";
		document.${formName}.txtNoLot.value = "";		
		document.${formName}.socNegeriC.value = "";
		document.${formName}.socDaerahC.value = "";
		document.${formName}.socMukimC.value = "";		
	}
	doAjaxCall${formName}("");
}
function papar(idFail,idStatus) {

	document.${formName}.idFail.value = idFail;
	document.${formName}.flagFrom.value = "failKeseluruhan";
	
	document.${formName}.action = "$EkptgUtil.getTabID("Penguatkuasaan",$portal_role)?_portal_module=ekptg.view.php2.FrmCRBMaklumatPermohonanView";
	document.${formName}.submit();
}
</script>
