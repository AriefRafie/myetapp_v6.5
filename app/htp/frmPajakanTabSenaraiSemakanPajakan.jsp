   <table width="100%" border="0">
                   			#set ( $checked = "" )
                            #foreach ( $semak in $senaraiSemakan )
                                #set( $i = $velocityCount )
                                #if ( ($i % 2) == 0 )
                                    #set( $row = "row2" )
                                #else
                                    #set( $row = "row1" )
                                #end
                                <tr>
                                  <td class="$row" width="30%" align="right">
                                        #if ( $semakclass.isSemakan("$IdPermohonan", "$semak.id" ))
                                            #set ( $checked = "checked" )
                                        #else
                                           #set ( $checked = "" )
                                        #end
                                        <input class="cb" type="checkbox" name="cbsemaks" value="$semak.id" $checked $modeSoc>
                                  </td>
                                    <td class="$row" width="70%">
                                        $semak.keterangan
                                  </td>
                                </tr>       
                            #end
                          <tr>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                          </tr>
                          <tr>
                            <td colspan="2"><div align="center"><input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="Kemaskini()">&nbsp;&nbsp;<input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="Simpan()">&nbsp;&nbsp;<input type="reset" name="cmdBatal" id="cmdBatal" $btnName onclick="Batal()">&nbsp;&nbsp;<input type="button" name="cmdKembali" id="cmdKembali" value="Kembali" onClick="Kembali()"></div></td>
                          </tr>
                          <tr>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                          </tr>
                    </table>