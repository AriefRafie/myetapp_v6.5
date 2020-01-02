<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td width="1%"><span class="style1">*</span></td>
    <td width="28%">Nama Dokumen</td>
    <td width="1%">:</td>
    <td width="70%"><input name="namaDokumen" type="text" id="namaDokumen" onBlur="this.value=this.value.toUpperCase()" size="35" maxlength="200"/>    </td>
  </tr>
  <tr>
    <td width="1%"><span class="style1">*</span></td>
    <td>Muatnaik Dokumen</td>
    <td>:</td>
    <td><input class="texts" type="file" id="dokumen" name="dokumen" style="width:100%" onfocus="$('err_dokumen').innerHTML = '';" onchange="simpanDokumen()">
      <div id="dokumenMuatnaik" style="display:none"> <img src="../img/indicator.gif"> <span style="font-weight:bold;color:#008000;">Sedang Muatnaik...</span> </div>
      <div id="err_dokumen" style="color:#CC0000;font-weight:bold;border:2px #000"></div></td>
  </tr>
  <tr>
    <td width="1%">&nbsp;</td>
    <td width="28%">&nbsp;</td>
    <td width="1%">&nbsp;</td>
    <td width="70%"><span class="style4"><i><font color="#ff0000">Perhatian</font> : </i><span class="style5">Saiz muat naik imej adalah tidak melebihi 2MB. Jika muat naik anda tidak berjaya sila cuba dengan saiz imej yang lebih kecil.</span></span></td>
  </tr>
</table>
<iframe id="upload_dokumen" name="upload_dokumen" width="0px" height="0px" style="visibility:hidden"></iframe>
