<style type="text/css">
<!--
.style1 {
	color: #0033FF
}
-->
</style>
<p>
  <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
  <input type="hidden" name="actionOnline" />
  <input type="hidden" name="idFail" />
  <input type="text" name="idStatus" />
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
          </td>
        </tr>
        <tr>
          <td width="30%" height="24" scope="row" align="right">No Rujukan <i>Online</i> : </td>
          <td width="70%"><input name="txtNoPermohonan" id="txtNoPermohonan" type="text" value="$txtNoPermohonan" size="50" maxlength="50" style="text-transform:uppercase;" ></td>
        </tr>
        <tr>
          <td width="30%" height="24" scope="row" align="right">Tarikh Terima : </td>
          <td width="70%"><input type="text" name="txdTarikhTerima" id="txdTarikhTerima" value="$txdTarikhTerima" onblur="check_date(this)" size="9"/>
            <a href="javascript:displayDatePicker('txdTarikhTerima',false,'dmy');"><img border="0" src="../img/calendar.gif"/></td>
        </tr>
        <tr>
          <td scope="row"></td>
          <td><input name="cmdCari" id="cmdCari" value="Cari" type="button" onclick="javascript:carian()">
            <input name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" type="reset" onClick="javascript:kosongkan()"></td>
        </tr>
          <td scope="row">&nbsp;</td>
          <td>&nbsp;</td>
      </table>
      </fieldset></td>
  </tr>
  
  <tr>
    <td><fieldset>
      <legend><b>SENARAI PERMOHONAN</b></legend>
      #parse("app/utils/record_paging.jsp")
      <table align="center" width="100%">
        <tr>
          <td colspan="5" scope="row"><input name="cmdDaftar" type="button" value="Daftar Permohonan Baru" onclick="javascript:daftarBaru()"/></td>
        </tr>
        <tr class="table_header">
          <td scope="row" width="3%" align="center"><strong>Bil</strong></td>
          <td width="20%"><strong>No Rujukan Online</strong></td>
          <td width="20%"><strong>No Fail</strong></td>
          <td width="30%"><strong>Tujuan</strong></td>
          <td width="8%" align="center"><strong>Tarikh Mohon</strong></td>
          <td width="20%"><strong>Status</strong></td>
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
          <td class="$row"><a href="javascript:papar('$list.idFail','$list.idStatus','$list.idPemohon')" class="style1">$list.noPermohonan</a></td>
         <!--  <td class="$row">$list.noFail</td> --> 
          <td class="$row"><a href="javascript:paparRenewLesen('$list.idFail','$list.idStatus','$list.idPemohon')" class="style1">$list.noFail</a></td>
          <td class="$row">$list.tujuanPengambilan</td>
          <td class="$row" align="center">$list.tarikhPermohonan </td>
          <td class="$row">$list.status</td>
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

<div id="setSessionIdFail_result"></div>
<script>
function carian(){
	document.${formName}.actionOnline.value = "";
	document.${formName}.submit();
}

function papar(idFail,idStatus,idPemohon) {
	document.${formName}.idFail.value = idFail;
	document.${formName}.idStatus.value = idStatus;
	document.${formName}.idPemohon.value = idPemohon;
	document.${formName}.actionOnline.value = "seterusnya"; 	
	document.${formName}.submit();
}
function paparRenewLesen(idFail,idStatus,idPemohon) {
	document.${formName}.idFail.value = idFail;
	document.${formName}.idStatus.value = idStatus;
	document.${formName}.idPemohon.value = idPemohon;
	document.${formName}.actionOnline.value = "daftarBaruLesen";
	document.${formName}.submit();
}

function daftarBaru(){
	document.${formName}.actionOnline.value = "daftarBaru";;
	document.${formName}.idStatus.value = "";
	document.${formName}.submit();
}
</script>
