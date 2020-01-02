<fieldset>
<legend>Maklumat Pajakan</legend>
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tbody>
    	<tr>
		<td align="center" valign="top" width="50%"><table width="100%" border="0">
              <tr>
                <td width="3%"><div align="right"><font color="#FF0000"></font></div></td>
                <td width="20%"><div align="left"><strong>Negeri</strong></div></td>
                <td width="2%">:</td>
                <td width="75%">$selectNegeri</td>
              </tr>
              <tr>
                <td><div align="right"><font color="#FF0000"></font></div></td>
                <td><div align="left"><strong>Kementerian</strong></div></td>
                <td>:</td>
                <td>$selectKementerian</td>
          </tr>
              <tr>
                <td><div align="right"><font color="#FF0000"></font></div></td>
                <td><div align="left"><strong>Agensi</strong></div></td>
                <td>:</td>
                <td>$selectAgensi</td>
            </tr>
              <tr>
                <td><div align="right"><font color="#FF0000"></font></div></td>
                <td><div align="left"><strong>Urusan</strong></div></td>
                <td>:</td>
                <td>$selectUrusan</td>
              </tr>
              <tr>
                <td><div align="right"></div></td>
                <td><div align="left"><strong>Sub Urusan</strong></div></td>
                <td>:</td>
                <td>$selectSuburusan
                <input type="hidden" name="idSuburusan" value="$idSuburusan" /></td>
              </tr>
              <tr>
                <td><div align="right"><font color="#FF0000"></font></div></td>
                <td><div align="left"><strong>Jenis Tanah</strong></div></td>
                <td>:</td>
                <td>$selectJenisTanah</td>
              </tr>
              <tr style="display:none">
                <td><div align="right"><font color="#FF0000"></font></div></td>
                <td><div align="left"><strong>Jenis Fail</strong></div></td>
                <td>:</td>
                <td>$selectSuburusan</td>
              </tr>
              <tr style="display:none">
                <td><div align="right"><font color="#FF0000"></font></div></td>
                <td><div align="left"><strong>UP. Pegawai</strong></div></td>
                <td>:</td>
                <td>$selectSuburusan</td>
              </tr>
              <tr>
                <td valign="top"><div align="right"><font color="#FF0000"></font></div></td>
                <td valign="top"><div align="left"><strong>Tajuk</strong></div></td>
                <td valign="top">:</td>
                <td>$tajuk</td>
              </tr>
        </table></td>
        <td align="center" valign="top" width="45%"><table width="100%" border="0">
              <tr>
                <td width="3%"><div align="right"></div></td>
                <td width="33%"><div align="left"><strong>No. Fail Seksyen</strong></div></td>
                <td width="2%">:</td>
                <td width="62%">$noFail</td>
              </tr>
              <tr>
                <td><div align="right"><font color="#FF0000"></font></div></td>
                <td><div align="left"><strong>No. Fail KJP</strong></div></td>
                <td>:</td>
                <td>$noRujukanKJP</td>
              </tr>
              <tr style="display:none">
                <td><div align="right"><font color="#FF0000"></font></div></td>
                <td><div align="left"><strong>No. Fail UPT</strong></div></td>
                <td>:</td>
                <td>$noRujukan</td>
            </tr>
              <tr>
                <td><div align="right"><font color="#FF0000"></font></div></td>
                <td><div align="left"><strong>No. Fail Lain</strong></div></td>
                <td>:</td>
                <td>$noRujukan</td>
            </tr>
              <tr>
                <td><div align="right"><font color="#FF0000"></font></div></td>
                <td><div align="left"><strong>Tarikh Surat KJP</strong></div></td>
                <td>:</td>
                <td>$tSurat</td>
              </tr>              
            <tr>
              <td><div align="right"><font color="#FF0000"></font></div></td>
                <td><div align="left"><strong>Tarikh Permohonan</strong></div></td>
                <td>:</td>
                <td>$tPermohonan</td>
              </tr>
              #if ($IdPermohonan == "")
              <tr>
                <td><div align="right"></div></td>
                <td><div align="left"><strong>Tarikh Buka Fail</strong></div></td>
                <td>:</td>
                <td>$datenow</td>
              </tr>
            #else
              <tr>
                <td><div align="right"></div></td>
                <td><div align="left"><strong>Tarikh Buka Fail</strong></div></td>
                <td>:</td>
                <td>$tAgihan</td>
              </tr>
            #end
        </table></td>
      </tr>
    </tbody>
</table>
</fieldset>