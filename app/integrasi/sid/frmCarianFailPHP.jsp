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
        <tr>
          <td width="30%" height="24" scope="row" align="right">No Fail : </td>
          <td width="70%"><input name="txtNoFail" id="txtNoFail" type="text" value="$!noFail" size="50" maxlength="50" style="text-transform:uppercase;" >
          </td>
        </tr>
        <tr>
          <td width="30%" height="24" scope="row" align="right">Negeri : </td>
          <td width="70%">$!selectNegeri
          </td>
        </tr>
        <tr>
          <td width="30%" height="24" scope="row" align="right">Daerah : </td>
          <td width="70%">$!selectDaerah
          </td>
        </tr>
        <tr>
          <td width="30%" height="24" scope="row" align="right">Jenis Fail : </td>
          <td width="70%"><select name="socJenisFail" id="socJenisFail" style="width:260px;">
              <option value="">SILA PILIH</option>
              #if ($!userRole == '(PHP)UserAPB' || $!userRole == '(PHP)UserHasil')
              <option $selectedAPB value="APB">AKTA PELANTAR BENUA</option>
              #end
              #if ($!userRole == '(PHP)UserHasil')
              <option $selectedREV value="REV">HASIL</option>
              #end
              #if ($!userRole == '(PHP)UserPelepasan' || $!userRole == '(PHP)UserHasil')
              <option $selectedPLP value="PLP">PELEPASAN</option>
              #end
              #if ($!userRole == '(PHP)UserPelepasan')
              <option $selectedPNW value="PNW">PENAWARAN</option>
              <option $selectedTKR value="TKR">TUKARGUNA</option>
              #end
              #if ($!userRole == '(PHP)PYWPengarahHQ' || $!userRole == '(PHP)PYWPenolongPengarahHQ' || $!userRole == '(PHP)PYWPenolongPegawaiTanahHQ' || $!userRole == '(PHP)UserHasil')
              <option $selectedPYW value="PYW">PENYEWAAN</option> 
              #end 
              #if ($!userRole == '(PHP)UserPenguatkuasaan')
              <option $selectedCRB value="CRB">PENGUATKUASAAN</option>     
              #end            
            </select>
          </td>
        </tr>
        <tr>
          <td scope="row"></td>
          <td><input name="cmdCari" id="cmdCari" value="Cari" type="button" onclick="javascript:carian()">
            <input name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" type="reset" onClick="javascript:kosongkan()"></td>
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
      <legend><b>SENARAI DOKUMEN</b></legend>
      #parse("app/utils/record_paging.jsp")
      <table align="center" width="100%">
        <tr class="table_header">
          <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
          <td width="30%"><strong>No Fail</strong></td>
          <td ><strong>Negeri</strong></td>
          <td ><strong>Daerah</strong></td>
          <td ><strong>Jenis Dokumen</strong></td>
          <td ><strong>Dokumen</strong></td>
        </tr>
        #set ($!list = "")
        #if ($!SenaraiFail.size() > 0)
        #set ( $count = $startNumber )
        #foreach ($!list in $!SenaraiFail)
        #set ( $count = $count + 1 )
        #if ($count == '')
        #set( $row = "row1" )
        #elseif (($count % 2) != 0)
        #set( $row = "row1" )
        #else 
        #set( $row = "row2" )
        #end
        <tr>
          <td class="$row" align="center">$!count</td>
          <td class="$row">$!list.noFail</td>
          <td class="$row">$!list.negeri</td>
          <td class="$row">$!list.daerah</td>
          <td class="$row">$!list.jenisDokumen</td>
          <td class="$row"><a href="javascript:paparDokumen('$!list.dirDokumen')" class="style1">$!list.namaDokumen</a></td>
        </tr>
        #end
        #else
        <tr>
          <td class="row1" align="center">&nbsp;</td>
          <td class="row1" colspan="5">Tiada Rekod</td>
        </tr>
        #end
      </table>
      </fieldset></td>
  </tr>
</table>
<script>
function carian(){
	doAjaxCall${formName}("carian");
}
function kosongkan() {
	document.${formName}.reset();
	document.${formName}.txtNoFail.value = "";
	document.${formName}.socNegeri.value = "";
	document.${formName}.socDaerah.value = "";
	document.${formName}.socJenisFail.value = "";
	doAjaxCall${formName}("");
}
function doChangeNegeri(){
	doAjaxCall${formName}("doChangeNegeri");
}
function paparDokumen(url){	
	
    var hWnd = window.open(url,'printuser','width=1000,height=200, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
</script>
