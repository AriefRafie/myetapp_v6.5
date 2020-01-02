<style type="text/css">
<!--
  .link {color: #0000FF}
  .mandatori {color:#FF0000}
  .style1 {color: #000000; }
-->
</style>
#if ($action2 == 'sendAPB')
	#set ($READONLY_APB = 'readonly="readonly" class="disabled"')
	#set ($DISABLE_APB = 'disabled="disabled" class="disabled"')
#else
	#set ($READONLY_APB = '')
	#set ($DISABLE_APB = '')
#end
#if ($action2 == 'sendAPB')
<fieldset>
  <div class="success">Maklumat Ulasan Teknikal telah berjaya dihantar ke JKPTG.</div>
</fieldset>
<br />
#elseif ($action2 == 'saveAPB')
<fieldset>
  <div class="success">Maklumat Ulasan Teknikal telah berjaya disimpan.</div>
</fieldset>
<br />
#end
<table width="100%" border="0" align="center">
  <tr>
    <td width="50%" valign="top">
      <fieldset>
        <legend><strong>MAKLUMAT PERMOHONAN</strong></legend>
        <table width="100%" border="0" align="center">
          <tr>
            <td width="25%" align="right" valign="top"><strong>NO FAIL</strong></td>
            <td width="1%" align="center" valign="top">:</td>
            <td align="left" valign="top" class="link">$!MP_NOFAIL</td>
          </tr>
          <tr>
            <td width="25%" align="right" valign="top"><strong>URUSAN</strong></td>
            <td width="1%" align="center" valign="top">:</td>
            <td align="left" valign="top" class="link">$!MP_URUSAN</td>
          </tr>
          <tr>
            <td width="25%" align="right" valign="top"><strong>TARIKH TERIMA</strong></td>
            <td width="1%" align="center" valign="top">:</td>
            <td align="left" valign="top" class="link">$!MP_TARIKHTERIMA</td>
          </tr>
          <tr>
            <td width="25%" align="right" valign="top"><strong>TARIKH SURAT</strong></td>
            <td width="1%" align="center" valign="top">:</td>
            <td align="left" valign="top" class="link">$!MP_TARIKHSURAT</td>
          </tr>
          <tr>
            <td width="25%" align="right" valign="top"><strong>PERKARA</strong></td>
            <td width="1%" align="center" valign="top">:</td>
            <td align="left" valign="top" class="link">$!MP_PERKARA</td>
          </tr>
        </table>
      </fieldset>
    </td>
    <td width="50%" valign="top">
      <fieldset>
      <legend><strong>MAKLUMAT PEMOHON</strong></legend>
        <table width="100%" border="0" align="center">
          <tr>
            <td width="25%" align="right" valign="top"><strong>NAMA PEMOHON</strong></td>
            <td width="1%" align="center" valign="top">:</td>
            <td align="left" valign="top" class="link">$!MP_NAMAPEMOHON</td>
          </tr>
          <tr>
            <td width="25%" align="right" valign="top"><strong>ALAMAT</strong></td>
            <td width="1%" align="center" valign="top">:</td>
            <td align="left" valign="top" class="link">$!MP_ALAMAT</td>
          </tr>
          <tr>
            <td width="25%" align="right" valign="top"><strong>POSKOD</strong></td>
            <td width="1%" align="center" valign="top">:</td>
            <td align="left" valign="top" class="link">$!MP_POSKOD</td>
          </tr>
          <tr>
            <td width="25%" align="right" valign="top"><strong>NEGERI</strong></td>
            <td width="1%" align="center" valign="top">:</td>
            <td align="left" valign="top" class="link">$!MP_NEGERI</td>
          </tr>
          <tr>
            <td align="right" valign="top"><strong>NO. TELEFON</strong></td>
            <td align="center" valign="top">:</td>
            <td align="left" valign="top" class="link">$!MP_NOTELEFON</td>
          </tr>
          <tr>
            <td width="25%" align="right" valign="top"><strong>NO. FAKS</strong></td>
            <td width="1%" align="center" valign="top">:</td>
            <td align="left" valign="top" class="link">$!MP_NOFAKS</td>
          </tr>
        </table>
      </fieldset>
    </td>
  </tr>
</table>
<br />
<fieldset>
  <legend><strong>MAKLUMAT KAWASAN</strong></legend>
  <table width="100%" border="0" align="center">
    <tr>
      <td align="right" valign="top" width="30%"><strong>LUAR PERAIRAN NEGERI</strong></td>
      <td align="center" valign="top" width="1%">:</td>
      <td align="left" valign="top" class="link">$!KAW_LUARPERAIRANNEGERI</td>
    </tr>
    <tr>
      <td align="right" valign="top"><strong>NEGERI</strong></td>
      <td align="center" valign="top">:</td>
      <td align="left" valign="top" class="link">$!KAW_NEGERI</td>
    </tr>
    <tr>
      <td align="right" valign="top"><strong>LOKASI</strong></td>
      <td align="center" valign="top">:</td>
      <td align="left" valign="top" class="link">$!KAW_LOKASI</td>
    </tr>
    <tr>
      <td align="right" valign="top"><strong>LUAS DIPOHON</strong></td>
      <td align="center" valign="top">:</td>
      <td align="left" valign="top" class="link">$!KAW_LUASDIPOHON</td>
    </tr>
  </table>  
</fieldset>
<br />
<fieldset>
  <legend><strong>LAIN-LAIN</strong></legend>
  <table width="100%" border="0" align="center">
    <tr>
      <td align="right" valign="top" width="30%"><strong>MAKLUMAT TAMBAHAN</strong></td>
      <td align="center" valign="top" width="1%">:</td>
      <td align="left" valign="top" class="link">$!LAINLAIN_MAKLUMATTAMBAHAN</td>
    </tr>
  </table>  
</fieldset>
<br />
<fieldset>
  <legend><strong>SENARAI KOORDINAT</strong></legend>
  <table width="100%" border="0" align="center">
    <tr class="table_header">
      <td width="10%" scope="row" align="center"><strong>NO</strong></td>
      <td width="40%"><strong>LABEL KOORDINAT</strong></td>
      <td width="25%" align="center"><strong>LATITUD (U)</strong></td>
      <td width="25%" align="center"><strong>LONGITUD (T)</strong></td>
    </tr>
#set ($list = '')
#foreach ($list in $ListKoordinat)
	#if ($list.NO == '')
    	#set ($row = 'row1')
    #elseif ($list.NO % 2 != 0)
    	#set ($row = 'row1')
    #else
    	#set ($row = 'row2')
    #end
    <tr>
    #if ($list.NO != '') 
      <td class="$row" valign="top" align="center">$list.NO</td>
      <td class="$row" valign="top">$list.LABEL_TITIK</td>
      <td class="$row" valign="top" align="center">$list.DARJAH_U&deg;$list.MINIT_U&prime;$list.SAAT_U&Prime;</td>
      <td class="$row" valign="top" align="center">$list.DARJAH_T&deg;$list.MINIT_T&prime;$list.SAAT_T&Prime;</td>
    #else
      <td colspan="4" class="$row" style="text-align:center">Tiada Rekod</td>
    #end
    </tr>
#end
  </table>
</fieldset>
<br />
<!--    
<fieldset>
  <legend><strong>MAKLUMAT FAIL LAMPIRAN DARIPADA JKPTG</strong></legend>
  <table width="100%" cellspacing="0" cellpadding="2">
    <tr>
      <td width="25%" align="right" valign="top"><strong>IMEJ (jika ada)</strong></td>
      <td width="1%" align="center" valign="top">:</td>
      <td align="left" valign="top" class="style1" colspan="2"><input name="KAW_IMEJ" type="file" id="KAW_IMEJ" style="font-family:Verdana, Geneva, sans-serif" size="50" $DISABLE_JKPTG /></td>
    </tr>
    <tr>
      <td width="25%" align="right" valign="top"><strong>KETERANGAN FAIL</strong></td>
      <td width="1%" align="center" valign="top">:</td>
      <td align="left" valign="top" class="style1" colspan="2"><input name="KAW_KETERANGAN_IMEJ" type="text" id="KAW_KETERANGAN_IMEJ" style="font-family:Verdana, Geneva, sans-serif" size="50" maxlength="255" /></td>
    </tr>
#if ($action2 == 'editAPB')
    <tr>
      <td width="25%" align="right" valign="top">&nbsp;</td>
      <td width="1%" align="center" valign="top">&nbsp;</td>
      <td valign="top"><input type="button" name="addFileJKPTG" value="Tambah Fail" onclick="addFileJKPTG();" /></td>
    </tr>
#end
    <tr>
      <td align="center" valign="top" colspan="3">&nbsp;</td>
    </tr>
  </table>
  <table width="100%" cellspacing="0" cellpadding="2">
    <tr class="table_header">
      <td width="5%" scope="row" align="center"><strong>NO</strong></td>
      <td width="25%"><strong>NAMA FAIL</strong></td>
      <td width="60%"><strong>KETERANGAN</strong></td>
#if ($action2 == 'editAPB')
      <td width="10%" style="text-align:center"><input type="button" id="cmdDeleteFile" name="cmdDeleteFile" value="PADAM FAIL" onclick="deleteAPBFile();" /></td>
#else
      <td width="10%" style="text-align:center">&nbsp;</td>
#end
    </tr>
#set ($list = '')
#foreach ($list in $ListUpload)
	#if ($list.No == '') 
    	#set ($row = 'row1')
    #elseif ($list.No % 2 != 0)
    	#set ($row = 'row1')
    #else 
    	#set ($row = 'row2')
    #end
    <tr>
    #if ($list.No != '') 
      <td class="$row" valign="top" align="center">$list.No</td>
      <td class="$row" valign="top"><a href="javascript:viewAPBFile('$list.ID_UPLOAD')" style="color:#0000FF">$list.NamaFail</a></td>
      <td class="$row" valign="top">$list.Keterangan</td>
      <td class="$row" valign="top" style="text-align:center"><input type="checkbox" name="FILE_DELETE" id="FILE_DELETE" value="$list.ID_UPLOAD" $DISABLE_JKPTG /></td>
    #else
      <td colspan="4" class="$row" style="text-align:center">Tiada Rekod</td>
    #end
    </tr>
#end
    <tr>
      <td colspan="4">&nbsp;</td>
    </tr>
  </table>
</fieldset>
<br />
-->
<fieldset>
  <legend><strong>MAKLUMBALAS JABATAN/AGENSI</strong></legend>
  <table width="100%" border="0" align="center">
    <tr>
      <td width="25%" align="right" valign="top"><strong>ULASAN TEKNIKAL #if ($action2 != 'sendAPB')<span class="mandatori">*</span>#end</strong></td>
      <td width="1%" align="center" valign="top">:</td>
      <td align="left" valign="top" class="style1"><textarea name="MB_ULASAN" id="MB_ULASAN" cols="50" rows="5" $READONLY_APB>$!MB_ULASAN</textarea></td>
    </tr>
    <tr>
      <td align="right" valign="top"><strong>NAMA PEMOHON BERTINDIH 1</strong></td>
      <td align="center" valign="top">:</td>
      <td align="left" valign="top" class="style1"><input name="MB_NAMAPEMOHONTINDIH1" type="text" id="MB_NAMAPEMOHONTINDIH1" style="text-transform:uppercase" value="$!MB_NAMAPEMOHONTINDIH1" size="50" maxlength="255" $READONLY_APB /></td>
    </tr>
    <tr>
      <td align="right" valign="top"><strong>NAMA PEMOHON BERTINDIH 2</strong></td>
      <td align="center" valign="top">:</td>
      <td align="left" valign="top" class="style1"><input name="MB_NAMAPEMOHONTINDIH2" type="text" id="MB_NAMAPEMOHONTINDIH2" style="text-transform:uppercase" value="$!MB_NAMAPEMOHONTINDIH2" size="50" maxlength="255" $READONLY_APB /></td>
    </tr>
    <tr>
      <td align="right" valign="top"><strong>NAMA PEMOHON BERTINDIH 3</strong></td>
      <td align="center" valign="top">:</td>
      <td align="left" valign="top" class="style1"><input name="MB_NAMAPEMOHONTINDIH3" type="text" id="MB_NAMAPEMOHONTINDIH3" style="text-transform:uppercase" value="$!MB_NAMAPEMOHONTINDIH3" size="50" maxlength="255" $READONLY_APB /></td>
    </tr>
    <tr>
      <td align="right" valign="top"><strong>TARIKH MOHON YANG BERTINDIH</strong></td>
      <td align="center" valign="top">:</td>
      <td align="left" valign="top" class="style1"><input name="MB_TARIKHMOHONTINDIH" type="text" id="MB_TARIKHMOHONTINDIH" value="$!MB_TARIKHMOHONTINDIH" size="15" maxlength="10" style="text-transform:uppercase" $READONLY_APB />&nbsp;<a href="javascript:displayDatePicker('MB_TARIKHMOHONTINDIH',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0" /></a></td>
    </tr>
    <!-- tr>
      <td align="right" valign="top"><strong>IMEJ KAWASAN (jika ada)</strong></td>
      <td align="center" valign="top">:</td>
      <td align="left" valign="top" class="style1">
        <input name="MB_IMEJ" type="file" id="MB_IMEJ" style="font-family:Verdana, Geneva, sans-serif" size="50" $DISABLE_APB />
#if ($action2 != 'sendAPB')
        &nbsp;
        <input type="button" id="MB_UPLOAD" name="MB_UPLOAD" value="Muat Naik" onclick="doUpload();" />
#end
      </td>
    </tr -->
#if ($hasImage == 'true')
    <tr>
      <td align="right" valign="top">&nbsp;</td>
      <td align="center" valign="top">&nbsp;</td>
      <td align="left" valign="top" class="style1">
        <a class="link" onclick="displayImage();" style="cursor:pointer">Papar Imej ($!imageName)</a>
	#if ($hasImage == 'true')
        &nbsp;
        <input type="button" id="MB_DELETEIMAGE" name="MB_DELETEIMAGE" value="Padam Imej" onclick="deleteImage();" />
	#end
      </td>
    </tr>
#end
    <tr>
      <td align="right" valign="top"><strong>KO-ORDINAT KAWASAN (jika ada)</strong></td>
      <td align="center" valign="top">:</td>
      <td align="left" valign="top" class="style1"><input name="MB_KOORDINAT" type="text" id="MB_KOORDINAT" style="text-transform:uppercase" value="$!MB_KOORDINAT" size="50" maxlength="255" $READONLY_APB /></td>
    </tr>
    <tr>
      <td align="right" valign="top"><strong>CATATAN</strong></td>
      <td align="center" valign="top">:</td>
      <td align="left" valign="top" class="style1"><textarea name="MB_CATATAN" id="MB_CATATAN" cols="50" rows="5" $READONLY_APB>$!MB_CATATAN</textarea></td>
    </tr>
    <tr>
      <td align="right" valign="top"><strong>NAMA PENGULAS</strong></td>
      <td align="center" valign="top">:</td>
      <td align="left" valign="top" class="style1"><input name="MB_NAMAPENGULAS" type="text" id="MB_NAMAPENGULAS" style="text-transform:uppercase" value="$!MB_NAMAPENGULAS" size="50" maxlength="255" $READONLY_APB /></td>
    </tr>
    <tr>
      <td align="right" valign="top"><strong>NO TELEFON PENGULAS</strong></td>
      <td align="center" valign="top">:</td>
      <td align="left" valign="top" class="style1"><input name="MB_NOTELPENGULAS" type="text" id="MB_NOTELPENGULAS" style="text-transform:uppercase" value="$!MB_NOTELPENGULAS" size="30" maxlength="255" $READONLY_APB /></td>
    </tr>
#if ($action2 != 'sendAPB')
    <tr>
      <td colspan="3" valign="top" style="font-style:italic"><span class="mandatori">Perhatian</span>: Sila pastikan label bertanda <span class="mandatori">*</span> diisi sebelum fail dihantar semula ke JKPTG.</td>
    </tr>
#end
    <tr>
      <td colspan="3" valign="top">&nbsp;</td>
    </tr>
  </table>
</fieldset>
<br />
<div style="text-align:center">
  <input id="cmdKembali" name="cmdKembali" type="button" value="Kembali" onclick="backAPB();" />&nbsp;
#if (1 == 0)
  <input id="cmdCetakMaklumat" name="cmdCetakMaklumat" type="button" value="Cetak Maklumat" onclick="printAPB('3', '$ID_HA');" />&nbsp;
#end
#if ($action2 != 'sendAPB')
  <input id="cmdSimpan" name="cmdSimpan" type="button" value="Simpan" onclick="saveAPB();" />&nbsp;
  <input id="cmdHantarJKPTG" name="cmdHantarJKPTG" type="button" value="Hantar ke JKPTG" onclick="sendAPB();" />&nbsp;
#end
</div>
<input type="hidden" id="ID_ULASANTEKNIKAL" name="ID_ULASANTEKNIKAL" value="$ID_ULASANTEKNIKAL" />
<input type="hidden" id="action2" name="action2" value="$action2" />
<input type="hidden" id="action" name="action" value="$action" />
<input type="hidden" id="mode" name="mode" />
<script type="text/javascript">
  function sendAPB() {
      if (document.getElementById('MB_ULASAN').value == '') {
          alert('Sila isikan ulasan teknikal permohonan.');
          document.getElementById('MB_ULASAN').focus();
          return;
      }
      if (!window.confirm("Adakah anda pasti?")) return;
      document.${formName}.action = "?_portal_module=ekptg.view.integrasi.FrmViewAPB&action2=sendAPB";
      document.${formName}.method = "POST";
      document.${formName}.submit();
  }
  function saveAPB() {
      if (!window.confirm("Adakah anda pasti?")) return;
      document.${formName}.action = "?_portal_module=ekptg.view.integrasi.FrmViewAPB&action2=saveAPB";
      document.${formName}.method = "POST";
      document.${formName}.submit();
  }
  function backAPB() {
      document.${formName}.action = "$EkptgUtil.getTabID("My Info",$portal_role)?_portal_module=ekptg.view.integrasi.FrmViewMyInfoAPB&action2=";
      document.${formName}.submit();
  }
  function printAPB(RT, ID_HA) {
	  var url = "../servlet/ekptg.report.integrasi.ReportJPPH?reportType=PDF&rt=" + RT + "&ID_HA=" + ID_HA;
	  var hWnd = window.open(url,'Cetak','width=800,height=500, resizable=yes,scrollbars=yes');
	  if ((document.window != null) && (!hWnd.opener))
	  hWnd.opener = document.window;
	  if (hWnd.focus != null) hWnd.focus();
  }
  function editAPB() {
      document.${formName}.action = "?_portal_module=ekptg.view.integrasi.FrmViewAPB&action2=editAPB";
      document.${formName}.method = "POST";
      document.${formName}.submit();
  }
  function addFileJKPTG() {
      document.${formName}.action = "?_portal_module=ekptg.view.integrasi.FrmViewAPB&action2=editAPB";
      document.${formName}.method = "POST";
      document.${formName}.submit();
  }
  function doUpload() {
      document.${formName}.action = "?_portal_module=ekptg.view.integrasi.FrmViewAPB&action2=uploadFileAPB&ID_ULASANTEKNIKAL=$!ID_ULASANTEKNIKAL";
      document.${formName}.enctype = "multipart/form-data";
      document.${formName}.encoding = "multipart/form-data";
      document.${formName}.method = "POST";
      document.${formName}.submit();
  }
  function displayImage() {	
      var url = "../servlet/ekptg.view.integrasi.DisplayBlob?ptype=APB&ID_ULASANTEKNIKAL=$!ID_ULASANTEKNIKAL";
	  var hWnd = window.open(url,'DisplayFile','width=800,height=500, resizable=yes,scrollbars=yes');
	  if ((document.window != null) && (!hWnd.opener))
	  hWnd.opener = document.window;
	  if (hWnd.focus != null) hWnd.focus();
  }
  function deleteImage() {
      document.${formName}.action = "?_portal_module=ekptg.view.integrasi.FrmViewAPB&action2=deleteImage";
      document.${formName}.method = "POST";
      document.${formName}.submit();
  }
</script>