<fieldset>
<legend><strong>KELULUSAN JEMAAH MENTERI</strong></legend>

	<table width="100%" border="0">
    #foreach ($beanMaklumatJM in $BeanMaklumatJM)
      <tr>
        <td width="1%">&nbsp;</td>
        <td width="28%">Tarikh Perbadanan Berkuatkuasa</td>
        <td width="1%">:</td>
        <td width="70%"><input type="text" name="txdTarikhPerbadanan" id="txdTarikhPerbadanan" size="10" value="$beanMaklumatJM.tarikhPerbadanan" class="$classDis" $readOnly onblur="check_date(this)"/>
        
         #if($mode == 'update')
          <img src="../img/calendar.gif" alt="Calendar" border="0" style="display:$Style2" onClick="displayDatePicker('txdTarikhPerbadanan',false,'dmy');">
		#end
        </td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>Tarikh Mesyuarat</td>
        <td>:</td>
        <td><input type="text" name="txdtarikhMesyuarat" id="txdtarikhMesyuarat" size="10" value="$beanMaklumatJM.tarikhMesyuarat" class="$classDis" $readOnly onblur="check_date(this)"/>
        
         #if($mode == 'update')
          <img src="../img/calendar.gif" alt="Calendar" border="0" onClick="displayDatePicker('txdtarikhMesyuarat',false,'dmy');">
         #end
        </td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>No Memorandum</td>
        <td>:</td>
        <td><input type="text" name="txtNoMemorandum" id="txtNoMemorandum" value="$beanMaklumatJM.noMemo" class="$classDis" $readOnly onBlur="this.value=this.value.toUpperCase();"/></td>
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
            <input class="stylobutton" type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="javascript:KemaskiniJM()" />
            #if ($idStatus == '69')
            <input class="stylobutton" type="button" name="cmdSeterusnya" id="cmdSeterusnya" value="Selesai Perjanjian" onclick="javascript:seterusnya()" />
            #end
        #elseif ($mode == 'update')
            <input class="stylobutton" type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:SimpanJM()" />
            <input class="stylobutton" type="reset" name="cmdBatal" id="cmdBatal" value="Kosongkan"/>
            <input class="stylobutton" type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="javascript:batalJM()" />
        #end
        </td>
      </tr>
    </table>
    
</fieldset>
