<style type="text/css">
<!--
  .link {color: #0000FF}
  .mandatori {color:#FF0000}
-->
</style>
#if ($saveBorangLampiranA1 == 'true')
<fieldset>
  <div class="success">Data telah berjaya disimpan.</div>
</fieldset>
<br />
#end
<fieldset>
  <legend><strong>MAKLUMAT PERMOHONAN</strong></legend>
  <br />
  <table width="70%" border="0" align="center">
    <tr>
      <td width="25%" align="right" valign="top" nowrap="nowrap"><strong>NO FAIL</strong></td>
      <td width="1%" align="center" valign="top" nowrap="nowrap">:</td>
      <td width="74%" align="left" valign="top" nowrap="nowrap" class="link">$MP_NOFAIL</td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><strong>NO PERMOHONAN</strong></td>
      <td align="center" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="left" valign="top" nowrap="nowrap" class="link">$MP_NOPERMOHONAN</td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><strong>NO LOT</strong></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap" class="link">$MP_NOLOT</td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><strong>NEGERI</strong></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap" class="link">$MP_NEGERI</td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><strong>DAERAH / JAJAHAN</strong></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap" class="link">$MP_DAERAH</td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><strong>MUKIM / PEKAN / BANDAR</strong></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap" class="link">$MP_MUKIM</td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap"><strong>NO SYIT UKUR</strong></td>
      <td align="center" valign="top" nowrap="nowrap">:</td>
      <td align="left" valign="top" nowrap="nowrap" class="link">$MP_NOSYITUKUR</td>
    </tr>
    <tr>
      <td align="right" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="center" valign="top" nowrap="nowrap">&nbsp;</td>
      <td align="left" valign="top" nowrap="nowrap" class="link">&nbsp;</td>
    </tr>
  </table>
</fieldset>
<br />
<fieldset>
  <legend><strong>Cetak Surat Iringan ke JKPTG</strong></legend>
  <table width="70%" border="0" align="center">
    <tr>
      <td width="25%" align="right" valign="top" nowrap="nowrap" style="font-weight:bold">No Rujukan Tuan</td>
      <td width="1%" valign="top" nowrap="nowrap" align="center">:</td>
      <td width="74%" valign="top" nowrap="nowrap"><input name="RujukanTuan" type="text" id="RujukanTuan" onkeyup="this.value=this.value.toUpperCase();" value="" size="50" maxlength="50" /></td>
    </tr>
    <tr>
      <td valign="top" nowrap="nowrap" style="font-weight:bold" align="right">No Rujukan Kami</td>
      <td valign="top" nowrap="nowrap" align="center">:</td>
      <td valign="top" nowrap="nowrap"><input name="RujukanKami" type="text" id="RujukanKami" onkeyup="this.value=this.value.toUpperCase();" value="" size="50" maxlength="50" /></td>
    </tr>
    <tr>
      <td valign="top" nowrap="nowrap" style="font-weight:bold" align="right">Tarikh Hantar (Masehi)</td>
      <td valign="top" nowrap="nowrap" align="center">:</td>
      <td valign="top" nowrap="nowrap"><input name="TarikhHantarMasehi" type="text" id="TarikhHantarMasehi" onkeyup="this.value=this.value.toUpperCase();" value="" size="50" maxlength="50" /></td>
    </tr>
    <tr>
      <td valign="top" nowrap="nowrap" style="font-weight:bold" align="right">Tarikh Hantar (Hijrah)</td>
      <td valign="top" nowrap="nowrap" align="center">:</td>
      <td valign="top" nowrap="nowrap"><input name="TarikhHantarHijrah" type="text" id="TarikhHantarHijrah" onkeyup="this.value=this.value.toUpperCase();" value="" size="50" maxlength="50" /></td>
    </tr>
    <tr>
      <td valign="top" nowrap="nowrap" style="font-weight:bold" align="right">Nama Penerima</td>
      <td valign="top" nowrap="nowrap" align="center">:</td>
      <td valign="top" nowrap="nowrap"><input name="NamaPenerima" type="text" id="NamaPenerima" onkeyup="this.value=this.value.toUpperCase();" value="" size="50" maxlength="255" /></td>
    </tr>
    <tr>
      <td valign="top" nowrap="nowrap" style="font-weight:bold" align="right">Alamat 1 Penerima</td>
      <td valign="top" nowrap="nowrap" align="center">:</td>
      <td valign="top" nowrap="nowrap"><input name="AlamatPenerima1" type="text" id="AlamatPenerima1" onkeyup="this.value=this.value.toUpperCase();" value="" size="50" maxlength="255" /></td>
    </tr>
    <tr>
      <td valign="top" nowrap="nowrap" style="font-weight:bold" align="right">Alamat 2 Penerima</td>
      <td valign="top" nowrap="nowrap" align="center">:</td>
      <td valign="top" nowrap="nowrap"><input name="AlamatPenerima2" type="text" id="AlamatPenerima2" onkeyup="this.value=this.value.toUpperCase();" value="" size="50" maxlength="255" /></td>
    </tr>
    <tr>
      <td valign="top" nowrap="nowrap" style="font-weight:bold" align="right">Alamat 3 Penerima</td>
      <td valign="top" nowrap="nowrap" align="center">:</td>
      <td valign="top" nowrap="nowrap"><input name="AlamatPenerima3" type="text" id="AlamatPenerima3" onkeyup="this.value=this.value.toUpperCase();" value="" size="50" maxlength="50" /></td>
    </tr>
    <tr>
      <td valign="top" nowrap="nowrap" style="font-weight:bold" align="right">Alamat 4 Penerima</td>
      <td valign="top" nowrap="nowrap" align="center">:</td>
      <td valign="top" nowrap="nowrap"><input name="AlamatPenerima4" type="text" id="AlamatPenerima4" onkeyup="this.value=this.value.toUpperCase();" value="" size="50" maxlength="255" /></td>
    </tr>
    <tr>
      <td valign="top" nowrap="nowrap" style="font-weight:bold" align="right">Tajuk Surat</td>
      <td valign="top" nowrap="nowrap" align="center">:</td>
      <td valign="top" nowrap="nowrap"><textarea name="TajukSurat" cols="50" rows="3" id="TajukSurat" onkeyup="this.value=this.value.toUpperCase();"></textarea></td>
    </tr>
    <tr>
      <td valign="top" nowrap="nowrap" style="font-weight:bold" align="right">Tarikh Surat</td>
      <td valign="top" nowrap="nowrap" align="center">:</td>
      <td valign="top" nowrap="nowrap"><input name="TarikhSurat" type="text" id="TarikhSurat" onkeyup="this.value=this.value.toUpperCase();" value="" size="50" maxlength="50" /></td>
    </tr>
    <tr>
      <td valign="top" nowrap="nowrap" style="font-weight:bold" align="right">Tarikh Terima</td>
      <td valign="top" nowrap="nowrap" align="center">:</td>
      <td valign="top" nowrap="nowrap"><input name="TarikhTerima" type="text" id="TarikhTerima" onkeyup="this.value=this.value.toUpperCase();" value="" size="50" maxlength="50" /></td>
    </tr>
    <tr>
      <td valign="top" nowrap="nowrap" style="font-weight:bold" align="right">Nama Pengirim</td>
      <td valign="top" nowrap="nowrap" align="center">:</td>
      <td valign="top" nowrap="nowrap"><input name="NamaPengirim" type="text" id="NamaPengirim" onkeyup="this.value=this.value.toUpperCase();" value="" size="50" maxlength="255" /></td>
    </tr>
    <tr>
      <td valign="top" nowrap="nowrap" style="font-weight:bold" align="right">Jawatan Pengirim</td>
      <td valign="top" nowrap="nowrap" align="center">:</td>
      <td valign="top" nowrap="nowrap"><input name="JawatanPengirim" type="text" id="JawatanPengirim" onkeyup="this.value=this.value.toUpperCase();" value="" size="50" maxlength="255" /></td>
    </tr>
    <tr>
      <td valign="top" nowrap="nowrap" style="font-weight:bold" align="right">Jabatan Pengirim</td>
      <td valign="top" nowrap="nowrap" align="center">:</td>
      <td valign="top" nowrap="nowrap"><input name="JabatanPengirim" type="text" id="JabatanPengirim" onkeyup="this.value=this.value.toUpperCase();" value="" size="50" maxlength="255" /></td>
    </tr>
    <tr>
      <td colspan="3">&nbsp;</td>
    </tr>
    <tr>
      <td colspan="3" align="center" valign="top"><input type="button" name="cmdCetakSurat" value="Cetak Surat" onclick="printSuratIringan();" />
      <input type="button" name="cmdKembali" value="Kembali" onclick="backBorangLampiranA1();" id="cmdKembali" /></td>
    </tr>
  </table>  
</fieldset>
<br />
<input type="hidden" id="ID_PERMOHONAN" name="ID_PERMOHONAN" value="$ID_PERMOHONAN" />
<input type="hidden" id="ID_FAIL" name="ID_FAIL" value="$ID_FAIL" />
<input type="hidden" id="ID_NEGERI" name="ID_NEGERI" value="$ID_NEGERI" />
<input type="hidden" id="ID_DAERAH" name="ID_DAERAH" value="$ID_DAERAH" />
<input type="hidden" id="ID_MUKIM" name="ID_MUKIM" value="$ID_MUKIM" />
<input type="hidden" id="MP_NOLOT" name="MP_NOLOT" value="$MP_NOLOT" />
<input type="hidden" id="MP_NOSYITUKUR	" name="MP_NOSYITUKUR" value="$MP_NOSYITUKUR" />
<input type="hidden" id="action2" name="action2" value="$action2" />
<script type="text/javascript">
  function printSuratIringan() {
	  //var url = "../servlet/ekptg.report.integrasi.BorangLampiranA1?reportType=PDF&rType=1";
	  var url = "../servlet/ekptg.report.integrasi.BorangLampiranA1?reportType=PDF&rType=1&NamaPenerima=" + document.${formName}.NamaPenerima.value + "&AlamatPenerima1=" + document.${formName}.AlamatPenerima1.value + "&AlamatPenerima2=" + document.${formName}.AlamatPenerima2.value + "&AlamatPenerima3=" + document.${formName}.AlamatPenerima3.value + "&AlamatPenerima4=" + document.${formName}.AlamatPenerima4.value + "&TajukSurat=" + document.${formName}.TajukSurat.value + "&RujukanTuan=" + document.${formName}.RujukanTuan.value + "&RujukanKami=" + document.${formName}.RujukanKami.value + "&TarikhHantarMasehi=" + document.${formName}.TarikhHantarMasehi.value + "&TarikhHantarHijrah=" + document.${formName}.TarikhHantarHijrah.value + "&TarikhSurat=" + document.${formName}.TarikhSurat.value + "&TarikhTerima=" + document.${formName}.TarikhTerima.value + "&NamaPengirim=" + document.${formName}.NamaPengirim.value + "&JawatanPengirim=" + document.${formName}.JawatanPengirim.value + "&JabatanPengirim=" + document.${formName}.JabatanPengirim.value;
	  var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	  if ((document.window != null) && (!hWnd.opener))
	  hWnd.opener = document.window;
	  if (hWnd.focus != null) hWnd.focus();
  }
  function backBorangLampiranA1() {
      document.${formName}.action = "?_portal_module=ekptg.view.integrasi.FrmViewBorangLampiranA1&action2=";
	  document.${formName}.method = "POST";
	  document.${formName}.submit();
  }
</script>