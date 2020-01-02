<style type="text/css">
<!--
.csscolor {
	color: #00C;
}
-->
</style>
#foreach ( $info in $Info )
  <table border="0" cellpadding="0" cellspacing="0" width="100%">
    <tbody>
      <tr>
		<td align="center" valign="top" width="50%"><table width="100%" border="0">
              <tr>
                <td>Negeri</td>
                <td>:</td>
                <td class="csscolor">$Negeri</td>
              </tr>
              <tr>
                <td width="23%"><div align="left">Kementerian</div></td>
                <td width="2%">:</td>
                <td width="75%" class="csscolor">$selectKementerian</td>
          </tr>
              <tr>
                <td><div align="left">Agensi</div></td>
                <td>:</td>
                <td class="csscolor">$selectAgensi</td>
              </tr>
              <tr>
                <td><div align="left">Urusan</div></td>
                <td>:</td>
                <td class="csscolor">$selectSuburusan</td>
              </tr>              
              <tr>
                <td valign="top"><div align="left">Tajuk</div></td>
                <td valign="top">:</td>
                <td class="csscolor">$info.tajuk</td>
              </tr>
        </table></td>
        <td align="center" valign="top" width="50%"><table width="100%" border="0">
              <tr>
                <td width="30%"><div align="left">No. Fail Seksyen</div></td>
                <td width="2%">:</td>
                <td width="68%" class="csscolor">$info.noFail</td>
              </tr>
              <tr>
                <td><div align="left">No. Fail KJP</div></td>
                <td>:</td>
                <td class="csscolor">$info.noRujukanKJP</td>
              </tr>
              <tr>
                <td><div align="left">No.Fail Lain</div></td>
                <td>:</td>
                <td class="csscolor">$info.noRujukan</td>
              </tr>
              <tr>
                <td><div align="left">Tarikh Surat KJP</div></td>
                <td>:</td>
                <td class="csscolor">$info.tSurat</td>
              </tr>              
              <tr>
                <td><div align="left">Tarikh Permohonan</div></td>
                <td>:</td>
                <td class="csscolor">
                $info.tPermohonan
                </td>
              </tr>
              <tr>
                <td><div align="left">Tarikh Buka Fail</div></td>
                <td>:</td>
                <td class="csscolor">$info.tAgihan</td>
              </tr>
        </table></td>
      </tr>
      <tr>
        <td colspan="2"></td>
      </tr>
    </tbody>
  </table>
  #end