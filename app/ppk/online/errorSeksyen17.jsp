<fieldset>
	<table>
		<tr>
			<td>
				
			
			</td>
		</tr>
		
		<tr>
			<td>
				<table>
					<tr>
						<td>
						<b>
							#if($ERRORNO == 1)
								
									Harap Maaf, tiada permohonan awal bagi maklumat si mati yang dimasukkan. Tuan/Puan tidak boleh meneruskan permohonan Seksyen 17. Sila  muat turun Borang P untuk permohonan secara manual
							
							#elseif($ERRORNO == 2)
									Harap Maaf, Permohonan Seksyen 8 masih belum diselesaikan . Tuan/Puan tidak boleh meneruskan permohonan Seksyen 17. Sila berhubung dengan Pejabat Pusaka terlebih dahulu.
							#elseif($ERRORNO == 3)
									Harap Maaf, Permohonan sebelum ini masih belum diselesaikan . Tuan/Puan tidak boleh meneruskan permohonan baru Seksyen 17. Sila berhubung dengan Pejabat Pusaka terlebih dahulu.
							#else
									Uncaught exception
								
							#end							</b>						</td>
				  </tr>
					<!--
                    <tr>
						<td>
						
							#if($ERRORNO == 1)
								
									<input type="button" value="Kembali ke Menu Utama" onclick="menuUtama()"/>
									<input type="button" value="Muat Turuan Borang P" />
							
							#elseif($ERRORNO == 2)
									<input type="button" value="Kembali ke Menu Utama" onclick="menuUtama()"/>
									<input type="button" value="Semak Status Permohonan" onclick="gotoSemakStatus()"/>
									
							#elseif($ERRORNO == 3)
									<input type="button" value="Kembali ke Menu Utama" onclick="menuUtama()"/>
									<input type="button" value="Semak Status Permohonan" onclick="gotoSemakStatus()"/>
									 
							#else
									<input type="button" value="Kembali ke Menu Utama" onclick="menuUtama()"/>
								
							#end
						</td>
					
					</tr>
                    -->
				
				
				</table>
			
			</td>
		</tr>
	</table>


</fieldset>