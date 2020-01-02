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
#end

<strong>Penerimaan Permohonan</strong><br>
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
<legend>Maklumat Semakan</legend>
  <table border="0" cellpadding="0" cellspacing="0" width="100%">
    <tbody>
      <tr>
		<td align="center" valign="top" width="50%"><table width="100%" border="0">
              <tr>
                <td width="3%"><div align="right"><font color="#FF0000">*</font></div></td>
                <td width="20%"><div align="left"><strong>Negeri</strong></div></td>
                <td width="2%">:</td>
                <td width="75%">$selectNegeri</td>
              </tr>
              <tr>
                <td><div align="right"><font color="#FF0000">*</font></div></td>
                <td><div align="left"><strong>Kementerian</strong></div></td>
                <td>:</td>
                <td>$selectKementerian</td>
          </tr>
              <tr>
                <td><div align="right"><font color="#FF0000">*</font></div></td>
                <td><div align="left"><strong>Agensi</strong></div></td>
                <td>:</td>
                <td>$selectAgensi</td>
            </tr>
              <tr>
                <td><div align="right"><font color="#FF0000"></font></div></td>
                <td><div align="left"><strong>Urusan</strong></div></td>
                <td>:</td>
                <td>$selectUrusan</td>
              </tr>
              <tr>
                <td><div align="right"></div></td>
                <td><div align="left"><strong>Sub Urusan</strong></div></td>
                <td>:</td>
                <td>$selectSuburusan
                <input type="hidden" name="idSuburusan" value="$idSuburusan" /></td>
              </tr>
              <tr>
                <td><div align="right"><font color="#FF0000">*</font></div></td>
                <td><div align="left"><strong>Jenis Tanah</strong></div></td>
                <td>:</td>
                <td>$selectJenisTanah</td>
              </tr>
              <tr style="display:none">
                <td><div align="right"><font color="#FF0000">*</font></div></td>
                <td><div align="left"><strong>Jenis Fail</strong></div></td>
                <td>:</td>
                <td>$selectSuburusan</td>
              </tr>
              <tr style="display:none">
                <td><div align="right"><font color="#FF0000">*</font></div></td>
                <td><div align="left"><strong>UP. Pegawai</strong></div></td>
                <td>:</td>
                <td>$selectSuburusan</td>
              </tr>
              <tr>
                <td valign="top"><div align="right"><font color="#FF0000">*</font></div></td>
                <td valign="top"><div align="left"><strong>Tajuk</strong></div></td>
                <td valign="top">:</td>
                <td><label>
                  <textarea name="txtTajuk" id="txtTajuk" cols="41" rows="3" onkeyup="this.value=this.value.toUpperCase();" $modeSoc >$tajuk</textarea>
                </label></td>
              </tr>
        </table></td>
        <td align="center" valign="top" width="45%"><table width="100%" border="0">
              <tr>
                <td width="3%"><div align="right"></div></td>
                <td width="33%"><div align="left"><strong>No. Fail Seksyen</strong></div></td>
                <td width="2%">:</td>
                <td width="62%"><input type="text" name="txtNoFailSek" size="28" value="$noFail" readonly="readonly"></td>
              </tr>
              <tr>
                <td><div align="right"><font color="#FF0000">*</font></div></td>
                <td><div align="left"><strong>No. Fail KJP</strong></div></td>
                <td>:</td>
                <td><input type="text" name="txtNoFailKJP" id="txtNoFailKJP" onkeyup="this.value=this.value.toUpperCase();" size="28" maxlength="50" value="$noRujukanKJP" $mode /></td>
              </tr>
              <tr style="display:none">
                <td><div align="right"><font color="#FF0000">*</font></div></td>
                <td><div align="left"><strong>No. Fail UPT</strong></div></td>
                <td>:</td>
                <td><input type="text" name="txtNoFailUPT" id="txtNoFailUPT" size="28" maxlength="50" onkeyup="this.value=this.value.toUpperCase();" value="$noRujukan" $mode /></td>
            </tr>
              <tr>
                <td><div align="right"><font color="#FF0000">*</font></div></td>
                <td><div align="left"><strong>No. Fail Lain</strong></div></td>
                <td>:</td>
                <td><input type="text" name="txtNoFailLain" id="txtNoFailLain" size="28" maxlength="50" onkeyup="this.value=this.value.toUpperCase();" value="$noRujukan" $mode /></td>
            </tr>
              <tr>
                <td><div align="right"><font color="#FF0000">*</font></div></td>
                <td><div align="left"><strong>Tarikh Surat KJP</strong></div></td>
                <td>:</td>
                <td><input type="text" name="txdTarikhSurKJP" id="txdTarikhSurKJP" size="10" value="$tSurat" readonly="readonly">
                <img src="../img/calendar.gif" border="0" onClick="displayDatePicker('txdTarikhSurKJP',false,'dmy');" style="display:$Style2"></td>
              </tr>              
            <tr>
              <td><div align="right"><font color="#FF0000">*</font></div></td>
                <td><div align="left"><strong>Tarikh Permohonan</strong></div></td>
                <td>:</td>
                <td><input type="text" name="txdTarikhPermohonan" id="txdTarikhPermohonan" size="10" value="$tPermohonan" readonly="readonly" />
                <img src="../img/calendar.gif" border="0" onClick="displayDatePicker('txdTarikhPermohonan',false,'dmy');" style="display:$Style2"></td>
              </tr>
              #if ($IdPermohonan == "")
              <tr>
                <td><div align="right"></div></td>
                <td><div align="left"><strong>Tarikh Buka Fail</strong></div></td>
                <td>:</td>
                <td><input type="text" name="txdTarikhBukaFail" id="txdTarikhBukaFail" size="10" value="$datenow" readonly="readonly">
                <input type="button" style="display:none" onClick="displayDatePicker('txdTarikhBukaFail',false,'dmy');" value="Kalendar" $modeSoc/></td>
              </tr>
            #else
              <tr>
                <td><div align="right"></div></td>
                <td><div align="left"><strong>Tarikh Buka Fail</strong></div></td>
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
            <td colspan="2"><strong>Senarai Semakan Permohonan Tanah</strong></td>
          </tr>
          <!--start tab coding -->
          <tr>
            <td colspan="2">
            
            <div id="TabbedPanels1" class="TabbedPanels">
              <ul class="TabbedPanelsTabGroup">
                <li class="TabbedPanelsTab" title="Semakan" onclick="setSelected(0)"><strong><font size="1">Senarai Semakan Pajakan</font></strong></li>
                <li class="TabbedPanelsTab" title="Hakmilik" onclick="setSelected(1);Hakmilik()"><strong><font size="1">Semakan Hakmilik</font></strong> </li>
              </ul>
              <div class="TabbedPanelsContentGroup">
                <div class="TabbedPanelsContent">
                <table width="100%" border="0">
                   			#set ( $checked = "" )
                            #foreach ( $semak in $senaraiSemakan )
                                #set( $i = $velocityCount )
                                #if ( ($i % 2) == 0 )
                                    #set( $row = "row2" )
                                #else
                                    #set( $row = "row1" )
                                #end
                                <tr>
                                  <td class="$row" width="30%" align="right">
                                        #if ( $semakclass.isSemakan("$IdPermohonan", "$semak.id" ))
                                            #set ( $checked = "checked" )
                                        #else
                                           #set ( $checked = "" )
                                        #end
                                        <input class="cb" type="checkbox" name="cbsemaks" value="$semak.id" $checked $modeSoc>
                                  </td>
                                    <td class="$row" width="70%">
                                        $semak.keterangan
                                  </td>
                                </tr>       
                            #end
                          <tr>
                            <td><div align="right"><strong></strong></div></td>
                            <td>&nbsp;</td>
                          </tr>
                          <tr>
                            <td colspan="2"><div align="center"><input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="Kemaskini()">&nbsp;&nbsp;<input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="Simpan()">&nbsp;&nbsp;<input type="reset" name="cmdBatal" id="cmdBatal" $btnName onclick="Batal()">&nbsp;&nbsp;<input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="Kembali()"></div></td>
                          </tr>
                          <tr>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                          </tr>
                    </table>
                    </div>
                <div class="TabbedPanelsContent">
                <table width="100%" border="0">
                          
                          <tr>
                            <td width="35%" height="28"><div align="right"><strong>Daerah :</strong></div></td>
                            <td width="65%">$selectDaerah</td>
                          </tr>
                          <tr>
                            <td height="28"><div align="right"><strong>Mukim / Pekan / Bandar :</strong></div></td>
                            <td>$selectMukim</td>
                          </tr>
                          <tr>
                            <td height="28"><div align="right"><strong>Jenis Hakmilik :</strong></div></td>
                            <td>$selectJenishakmilik</td>
                          </tr>
                          <tr>
                            <td height="28"><div align="right"><strong>No. Hakmilik :</strong></div></td>
                            <td><input type="text" name="txtNoHakmilik" size="45" maxlength="50" onkeyup="this.value=this.value.toUpperCase();" value="$NoHakmilik" /></td>
                  </tr>
                          <tr>
                            <td height="28"><div align="right"></div></td>
                            <td><input style="width:80px" type="button" name="cmdCari" id="cmdCari" value="Cari" onclick="HakmilikSearch()" />&nbsp;
                            <input type="button" name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" onclick="cancel()"></td>
                          </tr>
                          <tr>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                          </tr>
                          <tr>
                            <td colspan="2"><table width="100%" border="0">
                              <tr class="table_header">
                                <td width="13%" scope="col"><strong>Negeri</strong></td>
                                <td width="15%" scope="col"><strong>Daerah</strong></td>
                                <td width="17%" scope="col"><strong>Mukim/Pekan/Bandar</strong></td>
                                <!-- <td width="13%" scope="col"><strong>Jenis Hakmilik</strong></td> -->
                                <td width="12%" scope="col"><strong>No. Hakmilik</strong></td>
                                <td width="17%" scope="col"><strong>Pegangan Hakmilik</strong></td>
                                <td width="13%" scope="col" style="display:none"><strong>Tarikh Daftar Pajakan</strong></td>
                              </tr>
                              #set ($count = 0)
                              #foreach ( $hakmilik in $SenaraiHakmilik )
                                #set ($count = $count+1)
                                  #set( $i = $velocityCount )
                                  #if ( ($i % 2) != 1 )
                                       #set( $row = "row2" )
                                  #else
                                       #set( $row = "row1" )
                                  #end
                              <tr style="display:$style1">
                                <td class="$row">$hakmilik.namaNegeri</td>
                                <td class="$row">$hakmilik.namaDaerah</td>
                                <td class="$row">$hakmilik.namaMukim</td>
                                <!-- <td class="$row">$hakmilik.kodJenisHakmilik</td> -->
                                <td class="$row"><a href="javascript:seterusnyaPemohon('$hakmilik.idHakmilikurusan')" class="style1">$hakmilik.kodJenisHakmilik$hakmilik.noHakmilik</a></td>
                                <td class="$row">$hakmilik.peganganHakmilik</td>
                                <td class="$row" style="display:none">$hakmilik.tarikhMasuk</td>
                              </tr>
                              <tr style="display:$style2">
                                <td class="$row">$hakmilik.namaNegeri</td>
                                <td class="$row">$hakmilik.namaDaerah</td>
                                <td class="$row">$hakmilik.namaMukim</td>
                                <!-- <td class="$row">$hakmilik.kodJenisHakmilik</td> -->
                                <td class="$row"><a href="javascript:seterusnyaPemohonBaru('$hakmilik.idHakmilik')" class="style1">$hakmilik.kodJenisHakmilik $hakmilik.noHakmilik</a></td>
                                <td class="$row">$hakmilik.peganganHakmilik</td>
                                <td class="$row" style="display:none">$hakmilik.tarikhMasuk</td>
                              </tr>
                              #end
                              #if ($count == 0)
                              <tr> 
                                <td colspan="6" scope="row"><font color="#FF0000">Tiada Rekod.</font></td>
                              </tr>
                              #end
                            </table></td>
                          </tr>
                    </table>
              </div>
              </div>
            </div>            </td>
          </tr>
          <!--end tab coding -->
        </table></td>
      </tr>
    </tbody>
  </table>
  <input name="tabId" type="hidden" id="tabId" value="$selectedTab"/>
  <input type="hidden" name="idFail" value="$idFail">
  <input type="hidden" name="idPermohonan" value="$IdPermohonan">
  <input type="hidden" name="idHakmilik" value="">
  <input type="hidden" name="idHakmilikurusan" value="">
  <input type="hidden" name="commander" value="$commander">  
  <input type="hidden" name="mode">
  <input type="hidden" name="style1" value="$Style1">
  <input type="hidden" name="style2" value="$Style2">
</fieldset>
<script>
<!--
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$selectedTab});
//-->
function Kembali() {
	document.${formName}.action = "";
	document.${formName}.commander.value = "";
	document.${formName}.submit();
}
function Seterusnya() {
	document.${formName}.action = "";
	document.${formName}.mode.value = "tanahview";
	document.${formName}.commander.value = "Maklumat";
	document.${formName}.submit();
}
function Kemaskini() {
	document.${formName}.action = "";
	document.${formName}.mode.value = "kemaskini";
	document.${formName}.commander.value = "FailBaru";
	document.${formName}.submit();
}
function Batal() {
	document.${formName}.action = "";
	if(document.${formName}.idPermohonan.value == "")
		document.${formName}.mode.value = "baru";
	else
		document.${formName}.mode.value = "view";
	document.${formName}.commander.value = "FailBaru";
	document.${formName}.submit();
}
function Simpan() {
	if(document.${formName}.socNegeri.value == ""){
		alert('Sila pilih " Negeri " terlebih dahulu.');
  		document.${formName}.socNegeri.focus(); 
		return; 
	}
	if(document.${formName}.socKementerian.value == ""){
		alert('Sila pilih " Kementerian " terlebih dahulu.');
  		document.${formName}.socKementerian.focus(); 
		return; 
	}
	if(document.${formName}.socAgensi.value == ""){
		alert('Sila pilih " Agensi " terlebih dahulu.');
  		document.${formName}.socAgensi.focus(); 
		return; 
	}
	if(document.${formName}.socJenisTanah.value == ""){
		alert('Sila pilih " Jenis Tanah " terlebih dahulu.');
  		document.${formName}.socJenisTanah.focus(); 
		return; 
	}
	if(document.${formName}.txtTajuk.value == ""){
		alert('Sila masukkan " Tajuk " terlebih dahulu.');
  		document.${formName}.txtTajuk.focus(); 
		return; 
	}
	if(document.${formName}.txtNoFailKJP.value == ""){
		alert('Sila masukkan " No. Fail KJP " terlebih dahulu.');
  		document.${formName}.txtNoFailKJP.focus(); 
		return; 
	}
	if(document.${formName}.txtNoFailLain.value == ""){
		alert('Sila masukkan " No. Fail Lain " terlebih dahulu.');
  		document.${formName}.txtNoFailLain.focus(); 
		return; 
	}
	if(document.${formName}.txdTarikhSurKJP.value == ""){
		alert('Sila masukkan " Tarikh Surat KJP " terlebih dahulu.');
  		document.${formName}.txdTarikhSurKJP.focus(); 
		return; 
	}
	if(document.${formName}.txdTarikhPermohonan.value == ""){
		alert('Sila masukkan " Tarikh Permohonan " terlebih dahulu.');
  		document.${formName}.txdTarikhPermohonan.focus(); 
		return; 
	}
	if ( !window.confirm("Anda Pasti?") ) return;
	document.${formName}.action = "";
	document.${formName}.mode.value = "simpan";
	document.${formName}.commander.value = "FailBaru";
	document.${formName}.submit();
}
function setSelected(tabId) {
    document.${formName}.tabId.value = tabId;
}

function doChangeKementerian() {
 	doAjaxCall${formName}("doChangeKementerian");
}
//end 
//hakmilik tab's 
function seterusnyaPemohon(idHakmilikurusan){
	document.${formName}.tabId.value = 0;
	document.${formName}.idHakmilikurusan.value = idHakmilikurusan;
	document.${formName}.action = "";
	document.${formName}.mode.value = "pemohonview";
	document.${formName}.commander.value = "Pemohon";
	document.${formName}.submit();
}
function seterusnyaPemohonBaru(idHakmilik){
	document.${formName}.tabId.value = 0;
	document.${formName}.idHakmilik.value = idHakmilik;
	document.${formName}.action = "";
	document.${formName}.mode.value = "pemohonview";
	document.${formName}.commander.value = "Pemohon";
	document.${formName}.submit();
}
function cancel() {
	document.${formName}.reset();
	document.${formName}.socJenishakmilik.value = "";
	document.${formName}.socMukim.value = "";
	document.${formName}.socDaerah.value = "";
	document.${formName}.txtNoHakmilik.value = "";
	document.${formName}.socDaerah.focus();
}

function Hakmilik(){
	if(document.${formName}.idPermohonan.value == "")
		document.${formName}.tabId.value = '0';
	else{
		document.${formName}.action = "";
		document.${formName}.mode.value = "";
		document.${formName}.commander.value = "Hakmilik";
	}
	document.${formName}.submit();
}
function HakmilikSearch(){
	if(document.${formName}.idPermohonan.value == "")
		document.${formName}.tabId.value = '0';
	else{
		document.${formName}.action = "";
		document.${formName}.mode.value = "cari";
		document.${formName}.commander.value = "Hakmilik";
	}
	document.${formName}.submit();
}
function doChangeDaerah() {
 	doAjaxCall${formName}("doChangeDaerah");
}
//end hakmilik tab's

document.${formName}.cmdKemaskini.style.display = document.${formName}.style1.value;
document.${formName}.cmdSimpan.style.display = document.${formName}.style2.value;
document.${formName}.cmdBatal.style.display = document.${formName}.style2.value;
//document.forms[0].cmdKembali.style.display = document.f2.style1.value;
document.${formName}.cmdSeterusnya.style.display = document.${formName}.style1.value;

</script>