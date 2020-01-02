<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td><fieldset>
      <legend>TETAPAN SASARAN KPI</legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="2%">&nbsp;</td>
          <td width="2%">&nbsp;</td>
          <td width="49%">&nbsp;</td>
          <td width="1%">&nbsp;</td>
          <td colspan="4">&nbsp;</td>
        </tr>
        #foreach($beanMaklumatKPISasaran in $BeanMaklumatKPISasaran) 
        <tr>
          <td><strong>1)</strong></td>
          <td colspan="2"><strong>Sasaran Aktiviti :</strong></td>
          <td>&nbsp;</td>
          <td colspan="4">&nbsp;</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td valign="top">a)</td>
          <td valign="top">Mendaftar Permohonan <strong>SEHINGGA</strong> menghantar serentak surat meminta Ulasan dari KJP &amp; KJT, Laporan Tanah JKPTG(N) dan Nilaian JPPH.</td>
          <td >:</td>
          <td colspan="4"><input type="text" name="F1" id="F1" size="5" maxlength="5" style="text-align:center" value="$beanMaklumatKPISasaran.F1" $readonly class="$inputTextClass" onkeyup="validateNumber(this,this.value);">
          <strong>hari</strong></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td valign="top">b)</td>
          <td valign="top">Terima lengkap Ulasan KJP &amp; KJT, Laporan Tanah JKPTG(N) dan Nilaian JPPH <strong>SEHINGGA </strong>menghantar Kertas Cadangan kepada Menteri Kewangan.</td>
          <td >:</td>
          <td colspan="4" ><input type="text" name="F2" id="F2" size="5" maxlength="5" style="text-align:center" value="$beanMaklumatKPISasaran.F2" $readonly class="$inputTextClass" onkeyup="validateNumber(this,this.value);">
            <strong>hari</strong></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td valign="top">c)</td>
          <td valign="top">Terima Keputusan Menteri Kewangan <strong>SEHINGGA</strong> Minit Ceraian dihantar ke KSU.</td>
          <td >:</td>
          <td colspan="4" ><input type="text" name="F3" id="F3" size="5" maxlength="5" style="text-align:center" value="$beanMaklumatKPISasaran.F3" $readonly class="$inputTextClass" onkeyup="validateNumber(this,this.value);">
            <strong>hari</strong></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td valign="top">d)</td>
          <td valign="top">Terima Kelulusan Menteri NRE <strong>SEHINGGA</strong> Borang 12A/12B dihantar kpd Pemohon.</td>
          <td >:</td>
          <td colspan="4" ><input type="text" name="F4" id="F4" size="5" maxlength="5" style="text-align:center" value="$beanMaklumatKPISasaran.F4" $readonly class="$inputTextClass" onkeyup="validateNumber(this,this.value);">
            <strong>hari</strong></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td >&nbsp;</td>
          <td >&nbsp;</td>
          <td >&nbsp;</td>
          <td colspan="4" >&nbsp;</td>
        </tr>
        <tr>
          <td><strong>2)</strong></td>
          <td colspan="2" ><strong>Permohonan Masih Dalam Proses :</strong></td>
          <td >&nbsp;</td>
          <td colspan="4" >&nbsp;</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td >a)</td>
          <td >Menunggu Ulasan dari KJP &amp; KJT, Laporan Tanah JKPTG(N) dan Nilaian JPPH.</td>
          <td  bgcolor="#33FF99">:</td>
          <td width="3%"  align="right">&nbsp;</td>
          <td width="1%"  align="center">&lt;</td>
          <td width="3%" ><input type="text" name="F5" id="F5" size="5" maxlength="5" style="text-align:center" value="$beanMaklumatKPISasaran.F5" $readonly class="$inputTextClass" onkeyup="validateNumber(this,this.value);" onBlur="doChangeField()"></td>
          <td width="39%" ><strong>hari</strong></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td >&nbsp;</td>
          <td >&nbsp;</td>
          <td  bgcolor="yellow">:</td>
          <td><div align="center"><span class="style2">$P1</span></div></td>
          <td  align="center">-</td>
          <td ><input type="text" name="F6" id="F6" size="5" maxlength="5" style="text-align:center" value="$beanMaklumatKPISasaran.F6" $readonly class="$inputTextClass" onkeyup="validateNumber(this,this.value);" onBlur="validateHariDimasukkan(this,this.value,$beanMaklumatKPISasaran.F6,$P1);doChangeField()"></td>
          <td ><strong>hari</strong></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td >&nbsp;</td>
          <td >&nbsp;</td>
          <td  bgcolor="red">:</td>
          <td >&nbsp;</td>
          <td  align="center">&gt;</td>
          <td ><div align="center"><span class="style2">$P2</span></div></td>
          <td ><strong>hari</strong></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td >&nbsp;</td>
          <td >&nbsp;</td>
          <td >&nbsp;</td>
          <td colspan="4" >&nbsp;</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td >b)</td>
          <td >Menunggu Keputusan dari Menteri Kewangan.</td>
          <td  bgcolor="#33FF99">:</td>
          <td width="3%"  align="right">&nbsp;</td>
          <td width="1%"  align="center">&lt;</td>
          <td colspan="2" ><input type="text" name="F7" id="F7" size="5" maxlength="5" style="text-align:center" value="$beanMaklumatKPISasaran.F7" $readonly class="$inputTextClass" onkeyup="validateNumber(this,this.value);" onBlur="doChangeField()"> <strong>hari</strong></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td >&nbsp;</td>
          <td >&nbsp;</td>
          <td  bgcolor="yellow">:</td>
          <td><div align="center"><span class="style2">$P3</span></div></td>
          <td  align="center">-</td>
          <td colspan="2" ><input type="text" name="F8" id="F8" size="5" maxlength="5" style="text-align:center" value="$beanMaklumatKPISasaran.F8" $readonly class="$inputTextClass" onkeyup="validateNumber(this,this.value);" onBlur="validateHariDimasukkan(this,this.value,$beanMaklumatKPISasaran.F8,$P3);doChangeField()"> <strong>hari</strong></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td >&nbsp;</td>
          <td >&nbsp;</td>
          <td  bgcolor="red">:</td>
          <td >&nbsp;</td>
          <td  align="center">&gt;</td>
          <td ><div align="center"><span class="style2">$P4</span></div></td>
          <td ><strong>hari</strong></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td >&nbsp;</td>
          <td >&nbsp;</td>
          <td >&nbsp;</td>
          <td colspan="4" >&nbsp;</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td >c)</td>
          <td >Menunggu Perakuan KSU seterusnya Kelulusan Menteri NRE.</td>
          <td  bgcolor="#33FF99">:</td>
          <td width="3%"  align="right">&nbsp;</td>
          <td width="1%"  align="center">&lt;</td>
          <td colspan="2" ><input type="text" name="F9" id="F9" size="5" maxlength="5" style="text-align:center" value="$beanMaklumatKPISasaran.F9" $readonly class="$inputTextClass" onkeyup="validateNumber(this,this.value);" onBlur="doChangeField()"> <strong>hari</strong></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td >&nbsp;</td>
          <td >&nbsp;</td>
          <td  bgcolor="yellow">:</td>
          <td><div align="center"><span class="style2">$P5</span></div></td>
          <td  align="center">-</td>
          <td colspan="2" ><input type="text" name="F10" id="F10" size="5" maxlength="5" style="text-align:center" value="$beanMaklumatKPISasaran.F10" $readonly class="$inputTextClass" onkeyup="validateNumber(this,this.value);" onBlur="validateHariDimasukkan(this,this.value,$beanMaklumatKPISasaran.F10,$P5);doChangeField()"> <strong>hari</strong></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td >&nbsp;</td>
          <td >&nbsp;</td>
          <td  bgcolor="red">:</td>
          <td >&nbsp;</td>
          <td  align="center">&gt;</td>
          <td ><div align="center"><span class="style2">$P6</span></div></td>
          <td ><strong>hari</strong></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td >&nbsp;</td>
          <td >&nbsp;</td>
          <td >&nbsp;</td>
          <td colspan="4" >&nbsp;</td>
        </tr>
        #end
        <tr>
          <td colspan="8" align="center">
          #if ($mode == 'view')
          <input name="cmdKemaskini" id="cmdKemaskini" type="button" value="Kemaskini" onClick="kemaskini()">
          #end
          #if ($mode == 'update')
          <input name="cmdSimpanKemaskini" id="cmdSimpanKemaskini" type="button" value="Simpan" onClick="simpanKemaskini()">
          <input name="cmdBatal" id="cmdBatal" type="button" value="Batal" onClick="batal()">
          #end          </td>
        </tr>
      </table>
      </fieldset></td>
  </tr>
</table>
