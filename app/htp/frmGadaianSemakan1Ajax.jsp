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

<strong> Pendaftaran Gadaian</strong>
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
  <table border="0" cellpadding="0" cellspacing="0" width="100%">
    <tbody>
      <tr>
		<td align="center" valign="top" width="50%"><table width="100%" border="0">
              <tr>
                <td width="3%"><div align="right"><font color="#FF0000">*</font></div></td>
                <td width="20%"><div align="left"><strong>Negeri</strong></div></td>
                <td width="2%" valign="top">:</td>
                <td width="75%">$selectNegeri</td>
              </tr>
              <tr>
                <td><div align="right"><font color="#FF0000">*</font></div></td>
                <td><div align="left"><strong>Kementerian</strong></div></td>
                <td valign="top">:</td>
                <td>$selectKementerian</td>
          </tr>
              <tr>
                <td><div align="right"><font color="#FF0000">*</font></div></td>
                <td><div align="left"><strong>Urusan</strong></div></td>
                <td>:</td>
                <td>$selectSuburusan                </td>
            </tr>
              <tr>
                <td valign="top"><div align="right"><font color="#FF0000">*</font></div></td>
                <td valign="top"><div align="left"><strong>Tajuk </strong></div></td>
                <td valign="top">:</td>
                <td><label>
                  <textarea name="txtTajuk" id="Tajuk" cols="45" rows="3" onkeyup="this.value=this.value.toUpperCase();" $modeSoc>$tajuk </textarea>
                </label></td>
              </tr>
        </table></td>
        <td align="center" valign="top" width="50%"><table width="100%" border="0">
              <tr>
                <td width="28%"><div align="left"><strong>No. Fail Seksyen</strong></div></td>
                <td width="2%">:</td>
                <td width="70%"><input type="text" name="txtNoFailSek" size="40" maxlength="400" value="$noFail"></td>
              </tr>
              
             #if ($idFail == "")
             <tr>
               <td><div align="left"><strong>Tarikh Daftar Fail</strong></div></td>
                <td>:</td>
                <td><input type="text" name="txdTarikhBukaFail" id="txdTarikhBukaFail" size="15" value="$datenow" />
               <input type="button" style="display:none" onClick="displayDatePicker('txdTarikhBukaFail',false,'dmy');" value="Kalendar" $modeSoc/>                </td>
              </tr>
             #else
             <tr>
               <td><div align="left"><strong>Tarikh Daftar Fail</strong></div></td>
                <td>:</td>
                <td>
                <input type="text" name="txdTarikhBukaFail" id="txdTarikhBukaFail" size="15" value="$tarikhDaftarFail">
               <input type="button" style="display:none" onClick="displayDatePicker('txdTarikhBukaFail',false,'dmy');" value="Kalendar" $modeSoc/>                </td>
              </tr>
             #end
        </table></td>
      </tr>
      <tr>
        <td colspan="2">&nbsp;</td>
      </tr>
      <tr>
		<td colspan="2" align="center"><input type="submit" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="Kemaskini()">&nbsp;&nbsp;<input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="Simpan()">&nbsp;&nbsp;<input type="reset" name="cmdBatal" id="cmdBatal" $btnName onclick="Batal()">&nbsp;&nbsp;<input type="button" style="display:none" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="Kembali()" />&nbsp;&nbsp;<input type="button" name="cmdSeterusnya" id="cmdSeterusnya" value="Seterusnya" onclick="Seterusnya()"></td>
      </tr>
    </tbody>
  </table>
  <input type="hidden" name="idFail" value="$idFail">
  <input type="hidden" name="noFail" value="$noFail">
<!--  <input type="hidden" name="command">
  <input type="hidden" name="mode"> -->
  <input type="hidden" name="style1" value="$Style1">
  <input type="hidden" name="style2" value="$Style2">

</fieldset>
<script>
function Kembali() {
	document.f2.action = "";
	document.f2.command.value = "";
	document.f2.submit();
}
function Seterusnya() {
	document.f2.action = "";
	//document.f2.command.value = "PenHakmilik";
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