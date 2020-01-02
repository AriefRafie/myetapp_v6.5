
 #set ($IdPeguam = "")
               	#set ($NamaPeguam = "")
                #set ($KodPeguam = "")
                #set ($Alamat1 = "")
                #set ($Alamat2 = "")
                #set ($Alamat3 = "")
                #set ($Poskod = "")
                #set ($NoTel = "")
                #set ($NoFax = "")
                #foreach ( $peguam in $Peguam )
                    #set ($IdPeguam = $peguam.IdPeguam)
                	#set ($NamaPeguam = $peguam.NamaPeguam)
                    #set ($KodPeguam = $peguam.noRujukan)
                    #set ($Alamat1 = $peguam.Alamat1)
                    #set ($Alamat2 = $peguam.Alamat2)
                    #set ($Alamat3 = $peguam.Alamat3)
                    #set ($Poskod = $peguam.Poskod)
                    #set ($NoTel = $peguam.NoTel)
                    #set ($NoFax = $peguam.NoFax)
                #end

                #set ($idNegeri = "")
                #foreach ($negeri in $Info)
                	#set ($idNegeri = $negeri.idNegeri)
                #end

                #set ($btnNamePemilik = "value='Kosongkan'")
				#if ($IdPeguam != "")
					#set ($btnNamePemilik = "value='Batal'")
				#end
                <table width="100%" border="0">
                          <tr>
                            <td colspan="2"><div align="left">
                            <fieldset>
							<legend><strong>MAKLUMAT PEGUAM</strong></legend><table width="100%" border="0">
                                <tr>
                                  <td width="20%"><div align="right"><strong><font color="#FF0000">*</font></strong></div></td>
                                    <td width="14%"><div align="left">Nama Peguam</div></td>
                                    <td width="1%">:</td>
                                    <td width="65%"><input type="text" name="txtNamaPeguam" onkeyup="this.value=this.value.toUpperCase();" size="50" maxlength="80" value="$NamaPeguam" $mode $classDis /></td>
                                  </tr>
                                  <tr>
                                    <td><div align="right"><strong></strong></div></td>
                                    <td><div align="left">Kod Peguam</div></td>
                                    <td>:</td>
                                    <td><input type="text" name="txtKodPeguam" onkeyup="this.value=this.value.toUpperCase();" size="30" maxlength="50" value="$KodPeguam" $mode $classDis /></td>
                                  </tr>
                                  <tr>
                                    <td><div align="right"></div></td>
                                    <td><div align="left">Alamat</div></td>
                                    <td>:</td>
                                    <td><input type="text" name="txtAlamat1" onkeyup="this.value=this.value.toUpperCase();" size="50" maxlength="60" value="$Alamat1" $mode $classDis /></td>
                                  </tr>
                                  <tr>
                                    <td><div align="right"><strong></strong></div></td>
                                    <td><div align="left"></div></td>
                                    <td>&nbsp;</td>
                                    <td><input type="text" name="txtAlamat2" onkeyup="this.value=this.value.toUpperCase();" size="50" maxlength="60" value="$Alamat2" $mode $classDis /></td>
                                  </tr>
                                  <tr>
                                    <td><div align="right"><strong></strong></div></td>
                                    <td><div align="left"></div></td>
                                    <td>&nbsp;</td>
                                    <td><input type="text" name="txtAlamat3" onkeyup="this.value=this.value.toUpperCase();" size="50" maxlength="60" value="$Alamat3" $mode $classDis /></td>
                                  </tr>
                                  <tr>
                                    <td><div align="right"></div></td>
                                    <td><div align="left">Poskod</div></td>
                                    <td>:</td>
                                    <td><input type="text" name="txtPoskod" size="5" maxlength="5" onkeyup="validatePoskod(this,this.value);" value="$Poskod" $mode $classDis /></td>
                                  </tr>
                                  <tr>
                                    <td><div align="right"></div></td>
                                    <td><div align="left">Negeri</div></td>
                                    <td>:</td>
                                    <td>$selectBNegeri</td>
                                  </tr>
                                  <tr>
                                    <td><div align="right"></div></td>
                                    <td><div align="left">Daerah</div></td>
                                    <td>:</td>
                                    <td>$selectBDaerah</td>
                                  </tr>
                                  <tr>
                                    <td><div align="right"><strong></strong></div></td>
                                    <td><div align="left">No. Telefon</div></td>
                                    <td>:</td>
                                    <td><input type="text" name="txtNoTelefon" size="20" maxlength="14" value="$NoTel" $mode $classDis /></td>
                                  </tr>
                                  <tr>
                                    <td><div align="right"><strong></strong></div></td>
                                    <td><div align="left">No. Fax</div></td>
                                    <td>:</td>
                                    <td><input type="text" name="txtNoFax" size="20" maxlength="14"  value="$NoFax" $mode $classDis /></td>
                                </tr>
                              </table>
                              </fieldset>
                            </div></td>
                          </tr>
                          <tr>
                            <td colspan="2"><div align="center"><input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="javascript:fPM_KemaskiniPeguam()">&nbsp;&nbsp;<input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:fPM_SimpanPeguam()">&nbsp;&nbsp;<input type="reset" name="cmdBatal" id="cmdBatal" $btnNamePemilik onclick="javascript:fPM_BatalPeguam()">&nbsp;&nbsp;<input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="javascript:fPM_KembaliPeguam()"> &nbsp;&nbsp; <input type="button" name="seterusnya" id="seterusnya" value="Seterusnya" onclick="javascript:fPM_seterusnya()"/>
                            </div></td>
                          </tr>
                          <tr>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                          </tr>
                    </table>
                    
                    <input type="hidden" name="idPeguam" value="$IdPeguam">
                    <input type="hidden" name="idFail" value="$IdFail">
  					<input type="hidden" name="idPermohonan" value="$IdPermohonan">
                                        <input type="hidden" name="idHakmilikurusan" value="$IdHakmilikurusan">