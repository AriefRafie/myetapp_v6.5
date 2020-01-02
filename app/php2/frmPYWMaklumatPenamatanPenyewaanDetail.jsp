<table width="100%" border="0" cellspacing="2" cellpadding="2">
#foreach($beanMaklumatPenamatanPenyewaan in $BeanMaklumatPenamatanPenyewaan)
  <tr>
    <td><fieldset>
      <legend>MAKLUMAT PENAMATAN PENYEWAAN</legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="1%"></td>
          <td width="28%">Sebab Tamat</td>
          <td width="1%">:</td>
          <td width="70%">
          	<select name="socSebabTamat" id="socSebabTamat" style="width:200px;"  $readonly class="$disabled" $disabled>
          		    #if($beanMaklumatPenamatanPenyewaan.socSebabTamat == '1')
	          		    <option value="">SILA PILIH</option>
	                    <option value="01" selected="selected">Penyewa melanggar syarat penyewaan</option>
	                    <option value="02">KJP memerlukan tapak</option>
	                    <option value="03">Tanah pelepasan (Tanah bukan milik KJP)</option>
	                    <option value="04">Persetujuan bersama</option>
	                    <option value="05">Tukarguna</option>
	                    <option value="06">Lain-lain keadaan</option>
          		    #elseif ($beanMaklumatPenamatanPenyewaan.socSebabTamat == '2')
          		    	<option value="">SILA PILIH</option>
	                    <option value="01">Penyewa melanggar syarat penyewaan</option>
	                    <option value="02" selected="selected">KJP memerlukan tapak</option>
	                    <option value="03">Tanah pelepasan (Tanah bukan milik KJP)</option>
	                    <option value="04">Persetujuan bersama</option>
	                    <option value="05">Tukarguna</option>
	                    <option value="06">Lain-lain keadaan</option>
          		    #elseif ($beanMaklumatPenamatanPenyewaan.socSebabTamat == '3')
	          		    <option value="">SILA PILIH</option>
	                    <option value="01">Penyewa melanggar syarat penyewaan</option>
	                    <option value="02">KJP memerlukan tapak</option>
	                    <option value="03" selected="selected">Tanah pelepasan (Tanah bukan milik KJP)</option>
	                    <option value="04">Persetujuan bersama</option>
	                    <option value="05">Tukarguna</option>
	                    <option value="06">Lain-lain keadaan</option>          		    
          		    #elseif ($beanMaklumatPenamatanPenyewaan.socSebabTamat == '4')
	          		    <option value="">SILA PILIH</option>
	                    <option value="01">Penyewa melanggar syarat penyewaan</option>
	                    <option value="02">KJP memerlukan tapak</option>
	                    <option value="03">Tanah pelepasan (Tanah bukan milik KJP)</option>
	                    <option value="04" selected="selected">Persetujuan bersama</option>
	                    <option value="05">Tukarguna</option>
	                    <option value="06">Lain-lain keadaan</option>          		    
          		    #elseif ($beanMaklumatPenamatanPenyewaan.socSebabTamat == '5')
	          		    <option value="">SILA PILIH</option>
	                    <option value="01">Penyewa melanggar syarat penyewaan</option>
	                    <option value="02">KJP memerlukan tapak</option>
	                    <option value="03">Tanah pelepasan (Tanah bukan milik KJP)</option>
	                    <option value="04">Persetujuan bersama</option>
	                    <option value="05" selected="selected">Tukarguna</option>
	                    <option value="06">Lain-lain keadaan</option>          		    
          		    #elseif ($beanMaklumatPenamatanPenyewaan.socSebabTamat == '6')
	          		    <option value="">SILA PILIH</option>
	                    <option value="01">Penyewa melanggar syarat penyewaan</option>
	                    <option value="02">KJP memerlukan tapak</option>
	                    <option value="03">Tanah pelepasan (Tanah bukan milik KJP)</option>
	                    <option value="04">Persetujuan bersama</option>
	                    <option value="05">Tukarguna</option>
	                    <option value="06" selected="selected">Lain-lain keadaan</option>          		    
          		    #end

          	</select>						
          </td>
        </tr>
        #if ($flagSebabTamat != 'Y')
        <tr>
          <td width="1%"></td>
          <td width="28%">Tarikh Surat Tamat</td>
          <td width="1%">:</td>
          <td width="70%">
          	<input type="text" name="txtTarikhSuratTamat" id="txtTarikhSuratTamat" value="$beanMaklumatPenamatanPenyewaan.tarikhSuratTamat" size="9" onBlur="check_date(this)"  $readonly class="$inputTextClass" />
            <a href="javascript:displayDatePicker('txtTarikhSuratTamat',false,'dmy');"><img border="0" src="../img/calendar.gif"/>
          </td>
        </tr>   
        #end         
        <tr>
          <td width="1%"></td>
          <td width="28%">Catatan</td>
          <td width="1%">:</td>
          <td width="70%">
          	<input name="txtCatatanTamat" id="txtCatatanTamat" value="$beanMaklumatPenamatanPenyewaan.catatanTamat" type="text" size="30" maxlength="50" $readonly class="$inputTextClass" />
          </td>
        </tr> 
        #if ($flagSebabTamat != 'Y')
        <tr>  
	        <td width="1%"></td>
	        <td width="28%"></td>
        	<td width="1%"></td>
	          <td width="78%">
	          		#if ($mode == 'view')
	          			  	<input type="button" name="cmdKemaskini" value="Kemaskini" onClick="goKemaskini()"/>
	    					<input type="button" name="cmdSeterusnya" value="Seterusnya" onClick="doSeterusnya()"/>          		
	          		#end
	          		#if ($mode == 'update')
	          			  	<input type="button" name="cmdSimpanTamatSewa" value="Simpan" onClick="doSimpanTamatSewa()"/>
	    					<input type="button" name="cmdBatalTamatSewa" value="Batal" onClick="goBatal()"/>
	          		#end
			   </td>
        </tr>
		#end
      </table>
      </fieldset></td>
  </tr>
  #end
</table>