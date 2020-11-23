<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td colspan="2"><fieldset>
      <legend>MAKLUMAT PENGARAH</legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        #foreach ($beanMaklumatPengarah in $BeanMaklumatPengarah)
        
        <tr>
          <td width="1%">#if ($mode == 'newPengarah' ||  $mode == 'updatePengarah')<span class="style1">*</span>#end</td>
          <td width="28%">Warganegara</td>
          <td width="1%">:</td>
          <!-- <td width="70%">$selectWarganegara</td> -->
          <td width="70%"><select name="socWarganegara" onchange="getLainlain('LainLainNegara');" class="$inputTextClass" $disabled > 
          		#if ($socWarganegara == "16")
          			<option value="16">NZL - NEW ZEALAND</option>
          		#end
          		#if ($socWarganegara == "27")
          			<option value="27">USA - AMERIKA SYARIKAT</option>
          		#end
          		#if ($socWarganegara == "20")
          			<option value="20">SAU - ARAB SAUDI</option>
          		#end
          		#if ($socWarganegara == "2")
          			<option value="2">AUS - AUSTRALIA</option>
          		#end
          		#if ($socWarganegara == "3")
          			<option value="3">BGL - BANGLADESH</option>
          		#end
          		#if ($socWarganegara == "4")
          			<option value="4">BRU - BRUNEI</option>
          		#end
          		#if ($socWarganegara == "18")
          			<option value="18">PHI - FILIPINA</option>
          		#end
          		#if ($socWarganegara == "7")
          			<option value="7">HK - HONG KONG</option>
          		#end
          		#if ($socWarganegara == "9")
          			<option value="9">IND - INDIA</option>
          		#end
          		#if ($socWarganegara == "8")
          			<option value="8">INA - INDONESIA</option>
          		#end
          		#if ($socWarganegara == "10")
          			<option value="10">ITA - ITALI</option>
          		#end
          		#if ($socWarganegara == "11")
          			<option value="11">JPN - JEPUN</option>
          		#end
          		#if ($socWarganegara == "6")
          			<option value="6">GER - JERMAN BARAT</option>
          		#end
          		#if ($socWarganegara == "12")
          			<option value="12">KOR - KOREA SELATAN</option>
          		#end
          		#if ($socWarganegara == "13")
          			<option value="13">LN - LAIN-LAIN</option>
          		#end
          		#if ($socWarganegara == "14")
          			<option value="14">MAL - MALAYSIA</option>
          		#end
          		#if ($socWarganegara == "15")
          			<option value="15">MAU - MAURITIUS</option>
          		#end
          		#if ($socWarganegara == "16")
          			<option value="16">NZL - NEW ZEALAND</option>
          		#end
          		#if ($socWarganegara == "17")
          			<option value="17">PAK - PAKISTAN</option>
          		#end
          		#if ($socWarganegara == "19")
          			<option value="19">PT - PENDUDUK TETAP</option>
          		#end
          		#if ($socWarganegara == "5")
          			<option value="5">FRA - PERANCHIS</option>
          		#end
          		#if ($socWarganegara == "21")
          			<option value="21">SIN - SINGAPURA</option>
          		#end
          		#if ($socWarganegara == "22")
          			<option value="22">SRI - SRI LANKA</option>
          		#end
          		#if ($socWarganegara == "23")
          			<option value="23">SWI - SWITZERLAND</option>
          		#end
          		#if ($socWarganegara == "25")
          			<option value="19">TPE - TAIWAN</option>
          		#end
          		
          		#if ($socWarganegara == "24")
          			<option value="24">THA - THAILAND</option>
          		#end
          		#if ($socWarganegara == "1")
          			<option value="1">0 - TIDAK BERKENAAN</option>
          		#end
          		#if ($socWarganegara == "26")
          			<option value="26">UK - UNITED KINGDOM</option>
          		#end
          		
          		<option value="">SILA PILIH</option>
				<option value="27">USA - AMERIKA SYARIKAT</option>
				<option value="20">SAU - ARAB SAUDI</option>
				<option value="2">AUS - AUSTRALIA</option>
				<option value="3">BGL - BANGLADESH</option>
				<option value="4">BRU - BRUNEI</option>
				<option value="18">PHI - FILIPINA</option>
				<option value="7">HK - HONG KONG</option>
				<option value="9">IND - INDIA</option>
				<option value="8">INA - INDONESIA</option>
				<option value="10">ITA - ITALI</option>
				<option value="11">JPN - JEPUN</option>
				<option value="6">GER - JERMAN BARAT</option>
				<option value="12">KOR - KOREA SELATAN</option>
				<option value="13">LN - LAIN-LAIN</option>
				<option value="14">MAL - MALAYSIA</option>
				<option value="15">MAU - MAURITIUS</option>
				<option value="16">NZL - NEW ZEALAND</option>
				<option value="17">PAK - PAKISTAN</option>
				<option value="19">PT - PENDUDUK TETAP</option>
				<option value="5">FRA - PERANCHIS</option>
				<option value="21">SIN - SINGAPURA</option>
				<option value="22">SRI - SRI LANKA</option>
				<option value="23">SWI - SWITZERLAND</option>
				<option value="25">TPE - TAIWAN</option>
				<option value="24">THA - THAILAND</option>
				<option value="1">0 - TIDAK BERKENAAN</option>
				<option value="26">UK - UNITED KINGDOM</option> 
        </select></td>
        </tr>
        <tr id ="LainLainNegara">
          <td>#if ($mode == 'newPengarah' ||  $mode == 'updatePengarah')<span class="style1">*</span>#end</td>
          <td>Nama Warganegara</td>
          <td>:</td>
          <td>
          	<input name="txtWarga" type="text" class="$inputTextClass" id="txtWarga" value="$beanMaklumatPengarah.warga" 
          	 $readonly  onBlur="this.value=this.value.toUpperCase();" size="47" maxlength="80" />
          </td>
        </tr>
        
        <tr >
          <td>#if ($mode == 'newPengarah' ||  $mode == 'updatePengarah')<span class="style1">*</span>#end</td>
          <td>Nama</td>
          <td>:</td>
          <td><input name="txtNamaPengarah" type="text" class="$inputTextClass" id="txtNamaPengarah" value="$beanMaklumatPengarah.nama" 
           $readonly  onBlur="this.value=this.value.toUpperCase();" size="47" maxlength="80" />          
          </td>
        </tr>
        <tr>
          <td>#if ($mode == 'newPengarah' ||  $mode == 'updatePengarah')<span class="style1">*</span>#end</td>
          <td>Jenis Pengenalan</td>
          <td>:</td>
         <!-- <td>$selectJenisPengenalan</td>-->
          <td width="70%"><select name="socJenisPengenalan" onchange="getJenisPengenalan('JenisPengenalan');" class="$inputTextClass" $disabled > 
          		#if ($socWarganegara == "1")
          			<option value="1">B - NO.K/P BARU</option>
          		#end
          		#if ($socWarganegara == "3")
          			<option value="3">L - NO.K/P LAMA</option>
          		#end
          		#if ($socWarganegara == "4")
          			<option value="4">P - NO PASPORT</option>
          		#end
          		#if ($socWarganegara == "5")
          			<option value="5">T - NO TENTERA</option>
          		#end
          		#if ($socWarganegara == "6")
          			<option value="6">I - NO POLIS</option>
          		#end
          		#if ($socWarganegara == "11")
          			<option value="11">A - SURAT BERANAK</option>
          		#end
          		
	         	<option value="">SILA PILIH</option>
				<option value="1">B - NO.K/P BARU</option>
				<option value="3">L - NO.K/P LAMA</option>
				<option value="4">P - NO PASPORT</option>
				<option value="5">T - NO TENTERA</option>
				<option value="6">I - NO POLIS</option>
				<option value="11">A - SURAT BERANAK</option>
			</select>
		  </td>
        </tr>
        <tr>
          <td>#if ($mode == 'newPengarah' ||  $mode == 'updatePengarah')<span class="style1">*</span>#end</td>
          <td>No. Pengenalan</td>
          <td>:</td>
          <td><input name="txtNoPengenalan" type="text" class="$inputTextClass" id="txtNoPengenalan" value="$beanMaklumatPengarah.noPengenalan" size="12" maxlength="12" $readonly /></td>
        </tr>
        <tr>  
          <td>#if ($mode == 'newPengarah' ||  $mode == 'updatePengarah')<span class="style1">*</span>#end</td>
          <td>Bangsa</td>
          <td>: </td>
          <!--<td>$selectBangsa</td>-->
           <td width="70%"><select name="socBangsa" onchange="getLainLainBangsa('LainLainBangsa');" class="$inputTextClass" $disabled> 
                #if ($socBangsa == "1")
          			<option value="1">MEL - PERSEORANGAN MELAYU</option>
          		#end
          		#if ($socBangsa == "2")
          			<option value="2">CIN - PERSEORANGAN CINA</option>
          		#end
          		#if ($socBangsa == "3")
          			<option value="3">IND - PERSEORANGAN INDIA</option>
          		#end
          		#if ($socBangsa == "7")
          			<option value="7">LN - LAIN-LAIN</option>
          		#end
          		
           		<option value="">SILA PILIH</option>
				<option value="1">MEL - PERSEORANGAN MELAYU</option>
				<option value="2">CIN - PERSEORANGAN CINA</option>
				<option value="3">IND - PERSEORANGAN INDIA</option>
				<option value="7">LN - LAIN-LAIN</option>
          	</select></td>
        </tr>
       <tr id ="LainLainBangsa">
          <td>#if ($mode == 'newPengarah' ||  $mode == 'updatePengarah')<span class="style1">*</span>#end</td>
          <td>Nama Bangsa</td>
          <td>:</td>
          <td>
          	<input name="txtBangsa" type="text" class="$inputTextClass" id="txtBangsa" value="$beanMaklumatPengarah.bangsa" 
          	 $readonly  onBlur="this.value=this.value.toUpperCase();" size="47" maxlength="80" />
          </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>Pegangan Saham</td>
          <td>:</td>
          <td>RM 
            <input name="txtSaham" type="text" class="$inputTextClass" id="txtSaham" onblur="validateCurrency(this,this.value,'$beanMaklumatPengarah.saham');" value="$beanMaklumatPengarah.saham" size="20" $readonly /></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
        </tr>
        #if ($mode == 'newPengarah' ||  $mode == 'updatePengarah')
        <tr>
          <td colspan="4" valign="bottom"><i><font color="#ff0000">Perhatian</font>: Pastikan label bertanda <font color="#ff0000">*</font> diisi.</i></td>
        </tr>
        #end
        #end
      </table>
      </fieldset></td>
  </tr>
  <tr>
    <td colspan="2">&nbsp;</td>
  </tr>
  <tr>
    <td width="30%">&nbsp;</td>
    <td width="70%"> #if ($mode == 'newPengarah')
      <input type="button" name="cmdSimpanPengarah" id="cmdSimpanPengarah" value="Simpan" onClick="simpanPengarah()"/>
      <input type="button" name="cmdBatalPengarah" id="cmdBatalPengarah" value="Kembali" onClick="batalPengarah()"/>
      #end
      #if ($idStatus == '')
      #if ($mode == 'viewPengarah')
      <input type="button" name="cmdKemaskiniPengarah" id="cmdKemaskiniPengarah" value="Kemaskini" onClick="kemaskiniPengarah()"/>
      <input type="button" name="cmdHapusPengarah" id="cmdHapusPengarah" value="Hapus" onClick="hapusPengarah()"/>
      <input type="button" name="cmdBatalPengarah" id="cmdBatalPengarah" value="Kembali" onClick="batalPengarah()"/>
      #end
      #end
      #if ($mode == 'updatePengarah')
      <input type="button" name="cmdSimpanKemaskiniPengarah" id="cmdSimpanKemaskiniPengarah" value="Simpan" onClick="simpanKemaskiniPengarah()"/>
      <input type="button" name="cmdBatalKemaskiniPengarah" id="cmdSimpanKemaskiniPengarah" value="Kembali" onClick="batalKemaskiniPengarah()"/>
      #end </td>
  </tr>
</table>
