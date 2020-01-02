#set ($idFail = "")
#set ($tajuk = "")
#set ($noFail = "")
##set ($tSurat = "")
#set ($noRujukan = "")
##set ($tAgihan = "")
#set ($tarikhDaftarFail = "")
#set ($idUrusan = "")
#set ($idnegeri = "")

#foreach ( $semak in $Semak )
    #set ($idFail = $semak.idFail)
	#set ($tajuk = $semak.tajuk)
    #set ($noFail = $semak.noFail)
    ##set ($tSurat = $semak.tSurat)
    #set ($noRujukan = $semak.noRujukan)
    ##set ($tAgihan = $semak.tAgihan)
    #set ($tarikhDaftarFail = $semak.tarikhDaftarFail)
    #set ($idUrusan = $semak.idSuburusan)
	#set ($idnegeri = $semak.idNegeri)
    
#end

#set ($btnName = "value='Kosongkan'")
#if ($idFail != "")
	#set ($btnName = "value='Batal'")
#end

<strong> Pendaftaran Gadaian</strong>
<br>
#if ( $SimpanStatus == "success" )
<!--
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
    -->
#end
<br>
<fieldset>
<legend>Maklumat Fail</legend>
  <table border="0" cellpadding="0" cellspacing="0" width="100%">
    <tbody>
      <tr>
		<td align="center" valign="top" width="50%"><table width="100%" border="0">
		  <tr>
		    <td><div align="right"><font color="#FF0000">*</font></div></td>
		    <td><div align="left"><strong>Urusan</strong></div></td>
		    <td>:</td>
		    <td>$selectSuburusan</td>
		    </tr>
		  <tr>
		    <td><div align="right"><font color="#FF0000">*</font></div></td>
		    <td><strong>Agensi</strong></td>
		    <td valign="top">:</td>
		    <td>$selectAgensi</td>
		    </tr>
              <tr>
                <td width="3%"><div align="right"><font color="#FF0000">*</font></div></td>
                <td width="20%"><div align="left"><strong>Negeri</strong></div></td>
                <td width="2%" valign="top">:</td>
                <td width="75%">$selectNegeri</td>
              </tr>
              <tr>
                <td valign="top"><div align="right"><font color="#FF0000">*</font></div></td>
                <td valign="top"><div align="left"><strong>Tajuk </strong></div></td>
                <td valign="top">:</td>
                <td>$selectTajuk</td>
              </tr>
              <tr>
                <td valign="top">&nbsp;</td>
                <td valign="top">&nbsp;</td>
                <td valign="top">&nbsp;</td>
                <td>&nbsp;</td>
              </tr>
        </table></td>
        <td align="center" valign="top" width="50%"><table width="100%" border="0">
              <tr>
                <td width="3%"><div align="right"><font color="#FF0000">*</font></div></td>
                <td width="31%"><div align="left"><strong>No. Fail Seksyen</strong></div></td>
                <td width="2%">:</td>
                <td width="64%">
                 #if ($idFail == "")
                <input type="text" name="txtNoFailSek" size="40" maxlength="400" class="" value="" onkeyup="this.value=this.value.toUpperCase();">
                #else
                
                $noFail
 
                </td>
                #end              
                
                
                
              </tr>
              
             #if ($idFail == "")
             <tr>
               <td>&nbsp;</td>
               <td><div align="left"><strong>Tarikh Daftar Fail</strong></div></td>
                <td>:</td>
                <td><input type="text" name="txdTarikhBukaFail" id="txdTarikhBukaFail" size="15" value="$datenow" />
               <input type="button" style="display:none" onClick="displayDatePicker('txdTarikhBukaFail',false,'dmy');" value="Kalendar" $modeSoc/>                </td>
              </tr>
             #else
             <tr>
               <td>&nbsp;</td>
               <td><div align="left"><strong>Tarikh Daftar Fail</strong></div></td>
                <td>:</td>
                <td>
                $tarikhDaftarFail
                </td>
              </tr>
             #end
        </table>
        </td>
      </tr>
      <tr>
        <td colspan="2">&nbsp;</td>
      </tr>
      <tr>
      <td colspan="2" align="center">
       #if ($idFail == "")
		&nbsp;&nbsp;
        <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onClick="javascript:fGS2A_Simpan()" />&nbsp;&nbsp;
        <input type="reset" name="cmdBatal" id="cmdBatal" $btnName onClick="javascript:fGS2A_Batal()" />
        &nbsp;&nbsp;<input type="button" style="display:none" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="javascript:fGS2A_Kembali()" />&nbsp;&nbsp;
        
        #else
        <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onClick="jaavascript:fGS2A_Kemaskini()" />	&nbsp;&nbsp;
        <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onClick="javascript:fGS2A_Simpan()" />&nbsp;&nbsp;
        <input type="reset" name="cmdBatal" id="cmdBatal" $btnName onClick="javascript:fGS2A_Batal()" />
        &nbsp;&nbsp;<input type="button" style="display:none" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="javascript:fGS2A_Kembali()" />&nbsp;&nbsp;<input type="button" name="cmdSeterusnya" id="cmdSeterusnya" value="Seterusnya" onClick="javascript:fGS2A_Seterusnya()" />
        #end
        
        </td>
      </tr>
    </tbody>
  </table>
 
  <input type="hidden" name="idFail" value="$idFail">
  <input type="hidden" name="noFail" value="$noFail">

  <input type="hidden" name="urusan" value="$idUrusan">
  <input type="hidden" name="negeri" value="$idnegeri">

</fieldset>






