<div align="left"> #set ($btnNameTanah = "value='Kosongkan'")
  #if ($IdUlasankptg != "")
  #set ($btnNameTanah = "value='Batal'")
  #end
  <fieldset>
    <legend><strong>PERAKUAN KPTG</strong></legend>
    <table width="100%" border="0">
      <tr>
        <td width="30%" scope="row" align="right"><font color="#FF0000">*</font>Tarikh Hantar :</td>
        <td width="70%"><input type="text" name="txdTarikhHantar" id="txdTarikhHantar" size="10" value="$TarikhHantar" $modeSoc $classDis onblur="check_date(this)" />
          <img src="../img/calendar.gif" alt="Calendar" border="0" style="display:$Style2" onClick="displayDatePicker('txdTarikhHantar',false,'dmy');">
          
          </td>
      </tr>
      <tr style="display:none">
        <td scope="row" align="right">Perakuan JKPTG :</td>
        <td><select name="socPerakuan1" id="socPerakuan">
          <option selected="selected">sila pilih perakuan</option>
          <option>Disokong Dengan Syarat</option>
          <option>Disokong Tanpa Syarat</option>
          <option>Tidak Disokong</option>
        </select></td>
      </tr>
      <tr>
        <td scope="row" align="right" valign="top">Catatan :</td>
        <td><label>
          <textarea name="Ulasan" id="Ulasan" cols="30" rows="3" onkeyup="this.value=this.value.toUpperCase();" $modeSoc $classDis >$Ulasan</textarea>
        </label></td>
      </tr>
      <tr>
        <td colspan="2"><div align="center">
          <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="javascript:fPTPJKPTG_kemaskini()">
          &nbsp;&nbsp;
          <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:fPTPJKPTG_SimpanUlasan()">
          &nbsp;&nbsp;
          <input type="reset" name="cmdBatal" id="cmdBatal" $btnNameTanah onclick="javascript:fPTPJKPTG_BatalUlasan()">
          &nbsp;&nbsp;
          <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="javascript:fPM_KembaliTanah()">
        </div></td>
      </tr>
    </table>
  </fieldset>
</div>

<input type="hidden" name="idFail" value="$IdFail">
<input type="hidden" name="idPermohonan" value="$IdPermohonan">
<input type="hidden" name="idHakmilikurusan" value="$IdHakmilikurusan">
<input type="hidden" name="IdUlasankptg" value="$IdUlasankptg">
