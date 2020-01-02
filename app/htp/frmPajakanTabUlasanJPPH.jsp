<style type="text/css">
<!--
.style1 {color: #0033FF}
-->
</style>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #if ($mode == 'newJPPH' || $mode == 'updateJPPH' || $mode == 'viewJPPH')
  <tr>
    <td>
    
    	<fieldset><legend><strong>ULASAN JPPH</strong></legend>
        
            <table width="100%" border="0" cellspacing="2" cellpadding="2">
            #foreach ($beanUlasanJPPH in $BeanUlasanJPPH)
              <tr>
                <td width="1%">#if ($mode == 'newJPPH' || $mode == 'updateJPPH')<font color="#FF0000">*</font>#end</td>
                <td width="28%">No Ruj. JPPH</td>
                <td width="1%">:</td>
                <td width="70%"><input type="text" name="txtNoRujJPPH" id="txtNoRujJPPH" onBlur="this.value=this.value.toUpperCase();" value="$beanUlasanJPPH.noRujukan" class="$classDis" $readOnly/></td>
              </tr>
              <tr>
                <td>#if ($mode == 'newJPPH' || $mode == 'updateJPPH')<font color="#FF0000">*</font>#end</td>
                <td>Tarikh Hantar</td>
                <td>:</td>
                <td><input type="text" name="txdTarikhHantarJPPH" id="txdTarikhHantarJPPH" size="10" value="$beanUlasanJPPH.tarikhHantar" onblur="check_date(this)" class="$classDis" $readOnly/>
                #if ($mode == 'newJPPH' || $mode == 'updateJPPH')
                <img src="../img/calendar.gif" alt="Calendar" border="0" onclick="displayDatePicker('txdTarikhHantarJPPH',false,'dmy');" />
                #end                </td>
              </tr>
              <tr>
                <td>#if ($mode == 'newJPPH' || $mode == 'updateJPPH')<font color="#FF0000">*</font>#end</td>
                <td>Tarikh Terima</td>
                <td>:</td>
                <td><input type="text" name="txdTarikhTerimaJPPH" id="txdTarikhTerimaJPPH" size="10" value="$beanUlasanJPPH.tarikhTerima" onblur="check_date(this)" class="$classDis" $readOnly/>
                #if ($mode == 'newJPPH' || $mode == 'updateJPPH')
                <img src="../img/calendar.gif" alt="Calendar" border="0" onclick="displayDatePicker('txdTarikhTerimaJPPH',false,'dmy');" />
                #end                </td>
              </tr>
              <tr>
                <td>#if ($mode == 'newJPPH' || $mode == 'updateJPPH')<font color="#FF0000">*</font>#end</td>
                <td>Tahun Nilaian</td>
                <td>:</td>
                <td><input type="text" name="txtTahunNilaian" id="txtTahunNilaian" size="2" value="$beanUlasanJPPH.tahunNilaian" class="$classDis" $readOnly/> Tahun</td>
              </tr>
              <tr>
                <td style="visibility:hidden">#if ($mode == 'newJPPH' || $mode == 'updateJPPH')<font color="#FF0000">*</font>#end</td>
                <td>Nilaian Tanah</td>
                <td>:</td>
                <td>RM 
                  <input name="txtNilaiTanah" type="text" class="$classDis" id="txtNilaiTanah" onblur="validateCurrency(this,this.value,'')" value="$beanUlasanJPPH.nilaian" size="25" maxlength="25" $readOnly /></td>
              </tr>
              <tr style="display:none">
                <td>#if ($mode == 'newJPPH' || $mode == 'updateJPPH')<font color="#FF0000">*</font>#end</td>
                <td>Syor Bayaran</td>
                <td>:</td>
                <td>RM 
                  <input name="txtSyorBayaran" type="text" class="$classDis" id="txtSyorBayaran" onblur="validateCurrency(this,this.value,'')" value="$beanUlasanJPPH.syor" size="25" maxlength="25" $readOnly /></td>
              </tr>
              <tr>
                <td valign="top">&nbsp;</td>
                <td valign="top">Ulasan</td>
                <td valign="top">:</td>
                <td valign="top"><textarea name="txtKeteranganJPPH" id="txtKeteranganJPPH" cols="50" rows="5" onBlur="this.value=this.value.toUpperCase();" class="$classDis" $readOnly>$beanUlasanJPPH.ulasan</textarea></td>
              </tr>
              #end
              <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td colspan="2">
                #if ($mode == 'newJPPH')
                	<input class="stylobutton" type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:SimpanJPPH()" />
                    <input class="stylobutton" type="reset" name="cmdBatal" id="cmdBatal" value="Kosongkan"/>
                    <input class="stylobutton" type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="javascript:batalJPPH()" />
                #elseif ($mode == 'viewJPPH')
                    <input class="stylobutton" type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="javascript:KemaskiniJPPH()" />
                    <input class="stylobutton" type="button" name="cmdHapus" id="cmdHapus" value="Hapus" onclick="javascript:HapusJPPH()" />
                    <input class="stylobutton" type="button" name="cmdBatal" id="cmdBatal" value="Kembali" onclick="javascript:batalJPPH()" />
                    
                #elseif ($mode == 'updateJPPH')
                  <input type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:SimpanUpdateJPPH()" />
                    <input type="reset" name="cmdBatal" id="cmdBatal" value="Kosongkan"/>
                    <input type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="javascript:batalUpdateJPPH()" />
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
    <legend><strong>SENARAI ULASAN</strong></legend>
    
    	<table align="center" width="100%"> 
            #if ($mode == 'view')
            <tr>
              <td colspan="8" scope="row"><input class="stylobutton" name="cmdDaftar" type="button" value="Daftar Baru" onclick="javascript:daftarBaruJPPH()"/></td>
            </tr>
            #end
            <tr class="table_header">
              <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
              <td width="15%"><strong>No. Rujukan</strong></td>
              <td width="15%" align="center"><strong>Tarikh Hantar</strong></td>
              <td width="15%" align="center"><strong>Tarikh Terima</strong></td>
              <td width="10%" align="center"><strong>Tahun Nilaian (Tahun)</strong></td>
              <td colspan="2" align="center"><strong>Nilaian Tanah (RM)</strong></td>
              <td width="20%" align="center" style="display:none"><strong>Syor Bayaran (RM)</strong></td>
        </tr>
          #set ($list = "")
          #if ($SenaraiUlasanJPPH.size() > 0)
          #foreach ($list in $SenaraiUlasanJPPH)
            #if ($list.bil == '')
                #set( $row = "row1" )
            #elseif (($list.bil % 2) != 0)
                #set( $row = "row1" )
            #else 
                #set( $row = "row2" )
            #end
      <tr>
            <td class="$row" align="center">$list.bil</td>
            <td class="$row"><a href="javascript:paparJPPH('$list.idUlasanTeknikal','$list.idUlasanNilai')" class="style1">$list.noRujukan</a></td>
            <td class="$row" align="center">$list.tarikhHantar</td>
          <td class="$row" align="center">$list.tarikhTerima</td>
            <td class="$row" align="center">$list.tahunNilaian</td>
            <td colspan="2" align="right" class="$row">$list.nilaian</td>
            <td class="$row" align="right" style="display:none">$list.syor</td>
      </tr>
          #end
          #else
          <tr>
            <td class="row1">&nbsp;</td>
            <td class="row1">Tiada Rekod</td>
            <td class="row1">&nbsp;</td>
            <td class="row1">&nbsp;</td>
            <td class="row1">&nbsp;</td>
            <td width="20%" class="row1">&nbsp;</td>
            <td width="20%" class="row1">&nbsp;</td>
            <td class="row1" style="
            display:none"></td>
      </tr>
          #end
        </table>
        
    </fieldset>
    
    </td>
  </tr>
</table>
