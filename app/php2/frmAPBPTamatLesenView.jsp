<style type="text/css">
<!--
.style1 {
	color: #FF0000
}
-->
</style>
#set($saizSebabTamat="500")
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td>
      #foreach($beanMaklumatMohonTamat in $BeanMaklumatMohonTamat)
      <table align="center" width="100%">
        <tr>
          <td>#if ($modePopup == 'update')<span class="style1">*</span>#end</td>
          <td valign="top">Permohonan Dari</td>
          <td>:</td>
          <td><select name="socFlagDari" id="socFlagDari" style="width:100px;" $readonly class="$inputTextClass" $inputTextClass >
                    
        	     #if($beanMaklumatMohonTamat.socFlagDari=='J')
                   
            <option value="">SILA PILIH</option>
              <option value="J" selected="selected">J - JKPTG</option>
              <option value="P">P - PELESEN</option>
            
                #elseif ($beanMaklumatMohonTamat.socFlagDari=='P')
                   
            <option value="">SILA PILIH</option>
              <option value="J">J - JKPTG</option>
              <option value="P" selected="selected">P - PELESEN</option>
                          
                #elseif ($beanMaklumatMohonTamat.socFlagDari=='')
                   
            <option value="" selected="selected">SILA PILIH</option>
              <option value="J">J - JKPTG</option>
              <option value="P">P - PELESEN</option>
            
                #end                    
             
          </select>
          </td>
        </tr>
        <tr>
          <td width="1%">#if ($mode == 'update')<span class="style1">*</span>#end</td>
          <td width="30%" valign="top">Tarikh Surat</td>
          <td width="1%">:</td>
          <td width="68%"><input name="tarikhSurat" type="text" class="$inputTextClass" id="tarikhSurat" value="$beanMaklumatMohonTamat.tarikhSurat" size="9" maxlength="10" $readonly onBlur="check_date(this);validateTarikh()"/>
            <a href="javascript:displayDatePicker('tarikhSurat',false,'dmy');">#if ($mode != 'view')<img border="0" src="../img/calendar.gif"/>#end
            <div id="tarikhTerima_check" style="color:red" ></div></td>
        </tr>
        <tr>
          <td width="1%">#if ($mode == 'update')<span class="style1">*</span>#end</td>
          <td valign="top">Tarikh Terima</td>
          <td>:</td>
          <td><input name="tarikhTerima" type="text" class="$inputTextClass" id="tarikhTerima" value="$beanMaklumatMohonTamat.tarikhTerima" size="9" maxlength="10" $readonly onBlur="check_date(this);validateTarikh()"/>
            <a href="javascript:displayDatePicker('tarikhTerima',false,'dmy');">#if ($mode != 'view')<img border="0" src="../img/calendar.gif"/>#end</td>
        </tr>
        <tr>
          <td valign="top">#if ($mode == 'update')<span class="style1"></span>#end</td>
          <td valign="top">No Rujukan</td>
          <td valign="top">:</td>
          <td valign="top">
            <input name="rujukan" type="text" id="rujukan" value="$beanMaklumatMohonTamat.rujukan" size="40" maxlength="80" $readonly class="$inputTextClass" >          </td>
        </tr>
        <tr>
          <td valign="top">#if ($mode == 'update')<span class="style1">*</span>#end</td>
          <td valign="top">Sebab Penamatan</td>
          <td valign="top">:</td>
          <td valign="top"><textarea name="sebabTamat" cols="40" rows="5" id="sebabTamat" $readonly class="$inputTextClass"   onKeyUp="textCounter(this.form.sebabTamat,this.form.remLen1,$!saizSebabTamat);" onKeyDown="textCounter(this.form.sebabTamat,this.form.remLen1,$!saizSebabTamat);" >$beanMaklumatMohonTamat.sebabTamat</textarea></td>
        </tr>
       #if ($mode == 'update')
        <tr>
          <td valign="bottom">&nbsp;</td>
          <td valign="bottom">&nbsp;</td>
          <td valign="bottom">&nbsp;</td>
          <td valign="bottom">Baki Aksara :&nbsp;
            <input type="text" readonly="readonly" class="disabled" name="remLen1" size="3" maxlength="3" value="$!saizSebabTamat" /></td>
        </tr>
        #end
        #if ($mode == 'update')
        <tr>
          <td colspan="4" valign="bottom"><i><font color="#ff0000">Perhatian</font> : Pastikan label bertanda <font color="#ff0000">*</font> diisi.</i></td>
        </tr>
        #end
        <tr>
          <td></td>
          <td></td>
          <td></td>
          <td> 
          #if ($mode == 'update')
            <input type="button" name="cmdDaftarBaru" id="cmdSimpanKemaskini" value="Simpan" onClick="doSimpanKemaskiniTamatLesen()"/>
            <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onClick="doBatalMohonTamat()"/>
          #end 
          #if ($mode == 'view')
          	<input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onClick="doKemaskiniTamatLesen()"/>
            <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="doBackToList()"/>
          #end          </td>
        </tr>
        <tr>
          <td colspan="4">&nbsp;</td>
        </tr>
        #end
      </table>
    </td>
  </tr>
</table>
