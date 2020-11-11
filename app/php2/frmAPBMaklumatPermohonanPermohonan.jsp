#set($saizTxtTujuanPengambilan="500")
#set($saizTxtRingkasanPengalaman="900")
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #if ($mode == 'view' || $mode == 'update')
  <tr>
    <td colspan="2"><fieldset>
      <legend><strong>MAKLUMAT PERMOHONAN</strong></legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        #foreach ($beanMaklumatPermohonan in $BeanMaklumatPermohonan)
        <tr>
          <td>#if ($mode == 'update')<span class="style1">*</span>#end</td>
          <td valign="top">Tarikh Terima</td>
          <td>:</td>
          <td><input type="text" name="tarikhTerima" id="tarikhTerima" value="$beanMaklumatPermohonan.tarikhTerima" onblur="check_date(this);cekTarikhTerima(this)" size="11" $readonly class="$inputTextClass"/>
            #if ($mode != 'view') <a href="javascript:displayDatePicker('tarikhTerima',false,'dmy');"><img border="0" src="../img/calendar.gif"/> #end </td>
        </tr>
        <tr>
          <td>#if ($mode == 'update')<span class="style1">*</span>#end</td>
          <td valign="top">Tarikh Surat</td>
          <td>:</td>
          <td><input type="text" name="tarikhSurat" id="tarikhSurat" value="$beanMaklumatPermohonan.tarikhSurat" onblur="check_date(this);cekTarikhSurat(this)" size="11" $readonly class="$inputTextClass"/>
            <a href="javascript:displayDatePicker('tarikhSurat',false,'dmy');">#if ($mode != 'view')<img border="0" src="../img/calendar.gif"/>#end</td>
        </tr>
        <tr>
          <td valign="top">#if ($mode == 'update')<span class="style1">*</span>#end</td>
          <td valign="top">No. Rujukan Surat</td>
          <td valign="top">:</td>
          <td><input name="txtNoRujukanSurat" type="text" class="$inputTextClass" id="txtNoRujukanSurat"  value="$beanMaklumatPermohonan.noRujSurat" size="43" maxlength="250" $readonly />
          </td>
        </tr>
        <tr>
          <td valign="top">#if ($mode == 'update')<span class="style1">*</span>#end</td>
          <td valign="top">Perkara</td>
          <td valign="top">:</td>
          <td><textarea name="txtPerkara" id="txtPerkara" rows="5" cols="50" $readonly class="$inputTextClass"  onkeyup="textCounter(this.form.sebabTamat,this.form.remLen1,$!saizPerkara);" onkeydown="textCounter(this.form.sebabTamat,this.form.remLen1,$!saizPerkara);">$beanMaklumatPermohonan.perkara</textarea></td>
        </tr>
        <tr>
          <td width="1%">&nbsp;</td>
          <td width="28%">Jenis Tujuan</td>
          <td width="1%">:</td>
          <td width="70%">MENGOREK</td>
        </tr>
        <tr>
          <td>#if ($mode == 'update')<span class="style1">*</span>#end</td>
          <td>Kaitan Tujuan</td>
          <td>:</td>
          <td>$selectTujuanKaitan</td>
        </tr>
        <tr>
          <td valign="top">#if ($mode == 'update')<span class="style1">*</span>#end</td>
          <td valign="top">Tujuan Pengambilan</td>
          <td valign="top">:</td>
          <td valign="top"><textarea name="txtTujuanPengambilan" id="txtTujuanPengambilan" cols="43" rows="5" $readonly class="$inputTextClass"  onKeyUp="textCounter(this.form.txtTujuanPengambilan,this.form.remLen1,$!saizTxtTujuanPengambilan);" onKeyDown="textCounter(this.form.txtTujuanPengambilan,this.form.remLen1,$!saizTxtTujuanPengambilan);" >$beanMaklumatPermohonan.tujuanPengambilan</textarea></td>
        </tr>
        #if ($mode == 'update')
        <tr>
          <td valign="top">&nbsp;</td>
          <td valign="top">&nbsp;</td>
          <td valign="top">&nbsp;</td>
          <td valign="top">Baki Aksara :&nbsp;
            <input type="text" readonly="readonly" class="disabled" name="remLen1" size="3" maxlength="3" value="$!saizTxtTujuanPengambilan" /></td>
        </tr>
        #end
        <tr>
          <td>#if ($mode == 'update')<span class="style1">*</span>#end</td>
          <td>Tempoh Lesen Dipohon</td>
          <td>:</td>
          <td><input name="txtTempoh" type="text" size="1" maxlength="2" onkeyup="validateNumber(this,this.value);" value="$beanMaklumatPermohonan.tempoh" $readonly class="$inputTextClass"/>
            $selectTempoh</td>
        </tr>
        <tr>
          <td valign="top">&nbsp;</td>
          <td valign="top">Ringkasan Pengalaman Pemohon</td>
          <td valign="top">:</td>
          <td valign="top"><textarea name="txtRingkasanPengalaman" id="txtRingkasanPengalaman" cols="43" rows="5"  onKeyUp="textCounter(this.form.txtRingkasanPengalaman,this.form.remLen2,$!saizTxtRingkasanPengalaman);" onKeyDown="textCounter(this.form.txtRingkasanPengalaman,this.form.remLen2,$!saizTxtRingkasanPengalaman);" $readonly class="$inputTextClass">$beanMaklumatPermohonan.pengalaman</textarea></td>
        </tr>
        #if ($mode == 'update')
        <tr>
          <td valign="top">&nbsp;</td>
          <td valign="top">&nbsp;</td>
          <td valign="top">&nbsp;</td>
          <td valign="top">Baki Aksara :&nbsp;
            <input type="text" readonly="readonly" class="disabled" name="remLen2" size="3" maxlength="3" value="$!saizTxtRingkasanPengalaman" /></td>
        </tr>
        #end
        #end
      </table>
      </fieldset></td>
  </tr>
  #end
  <tr>
    <!--<td colspan="2"><table width="100%" border="0" cellspacing="2" cellpadding="2">
        #if ($mode == 'newProjek' || $mode == 'viewProjek' || $mode == 'updateProjek')
        <tr>
          <td>#parse("app/php2/frmAPBMaklumatProjek.jsp")</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
        </tr>
        #end
        #if ($mode == 'view' || $mode == 'newProjek' || $mode == 'viewProjek' || $mode == 'updateProjek')
        <tr>
          <td><fieldset>
            <legend><strong>SENARAI PROJEK</strong></legend>
            <table align="center" width="100%">
              #if ($mode == 'view')
              <tr>
                <td colspan="5" scope="row"><input name="cmdDaftar" type="button" value="Tambah" onclick="javascript:tambahProjek()"/></td>
              </tr>
              #end
              <tr class="table_header">
                <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
                <td width="95%"><strong>Nama Projek</strong></td>
              </tr>
              #set ($listProjek = "")
              #if ($SenaraiProjek.size() > 0)
              #foreach ($listProjek in $SenaraiProjek)
              #if ($listProjek.bil == '')
              #set( $row = "row1" )
              #elseif (($listProjek.bil % 2) != 0)
              #set( $row = "row1" )
              #else 
              #set( $row = "row2" )
              #end
              <tr>
                <td class="$row" align="center">$listProjek.bil</td>
                <td class="$row"><a href="javascript:paparProjek('$listProjek.idProjek')" class="style2">$listProjek.namaProjek</a></td>
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
        #end
      </table></td>-->
  </tr>
  #if ($mode == 'view' || $mode == 'update')
  <tr>
    <td colspan="2"><fieldset>
      <legend><strong>KAWASAN PERMOHONAN</strong></legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        #foreach ($beanMaklumatPermohonan in $BeanMaklumatPermohonan)
        <tr>
          <td width="1%">#if ($mode == 'update')<span class="style1">*</span>#end</td>
          <td width="29%">Luar Perairan Negeri</td>
          <td width="70%">: $selectFlagLuar</td>
        </tr>
        <tr>
          <td width="1%">#if ($mode == 'update')<span class="style1">*</span>#end</td>
          <td>Negeri</td>
          <td>: 
            $selectNegeri</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Lokasi</td>
          <td>:
            <input name="txtLokasi" type="text" class="$inputTextClass" id="txtLokasi"  value="$beanMaklumatPermohonan.lokasi" size="43" maxlength="250" $readonly /></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Luas Dipohon</td>
          <td>:
            <input type="text" name="txtLuas" id="txtLuas" size="10" onkeyup="validateNumber(this,this.value);" maxlength="10" value="$beanMaklumatPermohonan.luas" $readonly class="$inputTextClass"/>
            $selectLuas</td>
        </tr>
        #end
      </table>
      </fieldset></td>
  </tr>
  #end
  <tr>
    <td colspan="2"><table width="100%" border="0" cellspacing="2" cellpadding="2">
        #if ($mode == 'newKoordinat' || $mode == 'viewKoordinat' || $mode == 'updateKoordinat')
        <tr>
          <td>#parse("app/php2/frmAPBMaklumatKoordinat.jsp")</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
        </tr>
        #end
        #if ($mode == 'view' || $mode == 'newKoordinat' || $mode == 'viewKoordinat' || $mode == 'updateKoordinat')
        <tr>
          <td><fieldset>
            <legend><strong>SENARAI KOORDINAT</strong></legend>
            <table align="center" width="100%">
              #if ($mode == 'view')
              <tr>
                <td colspan="3" scope="row"><input name="cmdDaftar" type="button" value="Tambah" onclick="javascript:tambahKoordinat()"/></td>
              </tr>
              #end
              <tr class="table_header">
                <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
                <td width="35%"><strong>Label Koordinat</strong></td>
                <td align="center"><strong>Latitud (U)</strong></td>
                <td align="center"><strong>Longitud (T)</strong></td>
              </tr>
              #set ($listKoordinat = "")
              #if ($SenaraiKoordinat.size() > 0)
              #foreach ($listKoordinat in $SenaraiKoordinat)
              #if ($listKoordinat.bil == '')
              #set( $row = "row1" )
              #elseif (($listKoordinat.bil % 2) != 0)
              #set( $row = "row1" )
              #else 
              #set( $row = "row2" )
              #end
              <tr>
                <td class="$row" align="center">$listKoordinat.bil</td>
                <td class="$row"><a href="javascript:paparKoordinat('$listKoordinat.idKoordinat')" class="style2">$listKoordinat.labelTitik</a></td>
                <td align="center" class="$row">$listKoordinat.darjahU&deg; &nbsp; $listKoordinat.minitU&prime; &nbsp; #if($!listKoordinat.saatU != '') $listKoordinat.saatU&Prime; #end</td>
                <td align="center" class="$row">$listKoordinat.darjahT&deg; &nbsp; $listKoordinat.minitT&prime; &nbsp; #if($!listKoordinat.saatT != '') $listKoordinat.saatT&Prime; #end</td>
              </tr>
              #end
              #else
              <tr>
                <td class="row1" align="center">&nbsp;</td>
                <td class="row1">Tiada Rekod</td>
                <td align="center" class="row1">&nbsp;</td>
                <td align="center" class="row1">&nbsp;</td>
              </tr>
              #end
            </table>
            </fieldset></td>
        </tr>
        <tr>
          <td><fieldset>
            <legend><strong>SENARAI SEJARAH KOORDINAT</strong></legend>
            <table align="center" width="100%">
              <tr class="table_header">
                <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
                <td width="35%" align="center"><strong>Label Koordinat</strong></td>
                <td align="center"><strong>Latitud (U)</strong></td>
                <td align="center"><strong>Longitud (T)</strong></td>
              </tr>
              #set ($listKoordinatHistory = "")
              #if ($SenaraiKoordinatHistory.size() > 0)
              #foreach ($listKoordinatHistory in $SenaraiKoordinatHistory)
              #if ($listKoordinatHistory.bil == '')
              #set( $row = "row1" )
              #elseif (($listKoordinatHistory.bil % 2) != 0)
              #set( $row = "row1" )
              #else 
              #set( $row = "row2" )
              #end
              <tr>
                <td class="$row" align="center">$listKoordinatHistory.bil</td>
                <td class="$row">$listKoordinatHistory.labelTitik</a></td>
                <td align="center" class="$row">$listKoordinatHistory.darjahU&deg; &nbsp; $listKoordinatHistory.minitU&prime; &nbsp; #if($!listKoordinatHistory.saatU != '') $listKoordinatHistory.saatU&Prime; #end</td>
                <td align="center" class="$row">$listKoordinatHistory.darjahT&deg; &nbsp; $listKoordinatHistory.minitT&prime; &nbsp; #if($!listKoordinatHistory.saatT != '') $listKoordinatHistory.saatT&Prime; #end</td>
              </tr>
              #end
              #else
              <tr>
                <td class="row1" align="center">&nbsp;</td>
                <td class="row1">Tiada Rekod</td>
                <td align="center" class="row1">&nbsp;</td>
                <td align="center" class="row1">&nbsp;</td>
              </tr>
              #end
            </table>
          </fieldset></td>
        </tr>
        #end
      </table></td>
  </tr>
  <tr>
    <td colspan="2"><table width="100%" border="0" cellspacing="2" cellpadding="2">
        #if ($mode == 'newPakar' || $mode == 'viewPakar' || $mode == 'updatePakar')
        <tr>
          <td>#parse("app/php2/frmAPBMaklumatPakar.jsp")</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
        </tr>
        #end
        #if ($mode == 'view' || $mode == 'newPakar' || $mode == 'viewPakar' || $mode == 'updatePakar')
        <tr>
          <td><fieldset>
            <legend><strong>SENARAI PAKAR</strong></legend>
            <table align="center" width="100%">
              #if ($mode == 'view')
              <tr>
                <td colspan="5" scope="row"><input name="cmdDaftar" type="button" value="Tambah" onclick="javascript:tambahPakar()"/></td>
              </tr>
              #end
              <tr class="table_header">
                <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
                <td width="95%"><strong>Nama Pakar</strong></td>
              </tr>
              #set ($listPakar = "")
              #if ($SenaraiPakar.size() > 0)
              #foreach ($listPakar in $SenaraiPakar)
              #if ($listPakar.bil == '')
              #set( $row = "row1" )
              #elseif (($listPakar.bil % 2) != 0)
              #set( $row = "row1" )
              #else 
              #set( $row = "row2" )
              #end
              <tr>
                <td class="$row" align="center">$listPakar.bil</td>
                <td class="$row"><a href="javascript:paparPakar('$listPakar.idPakar')" class="style2">$listPakar.nama</a></td>
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
        #end
      </table></td>
  </tr>
  <tr>
    <td colspan="2">&nbsp;</td>
  </tr>
  #if ($mode == 'update')
  <tr>
    <td colspan="2" valign="bottom"><i><font color="#ff0000">Perhatian</font> : Pastikan label bertanda <font color="#ff0000">*</font> diisi.</i></td>
  </tr>
  #end
  <tr>
    <td width="30%">&nbsp;</td>
    <td width="70%"> #if ($mode == 'view')
      <input type="button" name="cmdKemaskiniPermohonan" id="cmdKemaskiniPermohonan" value="Kemaskini" onclick="kemaskiniPermohonan()"/>
      #if ($idStatus == '1610198')
      <input type="button" name="cmdSeterusnya" id="cmdSeterusnya" value="Seterusnya" onclick="seterusnya()"/>
      <input type="button" name="cmdBatalPermohonan" id="cmdBatalPermohonan" value="Batal Permohonan" onClick="gotoBatalPermohonan()"/>
      #end
      <input type="button" name="cdmCetak" id="cdmCetak" value="Cetak" onClick="javascript:setTable('tableReport')"/>
      #end
      #if ($mode == 'update')
      <input type="button" name="cmdSimpanKemaskiniPermohonan" id="cmdSimpanKemaskiniPermohonan" value="Simpan" onclick="simpanKemaskiniPermohonan()"/>
      <input type="button" name="cmdBatalKemaskiniPermohonan" id="cmdSimpanKemaskiniPermohonan" value="Batal" onclick="batalKemaskiniPermohonan()"/>
      #end </td>
  </tr>
</table>
<script>
function validateCurrency(elmnt,content,content2) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = content2.toFixed(2);
		return;
	}
	var num = content * 1;
	elmnt.value = num.toFixed(2);
	return;
}
</script>
