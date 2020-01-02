<p>
  <input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>

  <input name="mode" type="hidden" id="mode" value="$mode"/>
  <input name="hitButton" type="hidden" id="hitButton" />
  <input name="idFail" type="hidden" id="idFail" value="$idFail"/>
  <input name="idStatus" type="hidden" id="idStatus" value="$idStatus"/>
  <input type="hidden" name="actionPajakan" value="$actionPajakan"/>
  <input type="hidden" name="subUrusan" value="$subUrusan"/>
  
</p>
<table width="100%" border="0" >

  <tr>
    <td>#parse("app/htp/frmPajakanHeader.jsp")</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>
    
    <fieldset>
    <legend>PENAMATAN PAJAKAN</legend>
    <table width="100%" border="0">
      <tr>
        <td width="4%">#if ($mode == 'newBatal' || $mode == 'updateBatal')<font color="#FF0000">*</font>#end</td>
        <td width="22%">Tarikh Pembatalan</td>
        <td width="1%">:</td>
        <td width="53%"><input type="text" name="txdTarikhBatal" id="txdTarikhBatal" size="10" value="$beanPembatalan.tarikhBatal" onblur="check_date(this)" class="$classDis" $readOnly/>
#if ($mode == 'newBatal' || $mode == 'updateBatal') <img src="../img/calendar.gif" alt="Calendar" border="0" onclick="displayDatePicker('txdTarikhBatal',false,'dmy');" /> #end </td>
        <td width="20%">&nbsp;</td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>Sebab Pembatalan</td>
        <td>:</td>
        <td><select name="socBatal" id="socBatal">
          <option>SILA PILIH</option>
          <option value="MK">Melanggar Kontrak</option>
        </select></td>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td valign="top">Ulasan</td>
        <td>&nbsp;</td>
        <td><textarea name="txtUlasan" id="txtUlasan" cols="50" rows="5" onblur="this.value=this.value.toUpperCase();" class="$classDis" $readOnly>$beanPenamatan.ulasan</textarea></td>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>#if ($mode == 'newBatal')
          <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:SimpanBatalPenamatan()" />
          <input type="reset" name="cmdBatal" id="cmdBatal" value="Kosongkan"/>
          <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="javascript:batalPenamatan()" />
#elseif ($mode == 'viewBatal')
<input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="javascript:KemaskiniPenamatan()" />
<input type="button" name="cmdHapus" id="cmdHapus" value="Hapus" onclick="javascript:HapusJPPH()" />
<input type="button" name="cmdBatal" id="cmdBatal" value="Kembali" onclick="javascript:batalPenamatan()" />
#elseif ($mode == 'updateBatal')
<input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:SimpanUpdatePenamatan()" />
<input type="reset" name="cmdBatal" id="cmdBatal" value="Kosongkan"/>
<input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="javascript:batalUpdatePenamatan()" />
#end </td>
        <td>&nbsp;</td>
      </tr>
    </table>
    </fieldset>
    
    </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>

  <tr>
    <td>
    </td>
  </tr>


  <tr>
    <td>
    
    </td>
  </tr>

</table>
 
