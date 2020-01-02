<input name="semakan" type="hidden" value="$semakan" />
<input name="noHakmilik" type="hidden" value="$noHakmilik" />
<input name="noWarta" type="hidden" value="$noWarta" />
<input name="noSiriPerjanjian" type="hidden" value="$noSiriPerjanjian" />
<input name="noSiriLesen" type="hidden" value="$noSiriLesen" />
<input name="action" type="hidden" value="$action" />
&nbsp;
<table width="100%" border="0" cellpadding="2">
#if($semakan == 2 && $noHakmilik != '')
  <tr>
    <td>
    <fieldset>
    <legend><strong>::Maklumat Tanah Hakmilik::</strong></legend>
    <table width="100%" border="0" cellpadding="2">
      <tr>
        <td width="29%">No Hakmilik</td>
        <td width="1%">:</td>
        <td width="70%">$noHakmilik</td>
      </tr>
      <tr>
        <td>Jenis Hakmilik</td>
        <td>:</td>
        <td>$jenisHakmilik</td>
      </tr>
      <tr>
        <td>Kegunaan Tanah</td>
        <td>:</td>
        <td>$kegunaanTanahHakmilik</td>
      </tr>
      <tr>
        <td>Hakmilik Asal</td>
        <td>:</td>
        <td>$hakmilikAsal</td>
      </tr>
    </table>
    </fieldset>
    </td>
  </tr>
  #end
  #if ($semakan == 2 && $noWarta != '')
  <tr>
    <td>
    <fieldset>
    <legend><strong>::Maklumat Tanah Rizab::</strong></legend>
    <table width="100%" border="0" cellpadding="2">
      <tr>
        <td width="29%">No Warta Rizab</td>
        <td width="1%">:</td>
        <td width="70%">$noWarta</td>
      </tr>
      <tr>
        <td>Lokasi</td>
        <td>:</td>
        <td>$lokasi</td>
      </tr>
      <tr>
        <td>Kegunaan Tanah</td>
        <td>:</td>
        <td>$kegunaanTanahRizab</td>
      </tr>
      <tr>
        <td>Status Sah</td>
        <td>:</td>
        <td>$statusSah</td>
      </tr>
    </table>
    </fieldset>
    </td>
  </tr>
  #end
  #if ($semakan == 1 && $noSiriPerjanjian != '')
  <tr>
    <td>
    <fieldset>
    <legend><strong>::Maklumat Penyewaan::</strong></legend>
    <table width="100%" border="0" cellpadding="2">
          <tr>
            <td width="29%">No Siri Perjanjian</td>
            <td width="1%">:</td>
            <td width="70%">$noSiriPerjanjian</td>
        </tr>
          <tr>
            <td>No Lot</td>
            <td>:</td>
            <td>$noLot</td>
        </tr>
          <tr>
            <td>Kadar Sewa</td>
            <td>:</td>
            <td>$kadarSewa</td>
        </tr>
          <tr>
            <td>Status Sewa</td>
            <td>:</td>
            <td>$statusPerjanjian</td>
          </tr>
        </table>
    </fieldset>
    </td>
  </tr>
  #end
  #if($semakan == 1 && $noSiriLesen != '')
  <tr>
    <td>
    <fieldset>
    <legend><strong>::Maklumat Lesen::</strong></legend>
    <table width="100%" border="0" cellpadding="2">
          <tr>
            <td width="29%">No Siri Lesen</td>
            <td width="1%">:</td>
            <td width="70%">$noSiriLesen</td>
        </tr>
          <tr>
            <td>Lokasi</td>
            <td>:</td>
            <td>$lokasi</td>
        </tr>
          <tr>
            <td>Tempoh Lesen Dipohon</td>
            <td>:</td>
            <td>$tempoh</td>
        </tr>
          <tr>
            <td>Status Lesen</td>
            <td>:</td>
            <td>$statusLesen</td>
          </tr>
        </table>
    </fieldset>
    </td>
  </tr>
  #end
  <tr align="center">
    <td><label>
      <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="kembali()">
    </label></td>
  </tr>
</table>
 <table width="100%" border="0" cellpadding="2">
  	<tr>
    <td align="right"><strong>CL-08-10</strong></td>
  	</tr>
</table>
<script>
function kembali(){
	
	document.${formName}.action.value = "kembali";
	document.${formName}.submit();
	
}
</script>

