#foreach($beanMaklumatTanah in $BeanMaklumatTanah)
            

<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td width="50%" valign="top"><table width="100%"  cellpadding="2" cellspacing="2" border="0">
        <tr>
          <td width="37%" align="right">PEGANGAN HAKMILIK :</td>
          <td width="63%">$beanMaklumatTanah.peganganHakmilik</td>
        </tr>
        <tr>
          <td align="right">NO. LOT :</td>
          <td>$beanMaklumatTanah.lot</td>
        </tr>
        <tr>
          <td align="right">LUAS. LOT :</td>
          <td>$beanMaklumatTanah.luas</td>
        </tr>
        <tr>
          <td align="right">NO. HAKMILIK :</td>
          <td>$beanMaklumatTanah.hakmilik</td>
        </tr>
        <tr>
          <td align="right">NO. WARTA :</td>
          <td>$beanMaklumatTanah.noWarta</td>
        </tr>
        <tr>
          <td align="right">TARIKH WARTA :</td>
          <td>$beanMaklumatTanah.tarikhWarta</td>
        </tr>
        <tr>
          <td align="right">MUKIM :</td>
          <td>$beanMaklumatTanah.mukim</td>
        </tr>
        <tr>
          <td align="right">DAERAH :</td>
          <td>$beanMaklumatTanah.daerah</td>
        </tr>
        <tr>
          <td align="right">NEGERI :</td>
          <td>$beanMaklumatTanah.negeri</td>
        </tr>
        <tr>
          <td align="right">KATEGORI TANAH :</td>
          <td>$beanMaklumatTanah.kategoriTanah</td>
        </tr>
        <tr>
          <td align="right">SUBKATEGORI TANAH :</td>
          <td>$beanMaklumatTanah.subKategoriTanah</td>
        </tr>
      </table></td>
      
      
    <td width="50%" valign="top"><table width="100%"  cellpadding="2" cellspacing="2" border="0">
        <tr>
          <td width="36%" align="right" valign="top">SYARAT NYATA</td>
          <td width="1%" valign="top">:</td>
          <td width="63%" rowspan="3" valign="top">$beanMaklumatTanah.syarat</td>
        </tr>
        <tr>
          <td align="right">&nbsp;</td>
          <td width="1%">&nbsp;</td>
        </tr>
        <tr>
          <td align="right">&nbsp;</td>
          <td width="1%">&nbsp;</td>
        </tr>
        <tr>
          <td align="right" valign="top">SEKATAN KEPENTINGAN</td>
          <td width="1%" valign="top">:</td>
          <td rowspan="3" valign="top">$beanMaklumatTanah.sekatan</td>
        </tr>
        <tr>
          <td align="right">&nbsp;</td>
          <td width="1%">&nbsp;</td>
        </tr>
        <tr>
          <td align="right">&nbsp;</td>
          <td width="1%">&nbsp;</td>
        </tr>
        <tr>
          <td align="right" valign="top">KEGUNAAN TANAH</td>
          <td width="1%" valign="top">:</td>
          <td rowspan="3" valign="top">$beanMaklumatTanah.kegunaanTanah</td>
        </tr>
        <tr>
          <td align="right">&nbsp;</td>
          <td width="1%">&nbsp;</td>
        </tr>
        <tr>
          <td align="right">&nbsp;</td>
          <td width="1%">&nbsp;</td>
        </tr>
        <tr>
          <td align="right" valign="top">KEMENTERIAN</td>
          <td width="1%" valign="top">:</td>
          <td valign="top">$beanMaklumatTanah.kementerian</td>
        </tr>
        <tr>
          <td align="right" valign="top">AGENSI</td>
          <td width="1%" valign="top">:</td>
          <td valign="top">$beanMaklumatTanah.agensi</td>
        </tr>
      </table></td>
  </tr>
</table>

<table width="100%" border="0" cellspacing="2" cellpadding="2">
              <tr>
                <td><fieldset>
                  <legend><b>SENARAI TANAH</b></legend>
                  <table align="center" width="100%">
                    <tr>
                      <td colspan="8" scope="row"><input name="cmdDaftar" type="button" value="Daftar Hakmilik" onClick="javascript:doDaftarHakmilik('$idPermohonan','$idKategoriPemohon','$idNegeriPemohon','$idKementerianPemohon')"/>
                        <input name="cmdDaftar" type="button" value="Daftar Borang K" onClick="javascript:doDaftarBorangK('$idPermohonan','$idKategoriPemohon','$idNegeriPemohon','$idKementerianPemohon')"/></td>
                    </tr>
                    <tr class="table_header">
                      <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
                      <td width="15%"><strong>Pegangan Hakmilik</strong></td>
                      <td width="10%"><strong>Lot</strong></td>
                      <td width="10%"><strong>No. Hakmilik</strong></td>
                      <td width="10%"><strong>No. Warta</strong></td>
                      <td width="15%"><strong>Mukim</strong></td>
                      <td width="15%"><strong>Daerah</strong></td>
                      <td width="15%"><strong>Negeri</strong></td>
                     ## <td width="5%"><strong></strong></td>
                    </tr>
                    ##set ($beanMaklumatTanah = "")
                    #if ($beanMaklumatTanah.size() > 0)
                    #foreach ($beanMaklumatTanah in $SenaraiTanahBerkaitan)
                    #if ($beanMaklumatTanah.bil == '')
                    #set( $row = "row1" )
                    #elseif (($beanMaklumatTanah.bil % 2) != 0)
                    #set( $row = "row1" )
                    #else 
                    #set( $row = "row2" )
                    #end                    
                    
##                    #set ($beanMaklumatTanah = "")
##                    #if ($beanMaklumatTanah.size() > 0)
##                   #foreach ($beanMaklumatTanah in $BeanMaklumatTanah)
##                    #if ($beanMaklumatTanah.bil == '')
##                    #set( $row = "row1" )
##                    #elseif (($beanMaklumatTanah.bil % 2) != 0)
##                    #set( $row = "row1" )
##                    #else 
##                    #set( $row = "row2" )
##                   #end
                    <tr>
                      <td class="$row" align="center">$beanMaklumatTanah.bil</td>
                      <td class="$row">$beanMaklumatTanah.peganganHakmilik</td>
                      <td class="$row">$beanMaklumatTanah.noLot</td>
                      <td class="$row">$beanMaklumatTanah.noHakmilik</td>
                      <td class="$row">$beanMaklumatTanah.noWarta</td>
                      <td class="$row">$beanMaklumatTanah.mukim</td>
                      <td class="$row">$beanMaklumatTanah.daerah</td>
                      <td class="$row">$beanMaklumatTanah.negeri</td>
##                      <td class="$row" align="center"><a href="#" class="style2" onClick="javascript:doHapus('$beanMaklumatTanah.idHakmilikPermohonan')"><img border="0" src="../img/hapus.gif"/></a></td>
                    </tr>
                    #end
                    #else
                    <tr>
                      <td class="row1" align="center">&nbsp;</td>
                      <td class="row1">Tiada Rekod</td>
                      <td class="row1">&nbsp;</td>
                      <td class="row1">&nbsp;</td>
                      <td class="row1">&nbsp;</td>
                      <td class="row1">&nbsp;</td>
                      <td class="row1">&nbsp;</td>
                      <td class="row1">&nbsp;</td>
                    ##  <td class="row1">&nbsp;</td>
                    </tr>
                    #end
                    <tr>
                      <td colspan="8">&nbsp;</td>
                    </tr>
                    ##if ($flagPopup == '')
                    ##<tr>
                    ##  <td colspan="8" align="center">
                    ##    #if($idStatus == '1610198')
                    ##    <input type="button" name="cmdSeterusnya" id="cmdHantar" value="Seterusnya" onClick="doSeterusnya()"/>
                    ##    <input type="button" name="cmdBatalPermohonan" id="cmdBatalPermohonan" value="Batal Permohonan" onClick="gotoBatalPermohonan()"/>
                    ##    #end
                    ##    <input type="button" name="cdmCetak" id="cdmCetak" value="Cetak" onClick="javascript:setTable('tableReport')"/>
                    ##    </td>
                    ##</tr>
                    ##end
                  </table>
                  </fieldset></td>
              </tr>
            </table>
#end
