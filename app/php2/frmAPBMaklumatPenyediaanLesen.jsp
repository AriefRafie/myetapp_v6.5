<style type="text/css">
<!--
.style1 {
	color: #FF0000
}
.style2 {
	color: #0000FF
}
-->
</style>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
        #foreach ($beanMaklumatSuratKelulusanLesenKepadaPemohon in $BeanMaklumatSuratKelulusanLesenKepadaPemohon)
        <tr>
          <td width="1%" valign="top">#if ($mode != 'view')<span class="style1">*</span>#end</td>
          <td width="28%" valign="top">No Lesen</td>
          <td width="1%" valign="top">:</td>
          <td width="70%" valign="top"><input name="txtNoLesen" type="text" class="$inputTextClass" id="txtNoLesen" value="$beanMaklumatSuratKelulusanLesenKepadaPemohon.txtNoLesen" size="30" maxlength="100" $readonly/></td>
        </tr>
        <tr>
          <td valign="top">#if ($mode != 'view')<span class="style1">*</span>#end</td>
          <td valign="top">Tarikh Mula</td>
          <td valign="top">:</td>
          <td valign="top"><input name="txtTarikhMula" type="text" class="$inputTextClass" id="txtTarikhMula" onBlur="check_date(this);autoCalc(this, this.value, 'txtTarikhTamat');" value="$beanMaklumatSuratKelulusanLesenKepadaPemohon.txtTarikhMula" size="9" maxlength="10" $readonly />
            #if ($mode != 'view') <a href="javascript:displayDatePicker('txtTarikhMula',false,'dmy');"><img border="0" src="../img/calendar.gif"/> #end </td>
        </tr>
        <tr>
          <td valign="top">&nbsp;</td>
          <td valign="top">Tempoh Yang Diluluskan</td>
          <td valign="top">:</td>
          <td valign="top">
          <input type="hidden" id="temp_tempoh" name="temp_tempoh" value="$beanMaklumatSuratKelulusanLesenKepadaPemohon.tempohDiluluskan">
          <input type="hidden" id="temp_id_tempoh" name="temp_id_tempoh" value="$beanMaklumatSuratKelulusanLesenKepadaPemohon.idTempoh">
          $beanMaklumatSuratKelulusanLesenKepadaPemohon.tempohDiluluskan #if($beanMaklumatSuratKelulusanLesenKepadaPemohon.idTempoh=='1') Bulan #else Tahun #end</td>
        </tr>
        <tr>
          <td valign="top">#if ($mode != 'view')<span class="style1">*</span>#end</td>
          <td valign="top">Tarikh Tamat</td>
          <td valign="top">:</td>
          <td valign="top"><input name="txtTarikhTamat" type="text" class="$inputTextClass" id="txtTarikhTamat" onBlur="check_date(this)" value="$beanMaklumatSuratKelulusanLesenKepadaPemohon.txtTarikhTamat" size="9" maxlength="10" $readonly/>
            #if ($mode != 'view') <a href="javascript:displayDatePicker('txtTarikhTamat',false,'dmy');"><img border="0" src="../img/calendar.gif"/> #end </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Fee Lesen</td>
          <td>:</td>
          <td>RM $beanMaklumatSuratKelulusanLesenKepadaPemohon.kadarFeeLesen          bagi setiap
            $beanMaklumatSuratKelulusanLesenKepadaPemohon.kmPersegi
            km persegi</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Jumlah Fee Lesen</td>
          <td>:</td>
          <td>RM
            $beanMaklumatSuratKelulusanLesenKepadaPemohon.jumlahFeeLesen </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Kadar Royalti Pasir</td>
          <td>:</td>
          <td>RM
            $beanMaklumatSuratKelulusanLesenKepadaPemohon.kadarRoyaltiPasir / meter padu</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Jumlah Royalti Keseluruhan</td>
          <td>:</td>
          <td>RM $beanMaklumatSuratKelulusanLesenKepadaPemohon.jumlahRoyaltiSeluruh</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Jumlah Pendahuluan Royalti</td>
          <td>:</td>
          <td>RM          $beanMaklumatSuratKelulusanLesenKepadaPemohon.jumDahuluRoyalti</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Luar Perairan Negeri</td>
          <td>: </td>
          <td>#if($beanMaklumatSuratKelulusanLesenKepadaPemohon.flagLuar=='1') Ya #else Tidak #end</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Negeri</td>
          <td>: </td>
          <td>$beanMaklumatSuratKelulusanLesenKepadaPemohon.namaNegeri</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Lokasi</td>
          <td>: </td>
          <td>$beanMaklumatSuratKelulusanLesenKepadaPemohon.lokasi</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Luas Kawasan</td>
          <td>: </td>
          <td>$beanMaklumatSuratKelulusanLesenKepadaPemohon.luas $beanMaklumatSuratKelulusanLesenKepadaPemohon.jenisLuas</td>
        </tr>
        #if ($mode != 'view')
        <tr>
          <td colspan="4" valign="bottom"><i><font color="#ff0000">Perhatian</font> : Pastikan label bertanda <font color="#ff0000">*</font> diisi.</i></td>
        </tr>
        #end
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>#if ($mode == 'view')
            <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onClick="doKemaskini()"/>
            <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onClick="javascript:setTable('tableReport')"/>
            #if($idStatus =='1610239')
            <input type="button" name="cmdSeterusnya" id="cmdHantar" value="Seterusnya" onClick="doSeterusnya()"/>
            <input type="button" name="cmdBatalPermohonan" id="cmdBatalPermohonan" value="Batal Permohonan" onClick="gotoBatalPermohonan()"/>
            #end       
            #end
            #if ($mode == 'update')
            <input type="button" name="cmdSimpanKemaskini" id="cmdSimpanKemaskini" value="Simpan" onClick="doSimpanKemaskiniSuratKelulusanLesenKepadaPemohon()"/>
            <input type="button" name="cmdBatalKemaskini" id="cmdBatalKemaskini" value="Batal" onClick="doBatalKemaskini()"/>
            #end </td>
        </tr>
        #end
      </table>
</table>
<fieldset id="tableReport" style="display:none;"-->
<legend><strong>SENARAI LAPORAN</strong></legend>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td ><a href="#" class="style2" onClick="javascript:cetakSuratHantarLesen('$idFail')"> Surat Hantar Lesen </a></td>
  </tr>
  <tr>
    <td ><a href="#" class="style2" onClick="javascript:cetakSyaratKelulusan('$idFail','$idPermohonan')"> Syarat-syarat Kelulusan </a></td>
  </tr>
</table>
</fieldset>