#foreach($beanMaklumatTanah in $BeanMaklumatTanah)
	#set($idHakmilikAgensi = $beanMaklumatTanah.idHakmilikAgensi)
    #set($idHakmilik = $beanMaklumatTanah.idHakmilik)
    #set($idTanahGanti = $beanMaklumatTanah.idTanahGanti)
    #set($peganganHakmilik = $beanMaklumatTanah.peganganHakmilik)
    #set($jenisLot = $beanMaklumatTanah.jenisLot)
    #set($noLot = $beanMaklumatTanah.noLot)
    #set($luasLot = $beanMaklumatTanah.luasLot)
    #set($jenisHakmilik = $beanMaklumatTanah.jenisHakmilik)
    #set($noHakmilik = $beanMaklumatTanah.noHakmilik)
    #set($noWarta = $beanMaklumatTanah.noWarta)
    #set($tarikhWarta = $beanMaklumatTanah.tarikhWarta)             
    #set($mukim = $beanMaklumatTanah.mukim)
    #set($daerah = $beanMaklumatTanah.daerah)
    #set($negeri = $beanMaklumatTanah.negeri)            
    #set($kategoriTanah = $beanMaklumatTanah.kategoriTanah)
    #set($subKategoriTanah = $beanMaklumatTanah.subKategoriTanah)
    #set($syarat = $beanMaklumatTanah.syarat)
    #set($sekatan = $beanMaklumatTanah.sekatan)
    #set($kementerian = $beanMaklumatTanah.kementerian)
    #set($agensi = $beanMaklumatTanah.agensi) 
    #set($kegunaanTanah = $beanMaklumatTanah.kegunaanTanah)           
#end
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td width="50%" valign="top"><table width="100%"  cellpadding="2" cellspacing="2" border="0">
        <tr>
          <td width="37%" align="right">PEGANGAN HAKMILIK :</td>
          <td width="63%">$peganganHakmilik</td>
        </tr>
        <tr>
          <td align="right">NO. LOT :</td>
          <td>$noLot</td>
        </tr>
        <tr>
          <td align="right">LUAS. LOT :</td>
          <td>$luasLot</td>
        </tr>
        <tr>
          <td align="right">NO. HAKMILIK :</td>
          <td>$noHakmilik</td>
        </tr>
        <tr>
          <td align="right">NO. WARTA :</td>
          <td>$noWarta</td>
        </tr>
        <tr>
          <td align="right">TARIKH WARTA :</td>
          <td>$tarikhWarta</td>
        </tr>
        <tr>
          <td align="right">MUKIM :</td>
          <td>$mukim</td>
        </tr>
        <tr>
          <td align="right">DAERAH :</td>
          <td>$daerah</td>
        </tr>
        <tr>
          <td align="right">NEGERI :</td>
          <td>$negeri</td>
        </tr>
        <tr>
          <td align="right">KATEGORI TANAH :</td>
          <td>$kategoriTanah</td>
        </tr>
        <tr>
          <td align="right">SUBKATEGORI TANAH :</td>
          <td>$subKategoriTanah</td>
        </tr>
      </table></td>
    <td width="50%" valign="top"><table width="100%"  cellpadding="2" cellspacing="2" border="0">
        <tr>
          <td width="36%" align="right" valign="top">SYARAT NYATA</td>
          <td width="1%" valign="top">:</td>
          <td width="63%" rowspan="3" valign="top">$syarat</td>
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
          <td rowspan="3" valign="top">$sekatan</td>
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
          <td rowspan="3" valign="top">$kegunaanTanah</td>
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
          <td valign="top">$kementerian</td>
        </tr>
        <tr>
          <td align="right" valign="top">AGENSI</td>
          <td width="1%" valign="top">:</td>
          <td valign="top">$agensi</td>
        </tr>
      </table></td>
  </tr>
</table>
