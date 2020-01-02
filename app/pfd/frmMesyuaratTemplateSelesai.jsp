<style type="text/css">
<!--
  .link {color: #0000FF}
  .mandatori {color:#FF0000}
-->
</style>
<input type="hidden" id="idNegeri" name="idNegeri" value="$idNegeri" />
<input type="hidden" id="Mesyuarat_Unit" name="Mesyuarat_Unit" value="$Mesyuarat_Unit" />
<fieldset>
  <legend><strong>MAKLUMAT MESYUARAT</strong></legend>
  <br />
  <table width="100%" align="center">
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><div align="left"><strong>No Fail / No Rujukan Mesyuarat</strong></div></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap" class="link">$Mesyuarat_NoRujukanMesyuarat</td>
    </tr>
    <tr>
      <td width="29%" align="right" valign="top" nowrap="nowrap"><div align="left"><strong>Tajuk Mesyuarat</strong></div></td>
      <td width="1%" align="center" valign="top" nowrap="nowrap">:</td>
      <td width="70%" align="left" valign="top" nowrap="nowrap" class="link">
        $Mesyuarat_Tajuk</td>
    </tr>
    <!--tr>
      <td align="right" valign="top" nowrap="nowrap"><div align="left"><strong>Dari</strong></div></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap" class="link">
      $Mesyuarat_Dari</td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><div align="left"><strong>Hingga</strong></div></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap" class="link">
      $Mesyuarat_Hingga</td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><div align="left"><strong>Lokasi</strong></div></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap" class="link">
        $Mesyuarat_Lokasi</td>
    </tr-->
    #if($idNegeri == '16')
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><div align="left"><strong>Urusetia / Seksyen</strong></div></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap" class="link">
        $Mesyuarat_Seksyen</td>
    </tr>
    #else
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><div align="left"><strong>Urusetia / Unit</strong></div></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap" class="link">
        $Mesyuarat_Unit</td>
    </tr>
    #end
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><div align="left"><strong>No Fail</strong></div></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap" class="link">$Mesyuarat_NoFail</td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><div align="left"><strong>Disediakan Oleh</strong></div></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap" class="link">
        $Mesyuarat_DisediakanOleh</td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><div align="left"><strong>Disemak Oleh</strong></div></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap" class="link">
        $Mesyuarat_DisemakOleh      </td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><div align="left"><strong>Disahkan Oleh</strong></div></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap" class="link">$Mesyuarat_DisahkanOleh</td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><div align="left"><strong>Catatan</strong></div></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap" class="link">
        $Mesyuarat_Catatan</td>
    </tr>
  </table>
</fieldset>
<br />
<fieldset>
  <legend><strong>SETERUSNYA..</strong></legend>
  <br />
  <table width="100%" align="center">
    <tr>
      <td align="center" valign="top" nowrap="nowrap">
        <input type="button" id="cmdPrev" name="cmdPrev" value="Kembali" onclick="daftarAhli();" />
        <input type="button" id="cmdSenaraiTemplate" name="cmdSenaraiTemplate" value="Kembali ke Senarai Mesyuarat" onclick="senaraiTemplate();" />
        <input type="button" id="cmdJanaMesyuarat" name="cmdJanaMesyuarat" value="Panggil Mesyuarat" onclick="janaMesyuarat();" />
      </td>
    </tr>
  </table>
</fieldset>
<br />
<fieldset>
  <legend><strong>SENARAI AHLI</strong></legend>
  <br />
  <table width="100%"align="center">
    <tr class="table_header">
      <td width="10%" align="center" valign="top" nowrap="nowrap">No</td>
      <td width="20%" align="left" valign="top" nowrap="nowrap">Peranan</td>
      <td align="left" valign="top" nowrap="nowrap">Nama Ahli</td>
    </tr>
#set ($fail = '')
#foreach ($fail in $List_AhliMesyuarat)
	#if ($fail.No == '') 
    	#set ($row = 'row1')
    #elseif ($fail.No % 2 != 0)
	    #set ($row = 'row1')
    #else 
	    #set ($row = 'row2')
    #end
    #if ($fail.No != '')
    <tr>
      <td class="$row" align="center" valign="top" nowrap="nowrap">$fail.No</td>
      <td class="$row" align="left" valign="top" nowrap="nowrap">$fail.Peranan</td>
      <td class="$row" align="left" valign="top" nowrap="nowrap">$fail.NamaAhli</td>
    </tr>
	#end
#end
  </table>
</fieldset>
<br />
<input type="hidden" id="ID_MESYUARAT" name="ID_MESYUARAT" value="$ID_MESYUARAT" />
<input type="hidden" id="action" name="action" value="$action" />
<script type="text/javascript">
  function daftarAhli() {
	  document.${formName}.action = "?_portal_module=ekptg.view.pfd.FrmViewMesyuaratTemplate&action=daftarAhli&PrevPage=2";
	  document.${formName}.submit();
  }
  function janaMesyuarat() {
	  document.${formName}.action = "?_portal_module=ekptg.view.pfd.FrmViewMesyuarat&actionx=daftarMesyuarat&ID_MESYUARAT=&ID_TEMPLATE=$ID_MESYUARAT";
	  document.${formName}.submit();
  }
  function senaraiTemplate() {
	  document.${formName}.action = "?_portal_module=ekptg.view.pfd.FrmViewMesyuaratTemplateCarian&action=";
	  document.${formName}.submit();
  }  
  function cetakMesyuarat() {
		var url = "../servlet/ekptg.report.pfd.PFDReport?reportType=PDF&TYPE=4&MINITMESYUARAT_ID=$ID_MESYUARAT";
	    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	    if ((document.window != null) && (!hWnd.opener))
		hWnd.opener = document.window;
	    if (hWnd.focus != null) hWnd.focus();
  }
</script>