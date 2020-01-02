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

  <table border="0" cellpadding="0" cellspacing="0" width="100%">
    <tbody>
      <tr>
		<td align="center" valign="top" width="50%"><table width="100%" border="0">
              <tr>
                <td width="23%"><div align="left"><strong>Kementerian</strong></div></td>
                <td width="2%">:</td>
                <td width="75%"><font color="blue">$selectKementerian</font></td>
              </tr>
              <tr>
                <td><div align="left"><strong>Agensi</strong></div></td>
                <td>:</td>
                <td><font color="blue">$selectAgensi</font></td>
              </tr>
              <tr>
                <td valign="top"><div align="left"><strong>Tajuk</strong></div></td>
                <td valign="top">:</td>
                <td>
                <font color="blue">$TajukFail</font>
                <!--
                <label>
                <textarea name="Tajuk" id="Tajuk" cols="45" rows="3" disabled="disabled">$TajukFail </textarea>
                </label>
                -->
                </td>
              </tr>
        </table></td>
        <td align="center" valign="top" width="50%"><table width="100%" border="0">
              <tr>
                <td width="30%"><div align="left"><strong>No. Fail Seksyen</strong></div></td>
                <td width="2%">:</td>
                <td width="68%">
                <font color="blue">$NoFail</font>
                <!-- <input name="txtNoFailSek" type="text" disabled="class disabled" value="$NoFail" size="40"> -->
                </td>
              </tr>
              <tr>
                <td><div align="left"><strong>No. Fail Lain</strong></div></td>
                <td>:</td>
                <td> <font color="blue">$NoRujukanLain</font>
                <!--<input name="txtNoFailLain" type="text" disabled="class disabled" id="txtNoFailLain" value="$NoRujukanLain" size="40"> -->
                </td>
              </tr>
        </table></td>
      </tr>
      <tr>
      	<td colspan="2"><hr size="2" width="100%"></td>
      </tr>
      <tr>
        <td><table width="100%" border="0">
          <tr>
            <td width="33%"><div align="right"><strong>Negeri :</strong></div></td>
            <td width="67%"><font color="blue">$selectNegeri</font>
            <input type="hidden" name="idNegeri" value="$IdNegeri" /></td>
          </tr>
          <tr>
            <td><div align="right"><strong><font color="#FF0000">*</font>Daerah :</strong></div></td>
            <td>$selectDaerah</td>
          </tr>
          <tr>
            <td><div align="right"><strong><font color="#FF0000">*</font>Bandar/Pekan/Mukim :</strong></div></td>
            <td>$selectMukim</td>
          </tr>
          <tr>
            <td><div align="right"><strong><font color="#FF0000">*</font>Jenis Hakmilik :</strong></div></td>
            <td>$selectJenisHakmilik</td>
          </tr>
        </table></td>
        <td><table width="100%" border="0">
          <tr>
            <td width="33%"><div align="right"><strong><font color="#FF0000">*</font>No. Hakmilik :</strong></div></td>
            <td width="67%"><label>
              <input type="text" name="txtNoHakmilik" id="txtNoHakmilik" maxlength="21" onkeyup="this.value=this.value.toUpperCase();" value="$NoHakmilik" $modeSoc >
            </label></td>
          </tr>
          <tr>
            <td><div align="right"><strong><font color="#FF0000">*</font>Kod Lot/No. :</strong></div></td>
            <td><table width="100%" border="0">
              <tr>
              <td width="60%">$selectLot</td>
                <td width="40%"><label>
                  <input type="text" name="txtNoLot" id="txtKodLot" maxlength="20" onkeyup="this.value=this.value.toUpperCase();" value="$NoLot" $modeSoc>
                </label></td>
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
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      <tr>
		<td colspan="2" align="center"><input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="javascript:fGPHA_Kemaskini()">&nbsp;&nbsp;<input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:fGPHA_Simpan()">&nbsp;&nbsp;<input type="reset" name="cmdBatal" id="cmdBatal" $btnName onclick="javascript:fGPHA_Batal()">&nbsp;&nbsp;<input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="javascript:fGPHA_Kembali()">&nbsp;&nbsp;<input type="button" name="cmdSeterusnya" id="cmdSeterusnya" value="Seterusnya" onclick="javascript:fGPHA_seterusnya()"></td>
      </tr>
      
    </tbody>
  </table>  
  <input type="hidden" name="idHakmilikurusan" value="$IdHakmilikurusan">
  <input type="hidden" name="idFail" value="$IdFail">
  <input type="hidden" name="noFail" value="$NoFail">
  <input type="hidden" name="idPermohonan" value="$IdPermohonan">
  <input type="hidden" name="style1" value="$Style1">
  <input type="hidden" name="style2" value="$Style2">

</fieldset>