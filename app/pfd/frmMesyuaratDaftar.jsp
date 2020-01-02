<style type="text/css">
<!--
  .link {color: #0000FF}
  .mandatori {color:#FF0000}
-->
</style>
<input type="hidden" id="idNegeri" name="idNegeri" value="$idNegeri" />
<input type="hidden" id="SOC_UNIT" name="SOC_UNIT1" value="$SOC_UNIT" />
<input type="hidden" id="Mesyuarat_Unit" name="Mesyuarat_Unit" value="$Mesyuarat_Unit" />
#if ($locIsUnavailable == 'true')
<fieldset>
  <div class="error">Lokasi yang dipilih sudah ditempah. Sila pilih tarikh, masa atau lokasi yang lain.</div>
</fieldset>
<br />
#end
#if ($statusProses == '5')
<fieldset>
  <table width="100%" align="center">
#if ($hideSaveButton != 'true')  
    <tr>
      <td style="width:30%; text-align:right">&nbsp;</td>
      <td style="width:70%">
        <input type="radio" id="optJenisReport" name="optJenisReport" value="6" checked="checked" />Cetak Surat Panggilan Mesyuarat<br />
      </td>
    </tr>

    <tr>
      <td style="text-align:right">&nbsp;</td>
      <td>
        <input type="radio" id="optJenisReport" name="optJenisReport" value="4" />Cetak Minit Mesyuarat<br />
      </td>
    </tr>
    
    <tr>
      <td style="text-align:right">&nbsp;</td>
      <td>
        <input type="radio" id="optJenisReport" name="optJenisReport" value="15" />Cetak Minit Mesyuarat (Maklumbalas)<br />
      </td>
    </tr>
    
    <tr>
      <td style="text-align:right">&nbsp;</td>
      <td>
        <input type="radio" id="optJenisReport" name="optJenisReport" value="5" />Cetak Surat Tempahan Makanan<br />
      </td>
    </tr>
    <!--tr>
      <td style="text-align:right">5.</td>
      <td>
        <input type="radio" id="optJenisReport" name="optJenisReport" value="8" />Cetak Maklumbalas<br />
      </td>
    </tr-->
        <tr>
      <td style="text-align:right">&nbsp;</td>
      <td>
        <input type="radio" id="optJenisReport" name="optJenisReport" value="9" />Cetak Lampiran Senarai Edaran <br />
      </td>
    </tr>
        <tr>
      <td style="text-align:right">&nbsp;</td>
      <td>
        <input type="radio" id="optJenisReport" name="optJenisReport" value="10" />Cetak Borang Kehadiran<br />
      </td>
    </tr>
        <!--tr>
      <td style="text-align:right">8.</td>
      <td>
        <input type="radio" id="optJenisReport" name="optJenisReport" value="11" />Cetak Senarai Kehadiran<br />
      </td>
    </tr-->
#else  
    <tr>
      <td style="width:30%; text-align:right">&nbsp;</td>
      <td style="width:70%">
        <input type="radio" id="optJenisReport" name="optJenisReport" value="4" checked="checked" />Cetak Minit Mesyuarat<br />
      </td>
    </tr>
#end    
    <tr>
      <td style="text-align:right">&nbsp;</td>
      <td>
      #if($idNegeri == '16')
      <input type="button" id="cmdCetakMesyuarat" name="cmdCetakMesyuarat" value="Cetak" onclick="cetakMesyuarat('$ID_MESYUARAT');" />
      #else
            <input type="button" id="cmdCetakMesyuarat" name="cmdCetakMesyuarat" value="Cetak" onclick="cetakMesyuaratNegeri('$ID_MESYUARAT');" />
      #end
      </td>
      </tr>
  </table>
</fieldset>
#end
&nbsp;
<fieldset>
  <legend><strong>DAFTAR MESYUARAT</strong></legend>
  <br />
  <table width="100%"  align="center">
    <tr>
      <td width="30%" align="right" valign="top" nowrap="nowrap"><div align="left"><span class="mandatori">*</span> Bil Mesyuarat</div></td>
      <td width="2%" align="center" valign="top" nowrap="nowrap">:</td>
      <td width="68%" align="left" valign="top" nowrap="nowrap">
        <input name="Mesyuarat_Bil" type="text" id="Mesyuarat_Bil" value="$!Mesyuarat_Bil" size="10" $RO_MaklumatMesyuarat />      </td>
    </tr>
    <tr> 
      <td align="right" valign="top" nowrap="nowrap"><div align="left"><span class="mandatori">*</span> No Fail / No Rujukan Dokumen</div></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap"><input name="Mesyuarat_NoFail" type="text" id="Mesyuarat_NoFail" onkeyup="this.value=this.value.toUpperCase();" value="$!Mesyuarat_NoFail" size="50" maxlength="255" $RO_NoFail /></td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><div align="left"><span class="mandatori">*</span> Tajuk Mesyuarat</div></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap">
        <input name="Mesyuarat_Tajuk" type="text" id="Mesyuarat_Tajuk" value="$!Mesyuarat_Tajuk" size="100" maxlength="255" onkeyup="this.value=this.value.toUpperCase();" $RO_MaklumatMesyuarat />      </td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><div align="left"><span class="mandatori">*</span> Jenis Mesyuarat</div></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap">
        <select id="Mesyuarat_Kategori" name="Mesyuarat_Kategori" style="width:auto" $RO_MaklumatMesyuarat>
#if ($Mesyuarat_Kategori == '2')
          <option value="1">AD-HOC</option>
          <option value="2" selected="selected">RUTIN</option>
#else
          <option value="1" selected="selected">AD-HOC</option>
          <option value="2">RUTIN</option>
#end      
        </select>      </td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><div align="left"><span class="mandatori">*</span> Tarikh</div></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap">
        <input name="Mesyuarat_Tarikh" type="text" id="Mesyuarat_Tarikh" value="$!Mesyuarat_Tarikh" size="15" maxlength="10" readonly="readonly" />
        <a href="javascript:displayDatePicker('Mesyuarat_Tarikh',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0" $RO_MaklumatMesyuarat /></a>      </td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><div align="left"><span class="mandatori">*</span> Dari</div></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap">
        <input name="Mesyuarat_Dari" type="text" id="Mesyuarat_Dari" value="$!Mesyuarat_Dari" size="10" $RO_MaklumatMesyuarat /> 
        (<em>format: hhmm, contoh 1500</em>)</td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><div align="left"><span class="mandatori">*</span> Hingga</div></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap">
        <input name="Mesyuarat_Hingga" type="text" id="Mesyuarat_Hingga" value="$!Mesyuarat_Hingga" size="10" $RO_MaklumatMesyuarat /> 
        (<em>format: hhmm, contoh 1500</em>)</td>
    </tr>
    #if($idNegeri == '16')
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><div align="left"><span class="mandatori">*</span> Urusetia / Seksyen</div></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap">$Mesyuarat_Seksyen </td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><div align="left"><span class="mandatori">*</span> Lokasi</div></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap">
        $Mesyuarat_Lokasi      </td>
    </tr>
    #else
        <tr>
      <td align="right" valign="top" nowrap="nowrap"><div align="left"><span class="mandatori">*</span> Urusetia / Unit</div></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap">$Mesyuarat_Unit </td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><div align="left"><span class="mandatori">*</span> Lokasi</div></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap">
        $Mesyuarat_Lokasi</td>
    </tr>
    #end
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><div align="left">Status</div></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap">
        $Mesyuarat_Status      </td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><div align="left">No Fail yang Diperlukan</div></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap">
        <textarea name="Mesyuarat_NoFailDiperlukan" cols="50" rows="2" id="Mesyuarat_NoFailDiperlukan" onkeyup="this.value=this.value.toUpperCase();" $RO_MaklumatMesyuarat>$!Mesyuarat_NoFailDiperlukan</textarea>      </td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><div align="left">Disediakan Oleh</div></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap">
        <input name="Mesyuarat_DisediakanOleh" type="text" id="Mesyuarat_DisediakanOleh" value="$!Mesyuarat_DisediakanOleh" size="50" maxlength="255" onkeyup="this.value=this.value.toUpperCase();" $RO_MaklumatMesyuarat />      </td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><div align="left">Disemak Oleh</div></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap">
        <input name="Mesyuarat_DisemakOleh" type="text" id="Mesyuarat_DisemakOleh" value="$!Mesyuarat_DisemakOleh" size="50" maxlength="255" onkeyup="this.value=this.value.toUpperCase();" $RO_MaklumatMesyuarat />      </td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><div align="left">Disahkan Oleh</div></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap">
        <input name="Mesyuarat_DisahkanOleh" type="text" id="Mesyuarat_DisahkanOleh" value="$!Mesyuarat_DisahkanOleh" size="50" maxlength="255" onkeyup="this.value=this.value.toUpperCase();" $RO_MaklumatMesyuarat />      </td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><div align="left">Catatan</div></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap">
        <textarea name="Mesyuarat_Catatan" cols="50" rows="5" id="Mesyuarat_Catatan" onkeyup="this.value=this.value.toUpperCase();" $RO_MaklumatMesyuarat>$!Mesyuarat_Catatan</textarea>      </td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><div align="left"></div></td>
      <td align="center" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="left" valign="top" nowrap="nowrap"><input type="button" id="cmdNext" name="cmdNext" value="Seterusnya " onclick="daftarAhli();" /></td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><div align="left"></div></td>
      <td align="center" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="left" valign="top" nowrap="nowrap"><span style="font-style:italic">Sila pastikan medan bertanda <span class="mandatori">*</span> diisi</span></td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><div align="left"></div></td>
      <td align="center" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="left" valign="top" nowrap="nowrap">&nbsp;</td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><div align="left"></div></td>
      <td align="center" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="left" valign="top" nowrap="nowrap"><input type="button" id="cmdDelete" name="cmdDelete" value="Hapus Mesyuarat" onclick="hapusMesyuarat();" /></td>
    </tr>
  </table>
</fieldset>
<br />
<input type="hidden" id="ID_MESYUARAT" name="ID_MESYUARAT" value="$ID_MESYUARAT" />
<input type="hidden" id="ID_TEMPLATE" name="ID_TEMPLATE" value="$ID_TEMPLATE" />
<input type="hidden" id="PrevPage" name="PrevPage" value="1" />
<input type="hidden" id="actionx" name="actionx" value="$actionx" />
<script type="text/javascript">
  function daftarAhli() {
	  if (document.${formName}.Mesyuarat_Bil.value == '') {
		  alert('Sila masukkan Bilangan Mesyuarat.');
		  document.${formName}.Mesyuarat_Bil.focus();
		  return;
	  }
	  if (document.${formName}.Mesyuarat_NoFail.value == '') {
		  alert('Sila masukkan No Fail / No Rujukan Dokumen.');
		  document.${formName}.Mesyuarat_NoFail.focus();
		  return;
	  }
	  if (document.${formName}.Mesyuarat_Tajuk.value == '') {
		  alert('Sila masukkan Tajuk Mesyuarat.');
		  document.${formName}.Mesyuarat_Tajuk.focus();
		  return;
	  }
	  if (document.${formName}.Mesyuarat_Tarikh.value == '') {
		  alert('Sila masukkan Tarikh Mesyuarat.');
		  document.${formName}.Mesyuarat_Tarikh.focus();
		  return;
	  }
	  if (document.${formName}.Mesyuarat_Dari.value == '') {
		  alert('Sila masukkan waktu mesyuarat bermula.');
		  document.${formName}.Mesyuarat_Dari.focus();
		  return;
	  }
	  if (document.${formName}.Mesyuarat_Hingga.value == '') {
		  alert('Sila masukkan waktu mesyuarat berakhir.');
		  document.${formName}.Mesyuarat_Hingga.focus();
		  return;
	  }
	  if(idNegeri == 16){
		   if (document.${formName}.SOC_SEKSYEN.value == '') {
			  alert('Sila masukkan Urusetia / Seksyen.');
			  document.${formName}.SOC_SEKSYEN.focus();
			  return;
		  }
		  
			  if (document.${formName}.SOC_LOKASI.value == '') {
			  alert('Sila masukkan Lokasi');
			  document.${formName}.SOC_LOKASI.focus();
			  return;
		  }
	  }
	  else{
		   if (document.${formName}.SOC_UNIT.value == '') {
			  alert('Sila masukkan Urusetia / Unit.');
			  document.${formName}.SOC_SEKSYEN.focus();
			  return;
		  }
		  
			  if (document.${formName}.SOC_LOKASI.value == '') {
			  alert('Sila masukkan Lokasi');
			  document.${formName}.SOC_LOKASI.focus();
			  return;
		  }
	  }
	 
	  document.${formName}.action = "?_portal_module=ekptg.view.pfd.FrmViewMesyuarat&actionx=daftarAhli";
	  document.${formName}.submit();
  }
  function hapusMesyuarat() {
	  if (!window.confirm("Adakah anda pasti?")) return;
	  document.${formName}.action = "?_portal_module=ekptg.view.pfd.FrmViewMesyuarat&actionx=hapusMesyuarat&ID_MESYUARAT=$ID_MESYUARAT";
	  document.${formName}.submit();
  }
function cetakMesyuarat(id_mesyuarat) {
	  var cetakmaklumbalas = "no";
	  for (var i=0; i < document.${formName}.optJenisReport.length; i++) {
		  if (document.${formName}.optJenisReport[i].checked) {
			  var TYPE = document.${formName}.optJenisReport[i].value;
			  
			  if(TYPE == "15")
			  {
				cetakmaklumbalas = "yes";  
			  }
		  }
	  }
	  
	  if(cetakmaklumbalas == "no")
	  {
	  var url = "../servlet/ekptg.report.pfd.PFDReport?reportType=PDF&TYPE=" + TYPE + "&ID_MESYUARAT="+id_mesyuarat+"&MINITMESYUARAT_ID=$ID_MESYUARAT";
	  var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	  if ((document.window != null) && (!hWnd.opener))
	  hWnd.opener = document.window;
	  if (hWnd.focus != null) hWnd.focus();
	  }
	  
	  if(cetakmaklumbalas == "yes")
	  {
	   var url = "../servlet/ekptg.report.pfd.CetakMaklumBalas?ID_MESYUARAT="+id_mesyuarat;  
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	  }
	  
  }
  
   function cetakMesyuaratNegeri(id_mesyuarat) {
	  var cetakmaklumbalas = "no";
	  for (var i=0; i < document.${formName}.optJenisReport.length; i++) {
		  if (document.${formName}.optJenisReport[i].checked) {
			  var TYPE = document.${formName}.optJenisReport[i].value;
			  
			  if(TYPE == "15")
			  {
				cetakmaklumbalas = "yes";  
			  }
		  }
	  }
	  
	  if(cetakmaklumbalas == "no")
	  {
	  var url = "../servlet/ekptg.report.pfd.PFDReportNegeri?reportType=PDF&TYPE=" + TYPE + "&ID_MESYUARAT="+id_mesyuarat+"&MINITMESYUARAT_ID=$ID_MESYUARAT";
	  var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	  if ((document.window != null) && (!hWnd.opener))
	  hWnd.opener = document.window;
	  if (hWnd.focus != null) hWnd.focus();
	  }
	  
	  if(cetakmaklumbalas == "yes")
	  {
	   var url = "../servlet/ekptg.report.pfd.CetakMaklumBalas?ID_MESYUARAT="+id_mesyuarat;  
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();
	  }
	  
  }
  
  function cetakMaklumBalas(id_mesyuarat)
{
    var url = "../servlet/ekptg.report.pfd.CetakMaklumBalas?ID_MESYUARAT="+id_mesyuarat;  
    var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
    if ((document.window != null) && (!hWnd.opener))
	hWnd.opener = document.window;
    if (hWnd.focus != null) hWnd.focus();

}
</script>