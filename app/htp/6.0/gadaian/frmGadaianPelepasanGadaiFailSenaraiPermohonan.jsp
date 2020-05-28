<!-- frmGadaianPelepasanGadai -->
<style type="text/css">
	<!--
	.pautanms {color: #0000FF}
	-->
</style> 

<table width="100%" border="0" cellspacing="2" cellpadding="2">
	<tr>
		<td>			
			<fieldset><legend>PERMOHONAN UNTUK MELEPAS GADAIAN</legend>
			
				<table border="0" cellpadding="2" cellspacing="1" width="100%">
				    <tr class="table_header">
				      <td width="3%"><b>Bil.</b></td>
				      <!--<td width="16%"><b>Id Permohonan</b></td>-->
				      <td width="20%"><b>No Fail</b></td>
				      <td width="77%"><b>Nama</b></td>
				    </tr>
				  #set ($count = 0)
				  #foreach ( $fail in $Senaraifail )
				  #set ($count = $count+1)
				  #set( $i = $velocityCount )
				  #if ( ($i % 2) != 1 )
				  #set( $row = "row2" )
				  #else
				  #set( $row = "row1" )
				  #end
				  <tr>
				    <td class="$row">$fail.bil.</td>
				    <td class="$row"><a href="javascript:viewDetails('$fail.idFail', '$fail.idPermohonan')" class="pautanms">$fail.noFailLain</a></td>
				    <td class="$row">$fail.nama</td>
				    <!-- <td class="$row">$fail.noFailLain</td> -->
				    
				
				  </tr>
				  #end
				  #if ($count == 0)
				  <tr>
				    <td colspan="3" scope="row"><font color="#FF0000">Tiada Rekod.</font></td>
				  </tr>
				  #end
				  
				</table>
			</fieldset>
    	
    	</td>
	</tr>
</table>
