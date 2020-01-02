<style>
  .col_blue {
    color:#0000FF;
  }
</style>
#if ($noData == 'true')
<fieldset>
  <div class="warning">
    Sila pilih permohonan yang hendak dijanakan fail-fail PU terlebih dahulu di tab Seksyen 8.
  </div>
</fieldset>
#else
#if ($savePUBData == 'true')
<fieldset>
  <div class="success">
    Maklumat data bagi fail PUB telah berjaya disimpan.
  </div>
</fieldset>
#end
#if ($savePUDData == 'true')
<fieldset>
  <div class="success">
    Maklumat data bagi fail PUD telah berjaya disimpan.
  </div>
</fieldset>
#end
#if ($savePULData == 'true')
<fieldset>
  <div class="success">
    Maklumat data bagi fail PUL telah berjaya disimpan.
  </div>
</fieldset>
#end
#if ($emailSent == 'true')
<fieldset>
  <div class="success">
    Fail PUB, PUD dan PUL telah berjaya dihantar ke alamat e-mel yang dinyatakan. Sila semak peti masuk e-mel untuk memuat turun fail-fail tersebut.
  </div>
</fieldset>
#end
<fieldset>
  <legend><strong>MAKLUMAT HAKMILIK</strong></legend>
  <table width="100%">
    <tr>
      <td width="40%" align="right" valign="top"><strong>DAERAH</strong></td>
      <td width="1%" align="center" valign="top">:</td>
      <td width="59%" align="left" valign="top" class="col_blue" style="text-transform:uppercase">$!DAERAH</td>
    </tr>
    <tr>
      <td align="right" valign="top"><strong>MUKIM</strong></td>
      <td align="center" valign="top">:</td>
      <td align="left" valign="top" class="col_blue" style="text-transform:uppercase">$!MUKIM</td>
    </tr>
    <tr>
      <td align="right" valign="top"><strong>JENIS HAKMILIK</strong></td>
      <td align="center" valign="top">:</td>
      <td align="left" valign="top" class="col_blue" style="text-transform:uppercase">$!JENIS_HAKMILIK</td>
    </tr>
    <tr>
      <td align="right" valign="top"><strong>NO HAKMILIK</strong></td>
      <td align="center" valign="top">:</td>
      <td align="left" valign="top" class="col_blue" style="text-transform:uppercase">$!NO_HAKMILIK</td>
    </tr>
    <tr>
      <td align="right" valign="top"><strong>NO LOT/NO PT</strong></td>
      <td align="center" valign="top">:</td>
      <td align="left" valign="top" class="col_blue" style="text-transform:uppercase">$!NO_LOTPT</td>
    </tr>
    <tr>
      <td align="right" valign="top"><strong>TARIKH DAFTAR BORANG K</strong></td>
      <td align="center" valign="top">:</td>
      <td align="left" valign="top" class="col_blue" style="text-transform:uppercase">$!TARIKH_DAFTAR_BORANGK</td>
    </tr>
    <tr>
      <td align="right" valign="top"><strong>LUAS DIAMBIL</strong></td>
      <td align="center" valign="top">:</td>
      <td align="left" valign="top" class="col_blue" style="text-transform:uppercase">$!LUAS_DIAMBIL</td>
    </tr>
    <tr>
      <td align="right" valign="top"><strong>TARIKH SURAT PTG</strong></td>
      <td align="center" valign="top">:</td>
      <td align="left" valign="top" class="col_blue" style="text-transform:uppercase">$!TARIKH_SURAT_PTG</td>
    </tr>
    <tr>
      <td align="right" valign="top"><strong>NO SYIT</strong></td>
      <td align="center" valign="top">:</td>
      <td align="left" valign="top" class="col_blue" style="text-transform:uppercase">$!NO_SYIT</td>
    </tr>
    <tr>
      <td colspan="3">&nbsp;</td>
    </tr>
    <tr>
      <td colspan="3" align="center"><a href="http://www.jupem.gov.my/sppmg/" target="_blank" class="col_blue" style="text-decoration:underline">Portal JUPEM2U</a></td>
    </tr>
  </table>
</fieldset>
<br />
<fieldset>
  <legend><strong>FAIL PUB</strong></legend>
  <input type="button" id="cmdPUBTambah" name="cmdPUBTambah" value="Tambah Data Fail PUB" onclick="addRowPUB();" /><br /><br />
#if ($viewPUBField == 'true')  
  <fieldset>
    <legend><strong>DATA PUB</strong></legend>
    <table width="100%" align="center">
      <tr>
        <td width="15%" align="right" valign="top" scope="row"><strong>Negeri</strong></td>
        <td width="2%" align="center" valign="top" scope="row">:</td>
        <td width="31%" align="left" valign="top">$!SOC_NEGERI_PUB</td>
        <td width="4%" align="center" valign="top" scope="row">&nbsp;</td>
        <td width="15%" align="right" valign="top" scope="row"><strong>EastFrom</strong></td>
        <td width="2%" align="center" valign="top" scope="row">:</td>
        <td width="31%" align="left" valign="top"><input name="DATA_PUB_14" type="text" id="DATA_PUB_14" value="$!DATA_PUB_14" size="20" maxlength="12" /></td>
      </tr>
      <tr>
        <td align="right" valign="top" scope="row"><strong>Daerah</strong></td>
        <td align="center" valign="top" scope="row">:</td>
        <td align="left" valign="top">$!SOC_DAERAH_PUB</td>
        <td align="center" valign="top" scope="row">&nbsp;</td>
        <td align="right" valign="top" scope="row"><strong>Bearing </strong></td>
        <td align="center" valign="top" scope="row">:</td>
        <td align="left" valign="top"><input name="DATA_PUB_15" type="text" id="DATA_PUB_15" value="$!DATA_PUB_15" size="20" maxlength="9" /></td>
      </tr>
      <tr>
        <td align="right" valign="top" scope="row"><strong>Mukim</strong></td>
        <td align="center" valign="top" scope="row">:</td>
        <td align="left" valign="top">$!SOC_MUKIM_PUB</td>
        <td align="center" valign="top" scope="row">&nbsp;</td>
        <td align="right" valign="top" scope="row"><strong>Distance </strong></td>
        <td align="center" valign="top" scope="row">:</td>
        <td align="left" valign="top"><input name="DATA_PUB_16" type="text" id="DATA_PUB_16" value="$!DATA_PUB_16" size="20" maxlength="10" /></td>
      </tr>
      <tr>
        <td align="right" valign="top" scope="row"><strong>Seksyen</strong></td>
        <td align="center" valign="top" scope="row">:</td>
        <td align="left" valign="top"><input name="DATA_PUB_04" type="text" id="DATA_PUB_04" value="$!DATA_PUB_04" size="10" maxlength="3" /></td>
        <td align="center" valign="top" scope="row">&nbsp;</td>
        <td align="right" valign="top" scope="row"><strong>Unit </strong></td>
        <td align="center" valign="top" scope="row">:</td>
        <td align="left" valign="top"><input name="DATA_PUB_17" type="text" id="DATA_PUB_17" value="$!DATA_PUB_17" size="10" maxlength="2" /></td>
      </tr>
      <tr>
        <td align="right" valign="top" scope="row"><strong>PUNo</strong></td>
        <td align="center" valign="top" scope="row">:</td>
        <td align="left" valign="top"><input name="DATA_PUB_05" type="text" id="DATA_PUB_05" value="$!DATA_PUB_05" size="30" maxlength="15" /></td>
        <td align="center" valign="top" scope="row">&nbsp;</td>
        <td align="right" valign="top" scope="row"><strong>MarkDescTo </strong></td>
        <td align="center" valign="top" scope="row">:</td>
        <td align="left" valign="top">$!SOC_MARKDESC_TO</td>
      </tr>
      <tr>
        <td align="right" valign="top" scope="row"><strong>PTNo</strong></td>
        <td align="center" valign="top" scope="row">:</td>
        <td align="left" valign="top"><input name="DATA_PUB_06" type="text" id="DATA_PUB_06" value="$!DATA_PUB_06" size="20" maxlength="8" onblur="generateQTKeyPUB();" /></td>
        <td align="center" valign="top" scope="row">&nbsp;</td>
        <td align="right" valign="top" scope="row"><strong>SerialTo</strong></td>
        <td align="center" valign="top" scope="row">:</td>
        <td align="left" valign="top"><input name="DATA_PUB_19" type="text" id="DATA_PUB_19" value="$!DATA_PUB_19" size="20" maxlength="10" /></td>
      </tr>
      <tr>
        <td align="right" valign="top" scope="row"><strong>Lot</strong></td>
        <td align="center" valign="top" scope="row">:</td>
        <td align="left" valign="top"><input name="DATA_PUB_07" type="text" id="DATA_PUB_07" value="$!DATA_PUB_07" size="20" maxlength="7" onblur="generateUPIPUB();" /></td>
        <td align="center" valign="top" scope="row">&nbsp;</td>
        <td align="right" valign="top" scope="row"><strong>NorthTo</strong></td>
        <td align="center" valign="top" scope="row">:</td>
        <td align="left" valign="top"><input name="DATA_PUB_20" type="text" id="DATA_PUB_20" value="$!DATA_PUB_20" size="20" maxlength="12" /></td>
      </tr>
      <tr>
        <td align="right" valign="top" scope="row"><strong>QTNo</strong></td>
        <td align="center" valign="top" scope="row">:</td>
        <td align="left" valign="top"><input name="DATA_PUB_08" type="text" id="DATA_PUB_08" value="$!DATA_PUB_08" size="30" maxlength="16" /></td>
        <td align="center" valign="top" scope="row">&nbsp;</td>
        <td align="right" valign="top" scope="row"><strong>EastTo</strong></td>
        <td align="center" valign="top" scope="row">:</td>
        <td align="left" valign="top"><input name="DATA_PUB_21" type="text" id="DATA_PUB_21" value="$!DATA_PUB_21" size="20" maxlength="12" /></td>
      </tr>
      <tr>
        <td align="right" valign="top" scope="row"><strong>UPI</strong></td>
        <td align="center" valign="top" scope="row">:</td>
        <td align="left" valign="top"><input name="DATA_PUB_09" type="text" id="DATA_PUB_09" value="$!DATA_PUB_09" size="30" maxlength="16" /></td>
        <td align="center" valign="top" scope="row">&nbsp;</td>
        <td align="right" valign="top" scope="row"><strong>BearingCal </strong></td>
        <td align="center" valign="top" scope="row">:</td>
        <td align="left" valign="top"><input name="DATA_PUB_22" type="text" id="DATA_PUB_22" value="$!DATA_PUB_22" size="20" maxlength="9" /></td>
      </tr>
      <tr>
        <td align="right" valign="top" scope="row"><strong>PUQTKey</strong></td>
        <td align="center" valign="top" scope="row">:</td>
        <td align="left" valign="top"><input name="DATA_PUB_10" type="text" id="DATA_PUB_10" value="$!DATA_PUB_10" size="30" maxlength="35" /></td>
        <td align="center" valign="top" scope="row">&nbsp;</td>
        <td align="right" valign="top" scope="row"><strong>DistanceCal </strong></td>
        <td align="center" valign="top" scope="row">:</td>
        <td align="left" valign="top"><input name="DATA_PUB_23" type="text" id="DATA_PUB_23" value="$!DATA_PUB_23" size="20" maxlength="10" /></td>
      </tr>
      <tr>
        <td align="right" valign="top" scope="row"><strong>MarkDescFrom </strong></td>
        <td align="center" valign="top" scope="row">:</td>
        <td align="left" valign="top">$!SOC_MARKDESC_FROM</td>
        <td align="center" valign="top" scope="row">&nbsp;</td>
        <td align="right" valign="top" scope="row"><strong>Class </strong></td>
        <td align="center" valign="top" scope="row">:</td>
        <td align="left" valign="top">
          $!SOC_CLASS
        </td>
      </tr>
      <tr>
        <td align="right" valign="top" scope="row"><strong>SerialFrom</strong></td>
        <td align="center" valign="top" scope="row">:</td>
        <td align="left" valign="top"><input name="DATA_PUB_12" type="text" id="DATA_PUB_12" value="$!DATA_PUB_12" size="20" maxlength="10" /></td>
        <td align="center" valign="top" scope="row">&nbsp;</td>
        <td align="right" valign="top" scope="row"><strong>LineCode </strong></td>
        <td align="center" valign="top" scope="row">:</td>
        <td align="left" valign="top">
          $!SOC_LINECODE        </td>
      </tr>
      <tr>
        <td align="right" valign="top" scope="row"><strong>NorthFrom</strong></td>
        <td align="center" valign="top" scope="row">:</td>
        <td align="left" valign="top"><input name="DATA_PUB_13" type="text" id="DATA_PUB_13" value="$!DATA_PUB_13" size="20" maxlength="12" /></td>
        <td align="center" valign="top" scope="row">&nbsp;</td>
        <td align="right" valign="top" scope="row"><strong>LineType </strong></td>
        <td align="center" valign="top" scope="row">:</td>
        <td align="left" valign="top">
          $!SOC_LINETYPE        </td>
      </tr>
      <tr>
        <td colspan="7" align="center">&nbsp;</td>
      </tr>
      <tr>
        <td colspan="7" align="center">
          <input type="button" id="cmdPUBAdd" name="cmdPUBAdd" value="Simpan Data PUB" onclick="savePUBData();" />&nbsp;
          <input type="button" id="cmdPUBCloseFieldsetData" name="cmdPUBCloseFieldsetData" value="Tutup Fieldset Data PUB" onclick="closePUBData();" />
        </td>
      </tr>
    </table>
  </fieldset>
  <br />
#end  
  <fieldset>
    <legend><strong>SENARAI DATA PUB</strong></legend>
    <input type="button" id="cmdJanaPUB" name="cmdJanaPUB" value="Jana Fail PUB" onclick="generatePUBFile();" />
    <table width="100%">
      <tr class="table_header">
        <td width="4%" align="center" valign="top">No</td>
        <td width="16%" align="center" valign="top">PUNo</td>
        <td width="16%" align="center" valign="top">PTNo</td>
        <td width="16%" align="center" valign="top">Lot</td>
        <td width="16%" align="center" valign="top">QTNo</td>
        <td width="16%" align="center" valign="top">UPI</td>
        <td width="16%" align="center" valign="top">PUQTKey</td>
      </tr>
#set ($l = '')
#foreach ($l in $ListDataPUB)
	#if ($l.No == '') 
    	#set ($row = 'row1')
    #elseif ($l.No % 2 != 0)
    	#set ($row = 'row1')
    #else 
    	#set ($row = 'row2')
    #end
      <tr>
    #if ($l.No != '')       
        <td class="$row" width="4%" align="center" valign="top">$l.No</td>
        <td class="$row" width="16%" align="center" valign="top"><a href="javascript:getPUBData('$l.ID_JUPEMPUB')" class="col_blue">$l.PUNo</a></td>
        <td class="$row" width="16%" align="center" valign="top"><a href="javascript:getPUBData('$l.ID_JUPEMPUB')" class="col_blue">$l.PTNo</a></td>
        <td class="$row" width="16%" align="center" valign="top">$l.Lot</td>
        <td class="$row" width="16%" align="center" valign="top">$l.QTNo</td>
        <td class="$row" width="16%" align="center" valign="top">$l.UPI</td>
        <td class="$row" width="16%" align="center" valign="top">$l.PUQTKey</td>
	#else
        <td class="$row" colspan="7" align="center" valign="top">Tiada Rekod</td>
    #end        
      </tr>
#end
      <tr>
        <td colspan="7">&nbsp;</td>
      </tr>
    </table>
  </fieldset>
  <br />
</fieldset>
<br />
<fieldset>
  <legend><strong>FAIL PUD</strong></legend>
  <input type="button" id="cmdPUDTambah" name="cmdPUDTambah" value="Tambah Data Fail PUD" onclick="addRowPUD();" /><br /><br />
#if ($viewPUDField == 'true')  
  <fieldset>
    <legend><strong>DATA PUD</strong></legend>
    <table width="100%" align="center">
      <tr>
        <td width="15%" align="right" valign="top" scope="row"><strong>Negeri</strong></td>
        <td width="2%" align="center" valign="top" scope="row">:</td>
        <td width="31%" align="left" valign="top">$!SOC_NEGERI_PUD</td>
        <td width="4%" align="center" valign="top" scope="row">&nbsp;</td>
        <td align="right" valign="top" scope="row"><strong>Area</strong></td>
        <td align="center" valign="top" scope="row">:</td>
        <td align="left" valign="top"><input name="DATA_PUD_08" type="text" id="DATA_PUD_08" value="$!DATA_PUD_08" size="30" maxlength="16" /></td>
      </tr>
      <tr>
        <td align="right" valign="top" scope="row"><strong>Daerah</strong></td>
        <td align="center" valign="top" scope="row">:</td>
        <td align="left" valign="top">$!SOC_DAERAH_PUD</td>
        <td align="center" valign="top" scope="row">&nbsp;</td>
        <td align="right" valign="top" scope="row"><strong>Unit</strong></td>
        <td align="center" valign="top" scope="row">:</td>
        <td align="left" valign="top"><input name="DATA_PUD_09" type="text" id="DATA_PUD_09" value="$!DATA_PUD_09" size="10" maxlength="2" /></td>
      </tr>
      <tr>
        <td align="right" valign="top" scope="row"><strong>Mukim</strong></td>
        <td align="center" valign="top" scope="row">:</td>
        <td align="left" valign="top">$!SOC_MUKIM_PUD</td>
        <td align="center" valign="top" scope="row">&nbsp;</td>
        <td align="right" valign="top" scope="row"><strong>SvyFees</strong></td>
        <td align="center" valign="top" scope="row">:</td>
        <td align="left" valign="top"><input name="DATA_PUD_10" type="text" id="DATA_PUD_10" value="$!DATA_PUD_10" size="20" maxlength="10" /></td>
      </tr>
      <tr>
        <td align="right" valign="top" scope="row"><strong>Seksyen</strong></td>
        <td align="center" valign="top" scope="row">:</td>
        <td align="left" valign="top"><input name="DATA_PUD_04" type="text" id="DATA_PUD_04" value="$!DATA_PUD_04" size="10" maxlength="3" /></td>
        <td align="center" valign="top" scope="row">&nbsp;</td>
        <td align="right" valign="top" scope="row"><strong>LandUseCode</strong></td>
        <td align="center" valign="top" scope="row">:</td>
        <td align="left" valign="top">$!SOC_LANDTITLE</td>
      </tr>
      <tr>
        <td align="right" valign="top" scope="row"><strong>PUNo</strong></td>
        <td align="center" valign="top" scope="row">:</td>
        <td align="left" valign="top"><input name="DATA_PUD_05" type="text" id="DATA_PUD_05" value="$!DATA_PUD_05" size="30" maxlength="15" /></td>
        <td align="center" valign="top" scope="row">&nbsp;</td>
        <td align="right" valign="top" scope="row"><strong>LandTitleCode</strong></td>
        <td align="center" valign="top" scope="row">:</td>
        <td align="left" valign="top">$!SOC_LANDUSE</td>
      </tr>
      <tr>
        <td align="right" valign="top" scope="row"><strong>LoNo</strong></td>
        <td align="center" valign="top" scope="row">:</td>
        <td align="left" valign="top"><input name="DATA_PUD_06" type="text" id="DATA_PUD_06" value="$!DATA_PUD_06" size="30" maxlength="30" onblur="generateQTKeyPUD();" /></td>
        <td align="center" valign="top" scope="row">&nbsp;</td>
        <td align="right" valign="top" scope="row"><strong>SijilAkaunNo</strong></td>
        <td align="center" valign="top" scope="row">:</td>
        <td align="left" valign="top"><input name="DATA_PUD_13" type="text" id="DATA_PUD_13" value="$!DATA_PUD_13" size="20" maxlength="15" /></td>
      </tr>
      <tr>
        <td align="right" valign="top" scope="row"><strong>FileNo</strong></td>
        <td align="center" valign="top" scope="row">:</td>
        <td align="left" valign="top"><input name="DATA_PUD_07" type="text" id="DATA_PUD_07" value="$!DATA_PUD_07" size="30" maxlength="30" onblur="generateUPIPUD();" /></td>
        <td align="center" valign="top" scope="row">&nbsp;</td>
        <td align="right" valign="top" scope="row"><strong>Remarks</strong></td>
        <td align="center" valign="top" scope="row">:</td>
        <td align="left" valign="top"><input name="DATA_PUD_14" type="text" id="DATA_PUD_14" value="$!DATA_PUD_14" size="20" maxlength="12" /></td>
      </tr>
      <tr>
        <td colspan="7" align="center">&nbsp;</td>
      </tr>
      <tr>
        <td colspan="7" align="center">
          <input type="button" id="cmdPUDAdd" name="cmdPUDAdd" value="Simpan Data PUD" onclick="savePUDData();" />&nbsp;
          <input type="button" id="cmdPUDCloseFieldsetData" name="cmdPUDCloseFieldsetData" value="Tutup Fieldset Data PUD" onclick="closePUDData();" />
        </td>
      </tr>
    </table>
  </fieldset>
  <br />
#end  
  <fieldset>
    <legend><strong>SENARAI DATA PUD</strong></legend>
    <input type="button" id="cmdJanaPUD" name="cmdJanaPUD" value="Jana Fail PUD" onclick="generatePUDFile();" />
    <table width="100%">
      <tr class="table_header">
        <td width="5%" align="center" valign="top">No</td>
        <td width="19%" align="center" valign="top">PUNo</td>
        <td width="19%" align="center" valign="top">LoNo</td>
        <td width="19%" align="center" valign="top">FileNo</td>
        <td width="19%" align="center" valign="top">LandUseCode</td>
        <td width="19%" align="center" valign="top">LandTitleCode</td>
      </tr>
#set ($l = '')
#foreach ($l in $ListDataPUD)
	#if ($l.No == '') 
    	#set ($row = 'row1')
    #elseif ($l.No % 2 != 0)
    	#set ($row = 'row1')
    #else 
    	#set ($row = 'row2')
    #end
      <tr>
    #if ($l.No != '')       
        <td class="$row" width="4%" align="center" valign="top">$l.No</td>
        <td class="$row" width="16%" align="center" valign="top"><a href="javascript:getPUDData('$l.ID_JUPEMPUD')" class="col_blue">$l.PUNo</a></td>
        <td class="$row" width="16%" align="center" valign="top"><a href="javascript:getPUDData('$l.ID_JUPEMPUD')" class="col_blue">$l.LoNo</a></td>
        <td class="$row" width="16%" align="center" valign="top">$l.FileNo</td>
        <td class="$row" width="16%" align="center" valign="top">$l.LandUseCode</td>
        <td class="$row" width="16%" align="center" valign="top">$l.LandTitleCode</td>
	#else
        <td class="$row" colspan="6" align="center" valign="top">Tiada Rekod</td>
    #end        
      </tr>
#end
      <tr>
        <td colspan="6">&nbsp;</td>
      </tr>
    </table>
  </fieldset>
  <br />
</fieldset>
<br />
<fieldset>
  <legend><strong>FAIL PUL</strong></legend>
  <input type="button" id="cmdPULTambah" name="cmdPULTambah" value="Tambah Data Fail PUL" onclick="addRowPUL();" /><br /><br />
#if ($viewPULField == 'true')  
  <fieldset>
    <legend><strong>DATA PUL</strong></legend>
    <table width="100%" align="center">
      <tr>
        <td width="15%" align="right" valign="top" scope="row"><strong>Negeri</strong></td>
        <td width="2%" align="center" valign="top" scope="row">:</td>
        <td width="31%" align="left" valign="top">$!SOC_NEGERI_PUL</td>
        <td width="4%" align="center" valign="top" scope="row">&nbsp;</td>
        <td align="right" valign="top" scope="row"><strong>QTNo</strong></td>
        <td align="center" valign="top" scope="row">:</td>
        <td align="left" valign="top"><input name="DATA_PUL_08" type="text" id="DATA_PUL_08" value="$!DATA_PUL_08" size="30" maxlength="16" /></td>
      </tr>
      <tr>
        <td align="right" valign="top" scope="row"><strong>Daerah</strong></td>
        <td align="center" valign="top" scope="row">:</td>
        <td align="left" valign="top">$!SOC_DAERAH_PUL</td>
        <td align="center" valign="top" scope="row">&nbsp;</td>
        <td align="right" valign="top" scope="row"><strong>UPI</strong></td>
        <td align="center" valign="top" scope="row">:</td>
        <td align="left" valign="top"><input name="DATA_PUL_09" type="text" id="DATA_PUL_09" value="$!DATA_PUL_09" size="30" maxlength="16" /></td>
      </tr>
      <tr>
        <td align="right" valign="top" scope="row"><strong>Mukim</strong></td>
        <td align="center" valign="top" scope="row">:</td>
        <td align="left" valign="top">$!SOC_MUKIM_PUL</td>
        <td align="center" valign="top" scope="row">&nbsp;</td>
        <td align="right" valign="top" scope="row"><strong>PUQTKey</strong></td>
        <td align="center" valign="top" scope="row">:</td>
        <td align="left" valign="top"><input name="DATA_PUL_10" type="text" id="DATA_PUL_10" value="$!DATA_PUL_10" size="30" maxlength="35" /></td>
      </tr>
      <tr>
        <td align="right" valign="top" scope="row"><strong>Seksyen</strong></td>
        <td align="center" valign="top" scope="row">:</td>
        <td align="left" valign="top"><input name="DATA_PUL_04" type="text" id="DATA_PUL_04" value="$!DATA_PUL_04" size="10" maxlength="3" /></td>
        <td align="center" valign="top" scope="row">&nbsp;</td>
        <td align="right" valign="top" scope="row"><strong>Unit</strong></td>
        <td align="center" valign="top" scope="row">:</td>
        <td align="left" valign="top"><input name="DATA_PUL_11" type="text" id="DATA_PUL_11" value="$!DATA_PUL_11" size="10" maxlength="2" /></td>
      </tr>
      <tr>
        <td align="right" valign="top" scope="row"><strong>PUNo</strong></td>
        <td align="center" valign="top" scope="row">:</td>
        <td align="left" valign="top"><input name="DATA_PUL_05" type="text" id="DATA_PUL_05" value="$!DATA_PUL_05" size="30" maxlength="15" /></td>
        <td align="center" valign="top" scope="row">&nbsp;</td>
        <td align="right" valign="top" scope="row"><strong>ApArea</strong></td>
        <td align="center" valign="top" scope="row">:</td>
        <td align="left" valign="top"><input name="DATA_PUL_12" type="text" id="DATA_PUL_12" value="$!DATA_PUL_12" size="20" maxlength="16" /></td>
      </tr>
      <tr>
        <td align="right" valign="top" scope="row"><strong>PTNo</strong></td>
        <td align="center" valign="top" scope="row">:</td>
        <td align="left" valign="top"><input name="DATA_PUL_06" type="text" id="DATA_PUL_06" value="$!DATA_PUL_06" size="20" maxlength="8" onblur="generateQTKeyPUL();" /></td>
        <td align="center" valign="top" scope="row">&nbsp;</td>
        <td align="right" valign="top" scope="row"><strong>AreaCal</strong></td>
        <td align="center" valign="top" scope="row">:</td>
        <td align="left" valign="top"><input name="DATA_PUL_13" type="text" id="DATA_PUL_13" value="$!DATA_PUL_13" size="20" maxlength="16" /></td>
      </tr>
      <tr>
        <td align="right" valign="top" scope="row"><strong>Lot</strong></td>
        <td align="center" valign="top" scope="row">:</td>
        <td align="left" valign="top"><input name="DATA_PUL_07" type="text" id="DATA_PUL_07" value="$!DATA_PUL_07" size="20" maxlength="7" onblur="generateUPIPUL();" /></td>
        <td align="center" valign="top" scope="row">&nbsp;</td>
        <td align="right" valign="top" scope="row">&nbsp;</td>
        <td align="center" valign="top" scope="row">&nbsp;</td>
        <td align="left" valign="top">&nbsp;</td>
      </tr>
      <tr>
        <td colspan="7" align="center">&nbsp;</td>
      </tr>
      <tr>
        <td colspan="7" align="center">
          <input type="button" id="cmdPULAdd" name="cmdPULAdd" value="Simpan Data PUL" onclick="savePULData();" />&nbsp;
          <input type="button" id="cmdPULCloseFieldsetData" name="cmdPULCloseFieldsetData" value="Tutup Fieldset Data PUL" onclick="closePULData();" />
        </td>
      </tr>
    </table>
  </fieldset>
  <br />
#end  
  <fieldset>
    <legend><strong>SENARAI DATA PUL</strong></legend>
    <input type="button" id="cmdJanaPUL" name="cmdJanaPUL" value="Jana Fail PUL" onclick="generatePULFile();" />
    <table width="100%">
      <tr class="table_header">
        <td width="5%" align="center" valign="top">No</td>
        <td width="19%" align="center" valign="top">PUNo</td>
        <td width="19%" align="center" valign="top">PTNo</td>
        <td width="19%" align="center" valign="top">Lot</td>
        <td width="19%" align="center" valign="top">QTNo</td>
        <td width="19%" align="center" valign="top">UPI</td>
      </tr>
#set ($l = '')
#foreach ($l in $ListDataPUL)
	#if ($l.No == '') 
    	#set ($row = 'row1')
    #elseif ($l.No % 2 != 0)
    	#set ($row = 'row1')
    #else 
    	#set ($row = 'row2')
    #end
      <tr>
    #if ($l.No != '')       
        <td class="$row" width="4%" align="center" valign="top">$l.No</td>
        <td class="$row" width="16%" align="center" valign="top"><a href="javascript:getPULData('$l.ID_JUPEMPUL')" class="col_blue">$l.PUNo</a></td>
        <td class="$row" width="16%" align="center" valign="top"><a href="javascript:getPULData('$l.ID_JUPEMPUL')" class="col_blue">$l.PTNo</a></td>
        <td class="$row" width="16%" align="center" valign="top">$l.Lot</td>
        <td class="$row" width="16%" align="center" valign="top">$l.QTNo</td>
        <td class="$row" width="16%" align="center" valign="top">$l.UPI</td>
	#else
        <td class="$row" colspan="6" align="center" valign="top">Tiada Rekod</td>
    #end        
      </tr>
#end
      <tr>
        <td colspan="6">&nbsp;</td>
      </tr>
    </table>
  </fieldset>
  <br />
</fieldset>
<br />
<!--<fieldset>
  <legend><strong>LAMPIRAN</strong></legend>
    <table width="100%">
      <tr>
        <td width="40%" align="right" valign="top" scope="row">Fail untuk Dilampirkan</td>
        <td width="1%" align="center" valign="top" scope="row">:</td>
        <td width="59%" align="left" valign="top"><input type="file" id="FILE_NAME" name="FILE_NAME" /></td>
      </tr>
      <tr>
        <td colspan="3" align="center" valign="top" scope="row"><input type="button" id="FILE_UPLOAD" name="FILE_UPLOAD" value="Muat Naik" onclick="javascript:doUpload();" /></td>
      </tr>
    </table>
    <br />
    <br />
    <table width="100%">
      <tr>
        <td width="40%" align="right" valign="top" scope="row">Fail untuk Dilampirkan</td>
        <td width="1%" align="center" valign="top" scope="row">:</td>
        <td width="59%" align="left" valign="top"><input type="file" id="FILE_NAME" name="FILE_NAME" /></td>
      </tr>
      <tr>
        <td colspan="3" align="center" valign="top" scope="row"><input type="button" id="FILE_UPLOAD" name="FILE_UPLOAD" value="Muat Naik" /></td>
      </tr>
    </table>
</fieldset>
<br />-->
<fieldset>
  <legend><strong>E-MAIL</strong></legend>
  <table width="100%">
    <tr>
      <td width="40%" align="right" valign="top"><strong>ALAMAT E-MAIL</strong></td>
      <td width="1%" align="center" valign="top">:</td>
      <td width="59%" align="left" valign="top"><input name="EMAIL_ADDR" type="text" id="EMAIL_ADDR" value="$!EMAIL_ADDR" size="50" maxlength="255" /></td>
    </tr>
    <tr>
      <td align="center" valign="top">&nbsp;</td>
      <td align="center" valign="top">&nbsp;</td>
      <td align="left" valign="top"><input type="button" id="cmdEmail" name="cmdEmail" value="Hantar E-mail berserta Lampiran" onclick="emailJUPEM();" /></td>
    </tr>
  </table>  
</fieldset>
<div align="center">
  <input type="button" id="cmdKembali" name="cmdKembali" value="Kembali" onclick="backJUPEM();" />&nbsp;
</div>
<input type="hidden" id="ID_PERMOHONAN" name="ID_PERMOHONAN" value="$!ID_PERMOHONAN" />
<input type="hidden" id="ID_HAKMILIK" name="ID_HAKMILIK" value="$!ID_HAKMILIK" />
<input type="hidden" id="ID_JUPEMPUB" name="ID_JUPEMPUB" value="$!ID_JUPEMPUB" />
<input type="hidden" id="ID_JUPEMPUD" name="ID_JUPEMPUD" value="$!ID_JUPEMPUD" />
<input type="hidden" id="ID_JUPEMPUL" name="ID_JUPEMPUL" value="$!ID_JUPEMPUL" />
<input type="hidden" id="action2" name="action2" />
<input type="hidden" id="mode" name="mode" />
<script type="text/javascript">
  function generateUPIPUB() {
      var NEGERI = document.getElementById('DATA_PUB_01').value;
      var DAERAH = document.getElementById('DATA_PUB_02').value;
      var MUKIM = document.getElementById('DATA_PUB_03').value;
      var SEKSYEN = document.getElementById('DATA_PUB_04').value;
      var LOT = document.getElementById('DATA_PUB_07').value;
      document.getElementById('DATA_PUB_09').value = NEGERI + DAERAH + MUKIM + SEKSYEN + LOT;
  }
  function generateQTKeyPUB() {
      var NEGERI = document.getElementById('DATA_PUB_01').value;
      var DAERAH = document.getElementById('DATA_PUB_02').value;
      var MUKIM = document.getElementById('DATA_PUB_03').value;
      var SEKSYEN = document.getElementById('DATA_PUB_04').value;
      var PUNO = document.getElementById('DATA_PUB_05').value;
      var PTNO = document.getElementById('DATA_PUB_06').value;
      document.getElementById('DATA_PUB_10').value = NEGERI + DAERAH + MUKIM + SEKSYEN + PUNO + "*" + PTNO;
  }
  function generateUPIPUD() {
      var NEGERI = document.getElementById('DATA_PUD_01').value;
      var DAERAH = document.getElementById('DATA_PUD_02').value;
      var MUKIM = document.getElementById('DATA_PUD_03').value;
      var SEKSYEN = document.getElementById('DATA_PUD_04').value;
      var LOT = document.getElementById('DATA_PUD_07').value;
      document.getElementById('DATA_PUD_09').value = NEGERI + DAERAH + MUKIM + SEKSYEN + LOT;
  }
  function generateQTKeyPUD() {
      var NEGERI = document.getElementById('DATA_PUD_01').value;
      var DAERAH = document.getElementById('DATA_PUD_02').value;
      var MUKIM = document.getElementById('DATA_PUD_03').value;
      var SEKSYEN = document.getElementById('DATA_PUD_04').value;
      var PUNO = document.getElementById('DATA_PUD_05').value;
      var PTNO = document.getElementById('DATA_PUD_06').value;
      document.getElementById('DATA_PUD_10').value = NEGERI + DAERAH + MUKIM + SEKSYEN + PUNO + "*" + PTNO;
  }
  function generateUPIPUL() {
      var NEGERI = document.getElementById('DATA_PUL_01').value;
      var DAERAH = document.getElementById('DATA_PUL_02').value;
      var MUKIM = document.getElementById('DATA_PUL_03').value;
      var SEKSYEN = document.getElementById('DATA_PUL_04').value;
      var LOT = document.getElementById('DATA_PUL_07').value;
      document.getElementById('DATA_PUL_09').value = NEGERI + DAERAH + MUKIM + SEKSYEN + LOT;
  }
  function generateQTKeyPUL() {
      var NEGERI = document.getElementById('DATA_PUL_01').value;
      var DAERAH = document.getElementById('DATA_PUL_02').value;
      var MUKIM = document.getElementById('DATA_PUL_03').value;
      var SEKSYEN = document.getElementById('DATA_PUL_04').value;
      var PUNO = document.getElementById('DATA_PUL_05').value;
      var PTNO = document.getElementById('DATA_PUL_06').value;
      document.getElementById('DATA_PUL_10').value = NEGERI + DAERAH + MUKIM + SEKSYEN + PUNO + "*" + PTNO;
  }
  function doChangeSOC(SOC_ID) {
      document.${formName}.action = "?_portal_module=ekptg.view.integrasi.FrmViewJUPEM";
      document.${formName}.mode.value = SOC_ID;
      document.${formName}.method = "POST";
	  doAjaxCall${formName}("");
  }
  function addRowPUB() {
      document.${formName}.action = "?_portal_module=ekptg.view.integrasi.FrmViewJUPEM";
      document.${formName}.mode.value = "addRowPUB";
      document.${formName}.method = "POST";
      document.${formName}.submit();
  }
  function savePUBData() {
      if (!window.confirm("Adakah anda pasti?")) return;
      document.${formName}.action = "?_portal_module=ekptg.view.integrasi.FrmViewJUPEM";
      document.${formName}.mode.value = "savePUBData";
      document.${formName}.method = "POST";
      document.${formName}.submit();
  }
  function addRowPUD() {
      document.${formName}.action = "?_portal_module=ekptg.view.integrasi.FrmViewJUPEM";
      document.${formName}.mode.value = "addRowPUD";
      document.${formName}.method = "POST";
      document.${formName}.submit();
  }
  function savePUDData() {
      if (!window.confirm("Adakah anda pasti?")) return;
      document.${formName}.action = "?_portal_module=ekptg.view.integrasi.FrmViewJUPEM";
      document.${formName}.mode.value = "savePUDData";
      document.${formName}.method = "POST";
      document.${formName}.submit();
  }
  function addRowPUL() {
      document.${formName}.action = "?_portal_module=ekptg.view.integrasi.FrmViewJUPEM";
      document.${formName}.mode.value = "addRowPUL";
      document.${formName}.method = "POST";
      document.${formName}.submit();
  }
  function savePULData() {
      if (!window.confirm("Adakah anda pasti?")) return;
      document.${formName}.action = "?_portal_module=ekptg.view.integrasi.FrmViewJUPEM";
      document.${formName}.mode.value = "savePULData";
      document.${formName}.method = "POST";
      document.${formName}.submit();
  }
  function getPUBData(ID_JUPEMPUB) {
      document.${formName}.action = "?_portal_module=ekptg.view.integrasi.FrmViewJUPEM";
      document.${formName}.mode.value = "getPUBData";
      document.${formName}.ID_JUPEMPUB.value = ID_JUPEMPUB;
      document.${formName}.method = "POST";
      document.${formName}.submit();
  }
  function getPUDData(ID_JUPEMPUD) {
      document.${formName}.action = "?_portal_module=ekptg.view.integrasi.FrmViewJUPEM";
      document.${formName}.mode.value = "getPUDData";
      document.${formName}.ID_JUPEMPUD.value = ID_JUPEMPUD;
      document.${formName}.method = "POST";
      document.${formName}.submit();
  }
  function getPULData(ID_JUPEMPUL) {
      document.${formName}.action = "?_portal_module=ekptg.view.integrasi.FrmViewJUPEM";
      document.${formName}.mode.value = "getPULData";
      document.${formName}.ID_JUPEMPUL.value = ID_JUPEMPUL;
      document.${formName}.method = "POST";
      document.${formName}.submit();
  }
  function generatePUBFile() {
      var url = "../servlet/ekptg.view.integrasi.DisplayText?&action2=generatePUB&ID_PERMOHONAN=$!ID_PERMOHONAN&ID_HAKMILIK=$!ID_HAKMILIK";
      var hWnd = window.open(url, 'printuser', 'width=500, height=300,  resizable=yes, scrollbars=yes, copyhistory=yes, location=no, directories=no, status=yes, toolbar=no, menubar=no');
      if ((document.window != null) && (!hWnd.opener))
      hWnd.opener = document.window;
      if (hWnd.focus != null) hWnd.focus();
  }
  function generatePUDFile() {
      var url = "../servlet/ekptg.view.integrasi.DisplayText?&action2=generatePUD&ID_PERMOHONAN=$!ID_PERMOHONAN&ID_HAKMILIK=$!ID_HAKMILIK";
      var hWnd = window.open(url, 'printuser', 'width=500, height=300,  resizable=yes, scrollbars=yes, copyhistory=yes, location=no, directories=no, status=yes, toolbar=no, menubar=no');
      if ((document.window != null) && (!hWnd.opener))
      hWnd.opener = document.window;
      if (hWnd.focus != null) hWnd.focus();
  }
  function generatePULFile() {
      var url = "../servlet/ekptg.view.integrasi.DisplayText?&action2=generatePUL&ID_PERMOHONAN=$!ID_PERMOHONAN&ID_HAKMILIK=$!ID_HAKMILIK";
      var hWnd = window.open(url, 'printuser', 'width=500, height=300,  resizable=yes, scrollbars=yes, copyhistory=yes, location=no, directories=no, status=yes, toolbar=no, menubar=no');
      if ((document.window != null) && (!hWnd.opener))
      hWnd.opener = document.window;
      if (hWnd.focus != null) hWnd.focus();
  }
  function emailJUPEM() {
      if (document.getElementById('EMAIL_ADDR').value == '') {
	      alert('Sila isikan alamat e-mel penerima.');
		  document.getElementById('EMAIL_ADDR').focus();
		  return false;
	  }
      document.${formName}.action = "?_portal_module=ekptg.view.integrasi.FrmViewJUPEM";
      document.${formName}.mode.value = "emailJUPEM";
      document.${formName}.method = "POST";
      document.${formName}.submit();
  }
  function closePUBData() {
      document.${formName}.action = "?_portal_module=ekptg.view.integrasi.FrmViewJUPEM";
      document.${formName}.mode.value = "";
      document.${formName}.method = "POST";
      document.${formName}.submit();
  }
  function closePUDData() {
      document.${formName}.action = "?_portal_module=ekptg.view.integrasi.FrmViewJUPEM";
      document.${formName}.mode.value = "";
      document.${formName}.method = "POST";
      document.${formName}.submit();
  }
  function closePULData() {
      document.${formName}.action = "?_portal_module=ekptg.view.integrasi.FrmViewJUPEM";
      document.${formName}.mode.value = "";
      document.${formName}.method = "POST";
      document.${formName}.submit();
  }
</script>
#end