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
  <input name="flagFrom" type="hidden" id="flagFrom"/>
  <input name="initiateFlagBuka" type="hidden" id="initiateFlagBuka"/>
  <input name="idNegeriUser" type="hidden" id="idNegeriUser" value="$idNegeriUser"/>
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
          <td width="30%" height="24" scope="row" align="right">No Fail : </td>
          <td width="70%"><input name="txtNoFail" id="txtNoFail" type="text" value="$txtNoFail" size="50" maxlength="50" style="text-transform:uppercase;" >
            <input type="hidden" name="idFail" />
            <input type="hidden" name="idPermohonan" />
            <input type="hidden" name="idStatus" />
            <input type="hidden" name="actionPenyewaan" />
            #if($flagDetail == '') <a href="javascript:bukaCarian();" class="style1">Buka Carian Terperinci </a> #else <a href="javascript:tutupCarian();" class="style1">Tutup Carian Terperinci </a> #end </td>
        </tr>
        <tr>
          <td width="30%" height="24" scope="row" align="right">No Fail Negeri : </td>
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
          <td width="70%"><input type="text" name="txdTarikhTerima" id="txdTarikhTerima" value="$txdTarikhTerima" onblur="check_date(this)" size="9"/>
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
          <td scope="row" align="right">Jenis Hakmilik :</td>
          <td>$selectJenisHakmilik</td>
        </tr>
        <tr>
          <td scope="row" align="right">No Hakmilik :</td>
          <td><input name="txtNoHakmilik" id="txtNoHakmilik" type="text" value="$txtNoHakmilik" size="30" maxlength="50" /></td>
        </tr>
        <tr>
          <td scope="row" align="right">No Warta :</td>
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
        <tr>
          <td colspan="6" scope="row">
          	#if ($userRole == '(PHP)PYWPenolongPegawaiTanahNegeri')
            <input name="cmdDaftar" type="button" value="Daftar Permohonan Baru" onclick="javascript:daftarBaru()"/>
            &nbsp;
            #end
            <input name="cmdSemakan" type="button" value="Semakan Maklumat Tanah" onclick="javascript:semakanMaklumatTanah()"/> 
            &nbsp;
            <input name="cmdSemakanBorangK" type="button" value="Semakan Maklumat Borang K" onclick="javascript:semakanMaklumatBorangK()"/></td>
        </tr>
        <tr class="table_header" align="center">
          <td scope="row" width="4%" align="center"><strong>Bil</strong></td>
          <td width="20%"><strong>No Fail</strong></td>
          <td width="18%"><strong>No Fail Negeri</strong></td>
          <td width="20%"><strong>Nama Pemohon</strong></td>
          <td width="7%"><strong>Tarikh Terima</strong></td>
          <td width="10%"><strong>Status</strong></td>
          <td width="8%"><strong>Tindakan Daripada</strong></td>
          <td width="18%"><strong>Daftar Oleh</strong></td>
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
          #if ($list.noSambungan != '' && $list.noSambungan != '0')
          <td class="$row"><a href="javascript:papar('$list.idFail','$list.idStatus')" class="style1">$list.noFail</a>&nbsp;(SAMBUNGAN)
          &nbsp;&nbsp;
          #if($list.flagBuka == 'T')
          #if($list.flagMT == 'Y')
          <span class="style1"><blink><strong>MAKLUMAT TAMBAHAN</strong></blink></span>
          #elseif($list.flagPindaan == 'Y')
          <span class="style1"><blink><strong>PINDAAN MAKLUMAT</strong></blink></span>
          #elseif($list.flagPembetulan == 'Y')
          <span class="style1"><blink><strong>PEMBETULAN MAKLUMAT</strong></blink></span>
          #else
          <span class="style1"><blink><strong>BARU</strong></blink></span>
          #end
          #end 
          </td>
          #else
          <td class="$row"><a href="javascript:papar('$list.idFail','$list.idStatus')" class="style1">$list.noFail</a>
          &nbsp;&nbsp;
          #if($list.flagBuka == 'T')
          #if($list.flagMT == 'Y')
          <span class="style1"><blink><strong>MAKLUMAT TAMBAHAN</strong></blink></span>
          #elseif($list.flagPindaan == 'Y')
          <span class="style1"><blink><strong>PINDAAN MAKLUMAT</strong></blink></span>
          #elseif($list.flagPembetulan == 'Y')
          <span class="style1"><blink><strong>PEMBETULAN MAKLUMAT</strong></blink></span>
          #else
          <span class="style1"><blink><strong>BARU</strong></blink></span>
          #end
          #end
          </td>
          #end
          <td class="$row">$list.noFailNegeri</td>
          <td class="$row">$list.namaPemohon</td>
          <td class="$row" align="center">$list.tarikhTerima</td>
          <td class="$row">$list.status</td>
          #if($list.idStatus == '1610213' || $list.idStatus == '1610201' || $list.idStatus == '1610206' || $list.idStatus == '1610214')
          <td class="$row" align="center">HQ</td>
          #else
          <td class="$row" align="center">Negeri</td>
          #end
          <td class="$row">$list.userLogin</td>
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
	document.${formName}.initiateFlagBuka.value = "Y";
	document.${formName}.flagFrom.value = "failTugasan";	

	if (idStatus == '1610198'){
		document.${formName}.action = "$EkptgUtil.getTabID("Penyewaan",$portal_role)?_portal_module=ekptg.view.php2.FrmPYWMaklumatPermohonanView";
	} else if (idStatus == '1610199'){
		document.${formName}.action = "$EkptgUtil.getTabID("Penyewaan",$portal_role)?_portal_module=ekptg.view.php2.FrmPYWJabatanTeknikalView";
	} else if (idStatus == '1610200'){
		document.${formName}.action = "$EkptgUtil.getTabID("Penyewaan",$portal_role)?_portal_module=ekptg.view.php2.FrmPYWLawatanTapakView";
	} else if (idStatus == '1610213'){
		document.${formName}.action = "$EkptgUtil.getTabID("Penyewaan",$portal_role)?_portal_module=ekptg.view.php2.FrmPYWKertasRingkasanView";
	} else if (idStatus == '1610201'){
		document.${formName}.action = "$EkptgUtil.getTabID("Penyewaan",$portal_role)?_portal_module=ekptg.view.php2.FrmPYWMesyuaratView";
	} else if (idStatus == '1610206'){
		document.${formName}.action = "$EkptgUtil.getTabID("Penyewaan",$portal_role)?_portal_module=ekptg.view.php2.FrmPYWKeputusanView";
	} else if (idStatus == '1610214' || idStatus == '1610195'){
		document.${formName}.action = "$EkptgUtil.getTabID("Penyewaan",$portal_role)?_portal_module=ekptg.view.php2.FrmPYWPerjanjianView";
	} else if (idStatus == '1610221'){
		document.${formName}.action = "$EkptgUtil.getTabID("Penyewaan",$portal_role)?_portal_module=ekptg.view.php2.FrmPYWPenamatanPerjanjianView";
	} else {
		document.${formName}.action = "$EkptgUtil.getTabID("Penyewaan",$portal_role)?_portal_module=ekptg.view.php2.FrmPYWMaklumatPermohonanView";
	} 
	document.${formName}.submit();
}
function daftarBaru(){
	document.${formName}.actionPenyewaan.value = "daftarBaru";
	document.${formName}.submit();
}
function semakanMaklumatTanah() {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmPopupSemakanMaklumatTanahView";
    var hWnd = window.open(url,'printuser','width=1000,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
function semakanMaklumatBorangK() {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmPopupSemakanMaklumatBorangKView";
    var hWnd = window.open(url,'printuser','width=1000,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
</script>
