<fieldset>
<legend>MAKLUMAT TANAH</legend>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #foreach ($beanMaklumatTanah in $BeanMaklumatTanah)
  <input type="hidden" name="noLotTanah" id="noLotTanah" value="$beanMaklumatTanah.lot" />
  <input type="hidden" name="noMilikTanah" id="noMilikTanah" value="$beanMaklumatTanah.hakmilik" />
  <input type="hidden" name="noWartaTanah" id="noWartaTanah" value="$beanMaklumatTanah.noWarta" />
  <input type="hidden" name="namaMukimTanah" id="namaMukimTanah" value="$beanMaklumatTanah.mukim" />
  <input type="hidden" name="namaDerahTanah" id="namaDerahTanah" value="$beanMaklumatTanah.daerah" />
  <input type="hidden" name="namaNegeriTanah" id="namaNegeriTanah" value="$beanMaklumatTanah.negeri" />
  <input type="hidden" name="namatujuan" id="namatujuan" value="$beanMaklumatTanah.tujuan" />
  <input type="hidden" name="status" id="status" value="$beanMaklumatTanah.statusRizab" />
  <input name="txtNama" type="hidden" id="txtNama" value="$beanMaklumatTanah.nama" />
  <tr>
    <td>&nbsp;</td>
    <td valign="top">Negeri</td>
    <td>:</td>
    <td>$selectNegeriTanah</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td valign="top">Kementerian</td>
    <td>:</td>
    <td>$selectKementerian</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td valign="top">Agensi</td>
    <td>:</td>
    <td>$selectAgensi</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td valign="top">Maklumat Lot</td>
    <td valign="top">:</td>
    <td valign="top">
    <textarea name="txtMaklumatLot" id="txtMaklumatLot" rows="5"
    cols="50" $readonly class="$inputTextClass"
    onblur="this.value=this.value.toUpperCase();">$beanMaklumatTanah.maklumatLot</textarea>
      <input type="button" name="cmdDaftarBaru2" id="cmdDaftarBaru2" $readonly class="$inputTextClass" value="Jana Maklumat Lot" onclick="janaMaklumatLot()" /></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>Jenis Luas</td>
    <td>:</td>
    <td>#parse("app/php2/unit_luas.jsp") </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>Luas</td>
    <td>:</td>
    <td><input name="txtLuas" type="text" class="$inputTextClass" id="txtLuas" value="$beanMaklumatTanah.luas" maxlength="50" $readonly/>
    </td>
  </tr>
  <tr>
    <td width="1%" valign="top">&nbsp;</td>
    <td valign="top">Catatan</td>
    <td valign="top">:</td>
    <td><textarea name="txtCatatanTanah" id="txtCatatanTanah" rows="5" cols="50" $readonly class="$inputTextClass" onblur="this.value=this.value.toUpperCase();"  >$!beanMaklumatTanah.catatanTanah</textarea>    </td>
  </tr>
  #end
  #if ($mode == 'update')
  <tr>
    <td colspan="4" valign="bottom"><i><font color="#ff0000">Perhatian</font> : Pastikan label bertanda <font color="#ff0000">*</font> diisi.</i></td>
  </tr>
  #end
  <tr>
    <td width="1%">&nbsp;</td>
    <td width="28%">&nbsp;</td>
    <td width="1%">&nbsp;</td>
    <td width="70%"> #if ($mode == 'update')
      <input type="button" name="cmdSimpanKemaskini" id="cmdSimpanKemaskini" value="Simpan" onClick="doSimpanKemaskiniTanah()"/>
      <input type="button" name="cmdBatalKemaskini" id="cmdBatalKemaskini" value="Batal" onClick="doBatalKemaskini()"/>
      #end
      #if ($mode == 'view')
      <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onClick="doKemaskiniTanah()"/>
      #end </td>
  </tr>
</table>
</fieldset>

<script>
function janaMaklumatLot() {
	var strTajuk = " ";
	var milikOrRizab = " ";
	var str1  = document.${formName}.noLotTanah.value;
	var str2  = document.${formName}.noMilikTanah.value;
	var str3  = document.${formName}.namaMukimTanah.value;
	var str4  = document.${formName}.namaDerahTanah.value;
	var str5  = document.${formName}.namaNegeriTanah.value;
	var str6 = document.${formName}.noWartaTanah.value;
	var strTujuan = document.${formName}.namatujuan.value;
	var statusRizabTnh = document.${formName}.status.value;
	var namaPemohon = document.${formName}.txtNama.value;

	if(statusRizabTnh == 'MILIK') {
		milikOrRizab = str2;
	} else if(statusRizabTnh == 'RIZAB') {
		milikOrRizab = str6;
	}

	strTajuk = str1 +", " + milikOrRizab +", " + str3 + ", "+ str4 + ", " + str5  + " OLEH " + namaPemohon + " UNTUK TUJUAN " + strTujuan ;
	document.${formName}.txtMaklumatLot.value = strTajuk;
	}
</script>
