<style type="text/css">
<!--
.pautanms {color: #0000FF}
-->
</style>
<!--<strong> Senarai Permohonan </strong>
<br><br>-->
<fieldset> <legend>Carian</legend>
  <table border="0" cellpadding="0" cellspacing="0" width="100%">
    <tbody>
      <tr>
        <td align="right">Nama Pemilik : &nbsp;&nbsp;</td>
        <td><input name="NamaPemohon" type="text" size="40" value="$carian" onkeyup="this.value=this.value.toUpperCase();"></td>
      </tr>
      <tr>
        <td></td>
        <td><input name="cari" value="Cari" type="button" onclick="javascript:fGSPA_search_data()">
        <input value="Kosongkan" onclick="javascript:fGSPA_cancel();" type="button"></td>
      </tr>
    </tbody>
  </table>

</fieldset>
<fieldset>
<legend>Senarai Permohonan untuk Fail $NoFail</legend>

  <input name="add" value="Tambah" type="button" onclick="javascript:fGSPA_Tambah()" />
  &nbsp;&nbsp;
<input name="kembali" value="Kembali" type="button" onclick="javascript:fGSPA_Kembali()">

  <table border="0" cellpadding="0" cellspacing="0" width="100%">
    <tbody>
      <tr class="table_header">
        <td width="15%"><b>No</b></td>
        <td width="50%"><b>Nama Pemilik</b></td>
        <td width="35%"><b>Status</b></td>
      </tr>
      #set ($count = 0)
      #foreach ( $permohonan in $SenaraiPermohonan )
      #set ($count = $count+1)
      	#set( $i = $velocityCount )
          #if ( ($i % 2) != 1 )
               #set( $row = "row2" )
          #else
               #set( $row = "row1" )
          #end
      <tr>
        <td class="$row">$permohonan.bil</td>
        <td class="$row"><a href="javascript:fGSPA_seterusnya('$permohonan.idPermohonan')" class="pautanms">$permohonan.nama</a></td>
        <td class="$row">$permohonan.keterangan</td>
      </tr>
      #end
      #if ($count == 0)
      <tr>
        <td colspan="5" scope="row"><font color="#FF0000">Tiada Rekod.</font></td>
      </tr>
      #end
    </tbody>
  </table>
  <input type="hidden" name="idFail" value="$IdFail">
  <input type="hidden" name="noFail" value="$NoFail">


</fieldset>
