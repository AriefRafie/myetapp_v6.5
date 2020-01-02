<!--<strong> Semakan Pajakan Kecil Tanah/Bangunan</strong>
<br><br>-->
<fieldset>
<legend><strong>Pajakan Kecil Tanah/Bangunan</strong></legend>
  <table border="1" cellpadding="0" cellspacing="0" width="100%">
    <tbody>
      <tr>
		<td align="center" valign="top"><table width="100%" border="0">
              <tr>
                <td><div align="right"><strong>Negeri :</strong></div></td>
                <td>$socNegeri
                <!--<input type="text" name="negeri" size="30" value="$permohonanInfo.negeri">--></td>
              </tr>
              <tr>
                <td><div align="right"><strong>Kementerian :</strong></div></td>
                <td>$socKementerian
                <!--<input type="text" name="kementerian" size="30" value="$permohonanInfo.kementerian">--></td>
		
              </tr>
              <tr>
                <td><div align="right"><strong>Agensi :</strong></div></td>
                <td>$socAgensi
                <!--<input type="text" name="agensi" size="30" value="$permohonanInfo.agensi">-->
                </td>
              </tr>
              <!--<tr>
                <td><div align="right"><strong>Urusan :</strong></div></td>
                <td><select name="Urusan" id="Urusan">
                  <option selected value="">Sila Pilih Urusan</option>
                </select></td>
              </tr> -->
              <tr>
                <td valign="top"><div align="right"><strong>Tajuk :</strong></div></td>
                <td><label>
                  <textarea name="Tajuk" id="Tajuk" cols="30" disabled rows="3">$permohonanInfo.tujuan</textarea>
                </label></td>
              </tr>
        </table></td>
        <td align="center" valign="top"><table width="100%" border="0">
              <tr>
                <td><div align="right"><strong>No. Fail Seksyen :</strong></div></td>
                <td><input  disabled type="text" name="txtNoFailSek" size="30" value="$permohonanInfo.fail"></td>
              </tr>
              <tr>
                <td><div align="right"><strong>Tarikh Surat KJP :</strong></div></td>
                <td><input type="text" name="txdTarikhSurKJP" id="txdTarikhSurKJP" value="$util.getDateTime($permohonanInfo.tsurat, "dd/MM/yyyy")" size="15" readonly disabled>
                <input type="button"  disabled onClick="displayDatePicker('txdTarikhSurKJP',false,'dmy');" value="Kalendar"/></td>
              </tr>
              <tr>
                <td><div align="right"><strong>No. Fail Lain :</strong></div></td>
                <td><input type="text" name="txtNoFailLain" id="txtNoFailLain" value="$permohonanInfo.rujukanlain" disabled></td>
              </tr>
              <tr>
                <td><div align="right"><strong>Tarikh Buka Fail :</strong></div></td>
                <td><input type="text" name="txdTarikhBukaFail" id="txdTarikhBukaFail" size="15" value="$util.getDateTime($permohonanInfo.tdaftar, "dd/MM/yyyy")" readonly disabled></td>
              </tr>
        </table></td>
      </tr>
    </tbody>
  </table>  
  	<input type="hidden" name="command">
   	<input type="hidden" name="id_kemaskini">
</fieldset>
