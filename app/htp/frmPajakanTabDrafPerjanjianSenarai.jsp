<style type="text/css">
<!--
.style1 {color: #0033FF}
.row1 {
	color: #F00;
}
.q1 {
	text-align: center;
}
.e {
	color: #F00;
}
-->
</style>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  #if ($mode == 'newDraf' || $mode == 'updateDraf' || $mode == 'viewDraf')
  <tr>
    <td>
    
    	<fieldset><legend><strong>DRAF PERJANJIAN</strong></legend>
        
            <table width="100%" border="0" cellspacing="2" cellpadding="2">
            #foreach ($beanDraf in $BeanDraf)
              <tr>
                <td width="1%" class="e">*</td>
                <td width="28%">Tarikh Hantar Draf Ke Pemohon</td>
                <td width="1%">:</td>
                <td width="70%"><input type="text" name="txdTarikhHantarDraf" id="txdTarikhHantarDraf" size="10" value="$beanDraf.tarikhHantar" onblur="check_date(this)" class="$classDis" $readOnly/>
                #if ($mode == 'newDraf' || $mode == 'updateDraf')
                <img src="../img/calendar.gif" alt="Calendar" border="0" onclick="displayDatePicker('txdTarikhHantarDraf',false,'dmy');" />
                #end                
                </td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>Tarikh Terima Draf Dari Pemohon</td>
                <td>:</td>
                <td><input type="text" name="txdTarikhTerimaDraf" id="txdTarikhTerimaDraf" size="10" value="$beanDraf.tarikhTerima" onblur="check_date(this)" class="$classDis" $readOnly/>
                #if ($mode == 'newDraf' || $mode == 'updateDraf')
                <img src="../img/calendar.gif" alt="Calendar" border="0" onclick="displayDatePicker('txdTarikhTerimaDraf',false,'dmy');" />
                #end                </td>
              </tr>
              <tr>
                <td width="1%">&nbsp;</td>
                <td width="28%">Tarikh Hantar Ke PKP</td>
                <td width="1%">:</td>
                <td width="70%">                <strong>
                  <input type="text" name="txdTarikhHantarPKP" id="txdTarikhHantarPKP" size="10" value="$beanDraf.tarikhHantarPKP" onblur="check_date(this)" class="$classDis" $readonly/>
                </strong>#if ($mode == 'newDraf' || $mode == 'updateDraf') <img src="../img/calendar.gif" alt="Calendar" border="0" onclick="displayDatePicker('txdTarikhHantarPKP',false,'dmy');" />
                #end                
                </td>
              </tr>
              <tr>
                <td>&nbsp;</td>
                <td>Tarikh Terima Dari PKP</td>
                <td>:</td>
                <td><input type="text" name="txdTarikhTerimaPKP" id="txdTarikhTerimaPKP" size="10" value="$beanDraf.tarikhTerimaPKP" onblur="check_date(this)" class="$classDis" $readOnly/>
                #if ($mode == 'newDraf' || $mode == 'updateDraf')
                <img src="../img/calendar.gif" alt="Calendar" border="0" onclick="displayDatePicker('txdTarikhTerimaPKP',false,'dmy');" />
                #end                </td>
              </tr>
              <tr>
                <td valign="top">&nbsp;</td>
                <td valign="top">Ulasan PKP</td>
                <td valign="top">:</td>
                <td valign="top"><textarea name="txtKeteranganDraf" id="txtKeteranganDraf" cols="50" rows="5" onBlur="this.value=this.value.toUpperCase();" class="$classDis" $readOnly>$beanDraf.ulasan</textarea></td>
              </tr>
              #end
              <tr style="display:none">
                <td>&nbsp;</td>
                <td>Lampiran Perjanjian</td>
                <td>:</td>
                <td><input id="fileupload" name="fileupload" type="file" size="40" /></td>
              </tr>
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
                #if ($mode == 'newDraf')
                	<input class="stylobutton" type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:SimpanDraf()" />
                    <input class="stylobutton" type="reset" name="cmdBatal" id="cmdBatal" value="Kosongkan"/>
                    <input class="stylobutton" type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="javascript:batalDraf()" />
                #elseif ($mode == 'viewDraf')
                    <input class="stylobutton" type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="javascript:KemaskiniDraf()" />
                    <input class="stylobutton" type="button" name="cmdHapus" id="cmdHapus" value="Hapus" onclick="javascript:HapusDraf()" />
                    <input class="stylobutton" type="button" name="cmdBatal" id="cmdBatal" value="Kembali" onclick="javascript:batalDraf()" />
                    
                #elseif ($mode == 'updateDraf')
                    <input class="stylobutton" type="button" name="cmdSimpan" id="cmdSimpan" value="Simpan" onclick="javascript:SimpanUpdateDraf()" />
                    <input class="stylobutton" type="reset" name="cmdBatal" id="cmdBatal" value="Kosongkan"/>
                    <input class="stylobutton" type="button" name="cmdBatal" id="cmdBatal" value="Batal" onclick="javascript:batalUpdateDraf()" />
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
    <legend><strong>SENARAI DRAF</strong></legend>
    
    	<table align="center" width="100%"> 
            #if ($mode == 'view')
            <tr>
              <td colspan="7" scope="row"><input class="stylobutton" name="cmdDaftar" type="button" value="Daftar Baru" onclick="javascript:daftarBaruDraf()"/></td>
            </tr>
            #end
            <tr class="table_header">
              <td scope="row" width="4%" align="center"><strong>Bil</strong></td>
              <td width="20%"><strong>Tarikh Terima Draf
                
              </strong></td>
              <td width="18%" align="center"><strong>Tarikh HantarDraf</strong></td>
              <td width="22%" align="center"><strong>Tarikh Hantar PKP</strong></td>
              <td width="32%" align="center"><strong>Tarikh Terima PKP</strong></td>
              <td width="4%" class="q1" ><strong>Hapus</strong></td>
        </tr>
          #set ($list = "")
          #if ($SenaraiDraf.size() > 0)
          #foreach ($list in $SenaraiDraf)
            #if ($list.bil == '')
                #set( $row = "row1" )
            #elseif (($list.bil % 2) != 0)
                #set( $row = "row1" )
            #else 
                #set( $row = "row2" )
            #end
      <tr>
            <td class="$row" align="center">$list.bil</td>
            <td class="$row"><a href="javascript:paparDraf('$list.idDraf')" class="style1">$list.tarikhTerima</a></td>
            <td class="$row" align="center"><a href="javascript:paparDraf('$list.idDraf')" class="style1">$list.tarikhHantar</a></td>
            <td class="$row" align="center">$list.tarikhHantarPKP</td>
            <td class="$row" align="center">$list.tarikhTerimaPKP</td>
          	<td class="q1" ><a href="javascript: HapusDraf('$list.idDraf')"><img src="../img/delete.gif" border="0" align="middle"></td><input type="hidden" value="$list.ulasan"/>
      </tr>
          #end
          #else
          <tr>
            <td class="row1">&nbsp;</td>
            <td class="row1">Tiada Rekod</td>
            <td class="row1">&nbsp;</td>
            <td class="row1">&nbsp;</td>
            <td class="row1">&nbsp;</td>
            <td class="q1">&nbsp;</td>
      </tr>
          #end
        </table>
        
    </fieldset>
    
    </td>
  </tr>
</table>
