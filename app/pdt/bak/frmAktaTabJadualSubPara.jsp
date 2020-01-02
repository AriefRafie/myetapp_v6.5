        <fieldset>
        <table width="100%" border="0">      
          <tr>
            <td width="29%" align="right" valign="top"><span class="mandatory">*</span> No Akta</td>
            <td width="1%" align="center" valign="top">:</td>
            <td width="70%" valign="top">
              <input name="txtNoAkta" type="text" id="txtNoAkta" onblur="this.value=this.value.toUpperCase()" value="$!JadualSubPara_NoAkta" size="25" $RO_General />
            </td>
          </tr>
          <tr>
            <td width="29%" align="right" valign="top"><span class="mandatory">*</span> Nama Akta</td>
            <td align="center" valign="top">:</td>
            <td valign="top">
              <input name="txtNamaAkta" type="text" id="txtNamaAkta" onblur="this.value=this.value.toUpperCase()" value="$!JadualSubPara_NamaAkta" size="50" $RO_General />
            </td>
          </tr>
          <tr>
            <td width="29%" align="right" valign="top">Jadual</td>
            <td align="center" valign="top">:</td>
            <td valign="top">
              <input type="text" name="txtPenggal" id="txtPenggal" value="$!JadualSubPara_Jadual" size="50" $RO_General /> 
            </td>
          </tr>
          <tr>
            <td width="29%" align="right" valign="top">Nama Jadual</td>
            <td align="center" valign="top">:</td>
            <td valign="top">
              <input type="text" name="txtTajukPenggal" id="txtTajukPenggal" value="$!JadualSubPara_NamaJadual" size="50" $RO_General />
            </td>
          </tr>  
          <tr>
            <td align="right" valign="top">Para</td>
            <td align="center" valign="top">:</td>
            <td valign="top"><input type="text" name="txtTajukPenggal2" id="txtTajukPenggal2" value="$!JadualSubPara_Para" size="50" $RO_General /></td>
          </tr>
          <tr>
            <td width="29%" align="right" valign="top">Sub Para</td>
            <td align="center" valign="top">:</td>
            <td valign="top"><textarea name="txtSubPara" cols="50" rows="5" id="txtSubPara" $RO_General>$!JadualSubPara_SubPara</textarea>
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
#if($action == 'viewJadualSubPara')
              <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="updateJadualSubPara()"/>
              <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="printJadualSubPara()" />
#elseif($action == 'updateJadualSubPara')
              <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="saveJadualSubPara()"/>
              <input type="button" name="cmdHapus" id="cmdHapus" value="Hapus" onclick="deleteJadualSubPara()"/>
              <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="printJadualSubPara()" />
              <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="backJadualSubPara()"/>
#else
              <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="backJadualSubPara()"/>
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
          <legend>SENARAI PENGGAL</legend>
          <table width="100%" align="center" cellpadding="2" cellspacing="0">
            <tr class="table_header">
              <td width="1%" scope="row"><strong>No Akta</strong></td>
              <td width="20%"><strong>Nama Akta</strong></td>
              <td width="20%"><strong>No Penggal</strong></td>
              <td width="20%"><strong>Tajuk Penggal</strong></td>
            </tr>
            <tr>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
            </tr>
          </table>
        </fieldset>
