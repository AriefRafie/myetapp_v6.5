 <table width="100%" border="0">
                        <tr>
                            <td colspan="2"><div align="left">
                              <fieldset>
								<legend>MAKLUMAT ASAS TANAH &amp; BANGUNAN JIKA ADA
                            	</legend><table width="100%" border="0">
                                    <tr>
                            			<td width="100%"><div align="left"><input type="button" name="cmdTambah" id="cmdTambah" value="Tambah" onclick="javascript:fPM_TBangunTambah('TBangun','baru',1,1)" /></div></td>
                  					</tr>
                          			<tr>
                            			<td><div align="left"><strong></strong>
                              				<table width="100%" border="0">
                                				<tr class="table_header">
                                  					<td scope="col">Jenis Hakmilik</td>
                                 					<td scope="col">No. Hakmilik</td>
                                                    <td scope="col">No. Bangunan</td>
                                                    <td scope="col">No. Tingkat</td>
                                                    <td scope="col">No. Petak</td>
                                  					<td scope="col">Kod Lot</td>
                                  					<td scope="col">No Lot</td>
                                  					<td scope="col">Kod Luas</td>
                                  					<td scope="col">Luas</td>
                                  					<td scope="col" style="display:none">Luas (H)</td>
                                  					<td scope="col">No Pelan</td>
                                  					<td scope="col" style="display:none">Status</td>
                                  					<td scope="col">Tarikh Mula</td>
                                  					<td scope="col">Tarikh Luput</td>
                                				</tr>
                                                #set ($countBangun = 0)
                                                #set ($countPetak = 0)
                                                #set ($count = 0)
                                                #foreach ( $tbangun in $TBangun )
                                                #set ($count = $count+1)
                                                  #set( $i = $velocityCount )
                                                  #if ( ($i % 2) != 1 )
                                                       #set( $row = "row2" )
                                                  #else
                                                       #set( $row = "row1" )
                                                  #end
                                				<tr>
                                  				  <td class="$row" scope="row">$tbangun.HakKeterangan</td>
                                                  <td class="$row"><a href="javascript:fPM_TBangunDetails('$IdPermohonan','$tbangun.IdHakmilikurusan','TBangun','view')">$tbangun.NoHakmilik</a></td>
                                                  <td class="$row">$tbangun.LotKeterangan</td>
                                                  <td class="$row">$tbangun.NoBangunan</td>
                                                  <td class="$row">$tbangun.NoTingkat</td>
                                                  <td class="$row">$tbangun.NoPetak</td>
                                                  <td class="$row">$tbangun.NoLot</td>
                                                  <td class="$row">$tbangun.LuasKeterangan</td>
                                                  <td class="$row">$tbangun.Luas</td>
                                                  <td class="$row" style="display:none">Luas H</td>
                                                  <td class="$row">$tbangun.NoPelan</td>
                                                  <td class="$row" style="display:none">Status</td>
                                                  <td class="$row">$tbangun.TarikhMula</td>
                                                  <td class="$row">$tbangun.TarikhLuput</td>
                                                  #if($tbangun.NoBangunan != "TIADA" || $tbangun.NoBangunan == "")
                                                  	#set ($countBangun = $countBangun + 1)
                                                  #end
                                                  #if($tbangun.NoPetak != "TIADA" || $tbangun.NoPetak == "")
                                                  	#set ($countPetak = $countPetak + 1)
                                                  #end
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
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                          </tr>
                          <tr style="display:none">
                            <td colspan="2">&nbsp;</td>
                          </tr>
                    </table>
                    
                    <input type="hidden" name="idHakmilikurusan" value="$IdHakmilikurusan">
                	<input type="hidden" name="idFail" value="$IdFail">
  					<input type="hidden" name="idPermohonan" value="$IdPermohonan">
