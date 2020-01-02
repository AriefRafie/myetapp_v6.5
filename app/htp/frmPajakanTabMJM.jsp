<fieldset><legend>MEMORANDUM JEMAAH MENTERI</legend>
    <table width="100%" border="0">
      <tr>
        <td colspan="2">
        
            <table width="100%" border="0">
            #foreach ($beanMJM in $BeanMJM)
  			<input name="idPemohon" type="hidden" id="idPemohon" value="$beanMaklumatPemohon.idPemohon"/>
              <tr>
                <td width="1%">#if ($mode != 'view')<font color="#FF0000">*</font>#end</td>
                <td width="28%">Tarikh Hantar PTP</td>
                <td width="1%">:</td>
                <td width="70%"><input type="text" name="txdTHPTP" id="txdTHPTP" size="10" value="$beanMJM.tarikhHantarPTP" onBlur="check_date(this)" class="$classDis" $readOnly />
                
                #if($mode == 'update')
                  <img src="../img/calendar.gif" alt="Calendar" border="0" style="display:$Style2" onClick="displayDatePicker('txdTHPTP',false,'dmy');">
                 #end          
                 </td>
              </tr>
              <tr>
                <td align="right">#if ($mode != 'view')<font color="#FF0000">*</font>#end</td>
                <td>Tarikh Terima PTP</td>
                <td>:</td>
                <td><input type="text" name="txdTTPTP" id="txdTTPTP" size="10" value="$beanMJM.tarikhTerimaPTP" onBlur="check_date(this)" class="$classDis" $readOnly />
                
                #if($mode == 'update')
                  <img src="../img/calendar.gif" alt="Calendar" border="0" style="display:$Style2" onClick="displayDatePicker('txdTTPTP',false,'dmy');">
                #end          
                </td>
              </tr>
              <tr>
                <td>#if ($mode != 'view')<font color="#FF0000">*</font>#end</td>
                <td>Tarikh Hantar ke Bahagian TUP</td>
                <td>:</td>
                <td><input type="text" name="txdTHTUP" id="txdTHTUP" size="10" value="$beanMJM.tarikhHantarKSU" onBlur="check_date(this)" class="$classDis" $readOnly />
                
                #if($mode == 'update')
                  <img src="../img/calendar.gif" alt="Calendar" border="0" style="display:$Style2" onClick="displayDatePicker('txdTHTUP',false,'dmy');">
                #end          
                </td>
              </tr>
              <tr>
                <td>#if ($mode != 'view')<font color="#FF0000">*</font>#end</td>
                <td>Tarikh Mesyuarat Jemaah Menteri</td>
                <td>:</td>
                <td><input type="text" name="txdTMJM" id="txdTMJM" size="10" value="$beanMJM.tarikhMesyuaratJM" onBlur="check_date(this)" class="$classDis" $readOnly />
                
                #if($mode == 'update')                
                  <img src="../img/calendar.gif" alt="Calendar" border="0" style="display:$Style2" onClick="displayDatePicker('txdTMJM',false,'dmy');">
                #end          
                </td>
              </tr>
              <tr>
                <td>#if ($mode != 'view')<font color="#FF0000">*</font>#end</td>
                <td>Tarikh Terima Keputusan</td>
                <td>:</td>
                <td><input type="text" name="txdTTK" id="txdTTK" size="10" value="$beanMJM.tarikhTerimaKeputusan" onBlur="check_date(this)" class="$classDis" $readOnly />
                
                #if($mode == 'update')
                  <img src="../img/calendar.gif" alt="Calendar" border="0" style="display:$Style2" onClick="displayDatePicker('txdTTK',false,'dmy');">
                 #end          
                 </td>
              </tr>
              <tr>
                <td>#if ($mode != 'view')<font color="#FF0000">*</font>#end</td>
                <td>No. Memorandum</td>
                <td>:</td>
                <td><input type="text" name="txtNoMemorandum" value="$beanMJM.noMemorandum" onBlur="this.value=this.value.toUpperCase();" class="$classDis" $readOnly /></td>
              </tr>
              <tr>
                <td>#if ($mode != 'view')<font color="#FF0000">*</font>#end</td>
                <td>Keputusan</td>
                <td>:</td>
                <td>
                <select name="txtKeputusan" id="txtKeputusan" class="$classDis" $classDis>
                  
                  #if($beanMJM.keputusan == 'L' || $beanMJM.keputusan == "" )
						    
                  <option value="L" selected="selected">LULUS</option>
                  <option value="TL">TIDAK LULUS</option>

                  #else
  
                  <option value="L" >LULUS</option>
                  <option value="TL" selected="selected" >TIDAK LULUS</option>

                  #end
        
                </select></td>
              </tr>
              <tr>
                <td valign="top">#if ($mode != 'view')<font color="#FF0000">*</font>#end</td>
                <td valign="top">Keterangan Kelulusan</td>
                <td valign="top">:</td>
                <td valign="top"><textarea name="txtKeterangan" id="txtKeterangan" cols="50" rows="5" onkeyup="this.value=this.value.toUpperCase();" class="$classDis" $readOnly >$beanMJM.tindakanLanjut</textarea></td>
              </tr>
              <tr>
                <td valign="top">#if ($mode != 'view')<font color="#FF0000">*</font>#end</td>
                <td valign="top">Tarikh Makluman Keputusan kepada Pemohon</td>
                <td valign="top">&nbsp;</td>
                <td valign="top"><input name="txdTMKPemohon" type="text" class="$classDis" id="txdTMKPemohon" onblur="check_date(this)" value="$beanMJM.tarikhMaklumanKeputusan" size="10" maxlength="10" $readOnly />
#if($mode == 'update') <img src="../img/calendar.gif" alt="Calendar" border="0" style="display:$Style2" onclick="displayDatePicker('txdTMKPemohon',false,'dmy');" /> #end </td>
              </tr>
              #end
            </table>
        
        </td>
      </tr>
      <tr>
        <td colspan="2">&nbsp;</td>
      </tr>
      <tr>
        <td width="30%">&nbsp;</td>
        <td width="70%">
        	#if ($mode == 'view')
                <input class="stylobutton" type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="javascript:KemaskiniMemo()" />
                #if ($idStatus == '65')
                <input class="stylobutton" type="button" name="cmdSeterusnya" id="cmdSeterusnya" value="Selesai Memorandum" onclick="javascript:seterusnya()" />
                #end
            #elseif ($mode == 'update')
                <input class="stylobutton" type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:SimpanMemo()" />
                <input class="stylobutton" type="reset" name="cmdBatal" id="cmdBatal" value="Kosongkan"/>
                <input class="stylobutton" type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="javascript:batalMemo()" />
            #end  
        </td>
      </tr>
    </table>
</fieldset>