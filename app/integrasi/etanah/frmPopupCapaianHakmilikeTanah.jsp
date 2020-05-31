<style type="text/css">

#if ($!modul == 'htp')
<!--
#parse("css/eTapp_HTP.css") .style1 {
color: #0000FF
}
.style2 {
	color: #FF0000
}
-->

#else
<!--
#parse("css/eTapp_PPK.css") .style1 {
color: #0000FF
}
.style2 {
	color: #FF0000
}
-->
        
#end

</style>
<input type="hidden" name="modul" value="$!modul"/>
<input type="hidden" name="actionPopup"/>
<input type="hidden" name="hitButt"/>
<input type="hidden" name="idHakmilik" value="$idHakmilik"/>
<input type="hidden" name="noResit" value="$noResit"/>
<input type="hidden" name="idPermohonan" value="$idPermohonan"/>
<input type="hidden" name="idPPKHTA" />
<table width="100%" border="0">
  <tr>
    <td><fieldset>
      <legend>MAKLUMAT CARIAN HAKMILIK</legend>
      <table width="100%" border="0">
        #if ($flagMsg == 'Y')
        <tr>
          <td colspan="3">&nbsp;
            <div class="success">$outputMsg</div></td>
        </tr>
        #end
        #if ($flagMsg == 'N')
        <tr>
          <td colspan="3">&nbsp;
            <div class="error">CAPAIAN TIDAK BERJAYA : $outputMsg</div></td>
        </tr>
        #end
        <tr>
          <td width="29%" scope="row" align="right"><span class="style2">*</span> ID Hakmilik</td>
          <td width="1%">:</td>
          <td width="70%"><input name="txtHakmilik" id="txtHakmilik" type="text" value="$txtHakmilik" size="30" maxlength="50"/></td>
        </tr>
        <tr>
          <td scope="row" align="right"><span class="style2">*</span> Nombor Resit Carian</td>
          <td>:</td>
          <td><input name="txtNoResit" id="txtNoResit" type="text" value="$txtNoResit" size="30" maxlength="50"/></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td><input name="cmdSimpan" id="cmdSimpan" value="Hantar" type="button" onclick="javascript:hantar()">
            <input name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" type="reset" onclick="javascript:kosongkan()"/></td>
        </tr>
      </table>
      </fieldset></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td><fieldset>
      <legend>SENARAI HAKMILIK CARIAN</legend>
      #parse("app/utils/record_paging_popup.jsp")
      <table align="center" width="100%">
        <tr class="table_header">
          <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
          <td width="10%"><strong>ID Hakmilik</strong></td>
          <td width="15%"><strong>No. Resit</strong></td>
          <td width="15%"><strong>Tarikh Terima</strong></td>
          <td width="10%"><strong>Status Hakmilik</strong></td>
        </tr>
        #set ($senaraiCarianRasmi = "")
        #if ($SenaraiCarianRasmi.size() > 0)
        #foreach ($senaraiCarianRasmi in $SenaraiCarianRasmi)
        #if ($senaraiCarianRasmi.bil == '')
        #set( $row = "row1" )
        #elseif (($senaraiCarianRasmi.bil % 2) != 0)
        #set( $row = "row1" )
        #else 
        #set( $row = "row2" )
        #end
        <tr>
          <td class="$row" align="center">$senaraiCarianRasmi.bil</td>
          <td class="$row"><a href="javascript:papar('$senaraiCarianRasmi.idPPKHTA')" class="style1">$senaraiCarianRasmi.idHakmilik</a></td>
          <td class="$row">$senaraiCarianRasmi.noResit</td>
          <td class="$row">$senaraiCarianRasmi.tarikhTerima</td>
          #if ($senaraiCarianRasmi.flagAktif == 'Y')
          <td class="$row"><span style="color:#0000FF"><strong>BARU DITERIMA</strong></span></td>
          #elseif ($senaraiCarianRasmi.flagAktif == 'T')
          <td class="$row"><span style="color:#FF0000"><strong>TELAH DIDAFTAR</strong></span></td>
          #else
          <td class="$row">&nbsp;</td>
          #end </tr>
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
		document.${formName}.reset();
		document.${formName}.txtNoResit.value = "";
		document.${formName}.txtHakmilik.value = "";
		doAjaxCall${formName}("");
	}
	function hantar() {
		if(document.${formName}.txtNoResit.value == ""){
			alert('Sila masukkan No. Resit Carian.');
	  		document.${formName}.txtNoResit.focus(); 
			return; 
		}
		if(document.${formName}.txtHakmilik.value == ""){
			alert('Sila masukkan ID Hakmilik.');
	  		document.${formName}.txtHakmilik.focus(); 
			return; 
		}
		
		if ( !window.confirm("Adakah Anda Pasti ?") ){
			return;
		}
		
		document.${formName}.hitButt.value = "hantar";
		document.${formName}.actionPopup.value = "";
		document.${formName}.submit();
	}
	function papar(idPPKHTA) {	
		document.${formName}.idPPKHTA.value = idPPKHTA;
		document.${formName}.actionPopup.value = "papar";
		document.${formName}.submit();
	}
</script>
