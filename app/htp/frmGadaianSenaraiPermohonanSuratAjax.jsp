<strong> Senarai Permohonan </strong>
<br><br>
<fieldset> <legend>Carian</legend>

  <table border="0" cellpadding="0" cellspacing="0" width="100%">
    <tbody>
      <tr class="table_header">
        <td align="right">Nama Pemilik : &nbsp;&nbsp;</td>
        <td><input name="NamaPemohon" type="text" size="40" value="$carian" onkeyup="this.value=this.value.toUpperCase();"></td>
      </tr>
      <tr>
        <td></td>
        <td><input name="cari" value="Cari" type="button" onclick="javascript:fGSPSA_search_data()">
        <input value="Kosongkan" onclick="javascript:fGSPSA_cancel();" type="button"></td>
      </tr>
    </tbody>
  </table>
<!--
  <input type="hidden" name="idFail" value="$IdFail">
  <input type="hidden" name="noFail" value="$NoFail">
  -->

</fieldset>
<fieldset>
<legend>Senarai Permohonan untuk Fail $NoFail</legend>

	<input name="kembali" value="Kembali" type="button" onclick="javascript:fGSPSA_Kembali()" />


  <table border="0" cellpadding="0" cellspacing="0" width="100%">
    <tbody>
      <tr class="table_header">
        <td width="20%"><b>No</b></td>
        <td width="70%"><b>Nama Pemilik</b></td>
        <td width="10%"><b>Tindakan</b></td>
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
        <td class="$row">$permohonan.nama</td>
        <td class="$row"><input type="button" name="paparSurat" value="Papar Surat" onclick="javascript:fGSPSA_Papar('$permohonan.idPermohonan','$permohonan.idNegeri')" /></td>
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
  <!--<input type="hidden" name="idNegeri" value= '$permohonan.idNegeri'>
  <input type="hidden" name="idPermohonan" value="$permohonan.idPermohonan"> -->


</fieldset>