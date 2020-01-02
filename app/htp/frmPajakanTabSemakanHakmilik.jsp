 <table width="100%" border="0">
                          
                          <tr>
                            <td width="35%" height="28"><div align="right"><strong>Daerah :</strong></div></td>
                            <td width="65%">$selectDaerah</td>
                          </tr>
                          <tr>
                            <td height="28"><div align="right"><strong>Mukim / Pekan / Bandar :</strong></div></td>
                            <td>$selectMukim</td>
                          </tr>
                          <tr>
                            <td height="28"><div align="right"><strong>Jenis Hakmilik :</strong></div></td>
                            <td>$selectJenishakmilik</td>
                          </tr>
                          <tr>
                            <td height="28"><div align="right"><strong>No. Hakmilik :</strong></div></td>
                            <td><input type="text" name="txtNoHakmilik" size="45" maxlength="50" onkeyup="this.value=this.value.toUpperCase();" value="$NoHakmilik" /></td>
                  </tr>
                          <tr>
                            <td height="28"><div align="right"></div></td>
                            <td><input style="width:80px" type="button" name="cmdCari" id="cmdCari" value="Cari" onclick="HakmilikSearch()" />&nbsp;
                            <input type="button" name="cmdKosongkan" id="cmdKosongkan" value="Kosongkan" onclick="cancel()"></td>
                          </tr>
                          <tr>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                          </tr>
                          <tr>
                            <td colspan="2"><table width="100%" border="0">
                              <tr class="table_header">
                                <td width="13%" scope="col"><strong>Negeri</strong></td>
                                <td width="15%" scope="col"><strong>Daerah</strong></td>
                                <td width="17%" scope="col"><strong>Mukim/Pekan/Bandar</strong></td>
                                <!-- <td width="13%" scope="col"><strong>Jenis Hakmilik</strong></td> -->
                                <td width="12%" scope="col"><strong>No. Hakmilik</strong></td>
                                <td width="17%" scope="col"><strong>Pegangan Hakmilik</strong></td>
                                <td width="13%" scope="col" style="display:none"><strong>Tarikh Daftar Pajakan</strong></td>
                              </tr>
                              #set ($count = 0)
                              #foreach ( $hakmilik in $SenaraiHakmilik )
                                #set ($count = $count+1)
                                  #set( $i = $velocityCount )
                                  #if ( ($i % 2) != 1 )
                                       #set( $row = "row2" )
                                  #else
                                       #set( $row = "row1" )
                                  #end
                              <tr style="display:$style1">
                                <td class="$row">$hakmilik.namaNegeri</td>
                                <td class="$row">$hakmilik.namaDaerah</td>
                                <td class="$row">$hakmilik.namaMukim</td>
                                <!-- <td class="$row">$hakmilik.kodJenisHakmilik</td> -->
                                <td class="$row"><a href="javascript:seterusnyaPemohon('$hakmilik.idHakmilikurusan')" class="style1">$hakmilik.kodJenisHakmilik$hakmilik.noHakmilik</a></td>
                                <td class="$row">$hakmilik.peganganHakmilik</td>
                                <td class="$row" style="display:none">$hakmilik.tarikhMasuk</td>
                              </tr>
                              <tr style="display:$style2">
                                <td class="$row">$hakmilik.namaNegeri</td>
                                <td class="$row">$hakmilik.namaDaerah</td>
                                <td class="$row">$hakmilik.namaMukim</td>
                                <!-- <td class="$row">$hakmilik.kodJenisHakmilik</td> -->
                                <td class="$row"><a href="javascript:seterusnyaPemohonBaru('$hakmilik.idHakmilik')" class="style1">$hakmilik.kodJenisHakmilik $hakmilik.noHakmilik</a></td>
                                <td class="$row">$hakmilik.peganganHakmilik</td>
                                <td class="$row" style="display:none">$hakmilik.tarikhMasuk</td>
                              </tr>
                              #end
                              #if ($count == 0)
                              <tr> 
                                <td colspan="6" scope="row"><font color="#FF0000">Tiada Rekod.</font></td>
                              </tr>
                              #end
                            </table></td>
                          </tr>
                    </table>