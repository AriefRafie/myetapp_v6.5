<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <tr>
    <td><fieldset>
      <legend><b>SENARAI HUTANG</b></legend>
      <table align="center" width="100%">
         <tr>
          <td colspan="5" scope="row">
          #if($!role != "adminppk")
          <input name="cmdDaftar" type="button" value="Tambah" onClick="doDivAjaxCall$formname('divMainForm','daftarHutang','');"/>
          #end
          </td>
        </tr>
        <tr class="table_header">
          <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
          <td width="30%"><strong>Nama / Agensi Pemiutang</strong></td>
          <td><strong>Jenis Hutang</strong></td>
          <td><strong>Nilai Hutang (RM)</strong></td>
          <td><strong>Baki Hutang (RM)</strong></td>
        </tr>
        #set ($list = "")
        #set ( $count = $startNumber )
        #if ($listHutang.size() > 0)
        #foreach ($list in $listHutang)
        #set ( $count = $count + 1 )
        #if ($count == '')
        #set( $row = "row1" )
        #elseif (($count % 2) != 0)
        #set( $row = "row1" )
        #else 
        #set( $row = "row2" )
        #end
        <tr>
          <td class="$row" align="center">$count</td>
          <td class="$row"><a href="javascript:paparHutang('$!list.idSenaraiHutang')" class="style2">$!list.nama</a></td>
          <td class="$row">$!list.jenisHutang</td>
          <td class="$row">$!list.nilaiHutang</td>
          <td class="$row">$!list.bakiHutang $!list.idPejabat</td>
        </tr>
        #end
        #else
        <tr>
          <td class="row1" align="center">&nbsp;</td>
          <td class="row1" colspan="4">Tiada Rekod</td>
        </tr>
        #end
      </table>
      </fieldset></td>
  </tr>
</table>
