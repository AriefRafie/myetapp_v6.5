

<table width="100%" border="0">
	<tr>
		<td>
			<fieldset><legend><strong>MAKLUMAT BORANG PINDAH MILIK</strong></legend>
			<table width="100%" border="0">
				<!--<tr>
					<td>
						<input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Tambah" onclick="kemaskiniPindahMilik($!mo.idPindahMilik)">						
					</td>
				</tr> -->
				<tr class="table_header">
					<td width="3%"><strong>Bil.</td>
					<td width="32%" ><strong>No. Hakmilik</strong></td>
					<td width="20%" ><strong>Tarikh Terima</strong></td>
					<td width="20%" ><strong>Tarikh Hantar</strong></td>
					<td width="20%" ><strong>Tarikh Tandatangan PTP</strong></td>
					<td width="5%"></td>
				</tr>
				#set ( $cnt = 0 )
			    #foreach($mo in $pindahMilik)
			    #set ( $cnt = $cnt + 1 )
			    #if ($senarai.bil == '')
			    #set( $row = "row1" )
			    #elseif (($senarai.bil % 2) != 0)
			    #set( $row = "row1" )
			    #else 
			    #set( $row = "row2" )
			    #end
    				<tr>
    					<td>$!cnt.</td>
						<td>$!mo.hakmilikUrusan.kodjenishakmilik $!mo.hakmilikUrusan.nohakmilik</td>
						<td>$!mo.tarikhTerima</td>
						<td>$!mo.tarikhTandatangan</td>
						<td>$!mo.tarikhHantar</td>
    				<!--</tr>-->
						<td>
							<input type="button" name="cmdKemaskini" id="cmdKemaskini" value="Kemaskini" onclick="kemaskiniPindahMilik($!mo.idPindahMilik)">
						</td>
    				</tr>
    
    		#end

			</table>			
			
			</fieldset>
		</td>
	</tr>	
	
</table>
