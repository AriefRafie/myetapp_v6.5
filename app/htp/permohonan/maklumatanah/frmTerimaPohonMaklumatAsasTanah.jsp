<!-- 
Skrin Parent : frmTerimaPohonMaklumatTABB2.jsp
-->
<style type="text/css">
<!--
.Xstar {	color: #F00;
}
.Xstar .style1 {
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
<fieldset>

	<table width="100%" border="0">
	  <tr>
	    <td colspan="10" >
	    	<input class="stylobutton100" type="button" name="btnTambahAsasTanah" id="btnTambahAsasTanah" value="Tambah" onclick="TambahAsasTanah(0,1,'kemaskinipermohonan','MakAsasTanah')"/>
	  	</td>
	  </tr>
	  <tr class="table_header">
	
	  	<td width="3%">Bil.</td>
	    <!-- <td width="12%">Daerah</td> -->
	    <td width="10%">$!labelDaerah</td>
	    <td width="14%">Bandar/Pekan/Mukim</td>
	    <td width="13%">Kod Lot</td>
	    <td width="11%">No.Syit</td>
	    <td width="8%">No. Pelan</td>
	    <td width="10%">Kod Luas</td>
	    <td width="5%">Luas</td>
	    <td width="19%">Lokasi</td>
	    <td width="10%">Tindakan&nbsp;</td>
	
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
	    <td class="$row">
	    	<a href="javascript:KemaskiniAsasTanah(0,2,'kemaskinipermohonan','MakAsasTanah','$mat.idhakmilikurusan')" class="pautanms">
	    	$mat.nama_daerah</a>
	    </td>
	    <td class="$row">$mat.nama_mukim</td>
	    <td class="$row">
	    	<a href="javascript:LokasiTanah(0,3,'kemaskinipermohonan','LokasiTanah','$mat.idhakmilikurusan')" class="pautanms"></a>
	    $mat.keterangan$mat.nolot
	    </td>
	    <td class="$row">$mat.nosyit</td>
	    <td class="$row">$mat.nopelan</td>
	    <td class="$row">$mat.keteranganluas</td>
	    <td class="$row">$mat.luas</td>
	    <td class="$row">$mat.lokasi</td>
	    <td class="$row">
	    	<a href="javascript:gisWindow('');" class="pautanms"><b>GIS</b></a>|
	    	<a href="javascript:DetailTanah(0,3,'kemaskinipermohonan','MakAsasTanah','DetailLot','$mat.idhakmilikurusan')" class="pautanms"><b>Seterusnya>></b></a>
	    </td>
	  </tr>
	  #end
	  	#if ($cnt == 0)
			<tr> 
				<td colspan="10" scope="row"><font color="#FF0000">Tiada Rekod.</font></td>
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

</fieldset>

<script>
	
	function TambahAsasTanah(){
		alert('TambahAsasTanah');
		//doAjaxCall${formName}("TambahAsasTanah");
	}
	
</script>
