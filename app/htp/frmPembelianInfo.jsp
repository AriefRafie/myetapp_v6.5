#foreach ( $info in $Info )
  <table border="0" cellpadding="0" cellspacing="0" width="100%">
    <tbody>
      <tr>
		<td align="center" valign="top" width="50%"><table width="100%" border="0">
              <tr>
                <td width="23%"><div align="left">Kementerian</div></td>
                <td width="2%">:</td>
                <td width="75%">$selectKementerian</td>
          </tr>
              <tr>
                <td><div align="left">Agensi</div></td>
                <td>:</td>
                <td>$selectAgensi</td>
              </tr>
              <tr>
                <td><div align="left">Sub Urusan</div></td>
                <td>:</td>
                <td>$selectSuburusan</td>
              </tr>              
              <tr>
                <td valign="top"><div align="left">Tajuk</div></td>
                <td valign="top">:</td>
                <td><label>
                  <textarea name="Tajuk" id="Tajuk" cols="41" rows="4" disabled="disabled">$info.tajuk </textarea>
                </label></td>
              </tr>
        </table></td>
        <td align="center" valign="top" width="50%"><table width="100%" border="0">
              <tr>
                <td width="30%"><div align="left">No. Fail Seksyen</div></td>
                <td width="2%">:</td>
                <td width="68%"><input type="text" name="txtNoFailSek" size="28" value="$info.noFail" readonly="readonly"></td>
              </tr>
              <tr>
                <td><div align="left">No. Fail KJP</div></td>
                <td>:</td>
                <td><input type="text" name="txtNoFailKJP" id="txtNoFailKJP" size="28" value="$info.noRujukanKJP" readonly="readonly"></td>
              </tr>
              <tr>
                <td><div align="left">No.Fail Lain</div></td>
                <td>:</td>
                <td><input type="text" name="txtNoFailLain" id="txtNoFailLain" size="28" value="$info.noRujukan" readonly="readonly" /></td>
              </tr>
              <tr>
                <td><div align="left">Tarikh Surat KJP</div></td>
                <td>:</td>
                <td><input type="text" name="txdTarikhSurKJP" id="txdTarikhSurKJP" size="10" value="$info.tSurat" readonly="readonly" /></td>
              </tr>              
              <tr>
                <td><div align="left">Tarikh Permohonan</div></td>
                <td>:</td>
                <td><input type="text" name="txdTarikhSurKJP" id="txdTarikhSurKJP" size="10" value="$info.tPermohonan" readonly="readonly" /></td>
              </tr>
              <tr>
                <td><div align="left">Tarikh Buka Fail</div></td>
                <td>:</td>
                <td><input type="text" name="txdTarikhBukaFail" id="txdTarikhBukaFail" size="10" value="$info.tAgihan" readonly="readonly" /></td>
              </tr>
        </table></td>
      </tr>
      <tr>
        <td colspan="2"></td>
      </tr>
    </tbody>
  </table>
  #end