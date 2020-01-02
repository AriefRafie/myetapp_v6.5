<style type="text/css">
<!--
.style1 {color: #FF0000}
-->
</style>
<fieldset>
  <legend><strong>SUB-MINIT</strong></legend>
  <table width="100%" border="0" align="center">
    <tr>
      <td width="29%" align="right" valign="top"><div align="left"><span class="mandatori style1">*</span> Agenda Mesyuarat</div></td>
      <td width="1%" align="center" valign="top">:</td>
      <td width="70%" valign="top">$!SubMinit_Agenda</td>
    </tr>
    <tr>
      <td  align="right" valign="top"><div align="left"><span class="mandatori style1">*</span> Minit Mesyuarat</div></td>
      <td  align="center" valign="top">:</td>
      <td  valign="top">$!SubMinit_Minit</td>
    </tr>
    <tr>
      <td align="right" valign="top"><div align="left">No Sub-Minit</div></td>
      <td align="center" valign="top">:</td>
      <td valign="top"><input name="SubMinit_No" type="text" id="SubMinit_No" value="$!SubMinit_No" size="10" maxlength="2" readonly="readonly" /></td>
    </tr>
    <tr>
      <td align="right" valign="top"><div align="left">Tajuk Sub-Minit</div></td>
      <td align="center" valign="top">:</td>
      <td valign="top"><input name="SubMinit_Tajuk" type="text" id="SubMinit_Tajuk" value="$!SubMinit_Tajuk" onkeyup="this.value=this.value.toUpperCase();" size="50" maxlength="50" /></td>
    </tr>
    <tr>
      <td align="right" valign="top"><div align="left">Sub-Minit</div></td>
      <td align="center" valign="top">:</td>
      <td valign="top"><textarea id="SubMinit_SubMinit" name="SubMinit_SubMinit" cols="80" rows="8">$!SubMinit_SubMinit</textarea></td>
    </tr>
    <tr>
      <td align="right" valign="top"><div align="left">Tindakan/Makluman</div></td>
      <td align="center" valign="top">:</td>
      <td valign="top">
        <textarea id="SubMinit_Tindakan" name="SubMinit_Tindakan" cols="40" rows="2">$!SubMinit_Tindakan</textarea>
       <br />
      <select name="socPegawai" id="socPegawai" $readonly $disabled>
                    <option value="" >SILA PILIH</option>
                  #foreach($listPegawai in $SenaraiPegawai)
                  	#if($selectidorang == $listPegawai.user_id)
                    <option value="$listPegawai.user_id" selected="selected">$listPegawai.user_name </option>
                    #else
                    <option value="$listPegawai.user_id" >$listPegawai.user_name </option>
                 	 #end
                  #end
        </select>
        <br />
        <input type="checkbox" id="SubMinit_Makluman" name="SubMinit_Makluman" value="1" onclick="javascript:checkMaklumanSubMinit();" $SubMinit_Makluman />Makluman      </td>
    </tr>
    <tr>
      <td align="right" valign="top"><div align="left">Maklumbalas</div></td>
      <td align="center" valign="top">:</td>
      <td valign="top"><textarea id="SubMinit_Maklumbalas" name="SubMinit_Maklumbalas" cols="80" rows="8">$!SubMinit_Maklumbalas</textarea>
      </td>
    </tr>
    <tr>
      <td colspan="2"><div align="left"></div></td>
      <td>
#if ($hideSaveButton != 'true')  
        <input type="button" id="cmdSimpan" name="cmdSimpan" value="Simpan" onclick="simpanMesyuaratSubMinit();" />
#end
        <input type="button" id="cmdKosong" name="cmdKosong" value="Kosongkan" onclick="doChangeTab(3, 'daftarMesyuaratSubMinit');" />      </td>
    </tr>
    <tr>
      <td colspan="3"><div align="left"></div></td>
    </tr>
  </table>
</fieldset>