<!--<strong> Senarai Permohonan </strong>
<br><br>-->
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td>

<fieldset> <legend>CARIAN</legend>

  <table border="0" cellpadding="0" cellspacing="0" width="100%">
    <tbody>
      <tr >
        <td align="right">Nama Pemilik : &nbsp;&nbsp;</td>
        <td><input name="NamaPemohon" type="text" size="40" value="$carian" onkeyup="this.value=this.value.toUpperCase();"></td>
      </tr>
      <tr>
        <td></td>
        <td>
        	<input class="stylobutton" name="cari" value="Cari" type="button" onclick="javascript:fGSPSA_search_data()">
        <input class="stylobutton" value="Kosongkan" onclick="javascript:fGSPSA_cancel();" type="button"></td>
      </tr>
    </tbody>
  </table>
<!--
  <input type="hidden" name="idFail" value="$IdFail">
  <input type="hidden" name="noFail" value="$NoFail">
  -->

</fieldset>
<fieldset>
<legend>SENARAI PERMOHONAN UNTUK FAIL $NoFail</legend>

	<input class="stylobutton" name="kembali" value="Kembali" type="button" onclick="javascript:fGSPSA_Kembali()" />


  <table border="0" cellpadding="0" cellspacing="0" width="100%">
    <tbody>
      <tr class="table_header">
        <td width="5%">NO</td>
        <td width="85%">NAMA PEMINJAM</td>
        <td width="10%">TINDAKAN</td>
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
        <td class="$row">
        	<input class="stylobutton" type="button" name="paparSurat" value="Papar Surat" onclick="javascript:fGSPSA_Papar('$permohonan.idPermohonan','$permohonan.idNegeri')" />
        </td>
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
		</td>
	</tr>
	<tr>
                <td>
                <p align="right">CL - 04 - 0202</p>
                </td>
	</tr>  
</table>