#foreach($beanMaklumatTanah in $BeanMaklumatTanah)
	#set($idHakmilikAgensi = $beanMaklumatTanah.idHakmilikAgensi)
    #set($idHakmilik = $beanMaklumatTanah.idHakmilik)
    #set($peganganHakmilik = $beanMaklumatTanah.peganganHakmilik)
    #set($jenisLot = $beanMaklumatTanah.jenisLot)
    #set($noLot = $beanMaklumatTanah.noLot)
    #set($luasLot = $beanMaklumatTanah.luasLot)
    #set($luasDiTawarkan = $beanMaklumatTanah.luasDiTawarkan)
    #set($luasTawar = $beanMaklumatTanah.luasTawar)
    #set($jenisHakmilik = $beanMaklumatTanah.jenisHakmilik)
    #set($noHakmilik = $beanMaklumatTanah.noHakmilik)
    #set($noWarta = $beanMaklumatTanah.noWarta)
    #set($tarikhWarta = $beanMaklumatTanah.tarikhWarta)             
    #set($mukim = $beanMaklumatTanah.mukim)
    #set($daerah = $beanMaklumatTanah.daerah)
    #set($negeri = $beanMaklumatTanah.negeri)                       
#end
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td width="50%" valign="top"><table width="100%"  cellpadding="2" cellspacing="2" border="0">
        <tr>
          <td width="40%" align="right">PEGANGAN HAKMILIK :</td>
          <td width="60%">$peganganHakmilik</td>
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
          <td align="right"><strong>LUAS DITAWARKAN :</strong></td>
          <td><strong>$luasTawar <input name="txtLuasTawar" type="hidden" value="$luasDiTawarkan"/></strong></td>
        </tr>
        <tr>
          <td align="right">NO. HAKMILIK :</td>
          <td>$noHakmilik</td>
        </tr>
      </table></td>
    <td width="50%" valign="top"><table width="100%"  cellpadding="2" cellspacing="2" border="0">
        <tr>
          <td width="37%" align="right">NO. WARTA :</td>
          <td width="63%">$noWarta</td>
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
      </table></td>
  </tr>
</table>
