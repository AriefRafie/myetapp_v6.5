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
            <td class="mediumselect">$selectJenisHakmilik</td>
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
      <td><table width="100%" border="0">
          <tr>
            <td width="40%"><div align="right"><strong><font color="#FF0000">*</font>Tarikh Terima Hakmilik :</strong></div></td>
            <td width="60%"><input type="text" name="txdTarikhTerima" id="txdTarikhTerima" size="15" value="$TarikhMula">
            <img src="../img/calendar.gif" border="0" onClick="displayDatePicker('txdTarikhTerima',false,'dmy');" style="display:$Style2"></td>
          </tr>
          <tr>
            <td><div align="right"><strong>Cukai Tahun Pertama :</strong></div></td>
            <td>RM 
              <label>
              <input type="text" name="txtCukaiPertama" id="txtCukaiPertama" maxlength="9" size="17" onkeyup="validateCurrency(this,this.value);" value="$Cukai" $modeSoc />
              </label></td>
          </tr>
          <tr>
            <td><div align="right"><strong>Lokasi:</strong></div></td>
            <td><label>
              <input type="text" name="txtLokasi" id="txtLokasi" maxlength="60" onkeyup="this.value=this.value.toUpperCase();" value="$Lokasi" $modeSoc>
            </label></td>
          </tr>
          <tr>
            <td><div align="right"><strong><font color="#FF0000">*</font>Unit Luas :</strong></div></td>
            <td>$selectLuas</td>
          </tr>
          <tr>
            <td><div align="right"><strong>No. Pelan Akui :</strong></div></td>
            <td><label>
              <input type="text" name="txtNoPelan" id="txtNoPelan" maxlength="50" onkeyup="this.value=this.value.toUpperCase();" value="$NoPelan" $modeSoc>
            </label></td>
          </tr>
          <tr>
            <td><div align="right"><strong><font color="#FF0000">*</font>Rizab :</strong></div></td>
            <td>$selectRizab</td>
          </tr>
          <tr>
            <td><div align="right"><strong><font color="#FF0000">*</font>Kategori :</strong></div></td>
            <td>$selectKategori</td>
          </tr>
          <tr>
            <td valign="top"><div align="right"><strong>Syarat Nyata :</strong></div></td>
            <td><label>
              <textarea name="txtSyarat" id="txtSyarat" cols="45" rows="3" onkeyup="this.value=this.value.toUpperCase();" $modeSoc>$Syarat</textarea>
            </label></td>
          </tr>
        </table>
        </td>
      <td valign="top"><table width="100%" border="0">
          <tr>
            <td width="40%"><div align="right"><strong><font color="#FF0000">*</font>Tarikh Luput :</strong></div></td>
            <td width="60%"><input type="text" name="txdTarikhLuput" id="txdTarikhLuput" size="15" value="$TarikhLuput">
            <img src="../img/calendar.gif" border="0" onClick="displayDatePicker('txdTarikhLuput',false,'dmy');" style="display:$Style2"></td>
          </tr>
          <tr>
            <td><div align="right"><strong>Cukai Semasa :</strong></div></td>
            <td>RM 
              <label>
              <input type="text" name="txtCukaiSemasa" id="txtCukaiSemasa" maxlength="16" size="17" onkeyup="validateCurrency(this,this.value);" value="$CukaiTerkini" $modeSoc>
            </label></td>
          </tr>
          <tr>
            <td><div align="right"><strong><font color="#FF0000">*</font>Luas :</strong></div></td>
            <td><label>
              <input type="text" name="txtLuas" id="txtLuas" maxlength="40" onkeyup="validateCurrency(this,this.value);" value="$Luas" $modeSoc>
            </label></td>
          </tr>
          <tr>
            <td><div align="right"><strong><font color="#FF0000">*</font>Tarikh Warta :</strong></div></td>
            <td><input type="text" name="txdTarikhWarta" id="txdTarikhWarta" size="15" value="$TarikhRizab" />
              <img src="../img/calendar.gif" border="0" onClick="displayDatePicker('txdTarikhWarta',false,'dmy');" style="display:$Style2"></td>
          </tr>
          <tr>
            <td><div align="right"><strong><font color="#FF0000">*</font>No.Warta :</strong></div></td>
            <td><label>
              <input type="text" name="txtNoWarta" id="txtNoWarta" maxlength="10" onkeyup="this.value=this.value.toUpperCase();" value="$NoRizab" $modeSoc>
            </label></td>
          </tr>
          <tr>
            <td><div align="right"><strong>No. PU :</strong></div></td>
            <td><label>
              <input type="text" name="txtNoPU" id="txtNoPU" maxlength="5" onkeyup="this.value=this.value.toUpperCase();" value="$Noptk" $modeSoc>
            </label></td>
          </tr>
          <tr>
            <td><div align="right"><strong>No. Syit Piawai :</strong></div></td>
            <td><label>
              <input type="text" name="txtNoSyit" id="txtNoSyit" maxlength="50" onkeyup="this.value=this.value.toUpperCase();" value="$NoSyit" $modeSoc>
            </label></td>
          </tr>
          <tr>
            <td valign="top"><div align="right"><strong>Sekatan Kepentingan :</strong></div></td>
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
		<td colspan="2" align="center">
        #if($pagemode == 'baru')

        &nbsp;&nbsp;<input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:fGPHA_Simpan()">&nbsp;&nbsp;<input type="reset" name="cmdBatal" id="cmdBatal" $btnName onclick="javascript:fGPHA_Batal()">&nbsp;&nbsp;&nbsp;&nbsp;
        
        #else
        <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="javascript:fGPHA_Kemaskini()">&nbsp;&nbsp;<input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:fGPHA_Simpan()">&nbsp;&nbsp;<input type="reset" name="cmdBatal" id="cmdBatal" $btnName onclick="javascript:fGPHA_Batal()">&nbsp;&nbsp;<input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="javascript:fGPHA_Kembali()">&nbsp;&nbsp;<input type="button" name="cmdSeterusnya" id="cmdSeterusnya" value="Seterusnya" onclick="javascript:fGPHA_seterusnya()">
        
        #end
        </td>
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