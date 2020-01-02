<div id="fileupload_progress"></div>
<div id="progressBar" style="display: none;">
<div id="progressBarBoxContent"></div>
</div>

#if ($completed)
<script>
parent.document.getElementById("fileupload_progress").innerHTML="<div class=\"success\">Fail berjaya dimuatnaik.</div>";
</script>
#end
#if ($limitExceed)
<script>
parent.document.getElementById("fileupload_progress").innerHTML="<div class=\"success\">Saiz fail yang dimuatnaik lebih besar daripada yang dibenarkan.</div>";
</script>
#end

<input name="idStatus" type="hidden" id="idStatus"/>
<input name="idPermohonan" type="hidden" id="idPermohonan"/>
<!-- <input name="idDokumen" type="hidden" id="idDokumen" value="$idDokumen"/> -->


<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #if ($flagPopup == 'openPopupDokumen')
  <tr>
    <td> #parse("app/php2/frmTKRPelanDetail.jsp") </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
  #end
  <tr>
    <td><fieldset>
      <legend><strong>SENARAI PELAN</strong></legend>
      <table align="center" width="100%">
        <!-- kmie, 20100812 (MacGDI) -->
        <tr>
          <td colspan="2" scope="row"><a href="http://g4nre.mygeoportal.gov.my" target="_blank" style="color:#0000FF">MacGDI</a></td>
        </tr>
        <!-- end edit (kmie) -->
        <tr>
          <td colspan="2" scope="row"><input name="cmdDaftar" type="button" value="Tambah" onClick="javascript:daftarDokumenP()"/></td>
        </tr>
      
        <tr class="table_header">
          <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
          <td><strong>Nama Pelan</strong></td>
        </tr>
        #set ($senaraiPelan = "")
        #if ($SenaraiPelan.size() > 0)
        #foreach ($senaraiPelan in $SenaraiPelan)
        #if ($senaraiPelan.bil == '')
        #set( $row = "row1" )
        #elseif (($senaraiPelan.bil % 2) != 0)
        #set( $row = "row1" )
        #else 
        #set( $row = "row2" )
        #end
        <tr>
          <td class="$row" align="center">$senaraiPelan.bil</td>
          <td class="$row"><a href="javascript:paparDokumenP($senaraiPelan.idDokumen)" class="style2">$senaraiPelan.namaPelan</a></td>
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