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
#set ($idFail = "")
#set ($tajuk = "")
#set ($noFail = "")
##set ($tSurat = "")
#set ($noRujukan = "")
##set ($tAgihan = "")
#set ($tarikhDaftarFail = "")
#foreach ( $semak in $Semak )
    #set ($idFail = $semak.idFail)
	#set ($tajuk = $semak.tajuk)
    #set ($noFail = $semak.noFail)
    ##set ($tSurat = $semak.tSurat)
    #set ($noRujukan = $semak.noRujukan)
    ##set ($tAgihan = $semak.tAgihan)
    #set ($tarikhDaftarFail = $semak.tarikhDaftarFail)
#end

#set ($btnName = "value='Kosongkan'")
#if ($idFail != "")
	#set ($btnName = "value='Batal'")
#end

<strong> Pendaftaran Perletakhakan</strong>
<br>
#if ( $SimpanStatus == "success" )
    <table width="100%" border="0">
        <tr>
            <td>
            <font color="blue">
            <b>
            #if ( $ResultSimpan == "baru" )
                Fail anda telah berjaya disimpan.
            #elseif ($ResultSimpan == "kemaskini" )
                Fail anda telah berjaya dikemaskini.
            #end
            </b>
            </font>
            </td>
        </tr>
    </table>
#end
<br>
<fieldset>
<legend>Maklumat Fail</legend>
<form name="f2" method="post">
  <table border="0" cellpadding="0" cellspacing="0" width="100%">
    <tbody>
      <tr>
		<td align="center" valign="top" width="55%"><table width="100%" border="0">
              <tr>
                <td width="25%"><div align="right"><strong><font color="#FF0000">*</font>Negeri :</strong></div></td>

                <td width="75%">$selectNegeri</td>
              </tr>
              <tr>
                <td><div align="right"><strong><font color="#FF0000">*</font>Kementerian :</strong></div></td>
                <td>$selectKementerian</td>
          </tr>
              <tr>
                <td><div align="right"><strong><font color="#FF0000">*</font>Urusan :</strong></div></td>
                <td>$selectSuburusan                </td>
            </tr>
              <tr>
                <td valign="top"><div align="right"><strong><font color="#FF0000">*</font>Tajuk :</strong></div></td>
                <td><label>
                  <textarea name="txtTajuk" id="Tajuk" cols="30" rows="3" onkeyup="this.value=this.value.toUpperCase();" $modeSoc>$tajuk </textarea>
                </label></td>
              </tr>
        </table></td>
        <td align="center" valign="top" width="45%"><table width="100%" border="0">
              <tr>
                <td width="35%"><div align="right"><strong>No. Fail Seksyen :</strong></div></td>
                <td width="65%"><input type="text" name="txtNoFailSek" size="30" value="$noFail" readonly="readonly"></td>
              </tr>
              
             #if ($idFail == "")
             <tr>
                <td><div align="right"><strong>Tarikh Daftar Fail :</strong></div></td>
                <td><input type="text" name="txdTarikhBukaFail" id="txdTarikhBukaFail" size="15" value="$datenow" readonly="readonly" />
               <input type="button" style="display:none" onClick="displayDatePicker('txdTarikhBukaFail',false,'dmy');" value="Kalendar" $modeSoc/>                </td>
              </tr>
             #else
             <tr>
                <td><div align="right"><strong>Tarikh Daftar Fail :</strong></div></td>
                <td>
                <input type="text" name="txdTarikhBukaFail" id="txdTarikhBukaFail" size="15" value="$tarikhDaftarFail" readonly="readonly">
                <input type="button" style="display:none" onClick="displayDatePicker('txdTarikhBukaFail',false,'dmy');" value="Kalendar" $modeSoc/>                </td>
              </tr>
             #end
        </table></td>
      </tr>
      <tr>
        <td colspan="2"></td>
      </tr>
      <tr>
		<td colspan="2" align="center"><input type="submit" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="Kemaskini()">&nbsp;&nbsp;<input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="Simpan()">&nbsp;&nbsp;<input type="reset" name="cmdBatal" id="cmdBatal" $btnName onclick="Batal()">&nbsp;&nbsp;<input type="button" style="display:none" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="Kembali()" />&nbsp;&nbsp;<input type="button" name="cmdSeterusnya" id="cmdSeterusnya" value="Seterusnya" onclick="Seterusnya()"></td>
      </tr>
    </tbody>
  </table>
  <input type="hidden" name="idFail" value="$idFail">
  <input type="hidden" name="noFail" value="$noFail">
  <input type="hidden" name="command">
  <input type="hidden" name="mode">
  <input type="hidden" name="style1" value="$Style1">
  <input type="hidden" name="style2" value="$Style2">
</form>
</fieldset>
<script>
function Kembali() {
	document.f2.action = "";
	document.f2.command.value = "";
	document.f2.submit();
}
function Seterusnya() {
	document.f2.action = "";
	document.f2.command.value = "Semakan";
	document.f2.mode.value = "baru";
	document.f2.submit();
}
function Kemaskini() {
	document.f2.action = "";
	document.f2.mode.value = "kemaskini";
	document.f2.command.value = "FailBaru";
	document.f2.submit();
}
function Batal() {
	document.f2.action = "";
	if(document.f2.idFail.value == "")
		document.f2.mode.value = "";
	else
		document.f2.mode.value = "view";
	document.f2.command.value = "FailBaru";
	document.f2.submit();
}
function Simpan() {
	if(document.f2.socNegeri.value == ""){
		alert('Sila pilih " Negeri " terlebih dahulu.');
  		document.f2.socNegeri.focus(); 
		return; 
	}
	if(document.f2.socKementerian.value == ""){
		alert('Sila pilih " Kementerian " terlebih dahulu.');
  		document.f2.socKementerian.focus(); 
		return; 
	}
	if(document.f2.socSuburusan.value == ""){
		alert('Sila pilih " Urusan " terlebih dahulu.');
  		document.f2.socSuburusan.focus(); 
		return; 
	}
	if(document.f2.txtTajuk.value == ""){
		alert('Sila masukkan " Tajuk " terlebih dahulu.');
  		document.f2.txtTajuk.focus(); 
		return; 
	}
	if ( !window.confirm("Anda Pasti?") ) return;
	document.f2.action = "";
	document.f2.mode.value = "simpan";
	document.f2.command.value = "FailBaru";
	document.f2.submit();
}

document.forms[0].cmdKemaskini.style.display = document.f2.style1.value;
document.forms[0].cmdSimpan.style.display = document.f2.style2.value;
document.forms[0].cmdBatal.style.display = document.f2.style2.value;
//document.forms[0].cmdKembali.style.display = document.f2.style1.value;
document.forms[0].cmdSeterusnya.style.display = document.f2.style1.value;

</script>