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
#set ($tajuk = "")
#set ($idFail = "")
#set ($noFail = "")
#set ($tSurat = "")
#set ($tPermohonan = "")
#set ($noRujukanKJP = "")
#set ($noRujukan = "")
#set ($tAgihan = "")
#set ($idAgensi = "")
#set ($idSuburusan = "")
#foreach ( $semak in $Semak )
	#set ($tajuk = $semak.tajuk)
    #set ($idFail = $semak.idFail)
    #set ($noFail = $semak.noFail)
    #set ($tSurat = $semak.tSurat)
    #set ($noRujukanKJP = $semak.noRujukanKJP)
    #set ($noRujukan = $semak.noRujukan)
    #set ($tAgihan = $semak.tAgihan)
    #set ($tPermohonan = $semak.tPermohonan)
    #set ($idSuburusan = $semakbaru.idSuburusan)
#end

#set ($btnName = "value='Kosongkan'")
#if ($IdPermohonan != "")
	#set ($btnName = "value='Batal'")
#end<strong>Pendaftaran Pembelian</strong><br>
#if ( $SimpanStatus == "success" )
    <table width="100%" border="0">
        <tr>
            <td>
            <font color="blue">
            <b>
            #set ($alertmsg = "")
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
<legend>Maklumat Semakan</legend>
<form name="f2" method="post">
  <table border="0" cellpadding="0" cellspacing="0" width="100%">
    <tbody>
      <tr>
		<td align="center" valign="top" width="50%"><table width="100%" border="0">
              <tr>
                <td width="3%"><div align="right"><font color="#FF0000">*</font></div></td>
                <td width="20%"><div align="left">Negeri</div></td>
                <td width="2%">:</td>
                <td width="75%">$selectNegeri</td>
              </tr>
              <tr>
                <td><div align="right"><font color="#FF0000">*</font></div></td>
                <td><div align="left">Kementerian</div></td>
                <td>:</td>
                <td>$selectKementerian</td>
          </tr>
              <tr>
                <td><div align="right"><font color="#FF0000">*</font></div></td>
                <td><div align="left">Agensi</div></td>
                <td>:</td>
                <td>$selectAgensi</td>
            </tr>
              <tr>
                <td><div align="right"><font color="#FF0000">*</font></div></td>
                <td><div align="left">Urusan</div></td>
                <td>:</td>
                <td>$selectSuburusan
                <input type="hidden" name="idSuburusan" value="$idSuburusan"></td>
              </tr>
              <tr style="display:none">
                <td><div align="right"><font color="#FF0000">*</font></div></td>
                <td><div align="left">Status Tanah</div></td>
                <td>:</td>
                <td>$selectSuburusan</td>
              </tr>
              <tr style="display:none">
                <td><div align="right"><font color="#FF0000">*</font></div></td>
                <td><div align="left">Jenis Fail</div></td>
                <td>:</td>
                <td>$selectSuburusan</td>
              </tr>
              <tr style="display:none">
                <td><div align="right"><font color="#FF0000">*</font></div></td>
                <td><div align="left">UP. Pegawai</div></td>
                <td>:</td>
                <td>$selectSuburusan</td>
              </tr>
              <tr>
                <td valign="top"><div align="right"><font color="#FF0000">*</font></div></td>
                <td valign="top"><div align="left">Tajuk</div></td>
                <td valign="top">:</td>
                <td><label>
                  <textarea name="txtTajuk" id="txtTajuk" cols="41" rows="3" onkeyup="this.value=this.value.toUpperCase();" $modeSoc >$tajuk</textarea>
                </label></td>
              </tr>
        </table></td>
        <td align="center" valign="top" width="45%"><table width="100%" border="0">
              <tr>
                <td width="3%"><div align="right"></div></td>
                <td width="33%"><div align="left">No. Fail Seksyen</div></td>
                <td width="2%">:</td>
                <td width="62%"><input type="text" name="txtNoFailSek" size="28" value="$noFail" readonly="readonly"></td>
              </tr>
              <tr>
                <td><div align="right"><font color="#FF0000">*</font></div></td>
                <td><div align="left">No. Fail KJP</div></td>
                <td>:</td>
                <td><input type="text" name="txtNoFailKJP" id="txtNoFailKJP" onkeyup="this.value=this.value.toUpperCase();" size="28" maxlength="50" value="$noRujukanKJP" $mode /></td>
            </tr>
              <tr style="display:none">
                <td><div align="right"><font color="#FF0000">*</font></div></td>
                <td><div align="left">No. Fail UPT</div></td>
                <td>:</td>
                <td><input type="text" name="txtNoFailUPT" id="txtNoFailUPT" size="28" maxlength="50" onkeyup="this.value=this.value.toUpperCase();" value="$noRujukan" $mode /></td>
            </tr>
              <tr>
                <td><div align="right"><font color="#FF0000">*</font></div></td>
                <td><div align="left">No. Fail Lain</div></td>
                <td>:</td>
                <td><input type="text" name="txtNoFailLain" id="txtNoFailLain" size="28" maxlength="50" onkeyup="this.value=this.value.toUpperCase();" value="$noRujukan" $mode /></td>
              </tr>
              <tr>
                <td><div align="right"><font color="#FF0000">*</font></div></td>
                <td><div align="left">Tarikh Surat KJP</div></td>
                <td>:</td>
                <td><input type="text" name="txdTarikhSurKJP" id="txdTarikhSurKJP" size="10" value="$tSurat" readonly="readonly">
                <img src="../img/calendar.gif" border="0" onClick="displayDatePicker('txdTarikhSurKJP',false,'dmy');" style="display:$Style2"></td>
              </tr>              
            <tr>
              <td><div align="right"><font color="#FF0000">*</font></div></td>
                <td><div align="left">Tarikh Permohonan</div></td>
                <td>:</td>
                <td><input type="text" name="txdTarikhPermohonan" id="txdTarikhPermohonan" size="10" value="$tPermohonan" readonly="readonly" />
                <img src="../img/calendar.gif" border="0" onClick="displayDatePicker('txdTarikhPermohonan',false,'dmy');" style="display:$Style2"></td>
              </tr>
              #if ($IdPermohonan == "")
              <tr>
                <td><div align="right"></div></td>
                <td><div align="left">Tarikh Buka Fail</div></td>
                <td>:</td>
                <td><input type="text" name="txdTarikhBukaFail" id="txdTarikhBukaFail" size="10" value="$datenow" readonly="readonly">
                <input type="button" style="display:none" onClick="displayDatePicker('txdTarikhBukaFail',false,'dmy');" value="Kalendar" $modeSoc/></td>
              </tr>
            #else
              <tr>
                <td><div align="right"></div></td>
                <td><div align="left">Tarikh Buka Fail</div></td>
                <td>:</td>
                <td><input type="text" name="txdTarikhBukaFail" id="txdTarikhBukaFail" size="10" value="$tAgihan" readonly="readonly">
                <input type="button" style="display:none" onClick="displayDatePicker('txdTarikhBukaFail',false,'dmy');" value="Kalendar" $modeSoc/></td>
              </tr>
            #end
        </table></td>
      </tr>
      <tr>
        <td colspan="2"><table width="100%" border="0">
          <tr>
            <td colspan="2"><hr size="2" width="100%"></td>
          </tr>
          <tr>
            <td colspan="2"><strong>Senarai Semakan Pembelian</strong></td>
          </tr>
          <tr>
            <td colspan="2"><table width="100%" border="0">
  <tr style="display:none">
    <td valign="top" align="right" width="30%">Status Tanah :</td>
    <td width="70%"><table width="100%" border="0">
      <tr>
        <td align="right" width="5%"><input type="checkbox" name="checkbox" id="checkbox" /></td>
        <td width="95%">Tanah Bermilik (No. Hakmilik hendaklah disertakan juga)</td>
      </tr>
      <tr>
        <td align="right"><input type="checkbox" name="checkbox2" id="checkbox2" /></td>
        <td>Tanah dalam proses berimilik</td>
      </tr>
    </table></td>
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
            <td class="$row" width="10" align="right">
                #if ( $semakclass.isSemakan("$IdPermohonan", "$semak.id" ))
                    #set ( $checked = "checked" )
                #else
                   #set ( $checked = "" )
                #end
                <input class="cb" type="checkbox" name="cbsemaks" value="$semak.id" $checked $modeSoc>
            </td>
            <td class="$row">
                $semak.keterangan
            </td>
        </tr>       
    #end
  <tr style="display:none">
    <td valign="top" align="right">Maklumat Tanah :</td>
    <td><table width="100%" border="0">
      <tr>
        <td align="right" width="5%"><input type="checkbox" name="checkbox3" id="checkbox3" /></td>
        <td width="95%">Carian Rasmi Hakmilik / Salinan Hakmilik Tanah</td>
      </tr>
      <tr>
        <td align="right"><input type="checkbox" name="checkbox4" id="checkbox4" /></td>
        <td>Tiada Bebanan</td>
      </tr>
    </table></td>
  </tr>
</table>
</td>
          </tr>
        </table></td>
      </tr>
      <tr>
		<td colspan="2" align="center"><input type="submit" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="Kemaskini()">&nbsp;&nbsp;<input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="Simpan()">&nbsp;&nbsp;<input type="reset" name="cmdBatal" id="cmdBatal" $btnName onclick="Batal()">&nbsp;&nbsp;<input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="Kembali()">&nbsp;&nbsp;<input type="button" name="cmdSeterusnya" id="cmdSeterusnya" value="Seterusnya" onclick="Seterusnya()">&nbsp;&nbsp;<input type="button" name="cmdPapar" id="cmdPapar" value="Papar Surat" style="display:none"></td>
      </tr>
    </tbody>
  </table>
  <input type="hidden" name="idFail" value="$idFail">
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
	document.f2.command.value = "";
	document.f2.submit();
}
function Seterusnya() {
	document.f2.action = "";
	document.f2.mode.value = "tanahview";
	document.f2.command.value = "Maklumat";
	document.f2.submit();
}
function Kemaskini() {
	document.f2.action = "";
	document.f2.mode.value = "kemaskini";
	document.f2.command.value = "Semakan";
	document.f2.submit();
}
function Batal() {
	document.f2.action = "";
//	if(document.f2.idPermohonan.value == "")
//		document.f2.mode.value = "baru";
//	else
		document.f2.mode.value = "view";
	document.f2.command.value = "Semakan";
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
	if(document.f2.socAgensi.value == ""){
		alert('Sila pilih " Agensi " terlebih dahulu.');
  		document.f2.socAgensi.focus(); 
		return; 
	}
	if(document.f2.socSuburusan.value == ""){
		alert('Sila pilih " Urusan " terlebih dahulu.');
  		document.f2.socSuburusan.focus(); 
		return; 
	}
	if(document.f2.txtNoFailKJP.value == ""){
		alert('Sila masukkan " No. Fail KJP " terlebih dahulu.');
  		document.f2.txtNoFailKJP.focus(); 
		return; 
	}
	if(document.f2.txdTarikhSurKJP.value == ""){
		alert('Sila masukkan " Tarikh Surat KJP " terlebih dahulu.');
  		document.f2.txdTarikhSurKJP.focus(); 
		return; 
	}
	if(document.f2.txtNoFailLain.value == ""){
		alert('Sila masukkan " No. Fail Lain " terlebih dahulu.');
  		document.f2.txtNoFailLain.focus(); 
		return; 
	}
	if(document.f2.txdTarikhPermohonan.value == ""){
		alert('Sila masukkan " Tarikh Permohonan " terlebih dahulu.');
  		document.f2.txdTarikhPermohonan.focus(); 
		return; 
	}
	
	if ( !window.confirm("Anda Pasti?") ) {
		return false;
		}
	document.f2.action = "";
	document.f2.mode.value = "simpan";
	document.f2.command.value = "Semakan";
	document.f2.submit();
}

function alertMsg(msg){
	alert(msg);
	return;
}

document.forms[0].cmdKemaskini.style.display = document.f2.style1.value;
document.forms[0].cmdSimpan.style.display = document.f2.style2.value;
document.forms[0].cmdBatal.style.display = document.f2.style2.value;
//document.forms[0].cmdKembali.style.display = document.f2.style1.value;
document.forms[0].cmdSeterusnya.style.display = document.f2.style1.value;

</script>