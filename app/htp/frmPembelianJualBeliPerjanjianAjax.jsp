    #foreach ( $pjualbeli in $PJualBeli )
		#set ($noKontrak = $pjualbeli.noRujPerjanjian)
    	#set ($tarikhPerjanjian = $pjualbeli.tarikhPerjanjian)
    	#set ($tempohBulan = $pjualbeli.tempoh)
        #set ($tarikhSempurna14A = $pjualbeli.tarikhBorang14a)
        #set ($HargaTambahan = $pjualbeli.hargaTambahan)
        #set ($HargaBeli = $pjualbeli.hargaBeli)
        #set ($tarikhPTP = $pjualbeli.tarikhPTP)
        #set ($tarikhSerahBangunan = $pjualbeli.tarikhSerahBangunan)
        #set ($jumlahHakmilik = $pjualbeli.bilHakmilikBeli)
        #set ($bilUnitBangunan = $pjualbeli.bilUnitBangunan)
        #set ($nilaiTanah = $pjualbeli.nilaiTanah)
        #set ($nilaiBangunan = $pjualbeli.nilaiBangunan)
        #set ($tarikhPTambahan = $pjualbeli.tarikhPTambahan)
        #set ($socSebab = $pjualbeli.socSebab)
        #set ($alasan = $pjualbeli.alasan)
        #set ($TempohPerjanjian = $pjualbeli.TempohPerjanjian)
        #set ($TarikhSerahPTambahan = $pjualbeli.TarikhSerahPTambahan)
        #set ($idPerjanjian = $pjualbeli.idPerjanjian )
	#end   


<table width="100%" border="0">
  <tr>
    <td>
    <fieldset>
    	<legend>PERJANJIAN JUAL BELI
      </legend><table width="100%" border="0">
        <tr>
          <td>
          
          <table width="100%" border="0">
            <tr>
              <td width="40%" align="right" scope="row">No Kontrak :</td>
              <td width="60%"><input type="text" name="txtNoKontrak" id="txtNoKontrak" size="30" maxlength="50" onkeyup="this.value=this.value.toUpperCase();" value="$noKontrak" $mode $classDis /></td>
            </tr>
            <tr>
              <td align="right" scope="row">Tarikh perjanjian :</td>
              <td><input name="txtTarikhPerjanjian" type="text" id="txtTarikhPerjanjian" onblur="check_date(this)" value="$tarikhPerjanjian" size="10" maxlength="10" $mode $classDis />
              
              #if($addmode == "new" || $addmode == "update")
                <img src="../img/calendar.gif" alt="Calendar" border="0" onClick="displayDatePicker('txtTarikhPerjanjian',false,'dmy');">
                #end
                
                </td>
            </tr>
            <tr style="display:none">
              <td align="right" scope="row">Tempoh Bulan :</td>
              <td><input type="text" name="txtTempohBulan" id="txtTempohBulan" size="3" maxlength="3" onkeyup="this.value=this.value.toUpperCase();" value="$tempohBulan" $mode $classDis /></td>
            </tr>
            <tr>
              <td align="right" scope="row">Tarikh Penyempurnaan 14A :</td>
              <td><input name="txtTarikhSempurna14A" type="text" id="txtTarikhSempurna14A" onblur="check_date(this)" value="$tarikhSempurna14A" size="10" maxlength="10" $mode $classDis />
              
                #if($addmode == "new" || $addmode == "update")
                <img src="../img/calendar.gif" alt="Calendar" border="0" style="display:$Style2" onclick="displayDatePicker('txtTarikhSempurna14A',false,'dmy');" />
                #end
                
                
                </td>
            </tr>
            <tr>
              <td align="right" scope="row">Harga Tambahan :</td>
              <td><input type="text" name="txtHargaTambahan" id="txtHargaTambahan" size="30" maxlength="50" onkeyup="this.value=this.value.toUpperCase();" value="$HargaTambahan" $mode $classDis /></td>
            </tr>
            <tr>
              <td align="right" scope="row">Harga Beli RM :</td>
              <td><input type="text" name="txtHargaBeli" id="txtHargaBeli" size="30" maxlength="50" onkeyup="this.value=this.value.toUpperCase();" value="$HargaBeli" $mode $classDis /></td>
            </tr>
          </table></td>
          <td><table width="100%" border="0">
            <tr>
              <td width="40%" align="right" scope="row">Tarikh Tandatangan PTP :</td>
              <td width="60%"><input name="txtTarikhPTP" type="text" id="txtTarikhPTP" onblur="check_date(this)" value="$tarikhPTP" size="10" maxlength="10" $mode $classDis />
              
              #if($addmode == "new" || $addmode == "update")
                <img src="../img/calendar.gif" alt="Calendar" border="0" onClick="displayDatePicker('txtTarikhPTP',false,'dmy');" >
                #end
                </td>
            </tr>
            <tr style="display:none">
              <td align="right" scope="row">Tarikh Serah Bangunan :</td>
              <td><input name="txtarikhSerahBangunan" type="text" id="txtarikhSerahBangunan" onblur="check_date(this)" value="$tarikhSerahBangunan" size="10" maxlength="10" $mode $classDis />
                <img src="../img/calendar.gif" alt="Calendar" border="0" onClick="displayDatePicker('txtarikhSerahBangunan',false,'dmy');" ></td>
            </tr>
            <tr>
              <td align="right" scope="row">Jumlah Hakmilik Tanah :</td>
              <td><input type="text" name="txtjumlahHakmilik" id="txtjumlahHakmilik" size="2" maxlength="2" onkeyup="this.value=this.value.toUpperCase();" value="$jumlahHakmilik" $mode $classDis /></td>
            </tr>
            <tr>
              <td align="right" scope="row">Bil. Unit Bangunan :</td>
              <td><input type="text" name="txtbilUnitBangunan" id="txtbilUnitBangunan" size="30" maxlength="50" onkeyup="this.value=this.value.toUpperCase();" value="$bilUnitBangunan" $mode $classDis /></td>
            </tr>
            <tr>
              <td align="right" scope="row">Nilai Tanah RM :</td>
              <td><input type="text" name="txtnilaiTanah" id="txtnilaiTanah" size="30" maxlength="50" onkeyup="this.value=this.value.toUpperCase();" value="$nilaiTanah" $mode $classDis /></td>
            </tr>
            <tr>
              <td align="right" scope="row">Nilai Bangunan RM :</td>
              <td><input type="text" name="txtnilaiBangunan" id="txtnilaiBangunan" size="30" maxlength="50" onkeyup="this.value=this.value.toUpperCase();" value="$nilaiBangunan" $mode $classDis /></td>
            </tr>
          </table></td>
        </tr>
      </table>
	</fieldset>


    <table width="100%" border="0">
      <tr>
        <td width="51%" valign="top">&nbsp;</td>
        <td width="49%" valign="top">&nbsp;</td>
      </tr>
      <tr>
        <td colspan="2" valign="top" align="center">
        
        #if($addmode == "view")
        <input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="javascript:fPJBPA_Kemaskini()">
          &nbsp;&nbsp;
         #end
         #if($addmode == "new")
          <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:fPJBPA_SimpanPerjanjian()"> 
          #end
          &nbsp;&nbsp;
          #if($addmode == "update")
          <input type="button" name="cmdSimpan2" id="cmdSimpan2" value="Simpan" onclick="javascript:fPJBPA_SimpanUpdatePerjanjian()" />
          &nbsp;&nbsp;
          <input type="reset" name="cmdBatal" id="cmdBatal" $btnNamePeguam onclick="javascript:fPJBPA_Batal()">
          &nbsp;&nbsp;
          <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="javascript:fPJBPA_Kembali()">
          #end
          </td>
        </tr>
    </table>
</td>
  </tr>
</table>


<!--
                  <input type="hidden" name="idHakmilikurusan" value="$IdHakmilikurusan">
                	<input type="hidden" name="idFail" value="$IdFail">
  					<input type="hidden" name="noFail" value="$NoFail">
  					<input type="hidden" name="idPermohonan" value="$IdPermohonan">
                    
                    -->
                    
                    <input type="hidden" name="idFail" value="$IdFail">
  					<input type="hidden" name="idPermohonan" value="$IdPermohonan">
                    <input type="hidden" name="idPerjanjian" value="$idPerjanjian">
                    


