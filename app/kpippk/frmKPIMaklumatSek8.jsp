

<table width="100%" border="0">
  
  <tr class="table_headerkpi">
  		<td width="25%" align="center"><b>AKTIVITI<br>(ALIRAN PROSES "TO BE")</b></td>
    	<td width="10%" align="center"><b>Sasaran masa menyiapkan satu aktiviti (hari)</b></td>
    	<td width="10%" align="center"><b>Jumlah sebenar bilangan aktiviti dilakukan</b></td>
    	<td width="10%" align="center"><b>Jumlah masa sebenar menyiapkan semua aktiviti (hari)</b></td>
    	<td width="10%" align="center"><b>Purata masa menyiapkan satu aktiviti (hari)</b></td>
    	<td width="10%" align="center"><b>Efisensi Aktiviti (Kecekapan)</b></td>
    	<td colspan="2" align="center"><b>KEBERKESANAN - (OUTCOME)</b></td>
  </tr>
  
  <tr class="table_headerkpi">
    	<td align="center"><div><b>A <br></b><em>Preset</em></div></td>
     	<td align="center"><b>B</b><br><em>Preset</em></td>
     	<td align="center"><b>C</b><br><em>(direct capture)</em></td>
        <td align="center"><b>D</b><br><em>(direct capture)</em></td>
    	<td align="center"><b>E = D/C</b><br><em>(compute)</em></td>
     	<td align="center"><b>F = B/E %</b><br><em>(compute)</em></td>
    	<td width="15%" align="center"><b>(Permohonan menunggu) :</b></td>
   		<td width="10%" align="center"><b>Bilangan</b><br><em>(direct capture)</em></td>
  </tr>
  
  <tr>
    	<td class="row2"><b>1.</b>$!ProsesPPK1</td>
  		<td class="row2" align="center"><b>$!txtF1</b>
  		<!-- hidden value -->
        <input name="HtxtF1" type="hidden" id="HtxtF1" size="5" maxlength="5" value="$!txtF1" />
        </td>
    	
    	<td class="row2" align="center"><b>$!jumlah_aktiviti_boranga</b>
    	<!-- hidden value -->
        <input name="jumlah_aktiviti_boranga" type="hidden" id="jumlah_aktiviti_boranga" size="5" maxlength="5" value="$!jumlah_aktiviti_boranga" />
        </td>
    	
    	<td class="row2" align="center"><b>$!jumlah_hari_boranga</b>
    	<!-- hidden value -->
        <input name="jumlah_hari_boranga" type="hidden" id="jumlah_hari_boranga" size="5" maxlength="5" value="$!jumlah_hari_boranga" />
     	</td>
    	
    	<td class="row2" align="center">
       	<input name="purata_masa_boranga" type="hidden" id="purata_masa_boranga" value="$!purata_masa_boranga" />
    	<font color="blue"><b>$!purata_masa_boranga</b></font>
    	</td>
    	
    	#set($efisensi_boranga = 0)
    	#set($efisensi_boranga = ($Utils.parseDouble($!txtF1) / $!Utils.parseDouble($!purata_masa_boranga))*100 )
    	
    	<td class="row2">
     		<input name="efisensi_boranga" type="hidden" id="efisensi_boranga" value="$Utils.format2Decimal($efisensi_boranga)" >
    		<div align="center"><font color="blue"><b>$Utils.format2Decimal($efisensi_boranga)%</b></font></div>
    	</td> 
    	
    	
    	<td rowspan="20" colspan="2" valign="top">
    	
    	<table width="100%" border="0" class="table_headerkpi">
     		<tr class="row2">
     			<td width="60%">
     				<table width="100%">
       					<tr>
         					<td width="15%"  valign="top">1.</td>
         					<td width="85%">$!lblOutcome1</td>
       					</tr>
     				</table>
     			</td>
      
      			<td width="40%" align="center"><b>$!totalPermohonan</b>
            		<input name="totalPermohonan" type="hidden" id="totalPermohonan" size="5" maxlength="5" value="$!totalPermohonan">
        		</td>
     		</tr>
     		
     		<tr class="row2">
     			<td width="60%">
     				<table width="100%" border="0">
       					<tr>
         					<td width="15%" valign="top">2.</td>
         					<td width="85%">$!lblOutcome2</td>
       					</tr>
     				</table>
     			</td>
     			
      			<td width="40%" align="center"><b>$!totalSelesai</b>
     				<input name="totalSelesai" type="hidden" id="totalSelesai" size="5" maxlength="5" value="$!totalSelesai">
        		</td>
     		</tr>
     		
     		<tr class="row2">
     			<td colspan="2">
     				<table width="100%" border="0">
       					<tr>
         					<td width="10%" valign="top">3.</td>
         					<td width="90%">$!lblOutcome3</td>
       					</tr>
     				</table>     
     			</td>
     		</tr>
     		
     		<tr>
     			<td colspan="2">
     				<table width="100%" border="0" class="row1">
     				
     					<tr><td colspan="3"></td></tr>
     					
     					<tr>
     						<td colspan="2">
     							<table width="100%">
     								<tr><td colspan="3"></td></tr>
       								<tr>
         								<td width="10%"></td>
         								<td width="5%" valign="top">a.</td>
         								<td width="85%">$!ProsesTungguA</td>
       								</tr>
     							</table>        
     						</td>
     					</tr>
     					
     					<tr>
     						<td width="60%" align="right">&lt; $!txtT1 $!lblHari</td>
      						<td bgcolor="#33FF99" width="40%" align="center">
      							<a style="cursor:pointer" onClick="openPopupMenunggu('$!ProsesTungguA &lt; $!txtT1 $!lblHari','$!jumlahTungguJPPHHijau','jpph','$!txtT1','$!txtT2','hijau')" title="Klik untuk paparan No Fail">
      							<b><font color="#000000">$!jumlahTungguJPPHHijau</font></b>    
      							</a>
        						<input name="jumlahTungguJPPHHijau" type="hidden" id="jumlahTungguJPPHHijau" size="5" maxlength="5" value="$!jumlahTungguJPPHHijau" >
      				 		</td>
     					</tr>
     
     					<tr>
     						<td align="right">$!lblT1 - $!txtT2 $!lblHari</td>
      						<td bgcolor="yellow" align="center">
      							<a style="cursor:pointer" onClick="openPopupMenunggu('$!ProsesTungguA $!lblT1 - $!txtT2 $!lblHari','$!jumlahTungguJPPHKuning','jpph','$!txtT1','$!txtT2','kuning')" title="Klik untuk paparan No Fail">
       							<b><font color="#000000">$!jumlahTungguJPPHKuning</font></b>
        						<input name="jumlahTungguJPPHKuning" type="hidden" id="jumlahTungguJPPHKuning" size="5" maxlength="5" value="$!jumlahTungguJPPHKuning" >
      							</a>
      						</td>
     					</tr>
     					
     					<tr>
     						<td align="right">&gt; $!lblT2 $!lblHari</td>
      						<td bgcolor="red" align="center">
      							<a style="cursor:pointer" onClick="openPopupMenunggu('$!ProsesTungguA &gt; $!lblT2 $!lblHari','$!jumlahTungguJPPHMerah','jpph','$!txtT1','$!txtT2','merah')" title="Klik untuk paparan No Fail">
        						<b><font color="#000000">$!jumlahTungguJPPHMerah</font></b>
        						<input name="jumlahTungguJPPHMerah" type="hidden" id="jumlahTungguJPPHMerah" size="5" maxlength="5" value="$!jumlahTungguJPPHMerah" >
        						</a>
        					</td>
     					</tr>
     					
     					<tr><td colspan="3">&nbsp;</td></tr>
     					
     				</table>     
     			</td>
     		</tr>
     		
     		<tr>
     			<td colspan="2">
     				<table width="100%" border="0" class="row2">
     				
     					<tr><td colspan="3"></td></tr>
     					
     					<tr>
     						<td colspan="2">
     							<table width="100%">
       								<tr>
         								<td width="10%"></td>
         								<td width="5%" valign="top">b.</td>
         								<td width="85%">$!ProsesTungguB</td>
     								</tr>
     							</table>       
     						</td>
     					</tr>
      
      					<tr>
     						<td width="60%" align="right">&lt; $!txtT3 $!lblHari</td>
      						<td bgcolor="#33FF99" width="40%" align="center"> 
      							<a style="cursor:pointer" onClick="openPopupMenunggu('$!ProsesTungguB &lt; $!txtT3 $!lblHari','$!jumlahTungguBorangCHijau','borangc','$!txtT3','$!txtT4','hijau')" title="Klik untuk paparan No Fail">
      							<b><font color="#000000">$!jumlahTungguBorangCHijau</font></b>
						    	<input name="jumlahTungguBorangCHijau" type="hidden" id="jumlahTungguBorangCHijau" size="5" maxlength="5" value="$!jumlahTungguBorangCHijau" >
       							</a>
       						</td>
     					</tr>
      
      					<tr>
     						<td align="right">$!lblT3 - $!txtT4 $!lblHari</td>
      						<td bgcolor="yellow" align="center">
      							<a style="cursor:pointer" onClick="openPopupMenunggu('$!ProsesTungguB $!lblT3 - $!txtT4 $!lblHari','$!jumlahTungguBorangCKuning','borangc','$!txtT3','$!txtT4','kuning')" title="Klik untuk paparan No Fail">
								<b><font color="#000000">$!jumlahTungguBorangCKuning</font></b> 
       							<input name="jumlahTungguBorangCKuning" type="hidden" id="jumlahTungguBorangCKuning" size="5" maxlength="5" value="$!jumlahTungguBorangCKuning" >
       							</a>
        					</td>
     					</tr>
     					
				      	<tr>
				     		<td align="right">&gt; $!lblT4 $!lblHari</td>
				      		<td bgcolor="red" align="center">
				      		    <a style="cursor:pointer" onClick="openPopupMenunggu('$!ProsesTungguB &gt; $!lblT4 $!lblHari','$!jumlahTungguBorangCMerah','borangc','$!txtT3','$!txtT4','merah')" title="Klik untuk paparan No Fail">
				     			<b><font color="#000000">$!jumlahTungguBorangCMerah</font></b>
				        		<input name="jumlahTungguBorangCMerah" type="hidden" id="jumlahTungguBorangCMerah" size="5" maxlength="5" value="$!jumlahTungguBorangCMerah" >
				        		</a>
				        	</td>
				     	</tr>
				     	
				     	<tr><td colspan="3">&nbsp;</td></tr>
				     	
     				</table>     
     			</td>
     		</tr>
     
     		<tr>
     			<td colspan="2">
     				<table width="100%" class="row1">
     					
     					<tr><td colspan="3"></td></tr>
     					
     					<tr>
     						<td colspan="2">
     							<table width="100%">
       								<tr>
         								<td width="10%"></td>
         								<td width="5%"  valign="top">c.</td>
         								<td width="85%">$!ProsesTungguC</td>
       								</tr>
     							</table>       
     						</td>
     					</tr>
     					
      					<tr>
     						<td width="60%" align="right">&lt; $!txtT5 $!lblHari</td>
      						<td  bgcolor="#33FF99" width="40%" align="center">
      							<a style="cursor:pointer" onClick="openPopupMenunggu('$!ProsesTungguC &lt; $!txtT5 $!lblHari','$!jumlahTungguBicaraKolateralHijau','kolateral','$!txtT5','$!txtT6','hijau')" title="Klik untuk paparan No Fail">
      							<b><font color="#000000">$!jumlahTungguBicaraKolateralHijau</font></b>
					        	<input name="jumlahTungguBicaraKolateralHijau" type="hidden" id="jumlahTungguBicaraKolateralHijau" size="5" maxlength="5" value="$!jumlahTungguBicaraKolateralHijau" >
					        	</a>
					        </td>
    	 				</tr>
    	 				
      					<tr>
     						<td align="right">$!lblT5 - $!txtT6 $!lblHari</td>
      						<td bgcolor="yellow" align="center">
      						    <a style="cursor:pointer" onClick="openPopupMenunggu('$!ProsesTungguC $!lblT5 - $!txtT6 $!lblHari','$!jumlahTungguBicaraKolateralKuning','kolateral','$!txtT5','$!txtT6','kuning')" title="Klik untuk paparan No Fail">
      							<b><font color="#000000">$!jumlahTungguBicaraKolateralKuning</font></b> 
        						<input name="jumlahTungguBicaraKolateralKuning" type="hidden" id="jumlahTungguBicaraKolateralKuning" size="5" maxlength="5" value="$!jumlahTungguBicaraKolateralKuning" >
        						</a>
        					</td>
     					</tr>
      
      					<tr>
     						<td align="right">&gt; $!lblT6  $!lblHari</td>
      						<td bgcolor="red" align="center">
      							<a style="cursor:pointer" onClick="openPopupMenunggu('$!ProsesTungguC &gt; $!lblT6  $!lblHari','$!jumlahTungguBicaraKolateralMerah','kolateral','$!txtT5','$!txtT6','merah')" title="Klik untuk paparan No Fail">
      							<b><font color="#000000">$!jumlahTungguBicaraKolateralMerah</font></b>
        						<input name="jumlahTungguBicaraKolateralMerah" type="hidden" id="jumlahTungguBicaraKolateralMerah" size="5" maxlength="5" value="$!jumlahTungguBicaraKolateralMerah" >
        						</a>
             				</td>
     					</tr>
     					
     					<tr><td colspan="3">&nbsp;</td></tr>
     					
     				</table>     
     			</td>
     		</tr>
     
     		<tr>
     			<td colspan="2">
     				<table width="100%" border="0" class="row2">
     				
     					<tr><td colspan="3"></td></tr>
     					
     					<tr>
     						<td colspan="2">
     							<table width="100%">
       								<tr>
         								<td width="10%"></td>
         								<td width="5%" valign="top">d.</td>
         								<td width="85%">$!ProsesTungguD</td>
     								</tr>
     							</table>       
     						</td>
     					</tr>
      
      					<tr>
     						<td width="60%" align="right">&lt; $!txtT7 $!lblHari</td>
      						<td bgcolor="#33FF99" width="40%" align="center"> 
      							<b><font color="#000000">0</font></b>
						    	<input name="qqqq" type="hidden" id="qqqq" size="5" maxlength="5" value="0" >
       						</td>
     					</tr>
      
      					<tr>
     						<td align="right">$!lblT7 - $!txtT8 $!lblHari</td>
      						<td bgcolor="yellow" align="center">
								<b><font color="#000000">0</font></b>
       							<input name="rrrr" type="hidden" id="rrrr" size="5" maxlength="5" value="0" >
        					</td>
     					</tr>
     					
				      	<tr>
				     		<td align="right">&gt; $!lblT8 $!lblHari</td>
				      		<td bgcolor="red" align="center">
				     			<b><font color="#000000">0</font></b>
				        		<input name="ssss" type="hidden" id="ssss" size="5" maxlength="5" value="0" >
				        	</td>
				     	</tr>
				     	
				     	<tr><td colspan="3">&nbsp;</td></tr>
				     	
     				</table>     
     			</td>
     		</tr>
     
     		
     		<tr>
     			<td colspan="2">
     				<table width="100%" class="row1">
     					
     					<tr><td colspan="3"></td></tr>
     					
     					<tr>
     						<td colspan="2">
     							<table width="100%">
       								<tr>
         								<td width="10%"></td>
         								<td width="5%"  valign="top">e.</td>
         								<td width="85%">$!ProsesTungguE</td>
       								</tr>
     							</table>       
     						</td>
     					</tr>
     					
      					<tr>
     						<td width="60%" align="right">&lt; $!txtT9 $!lblHari</td>
      						<td  bgcolor="#33FF99" width="40%" align="center">
      							<a style="cursor:pointer" onClick="openPopupMenunggu('$!ProsesTungguE &lt; $!txtT9 $!lblHari','$!jumlahTungguMTHijau','mahkamahtinggi','$!txtT9','$!txtT10','hijau')" title="Klik untuk paparan No Fail">
      							<b><font color="#000000">$!jumlahTungguMTHijau</font></b>
					        	<input name="jumlahTungguMTHijau" type="hidden" id="jumlahTungguMTHijau" size="5" maxlength="5" value="$!jumlahTungguMTHijau" >
					        	</a>
					        </td>
    	 				</tr>
    	 				
      					<tr>
     						<td align="right">$!lblT9 - $!txtT10 $!lblHari</td>
      						<td bgcolor="yellow" align="center">
      							<a style="cursor:pointer" onClick="openPopupMenunggu('$!ProsesTungguE $!lblT9 - $!txtT10 $!lblHari','$!jumlahTungguMTKuning','mahkamahtinggi','$!txtT9','$!txtT10','kuning')" title="Klik untuk paparan No Fail">
      							<b><font color="#000000">$!jumlahTungguMTKuning</font></b> 
        						<input name="$!jumlahTungguMTKuning" type="hidden" id="jumlahTungguMTKuning" size="5" maxlength="5" value="$!jumlahTungguMTKuning" >
        						</a>
        					</td>
     					</tr>
      
      					<tr>
     						<td align="right">&gt; $!lblT10  $!lblHari</td>
      						<td bgcolor="red" align="center">
      							<a style="cursor:pointer" onClick="openPopupMenunggu('$!ProsesTungguE &gt; $!lblT10  $!lblHari','$!jumlahTungguMTMerah','mahkamahtinggi','$!txtT9','$!txtT10','merah')" title="Klik untuk paparan No Fail">
      							<b><font color="#000000">$!jumlahTungguMTMerah</font></b>
        						<input name="jumlahTungguMTMerah" type="hidden" id="jumlahTungguMTMerah" size="5" maxlength="5" value="$!jumlahTungguMTMerah" >
        						</a>
             				</td>
     					</tr>
     					
     					<tr><td colspan="3">&nbsp;</td></tr>
     					
     				</table>     
     			</td>
     		</tr>
     		
     		<tr>
     			<td colspan="2">
     				<table width="100%" border="0" class="row2">
     				
     					<tr><td colspan="3"></td></tr>
     					
     					<tr>
     						<td colspan="2">
     							<table width="100%">
       								<tr>
         								<td width="10%"></td>
         								<td width="5%" valign="top">f.</td>
         								<td width="85%">$!ProsesTungguF</td>
     								</tr>
     							</table>       
     						</td>
     					</tr>
      
      					<tr>
     						<td width="60%" align="right">&lt; $!txtT11 $!lblHari</td>
      						<td bgcolor="#33FF99" width="40%" align="center"> 
      							<a style="cursor:pointer" onClick="openPopupMenunggu('$!ProsesTungguF &lt; $!txtT11 $!lblHari','$!jumlahTungguROTSHijau','rots','$!txtT11','$!txtT12','hijau')" title="Klik untuk paparan No Fail">
      							<b><font color="#000000">$!jumlahTungguROTSHijau</font></b>
						    	<input name="jumlahTungguROTSHijau" type="hidden" id="jumlahTungguROTSHijau" size="5" maxlength="5" value="$!jumlahTungguROTSHijau" >
       							</a>
       						</td>
     					</tr>
      
      					<tr>
     						<td align="right">$!lblT11 - $!txtT12 $!lblHari</td>
      						<td bgcolor="yellow" align="center">
      							<a style="cursor:pointer" onClick="openPopupMenunggu('$!ProsesTungguF $!lblT11 - $!txtT12 $!lblHari','$!jumlahTungguROTSKuning','rots','$!txtT11','$!txtT12','kuning')" title="Klik untuk paparan No Fail">
								<b><font color="#000000">$!jumlahTungguROTSKuning</font></b>
       							<input name="jumlahTungguROTSKuning" type="hidden" id="jumlahTungguROTSKuning" size="5" maxlength="5" value="$!jumlahTungguROTSKuning" >
       							</a>
        					</td>
     					</tr>
     					
				      	<tr>
				     		<td align="right">&gt; $!lblT12 $!lblHari</td>
				      		<td bgcolor="red" align="center">
				      			<a style="cursor:pointer" onClick="openPopupMenunggu('$!ProsesTungguF &gt; $!lblT12 $!lblHari','$!jumlahTungguROTSMerah','rots','$!txtT11','$!txtT12','merah')" title="Klik untuk paparan No Fail">
				     			<b><font color="#000000">$!jumlahTungguROTSMerah</font></b>
				        		<input name="jumlahTungguROTSMerah" type="hidden" id="jumlahTungguROTSMerah" size="5" maxlength="5" value="$!jumlahTungguROTSMerah" >
				        		</a>
				        	</td>
				     	</tr>
				     	
				     	<tr><td colspan="3">&nbsp;</td></tr>
				     	
     				</table>     
     			</td>
     		</tr>
     		
     	</table>     
     	</td>
  </tr>
 
  <tr>
      	<td colspan="6"><b>2.</b>$ProsesTungguA</td>
  </tr>
  
  <tr>
      	<td colspan="6"><b>3.</b>$ProsesTungguB</td>
  </tr>
  
  <tr>
    	<td class="row2"><b>4.</b>$!ProsesPPK2</td>
    	
    	<td class="row2" align="center"><b>$!txtF2</b>
      		<input name="HtxtF2" type="hidden" id="HtxtF2" size="5" maxlength="5" value="$!txtF2" />
    	</td>
    	
    	<td class="row2" align="center"><b>$!jumlah_aktiviti_nilai_jpph</b>
        	<input name="jumlah_aktiviti_nilai_jpph" type="hidden" id="jumlah_aktiviti_nilai_jpph" size="5" maxlength="5" value="$!jumlah_aktiviti_nilai_jpph">
        </td>
        
    	<td class="row2" align="center"><b>$!jumlah_hari_nilai_jpph</b>
        	<input name="jumlah_hari_nilai_jpph" type="hidden" id="jumlah_hari_nilai_jpph" size="5" maxlength="5" value="$!jumlah_hari_nilai_jpph">
        </td>
    
    	<td class="row2" align="center">
      		<input name="purata_masa_nilai_jpph" type="hidden" id="purata_masa_nilai_jpph" value="$!purata_masa_nilai_jpph" />
    		<font color="blue"><b>$!purata_masa_nilai_jpph</b></font>
    	</td>
    	
    	#set($efisensi_nilai_jpph = 0)
    	#set($efisensi_nilai_jpph = ($Utils.parseDouble($!txtF2) / $!Utils.parseDouble($!purata_masa_nilai_jpph))*100 )
    	
    	<td class="row2">
     		<input name="efisensi_nilai_jpph" type="hidden" id="efisensi_nilai_jpph" value="$Utils.format2Decimal($efisensi_nilai_jpph)" >
    		<div align="center"><font color="blue"><b>$Utils.format2Decimal($efisensi_nilai_jpph)%</b></font></div>
    	</td> 
     
  </tr>
  
  <tr>
   	 	<td class="row2"><b>5.</b>$!ProsesPPK3</td>
    	<td class="row2" align="center"><b>$!txtF3</b>
      		<input name="HtxtF3" type="hidden" id="HtxtF3" size="5" maxlength="5" value="$!txtF3">
  		</td>
  		
    	<td class="row2" align="center"><b>$!jumlah_aktiviti_borangc</b>
        	<input name="jumlah_aktiviti_borangc" type="hidden" id="jumlah_aktiviti_borangc" size="5" maxlength="5" value="$!jumlah_aktiviti_borangc" >
        </td>
    
    	<td class="row2" align="center"><b>$!jumlah_hari_borangc</b>
        	<input name="jumlah_hari_borangc" type="hidden" id="jumlah_hari_borangc" size="5" maxlength="5" value="$!jumlah_hari_borangc">
        </td>
    
    	<td class="row2"  align="center">
    		<input name="purata_masa_borangc" type="hidden" id="purata_masa_borangc" value="$!purata_masa_borangc" />
    		<font color="blue"><b>$!purata_masa_borangc</b></font>
    	</td>
    	
    	#set($efisensi_borangc = 0)
    	#set($efisensi_borangc = ($Utils.parseDouble($!txtF3) / $!Utils.parseDouble($!purata_masa_borangc))*100 )
    	
    	<td class="row2">
     		<input name="efisensi_borangc" type="hidden" id="efisensi_borangc" value="$Utils.format2Decimal($efisensi_borangc)" >
    		<div align="center"><font color="blue"><b>$Utils.format2Decimal($efisensi_borangc)%</b></font></div>
    	</td> 
    	
  </tr>
  
  <tr>
    	<td class="row2"><b>6.</b>$!ProsesPPK4</td>
    	<td class="row2" align="center"><b>$!txtF4</b>
      		<input name="HtxtF4" type="hidden" id="HtxtF4" size="5" maxlength="5" value="$!txtF4">
    	</td>
    
    	<td class="row2" align="center"><b>$!jumlah_aktiviti_mohon_bke</b>
        	<input name="jumlah_aktiviti_mohon_bke" type="hidden" id="jumlah_aktiviti_mohon_bke" size="5" maxlength="5" value="$!jumlah_aktiviti_mohon_bke">
        </td>
    
    	<td class="row2" align="center"><b>$!jumlah_hari_mohon_bke</b>
        	<input name="jumlah_hari_mohon_bke" type="hidden" id="jumlah_hari_mohon_bke" size="5" maxlength="5" value="$!jumlah_hari_mohon_bke">
        </td>
    
    	<td class="row2"  align="center">
    		<input name="purata_masa_mohon_bke" type="hidden" id="purata_masa_mohon_bke" value="$!purata_masa_mohon_bke" />
    		<font color="blue"><b>$!purata_masa_mohon_bke</b></font>
    	</td>
    	
    	#set($efisensi_mohon_bke = 0)
    	#set($efisensi_mohon_bke = ($Utils.parseDouble($!txtF4) / $!Utils.parseDouble($!purata_masa_mohon_bke))*100 )
    	
    	<td class="row2">
     		<input name="efisensi_mohon_bke" type="hidden" id="efisensi_mohon_bke" value="$Utils.format2Decimal($efisensi_mohon_bke)" >
    		<div align="center"><font color="blue"><b>$Utils.format2Decimal($efisensi_mohon_bke)%</b></font></div>
    	</td> 	
    	
  </tr> 
  
  <tr>
    	<td class="row2"><b>7.</b>$!ProsesPPK5</td>
    	<td class="row2" align="center"><b>$!txtF5</b>
      		<input name="HtxtF5" type="hidden" id="HtxtF5" size="5" maxlength="5" value="$!txtF5">
   		</td>
   		
    	<td class="row2" align="center"><b>$!jumlah_aktiviti_borang_q</b>
        	<input name="jumlah_aktiviti_borang_q" type="hidden" id="jumlah_aktiviti_borang_q" size="5" maxlength="5" value="$!jumlah_aktiviti_borang_q">
      	</td>
    
    	<td class="row2" align="center"><b>$!jumlah_hari_borang_q</b>
        	<input name="jumlah_hari_borang_q" type="hidden" id="jumlah_hari_borang_q" size="5" maxlength="5" value="$!jumlah_hari_borang_q">
      	</td>
      	
    	<td class="row2"  align="center">
    		<input name="purata_masa_borang_q" type="hidden" id="purata_masa_borang_q" value="$!purata_masa_borang_q" />
    		<font color="blue"><b>$!purata_masa_borang_q</b></font>
    	</td>
    	
    	#set($efisensi_borang_q = 0)
    	#set($efisensi_borang_q = ($Utils.parseDouble($!txtF5) / $!Utils.parseDouble($!purata_masa_borang_q))*100 )
    	
    	<td class="row2">
     		<input name="efisensi_borang_q" type="hidden" id="efisensi_borang_q" value="$Utils.format2Decimal($efisensi_borang_q)" >
    		<div align="center"><font color="blue"><b>$Utils.format2Decimal($efisensi_borang_q)%</b></font></div>
    	</td>
    	
  </tr>
    
  <!-- <tr>
    	<td class="row2"><b>8.</b>$!ProsesPPK6</td>
     	<td class="row2" align="center"><b>$!txtF6</b>
      		<input name="HtxtF6" type="hidden" id="HtxtF6" size="5" maxlength="5" value="$!txtF6">
    	</td>
    	
    	<td class="row2" align="center"><b>$!AKTIVITI_PINDAH_FAIL</b>
        	<input name="AKTIVITI_PINDAH_FAIL" type="hidden" id="AKTIVITI_PINDAH_FAIL" size="5" maxlength="5" value="$!AKTIVITI_PINDAH_FAIL">
      	</td>
      	
    	<td class="row2" align="center"><b>bb55</b>
        	<input name="bb55" type="hidden" id="bb55" size="5" maxlength="5" value="bb55">
      	</td>
      	
    	<td class="row2">
    		<input name="????" type="hidden" id="????" size="5" maxlength="5" >
    		<div align="center" id="bb66">bb66</div>
    	</td>
    	
    	<td class="row2">
    		<input name="????" type="hidden" id="????" size="5" maxlength="5" >
    		<div align="center" id="bb77">bb77</div>
    	</td>
  </tr> -->  
  
  
  <tr>
   	 	<td class="row2"><b>8.</b>$!ProsesPPK7</td>
    	<td class="row2" align="center"><b>$!txtF7</b>
      		<input name="HtxtF7" type="hidden" id="HtxtF7" size="5" maxlength="5" value="$!txtF7">
   		</td>
    
    	<td class="row2" align="center"><b>$!jumlah_aktiviti_sedia_notis</b>
        	<input name="jumlah_aktiviti_sedia_notis" type="hidden" id="jumlah_aktiviti_sedia_notis" size="5" maxlength="5" value="$!jumlah_aktiviti_sedia_notis">
      	</td>
    
    	<td class="row2" align="center"><b>$!jumlah_hari_sedia_notis</b>
        	<input name="jumlah_hari_sedia_notis" type="hidden" id="jumlah_hari_sedia_notis" size="5" maxlength="5" value="$!jumlah_hari_sedia_notis">
      	</td>
    
    	<td class="row2"  align="center">
      		<input name="purata_masa_sedia_notis" type="hidden" id="purata_masa_sedia_notis" value="$!purata_masa_sedia_notis" />
    		<font color="blue"><b>$!purata_masa_sedia_notis</b></font>
    	</td>
    	
    	#set($efisensi_sedia_notis = 0)
    	#set($efisensi_sedia_notis = ($Utils.parseDouble($!txtF7) / $!Utils.parseDouble($!purata_masa_sedia_notis))*100 )
    	
    	<td class="row2">
     		<input name="efisensi_sedia_notis" type="hidden" id="efisensi_sedia_notis" value="$Utils.format2Decimal($efisensi_sedia_notis)" >
    		<div align="center"><font color="blue"><b>$Utils.format2Decimal($efisensi_sedia_notis)%</b></font></div>
    	</td>
    	
  </tr>
  
  <tr>
   	 	<td class="row2"><b>9.</b>$!ProsesPPK8</td>
    	<td class="row2" align="center"><b>$!txtF8</b>
      		<input name="HtxtF8" type="hidden" id="HtxtF8" size="5" maxlength="5" value="$!txtF8">
   		</td>
    
    	<td class="row2" align="center"><b>$!jumlah_aktiviti_bicara_pertama</b>
        	<input name="jumlah_aktiviti_bicara_pertama" type="hidden" id="jumlah_aktiviti_bicara_pertama" size="5" maxlength="5" value="$!jumlah_aktiviti_bicara_pertama">
      	</td>
    
    	<td class="row2" align="center"><b>$!jumlah_hari_bicara_pertama</b>
        	<input name="jumlah_hari_bicara_pertama" type="hidden" id="jumlah_hari_bicara_pertama" size="5" maxlength="5" value="$!jumlah_hari_bicara_pertama">
      	</td>
    
    	<td class="row2"  align="center">
      		<input name="purata_masa_bicara_pertama" type="hidden" id="purata_masa_bicara_pertama" value="$!purata_masa_bicara_pertama" />
    		<font color="blue"><b>$!purata_masa_bicara_pertama</b></font>
    	</td>
    	
    	#set($efisensi_bicara_pertama = 0)
    	#set($efisensi_bicara_pertama = ($Utils.parseDouble($!txtF8) / $!Utils.parseDouble($!purata_masa_bicara_pertama))*100 )
    	
    	<td class="row2">
     		<input name="efisensi_bicara_pertama" type="hidden" id="efisensi_bicara_pertama" value="$Utils.format2Decimal($efisensi_bicara_pertama)" >
    		<div align="center"><font color="blue"><b>$Utils.format2Decimal($efisensi_bicara_pertama)%</b></font></div>
    	</td>
    	
  </tr>
  
  <tr>
   	 	<td class="row2"><b>10.</b>$!ProsesPPK9</td>
    	<td class="row2" align="center"><b>$!txtF9</b>
      		<input name="HtxtF9" type="hidden" id="HtxtF9" size="5" maxlength="5" value="$!txtF9">
   		</td>
    
    	<td class="row2" align="center"><b>$!jumlah_aktiviti_borang_l</b>
        	<input name="jumlah_aktiviti_borang_l" type="hidden" id="jumlah_aktiviti_borang_l" size="5" maxlength="5" value="$!jumlah_aktiviti_borang_l">
      	</td>
    
    	<td class="row2" align="center"><b>$!jumlah_hari_borang_l</b>
        	<input name="jumlah_hari_borang_l" type="hidden" id="jumlah_hari_borang_l" size="5" maxlength="5" value="$!jumlah_hari_borang_l">
      	</td>
    
    	<td class="row2" align="center">
      		<input name="purata_masa_borang_l" type="hidden" id="purata_masa_borang_l" value="$!purata_masa_borang_l" />
    		<font color="blue"><b>$!purata_masa_borang_l</b></font>
    	</td>
    	
    	#set($efisensi_borang_l = 0)
    	#set($efisensi_borang_l = ($Utils.parseDouble($!txtF9) / $!Utils.parseDouble($!purata_masa_borang_l))*100 )
    	
    	<td class="row2">
     		<input name="efisensi_borang_l" type="hidden" id="efisensi_borang_l" value="$Utils.format2Decimal($efisensi_borang_l)" >
    		<div align="center"><font color="blue"><b>$Utils.format2Decimal($efisensi_borang_l)%</b></font></div>
    	</td>
    	
  </tr>
  
  <tr>
    	<td colspan="6"><b>11.</b>$ProsesTungguC</td>
  </tr>
  
  <tr>
   	 	<td class="row2"><b>12.</b>$!ProsesPPK10</td>
    	<td class="row2" align="center"><b>$!txtF10</b>
      		<input name="HtxtF10" type="hidden" id="HtxtF10" size="5" maxlength="5" value="$!txtF10">
   		</td>
    
    	<td class="row2" align="center"><b>$!jumlah_aktiviti_borang_n</b>
        	<input name="jumlah_aktiviti_borang_n" type="hidden" id="jumlah_aktiviti_borang_n" size="5" maxlength="5" value="$!jumlah_aktiviti_borang_n">
      	</td>
    
    	<td class="row2" align="center"><b>$!jumlah_hari_borang_n</b>
        	<input name="jumlah_hari_borang_n" type="hidden" id="jumlah_hari_borang_n" size="5" maxlength="5" value="$!jumlah_hari_borang_n">
      	</td>
    
    	<td class="row2" align="center">
      		<input name="purata_masa_borang_n" type="hidden" id="purata_masa_borang_n" value="$!purata_masa_borang_n" />
    		<font color="blue"><b>$!purata_masa_borang_n</b></font>
    	</td>
    	
    	#set($efisensi_borang_n = 0)
    	#set($efisensi_borang_n = ($Utils.parseDouble($!txtF10) / $!Utils.parseDouble($!purata_masa_borang_n))*100 )
    	
    	<td class="row2">
     		<input name="efisensi_borang_n" type="hidden" id="efisensi_borang_n" value="$Utils.format2Decimal($efisensi_borang_n)" >
    		<div align="center"><font color="blue"><b>$Utils.format2Decimal($efisensi_borang_n)%</b></font></div>
    	</td>
    	
  </tr>
  
  <tr>
    	<td colspan="6"><b>13.</b>$ProsesTungguD</td>
  </tr>
  
  <tr>
   	 	<td class="row2"><b>14.</b>$!ProsesPPK11</td>
    	<td class="row2" align="center"><b>$!txtF11</b>
      		<input name="HtxtF11" type="hidden" id="HtxtF11" size="5" maxlength="5" value="$!txtF11">
   		</td>
    
    	<td class="row2" align="center"><b>$!jumlah_aktiviti_borang_j_mt</b>
        	<input name="jumlah_aktiviti_borang_j_mt" type="hidden" id="jumlah_aktiviti_borang_j_mt" size="5" maxlength="5" value="$!jumlah_aktiviti_borang_j_mt">
      	</td>
    
    	<td class="row2" align="center"><b>$!jumlah_hari_borang_j_mt</b>
        	<input name="jumlah_hari_borang_j_mt" type="hidden" id="jumlah_hari_borang_j_mt" size="5" maxlength="5" value="$!jumlah_hari_borang_j_mt">
      	</td>
    
    	<td class="row2" align="center">
      		<input name="purata_masa_borang_j_mt" type="hidden" id="purata_masa_borang_j_mt" value="$!purata_masa_borang_j_mt" />
    		<font color="blue"><b>$!purata_masa_borang_j_mt</b></font>
    	</td>
    	
    	#set($efisensi_borang_j_mt = 0)
    	#set($efisensi_borang_j_mt = ($Utils.parseDouble($!txtF11) / $!Utils.parseDouble($!purata_masa_borang_j_mt))*100 )
    	
    	<td class="row2">
     		<input name="efisensi_borang_j_mt" type="hidden" id="efisensi_borang_j_mt" value="$Utils.format2Decimal($efisensi_borang_j_mt)" >
    		<div align="center"><font color="blue"><b>$Utils.format2Decimal($efisensi_borang_j_mt)%</b></font></div>
    	</td>
    	
  </tr>
  
  <tr>
    	<td colspan="6"><b>15.</b>$ProsesTungguE</td>
  </tr>
  
  <tr>
   	 	<td class="row2"><b>16.</b>$!ProsesPPK12</td>
    	<td class="row2" align="center"><b>$!txtF12</b>
      		<input name="HtxtF12" type="hidden" id="HtxtF12" size="5" maxlength="5" value="$!txtF12">
   		</td>
    
    	<td class="row2" align="center"><b>$!jumlah_aktiviti_keputusan_mt</b>
        	<input name="jumlah_aktiviti_keputusan_mt" type="hidden" id="jumlah_aktiviti_keputusan_mt" size="5" maxlength="5" value="$!jumlah_aktiviti_keputusan_mt">
      	</td>
    
    	<td class="row2" align="center"><b>$!jumlah_hari_keputusan_mt</b>
        	<input name="jumlah_hari_keputusan_mt" type="hidden" id="jumlah_hari_keputusan_mt" size="5" maxlength="5" value="$!jumlah_hari_keputusan_mt">
      	</td>
    
    	<td class="row2" align="center">
      		<input name="purata_masa_keputusan_mt" type="hidden" id="purata_masa_keputusan_mt" value="$!purata_masa_keputusan_mt" />
    		<font color="blue"><b>$!purata_masa_keputusan_mt</b></font>
    	</td>
    	
    	#set($efisensi_keputusan_mt = 0)
    	#set($efisensi_keputusan_mt = ($Utils.parseDouble($!txtF12) / $!Utils.parseDouble($!purata_masa_keputusan_mt))*100 )
    	
    	<td class="row2">
     		<input name="efisensi_keputusan_mt" type="hidden" id="efisensi_keputusan_mt" value="$Utils.format2Decimal($efisensi_keputusan_mt)" >
    		<div align="center"><font color="blue"><b>$Utils.format2Decimal($efisensi_keputusan_mt)%</b></font></div>
    	</td>
  </tr>
  
  <tr>
   	 	<td class="row2"><b>17.</b>$!ProsesPPK13</td>
    	<td class="row2" align="center"><b>$!txtF13</b>
      		<input name="HtxtF13" type="hidden" id="HtxtF13" size="5" maxlength="5" value="$!txtF13">
   		</td>
    
    	<td class="row2" align="center"><b>$!jumlah_aktiviti_borang_j_rots</b>
        	<input name="jumlah_aktiviti_borang_j_rots" type="hidden" id="jumlah_aktiviti_borang_j_rots" size="5" maxlength="5" value="$!jumlah_aktiviti_borang_j_rots">
      	</td>
    
    	<td class="row2" align="center"><b>$!jumlah_hari_borang_j_rots</b>
        	<input name="jumlah_hari_borang_j_rots" type="hidden" id="jumlah_hari_borang_j_rots" size="5" maxlength="5" value="$!jumlah_hari_borang_j_rots">
      	</td>
    
    	<td class="row2" align="center">
      		<input name="purata_masa_borang_j_rots" type="hidden" id="purata_masa_borang_j_rots" value="$!purata_masa_borang_j_rots" />
    		<font color="blue"><b>$!purata_masa_borang_j_rots</b></font>
    	</td>
    	
    	#set($efisensi_borang_j_rots = 0)
    	#set($efisensi_borang_j_rots = ($Utils.parseDouble($!txtF13) / $!Utils.parseDouble($!purata_masa_borang_j_rots))*100 )
    	
    	<td class="row2">
     		<input name="efisensi_borang_j_rots" type="hidden" id="efisensi_borang_j_rots" value="$Utils.format2Decimal($efisensi_borang_j_rots)" >
    		<div align="center"><font color="blue"><b>$Utils.format2Decimal($efisensi_borang_j_rots)%</b></font></div>
    	</td>
    	
  </tr>
  
  <tr>
    	<td colspan="6"><b>18.</b>$ProsesTungguF</td>
  </tr>
  
  <tr>
   	 	<td class="row2"><b>19.</b>$!ProsesPPK14</td>
    	<td class="row2" align="center"><b>$!txtF14</b>
      		<input name="HtxtF14" type="hidden" id="HtxtF14" size="5" maxlength="5" value="$!txtF14">
   		</td>
    
    	<td class="row2" align="center"><b>$!jumlah_aktiviti_keputusan_rots</b>
        	<input name="jumlah_aktiviti_keputusan_rots" type="hidden" id="jumlah_aktiviti_keputusan_rots" size="5" maxlength="5" value="$!jumlah_aktiviti_keputusan_rots">
      	</td>
    
    	<td class="row2" align="center"><b>$!jumlah_hari_keputusan_rots</b>
        	<input name="jumlah_hari_keputusan_rots" type="hidden" id="jumlah_hari_keputusan_rots" size="5" maxlength="5" value="$!jumlah_hari_keputusan_rots">
      	</td>
    
    	<td class="row2" align="center">
      		<input name="purata_masa_keputusan_rots" type="hidden" id="purata_masa_keputusan_rots" value="$!purata_masa_keputusan_rots" />
    		<font color="blue"><b>$!purata_masa_keputusan_rots</b></font>
    	</td>
    	
    	#set($efisensi_keputusan_rots = 0)
    	#set($efisensi_keputusan_rots = ($Utils.parseDouble($!txtF14) / $!Utils.parseDouble($!purata_masa_keputusan_rots))*100 )
    	
    	<td class="row2">
     		<input name="efisensi_keputusan_rots" type="hidden" id="efisensi_keputusan_rots" value="$Utils.format2Decimal($efisensi_keputusan_rots)" >
    		<div align="center"><font color="blue"><b>$Utils.format2Decimal($efisensi_keputusan_rots)%</b></font></div>
    	</td>
  </tr>
  
  <tr>
   	 	<td class="row2"><b>20.</b>$!ProsesPPK15</td>
    	<td class="row2" align="center"><b>$!txtF15</b>
      		<input name="HtxtF15" type="hidden" id="HtxtF15" size="5" maxlength="5" value="$!txtF15">
   		</td>
    
    	<td class="row2" align="center"><b>$!jumlah_aktiviti_sedia_perintah</b>
        	<input name="jumlah_aktiviti_sedia_perintah" type="hidden" id="jumlah_aktiviti_sedia_perintah" size="5" maxlength="5" value="$!jumlah_aktiviti_sedia_perintah">
      	</td>
    
    	<td class="row2" align="center"><b>$!jumlah_hari_sedia_perintah</b>
        	<input name="jumlah_hari_sedia_perintah" type="hidden" id="jumlah_hari_sedia_perintah" size="5" maxlength="5" value="$!jumlah_hari_sedia_perintah">
      	</td>
    
    	<td class="row2" align="center">
      		<input name="purata_masa_sedia_perintah" type="hidden" id="purata_masa_sedia_perintah" value="$!purata_masa_sedia_perintah" />
    		<font color="blue"><b>$!purata_masa_sedia_perintah</b></font>
    	</td>
    	
    	#set($efisensi_sedia_perintah = 0)
    	#set($efisensi_sedia_perintah = ($Utils.parseDouble($!txtF15) / $!Utils.parseDouble($!purata_masa_sedia_perintah))*100 )
    	
    	<td class="row2">
     		<input name="efisensi_sedia_perintah" type="hidden" id="efisensi_sedia_perintah" value="$Utils.format2Decimal($efisensi_sedia_perintah)" >
    		<div align="center"><font color="blue"><b>$Utils.format2Decimal($efisensi_sedia_perintah)%</b></font></div>
    	</td>
  </tr>
  
</table>