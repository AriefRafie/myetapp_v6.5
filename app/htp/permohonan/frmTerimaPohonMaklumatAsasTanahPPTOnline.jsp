<style type="text/css">
<!--
.star {	color: #F00;
}
.star .style1 {
	color: #00F;
	font-weight: bold;
}
-->
</style>
  <style type="text/css">
  <!--
  .pautanms {color: #0033FF}
  -->
  </style>
<table width="98%" border="0">
  <tr bgcolor="#996600">
    <td colspan="10" bgcolor="#FFFFFF">
  						<input class="stylobutton" type="button" name="cmdSimpan" id="cmdSimpan" value="Tambah" onclick="viewBorangK()"> 				 			
  	</td>
  </tr>
  <tr bgcolor="#996600">

  	<td width="3%">Bil.</td>
    <td width="12%">Daerah</td>
    <td width="14%">Bandar/Pekan/Mukim</td>
    <td width="13%">Kod Lot</td>
    <td width="11%">No.Syit</td>
    <td width="8%">No. Pelan</td>
    <td width="10%">Kod Luas</td>
    <td width="5%">Luas</td>
    <td width="16%">Lokasi</td>
    <td width="8%">&nbsp;</td>

  </tr>
  #set ( $cnt = 0 )			
  #foreach ($mat in $MAT)
  	#set ( $cnt = $cnt + 1 )
                #set( $i = $velocityCount )
                #if ( ($i % 2) == 0 )
                    #set( $row = "row2" )
                #else
                    #set( $row = "row1" )
                #end
  <tr>
	<td class="$row"><a href="javascript:KemaskiniAsasTanah(0,2,'kemaskinipermohonan','MakAsasTanah','$mat.idhakmilikurusan')" class="">$cnt.</a></td>
    <td class="$row"><a href="javascript:KemaskiniAsasTanah(0,2,'kemaskinipermohonan','MakAsasTanah','$mat.idhakmilikurusan')" class="pautanms" style="display:none"></a>$mat.nama_daerah</td>
    <td class="$row">$mat.nama_mukim</td>
    <td class="$row">
    <!--<a href="javascript:LokasiTanah(0,3,'kemaskinipermohonan','LokasiTanah','$mat.idhakmilikurusan')" class="style1">-->
    $mat.keterangan$mat.nolot</span></td>
    <td class="$row">$mat.nosyit</td>
    <td class="$row">$mat.nopelan</td>
    <td class="$row">$mat.keteranganluas</td>
    <td class="$row">$mat.luas</td>
    <td class="$row">$mat.lokasi</td>
    <td class="$row"><span class="star"><a href="javascript:DetailTanah(0,3,'kemaskinipermohonan','MakAsasTanah','DetailLot','$mat.idhakmilikurusan')" class="style1">Seterusnya>></a></span></td>
  </tr>
  #end
  
  <tr>
    <td colspan="2">&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
</table>
<p id="Submit">&nbsp;</p>

<script>
	
function TambahAsasTanah(){
	doAjaxCall${formName}("TambahAsasTanah");
}
	
</script>