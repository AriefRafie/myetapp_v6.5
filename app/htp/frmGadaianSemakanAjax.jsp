#set ($tajuk = "")
#set ($noFail = "")
#set ($tSurat = "")
#set ($tPermohonan = "")
#set ($noRujukan = "")
#set ($tAgihan = "")
#set ($idAgensi = "")
#set ($idSuburusan = "")
#foreach ( $semakbaru in $SemakBaru )
	#set ($tajuk = $semakbaru.tajuk)
    #set ($noFail = $semakbaru.noFail)
    #set ($idAgensi = $semakbaru.idAgensi)
    #set ($idSuburusan = $semakbaru.idSuburusan)
    #set ($tAgihan = $semakbaru.tAgihan)
#end
#foreach ( $semak in $Semak )
	#set ($tajuk = $semak.tajuk)
    #set ($noFail = $semak.noFail)
    #set ($tSurat = $semak.tSurat)
    #set ($noRujukan = $semak.noRujukan)
    #set ($tAgihan = $semak.tAgihan)
    #set ($tPermohonan = $semak.tPermohonan)
    #set ($idSuburusan = $semakbaru.idSuburusan)
#end

#set ($btnName = "value='Kosongkan'")
#if ($IdPermohonan != "")
	#set ($btnName = "value='Batal'")
#end

<fieldset>
<legend><strong>MAKLUMAT PERMOHONAN</strong></legend>
  <table border="0" cellpadding="0" cellspacing="0" width="100%">
    <tbody>
      <tr>
		<td align="center" valign="top" width="50%"><table width="100%" border="0">
              <tr>
                <td width="3%"><div align="right"></div></td>
                <td width="20%"><div align="left">Negeri</div></td>
                <td width="2%" valign="top">:</td>
                <td width="75%">$selectNegeri</td>
              </tr>
              <tr>
                <td><div align="right"></div></td>
                <td><div align="left">Agensi</div></td>
                <td valign="top">:</td>
                <td>$selectAgensi</td> 
            </tr>
              <tr>
                <td><div align="right"></div></td>
                <td><div align="left">Urusan</div></td>
                <td valign="top">:</td>
                <td>$selectSuburusan
                <input type="hidden" name="idSuburusan" value="$idSuburusan"></td>
              </tr>
              <tr>
                <td valign="top"><div align="right"></div></td>
                <td valign="top"><div align="left">Tajuk</div></td>
                <td valign="top">:</td>
                <td>$tajuk</td>
              </tr>
              <tr>
                <td valign="top">&nbsp;</td>
                <td valign="top">&nbsp;</td>
                <td valign="top">&nbsp;</td>
                <td><input type="hidden" name="txtTajuk2" value="$tajuk" /></td>
              </tr>
        </table></td>
        <td align="center" valign="top" width="50%"><table width="100%" border="0">
              <tr>
                <td width="3%"><div align="right"></div></td>
                <td width="30%"><div align="left">No. Fail Seksyen</div></td>
                <td width="2%">:</td>
                <td width="65%"><!--<input type="text" name="txtNoFailSek" maxlength="400" size="40" value="$noFail"">-->
                $noFail
                </td>
              </tr>
              <tr>
                <td><div align="right"><font color="#FF0000">*</font></div></td>
                <td><div align="left">No. Fail Lain</div></td>
                <td>:</td>
                <td><input type="text" name="txtNoFailLain" id="txtNoFailLain" size="40" maxlength="50" onkeyup="this.value=this.value.toUpperCase();" value="$noRujukan" $mode $classDis /></td>
              </tr>
              <tr>
                <td><div align="right"><font color="#FF0000">*</font></div></td>
                <td><div align="left">Tarikh Surat KJP</div></td>
                <td>:</td>
                <td><input type="text" name="txdTarikhSurKJP" id="txdTarikhSurKJP" size="15" value="$tSurat" $mode $classDis >
                <img src="../img/calendar.gif" border="0" onClick="displayDatePicker('txdTarikhSurKJP',false,'dmy');" style="display:$Style2"></td>
              </tr>              
              <tr>
                <td><div align="right"><font color="#FF0000">*</font></div></td>
                <td><div align="left">Tarikh Permohonan</div></td>
                <td>:</td>
                <td><input type="text" name="txdTarikhPermohonan" id="txdTarikhPermohonan" size="15" value="$tPermohonan" $mode $classDis />
                <img src="../img/calendar.gif" border="0" onClick="displayDatePicker('txdTarikhPermohonan',false,'dmy');" style="display:$Style2"></td>
              </tr>
            #if ($IdPermohonan == "")
              <tr>
                <td><div align="right"></div></td>
                <td><div align="left">Tarikh Buka Fail</div></td>
                <td>:</td>
                <td><input type="text" name="txdTarikhBukaFail" id="txdTarikhBukaFail" size="15" value="$datenow" readonly="readonly" $classDis >
                <input type="button" style="display:none" onClick="displayDatePicker('txdTarikhBukaFail',false,'dmy');" value="Kalendar" $modeSoc/></td>
              </tr>
            #else
              <tr>
                <td><div align="right"></div></td>
                <td><div align="left">Tarikh Buka Fail</div></td>
                <td>:</td>
                <td><input type="text" name="txdTarikhBukaFail" id="txdTarikhBukaFail" size="15" value="$tAgihan" readonly="readonly" $classDis >
                <input type="button" style="display:none" onClick="displayDatePicker('txdTarikhBukaFail',false,'dmy');" value="Kalendar" $modeSoc /></td>
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
            <td colspan="2"><strong>SENARAI SEMAKAN</strong></td>
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
                    <td class="$row" width="10">
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
        </table></td>
      </tr>
      <tr>
		<td colspan="2" align="center">
        
		#if($pagemode == 'baru' || $pagemode == 'kemaskini')
        
        &nbsp;&nbsp;<input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:fGSA_Simpan()">&nbsp;&nbsp;<input type="reset" name="cmdBatal" id="cmdBatal" $btnName onclick="javascript:fGSA_Batal()">
		  &nbsp;&nbsp;&nbsp;

        #elseif($pagemode == 'simpan')
       
       <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="javascript:fGSA_Kemaskini()">&nbsp;&nbsp;<input type="button" name="cmdSeterusnya" id="cmdSeterusnya" value="Seterusnya" onclick="javascript:fGSA_Seterusnya()">
        
       #else
       
       <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="javascript:fGSA_Kembali()">&nbsp;&nbsp;<input type="button" name="cmdSeterusnya" id="cmdSeterusnya" value="Seterusnya" onclick="javascript:fGSA_Seterusnya()">
        
         #end
        
        </td>
        
       
        
      </tr>
    </tbody>
  </table>

  <input type="hidden" name="idFail" value="$IdFail">
  <input type="hidden" name="noFail" value="$NoFail">
  <input type="hidden" name="idPermohonan" value="$IdPermohonan">
  <input type="hidden" name="style1" value="$Style1">
  <input type="hidden" name="style2" value="$Style2">
  <input type="hidden" name="socAgensi" value="$agensiVal">

</fieldset>