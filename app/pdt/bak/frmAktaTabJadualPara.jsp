        <fieldset>
        <table width="100%" border="0">      
          <tr>
            <td width="29%" align="right" valign="top"><span class="mandatory">*</span> No Akta</td>
            <td width="1%" align="center" valign="top">:</td>
            <td width="70%" valign="top">
              <input name="txtNoAkta" type="text" id="txtNoAkta" onblur="this.value=this.value.toUpperCase()" value="$!JadualPara_NoAkta" size="25" $RO_General />
            </td>
          </tr>
          <tr>
            <td width="29%" align="right" valign="top"><span class="mandatory">*</span> Nama Akta</td>
            <td align="center" valign="top">:</td>
            <td valign="top">
              <input name="txtNamaAkta" type="text" id="txtNamaAkta" onblur="this.value=this.value.toUpperCase()" value="$!JadualPara_NamaAkta" size="50" $RO_General />
            </td>
          </tr>
          <tr>
            <td width="29%" align="right" valign="top">Jadual</td>
            <td align="center" valign="top">:</td>
            <td valign="top">
              <input type="text" name="txtPenggal" id="txtPenggal" value="$!JadualPara_Jadual" size="50" $RO_General /> 
            </td>
          </tr>
          <tr>
            <td width="29%" align="right" valign="top">Nama Jadual</td>
            <td align="center" valign="top">:</td>
            <td valign="top">
              <input type="text" name="txtTajukPenggal" id="txtTajukPenggal" value="$!JadualPara_NamaJadual" size="50" $RO_General />
            </td>
          </tr>  
          <tr>
            <td width="29%" align="right" valign="top">Para</td>
            <td align="center" valign="top">:</td>
            <td valign="top"><textarea name="txtPara" cols="50" rows="5" id="txtPara" $RO_General>$!JadualPara_Para</textarea>
            </td>
          </tr>  
          <tr>
            <td align="right">&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td align="right">&nbsp;</td>
            <td>&nbsp;</td>
            <td>
#if($action == 'viewJadualPara')
              <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="updateJadualPara()"/>
              <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="printJadualPara()" />
#elseif($action == 'updateJadualPara')
              <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="saveJadualPara()"/>
              <input type="button" name="cmdHapus" id="cmdHapus" value="Hapus" onclick="deleteJadualPara()"/>
              <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="printJadualPara()" />
              <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="backJadualPara()"/>
#else
              <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="backJadualPara()"/>
#end
            </td>
          </tr>
          <tr>
            <td align="right">&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
        </table>
        </fieldset>
        <br />
        <fieldset>
          <legend>SENARAI JADUAL (PARA)</legend>
          <table width="100%" align="center" cellpadding="2" cellspacing="0">
            <tr class="table_header">
              <td width="1%" scope="row"><strong>No </strong></td>
              <td width="20%"><strong>Jadual</strong></td>
              <td width="20%"><strong>No Jadual</strong></td>
              <td width="20%"><strong>Para</strong></td>
            </tr>
            <tr>
              <td>&nbsp;</td>
            </tr>
          </table>
</fieldset>
