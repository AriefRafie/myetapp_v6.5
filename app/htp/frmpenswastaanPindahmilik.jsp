<fieldset>

#parse("app/utils/record_paging.jsp")

<table width="100%" border="0">

 <tr class="table_header">
 
    
    <td width="19%"><div align="center"><strong>Negeri</strong></div></td>
    <td width="16%"><div align="center"><strong>Daerah</strong></div></td>
    <td width="16%"><div align="center"><strong>Mukim</strong></div></td>
    <td width="16%"><div align="center"><strong>Jenis Hakmilik</strong></div></td>
    <td width="16%"><div align="center"><strong>No. Hakmilik</strong></div></td>
    
  </tr>
#set ($list = "")
  <tr>
    <td height="25"><label>
      $!list.nama_negeri        
        <div align="center"></div>
    </label></td>
    <td><label>
      $!list.nama_daerah
        <div align="center"></div>
    </label></td>
    <td><label>
    $!list.nama_mukim  
      <div align="center"></div>
    </label></td>
    <td><label>
      $!list.jenis_hakmilik
        <div align="center"></div>
    </label></td>
    <td><label>
      $!list.no_hakmilik      
        <div align="center"></div>
    </label></td>
     </tr>
</table>
<table width="100%" border="0">
  <tr>
    <td width="27%"><div align="right">No. Perserahan</div></td>
    <td width="73%"><label>
      <input type="text" name="txtnoperserahan" id="txtnoperserahan">
    </label></td>
  </tr>
  <tr>
    <td><div align="right">Tarikh Daftar</div></td>
    <td><label>
      <input type="text" name="txttarikhdaftar" id="txttarikhdaftar">
    </label><a href="javascript:displayDatePicker('txttarikhdaftar',false,'dmy');"><img src="../img/calendar.gif" alt="" border="0"/></a></td>
  </tr>
  <tr>
    <td><div align="right">Urusan</div></td>
    <td><label>
      <input type="text" name="txturusan2" id="txturusan2">
    </label></td>
  </tr>
  <tr>
    <td><div align="right">Nama Penerima</div></td>
    <td><label>
      <input type="text" name="txtnamapenerima" id="txtnamapenerima">
    </label></td>
  </tr>
  <tr>
    <td><div align="right">Alamat</div></td>
    <td><label>
    <textarea name="txtalamat" id="txtalamat" cols="30" rows="2"></textarea>
    </label></td>
  </tr>
  <tr>
    <td><div align="right">Poskod</div></td>
    <td><label>
      <input type="text" name="txtposkod" id="txtposkod">
    </label></td>
  </tr>
  <tr>
    <td><div align="right">Negeri</div></td>
    <td>$selectNegeri</td>
  </tr>
</table>

</fieldset>
<label>
<div align="center">
  <input type="button" name="txtkemaskini" id="txtkemaskini" value="Kemaskini" />
  <input type="button" name="txthapus" id="txthapus" value="Hapus" />
  <input type="button" name="txtsimpan" id="txtsimpan" value="Simpan" />
  <input type="button" name="txtbatal" id="txtbatal" value="Batal" />
  <input type="button" name="txtkembali" id="txtkembali" value="Kembali" onclick="back" />
</div>
</label>

<input type="hidden" name="id_permohonan" value="$id_permohonan"/>

