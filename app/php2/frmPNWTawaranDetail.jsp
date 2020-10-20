<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td><fieldset>
      <legend>MAKLUMAT PENERIMA TAWARAN</legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        #foreach($beanHeader in $BeanHeader)
        <tr>
          <td width="1%">&nbsp;</td>
          <td width="28%">No. Rujukan Surat JKPTG</td>
          <td width="1%">:</td>
          <td width="63%">$beanHeader.noFail
         	<input type="hidden" name="txtNoRujukanJKPTG" id="txtNoRujukanJKPTG" value="$beanHeader.noFail"/>
          </td>
        </tr>
        #end
        #foreach ($beanMaklumatAgensi in $BeanMaklumatAgensi)
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
        <tr>
          <td width="1%">#if ($modePopup != 'view')<span class="style1">*</span>#end</td>
          <td width="28%">No. Rujukan Surat KJP</td>
          <td width="1%">:</td>
          <td width="63%">
         	<input type="text" name="txtNoRujukanKJP" id="txtNoRujukanKJP" value="$!beanMaklumatAgensi.noRujukanSuratKJP" $readonlyPopup 
         			class="$inputTextClassPopup" size="43" onblur="this.value=this.value.toUpperCase();"/>
          </td>
        </tr>
        <tr>
          <td width="1%">#if ($modePopup != 'view')<span class="style1">*</span>#end</td>
          <td width="28%">Tarikh Surat</td>
          <td width="1%">:</td>
          <td width="70%"><input type="text" name="txtTarikhTerima" id="txtTarikhTerima" value="$beanMaklumatAgensi.tarikhTerima" onblur="check_date(this)" size="9" $readonlyPopup class="$inputTextClassPopup"/>
            #if ($modePopup != 'view') <a href="javascript:displayDatePicker('txtTarikhTerima',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a>#end </td>
        </tr>
        <tr>
          <td valign="top">#if ($modePopup != 'view')<span class="style1">*</span>#end</td>
          <td valign="top">Tujuan Kegunaan</td>
          <td valign="top">:</td>
          <td valign="top"><textarea name="txtTujuanKegunaan" id="txtTujuanKegunaan" rows="5" cols="50" $readonlyPopup class="$inputTextClassPopup">$beanMaklumatAgensi.tujuanKegunaan</textarea></td>
        </tr>
        #end
        #foreach ($beanMaklumatImejan in $BeanMaklumatImejan)
   		<tr>
   			#if ($modeDokumen == 'addDokumen')
	          <td width="1%">&nbsp;</td>
	          <td width="28%">&nbsp;</td>
	          <td width="1%">&nbsp; <input type="hidden" id="idDokumen" name="idDokumen" value="$beanMaklumatImejan.idDokumen" /> </td>
	          <td width="70%"><img src="../servlet/ekptg.view.php2.FrmDisplayImage?id=$beanMaklumatImejan.idDokumen" alt="Imej Pelan" border="1" width="250" height="250" onclick="cetakImej('$beanMaklumatImejan.idDokumen')"/></td>
   			#end
        </tr>
        <tr>
        	#if ($modePopup != 'view')        
	          <td width="1%"></td>
	          <td width="28%">Minit Mesyuarat</td>
	          <td width="1%">:</td>
	          <td width="70%"><input id="fileupload" name="fileupload" type="file" size="40" $readonlyPopup  class="$inputTextClassPopup" /></td>
        	#end
        </tr>        
        #end
        
        
        
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
        #if ($modePopup != 'view')
        <tr>
          <td colspan="4" valign="bottom"><i><font color="#ff0000">Perhatian</font>: Pastikan label bertanda <font color="#ff0000">*</font> diisi.</i></td>
        </tr>
        #end
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td> 
          	#if ($modePopup == 'new')
	            <input name="cmdSimpan" type="button" value="Simpan" onClick="javascript:simpanAgensi()" />
	            <input name="cmdBatal" type="button" value="Batal" onClick="javascript:batalAgensi()" />
            #end
            #if ($modePopup == 'view')
	            <input name="cmdKemaskini" type="button" value="Kemaskini" onClick="javascript:kemaskiniAgensi()" />
	            <input name="cmdHapus" type="button" value="Hapus" onClick="javascript:hapusAgensi()" >
            #end
            #if ($modePopup == 'update')
	            <input name="cmdSimpanKemaskini" type="button" value="Simpan" onClick="javascript:simpanKemaskiniAgensi()" />
	            <input name="txtBatal" type="button" value="Batal" onClick="javascript:batalKemaskiniAgensi()" />
            	#if ($modeDokumen == 'noDokumen')
	            	<input name="cmdSimpanKemaskini" type="button" value="Tambah Minit Mesyuarat" onClick="javascript:tambahMinitMesyuarat()" />
	            #end
            #end 
          </td>
        </tr>
      </table>
      </fieldset></td>
  </tr>
</table>
