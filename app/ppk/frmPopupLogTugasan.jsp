<!--  arief add for Status / Rekod pergerakan fail 6/4/2020  -->

<style type="text/css">
<!--
.style1 {
	color: #0000FF
}
#parse("css/eTapp_PPK.css")
-->
</style>
<table width="100%" border="0" cellspacing="2" cellpadding="2">
  <!--  <tr>
    <td><fieldset>
      <legend><strong>TUGASAN SEMASA</strong></legend>
      <table width="100%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="30%"><strong>Tugasan Dari</strong></td>
          <td width="70%">:<strong> $!tugasanSemasa.tugasanDari</strong></td>
        </tr>
        <tr>
          <td><strong>Tindakan Pegawai</strong></td>
          <td><strong>: $!tugasanSemasa.pegawaiTindakan - $!tugasanSemasa.rolePegawaiTindakan</strong></td>
        </tr>
        <tr>
          <td><strong>Tarikh Ditugaskan</strong></td>
          <td><strong>: $!tugasanSemasa.tarikhTugasan</strong></td>
        </tr>
        <tr>
          <td><strong>Catatan</strong></td>
          <td><strong>: $!tugasanSemasa.catatan</strong></td>
        </tr>
        <tr>
          <td><strong>Tarikh Hantar Fail Ke Pegawai</strong></td>
          <td><strong>: $!tarikhHantarFailKePegawai</strong></td>
        </tr>
      </table>
      </fieldset></td>
  </tr>-->
  <tr>
    <td><fieldset>
      <legend><b>SENARAI LOG TUGASAN</b></legend>
      <table align="center" width="100%">
        <tr class="table_header" align="center">
          <td scope="row" width="5%" align="center"><strong>Bil</strong></td>
          <td ><strong>No. Fail</strong></td>
          <td ><strong>Tindakan Pegawai</strong></td>
          <td ><strong>Tarikh Ditugaskan</strong></td>
        </tr>
        #set ($list = "")
        #set ($counter = 0)
        #if ($listLogTugasan.size() > 0)
        #foreach ($list in $listLogTugasan)
        #set ($counter = $counter+1)
        #if ($counter == '')
        #set( $row = "row1" )
        #elseif (($counter % 2) != 0)
        #set( $row = "row1" )
        #else 
        #set( $row = "row2" )
        #end
        <tr>
          <td class="$row" align="center">$counter</td>
          <td class="$row">$list.noFail</td>
          <td class="$row">$list.pegawaiTindakan - $list.rolePegawaiTindakan</td>
          <td class="$row" align="center">$list.tarikhTugasan</td>
        </tr>
        #end
        #else
        <tr>
          <td class="row1" align="center">&nbsp;</td>
          <td class="row1">Tiada Rekod</td>
          <td class="row1">&nbsp;</td>
          <td class="row1">&nbsp;</td>
        </tr>
        #end
      </table>
      </fieldset></td>
  </tr>
</table>
