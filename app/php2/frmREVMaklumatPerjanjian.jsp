<fieldset><legend>MAKLUMAT PERJANJIAN</legend>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td>
      <fieldset><legend>UTAMA</legend>
      <table align="center" width="100%">
        <tr>
          <td colspan="8" scope="row"><input name="cmdDaftar" type="button" value="Tambah" onclick="javascript:daftarPerjanjian('$idFail','$idHasil', 'U')"/>
            &nbsp;&nbsp;</td>
        </tr>
        <tr class="table_header">
          <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
          <td width="15%"><strong>No. Siri Perjanjian</strong></td>
          <td width="10%" align="center"><strong>Tarikh Mula</strong></td>
          <td width="10%" align="center"><strong>Tarikh Tamat</strong></td>
          <td width="10%" align="right"><strong>Deposit (RM)</strong></td>
          <td width="10%" align="right"><strong>Sewa (RM)</strong></td>
          <td width="10%" align="center"><strong>Status</strong></td>
          <td width="30%" align="center"><strong>Catatan</strong></td>
        </tr>
        #set ($list = "")
        #if ($SenaraiPerjanjian.size() > 0)
        #foreach ($list in $SenaraiPerjanjian)
        #if ($list.bil == '')
        #set( $row = "row1" )
        #elseif (($list.bil % 2) != 0)
        #set( $row = "row1" )
        #else 
        #set( $row = "row2" )
        #end
        <tr>
          <td class="$row" align="center">$list.bil</td>
          <td class="$row"><a href="javascript:paparPerjanjian('$list.idBayaranPerluDibayar', '$list.idHasil','$idFail', 'U')"><font color="#0000FF">$list.noSiri</font></a></td>
          <td class="$row" align="center">$list.tarikhMula</td>
          <td class="$row" align="center">$list.tarikhTamat</td>
          <td class="$row" align="right">$list.deposit</td>
          <td class="$row" align="right">$list.sewa</td>
          #if ($list.flagAktif == 'Y')
          <td class="$row" align="center"><strong>AKTIF</strong></td>
          #else
          <td class="$row" align="center"><strong>TIDAK AKTIF</strong></td>
          #end 
           <td class="$row" align="center">$list.catatan</td>
        </tr>
          
        #end
        #else
        <tr>
          <td class="row1" align="center">&nbsp;</td>
          <td class="row1">Tiada Rekod</td>
          <td class="row1" align="center">&nbsp;</td>
          <td class="row1" align="center">&nbsp;</td>
          <td class="row1" align="right">&nbsp;</td>
          <td class="row1" align="right">&nbsp;</td>
          <td class="row1" align="right">&nbsp;</td>
          <td class="row1" align="right">&nbsp;</td>
        </tr>
        #end
      </table>
      </fieldset></td>
  </tr>
    <tr>
    <td><fieldset>
      <legend>TAMBAHAN</legend>
      <table align="center" width="100%">
        <tr>
          <td colspan="7" scope="row"><input name="cmdDaftar" type="button" value="Tambah" onclick="javascript:daftarPerjanjian('$idFail','$idHasil', 'T')"/>
            &nbsp;&nbsp;</td>
        </tr>
        <tr class="table_header">
          <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
          <td width="15%"><strong>No. Siri Perjanjian</strong></td>          
          <td width="10%" align="center"><strong>Tarikh Mula Kuatkuasa</strong></td>
          <td width="10%" align="center"><strong>Tarikh Tamat Kuatkuasa</strong></td>
          <td width="10%" align="right"><strong> Sewa (RM)</strong></td>          
          <td width="15%" align="center"><strong>Jenis Perjanjian</strong></td>
          <td width="10%" align="center"><strong>Status</strong></td>
          <td width="25%" align="center"><strong>Catatan</strong></td>
        </tr>
        #set ($list = "")
        #if ($SenaraiPerjanjianTambahan.size() > 0)
        #foreach ($list in $SenaraiPerjanjianTambahan)
        #if ($list.bil == '')
        #set( $row = "row1" )
        #elseif (($list.bil % 2) != 0)
        #set( $row = "row1" )
        #else 
        #set( $row = "row2" )
        #end
        <tr>
          <td class="$row" align="center">$list.bil</td>
          <td class="$row"><a href="javascript:paparPerjanjian('$list.idBayaranPerluDibayar', '$list.idHasil','$idFail', 'T')"><font color="#0000FF">$list.noSiri</font></a></td>          
          <td class="$row" align="center">$list.tarikhMula</td>
          <td class="$row" align="center">$list.tarikhTamat</td>
          <td class="$row" align="right">$list.sewa</td>
          <td class="$row" align="center"><strong>$list.keteranganFlagPerjanjian</strong></td>
          #if ($list.flagAktif == 'Y')
          <td class="$row" align="center"><strong>AKTIF</strong></td>
          #else
          <td class="$row" align="center"><strong>TIDAK AKTIF</strong></td>
          #end
          <td width="8%" align="center" class="$row">$list.catatan</td> 
        </tr>
        #end
        #else
        <tr>
          <td class="row1" align="center">&nbsp;</td>
          <td class="row1">Tiada Rekod</td>
          <td colspan="6" align="center" class="row1">&nbsp;</td>
        </tr>
        #end
      </table>
      </fieldset>
      </td>
  </tr>
  </table>
  </fieldset>
