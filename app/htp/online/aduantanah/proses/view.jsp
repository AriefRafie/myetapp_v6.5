<style type="text/css">
<!--
.style1 {
	color: #FF0000
}
.style2 {
	color: #0000FF
}
-->
</style>

<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td colspan="2"><fieldset>
      <strong>
      <legend>MAKLUMAT ADUAN</legend>
      </strong>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="1%">&nbsp;</td>
          <td width="28%">No Aduan</td>
          <td width="1%">:</td>
          <td width="70%">$!complaint.id </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Status</td>
          <td>:</td>
          <td>$!complaint.status </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Nama Pengadu</td>
          <td>:</td>
          <td>$!complaint.namaPengadu</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Jawatan</td>
          <td>:</td>
          <td>$!complaint.jawatanPengadu</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Emel</td>
          <td>:</td>
          <td>$!complaint.emailPengadu </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>No Telefon</td>
          <td>:</td>
          <td>$!complaint.phonePengadu </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Seksyen</td>
          <td>:</td>
          <td>$!complaint.seksyenPengadu</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Pejabat</td>
          <td>:</td>
          <td>$!complaint.pejabatPengadu</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Negeri</td>
          <td>:</td>
          <td>$!complaint.negeriPengadu</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Daerah</td>
          <td>:</td>
          <td>$!complaint.daerahPengadu</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>No. Fail</td>
          <td>:</td>
          <td>$!complaint.noFail</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Jenis Aduan</td>
          <td>:</td>
          <td>$!complaint.type.code -  $!complaint.type.description </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Keterangan Aduan</td>
          <td>:</td>
          <td>$!complaint.catatan </td>
        </tr>
         #foreach ( $tanahs in $tanah )
        <tr>
		    <td colspan="4" align="left">
				<fieldset>
				  <legend><strong>MAKLUMAT TANAH</strong></legend>
					  <table width="100%" border="0" cellspacing="2" cellpadding="2">
							<tr>
							  <td width="1%">&nbsp;</td>
							  <td width="28%">Negeri</td>
							  <td width="1%">:</td>
							  <td width="70%">$!tanahs.nama_negeritanah</td>
							</tr>
							<tr>
							  <td>&nbsp;</td>
							  <td>Daerah</td>
							  <td>:</td>
							  <td>$!tanahs.nama_daerahtanah</td>
							</tr>
							<tr>
							  <td>&nbsp;</td>
							  <td>Mukim</td>
							  <td>:</td>
							  <td>$!tanahs.nama_mukimtanah</td>
							</tr>
							<tr>
							  <td>&nbsp;</td>
							  <td>No. Hakmilik</td>
							  <td>:</td>
							  <td>$!tanahs.NO_HAKMILIK</td>
							</tr>
							<tr>
							  <td>&nbsp;</td>
							  <td>No. Lot</td>
							  <td>:</td>
							  <td>$!tanahs.NO_LOT</td>
							</tr>
					</table>
				</fieldset>
			</td>
		</tr>
#end
        <tr>
          <td></td>
          <td valign="top"> Lampiran </td>
          <td valign="top"> : </td>
          <td colspan="4"><table>
              #foreach($lampiran in $complaint.lampiran)
              <tr>
                <td><a href="javascript:papar_Lampiran('$lampiran.id')" class="style1">$lampiran.fileName</a> </td>
              </tr>
              #end
            </table></td>
        </tr>
      </table>
      </fieldset></td>
  </tr>

</table>
<input type="hidden" name="idRespon" value="$!response.id">
<p>
  <input type="hidden" name="idComplaint" value="$!complaint.id">
</p>