#set($saizTxtLain = "4000")

<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td><fieldset>
      <legend>MAKLUMAT PERTINDIHAN</legend>      
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        #foreach ($beanMaklumatPertindihan in $BeanMaklumatPertindihan)
        <tr>
          <td width="1%">#if ($modePopup != 'view')<span class="style1">*</span>#end</td>
          <td width="28%">No Fail Syarikat </td>
          <td width="1%">:</td>
          <td width="70%"><input name="txtNoFailTindih" type="text" class="$inputTextClassPopup" id="txtNoFailTindih" value="$beanMaklumatPertindihan.txtNoFailTindih" size="50" maxlength="200" $readonlyPopup /></td>
        </tr>
        <tr>
          <td>#if ($modePopup != 'view')<span class="style1">*</span>#end</td>
          <td>Nama Syarikat</td>
          <td>:</td>
          <td><input name="txtNamaSyarikat" type="text" class="$inputTextClassPopup" id="txtNamaSyarikat" value="$beanMaklumatPertindihan.txtNamaSyarikat" size="50" maxlength="200" $readonlyPopup /></td>
        </tr>
        <tr>
          <td>#if ($modePopup != 'view')<span class="style1">*</span>#end</td>
          <td>Jenis Pertindihan</td>
          <td>:</td>
          <td><select name="socJenisTindih" id="socJenisTindih" style="width:200px;" $readonlyPopup class="$inputTextClassPopup" $inputTextClassPopup>
					#if($beanMaklumatPertindihan.socJenisTindih == '1')               
		              <option value="">SILA PILIH</option>
		              <option value="1" selected="selected">1 - SELURUH</option>
		              <option value="2">2 - SEBAHAGIAN </option>
		    		#elseif($beanMaklumatPertindihan.socJenisTindih == '2')              
		              <option value="">SILA PILIH</option>
		              <option value="1">1 - SELURUH</option>
		              <option value="2" selected="selected">2 - SEBAHAGIAN </option>
		    		#elseif($beanMaklumatPertindihan.socJenisTindih == '')             
		              <option value="" selected="selected">SILA PILIH</option>
		              <option value="1">1 - SELURUH</option>
		              <option value="2">2 - SEBAHAGIAN </option>
		    		#end                                                             
            </select>
          </td>
        </tr>
        <tr>
          <td valign="top">#if ($modePopup != 'view')<span class="style1">*</span>#end</td>
          <td valign="top">Status Koordinat</td>
          <td valign="top">:</td>
          <td valign="top"><select name="socStatusTindih" id="socStatusTindih" style="width:250px;" $readonlyPopup class="$inputTextClassPopup" $inputTextClassPopup>
				#if($beanMaklumatPertindihan.socStatusTindih == '1')               
			              <option value="">SILA PILIH</option>
			              <option value="1" selected="selected">1 - LULUS SECARA DASAR</option>
			              <option value="2">2 - TELAH DIKELUARKAN LESEN </option>
			              <option value="3">3 - DITOLAK </option>
			              <option value="4">4 - LAIN-LAIN </option>
			    #elseif($beanMaklumatPertindihan.socStatusTindih == '2')   
			              <option value="">SILA PILIH</option>
			              <option value="1">1 - LULUS SECARA DASAR</option>
			              <option value="2" selected="selected">2 - TELAH DIKELUARKAN LESEN </option>
			              <option value="3">3 - DITOLAK </option>
			              <option value="4">4 - LAIN-LAIN </option>
			   #elseif($beanMaklumatPertindihan.socStatusTindih == '3')     
			              <option value="">SILA PILIH</option>
			              <option value="1">1 - LULUS SECARA DASAR</option>
			              <option value="2">2 - TELAH DIKELUARKAN LESEN </option>
			              <option value="3" selected="selected">3 - DITOLAK </option>
			              <option value="4">4 - LAIN-LAIN </option>
			   #elseif($beanMaklumatPertindihan.socStatusTindih == '4') 
			              <option value="">SILA PILIH</option>
			              <option value="1">1 - LULUS SECARA DASAR</option>
			              <option value="2">2 - TELAH DIKELUARKAN LESEN </option>
			              <option value="3">3 - DITOLAK </option>
			              <option value="4" selected="selected">4 - LAIN-LAIN </option>
			   #elseif($beanMaklumatPertindihan.socStatusTindih == '')   
			              <option value="" selected="selected">SILA PILIH</option>
			              <option value="1">1 - LULUS SECARA DASAR</option>
			              <option value="2">2 - TELAH DIKELUARKAN LESEN </option>
			              <option value="3">3 - DITOLAK </option>
			              <option value="4">4 - LAIN-LAIN </option>
			    #end                                                             
            </select>
          </td>
        </tr>
        <tr>
          <td valign="top">&nbsp;</td>
          <td valign="top">Status / Catatan</td>
          <td valign="top">:</td>
          <td valign="top"><p>
              <textarea name="txtLain" id="txtLain" rows="5" cols="50" $readonlyPopup class="$inputTextClassPopup" onKeyUp="textCounter(this.form.txtLain,this.form.remLen22,$!saizTxtLain);" onKeyDown="textCounter(this.form.txtLain,this.form.remLen22,$!saizTxtLain);">$beanMaklumatPertindihan.txtLain</textarea>
            </p></td>
        </tr>
        <tr>
          <td valign="top">&nbsp;</td>
          <td valign="top">&nbsp;</td>
          <td valign="top">&nbsp;</td>
          <td valign="top">Baki Aksara :&nbsp;
            <input type="text" readonly="readonly" class="disabled" name="remLen22" size="3" maxlength="3" value="$!saizTxtLain" /></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td colspan="4" valign="bottom"><i><font color="#ff0000">Perhatian</font> : Pastikan label bertanda <font color="#ff0000">*</font> diisi.</i></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>#if ($modePopup == 'new')
            <input name="cmdSimpan" type="button" onclick="doSimpanMaklumatPertindihanKoordinat()" value="Simpan" />
            <input name="cmdBatal" type="button" onclick="doBatalMaklumatPertindihanKoordinat()" value="Batal" />
            #end
            #if ($modePopup == 'view')
            <input name="cmdKemaskini" type="button" onclick="doKemaskiniMaklumatPertindihanKoordinat()" value="Kemaskini" />
            <input name="cmdHapus" type="button" onclick="doHapusMaklumatPertindihanKoordinat()" value="Hapus" />
            <input name="cmdBatal" type="button" onclick="doBatalMaklumatPertindihanKoordinat()" value="Batal" />
            #end
            #if ($modePopup == 'update')
            <input name="cmdSimpan2" type="button" onclick="doSimpanKemaskiniMaklumatPertindihanKoordinat()" value="Simpan" />
            <input name="cmdBatal" type="button" onclick="doBatalKemaskiniMaklumatPertindihanKoordinat()" value="Batal" />
            #end </td>
        </tr>
        #end
      </table>
      </fieldset></td>
  </tr>
</table>