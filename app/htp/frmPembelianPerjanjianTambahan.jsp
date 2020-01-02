<style type="text/css">
<!--
.style1 {color: #0033FF}
.tiadarekod {	color: #F00;
}
-->
</style>
<table width="100%" border="0">
  <tr>
    <td>&nbsp;</td>
  </tr>
  
#if($addmode == "newPTambahan" || $addmode == "viewPTambahan" || $addmode == "updatePTambahan")
  <tr>
    <td>
    #foreach ($ptambahan in $PTambahan)
    <fieldset>
    <legend><strong>PERJANJIAN TAMBAHAN</strong></legend>
    <table width="100%" border="0">
      <tr>
        <td width="14%">&nbsp;</td>
        <td width="26%">&nbsp;</td>
        <td width="1%">&nbsp;</td>
        <td width="46%">&nbsp;</td>
        <td width="13%">&nbsp;</td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>Tarikh Perjanjian Tambahan</td>
        <td>:</td>
        <td><input name="txttarikhPTambahan" type="text" id="txttarikhPTambahan" onblur="check_date(this)" value="$ptambahan.tarikhPTambahan" size="10" maxlength="10" $mode $classDis />
          <img src="../img/calendar.gif" alt="Calendar" border="0" style="display:$Style2" onclick="displayDatePicker('txttarikhPTambahan',false,'dmy');" /></td>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>Tempoh Perjanjian</td>
        <td>&nbsp;</td>
        <td><input name="txtTempohPerjanjian" type="text" id="txtTempohPerjanjian" value="$ptambahan.tempoh" size="2" maxlength="2" $mode $classDis /> 
          bulan</td>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>Tarikh Akhir Perjanjian</td>
        <td>:</td>
        <td><input name="txTarikhAkhirPTambahan" type="text" id="txTarikhAkhirPTambahan" onblur="check_date(this)" value="$ptambahan.TarikhAkhirPTambahan" size="10" maxlength="10" $mode $classDis />
          <img src="../img/calendar.gif" alt="Calendar" border="0" onclick="displayDatePicker('txTarikhAkhirPTambahan',false,'dmy');" /></td>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>Tarikh Hantar Perjanjian Tambahan</td>
        <td>&nbsp;</td>
        <td><input name="txTarikhHantar" type="text" id="txTarikhHantar" onblur="check_date(this)" value="$ptambahan.tarikhHantar" size="10" maxlength="10" $mode $classDis />
          <img src="../img/calendar.gif" alt="Calendar" border="0" onclick="displayDatePicker('txTarikhHantar',false,'dmy');" /></td>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>Sebab-sebab</td>
        <td>:</td>
        <td><select name="socSebab" id="socPerakuan" $mode $classDis >
        
        #if($ptambahan.sebab == "")
          <option selected="selected">Sila Pilih Sebab</option>
          <option value="PT">Perlanjutan Tempoh</option>
          <option value="SL">Sebab-Sebab Lain</option>
         
         #elseif($ptambahan.sebab == "PT")
          <option >Sila Pilih Sebab</option>
          <option value="PT" selected="selected">Perlanjutan Tempoh</option>
          <option value="SL">Sebab-Sebab Lain</option>

        
        #else($ptambahan.sebab == "SL")
          <option selected="selected">Sila Pilih Sebab</option>
          <option value="PT">Perlanjutan Tempoh</option>
          <option value="SL" selected="selected">Sebab-Sebab Lain</option>
        #end
          
        </select></td>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>Alasan</td>
        <td>:</td>
        <td><textarea name="Alasan" id="Alasan" cols="30" rows="3" onkeyup="this.value=this.value.toUpperCase();" $mode $classDis >$ptambahan.alasan</textarea></td>
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
        <td colspan="5" align="center">
        
        #if($addmode == "viewPTambahan")
        <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="javascript:fPPT_Kemaskini($ptambahan.idPerjanjiantambahan)" />
&nbsp;&nbsp;
		#end
        
        #if($addmode == "newPTambahan")
<input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:fPPT_SimpanPerjanjianTambahan()" />
		#end

	#if($addmode == "updatePTambahan")
<input type="button" name="cmdSimpanUpdate" id="cmdSimpanUpdate" value="Simpan" onclick="javascript:fPPT_SimpanUpdatePerjanjianTambahan($ptambahan.idPerjanjiantambahan)" />
&nbsp;&nbsp;
<input type="reset" name="cmdBatal" id="cmdBatal" $btnNamePeguam onclick="javascript:fPPT_Batal()" />
&nbsp;&nbsp;
<input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="javascript:fPPT_Kembali()" />
	#end

</td>
        </tr>
    </table>
    </fieldset>
    #end
    </td>
  </tr>
  #end
  <tr>
    <td align="center">&nbsp;</td>
  </tr>
  <tr>
    <td><fieldset>
      <legend><strong>SENARAI PERJANJIAN TAMBAHAN</strong>
      </legend>
      <table align="center" width="100%">
        <tr>
          <td colspan="5" scope="row"><input name="cmdDaftar" type="button" value="Daftar Baru" onclick="javascript:daftarBaruPTambahan()"/></td>
        </tr>
        <tr class="table_header">
          <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
          <td width="15%"><strong>Tarikh Perjanjian Tambahan</strong></td>
          <td width="15%" align="center"><strong>Tarikh Akhir Perjanjian Tambahan</strong></td>
          <td width="35%" ><strong>Alasan</strong></td>
        </tr>
        #set ($list = "")
        #if ($SenaraiPTambahan.size() > 0)
        #foreach ($list in $SenaraiPTambahan)
        #if ($list.bil == '')
        #set( $row = "row1" )
        #elseif (($list.bil % 2) != 0)
        #set( $row = "row1" )
        #else 
        #set( $row = "row2" )
        #end
        <tr>
          <td class="$row" align="center">$list.bil</td>
          <td class="$row"><a href="javascript:paparPTambahan('$list.idPerjanjiantambahan')" class="style1">$list.tarikhPTambahan</a></td>
          <td class="$row" align="center">$list.tarikhAkhir</td>
          <td class="$row" >$list.alasan</td>
        </tr>
        #end
        #else
        <tr>
          <td class="row1">&nbsp;</td>
          <td class="row1"><span class="tiadarekod">Tiada Rekod</span></td>
          <td class="row1">&nbsp;</td>
          <td class="row1">&nbsp;</td>
        </tr>
        #end
      </table>
    </fieldset></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
</table>

<p>
  <input type="hidden" name="idFail" value="$IdFail">
  <input type="hidden" name="idPermohonan" value="$IdPermohonan">
</p>
