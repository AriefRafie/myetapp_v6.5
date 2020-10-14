<table width="100%" border="0" cellspacing="2" cellpadding="2"> 
  <tr>
    <td colspan="2"><fieldset>
      <legend>MAKLUMAT KJP</legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        #foreach ($beanMaklumatKJP in $BeanMaklumatKJP)
        #set ($flagKeputusanKJP = $!beanMaklumatKJP.flagKeputusan)
        <tr>
          <td width="1%">&nbsp;</td>
          <td width="28%">Kementerian</td>
          <td width="1%">:</td>
          <td width="70%">$beanMaklumatKJP.kementerian</td>
        </tr>
        <tr>
          <td width="1%">&nbsp;</td>
          <td width="28%">Agensi</td>
          <td width="1%">:</td>
          <td width="70%">$beanMaklumatKJP.agensi</td>
        </tr>
        #if ($flagStatus == '1')
        <tr>
          <td width="1%">&nbsp;</td>
          <td width="28%">Tarikh Hantar</td>
          <td width="1%">:</td>
          <td width="70%"><input name="txtTarikhHantar" type="text" $readonlyPopup class="$inputTextClassPopup" id="txtTarikhHantar" onBlur="check_date(this);calcDate()" value="$beanMaklumatKJP.tarikhHantar" size="9" maxlength="10">
            #if ($modePopup != 'view') <a href="javascript:displayDatePicker('txtTarikhHantar',false,'dmy');"><img border="0" src="../img/calendar.gif"/>#end</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Jangkamasa</td>
          <td>:</td>
          <td><input type="text" name="txtJangkaMasa" id="txtJangkaMasa" size="1" maxlength="2" value="$beanMaklumatKJP.jangkamasa" onBlur="validateNumber(this,this.value,'$beanMaklumatKJP.jangkamasa');calcDate()" $readonlyPopup class="$inputTextClassPopup">
            Hari</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Tarikh Dijangka Terima</td>
          <td>:</td>
          <td><input name="txtTarikhJangkaTerima" type="text" $readonlyPopup class="$inputTextClassPopup" id="txtTarikhJangkaTerima" onBlur="check_date(this)" value="$beanMaklumatKJP.tarikhJangkaTerima" size="9" maxlength="10">
            #if ($modePopup != 'view') <a href="javascript:displayDatePicker('txtTarikhJangkaTerima',false,'dmy');"><img border="0" src="../img/calendar.gif"/>#end</td>
        </tr>
        #else
        <tr>
          <td width="1%">&nbsp;</td>
          <td width="28%">Tarikh Hantar</td>
          <td width="1%">:</td>
          <td width="70%"><input name="txtTarikhHantar" type="text" readonly="readonly" class="disabled" id="txtTarikhHantar" value="$beanMaklumatKJP.tarikhHantar" size="9" maxlength="10"></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Jangkamasa</td>
          <td>:</td>
          <td><input type="text" name="txtJangkaMasa" id="txtJangkaMasa" size="1" maxlength="2" value="$beanMaklumatKJP.jangkamasa" readonly="readonly" class="disabled">
            Hari</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Tarikh Dijangka Terima</td>
          <td>:</td>
          <td><input name="txtTarikhJangkaTerima" type="text" readonly="readonly" class="disabled" id="txtTarikhJangkaTerima" value="$beanMaklumatKJP.tarikhJangkaTerima" size="9" maxlength="10"></td>
        </tr>
        #end
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
        #if ($flagStatus == '2')
        <tr>
          <td colspan="4"><fieldset>
            <legend>MAKLUMBALAS KJP</legend>
            <table width="100%" border="0" cellspacing="2" cellpadding="2">
              <tr>
                <td width="1%">#if ($modePopup != 'view')<span class="style1">*</span>#end</td>
                <td width="28%">Tarikh Terima</td>
                <td width="1%">:</td>
                <td width="70%"><input name="txtTarikhTerima" type="text" $readonlyPopup class="$inputTextClassPopup" id="txtTarikhTerima" onBlur="check_date(this)" value="$beanMaklumatKJP.tarikhTerima" size="9" maxlength="10">
                  #if ($modePopup != 'view') <a href="javascript:displayDatePicker('txtTarikhTerima',false,'dmy');"><img border="0" src="../img/calendar.gif"/>#end</td>
              </tr>
              <tr>
                <td width="1%">#if ($modePopup != 'view')<span class="style1">*</span>#end</td>
                <td width="28%">Tarikh Surat</td>
                <td width="1%">:</td>
                <td width="70%"><input name="txtTarikhSurat" type="text" $readonlyPopup class="$inputTextClassPopup" id="txtTarikhSurat" onBlur="check_date(this)" value="$beanMaklumatKJP.tarikhSurat" size="9" maxlength="10">
                  #if ($modePopup != 'view') <a href="javascript:displayDatePicker('txtTarikhSurat',false,'dmy');"><img border="0" src="../img/calendar.gif"/>#end</td>
              </tr>
              <tr>
                <td>#if ($modePopup != 'view')<span class="style1">*</span>#end</td>
                <td>No Rujukan Surat</td>
                <td>:</td>
                <td><input type="text" name="txtNoRujukanSurat" id="txtNoRujukanSurat" $readonlyPopup class="$inputTextClassPopup" size="50" value="$beanMaklumatKJP.noRujukan" onblur="this.value=this.value.toUpperCase();"/></td>
              </tr>
              <tr>
                <td>#if ($modePopup != 'view')<span class="style1">*</span>#end</td>
                <td>Keputusan</td>
                <td>:</td>
                <td><select name="socKeputusan" id="socKeputusan" style="width:140px;" $readonlyPopup class="$inputTextClassPopup" onchange="javascript:doChangeKeputusanKJP(this.value)" $disabled>
		            #if ($beanMaklumatKJP.flagKeputusan == 'L')
		            	<option>SILA PILIH</option>
		                <option value="L" selected="selected">LULUS</option>
		                <option value="T">TOLAK</option>
		                <option value="G">LULUS BERSYARAT</option>
		            #elseif ($beanMaklumatKJP.flagKeputusan == 'T')
		                <option>SILA PILIH</option>
		                <option value="L">LULUS</option>
		                <option value="T" selected="selected">TOLAK</option>
		                <option value="G">LULUS BERSYARAT</option>
		            #elseif ($beanMaklumatKJP.flagKeputusan == 'G')
		                <option>SILA PILIH</option>
		                <option value="L">LULUS</option>
		                <option value="T">TOLAK</option>
		                <option value="G" selected="selected">LULUS BERSYARAT</option>
		            #else
		                <option selected="selected">SILA PILIH</option>
		                <option value="L">LULUS</option>
		                <option value="T">TOLAK</option>
		                <option value="G">LULUS BERSYARAT</option>
		            #end
		        </select></td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td valign="top">Ulasan</td>
                <td valign="top">:</td>
                <td valign="top"><textarea name="txtUlasan" id="txtUlasan" rows="5" cols="50" $readonlyPopup class="$inputTextClassPopup">$beanMaklumatKJP.ulasan</textarea></td>
              </tr>
              <tr>
	          	<td>&nbsp;</td>
	          	<td>&nbsp;</td>
	          	<td>&nbsp;</td>
	          	<td>&nbsp;</td>
	          </tr>
			  #if ($flagKeputusanKJP == 'G')
	          <tr>
	          	<td colspan="4"><fieldset>
	              <legend>MAKLUMBALAS PEMOHON</legend>
	              <table width="100%" border="0" cellspacing="2" cellpadding="2">
             		<tr>
			          <td width="1%">&nbsp;</td>
			          <td width="28%">Keputusan Pemohon</td>
			          <td width="1%">:</td>
			          <td><select name="socKeputusanPemohon" id="socKeputusanPemohon" style="width:140px;" $readonlyPopup class="$inputTextClassPopup" $disabled>
			          	#if ($beanMaklumatKJP.flagKeputusanPemohon == 'S')
			            	<option>SILA PILIH</option>
			                <option value="S" selected="selected">SETUJU</option>
			                <option value="TS">TIDAK SETUJU</option>
		           		#elseif ($beanMaklumatKJP.flagKeputusanPemohon == 'TS')
			                <option>SILA PILIH</option>
			                <option value="S">SETUJU</option>
			                <option value="TS" selected="selected">TIDAK SETUJU</option>
			            #else
			                <option selected="selected">SILA PILIH</option>
			                <option value="S">SETUJU</option>
			                <option value="TS">TIDAK SETUJU</option>
			            #end
			          </select></td>
			        </tr>
             		<tr>
			          <td width="1%">&nbsp;</td>
			          <td width="28%">Ulasan Pemohon</td>
			          <td width="1%">:</td>
			          <td width="70%"><textarea name="txtUlasanPemohon" id="txtUlasanPemohon" rows="5" cols="50" $readonlyPopup class="$inputTextClassPopup">$beanMaklumatKJP.ulasanPemohon</textarea></td>
              		</tr>
	          	  </table>
	          	</fieldset></td>
	          </tr>
	          #end
	          #if ($modePopup == 'view')
              <tr>
                <td>&nbsp;</td>
                <td valign="top">&nbsp;</td>
                <td valign="top">&nbsp;</td>
                <td valign="top">&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td valign="top">Lampiran</td>
                <td valign="top">:</td>
                <td valign="top"> #if ($idLampiran != '')<a href="javascript:cetakLampiran($idLampiran)" class="style2">$!namaFailLampiran</a> #end
                &nbsp;&nbsp;&nbsp;&nbsp;
                <input name="cmdUpload" type="button" onclick="uploadDoc($idUlasanTeknikal)" value="Muat Naik Lampiran" /></td>
              </tr>
              #end
	         </table>
	        </fieldset></td>
	      </tr>
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
    <td width="70%"> 
    	#if ($modePopup == 'new')
	      <input name="cmdSimpan" type="button" onClick="doSimpanMaklumatKJP()" value="Hantar">
	      <input name="cmdBatal" type="button" onClick="doBatalMaklumatKJP()" value="Batal">
      	#end
      	#if ($modePopup == 'newUlangan')
	      <input name="cmdSimpan" type="button" onClick="doSimpanMaklumatUlanganKJP()" value="Hantar">
	      <input name="cmdBatal" type="button" onClick="doBatalKemaskiniMaklumatKJP()" value="Batal">
	    #end
      	#if ($modePopup == 'view')
      		#if ($!{session.getAttribute("FLAG_FROM")} == 'failTugasan' || $!{session.getAttribute("FLAG_FROM")} == 'failHQ')
      			#if ($flagAktif != 'T')
			      <input name="cmdKemaskini" type="button" onClick="doKemaskiniMaklumatKJP()" value="Kemaskini">
			      <input name="cmdHapus" type="button" onClick="doHapusMaklumatKJP()" value="Hapus">
      				#if ($flagStatus == '1')
				      <input name="cmdTerima" type="button" onClick="doTerimaKJP()" value="Terima">
				      <input name="cmdUlangan" type="button" onClick="doUlanganKJP()" value="Ulangan">
      				#end
      				#if ($flagKeputusanKJP == 'G')
      					<input name="cmdCetak" type="button" onClick="cetakPLPSuratLulusBersyarat('$idFail')" value="Cetak Surat Lulus Bersyarat">
      				#elseif ($flagKeputusanKJP == 'T')
      					<input name="cmdCetak" type="button" onClick="javascript:cetakPLPSuratTolak('$idFail')" value="Cetak Surat Tolak">
      				#end
      			#end
      		#end
      	#end
      	#if ($modePopup == 'update')
	      <input name="cmdSimpan2" type="button" onclick="doSimpanKemaskiniMaklumatKJP()" value="Hantar" />
	      <input name="cmdBatal" type="button" onClick="doBatalKemaskiniMaklumatKJP()" value="Batal">
      	#end 
    </td>
  </tr>
</table>
