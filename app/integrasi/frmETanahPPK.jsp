#if ($action2 == 'checkHM')
	#set ($selHM = 'selected="selected"')
#elseif ($action2 == 'sendPSK')
	#set ($selPSK = 'selected="selected"')
#end
<fieldset>
  <legend><strong>Maklumat Hakmilik</strong></legend>
  <table width="70%" align="center" cellspacing="0" cellpadding="2" style="border:0px solid #000">
    <tr>
      <td width="25%" align="right"><strong>No Fail</strong></td>
      <td width="1%" align="center">:</td>
      <td width="74%" align="left">$MH_NoFail</td>
    </tr>
    <tr>
      <td width="25%" align="right"><strong>No Permohonan</strong></td>
      <td width="1%" align="center">:</td>
      <td width="74%" align="left">$MH_NoPermohonan</td>
    </tr>
    <tr>
      <td width="25%" align="right"><strong>No Hakmilik</strong></td>
      <td width="1%" align="center">:</td>
      <td width="74%" align="left">$MH_NoHakmilik</td>
    </tr>
#if ($isPPT != '1')
    <tr>
      <td width="25%" align="right"><strong>Nama Si Mati</strong></td>
      <td width="1%" align="center">:</td>
      <td width="74%" align="left">$MH_NamaSiMati</td>
    </tr>
    <tr>
      <td width="25%" align="right"><strong>No KP Si Mati</strong></td>
      <td width="1%" align="center">:</td>
      <td width="74%" align="left">$MH_NoKPSiMati</td>
    </tr>
#end
  </table>
</fieldset>
<br />
#if ($isSMTUser == 'true')
<div style="margin-left:10px">
  <a id="lblWSeTanah" name="lblWSeTanah" onclick="toggleFieldsetWS();" style="color:#0000FF">Maklumat Web Service eTanah</a>
</div>
<br />
<table id="tblWSeTanah" style="visibility:collapse; width:100%" align="center">
  <tr>
    <td align="center">
      <fieldset>
        <legend><strong>Maklumat Web Service</strong></legend>
        <table width="70%" align="center" cellspacing="0" cellpadding="2" style="border:0px solid #000">
          <tr>
            <td width="25%" align="right"><strong>Username</strong></td>
            <td width="1%" align="center">:</td>
            <td width="74%" align="left"><input name="WS_USER" type="text" id="WS_USER" value="$!WS_USER" size="50" maxlength="255" /></td>
          </tr>
          <tr>
            <td width="25%" align="right"><strong>Password</strong></td>
            <td width="1%" align="center">:</td>
            <td width="74%" align="left"><input name="WS_PASS" type="text" id="WS_PASS" value="$!WS_PASS" size="50" maxlength="255" /></td>
          </tr>
          <tr>
            <td width="25%" align="right"><strong>Web Service URL</strong></td>
            <td width="1%" align="center">:</td>
            <td width="74%" align="left"><input name="WS_URL" type="text" id="WS_URL" value="$!WS_URL" size="50" maxlength="255" /></td>
          </tr>
          <tr>
            <td width="25%" align="right"><strong>Method</strong></td>
            <td width="1%" align="center">:</td>
            <td width="74%" align="left">
              <select id="action2" name="action2" style="width:auto">              
                <option value="checkHM" $!selHM>checkHM</option>
                <option value="sendPSK" $!selPSK>sendPSK</option>
              </select>
            </td>
          </tr>
          <tr>
            <td width="25%" align="right"><strong>ID_HAKMILIK</strong></td>
            <td width="1%" align="center">:</td>
            <td width="74%" align="left"><input name="ID_HAKMILIK" type="text" id="ID_HAKMILIK" value="$!ID_HAKMILIK" size="50" maxlength="255" /></td>
          </tr>
          <tr>
            <td width="25%" align="right"><strong>ID Hakmilik eTanah</strong></td>
            <td width="1%" align="center">:</td>
            <td width="74%" align="left"><input name="WS_IDHM" type="text" id="WS_IDHM" value="$!WS_IDHM" size="50" maxlength="255" /></td>
          </tr>
          <tr>
            <td width="25%">&nbsp;</td>
            <td width="1%">&nbsp;</td>
            <td width="74%"><input type="submit" value="SEND" /></td>
          </tr>
         </table>
      </fieldset>
      <br />
    </td>
  </tr>
</table>
#end
#if ($action2 == 'checkHM')
<fieldset>
  <legend><strong>Capaian Maklumat Hakmilik eTanah</strong></legend>
	#if ($HM_FLAG == 'invalid')
  <div class="error">
    Tiada maklumat bagi nombor hakmilik yang diberikan.<br />Kod Ralat: $HM_DESC
  </div>
	#elseif ($HM_FLAG == 'error')
  <div class="error">
    Sistem eTanah tidak dapat dicapai buat masa ini. Sila cuba sebentar lagi.<br />Kod Ralat: $HM_DESC
  </div>
	#end
  <table width="70%" align="center" cellspacing="0" cellpadding="2" style="border:0px solid #000">
    <tr>
      <td width="25%" align="right" valign="top"><strong>Kod Bandar / Pekan / Mukim</strong></td>
      <td width="1%" align="center" valign="top">:</td>
      <td width="74%" align="left" valign="top">$!HM_KODBANDARPEKANMUKIM</td>
    </tr>
    <tr>
      <td width="25%" align="right" valign="top"><strong>Kod Daerah</strong></td>
      <td width="1%" align="center" valign="top">:</td>
      <td width="74%" align="left" valign="top">$!HM_KODDAERAH</td>
    </tr>
    <tr>
      <td width="25%" align="right" valign="top"><strong>Kod Lot</strong></td>
      <td width="1%" align="center" valign="top">:</td>
      <td width="74%" align="left" valign="top">$!HM_KODLOT</td>
    </tr>
    <tr>
      <td width="25%" align="right" valign="top"><strong>No Hakmilik</strong></td>
      <td width="1%" align="center" valign="top">:</td>
      <td width="74%" align="left" valign="top">$!HM_NOHAKMILIK</td>
    </tr>
    <tr>
      <td width="25%" align="right" valign="top"><strong>No Lot</strong></td>
      <td width="1%" align="center" valign="top">:</td>
      <td width="74%" align="left" valign="top">$!HM_NOLOT</td>
    </tr>
  </table>
  <br  />
  <table width="70%" align="center" cellspacing="0" cellpadding="2" style="border:0px solid #000">
    <tr class="table_header">
      <td width="5%" scope="row" align="center"><strong>NO</strong></td>
      <td width="45%"><strong>NAMA PEMILIK</strong></td>
      <td width="20%" align="center"><strong>NO KP PEMILIK</strong></td>
      <td width="15%" align="center"><strong>SYER PENYEBUT</strong></td>
      <td width="15%" align="center"><strong>SYER PEMBILANG</strong></td>
    </tr>
#set ($list = '')
#foreach ($list in $ListPemilik)
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
      <td class="$row" valign="top">$list.NamaPemilik</td>
      <td class="$row" valign="top" align="center">$list.NoKPPemilik</td>
      <td class="$row" valign="top" align="center">$list.SyerPenyebut</td>
      <td class="$row" valign="top" align="center">$list.SyerPembilang</td>
    #else
      <td colspan="5" class="$row" style="text-align:center">Tiada Rekod</td>
    #end    </tr>
#end
	#if ($row == 'row2') 
    	#set ($row = 'row1')
    #else 
    	#set ($row = 'row2')
    #end
    <tr>
      <td colspan="3" align="right" valign="top" class="$row"><strong>Jumlah Syer Penyebut:</strong></td>
      <td class="$row" valign="top" align="center">$TotalSyerPenyebut</td>
      <td class="$row" valign="top" align="center">$TotalSyerPembilang</td>
    </tr>
    <tr>
      <td colspan="5">&nbsp;</td>
    </tr>
  </table>
</fieldset>
#else
<fieldset>
  <legend><strong>Kemaskini Maklumat Pusaka</strong></legend>
#if ($HM_FLAG == 'valid')
  <div class="success">
    Maklumat pusaka telah berjaya dihantar ke sistem eTanah.
    <br /><br />
  </div>
#elseif ($HM_FLAG == 'invalid')
  <div class="error" style="font-weight:bold">
    Maklumat Pusaka tidak dapat dihantar ke sistem eTanah.<br />
    Kod Ralat: $HM_DESC
    <br /><br />
  </div>
#elseif ($nextAction == '')
  <div class="info">
    Sila isikan maklumat-maklumat lain yang akan dihantar ke sistem eTanah.
    <br /><br />
  </div>
#end
  <table width="70%" align="center" cellspacing="0" cellpadding="2" style="border:0px solid #000">
    <tr>
      <td width="25%" align="right" valign="top"><strong>No Fail JKPTG</strong></td>
      <td width="1%" align="center" valign="top">:</td>
      <td width="74%" align="left" valign="top"><input name="PSK_NOFAIL" type="text" id="PSK_NOFAIL" value="$!MH_NoFail" size="50" maxlength="255" /></td>
    </tr>
    <tr>
      <td align="right" valign="top">
        <strong onmouseover="showHoverInfo('infoNoMahkamah');" onmouseout="hideHoverInfo('infoNoMahkamah');">No Mahkamah</strong>
        <div id="infoNoMahkamah" style="display:none; position:absolute; border-style: solid; background-color: white; padding: 5px;">
          Nombor fail yang diberikan oleh mahkamah.
        </div>
      </td>
      <td align="center" valign="top">:</td>
      <td align="left" valign="top"><input name="PSK_NOMAHKAMAH" type="text" id="PSK_NOMAHKAMAH" value="$!PSK_NOMAHKAMAH" size="50" maxlength="255" /></td>
    </tr>
    <tr>
      <td align="right" valign="top">
        <strong onmouseover="showHoverInfo('infoTarikhMahkamah');" onmouseout="hideHoverInfo('infoTarikhMahkamah');">Tarikh Mahkamah</strong>
        <div id="infoTarikhMahkamah" style="display:none; position:absolute; border-style: solid; background-color: white; padding: 5px;">
          Tarikh permohonan Bicara Pusaka ini dibicarakan di mahkamah.
        </div>
      </td>
      <td align="center" valign="top">:</td>
      <td align="left" valign="top"><input name="PSK_TARIKHMAHKAMAH" type="text" id="PSK_TARIKHMAHKAMAH" value="$!PSK_TARIKHMAHKAMAH" size="15" maxlength="10" />
      &nbsp;<a href="javascript:displayDatePicker('PSK_TARIKHMAHKAMAH',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0" /></a></td>
    </tr>
    <tr>
      <td align="right" valign="top">
        <strong onmouseover="showHoverInfo('infoPejabatMahkamah');" onmouseout="hideHoverInfo('infoPejabatMahkamah');">Pejabat Mahkamah</strong>
        <div id="infoPejabatMahkamah" style="display:none; position:absolute; border-style: solid; background-color: white; padding: 5px;">
          Nama Pejabat / Mahkamah yang terlibat dengan perbicaraan Bicara Pusaka.
        </div>
      </td>
      <td align="center" valign="top">:</td>
      <td align="left" valign="top"><input name="PSK_PEJABATMAHKAMAH" type="text" id="PSK_PEJABATMAHKAMAH" value="$!PSK_PEJABATMAHKAMAH" size="50" maxlength="255" /></td>
    </tr>
    <tr>
      <td align="right" valign="top">
        <strong onmouseover="showHoverInfo('infoNoPerintah');" onmouseout="hideHoverInfo('infoNoPerintah');">No Perintah</strong>
        <div id="infoNoPerintah" style="display:none; position:absolute; border-style: solid; background-color: white; padding: 5px;">
          Nombor Perintah yang dikeluarkan oleh mahkamah.
        </div>
      </td>
      <td align="center" valign="top">:</td>
      <td align="left" valign="top"><input name="PSK_NOPERINTAH" type="text" id="PSK_NOPERINTAH" value="$!MH_NoFail" size="50" maxlength="255" /></td>
    </tr>
    <tr>
      <td align="right" valign="top"><strong>Jenis Perintah</strong></td>
      <td align="center" valign="top">:</td>
      <td align="left" valign="top">$SOC_JENISPERINTAH</td>
    </tr>
    <tr>
      <td colspan="3">&nbsp;</td>
    </tr>
  </table>
  <br />
  <table width="70%" cellspacing="0" cellpadding="2" style="border:0px solid #000">
    <tr>
      <td align="center" valign="top">
        Jumlah Pemilik Baru:
        <select name="PB_JUMLAH" style="width:auto" onchange="doChangeSOC('PB_JUMLAH')">
          <option value="1" $PB_JUM_1>1</option>
          <option value="2" $PB_JUM_2>2</option>
          <option value="3" $PB_JUM_3>3</option>
          <option value="4" $PB_JUM_4>4</option>
          <option value="5" $PB_JUM_5>5</option>
        </select>
      </td>
    </tr>
  </table>
  <table width="70%" align="center" cellspacing="0" cellpadding="2" style="border:0px solid #000">
    <tr>
      <td colspan="3" align="center" valign="top" class="table_header"><strong>Pemilik Baru 1</strong></td>
    </tr>
    <tr>
      <td width="25%" align="right" valign="top"><strong>Jenis Pengenalan</strong></td>
      <td width="1%" align="center" valign="top">:</td>
      <td width="74%" align="left" valign="top">$SOC_PB_JENISPENGENALAN </td>
    </tr>
    <tr>
      <td align="right" valign="top"><strong>No Pengenalan</strong></td>
      <td align="center" valign="top">:</td>
      <td align="left" valign="top"><input name="PSK_PB_NOPENGENALAN" type="text" id="PSK_PB_NOPENGENALAN" value="$!PSK_PB_NOPENGENALAN" size="20" maxlength="20" /></td>
    </tr>
    <tr>
      <td align="right" valign="top"><strong>Nama Pihak Berkepentingan</strong></td>
      <td align="center" valign="top">:</td>
      <td align="left" valign="top"><input name="PSK_PB_NAMAPB" type="text" id="PSK_PB_NAMAPB" value="$!PSK_PB_NAMAPB" size="50" maxlength="255" /></td>
    </tr>
    <tr>
      <td align="right" valign="top"><strong>Jenis Pihak Berkepentingan</strong></td>
      <td align="center" valign="top">:</td>
      <td align="left" valign="top">$SOC_PB_JENISPB</td>
    </tr>
    <tr>
      <td align="right" valign="top"><strong>Kumpulan Pihak Berkepentingan</strong></td>
      <td align="center" valign="top">:</td>
      <td align="left" valign="top"><input name="PSK_PB_KUMPPB" type="text" id="PSK_PB_KUMPPB" value="$!PSK_PB_KUMPPB" size="10" maxlength="2" /></td>
    </tr>
    <tr>
      <td align="right" valign="top"><strong>Syer Penyebut</strong></td>
      <td align="center" valign="top">:</td>
      <td align="left" valign="top"><input name="PSK_PB_SYERPENYEBUT" type="text" id="PSK_PB_SYERPENYEBUT" value="$!PSK_PB_SYERPENYEBUT" size="10" maxlength="14" /></td>
    </tr>
    <tr>
      <td align="right" valign="top"><strong>Syer Pembilang</strong></td>
      <td align="center" valign="top">:</td>
      <td align="left" valign="top"><input name="PSK_PB_SYERPEMBILANG" type="text" id="PSK_PB_SYERPEMBILANG" value="$!PSK_PB_SYERPEMBILANG" size="10" maxlength="14" /></td>
    </tr>
    <tr>
      <td align="right" valign="top"><strong>Kod Bangsa</strong></td>
      <td align="center" valign="top">:</td>
      <td align="left" valign="top">$SOC_PB_KODBANGSA</td>
    </tr>
    <tr>
      <td align="right" valign="top"><strong>Kod Jantina</strong></td>
      <td align="center" valign="top">:</td>
      <td align="left" valign="top">$SOC_PB_KODJANTINA</td>
    </tr>
    <tr>
      <td align="right" valign="top"><strong>Kod Warganegara</strong></td>
      <td align="center" valign="top">:</td>
      <td align="left" valign="top">$SOC_PB_KODWARGANEGARA</td>
    </tr>
    <tr>
      <td align="right" valign="top"><strong>Alamat 1 (Rumah)</strong></td>
      <td align="center" valign="top">:</td>
      <td align="left" valign="top"><input name="PSK_PB_ALAMATRUMAH1" type="text" id="PSK_PB_ALAMATRUMAH1" value="$!PSK_PB_ALAMATRUMAH1" size="50" maxlength="40" /></td>
    </tr>
    <tr>
      <td align="right" valign="top"><strong>Alamat 2 (Rumah)</strong></td>
      <td align="center" valign="top">:</td>
      <td align="left" valign="top"><input name="PSK_PB_ALAMATRUMAH2" type="text" id="PSK_PB_ALAMATRUMAH2" value="$!PSK_PB_ALAMATRUMAH2" size="50" maxlength="40" /></td>
    </tr>
    <tr>
      <td align="right" valign="top"><strong>Alamat 3 (Rumah)</strong></td>
      <td align="center" valign="top">:</td>
      <td align="left" valign="top"><input name="PSK_PB_ALAMATRUMAH3" type="text" id="PSK_PB_ALAMATRUMAH3" value="$!PSK_PB_ALAMATRUMAH3" size="50" maxlength="40" /></td>
    </tr>
    <tr>
      <td align="right" valign="top"><strong>Poskod (Rumah)</strong></td>
      <td align="center" valign="top">:</td>
      <td align="left" valign="top"><input name="PSK_PB_POSKODRUMAH" type="text" id="PSK_PB_POSKODRUMAH" value="$!PSK_PB_POSKODRUMAH" size="10" maxlength="5" /></td>
    </tr>
    <tr>
      <td align="right" valign="top"><strong>Kod Negeri (Rumah)</strong></td>
      <td align="center" valign="top">:</td>
      <td align="left" valign="top">$SOC_PB_KODNEGERIRUMAH</td>
    </tr>
    <tr>
      <td align="right" valign="top"><strong>Kod Bandar (Rumah)</strong></td>
      <td align="center" valign="top">:</td>
      <td align="left" valign="top">$SOC_PB_KODBANDARRUMAH</td>
    </tr>
    <tr>
      <td align="right" valign="top"><strong>Alamat 1 (Surat)</strong></td>
      <td align="center" valign="top">:</td>
      <td align="left" valign="top"><input name="PSK_PB_ALAMATSURAT1" type="text" id="PSK_PB_ALAMATSURAT1" value="$!PSK_PB_ALAMATSURAT1" size="50" maxlength="40" /></td>
    </tr>
    <tr>
      <td align="right" valign="top"><strong>Alamat 2 (Surat)</strong></td>
      <td align="center" valign="top">:</td>
      <td align="left" valign="top"><input name="PSK_PB_ALAMATSURAT2" type="text" id="PSK_PB_ALAMATSURAT2" value="$!PSK_PB_ALAMATSURAT2" size="50" maxlength="40" /></td>
    </tr>
    <tr>
      <td align="right" valign="top"><strong>Alamat 3 (Surat)</strong></td>
      <td align="center" valign="top">:</td>
      <td align="left" valign="top"><input name="PSK_PB_ALAMATSURAT3" type="text" id="PSK_PB_ALAMATSURAT3" value="$!PSK_PB_ALAMATSURAT3" size="50" maxlength="40" /></td>
    </tr>
    <tr>
      <td align="right" valign="top"><strong>Poskod (Surat)</strong></td>
      <td align="center" valign="top">:</td>
      <td align="left" valign="top"><input name="PSK_PB_POSKODSURAT" type="text" id="PSK_PB_POSKODSURAT" value="$!PSK_PB_POSKODSURAT" size="10" maxlength="5" /></td>
    </tr>
    <tr>
      <td align="right" valign="top"><strong>Kod Negeri (Surat)</strong></td>
      <td align="center" valign="top">&nbsp;</td>
      <td align="left" valign="top">$SOC_PB_KODNEGERISURAT</td>
    </tr>
    <tr>
      <td align="right" valign="top"><strong>Kod Bandar (Surat)</strong></td>
      <td align="center" valign="top">:</td>
      <td align="left" valign="top">$SOC_PB_KODBANDARSURAT</td>
    </tr>
    <tr>
      <td colspan="3">&nbsp;</td>
    </tr>
  </table>
  <br />
#if ($PB_COUNT > 1)
	#foreach ($PBC in $ListPB)
  <table width="70%" align="center" cellspacing="0" cellpadding="2" style="border:0px solid #000">
    <tr>
      <td colspan="3" align="center" valign="top" class="table_header"><strong>Pemilik Baru $PBC.No</strong></td>
    </tr>
    <tr>
      <td width="25%" align="right" valign="top"><strong>Jenis Pengenalan</strong></td>
      <td width="1%" align="center" valign="top">:</td>
      <td width="74%" align="left" valign="top">$SOC_PB_JENISPENGENALAN</td>
    </tr>
    <tr>
      <td align="right" valign="top"><strong>No Pengenalan</strong></td>
      <td align="center" valign="top">:</td>
      <td align="left" valign="top"><input name="PSK_PB_NOPENGENALAN" type="text" id="PSK_PB_NOPENGENALAN" value="$!PSK_PB_NOPENGENALAN" size="50" maxlength="255" /></td>
    </tr>
    <tr>
      <td align="right" valign="top"><strong>Nama Pihak Berkepentingan</strong></td>
      <td align="center" valign="top">:</td>
      <td align="left" valign="top"><input name="PSK_PB_NAMAPB" type="text" id="PSK_PB_NAMAPB" value="$!PSK_PB_NAMAPB" size="50" maxlength="255" /></td>
    </tr>
    <tr>
      <td align="right" valign="top"><strong>Jenis Pihak Berkepentingan</strong></td>
      <td align="center" valign="top">:</td>
      <td align="left" valign="top">$SOC_PB_JENISPB</td>
    </tr>
    <tr>
      <td align="right" valign="top"><strong>Kumpulan Pihak Berkepentingan</strong></td>
      <td align="center" valign="top">:</td>
      <td align="left" valign="top"><input name="PSK_PB_KUMPPB" type="text" id="PSK_PB_KUMPPB" value="$!PSK_PB_KUMPPB" size="10" maxlength="2" /></td>
    </tr>
    <tr>
      <td align="right" valign="top"><strong>Syer Penyebut</strong></td>
      <td align="center" valign="top">:</td>
      <td align="left" valign="top"><input name="PSK_PB_SYERPENYEBUT" type="text" id="PSK_PB_SYERPENYEBUT" value="$!PSK_PB_SYERPENYEBUT" size="10" maxlength="14" /></td>
    </tr>
    <tr>
      <td align="right" valign="top"><strong>Syer Pembilang</strong></td>
      <td align="center" valign="top">:</td>
      <td align="left" valign="top"><input name="PSK_PB_SYERPEMBILANG" type="text" id="PSK_PB_SYERPEMBILANG" value="$!PSK_PB_SYERPEMBILANG" size="10" maxlength="14" /></td>
    </tr>
    <tr>
      <td align="right" valign="top"><strong>Kod Bangsa</strong></td>
      <td align="center" valign="top">:</td>
      <td align="left" valign="top">$SOC_PB_KODBANGSA</td>
    </tr>
    <tr>
      <td align="right" valign="top"><strong>Kod Jantina</strong></td>
      <td align="center" valign="top">:</td>
      <td align="left" valign="top">$SOC_PB_KODJANTINA</td>
    </tr>
    <tr>
      <td align="right" valign="top"><strong>Kod Warganegara</strong></td>
      <td align="center" valign="top">:</td>
      <td align="left" valign="top">$SOC_PB_KODWARGANEGARA</td>
    </tr>
    <tr>
      <td align="right" valign="top"><strong>Alamat 1 (Rumah)</strong></td>
      <td align="center" valign="top">:</td>
      <td align="left" valign="top"><input name="PSK_PB_ALAMATRUMAH1" type="text" id="PSK_PB_ALAMATRUMAH1" value="$!PSK_PB_ALAMATRUMAH1" size="50" maxlength="40" /></td>
    </tr>
    <tr>
      <td align="right" valign="top"><strong>Alamat 2 (Rumah)</strong></td>
      <td align="center" valign="top">:</td>
      <td align="left" valign="top"><input name="PSK_PB_ALAMATRUMAH2" type="text" id="PSK_PB_ALAMATRUMAH2" value="$!PSK_PB_ALAMATRUMAH2" size="50" maxlength="40" /></td>
    </tr>
    <tr>
      <td align="right" valign="top"><strong>Alamat 3 (Rumah)</strong></td>
      <td align="center" valign="top">:</td>
      <td align="left" valign="top"><input name="PSK_PB_ALAMATRUMAH3" type="text" id="PSK_PB_ALAMATRUMAH3" value="$!PSK_PB_ALAMATRUMAH3" size="50" maxlength="40" /></td>
    </tr>
    <tr>
      <td align="right" valign="top"><strong>Poskod (Rumah)</strong></td>
      <td align="center" valign="top">:</td>
      <td align="left" valign="top"><input name="PSK_PB_POSKODRUMAH" type="text" id="PSK_PB_POSKODRUMAH" value="$!PSK_PB_POSKODRUMAH" size="10" maxlength="5" /></td>
    </tr>
    <tr>
      <td align="right" valign="top"><strong>Kod Negeri (Rumah)</strong></td>
      <td align="center" valign="top">:</td>
      <td align="left" valign="top">$SOC_PB_KODNEGERIRUMAH</td>
    </tr>
    <tr>
      <td align="right" valign="top"><strong>Kod Bandar (Rumah)</strong></td>
      <td align="center" valign="top">:</td>
      <td align="left" valign="top">$SOC_PB_KODBANDARRUMAH</td>
    </tr>
    <tr>
      <td align="right" valign="top"><strong>Alamat 1 (Surat)</strong></td>
      <td align="center" valign="top">:</td>
      <td align="left" valign="top"><input name="PSK_PB_ALAMATSURAT1" type="text" id="PSK_PB_ALAMATSURAT1" value="$!PSK_PB_ALAMATSURAT1" size="50" maxlength="40" /></td>
    </tr>
    <tr>
      <td align="right" valign="top"><strong>Alamat 2 (Surat)</strong></td>
      <td align="center" valign="top">:</td>
      <td align="left" valign="top"><input name="PSK_PB_ALAMATSURAT2" type="text" id="PSK_PB_ALAMATSURAT2" value="$!PSK_PB_ALAMATSURAT2" size="50" maxlength="40" /></td>
    </tr>
    <tr>
      <td align="right" valign="top"><strong>Alamat 3 (Surat)</strong></td>
      <td align="center" valign="top">:</td>
      <td align="left" valign="top"><input name="PSK_PB_ALAMATSURAT3" type="text" id="PSK_PB_ALAMATSURAT3" value="$!PSK_PB_ALAMATSURAT3" size="50" maxlength="40" /></td>
    </tr>
    <tr>
      <td align="right" valign="top"><strong>Poskod (Surat)</strong></td>
      <td align="center" valign="top">:</td>
      <td align="left" valign="top"><input name="PSK_PB_POSKODSURAT" type="text" id="PSK_PB_POSKODSURAT" value="$!PSK_PB_POSKODSURAT" size="10" maxlength="5" /></td>
    </tr>
    <tr>
      <td align="right" valign="top"><strong>Kod Negeri (Surat)</strong></td>
      <td align="center" valign="top">&nbsp;</td>
      <td align="left" valign="top">$SOC_PB_KODNEGERISURAT</td>
    </tr>
    <tr>
      <td align="right" valign="top"><strong>Kod Bandar (Surat)</strong></td>
      <td align="center" valign="top">:</td>
      <td align="left" valign="top">$SOC_PB_KODBANDARSURAT</td>
    </tr>
    <tr>
      <td colspan="3">&nbsp;</td>
    </tr>
  </table>
  <br />
	#end
#end  
  <table width="70%" align="center" cellspacing="0" cellpadding="2" style="border:0px solid #000">
    <tr>
      <td align="center">
        <input type="button" name="cmdSendPSK" onclick="sendPSK();" value="HANTAR MAKLUMAT PUSAKA" />&nbsp;
        <input type="button" name="cmdCloseWindow" onclick="javascript:window.close();" value="TUTUP" />
      </td>
    </tr>
  </table>
</fieldset>
#end
<input type="hidden" id="action2" name="action2" value="$action2" />
<input type="hidden" id="nextAction" name="nextAction" />
<input type="hidden" id="NO_HAKMILIK" name="NO_HAKMILIK" value="$NO_HAKMILIK" />
<script type="text/javascript">
  function checkHM() {
      document.${formName}.action2.value = "checkHM";
      document.${formName}.submit();
  }
  function toggleFieldsetWS() {
      if (document.getElementById('tblWSeTanah').style.visibility == "collapse") {
          document.getElementById('tblWSeTanah').style.visibility = "visible"
	  } else {
          document.getElementById('tblWSeTanah').style.visibility = "collapse"
	  }
  }
  function sendPSK() {
      document.${formName}.action2.value = "sendPSK";
      document.${formName}.nextAction.value = "sendPSK";
      document.${formName}.submit();
  }
  function doChangeSOC(SOC_TYPE) {
	  document.${formName}.action2.value = "sendPSK";
	  doAjaxCall${formName}(SOC_TYPE);
  }
</script>
<script type="text/javascript" language="JavaScript">
<!-- Copyright 2006,2007 Bontrager Connection, LLC
// http://bontragerconnection.com/ and http://willmaster.com/
// Version: July 28, 2007
var cX = 0; var cY = 0; var rX = 0; var rY = 0;
function UpdateCursorPosition(e){ cX = e.pageX; cY = e.pageY;}
function UpdateCursorPositionDocAll(e){ cX = event.clientX; cY = event.clientY;}
if(document.all) { document.onmousemove = UpdateCursorPositionDocAll; }
else { document.onmousemove = UpdateCursorPosition; }
function AssignPosition(d) {
if(self.pageYOffset) {
	rX = self.pageXOffset;
	rY = self.pageYOffset;
	}
else if(document.documentElement && document.documentElement.scrollTop) {
	rX = document.documentElement.scrollLeft;
	rY = document.documentElement.scrollTop;
	}
else if(document.body) {
	rX = document.body.scrollLeft;
	rY = document.body.scrollTop;
	}
if(document.all) {
	cX += rX; 
	cY += rY;
	}
d.style.left = (cX+10) + "px";
d.style.top = (cY+10) + "px";
}
function hideHoverInfo(d) {
if(d.length < 1) { return; }
document.getElementById(d).style.display = "none";
}
function showHoverInfo(d) {
if(d.length < 1) { return; }
var dd = document.getElementById(d);
AssignPosition(dd);
dd.style.display = "block";
}
function ReverseContentDisplay(d) {
if(d.length < 1) { return; }
var dd = document.getElementById(d);
AssignPosition(dd);
if(dd.style.display == "none") { dd.style.display = "block"; }
else { dd.style.display = "none"; }
}
//-->
</script>