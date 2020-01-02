
<fieldset>
<table width="100%" border="0">
  <tr>
    <td><strong>Ulasan Memorandum</strong></td>
  </tr>
</table>
<table width="100%" border="0">
  <tr>
    <td width="41%"><div align="right">Tarikh Terima dari KSU</div></td>
    <td width="59%"><label>
      <input type="text" name="txtterimaksu" id="txtterimaksu">
    </label><a href="javascript:displayDatePicker('txtterimaksu',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></a> </td>
  </tr>
  <tr>
    <td><div align="right">Tarikh Ulasan ke KSU</div></td>
    <td><label>
      <input type="text" name="txtulasanksu" id="txtulasanksu">
    </label><a href="javascript:displayDatePicker('txtulasanksu',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></a></td>
  </tr>
</table>

</fieldset>
<fieldset>
<table width="100%" border="0">
  <tr>
    <td><strong>Keputusan Memorandum</strong></td>
  </tr>
</table>

<table width="100%" border="0">
  <tr>
    <td width="41%"><div align="right">No. Memorandum</div></td>
    <td width="59%"><label>
      <input type="text" name="txtnomemorandum" id="txtnomemorandum">
    </label></td>
  </tr>
  <tr>
    <td><div align="right">Tarikh Mesyuarat JM</div></td>
    <td><label>
      <input type="text" name="txttrkhmesyuaratJM" id="txttrkhmesyuaratJM">
    </label><a href="javascript:displayDatePicker('txttrkhmesyuaratJM',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></a> </td>
  </tr>
  <tr>
    <td><div align="right">Tarikh Keputusan</div></td>
    <td><label>
      <input type="text" name="txttrkhkeputusan" id="txttrkhkeputusan">
    </label> <a href="javascript:displayDatePicker('txttrkhkeputusan',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></a></td>
  </tr>
  <tr>
    <td><div align="right">Keputusan</div></td>
    <td>$!selectKeputusan</td>
  </tr>
  <tr>
    <td><div align="right">Catatan</div></td>
    <td><label>
      <textarea name="txtcatatan" id="txtcatatan" cols="30" rows="2"></textarea>
    </label></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    ##<td>$!mode</td>
    <td><div align="right"></div></td>
    <td>&nbsp;</td>
  </tr>
</table>

</fieldset>
<label>
#if($mode1 == "view")  
      <input type="button" name="txtkemaskini" id="txtkemaskini" value="Kemaskini" onclick="viewSyarikatUpdate('$id_permohonan')" />
      <input type="button" name="txtkembali2" id="txtkembali2" value="Kembali" onclick="backList('$id_permohonan')"/>
      <!--<input type="button" name="txtseterusnya" id="txtseterusnya" value="seterusnya" onclick ="screenMesyuarat111('$id_permohonan')"/> -->
      <input type="button" name="txtseterusnya" id="txtseterusnya" value="Seterusnya" onclick="screenPindahMilik('$id_permohonan')"/> 
  #end
    
#if($mode1 == "new")  
<input type="button" name="txtsimpan" id="txtsimpan" value="Simpan" onclick="viewSyarikatSimpan('$id_permohonan')"/>
     <input type="button" name="txthapus" id="txthapus" value="Hapus" onclick ="reset()"/>
     <input type="button" name="txtkembali" id="txtkembali" value="Kembali" onclick="backList('$id_permohonan')" />
    #end
   
    #if($mode1 == "update")  
    <input type="button" name="txtsimpan" id="txtsimpan" value="Simpan" onclick= "viewSyarikatSimpanUpdate('$id_permohonan')"/>
    <input type="button" name="txtbatal" id="txtbatal" value="Batal" />
    #end
<div align="center">
  <input type="button" name="txtkemaskini" id="txtkemaskini" value="Kemaskini" />
  <input type="button" name="txthapus" id="txthapus" value="Hapus" />
  <input type="button" name="txtsimpan" id="txtsimpan" value="Simpan" onclick ="" />
  <input type="button" name="txtbatal" id="txtbatal" value="Batal" />
  <input type="button" name="txtkembali" id="txtkembali" value="Kembali" />
</div>
</label>
</fieldset>