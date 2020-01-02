  <table width="100%" border="0">
                        <tr>
                            <td colspan="2"><div align="left">
                              <fieldset>
								<legend>Maklumat Draf</legend>
                            	<table width="100%" border="0">
							  <tr>
							    <td colspan="4"><table width="100%" border="0">
                                  <tr class="table_header">
                                    <td width="10%"><strong>Bil.</strong></td>
                                    <td width="25%"><strong>Tarikh Hantar Draf</strong></td>
                                    <td width="25%"><strong>Tarikh Terima Draf</strong></td>
                                    <td width="35%"><strong>Ulasan</strong></td>
                                    <td width="5%"></td>
                                  </tr>
                                  #set ($countDraf = 0)
                                  #foreach ( $listdraf in $ListDraf )
                                    #set ($countDraf = $countDraf+1)
                                      #set( $i = $velocityCount )
                                      #if ( ($i % 2) != 1 )
                                           #set( $row = "row2" )
                                      #else
                                           #set( $row = "row1" )
                                      #end
                                  <tr>
                                    <td class="$row">$countDraf</td>
                                    <td class="$row">$listdraf.tarikhHantarDraf</td>
                                    <td class="$row">$listdraf.tarikhTerimaDraf</td>
                                    <td class="$row">$listdraf.ulasanLA</td>
                                    <td class="$row" align="center"><a href="javascript:KemaskiniDraf('$listdraf.idDokumenperjanjian', '$listdraf.tarikhHantarDraf', '$listdraf.tarikhTerimaDraf','$listdraf.ulasanLA')" class="style1"><img src="../img/edit.gif" border="0"></a></td>
                                  </tr>
                                  #end
                                  #if ($countDraf == 0)
                                  <tr> 
                                    <td colspan="5" scope="row"><font color="#FF0000">Tiada Rekod.</font></td>
                                  </tr>
                                  #end
                                </table></td>
							    </tr>
                                <tr>
							    <td width="24%">&nbsp;</td>
                            			<td width="15%"></td>
                            			<td width="1%"></td>
                            			<td width="60%"><div align="right"><strong>Bil. Draf&nbsp;:&nbsp;&nbsp;&nbsp;$countDraf</strong></div></td>
                       			  </tr>
							  <!-- <tr>
							    <td width="24%">&nbsp;</td>
                            			<td width="15%"><div align="left"><strong>Bil. Draf</strong></div></td>
                            			<td width="1%"><strong>:</strong></td>
                            			<td width="60%"><input type="text" name="txtCount" id="txtCount" size="15" maxlength="50" onkeyup="this.value=this.value.toUpperCase();" value="$countDraf" readonly="readonly"/></td>
                       			  </tr> -->
                          			
                          			
                                          <tr style="display:none">
                                            <td colspan="3"><table width="100%" border="0">
                                              <tr>
                                                <td width="35%">&nbsp;</td>
                                                <td width="34%"><strong>Tarikh Hantar Draf</strong></td>
                                                <td width="1%"><strong>:</strong></td>
                                                <td width="30%"><input type="text" name="txdTarikhHantarDraf1" id="txdTarikhHantarDraf1" size="10" value="" readonly="readonly" />
                                <img src="../img/calendar.gif" border="0" onClick="displayDatePicker('txdTarikhHantarDraf',false,'dmy');" style="display:$Style2"></td>
                                              </tr>
                                              <tr>
                                                <td>&nbsp;</td>
                                                <td><strong>Tarikh Hantar LA</strong></td>
                                                <td><strong>:</strong></td>
                                                <td><input type="text" name="txdTarikhHantar" id="txdTarikhHantar" size="10" value="$TarikhHantar" readonly="readonly" />
                                <img src="../img/calendar.gif" border="0" onClick="displayDatePicker('txdTarikhHantar',false,'dmy');" style="display:$Style2"></td>
                                              </tr>
                                            </table></td>
                                            <td>
                                            <table width="100%" border="0">
                                              <tr>  
                                              	<td width="5%">&nbsp;</td>                                              
                                                <td width="38%"><strong>Tarikh Terima Draf</strong></td>
                                                <td width="1%"><strong>:</strong></td>
                                                <td width="30%"><input type="text" name="txdTarikhHantarDraf1" id="txdTarikhHantarDraf1" size="10" value="" readonly="readonly" />
                                <img src="../img/calendar.gif" border="0" onClick="displayDatePicker('txdTarikhHantarDraf',false,'dmy');"></td>
                                				<td width="26%">&nbsp;</td>
                                              </tr>
                                              <tr style="display:none">     
                                              	<td>&nbsp;</td>                                           
                                                <td><strong>Tarikh Terima Kembali LA</strong></td>
                                                <td><strong>:</strong></td>
                                                <td><input type="text" name="txdTarikhHantar" id="txdTarikhHantar" size="10" value="" readonly="readonly" />
                                <img src="../img/calendar.gif" border="0" onClick="displayDatePicker('txdTarikhHantar',false,'dmy');"></td>
                                				<td>&nbsp;</td>
                                              </tr>
                                            </table>                                            </td>
                                          </tr>
                                          <tr>
                                            <td>&nbsp;</td>
                                            <td valign="top"><strong>Tarikh Hantar Draf</strong></td>
                                            <td valign="top"><strong>:</strong></td>
                                            <td><input type="text" name="txdTarikhHantarDraf" id="txdTarikhHantarDraf" size="10" value="" readonly="readonly" />
                                <img src="../img/calendar.gif" border="0" onClick="displayDatePicker('txdTarikhHantarDraf',false,'dmy');"></td>
                                          </tr>
                                          <tr>
                                            <td>&nbsp;</td>
                                            <td valign="top"><strong>Tarikh Terima Draf</strong></td>
                                            <td valign="top"><strong>:</strong></td>
                                            <td><input type="text" name="txdTarikhTerimaDraf" id="txdTarikhTerimaDraf" size="10" value="" readonly="readonly" />
                                <img src="../img/calendar.gif" border="0" onClick="displayDatePicker('txdTarikhTerimaDraf',false,'dmy');"></td>
                                          </tr>
                                          <tr>
                                            <td>&nbsp;</td>
                                            <td valign="top"><div align="left"><strong>Ulasan LA</strong></div></td>
                                            <td valign="top"><strong>:</strong></td>
                                            <td><label>
                                      <textarea name="txtUlasanDraf" id="txtUlasanDraf" cols="30" rows="3" onkeyup="this.value=this.value.toUpperCase();"></textarea>
                                    </label></td>
                                  </tr>
                                  <tr>
                                <td colspan="4">
                                  <div align="center">
  							  <input type="button" name="cmdSimpanDraf" id="cmdSimpanDraf" value="Simpan" onclick="SimpanDraf()">
  &nbsp;&nbsp;
                              <input type="button" name="cmdBatalDraf" id="cmdBatalDraf" value="Kosongkan" onclick="BatalDraf()">
  &nbsp;&nbsp;
                              <input type="button" name="cmdKembaliDraf" id="cmdKembaliDraf" value="Kembali" onClick="Kembali()">
                                  </div></td>
                              </tr>
								</table>
                              </fieldset>
							</div></td>
                          </tr>                          
                    </table>