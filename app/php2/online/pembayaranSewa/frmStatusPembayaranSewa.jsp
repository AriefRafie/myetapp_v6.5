<style type="text/css">

.style1 {
	color: #0033FF
}

</style>
<p>
  <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'/>
  <input name="flagDetail" type="hidden" id="flagDetail" value="$flagDetail"/>
  <input type="hidden" name="actionPenyewaan" />
  <input type="hidden" name="idStatus" />
  <input type="hidden" name="idPemohon" />
  <input type="hidden" name="idFail">
  <input type="hidden" name="idHasil" id="idHasil" value="$idHasil"/>
  <input type="hidden" id="namamodul" name="namamodul"  >
  <input type="hidden" id="namatab" name="namatab"  >
</p>

<div id="divMainForm">
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td><fieldset>
        <legend><b>CARIAN</b></legend>
        <table width="100%" align="center" border="0">
          <tr>
            <td width="30%" height="24" scope="row" align="right">Nama Penyewa : </td>
            <td width="70%"><input name="findNamaPenyewa" id="findNamaPenyewa" type="text" value="$!findNamaPenyewa" size="50" maxlength="50" style="text-transform:uppercase;" ></td>
          </tr>
          <!-- <tr>
            <td width="30%" height="24" scope="row" align="right">Tarikh Resit : </td>
            <td width="70%"><input name="findTarikhResit" id="findTarikhResit" type="text" value="$!findTarikhResit" size="50" maxlength="50" style="text-transform:uppercase;" ></td>
          </tr> -->
          <tr>
            <td width="30%" height="24" scope="row" align="right">Tarikh Resit : </td>
            <td width="70%"><input name="findTarikhResit" id="findTarikhResit" value="$!findTarikhResit" size="11" type="text" onblur="check_date(this)" />
       		 	<img src="../img/calendar.gif" onclick="displayDatePicker('findTarikhResit',false,'dmy');"></td>
          </tr>   
          <tr>
          	<td>&nbsp</td>
        	<td>
        		<input type="button" name="cari" id="cari" value="Cari" onclick="javascript:carian();" />
  				<input type="button" name="kosongkan" id="kosongkan" value ="Kosongkan" onClick="javascript:findKosongkan();"/>
  				<input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="javascript:kembali();"/>
            </td>
          </tr>
        </table>
      </fieldset></td>
  </tr>
  <tr>
    <td><fieldset> 
      <legend><b>SENARAI PEMBAYARAN SEWA</b></legend>
      <table align="center" width="100%">
      
        <tr class="table_header">
          <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
          <td width="20%"><strong>No Fail</strong></td>
          <td width="25%"><strong>Nama Penyewa</strong></td>
          <!-- <td width="10%"><strong>No Siri Perjanjian</strong></td> -->
          <td width="20%"><strong>Tarikh Mula</strong></td>
          <td width="20%"><strong>Tarikh Tamat</strong></td>
        </tr>
        
        #set ($list = "")
        #if ($status_pembayaranSewa.size() > 0)
        #foreach ($list in $status_pembayaranSewa)
        #if ($list.BIL == '')
        #set( $row = "row1" )
        #elseif (($list.BIL % 2) != 0)
        #set( $row = "row1" )
        #else 
        #set( $row = "row2" )
        #end
        
        <tr>
          <td class="$row" align="center">$list.BIL</td>
          <td class="$row"><a href="javascript:doDivAjaxCall$formname('showDetails$list.idHasil','paparDetails','ID_HASIL=$list.idHasil&NO_FAIL=$list.NO_FAIL');" class="style1">$list.NO_FAIL</a></td>
          <td class="$row">$list.NAMA</td>
          <!-- <td class="$row">$list.NO_RUJUKAN</td> -->
          <td class="$row">$list.TARIKH_MULA</td>
          <td class="$row">$list.TARIKH_TAMAT</td>
        </tr>
        
        <tr>
          <td   colspan="10" id="showDetails$list.idHasil"></td>
         
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
</div>

<script>
function papar(idHasil) {
	document.${formName}.idHasil.value = idHasil;
	document.${formName}.command.value = "papar";
    document.${formName}.submit();
}
function findKosongkan() {
	document.${formName}.findNamaPenyewa.value = "";
	document.${formName}.findTarikhResit.value = "";
	document.${formName}.command.value = "cari";
	document.${formName}.submit();
}
function carian() {
	document.${formName}.command.value = "cari";
	document.${formName}.submit();
}
function kembali() {
	document.${formName}.namamodul.value = "";
	document.${formName}.namatab.value = "";
    document.${formName}.action = "?myrole=online";
    //document.${formName}.namatab.value = "Menu";
    document.${formName}.namamodul.value = "ekptg.view.online.FrmOnlineMenuUtama";
    document.${formName}.submit();
}
function janaPenyataAkaun(idHasil) {
	//alert("idHasil : "+idHasil);
	var url = "../servlet/ekptg.report.php2.REVPenyataAkaun?id_hasil="+idHasil;
    var hWnd = window.open(url,'printuser','width=1000,height=200, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
       hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	hWnd.focus();
}
</script>