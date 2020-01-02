<fieldset>
<legend>MAKLUMAT BANGUNAN</legend>

<table width="100%">
	<tr>
	  <td colspan="2">
	    <input type="button" name="cmdSimpan" id="cmdSimpan" value="Tambah" onclick="doAjaxCall${formName}('tambahBangunan')"></td>
	</tr>
	
	
	<tr class="table_header">
		  <td width="3%" scope="col">Bil.</td>
					<td scope="col">Jenis dan No. Hakmilik</td>
					<td scope="col">No Lot/PT</td>		
					<td scope="col">No Strata</td>
					<td scope="col"></td>
		</tr>
    #set ( $cnt = 0 )
    #foreach($mo in $buildings)
    #set ( $cnt = $cnt + 1 )
    #if ($senarai.bil == '')
    #set( $row = "row1" )
    #elseif (($senarai.bil % 2) != 0)
    #set( $row = "row1" )
    #else 
    #set( $row = "row2" )
    #end
		<tr >
		  <td class="$row">$cnt.</td>
		  <td class="$row">$!mo.hakmilikUrusan.keterangan$!mo.hakmilikUrusan.nohakmilik</td>
		  <td class="$row"><a href="javascript:paparBangunan('$!mo.idBangunan')" >$!mo.hakmilikUrusan.getKeteranganLot()$!mo.hakmilikUrusan.getNolot()</a></td>
		  <td scope="col">$!mo.noBangunan$!mo.noTingkat$!mo.noPetak</td>
		  <td class="$row">
		  	<input class="stylobutton" type="button" value="Hapus" onclick="javascript:deleteBangunan('$!mo.idBangunan')">
		  </td>
    </tr>
      #end
	
</table>
</fieldset>