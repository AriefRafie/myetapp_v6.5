<style type="text/css">
<!--
.kaler_biru {color: #0000FF}
-->
</style>

#foreach ( $dataPelesen in $dataPelesen )
	#set ($lblNamaPelesen=$dataPelesen.dataPelesen)
    #set ($lblNoPelesen=$dataPelesen.no_siri_lesen)
    #set ($lblAlamat1Tetap=$dataPelesen.alamat1_tetap)
    #set ($lblAlamat2Tetap=$dataPelesen.alamat2_tetap)
    #set ($lblAlamat3Tetap=$dataPelesen.alamat3_tetap)
    #set ($lblPoskodTetap=$dataPelesen.poskod_tetap)
    #set ($lblBandarTetap=$dataPelesen.bandartetap)
    #set ($lblNegeriTetap=$dataPelesen.negeritetap)
    #set ($lblAlamat1Surat=$dataPelesen.alamat1_surat)
    #set ($lblAlamat2Surat=$dataPelesen.alamat2_surat)
    #set ($lblAlamat3Surat=$dataPelesen.alamat3_surat)
    #set ($lblPoskodSurat=$dataPelesen.poskod_surat)
    #set ($lblBandarSurat=$dataPelesen.bandarsurat)
    #set ($lblNegeriSurat=$dataPelesen.negerisurat)    
    #set ($lblNoHp=$dataPelesen.no_tel_bimbit)
    #set ($lblNoPej=$dataPelesen.no_tel_pejabat)
    #set ($lblNoRumah=$dataPelesen.no_tel_rumah)
#end

<!------------------------------------------ HEADER ----------------------------------------------------->
<fieldset>
<legend>Maklumat Perlesenan</legend>
	<table width="100%" border="0">
    
		<table width="100%"> 
			<tr>
				<td width="51%"><table width="101%"  cellpadding="1" cellspacing="1" border="0">
                  <tr>
                    <td width="16%" valign="top" style="text-transform:uppercase">Nama Pelesen</td>
                    <td width="1%" valign="top">:</td>
                    <td width="83%"><span class="kaler_biru">$!lblNamaPelesen.toUpperCase()</span></td>
                  </tr>
                  <tr>
                    <td valign="top" style="text-transform:uppercase">No Lesen</td>
                    <td valign="top">:</td>
                    <td><span class="kaler_biru">$!lblNoPelesen.toUpperCase()</span></td>
                  </tr>                 
                  <tr>
                    <td valign="top" style="text-transform:uppercase">Alamat Tetap</td>
                    <td valign="top">:</td>
                    <td><span class="kaler_biru">$!lblAlamat1Tetap.toUpperCase() $!lblAlamat2Tetap.toUpperCase()</span></td>
                  </tr> 
                  <tr>
                    <td valign="top" style="text-transform:uppercase"></td>
                    <td valign="top">&nbsp;</td>
                    <td><span class="kaler_biru">$!lblAlamat3Tetap.toUpperCase() $!lblPoskodTetap.toUpperCase() $!lblBandarTetap.toUpperCase()</span></td>
                  </tr> 
                  <tr>
                    <td valign="top" style="text-transform:uppercase"></td>
                    <td valign="top">&nbsp;</td>
                    <td><span class="kaler_biru">$!lblNegeriTetap.toUpperCase()</span></td>
                  </tr>  
<!--                  <tr>
                    <td valign="top" style="text-transform:uppercase">Alamat Surat Menyurat</td>
                    <td valign="top">:</td>
                    <td><span class="kaler_biru">$!lblAlamat1Surat.toUpperCase() $!lblAlamat2Surat.toUpperCase()</span></td>
                  </tr> 
                  <tr>
                    <td valign="top" style="text-transform:uppercase"></td>
                    <td valign="top">&nbsp;</td>
                    <td><span class="kaler_biru">$!lblAlamat3Surat.toUpperCase() $!lblPoskodSurat.toUpperCase() $!lblBandarSurat.toUpperCase()</span></td>
                  </tr> 
                  <tr>
                    <td valign="top" style="text-transform:uppercase"></td>
                    <td valign="top">&nbsp;</td>
                    <td><span class="kaler_biru">$!lblNegeriSurat.toUpperCase()</span></td>
                  </tr>                                                                        
-->                  <tr>
                    <td valign="top" style="text-transform:uppercase">No HP</td>
                    <td valign="top">:</td>
                    <td><span class="kaler_biru">$!lblNoHp</span></td>
                  </tr>   
                  <tr>
                    <td valign="top" style="text-transform:uppercase">No Pejabat</td>
                    <td valign="top">:</td>
                    <td><span class="kaler_biru">$!lblNoPej</span></td>
                  </tr>   
                  <tr>
                    <td valign="top" style="text-transform:uppercase">No Rumah</td>
                    <td valign="top">:</td>
                    <td><span class="kaler_biru">$!lblNoRumah</span></td>
                  </tr>                                                                
          </table>
          </td>
      
				
<!--		  <td width="49%">
  			<table width="100%"  cellpadding="1" cellspacing="1" border="0">
						<tr>
							<td width="44%" valign="top" style="text-transform:uppercase">Bulan</td>
						    <td width="1%" valign="top">:</td>
					      	<td width="55%">$!nama_mukim.toUpperCase()</td>
			  			</tr>
						<tr>
						  	<td style="text-transform:uppercase">Tahun Pengambilan</td>
							<td>:</td>
							<td>$!syer_bawah</td>
						</tr>                      
			</table>			  
          </td>-->	
            			
		  </tr>
		</table>    
        
</fieldset>            
<br />
<!------------------------------------------ END HEADER ----------------------------------------------------->    
