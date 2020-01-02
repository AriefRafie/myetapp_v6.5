<input type="hidden" id="idSenaraiHutang" name="idSenaraiHutang" value="$!hutang.idSenaraiHutang">

#if ($role != "(INTEGRASI)UsersAgensi")
	#set ($disabled = "disabled")
	#else
		#set ($disable = $disabled)
#end

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
                 #if ($role == "(INTEGRASI)UsersAgensi")
                 	<td width="70%"><b>$!hutang.nama</b><input name="nama" disabled  type="hidden" id="nama" onblur="this.value=this.value.toUpperCase()" value="$!hutang.nama" size="35" maxlength="200"/></td>
                 	#else
                 	<td width="70%"><b>$!hutang.nama</b></td>
                 #end
                
              </tr>
              <tr>
                <td width="1%">&nbsp;</td>
                <td width="28%">No. Pengenalan / Pendaftaran</td>
                <td width="1%">:</td>
                #if ($role == "(INTEGRASI)UsersAgensi")
               		<td width="70%"><input name="noPengenalan" $disable class="$disable" type="text" id="noPengenalan" value="$!hutang.noPengenalan" size="35"/></td>
             	#else
             		<td width="70%"><b>$!hutang.noPengenalan</b></td>
             	#end
              </tr>
              <tr>
                <td width="1%"><span class="style1">*</span></td>
                <td width="28%">Alamat</td>
                <td width="1%">:</td>
                #if ($role == "(INTEGRASI)UsersAgensi")
               		<td width="70%"><input name="alamat1" $disable class="$disable" type="text" id="alamat1" onBlur="this.value=this.value.toUpperCase()" value="$!hutang.alamat1" size="35"/></td>
              	#else
              		<td width="70%"><b>$!hutang.alamat1</b></td>
              	#end
              </tr>
              <tr>
                <td width="1%">&nbsp;</td>
                <td width="28%">&nbsp;</td>
                <td width="1%">:</td>
                #if ($role == "(INTEGRASI)UsersAgensi")
               		<td width="70%"><input name="alamat2" $disable class="$disable" type="text" id="alamat2" onBlur="this.value=this.value.toUpperCase()" value="$!hutang.alamat2" size="35"/></td>
              	#else
              		<td width="70%"><b>$!hutang.alamat2</b></td>
              	#end
              </tr>
              <tr>
                <td width="1%">&nbsp;</td>
                <td width="28%">&nbsp;</td>
                <td width="1%">:</td>
                #if ($role == "(INTEGRASI)UsersAgensi")
                	<td width="70%">
                	<input name="alamat3" $disable class="$disable" type="text" id="alamat3" onBlur="this.value=this.value.toUpperCase()" value="$!hutang.alamat3" size="35"/>
                	</td>
              	#else
              		<td width="70%"><b>$!hutang.alamat3</b></td>
              	#end
              </tr>
              <tr>
                <td width="1%"><span class="style1">*</span></td>
                <td width="28%">Poskod</td>
                <td width="1%">:</td>
                #if ($role == "(INTEGRASI)UsersAgensi")
               		<td width="70%"><input name="poskod" type="text" $disable class="$disable" id="poskod" onBlur="this.value=this.value.toUpperCase()" value="$!hutang.poskod" size="5" maxlength="5" onKeyUp="javascript:validateIC(event,this,this.value,'poskod')"/></td>
              	#else
              		<td width="70%"><b>$!hutang.poskod</b></td>
              	#end
              </tr>
              <tr>
                <td width="1%"><span class="style1">*</span></td>
                <td width="28%">Negeri</td>
                <td width="1%">:</td>
                #if ($role == "(INTEGRASI)UsersAgensi")
               		<td width="70%">$!selectNegeri</td>
               	#else
               		<td width="70%"><b>$!selectNegeri</b></td>
               	#end         		
              </tr>
              <tr>
                <td width="1%"><span class="style1">*</span></td>
                <td width="28%">Bandar</td>
                <td width="1%">:</td>
                #if ($role == "(INTEGRASI)UsersAgensi")
                	<td width="70%"><div id="divBandar">#parse("$templateDir/selectBandar.jsp")  </div></td>
                #else
                	<td width="70%"><div id="divBandar">#parse("$templateDir/selectBandar.jsp")  </div></td>
                #end
              </tr>
              <tr>
                <td width="1%"><span class="style1">*</span></td>
                <td width="28%">No. Telefon</td>
                <td width="1%">:</td>
                #if ($role == "(INTEGRASI)UsersAgensi")
                	<td width="70%">
                	<input name="noTelefon" $disable class="$disable" type="text" id="noTelefon"  value="$!hutang.noTelefon" size="35"/>
                	</td>
                #else
                	<td width="70%"><b>$!hutang.noTelefon</b></td>
                #end
              </tr>
              
            </table></td>
          <td width="50%" valign="top"><table width="100%" border="0" cellspacing="2" cellpadding="2">
              <tr>
                <td width="1%"><span class="style1">*</span></td>
                <td width="28%">Jenis Hutang</td>
                <td width="1%">:</td>
                #if ($role == "(INTEGRASI)UsersAgensi")
               		<td width="70%"><input name="jenisHutang" $disable class="$disable" type="text" id="jenisHutang" value="$!hutang.jenisHutang" size="35" onblur="this.value=this.value.toUpperCase()"/></td>
             	#else
             		<td width="70%"><b>$!hutang.jenisHutang</b></td>
             	#end
              </tr>
              <tr>
                <td width="1%"><span class="style1">*</span></td>
                <td width="28%">No. Akaun</td>
                <td width="1%">:</td>
                #if ($role == "(INTEGRASI)UsersAgensi")
                	<td width="70%"><input name="noAkaun" $disable class="$disable" type="text" id="noAkaun" value="$!hutang.noAkaun" size="35" onblur="this.value=this.value.toUpperCase()"/></td>
              	#else
              		<td width="70%"><b>$!hutang.noAkaun</b></td>
              	#end
              </tr>
              <tr>
                <td width="1%" valign="top"><span class="style1">*</span></td>
                <td width="28%" valign="top">Tarikh Perjanjian</td>
                <td width="1%" valign="top">:</td>
                #if ($role == "(INTEGRASI)UsersAgensi")
                	               		<td width="70%"><input type="text" $disable class="$disable" name="tarikhPerjanjian" id="tarikhPerjanjian" value="$!hutang.tarikhPerjanjian" onblur="check_date(this);" size="9"/>
                    <a href="javascript:displayDatePicker('tarikhPerjanjian',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a></td>
                #else
                	<td width="70%"><b>$!hutang.tarikhPerjanjian</b></td>
              	#end
              </tr>
              <tr>
                <td width="1%" valign="top">&nbsp;</td>
                <td width="28%" valign="top">Butiran Hutang</td>
                <td width="1%" valign="top">:</td>
                #if ($role == "(INTEGRASI)UsersAgensi")
                	<td width="70%" valign="top"><textarea $disable class="$disable" name="butiranHutang" cols="35" rows="2" id="butiranHutang" onBlur="this.value=this.value.toUpperCase()" >$!hutang.butiranHutang</textarea></td>
              	#else
              		<td width="70%" valign="top"><textarea $disabled name="butiranHutang" cols="35" rows="2" id="butiranHutang" onBlur="this.value=this.value.toUpperCase()" >$!hutang.butiranHutang</textarea></td>
              	#end
              </tr>
              <tr>
                <td width="1%"><span class="style1">*</span></td>
                <td width="28%">Nilai Hutang (RM)</td>
                <td width="1%">:</td>
                #if ($role == "(INTEGRASI)UsersAgensi")
                	<td width="70%"><input type="text" $disable class="$disable" name="nilaiHutang" id="nilaiHutang" value="$!hutang.nilaiHutang" onblur="validateCurrency(this,this.value,'$!hutang.nilaiHutang');" style="text-align:right"/></td>
              	#else
              		<td width="70%"><b>$!hutang.nilaiHutang</b></td>
              	#end
              </tr>
              <tr>
                <td width="1%"><span class="style1">*</span></td>
                <td width="28%">Baki Hutang (RM)</td>
                <td width="1%">:</td>
                #if ($role == "(INTEGRASI)UsersAgensi") 
                	<td width="70%"><input type="text" $disable class="$disable" name="bakiHutang" id="bakiHutang" value="$!hutang.bakiHutang" onblur="validateCurrency(this,this.value,'$!hutang.bakiHutang');" style="text-align:right"/></td>
                #else
                	<td width="70%"><b>$!hutang.bakiHutang</b></td>
                #end
              </tr>
              <tr>
                <td width="1%">&nbsp;</td>
                <td width="28%">Tarikh Tamat Tempoh Hutang</td>
                <td width="1%">:</td>
                #if ($role == "(INTEGRASI)UsersAgensi") 
               		<td width="70%"><input type="text" $disable class="$disable" name="tarikhTamatTempohHutang" id="tarikhTamatTempohHutang" value="$!hutang.tarikhTamatTempohHutang" onblur="check_date(this);" size="9"/>
                    <a href="javascript:displayDatePicker('tarikhTamatTempohHutang',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a></td>
                #else
                	<td width="70%"><b>$!hutang.tarikhTamatTempohHutang</b></td>
                #end
              </tr>
              <!-- ADD BY PEJE -->
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
               		<td width="70%"><input type="text" $disable class="$disable" name="tarikhSelesaiHutang" id="tarikhSelesaiHutang" value="$!hutang.tarikhSelesaiHutang" onblur="check_date(this);" size="9"/>
                    <a href="javascript:displayDatePicker('tarikhSelesaiHutang',false,'dmy');"><img border="0" src="../img/calendar.gif"/></a></td>
                #else
                	<td width="70%"><b>$!hutang.tarikhSelesaiHutang</b></td>
                #end
              </tr>
              #end
              
              <tr>
                <td width="1%"><span class="style1">*</span></td>
                <td width="28%">Perlindungan Insurans</td>
                <td width="1%">:</td>
                #if ($role == "(INTEGRASI)UsersAgensi")
               		<td width="70%">#parse("app/ppk/eHutang/selectInsuransHutang.jsp")</td>
               	#else
               		<td width="70%">
                    #if ($!hutang.insuransHutang == 'Y')
                    <b>YA</b>
                    #elseif ($!hutang.insuransHutang == 'T')
                    <b>TIDAK</b>
                    #end
                    </td>
               	#end                     		
              </tr>
              #if ($!hutang.insuransHutang == 'Y')
              <tr>
                <td width="1%">&nbsp;</td>
                <td width="28%">Catatan (Insurans)</td>
                <td width="1%">:</td>
                #if ($role == "(INTEGRASI)UsersAgensi") 
               		<td width="70%"><textarea $disable class="$disable" name="catatanInsuransHutang" cols="35" rows="2" id="catatanInsuransHutang" onBlur="this.value=this.value.toUpperCase()" >$!hutang.catatanInsuransHutang</textarea>
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
                <td width="70%" valign="top"><textarea name="catatan"  $disable class="$disable" cols="35" rows="2" id="catatan" onBlur="this.value=this.value.toUpperCase()" >$!hutang.catatan</textarea></td>
              </tr>
              <!-- 
              <tr>
                <td width="1%" valign="top">&nbsp;</td>
                <td width="28%" valign="top"></td>
                <td width="1%" valign="top"></td>
                <td width="70%" valign="top"><input type="checkbox" name="selesai" id="selesai" onClick=""/> Hutang telah diselesaikan
              	</td>
              </tr>
               -->
              <tr>
                <td width="1%">&nbsp;</td>
                <td width="28%">Tarikh Kemaskini</td>
                <td width="1%">:</td>
                <td width="70%"><b>$!hutang.tarikhKemaskini</b></td>
              </tr>
            </table></td>
        </tr>
         
        <tr>
        
          <td colspan="2" align="center">
          
           #if(($!role != "adminppk") && ($disable == "disabled"))
            <input type="button" name="cmdSimpan" id="cmdSimpan" value="Kemaskini" onclick="kemaskiniHutang()"/>
            <input type="button" name="cmdHapus" id="cmdHapus" value="Hapus" onclick="hapusHutang('$!hutang.idSenaraiHutang')"/>
            
            #if ($!cmdSimpan == "simpan")
            #set ($id2=$!hutang.idHutang+"5")
            <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="doDivAjaxCall$formname('divMainForm','paparPenghutang','id='+ $!id2);"/></td>            
            #else
             <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="doDivAjaxCall$formname('divMainForm','paparPenghutang','id='+ $!id2);"/></td>
            #end
           #end
           #if(($!role != "adminppk") && ($disable != "disabled"))
           		<input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="simpanKemaskiniHutang()"/>
           		<input type="button" name="cmdKembali" id="cmdKembali" value="Batal" onclick="doDivAjaxCall$formname('divMainForm','paparPenghutang','id='+ $!id2);"/></td>
          #end  
           #if ($role != "(INTEGRASI)UsersAgensi")
             #if ($!hutang.flag_salin == "0")
            <!--<input type="button" name="cmdSimpan" id="cmdSimpan" value="Salin" onclick="doDivAjaxCall$formname('divMainForm','SimpanDalamPenghutang','id_SenaraiHutang='+ $!hutang.idSenaraiHutang);"/>
             <input type="button" name="cmdSimpan2" id="cmdSimpan2" value="Salin Semua" onclick="doDivAjaxCall$formname('divMainForm','SimpanDalamPenghutangSemua','id_SenaraiHutang='+ $!hutang.idSenaraiHutang);"/> -->
            #end
            <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="doDivAjaxCall$formname('divMainForm','paparPenghutang','id='+ $!id2);"/></td>
      		 #end
        </tr>
        
      </table>
      </fieldset></td>
  </tr>
</table>

<script type="text/javascript">

</script>
