<input name="action" type="hidden" value="$action" />
<style type="text/css">
<!--
.style1 {color: #0000FF}
-->
</style>
<body>
<fieldset>
<legend>TUKARGUNA/ PENAWARAN/ PELEPASAN</legend>
<table width="100%" border="0">
  <tr>
    <td><fieldset>
    <legend>Carian</legend>
    <table width="100%" height="52" border="0">
      <tr>
        <td width="471"><div align="right">No Fail</div></td>
        <td width="11"><div align="center">:</div></td>
        <td width="709"><input name="txtNoFailCarian" type="text" id="txtNoFailCarian" size="50"></td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td><input type="submit" name="btnFind" id="btnFind" value="Carian" />
          <input type="submit" name="btnKosong" id="btnKosong" value="Kosongkan" /></td>
      </tr>
    </table>
    </fieldset>    </td>
  </tr>
  
  <tr>
    <td width="998"><fieldset>
    <legend>Senarai Fail TukarGuna/ Penawaran/ Pelepasan</legend>
    #parse("app/utils/record_paging.jsp") 
    <table width="100%" border="0" align="center">
      <tr class="table_header">
        <td><strong>Bil.</strong></td>
        <td><strong>No. Fail Seksyen</strong></td>
        <td><strong>Tarikh Buka Fail</strong></td>
        <td><strong>Urusan</strong></td>
        <td><strong>Kementerian Terkini</strong></td>
        <td><strong>Status Fail</strong></td>
      </tr>
      #foreach ($senaraiTPP in $SenaraiTukargunPenawaranPelepasan)
      <tr>
        <td>$senaraiTPP.bil</td>
        <td><a href="javascript:fail_detail('$senaraiTPP.idHakmilik')" class="style1">$senaraiTPP.noFail</a></td>
        <td>$senaraiTPP.tarikhMasuk</td>
        <td>$senaraiTPP.namaUrusan</td>
        <td>$senaraiTPP.namaKementerian</td>
        <td>&nbsp;</td>
      </tr>
      #end
    </table>
    </fieldset>    </td>
  </tr>
</table>
</fieldset>
<script>
function fail_detail(){   
	document.${formName}.action = "?_portal_module=ekptg.view.htp.FrmRekodCarianTukargunaPenawaranPelepasan&action=paparFail";
	document.${formName}.submit();
}
</script>