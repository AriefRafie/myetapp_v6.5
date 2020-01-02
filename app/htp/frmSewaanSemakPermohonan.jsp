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
                <td>$socNegeri
                <!--<input type="text" name="negeri" size="30" value="$permohonanInfo.negeri">
                --></td>
              </tr>
              <tr>
                <td><div align="right"><strong>Kementerian :</strong></div></td>
                <td>$socKementerian
                <!--<input type="text" name="kementerian" size="30" value="$permohonanInfo.kementerian">-->
                </td>
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
                  <textarea name="Tajuk" id="Tajuk" cols="30" rows="3">$permohonanInfo.tujuan</textarea>
                </label></td>
              </tr>
        </table></td>
        <td align="center" valign="top"><table width="100%" border="0">
              <tr>
                <td><div align="right"><strong>No. Fail Seksyen :</strong></div></td>
                <td><input type="text" name="txtNoFailSek" size="30" value="$permohonanInfo.fail"></td>
              </tr>
              <tr>
                <td><div align="right"><strong>Tarikh Surat KJP :</strong></div></td>
                <td><input type="text" name="txdTarikhSurKJP" id="txdTarikhSurKJP" value="$util.getDateTime($permohonanInfo.tsurat, "dd/MM/yyyy")" size="15" readonly>
                <input type="button" onClick="displayDatePicker('txdTarikhSurKJP',false,'dmy');" value="Kalendar"/></td>
              </tr>
              <tr>
                <td><div align="right"><strong>No. Fail Lain :</strong></div></td>
                <td><input type="text" name="txtNoFailLain" id="txtNoFailLain" value="$permohonanInfo.rujukanlain"></td>
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

           #set ( $checked = "" )
            #foreach ( $semak in $senaraiSemakan )
                #set( $i = $velocityCount )
                #if ( ($i % 2) == 0 )
                    #set( $row = "row2" )
                #else
                    #set( $row = "row1" )
                #end
                <tr>
                    <td class="$row" width="10">
                        #if ( $semakclass.isSemakan("$permohonanInfo.idpermohonan", "$semak.id" ))
                            #set ( $checked = "checked" )
                        #else
                            #set ( $checked = "" )
                        #end
                        <input class="cb" type="checkbox" name="cbsemaks" value="$semak.id" $checked>
                    </td>
                    <td class="$row">
                        $semak.keterangan
                    </td>

                </tr>       
            #end
          
        </table></td>
      </tr>
      <tr>
		<td colspan="2" align="center">
			<input type="button" name="cmdSimpan" id="cmdSimpan" value="Kemaskini" onclick="semakanPTambah('$permohonanInfo.idpermohonan')">&nbsp
			<input type="reset" name="cmdBatal" id="cmdBatal" value="Batal">&nbsp;
			<input type="button" name="cmdSeterusnya" id="cmdsblm" value="Seterusnya" onclick="nexti('$permohonanInfo.idpermohonan')">&nbsp;
			<input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="backPre('$permohonanInfo.idfail')">&nbsp;
			<input type="button" name="cmdFail" id="cmdKembali" value="Senarai Fail" onclick="backMain()">&nbsp;
		</td>
      </tr>
    </tbody>
  </table>  
  	<input type="hidden" name="command">
   	<input type="hidden" name="idkemaskini" >
   	<input type="hidden" name="fail" >
 
</form>
</fieldset>
<script>
function semakanPTambah(id) {
	if ( document.f2.cmdSimpan.value == "Kemaskini" ) {
		document.f2.cmdSimpan.value = "Simpan";	
		return;
	}else {
		document.f2.command.value = "semakankemaskini";
	}

	//document.f2.command.value = "semakankemaskini";
	document.f2.method="post";
	document.f2.idkemaskini.value = id;
	document.f2.action = "";
	document.f2.submit();
}

function nexti(id) {
	document.f2.command.value = "semakanseterus";
	document.f2.idkemaskini.value = id;
	document.f2.method="post";
	document.f2.action = "";
	document.f2.submit();
}

function kemaskiniPermohonan(id) {
	document.f2.idkemaskini.value = id;
	document.f2.command.value = "update";
	document.f2.method="post";
	document.f2.action = "";
	document.f2.submit();
}

function backPre(id) {
	document.f2.fail.value = id;
	document.f2.command.value = "pkpermohonan";
	document.f2.method="post";
	document.f2.action = "";
	document.f2.submit();
}

function backMain() {
	//document.f2.command.value = "semakanseterus";
	document.f2.method="post";
	document.f2.action = "";
	document.f2.submit();
}
</script>
