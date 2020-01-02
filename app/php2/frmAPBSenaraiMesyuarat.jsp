<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #if ($flagPopup == 'openPopupMesyuarat')
  <tr>
    <td><fieldset>
      <legend><strong>MESYUARAT</strong></legend>
      #parse("app/php2/frmAPBMesyuaratDetail.jsp")
      </fieldset></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
  #end
  <tr>
    <td><fieldset>
      <legend><strong>SENARAI MESYUARAT</strong></legend>
      #parse("app/utils/record_paging.jsp")
      <table align="center" width="100%">
        #if ($currentKeputusan!='L' && $currentKeputusan!='T' && $currentKeputusan!='D')
        #if ($flagPopup == '')
        <tr>
          <td colspan="4" scope="row"><input name="cmdDaftar" type="button" value="Tambah" onClick="javascript:doDaftarBaruMesyuarat()"/></td>
        </tr>
        #end
        #end
        <tr class="table_header">
          <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
          <td width="45%"><strong>Bil Mesyuarat</strong></td>
          <td width="15%"><strong>Tarikh</strong></td>
          <td width="35%"><strong>Keputusan Mesyuarat</strong></td>
        </tr>
        #set ($senaraiMesyuarat = "")
        #if ($SenaraiMesyuarat.size() > 0)
        #foreach ($senaraiMesyuarat in $SenaraiMesyuarat)
        #if ($senaraiMesyuarat.bil == '')
        #set( $row = "row1" )
        #elseif (($senaraiMesyuarat.bil % 2) != 0)
        #set( $row = "row1" )
        #else 
        #set( $row = "row2" )
        #end
        <tr>
          <td class="$row" align="center">$senaraiMesyuarat.bil</td>
          <td class="$row"><a href="javascript:paparMesyuarat('$senaraiMesyuarat.idKertasKerja','$senaraiMesyuarat.idMesyuarat')" class="style2">$senaraiMesyuarat.bilMesyuarat</a></td>
          <td class="$row">$senaraiMesyuarat.tarikhMesyuarat</td>
          <td class="$row"> #if($senaraiMesyuarat.keputusan=='L')
            LULUS
            #elseif ($senaraiMesyuarat.keputusan=='D')
            LULUS SECARA DASAR
            #elseif($senaraiMesyuarat.keputusan=='T')
            TOLAK
            #else
            TANGGUH
            #end </td>
        </tr>
        #set ($idMesyuarat = $senaraiMesyuarat.idMesyuarat)
        #end
        #else
        <tr>
          <td class="row1" align="center">&nbsp;</td>
          <td class="row1">Tiada Rekod</td>
          <td class="row1">&nbsp;</td>
          <td class="row1">&nbsp;</td>
        </tr>
        #end
        <tr>
          <td colspan="8">&nbsp;</td>
        </tr>
      </table>
      </fieldset></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td align="center"> #if($idStatus == '1610201')
      <input type="button" name="cmdHantar" id="cmdHantar" value="Seterusnya" onClick="doSeterusnya()"/>
      #end
      <input type="button" name="cdmCetak3" id="cdmCetak3" value="Cetak" onClick="javascript:setTable('tableReport')"/>
  </tr>
</table>
<fieldset id="tableReport" style="display:none;"-->
<legend><strong>SENARAI LAPORAN</strong></legend>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td ><a href="#" class="style2" onClick="javascript:cetakLaporanMeyuarat('$idFail')"> Minit Mesyuarat </a></td>
  </tr>
  <tr>
    <td ><a href="#" class="style2" onClick="javascript:cetakPerakuanKSUdanKelulusanMenteri('$idFail')"> Surat Perakuan KSU dan Kelulusan Menteri </a></td>
  </tr>
</table>
</fieldset>
