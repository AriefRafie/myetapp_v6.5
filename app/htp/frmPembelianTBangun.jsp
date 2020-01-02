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
#set ($NoPetak = "")
#set ($NoBangunan = "")
#set ($NoTingkat = "")
#set ($IdHakmilikurusan = "")
#foreach ( $tbangun in $TBangun )
	#set ($NoPetak = $tbangun.NoPetak)
    #set ($NoBangunan = $tbangun.NoBangunan)
    #set ($NoTingkat = $tbangun.NoTingkat)
    #set ($IdHakmilikurusan = $tbangun.IdHakmilikurusan)
#end

#set ($btnName = "value='Kosongkan'")
#if ($IdHakmilikurusan != "")
	#set ($btnName = "value='Batal'")
#end

<strong> Maklumat Tanah </strong>
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
<legend>Pendaftaran Tanah & Bangunan</legend>
<form name="f2" method="get">
  <table border="0" cellpadding="0" cellspacing="0" width="100%">
    <tbody>
      <tr>
        <td width="50%"><table width="100%" border="0">
          <tr>
            <td width="33%" height="25"><div align="right"><font color="#FF0000">*</font>No. Hakmilik :</div></td>
            <td width="67%">$selectNoHakmilik</td>
          </tr>
          <tr>
            <td><div align="right">No. Bangunan :</div></td>
            <td><input type="text" name="txtNoBangunan" id="txtNoBangunan" size="10" maxlength="5" onkeyup="this.value=this.value.toUpperCase();" value="$NoBangunan" $mode /></td>
          </tr>
        </table></td>
        <td width="50%"><table width="100%" border="0">
          <tr>
            <td width="33%"><div align="right"><font color="#FF0000">*</font>No. Petak :</div></td>
            <td width="67%"><label>
              <input type="text" name="txtNoPetak" id="txtNoPetak" size="10" maxlength="5" onkeyup="this.value=this.value.toUpperCase();" value="$NoPetak" $mode >
            </label></td>
          </tr>
          <tr>
            <td><div align="right">No. Tingkat :</div></td>
            <td width="67%"><label><input type="text" name="txtNoTingkat" id="txtNoTingkat" maxlength="5" size="10" onkeyup="this.value=this.value.toUpperCase();" value="$NoTingkat" $mode></label></td>
          </tr>
        </table></td>
      </tr>
      <tr>
      	<td colspan="2"><hr size="2" width="100%"></td>
      </tr>
      <tr>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      </tr>
      <tr>
		<td colspan="2" align="center"><input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="Kemaskini()">&nbsp;&nbsp;<input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="Simpan()">&nbsp;&nbsp;<input type="reset" name="cmdBatal" id="cmdBatal" $btnName onclick="Batal()">&nbsp;&nbsp;<input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="Kembali()">&nbsp;&nbsp;<input type="button" style="display:none" name="cmdSeterusnya" id="cmdSeterusnya" value="Seterusnya" onclick="seterusnya()"></td>
      </tr>
    </tbody>
  </table>  
  <input type="hidden" name="idHakmilikurusan" value="$IdHakmilikurusan">
  <input type="hidden" name="idPermohonan" value="$IdPermohonan">
  <input type="hidden" name="command">
  <input type="hidden" name="mode">
  <input type="hidden" name="style1" value="$Style1">
  <input type="hidden" name="style2" value="$Style2">
</form>
</fieldset>
<script>
function Kembali() {
	window.close();
}
function seterusnya() {
	document.f2.action = "";
	document.f2.mode.value = "hakmilikview";
	document.f2.command.value = "TBangun";
	document.f2.submit();
}
function Kemaskini() {
	document.f2.action = "";
	document.f2.mode.value = "kemaskini";
	document.f2.command.value = "TBangun";
	document.f2.submit();
}
function Batal() {
	document.f2.action = "";
	if(document.f2.idHakmilikurusan.value == "")
		document.f2.mode.value = "baru";
	else
		document.f2.mode.value = "view";
	document.f2.command.value = "TBangun";
	document.f2.submit();
}
function Simpan() {
	if(document.f2.SocNoHakmilik.value == ""){
		alert('Sila pilih " No. Hakmilik " terlebih dahulu.');
  		document.f2.SocNoHakmilik.focus(); 
		return; 
	}
	if(document.f2.txtNoPetak.value == ""){
		alert('Sila masukkan " No. Petak " terlebih dahulu.');
  		document.f2.txtNoPetak.focus(); 
		return; 
	}
	if ( !window.confirm("Anda Pasti?") ) return;

	document.f2.action = "";
	document.f2.mode.value = "simpan";
	document.f2.command.value = "TBangun";
	document.f2.submit();
	//window.close();
}

document.forms[0].cmdKemaskini.style.display = document.f2.style1.value;
document.forms[0].cmdSimpan.style.display = document.f2.style2.value;
document.forms[0].cmdBatal.style.display = document.f2.style2.value;
document.forms[0].cmdKembali.style.display = document.f2.style1.value;
document.forms[0].cmdSeterusnya.style.display = document.f2.style1.value;

</script>