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
#set ($TajukFail = "")
#set ($NoFail = "")
#set ($NoRujukanLain = "")
#set ($IdNegeri = "")
#set ($NoHakmilik = "")
#set ($NoLot = "")
#set ($TarikhMula = "")
#set ($Cukai = "")
#set ($Lokasi = "")
#set ($NoPelan = "")
#set ($Syarat = "")
#set ($TarikhLuput = "")
#set ($CukaiTerkini = "")
#set ($Luas = "")
#set ($TarikhRizab = "")
#set ($NoRizab = "")
#set ($Noptk = "")
#set ($NoSyit = "")
#set ($Sekatan = "")
#set ($IdHakmilikurusan = "")
#foreach ( $penHamilik in $PenHamilik )
	#set ($TajukFail = $penHamilik.TajukFail)
    #set ($NoFail = $penHamilik.NoFail)
    #set ($NoRujukanLain = $penHamilik.NoRujukanLain)
    #set ($IdNegeri = $penHamilik.IdNegeri)
	#set ($NoHakmilik = $penHamilik.NoHakmilik)
    #set ($NoLot = $penHamilik.NoLot)
    #set ($TarikhMula = $penHamilik.TarikhMula)
    #set ($Cukai = $penHamilik.Cukai)
    #set ($Lokasi = $penHamilik.Lokasi)
    #set ($NoPelan = $penHamilik.NoPelan)
    #set ($Syarat = $penHamilik.Syarat)
    #set ($TarikhLuput = $penHamilik.TarikhLuput)
    #set ($CukaiTerkini = $penHamilik.CukaiTerkini)
    #set ($Luas = $penHamilik.Luas)
    #set ($TarikhRizab = $penHamilik.TarikhRizab)
    #set ($NoRizab = $penHamilik.NoRizab)
    #set ($Noptk = $penHamilik.Noptk)
    #set ($NoSyit = $penHamilik.NoSyit)
    #set ($Sekatan = $penHamilik.Sekatan)
    #set ($IdHakmilikurusan = $penHamilik.IdHakmilikurusan)
#end

#set ($btnName = "value='Kosongkan'")
#if ($IdHakmilikurusan != "")
	#set ($btnName = "value='Batal'")
#end

<strong> Pendaftaran Hakmilik </strong>
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
<legend>Maklumat Hakmilik</legend>
<form name="f2" method="post">
  <table border="0" cellpadding="0" cellspacing="0" width="100%">
    <tbody>
      <tr>
		<td align="center" valign="top" width="55%"><table width="100%" border="0">
              <tr>
                <td width="20%"><div align="right">Kementerian :</div></td>
                <td width="80%">$selectKementerian</td>
              </tr>
              <tr>
                <td><div align="right">Agensi :</div></td>
                <td>$selectAgensi</td>
              </tr>
              <tr>
                <td valign="top"><div align="right">Tajuk :</div></td>
                <td><label>
                <textarea name="Tajuk" id="Tajuk" cols="30" rows="3" disabled="disabled">$TajukFail </textarea>
                </label></td>
              </tr>
        </table></td>
        <td align="center" valign="top" width="40%"><table width="100%" border="0">
              <tr>
                <td width="33%"><div align="right">No. Fail Seksyen :</div></td>
                <td width="67%"><input type="text" name="txtNoFailSek" size="30" value="$NoFail" readonly="readonly"></td>
              </tr>
              <tr>
                <td><div align="right">No. Fail KJP :</div></td>
                <td><input type="text" name="txtNoFailLain" id="txtNoFailLain" value="$NoRujukanLain" readonly="readonly"></td>
              </tr>
              <tr>
                <td><div align="right">No. Fail PTG :</div></td>
                <td><input type="text" name="txtNoFailLain" id="txtNoFailLain" value="$NoRujukanLain" readonly="readonly"></td>
              </tr>
              <tr>
                <td><div align="right">No. Fail PTD :</div></td>
                <td><input type="text" name="txtNoFailLain" id="txtNoFailLain" value="$NoRujukanLain" readonly="readonly"></td>
              </tr>
        </table></td>
      </tr>
      <tr>
      	<td colspan="2"><hr size="2" width="100%"></td>
      </tr>
      <tr>
        <td><table width="100%" border="0">
          <tr>
            <td width="30%"><div align="right">Negeri :</div></td>
            <td width="70%">$selectNegeri
            <input type="hidden" name="idNegeri" value="$IdNegeri" /></td>
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
        <td><table width="100%" border="0">
          <tr>
            <td width="33%"><div align="right"><font color="#FF0000">*</font>No. Hakmilik :</div></td>
            <td width="67%"><label>
              <input type="text" name="txtNoHakmilik" id="txtNoHakmilik" maxlength="21" onkeyup="this.value=this.value.toUpperCase();" value="$NoHakmilik" $mode >
            </label></td>
          </tr>
          <tr>
            <td width="33%"><div align="right"><font color="#FF0000">*</font>No. Strata :</div></td>
            <td width="67%"><label>
              <input type="text" name="txtNoHakmilik" id="txtNoHakmilik" maxlength="21" onkeyup="this.value=this.value.toUpperCase();" value="$NoHakmilik" $mode >
            </label></td>
          </tr>
          <tr>
            <td><div align="right"><font color="#FF0000">*</font>Kod Lot/No. :</div></td>
            <td><table width="100%" border="0">
              <tr>
                <td width="40%"><label>
                  <input type="text" name="txtNoLot" id="txtKodLot" maxlength="20" onkeyup="this.value=this.value.toUpperCase();" value="$NoLot" $mode>
                </label></td>
                <td width="60%">$selectLot</td>
              </tr>
            </table></td>
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
            <td width="30%"><div align="right"><font color="#FF0000">*</font>Tarikh Terima Hakmilik :</div></td>
            <td width="70%"><input type="text" name="txdTarikhTerima" id="txdTarikhTerima" size="15" value="$TarikhMula" readonly="readonly">
            <img src="../img/calendar.gif" border="0" onClick="displayDatePicker('txdTarikhTerima',false,'dmy');" style="display:$Style2"></td>
          </tr>
          <tr>
            <td><div align="right">Cukai Tahun Pertama :</div></td>
            <td>RM 
              <label>
              <input type="text" name="txtCukaiPertama" id="txtCukaiPertama" maxlength="9" value="$Cukai" $mode>
            </label></td>
          </tr>
          <tr>
            <td><div align="right">Lokasi:</div></td>
            <td><label>
              <input type="text" name="txtLokasi" id="txtLokasi" maxlength="60" onkeyup="this.value=this.value.toUpperCase();" value="$Lokasi" $mode>
            </label></td>
          </tr>
          <tr>
            <td><div align="right"><font color="#FF0000">*</font>Unit Luas :</div></td>
            <td>$selectLuas</td>
          </tr>
          <tr>
            <td><div align="right">No. Pelan Akui :</div></td>
            <td><label>
              <input type="text" name="txtNoPelan" id="txtNoPelan" maxlength="50" onkeyup="this.value=this.value.toUpperCase();" value="$NoPelan" $mode>
            </label></td>
          </tr>
          <tr>
            <td><div align="right"><font color="#FF0000">*</font>Rizab :</div></td>
            <td>$selectRizab</td>
          </tr>
          <tr>
            <td><div align="right"><font color="#FF0000">*</font>Kategori :</div></td>
            <td>$selectKategori</td>
          </tr>
          <tr>
            <td valign="top"><div align="right">Syarat Nyata :</div></td>
            <td><label>
              <textarea name="txtSyarat" id="txtSyarat" cols="40" rows="3" onkeyup="this.value=this.value.toUpperCase();" $modeSoc>$Syarat</textarea>
            </label></td>
          </tr>
        </table>
        </td>
      <td valign="top"><table width="100%" border="0">
          <tr>
            <td width="35%"><div align="right"><font color="#FF0000">*</font>Tarikh Luput :</div></td>
            <td width="65%"><input type="text" name="txdTarikhLuput" id="txdTarikhLuput" size="15" value="$TarikhLuput" readonly="readonly">
            <img src="../img/calendar.gif" border="0" onClick="displayDatePicker('txdTarikhLuput',false,'dmy');" style="display:$Style2"></td>
          </tr>
          <tr>
            <td><div align="right">Cukai Semasa :</div></td>
            <td>RM 
              <label>
              <input type="text" name="txtCukaiSemasa" id="txtCukaiSemasa" maxlength="16" value="$CukaiTerkini" $mode>
            </label></td>
          </tr>
          <tr>
            <td><div align="right"><font color="#FF0000">*</font>Luas :</div></td>
            <td><label>
              <input type="text" name="txtLuas" id="txtLuas" maxlength="40" value="$Luas" $mode>
            </label></td>
          </tr>
          <tr>
            <td><div align="right"><font color="#FF0000">*</font>Tarikh Warta :</div></td>
            <td><input type="text" name="txdTarikhWarta" id="txdTarikhWarta" size="15" value="$TarikhRizab" readonly="readonly" />
              <img src="../img/calendar.gif" border="0" onClick="displayDatePicker('txdTarikhWarta',false,'dmy');" style="display:$Style2"></td>
          </tr>
          <tr>
            <td><div align="right"><font color="#FF0000">*</font>No.Warta :</div></td>
            <td><label>
              <input type="text" name="txtNoWarta" id="txtNoWarta" maxlength="10" onkeyup="this.value=this.value.toUpperCase();" value="$NoRizab" $mode>
            </label></td>
          </tr>
          <tr>
            <td><div align="right">No. PU :</div></td>
            <td><label>
              <input type="text" name="txtNoPU" id="txtNoPU" maxlength="5" onkeyup="this.value=this.value.toUpperCase();" value="$Noptk" $mode>
            </label></td>
          </tr>
          <tr>
            <td><div align="right">No. Syit Piawai :</div></td>
            <td><label>
              <input type="text" name="txtNoSyit" id="txtNoSyit" maxlength="50" onkeyup="this.value=this.value.toUpperCase();" value="$NoSyit" $mode>
            </label></td>
          </tr>
          <tr>
            <td valign="top"><div align="right">Sekatan Kepentingan :</div></td>
            <td><label>
              <textarea name="txtSekatan" id="txtSekatan" cols="40" rows="3" onkeyup="this.value=this.value.toUpperCase();" $modeSoc>$Sekatan</textarea>
            </label></td>
          </tr>
        </table>
        </td>
      </tr>
      <tr>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      </tr>
      <tr>
		<td colspan="2" align="center"><input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="Kemaskini()">&nbsp;&nbsp;<input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="Simpan()">&nbsp;&nbsp;<input type="reset" name="cmdBatal" id="cmdBatal" $btnName onclick="Batal()">&nbsp;&nbsp;<input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="Kembali()">&nbsp;&nbsp;<input type="button" name="cmdSeterusnya" id="cmdSeterusnya" value="Seterusnya" onclick="seterusnya()"></td>
      </tr>
      
    </tbody>
  </table>  
  <input type="hidden" name="idHakmilikurusan" value="$IdHakmilikurusan">
  <input type="hidden" name="idFail" value="$IdFail">
  <input type="hidden" name="noFail" value="$NoFail">
  <input type="hidden" name="idPermohonan" value="$IdPermohonan">
  <input type="hidden" name="command">
  <input type="hidden" name="mode">
  <input type="hidden" name="style1" value="$Style1">
  <input type="hidden" name="style2" value="$Style2">
</form>
</fieldset>
<script>
function Kembali() {
	document.f2.action = "";
	document.f2.command.value = "SenaraiPermohonan"
	document.f2.submit();
}
function seterusnya() {
	document.f2.action = "";
	document.f2.mode.value = "hakmilikview";
	document.f2.command.value = "Hakmilik"
	document.f2.submit();
}
function Kemaskini() {
	document.f2.action = "";
	document.f2.mode.value = "kemaskini";
	document.f2.command.value = "PenHakmilik"
	document.f2.submit();
}
function Batal() {
	document.f2.action = "";
	document.f2.mode.value = "";
	document.f2.command.value = "PenHakmilik"
	document.f2.submit();
}
function Simpan() {
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
		alert('Sila masukkan " Tarikh Terima Hakmilik " terlebih dahulu.');
  		document.f2.txdTarikhTerima.focus(); 
		return; 
	}
	if(document.f2.socLuas.value == ""){
		alert('Sila pilih " Unit Luas " terlebih dahulu.');
  		document.f2.socLuas.focus(); 
		return; 
	}
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
	if(document.f2.txdTarikhLuput.value == ""){
		alert('Sila masukkan " Tarikh Luput " terlebih dahulu.');
  		document.f2.txdTarikhLuput.focus(); 
		return; 
	}
	if(document.f2.txtLuas.value == ""){
		alert('Sila masukkan " Luas " terlebih dahulu.');
  		document.f2.txtLuas.focus(); 
		return; 
	}
	if(document.f2.txdTarikhWarta.value == ""){
		alert('Sila masukkan " Tarikh Warta " terlebih dahulu.');
  		document.f2.txdTarikhWarta.focus(); 
		return; 
	}
	if(document.f2.txtNoWarta.value == ""){
		alert('Sila masukkan " No. Warta " terlebih dahulu.');
  		document.f2.txtNoWarta.focus(); 
		return; 
	}
	if ( !window.confirm("Anda Pasti?") ) return;

	document.f2.action = "";
	document.f2.mode.value = "simpan";
	document.f2.command.value = "PenHakmilik"
	document.f2.submit();
}

document.forms[0].cmdKemaskini.style.display = document.f2.style1.value;
document.forms[0].cmdSimpan.style.display = document.f2.style2.value;
document.forms[0].cmdBatal.style.display = document.f2.style2.value;
document.forms[0].cmdKembali.style.display = document.f2.style1.value;
document.forms[0].cmdSeterusnya.style.display = document.f2.style1.value;

</script>