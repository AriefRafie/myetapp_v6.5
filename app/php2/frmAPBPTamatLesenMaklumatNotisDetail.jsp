<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td colspan="2"><fieldset>
      <legend>MAKLUMAT DOKUMEN</legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
         <tr>
          <td width="1%">#if ($modePopup != 'view')<span class="style1">*</span>#end</td>
          <td width="28%">Jenis Dokumen</td>
          <td width="1%">:</td>
          <td width="70%"><select name="jenisDokumen" id="jenisDokumen" style="width:230px;" $readonlyS class="$inputTextClassS" $inputTextClassS onChange="">
           #if($jenisDokumen == '1')       
              <option value="">SILA PILIH</option>
              <option value="1" selected="selected">SURAT TUNJUK SEBAB </option>
              <option value="2">SURAT PERINGATAN </option>
              <option value="3">SURAT PERINGATAN TERAKHIR </option>
           #elseif($jenisDokumen == '2')
             <option value="">SILA PILIH</option>
              <option value="1">SURAT TUNJUK SEBAB </option>
              <option value="2" selected="selected">SURAT PERINGATAN </option>
              <option value="3">SURAT PERINGATAN TERAKHIR </option>
           #elseif($jenisDokumen == '3')
             <option value="">SILA PILIH</option>
              <option value="1">SURAT TUNJUK SEBAB </option>
              <option value="2" >SURAT PERINGATAN </option>
              <option value="3" selected="selected">SURAT PERINGATAN TERAKHIR </option>
           #else
             <option value=""  selected="selected">SILA PILIH</option>
              <option value="1">SURAT TUNJUK SEBAB </option>
              <option value="2" >SURAT PERINGATAN </option>
              <option value="3">SURAT PERINGATAN TERAKHIR </option>
           #end
            </select>
          </td>
         </tr>
         <tr>
          <td width="1%">#if ($modePopup != 'view')<span class="style1">*</span>#end</td>
          <td width="28%">Surat Ke</td>
          <td width="1%">:</td>
          <td width="70%"><select name="idSuratKe" id="idSuratKe" style="width:100px;" $readonlyS class="$inputTextClassS" $inputTextClassS onChange="" >
   		    #if($idSuratKe == 'PTD')               
              <option value="">SILA PILIH</option>
              <option value="PTD" selected="selected"> 1 - PTD </option>
              <option value="PTG"> 2 - PTG </option>
              <option value="JKPTG"> 3 - JKPTG </option>
   			#elseif($idSuratKe == 'PTG') 
    		  <option value="">SILA PILIH</option>
              <option value="PTD"> 1 - PTD </option>    
              <option value="PTG" selected="selected"> 2 - PTG </option> 
              <option value="JKPTG"> 3 - JKPTG </option>               
			#elseif($idSuratKe == 'JKPTG') 
              <option value="">SILA PILIH</option>
              <option value="PTD"> 1 - PTD </option>
              <option value="PTG"> 2 - PTG </option>
              <option value="JKPTG" selected="selected"> 3 - JKPTG </option>      
   			#else
              <option value="" selected="selected">SILA PILIH</option>
              <option value="PTD"> 1 - PTD </option>
              <option value="PTG"> 2 - PTG </option>
              <option value="JKPTG"> 3 - JKPTG </option>
			#end            
            </select>
          </td>
        </tr>
        <tr>
          <td>#if ($modePopup != 'view')<span class="style1">*</span>#end</td>
          <td>Negeri</td>
          <td>:</td>
          <td>$selectNegeri</td>
        </tr>
        <tr>
          <td>#if ($modePopup != 'view')<span class="style1">*</span>#end</td>
          <td>Pejabat</td>
          <td>:</td>
          <td>$selectPejabat</td>
        </tr>
        #foreach ($beanMaklumatPejabat in $BeanMaklumatPejabat)
        <tr>
          <td>&nbsp;</td>
          <td>Nama Pejabat</td>
          <td>:</td>
          <td>$beanMaklumatPejabat.namaPejabat</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Alamat</td>
          <td>:</td>
          <td>$beanMaklumatPejabat.alamat1</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>:</td>
          <td>$beanMaklumatPejabat.alamat2</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>:</td>
          <td>$beanMaklumatPejabat.alamat3</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Poskod</td>
          <td>:</td>
          <td>$beanMaklumatPejabat.poskod</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Bandar</td>
          <td>:</td>
          <td>$beanMaklumatPejabat.bandar</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Negeri</td>
          <td>:</td>
          <td>$beanMaklumatPejabat.negeri</td>
        </tr>
        #end   
        #foreach ($beanMaklumatNotis in $BeanMaklumatNotis)
        #if ($flagStatus == '1')
        <tr>
          <td>&nbsp;</td>
          <td>Tarikh Hantar</td>
          <td>:</td>
          <td><input name="txtTarikhHantar" type="text" $readonlyPopup class="$inputTextClassPopup" id="txtTarikhHantar" onBlur="check_date(this);calcDate()" value="$beanMaklumatNotis.tarikhHantar" size="9" maxlength="10">
            #if ($modePopup != 'view') <a href="javascript:displayDatePicker('txtTarikhHantar',false,'dmy');"><img border="0" src="../img/calendar.gif"/>#end</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Jangkamasa</td>
          <td>:</td>
          <td><input type="text" name="txtJangkaMasa" id="txtJangkaMasa" size="1" maxlength="2" value="$beanMaklumatNotis.jangkamasa" onBlur="validateNumber(this,this.value,'$beanMaklumatNotis.jangkamasa');calcDate()" $readonlyPopup class="$inputTextClassPopup">
            Hari</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Tarikh Dijangka Terima</td>
          <td>:</td>
          <td><input name="txtTarikhJangkaTerima" type="text" $readonlyPopup class="$inputTextClassPopup" id="txtTarikhJangkaTerima" onBlur="check_date(this)" value="$beanMaklumatNotis.tarikhJangkaTerima" size="9" maxlength="10">
            #if ($modePopup != 'view') <a href="javascript:displayDatePicker('txtTarikhJangkaTerima',false,'dmy');"><img border="0" src="../img/calendar.gif"/>#end</td>
        </tr>
        #else
        <tr>
          <td>&nbsp;</td>
          <td>Tarikh Hantar</td>
          <td>:</td>
          <td><input name="txtTarikhHantar" type="text" readonly="readonly" class="disabled" id="txtTarikhHantar" value="$beanMaklumatNotis.tarikhHantar" size="9" maxlength="10"></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Jangkamasa</td>
          <td>:</td>
          <td><input type="text" name="txtJangkaMasa" id="txtJangkaMasa" size="1" maxlength="2" value="$beanMaklumatNotis.jangkamasa" readonly="readonly" class="disabled">
            Hari</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Tarikh Dijangka Terima</td>
          <td>:</td>
          <td><input name="txtTarikhJangkaTerima" type="text" readonly="readonly" class="disabled" id="txtTarikhJangkaTerima" value="$beanMaklumatNotis.tarikhJangkaTerima" size="9" maxlength="10"></td>
        </tr>
        #end
        #if ($flagStatus == '2')
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td colspan="4"><fieldset>
            <legend>MAKLUMBALAS</legend>
            <table width="100%" border="0" cellspacing="2" cellpadding="2">
              <tr>
                <td width="1%">&nbsp;</td>
                <td width="28%">Tarikh Terima</td>
                <td width="1%">:</td>
                <td width="70%"><input name="txtTarikhTerima" type="text" $readonlyPopup class="$inputTextClassPopup" id="txtTarikhTerima" onBlur="check_date(this)" value="$beanMaklumatNotis.tarikhTerima" size="9" maxlength="10">
                  #if ($modePopup != 'view') <a href="javascript:displayDatePicker('txtTarikhTerima',false,'dmy');"><img border="0" src="../img/calendar.gif"/>#end</td>
              </tr>
              <tr>
                <td width="1%">&nbsp;</td>
                <td width="28%">Tarikh Surat</td>
                <td width="1%">:</td>
                <td width="70%"><input name="txtTarikhSurat" type="text" $readonlyPopup class="$inputTextClassPopup" id="txtTarikhSurat" onBlur="check_date(this)" value="$beanMaklumatNotis.tarikhSurat" size="9" maxlength="10">
                  #if ($modePopup != 'view') <a href="javascript:displayDatePicker('txtTarikhSurat',false,'dmy');"><img border="0" src="../img/calendar.gif"/>#end</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>No Rujukan Surat</td>
                <td>:</td>
                <td><input type="text" name="txtNoRujukan" id="txtNoRujukan" $readonlyPopup class="$inputTextClassPopup" size="50" value="$beanMaklumatNotis.noRujukan" onBlur="this.value=this.value.toUpperCase();"/></td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td valign="top">Ulasan</td>
                <td valign="top">:</td>
                <td valign="top"><textarea name="txtUlasan" id="txtUlasan" rows="5" cols="50" $readonlyPopup class="$inputTextClassPopup">$beanMaklumatNotis.ulasan</textarea></td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>Nama Pegawai</td>
                <td>:</td>
                <td><input type="text" name="txtNamaPegawai" id="txtNamaPegawai" value="$beanMaklumatNotis.namaPegawai" $readonlyPopup class="$inputTextClassPopup" size="50" onBlur="this.value=this.value.toUpperCase();"></td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>Jawatan</td>
                <td>:</td>
                <td><input name="txtJawatan" type="text" $readonlyPopup class="$inputTextClassPopup" id="txtJawatan" value="$beanMaklumatNotis.jawatan" size="50" onBlur="this.value=this.value.toUpperCase();"/>
                </td>
              </tr>
            </table>
            </fieldset></td>
        </tr>
        #end
        #end
      </table>
      </fieldset></td>
  </tr>
  #if ($modePopup != 'view')
  <tr>
    <td colspan="2" valign="bottom"><i><font color="#ff0000">Perhatian</font> : Pastikan label bertanda <font color="#ff0000">*</font> diisi.</i></td>
  </tr>
  #end
  <tr>
    <td width="30%">&nbsp;</td>
    <td width="70%"> #if ($modePopup == 'new')
      <input name="cmdSimpanNotis" type="button" onClick="doSimpanMaklumatNotis()" value="Simpan">
      <input name="cmdBatal" type="button" onClick="doBatalMaklumatNotis()" value="Batal">
      #end
      <!--
      #if ($modePopup == 'newUlangan')
      <input name="cmdSimpanNotis" type="button" onClick="doSimpanMaklumatUlanganNotis425()" value="Simpan">
      <input name="cmdBatalNotis" type="button" onClick="doBatalKemaskiniMaklumatNotis425()" value="Batal">
      #end
      -->
      #if ($modePopup == 'view')
      #if ($flagAktif != 'T')
      <input name="cmdKemaskiniNotis" type="button" onClick="doKemaskiniMaklumatNotis425()" value="Kemaskini">
      <input name="cmdHapusNotis" type="button" onClick="doHapusMaklumatNotis425()" value="Hapus">
      <input name="cmdBatal1" type="button" value="Kembali" onClick="batalDokumen()" />
      <!--
      #if ($flagStatus == '1')
      <input name="cmdTerimaNotis" type="button" onClick="doTerimaNotis425()" value="Terima">
      <input name="cmdUlanganNotis" type="button" onClick="doUlanganNotis425()" value="Ulangan">
      #end
      -->
      #end
      #end
      #if ($modePopup == 'update')
      <input name="cmdSimpanNotis" type="button" onClick="doSimpanKemaskiniMaklumatNotis425()" value="Simpan" />
      <input name="cmdBatalNotis" type="button" onClick="doBatalKemaskiniMaklumatNotis425()" value="Batal">
      #end </td>
  </tr>
</table>
