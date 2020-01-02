<table width="100%" border="0" cellspacing="2" cellpadding="2">

  <tr>
    <td><fieldset>
      <legend><strong>MAKLUMAT HUTANG</strong></legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="50%" valign="top"><table width="100%" border="0" cellspacing="2" cellpadding="2">
              <tr>
                <td width="1%">&nbsp;</td>
                <td width="28%">MyID Baru</td>
                <td width="1%">:</td>
                <td width="70%"><b>$!penghutang.noPengenalanBaru</b></td>
              </tr>
              <tr>
                <td width="1%">&nbsp;</td>
                <td width="28%">Nama</td>
                <td width="1%">:</td>
                <td width="70%"><b>$!penghutang.nama</b></td>
              </tr>
              <tr>
                <td width="1%"><span class="style1">*</span></td>
                <td width="28%">Nama / Agensi Pemiutang</td>
                <td width="1%">:</td>
                <td width="70%"><b>$!namaPejabat.namaAgensi</b><input name="nama" type="hidden" id="nama" disabled onblur="this.value=this.value.toUpperCase()" value="$!namaPejabat.namaAgensi" size="35" maxlength="200"/></td>
              </tr>
              <tr>
                <td width="1%">&nbsp;</td>
                <td width="28%">No. Pengenalan / Pendaftaran</td>
                <td width="1%">:</td>
                <td width="70%"><input name="noPengenalan" type="text" id="noPengenalan" value="$!noPengenalan" size="35" onblur="this.value=this.value.toUpperCase()"/></td>
              </tr>
              <tr>
                <td width="1%"><span class="style1">*</span></td>
                <td width="28%">Alamat</td>
                <td width="1%">:</td>
                <td width="70%">
                #if ($alamat1 == "")
                <input name="alamat1" type="text" id="alamat1" onBlur="this.value=this.value.toUpperCase()" value="$!hutang.alamat1" size="35"/></td>
              	#else
              	<input name="alamat1" type="text" id="alamat1" onBlur="this.value=this.value.toUpperCase()" value="$!alamat1" size="35"/></td>
              	#end
              </tr>
              <tr>
                <td width="1%">&nbsp;</td>
                <td width="28%">&nbsp;</td>
                <td width="1%">:</td>
                <td width="70%">
                #if ($alamat2 == "")
                <input name="alamat2" type="text" id="alamat2" onBlur="this.value=this.value.toUpperCase()" value="$!hutang.alamat2" size="35"/></td>
                #else
				<input name="alamat2" type="text" id="alamat2" onBlur="this.value=this.value.toUpperCase()" value="$!alamat2" size="35"/></td>              	
				#end
              </tr>
              <tr>
                <td width="1%">&nbsp;</td>
                <td width="28%">&nbsp;</td>
                <td width="1%">:</td>
                <td width="70%">
                #if ($alamat3 == "")
                <input name="alamat3" type="text" id="alamat3" onBlur="this.value=this.value.toUpperCase()" value="$!hutang.alamat3" size="35"/></td>
                #else
				<input name="alamat3" type="text" id="alamat3" onBlur="this.value=this.value.toUpperCase()" value="$!alamat3" size="35"/></td>              	
				#end
              </tr>
              <tr>
                <td width="1%"><span class="style1">*</span></td>
                <td width="28%">Poskod</td>
                <td width="1%">:</td>
                <td width="70%">
                #if ($poskod == "")
                <input name="poskod" type="text" id="poskod" onBlur="this.value=this.value.toUpperCase()" value="$!hutang.poskod" size="5" maxlength="5"/></td>
                #else
				<input name="poskod" type="text" id="poskod" onBlur="this.value=this.value.toUpperCase()" value="$!poskod" size="5" maxlength="5"/></td>              	
				#end
              </tr>
              <tr>
                <td width="1%"><span class="style1">*</span></td>
                <td width="28%">Negeri</td>
                <td width="1%">:</td>
                <td width="70%">$!selectNegeri</td>
              </tr>
              <tr>
                <td width="1%"><span class="style1">*</span></td>
                <td width="28%">Bandar</td>
                <td width="1%">:</td>
                <td width="70%"><div id="divBandar">#parse("$templateDir/selectBandar.jsp")</div></td>
              </tr>
              <tr>
                <td width="1%"><span class="style1">*</span></td>
                <td width="28%">No. Telefon</td>
                <td width="1%">:</td>
                <td width="70%">
                #if ($noTelefon == "")
                <input name="noTelefon" type="text" id="noTelefon" value="$!hutang.noTelefon" size="35" /></td>
                #else
                <input name="noTelefon" type="text" id="noTelefon" value="$!noTelefon" size="35" /></td>
                #end
                
              </tr>
            </table></td>
          <td width="50%" valign="top"><table width="100%" border="0" cellspacing="2" cellpadding="2">
              <tr>
                <td width="1%"><span class="style1">*</span></td>
                <td width="28%">Jenis Hutang</td>
                <td width="1%">:</td>
                <td width="70%">
                #if ($jenisHutang == "")
                <input name="jenisHutang" type="text" id="jenisHutang" value="$!hutang.jenisHutang" size="35" onblur="this.value=this.value.toUpperCase()"/></td>
                #else
                <input name="jenisHutang" type="text" id="jenisHutang" value="$!jenisHutang" size="35" onblur="this.value=this.value.toUpperCase()"/></td>
                #end
              </tr>
              <tr>
                <td width="1%"><span class="style1">*</span></td>
                <td width="28%">No. Akaun</td>
                <td width="1%">:</td>
                <td width="70%">
                #if ($noAkaun == "")
                <input name="noAkaun" type="text" id="noAkaun" value="$!hutang.noAkaun" size="35" onblur="this.value=this.value.toUpperCase()"/></td>
                #else
                <input name="noAkaun" type="text" id="noAkaun" value="$!noAkaun" size="35" onblur="this.value=this.value.toUpperCase()"/></td>
                #end
              </tr>
              <tr>
                <td width="1%" valign="top"><span class="style1">*</span></td>
                <td width="28%" valign="top">Tarikh Perjanjian</td>
                <td width="1%" valign="top">:</td>
                <td width="70%"><input type="text" name="tarikhPerjanjian" id="tarikhPerjanjian" value="$!tarikhPerjanjian" onblur="check_date(this);" size="9"/>
                <a href="javascript:displayDatePicker('tarikhPerjanjian',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a></td>
                
              </tr>
              <tr>
                <td width="1%" valign="top">&nbsp;</td>
                <td width="28%" valign="top">Butiran Hutang</td>
                <td width="1%" valign="top">:</td>
                <td width="70%" valign="top"><textarea name="butiranHutang"  cols="35" rows="2" id="butiranHutang" onBlur="this.value=this.value.toUpperCase()" >$!butiranHutang</textarea></td>
              </tr>
              <tr>
                <td width="1%"><span class="style1">*</span></td>
                <td width="28%">Nilai Hutang (RM)</td>
                <td width="1%">:</td>
                <td width="70%"><input type="text" name="nilaiHutang" id="nilaiHutang" value="$!nilaiHutang" onblur="validateCurrency(this,this.value,'$!hutang.nilaiHutang');" style="text-align:right"/></td>
              </tr>
              <tr>
                <td width="1%"><span class="style1">*</span></td>
                <td width="28%">Baki Hutang (RM)</td>
                <td width="1%">:</td>
                <td width="70%"><input type="text" name="bakiHutang" id="bakiHutang" value="$!bakiHutang" onblur="validateCurrency(this,this.value,'$!hutang.bakiHutang');" style="text-align:right"/></td>
              </tr>
              <tr>
                <td width="1%">&nbsp;</td>
                <td width="28%">Tarikh Tamat Tempoh Hutang</td>
                <td width="1%">:</td>
                <td width="70%"><input type="text" name="tarikhTamatTempohHutang" id="tarikhTamatTempohHutang" value="$!tarikhTamatTempohHutang" onblur="check_date(this);" size="9"/>
                  <a href="javascript:displayDatePicker('tarikhTamatTempohHutang',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a></td>
              </tr>
              <!-- ADD BY PEJE -->
              <!-- 
              <tr>
                <td width="1%"><span class="style1">*</span></td>
                <td width="28%">Status Hutang</td>
                <td width="1%">:</td>
                #if ($role == "(INTEGRASI)UsersAgensi")
               		<td width="70%">#parse("app/ppk/eHutang/selectStatusHutang.jsp")</td>
               	#else
               		<td width="70%">
                    #if ($!hutang.statusHutang == 'Y')
                    <b>SELESAI</b>
                    #elseif ($!hutang.statusHutang == 'T')
                    <b>BELUM SELESAI</b>
                    #end
                    </td>
               	#end                     		
              </tr>
              #if ($!hutang.statusHutang == 'Y')
              <tr>
                <td width="1%">&nbsp;</td>
                <td width="28%">Tarikh Selesai Hutang</td>
                <td width="1%">:</td>
                #if ($role == "(INTEGRASI)UsersAgensi") 
               		<td width="70%"><input type="text" $disable class="$disable" name="tarikhSelesaiHutang" id="tarikhSelesaiHutang" value="$!tarikhSelesaiHutang" onblur="check_date(this);" size="9"/>
                    <a href="javascript:displayDatePicker('tarikhSelesaiHutang',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a></td>
                #else
                	<td width="70%"><b>$!hutang.tarikhSelesaiHutang</b></td>
                #end
              </tr>
              #end
               -->
              <tr>
                <td width="1%"><span class="style1">*</span></td>
                <td width="28%">Perlindungan Insurans</td>
                <td width="1%">:</td>
                
                #if (($role == "(INTEGRASI)UsersAgensi") && ($!insuransHutang == ''))
               		<td width="70%">#parse("app/ppk/eHutang/selectInsuransHutang2.jsp")</td>
               	#end
               	#if ($!insuransHutang != '')
               		<td width="70%">
                    <select id="insuransHutang" name="insuransHutang" style="width:25%" onChange="doDivAjaxCall3$formname('divMainForm','doChangeInsuransHutang2','')" $!disabled>
                    <option value="">SILA PILIH</option>
                    #if ($!insuransHutang == 'Y')
   						 <option selected value="Y">YA</option>
   						 <option value="T">TIDAK</option>
                    #elseif ($!insuransHutang == 'T')
   						 <option value="Y">YA</option>
   						 <option selected value="T">TIDAK</option>
					
                    #end
                    </select>
                    </td>
               	#end                     		
              </tr>
              #if ($!insuransHutang == 'Y')
              <tr>
                <td width="1%">&nbsp;</td>
                <td width="28%">Catatan (Insurans)</td>
                <td width="1%">:</td>
                #if ($role == "(INTEGRASI)UsersAgensi") 
               		<td width="70%"><textarea  name="catatanInsuransHutang" cols="35" rows="2" id="catatanInsuransHutang" onBlur="this.value=this.value.toUpperCase()" >$!catatanInsuransHutang</textarea>
                    </td>
                #else
                	<td width="70%"><b>$!hutang.catatanInsuransHutang</b></td>
                #end
              </tr>
              #end
              <tr>
                <td width="1%" valign="top">&nbsp;</td>
                <td width="28%" valign="top">Catatan</td>
                <td width="1%" valign="top">:</td>
                <td width="70%" valign="top"><textarea name="catatan"  cols="35" rows="2" id="catatan" onBlur="this.value=this.value.toUpperCase()" >$!catatan</textarea></td>
              </tr>
            </table></td>
        </tr>
        <tr>
          <td colspan="2" align="center"><input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="simpanHutang()"/>
            <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="doDivAjaxCall$formname('divMainForm','paparPenghutang','id='+ $!id2);"/></td>
        </tr>
      </table>
      </fieldset></td>
  </tr>
</table>
