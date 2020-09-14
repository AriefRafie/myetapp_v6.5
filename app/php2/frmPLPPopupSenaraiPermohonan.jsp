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
	<input type="hidden" name="idMesyuarat" value='$!idMesyuarat'/>
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
          	<input type="checkbox" name="checkPermohonan" id="checkPermohonan$senaraiFail.idPermohonan" value="$senaraiFail.idPermohonan" onclick="$('err_checkPermohonan').innerHTML=''; at(this, event)" />
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
			##<input type="button" name="btnTutupPermohonan" id="btnTutupPermohonan" value="Tutup" onClick="doTutup()"/>
		</td>
	</tr>
</table>
<script>
function doTutup() {
	document.${formName}.actionPopup.value = "tutup";
	document.${formName}.submit();
}

function doPilih() {
	if(validatePilihanPermohonan()){
		if ( !window.confirm("Adakah Anda Pasti?") ){
			return;
		}
		document.${formName}.hitButton.value = "doSimpanPilihan";
		document.${formName}.submit();
	}
}

function validatePilihanPermohonan() {
	var err_count = 0;
	var checking = 0;
	var size = document.${formName}.checkPermohonan.length ;
	
	if( size > 1 ){
		for( var i = 0; i < document.${formName}.checkPermohonan.length; i++ ){
    		if(document.${formName}.checkPermohonan[i].checked){ 
    			checking += 1;
    		};
    	}
	} else {
		if( document.${formName}.checkPermohonan.checked == true ){
			checking += 1; 
		}
	}
	
	if ( checking == 0 ){
		$('err_checkPermohonan').innerHTML = "Sila pilih permohonan terlebih dahulu"; err_count++;  
	}
	
	if (err_count == 0) {
	}
	
	return err_count == 0;
}
</script>
