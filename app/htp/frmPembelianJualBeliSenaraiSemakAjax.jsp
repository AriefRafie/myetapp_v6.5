                <table width="100%" border="0">
                        <tr>
                            <td colspan="2"><div align="left">
                              <fieldset>
								<legend><strong>SENARAI SEMAK DRAF PERJANJIAN</strong></legend><table width="100%" border="0">
                               #set ( $checked = "" )
							   #foreach ( $semak in $senaraiSemakanList )
							        #set( $i = $velocityCount )
							        #if ( ($i % 2) == 0 )
							            #set( $row = "row2" )
			    				    #else
							            #set( $row = "row1" )
							        #end
  									<tr>
                            			<td width="10%" align="right" class="$row">
                                         #if ( $semakclass.isSemakan("$IdPermohonan", "$semak.id" ))
                   							 #set ( $checked = "checked" )
                						#else
                   							#set ( $checked = "" )
                						#end
                					<input class="cb" type="checkbox" name="cbsemaks" value="$semak.id" $checked $modeSoc>
                                    </td>
                            			<td width="90%" class="$row" >
                                          $semak.keterangan
                                        </td>
                                     
                          			</tr>
                                 #end
								</table>
                              </fieldset>
							</div></td>
                          </tr>                          
                          <tr>
                            <td><div align="right"><strong></strong></div></td>
                            <td>&nbsp;</td>
                          </tr>
                          <tr>
                            <td colspan="2"><div align="center"><input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="javascript:fPJBSSA_KemaskiniSemak()">&nbsp;&nbsp;<input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:fPJBSSA_SimpanSemak()">&nbsp;&nbsp;<input type="reset" name="cmdBatal" id="cmdBatal" $btnNamePeguam onclick="javascript:fPJBSSA_BatalSemak()">&nbsp;&nbsp;<input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="javascript:fPJBSSA_KembaliSemak()"></div></td>
                          </tr>
                          <tr>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                          </tr>
                    </table>
			<!--
                    <input type="hidden" name="idPeguam" value="$IdPeguam">
                  <input type="hidden" name="idHakmilikurusan" value="$IdHakmilikurusan">
                	<input type="hidden" name="idFail" value="$IdFail">
  					<input type="hidden" name="noFail" value="$NoFail">
  					<input type="hidden" name="idPermohonan" value="$IdPermohonan">
			-->
            
                    <input type="hidden" name="idFail" value="$IdFail">
  					<input type="hidden" name="idPermohonan" value="$IdPermohonan">