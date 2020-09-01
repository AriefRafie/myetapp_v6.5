<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td colspan="2"><fieldset>
      <legend><strong>MAKLUMAT ADUAN</strong></legend>
  <table width="100%" border="0" cellspacing="2" cellpadding="2">
 #foreach ( $complaint in $complaints )
		<tr>
            <td width="1%">&nbsp;</td>
            <td width="28%">No Aduan</td>
            <td width="1%">:</td>
            <td width="70%">$!complaint.NO_ADUAN </td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td align="left">Nama</td>
            <td >:</td>
            <td>$!complaint.user_name</td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td align="left">Jawatan</td>
            <td >:</td>
            <td>$!complaint.nama_jawatan</td>
          </tr>
          <tr>
            <td>&nbsp;</td>
	        <td align="left">Emel</td>
	        <td>:</td>
	        <td>$!complaint.emel</td>
	       </tr>
	       <tr>
            <td>&nbsp;</td>
            <td align="left">No. Tel</td>
            <td >:</td>
            <td>$!complaint.no_tel</td>
	       </tr>
         <tr>
            <td>&nbsp;</td>
            <td align="left">Seksyen</td>
            <td >:</td>
            <td>$!complaint.nama_seksyen</td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td align="left">Pejabat</td>
            <td >:</td>
            <td>$!complaint.nama_pejabat</td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td align="left">Negeri</td>
            <td >:</td>
            <td>$!complaint.nama_negeri</td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td align="left">Daerah</td>
            <td >:</td>
            <td>$!complaint.nama_daerah</td>
          </tr>

        <tr>
            <td>&nbsp;</td>
          <td align="left">No. Fail</td>
          <td>:</td>
          <td></td>
        </tr>
		<tr>
		    <td colspan="4" align="left">
				<fieldset>
				  <legend><strong>MAKLUMAT TANAH</strong></legend>
					  <table width="100%" border="0" cellspacing="2" cellpadding="2">
							<tr>
							  <td width="1%">&nbsp;</td>
							  <td width="28%">Negeri</td>
							  <td width="1%">:</td>
							  <td width="70%">$!complaint.nama_negeritanah</td>
							</tr>
							<tr>
							  <td>&nbsp;</td>
							  <td>Daerah</td>
							  <td>:</td>
							  <td>$!complaint.nama_daerahtanah</td>
							</tr>
							<tr>
							  <td>&nbsp;</td>
							  <td>Mukim</td>
							  <td>:</td>
							  <td>$!complaint.nama_mukimtanah</td>
							</tr>
							<tr>
							  <td>&nbsp;</td>
							  <td>No. Hakmilik</td>
							  <td>:</td>
							  <td>$!complaint.NO_HAKMILIK</td>
							</tr>
							<tr>
							  <td>&nbsp;</td>
							  <td>No. Warta</td>
							  <td>:</td>
							  <td>$!complaint.NO_WARTA</td>
							</tr>
							<tr>
							  <td>&nbsp;</td>
							  <td>Tarikh Warta</td>
							  <td>:</td>
							  <td>$!complaint.TARIKH_WARTA</td>
							</tr>
							<tr>
							  <td>&nbsp;</td>
							  <td>No. Lot</td>
							  <td>:</td>
							  <td>$!complaint.NO_LOT</td>
							</tr>
							<tr>
							  <td>&nbsp;</td>
							  <td>Luas Lot</td>
							  <td>:</td>
							  <td>$!complaint.LUAS</td>
							</tr>
					</table>
				</fieldset>
			</td>
		</tr>
         <tr>
		  <td>&nbsp;</td>
          <td align="left" valign="top">Keterangan Aduan</td>
          <td valign="top">:</td>
          <td>$!complaint.KETERANGAN_ADUAN</td>
        </tr>
        #if($!complaint.ID != "")
         <tr>
          <td width="2%" align="right"  valign="top"></td>
          <td align="left" valign="top">Dokumen Sokongan</td>
          <td valign="top">:</td>
          <td valign="top">
          #if($listDokumen_aduan.size() > 0)
          #foreach($list1 in $listDokumen_aduan)
          $list1.tajuk - <a href="javascript:papar_Lampiran('$list1.id_esdokumen')"><font color="blue"><u>$list1.nama_fail</u></font></a><a href="javascript:deleteDokumen1by1('$list1.id_esdokumen')" title="Hapus" ><font color="blue">&nbsp;<img   src="../img/validno.png"  height="10" width="10" alt="" border="0"/></font></a><br />
          #end
          #end
          <a href="javascript:bukaUpload()"><img src="../img/attachment-icon.png" alt="" border="0"/><font color="blue"><i>Muatnaik Dokumen</i></font></a>
                </td>
        </tr>
		#end
     </table>
     #end
     </fieldset>
    </td>
  </tr>

</table>