<style type="text/css">
<!--
.style1 {color: #0033FF}
-->
</style>         
          
            <table width="100%" border="0"  cellspacing="2" cellpadding="2">
             #if ($mode == 'newKJP' || $mode == 'updateKJP' || $mode == 'viewKJP')
              <tr>
                <td>
				  <fieldset><legend><strong>ULASAN KJP</strong></legend>
                	<table width="100%" border="0">
                #foreach ($beanUlasanKJP in $BeanUlasanKJP)
                  <tr>
                    <td width="8%" align="right">#if ($mode == 'newKJP' || $mode == 'updateKJP')<font color="#FF0000">*</font>#end</td>
                    <td>No. Rujukan KJP</td>
                    <td>:</td>
                    <td><input name="txtNoRujukanKJP" type="text" id="txtNoRujukanKJP" value="$beanUlasanKJP.noRujukan" size="40" class="$classDis" $readOnly onBlur="this.value=this.value.toUpperCase();" /></td>
                  </tr>
                  <tr>
                    <td width="8%" align="right">#if ($mode == 'newKJP' || $mode == 'updateKJP')<font color="#FF0000">*</font>#end</td>
                    <td width="24%">Tarikh Hantar Ke KJP</td>
                    <td width="1%">:</td>
                    <td width="67%"><input type="text" name="txdTarikhHantarKJP" id="txdTarikhHantarKJP" size="10" value="$beanUlasanKJP.tarikhHantar" onblur="check_date(this)" class="$classDis" $readOnly />
                      #if ($mode == 'newKJP' || $mode == 'updateKJP')<img src="../img/calendar.gif" alt="Calendar" border="0" style="display:$Style2" onClick="displayDatePicker('txdTarikhHantarKJP',false,'dmy');">
                    #end          
                     </td>
                  </tr>
                  <tr>
                    <td align="right">#if ($mode == 'newKJP' || $mode == 'updateKJP')<font color="#FF0000">*</font>#end</td>
                    <td>Tarikh Terima Dari KJP</td>
                    <td>:</td>
                    <td><input type="text" name="txdTarikhTerimaKJP" id="txdTarikhTerimaKJP" size="10" value="$beanUlasanKJP.tarikhTerima" onblur="check_date(this)" class="$classDis" $readOnly />
                      #if ($mode == 'newKJP' || $mode == 'updateKJP')<img src="../img/calendar.gif" alt="Calendar" border="0" style="display:$Style2" onClick="displayDatePicker('txdTarikhTerimaKJP',false,'dmy');">
                    #end          
                    </td>
                  </tr>
                  <tr>
                    <td align="right">#if ($mode == 'newKJP' || $mode == 'updateKJP')<font color="#FF0000">*</font>#end</td>
                    <td valign="middle">Keputusan</td>
                    <td valign="middle">:</td>
                    <td valign="middle">
                    
                    <select name="socKeputusan" id="socKeputusan" class="$classDis" $classDis>
						
                        #if($beanUlasanKJP.status == 'S')
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
                    <td valign="top"><textarea name="txtUlasanKJP" id="txtUlasanKJP" cols="50" rows="5" onBlur="this.value=this.value.toUpperCase();" class="$classDis" $readOnly>$beanUlasanKJP.ulasanKJP</textarea></td>

                  </tr>
                  #end
                  <tr>
                  	<td colspan="4">&nbsp;</td>
                  <tr>
                  	<td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>#if ($mode == 'newKJP')
                      <input class="stylobutton" type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:SimpanUlasanKJP()" />
                      <input class="stylobutton" type="reset" name="cmdBatal" id="cmdBatal" value="Kosongkan"/>
                      <input class="stylobutton" type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="javascript:batalUlasanKJP()" />
#elseif ($mode == 'viewKJP')
<input class="stylobutton" type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="javascript:KemaskiniUlasanKJP()" />
<input class="stylobutton" type="button" name="cmdHapus" id="cmdHapus" value="Hapus" onclick="javascript:HapusKJP()" />
<input class="stylobutton" type="button" name="cmdBatal" id="cmdBatal" value="Kembali" onclick="javascript:batalUlasanKJP()" />
#elseif ($mode == 'updateKJP')
<input class="stylobutton" type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:SimpanUpdateKJP()" />
<input class="stylobutton" type="reset" name="cmdBatal" id="cmdBatal" value="Kosongkan"/>
<input class="stylobutton" type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="javascript:batalUpdateKJP()" />
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
    <legend><strong>SENARAI ULASAN KJP</strong></legend>
    <table align="center" width="100%"> 
            #if ($mode == 'view')
            <tr>
              <td colspan="6" scope="row"><input class="stylobutton" name="cmdDaftar" type="button" value="Daftar Baru" onclick="javascript:daftarBaruKJP()"/></td>
            </tr>
            #end
            <tr class="table_header">
              <td scope="row" width="4%" align="center"><strong>Bil</strong></td>
              <td width="20%"><strong>No. Rujukan KJP</strong></td>
              <td width="15%" align="center"><strong>Tarikh Hantar</strong></td>
              <td width="17%" align="center"><strong>Tarikh Terima</strong></td>
              <td align="center"><strong>Keputusan</strong></td>
              <td align="center">Hapus</td>
            </tr>
          #set ($list = "")
          #if ($SenaraiUlasanKJP.size() > 0)
          #foreach ($list in $SenaraiUlasanKJP)
            #if ($list.bil == '')
                #set( $row = "row1" )
            #elseif (($list.bil % 2) != 0)
                #set( $row = "row1" )
            #else 
                #set( $row = "row2" )
            #end
      <tr>
            <td class="$row" align="center" width="4%">$list.bil</td>
            <td class="$row" width="20%"><a href="javascript:paparKJP('$list.idUlasanKJP')" class="style1">$list.noRujukan</a></td>
            <td class="$row" align="center" width="15%">$list.tarikhHantar</td>
          <td class="$row" align="center" width="17%">$list.tarikhTerima</td>
          <td align="center" class="$row">$list.statusName</td>
          <td align="center" class="$row"><a href="javascript: doDeleteHakmilik('$list.idHakmilikUrusan')"><img src="../img/delete.gif" border="0"></a></td>
          </tr>
          #end
          #else
          <tr>
            <td class="row1">&nbsp;</td>
            <td class="row1">Tiada Rekod</td>
            <td class="row1">&nbsp;</td>
            <td class="row1">&nbsp;</td>
            <td width="29%" class="row1">&nbsp;</td>
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

