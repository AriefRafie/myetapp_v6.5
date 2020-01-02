<style type="text/css">
<!--
.style1 {font-size: 10px}
-->
</style>

&nbsp;
<fieldset>
<legend>MAKLUMAT DOKUMEN</legend>
<table width="100%">
#foreach ($dokumen in $Dokumen)
<input name="idDokumen" type="hidden" value="$idDokumen" />
  <tr>
    <td width="29%" scope="row"><div align="right" class="style1">
      <div align="left">Bilangan Minit Fail</div>
    </div></td>
    <td width="1%" scope="row"><div align="right" class="style1">:</div></td>
    <td width="70%">$dokumen.bil_Minit_Dokumen</td>
  </tr>
  <tr>
    <td scope="row"><div align="right" class="style1">
      <div align="left">Jenis Dokumen</div>
    </div></td>
    <td scope="row"><div align="right" class="style1">:</div></td>
    <td>$dokumen.jenis_Dokumen</td>
  </tr>
  <tr>
    <td scope="row"><div align="right" class="style1">
      <div align="left">No Rujukan Dokumen</div>
    </div></td>
    <td scope="row"><div align="right" class="style1">:</div></td>
    <td>$dokumen.no_Rujukan_Dokumen</td>
  </tr>
  <tr>
    <td scope="row"><div align="right" class="style1">
      <div align="left">Tarikh Dokumen Keluar</div>
    </div></td>
    <td scope="row"><div align="right" class="style1">:</div></td>
    <td>$dokumen.tarikh_Dokumen_Keluar</td>
  </tr>
  <tr>
    <td scope="row"><div align="right" class="style1">
      <div align="left">Tarikh Dokumen Masuk</div>
    </div></td>
    <td scope="row"><div align="right" class="style1">:</div></td>
    <td>$dokumen.tarikh_Dokumen_Masuk</td>
  </tr>
  <tr>
    <td scope="row"><div align="right" class="style1">
      <div align="left">Nama Pengirim</div>
    </div></td>
    <td scope="row"><div align="right" class="style1">:</div></td>
    <td>$dokumen.nama_Pengirim</td>
  </tr>
  <tr>
    <td scope="row"><div align="right" class="style1">
      <div align="left">Nama Penerima</div>
    </div></td>
    <td scope="row"><div align="right" class="style1">:</div></td>
    <td>$dokumen.nama_Penerima</td>
  </tr>
  <tr>
    <td scope="row"><div align="right" class="style1">
      <div align="left">Tarikh Daftar</div>
    </div></td>
    <td scope="row"><div align="right" class="style1">:</div></td>
    <td>$dokumen.tarikh_Daftar</td>
  </tr>
  <tr>
    <td colspan="3" align="center" scope="row">
       
          <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" />
        
          <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="kembali()" />
          </td>
  </tr>
  #end
</table>
</fieldset>
<fieldset>
<legend>SENARAI MINIT ARAHAN</legend>
<table width="100%" >
  <tr>
    <td width="1%" scope="row">NO</td>
    <td width="10%">NO RUJUKAN DOKUMEN</td>
    <td width="20%">MINIT ARAHAN</td>
    <td width="14%">PEGAWAI YANG MEMBERI ARAHAN</td>
    <td width="14%">PEGAWAI YANG MENERIMA ARAHAN</td>
    <td width="10%">TARIKH MINIT ARAHAN</td>
    <td width="15%">STATUS TINDAKAN</td>
  </tr>
  #foreach ($minit in $MinitArahan)
  <tr>
    <td scope="row">$minit.bil</td>
    <td>$minit.no_Rujukan_Dokumen</td>
    <td>$minit.minit_Arahan</td>
    <td>$minit.PegawaiMengarah</td>
    <td>$minit.PegawaiMenerima</td>
    <td>$minit.tarikh_Minit_Arahan</td>
    <td>$minit.status_Tindakan</td>
  </tr>
  #end
</table>
</fieldset>
<table width="100%">
  <tr>
    <td align="right"><strong>CL-05-13</strong></td>
  </tr>
</table>

<script>


function kembali(){

	window.close();
}
</script>