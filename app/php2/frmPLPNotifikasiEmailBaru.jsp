##<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td colspan="4"><fieldset>
      <legend>MAKLUMAT NOTIFIKASI EMEL</legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
      	         <tr>
          <td width="1%"><span class="style1">*</span></td>
          <td width="28%">Surat Ke</td>
          <td width="1%">:</td>
          <td width="70%"><select name="idSuratKe" id="idSuratKe" style="width:100px;" $readonlyS class="$inputTextClassS" $inputTextClassS onChange="doChangeSuratKe()" >
   		  #if($idSuratKe == 'PTD')               
              <option value="">SILA PILIH</option>
              <option value="PTD" selected="selected"> 1 - PTD </option>
              <option value="PTG"> 2 - PTG </option>
              <option value="KJP"> 3 - KJP </option>
              <option value="JKPTG"> 4 - JKPTG </option>
          #elseif($idSuratKe == 'PTG') 
              <option value="">SILA PILIH</option>
              <option value="PTD"> 1 - PTD </option>
              <option value="PTG" selected="selected"> 2 - PTG </option>
              <option value="KJP"> 3 - KJP </option>
              <option value="JKPTG"> 4 - JKPTG </option>
          #elseif($idSuratKe == 'KJP') 
              <option value="">SILA PILIH</option>
              <option value="PTD"> 1 - PTD </option>
              <option value="PTG"> 2 - PTG </option>
              <option value="KJP" selected="selected"> 3 - KJP </option>
              <option value="JKPTG"> 4 - JKPTG </option>
          #elseif($idSuratKe == 'JKPTG') 
              <option value="">SILA PILIH</option>
              <option value="PTD"> 1 - PTD </option>
              <option value="PTG"> 2 - PTG </option>
              <option value="KJP"> 3 - KJP </option>
              <option value="JKPTG" selected="selected"> 4 - JKPTG </option>      
          #else
              <option value="" selected="selected">SILA PILIH</option>
              <option value="PTD"> 1 - PTD </option>
              <option value="PTG"> 2 - PTG </option>
              <option value="KJP"> 3 - KJP </option>
              <option value="JKPTG"> 4 - JKPTG </option>       
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
         <tr>
          <td><span class="style1">*</span></td>
          <td>Nama</td>
          <td>:</td>
          <td><input type="text" name="txtNamaPegawai" id="namaPegawai" value="" class="" size="50"></td>
        </tr>
        <tr>
          <td><span class="style1">*</span></td>
          <td>Emel</td>
          <td>:</td>
          <td><input type="text" name="txtEmel" id="emel" value="" class="" size="50"></td>
        </tr>
        <tr>
          <td><span class="style1">*</span></td>
          <td>No.Telefon</td>
          <td>:</td>
          <td><input name="txtNoTelefon" type="text" class="" id="noTelefon" value="" size="50"/>
          </td>
        </tr>
##        <tr>
##          <td><span class="style1">*</span></td>
##          <td>Tarikh Hantar</td>
##          <td>:</td>
##          <td><input name="txtTarikhHantar" type="text" $readonlyPopup class="$inputTextClassPopup" id="txtTarikhHantar" onBlur="check_date(this);calcDate()" value="" size="9" maxlength="10">
##            <a href="javascript:displayDatePicker('txtTarikhHantar',false,'dmy');"><img border="0" src="../img/calendar.gif"/></td>
##        </tr>
        <tr>
		  <td>&nbsp;</td>
		  <td>&nbsp;</td>
		  <td>&nbsp;</td>
      	  <td>
      	  <input name="cmdSimpanNotis" type="button" onClick="doSimpanRekodNotifikasiEmail()" value="Simpan">
      	  <input name="cmdBatal" type="button" onClick="doBatalMaklumatSuratPenghargaan()" value="Batal">
    	  </td>
        </tr>
      </table>
      </fieldset></td>
  </tr>
##</table>
