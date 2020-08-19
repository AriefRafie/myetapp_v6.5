<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td><fieldset>
      <legend>MAKLUMAT MESYUARAT</legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        #foreach ($beanMaklumatMesyuarat in $BeanMaklumatMesyuarat)
        <tr>
          <td width="1%" valign="top">#if ($modePopup != 'view')<span class="style2">*</span>#end</td>
          <td width="28%" valign="top">Tajuk Mesyuarat</td>
          <td width="1%" valign="top">:</td>
          <td width="70%" valign="top"><textarea name="txtTajukMesyuarat" id="txtTajukMesyuarat" rows="5" cols="50" $readonlyPopup class="$inputTextClassPopup" onblur="this.value=this.value.toUpperCase();">$beanMaklumatMesyuarat.tajukMesyuarat</textarea></td>
        </tr>
        <tr>
          <td>#if ($modePopup != 'view')<span class="style2">*</span>#end</td>
          <td>Bil. Mesyuarat</td>
          <td>:</td>
          <td><input name="txtBilMesyuarat" type="text" class="$inputTextClassPopup" id="txtBilMesyuarat" value="$beanMaklumatMesyuarat.bilMesyuarat" size="9" maxlength="12" $readonlyPopup onblur="this.value=this.value.toUpperCase();"/></td>
        </tr>
        <tr>
          <td width="1%">#if ($modePopup != 'view')<span class="style2">*</span>#end</td>
          <td width="28%">Tarikh Mesyuarat</td>
          <td width="1%">:</td>
          <td width="70%"><input type="text" name="txtTarikhMesyuarat" id="txtTarikhMesyuarat" size="9" onBlur="check_date(this)" value="$beanMaklumatMesyuarat.tarikhMesyuarat" $readonlyPopup class="$inputTextClassPopup">
            #if ($modePopup != 'view') <a href="javascript:displayDatePicker('txtTarikhMesyuarat',false,'dmy');"><img border="0" src="../img/calendar.gif"/> #end </td>
        </tr>
        <tr>
          <td>#if ($modePopup != 'view')<span class="style2">*</span>#end</td>
          <td>Masa Mesyuarat</td>
          <td>:</td>
          <td>$selectJamDari$selectMinitDari
            &nbsp;Hingga&nbsp;
            $selectJamHingga$selectMinitHingga </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Tempat Mesyuarat</td>
          <td>:</td>
          <td>$selectLokasi</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td colspan="4"><fieldset>
            <legend>SYOR</legend>
            <table width="100%" border="0" cellspacing="2" cellpadding="2">
              <tr>
                <td width="1%">&nbsp;</td>
                <td width="28%">Syor</td>
                <td width="1%">:</td>
                <td width="70%">
                <select name="socSyor" id="socSyor" onChange="onChangeValue(this)" $readonlyPopup class="$inputTextClassPopup" $inputTextClassPopup>
    			#if($beanMaklumatMesyuarat.flagSyor == 'L')        
                    <option value="">SILA PILIH</option>
                    <option value="L" selected="selected">L - LULUS</option>
                    <option value="T">T - TOLAK</option>
                    <option value="G">G - LULUS BERSYARAT</option>
                #elseif($beanMaklumatMesyuarat.flagSyor == 'T')
					<option value="">SILA PILIH</option>
                    <option value="L">L - LULUS</option>
                    <option value="T" selected="selected">T - TOLAK</option>
                    <option value="G">G - LULUS BERSYARAT</option>
                #elseif($beanMaklumatMesyuarat.flagSyor == 'G')
					<option value="">SILA PILIH</option>
                    <option value="L">L - LULUS</option>
                    <option value="T">T - TOLAK</option>
                    <option value="G" selected="selected">G - LULUS BERSYARAT</option>
                #else
					<option value="" selected="selected">SILA PILIH</option>
                    <option value="L">L - LULUS</option>
                    <option value="T">T - TOLAK</option>
                    <option value="G">G - LULUS BERSYARAT</option>
                #end
				</select></td>
              </tr>
              <tr>
                <td valign="top">&nbsp;</td>
                <td valign="top">Catatan</td>
                <td valign="top">:</td>
                <td valign="top"><textarea name="txtCatatan" id="txtCatatan" rows="5" cols="50" $readonlyPopup class="$inputTextClassPopup" >$beanMaklumatMesyuarat.catatan</textarea></td>
              </tr>
              #if($beanMaklumatMesyuarat.flagSyor == 'G')
              <tr>
		        <td colspan="4"><fieldset>
		      	  <legend>MAKLUMBALAS PEMOHON</legend>
		          <table width="100%" border="0" cellspacing="2" cellpadding="2">
		          	<tr>
				      <td width="1%">&nbsp;</td>
				      <td width="28%">Keputusan Pemohon</td>
				      <td width="1%">:</td>
				      <td><select name="socKeputusanPemohon" id="socKeputusanPemohon" style="width:140px;" $readonlyPopup class="$inputTextClassPopup">
				      #if ($beanMaklumatMesyuarat.flagKeputusanPemohon == 'S')
				      	<option>SILA PILIH</option>
				        <option value="S" selected="selected">SETUJU</option>
				        <option value="TS">TIDAK SETUJU</option>
			          #elseif ($beanMaklumatMesyuarat.flagKeputusanPemohon == 'TS')
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
				      <td width="70%"><textarea name="txtUlasanPemohon" id="txtUlasanPemohon" rows="5" cols="50" $readonlyPopup class="$inputTextClassPopup">$beanMaklumatMesyuarat.ulasanPemohon</textarea></td>
	                </tr>
		          </table>
		        </fieldset></td>
	    	  </tr>
	    	  #end
            </table>
            </fieldset></td>
        </tr>
        #if ($modePopup == 'view')
        #set($idDokumen = '')
        #set($namaFail = '')
        #foreach ($beanMinitMesyuarat in $BeanMinitMesyuarat)
        #set($idDokumen = $beanMinitMesyuarat.idDokumen)
        #set($namaFail = $beanMinitMesyuarat.namaFail)
        #end
        <tr>
          <td colspan="4"><fieldset>
            <legend>MINIT MESYUARAT</legend>
            <table width="100%" border="0" cellspacing="2" cellpadding="2">
              <tr>
                <td width="1%">&nbsp;</td>
                <td width="28%">Muat Turun Minit Mesyuarat</td>
                <td width="1%">:</td>
                <td width="70%">#if ($idDokumen != '') <a href="#" onclick="cetakDokumen($idDokumen)" class="style1">$namaFail</a> &nbsp;&nbsp; #end</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td><input id="fileuploadMM" name="fileuploadMM" type="file" size="40" /></td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td><span class="style4"><i><font color="#ff0000">Perhatian</font> : </i><span class="style5">Saiz muat naik fail adalah tidak melebihi 2MB. Jika muat naik anda tidak berjaya sila cuba dengan saiz fail yang lebih kecil.</span></span></td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td><input name="cmdSimpan" type="button" value="Simpan Dokumen" onclick="simpanDokumenMM('$idMesyuarat','$idPermohonan')" /></td>
              </tr>
            </table>
            </fieldset></td>
        </tr>
        #end
 
        #if ($flagNotifikasi == 'openNotifikasi')
		  <tr>
		    <td> #parse("app/php2/frmPLPNotifikasiEmailBaru.jsp") </td>
		  </tr>
		  <tr>
		    <td>&nbsp;</td>
		  </tr>
  		#end
        #if ($modePopup != 'view')
        <tr>
          <td colspan="4" valign="bottom"><i><font color="#ff0000">Perhatian</font>: Pastikan label bertanda <font color="#ff0000">*</font> diisi.</i></td>
        </tr>
        #end
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td> #if ($modePopup == 'new')
            <input name="cmdSimpan" type="button" value="Simpan" onClick="simpanMesyuarat()" />
            <input name="cmdBatal" type="button" value="Batal" onClick="batalMesyuarat()" />
            #end
            #if ($modePopup == 'view')
            #if ($!{session.getAttribute("FLAG_FROM")} == 'failTugasan' || $!{session.getAttribute("FLAG_FROM")} == 'failHQ')
            <input name="cmdKemaskini" type="button" value="Kemaskini" onClick="kemaskiniMesyuarat()" >
            <input name="cmdHapus" type="button" value="Hapus" onClick="hapusMesyuarat()" >
            #end
            <input name="cmdKembali" type="button" value="Kembali" onClick="kembali()" >
            #if($beanMaklumatMesyuarat.flagSyor == 'G')
      		<input name="cmdCetak" type="button" onClick="cetakPLPSuratLulusBersyarat('$idFail')" value="Cetak Surat Lulus Bersyarat">
      		#elseif($beanMaklumatMesyuarat.flagSyor == 'T')
      		<input name="cmdCetak" type="button" onClick="javascript:cetakPLPSuratTolak('$idFail')" value="Cetak Surat Tolak">
      		#end
            #end 
            #if ($modePopup == 'update')
            <input name="cmdSimpanKemaskini" type="button" value="Simpan" onClick="simpanKemaskiniMesyuarat()" />
            <input name="txtBatal" type="button" value="Batal" onClick="batalKemaskiniMesyuarat()" />
            #end </td>
        </tr>
        #end
      </table>
      </fieldset></td>
  </tr>
</table>
#parse("app/php2/frmPLPNotifikasiEmail.jsp")
