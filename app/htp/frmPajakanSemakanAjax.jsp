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

<br/>
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
                <td><div align="right"><font color="#FF0000"></font><font color="#FF0000">*</font></div></td>
                <td><div align="left"><strong>Urusan</strong></div></td>
                <td>:</td>
                <td>$selectSuburusan </td>
              </tr>
              <tr>
                <td><div align="right"><font color="#FF0000">*</font></div></td>
                <td><div align="left"><strong>Jenis Tanah</strong></div></td>
                <td>:</td>
                <td>$selectJenisTanah</td>
              </tr>
              <tr>
                <td><div align="right"><font color="#FF0000">*</font></div></td>
                <td><div align="left"><strong>Jenis Fail</strong></div></td>
                <td>:</td>
                <td>$jenisFail</td>
              </tr>
              <tr>
                <td valign="top"><div align="right"><font color="#FF0000">*</font></div></td>
                <td valign="top"><div align="left"><strong>Tajuk</strong></div></td>
                <td valign="top">:</td>
                <td>
                  <textarea name="txtTajuk" id="txtTajuk" cols="41" rows="3" onkeyup="this.value=this.value.toUpperCase();" $modeSoc >$tajuk</textarea>
                </td>
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
                <td><input type="text" name="txdTarikhSurKJP" id="txdTarikhSurKJP" size="10" value="$tSurat" onblur="check_date(this)">
                <img src="../img/calendar.gif" border="0" onClick="displayDatePicker('txdTarikhSurKJP',false,'dmy');" ></td>
              </tr>              
            <tr>
              <td><div align="right"><font color="#FF0000">*</font></div></td>
                <td><div align="left"><strong>Tarikh Permohonan</strong></div></td>
                <td>:</td>
                <td><input type="text" name="txdTarikhPermohonan" id="txdTarikhPermohonan" size="10" value="$tPermohonan" onblur="check_date(this)" />
                <img src="../img/calendar.gif" border="0" onClick="displayDatePicker('txdTarikhPermohonan',false,'dmy');"></td>
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
              
              <!-- javascript:setSelected(3,'Maklumat','peguamview',0) -->
              
                <li class="TabbedPanelsTab" title="Semakan" onclick="setSelected(0)"><strong><font size="1">Senarai Semakan Pajakan</font></strong></li>
                <li class="TabbedPanelsTab" title="Hakmilik" onclick="setSelected(1);Hakmilik()"><strong><font size="1">Semakan Hakmilik</font></strong> </li>
              </ul>
              
              <div class="TabbedPanelsContentGroup">
              
                <div class="TabbedPanelsContent">
                <!-- content senarai semakan pajakan -->
                <!-- frmPajakanTabSenaraiSemakanPajakan.jsp -->
                 #if($selectedTab == '0')
                	#parse ("app/htp/frmPajakanTabSenaraiSemakanPajakan.jsp")
                #end
             
                    </div>
                    <!-- close content senarai semakan pajakan -->
                    
                <div class="TabbedPanelsContent">
                <!-- content semakan hakmilik -->
                <!-- frmPajakanTabSemakanHakmilik.jsp -->
	   			#if($selectedTab == '1')
                	#parse ("app/htp/frmPajakanTabSemakanHakmilik.jsp")
                #end
               	
              </div>
              <!-- close content semakan hakmilik -->
              
              </div>
              
            </div>
            </td>
          </tr>
          <!--end tab coding -->
        </table></td>
      </tr>
    </tbody>
  </table>
  <input type="hidden" name="idFail" value="$idFail">
  <input type="hidden" name="idPermohonan" value="$IdPermohonan">
  <input type="hidden" name="idHakmilik" value="">
  <input type="hidden" name="idHakmilikurusan" value="">
</fieldset>

<script>
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1",{defaultTab:$selectedTab});
</script>