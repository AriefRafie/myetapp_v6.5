
                  <table width="100%" border="0">
                          <tr>
                              <td colspan="2"><div align="left">
                                <fieldset>
                                  <legend>Semakan Kementerian</legend>
                                  <table width="100%" border="0" cellpadding="0">
                                      <tr>
                                          <td width="30%">
                                        <div align="right">Semakan :</div></td>
                                          
                                          <td width="70%">
                                          
                                     <select name="semakanKem" id="semakanKem">
                                         <option value = "" #if($semakanKem == '') selected="selected" #end >Sila Pilih</option>
                                         <option value="A" #if($semakanKem == 'A') selected="selected" #end  >Ada</option>
                                         <option value="T" #if($semakanKem == 'T') selected="selected" #end  >Tiada</option>

                                        </select>
                                        
                                        </td>
                                      </tr>
                                      <tr>
                                          <td><div align="right">No Rujukan Surat :</div></td>
                                          <td><input type="text" name="txtNoSuratRujukan" id="txtNoSuratRujukan" value="$noRujSurKem" /></td>
                                      </tr>
                                      <tr>
                                        <td><div align="right">Tarikh Surat :</div></td>
                                        <td><input type="text" name="txdTarikhSuratKem" id="txdTarikhSuratKem" size="15" value="$tarikhSurKem" />
                                        
                                        #if($pagemode == 'baru' || $pagemode == 'kemaskini')
                                        
                                        <img src="../img/calendar.gif" alt="calender" border="0" style="" onclick="displayDatePicker('txdTarikhSuratKem',false,'dmy');" />
                                        
                                        #end
                                        
                                        </td>
                                    </tr>
                                      <tr>
                                        <td><div align="right">Catatan :</div></td>
                                        <td><textarea name="txtcatatanKem" id="txtcatatanKem" cols="45" rows="5" >$catatanKementerian </textarea></td>
                                    </tr>
                                      <tr>
                                          <td colspan="2"></td>
                                      </tr>
                                      <tr>
                                          <td colspan="2">&nbsp;</td>
                                            </tr>
                                  </table>
                                </fieldset>
                              </div></td>
                            </tr>
                            <tr>
                              <td></td>
                              <td>&nbsp;</td>
                            </tr>
                            <tr>
                              <td colspan="2"><div align="left">
  
                          <table width="100%" border="0">
                          <tr>
                              <td colspan="2"><div align="left">
                             <fieldset>
                                  <legend>PKP
                                  </legend>
                                  <table width="100%" border="0">
                                      <tr>
                                          <td width="30%"><div align="right">Asal :</div></td>
                                          <td width="70%"><select name="selectAsalPKP" id="selectAsalPKP">
                                            <option value="" #if($selectAsalPKP == '') selected="selected" #end >Sila Pilih</option>
                                            <option value="L" #if($selectAsalPKP == 'L') selected="selected" #end>Lulus</option>
                                            <option value="T" #if($selectAsalPKP == 'T') selected="selected" #end >Tidak Lulus</option>
                                        </select></td>
                                      </tr>
                                      <tr>
                                          <td><div align="right">No Rujukan Surat :</div></td>
                                          <td><input type="text" name="txtNoSuratRujukanPKP" id="txtNoSuratRujukanPKP" value="$noRujSurPKP" /></td>
                                      </tr>
                                      <tr>
                                        <td><div align="right">Tarikh Surat :</div></td>
                                        <td><input type="text" name="txdTarikhSuratPKP" id="txdTarikhSuratPKP" size="15" value="$tarikhSurPKP" />
                                        
                                        #if($pagemode == 'baru' || $pagemode == 'kemaskini')
                                        
                                        <img src="../img/calendar.gif" alt="calender" border="0" style="" onclick="displayDatePicker('txdTarikhSuratPKP',false,'dmy');" />
                                        
                                        #end
                                        
                                        </td>
                                    </tr>
                                      <tr>
                                        <td><div align="right">Catatan :</div></td>
                                        <td><textarea name="txtcatatanPKP" id="txtcatatanPKP" cols="45" rows="5">$catatanPKP</textarea></td>
                                    </tr>
                                      <tr>
                                          <td colspan="2"></td>
                                      </tr>
                                      <tr>
                                          <td colspan="2">&nbsp;</td>
                                    </tr>
                                      <tr>
                                        <td colspan="2" align="center">
                                        
               #if($pagemode == 'baru' || $pagemode == 'kemaskini')
                                         
<input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="fPSK_Simpan()"/>&nbsp;&nbsp;
              <input type="button" name="cmdBatal2" id="cmdBatal" value="Batal" onclick="fPSK_Batal()" />		
              
              #elseif($pagemode == 'simpan')
                                        
              <input type="button" name="cmdkemaskini" id="cmdkemaskini" value="Kemaskini" onclick="fPSK_Kemaskini()" />&nbsp;&nbsp; <input type="button" name="cmdSeterusnya" id="cmdSeterusnya" value="Seterusnya" onclick="fPSK_Seterusnya()" />
              
              #else
   <input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onclick="fPSK_Kembali()" />&nbsp;&nbsp;
  
   
				#end
                                          
                                          </td>
                                    </tr>
                                      <tr>
                                        <td colspan="2">&nbsp;</td>
                                    </tr>
                                  </table>
                                  
                                </fieldset>
                                
                              </div></td>
                            </tr>
                      </table>
                      
  <input type="hidden" name="idPermohonan" value="$idPermohonan" />