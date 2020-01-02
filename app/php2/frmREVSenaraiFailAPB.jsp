<script type="text/javascript" src="../../library/js/report.js" ></script>
<style type="text/css">
<!--
.style1 {
	color: #0033FF
}
.style2 {
	color: #FF0000;
	font-weight: bold;
}
.blink {
	animation: blink 1s steps(5, start) infinite;
}
@keyframes blink {
 to {
 visibility: hidden;
}
-->
</style>
<p>
  <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
  <input name="mode" type="hidden" id="mode" />
  <input type="hidden" name="idJadualPertama" />
  <input type="hidden" name="actionHasil"/>
  <input type="hidden" name="idFail" />
</p>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td><fieldset>
      <legend><b>CARIAN</b></legend>
      <table width="100%" align="center" border="0">
        <tr>
          <td width="30%" height="24" scope="row" align="right">No Fail : </td>
          <td width="70%"><input name="txtNoFail" id="txtNoFail" type="text" value="$txtNoFail" size="50" maxlength="50" style="text-transform:uppercase;">
          </td>
        </tr>
        <tr>
          <td width="30%" height="24" scope="row" align="right">Nama Pemohon / Syarikat: </td>
          <td width="70%"><input name="txtNamaPemohon" id="txtNamaPemohon" type="text" value="$txtNamaPemohon" size="50" maxlength="50" style="text-transform:uppercase;">
          </td>
        </tr>
        <tr>
          <td height="24" scope="row" align="right"><em>MyID / MyCoID</em> : </td>
          <td><input name="txtNoPengenalan" id="txtNoPengenalan" type="text" value="$txtNoPengenalan" size="50" maxlength="50" style="text-transform:uppercase;" /></td>
        </tr>
        <tr>
          <td width="30%" height="24" scope="row" align="right">No Lesen : </td>
          <td width="70%"><input name="txtNoLesen" id="txtNoLesen" type="text" value="$txtNoLesen" size="50" maxlength="50" style="text-transform:uppercase;">
          </td>
        </tr>
        <tr>
          <td scope="row" align="right"> Nama Bank :</td>
          <td>$selectBankC</td>
        </tr>
        <tr>
          <td width="30%" height="24" scope="row" align="right">No Cek : </td>
          <td width="70%"><input name="txtNoCek" id="txtNoCek" type="text" value="$txtNoCek" size="50" maxlength="50" style="text-transform:uppercase;">
          </td>
        </tr>
        <tr>
          <td scope="row" align="right">No. Resit :</td>
          <td><input name="txtNoResit" id="txtNoResit" type="text" value="$!txtNoResit" size="50" maxlength="50"/></td>
        </tr>
        <tr>
          <td scope="row"></td>
          <td><input name="cmdCari" id="cmdCari" value="Cari" type="button" onclick="javascript:carian()">
            <input name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" type="reset" onClick="javascript:kosongkan()">
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
      <legend><b>SENARAI FAIL</b></legend>
      #parse("app/utils/record_paging.jsp")
      <table align="center" width="100%">
        <tr>
          <td colspan="6" scope="row"><input name="cmdJanaPenerimaan" type="button" value="Jana Borang Penerimaan Bayaran" onclick="javascript:janaBorangDaftarMelAPB()"/>
            <input name="cmdJana" type="button" value="Jana Borang Penyerahan" onclick="javascript:janaBorangPenyerahanAPB()"/>
            <input name="cmdCetak" type="button" value="Cetak Senarai Fail" onclick="javascript:cetakSenaraiFail()"/></td>
        </tr>
        <tr class="table_header">
          <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
          <td width="20%"><strong>No Fail</strong></td>
          <td width="30%"><strong>Nama Pemohon/Syarikat</strong></td>
          <td width="10%" align="center"><strong>Tarikh Mohon</strong></td>
          <td width="25%"><strong>Status</strong></td>
          <td width="10%"><strong>No. Lesen</strong></td>
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
          <td class="$row" align="center">$list.bil </td>
          <td class="$row"><a href="javascript:papar('$list.idJadualPertama','$list.idFail')" class="style1">$list.noFail</a></td>
          <td class="$row">$list.namaPemohon</td>
          <td class="$row" align="center">$list.tarikhPermohonan</td>
          <td class="$row">$list.status</td>
          <td class="$row">$list.noLesen</td>
        </tr>
        #end
        #else
        <tr>
          <td class="row1" align="center">&nbsp;</td>
          <td colspan="5" class="row1">Tiada Rekod</td>
        </tr>
        #end
      </table>
      </fieldset></td>
  </tr>
</table>
<script>
function carian(){
	document.${formName}.actionHasil.value = "";
	doAjaxCall${formName}("");
}
function kosongkan() {
	document.${formName}.reset();
	document.${formName}.txtNoFail.value = "";
	document.${formName}.txtNamaPemohon.value = "";
	document.${formName}.txtNoPengenalan.value = "";
	document.${formName}.txtNoLesen.value = "";
	document.${formName}.socBankC.value = "";
	document.${formName}.txtNoCek.value = "";
	document.${formName}.txtNoResit.value = "";	
	doAjaxCall${formName}("");
}
function papar(idJadualPertama, idFail) {
	document.${formName}.actionHasil.value = "papar";
	document.${formName}.mode.value = "view";
	document.${formName}.idJadualPertama.value = idJadualPertama;
	document.${formName}.idFail.value = idFail;
	document.${formName}.submit();
}

function janaBorangPenyerahanAPB() {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmREVPopupCetakLaporanView?report=BorangPenyerahanAPB";
    var hWnd = window.open(url,'printuser','width=1000,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}

function janaBorangDaftarMelAPB() {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmREVPopupCetakLaporanView?report=BorangDaftarMelAPB";
    var hWnd = window.open(url,'printuser','width=1000,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}

function cetakSenaraiFail(){	
	
	var url = "../servlet/ekptg.report.php2.REVSenaraiFailAPB?NO_FAIL="+document.${formName}.txtNoFail.value+"&NAMA_PEMOHON="+document.${formName}.txtNamaPemohon.value+"&NO_PENGENALAN="+document.${formName}.txtNoPengenalan.value+"&NO_LESEN="+document.${formName}.txtNoLesen.value;
	
    var hWnd = window.open(url,'printuser','width=1000,height=200, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
</script>
