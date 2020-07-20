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
          <td>$beanMaklumatTanah.noLot</td>
        </tr>
        <tr>
          <td align="right">LUAS. LOT :</td>
          <td>$beanMaklumatTanah.luasBersamaan</td>
        </tr>
        <tr>
          <td align="right">NO. HAKMILIK :</td>
          <td>$beanMaklumatTanah.noHakmilik</td>
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
#end
