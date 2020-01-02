                #foreach ( $penjual in $Penjual )
                	#set ($NamaPenjual = $penjual.nama)
                    #set ($noIc = $penjual.ic)
                    #set ($Alamat1Penjual = $penjual.alamat1)
                    #set ($Alamat2Penjual = $penjual.alamat2)
                    #set ($Alamat3Penjual = $penjual.alamat3)
                    #set ($PoskodPenjual = $penjual.poskod)
                    #set ($NoTelPenjual = $penjual.tel)
                    #set ($NoFaxPenjual = $penjual.fax)
                    #set ($noPA = $penjual.noPA)
                #end

                #set ($idNegeri = "")
                #foreach ($negeri in $Info)
                	#set ($idNegeri = $negeri.idNegeri)
                #end

                #set ($btnNamePemilik = "value='Kosongkan'")
				#if ($Idpenjual != "")
					#set ($btnNamePemilik = "value='Batal'")
				#end
                <table width="100%" border="0">
                          <tr>
                            <td colspan="2"><div align="left">
                            <fieldset>
							<legend><strong>MAKLUMAT PENJUAL</strong></legend><table width="100%" border="0">
                                <tr>
                                  <td>&nbsp;</td>
                                  <td width="14%">&nbsp;</td>
                                  <td width="1%">&nbsp;</td>
                                  <td width="65%">&nbsp;</td>
                                </tr>
                                <tr>
                                  <td width="20%">&nbsp;</td>
                                  <td colspan="3"><input name="PenjualSama" type="checkbox" id="PenjualSama" onclick="javascript:PenjualPembeli()" $modeselected />
Penjual Adalah Sama Dengan Pemilik </td>
                                </tr>
                                #if($show == 'open')
                                <tr>
                                  <td><div align="right"></div></td>
                                  <td><div align="left">Penjual</div></td>
                                  <td>:</td>
                                  <td>$selectPenjual</td>
                                </tr>
                                #end
                                <tr>
                                  <td><div align="right"><strong><font color="#FF0000">*</font></strong></div></td>
                                  <td><div align="left">Nama Penjual</div></td>
                                  <td>:</td>
                                  <td><input type="text" name="txtNamaPenjual" onkeyup="this.value=this.value.toUpperCase();" size="50" maxlength="80" value="$NamaPenjual" $mode $classDis /></td>
                                </tr>
                                #if($show == 'open')
                                  <tr>
                                    <td><div align="right"><strong></strong></div></td>
                                    <td><div align="left">No. IC</div></td>
                                    <td>:</td>
                                    <td><input type="text" name="txtKodPenjual" onkeyup="this.value=this.value.toUpperCase();" size="30" maxlength="50" value="$noIc" $mode $classDis /></td>
                                  </tr>
                                  #end
                                  
                                  <tr>
                                    <td><div align="right"></div></td>
                                    <td><div align="left">Alamat</div></td>
                                    <td>:</td>
                                    <td><input type="text" name="txtAlamat1" onkeyup="this.value=this.value.toUpperCase();" size="50" maxlength="60" value="$Alamat1Penjual" $mode $classDis /></td>
                                  </tr>
                                  <tr>
                                    <td><div align="right"><strong></strong></div></td>
                                    <td><div align="left"></div></td>
                                    <td>&nbsp;</td>
                                    <td><input type="text" name="txtAlamat2" onkeyup="this.value=this.value.toUpperCase();" size="50" maxlength="60" value="$Alamat2Penjual" $mode $classDis /></td>
                                  </tr>
                                  <tr>
                                    <td><div align="right"><strong></strong></div></td>
                                    <td><div align="left"></div></td>
                                    <td>&nbsp;</td>
                                    <td><input type="text" name="txtAlamat3" onkeyup="this.value=this.value.toUpperCase();" size="50" maxlength="60" value="$Alamat3Penjual" $mode $classDis /></td>
                                  </tr>
                                  <tr>
                                    <td><div align="right"></div></td>
                                    <td><div align="left">Poskod</div></td>
                                    <td>:</td>
                                    <td><input type="text" name="txtPoskod" size="15" maxlength="5" onkeyup="validatePoskod(this,this.value);" value="$PoskodPenjual" $mode $classDis /></td>
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
                                    <td><input type="text" name="txtNoTelefon" size="20" maxlength="14" value="$NoTelPenjual" $mode id="txtNoTelefon" $classDis /></td>
                                  </tr>
                                  <tr>
                                    <td><div align="right"><strong></strong></div></td>
                                    <td><div align="left">No. Fax</div></td>
                                    <td>:</td>
                                    <td><input type="text" name="txtNoFax" size="20" maxlength="14"  value="$NoFaxPenjual" $mode id="txtNoFax" $classDis /></td>
                                  </tr>
                                  
                                  #if($show !='open')
                                  <tr>
                                    <td><div align="right"></div></td>
                                    <td>No PA</td>
                                    <td><strong>:</strong></td>
                                    <td><input name="txtNoPA" type="text" id="txtNoPA"  value="$noPA" size="14" maxlength="14" $mode $classDis /></td>
                                  </tr>
                                  #end
                                  
                                  <tr>
                                    <td>&nbsp;</td>
                                    <td>&nbsp;</td>
                                    <td>&nbsp;</td>
                                    <td>&nbsp;</td>
                                  </tr>
                              </table>
                              </fieldset>
                            </div></td>
                          </tr>
                          <tr>
                            <td colspan="2"><div align="center"><input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="javascript:KemaskiniPenjual()">&nbsp;&nbsp;<input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:SimpanPenjual()">&nbsp;&nbsp;<input type="reset" name="cmdBatal" id="cmdBatal" $btnNamePemilik onclick="javascript:BatalPenjual()">&nbsp;&nbsp;<input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="javascript:KembaliPenjual()"> &nbsp;&nbsp; <input type="button" name="seterusnya" id="seterusnya" value="Seterusnya" onclick="javascript:SeterusnyaPenjual()"/>
                            </div></td>
                          </tr>
                          <tr>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                          </tr>
                    </table>
                    

                    <input type="hidden" name="idFail" value="$IdFail">
  					<input type="hidden" name="idPermohonan" value="$IdPermohonan">
                                        <input type="hidden" name="idHakmilikurusan" value="$IdHakmilikurusan">