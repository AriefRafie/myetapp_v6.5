<style type="text/css">
<!--
.style1 {color: #FF0000}
-->
</style>
<fieldset>
  <legend><strong>MINIT</strong></legend>
  <table width="100%"  align="center">
    <tr>
      <td width="29%" align="right" valign="top"><div align="left"><span class="mandatori style1">*</span> Agenda Mesyuarat</div></td>
      <td width="1%" align="center" valign="top">:</td>
      <td width="70%" valign="top">$!Minit_Agenda</td>
    </tr>
    <tr>
      <td align="right" valign="top"><div align="left">No Minit</div></td>
      <td align="center" valign="top">:</td>
      <td valign="top"><input name="Minit_No" type="text" id="Minit_No" value="$!Minit_No" size="10" maxlength="2" readonly="readonly" /></td>
    </tr>
    <tr>
      <td  align="right" valign="top"><div align="left">Tajuk Minit</div></td>
      <td  align="center" valign="top">:</td>
      <td valign="top"><input name="Minit_Tajuk" type="text" id="Minit_Tajuk" value="$!Minit_Tajuk" size="50" maxlength="50" onkeyup="this.value=this.value.toUpperCase();" /></td>
    </tr>
    <tr>
      <td  align="right" valign="top"><div align="left">Minit</div></td>
      <td  align="center" valign="top">:</td>
      <td  valign="top"><textarea id="Minit_Minit" name="Minit_Minit" cols="80" rows="8">$!Minit_Minit</textarea></td>
    </tr>
    <tr>
      <td align="right" valign="top"><div align="left">Tindakan/Makluman</div></td>
      <td align="center" valign="top">:</td>
      <td valign="top"><textarea id="Minit_Tindakan" name="Minit_Tindakan" cols="40" rows="2">$!Minit_Tindakan</textarea>
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
        <input type="checkbox" id="Minit_Makluman" name="Minit_Makluman" value="1" onclick="javascript:checkMaklumanMinit();" $Minit_Makluman />
      Makluman </td>
    </tr>
    
    <tr>
      <td align="right" valign="top"><div align="left">Maklumbalas</div></td>
      <td align="center" valign="top">:</td>
      <td valign="top"><textarea id="Minit_Maklumbalas" name="Minit_Maklumbalas" cols="80" rows="8">$!Minit_Maklumbalas</textarea>
      </td>
    </tr>
    
    
    <tr>
      <td colspan="2"><div align="left"></div></td>
      <td>
#if ($hideSaveButton != 'true')  
        <input type="button" id="cmdSimpan" name="cmdSimpan" value="Simpan" onclick="simpanMesyuaratMinit();" />
#end
        <input type="button" id="cmdKosong" name="cmdKosong" value="Kosongkan" onclick="doChangeTab(2, 'daftarMesyuaratMinit');" />      </td>
    </tr>
    <tr>
      <td colspan="3"><div align="left"></div></td>
    </tr>
  </table>
</fieldset>