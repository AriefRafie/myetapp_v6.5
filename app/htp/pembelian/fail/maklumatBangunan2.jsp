<fieldset>
<legend>MAKLUMAT BANGUNAN</legend>

<table width="100%">
	<tr>
	  <td colspan="2">
	    <input type="button" name="cmdSimpan" id="cmdSimpan" value="Tambah" onclick="doAjaxCall${formName}('tambahBangunan')" style="display:none"></td>
	</tr>
	
	
	<tr class="table_header">
		  <td scope="col">Bil.</td>
					<td scope="col">Jenis Hakmilik</td>
					<td scope="col">No. Hakmilik</td>
                  	<td scope="col">Kod Lot</td>
					<td scope="col">No Lot</td>
					
					<td scope="col">No Petak</td>
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
		  <td class="$row">$!mo.hakmilikUrusan.keterangan</td>
		  <td class="$row"><a href="javascript:paparBangunan('$!mo.idBangunan')" >$!mo.hakmilikUrusan.nohakmilik</a></td>
		  <td class="$row">$!mo.hakmilikUrusan.getKeteranganLot()</td>
		  <td class="$row">$!mo.hakmilikUrusan.getNolot()</td>
		  <td scope="col">$!mo.noPetak</td>
		  <td class="$row"><input class="stylobutton" type="button" value="Hapus" onclick="javascript:deleteBangunan('$!mo.idBangunan')" style="display:none"></td>
    </tr>
      #end
	
</table>
</fieldset>