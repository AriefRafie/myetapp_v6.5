 #set($style = "disabled=disabled")
#set($hide = 'style="display:none"')
 <fieldset>
<legend><strong>MAKLUMAT PENJUAL</strong></legend>
<table width="100%" border="0">
	<!-- <tr>
                      <td></td>
                      <td></td>
                      <td></td>
                      <td colspan="3">
                     	<select name="PenjualSama" $style >
                     	<option value="">--Pilih Pemilik Sekiranya Pemilik Adalah Penjual--</option>
                     	#foreach($pemilik in $pemiliks)
                     		<option value="$!pemilik.idpihakberkepentingan">$!pemilik.nama</option>
                     	#end
                     	
                     	</select>
                     	<input type="button" class="stylobutton" name="cmdSimpan" id="cmdSimpan" value="Proses.." onclick="penjualPembeli()" $enable $hide>
                      ##<input name="PenjualSama" type="checkbox" id="PenjualSama" onclick="javascript:penjualPembeli()" #if($!pemohon.flagPemilik == "Y") checked #end $enable />
						##Penjual Adalah Sama Dengan Pemilik </td>
 	</tr>
 -->
	<tr>
	     <td><div align="right"></div></td>
	     <td><div align="left">Nama Penjual</div></td>
	     <td>:</td>
	     <td><!--$!pemohon.nama -->
	     	 <input type="text" name="txtNamaPenjual" onkeyup="this.value=this.value.toUpperCase();" size="50" maxlength="80" value="$!pemohon.nama" $mode $classDis $style/> 
	     </td>
	</tr> 
	 <tr>
	      <td><div align="right"><strong></strong></div></td>
	      <td><div align="left">No. IC</div></td>
	      <td>:</td>
	      <td><!-- $!pemohon.noPemohon -->
	      	 <input type="text" name="txtKodPenjual" onkeyup="this.value=this.value.toUpperCase();" size="30" maxlength="50" value="$!pemohon.noPemohon" $mode $classDis $style/> 
	     	</td>
	</tr>

<tr>
                                    <td><div align="right"></div></td>
                                    <td><div align="left">Alamat</div></td>
                                    <td>:</td>
                                    <td><input type="text" name="txtAlamat1" onkeyup="this.value=this.value.toUpperCase();" size="50" maxlength="60" value="$!pemohon.alamat1" $mode $classDis $style/></td>
                                  </tr>
                                  <tr>
                                    <td><div align="right"><strong></strong></div></td>
                                    <td><div align="left"></div></td>
                                    <td>&nbsp;</td>
                                    <td><input type="text" name="txtAlamat2" onkeyup="this.value=this.value.toUpperCase();" size="50" maxlength="60" value="$!pemohon.alamat2" $mode $classDis $style/></td>
                                  </tr>
                                  <tr>
                                    <td><div align="right"><strong></strong></div></td>
                                    <td><div align="left"></div></td>
                                    <td>&nbsp;</td>
                                    <td><input type="text" name="txtAlamat3" onkeyup="this.value=this.value.toUpperCase();" size="50" maxlength="60" value="$!pemohon.alamat3" $mode $classDis $style/></td>
                                  </tr>
                                  <tr>
                                    <td><div align="right"></div></td>
                                    <td><div align="left">Poskod</div></td>
                                    <td>:</td>
                                    <td><input type="text" name="txtPoskod" size="15" maxlength="5" onkeyup="validatePoskod(this,this.value);" value="$!pemohon.poskod" $mode $classDis $style/></td>
                                  </tr>
                                  <tr>
                                    <td><div align="right"></div></td>
                                    <td><div align="left">Negeri</div></td>
                                    <td>:</td>
                                    <td>$selectNegeriP</td>
                                  </tr>
                                  <tr>
                                    <td><div align="right"></div></td>
                                    <td><div align="left">Daerah</div></td>
                                    <td>:</td>
                                    <td>$selectDaerahP</td>
                                  </tr>
                                  <tr>
                                    <td><div align="right"><strong></strong></div></td>
                                    <td><div align="left">No. Telefon</div></td>
                                    <td>:</td>
                                    <td><input type="text" name="txtNoTelefon" size="20" maxlength="14" value="$!pemohon.tel" $mode id="txtNoTelefon" onblur="validateNumber(this,this.value)" $classDis $style/></td>
                                  </tr>
                                  <tr>
                                    <td><div align="right"><strong></strong></div></td>
                                    <td><div align="left">No. Fax</div></td>
                                    <td>:</td>
                                    <td><input type="text" name="txtNoFax" size="20" maxlength="14"  value="$!pemohon.fax" onblur="validateNumber(this,this.value)" $mode id="txtNoFax" $classDis $style/></td>
                                  </tr>
                                   <tr>
                                    <td><div align="right"></div></td>
                                    <td>No PA</td>
                                    <td><strong>:</strong></td>
                                    <td><input name="txtNoPA" type="text" id="txtNoPA"  value="$!pemohon.noPA" size="14" maxlength="14" onblur="validateNumber(this,this.value)" $mode $classDis $style/></td>
                                  </tr>
                                  <tr>
                                  		<td colspan="4" align="center">
				                         #if($penjualMode == "new")
				  							<input type="button" class="stylobutton" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:simpanPenjual()" $hide>
				  							
				  						#elseif($penjualMode == "update")
				  							<input type="button" class="stylobutton" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:updatePenjual()" $hide>
				  						#else
				  							<input type="button" class="stylobutton" name="cmdSimpan" id="cmdSimpan" value="Kemaskini" onclick="javascript:kemaskiniPenjual()" $hide>
				  						#end
				  						<!-- <input type="button" class="stylobutton" name="cmdSimpan" id="cmdSimpan" value="Kembali" onclick=""> -->
                                  	</td>
                                  </tr>
</table>

</fieldset>
<input type="hidden" name="idPenjual" value="$!pemohon.idPemohon">