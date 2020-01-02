                <table width="100%" border="0">
                        <tr>
                            <td colspan="2"><div align="left">
                              <fieldset>
								<legend>MAKLUMAT ASAS TANAH JIKA ADA</legend>
                            	<table width="100%" border="0">
  									<tr>
                            			<td width="100%" colspan="2"><div align="left">
                            			  <input type="button" name="cmdTambah" id="cmdTambah" value="Tambah" onclick="fPM_TanahTambah('Tanah','baru',0,1)" />
                           			  </div></td>
                  					</tr>
                          			<tr>
                            			<td colspan="2"><div align="left">
                              				<table width="100%" border="0">
                                				<tr class="table_header">
                                  					<td scope="col"><strong>Jenis Hakmilik</strong></td>
                               					  <td scope="col"><strong>No. Hakmilik</strong></td>
                               					  <td scope="col"><strong>Kod Lot</strong></td>
                               					  <td scope="col"><strong>No Lot</strong></td>
                               					  <td scope="col"><strong>Kod Luas</strong></td>
                               					  <td scope="col"><strong>Luas</strong></td>
                               					  <td scope="col" style="display:none"><strong>Luas (H)</strong></td>
                               					  <td scope="col"><strong>No Pelan</strong></td>
                               					  <td scope="col" style="display:none"><strong>Status</strong></td>
                               					  <td scope="col"><strong>Tarikh Mula</strong></td>
                               					  <td scope="col"><strong>Tarikh Luput</strong></td>
                               				  </tr>
                                                #set ($count = 0)
                                                #foreach ( $tanah in $Tanah )
                                                #set ($count = $count+1)
                                                  #set( $i = $velocityCount )
                                                  #if ( ($i % 2) != 1 )
                                                       #set( $row = "row2" )
                                                  #else
                                                       #set( $row = "row1" )
                                                  #end
                                				<tr>
                                  				  <td class="$row" scope="row">$tanah.HakKeterangan</td>
                                                  <td class="$row"><a href="javascript:fPM_TanahDetails('$IdPermohonan','$tanah.IdHakmilikurusan','Tanah','view')">$tanah.NoHakmilik</a></td>
                                                  <td class="$row">$tanah.LotKeterangan</td>
                                                  <td class="$row">$tanah.NoLot</td>
                                                  <td class="$row">$tanah.LuasKeterangan</td>
                                                  <td class="$row">$tanah.Luas</td>
                                                  <td class="$row" style="display:none">Luas H</td>
                                                  <td class="$row">$tanah.NoPelan</td>
                                                  <td class="$row" style="display:none">Status</td>
                                                  <td class="$row">$tanah.TarikhMula</td>
                                                  <td class="$row">$tanah.TarikhLuput</td>
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
                            <td><div align="right"><strong></strong></div></td>
                            <td>&nbsp;</td>
                          </tr>
                          <tr>
                            <td colspan="2">&nbsp;</td>
                          </tr>
                    </table>
                    
                    <input type="hidden" name="idFail" value="$IdFail">
  					<input type="hidden" name="idUlasankptg" value="$IdUlasankptg">
  					<input type="hidden" name="idPermohonan" value="$IdPermohonan">
                    <input type="hidden" name="negeri" value="$negeri">
                    