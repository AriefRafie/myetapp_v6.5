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
#set ($NoHakmilik = "")
#set ($NoLot = "")
#set ($TarikhMula = "")
#set ($NoPelan = "")
#set ($TarikhLuput = "")
#set ($Luas = "")
#set ($IdHakmilikurusan = "")
#foreach ( $tanah in $Tanah )
	#set ($NoHakmilik = $tanah.NoHakmilik)
    #set ($NoLot = $tanah.NoLot)
    #set ($TarikhMula = $tanah.TarikhMula)
    #set ($NoPelan = $tanah.NoPelan)
    #set ($TarikhLuput = $tanah.TarikhLuput)
    #set ($Luas = $tanah.Luas)
    #set ($IdHakmilikurusan = $tanah.IdHakmilikurusan)
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
<legend>Pendaftaran Tanah</legend>
<form name="f2" method="get">
  <table border="0" cellpadding="0" cellspacing="0" width="100%">
    <tbody>
      <tr>
        <td width="50%"><table width="100%" border="0">
          <tr>
            <td width="33%" height="25"><div align="right"><font color="#FF0000">*</font>Negeri :</div></td>
            <td width="67%">$selectNegeri</td>
          </tr>
          <tr>
            <td><div align="right"><font color="#FF0000">*</font>Daerah :</div></td>
            <td>$selectDaerah</td>
          </tr>
          <tr>
            <td><div align="right"><font color="#FF0000">*</font>Bandar/Pekan/Mukim :</div></td>
            <td>$selectMukim</td>
          </tr>
          <tr>
            <td><div align="right"><font color="#FF0000">*</font>Jenis Hakmilik :</div></td>
            <td>$selectJenisHakmilik</td>
          </tr>
        </table></td>
        <td width="50%"><table width="100%" border="0">
          <tr>
            <td width="33%"><div align="right"><font color="#FF0000">*</font>No. Hakmilik :</div></td>
            <td width="67%"><label>
              <input type="text" name="txtNoHakmilik" id="txtNoHakmilik" maxlength="21" onkeyup="this.value=this.value.toUpperCase();" value="$NoHakmilik" $mode >
            </label></td>
          </tr>
          <tr>
            <td><div align="right"><font color="#FF0000">*</font>Kod/No. Lot :</div></td>
            <td><table width="100%" border="0">
              <tr>
                <td width="30%"><label>
                  <input type="text" name="txtNoLot" id="txtKodLot" maxlength="20" size="20" onkeyup="this.value=this.value.toUpperCase();" value="$NoLot" $mode>
                </label></td>
                <td width="70%">$selectLot</td>
              </tr>
            </table></td>
          </tr>
          <tr>
            <td><div align="right"></div></td>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td><div align="right"></div></td>
            <td>&nbsp;</td>
          </tr>
        </table></td>
      </tr>
      <tr>
      	<td colspan="2"><hr size="2" width="100%"></td>
      </tr>
      <tr>
      <td><table width="100%" border="0">
          <tr>
            <td width="33%"><div align="right"><font color="#FF0000">*</font>Tarikh Mula :</div></td>
            <td width="67%"><input type="text" name="txdTarikhTerima" id="txdTarikhTerima" size="10" value="$TarikhMula" readonly="readonly">
            <img src="../img/calendar.gif" border="0" onClick="displayDatePicker('txdTarikhTerima',false,'dmy');" style="display:$Style2"></td>
          </tr>
          <tr>
            <td><div align="right"><font color="#FF0000">*</font>Luas :</div></td>
            <td><label>
            <input type="text" name="txtLuas" id="txtLuas" maxlength="40" onkeyup="validateNumber(this,this.value);" value="$Luas" $mode />
            </label></td>
          </tr>
          <tr>
            <td><div align="right"><font color="#FF0000">*</font>Unit Luas :</div></td>
            <td>$selectLuas</td>
          </tr>
          <tr>
            <td><div align="right"><font color="#FF0000">*</font>No. Pelan Akui :</div></td>
            <td><label>
              <input type="text" name="txtNoPelan" id="txtNoPelan" maxlength="50" onkeyup="this.value=this.value.toUpperCase();" value="$NoPelan" $mode />
            </label></td>
          </tr>
        </table>        </td>
      <td valign="top"><table width="100%" border="0">
          <tr>
            <td width="35%"><div align="right"><font color="#FF0000">*</font>Tarikh Luput :</div></td>
            <td width="65%"><input type="text" name="txdTarikhLuput" id="txdTarikhLuput" size="10" value="$TarikhLuput" readonly="readonly">
            <img src="../img/calendar.gif" border="0" onClick="displayDatePicker('txdTarikhLuput',false,'dmy');" style="display:$Style2"></td>
          </tr>
          <tr style="display:none">
            <td><div align="right"><font color="#FF0000">*</font>Status :</div></td>
            <td>$selectStatus</td>
          </tr>
          <tr>
            <td><div align="right"><font color="#FF0000">*</font>Rizab :</div></td>
            <td>$selectRizab</td>
          </tr>
          <tr>
            <td><div align="right"><font color="#FF0000">*</font>Kategori :</div></td>
            <td>$selectKategori</td>
          </tr>
        </table>        </td>
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
function validateNumber(elmnt,content) {
	//if it is character, then remove it..
	if (isNaN(content)) {
		elmnt.value = RemoveNonNumeric(content);
		return;
	}
}

function RemoveNonNumeric( strString )
{
      var strValidCharacters = "1234567890.,";
      var strReturn = "";
      var strBuffer = "";
      var intIndex = 0;
      // Loop through the string
      for( intIndex = 0; intIndex < strString.length; intIndex++ )
      {
            strBuffer = strString.substr( intIndex, 1 );
            // Is this a number
            if( strValidCharacters.indexOf( strBuffer ) > -1 )
            {
                  strReturn += strBuffer;
            }
      }
      return strReturn;
}

function Kembali() {
	window.close();
}
function seterusnya() {
	document.f2.action = "";
	document.f2.mode.value = "hakmilikview";
	document.f2.command.value = "Tanah";
	document.f2.submit();
}
function Kemaskini() {
	document.f2.action = "";
	document.f2.mode.value = "kemaskini";
	document.f2.command.value = "Tanah";
	document.f2.submit();
}
function Batal() {
	document.f2.action = "";
	if(document.f2.idHakmilikurusan.value == "")
		document.f2.mode.value = "baru";
	else
		document.f2.mode.value = "view";
	document.f2.command.value = "Tanah";
	document.f2.submit();
}
function Simpan() {
	if(document.f2.socNegeri.value == ""){
		alert('Sila pilih " Negeri " terlebih dahulu.');
  		document.f2.socNegeri.focus(); 
		return; 
	}
	if(document.f2.socDaerah.value == ""){
		alert('Sila pilih " Daerah " terlebih dahulu.');
  		document.f2.socDaerah.focus(); 
		return; 
	}
	if(document.f2.socMukim.value == ""){
		alert('Sila pilih " Bandar/Pekan/Mukim " terlebih dahulu.');
  		document.f2.socMukim.focus(); 
		return; 
	}
	if(document.f2.socJenisHakmilik.value == ""){
		alert('Sila pilih " Jenis Hakmilik " terlebih dahulu.');
  		document.f2.socJenisHakmilik.focus(); 
		return; 
	}
	if(document.f2.txtNoHakmilik.value == ""){
		alert('Sila masukkan " No. Hakmilik " terlebih dahulu.');
  		document.f2.txtNoHakmilik.focus(); 
		return; 
	}
	if(document.f2.txtKodLot.value == ""){
		alert('Sila masukkan " Kod Lot/No. " terlebih dahulu.');
  		document.f2.txtKodLot.focus(); 
		return; 
	}
	if(document.f2.socLot.value == ""){
		alert('Sila pilih " Kod Lot/No. " terlebih dahulu.');
  		document.f2.socLot.focus(); 
		return; 
	}
	if(document.f2.txdTarikhTerima.value == ""){
		alert('Sila masukkan " Tarikh Mula " terlebih dahulu.');
  		document.f2.txdTarikhTerima.focus(); 
		return; 
	}
	if(document.f2.txtLuas.value == ""){
		alert('Sila masukkan " Luas " terlebih dahulu.');
  		document.f2.txtLuas.focus(); 
		return; 
	}
	if(document.f2.socLuas.value == ""){
		alert('Sila pilih " Unit Luas " terlebih dahulu.');
  		document.f2.socLuas.focus(); 
		return; 
	}
	if(document.f2.txtNoPelan.value == ""){
		alert('Sila masukkan " No. Pelan Akui " terlebih dahulu.');
  		document.f2.txtNoPelan.focus(); 
		return; 
	}	
	if(document.f2.txdTarikhLuput.value == ""){
		alert('Sila masukkan " Tarikh Luput " terlebih dahulu.');
  		document.f2.txdTarikhLuput.focus(); 
		return; 
	}
//	if(document.f2.socStatus.value == ""){
//		alert('Sila pilih " Status " terlebih dahulu.');
//  		document.f2.socStatus.focus(); 
//		return; 
//	}
	if(document.f2.socRizab.value == ""){
		alert('Sila pilih " Rizab " terlebih dahulu.');
  		document.f2.socRizab.focus(); 
		return; 
	}
	if(document.f2.socKategori.value == ""){
		alert('Sila pilih " Kategori " terlebih dahulu.');
  		document.f2.socKategori.focus(); 
		return; 
	}
	if ( !window.confirm("Anda Pasti?") ) return;

	document.f2.action = "";
	document.f2.mode.value = "simpan";
	document.f2.command.value = "Tanah";
	document.f2.submit();
	//window.close();
}

document.forms[0].cmdKemaskini.style.display = document.f2.style1.value;
document.forms[0].cmdSimpan.style.display = document.f2.style2.value;
document.forms[0].cmdBatal.style.display = document.f2.style2.value;
document.forms[0].cmdKembali.style.display = document.f2.style1.value;
document.forms[0].cmdSeterusnya.style.display = document.f2.style1.value;

</script>