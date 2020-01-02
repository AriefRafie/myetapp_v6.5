<fieldset><legend><strong>MOA</strong></legend>

	<table width="100%" border="0">
      #foreach ($beanMaklumatMOA in $BeanMaklumatMOA)
      <tr>
        <td width="1%">&nbsp;</td>
        <td width="28%">Tarikh Terima MOA</td>
        <td width="1%">:</td>
        <td width="70%"><input type="text" name="txdTarikhTerimaMOA" id="txdTarikhTerimaMOA" size="10" value="$beanMaklumatMOA.tarikhTerima" class="$classDis" $readOnly onblur="check_date(this)"/>        
         #if($mode == 'update')
          <img src="../img/calendar.gif" alt="Calendar" border="0" style="display:$Style2" onClick="displayDatePicker('txdTarikhTerimaMOA',false,'dmy');">
          #end          
          </td>
        </tr>
      <tr>
        <td>&nbsp;</td>
        <td>Tarikh Tandatangan PTP</td>
        <td>:</td>
        <td><input type="text" name="txdTarikhTandatanganPTP" id="txdTarikhTandatanganPTP" size="10" value="$beanMaklumatMOA.tarikhTandatangan" class="$classDis" $readOnly onblur="check_date(this)"/>
          #if($mode == 'update')
          <img src="../img/calendar.gif" alt="Calendar" border="0" onClick="displayDatePicker('txdTarikhTandatanganPTP',false,'dmy');">
          #end          
          </td>
        </tr>
      <tr>
        <td>&nbsp;</td>
        <td>Tarikh Hantar Ke Pengarah</td>
        <td>:</td>
        <td><input type="text" name="txdTarikhHantarPengarah" id="txdTarikhHantarPengarah" size="10" value="$beanMaklumatMOA.tarikhHantarPengarah" class="$classDis" $readOnly onblur="check_date(this)"/>
#if($mode == 'update') <img src="../img/calendar.gif" alt="Calendar" border="0" onclick="displayDatePicker('txdTarikhHantarPengarah',false,'dmy');" /> #end </td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>Tarikh MOA Dikembalikan</td>
        <td>:</td>
        <td><input type="text" name="txdTarikhKembaliMOA" id="txdTarikhKembaliMOA" size="10" value="$beanMaklumatMOA.tarikhKembali" class="$classDis" $readOnly onblur="check_date(this)"/>
        
         #if($mode == 'update')
          <img src="../img/calendar.gif" alt="Calendar" border="0" style="display:$Style2" onClick="displayDatePicker('txdTarikhKembaliMOA',false,'dmy');">
          #end          
          </td>
        </tr>
      <tr>
        <td>&nbsp;</td>
        <td>Tarikh MOA Didaftarkan</td>
        <td>:</td>
        <td><input type="text" name="txdTarikhDaftarMOA" id="txdTarikhDaftarMOA" size="10" value="$beanMaklumatMOA.tarikhDaftar" class="$classDis" $readOnly onblur="check_date(this)"/>
        
         #if($mode == 'update')
          <img src="../img/calendar.gif" alt="Calendar" border="0" onClick="displayDatePicker('txdTarikhDaftarMOA',false,'dmy');">
          #end          
          </td>
        </tr>
      <tr>
        <td>&nbsp;</td>
        <td>Tarikh Bayaran MOA</td>
        <td>:</td>
        <td><input name="txdTarikhBayaranMOA" type="text" id="txdTarikhBayaranMOA" value="$beanMaklumatMOA.tarikhBayar" size="10" class="$classDis" $readOnly onblur="check_date(this)"/> 
        
         #if($mode == 'update')
        <img src="../img/calendar.gif" alt="Calendar" border="0" onClick="displayDatePicker('txdTarikhBayaranMOA',false,'dmy');">
        #end        
        </td>
      </tr>
      <tr>
        <td >&nbsp;</td>
        <td >No. Perjanjian MOA</td>
        <td>:</td>
        <td><input name="txtNoPerjanjianMOA" type="text" id="txtNoPerjanjianMOA" value="$beanMaklumatMOA.noPerjanjian" class="$classDis" $readOnly onBlur="this.value=this.value.toUpperCase();"/></td>
        </tr>
      #end
      <tr>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td colspan="2">
        #if($mode == 'view')
            <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="javascript:kemaskiniMOA()" />
        #elseif ($mode == 'update')
        <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:simpanMOA()" />
            <input type="reset" name="cmdBatal" id="cmdBatal" value="Kosongkan"/>
            <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="javascript:batalMOA()" />
        #end
        </td>
      </tr>
    </table>