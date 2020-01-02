<style type="text/css">
<!--
.style1 {color: #0033FF}
-->
</style>         
          
            <table width="100%" border="0"  cellspacing="2" cellpadding="2">
             #if ($mode == 'newSPHP' || $mode == 'updateSPHP' || $mode == 'viewSPHP')
              <tr>
                <td>
				  <fieldset>
				    <legend><strong>ULASAN SPHP</strong>
                	</legend>
				    <table width="100%" border="0">
                #foreach ($beanUlasanSPHP in $BeanUlasanSPHP)
                  <tr>
                    <td width="8%" align="right">#if ($mode == 'newSPHP' || $mode == 'updateSPHP')<font color="#FF0000">*</font>#end</td>
                    <td>No. Rujukan SPHP</td>
                    <td>:</td>
                    <td><input name="txtNoRujukanSPHP" type="text" id="txtNoRujukanSPHP" value="$beanUlasanSPHP.noRujukan" size="40" class="$classDis" $readOnly onBlur="this.value=this.value.toUpperCase();" /></td>
                  </tr>
                  <tr>
                    <td width="8%" align="right">#if ($mode == 'newSPHP' || $mode == 'updateSPHP')<font color="#FF0000">*</font>#end</td>
                    <td width="24%">Tarikh Hantar</td>
                    <td width="1%">:</td>
                    <td width="67%"><input type="text" name="txdTarikhHantarSPHP" id="txdTarikhHantarSPHP" size="10" value="$beanUlasanSPHP.tarikhHantar" onblur="check_date(this)" class="$classDis" $readOnly />
                      #if ($mode == 'newSPHP' || $mode == 'updateSPHP')<img src="../img/calendar.gif" alt="Calendar" border="0" style="display:$Style2" onClick="displayDatePicker('txdTarikhHantarSPHP',false,'dmy');">
                    #end          
                     </td>
                  </tr>
                  <tr>
                    <td align="right">#if ($mode == 'newSPHP' || $mode == 'updateSPHP')<font color="#FF0000">*</font>#end</td>
                    <td>Tarikh Terima</td>
                    <td>:</td>
                    <td><input type="text" name="txdTarikhTerimaSPHP" id="txdTarikhTerimaSPHP" size="10" value="$beanUlasanSPHP.tarikhTerima" onblur="check_date(this)" class="$classDis" $readOnly />
                      #if ($mode == 'newSPHP' || $mode == 'updateSPHP')<img src="../img/calendar.gif" alt="Calendar" border="0" style="display:$Style2" onClick="displayDatePicker('txdTarikhTerimaSPHP',false,'dmy');">
                    #end          
                    </td>
                  </tr>
                  <tr>
                    <td align="right">#if ($mode == 'newSPHP' || $mode == 'updateSPHP')<font color="#FF0000">*</font>#end</td>
                    <td valign="middle">Keputusan</td>
                    <td valign="middle">:</td>
                    <td valign="middle">
                    <select name="socKeputusan" id="socKeputusan" class="$classDis" $classDis>

                        #if($beanUlasanSPHP.status == 'S' || $beanUlasanSPHP.status == "" )
						
                      <option value="S" selected="selected">SETUJU</option>
                      <option value="TS">TIDAK SETUJU</option>
              
                        #else
                        
                      <option value="S" >SETUJU</option>
                      <option value="TS" selected="selected" >TIDAK SETUJU</option>
              
                        #end
  
                    </select>
                    </td>
                  </tr>
                  <tr>
                    <td valign="top">&nbsp;</td>
                    <td valign="top">Ulasan</td>
                    <td valign="top">:</td>
                    <td valign="top"><textarea name="txtUlasanSPHP" id="txtUlasanSPHP" cols="50" rows="5" onBlur="this.value=this.value.toUpperCase();" class="$classDis" $readOnly>$beanUlasanSPHP.ulasan</textarea></td>

                  </tr>
                  #end
                  <tr>
                  	<td colspan="4">&nbsp;</td>
                  <tr>
                  	<td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>#if ($mode == 'newSPHP')
                      <input class="stylobutton" type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:SimpanUlasanSPHP()" />
                      <input class="stylobutton" type="reset" name="cmdBatal" id="cmdBatal" value="Kosongkan"/>
                      <input class="stylobutton" type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="javascript:batalUlasanSPHP()" />
#elseif ($mode == 'viewSPHP')
<input class="stylobutton" type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="javascript:KemaskiniUlasanSPHP()" />
<input class="stylobutton" type="button" name="cmdHapus" id="cmdHapus" value="Hapus" onclick="javascript:HapusSPHP()" />
<input class="stylobutton" type="button" name="cmdBatal" id="cmdBatal" value="Kembali" onclick="javascript:batalUlasanSPHP()" />
#elseif ($mode == 'updateSPHP')
<input class="stylobutton" type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:SimpanUpdateSPHP()" />
<input class="stylobutton"  type="reset" name="cmdBatal" id="cmdBatal" value="Kosongkan"/>
<input class="stylobutton" type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="javascript:batalUpdateSPHP()" />
#end </td>
              </table>
           		</fieldset>
                </td>
              </tr>
              
              <tr>
                <td>&nbsp;</td>
              </tr>
              
              #end
              
              <tr>
                <td>
                
               	  <fieldset>
    <legend><strong>SENARAI ULASAN SPHP</strong>    </legend>
    <table align="center" width="100%">
      #if ($mode == 'view')
      <tr>
        <td colspan="6" scope="row"><input class="stylobutton" name="cmdDaftar" type="button" value="Daftar Baru" onclick="javascript:daftarBaruSPHP()"/></td>
      </tr>
      #end
  <tr class="table_header">
    <td scope="row" width="4%" align="center"><strong>Bil</strong></td>
    <td width="18%"><strong>No. Rujukan SPHP</strong></td>
    <td width="15%" align="center"><strong>Tarikh Hantar</strong></td>
    <td width="15%" align="center"><strong>Tarikh Terima</strong></td>
    <td colspan="2" align="center"><strong>Keputusan</strong></td>
    </tr>
      #set ($list = "")
      #if ($SenaraiUlasanSPHP.size() > 0)
      #foreach ($list in $SenaraiUlasanSPHP)
      #if ($list.bil == '')
      #set( $row = "row1" )
      #elseif (($list.bil % 2) != 0)
      #set( $row = "row1")
      #else 
      #set( $row = "row2" )
      #end
      <tr>
        <td class="$row" align="center" width="4%">$list.bil</td>
        <td class="$row" width="18%"><a href="javascript:paparSPHP('$list.idUlasanSPHP')" class="style1">$list.noRujukan</a></td>
        <td class="$row" align="center" width="15%">$list.tarikhHantar</td>
        <td class="$row" align="center" width="15%">$list.tarikhTerima</td>
        <td colspan="2" align="center" class="$row">$list.statusName</td>
        </tr>
      #end
      #else
  <tr>
    <td class="row1">&nbsp;</td>
    <td class="row1">Tiada Rekod</td>
    <td class="row1">&nbsp;</td>
    <td class="row1">&nbsp;</td>
    <td width="33%" class="row1">&nbsp;</td>
    <td width="15%" class="row1">&nbsp;</td>
  </tr>
      #end
    </table>
                  </fieldset>
                
                
                </td>
              </tr>
              <tr>
                <td>&nbsp;</td>
              </tr>
            </table>
            <p>&nbsp;</p>
<!-- </fieldset> -->

