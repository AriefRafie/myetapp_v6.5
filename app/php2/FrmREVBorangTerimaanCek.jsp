<style type="text/css">
<!--
.style1 {
	color: #0033FF
}
-->
</style>
<p>
  <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
</p>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
<tr>
    <td><fieldset>
      <legend><b>CARIAN</b></legend>
      <table width="100%" align="center" border="0">
       <!--tambah tarikh -->
       <tr>
          <td width="30%" height="24" scope="row" align="right">Tarikh Terima : </td>
          <td width="70%"><input type="text" name="txtTarikhTerima" id="txtTarikhTerima" value="$txtTarikhTerima" size="9" onblur="check_date(this);"/>
            <a href="javascript:displayDatePicker('txtTarikhTerima',false,'dmy');"><img border="0" src="../img/calendar.gif"/> </td>
        </tr>
        
        <tr>
          <td width="30%" height="24" scope="row" align="right">Nama Penyewa : </td>
          <td width="70%"><input name="txtNamaPenyewa" id="txtNamaPenyewa" type="text" value="$txtNamaPenyewa" size="50" maxlength="50" style="text-transform:uppercase;">          </td>
        </tr>
        <tr>
          <td width="30%" height="24" scope="row" align="right">No Cek : </td>
          <td width="70%"><input name="txtNoCek" id="txtNoCek" type="text" value="$txtNoCek" size="50" maxlength="50" style="text-transform:uppercase;">          </td>
        </tr>
         <tr>
          <td width="30%" height="24" scope="row" align="right">Tarikh Cek : </td>
          <td width="70%"><input type="text" name="txtTarikhCek" id="txtTarikhCek" value="$txtTarikhCek" size="9" onblur="check_date(this);"/>
            <a href="javascript:displayDatePicker('txtTarikhCek',false,'dmy');"><img border="0" src="../img/calendar.gif"/> </td>
        </tr>
        <tr>
          <td width="30%" height="24" scope="row" align="right">Bank : </td>
          <td width="70%">$selectBank</td>
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
      <legend><b>SENARAI TERIMAAN</b></legend>
     #set ($pagingTitle = "Jumlah Carian")
        
		#parse("app/utils/record_paging.jsp")
      <table align="center" width="100%">
      <tr>
          <td colspan="6" scope="row">
          	<input name="cmdJana" type="button" value="Jana Laporan" onclick="janaBorangTerimaan()"/></td>
        </tr>
        <tr class="table_header">
          <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
          <td width="10%"><strong>Tarikh Terima</strong></td>
          <td width="30%"><strong>Nama Penyewa</strong></td>
          <td width="10%"><strong>No. Cek</strong></td>
          <td width="10%" ><strong>Tarikh Cek</strong></td>
          <td width="15%" align="center"><strong>Nama Bank</strong></td>
          <td width="15%" align="center"><strong>Amount (RM)</strong></td>
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
          <td class="$row" align="center">$list.tarikh</td>
          <td class="$row">$list.nama</td>
          <td class="$row">$list.noCek</td>
          <td class="$row" align="center">$list.tarikhCek</td>
          <td class="$row" align="center">$list.namaBank</td>
          <td class="$row" align="right">$list.kredit</td>
        </tr>
        #end
        #else
        <tr>
          <td class="row1" align="center">&nbsp;</td>
          <td class="row1">Tiada Rekod</td>
          <td class="row1">&nbsp;</td>
          <td class="row1">&nbsp;</td>
          <td class="row1">&nbsp;</td>
        </tr>
        #end
      </table>
      </fieldset></td>
  </tr>
</table>
<script>
function kosongkan() {
	//document.${formName}.reset();
	document.${formName}.txtNamaPenyewa.value = "";
	document.${formName}.txtNoCek.value = "";
	document.${formName}.socBankC.value = "";	
	doAjaxCall${formName}("");	
}
function carian(){
	document.${formName}.command.value = "cari";
	document.${formName}.action = "?_portal_module=ekptg.view.php2.FrmREVBorangTerimaanCekView"; 
	document.${formName}.submit();	
}

function janaBorangTerimaan() {
	var url = "../x/${securityToken}/ekptg.view.php2.FrmREVPopupCetakLaporanView?report=senaraiTerimaanCek";
    var hWnd = window.open(url,'printuser','width=1000,height=300, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
</script>
