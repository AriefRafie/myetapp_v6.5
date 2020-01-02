<form name="f1" method="post">
<input type="hidden" name="form_token" value='$!{session.getAttribute("form_token")}'>
<center>
	
	<fieldset>
	<legend><strong>Maklumat Tangguh</strong></legend>
		
		<table width="100%"  cellpadding="1" cellspacing="2" border="0">
			<tr class="table_header">
				<td width="9%" style="text-transform:uppercase"><div align="center">Bil</div></td>
			  <td width="13%" style="text-transform:uppercase">Tarikh Bicara</td>
		  	  <td width="22%" style="text-transform:uppercase">Keputusan Perbicaraan</td>
           	  <td width="28%" style="text-transform:uppercase">Keterangan</td>
              <td width="28%" style="text-transform:uppercase">Keputusan Mahkamah</td>
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
         		<td class="$row" align="center">$list.bil_bicara</td> 
         		<td class="$row">$list.tarikh_bicara</td> 
         		<td class="$row">$list.flag_tangguh</td> 
                <td class="$row">$list.sebab_tangguh</td>
                <td class="$row">$list.keputusan_mahkamah</td>
         	</tr>
           #end
        #else		
			<tr>
				<td colspan="5">Tiada Rekod</td>
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