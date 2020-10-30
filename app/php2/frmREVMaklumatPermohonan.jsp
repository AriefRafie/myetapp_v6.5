<fieldset>
<legend>MAKLUMAT PERMOHONAN</legend>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
    <td width="28%" valign="top">No. Fail</td>
    <td width="1%" >:</td>
    <td width="70%"><input name="noFail" type="text" id="noFail" size="49" maxlength="100" $readonly class="$inputTextClass" value="$!BeanMaklumatPermohonan.get(0).noFail" onBlur="this.value=this.value.toUpperCase();checkingExistNoFailUpdate();" />    </td>
  </tr>
  <tr>
    <td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
    <td valign="top">Urusan</td>
    <td>:</td>
    <td>$selectUrusan</td>
  </tr>
  <tr>
    <td width="1%">#if ($mode != 'view')<span class="style1">*</span>#end</td>
    <td valign="top">Suburusan</td>
    <td>:</td>
    <td>$selectSuburusan</td>
  </tr>
  <tr>
    <td width="1%" valign="top">#if ($mode != 'view')<span class="style1">*</span>#end</td>
    <td valign="top">Perkara</td>
    <td valign="top">:</td>
    <td><textarea name="txtPerkara" id="txtPerkara" rows="5" cols="50" $readonly class="$inputTextClass" onblur="this.value=this.value.toUpperCase();"  >$!BeanMaklumatPermohonan.get(0).perkara</textarea>    </td>
  </tr>
  <tr>
    <td width="1%" valign="top">&nbsp;</td>
    <td valign="top">Tujuan Sewa</td>
    <td valign="top">:</td>
    <td><textarea name="txtTujuan" id="txtTujuan" rows="5" cols="50" $readonly class="$inputTextClass" onblur="this.value=this.value.toUpperCase();"  >$!BeanMaklumatPermohonan.get(0).tujuan</textarea>    </td>
  </tr>
  <tr>
    <td width="1%" valign="top">&nbsp;</td>
    <td valign="top">Catatan</td>
    <td valign="top">:</td>
    <td><textarea name="txtCatatanPermohonan" id="txtCatatanPermohonan" rows="5" cols="50" $readonly class="$inputTextClass" onblur="this.value=this.value.toUpperCase();"  >$!BeanMaklumatPermohonan.get(0).catatan</textarea>    </td>
  </tr>
  <tr>
     <td>&nbsp;</td>
     <td>Tempoh Permohonan Sewa</td>
     <td>:</td>
     <td><select name="socTempohSewa" id="socTempohSewa" style="width:140px;" $readonly class="$disabled" $disabled>

 #if ($!BeanMaklumatPermohonan.get(0).flagTempohSewa == 'B')

         <option>SILA PILIH</option>
         <option value="B" selected>BULAN KE BULAN</option>
         <option value="T">3 TAHUN</option>
         <option value="JP">JANGKA PENDEK</option>

 #elseif ($!BeanMaklumatPermohonan.get(0).flagTempohSewa == 'T')

         <option>SILA PILIH</option>
         <option value="B">BULAN KE BULAN</option>
         <option value="T" selected>3 TAHUN</option>
         <option value="JP">JANGKA PENDEK</option>

 #elseif ($!BeanMaklumatPermohonan.get(0).flagTempohSewa == 'JP')

         <option>SILA PILIH</option>
         <option value="B">BULAN KE BULAN</option>
         <option value="T">3 TAHUN</option>
         <option value="JP" selected>JANGKA PENDEK</option>

 #else

         <option selected>SILA PILIH</option>
         <option value="B">BULAN KE BULAN</option>
         <option value="T">3 TAHUN</option>
         <option value="JP">JANGKA PENDEK</option>

 #end

       </select>
     </td>
   </tr>
   <tr>
     <td>#if ($mode == 'update')<span class="style1">*</span>#end</td>
     <td>Luas Kegunaan</td>
     <td>:</td>
     <td >$selectLuasKegunaan</td>
   </tr>
   <tr>
     <td>&nbsp;</td>
     <td>Keluasan Asal</td>
     <td>:</td>
     <td>$!BeanMaklumatPermohonan.get(0).luasAsal $!BeanMaklumatPermohonan.get(0).keteranganLuasAsal
       <input type="hidden" name="txtLuasAsal" id="txtLuasAsal" value="$!BeanMaklumatPermohonan.get(0).luasAsal"/></td>
   </tr>
   #if ($idLuasKegunaan == '2')
   <tr>
     <td>#if ($mode == 'update')<span class="style1">*</span>#end</td>
     <td>Unit Luas</td>
     <td>:</td>
     <td>#parse("app/php2/unit_luas.jsp") </td>
   </tr>
   #if ($idLuas != '99999' && $idLuas != '')
   <tr>
     <td>#if ($mode == 'update')<span class="style1">*</span>#end</td>
     <td>Luas Dipohon Sewa</td>
     <td>:</td>
     <td> #if ($idLuas == '0' || $idLuas == '1' || $idLuas == '2' || $idLuas == '3' )
       <input type="text" name="txtLuasMohon1" id="txtLuasMohon1" value="$!BeanMaklumatPermohonan.get(0).luas1" style="text-align:right" onkeyup="validateNumber(this,this.value);" onBlur="this.value=this.value.replace(/,/g,'');kiraLuas('$idLuas')" size="6"  $readonly class="$inputTextClass"/>
       #elseif ($idLuas == '7')
       <input type="text" name="txtLuasMohon1" id="txtLuasMohon1" value="$!BeanMaklumatPermohonan.get(0).luas1" style="text-align:right" onkeyup="validateNumber(this,this.value);" size="6" $readonly class="$inputTextClass" onBlur="kiraLuas('$idLuas')"/>
       <input type="text" name="txtLuasMohon2" id="txtLuasMohon2" value="$!BeanMaklumatPermohonan.get(0).luas2" style="text-align:right" onkeyup="validateNumber(this,this.value);" onBlur="this.value=this.value.replace(/,/g,'');kiraLuas('$idLuas')" size="6" / $readonly class="$inputTextClass">
       #elseif ($idLuas == '8' || $idLuas == '4')
       <input type="text" name="txtLuasMohon1" id="txtLuasMohon1" value="$!BeanMaklumatPermohonan.get(0).luas1" style="text-align:right" onkeyup="validateNumber(this,this.value);" size="6" $readonly class="$inputTextClass" onBlur="kiraLuas('$idLuas')"/>
       <input type="text" name="txtLuasMohon2" id="txtLuasMohon2" value="$!BeanMaklumatPermohonan.get(0).luas2" style="text-align:right" onkeyup="validateNumber(this,this.value);" size="6" $readonly class="$inputTextClass" onBlur="kiraLuas('$idLuas')"/>
       <input type="text" name="txtLuasMohon3" id="txtLuasMohon3" value="$!BeanMaklumatPermohonan.get(0).luas3" style="text-align:right" onkeyup="validateNumber(this,this.value);" onBlur="this.value=this.value.replace(/,/g,'');kiraLuas('$idLuas')" size="6"  $readonly class="$inputTextClass"/>
       #end </td>
   </tr>
   #end
   #end
   <tr>
     <td>&nbsp;</td>
     <td>Luas Bersamaan</td>
     <td>:</td>
     <td><input type="text" name="txtLuasBersamaan" id="txtLuasBersamaan" value="$!BeanMaklumatPermohonan.get(0).luasBersamaan"  style="text-align:right" readonly="readonly" class="disabled"/>
       HEKTAR</td>
   </tr>
   <tr>
     <td>&nbsp;</td>
     <td>Baki Luas</td>
     <td>:</td>
     <td><input type="text" name="txtBakiLuas" id="txtBakiLuas" value="$!BeanMaklumatPermohonan.get(0).luasBaki" readonly="readonly" class="disabled" style="text-align:right"/>
       HEKTAR</td>
   </tr>
   <tr>
     <td>&nbsp;</td>
     <td>&nbsp;</td>
     <td>&nbsp;</td>
     <td>&nbsp;</td>
   </tr>
</table>
</fieldset>
