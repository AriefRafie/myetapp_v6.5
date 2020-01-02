<style type="text/css">
<!--
.star {color: #F00;
}
-->
</style>
<!--#if($pageMode == 0)
    #set ($inputstyle = "class=disabled" )
    #set ($inputstyleread = "readonly class=disabled" )
    #set ($selectstyle = "disabled class=disabled" )
#else-->
    #set ($inputstyle = "" )
    #set ($inputstyleread = "" )
    #set ($selectstyle = "" )
<!--#end
#foreach($pembayaran in $vPembayaran)
	#set ( $a = $pembayaran.TarikhSuratKePTD )
#end
-->
<fieldset><legend>Bayaran Proses dah Keputusan</legend>
<table width="100%" border="0">
  <tr>
    <td><fieldset>
<legend>Pembayaran Permohonanx</legend>
<table width="100%" border="0">
  <tr>
    <td width="13%">Tarikh Surat Ke PTD</td>
    <td width="2%"><strong>:</strong></td>
    <td width="18%">
        <input name="txtTarikhSuratKePTD" type="text" id="txtTarikhSuratKePTD" value="$!pembayaran.TarikhSuratKePTD" size="11" maxlength="10" >
         <a href="javascript:displayDatePicker('txtTarikhSuratKePTD',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>
        </td>
    <td width="2%">&nbsp;</td>
    <td width="19%"><span class="star">* </span>Bayaran Proses (RM)</td>
    <td width="1%"><strong>:</strong></td>
    <td width="45%"><input type="text" name="txtBayaranProses" id="txtBayaranProses" size="11" value="$!pembayaran.BayaranProses" onblur="validateCurrency(this,this.value,'')"></td>
  </tr>
  <tr>
    <td>No.Resit</td>
    <td><strong>:</strong></td>
    <td><input type="text" name="txtNoResit" id="txtNoResit" size="30" value="$!pembayaran.NoResit" onkeyup="this.value=this.value.toUpperCase();" ></td>
    <td>&nbsp;</td>
    <td><span class="star">* </span>Tarikh Resit</td>
    <td><strong>:</strong></td>
    <td><input name="txtTarikhResit" type="text" id="txtTarikhResit" value="$!pembayaran.TarikhResit" size="11" maxlength="10" >
     <a href="javascript:displayDatePicker('txtTarikhResit',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a></td>
  </tr>
  <tr>
    <td>Cara Bayaran</td>
    <td><strong>:</strong></td>
    <td>$socBayaran</td>
    <td>&nbsp;</td>
    <td><span class="star">* </span>No Baucer/Cek/Bank Draft</td>
    <td><strong>:</strong></td>
    <td><input type="text" name="txtNoBaucerCekDraft" id="txtNoBaucerCekDraft" size="30"  value="$!pembayaran.NoBaucerCekDraft" onkeyup="this.value=this.value.toUpperCase();"></td>
  </tr>
  <tr>
    <td><span class="star">*</span> Penerima Bayaran</td>
    <td><strong>:</strong></td>
    <td><input type="text" name="txttempatBayaran" id="txttempatBayaran" size="30"  value="$!pembayaran.tempatBayaran" onkeyup="this.value=this.value.toUpperCase();" ></td>
    <td>&nbsp;</td>
    <td><span class="star">* </span>Tarikh Baucer / Cek</td>
    <td><strong>:</strong></td>
    <td><input name="txtTarikhBaucerCek" type="text" id="txtTarikhBaucerCek" value="$!pembayaran.TarikhBaucerCek" size="11" maxlength="10" >
     <a href="javascript:displayDatePicker('txtTarikhBaucerCek',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a></td>
  </tr>
  <tr>
    <td>
        <input class="stylobutton" type="button" name="SimpanBukti" id="SimpanBukti" value="Simpan" onclick="SimpanPembayaran()">
	</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
</table></fieldset></td>
  </tr>
  <tr>
    <td><!--<fieldset><legend>Keputusan Permohonan</legend>
    <table width="100%" border="0">
      <tr>
        <td width="11%">No. Rujukan PTD</td>
        <td width="1%"><strong>:</strong></td>
        <td width="24%">
            <input type="text" name="txtNoRujukPTD" id="txtNoRujukPTD" size="30">
         </td>
        <td width="1%">&nbsp;</td>
        <td width="25%">Status Keputusan</td>
        <td width="1%"><strong>:</strong></td>
        <td width="37%">
            <select name="Keputusan" id="Keputusan">
              <option value="TIADA">SILA PILIH KEPUTUSAN</option>
              <option value="DILULUSKAN">DILULUSKAN</option>
              <option value="TIDAK DILULUSKAN">TIDAK DILULUSKAN</option>
              <option value="BELUM ADA KEPUTUSAN">BELUM ADA KEPUTUSAN</option>
            </select>
          </td>
      </tr>
      <tr>
        <td>Tarikh Keputusan</td>
        <td><strong>:</strong></td>
        <td><input type="text" name="txtTarikhKeputusan" id="txtTarikhKeputusan" size="30"> <a href="javascript:displayDatePicker('txtTarikhKeputusan',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a></td>
        <td>&nbsp;</td>
        <td>Catatan</td>
        <td><strong>:</strong></td>
        <td rowspan="3">
            <textarea name="txtCatatan" id="txtCatatan" cols="45" rows="5"></textarea>
		</td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        </tr>
      <tr>
        <td><input class="stylobutton" type="button" name="SimpanKeputusan" id="SimpanKeputusan" value="Simpan"></td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        </tr>
    </table></fieldset></td>
  </tr>
</table>
<!--
</fieldset><input type="text" name="idpermohonan" id="idpermohonan" value="$!lblNoPermohonan" />
<table width="100%" border="0">

  <tr>
    <td class="w">*Maaf Tiada Maklumat Bayaran!! </td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td><input class="stylobutton" type="button" value="Tambah" onclick="TambahPembayaran()" /></td>
    <td>&nbsp;</td>
  </tr>

</table>
-->