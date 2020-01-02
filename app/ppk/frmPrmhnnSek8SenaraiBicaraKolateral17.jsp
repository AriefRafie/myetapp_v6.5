<form name="f1" method="post">
<center>
	
	<fieldset>
	<legend><strong>SENARAI TANGGUH KOLATERAL</strong></legend>
		
		<table width="100%"  cellpadding="1" cellspacing="2" border="0">
			<tr class="table_header">
				<td width="3%"><b>Bil</b></td>
				<td width="10%"><b>Tarikh Bicara</b></td>
				<td width="27%"><b>Keputusan Perbicaraan</b></td>
				<td width="30%"><b>Keterangan</b></td>
				<td width="30%"><b>Keputusan Mahkamah</b></td>
			</tr>
			
		#if($listBicara_size!=0)
		  	
           #foreach($list in $listBicara)
                   	 #set( $i = $velocityCount )
         		#if ( ($i % 2) != 1 )
              		 #set( $row = "row2" )
         		#else
               		 #set( $row = "row1" )
         		#end	
         	
         	<tr>
         		<td class="$row" align="center">$list.bil</td> 
         		<td class="$row">$list.tarikh_bicara</td> 
         		<td class="$row">$list.flag_tangguh.toUpperCase()</td> 
         		<td class="$row">$list.sebab_tangguh</td> 
         		<td class="$row">$list.keputusan_mahkamah</td> 
         	</tr>
           #end
        #else		
			<tr>
				<td colspan="3">Tiada Rekod</td>
			</tr>
		#end	
		</table>
	
	</fieldset>
	
		<table width="100%"  cellpadding="1" cellspacing="2" border="0">
			<tr align="center">
				<td><input type="button" name="cmdExit" value="kembali" onClick="self.close();return false;" /></td>
			</tr>
		</table>
</center>
</form>