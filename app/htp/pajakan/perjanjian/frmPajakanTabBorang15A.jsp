<fieldset>
<legend><strong>BORANG (15A / L / LX11 )</strong></legend>

<table width="100%" border="0">
##if($BeanMaklumat15A.size() > 0)	
#if(!$!beanMaklumat15A.equals(""))	
    ##foreach ($beanMaklumat15A in $BeanMaklumat15A)
      <tr>
        <td width="1%">&nbsp;</td>
        <td width="28%">Tarikh Terima Daripada Pemohon</td>
        <td width="1%">:</td>
        <td width="70%"><input type="text" name="txdTarikhTerimaPemohon" id="txdTarikhTerimaPemohon" size="10" value="$beanMaklumat15A.get('tarikhTerima')" class="$classDis" $readOnly  onblur="check_date(this)"/>        
        #if($mode == 'update')
          <img src="../img/calendar.gif" alt="Calendar" border="0" style="display:$Style2" onClick="displayDatePicker('txdTarikhTerimaPemohon',false,'dmy');">
        #end</td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>Tarikh Tandatangan PTP</td>
        <td>:</td>
        <td><input type="text" name="txdTarikhTandatanganPTP15A" id="txdTarikhTandatanganPTP15A" size="10" value="$beanMaklumat15A.get('tarikhTandatangan')" class="$classDis" $readOnly  onblur="check_date(this)"/>
        #if($mode == 'update')
          <img src="../img/calendar.gif" alt="Calendar" border="0" onClick="displayDatePicker('txdTarikhTandatanganPTP15A',false,'dmy');">
         #end
         </td>
      </tr>
        <tr>
        <td>&nbsp;</td>
        <td>Tarikh Hantar Ke Pemohon</td>
        <td>:</td>
        <td><input type="text" name="txdTarikhHantarPemohon" id="txdTarikhHantarPemohon" size="10" value="$beanMaklumat15A.get('tarikhHantar')" class="$classDis" $readOnly  onblur="check_date(this)"/>
        
         #if($mode == 'update')
          <img src="../img/calendar.gif" alt="Calendar" border="0" style="display:$Style2" onClick="displayDatePicker('txdTarikhHantarPemohon',false,'dmy');">
         #end
         </td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>Tarikh Daftar Pajakan</td>
        <td>:</td>
        <td><input type="text" name="txdTarikhDaftarPajakan" id="txdTarikhDaftarPajakan" size="10" value="$beanMaklumat15A.get('tarikhDaftar')" class="$classDis" $readOnly  onblur="check_date(this)"/>
        
        #if($mode == 'update')
          <img src="../img/calendar.gif" alt="Calendar" border="0" onClick="displayDatePicker('txdTarikhDaftarPajakan',false,'dmy');">
         #end
         </td>
      </tr>
      <tr>
        <td >&nbsp;</td>
        <td >Tarikh Terima Hakmilik</td>
        <td>:</td>
        <td><input name="txdtarikhTerimaHakmilik" type="text" id="txdtarikhTerimaHakmilik" value="$beanMaklumat15A.get('tarikhTerimaHakmilik')" size="10" class="$classDis" $readOnly  onblur="check_date(this)"/> 
        #if($mode == 'update')
        <img src="../img/calendar.gif" alt="Calendar" border="0" onClick="displayDatePicker('txdtarikhTerimaHakmilik',false,'dmy');">
        #end
        </td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>Tarikh Kemaskini Hakmilik</td>
        <td>:</td>
        <td><input name="txdTarikhKemaskiniHakmilik" type="text" id="txdTarikhKemaskiniHakmilik" value="$beanMaklumat15A.get('tarikhKemaskini')" size="10" class="$classDis" $readOnly  onblur="check_date(this)"/> 
        
        #if($mode == 'update')
        <img src="../img/calendar.gif" alt="Calendar" border="0" onClick="displayDatePicker('txdTarikhKemaskiniHakmilik',false,'dmy');">
        #end
        </td>
      </tr>
      <tr>
		<td>&nbsp;</td>
		<td>Notifikasi ke Rekod & Cukai</td>
		<td>:</td>
		<td>
			<input type="checkbox" id="flagNotifikasi" name="flagNotifikasi" value="Y" 
			#if($mode != 'update') disabled #end #if($!beanMaklumat15A.flag_notifikasi=='Y') checked #else #end>
		</td>
	  </tr>
	  ##end
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
            <input class="stylobutton100" type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="javascript:kemaskini15A()" />
      		#if ($idStatus == '69')
            <input class="stylobutton_" type="button" name="cmdSeterusnya" id="cmdSeterusnya" value="Selesai Perjanjian" onclick="javascript:seterusnyaSelesaiPerjanjian()" />
            #end
        #elseif ($mode == 'update')
        	<input class="stylobutton100" type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:simpan15A()" />
            <input class="stylobutton100" type="reset" name="cmdBatal" id="cmdBatal" value="Kosongkan"/>
            <input class="stylobutton100" type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="javascript:batal15A()" />

        #end
        </td>
      </tr>
#else
	  <tr>
        <td colspan="4"><font color="red">Sila lengkapkan Maklumat Pajakan</font></td>
      </tr>
#end      
    </table>
    
</fieldset>
