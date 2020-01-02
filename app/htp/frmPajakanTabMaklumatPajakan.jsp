<style type="text/css">
<!--
.style1 {color: #0033FF}
.row1 {
	color: #F00;
}
.e {
	color: #F00;
}
-->
</style>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #if ($mode == 'newPajakan' || $mode == 'updatePajakan' || $mode == 'viewPajakan')
  <tr>
    <td>
    
    	<fieldset>
    	<legend><strong>MAKLUMAT PAJAKAN</strong></legend>
        
            <table width="100%" border="0" cellspacing="2" cellpadding="2">
            #foreach ($beanPajakan in $BeanPajakan)
              <tr>
                <td width="1%" class="e">*</td>
                <td width="28%">Tarikh Tandatangan</td>
                <td width="1%">:</td>
                <td width="70%"><input type="text" name="txdTarikhTandatangan" id="txdTarikhTandatangan" size="10" value="$beanPajakan.tarikhTandatangan" onblur="check_date(this)" class="$classDis" $readOnly/>
                #if ($mode == 'newPajakan' || $mode == 'updatePajakan')
                <img src="../img/calendar.gif" alt="Calendar" border="0" onclick="displayDatePicker('txdTarikhTandatangan',false,'dmy');" />
                #end                
                </td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>Tarikh Mula Pajakan</td>
                <td>:</td>
                <td><input type="text" name="txdTarikhMulaPajakan" id="txdTarikhMulaPajakan" size="10" value="$beanPajakan.tarikhMula" onblur="check_date(this);cal_tarikh_luput()" class="$classDis" $readOnly/>
                #if ($mode == 'newPajakan' || $mode == 'updatePajakan')
                <img src="../img/calendar.gif" alt="Calendar" border="0" onclick="displayDatePicker('txdTarikhMulaPajakan',false,'dmy');" />
                #end                </td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td valign="top">Tempoh Pajakan</td>
                <td valign="top">:</td>
                <td valign="top"><input type="text" name="txtTempoh" id="txtTempoh" size="2" value="$beanPajakan.tempoh" class="$classDis" $readOnly onblur="cal_tarikh_luput()"/>
                  Tahun </td>
              </tr>
              <tr>
                <td valign="top">&nbsp;</td>
                <td>Tarikh Tamat Pajakan</td>
                <td>:</td>
                <td><input type="text" name="txdTarikhTamatPajakan" id="txdTarikhTamatPajakan" size="10" value="$beanPajakan.tarikhTamat" onblur="check_date(this)" class="$classDis" $readOnly/>
                  #if ($mode == 'newPajakan' || $mode == 'updatePajakan') <img src="../img/calendar.gif" alt="Calendar" border="0" onclick="displayDatePicker('txdTarikhTamatPajakan',false,'dmy');" /> #end </td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>Kadar Pajakan Setahun</td>
                <td>:</td>
                <td>RM
                  <input name="txtKadarPajakan" type="text" class="$classDis" id="txtKadarPajakan" value="$beanPajakan.kadarPajakan" size="10" $readOnly onblur="validateCurrency(this,this.value,'')"   /></td>
                <td>&nbsp;</td>
              </tr>
              <tr>
        <td>&nbsp;</td>
        <td>Kadar Cukai Setahun</td>
        <td>:</td>
        <td>RM 
          <input name="txtKadarCukai" type="text" class="$classDis" id="txtKadarCukai" value="$beanPajakan.kadar" size="10" $readOnly onblur="validateCurrency(this,this.value,'')"   /></td>
        <td>&nbsp;</td>
        </tr>
          <tr>
            <td>&nbsp;</td>
            <td>Cara Bayaran</td>
            <td>:</td>
            <td>$selectCaraBayar</td>
            <td>&nbsp;</td>
            </tr>
              
              <tr>
                <td>&nbsp;</td>
                <td>Kategori Cukai</td>
                <td>:</td>
                <td><select name="socKategoriCukai" id="socKategoriCukai" class="$classDis" $classDis>
                  
                  
                  #if($beanPajakan.katCukai == 'PT' || $beanPajakan.katCukai == "" )
						    
                  <!-- PT refers to PTP -->
                  <option value="PT" selected="selected">PTP</option>
                  <option value="P">PEMAJAK</option>
                  

                  #else
  
                  
                  <option value="PT" >PTP</option>
                <option value="P" selected="selected" >PEMAJAK</option>
                  

                  #end
        
                
                </select></td>
              </tr>
              #end
              <tr>
                <td>&nbsp;</td>
                <td valign="top">&nbsp;</td>
                <td valign="top">&nbsp;</td>
                <td valign="top">&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td colspan="2">
                #if ($mode == 'newPajakan')
                	<input class="stylobutton" type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:SimpanPajakan()" />
                    <input class="stylobutton" type="reset" name="cmdBatal" id="cmdBatal" value="Kosongkan"/>
                    <input class="stylobutton" type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="javascript:batalPajakan()" />
                #elseif ($mode == 'viewPajakan')
                    <input class="stylobutton" type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="javascript:KemaskiniPajakan()" />
                    <input class="stylobutton" type="button" name="cmdHapus" id="cmdHapus" value="Hapus" onclick="javascript:HapusPajakan()" />
                    <input class="stylobutton" type="button" name="cmdBatal" id="cmdBatal" value="Kembali" onclick="javascript:batalPajakan()" />
                    
                #elseif ($mode == 'updatePajakan')
                  <input class="stylobutton" type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:SimpanUpdatePajakan()" />
                    <input class="stylobutton" type="reset" name="cmdBatal" id="cmdBatal" value="Kosongkan"/>
                  <input class="stylobutton" type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="javascript:batalUpdatePajakan()" />
                #end 
                </td>
              </tr>
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
    <legend><strong>SENARAI PAJAKAN</strong></legend>
    
    <table align="center" width="100%"> 
            #if ($mode == 'view')
            <tr>
              <td colspan="8" scope="row"><input class="stylobutton" name="cmdDaftar" type="button" value="Daftar Baru" onclick="javascript:daftarBaruPajakan()"/></td>
            </tr>
            #end
            <tr class="table_header">
              <td scope="row" width="4%" align="center"><strong>Bil</strong></td>
              <td width="16%"><strong>Tarikh Tandatangan</strong></td>
              <td width="10%" align="center"><strong>Tarikh Mula</strong></td>
              <td width="11%" align="center"><strong>Tarikh Tamat</strong></td>              
              <td width="12%" ><strong>Tempoh Cukai</strong></td>
              <td width="18%" ><strong>Cara Bayaran</strong></td>
              <td width="16%" align="center"><strong>Kadar Pajakan Setahun (RM)</strong></td>
              <td width="13%" align="center"><strong>Kadar Cukai Setahun (RM)</strong></td>
        </tr>
          #set ($list = "")
          #if ($SenaraiPajakan.size() > 0)
          #foreach ($list in $SenaraiPajakan)
            #if ($list.bil == '')
                #set( $row = "row1" )
            #elseif (($list.bil % 2) != 0)
                #set( $row = "row1" )
            #else 
                #set( $row = "row2" )
            #end
      <tr>
            <td class="$row" align="center">$list.bil</td>
            <td class="$row"><a href="javascript:paparPajakan('$list.idPajakan')" class="style1">$list.tarikhTandatangan</a></td>
            <td class="$row" align="center">$list.tarikhMula</td>
            <td class="$row" align="center">$list.tarikhTamat</td>
          	<td class="$row" >$list.tempoh</td>
            <td class="$row" >$list.caraBayar</td>
            <td class="$row" align="right">$list.kadarPajakan</td>
            <td class="$row" align="right">$list.kadar</td>
      </tr>
          #end
          #else
          <tr>
            <td class="row1">&nbsp;</td>
            <td class="row1">Tiada Rekod</td>
            <td class="row1">&nbsp;</td>
            <td class="row1">&nbsp;</td>
            <td class="row1">&nbsp;</td>
            <td class="row1">&nbsp;</td>
            <td class="row1">&nbsp;</td>
            <td class="row1">&nbsp;</td>
      </tr>
          #end
        </table>
        
    </fieldset>
    
    
    </td>
  </tr>
</table>
