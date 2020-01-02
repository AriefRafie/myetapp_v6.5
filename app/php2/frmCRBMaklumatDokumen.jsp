<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td colspan="2"><fieldset>
      <legend>MAKLUMAT DOKUMEN</legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="1%">#if ($modePopup != 'view')<span class="style1">*</span>#end</td>
          <td width="28%">Jenis Dokumen</td>
          <td width="1%">:</td>
          <td width="70%">$selectDokumen</td>
        </tr>
        #if ($idDokumen == '1')
        <tr>
          <td>#if ($modePopup != 'view')<span class="style1">*</span>#end</td>
          <td>Surat ke </td>
          <td>:</td>
          <td><select name="idSuratKe" id="idSuratKe" style="width:100px;" $readonlyS class="$inputTextClassS" $inputTextClassS onChange="doChangeSuratKe()" >
              
              
                
	#if($idSuratKe == 'PTG')   
    
              
              
              <option value="">SILA PILIH</option>
              <option value="PTG" selected="selected"> 1 - PTG </option>
              <option value="PTD"> 2 - PTD </option>
              <option value="KJP"> 3 - KJP </option>
              <option value="KJT"> 4 - KJT </option>
              <option value="JKPTG"> 5 - JKPTG </option>
              
              
                      
    #elseif($idSuratKe == 'PTD') 
    
              
              
              <option value="">SILA PILIH</option>
              <option value="PTG"> 1 - PTG </option>
              <option value="PTD" selected="selected"> 2 - PTD </option>
              <option value="KJP"> 3 - KJP </option>
              <option value="KJT"> 4 - KJT </option>
              <option value="JKPTG"> 5 - JKPTG </option>
              
              
                
    #elseif($idSuratKe == 'KJP') 
    
              
              
              <option value="">SILA PILIH</option>
              <option value="PTG"> 1 - PTG </option>
              <option value="PTD"> 2 - PTD </option>
              <option value="KJP" selected="selected">3 - KJP </option>
              <option value="KJT"> 4 - KJT </option>
              <option value="JKPTG"> 5 - JKPTG </option>
              
              
                
    #elseif($idSuratKe == 'KJT') 
    
              
              
              <option value="">SILA PILIH</option>
              <option value="PTG"> 1 - PTG </option>
              <option value="PTD"> 2 - PTD </option>
              <option value="KJP"> 3 - KJP </option>
              <option value="KJT" selected="selected"> 4 - KJT </option>
              <option value="JKPTG"> 5 - JKPTG </option>
              
              
                  
    #elseif($idSuratKe == 'JKPTG') 
    
              
              
              <option value="">SILA PILIH</option>
              <option value="PTG"> 1 - PTG </option>
              <option value="PTD"> 2 - PTD </option>
              <option value="KJP"> 3 - KJP </option>
              <option value="KJT"> 4 - KJT </option>
              <option value="JKPTG" selected="selected"> 5 - JKPTG </option>
              
              
               
    #else
    
              
              
              <option value="" selected="selected">SILA PILIH</option>
              <option value="PTG"> 1 - PTG </option>
              <option value="PTD"> 2 - PTD </option>
              <option value="KJP"> 3 - KJP </option>
              <option value="KJT"> 4 - KJT </option>
              <option value="JKPTG"> 5 - JKPTG </option>
              
              
                
	#end
  
            
            
            </select>
          </td>
        </tr>
        #if ($idSuratKe == 'KJT')
        <tr>
          <td>#if ($modePopup != 'view')<span class="style1">*</span>#end</td>
          <td>Kementerian</td>
          <td>:</td>
          <td>$selectKementerian</td>
        </tr>
        <tr>
          <td>#if ($modePopup != 'view')<span class="style1">*</span>#end</td>
          <td>Agensi</td>
          <td>:</td>
          <td>$selectAgensi</td>
        </tr>
        #foreach ($beanMaklumatAgensi in $BeanMaklumatAgensi)
        <tr>
          <td>&nbsp;</td>
          <td>Nama</td>
          <td>:</td>
          <td>$beanMaklumatAgensi.namaAgensi</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Alamat</td>
          <td>:</td>
          <td>$beanMaklumatAgensi.alamat1</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>:</td>
          <td>$beanMaklumatAgensi.alamat2</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>:</td>
          <td>$beanMaklumatAgensi.alamat3</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Poskod</td>
          <td>:</td>
          <td>$beanMaklumatAgensi.poskod</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Negeri</td>
          <td>:</td>
          <td>$beanMaklumatAgensi.negeri</td>
        </tr>
        #end
        #elseif ($idSuratKe == 'KJP')
        #foreach ($beanMaklumatAgensi in $BeanMaklumatAgensi)
        <tr>
          <td>&nbsp;</td>
          <td>Nama</td>
          <td>:</td>
          <td>$beanMaklumatAgensi.namaAgensi</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Alamat</td>
          <td>:</td>
          <td>$beanMaklumatAgensi.alamat1</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>:</td>
          <td>$beanMaklumatAgensi.alamat2</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>:</td>
          <td>$beanMaklumatAgensi.alamat3</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Poskod</td>
          <td>:</td>
          <td>$beanMaklumatAgensi.poskod</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Negeri</td>
          <td>:</td>
          <td>$beanMaklumatAgensi.negeri</td>
        </tr>
        #end
        #elseif ($idSuratKe == 'PTG' || $idSuratKe == 'JKPTG' || $idSuratKe == 'PTD')
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
        #end
        #end
        #if ($idDokumen == '4')
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
        #end
        #foreach ($beanMaklumatKJT in $BeanMaklumatKJT)
        #if ($flagStatus == '1')
        <tr>
          <td>&nbsp;</td>
          <td>Tarikh Hantar</td>
          <td>:</td>
          <td><input name="txtTarikhHantar" type="text" $readonlyPopup class="$inputTextClassPopup" id="txtTarikhHantar" onBlur="check_date(this);calcDate()" value="$beanMaklumatKJT.tarikhHantar" size="9" maxlength="10">
            #if ($modePopup != 'view') <a href="javascript:displayDatePicker('txtTarikhHantar',false,'dmy');"><img border="0" src="../img/calendar.gif"/>#end</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Jangkamasa</td>
          <td>:</td>
          <td><input type="text" name="txtJangkaMasa" id="txtJangkaMasa" size="1" maxlength="2" value="$beanMaklumatKJT.jangkamasa" onBlur="validateNumber(this,this.value,'$beanMaklumatKJT.jangkamasa');calcDate()" $readonlyPopup class="$inputTextClassPopup">
            Hari</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Tarikh Dijangka Terima</td>
          <td>:</td>
          <td><input name="txtTarikhJangkaTerima" type="text" $readonlyPopup class="$inputTextClassPopup" id="txtTarikhJangkaTerima" onBlur="check_date(this)" value="$beanMaklumatKJT.tarikhJangkaTerima" size="9" maxlength="10">
            #if ($modePopup != 'view') <a href="javascript:displayDatePicker('txtTarikhJangkaTerima',false,'dmy');"><img border="0" src="../img/calendar.gif"/>#end</td>
        </tr>
        #else
        <tr>
          <td>&nbsp;</td>
          <td>Tarikh Hantar</td>
          <td>:</td>
          <td><input name="txtTarikhHantar" type="text" readonly="readonly" class="disabled" id="txtTarikhHantar" value="$beanMaklumatKJT.tarikhHantar" size="9" maxlength="10"></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Jangkamasa</td>
          <td>:</td>
          <td><input type="text" name="txtJangkaMasa" id="txtJangkaMasa" size="1" maxlength="2" value="$beanMaklumatKJT.jangkamasa" readonly="readonly" class="disabled">
            Hari</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Tarikh Dijangka Terima</td>
          <td>:</td>
          <td><input name="txtTarikhJangkaTerima" type="text" readonly="readonly" class="disabled" id="txtTarikhJangkaTerima" value="$beanMaklumatKJT.tarikhJangkaTerima" size="9" maxlength="10"></td>
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
                <td width="70%"><input name="txtTarikhTerima" type="text" $readonlyPopup class="$inputTextClassPopup" id="txtTarikhTerima" onBlur="check_date(this)" value="$beanMaklumatKJT.tarikhTerima" size="9" maxlength="10">
                  #if ($modePopup != 'view') <a href="javascript:displayDatePicker('txtTarikhTerima',false,'dmy');"><img border="0" src="../img/calendar.gif"/>#end</td>
              </tr>
              <tr>
                <td width="1%">&nbsp;</td>
                <td width="28%">Tarikh Surat</td>
                <td width="1%">:</td>
                <td width="70%"><input name="txtTarikhSurat" type="text" $readonlyPopup class="$inputTextClassPopup" id="txtTarikhSurat" onBlur="check_date(this)" value="$beanMaklumatKJT.tarikhSurat" size="9" maxlength="10">
                  #if ($modePopup != 'view') <a href="javascript:displayDatePicker('txtTarikhSurat',false,'dmy');"><img border="0" src="../img/calendar.gif"/>#end</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>No Rujukan Surat</td>
                <td>:</td>
                <td><input type="text" name="txtNoRujukan" id="txtNoRujukan" $readonlyPopup class="$inputTextClassPopup" size="50" value="$beanMaklumatKJT.noRujukan" onBlur="this.value=this.value.toUpperCase();"/></td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td valign="top">Ulasan</td>
                <td valign="top">:</td>
                <td valign="top"><textarea name="txtUlasan" id="txtUlasan" rows="5" cols="50" $readonlyPopup class="$inputTextClassPopup">$beanMaklumatKJT.ulasan</textarea></td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>Nama Pegawai</td>
                <td>:</td>
                <td><input type="text" name="txtNamaPegawai" id="txtNamaPegawai" value="$beanMaklumatKJT.namaPegawai" $readonlyPopup class="$inputTextClassPopup" size="50" onBlur="this.value=this.value.toUpperCase();"></td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>Jawatan</td>
                <td>:</td>
                <td><input name="txtJawatan" type="text" $readonlyPopup class="$inputTextClassPopup" id="txtJawatan" value="$beanMaklumatKJT.jawatan" size="50" onBlur="this.value=this.value.toUpperCase();"/>
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
      <input name="cmdSimpanKJT" type="button" onClick="doSimpanMaklumatKJT()" value="Simpan">
      <input name="cmdBatal" type="button" onClick="doBatalMaklumatKJT()" value="Batal">
      #end
      #if ($modePopup == 'newUlangan')
      <input name="cmdSimpan" type="button" onClick="doSimpanMaklumatUlanganKJT()" value="Simpan">
      <input name="cmdBatal" type="button" onClick="doBatalKemaskiniMaklumatKJT()" value="Batal">
      #end
      #if ($modePopup == 'view')
      #if ($flagAktif != 'T')
      <input name="cmdKemaskini" type="button" onClick="doKemaskiniMaklumatKJT()" value="Kemaskini">
      <input name="cmdHapus" type="button" onClick="doHapusMaklumatKJT()" value="Hapus">
      #if ($flagStatus == '1')
      <input name="cmdTerima" type="button" onClick="doTerimaKJT()" value="Terima">
      <input name="cmdUlangan" type="button" onClick="doUlanganKJT()" value="Ulangan">
      #end
      #end
      #end
      #if ($modePopup == 'update')
      <input name="cmdSimpan2" type="button" onClick="doSimpanKemaskiniMaklumatKJT()" value="Simpan" />
      <input name="cmdBatal" type="button" onClick="doBatalKemaskiniMaklumatKJT()" value="Batal">
      #end </td>
  </tr>
</table>
