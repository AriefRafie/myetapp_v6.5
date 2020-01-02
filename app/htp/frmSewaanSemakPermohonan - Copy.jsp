<style>
/* the div that holds the date picker calendar */
.dpDiv {
	}


/* the table (within the div) that holds the date picker calendar */
.dpTable {
	font-family: Tahoma, Arial, Helvetica, sans-serif;
	font-size: 12px;
	text-align: center;
	color: #505050;
	background-color: #ece9d8;
	border: 1px solid #AAAAAA;
	}


/* a table row that holds date numbers (either blank or 1-31) */
.dpTR {
	}


/* the top table row that holds the month, year, and forward/backward buttons */
.dpTitleTR {
	}


/* the second table row, that holds the names of days of the week (Mo, Tu, We, etc.) */
.dpDayTR {
	}


/* the bottom table row, that has the "This Month" and "Close" buttons */
.dpTodayButtonTR {
	}


/* a table cell that holds a date number (either blank or 1-31) */
.dpTD {
	border: 1px solid #ece9d8;
	}


/* a table cell that holds a highlighted day (usually either today's date or the current date field value) */
.dpDayHighlightTD {
	background-color: #CCCCCC;
	border: 1px solid #AAAAAA;
	}


/* the date number table cell that the mouse pointer is currently over (you can use contrasting colors to make it apparent which cell is being hovered over) */
.dpTDHover {
	background-color: #aca998;
	border: 1px solid #888888;
	cursor: pointer;
	color: red;
	}


/* the table cell that holds the name of the month and the year */
.dpTitleTD {
	}


/* a table cell that holds one of the forward/backward buttons */
.dpButtonTD {
	}


/* the table cell that holds the "This Month" or "Close" button at the bottom */
.dpTodayButtonTD {
	}


/* a table cell that holds the names of days of the week (Mo, Tu, We, etc.) */
.dpDayTD {
	background-color: #CCCCCC;
	border: 1px solid #AAAAAA;
	color: white;
	}


/* additional style information for the text that indicates the month and year */
.dpTitleText {
	font-size: 12px;
	color: gray;
	font-weight: bold;
	}


/* additional style information for the cell that holds a highlighted day (usually either today's date or the current date field value) */ 
.dpDayHighlight {
	color: 4060ff;
	font-weight: bold;
	}


/* the forward/backward buttons at the top */
.dpButton {
	font-family: Verdana, Tahoma, Arial, Helvetica, sans-serif;
	font-size: 10px;
	color: gray;
	background: #d8e8ff;
	font-weight: bold;
	padding: 0px;
	}


/* the "This Month" and "Close" buttons at the bottom */
.dpTodayButton {
	font-family: Verdana, Tahoma, Arial, Helvetica, sans-serif;
	font-size: 10px;
	color: gray;
	background: #d8e8ff;
	font-weight: bold;
	}
</style>

<strong> Semakan Pajakan Kecil Tanah/Bangunan</strong>
<br><br>
<fieldset>
<legend>Semakan Pajakan Kecil Tanah/Bangunan</legend>
<form name="f2">
  <table border="1" cellpadding="0" cellspacing="0" width="100%">
    <tbody>
      <tr>
		<td align="center" valign="top"><table width="100%" border="0">
              <tr>
                <td><div align="right"><strong>Negeri :</strong></div></td>
                <td><select name="Negeri" id="select">
                		<option selected value="" selected>Sila Pilih Negeri</option>
                        #foreach ( $negeri in $senaraiNegeri )
                        	<option selected value="$negeri.id">$negeri.KodNegeri - $negeri.namaNegeri</option>
                        #end
                    </select></td>
              </tr>
              <tr>
                <td><div align="right"><strong>Kementerian :</strong></div></td>
                <td><select name="Kementerian" id="select">
                		<option selected value="">Sila Pilih Kementerian</option>
                    </select></td>
              </tr>
              <tr>
                <td><div align="right"><strong>Agensi :</strong></div></td>
                <td><select name="Agensi" id="select">
                		<option selected value="">Sila Pilih Agensi</option>
                    </select></td>
              </tr>
              <tr>
                <td><div align="right"><strong>Urusan :</strong></div></td>
                <td><select name="Urusan" id="Urusan">
                  <option selected value="">Sila Pilih Urusan</option>
                </select></td>
              </tr>
              <tr>
                <td valign="top"><div align="right"><strong>Tajuk :</strong></div></td>
                <td><label>
                  <textarea name="Tajuk" id="Tajuk" cols="30" rows="3">$permohonanInfo.tujuan</textarea>
                </label></td>
              </tr>
        </table></td>
        <td align="center" valign="top"><table width="100%" border="0">
              <tr>
                <td><div align="right"><strong>No. Fail Seksyen :</strong></div></td>
                <td><input type="text" name="txtNoFailSek" size="30"></td>
              </tr>
              <tr>
                <td><div align="right"><strong>Tarikh Surat KJP :</strong></div></td>
                <td><input type="text" name="txdTarikhSurKJP" id="txdTarikhSurKJP" size="15" readonly>
                <input type="button" onClick="displayDatePicker('txdTarikhSurKJP',false,'dmy');" value="Kalendar"/></td>
              </tr>
              <tr>
                <td><div align="right"><strong>No. Fail Lain :</strong></div></td>
                <td><input type="text" name="txtNoFailLain" id="txtNoFailLain"></td>
              </tr>
              <tr>
                <td><div align="right"><strong>Tarikh Buka Fail :</strong></div></td>
                <td><input type="text" name="txdTarikhBukaFail" id="txdTarikhBukaFail" size="15" value="$util.getDateTime($permohonanInfo.tdaftar, "dd/MM/yyyy")" readonly></td>
              </tr>
        </table></td>
      </tr>
      <tr>
        <td colspan="2"><table width="100%" border="0">
          <tr>
            <td colspan="2"><strong>Senarai Semakan</strong></td>
            </tr>
          <tr>
            <td width="4%">&nbsp;</td>
            <td width="96%"><label>
              <input type="checkbox" name="cbBorang" id="cbBorang" >
              Pastikan Borang Lengkap Diisi</label></td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td><label>
              <input type="checkbox" name="cbGeran" id="cbGeran">
              Geran Asal / Hakmilik Tanah</label></td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td><label>
              <input type="checkbox" name="cbCukai" id="cbCukai">
              Resit Cukai</label></td>
          </tr>
        </table></td>
      </tr>
      <tr>
		<td colspan="2" align="center"><input type="submit" name="cmdSimpan" id="cmdSimpan" value="Simpan">&nbsp;&nbsp;<input type="reset" name="cmdBatal" id="cmdBatal" value="Batal">&nbsp;&nbsp;<input type="button" name="cmdKembali" id="cmdKembali" value="Kembali"></td>
      </tr>
    </tbody>
  </table>  
</form>
</fieldset>
<script>

</script>
