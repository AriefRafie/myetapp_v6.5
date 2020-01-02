                <table width="100%" border="0">
                        <tr>
                            <td colspan="2"><div align="left">
                              <fieldset>
								<legend>Draf Perjanjian
                            	</legend><table width="100%" border="0">
  									<tr>
                            			<td></td>
                           			</tr>
                                    <tr>
                            			<td><div align="left">
                            			  <input type="button" name="cmdTambah" id="cmdTambah" value="Tambah" onclick="fPM_TanahTambah('Tanah','baru',0,1)" />
                            			</div></td>
                  					</tr>
                          			<tr>
                            			<td><div align="left">
                              				<table width="100%" border="0">
                                				<tr class="table_header">
                                  					<td width="3%" scope="col"><strong>Bil</strong></td>
                               					  <td width="12%" scope="col"><strong>Tarikh Terima Draf Perjanjian</strong></td>
                               					  <td width="70%" scope="col" align="center"><strong>Ulasan</strong></td>
                               					  <td width="15%" scope="col"><strong>Tarikh Hantar Pembetulan Draf</strong></td>
                               				  </tr>
                                                #set ($count = 0)
                                                #foreach ( $draf in $Draf )
                                                #set ($count = $count+1)
                                                  #set( $i = $velocityCount )
                                                  #if ( ($i % 2) != 1 )
                                                       #set( $row = "row2" )
                                                  #else
                                                       #set( $row = "row1" )
                                                  #end
                                				<tr>
                                  				  <td class="$row" scope="row">$bil</td>
                                                  <td class="$row">$tarikhTerima</td>
                                                  <td class="$row">$ulasan</td>
                                                  <td class="$row">$tarikhHantar</td>
                                				</tr>
                                                #end
                                              	#if ($count == 0)
                                              	<tr>
                                                	<td colspan="11" scope="row"><font color="#FF0000">Tiada Rekod.</font></td>
                                              	</tr>
                                              	#end
                                              </table>
                                            </div></td>
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
                            <td colspan="2">&nbsp;</td>
                          </tr>
                    </table>
