<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td colspan="4"><fieldset>
      <legend>UNTUK PIHAK</legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
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
          <td>Jawatan</td>
          <td>:</td>
          <td><input name="txtJawatan" type="text" class="" id="jawatan" value="" size="50"/>
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
      	   #if ($flagPopup == 'openJUPEM')
      	  <input name="cmdSimpanNotis" type="button" onClick="doSimpanRekodEmailJUPEM()" value="Simpan">
      	   #end      	  
      	   #if ($flagPopup == 'openJAS')
      	  <input name="cmdSimpanNotis" type="button" onClick="doSimpanRekodEmailJAS()" value="Simpan">
      	   #end
      	   #if ($flagPopup == 'openJMG')
      	  <input name="cmdSimpanNotis" type="button" onClick="doSimpanRekodEmailJMG()" value="Simpan">
      	   #end
      	   #if ($flagPopup == 'openJP')
      	  <input name="cmdSimpanNotis" type="button" onClick="doSimpanRekodEmailJP()" value="Simpan">
      	   #end
      	   #if ($flagPopup == 'openJLM')
      	  <input name="cmdSimpanNotis" type="button" onClick="doSimpanRekodEmailJLM()" value="Simpan">
      	   #end
      	   #if ($flagPopup == 'openPHM')
      	  <input name="cmdSimpanNotis" type="button" onClick="doSimpanRekodEmailPHM()" value="Simpan">
      	   #end
      	   #if ($flagPopup == 'openJPS')
      	  <input name="cmdSimpanNotis" type="button" onClick="doSimpanRekodEmailJPS()" value="Simpan">
      	   #end
      	   #if ($flagPopup == 'openPTG')
      	  <input name="cmdSimpanNotis" type="button" onClick="doSimpanRekodEmailPTG()" value="Simpan">
      	   #end
      	  <input name="cmdBatal" type="button" onClick="doBatalSimpanRekodEmailJUPEM()" value="Batal">
    	  </td>
        </tr>
      </table>
      </fieldset></td>
  </tr>
</table>
