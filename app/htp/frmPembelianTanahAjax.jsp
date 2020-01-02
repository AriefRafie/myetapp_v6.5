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

<br/>
<fieldset>
<legend>Pendaftaran Tanah</legend>

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
              <input type="text" name="txtNoHakmilik" id="txtNoHakmilik" maxlength="21" onkeyup="this.value=this.value.toUpperCase();" value="$NoHakmilik" $mode $classDis >
            </label></td>
          </tr>
          <tr>
            <td><div align="right"><font color="#FF0000">*</font>Kod/No. Lot :</div></td>
            <td><table width="100%" border="0">
              <tr>
                <td width="30%"><label>
                  <input type="text" name="txtNoLot" id="txtKodLot" maxlength="20" size="20" onkeyup="this.value=this.value.toUpperCase();" value="$NoLot" $mode $classDis>
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
            <td width="67%"><input type="text" name="txdTarikhTerima" id="txdTarikhTerima" size="10" value="$TarikhMula" $classDis $mode onblur="check_date(this)" >
            
            #if($pagemode == 'baru' || $pagemode == 'kemaskini')
            
            <img src="../img/calendar.gif" border="0" onClick="displayDatePicker('txdTarikhTerima',false,'dmy');" style="display:$Style2">
            #end
            
            </td>
          </tr>
          <tr>
            <td><div align="right"><font color="#FF0000">*</font>Luas :</div></td>
            <td><label>
            <input type="text" name="txtLuas" id="txtLuas" maxlength="40" onkeyup="validateNumber(this,this.value);" value="$Luas" $mode $classDis />
            </label></td>
          </tr>
          <tr>
            <td><div align="right"><font color="#FF0000">*</font>Unit Luas :</div></td>
            <td>$selectLuas</td>
          </tr>
          <tr>
            <td><div align="right">No. Pelan Akui :</div></td>
            <td><label>
              <input type="text" name="txtNoPelan" id="txtNoPelan" maxlength="50" onkeyup="this.value=this.value.toUpperCase();" value="$NoPelan" $mode $classDis />
            </label></td>
          </tr>
        </table>        </td>
      <td valign="top"><table width="100%" border="0">
          <tr>
            <td width="35%"><div align="right"><font color="#FF0000">*</font>Tarikh Luput :</div></td>
            <td width="65%"><input type="text" name="txdTarikhLuput" id="txdTarikhLuput" size="10" value="$TarikhLuput" $classDis $mode onblur="check_date(this)" >
            
              #if($pagemode == 'baru' || $pagemode == 'kemaskini')
            
            <img src="../img/calendar.gif" border="0" onClick="displayDatePicker('txdTarikhLuput',false,'dmy');" style="display:$Style2">
            
            #end
            
            </td>
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
		<td colspan="2" align="center">
        
        #if($pagemode == 'baru' || $pagemode == 'kemaskini')
        
        <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:fPTA_Simpan()">&nbsp;&nbsp;<input type="reset" name="cmdBatal" id="cmdBatal" $btnName onclick="javascript:fPTA_Batal()">
        
        #elseif($pagemode == 'simpan')
        
        <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="javascript:fPTA_Kemaskini()">&nbsp;&nbsp;<input type="button" style="display:none" name="cmdSeterusnya" id="cmdSeterusnya" value="Seterusnya" onclick="javascript:fPTA_seterusnya()">
        
        #else
        
        &nbsp;&nbsp;&nbsp;&nbsp;<input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="javascript:fPTA_Kembali()">
        
        #end
        
        </td>
      </tr>
      <tr>
        <td colspan="2" align="center">&nbsp;</td>
      </tr>
      <tr>
        <td colspan="2">
        	
        
        
        
        
        
        </td>
      </tr>
    </tbody>
  </table>  
  <input type="hidden" name="idHakmilikurusan" value="$IdHakmilikurusan">
  <input type="hidden" name="idPermohonan" value="$IdPermohonan">
  <input type="hidden" name="idFail" value="$IdFail">




</fieldset>