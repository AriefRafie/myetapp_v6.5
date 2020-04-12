<div id="fileupload_progress"></div>
<div id="progressBar" style="display: none;">
<div id="progressBarBoxContent"></div>
</div>

#if ($completed)
<script>
parent.document.getElementById("fileupload_progress").innerHTML="<div class=\"success\">Fail berjaya dimuatnaik.</div>";
</script>
#end

<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #if ($flagPopup == 'openPopupDokumen')
  <tr>
    <td> #parse("app/php2/frmCRBOperasiImejDetail.jsp") </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
  #end
  <tr>
    <td><fieldset>
      <legend><strong>SENARAI IMEJ</strong></legend>
      <table align="center" width="100%">
        <!-- kmie, 20100812 (MacGDI) -->
		<!--         <tr> -->
		<!--           <td colspan="2" scope="row"><a href="http://g4nre.mygeoportal.gov.my" target="_blank" style="color:#0000FF">MacGDI</a></td> -->
		<!--         </tr> -->
        <!-- end edit (kmie) -->
        #if ($flagPopup == '')
        <tr>
          <td colspan="2" scope="row"><input name="cmdDaftar" type="button" value="Tambah" onClick="javascript:daftarDokumen()"/></td>
        </tr>
        #end
        <tr class="table_header">
          <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
          <td><strong>Nama Imej</strong></td>
        </tr>
        #set ($senaraiImejan = "")
        #if ($SenaraiImejan.size() > 0)
        #foreach ($senaraiImejan in $SenaraiImejan)
        #if ($senaraiImejan.bil == '')
        #set( $row = "row1" )
        #elseif (($senaraiImejan.bil % 2) != 0)
        #set( $row = "row1" )
        #else 
        #set( $row = "row2" )
        #end
        <tr>
          <td class="$row" align="center">$senaraiImejan.bil</td>
          <td class="$row"><a href="javascript:paparDokumen($senaraiImejan.idDokumen)" class="style2">$senaraiImejan.namaDokumen</a></td>
        </tr>
        #end
        #else
        <tr>
          <td class="row1" align="center">&nbsp;</td>
          <td class="row1">Tiada Rekod</td>
        </tr>
        #end
      </table>
      </fieldset></td>
  </tr>
</table>

