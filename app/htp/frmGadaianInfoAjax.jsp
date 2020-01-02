#foreach ( $info in $Info )
  <table border="0" cellpadding="0" cellspacing="0" width="100%">
    <tbody>
      <tr>
		<td align="center" valign="top" width="50%"><table width="100%" border="0">
              <tr>
                <td width="23%"><div align="left"><strong>Negeri</strong></div></td>
                <td width="2%">:</td>
                <td width="75%"><font color="blue">$selectNegeri</font></td>
              </tr>
              <tr>
                <td><div align="left"><strong>Kementerian</strong></div></td>
                <td>:</td>
                <td><font color="blue">$selectKementerian</font></td>
          </tr>
              <tr>
                <td><div align="left"><strong>Agensi</strong></div></td>
                <td>:</td>
                <td><font color="blue">$selectAgensi</font></td>
              </tr>
              <tr>
                <td><div align="left"><strong>Urusan</strong></div></td>
                <td>:</td>
                <td><font color="blue">$selectSuburusan</font></td>
              </tr>
              <tr>
                <td valign="top"><div align="left"><strong>Tajuk </strong></div></td>
                <td valign="top">:</td>
                <td>
                <font color="blue">$info.tajuk</font>
                <!--
                <label>
                  <textarea name="Tajuk" id="Tajuk" cols="45" rows="3" disabled="disabled">$info.tajuk </textarea>
                </label>
                -->
                </td>
              </tr>
        </table></td>
        <td align="center" valign="top" width="50%"><table width="100%" border="0">
              <tr>
                <td width="30%"><div align="left"><strong>No. Fail Seksyen</strong></div></td>
                <td width="2%">:</td>
                <td width="68%"><font color="blue">$info.noFail</font>
                <!-- <input type="text" name="txtNoFailSek" size="40" value="$info.noFail" readonly="readonly"> -->
                </td>
              </tr>
              <tr>
                <td><div align="left"><strong>No. Fail Lain</strong></div></td>
                <td>:</td>
                <td><font color="blue">$info.noRujukan</font>
                <!-- <input type="text" name="txtNoFailLain" id="txtNoFailLain" size="40" value="$info.noRujukan" readonly="readonly"> -->
                </td>
              </tr>
              <tr>
                <td><div align="left"><strong>Tarikh Surat KJP</strong></div></td>
                <td>:</td>
                <td><font color="blue">$info.tSurat</font>
                <!--
                <input type="text" name="txdTarikhSurKJP" id="txdTarikhSurKJP" size="15" value="$info.tSurat" readonly="readonly">
                -->
                </td>
              </tr>              
              <tr>
                <td><div align="left"><strong>Tarikh Permohonan</strong></div></td>
                <td>:</td>
                <td><font color="blue">$info.tPermohonan</font>
                <!--
                <input type="text" name="txdTarikhPermohonan" id="txdTarikhPermohonan" size="15" value="$info.tPermohonan" readonly="readonly" /> -->
                </td>
              </tr>
              <tr>
                <td><div align="left"><strong>Tarikh Buka Fail</strong></div></td>
                <td>:</td>
                <td><font color="blue">$info.tAgihan</font>
                <!--
                <input type="text" name="txdTarikhBukaFail" id="txdTarikhBukaFail" size="15" value="$info.tAgihan" readonly="readonly">
                -->
                </td>
              </tr>
        </table></td>
      </tr>
      <tr>
        <td colspan="2"></td>
      </tr>
    </tbody>
  </table>
  #end