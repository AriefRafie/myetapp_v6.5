<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td colspan="2"><fieldset>
        <legend>MAKLUMAT ULASAN</legend>
        <table width="100%" border="0" cellspacing="2" cellpadding="2">
          #parse("$templateDir/status.jsp")
          <tr>
            <td width="1%">&nbsp;</td>
            <td width="28%">Tarikh Ulasan</td>
            <td width="1%">:</td>
            <td width="70%"><strong>$!maklumatUlasan.tarikhHantar</strong></td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td>Jangkamasa</td>
            <td>:</td>
            <td><strong>$!maklumatUlasan.jangkamasa Hari</strong></td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td>Tarikh Akhir Maklumbalas</td>
            <td>:</td>
            <td><strong>$!maklumatUlasan.tarikhJangkaTerima</strong></td>
          </tr>
		  <tr>
		  	<td>&nbsp;</td>
            <td valign="top">Lampiran</td>
            <td valign="top">:</td>
			<td>
			  <table align="center" width="100%">
				<tr class="table_header">
				  <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
				  <td><strong>Nama Lampiran</strong></td>
				  <td><strong>Content</strong></td>
				  <td><strong>Catatan</strong></td>
				</tr>
				#set ($MaklumatLampiran = "")
		        #if ($maklumatLampiran.size() > 0)
		        #foreach ($MaklumatLampiran in $maklumatLampiran)
		        #if ($MaklumatLampiran.bil == '')
		        #set( $row = "row1" )
		        #elseif (($MaklumatLampiran.bil % 2) != 0)
		        #set( $row = "row1" )
		        #else 
		        #set( $row = "row2" )
		        #end
				<tr>
				  <td class="$row" align="center">$!MaklumatLampiran.bil</td>
				  <td class="$row">$!MaklumatLampiran.namaDokumen</td>
				  <td class="$row"><a href="javascript:cetakLampiran($MaklumatLampiran.idDokumen)" class="style2"><b>$!MaklumatLampiran.namaFail</b></a></td>
				  <td class="$row">$!MaklumatLampiran.catatan</td>
				</tr>
				#end
				#else
				<tr>
				  <td class="row1" align="center">&nbsp;</td>
				  <td class="row1">Tiada Rekod</td>
				</tr>
				#end
			  </table>
			</td>
		  </tr>
          <tr>
            <td colspan="4"><fieldset>
                <legend>MAKLUMBALAS</legend>
                <table width="100%" border="0" cellspacing="2" cellpadding="2">
                #if($!maklumatUlasan.flagStatus == '2')
                  <tr>
            <td width="1%">&nbsp;</td>
            <td width="28%">Tarikh Maklumbalas</td>
            <td width="1%">:</td>
            <td width="70%"><strong>$!maklumatUlasan.tarikhTerima</strong></td>
          </tr>#end
                  <tr>
                    <td width="1%"><font color="#ff0000">*</font></td>
                    <td width="28%">Tarikh Surat</td>
                    <td width="1%">:</td>
                    <td width="70%"><input name="txtTarikhSurat" type="text" id="txtTarikhSurat" onBlur="check_date(this)" value="$!maklumatUlasan.tarikhSurat" size="9" maxlength="10">
                      <a href="javascript:displayDatePicker('txtTarikhSurat',false,'dmy');"><img border="0" src="../img/calendar.gif"/></td>
                  </tr>
                  <tr>
                    <td><font color="#ff0000">*</font></td>
                    <td>No Rujukan Surat</td>
                    <td>:</td>
                    <td><input type="text" name="txtNoRujukanSurat" id="txtNoRujukanSurat" size="50" value="$!maklumatUlasan.noRujukan" onblur="this.value=this.value.toUpperCase();"/></td>
                  </tr>
                  <tr>
                    <td valign="top">&nbsp;</td>
                    <td valign="top">Ulasan</td>
                    <td valign="top">:</td>
                    <td valign="top"><textarea name="txtUlasan" id="txtUlasan" rows="5" cols="50">$!maklumatUlasan.ulasan</textarea></td>
                  </tr>
                  <tr>
                    <td><font color="#ff0000">*</font></td>
                    <td>Keputusan</td>
                    <td>:</td>
                    <td><select name="txtKeputusan" id="txtKeputusan" style="width:140px;">
                    #if ($!maklumatUlasan.flagKeputusan == 'L')
                        <option>SILA PILIH</option>
                        <option value="L" selected="selected">LULUS</option>
                        <option value="T">TOLAK</option>
                        <option value="G">TANGGUH</option>
                   #elseif ($!maklumatUlasan.flagKeputusan == 'T')
                        <option>SILA PILIH</option>
                        <option value="L">LULUS</option>
                        <option value="T" selected="selected">TOLAK</option>
                        <option value="G">TANGGUH</option>
                   #elseif ($!maklumatUlasan.flagKeputusan == 'G')
                        <option>SILA PILIH</option>
                        <option value="L">LULUS</option>
                        <option value="T">TOLAK</option>
                        <option value="G" selected="selected">TANGGUH</option>
                   #else
                        <option selected="selected">SILA PILIH</option>
                        <option value="L">LULUS</option>
                        <option value="T">TOLAK</option>
                        <option value="G">TANGGUH</option>
                   #end
                      </select></td>
                  </tr>
                  <tr>
                    <td>&nbsp;</td>
                    <td>Nama Pengulas</td>
                    <td>:</td>
                    <td><input type="text" name="txtNamaPengulas" id="txtNamaPengulas" size="50" value="$!maklumatUlasan.namaPengulas" onblur="this.value=this.value.toUpperCase();"/></td>
                  </tr>
                  <tr>
                    <td>&nbsp;</td>
                    <td>No. Telefon Pengulas</td>
                    <td>:</td>
                    <td><input type="text" name="txtNoTelPengulas" id="txtNoTelPengulas" size="50" value="$!maklumatUlasan.noTelPengulas" onblur="this.value=this.value.toUpperCase();"/></td>
                  </tr>
                  #if ($!maklumatUlasan.tarikhSurat != "")
                  <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                  </tr>
                  <tr>
                    <td colspan="4">#parse("$templateDir/muatNaikDokumenSokongan.jsp")</td>
                  </tr>
                  #end
                </table>
              </fieldset></td>
          </tr>
          <tr>
            <td colspan="2" valign="bottom"><i><font color="#ff0000">Perhatian</font> : Pastikan label bertanda <font color="#ff0000">*</font> diisi.</i></td>
          </tr>
        </table>
      </fieldset></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td width="30%">&nbsp;</td>
    <td width="70%">
    	<input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onClick="simpanUlasan()">
    	#if ( $lampiran )
      	<input type="button" name="cmdHantar" id="cmdHantar" value="Hantar" onClick="hantarUlasan()">
      	#end
      	<input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="doDivAjaxCall$formname('divMainForm','','');"/></td>
    <td width="1%"></td>
  </tr>
</table>
