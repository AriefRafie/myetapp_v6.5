<style type="text/css">
<!--
.style1 {
	color: #0000FF
}
#parse("css/eTapp_PHP.css")
-->
</style>
<p>
	<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
	<input type="hidden" name="actionPopup" value='$!actionPopup'/>
	<input type="hidden" name="idFail"/>
	<input type="hidden" name="idMesyuarat"/>
	<input type="hidden" name="hitButton" id="hitButton"/>
	<input type="hidden" name="step" id="step" value='$!step'/>
</p>

<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td><fieldset>
      <legend><b>SENARAI PERMOHONAN</b></legend>
      #parse("app/utils/record_paging_popup.jsp")
      <table align="center" width="100%">
		<tr class="table_header" align="center">
			<td scope="row" width="5%"><strong>Bil</strong></td>
			<td width="20%"><strong>No. Fail</strong></td>
			<td width="15%"><strong>Jenis Permohonan</strong></td>
			<td width="20%"><strong>Nama Pemohon</strong></td>
			<td width="10%"><strong>Pilih</strong></td>
		</tr>
        #set ($senaraiFail = "")
        #if ($SenaraiFail.size() > 0)
        #foreach ($senaraiFail in $SenaraiFail)
        #if ($senaraiFail.bil == '')
        #set( $row = "row1" )
        #elseif (($senaraiFail.bil % 2) != 0)
        #set( $row = "row1" )
        #else 
        #set( $row = "row2" )
        #end
        <tr>
          <td class="$row" align="center">$senaraiFail.bil</td>
          <td class="$row">$senaraiFail.noFail</a></td>
          <td class="$row">$!senaraiFail.jenisPermohonan</td>
          <td class="$row">$senaraiFail.namaPemohon</td>
          <td class="$row" align="center">
<!--           	<input type="checkbox" name="checkPermohonan" id="checkPermohonan$u.id" value="$u.id" onclick="$('err_checkPermohonan').innerHTML=''; at(this, event)" /> -->
          	<input type="checkbox" name="ids1" id="ids1" onClick="doUpdateCheckAll1()" value="$id_fail" title="Semak untuk pilih fail ini" >
          </td>
        #end
        #else
        <tr>
          <td class="row1">&nbsp;</td>
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
<br/><br/>
<table width="100%">
	<tr>
		<td align="left"><div id="err_checkPermohonan" style="color:#CC0000;font-weight:bold;border:2px #000"></div></td>
		<td align="center">
			<input type="button" name="btnPilihPermohonan" id="btnPilihPermohonan" value="Pilih" onClick="doPilih()"/>
			<input type="button" name="btnTutupPermohonan" id="btnTutupPermohonan" value="Tutup" onClick="doTutup()"/>
		</td>
	</tr>
</table>
<script>
function doTutup() {
	alert("1");
	document.${formName}.step.value = "";
	document.${formName}.submit();
}

function doPilih() {
	alert("1");
	if(validatePilihanPermohonan()){
		if ( !window.confirm("Adakah Anda Pasti?") ){
			return;
		}
		alert("2");
		document.${formName}.hitButton.value = "doSimpanPilihan";
		document.${formName}.submit();
	}
}

function validatePilihanPermohonan() {
	
}
</script>
