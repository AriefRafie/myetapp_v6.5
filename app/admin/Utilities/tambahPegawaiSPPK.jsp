

<tr >
<td align="left" valign="top" colspan="14">

<fieldset>
<legend>Tambah Maklumat Pegawai</legend>
<table width="100%" border="0" cellspacing="2" cellpadding="0">
       
        <tr>
          <td width="21%">Nama Pegawai</td>
          <td width="1%" align="center" valign="top">:</td>
          <td width="78%">
          <select id="USER_ID_$viewPejabat.ID_PEJABAT"  name="USER_ID_$viewPejabat.ID_PEJABAT" style="width: 500px;"
          onchange="doDivAjaxCall$formname('dataPegawai$ID_PEJABAT','getPegawaiData','ID_PEJABAT=$viewPejabat.ID_PEJABAT&JENISPEJ=$viewPejabat.JENIS_PEJ&USER_ID='+this.value);">
			<option value = "" >SILA PILIH</option>
			#foreach ( $listPeg in $listPegawaiPPK )		
			
			<option  value="$listPeg.USER_ID" >$listPeg.DETAILS_PEGAWAI</option>
			#end
		</select>
           
           </td>
        </tr>
        <tr>
        <td colspan="10">
		<div id="dataPegawai$ID_PEJABAT" >
        <!--<tr>
		<td>Kod Pegawai</td>
		<td align="center" valign="top">:</td>
		<td></td>
	  </tr>
        <tr>
          <td>Jawatan</td>
          <td align="center" valign="top">:</td>
          <td></td>
        </tr>
        <tr>
          <td>Keterangan Unit</td>
          <td align="center" valign="top">:</td>
          <td>&nbsp;</td>
      </tr>
        <tr>
          <td>Pejabat</td>
          <td align="center" valign="top">:</td>
          <td>&nbsp;</td>
      </tr>
        <tr>
          <td>Alamat</td>
          <td align="center" valign="top">:</td>
          <td>&nbsp;</td>
      </tr>
        <tr>
          <td>&nbsp;</td>
          <td align="center" valign="top">&nbsp;</td>
          <td>&nbsp;</td>
      </tr>
        <tr>
          <td>&nbsp;</td>
          <td align="center" valign="top">&nbsp;</td>
          <td>&nbsp;</td>
      </tr>
        <tr>
          <td>Poskod</td>
          <td align="center" valign="top">:</td>
          <td>&nbsp;</td>
      </tr>
        <tr>
          <td>Negeri</td>
          <td align="center" valign="top">:</td>
          <td>&nbsp;</td>
      </tr>
        <tr>
          <td>No. Tel </td>
          <td align="center" valign="top">:</td>
          <td></td>
      </tr>
        <tr>
          <td>Extension</td>
          <td align="center" valign="top">:</td>
          <td></td>
        </tr>
        <tr>
          <td>Emel</td>
          <td align="center" valign="top">:</td>
          <td></td>
        </tr>-->
        </div>
        </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td align="center" valign="top">&nbsp;</td>
          <td><input type="button" name="button" id="button" value="Simpan" onclick="doDivAjaxCall$formname('div_listaddPegawai$viewPejabat.ID_PEJABAT','simpanPegawaiSPPK','ID_PEJABAT=$viewPejabat.ID_PEJABAT&JENISPEJ=$viewPejabat.JENIS_PEJ&ID_RUJPPK=&USER_NAME='+$jquery('#USER_ID_$viewPejabat.ID_PEJABAT').val());">
            <input type="button" name="button2" id="button2" value="Batal" onclick="doDivAjaxCall$formname('div_listaddPegawai$viewPejabat.ID_PEJABAT','showDisplayPegawaiSPPK','ID_PEJABAT=$viewPejabat.ID_PEJABAT');"></td>
        </tr>
        
      </table>
</fieldset>


</td>		
</tr>