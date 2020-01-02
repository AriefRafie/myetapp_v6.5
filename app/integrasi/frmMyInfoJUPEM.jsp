<style>
  .col_blue {
    color:#0000FF;
  }
</style>
<fieldset>
  <legend><strong>MAKLUMAT HAKMILIK</strong></legend>
  <table width="100%">
    <tr>
      <td width="40%" align="right" valign="top"><strong>DAERAH</strong></td>
      <td width="1%" align="center" valign="top">:</td>
      <td width="59%" align="left" valign="top" class="col_blue">$!DAERAH</td>
    </tr>
    <tr>
      <td align="right" valign="top"><strong>MUKIM</strong></td>
      <td align="center" valign="top">:</td>
      <td align="left" valign="top" class="col_blue">$!MUKIM</td>
    </tr>
    <tr>
      <td align="right" valign="top"><strong>JENIS HAKMILIK</strong></td>
      <td align="center" valign="top">:</td>
      <td align="left" valign="top" class="col_blue">$!JENIS_HAKMILIK</td>
    </tr>
    <tr>
      <td align="right" valign="top"><strong>NO HAKMILIK</strong></td>
      <td align="center" valign="top">:</td>
      <td align="left" valign="top" class="col_blue">$!NO_HAKMILIK</td>
    </tr>
    <tr>
      <td align="right" valign="top"><strong>NO LOT/NO PT</strong></td>
      <td align="center" valign="top">:</td>
      <td align="left" valign="top" class="col_blue">$!NO_LOTPT</td>
    </tr>
    <tr>
      <td align="right" valign="top"><strong>TARIKH DAFTAR BORANG K</strong></td>
      <td align="center" valign="top">:</td>
      <td align="left" valign="top" class="col_blue">$!TARIKH_DAFTAR_BORANGK</td>
    </tr>
    <tr>
      <td align="right" valign="top"><strong>LUAS DIAMBIL</strong></td>
      <td align="center" valign="top">:</td>
      <td align="left" valign="top" class="col_blue">$!LUAS_DIAMBIL</td>
    </tr>
    <tr>
      <td align="right" valign="top"><strong>TARIKH SURAT PTG</strong></td>
      <td align="center" valign="top">:</td>
      <td align="left" valign="top" class="col_blue">$!TARIKH_SURAT_PTG</td>
    </tr>
    <tr>
      <td align="right" valign="top"><strong>NO SYIT</strong></td>
      <td align="center" valign="top">:</td>
      <td align="left" valign="top" class="col_blue">$!NO_SYIT</td>
    </tr>
  </table>
</fieldset>
<br />
<fieldset>
#set ($F.NAME == 'PUB')
  <legend><strong>FAIL $F.NAME</strong></legend><br />
  <fieldset>
    <legend><strong>JUMLAH DATA $F.NAME</strong></legend>
    <table width="100%">
      <tr>
        <td width="40%" align="right" valign="top" scope="row">Jumlah Data</td>
        <td width="1%" align="center" valign="top" scope="row">:</td>
        <td width="59%" align="left" valign="top"><input name="JUMLAH_DATA_$F.NAME" type="text" id="JUMLAH_DATA_$F.NAME" value="$!JUMLAH_DATA_$F.NAME" size="10" maxlength="5" />
      &nbsp;<input type="button" id="cmdJumlahData$F.NAME" name="cmdJumlahData$F.NAME" value="SET JUMLAH DATA" /></td>
      </tr>
    </table>
  </fieldset>
  <br />
  <fieldset>
    <legend><strong>DATA $F.NAME</strong></legend>
#set ($l = '')
#foreach ($l in $ListJumlahPUB)
    <table width="70%" align="center">
      <tr>
        <td width="19%" align="right" valign="top" scope="row">Negeri</td>
        <td width="1%" align="center" valign="top" scope="row">:</td>
        <td width="29%" align="left" valign="top"><input name="DATA_$F.NAME_01" type="text" id="DATA_$F.NAME_01" value="$!DATA_$F.NAME_01" size="30" maxlength="255" /></td>
        <td width="2%" align="center" valign="top" scope="row">&nbsp;</td>
        <td width="19%" align="right" valign="top" scope="row">EastFrom</td>
        <td width="1%" align="center" valign="top" scope="row">:</td>
        <td width="29%" align="left" valign="top"><input name="DATA_$F.NAME_14" type="text" id="DATA_$F.NAME_14" value="$!DATA_$F.NAME_14" size="30" maxlength="255" /></td>
      </tr>
      <tr>
        <td align="right" valign="top" scope="row">Daerah</td>
        <td align="center" valign="top" scope="row">:</td>
        <td align="left" valign="top"><input name="DATA_$F.NAME_02" type="text" id="DATA_$F.NAME_02" value="$!DATA_$F.NAME_02" size="30" maxlength="255" /></td>
        <td align="center" valign="top" scope="row">&nbsp;</td>
        <td align="right" valign="top" scope="row">Bearing </td>
        <td align="center" valign="top" scope="row">:</td>
        <td align="left" valign="top"><input name="DATA_$F.NAME_15" type="text" id="DATA_$F.NAME_15" value="$!DATA_$F.NAME_15" size="30" maxlength="255" /></td>
      </tr>
      <tr>
        <td align="right" valign="top" scope="row">Mukim</td>
        <td align="center" valign="top" scope="row">:</td>
        <td align="left" valign="top"><input name="DATA_$F.NAME_03" type="text" id="DATA_$F.NAME_03" value="$!DATA_$F.NAME_03" size="30" maxlength="255" /></td>
        <td align="center" valign="top" scope="row">&nbsp;</td>
        <td align="right" valign="top" scope="row">Distance </td>
        <td align="center" valign="top" scope="row">:</td>
        <td align="left" valign="top"><input name="DATA_$F.NAME_16" type="text" id="DATA_$F.NAME_16" value="$!DATA_$F.NAME_16" size="30" maxlength="255" /></td>
      </tr>
      <tr>
        <td align="right" valign="top" scope="row">Seksyen</td>
        <td align="center" valign="top" scope="row">:</td>
        <td align="left" valign="top"><input name="DATA_$F.NAME_04" type="text" id="DATA_$F.NAME_04" value="$!DATA_$F.NAME_04" size="30" maxlength="255" /></td>
        <td align="center" valign="top" scope="row">&nbsp;</td>
        <td align="right" valign="top" scope="row">Unit </td>
        <td align="center" valign="top" scope="row">:</td>
        <td align="left" valign="top"><input name="DATA_$F.NAME_17" type="text" id="DATA_$F.NAME_17" value="$!DATA_$F.NAME_17" size="30" maxlength="255" /></td>
      </tr>
      <tr>
        <td align="right" valign="top" scope="row">PUNo</td>
        <td align="center" valign="top" scope="row">:</td>
        <td align="left" valign="top"><input name="DATA_$F.NAME_05" type="text" id="DATA_$F.NAME_05" value="$!DATA_$F.NAME_05" size="30" maxlength="255" /></td>
        <td align="center" valign="top" scope="row">&nbsp;</td>
        <td align="right" valign="top" scope="row">MarkDescTo </td>
        <td align="center" valign="top" scope="row">:</td>
        <td align="left" valign="top"><input name="DATA_$F.NAME_18" type="text" id="DATA_$F.NAME_18" value="$!DATA_$F.NAME_18" size="30" maxlength="255" /></td>
      </tr>
      <tr>
        <td align="right" valign="top" scope="row">PTNo</td>
        <td align="center" valign="top" scope="row">:</td>
        <td align="left" valign="top"><input name="DATA_$F.NAME_06" type="text" id="DATA_$F.NAME_7" value="$!DATA_$F.NAME_06" size="30" maxlength="255" /></td>
        <td align="center" valign="top" scope="row">&nbsp;</td>
        <td align="right" valign="top" scope="row">SerialTo</td>
        <td align="center" valign="top" scope="row">:</td>
        <td align="left" valign="top"><input name="DATA_$F.NAME_19" type="text" id="DATA_$F.NAME_19" value="$!DATA_$F.NAME_19" size="30" maxlength="255" /></td>
      </tr>
      <tr>
        <td align="right" valign="top" scope="row">Lot</td>
        <td align="center" valign="top" scope="row">:</td>
        <td align="left" valign="top"><input name="DATA_$F.NAME_07" type="text" id="DATA_$F.NAME_07" value="$!DATA_$F.NAME_07" size="30" maxlength="255" /></td>
        <td align="center" valign="top" scope="row">&nbsp;</td>
        <td align="right" valign="top" scope="row">NorthTo</td>
        <td align="center" valign="top" scope="row">:</td>
        <td align="left" valign="top"><input name="DATA_$F.NAME_20" type="text" id="DATA_$F.NAME_20" value="$!DATA_$F.NAME_20" size="30" maxlength="255" /></td>
      </tr>
      <tr>
        <td align="right" valign="top" scope="row">QTNo</td>
        <td align="center" valign="top" scope="row">:</td>
        <td align="left" valign="top"><input name="DATA_$F.NAME_08" type="text" id="DATA_$F.NAME_08" value="$!DATA_$F.NAME_08" size="30" maxlength="255" /></td>
        <td align="center" valign="top" scope="row">&nbsp;</td>
        <td align="right" valign="top" scope="row">EastTo</td>
        <td align="center" valign="top" scope="row">:</td>
        <td align="left" valign="top"><input name="DATA_$F.NAME_21" type="text" id="DATA_$F.NAME_21" value="$!DATA_$F.NAME_21" size="30" maxlength="255" /></td>
      </tr>
      <tr>
        <td align="right" valign="top" scope="row">UPI</td>
        <td align="center" valign="top" scope="row">:</td>
        <td align="left" valign="top"><input name="DATA_$F.NAME_09" type="text" id="DATA_$F.NAME_09" value="$!DATA_$F.NAME_09" size="30" maxlength="255" /></td>
        <td align="center" valign="top" scope="row">&nbsp;</td>
        <td align="right" valign="top" scope="row">BearingCal </td>
        <td align="center" valign="top" scope="row">:</td>
        <td align="left" valign="top"><input name="DATA_$F.NAME_22" type="text" id="DATA_$F.NAME_22" value="$!DATA_$F.NAME_22" size="30" maxlength="255" /></td>
      </tr>
      <tr>
        <td align="right" valign="top" scope="row">PUQTKey</td>
        <td align="center" valign="top" scope="row">:</td>
        <td align="left" valign="top"><input name="DATA_$F.NAME_10" type="text" id="DATA_$F.NAME_10" value="$!DATA_$F.NAME_10" size="30" maxlength="255" /></td>
        <td align="center" valign="top" scope="row">&nbsp;</td>
        <td align="right" valign="top" scope="row">DistanceCal </td>
        <td align="center" valign="top" scope="row">:</td>
        <td align="left" valign="top"><input name="DATA_$F.NAME_23" type="text" id="DATA_$F.NAME_23" value="$!DATA_$F.NAME_23" size="30" maxlength="255" /></td>
      </tr>
      <tr>
        <td align="right" valign="top" scope="row">MarkDescFrom </td>
        <td align="center" valign="top" scope="row">:</td>
        <td align="left" valign="top"><input name="DATA_$F.NAME_11" type="text" id="DATA_$F.NAME_11" value="$!DATA_$F.NAME_11" size="30" maxlength="255" /></td>
        <td align="center" valign="top" scope="row">&nbsp;</td>
        <td align="right" valign="top" scope="row">Class </td>
        <td align="center" valign="top" scope="row">:</td>
        <td align="left" valign="top"><input name="DATA_$F.NAME_24" type="text" id="DATA_$F.NAME_24" value="$!DATA_$F.NAME_24" size="30" maxlength="255" /></td>
      </tr>
      <tr>
        <td align="right" valign="top" scope="row">SerialFrom</td>
        <td align="center" valign="top" scope="row">:</td>
        <td align="left" valign="top"><input name="DATA_$F.NAME_12" type="text" id="DATA_$F.NAME_12" value="$!DATA_$F.NAME_12" size="30" maxlength="255" /></td>
        <td align="center" valign="top" scope="row">&nbsp;</td>
        <td align="right" valign="top" scope="row">LineCode </td>
        <td align="center" valign="top" scope="row">:</td>
        <td align="left" valign="top"><input name="DATA_$F.NAME_25" type="text" id="DATA_$F.NAME_25" value="$!DATA_$F.NAME_25" size="30" maxlength="255" /></td>
      </tr>
      <tr>
        <td align="right" valign="top" scope="row">NorthFrom</td>
        <td align="center" valign="top" scope="row">:</td>
        <td align="left" valign="top"><input name="DATA_$F.NAME_13" type="text" id="DATA_$F.NAME_13" value="$!DATA_$F.NAME_13" size="30" maxlength="255" /></td>
        <td align="center" valign="top" scope="row">&nbsp;</td>
        <td align="right" valign="top" scope="row">LineType </td>
        <td align="center" valign="top" scope="row">:</td>
        <td align="left" valign="top"><input name="DATA_$F.NAME_26" type="text" id="DATA_$F.NAME_26" value="$!DATA_$F.NAME_26" size="30" maxlength="255" /></td>
      </tr>
      <tr>
        <td colspan="7">&nbsp;</td>
      </tr>
    </table>
#end    
  </fieldset>
</fieldset>
<br />
<fieldset>
#set ($F.NAME == 'PUD')
  <legend><strong>FAIL $F.NAME</strong></legend><br />
  <fieldset>
    <legend><strong>JUMLAH DATA $F.NAME</strong></legend>
    <table width="100%">
      <tr>
        <td width="40%" align="right" valign="top" scope="row">Jumlah Data</td>
        <td width="1%" align="center" valign="top" scope="row">:</td>
        <td width="59%" align="left" valign="top"><input name="JUMLAH_DATA_$F.NAME" type="text" id="JUMLAH_DATA_$F.NAME" value="$!JUMLAH_DATA_$F.NAME" size="10" maxlength="5" />
      &nbsp;<input type="button" id="cmdJumlahData$F.NAME" name="cmdJumlahData$F.NAME" value="SET JUMLAH DATA" /></td>
      </tr>
    </table>
  </fieldset>
  <br />
  <fieldset>
    <legend><strong>DATA $F.NAME</strong></legend>
#set ($l = '')
#foreach ($l in $ListJumlahPUD)
    <table width="70%" align="center">
      <tr>
        <td width="19%" align="right" valign="top" scope="row">Negeri </td>
        <td width="1%" align="center" valign="top" scope="row">:</td>
        <td width="29%" align="left" valign="top"><input name="DATA_$F.NAME_01" type="text" id="DATA_$F.NAME_01" value="$!DATA_$F.NAME_01" size="30" maxlength="255" /></td>
        <td width="2%" align="center" valign="top" scope="row">&nbsp;</td>
        <td width="19%" align="right" valign="top" scope="row">Area</td>
        <td width="1%" align="center" valign="top" scope="row">:</td>
        <td width="29%" align="left" valign="top"><input name="DATA_$F.NAME_08" type="text" id="DATA_$F.NAME_08" value="$!DATA_$F.NAME_08" size="30" maxlength="255" /></td>
      </tr>
      <tr>
        <td align="right" valign="top" scope="row">Daerah </td>
        <td align="center" valign="top" scope="row">:</td>
        <td align="left" valign="top"><input name="DATA_$F.NAME_02" type="text" id="DATA_$F.NAME_02" value="$!DATA_$F.NAME_02" size="30" maxlength="255" /></td>
        <td align="center" valign="top" scope="row">&nbsp;</td>
        <td align="right" valign="top" scope="row">Unit</td>
        <td align="center" valign="top" scope="row">:</td>
        <td align="left" valign="top"><input name="DATA_$F.NAME_09" type="text" id="DATA_$F.NAME_09" value="$!DATA_$F.NAME_09" size="30" maxlength="255" /></td>
      </tr>
      <tr>
        <td align="right" valign="top" scope="row">Mukim </td>
        <td align="center" valign="top" scope="row">:</td>
        <td align="left" valign="top"><input name="DATA_$F.NAME_03" type="text" id="DATA_$F.NAME_03" value="$!DATA_$F.NAME_03" size="30" maxlength="255" /></td>
        <td align="center" valign="top" scope="row">&nbsp;</td>
        <td align="right" valign="top" scope="row">SvyFees</td>
        <td align="center" valign="top" scope="row">:</td>
        <td align="left" valign="top"><input name="DATA_$F.NAME_10" type="text" id="DATA_$F.NAME_10" value="$!DATA_$F.NAME_10" size="30" maxlength="255" /></td>
      </tr>
      <tr>
        <td align="right" valign="top" scope="row">Seksyen </td>
        <td align="center" valign="top" scope="row">:</td>
        <td align="left" valign="top"><input name="DATA_$F.NAME_04" type="text" id="DATA_$F.NAME_04" value="$!DATA_$F.NAME_04" size="30" maxlength="255" /></td>
        <td align="center" valign="top" scope="row">&nbsp;</td>
        <td align="right" valign="top" scope="row">LandUseCode</td>
        <td align="center" valign="top" scope="row">:</td>
        <td align="left" valign="top"><input name="DATA_$F.NAME_11" type="text" id="DATA_$F.NAME_11" value="$!DATA_$F.NAME_11" size="30" maxlength="255" /></td>
      </tr>
      <tr>
        <td align="right" valign="top" scope="row">PUNo </td>
        <td align="center" valign="top" scope="row">:</td>
        <td align="left" valign="top"><input name="DATA_$F.NAME_05" type="text" id="DATA_$F.NAME_05" value="$!DATA_$F.NAME_05" size="30" maxlength="255" /></td>
        <td align="center" valign="top" scope="row">&nbsp;</td>
        <td align="right" valign="top" scope="row">LandTitleCode </td>
        <td align="center" valign="top" scope="row">:</td>
        <td align="left" valign="top"><input name="DATA_$F.NAME_12" type="text" id="DATA_$F.NAME_12" value="$!DATA_$F.NAME_12" size="30" maxlength="255" /></td>
      </tr>
      <tr>
        <td align="right" valign="top" scope="row">LoNo </td>
        <td align="center" valign="top" scope="row">:</td>
        <td align="left" valign="top"><input name="DATA_$F.NAME_06" type="text" id="DATA_$F.NAME_06" value="$!DATA_$F.NAME_06" size="30" maxlength="255" /></td>
        <td align="center" valign="top" scope="row">&nbsp;</td>
        <td align="right" valign="top" scope="row">SijilAkaunNo</td>
        <td align="center" valign="top" scope="row">:</td>
        <td align="left" valign="top"><input name="DATA_$F.NAME_13" type="text" id="DATA_$F.NAME_13" value="$!DATA_$F.NAME_13" size="30" maxlength="255" /></td>
      </tr>
      <tr>
        <td align="right" valign="top" scope="row">FileNo</td>
        <td align="center" valign="top" scope="row">:</td>
        <td align="left" valign="top"><input name="DATA_$F.NAME_07" type="text" id="DATA_$F.NAME_07" value="$!DATA_$F.NAME_07" size="30" maxlength="255" /></td>
        <td align="center" valign="top" scope="row">&nbsp;</td>
        <td align="right" valign="top" scope="row">Remarks</td>
        <td align="center" valign="top" scope="row">:</td>
        <td align="left" valign="top"><input name="DATA_$F.NAME_14" type="text" id="DATA_$F.NAME_14" value="$!DATA_$F.NAME_14" size="30" maxlength="255" /></td>
      </tr>
      <tr>
        <td colspan="7">&nbsp;</td>
      </tr>
    </table>
#end    
  </fieldset>
</fieldset>
<br />
<fieldset>
#set ($F.NAME == 'PUL')
  <legend><strong>FAIL $F.NAME</strong></legend><br />
  <fieldset>
    <legend><strong>JUMLAH DATA $F.NAME</strong></legend>
    <table width="100%">
      <tr>
        <td width="40%" align="right" valign="top" scope="row">Jumlah Data</td>
        <td width="1%" align="center" valign="top" scope="row">:</td>
        <td width="59%" align="left" valign="top"><input name="JUMLAH_DATA_$F.NAME" type="text" id="JUMLAH_DATA_$F.NAME" value="$!JUMLAH_DATA_$F.NAME" size="10" maxlength="5" />
      &nbsp;<input type="button" id="cmdJumlahData$F.NAME" name="cmdJumlahData$F.NAME" value="SET JUMLAH DATA" /></td>
      </tr>
    </table>
  </fieldset>
  <br />
  <fieldset>
    <legend><strong>DATA $F.NAME</strong></legend>
#set ($l = '')
#foreach ($l in $ListJumlahPUL)
    <table width="70%" align="center">
      <tr>
        <td width="19%" align="right" valign="top" scope="row">Negeri </td>
        <td width="1%" align="center" valign="top" scope="row">:</td>
        <td width="29%" align="left" valign="top"><input name="DATA_$F.NAME_01" type="text" id="DATA_$F.NAME_01" value="$!DATA_$F.NAME_01" size="30" maxlength="255" /></td>
        <td width="2%" align="center" valign="top" scope="row">&nbsp;</td>
        <td width="19%" align="right" valign="top" scope="row">QTNo</td>
        <td width="1%" align="center" valign="top" scope="row">:</td>
        <td width="29%" align="left" valign="top"><input name="DATA_$F.NAME_08" type="text" id="DATA_$F.NAME_08" value="$!DATA_$F.NAME_08" size="30" maxlength="255" /></td>
      </tr>
      <tr>
        <td align="right" valign="top" scope="row">Daerah </td>
        <td align="center" valign="top" scope="row">:</td>
        <td align="left" valign="top"><input name="DATA_$F.NAME_02" type="text" id="DATA_$F.NAME_02" value="$!DATA_$F.NAME_02" size="30" maxlength="255" /></td>
        <td align="center" valign="top" scope="row">&nbsp;</td>
        <td align="right" valign="top" scope="row">UPI </td>
        <td align="center" valign="top" scope="row">:</td>
        <td align="left" valign="top"><input name="DATA_$F.NAME_09" type="text" id="DATA_$F.NAME_09" value="$!DATA_$F.NAME_09" size="30" maxlength="255" /></td>
      </tr>
      <tr>
        <td align="right" valign="top" scope="row">Mukim </td>
        <td align="center" valign="top" scope="row">:</td>
        <td align="left" valign="top"><input name="DATA_$F.NAME_4" type="text" id="DATA_$F.NAME_03" value="$!DATA_$F.NAME_03" size="30" maxlength="255" /></td>
        <td align="center" valign="top" scope="row">&nbsp;</td>
        <td align="right" valign="top" scope="row">PUQTKey </td>
        <td align="center" valign="top" scope="row">:</td>
        <td align="left" valign="top"><input name="DATA_$F.NAME_10" type="text" id="DATA_$F.NAME_10" value="$!DATA_$F.NAME_10" size="30" maxlength="255" /></td>
      </tr>
      <tr>
        <td align="right" valign="top" scope="row">Seksyen </td>
        <td align="center" valign="top" scope="row">:</td>
        <td align="left" valign="top"><input name="DATA_$F.NAME_04" type="text" id="DATA_$F.NAME_04" value="$!DATA_$F.NAME_04" size="30" maxlength="255" /></td>
        <td align="center" valign="top" scope="row">&nbsp;</td>
        <td align="right" valign="top" scope="row">Unit </td>
        <td align="center" valign="top" scope="row">&nbsp;</td>
        <td align="left" valign="top"><input name="DATA_$F.NAME_11" type="text" id="DATA_$F.NAME_11" value="$!DATA_$F.NAME_11" size="30" maxlength="255" /></td>
      </tr>
      <tr>
        <td align="right" valign="top" scope="row">PUNo </td>
        <td align="center" valign="top" scope="row">:</td>
        <td align="left" valign="top"><input name="DATA_$F.NAME_05" type="text" id="DATA_$F.NAME_05" value="$!DATA_$F.NAME_05" size="30" maxlength="255" /></td>
        <td align="center" valign="top" scope="row">&nbsp;</td>
        <td align="right" valign="top" scope="row">ApArea </td>
        <td align="center" valign="top" scope="row">:</td>
        <td align="left" valign="top"><input name="DATA_$F.NAME_12" type="text" id="DATA_$F.NAME_12" value="$!DATA_$F.NAME_12" size="30" maxlength="255" /></td>
      </tr>
      <tr>
        <td align="right" valign="top" scope="row">PTNo</td>
        <td align="center" valign="top" scope="row">:</td>
        <td align="left" valign="top"><input name="DATA_$F.NAME_06" type="text" id="DATA_$F.NAME_06" value="$!DATA_$F.NAME_06" size="30" maxlength="255" /></td>
        <td align="center" valign="top" scope="row">&nbsp;</td>
        <td align="right" valign="top" scope="row">AreaCal</td>
        <td align="center" valign="top" scope="row">:</td>
        <td align="left" valign="top"><input name="DATA_$F.NAME_13" type="text" id="DATA_$F.NAME_13" value="$!DATA_$F.NAME_13" size="30" maxlength="255" /></td>
      </tr>
      <tr>
        <td align="right" valign="top" scope="row">Lot</td>
        <td align="center" valign="top" scope="row">:</td>
        <td align="left" valign="top"><input name="DATA_$F.NAME_07" type="text" id="DATA_$F.NAME_07" value="$!DATA_$F.NAME_07" size="30" maxlength="255" /></td>
        <td align="center" valign="top" scope="row">&nbsp;</td>
        <td align="right" valign="top" scope="row">&nbsp;</td>
        <td align="center" valign="top" scope="row">&nbsp;</td>
        <td align="left" valign="top">&nbsp;</td>
      </tr>
      <tr>
        <td colspan="7">&nbsp;</td>
      </tr>
    </table>
#end    
  </fieldset>
</fieldset>
<br />
<input type="hidden" id="ID_PERMOHONAN" name="ID_PERMOHONAN" value="$!ID_PERMOHONAN" />
<input type="hidden" id="ID_HAKMILIK" name="ID_HAKMILIK" value="$!ID_HAKMILIK" />