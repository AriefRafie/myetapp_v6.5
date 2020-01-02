        <fieldset>
        <table width="100%" border="0">      
          <tr>
            <td width="29%" align="right" valign="top"><span class="mandatory">*</span> No Akta</td>
            <td width="1%" align="center" valign="top">:</td>
            <td width="70%" valign="top">
              <input name="txtNoAkta" type="text" id="txtNoAkta" onblur="this.value=this.value.toUpperCase()" value="$!JadualSubSubPara_NoAkta" size="25" $RO_General />
            </td>
          </tr>
          <tr>
            <td width="29%" align="right" valign="top"><span class="mandatory">*</span> Nama Akta</td>
            <td align="center" valign="top">:</td>
            <td valign="top">
              <input name="txtNamaAkta" type="text" id="txtNamaAkta" onblur="this.value=this.value.toUpperCase()" value="$!JadualSubSubPara_NamaAkta" size="50" $RO_General />
            </td>
          </tr>
          <tr>
            <td width="29%" align="right" valign="top">Jadual</td>
            <td align="center" valign="top">:</td>
            <td valign="top">
              <input type="text" name="txtPenggal" id="txtPenggal" value="$!JadualSubSubPara_Jadual" size="50" $RO_General /> 
            </td>
          </tr>
          <tr>
            <td width="29%" align="right" valign="top">Nama Jadual</td>
            <td align="center" valign="top">:</td>
            <td valign="top">
              <input type="text" name="txtNamaJadual" id="txtNamaJadual" value="$!JadualSubSubPara_NamaJadual" size="50" $RO_General />
            </td>
          </tr>  
          <tr>
            <td align="right" valign="top">Para</td>
            <td align="center" valign="top">:</td>
            <td valign="top"><input type="text" name="txtPara" id="txtPara" value="$!JadualSubSubPara_Para" size="50" $RO_General /></td>
          </tr>
          <tr>
            <td align="right" valign="top">Sub Para</td>
            <td align="center" valign="top">&nbsp;</td>
            <td valign="top"><input type="text" name="txtTajukPenggal3" id="txtTajukPenggal3" value="$!JadualSubSubPara_SubPara" size="50" $RO_General /></td>
          </tr>
          <tr>
            <td width="29%" align="right" valign="top">Sub-Sub Para</td>
            <td align="center" valign="top">:</td>
            <td valign="top"><textarea name="txtSubSubPara" cols="50" rows="5" id="txtSubSubPara" $RO_General>$!JadualSubSubPara_SubSubPara</textarea>
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
#if($action == 'viewJadualSubSubPara')
              <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="updateJadualSubSubPara()"/>
              <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="printJadualSubSubPara()" />
#elseif($action == 'updateJadualSubSubPara')
              <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="saveJadualSubSubPara()"/>
              <input type="button" name="cmdHapus" id="cmdHapus" value="Hapus" onclick="deleteJadualSubSubPara()"/>
              <input type="button" name="cmdCetak" id="cmdCetak" value="Cetak" onclick="printJadualSubSubPara()" />
              <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="backJadualSubSubPara()"/>
#else
              <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="backJadualSubSubPara()"/>
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
