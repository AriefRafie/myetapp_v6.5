<style type="text/css">
<!--
  .link {color: #0000FF}
  .mandatori {color:#FF0000}
-->
</style>
<fieldset>
  <legend><strong>TEMPAHAN BILIK MESYUARAT</strong></legend><br />
  <table width="100%" align="center">
    <tr>
      <td width="29%" align="right" valign="top" nowrap="nowrap"><div align="left"><strong><span class="mandatori">*</span> Lokasi</strong></div></td>
      <td width="1%" align="center" valign="top" nowrap="nowrap">:</td>
      <td width="70%" align="left" valign="top" nowrap="nowrap">$Tempahan_Lokasi</td>
    </tr>
    <tr>
      <td  align="right" valign="top" nowrap="nowrap"><div align="left"><strong><span class="mandatori">*</span> Tarikh</strong></div></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td  align="left" valign="top" nowrap="nowrap"><input name="Tempahan_Tarikh" type="text" id="Tempahan_Tarikh" value="$!Tempahan_Tarikh" size="15" maxlength="10" readonly="readonly" />
      <a href="javascript:displayDatePicker('Tempahan_Tarikh',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0" /></a></td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><div align="left"><strong><span class="mandatori">*</span> Dari</strong></div></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap"><input name="Tempahan_Dari" type="text" id="Tempahan_Dari" value="$!Tempahan_Dari" size="10" />
        (<em>format: hhmm, contoh 1500</em>) </td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><div align="left"><strong><span class="mandatori">*</span> Hingga</strong></div></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap"><input name="Tempahan_Hingga" type="text" id="Tempahan_Hingga" value="$!Tempahan_Hingga" size="10" />
        (<em>format: hhmm, contoh 1500</em>) </td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><div align="left"></div></td>
      <td align="center" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="left" valign="top" nowrap="nowrap"><input type="button" id="cmdAvailability" name="cmdAvailability" value="Semak Kekosongan" onclick="checkAvailability();" /></td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><div align="left"></div></td>
      <td align="center" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="left" valign="top" nowrap="nowrap">&nbsp;</td>
    </tr>
  </table>
</fieldset>
<br />
#if ($isSavedSuccessfully == 'true')
  <table width="100%"  align="center">
    <tr>
      <td>
         <div>Tempahan mesyuarat telah berjaya untuk tarikh, lokasi dan masa yang dipilih.</div>
      </td>
    </tr>
  </table>
  <br />
#end
#if ($isCheckedAvailability == 'true')
  <table width="100%" align="center">
#if ($locIsAvailable != 'true')  
    <tr>
      <td colspan="3" align="center" valign="top" nowrap="nowrap">
        Maaf, tiada kekosongan untuk bilik mesyuarat di atas bagi masa dan tarikh yang diminta. Sila ubah lokasi/tarikh/masa mesyuarat dan semak semula.
      </td>
    </tr>
#else
    <tr>
      <td colspan="3" align="center" valign="top" nowrap="nowrap">
        Terdapat kekosongan bagi bilik mesyuarat untuk masa dan tarikh yang diminta. Sila masukkan maklumat mesyuarat di bawah.
      </td>
    </tr>
    <tr>
      <td align="right" width="29%" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="center" width="1%" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="left" width="70%" valign="top" nowrap="nowrap">&nbsp;</td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><span class="mandatori">*</span> <strong>No Fail / No Rujukan Dokumen</strong></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap"><input name="Tempahan_NoRujukanDokumen" type="text" id="Tempahan_NoRujukanDokumen" onkeyup="this.value=this.value.toUpperCase();" value="$!Tempahan_NoRujukanDokumen" size="50" maxlength="255" /></td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><span class="mandatori">*</span> <strong>Tajuk Mesyuarat</strong></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap">
        <input name="Tempahan_Tajuk" type="text" id="Tempahan_Tajuk" value="$!Tempahan_Tajuk" size="50" maxlength="255" onkeyup="this.value=this.value.toUpperCase();" />
      </td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><span class="mandatori">*</span> <strong>Urusetia / Seksyen</strong></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap">$Tempahan_Seksyen </td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="center" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="left" valign="top" nowrap="nowrap"><input type="button" id="cmdBook" name="cmdBook" value="Tempah Bilik Mesyuarat" onclick="bookTempahan();" /></td>
    </tr>
#end
  </table>
<br />
#end
<fieldset>
  <legend><strong>SENARAI TEMPAHAN BILIK MESYUARAT</strong></legend>
  <table width="100%" align="center">
    <tr>
      <td colspan="7" align="center" valign="top">
        Bulan: $Tempahan_Bulan
        &nbsp;Tahun: $Tempahan_Tahun
        <input type="button" id="cmdSearch" name="cmdSearch" value="Cari" onclick="searchTempahan();" />
      </td>
    </tr>
  </table>
  <table width="100%" style="border:1px solid #000000" align="center">
    <tr class="table_header">
      <td width="5%" align="center" valign="top" nowrap="nowrap">No</td>
      <td width="20%" align="left" valign="top" nowrap="nowrap">No Fail</td>
      <td width="30%" align="left" valign="top" nowrap="nowrap">Tajuk Mesyuarat</td>
      <td width="20%" align="left" valign="top" nowrap="nowrap">Lokasi</td>
      <td width="10%" align="center" valign="top" nowrap="nowrap">Tarikh</td>
      <td width="15%" align="center" valign="top" nowrap="nowrap">Masa</td>
    </tr>
#set ($fail = '')
#foreach ($fail in $List_Tempahan)
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
      <td class="$row" align="left" valign="top" nowrap="nowrap"><a href="javascript:viewMesyuarat('$fail.ID')" style="color:#0000FF">$fail.NoFail</a></td>
      <td class="$row" align="left" valign="top" nowrap="nowrap">$fail.TajukMesyuarat</td>
      <td class="$row" align="left" valign="top" nowrap="nowrap">$fail.Lokasi</td>
      <td class="$row" align="center" valign="top" nowrap="nowrap">$fail.Tarikh</td>
      <td class="$row" align="center" valign="top" nowrap="nowrap">$fail.Masa</td>
    </tr>
	#end
#end
  </table>
</fieldset>
<br />
<br />
<input type="hidden" id="ID_MESYUARAT" name="ID_MESYUARAT" value="$ID_MESYUARAT" />
<input type="hidden" id="idNegeri" name="idNegeri" value="$idNegeri" />
<input type="hidden" id="actionx" name="actionx" value="$actionx" />
<script type="text/javascript">
  function checkAvailability() {
	  if (document.${formName}.SOC_LOKASI.value == '') {
		  alert("Sila pilih lokasi untuk disemak.");
		  document.${formName}.SOC_LOKASI.focus();
		  return;
	  }
	  if (document.${formName}.Tempahan_Tarikh.value == '') {
		  alert("Sila pilih tarikh untuk disemak.");
		  document.${formName}.Tempahan_Tarikh.focus();
		  return;
	  }
	  if (document.${formName}.Tempahan_Dari.value == '') {
		  alert("Sila pilih waktu mula mesyuarat untuk disemak.");
		  document.${formName}.Tempahan_Dari.focus();
		  return;
	  }
	  if (document.${formName}.Tempahan_Hingga.value == '') {
		  alert("Sila pilih waktu habis mesyuarat untuk disemak.");
		  document.${formName}.Tempahan_Hingga.focus();
		  return;
	  }
	  if (document.${formName}.Tempahan_Dari.value > document.${formName}.Tempahan_Hingga.value) {
		  alert("Sila pastikan waktu mula mesyuarat adalah sebelum waktu habis mesyuarat.");
		  document.${formName}.Tempahan_Dari.focus();
		  return;
	  }
	  document.${formName}.action = "?_portal_module=ekptg.view.pfd.FrmViewMesyuaratTempahan";
	  doAjaxCall${formName}("checkAvailability");
  }
  function bookTempahan() {
	  if (document.${formName}.SOC_LOKASI.value == '') {
		  alert("Sila pilih lokasi mesyuarat.");
		  document.${formName}.SOC_LOKASI.focus();
		  return;
	  }
	  if (document.${formName}.Tempahan_Tarikh.value == '') {
		  alert("Sila pilih tarikh mesyuarat.");
		  document.${formName}.Tempahan_Tarikh.focus();
		  return;
	  }
	  if (document.${formName}.Tempahan_Dari.value == '') {
		  alert("Sila pilih waktu mula mesyuarat.");
		  document.${formName}.Tempahan_Dari.focus();
		  return;
	  }
	  if (document.${formName}.Tempahan_Hingga.value == '') {
		  alert("Sila pilih waktu habis mesyuarat.");
		  document.${formName}.Tempahan_Hingga.focus();
		  return;
	  }
	  if (document.${formName}.Tempahan_Dari.value > document.${formName}.Tempahan_Hingga.value) {
		  alert("Sila pastikan waktu mula mesyuarat adalah sebelum waktu habis mesyuarat.");
		  document.${formName}.Tempahan_Dari.focus();
		  return;
	  }
	  if (document.${formName}.Tempahan_NoRujukanDokumen.value == '') {
		  alert("Sila masukkan No Rujukan Dokumen.");
		  document.${formName}.Tempahan_NoRujukanDokumen.focus();
		  return;
	  }
	  if (document.${formName}.Tempahan_Tajuk.value == '') {
		  alert("Sila masukkan tajuk mesyuarat.");
		  document.${formName}.Tempahan_Tajuk.focus();
		  return;
	  }
	  if (document.${formName}.SOC_SEKSYEN.value == '') {
		  alert("Sila pilih seksyen mesyuarat.");
		  document.${formName}.SOC_SEKSYEN.focus();
		  return;
	  }
	  document.${formName}.action = "?_portal_module=ekptg.view.pfd.FrmViewMesyuaratTempahan";
	  doAjaxCall${formName}("bookTempahan");
  }
  function searchTempahan() {
	  document.${formName}.action = "?_portal_module=ekptg.view.pfd.FrmViewMesyuaratTempahan";
	  doAjaxCall${formName}("searchTempahan");
  }
  function viewMesyuarat(ID_MESYUARAT) {	
	  document.${formName}.action = "?_portal_module=ekptg.view.pfd.FrmViewMesyuarat&actionx=daftarMesyuarat&ID_MESYUARAT=" + ID_MESYUARAT;
	  document.${formName}.submit();
  }
</script>